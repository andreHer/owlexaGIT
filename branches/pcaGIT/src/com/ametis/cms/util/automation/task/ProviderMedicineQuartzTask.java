/**
 * 
 */
package com.ametis.cms.util.automation.task;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.ProviderMedicine;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ProviderMedicineService;
import com.ametis.cms.util.parser.ProviderMedicineParser;

/**
 * @author Mashuri Hasan
 *
 */
public class ProviderMedicineQuartzTask {
	private ProviderMedicineService providerMedicineService;
	private ProviderMedicineParser parser;
	
	public ProviderMedicineService getProviderMedicineService() {
		return providerMedicineService;
	}

	public void setProviderMedicineService(
			ProviderMedicineService providerMedicineService) {
		this.providerMedicineService = providerMedicineService;
	}

	public ProviderMedicineParser getParser() {
		return parser;
	}

	public void setParser(ProviderMedicineParser parser) {
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
			Object[] eqValue = {0,1,"upload"};
			
			int total = providerMedicineService.getTotal(null,null,eqParam,eqValue);
			Collection<ProviderMedicine> providerItemList = providerMedicineService.search(null,null,eqParam,eqValue,0,total);
			
			for(ProviderMedicine item : providerItemList) {
				parser.parseFile(new File(item.getUrl()), errorList);
				item.setModifiedTime(new Timestamp(System.currentTimeMillis()));
				item.setModifiedBy("ProviderMedicineParser");
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
				providerMedicineService.update(item,user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
