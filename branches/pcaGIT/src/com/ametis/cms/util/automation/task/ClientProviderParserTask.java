package com.ametis.cms.util.automation.task;


import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.ClientProvider;
import com.ametis.cms.datamodel.ProviderDoctor;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ClientProviderService;
import com.ametis.cms.util.parser.ClientProviderParser;

/**
 * User: ZaQ <zaki.rahman@gmail.com>
 */
public class ClientProviderParserTask {
	
	private ClientProviderService clientProviderService;
	private ClientProviderParser parser;
	
	
	public ClientProviderParser getParser() {
		return parser;
	}


	public void setParser(ClientProviderParser parser) {
		this.parser = parser;
	}


	public ClientProviderService getClientProviderService() {
		return clientProviderService;
	}


	public void setClientProviderService(ClientProviderService clientProviderService) {
		this.clientProviderService = clientProviderService;
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
			
			int total = clientProviderService.getTotal(null,null,eqParam,eqValue);
			
			Collection<ClientProvider> clientProviderList = clientProviderService.search(null,null,eqParam,eqValue,0,total);
			
			for(ClientProvider item : clientProviderList) {
			      parser.parseFile(new File(item.getUrl()), errorList);
			      item.setModifiedTime(new Timestamp(System.currentTimeMillis()));
			      item.setModifiedBy("ClientProviderParser");
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
			      clientProviderService.update(item,user);
			    }
			
		}catch (Exception e){
			e.printStackTrace();
		}

	}
}
	
