
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * DiagnosisMedicineCategory is a mapping of diagnosis_medicine_category Table.
*/
public class DiagnosisMedicineCategoryForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewDiagnosisMedicineCategory = false;
	private DiagnosisMedicineCategory diagnosisMedicineCategoryBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public DiagnosisMedicineCategoryForm()
    {
    	this.diagnosisMedicineCategoryBean = new DiagnosisMedicineCategory();
    	this.isNewDiagnosisMedicineCategory = true;
    }
    public DiagnosisMedicineCategoryForm (DiagnosisMedicineCategory object){
		this.diagnosisMedicineCategoryBean = object;
    }
    public boolean isNewDiagnosisMedicineCategory (){

    	return this.isNewDiagnosisMedicineCategory;
    }
	public DiagnosisMedicineCategory getDiagnosisMedicineCategory (){
		return this.diagnosisMedicineCategoryBean ;
	}
	public void setDiagnosisMedicineCategory (DiagnosisMedicineCategory object){
		this.diagnosisMedicineCategoryBean = object;
	}

			
	public void setDiagnosisMedCategoryId(String obj){

		diagnosisMedicineCategoryBean.setDiagnosisMedCategoryId(StringUtil.getIntegerValue(obj,0));

	}

	public String getDiagnosisMedCategoryId(){
		return StringUtil.getStringValue(
		diagnosisMedicineCategoryBean.getDiagnosisMedCategoryId());

	}
	
				
	public void setDiagnosisId(String obj){

		diagnosisMedicineCategoryBean.setDiagnosisId(StringUtil.getIntegerValue(obj,0));

	}

	public String getDiagnosisId(){
		return StringUtil.getStringValue(
		diagnosisMedicineCategoryBean.getDiagnosisId());

	}
	
				
	public void setMedicineCategoryId(String obj){

		diagnosisMedicineCategoryBean.setMedicineCategoryId(StringUtil.getIntegerValue(obj,0));

	}

	public String getMedicineCategoryId(){
		return StringUtil.getStringValue(
		diagnosisMedicineCategoryBean.getMedicineCategoryId());

	}
	
				
	public void setTotalUsage(String obj){

		diagnosisMedicineCategoryBean.setTotalUsage(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalUsage(){
		return StringUtil.getStringValue(
		diagnosisMedicineCategoryBean.getTotalUsage());

	}
	
				
	public void setTotalCharge(String obj){

		diagnosisMedicineCategoryBean.setTotalCharge(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalCharge(){
		return StringUtil.getStringValue(
		diagnosisMedicineCategoryBean.getTotalCharge());

	}
	
				
	public void setReferencePrice(String obj){

		diagnosisMedicineCategoryBean.setReferencePrice(StringUtil.getDoubleValue(obj,0));

	}

	public String getReferencePrice(){
		return StringUtil.getStringValue(
		diagnosisMedicineCategoryBean.getReferencePrice());

	}
	
					public void setDescription(String obj){

		diagnosisMedicineCategoryBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		diagnosisMedicineCategoryBean.getDescription());

	}
	
				
	public void setCreatedTime(String obj){

		diagnosisMedicineCategoryBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		diagnosisMedicineCategoryBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		diagnosisMedicineCategoryBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		diagnosisMedicineCategoryBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		diagnosisMedicineCategoryBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		diagnosisMedicineCategoryBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		diagnosisMedicineCategoryBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		diagnosisMedicineCategoryBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		diagnosisMedicineCategoryBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		diagnosisMedicineCategoryBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		diagnosisMedicineCategoryBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		diagnosisMedicineCategoryBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		diagnosisMedicineCategoryBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		diagnosisMedicineCategoryBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 

}
