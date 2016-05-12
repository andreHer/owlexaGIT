package com.ametis.cms.util.automation.task;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.PolicyProvider;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.PolicyProviderService;
import com.ametis.cms.util.parser.PolicyProviderParser;

public class PolicyProviderQuartzTask {
	private PolicyProviderService policyProviderService;
	private PolicyProviderParser parser;
	
	public PolicyProviderService getPolicyProviderService() {
		return policyProviderService;
	}


	public void setPolicyProviderService(PolicyProviderService policyProviderService) {
		this.policyProviderService = policyProviderService;
	}


	public PolicyProviderParser getParser() {
		return parser;
	}


	public void setParser(PolicyProviderParser parser) {
		this.parser = parser;
	}


	public void executeParse(){
		try {
			
			ActionUser user = new ActionUser();
			User theUser = new User();
			theUser.setUsername("parser");
			user.setUser(theUser);
			
			ArrayList<String> errorList = new ArrayList<String>();
			String[] eqParam = {"deletedStatus","statusUpdate","type"};
			Object[] eqValue = {1,0,"upload"};
			
			int total = policyProviderService.getTotal(null, null, eqParam, eqValue);
			
			Collection<PolicyProvider> policyProviderList = policyProviderService.search(null, null, eqParam, eqValue, 0, total);
		    
		    for(PolicyProvider item : policyProviderList) {
		      parser.parseFile(new File(item.getUrl()), errorList);
		      item.setModifiedTime(new Timestamp(System.currentTimeMillis()));
		      item.setModifiedBy("PolicyProviderParser");
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
		      policyProviderService.update(item, user);
		    }
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
