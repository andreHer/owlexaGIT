package com.ametis.cms.util.automation.task;

import java.util.Collection;
import java.util.Iterator;

import com.ametis.cms.datamodel.DataImportStage;
import com.ametis.cms.service.DataImportStageService;

public class FileUploadProcessorTask {

	private DataImportStageService dataImportStageService;

	public DataImportStageService getDataImportStageService() {
		return dataImportStageService;
	}

	public void setDataImportStageService(
			DataImportStageService dataImportStageService) {
		this.dataImportStageService = dataImportStageService;
	}
	
	
	public void doProcess(){
		try {
			System.out.println("Processing File UPload");
			String[] eqParam = {"status","deletedStatus"};
			Object[] eqValue = {DataImportStage.STATUS_OPEN,Integer.valueOf(0)};
			int total = dataImportStageService.getTotal(null,null,eqParam,eqValue);
			Collection<DataImportStage> importList = dataImportStageService.search(null,null,eqParam,eqValue,0,total);
			
			if (importList != null){
				for (Iterator iterator = importList.iterator(); iterator
						.hasNext();) {
					
					DataImportStage dataImportStage = (DataImportStage) iterator.next();
					
					if (dataImportStage.getTipe().intValue() == DataImportStage.TIPE_GROUP_SUSPEND){
						System.out.println("GROUP SUSPEND UPLOAD FOUND ==> " + dataImportStage.getImportRawFile());
						dataImportStageService.executeRawMigration(dataImportStage.getId());
						System.out.println("EXECUTE MIGRATION SUSPEND");
						dataImportStageService.executeMigration(dataImportStage.getId());
					}
					if (dataImportStage.getTipe().intValue() == DataImportStage.TIPE_PAYMENT){
						System.out.println("PAYMENT UPLOAD FOUND ==> " + dataImportStage.getImportRawFile());
						dataImportStageService.executeRawMigration(dataImportStage.getId());
						System.out.println("EXECUTE MIGRATION PAYMENT");
						dataImportStageService.executeMigration(dataImportStage.getId());
					}
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
