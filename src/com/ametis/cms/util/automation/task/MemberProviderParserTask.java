package com.ametis.cms.util.automation.task;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.MemberProvider;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.MemberProviderService;
import com.ametis.cms.util.parser.MemberProviderParser;

/**
 * User: ZaQ <zaki.rahman@gmail.com>
 */
public class MemberProviderParserTask {

	private MemberProviderService memberProviderService;
	private ConfigurationService configurationService;
	private MemberProviderParser parser;

	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public MemberProviderService getMemberProviderService() {
		return memberProviderService;
	}

	public void setMemberProviderService(MemberProviderService memberProviderService) {
		this.memberProviderService = memberProviderService;
	}

	public MemberProviderParser getParser() {
		return parser;
	}

	public void setParser(MemberProviderParser parser) {
		this.parser = parser;
	}

	public void executeParse() {

		ActionUser user = new ActionUser();
		User theUser = new User();
		theUser.setUsername("parser");
		user.setUser(theUser);

		ArrayList<String> errorList = new ArrayList<String>();

		try {

			String[] eqParam = { "deletedStatus", "statusUpdate", "type" };
			Object[] eqValue = { 1, 0, "upload" };

			int total = memberProviderService.getTotal(null, null, eqParam, eqValue);

			Collection<MemberProvider> providerDoctorList = memberProviderService.search(null, null, eqParam, eqValue,
					0, total);

			for (MemberProvider item : providerDoctorList) {
				parser.parseFile(new File(item.getUrl()), errorList);
				if (errorList.size() == 0) {
					item.setStatusUpdate(1);
				} else {
					item.setStatusUpdate(2);
					String errors = "";
					for (String error : errorList) {
						errors += error + "\n";
					}
					item.setUploadNote(errors);
				}
				memberProviderService.update(item, user);
			}
			Collection<Configuration> configurationList = configurationService.getAll();
			for (Configuration item : configurationList) {
				errorList = new ArrayList<String>();

				String uploadStorageDirectory = item.getUploadStorageDirectory();
				Path pathRoot = Paths.get(uploadStorageDirectory, "memberprovider");

				System.out.println("[MemberProviderParser] Processing " + pathRoot.toString());
				createDirectory(pathRoot, errorList);

				Path pathFailed = Paths.get(pathRoot.toString(), "failed");
				Path pathProcessing = Paths.get(pathRoot.toString(), "processing");
				Path pathProcessed = Paths.get(pathRoot.toString(), "processed");

				createDirectory(pathFailed, errorList);
				createDirectory(pathProcessing, errorList);
				createDirectory(pathProcessed, errorList);

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
						System.out.println("[MemberProviderParser] " + msg);
						errorList.add(msg);
					}
				}

				try {
					stream = Files.newDirectoryStream(pathProcessing);
					for (Path path : stream) {

						System.out.println("[MemberProviderParser] Processing " + path.toString());
						parser.parseFile(path.toFile(), errorList);

						if (errorList.size() > 0) {
							Files.move(path, Paths.get(pathFailed.toString(), path.getFileName().toString()),
									StandardCopyOption.REPLACE_EXISTING);
							for (String s : errorList) {
								System.out.println("[MemberProviderParser] " + s);
							}
						} else {
							Files.move(path, Paths.get(pathProcessed.toString(), path.getFileName().toString()),
									StandardCopyOption.REPLACE_EXISTING);
						}
					}
				} catch (IOException e) {
					String msg = "Error reading directory " + pathProcessing.toString();
					System.out.println("[MemberProviderParser] " + msg);
					errorList.add(msg);
				}
			}
		} catch (Exception e) {
			String msg = "Error reading directory ";
			System.out.println("[MemberProviderParser] " + msg);
			errorList.add(msg);
		}
	}

	public static void createDirectory(Path path, ArrayList<String> errorList) {
		try {
			Files.createDirectory(path);
		} catch (FileAlreadyExistsException faee) {
			// already exists
		} catch (IOException e) {

			String msg = "Error Creating " + path.toString();

			System.out.println("[MemberProviderParser] " + msg);

			errorList.add(msg);
		}
	}
}
