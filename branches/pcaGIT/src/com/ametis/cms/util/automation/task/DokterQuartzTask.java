package com.ametis.cms.util.automation.task;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.ProviderDoctor;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ProviderDoctorService;
import com.ametis.cms.util.parser.DoctorParser;

public class DokterQuartzTask {

	private ProviderDoctorService providerDoctorService;
	private DoctorParser parser;

	public DoctorParser getParser() {
		return parser;
	}

	public void setParser(DoctorParser parser) {
		this.parser = parser;
	}

	public ProviderDoctorService getProviderDoctorService() {
		return providerDoctorService;
	}

	public void setProviderDoctorService(ProviderDoctorService providerDoctorService) {
		this.providerDoctorService = providerDoctorService;
	}

	public void executeParse() {
		try {

			ActionUser user = new ActionUser();
			User theUser = new User();
			theUser.setUsername("parser");
			user.setUser(theUser);

			
			String[] eqParam = { "deletedStatus", "statusUpdate", "type" };
			Object[] eqValue = { 1, 0, "upload" };

			int total = providerDoctorService.getTotal(null, null, eqParam, eqValue);
			System.out.println("TOTAL DOCTOR PARSER = " + total);
			ArrayList<String> errorList = new ArrayList<String>();
			Collection<ProviderDoctor> providerDoctorList = providerDoctorService.search(null, null, eqParam, eqValue,
					0, total);

			for (ProviderDoctor item : providerDoctorList) {
				
				
				parser.parseFile(new File(item.getUrl()), errorList);
				item.setModifiedTime(new Timestamp(System.currentTimeMillis()));
				item.setModifiedBy("ProviderDoctorParser");
				
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
				providerDoctorService.update(item, user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
