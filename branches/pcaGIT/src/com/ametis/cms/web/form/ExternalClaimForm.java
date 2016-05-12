
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * ExternalClaim is a mapping of external_claim Table.
*/
public class ExternalClaimForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewExternalClaim = false;
	private ExternalClaim externalClaimBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ExternalClaimForm()
    {
    	this.externalClaimBean = new ExternalClaim();
    	this.isNewExternalClaim = true;
    }
    public ExternalClaimForm (ExternalClaim object){
		this.externalClaimBean = object;
    }
    public boolean isNewExternalClaim (){

    	return this.isNewExternalClaim;
    }
	public ExternalClaim getExternalClaim (){
		return this.externalClaimBean ;
	}
	public void setExternalClaim (ExternalClaim object){
		this.externalClaimBean = object;
	}

			
	public void setId(String obj){

		externalClaimBean.setId(StringUtil.getIntegerValue(obj,0));

	}

	public String getId(){
		return StringUtil.getStringValue(
		externalClaimBean.getId());

	}
	
					public void setClaimNumber(String obj){

		externalClaimBean.setClaimNumber(new String(obj));

	}

	public String getClaimNumber(){
		return StringUtil.getStringValue(
		externalClaimBean.getClaimNumber());

	}
	
					public void setClaimCategory(String obj){

		externalClaimBean.setClaimCategory(new String(obj));

	}

	public String getClaimCategory(){
		return StringUtil.getStringValue(
		externalClaimBean.getClaimCategory());

	}
	
					public void setClaimType(String obj){

		externalClaimBean.setClaimType(new String(obj));

	}

	public String getClaimType(){
		return StringUtil.getStringValue(
		externalClaimBean.getClaimType());

	}
	
					public void setBatchNumber(String obj){

		externalClaimBean.setBatchNumber(new String(obj));

	}

	public String getBatchNumber(){
		return StringUtil.getStringValue(
		externalClaimBean.getBatchNumber());

	}
	
				
	public void setAdmissionDate(String obj){

		externalClaimBean.setAdmissionDate(java.sql.Date.valueOf(obj));

	}

	public String getAdmissionDate(){
		return StringUtil.getStringValue(
		externalClaimBean.getAdmissionDate());

	}

	
				
	public void setDischargeDate(String obj){

		externalClaimBean.setDischargeDate(java.sql.Date.valueOf(obj));

	}

	public String getDischargeDate(){
		return StringUtil.getStringValue(
		externalClaimBean.getDischargeDate());

	}

	
				
	public void setClaimChargeValue(String obj){

		externalClaimBean.setClaimChargeValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getClaimChargeValue(){
		return StringUtil.getStringValue(
		externalClaimBean.getClaimChargeValue());

	}
	
				
	public void setClaimApprovedValue(String obj){

		externalClaimBean.setClaimApprovedValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getClaimApprovedValue(){
		return StringUtil.getStringValue(
		externalClaimBean.getClaimApprovedValue());

	}
	
				
	public void setExcessChargeValue(String obj){

		externalClaimBean.setExcessChargeValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getExcessChargeValue(){
		return StringUtil.getStringValue(
		externalClaimBean.getExcessChargeValue());

	}
	
				
	public void setExGratiaValue(String obj){

		externalClaimBean.setExGratiaValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getExGratiaValue(){
		return StringUtil.getStringValue(
		externalClaimBean.getExGratiaValue());

	}
	
					public void setCreatedBy(String obj){

		externalClaimBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		externalClaimBean.getCreatedBy());

	}
	
				
	public void setCreatedTime(String obj){

		externalClaimBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		externalClaimBean.getCreatedTime());

	}

	
					public void setModifiedBy(String obj){

		externalClaimBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		externalClaimBean.getModifiedBy());

	}
	
				
	public void setModifiedTime(String obj){

		externalClaimBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		externalClaimBean.getModifiedTime());

	}

	
					public void setClaimStatus(String obj){

		externalClaimBean.setClaimStatus(new String(obj));

	}

	public String getClaimStatus(){
		return StringUtil.getStringValue(
		externalClaimBean.getClaimStatus());

	}
	
					public void setMemberNumber(String obj){

		externalClaimBean.setMemberNumber(new String(obj));

	}

	public String getMemberNumber(){
		return StringUtil.getStringValue(
		externalClaimBean.getMemberNumber());

	}
	
					public void setMemberName(String obj){

		externalClaimBean.setMemberName(new String(obj));

	}

	public String getMemberName(){
		return StringUtil.getStringValue(
		externalClaimBean.getMemberName());

	}
	
					public void setCdvNumber(String obj){

		externalClaimBean.setCdvNumber(new String(obj));

	}

	public String getCdvNumber(){
		return StringUtil.getStringValue(
		externalClaimBean.getCdvNumber());

	}
	
					public void setDiagnosis1Code(String obj){

		externalClaimBean.setDiagnosis1Code(new String(obj));

	}

	public String getDiagnosis1Code(){
		return StringUtil.getStringValue(
		externalClaimBean.getDiagnosis1Code());

	}
	
					public void setDiagnosis2Code(String obj){

		externalClaimBean.setDiagnosis2Code(new String(obj));

	}

	public String getDiagnosis2Code(){
		return StringUtil.getStringValue(
		externalClaimBean.getDiagnosis2Code());

	}
	
					public void setDiagnosis3Code(String obj){

		externalClaimBean.setDiagnosis3Code(new String(obj));

	}

	public String getDiagnosis3Code(){
		return StringUtil.getStringValue(
		externalClaimBean.getDiagnosis3Code());

	}
	
				
	public void setRemainingLimit(String obj){

		externalClaimBean.setRemainingLimit(StringUtil.getDoubleValue(obj,0));

	}

	public String getRemainingLimit(){
		return StringUtil.getStringValue(
		externalClaimBean.getRemainingLimit());

	}
	
					public void setProviderCode(String obj){

		externalClaimBean.setProviderCode(new String(obj));

	}

	public String getProviderCode(){
		return StringUtil.getStringValue(
		externalClaimBean.getProviderCode());

	}
	
					public void setProviderName(String obj){

		externalClaimBean.setProviderName(new String(obj));

	}

	public String getProviderName(){
		return StringUtil.getStringValue(
		externalClaimBean.getProviderName());

	}
	
				
	public void setRegistrationTime(String obj){

		externalClaimBean.setRegistrationTime(java.sql.Date.valueOf(obj));

	}

	public String getRegistrationTime(){
		return StringUtil.getStringValue(
		externalClaimBean.getRegistrationTime());

	}

	
				
	public void setVerificationTime(String obj){

		externalClaimBean.setVerificationTime(java.sql.Date.valueOf(obj));

	}

	public String getVerificationTime(){
		return StringUtil.getStringValue(
		externalClaimBean.getVerificationTime());

	}

	
				
	public void setPaymentTime(String obj){

		externalClaimBean.setPaymentTime(java.sql.Date.valueOf(obj));

	}

	public String getPaymentTime(){
		return StringUtil.getStringValue(
		externalClaimBean.getPaymentTime());

	}

	
					public void setLocation(String obj){

		externalClaimBean.setLocation(new String(obj));

	}

	public String getLocation(){
		return StringUtil.getStringValue(
		externalClaimBean.getLocation());

	}
	
				
	public void setCdvGeneratedDate(String obj){

		externalClaimBean.setCdvGeneratedDate(java.sql.Date.valueOf(obj));

	}

	public String getCdvGeneratedDate(){
		return StringUtil.getStringValue(
		externalClaimBean.getCdvGeneratedDate());

	}

	
				
	public void setCdvConfirmDate(String obj){

		externalClaimBean.setCdvConfirmDate(java.sql.Date.valueOf(obj));

	}

	public String getCdvConfirmDate(){
		return StringUtil.getStringValue(
		externalClaimBean.getCdvConfirmDate());

	}

	
				
	public void setIsMigrated(String obj){

		externalClaimBean.setIsMigrated(StringUtil.getIntegerValue(obj,0));

	}

	public String getIsMigrated(){
		return StringUtil.getStringValue(
		externalClaimBean.getIsMigrated());

	}
	
					public void setPayTo(String obj){

		externalClaimBean.setPayTo(new String(obj));

	}

	public String getPayTo(){
		return StringUtil.getStringValue(
		externalClaimBean.getPayTo());

	}
	
					public void setAccountNo(String obj){

		externalClaimBean.setAccountNo(new String(obj));

	}

	public String getAccountNo(){
		return StringUtil.getStringValue(
		externalClaimBean.getAccountNo());

	}
	
					public void setBankAccount(String obj){

		externalClaimBean.setBankAccount(new String(obj));

	}

	public String getBankAccount(){
		return StringUtil.getStringValue(
		externalClaimBean.getBankAccount());

	}
	
					public void setBankName(String obj){

		externalClaimBean.setBankName(new String(obj));

	}

	public String getBankName(){
		return StringUtil.getStringValue(
		externalClaimBean.getBankName());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
