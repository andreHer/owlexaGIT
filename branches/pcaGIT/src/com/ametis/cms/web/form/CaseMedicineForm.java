
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * CaseMedicine is a mapping of case_medicine Table.
*/
public class CaseMedicineForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewCaseMedicine = false;
	private CaseMedicine caseMedicineBean ;
	private String medicineName;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public CaseMedicineForm()
    {
    	this.caseMedicineBean = new CaseMedicine();
    	this.isNewCaseMedicine = true;
    }
    public CaseMedicineForm (CaseMedicine object){
		this.caseMedicineBean = object;
    }
    public boolean isNewCaseMedicine (){

    	return this.isNewCaseMedicine;
    }
	public CaseMedicine getCaseMedicine (){
		return this.caseMedicineBean ;
	}
	public void setCaseMedicine (CaseMedicine object){
		this.caseMedicineBean = object;
	}

			
	public void setCaseMedicineId(String obj){

		caseMedicineBean.setCaseMedicineId(StringUtil.getIntegerValue(obj,0));

	}

	public String getCaseMedicineId(){
		return StringUtil.getStringValue(
		caseMedicineBean.getCaseMedicineId());

	}
	
				
	public void setCaseId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Case theCase = new Case();
			theCase.setCaseId(Integer.valueOf(obj));
			caseMedicineBean.setCaseId(theCase);
		}

	}

	public String getCaseId(){
		String result = "";
		
		if (caseMedicineBean.getCaseId() != null){
			result = StringUtil.getStringValue(
					caseMedicineBean.getCaseId().getCaseId());
		}
		return result;
		

	}
	
				
	public void setMedicineId(String obj){
		
		if (obj != null && !obj.equalsIgnoreCase("")){
			Medicine med = new Medicine();
			med.setMedicineId(Integer.valueOf(obj));
			caseMedicineBean.setMedicineId(med);	
		}

		

	}

	public String getMedicineId(){
		
		String result = "";
		
		if (caseMedicineBean.getMedicineId() != null){
			result = StringUtil.getStringValue(
					caseMedicineBean.getMedicineId().getMedicineId());
		}
		return result;
		

	}
	
				
	public void setTotalUsage(String obj){

		caseMedicineBean.setTotalUsage(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalUsage(){
		return StringUtil.getStringValue(
		caseMedicineBean.getTotalUsage());

	}
	
				
	public void setTotalCharge(String obj){

		caseMedicineBean.setTotalCharge(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalCharge(){
		return StringUtil.getStringValue(
		caseMedicineBean.getTotalCharge());

	}
	
				
	public void setReferencePrice(String obj){

		caseMedicineBean.setReferencePrice(StringUtil.getDoubleValue(obj,0));

	}

	public String getReferencePrice(){
		return StringUtil.getStringValue(
		caseMedicineBean.getReferencePrice());

	}
	
					public void setDescription(String obj){

		caseMedicineBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		caseMedicineBean.getDescription());

	}
	
				
	public void setCreatedTime(String obj){

		caseMedicineBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		caseMedicineBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		caseMedicineBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		caseMedicineBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		caseMedicineBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		caseMedicineBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		caseMedicineBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		caseMedicineBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		caseMedicineBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		caseMedicineBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		caseMedicineBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		caseMedicineBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		caseMedicineBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		caseMedicineBean.getDeletedStatus());

	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
