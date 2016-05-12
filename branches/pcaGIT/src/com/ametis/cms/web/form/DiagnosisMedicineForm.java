
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * DiagnosisMedicine is a mapping of diagnosis_medicine Table.
*/
public class DiagnosisMedicineForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewDiagnosisMedicine = false;
	private DiagnosisMedicine diagnosisMedicineBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public DiagnosisMedicineForm()
    {
    	this.diagnosisMedicineBean = new DiagnosisMedicine();
    	this.isNewDiagnosisMedicine = true;
    }
    public DiagnosisMedicineForm (DiagnosisMedicine object){
		this.diagnosisMedicineBean = object;
    }
    public boolean isNewDiagnosisMedicine (){

    	return this.isNewDiagnosisMedicine;
    }
	public DiagnosisMedicine getDiagnosisMedicine (){
		return this.diagnosisMedicineBean ;
	}
	public void setDiagnosisMedicine (DiagnosisMedicine object){
		this.diagnosisMedicineBean = object;
	}

			
	public void setDiagnosisMedicineId(String obj){

		diagnosisMedicineBean.setDiagnosisMedicineId(StringUtil.getIntegerValue(obj,0));

	}

	public String getDiagnosisMedicineId(){
		return StringUtil.getStringValue(
		diagnosisMedicineBean.getDiagnosisMedicineId());

	}
	
										
	public void setTotalUsage(String obj){

		diagnosisMedicineBean.setTotalUsage(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalUsage(){
		return StringUtil.getStringValue(
		diagnosisMedicineBean.getTotalUsage());

	}
	
				
	public void setTotalCharge(String obj){

		diagnosisMedicineBean.setTotalCharge(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalCharge(){
		return StringUtil.getStringValue(
		diagnosisMedicineBean.getTotalCharge());

	}
	
				
	public void setReferencePrice(String obj){

		diagnosisMedicineBean.setReferencePrice(StringUtil.getDoubleValue(obj,0));

	}

	public String getReferencePrice(){
		return StringUtil.getStringValue(
		diagnosisMedicineBean.getReferencePrice());

	}
	
					public void setDescription(String obj){

		diagnosisMedicineBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		diagnosisMedicineBean.getDescription());

	}
	
				
	public void setCreatedTime(String obj){

		diagnosisMedicineBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		diagnosisMedicineBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		diagnosisMedicineBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		diagnosisMedicineBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		diagnosisMedicineBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		diagnosisMedicineBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		diagnosisMedicineBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		diagnosisMedicineBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		diagnosisMedicineBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		diagnosisMedicineBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		diagnosisMedicineBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		diagnosisMedicineBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		diagnosisMedicineBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		diagnosisMedicineBean.getDeletedStatus());

	}
	
		

	// foreign affairs
	
	

	
	public void setMedicineId(String obj){
		Medicine fk = new Medicine();
		fk.setMedicineId(StringUtil.getIntegerValue(obj,0));
		diagnosisMedicineBean.setMedicineId(fk);

	}

	public String getMedicineId(){
		return StringUtil.getStringValue(
		diagnosisMedicineBean.getMedicineId().getMedicineId());

	}
	//---
	
	

	
	public void setDiagnosisId(String obj){
		Diagnosis fk = new Diagnosis();
		fk.setDiagnosisId(StringUtil.getIntegerValue(obj,0));
		diagnosisMedicineBean.setDiagnosisId(fk);

	}

	public String getDiagnosisId(){
		return StringUtil.getStringValue(
		diagnosisMedicineBean.getDiagnosisId().getDiagnosisId());

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
