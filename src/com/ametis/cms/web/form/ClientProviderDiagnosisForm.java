
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * ClientProviderDiagnosis is a mapping of client_provider_diagnosis Table.
*/
public class ClientProviderDiagnosisForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewClientProviderDiagnosis = false;
	private ClientProviderDiagnosis clientProviderDiagnosisBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ClientProviderDiagnosisForm()
    {
    	this.clientProviderDiagnosisBean = new ClientProviderDiagnosis();
    	this.isNewClientProviderDiagnosis = true;
    }
    public ClientProviderDiagnosisForm (ClientProviderDiagnosis object){
		this.clientProviderDiagnosisBean = object;
    }
    public boolean isNewClientProviderDiagnosis (){

    	return this.isNewClientProviderDiagnosis;
    }
	public ClientProviderDiagnosis getClientProviderDiagnosis (){
		return this.clientProviderDiagnosisBean ;
	}
	public void setClientProviderDiagnosis (ClientProviderDiagnosis object){
		this.clientProviderDiagnosisBean = object;
	}

			
	public void setClientProviderDiagnosisId(String obj){

		clientProviderDiagnosisBean.setClientProviderDiagnosisId(StringUtil.getIntegerValue(obj,0));

	}

	public String getClientProviderDiagnosisId(){
		return StringUtil.getStringValue(
		clientProviderDiagnosisBean.getClientProviderDiagnosisId());

	}
	
										
	public void setExclusionStatus(String obj){

		clientProviderDiagnosisBean.setExclusionStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getExclusionStatus(){
		return StringUtil.getStringValue(
		clientProviderDiagnosisBean.getExclusionStatus());

	}
	
				
	public void setCreatedTime(String obj){

		clientProviderDiagnosisBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		clientProviderDiagnosisBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		clientProviderDiagnosisBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		clientProviderDiagnosisBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		clientProviderDiagnosisBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		clientProviderDiagnosisBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		clientProviderDiagnosisBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		clientProviderDiagnosisBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		clientProviderDiagnosisBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		clientProviderDiagnosisBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		clientProviderDiagnosisBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		clientProviderDiagnosisBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		clientProviderDiagnosisBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		clientProviderDiagnosisBean.getDeletedStatus());

	}
	
		

	// foreign affairs
	
	

	
	public void setDiagnosisId(String obj){
		Diagnosis fk = new Diagnosis();
		fk.setDiagnosisId(StringUtil.getIntegerValue(obj,0));
		clientProviderDiagnosisBean.setDiagnosisId(fk);

	}

	public String getDiagnosisId(){
		return StringUtil.getStringValue(
		clientProviderDiagnosisBean.getDiagnosisId().getDiagnosisId());

	}
	//---
	
	

	
	public void setClientProviderId(String obj){
		ClientProvider fk = new ClientProvider();
		fk.setClientProviderId(StringUtil.getIntegerValue(obj,0));
		clientProviderDiagnosisBean.setClientProviderId(fk);

	}

	public String getClientProviderId(){
		return StringUtil.getStringValue(
		clientProviderDiagnosisBean.getClientProviderId().getClientProviderId());

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 

}
