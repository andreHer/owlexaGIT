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

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.util.parser.PolicyParser;

public class PolicyQuartzTask {

	private PolicyParser parser;
	private ConfigurationService configurationService;
	private PolicyService policyService;

	public PolicyService getPolicyService() {
		return policyService;
	}

	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}

	public PolicyParser getParser() {
		return parser;
	}

	public void setParser(PolicyParser parser) {
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

			ActionUser user = new ActionUser();
			User theUser = new User();
			theUser.setUsername("parser");
			user.setUser(theUser);

			ArrayList<String> errorList = new ArrayList<String>();


			Collection<Configuration> configurationList = configurationService.getAll();

			for (Configuration item : configurationList) {
				String companyCode = item.getCompanyCode();
				
				String uploadStorageDirectory = item.getUploadStorageDirectory();
				
				
				Path pathRoot = Paths.get(uploadStorageDirectory, "policy");
				//System.out.println("[Policy-Parser]  Processing " + pathRoot.toString());
				initDirectory(pathRoot, errorList);

				Path pathFailed = Paths.get(pathRoot.toString(), "failed");
				Path pathProcessing = Paths.get(pathRoot.toString(), "processing");
				Path pathProcessed = Paths.get(pathRoot.toString(), "processed");

				initDirectory(pathFailed, errorList);
				initDirectory(pathProcessing, errorList);
				initDirectory(pathProcessed, errorList);

				DirectoryStream<Path> stream = null;
				if (Files.isDirectory(pathRoot)) {
					try {
						stream = Files.newDirectoryStream(pathRoot);
						for (Path path : stream) {
							if (Files.isRegularFile(path)) {
								Files.move(path, Paths.get(pathProcessing.toString(), path.getFileName().toString()),
										StandardCopyOption.REPLACE_EXISTING);
							}
						}
						stream.close();
					} catch (IOException ioe) {
						String msg = "Error reading " + pathRoot;
						System.out.println("[Policy-Parser] " + msg);
						errorList.add(msg);
					}
				}

				if (Files.isDirectory(pathProcessing)) {
					try {
						stream = Files.newDirectoryStream(pathProcessing);
						for (Path path : stream) {
							if (Files.isRegularFile(path)) {
								//System.out.println("[Policy-Parser] Processing " + path.toString());
								parser.parseFile(path.toFile(), errorList);

								if (errorList.size() > 0) {
									Files.move(path, Paths.get(pathFailed.toString(), path.getFileName().toString()),
											StandardCopyOption.REPLACE_EXISTING);
									for (String s : errorList) {
										System.out.println("[Policy-Parser] " + s);
									}
								} else {
									Files.move(path, Paths.get(pathProcessed.toString(), path.getFileName().toString()),
											StandardCopyOption.REPLACE_EXISTING);
								}
							}
						}
						
						
						stream.close();
						
					} catch (IOException ioe) {
						String msg = "Error reading " + pathProcessing;
						System.out.println("[Policy-Parser] " + msg);
						errorList.add(msg);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void initDirectory(Path path, ArrayList<String> errorList) {
		try {
			
			if(!Files.exists(path)){
				Files.createDirectory(path);
				
			}			
			
		} catch (FileAlreadyExistsException faee) {
			// already exists
			
		} catch (IOException e) {
			String msg = "Error creating " + path.toString();
			System.out.println("[Policy-Parser] " + msg);
			errorList.add(msg);
		}
	}

}
