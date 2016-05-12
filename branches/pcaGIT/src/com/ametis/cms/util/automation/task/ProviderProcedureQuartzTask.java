/**
 * 
 */
package com.ametis.cms.util.automation.task;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.ProviderDoctor;
import com.ametis.cms.datamodel.ProviderProcedure;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ProviderProcedureService;
import com.ametis.cms.util.parser.ProviderProcedureParser;

/**
 * @author Mashuri Hasan
 *
 */
public class ProviderProcedureQuartzTask {
	private ProviderProcedureParser parser;
	private ProviderProcedureService providerProcedureService;
	
	

	public ProviderProcedureParser getParser() {
		return parser;
	}



	public void setParser(ProviderProcedureParser parser) {
		this.parser = parser;
	}



	public ProviderProcedureService getProviderProcedureService() {
		return providerProcedureService;
	}



	public void setProviderProcedureService(
			ProviderProcedureService providerProcedureService) {
		this.providerProcedureService = providerProcedureService;
	}



	public void executeParse(){
		try {
			ActionUser user = new ActionUser();
			User theUser = new User();
			theUser.setUsername("parser");
			user.setUser(theUser);

			ArrayList<String> errorList = new ArrayList<String>();
			String[] eqParam = {"deletedStatus","statusUpdate","type"};
			Object[] eqValue = {0,1,"upload"};
			
			int total = providerProcedureService.getTotal(null,null,eqParam,eqValue);
			Collection<ProviderProcedure> providerDoctorList = providerProcedureService.search(null,null,eqParam,eqValue,0,total);
			
			for(ProviderProcedure item : providerDoctorList) {
				parser.parseFile(new File(item.getUrl()), errorList);
				item.setModifiedTime(new Timestamp(System.currentTimeMillis()));
				item.setModifiedBy("ProviderDoctorParser");
				if(errorList.size() == 0) {
					item.setStatusUpdate(1);
				}
				else {
					item.setStatusUpdate(2);
					String errors = "";
					for(String error : errorList) {
						errors += error + "\n";
					}
					item.setUploadNote(errors);
				}
				providerProcedureService.update(item,user);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
