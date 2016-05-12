
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * MedicineCategory is a mapping of medicine_category Table.
*/
public class MedicineCategoryForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewMedicineCategory = false;
	private MedicineCategory medicineCategoryBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public MedicineCategoryForm()
    {
    	this.medicineCategoryBean = new MedicineCategory();
    	this.isNewMedicineCategory = true;
    }
    public MedicineCategoryForm (MedicineCategory object){
		this.medicineCategoryBean = object;
    }
    public boolean isNewMedicineCategory (){

    	return this.isNewMedicineCategory;
    }
	public MedicineCategory getMedicineCategory (){
		return this.medicineCategoryBean ;
	}
	public void setMedicineCategory (MedicineCategory object){
		this.medicineCategoryBean = object;
	}

			
	public void setMedicineCategoryId(String obj){

		medicineCategoryBean.setMedicineCategoryId(StringUtil.getIntegerValue(obj,0));

	}

	public String getMedicineCategoryId(){
		return StringUtil.getStringValue(
		medicineCategoryBean.getMedicineCategoryId());

	}
	
					public void setMedicineCategoryName(String obj){

		medicineCategoryBean.setMedicineCategoryName(new String(obj));

	}

	public String getMedicineCategoryName(){
		return StringUtil.getStringValue(
		medicineCategoryBean.getMedicineCategoryName());

	}
	
					public void setMedicineCategoryCode(String obj){

		medicineCategoryBean.setMedicineCategoryCode(new String(obj));

	}

	public String getMedicineCategoryCode(){
		return StringUtil.getStringValue(
		medicineCategoryBean.getMedicineCategoryCode());

	}
	
					public void setDescription(String obj){

		medicineCategoryBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		medicineCategoryBean.getDescription());

	}
	
				
	public void setCreatedTime(String obj){

		medicineCategoryBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		medicineCategoryBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		medicineCategoryBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		medicineCategoryBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		medicineCategoryBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		medicineCategoryBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		medicineCategoryBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		medicineCategoryBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		medicineCategoryBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		medicineCategoryBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		medicineCategoryBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		medicineCategoryBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		medicineCategoryBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		medicineCategoryBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 

}
