
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * DiagnosisSet is a mapping of diagnosis_set Table.
*/
public class DiagnosisSetForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewDiagnosisSet = false;
	private DiagnosisSet diagnosisSetBean ;
	private String clientName;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public DiagnosisSetForm()
    {
    	this.diagnosisSetBean = new DiagnosisSet();
    	this.isNewDiagnosisSet = true;
    }
    public DiagnosisSetForm (DiagnosisSet object){
		this.diagnosisSetBean = object;
    }
    public boolean isNewDiagnosisSet (){

    	return this.isNewDiagnosisSet;
    }
	public DiagnosisSet getDiagnosisSet (){
		return this.diagnosisSetBean ;
	}
	public void setDiagnosisSet (DiagnosisSet object){
		this.diagnosisSetBean = object;
	}

			
	public void setDiagnosisSetId(String obj){

		diagnosisSetBean.setDiagnosisSetId(StringUtil.getIntegerValue(obj,0));

	}

	public String getDiagnosisSetId(){
		return StringUtil.getStringValue(
		diagnosisSetBean.getDiagnosisSetId());

	}
	
					public void setDiagnosisSetName(String obj){

		diagnosisSetBean.setDiagnosisSetName(new String(obj));

	}

	public String getDiagnosisSetName(){
		return StringUtil.getStringValue(
		diagnosisSetBean.getDiagnosisSetName());

	}
	
					public void setDiagnosisSetCode(String obj){

		diagnosisSetBean.setDiagnosisSetCode(new String(obj));

	}

	public String getDiagnosisSetCode(){
		return StringUtil.getStringValue(
		diagnosisSetBean.getDiagnosisSetCode());

	}
	
					public void setDescription(String obj){

		diagnosisSetBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		diagnosisSetBean.getDescription());

	}
	
							
	public void setCreatedTime(String obj){

		diagnosisSetBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		diagnosisSetBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		diagnosisSetBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		diagnosisSetBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		diagnosisSetBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		diagnosisSetBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		diagnosisSetBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		diagnosisSetBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		diagnosisSetBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		diagnosisSetBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		diagnosisSetBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		diagnosisSetBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		diagnosisSetBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		diagnosisSetBean.getDeletedStatus());

	}
	
		

	// foreign affairs
	
	

	
	public void setClientId(String obj){
		if (obj !=null && !obj.equalsIgnoreCase("")){
		Client fk = new Client();
		fk.setClientId(StringUtil.getIntegerValue(obj,0));
		diagnosisSetBean.setClientId(fk);
		}

	}
	public String getClientId(){
		String result = "";
		
		if (diagnosisSetBean.getClientId() != null){
			result = StringUtil.getStringValue(
					diagnosisSetBean.getClientId().getClientId());
		}
		return result;

	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	//---
		// -- foreign affairs end

// class+ 

// class- 
}
