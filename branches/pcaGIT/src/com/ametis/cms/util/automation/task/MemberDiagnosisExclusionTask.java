package com.ametis.cms.util.automation.task;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.MemberDiagnosisExclusion;
import com.ametis.cms.datamodel.PolicyDiagnosisExclusion;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.MemberDiagnosisExclusionService;
import com.ametis.cms.util.parser.MemberDiagnosisExclusionParser;

public class MemberDiagnosisExclusionTask {

	private MemberDiagnosisExclusionService memberDiagnosisExclusionService;
	private MemberDiagnosisExclusionParser parser;

	public MemberDiagnosisExclusionService getMemberDiagnosisExclusionService() {
		return memberDiagnosisExclusionService;
	}

	public void setMemberDiagnosisExclusionService(MemberDiagnosisExclusionService memberDiagnosisExclusionService) {
		this.memberDiagnosisExclusionService = memberDiagnosisExclusionService;
	}

	public MemberDiagnosisExclusionParser getParser() {
		return parser;
	}

	public void setParser(MemberDiagnosisExclusionParser parser) {
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

			int total = memberDiagnosisExclusionService.getTotal(null, null, eqParam, eqValue);

			Collection<MemberDiagnosisExclusion> memberDiagnosisExclusionsList = memberDiagnosisExclusionService
					.search(null, null, eqParam, eqValue, 0, total);

			for (MemberDiagnosisExclusion item : memberDiagnosisExclusionsList) {
				
				parser.parseFile(new File(item.getUrl()), errorList);
				item.setModifiedTime(new Timestamp(System.currentTimeMillis()));
				item.setModifiedBy("MemberDiagnosisExclusionParser");
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
				memberDiagnosisExclusionService.update(item, user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
