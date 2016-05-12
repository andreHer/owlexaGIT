package com.ametis.cms.util.automation.task;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.MemberDiagnosisExclusion;
import com.ametis.cms.datamodel.ProviderItem;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ProviderItemService;
import com.ametis.cms.util.parser.ProviderItemParser;

public class ProviderItemQuartzTask {
	
	private ProviderItemService providerItemService;
	private ProviderItemParser parser;
	
	
	
	
	public ProviderItemService getProviderItemService() {
		return providerItemService;
	}




	public void setProviderItemService(ProviderItemService providerItemService) {
		this.providerItemService = providerItemService;
	}




	public ProviderItemParser getParser() {
		return parser;
	}




	public void setParser(ProviderItemParser parser) {
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
			
			int total = providerItemService.getTotal(null,null,eqParam,eqValue);
			
			
			Collection<ProviderItem> providerItemList = providerItemService.search(null,null,eqParam,eqValue,0,total);
			
			for(ProviderItem item : providerItemList) {
			      parser.parseFile(new File(item.getUrl()), errorList);
			      item.setModifiedTime(new Timestamp(System.currentTimeMillis()));
			      item.setModifiedBy("ProviderItemParser");
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
			      providerItemService.update(item,user);
			    }
		      
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}


}
