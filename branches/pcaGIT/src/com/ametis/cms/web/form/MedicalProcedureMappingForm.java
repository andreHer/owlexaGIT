
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * MedicalProcedureMapping is a mapping of medical_procedure_mapping Table.
*/
public class MedicalProcedureMappingForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewMedicalProcedureMapping = false;
	private MedicalProcedureMapping medicalProcedureMappingBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public MedicalProcedureMappingForm()
    {
    	this.medicalProcedureMappingBean = new MedicalProcedureMapping();
    	this.isNewMedicalProcedureMapping = true;
    }
    public MedicalProcedureMappingForm (MedicalProcedureMapping object){
		this.medicalProcedureMappingBean = object;
    }
    public boolean isNewMedicalProcedureMapping (){

    	return this.isNewMedicalProcedureMapping;
    }
	public MedicalProcedureMapping getMedicalProcedureMapping (){
		return this.medicalProcedureMappingBean ;
	}
	public void setMedicalProcedureMapping (MedicalProcedureMapping object){
		this.medicalProcedureMappingBean = object;
	}

			
	public void setMedicalProcedureMappingId(String obj){

		medicalProcedureMappingBean.setMedicalProcedureMappingId(StringUtil.getIntegerValue(obj,0));

	}

	public String getMedicalProcedureMappingId(){
		return StringUtil.getStringValue(
		medicalProcedureMappingBean.getMedicalProcedureMappingId());

	}
	
				
	public void setClientId(String obj){

		medicalProcedureMappingBean.setClientId(StringUtil.getIntegerValue(obj,0));

	}

	public String getClientId(){
		return StringUtil.getStringValue(
		medicalProcedureMappingBean.getClientId());

	}
	
				
	public void setMemberGroupId(String obj){

		medicalProcedureMappingBean.setMemberGroupId(StringUtil.getIntegerValue(obj,0));

	}

	public String getMemberGroupId(){
		return StringUtil.getStringValue(
		medicalProcedureMappingBean.getMemberGroupId());

	}
	
				
	public void setProcedureId(String obj){

		medicalProcedureMappingBean.setProcedureId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProcedureId(){
		return StringUtil.getStringValue(
		medicalProcedureMappingBean.getProcedureId());

	}
	
					public void setProcedureCode(String obj){

		medicalProcedureMappingBean.setProcedureCode(new String(obj));

	}

	public String getProcedureCode(){
		return StringUtil.getStringValue(
		medicalProcedureMappingBean.getProcedureCode());

	}
	
					public void setProcedureName(String obj){

		medicalProcedureMappingBean.setProcedureName(new String(obj));

	}

	public String getProcedureName(){
		return StringUtil.getStringValue(
		medicalProcedureMappingBean.getProcedureName());

	}
	
					public void setDescription(String obj){

		medicalProcedureMappingBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		medicalProcedureMappingBean.getDescription());

	}
	
				
	public void setStatus(String obj){

		medicalProcedureMappingBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		medicalProcedureMappingBean.getStatus());

	}
	
				
	public void setCreatedTime(String obj){

		medicalProcedureMappingBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		medicalProcedureMappingBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		medicalProcedureMappingBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		medicalProcedureMappingBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		medicalProcedureMappingBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		medicalProcedureMappingBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		medicalProcedureMappingBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		medicalProcedureMappingBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		medicalProcedureMappingBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		medicalProcedureMappingBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		medicalProcedureMappingBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		medicalProcedureMappingBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		medicalProcedureMappingBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		medicalProcedureMappingBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
