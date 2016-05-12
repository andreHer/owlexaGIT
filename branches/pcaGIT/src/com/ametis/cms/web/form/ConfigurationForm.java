
package com.ametis.cms.web.form;

import javax.persistence.Column;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * Configuration is a mapping of configuration Table.
*/
public class ConfigurationForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewConfiguration = false;
	private Configuration configurationBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ConfigurationForm()
    {
    	this.configurationBean = new Configuration();
    	this.isNewConfiguration = true;
    }
    public ConfigurationForm (Configuration object){
		this.configurationBean = object;
    }
    public boolean isNewConfiguration (){

    	return this.isNewConfiguration;
    }
	public Configuration getConfiguration (){
		return this.configurationBean ;
	}
	public void setConfiguration (Configuration object){
		this.configurationBean = object;
	}

			
	public void setConfigurationId(String obj){

		configurationBean.setConfigurationId(StringUtil.getIntegerValue(obj,0));

	}

	public String getConfigurationId(){
		return StringUtil.getStringValue(
		configurationBean.getConfigurationId());

	}
	
				
	public void setIsActive(String obj){

		configurationBean.setIsActive(StringUtil.getIntegerValue(obj,0));

	}

	public String getIsActive(){
		return StringUtil.getStringValue(
		configurationBean.getIsActive());

	}
	
				
	public void setInpatientThreshold(String obj){

		configurationBean.setInpatientThreshold(StringUtil.getDoubleValue(obj,0));

	}

	public String getInpatientThreshold(){
		return StringUtil.getStringValue(
		configurationBean.getInpatientThreshold());

	}
	
				
	public void setOutpatientThreshold(String obj){

		configurationBean.setOutpatientThreshold(StringUtil.getDoubleValue(obj,0));

	}

	public String getOutpatientThreshold(){
		return StringUtil.getStringValue(
		configurationBean.getOutpatientThreshold());

	}
	
				
	public void setDentalThreshold(String obj){

		configurationBean.setDentalThreshold(StringUtil.getDoubleValue(obj,0));

	}

	public String getDentalThreshold(){
		return StringUtil.getStringValue(
		configurationBean.getDentalThreshold());

	}
	
				
	public void setMaternityThreshold(String obj){

		configurationBean.setMaternityThreshold(StringUtil.getDoubleValue(obj,0));

	}

	public String getMaternityThreshold(){
		return StringUtil.getStringValue(
		configurationBean.getMaternityThreshold());

	}
	
				
	public void setOpticalThreshold(String obj){

		configurationBean.setOpticalThreshold(StringUtil.getDoubleValue(obj,0));

	}

	public String getOpticalThreshold(){
		return StringUtil.getStringValue(
		configurationBean.getOpticalThreshold());

	}
	
					public void setBankAccount(String obj){

		configurationBean.setBankAccount(new String(obj));

	}

	public String getBankAccount(){
		return StringUtil.getStringValue(
		configurationBean.getBankAccount());

	}
	
					public void setBankAccountName(String obj){

		configurationBean.setBankAccountName(new String(obj));

	}

	public String getBankAccountName(){
		return StringUtil.getStringValue(
		configurationBean.getBankAccountName());

	}
	
					public void setBankName(String obj){

		configurationBean.setBankName(new String(obj));

	}

	public String getBankName(){
		return StringUtil.getStringValue(
		configurationBean.getBankName());

	}
	
					public void setBankBranch(String obj){

		configurationBean.setBankBranch(new String(obj));

	}

	public String getBankBranch(){
		return StringUtil.getStringValue(
		configurationBean.getBankBranch());

	}
	
					public void setGeneralManager(String obj){

		configurationBean.setGeneralManager(new String(obj));

	}

	public String getGeneralManager(){
		return StringUtil.getStringValue(
		configurationBean.getGeneralManager());

	}
	
					public void setGeneralManagerLabel(String obj){

		configurationBean.setGeneralManagerLabel(new String(obj));

	}

	public String getGeneralManagerLabel(){
		return StringUtil.getStringValue(
		configurationBean.getGeneralManagerLabel());

	}
	
					public void setDirector(String obj){

		configurationBean.setDirector(new String(obj));

	}

	public String getDirector(){
		return StringUtil.getStringValue(
		configurationBean.getDirector());

	}
	
					public void setDirectorLabel(String obj){

		configurationBean.setDirectorLabel(new String(obj));

	}

	public String getDirectorLabel(){
		return StringUtil.getStringValue(
		configurationBean.getDirectorLabel());

	}
	
					public void setFinanceManager(String obj){

		configurationBean.setFinanceManager(new String(obj));

	}

	public String getFinanceManager(){
		return StringUtil.getStringValue(
		configurationBean.getFinanceManager());

	}
	
					public void setFinanceManagerLabel(String obj){

		configurationBean.setFinanceManagerLabel(new String(obj));

	}

	public String getFinanceManagerLabel(){
		return StringUtil.getStringValue(
		configurationBean.getFinanceManagerLabel());

	}
	
					public void setCompanyName(String obj){

		configurationBean.setCompany(new String(obj));

	}

	public String getCompanyName(){
		return StringUtil.getStringValue(
		configurationBean.getCompany());

	}
	
					public void setCompanyAddress(String obj){

		configurationBean.setCompanyAddress(new String(obj));

	}

	public String getCompanyAddress(){
		return StringUtil.getStringValue(
		configurationBean.getCompanyAddress());

	}
	
					public void setCompanyCode(String obj){

		configurationBean.setCompanyCode(new String(obj));

	}

	public String getCompanyCode(){
		return StringUtil.getStringValue(
		configurationBean.getCompanyCode());

	}
	
				
	public void setMonthlyUsageThreshold(String obj){

		configurationBean.setMonthlyUsageThreshold(StringUtil.getDoubleValue(obj,0));

	}

	public String getMonthlyUsageThreshold(){
		return StringUtil.getStringValue(
		configurationBean.getMonthlyUsageThreshold());

	}
	
					public void setCompany(String obj){

		configurationBean.setCompany(new String(obj));

	}

	public String getCompany(){
		return StringUtil.getStringValue(
		configurationBean.getCompany());

	}
	
				
	public void setCentralizedCdvNumbering(String obj){

		configurationBean.setCentralizedCDVNumbering(StringUtil.getIntegerValue(obj,0));

	}

	public String getCentralizedCdvNumbering(){
		return StringUtil.getStringValue(
		configurationBean.getCentralizedCDVNumbering());

	}
	
				
	public void setCentralizedClaimNumbering(String obj){

		configurationBean.setCentralizedClaimNumbering(StringUtil.getIntegerValue(obj,0));

	}

	public String getCentralizedClaimNumbering(){
		return StringUtil.getStringValue(
		configurationBean.getCentralizedClaimNumbering());

	}
	
				
	public void setCentralizedExcessNumbering(String obj){

		configurationBean.setCentralizedExcessNumbering(StringUtil.getIntegerValue(obj,0));

	}

	public String getCentralizedExcessNumbering(){
		return StringUtil.getStringValue(
		configurationBean.getCentralizedExcessNumbering());

	}
	
				
	public void setCentralizedCallNumbering(String obj){

		configurationBean.setCentralizedCallNumbering(StringUtil.getIntegerValue(obj,0));

	}

	public String getCentralizedCallNumbering(){
		return StringUtil.getStringValue(
		configurationBean.getCentralizedCallNumbering());

	}
	
				
	public void setCentralizedFundNumbering(String obj){

		configurationBean.setCentralizedFundNumbering(StringUtil.getIntegerValue(obj,0));

	}

	public String getCentralizedFundNumbering(){
		return StringUtil.getStringValue(
		configurationBean.getCentralizedFundNumbering());

	}
	
				
	public void setCentralizedBatchNumbering(String obj){

		configurationBean.setCentralizedBatchNumbering(StringUtil.getIntegerValue(obj,0));

	}

	public String getCentralizedBatchNumbering(){
		return StringUtil.getStringValue(
		configurationBean.getCentralizedBatchNumbering());

	}
	
				
	public void setCdvNumber(String obj){

		configurationBean.setCdvNumber(StringUtil.getIntegerValue(obj,0));

	}

	public String getCdvNumber(){
		return StringUtil.getStringValue(
		configurationBean.getCdvNumber());

	}
	
				
	public void setClaimNumber(String obj){

		configurationBean.setClaimNumber(StringUtil.getIntegerValue(obj,0));

	}

	public String getClaimNumber(){
		return StringUtil.getStringValue(
		configurationBean.getClaimNumber());

	}
	
				
	public void setExcessNumber(String obj){

		configurationBean.setExcessNumber(StringUtil.getIntegerValue(obj,0));

	}

	public String getExcessNumber(){
		return StringUtil.getStringValue(
		configurationBean.getExcessNumber());

	}
	
				
	public void setCallNumber(String obj){

		configurationBean.setCallNumber(StringUtil.getIntegerValue(obj,0));

	}

	public String getCallNumber(){
		return StringUtil.getStringValue(
		configurationBean.getCallNumber());

	}
	
				
	public void setFundNumber(String obj){

		configurationBean.setFundNumber(StringUtil.getIntegerValue(obj,0));

	}

	public String getFundNumber(){
		return StringUtil.getStringValue(
		configurationBean.getFundNumber());

	}
	
				
	public void setBatchNumber(String obj){

		configurationBean.setBatchNumber(StringUtil.getIntegerValue(obj,0));

	}

	public String getBatchNumber(){
		return StringUtil.getStringValue(
		configurationBean.getBatchNumber());

	}
	
					public void setOtherPaymentNumber(String obj){

		configurationBean.setOtherPaymentNumber(new Integer(obj));

	}

	public String getOtherPaymentNumber(){
		return StringUtil.getStringValue(
		configurationBean.getOtherPaymentNumber());

	}
	
				
	public void setIsUsingOtherPaymentNumber(String obj){

		configurationBean.setIsUsingOtherPaymentNumber(StringUtil.getIntegerValue(obj,0));

	}

	public String getIsUsingOtherPaymentNumber(){
		return StringUtil.getStringValue(
		configurationBean.getIsUsingOtherPaymentNumber());

	}
	
				
	public void setIsUsingBranch(String obj){

		configurationBean.setIsUsingBranch(StringUtil.getIntegerValue(obj,0));

	}

	public String getIsUsingBranch(){
		return StringUtil.getStringValue(
		configurationBean.getIsUsingBranch());

	}
	
				
	public void setExcessExpireDay(String obj){

		configurationBean.setExcessExpireDay(StringUtil.getIntegerValue(obj,0));

	}

	public String getExcessExpireDay(){
		return StringUtil.getStringValue(
		configurationBean.getExcessExpireDay());

	}
	
				
	public void setExcessReminderDay(String obj){

		configurationBean.setReminderExpireDay(StringUtil.getIntegerValue(obj,0));

	}

	public String getExcessReminderDay(){
		return StringUtil.getStringValue(
		configurationBean.getReminderExpireDay());

	}
	
					public void setTheme(String obj){

		configurationBean.setTheme(new String(obj));

	}

	public String getTheme(){
		return StringUtil.getStringValue(
		configurationBean.getTheme());

	}
	
					public void setReportStoragePath(String obj){

		configurationBean.setReportPath(new String(obj));

	}

	public String getReportStoragePath(){
		return StringUtil.getStringValue(
		configurationBean.getReportPath());

	}
	
					public void setMemberStoragePath(String obj){

		configurationBean.setMemberStoragePath(new String(obj));

	}

	public String getMemberStoragePath(){
		return StringUtil.getStringValue(
		configurationBean.getMemberStoragePath());

	}
	public void setUploadStoragePath(String obj){

		configurationBean.setUploadStorageDirectory(new String(obj));

	}

	public String getUploadStoragePath(){
		return StringUtil.getStringValue(
		configurationBean.getUploadStorageDirectory());

	}
	
					public void setBenefitTermConditionPath(String obj){

		configurationBean.setPolicyTermConditionPath(new String(obj));

	}

	public String getBenefitTermConditionPath(){
		return StringUtil.getStringValue(
		configurationBean.getPolicyTermConditionPath());

	}
	
				
	public void setClaimRejectionNumber(String obj){

		configurationBean.setClaimRejectionNumber(StringUtil.getIntegerValue(obj,0));

	}

	public String getClaimRejectionNumber(){
		return StringUtil.getStringValue(
		configurationBean.getClaimRejectionNumber());

	}
	
				
	public void setCaseRejectionNumber(String obj){

		configurationBean.setCaseRejectionNumber(StringUtil.getIntegerValue(obj,0));

	}

	public String getCaseRejectionNumber(){
		return StringUtil.getStringValue(
		configurationBean.getCaseRejectionNumber());

	}
	
				
	public void setClaimPendingNumber(String obj){

		configurationBean.setClaimPendingNumber(StringUtil.getIntegerValue(obj,0));

	}

	public String getClaimPendingNumber(){
		return StringUtil.getStringValue(
		configurationBean.getClaimPendingNumber());

	}
	
					public void setCallNumberTemplate(String obj){

		configurationBean.setCallNumberTemplate(new String(obj));

	}

	public String getCallNumberTemplate(){
		return StringUtil.getStringValue(
		configurationBean.getCallNumberTemplate());

	}
	
					public void setClaimNumberTemplate(String obj){

		configurationBean.setClaimNumberTemplate(new String(obj));

	}

	public String getClaimNumberTemplate(){
		return StringUtil.getStringValue(
		configurationBean.getClaimNumberTemplate());

	}
	
					public void setCaseNumberTemplate(String obj){

		configurationBean.setCaseNumberTemplate(new String(obj));

	}

	public String getCaseNumberTemplate(){
		return StringUtil.getStringValue(
		configurationBean.getCaseNumberTemplate());

	}
	
					public void setPaymentNumberTemplate(String obj){

		configurationBean.setPaymentNumberTemplate(new String(obj));

	}

	public String getPaymentNumberTemplate(){
		return StringUtil.getStringValue(
		configurationBean.getPaymentNumberTemplate());

	}
	
					public void setBatchNumberTemplate(String obj){

		configurationBean.setBatchNumberTemplate(new String(obj));

	}

	public String getBatchNumberTemplate(){
		return StringUtil.getStringValue(
		configurationBean.getBatchNumberTemplate());

	}
	
					public void setClaimRejectionNumberTemplate(String obj){

		configurationBean.setClaimRejectionNumberTemplate(new String(obj));

	}

	public String getClaimRejectionNumberTemplate(){
		return StringUtil.getStringValue(
		configurationBean.getClaimRejectionNumberTemplate());

	}
	
					public void setCaseRejectionNumberTemplate(String obj){

		configurationBean.setCaseRejectionNumberTemplate(new String(obj));

	}

	public String getCaseRejectionNumberTemplate(){
		return StringUtil.getStringValue(
		configurationBean.getCaseRejectionNumberTemplate());

	}
	
					public void setClaimPendingNumberTemplate(String obj){

		configurationBean.setClaimPendingNumberTemplate(new String(obj));

	}

	public String getClaimPendingNumberTemplate(){
		return StringUtil.getStringValue(
		configurationBean.getClaimPendingNumberTemplate());

	}
	
	
					public void setExcessNumberTemplate(String obj){

		configurationBean.setExcessNumberTemplate(new String(obj));

	}

	public String getExcessNumberTemplate(){
		return StringUtil.getStringValue(
		configurationBean.getExcessNumberTemplate());

	}
	public void setCardNumberPrefix(String obj){

		configurationBean.setSwipeCardPrefix(new String(obj));

	}

	public String getCardNumberPrefix(){
		return StringUtil.getStringValue(
		configurationBean.getSwipeCardPrefix());

	}
	
							
	public void setCaseNumber(String obj){

		configurationBean.setCaseNumber(StringUtil.getIntegerValue(obj,0));

	}

	public String getCaseNumber(){
		return StringUtil.getStringValue(
		configurationBean.getCaseNumber());

	}
	
		

	// foreign affairs
	
	

	
	public void setClientId(String obj){
		if (obj != null && !obj.equals("")){
			Client fk = new Client();
			fk.setClientId(StringUtil.getIntegerValue(obj,0));
			configurationBean.setClientId(fk);
		}

	}

	public String getClientId(){
		String result = "";
		
		if (configurationBean.getClientId() != null){
			result = configurationBean.getClientId().getClientId().toString();
		}
		return result;

	}
	

	
	public String getUploadMemberTemplateId() {
		String result = "";
		if (configurationBean.getUploadMemberTemplateId() != null){
			result = configurationBean.getUploadMemberTemplateId().toString();
		}
		return result;
	}

	public void setUploadMemberTemplateId(String val) {
		
		if (val != null && !val.equalsIgnoreCase("")){
			configurationBean.setUploadMemberTemplateId(Integer.valueOf(val));
		}
		
	}

	
	public String getUploadProviderTemplateId() {
		String result = "";
		if (configurationBean.getUploadProviderTemplateId() != null){
			result = configurationBean.getUploadProviderTemplateId().toString();
		}
		return result;
	}

	public void setUploadProviderTemplateId(String val) {
		if (val != null && !val.equalsIgnoreCase("")){
			configurationBean.setUploadProviderTemplateId(Integer.valueOf(val));
		}
	}

	
	public String getUploadMemberDiagnosisTemplateId() {
		String result = "";
		if (configurationBean.getUploadMemberDiagnosisTemplateId() != null){
			result = configurationBean.getUploadMemberDiagnosisTemplateId().toString();
		}
		return result;
	}

	public void setUploadMemberDiagnosisTemplateId(
			String val) {
		if (val != null && !val.equalsIgnoreCase("")){
			configurationBean.setUploadMemberDiagnosisTemplateId(Integer.valueOf(val));
		}
	}

	
	public String getUploadMemberProviderTemplateId() {
		String result = "";
		if (configurationBean.getUploadMemberProviderTemplateId() != null){
			result = configurationBean.getUploadMemberProviderTemplateId().toString();
		}
		return result;
	}

	public void setUploadMemberProviderTemplateId(
			String val) {
		if (val != null && !val.equalsIgnoreCase("")){
			configurationBean.setUploadMemberProviderTemplateId(Integer.valueOf(val));
		}
	}

	
	public String getUploadClaimHeaderTemplateId() {
		String result = "";
		if (configurationBean.getUploadClaimHeaderTemplateId() != null){
			result = configurationBean.getUploadClaimHeaderTemplateId().toString();
		}
		return result;
	}

	public void setUploadClaimHeaderTemplateId(String val) {
		if (val != null && !val.equalsIgnoreCase("")){
			configurationBean.setUploadClaimHeaderTemplateId(Integer.valueOf(val));
		}
	}

	
	public String getUploadClaimDetailTemplateId() {
		String result = "";
		if (configurationBean.getUploadClaimDetailTemplateId() != null){
			result = configurationBean.getUploadClaimDetailTemplateId().toString();
		}
		return result;
	}

	public void setUploadClaimDetailTemplateId(String val) {
		if (val != null && !val.equalsIgnoreCase("")){
			configurationBean.setUploadClaimDetailTemplateId(Integer.valueOf(val));
		}
	}
	public void setEdcAlertGlobal(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			this.configurationBean.setEdcAlertGlobal(Integer.valueOf(obj));
		}
		
	}
	public String getEdcAlertGlobal(){
		String result = "";
		if (configurationBean.getEdcAlertGlobal() != null){
			result = StringUtil.getStringValue(configurationBean.getEdcAlertGlobal());
		}
		return result;
		
	}
	
	//Add 20150828 by FVO, for alert Provider Roll Paper
	public void setProviderRollPaperRegister(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			this.configurationBean.setProviderRollPaperRegister(Double.valueOf(obj));
		}
		
	}
	public String getProviderRollPaperRegister(){
		String result = "";
		if (configurationBean.getProviderRollPaperRegister() != null){
			result = StringUtil.getStringValue(configurationBean.getProviderRollPaperRegister());
		}
		return result;
		
	}
	
	public void setProviderRollPaperPayment(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			this.configurationBean.setProviderRollPaperPayment(Double.valueOf(obj));
		}
		
	}
	public String getProviderRollPaperPayment(){
		String result = "";
		if (configurationBean.getProviderRollPaperPayment() != null){
			result = StringUtil.getStringValue(configurationBean.getProviderRollPaperPayment());
		}
		return result;
		
	}
	
	public void setProviderRollPaperLimit(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			this.configurationBean.setProviderRollPaperLimit(Integer.valueOf(obj));
		}
		
	}
	public String getProviderRollPaperLimit(){
		String result = "";
		if (configurationBean.getProviderRollPaperLimit() != null){
			result = StringUtil.getStringValue(configurationBean.getProviderRollPaperLimit());
		}
		return result;
		
	}
	
	public void setProviderRollPaperLength(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			this.configurationBean.setProviderRollPaperLength(Double.valueOf(obj));
		}
		
	}
	public String getProviderRollPaperLength(){
		String result = "";
		if (configurationBean.getProviderRollPaperLength() != null){
			result = StringUtil.getStringValue(configurationBean.getProviderRollPaperLength());
		}
		return result;
		
	}
}
