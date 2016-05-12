
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * Medicine is a mapping of medicine Table.
*/
public class MedicineForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewMedicine = false;
	private Medicine medicineBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public MedicineForm()
    {
    	this.medicineBean = new Medicine();
    	this.isNewMedicine = true;
    }
    public MedicineForm (Medicine object){
		this.medicineBean = object;
    }
    public boolean isNewMedicine (){

    	return this.isNewMedicine;
    }
	public Medicine getMedicine (){
		return this.medicineBean ;
	}
	public void setMedicine (Medicine object){
		this.medicineBean = object;
	}

			
	public void setMedicineId(String obj){

		medicineBean.setMedicineId(StringUtil.getIntegerValue(obj,0));

	}

	public String getMedicineId(){
		return StringUtil.getStringValue(
		medicineBean.getMedicineId());

	}
	
				
	public void setMedicineType(String obj){

		medicineBean.setMedicineType(obj);

	}

	public String getMedicineType(){
		return medicineBean.getMedicineType();

	}
	
				
	public void setDeletedStatus(String obj){

		medicineBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		medicineBean.getDeletedStatus());

	}
	
				
	public void setModifiedTime(String obj){

		medicineBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		medicineBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		medicineBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		medicineBean.getModifiedBy());

	}
	
					public void setDeletedBy(String obj){

		medicineBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		medicineBean.getDeletedBy());

	}
	
				
	public void setDeletedTime(String obj){

		medicineBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		medicineBean.getDeletedTime());

	}

	
				
	public void setCreatedTime(String obj){

		medicineBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		medicineBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		medicineBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		medicineBean.getCreatedBy());

	}
	
					public void setMedicineName(String obj){

		medicineBean.setMedicineName(new String(obj));

	}

	public String getMedicineName(){
		return StringUtil.getStringValue(
		medicineBean.getMedicineName());

	}
	
					public void setDescription(String obj){

		medicineBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		medicineBean.getDescription());

	}
	
					public void setGeneralDiagnose(String obj){

		medicineBean.setGeneralDiagnose(new String(obj));

	}

	public String getGeneralDiagnose(){
		return StringUtil.getStringValue(
		medicineBean.getGeneralDiagnose());

	}
	
					public void setMedicalDosage(String obj){

		medicineBean.setMedicalDosage(new String(obj));

	}

	public String getMedicalDosage(){
		return StringUtil.getStringValue(
		medicineBean.getMedicalDosage());

	}
	
					public void setMedicineClassification(String obj){

		medicineBean.setMedicineClassification(new String(obj));

	}

	public String getMedicineClassification(){
		return StringUtil.getStringValue(
		medicineBean.getMedicineClassification());

	}
	
					public void setMedicineCode(String obj){

		medicineBean.setMedicineCode(new String(obj));

	}

	public String getMedicineCode(){
		return StringUtil.getStringValue(
		medicineBean.getMedicineCode());

	}
	
				
	public void setMedicinePrice(String obj){

		medicineBean.setMedicinePrice(StringUtil.getDoubleValue(obj,0));

	}

	public String getMedicinePrice(){
		return StringUtil.getStringValue(
		medicineBean.getMedicinePrice());

	}
	
					public void setMedicineFactory(String obj){

		medicineBean.setMedicineFactory(new String(obj));

	}

	public String getMedicineFactory(){
		return StringUtil.getStringValue(
		medicineBean.getMedicineFactory());

	}
	
					public void setContraIndication(String obj){

		medicineBean.setContraIndication(new String(obj));

	}

	public String getContraIndication(){
		return StringUtil.getStringValue(
		medicineBean.getContraIndication());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
