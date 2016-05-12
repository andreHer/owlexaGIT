package com.ametis.cms.util.automation.task;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.util.parser.ClaimPaymentParser;

public class ClaimPaymentQuartzTask {
	
	private ClaimPaymentParser parser;
	private ConfigurationService configurationService;
	
	public ClaimPaymentParser getParser() {
		return parser;
	}

	public void setParser(ClaimPaymentParser parser) {
		this.parser = parser;
	}

	public ConfigurationService getConfigurationService() {
		return configurationService;
	}
	
	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}
	

	public void executeParse() {
		try {
			Logger logger = Logger.getLogger("claimpayment-App");
			
			ActionUser user = new ActionUser();
			User theUser = new User();
			theUser.setUsername("parser");
			user.setUser(theUser);
			
			String[] eqParam = {"deletedStatus","statusUpdate","type"};
			Object[] eqValue = {1,0,"upload"};
			
			int total = configurationService.getTotal(null,null,eqParam,eqValue);
			
		    Collection<Configuration> configurationList = configurationService.search(null, null, eqParam, eqValue, 0, total);
		    
		    for(Configuration item : configurationList) {
		      ArrayList<String> errorList = new ArrayList<String>();

		      String uploadStorageDirectory = item.getUploadStorageDirectory();
		      Path pathRoot = Paths.get(uploadStorageDirectory, "claimpay");
		      logger.info("Processing " + pathRoot.toString());
		      createDirectory(pathRoot, logger, errorList);

		      Path pathFailed = Paths.get(pathRoot.toString(), "failed");
		      Path pathProcessing = Paths.get(pathRoot.toString(), "processing");
		      Path pathProcessed = Paths.get(pathRoot.toString(), "processed");

		      createDirectory(pathFailed, logger, errorList);
		      createDirectory(pathProcessing, logger, errorList);
		      createDirectory(pathProcessed, logger, errorList);

		      DirectoryStream<Path> stream = null;
		      if(Files.isDirectory(pathRoot)) {
		        moveFromTo(stream, pathRoot, pathProcessing, logger, errorList);
		      }

		      try {
		        stream = Files.newDirectoryStream(pathProcessing);
		        for(Path path : stream) {
		          logger.info("Processing " + path.toString());
		          parser.parseFile(path.toFile(), errorList, item);

		          if(errorList.size() > 0) {
		            Files.move(path, Paths.get(pathFailed.toString(), path.getFileName().toString()), StandardCopyOption.REPLACE_EXISTING);
		            for(String s : errorList) {
		              logger.severe(s);
		            }
		          } else {
		            Files.move(path, Paths.get(pathProcessed.toString(), path.getFileName().toString()), StandardCopyOption.REPLACE_EXISTING);
		          }
		        }
		        stream.close();
		      } catch (IOException e) {
		        String msg = "Error reading directory " + pathProcessing.toString();
		        logger.severe(msg);
		        errorList.add(msg);
		      }
		    }
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
		
	public static void moveFromTo(DirectoryStream<Path> stream, Path pathSource, Path pathDestination, Logger logger, ArrayList<String> errorList) {
	    try {
	      stream = Files.newDirectoryStream(pathSource);
	      for(Path path : stream) {
	        if(Files.isRegularFile(path)) {
	          Files.move(path, Paths.get(pathDestination.toString(), path.getFileName().toString()), StandardCopyOption.REPLACE_EXISTING);
	        }
	        stream.close();
	      }
	    } catch (IOException e) {
	      String msg = "Error reading directory " + pathSource.toString();
	      logger.severe(msg);
	      errorList.add(msg);
	    }
	  }

	  public static void createDirectory(Path path, Logger logger, ArrayList<String> errorList) {
	    try {
	      Files.createDirectory(path);
	    } catch(FileAlreadyExistsException faee) {
	      // already exists
	    } catch (IOException e) {
	      String msg = "Error Creating " + path.toString();
	      logger.warning(msg);
	      errorList.add(msg);
	    }
	  }
}
