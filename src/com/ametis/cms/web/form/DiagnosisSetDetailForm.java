
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * DiagnosisSetDetail is a mapping of diagnosis_set_detail Table.
*/
public class DiagnosisSetDetailForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewDiagnosisSetDetail = false;
	private DiagnosisSetDetail diagnosisSetDetailBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public DiagnosisSetDetailForm()
    {
    	this.diagnosisSetDetailBean = new DiagnosisSetDetail();
    	this.isNewDiagnosisSetDetail = true;
    }
    public DiagnosisSetDetailForm (DiagnosisSetDetail object){
		this.diagnosisSetDetailBean = object;
    }
    public boolean isNewDiagnosisSetDetail (){

    	return this.isNewDiagnosisSetDetail;
    }
	public DiagnosisSetDetail getDiagnosisSetDetail (){
		return this.diagnosisSetDetailBean ;
	}
	public void setDiagnosisSetDetail (DiagnosisSetDetail object){
		this.diagnosisSetDetailBean = object;
	}

			
	public void setDiagnosisSetDetailId(String obj){

		diagnosisSetDetailBean.setDiagnosisSetDetailId(StringUtil.getIntegerValue(obj,0));

	}

	public String getDiagnosisSetDetailId(){
		return StringUtil.getStringValue(
		diagnosisSetDetailBean.getDiagnosisSetDetailId());

	}
	
								public void setDiagnosisCode(String obj){

		diagnosisSetDetailBean.setDiagnosisCode(new String(obj));

	}

	public String getDiagnosisCode(){
		return StringUtil.getStringValue(
		diagnosisSetDetailBean.getDiagnosisCode());

	}
	
					public void setDiagnosisName(String obj){

		diagnosisSetDetailBean.setDiagnosisName(new String(obj));

	}

	public String getDiagnosisName(){
		return StringUtil.getStringValue(
		diagnosisSetDetailBean.getDiagnosisName());

	}
	
					public void setDescription(String obj){

		diagnosisSetDetailBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		diagnosisSetDetailBean.getDescription());

	}
	
				
	public void setStatus(String obj){

		diagnosisSetDetailBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		diagnosisSetDetailBean.getStatus());

	}
	
				
	public void setCreatedTime(String obj){

		diagnosisSetDetailBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		diagnosisSetDetailBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		diagnosisSetDetailBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		diagnosisSetDetailBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		diagnosisSetDetailBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		diagnosisSetDetailBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		diagnosisSetDetailBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		diagnosisSetDetailBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		diagnosisSetDetailBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		diagnosisSetDetailBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		diagnosisSetDetailBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		diagnosisSetDetailBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		diagnosisSetDetailBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		diagnosisSetDetailBean.getDeletedStatus());

	}
	
					

	// foreign affairs
	
	

	
	public void setDiagnosisId(String obj){
		Diagnosis fk = new Diagnosis();
		fk.setDiagnosisId(StringUtil.getIntegerValue(obj,0));
		diagnosisSetDetailBean.setDiagnosisId(fk);

	}

	public String getDiagnosisId(){
		return StringUtil.getStringValue(
		diagnosisSetDetailBean.getDiagnosisId().getDiagnosisId());

	}
	//---
	
	

	
	public void setDiagnosisSetId(String obj){
		DiagnosisSet fk = new DiagnosisSet();
		fk.setDiagnosisSetId(StringUtil.getIntegerValue(obj,0));
		diagnosisSetDetailBean.setDiagnosisSetId(fk);

	}

	public String getDiagnosisSetId(){
		return StringUtil.getStringValue(
		diagnosisSetDetailBean.getDiagnosisSetId().getDiagnosisSetId());

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
