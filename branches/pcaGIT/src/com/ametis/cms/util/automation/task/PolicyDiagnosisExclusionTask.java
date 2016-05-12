package com.ametis.cms.util.automation.task;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.PolicyDiagnosisExclusion;
import com.ametis.cms.datamodel.ProviderDoctor;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.PolicyDiagnosisExclusionService;
import com.ametis.cms.util.parser.PolicyDiagnosisExclusionParser;

public class PolicyDiagnosisExclusionTask {

	private PolicyDiagnosisExclusionService policyDiagnosisExclusionService;
	private PolicyDiagnosisExclusionParser parser;

	public PolicyDiagnosisExclusionService getPolicyDiagnosisExclusionService() {
		return policyDiagnosisExclusionService;
	}

	public void setPolicyDiagnosisExclusionService(PolicyDiagnosisExclusionService policyDiagnosisExclusionService) {
		this.policyDiagnosisExclusionService = policyDiagnosisExclusionService;
	}

	public PolicyDiagnosisExclusionParser getParser() {
		return parser;
	}

	public void setParser(PolicyDiagnosisExclusionParser parser) {
		this.parser = parser;
	}

	public void executeParse() {
		try {

			ActionUser user = new ActionUser();
			User theUser = new User();
			theUser.setUsername("parser");
			user.setUser(theUser);
			ArrayList<String> errorList = new ArrayList<String>();
			String[] eqParam = { "deletedStatus", "statusUpdate", "type" };
			Object[] eqValue = { 1, 0, "upload" };

			int total = policyDiagnosisExclusionService.getTotal(null, null, eqParam, eqValue);

			Collection<PolicyDiagnosisExclusion> policyDiagnosisExclusionsList = policyDiagnosisExclusionService
					.search(null, null, eqParam, eqValue, 0, total);

			for (PolicyDiagnosisExclusion item : policyDiagnosisExclusionsList) {
				
				parser.parseFile(new File(item.getUrl()), errorList);
				item.setModifiedTime(new Timestamp(System.currentTimeMillis()));
				item.setModifiedBy("PolicyDiagnosisExclusionParser");
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
				policyDiagnosisExclusionService.update(item, user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
