package com.ametis.cms.util.automation.task;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.ProviderPoliklinik;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ProviderPoliklinikService;
import com.ametis.cms.util.parser.PoliklinikParser;

public class PoliklinikQuartzTask {
	private ProviderPoliklinikService providerPoliklinikService;
	private PoliklinikParser parser;

	public ProviderPoliklinikService getProviderPoliklinikService() {
		return providerPoliklinikService;
	}

	public void setProviderPoliklinikService(ProviderPoliklinikService providerPoliklinikService) {
		this.providerPoliklinikService = providerPoliklinikService;
	}

	public PoliklinikParser getParser() {
		return parser;
	}

	public void setParser(PoliklinikParser parser) {
		this.parser = parser;
	}

	public void executeParse() {
		try {
			ActionUser user = new ActionUser();
			User theUser = new User();
			theUser.setUsername("parser");
			user.setUser(theUser);

			String[] eqParam = { "deletedStatus", "statusUpdate", "type" };
			Object[] eqValue = { 1, 0, "upload" };

			int total = providerPoliklinikService.getTotal(null, null, eqParam, eqValue);

			Collection<ProviderPoliklinik> providerPoliklinikList = providerPoliklinikService.search(null, null,
					eqParam, eqValue, 0, total);

			ArrayList<String> errorList = new ArrayList<String>();
			for (ProviderPoliklinik pp : providerPoliklinikList) {
				parser.parseFile(new File(pp.getUrl()), errorList);
				pp.setModifiedTime(new Timestamp(System.currentTimeMillis()));
				pp.setModifiedBy("ProviderPoliklinikParser");
				if (errorList.size() == 0) {
					pp.setStatusUpdate(1);
				} else {
					pp.setStatusUpdate(2);
					String errors = "";
					for (String error : errorList) {
						errors += error + "\n";
					}
					pp.setUploadNote(errors);
				}
				providerPoliklinikService.update(pp, user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
