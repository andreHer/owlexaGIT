package com.ametis.cms.util.automation.task;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.MemberGroupProvider;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.MemberGroupProviderService;
import com.ametis.cms.util.parser.MemberGroupProviderParser;

/**
 * User: ZaQ <zaki.rahman@gmail.com>
 */
public class MemberGroupProviderParserTask {

	private MemberGroupProviderService memberGroupProviderService;
	private MemberGroupProviderParser parser;

	public MemberGroupProviderService getMemberGroupProviderService() {
		return memberGroupProviderService;
	}

	public void setMemberGroupProviderService(MemberGroupProviderService memberGroupProviderService) {
		this.memberGroupProviderService = memberGroupProviderService;
	}

	public MemberGroupProviderParser getParser() {
		return parser;
	}

	public void setParser(MemberGroupProviderParser parser) {
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

			int total = memberGroupProviderService.getTotal(null, null, eqParam, eqValue);

			Collection<MemberGroupProvider> groupProviderList = memberGroupProviderService.search(null, null, eqParam,
					eqValue, 0, total);

			for (MemberGroupProvider item : groupProviderList) {
				parser.parseFile(new File(item.getUrl()), errorList);
				item.setModifiedTime(new Timestamp(System.currentTimeMillis()));
				item.setModifiedBy("MemberGroupProviderParser");
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
				memberGroupProviderService.update(item, user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
