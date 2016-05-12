
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * PolicyProviderDiagnosis is a mapping of policy_provider_diagnosis Table.
*/
public class PolicyProviderDiagnosisForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewPolicyProviderDiagnosis = false;
	private PolicyProviderDiagnosis policyProviderDiagnosisBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public PolicyProviderDiagnosisForm()
    {
    	this.policyProviderDiagnosisBean = new PolicyProviderDiagnosis();
    	this.isNewPolicyProviderDiagnosis = true;
    }
    public PolicyProviderDiagnosisForm (PolicyProviderDiagnosis object){
		this.policyProviderDiagnosisBean = object;
    }
    public boolean isNewPolicyProviderDiagnosis (){

    	return this.isNewPolicyProviderDiagnosis;
    }
	public PolicyProviderDiagnosis getPolicyProviderDiagnosis (){
		return this.policyProviderDiagnosisBean ;
	}
	public void setPolicyProviderDiagnosis (PolicyProviderDiagnosis object){
		this.policyProviderDiagnosisBean = object;
	}

			
	public void setPolicyProviderDiagnosisId(String obj){

		policyProviderDiagnosisBean.setPolicyProviderDiagnosisId(StringUtil.getIntegerValue(obj,0));

	}

	public String getPolicyProviderDiagnosisId(){
		return StringUtil.getStringValue(
		policyProviderDiagnosisBean.getPolicyProviderDiagnosisId());

	}
	
										
	public void setExclusionId(String obj){

		policyProviderDiagnosisBean.setExclusionStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getExclusionId(){
		return StringUtil.getStringValue(
		policyProviderDiagnosisBean.getExclusionStatus());

	}
	
				
	public void setCreatedTime(String obj){

		policyProviderDiagnosisBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		policyProviderDiagnosisBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		policyProviderDiagnosisBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		policyProviderDiagnosisBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		policyProviderDiagnosisBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		policyProviderDiagnosisBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		policyProviderDiagnosisBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		policyProviderDiagnosisBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		policyProviderDiagnosisBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		policyProviderDiagnosisBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		policyProviderDiagnosisBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		policyProviderDiagnosisBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		policyProviderDiagnosisBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		policyProviderDiagnosisBean.getDeletedStatus());

	}
	
		

	// foreign affairs
	
	

	
	public void setDiagnosisId(String obj){
		Diagnosis fk = new Diagnosis();
		fk.setDiagnosisId(StringUtil.getIntegerValue(obj,0));
		policyProviderDiagnosisBean.setDiagnosisId(fk);

	}

	public String getDiagnosisId(){
		return StringUtil.getStringValue(
		policyProviderDiagnosisBean.getDiagnosisId().getDiagnosisId());

	}
	//---
	
	

	
	public void setPolicyProviderId(String obj){
		PolicyProvider fk = new PolicyProvider();
		fk.setPolicyProviderId(StringUtil.getIntegerValue(obj,0));
		policyProviderDiagnosisBean.setPolicyProviderId(fk);

	}

	public String getPolicyProviderId(){
		return StringUtil.getStringValue(
		policyProviderDiagnosisBean.getPolicyProviderId().getPolicyProviderId());

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 

}
