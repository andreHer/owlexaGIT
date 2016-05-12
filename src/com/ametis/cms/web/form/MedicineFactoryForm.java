
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * MedicineFactory is a mapping of medicine_factory Table.
*/
public class MedicineFactoryForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewMedicineFactory = false;
	private MedicineFactory medicineFactoryBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public MedicineFactoryForm()
    {
    	this.medicineFactoryBean = new MedicineFactory();
    	this.isNewMedicineFactory = true;
    }
    public MedicineFactoryForm (MedicineFactory object){
		this.medicineFactoryBean = object;
    }
    public boolean isNewMedicineFactory (){

    	return this.isNewMedicineFactory;
    }
	public MedicineFactory getMedicineFactory (){
		return this.medicineFactoryBean ;
	}
	public void setMedicineFactory (MedicineFactory object){
		this.medicineFactoryBean = object;
	}

			
	public void setMedicineFactoryId(String obj){

		medicineFactoryBean.setMedicineFactoryId(StringUtil.getIntegerValue(obj,0));

	}

	public String getMedicineFactoryId(){
		return StringUtil.getStringValue(
		medicineFactoryBean.getMedicineFactoryId());

	}
	
					public void setMedicineFactoryName(String obj){

		medicineFactoryBean.setMedicineFactoryName(new String(obj));

	}

	public String getMedicineFactoryName(){
		return StringUtil.getStringValue(
		medicineFactoryBean.getMedicineFactoryName());

	}
	
					public void setMedicineFactoryCode(String obj){

		medicineFactoryBean.setMedicineFactoryCode(new String(obj));

	}

	public String getMedicineFactoryCode(){
		return StringUtil.getStringValue(
		medicineFactoryBean.getMedicineFactoryCode());

	}
	
					public void setDescription(String obj){

		medicineFactoryBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		medicineFactoryBean.getDescription());

	}
	
				
	public void setCreatedTime(String obj){

		medicineFactoryBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		medicineFactoryBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		medicineFactoryBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		medicineFactoryBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		medicineFactoryBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		medicineFactoryBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		medicineFactoryBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		medicineFactoryBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		medicineFactoryBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		medicineFactoryBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		medicineFactoryBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		medicineFactoryBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		medicineFactoryBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		medicineFactoryBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 

}
