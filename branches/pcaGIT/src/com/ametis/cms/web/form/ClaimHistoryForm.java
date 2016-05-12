
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * ClaimHistory is a mapping of claim_history Table.
*/
public class ClaimHistoryForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewClaimHistory = false;
	private ClaimHistory claimHistoryBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ClaimHistoryForm()
    {
    	this.claimHistoryBean = new ClaimHistory();
    	this.isNewClaimHistory = true;
    }
    public ClaimHistoryForm (ClaimHistory object){
		this.claimHistoryBean = object;
    }
    public boolean isNewClaimHistory (){

    	return this.isNewClaimHistory;
    }
	public ClaimHistory getClaimHistory (){
		return this.claimHistoryBean ;
	}
	public void setClaimHistory (ClaimHistory object){
		this.claimHistoryBean = object;
	}

			
	public void setClaimHistoryId(String obj){

		claimHistoryBean.setClaimHistoryId(StringUtil.getIntegerValue(obj,0));

	}

	public String getClaimHistoryId(){
		return StringUtil.getStringValue(
		claimHistoryBean.getClaimHistoryId());

	}
	
								public void setClaimNumber(String obj){

		claimHistoryBean.setClaimNumber(new String(obj));

	}

	public String getClaimNumber(){
		return StringUtil.getStringValue(
		claimHistoryBean.getClaimNumber());

	}
	
					public void setDiagnosis1Code(String obj){

		claimHistoryBean.setDiagnosis1Code(new String(obj));

	}

	public String getDiagnosis1Code(){
		return StringUtil.getStringValue(
		claimHistoryBean.getDiagnosis1Code());

	}
	
					public void setDiagnosis2Code(String obj){

		claimHistoryBean.setDiagnosis2Code(new String(obj));

	}

	public String getDiagnosis2Code(){
		return StringUtil.getStringValue(
		claimHistoryBean.getDiagnosis2Code());

	}
	
					public void setDiagnosis3Code(String obj){

		claimHistoryBean.setDiagnosis3Code(new String(obj));

	}

	public String getDiagnosis3Code(){
		return StringUtil.getStringValue(
		claimHistoryBean.getDiagnosis3Code());

	}
	
					public void setDiagnosis1Name(String obj){

		claimHistoryBean.setDiagnosis1Name(new String(obj));

	}

	public String getDiagnosis1Name(){
		return StringUtil.getStringValue(
		claimHistoryBean.getDiagnosis1Name());

	}
	
					public void setDiagnosis2Name(String obj){

		claimHistoryBean.setDiagnosis2Name(new String(obj));

	}

	public String getDiagnosis2Name(){
		return StringUtil.getStringValue(
		claimHistoryBean.getDiagnosis2Name());

	}
	
					public void setDiagnosis3Name(String obj){

		claimHistoryBean.setDiagnosis3Name(new String(obj));

	}

	public String getDiagnosis3Name(){
		return StringUtil.getStringValue(
		claimHistoryBean.getDiagnosis3Name());

	}
	
					public void setProviderName(String obj){

		claimHistoryBean.setProviderName(new String(obj));

	}

	public String getProviderName(){
		return StringUtil.getStringValue(
		claimHistoryBean.getProviderName());

	}
	
					public void setMemberName(String obj){

		claimHistoryBean.setMemberName(new String(obj));

	}

	public String getMemberName(){
		return StringUtil.getStringValue(
		claimHistoryBean.getMemberName());

	}
	
					public void setCaseCategoryName(String obj){

		claimHistoryBean.setCaseCategoryName(new String(obj));

	}

	public String getCaseCategoryName(){
		return StringUtil.getStringValue(
		claimHistoryBean.getCaseCategoryName());

	}
	
					public void setDescription(String obj){

		claimHistoryBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		claimHistoryBean.getDescription());

	}
	
				
	public void setDuration(String obj){

		claimHistoryBean.setDuration(StringUtil.getIntegerValue(obj,0));

	}

	public String getDuration(){
		return StringUtil.getStringValue(
		claimHistoryBean.getDuration());

	}
	
					public void setDurationString(String obj){

		claimHistoryBean.setDurationString(new String(obj));

	}

	public String getDurationString(){
		return StringUtil.getStringValue(
		claimHistoryBean.getDurationString());

	}
	
				
	public void setClaimStatus(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			CaseStatus status = new CaseStatus();
			status.setCaseStatusId(Integer.valueOf(obj));
			claimHistoryBean.setClaimStatus(status);
		}

	}

	public String getClaimStatus(){
		return StringUtil.getStringValue(
		claimHistoryBean.getClaimStatus());

	}
	
				
	public void setActionTime(String obj){

		claimHistoryBean.setActionTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getActionTime(){
		return StringUtil.getStringValue(
		claimHistoryBean.getActionTime());

	}

	
					public void setActionBy(String obj){

		claimHistoryBean.setActionBy(new String(obj));

	}

	public String getActionBy(){
		return StringUtil.getStringValue(
		claimHistoryBean.getActionBy());

	}
	
				
	public void setCreatedTime(String obj){

		claimHistoryBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		claimHistoryBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		claimHistoryBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		claimHistoryBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		claimHistoryBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		claimHistoryBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		claimHistoryBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		claimHistoryBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		claimHistoryBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		claimHistoryBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		claimHistoryBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		claimHistoryBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		claimHistoryBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		claimHistoryBean.getDeletedStatus());

	}
	
				
	public void setClaimChargeValue(String obj){

		claimHistoryBean.setClaimChargeValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getClaimChargeValue(){
		return StringUtil.getStringValue(
		claimHistoryBean.getClaimChargeValue());

	}
	
				
	public void setClaimApprovedValue(String obj){

		claimHistoryBean.setClaimApprovedValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getClaimApprovedValue(){
		return StringUtil.getStringValue(
		claimHistoryBean.getClaimApprovedValue());

	}
	
				
	public void setClaimExcessValue(String obj){

		claimHistoryBean.setClaimExcessValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getClaimExcessValue(){
		return StringUtil.getStringValue(
		claimHistoryBean.getClaimExcessValue());

	}
	
				
	public void setPaidToProvider(String obj){

		claimHistoryBean.setPaidToProvider(StringUtil.getDoubleValue(obj,0));

	}

	public String getPaidToProvider(){
		return StringUtil.getStringValue(
		claimHistoryBean.getPaidToProvider());

	}
	
				
	public void setPrePaidExcess(String obj){

		claimHistoryBean.setPrePaidExcess(StringUtil.getDoubleValue(obj,0));

	}

	public String getPrePaidExcess(){
		return StringUtil.getStringValue(
		claimHistoryBean.getPrePaidExcess());

	}
	
				
	public void setRefund(String obj){

		claimHistoryBean.setRefund(StringUtil.getDoubleValue(obj,0));

	}

	public String getRefund(){
		return StringUtil.getStringValue(
		claimHistoryBean.getRefund());

	}
	
					public void setClaimStatusName(String obj){

		claimHistoryBean.setClaimStatusName(new String(obj));

	}

	public String getClaimStatusName(){
		return StringUtil.getStringValue(
		claimHistoryBean.getClaimStatusName());

	}
	
		

	// foreign affairs
	
	

	
	public void setClaimId(String obj){
		Claim fk = new Claim();
		fk.setClaimId(StringUtil.getIntegerValue(obj,0));
		claimHistoryBean.setClaimId(fk);

	}

	public String getClaimId(){
		return StringUtil.getStringValue(
		claimHistoryBean.getClaimId().getClaimId());

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
