
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * TherapyMedicine is a mapping of therapy_medicine Table.
*/
public class TherapyMedicineForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewTherapyMedicine = false;
	private TherapyMedicine therapyMedicineBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public TherapyMedicineForm()
    {
    	this.therapyMedicineBean = new TherapyMedicine();
    	this.isNewTherapyMedicine = true;
    }
    public TherapyMedicineForm (TherapyMedicine object){
		this.therapyMedicineBean = object;
    }
    public boolean isNewTherapyMedicine (){

    	return this.isNewTherapyMedicine;
    }
	public TherapyMedicine getTherapyMedicine (){
		return this.therapyMedicineBean ;
	}
	public void setTherapyMedicine (TherapyMedicine object){
		this.therapyMedicineBean = object;
	}

			
	public void setTherapyMedicineId(String obj){

		therapyMedicineBean.setTherapyMedicineId(StringUtil.getIntegerValue(obj,0));

	}

	public String getTherapyMedicineId(){
		return StringUtil.getStringValue(
		therapyMedicineBean.getTherapyMedicineId());

	}
	
				
	public void setTherapyId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Therapy therapy = new Therapy();
			therapy.setTherapyId(Integer.valueOf(obj));
			
			therapyMedicineBean.setTherapyId(therapy);
		}

	}

	public String getTherapyId(){
		String result = "";
		
		
		if (therapyMedicineBean.getTherapyId() != null){
			result = therapyMedicineBean.getTherapyId().toString();
		}
		return result;
		
		

	}
	
				
	public void setMedicineId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Medicine medicine = new Medicine();
			medicine.setMedicineId(Integer.valueOf(obj));
			therapyMedicineBean.setMedicineId(medicine);
		}

	}

	public String getMedicineId(){
		String result = "";
		
		
		if (therapyMedicineBean.getMedicineId() != null){
			result = therapyMedicineBean.getMedicineId().toString();
		}
		return result;

	}
	
				
	public void setTotalUsage(String obj){

		therapyMedicineBean.setTotalUsage(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalUsage(){
		return StringUtil.getStringValue(
		therapyMedicineBean.getTotalUsage());

	}
	
				
	public void setTotalCharge(String obj){

		therapyMedicineBean.setTotalCharge(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalCharge(){
		return StringUtil.getStringValue(
		therapyMedicineBean.getTotalCharge());

	}
	
				
	public void setReferencePrice(String obj){

		therapyMedicineBean.setReferencePrice(StringUtil.getDoubleValue(obj,0));

	}

	public String getReferencePrice(){
		return StringUtil.getStringValue(
		therapyMedicineBean.getReferencePrice());

	}
	
					public void setDescription(String obj){

		therapyMedicineBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		therapyMedicineBean.getDescription());

	}
	
				
	public void setCreatedTime(String obj){

		therapyMedicineBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		therapyMedicineBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		therapyMedicineBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		therapyMedicineBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		therapyMedicineBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		therapyMedicineBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		therapyMedicineBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		therapyMedicineBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		therapyMedicineBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		therapyMedicineBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		therapyMedicineBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		therapyMedicineBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		therapyMedicineBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		therapyMedicineBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 

}
