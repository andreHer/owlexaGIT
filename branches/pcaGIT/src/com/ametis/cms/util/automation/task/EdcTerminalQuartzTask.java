package com.ametis.cms.util.automation.task;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.EdcTerminal;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.EdcTerminalService;
import com.ametis.cms.util.parser.EdcTerminalParser;

public class EdcTerminalQuartzTask {
	private EdcTerminalService edcTerminalService;
	private EdcTerminalParser parser;
	
	
	public EdcTerminalService getEdcTerminalService() {
		return edcTerminalService;
	}

	public void setEdcTerminalService(EdcTerminalService edcTerminalService) {
		this.edcTerminalService = edcTerminalService;
	}

	public EdcTerminalParser getParser() {
		return parser;
	}

	public void setParser(EdcTerminalParser parser) {
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

			int total = edcTerminalService.getTotal(null, null, eqParam, eqValue);

			Collection<EdcTerminal> edcTerminalList = edcTerminalService.search(null, null, eqParam, eqValue, false,"id", 0, total);

			ArrayList<String> errorList = new ArrayList<String>();
			for (EdcTerminal item : edcTerminalList) {
				parser.parseFile(new File(item.getUrl()), errorList);
				item.setModifiedTime(new Timestamp(System.currentTimeMillis()));
				item.setModifiedBy("EDCTerminalParser");
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

				edcTerminalService.update(item, user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
