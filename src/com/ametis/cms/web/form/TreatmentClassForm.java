
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * TreatmentClass is a mapping of treatment_class Table.
*/
public class TreatmentClassForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewTreatmentClass = false;
	private TreatmentClass treatmentClassBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public TreatmentClassForm()
    {
    	this.treatmentClassBean = new TreatmentClass();
    	this.isNewTreatmentClass = true;
    }
    public TreatmentClassForm (TreatmentClass object){
		this.treatmentClassBean = object;
    }
    public boolean isNewTreatmentClass (){

    	return this.isNewTreatmentClass;
    }
	public TreatmentClass getTreatmentClass (){
		return this.treatmentClassBean ;
	}
	public void setTreatmentClass (TreatmentClass object){
		this.treatmentClassBean = object;
	}

			
	public void setTreatmentClassId(String obj){

		treatmentClassBean.setTreatmentClassId(StringUtil.getIntegerValue(obj,0));

	}

	public String getTreatmentClassId(){
		return StringUtil.getStringValue(
		treatmentClassBean.getTreatmentClassId());

	}
	
					public void setTreatmentClassName(String obj){

		treatmentClassBean.setTreatmentClassName(new String(obj));

	}

	public String getTreatmentClassName(){
		return StringUtil.getStringValue(
		treatmentClassBean.getTreatmentClassName());

	}
	
					public void setTreatmentClassCode(String obj){

		treatmentClassBean.setTreatmentClassCode(new String(obj));

	}

	public String getTreatmentClassCode(){
		return StringUtil.getStringValue(
		treatmentClassBean.getTreatmentClassCode());

	}
	
					public void setDescription(String obj){

		treatmentClassBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		treatmentClassBean.getDescription());

	}
	
				
	public void setClassIndex(String obj){

		treatmentClassBean.setClassIndex(StringUtil.getDoubleValue(obj,0));

	}

	public String getClassIndex(){
		return StringUtil.getStringValue(
		treatmentClassBean.getClassIndex());

	}
	
				
	public void setCreatedTime(String obj){

		treatmentClassBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		treatmentClassBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		treatmentClassBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		treatmentClassBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		treatmentClassBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		treatmentClassBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		treatmentClassBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		treatmentClassBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		treatmentClassBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		treatmentClassBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		treatmentClassBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		treatmentClassBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		treatmentClassBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		treatmentClassBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
