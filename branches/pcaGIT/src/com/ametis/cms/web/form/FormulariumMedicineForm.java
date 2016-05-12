
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * FormulariumMedicine is a mapping of formularium_medicine Table.
*/
public class FormulariumMedicineForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewFormulariumMedicine = false;
	private FormulariumMedicine formulariumMedicineBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public FormulariumMedicineForm()
    {
    	this.formulariumMedicineBean = new FormulariumMedicine();
    	this.isNewFormulariumMedicine = true;
    }
    public FormulariumMedicineForm (FormulariumMedicine object){
		this.formulariumMedicineBean = object;
    }
    public boolean isNewFormulariumMedicine (){

    	return this.isNewFormulariumMedicine;
    }
	public FormulariumMedicine getFormulariumMedicine (){
		return this.formulariumMedicineBean ;
	}
	public void setFormulariumMedicine (FormulariumMedicine object){
		this.formulariumMedicineBean = object;
	}

			
	public void setFormulariumId(String obj){

		formulariumMedicineBean.setFormulariumId(StringUtil.getIntegerValue(obj,0));

	}

	public String getFormulariumId(){
		return StringUtil.getStringValue(
		formulariumMedicineBean.getFormulariumId());

	}
	
					public void setFormulariumName(String obj){

		formulariumMedicineBean.setFormulariumName(new String(obj));

	}

	public String getFormulariumName(){
		return StringUtil.getStringValue(
		formulariumMedicineBean.getFormulariumName());

	}
	
					public void setFormulariumCode(String obj){

		formulariumMedicineBean.setFormulariumCode(new String(obj));

	}

	public String getFormulariumCode(){
		return StringUtil.getStringValue(
		formulariumMedicineBean.getFormulariumCode());

	}
	
					public void setPeriode(String obj){

		formulariumMedicineBean.setPeriode(new String(obj));

	}

	public String getPeriode(){
		return StringUtil.getStringValue(
		formulariumMedicineBean.getPeriode());

	}
	
				
	public void setStartDate(String obj){

		formulariumMedicineBean.setStartDate(java.sql.Date.valueOf(obj));

	}

	public String getStartDate(){
		return StringUtil.getStringValue(
		formulariumMedicineBean.getStartDate());

	}

	
				
	public void setEndDate(String obj){

		formulariumMedicineBean.setEndDate(java.sql.Date.valueOf(obj));

	}

	public String getEndDate(){
		return StringUtil.getStringValue(
		formulariumMedicineBean.getEndDate());

	}

	
					public void setDescription(String obj){

		formulariumMedicineBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		formulariumMedicineBean.getDescription());

	}
	
				
	public void setFormulariumType(String obj){

		formulariumMedicineBean.setFormulariumType(StringUtil.getIntegerValue(obj,0));

	}

	public String getFormulariumType(){
		return StringUtil.getStringValue(
		formulariumMedicineBean.getFormulariumType());

	}
	
				
	public void setProviderId(String obj){

		formulariumMedicineBean.setProviderId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProviderId(){
		return StringUtil.getStringValue(
		formulariumMedicineBean.getProviderId());

	}
	
				
	public void setClientId(String obj){

		formulariumMedicineBean.setClientId(StringUtil.getIntegerValue(obj,0));

	}

	public String getClientId(){
		return StringUtil.getStringValue(
		formulariumMedicineBean.getClientId());

	}
	
				
	public void setPolicyId(String obj){

		formulariumMedicineBean.setPolicyId(StringUtil.getIntegerValue(obj,0));

	}

	public String getPolicyId(){
		return StringUtil.getStringValue(
		formulariumMedicineBean.getPolicyId());

	}
	
				
	public void setStatus(String obj){

		formulariumMedicineBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		formulariumMedicineBean.getStatus());

	}
	
				
	public void setCreatedTime(String obj){

		formulariumMedicineBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		formulariumMedicineBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		formulariumMedicineBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		formulariumMedicineBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		formulariumMedicineBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		formulariumMedicineBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		formulariumMedicineBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		formulariumMedicineBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		formulariumMedicineBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		formulariumMedicineBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		formulariumMedicineBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		formulariumMedicineBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		formulariumMedicineBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		formulariumMedicineBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 

}
