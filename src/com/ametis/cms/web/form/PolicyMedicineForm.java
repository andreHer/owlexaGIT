
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * PolicyMedicine is a mapping of policy_medicine Table.
*/
public class PolicyMedicineForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewPolicyMedicine = false;
	private PolicyMedicine policyMedicineBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public PolicyMedicineForm()
    {
    	this.policyMedicineBean = new PolicyMedicine();
    	this.isNewPolicyMedicine = true;
    }
    public PolicyMedicineForm (PolicyMedicine object){
		this.policyMedicineBean = object;
    }
    public boolean isNewPolicyMedicine (){

    	return this.isNewPolicyMedicine;
    }
	public PolicyMedicine getPolicyMedicine (){
		return this.policyMedicineBean ;
	}
	public void setPolicyMedicine (PolicyMedicine object){
		this.policyMedicineBean = object;
	}

			
	public void setPolicyMedicineId(String obj){

		policyMedicineBean.setPolicyMedicineId(StringUtil.getIntegerValue(obj,0));

	}

	public String getPolicyMedicineId(){
		return StringUtil.getStringValue(
		policyMedicineBean.getPolicyMedicineId());

	}
	
				
	public void setDeletedStatus(String obj){

		policyMedicineBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		policyMedicineBean.getDeletedStatus());

	}
	
				
	public void setModifiedTime(String obj){

		policyMedicineBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		policyMedicineBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		policyMedicineBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		policyMedicineBean.getModifiedBy());

	}
	
					public void setDeletedBy(String obj){

		policyMedicineBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		policyMedicineBean.getDeletedBy());

	}
	
				
	public void setDeletedTime(String obj){

		policyMedicineBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		policyMedicineBean.getDeletedTime());

	}

	
				
	public void setCreatedTime(String obj){

		policyMedicineBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		policyMedicineBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		policyMedicineBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		policyMedicineBean.getCreatedBy());

	}
	
				
	public void setItemValue(String obj){

		policyMedicineBean.setItemValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getItemValue(){
		return StringUtil.getStringValue(
		policyMedicineBean.getItemValue());

	}
	
										
	public void setReferencePrice(String obj){

		policyMedicineBean.setReferencePrice(StringUtil.getDoubleValue(obj,0));

	}

	public String getReferencePrice(){
		return StringUtil.getStringValue(
		policyMedicineBean.getReferencePrice());

	}
	
					public void setDescription(String obj){

		policyMedicineBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		policyMedicineBean.getDescription());

	}
	
		

	// foreign affairs
	
	

	
	public void setMedicineId(String obj){
		Medicine fk = new Medicine();
		fk.setMedicineId(StringUtil.getIntegerValue(obj,0));
		policyMedicineBean.setMedicineId(fk);

	}

	public String getMedicineId(){
		return StringUtil.getStringValue(
		policyMedicineBean.getMedicineId().getMedicineId());

	}
	//---
	
	

	
	public void setPolicyId(String obj){
		Policy fk = new Policy();
		fk.setPolicyId(StringUtil.getIntegerValue(obj,0));
		policyMedicineBean.setPolicyId(fk);

	}

	public String getPolicyId(){
		return StringUtil.getStringValue(
		policyMedicineBean.getPolicyId().getPolicyId());

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
