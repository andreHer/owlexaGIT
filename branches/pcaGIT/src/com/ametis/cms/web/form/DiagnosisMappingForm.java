
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * DiagnosisMapping is a mapping of diagnosis_mapping Table.
*/
public class DiagnosisMappingForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewDiagnosisMapping = false;
	private DiagnosisMapping diagnosisMappingBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public DiagnosisMappingForm()
    {
    	this.diagnosisMappingBean = new DiagnosisMapping();
    	this.isNewDiagnosisMapping = true;
    }
    public DiagnosisMappingForm (DiagnosisMapping object){
		this.diagnosisMappingBean = object;
    }
    public boolean isNewDiagnosisMapping (){

    	return this.isNewDiagnosisMapping;
    }
	public DiagnosisMapping getDiagnosisMapping (){
		return this.diagnosisMappingBean ;
	}
	public void setDiagnosisMapping (DiagnosisMapping object){
		this.diagnosisMappingBean = object;
	}

			
	public void setDiagnosisMappingId(String obj){

		diagnosisMappingBean.setDiagnosisMappingId(StringUtil.getIntegerValue(obj,0));

	}

	public String getDiagnosisMappingId(){
		return StringUtil.getStringValue(
		diagnosisMappingBean.getDiagnosisMappingId());

	}
	
				
	public void setClientId(String obj){

		diagnosisMappingBean.setClientId(StringUtil.getIntegerValue(obj,0));

	}

	public String getClientId(){
		return StringUtil.getStringValue(
		diagnosisMappingBean.getClientId());

	}
	
					public void setDiagnosisCode(String obj){

		diagnosisMappingBean.setDiagnosisCode(new String(obj));

	}

	public String getDiagnosisCode(){
		return StringUtil.getStringValue(
		diagnosisMappingBean.getDiagnosisCode());

	}
	
					public void setDiagnosisName(String obj){

		diagnosisMappingBean.setDiagnosisName(new String(obj));

	}

	public String getDiagnosisName(){
		return StringUtil.getStringValue(
		diagnosisMappingBean.getDiagnosisName());

	}
	
					public void setDescription(String obj){

		diagnosisMappingBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		diagnosisMappingBean.getDescription());

	}
	
					public void setClientDiagnosisCode(String obj){

		diagnosisMappingBean.setClientDiagnosisCode(new String(obj));

	}

	public String getClientDiagnosisCode(){
		return StringUtil.getStringValue(
		diagnosisMappingBean.getClientDiagnosisCode());

	}
	
				
	public void setMemberGroupId(String obj){

		diagnosisMappingBean.setMemberGroupId(StringUtil.getIntegerValue(obj,0));

	}

	public String getMemberGroupId(){
		return StringUtil.getStringValue(
		diagnosisMappingBean.getMemberGroupId());

	}
	
				
	public void setCreatedTime(String obj){

		diagnosisMappingBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		diagnosisMappingBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		diagnosisMappingBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		diagnosisMappingBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		diagnosisMappingBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		diagnosisMappingBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		diagnosisMappingBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		diagnosisMappingBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		diagnosisMappingBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		diagnosisMappingBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		diagnosisMappingBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		diagnosisMappingBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		diagnosisMappingBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		diagnosisMappingBean.getDeletedStatus());

	}
	
				
	public void setDiagnosisId(String obj){

		diagnosisMappingBean.setDiagnosisId(StringUtil.getIntegerValue(obj,0));

	}

	public String getDiagnosisId(){
		return StringUtil.getStringValue(
		diagnosisMappingBean.getDiagnosisId());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
