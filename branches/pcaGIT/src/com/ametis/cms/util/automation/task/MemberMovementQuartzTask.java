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
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.util.LoggingUtil;
import com.ametis.cms.util.parser.MemberMovementParser;

public class MemberMovementQuartzTask {
	private ConfigurationService configurationService;
	private MemberMovementParser parser;
	
	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public MemberMovementParser getParser() {
		return parser;
	}

	public void setParser(MemberMovementParser parser) {
		this.parser = parser;
	}

	public void executeParse(){
		try {
			
			ActionUser user = new ActionUser();
			User theUser = new User();
			theUser.setUsername("parser");
			user.setUser(theUser);
			
			Logger logger = Logger.getLogger("membermovement-App");

		    FileHandler logFile;
		    SimpleFormatter simpleFormatter = new SimpleFormatter();
		    try {
		      logFile = new FileHandler(LoggingUtil.getLogDirectory() + "membermovement-app-%g.log", 5242880, 10, true);
		      logFile.setFormatter(simpleFormatter);
		      logger.addHandler(logFile);
		    } catch (IOException e) {
		      String warning = "Failure Initializing Logging";
		      logger.severe(warning);
		    }

//		    ConfigurationService configurationService = new ConfigurationService();
//		    List<Configuration> configurationList = configurationService.getAll();
		    ArrayList<String> errorList = new ArrayList<String>();
		    String[] eqParam = {"deletedStatus","statusUpdate","type"};
		    Object[] eqValue = {1,0,"upload"};
		    
		    int total = configurationService.getTotal(null, null, eqParam, eqValue);
		    
		    Collection<Configuration> configurationList = configurationService.search(null, null, eqParam, eqValue, 0, total);
		    
		    for(Configuration item : configurationList) {
		      String uploadStorageDirectory = item.getUploadStorageDirectory();
		      Path pathRoot = Paths.get(uploadStorageDirectory, "membermovement");
		      logger.info("Processing " + pathRoot.toString());

		      try {
		        Files.createDirectory(pathRoot);
		      } catch(FileAlreadyExistsException faee) {
		        logger.warning(pathRoot.toString() + " already exists");
		      } catch (IOException e) {
		        String msg = "Error creating " + pathRoot.toString();
		        logger.warning(msg);
		        errorList.add(msg);
		      }

		      Path pathFailed = Paths.get(pathRoot.toString(), "failed");
		      Path pathProcessing = Paths.get(pathRoot.toString(), "processing");
		      Path pathProcessed = Paths.get(pathRoot.toString(), "processed");

		      try {
		        Files.createDirectory(pathFailed);
		      } catch(FileAlreadyExistsException faee) {
		        logger.warning(pathFailed.toString() + " already exists");
		      } catch (IOException e) {
		        String msg = "Error creating " + pathFailed.toString();
		        logger.warning(msg);
		        errorList.add(msg);
		      }
		      try {
		        Files.createDirectory(pathProcessing);
		      } catch(FileAlreadyExistsException faee) {
		        logger.warning(pathProcessing.toString() + " already exists");
		      } catch (IOException e) {
		        String msg = "Error creating " + pathProcessing.toString();
		        logger.warning(msg);
		        errorList.add(msg);
		      }
		      try {
		        Files.createDirectory(pathProcessed);
		      } catch(FileAlreadyExistsException faee) {
		        logger.warning(pathProcessed.toString() + " already exists");
		      } catch (IOException e) {
		        String msg = "Error creating " + pathProcessed.toString();
		        logger.warning(msg);
		        errorList.add(msg);
		      }

		      logger.info("Failed Processing will go to " + pathFailed);
		      logger.info("Successful Processing will go to " + pathProcessed);

		      DirectoryStream<Path> stream = null;

		      if(Files.isDirectory(pathRoot)) {
		        try {
		          stream = Files.newDirectoryStream(pathRoot);
		          for(Path path : stream) {
		            if(Files.isRegularFile(path)) {
		              Files.move(path, Paths.get(pathProcessing.toString(), path.getFileName().toString()),  StandardCopyOption.REPLACE_EXISTING);
		            }
		          }
		          stream.close();
		        } catch(IOException ioe) {
		          String msg = "Error reading " + pathRoot;
		          logger.warning(msg);
		          errorList.add(msg);
		        }
		      }

		      if(Files.isDirectory(pathProcessing)) {
		        try {
		          stream = Files.newDirectoryStream(pathProcessing);
		          for(Path path : stream) {
		            if(Files.isRegularFile(path)) {
		              logger.info("Processing " + path.toString());
		              parser.parseFile(path.toFile(), errorList);

		              if(errorList.size() > 0) {
		                Files.move(path, Paths.get(pathFailed.toString(), path.getFileName().toString()), StandardCopyOption.REPLACE_EXISTING);
		                for(String s : errorList) {
		                  logger.severe(s);
		                }
		              }
		              else {
		                Files.move(path, Paths.get(pathProcessed.toString(), path.getFileName().toString()), StandardCopyOption.REPLACE_EXISTING);
		              }
		            }
		          }
		        } catch(IOException ioe) {
		          String msg = "Error reading " + pathProcessing;
		          logger.warning(msg);
		          errorList.add(msg);
		        }
		      }
		    }
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
