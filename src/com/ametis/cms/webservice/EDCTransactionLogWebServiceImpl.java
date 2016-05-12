package com.ametis.cms.webservice;

import java.sql.Timestamp;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.xfire.transport.http.XFireConfigurableServlet;
import org.codehaus.xfire.transport.http.XFireServlet;
import org.codehaus.xfire.transport.http.XFireServletChannel;
import org.codehaus.xfire.transport.http.XFireServletController;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.EdcTransactionLog;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.dto.EDCTransactionLogDto;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.EdcTransactionLogService;
import com.ametis.cms.service.ProviderService;



@WebService(name = "EDCTransactionLogWebService", 
		endpointInterface = "com.ametis.cms.webservice.IEDCTransactionLogWebService",
		serviceName="LogWebService")
public class EDCTransactionLogWebServiceImpl implements IEDCTransactionLogWebService{

	private EdcTransactionLogService edcLogService;
	private ProviderService providerService;
	private ConfigurationService configurationService;
	
	public EdcTransactionLogService getEdcLogService() {
		return edcLogService;
	}


	public void setEdcLogService(EdcTransactionLogService edcLogService) {
		this.edcLogService = edcLogService;
	}


	public ProviderService getProviderService() {
		return providerService;
	}


	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}


	public ConfigurationService getConfigurationService() {
		return configurationService;
	}


	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}


	public void logActivity(EDCTransactionLogDto log) {
		// TODO Auto-generated method stub
		
		try {
			if (log != null) {
				EdcTransactionLog edcLog = new EdcTransactionLog();
				edcLog.setActionCode(log.getActionCode());
				edcLog.setActivityLog(log.getActivityLog());
				edcLog.setActivityTime(new java.sql.Timestamp(System.currentTimeMillis()));
				edcLog.setCardNumber(log.getCardNumber());
				edcLog.setMemberNumber(log.getMemberNumber());
				edcLog.setMerchantCode(log.getMerchantCode());
				edcLog.setTerminalCode(log.getTerminalCode());
				edcLog.setTraceNumber(log.getTraceNumber());
				edcLog.setProviderName(log.getProviderName());
				edcLog.setResponseCode(log.getResponseCode());
				edcLog.setGroupName(log.getGroupName());
				edcLog.setAdditionalInfo(log.getAdditionalInfo());
				edcLog.setReferenceNumber(log.getReferenceNumber());
				edcLog.setCaseCategoryCode(log.getCaseCategoryCode());
				
				ActionUser user = new ActionUser();
				
				User usr = new User();
				usr.setUsername("edc-gateway");
				user.setUser(usr);
				
				HttpServletRequest request = XFireServletController.getRequest();
				
				if (request != null){
					String ip = request.getRemoteAddr();
					String session = request.getRequestedSessionId();
					
					user.setIpAddress(ip);
					user.setLoginSession(session);
				}
				
				edcLogService.create(edcLog, user);
				
				updateProviderRollPaper(log, user);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	private void updateProviderRollPaper(EDCTransactionLogDto log, ActionUser user){
		try{
			//Add 20150831 by FVO, for update provider
			System.out.println("MASUK UPDATE ROLL PAPER");
			Configuration conf = configurationService.get(0);
			if(conf.getProviderRollPaperLength() != null && conf.getProviderRollPaperLimit() != null &&
					conf.getProviderRollPaperPayment() != null && conf.getProviderRollPaperRegister() != null && log.getResponseCode() != null){
				if((log.getActionCode().equals("REGISTER") || log.getActionCode().equals("PAYMENT")) && log.getResponseCode().equals("00")){
					Provider prov = providerService.getByProviderCode(log.getMerchantCode());
					Double totalRoll = 0.0;
					Double rollLength = 0.0;
					Double checkTotalRoll = 0.0;
					int rollSubstract = 0;
					if(prov !=null){
						if(prov.getRollPaperAmount() != null){
							//providerService.
							if(prov.getRollPaperRegisterCustomize() > 0 && prov.getRollPaperPaymentCustomize() > 0){ // Menggunakan Settingan Customize
								if(log.getActionCode().equals("REGISTER")){ //Setting satu kali Register atau payment print 3 kali
									totalRoll = prov.getRollPaperRegisterCustomize()*3;
									rollLength = prov.getRollPaperRegisterCustomize();
								}else{
									totalRoll = prov.getRollPaperPaymentCustomize()*3;
									rollLength = prov.getRollPaperPaymentCustomize();
								}
								if(prov.getRollPaperActualLength() == null){ //Jika penggunaan Roll Paper pada provider tersebut masih kosong
									if(totalRoll <= conf.getProviderRollPaperLength()){ //Cek Apakah penggunaan roll masih kurang dari batas panjang roll
										prov.setRollPaperActualLength(totalRoll);
									}
								}else{
									//KOMEN DLU CODINGAN LAMA
									/*checkTotalRoll = totalRoll + prov.getRollPaperActualLength(); 
									if(checkTotalRoll <= conf.getProviderRollPaperLength()){ //Cek di roll tersebut masih cukup atau tidak
										prov.setRollPaperActualLength(checkTotalRoll);
									}else{													//Ganti roll baru, dikurangin
										if(prov.getRollPaperAmount()!=0){
											prov.setRollPaperAmount(prov.getRollPaperAmount()-1);
										}
										prov.setRollPaperActualLength(totalRoll);
									}*/
									checkTotalRoll = prov.getRollPaperActualLength();
									for(int i=0;i<3;i++){
										checkTotalRoll = checkTotalRoll + rollLength; //Penambahan sesuai Length setting Register atau Payment
										if(checkTotalRoll > conf.getProviderRollPaperLength()){
											if(prov.getRollPaperAmount()!=0){ //Kalau sudah 0 roll paper jangan dikurang lagi, CMIIW
												//prov.setRollPaperAmount(prov.getRollPaperAmount()-1);
												rollSubstract++;
											}
											//checkTotalRoll = 0.0; //Kembalikan nilai menjadi semula
											checkTotalRoll = rollLength;
										}
										//System.out.println("CHECK TOTAL ROLL : "+checkTotalRoll);
										//System.out.println("ROLL LENGTH : "+rollLength);
										//System.out.println("ROLL SUBSTRACT : "+rollSubstract);
									}
									
									prov.setRollPaperActualLength(checkTotalRoll);
									if(rollSubstract > 0){
										prov.setRollPaperAmount(prov.getRollPaperAmount()-rollSubstract);
									}
									
								}
								providerService.update(prov, user);
							}else{
								if(conf.getProviderRollPaperRegister() != null 
										&& conf.getProviderRollPaperPayment() != null){ // Menggunakan Settingan Default
									if(log.getActionCode().equals("REGISTER")){ //Setting satu kali Register atau payment print 3 kali
										totalRoll = conf.getProviderRollPaperRegister()*3;
										rollLength = conf.getProviderRollPaperRegister();
									}else{
										totalRoll = conf.getProviderRollPaperPayment()*3;
										rollLength = conf.getProviderRollPaperPayment();
									}
									if(prov.getRollPaperActualLength() == null){ //Jika penggunaan Roll Paper pada provider tersebut masih kosong
										if(totalRoll <= conf.getProviderRollPaperLength()){ //Cek Apakah penggunaan roll masih kurang dari batas panjang roll
											prov.setRollPaperActualLength(totalRoll);
										}
									}else{
										//KOMEN DLU CODINGAN LAMA
										/*checkTotalRoll = totalRoll + prov.getRollPaperActualLength();
										if(checkTotalRoll <= conf.getProviderRollPaperLength()){ //Cek di roll tersebut masih cukup atau tidak
											prov.setRollPaperActualLength(checkTotalRoll);
										}else{													//Ganti roll baru, dikurangin
											if(prov.getRollPaperActualLength() != 0){
												prov.setRollPaperAmount(prov.getRollPaperAmount()-1); 
											}
											prov.setRollPaperActualLength(totalRoll);
										}
										*/
										checkTotalRoll = prov.getRollPaperActualLength();
										for(int i=0;i<3;i++){
											checkTotalRoll = checkTotalRoll + rollLength; //Penambahan sesuai Length setting Register atau Payment
											if(checkTotalRoll > conf.getProviderRollPaperLength()){
												if(prov.getRollPaperAmount()!=0){ //Kalau sudah 0 roll paper jangan dikurang lagi, CMIIW
													//prov.setRollPaperAmount(prov.getRollPaperAmount()-1);
													rollSubstract++;
												}
												//checkTotalRoll = 0.0; //Kembalikan nilai menjadi semula
												checkTotalRoll = rollLength;
											}
										}
										
										prov.setRollPaperActualLength(checkTotalRoll);
										if(rollSubstract > 0){
											prov.setRollPaperAmount(prov.getRollPaperAmount()-rollSubstract);
										}
									}
									providerService.update(prov, user);
								}
							}
						}
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
;