
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * ClaimMedicine is a mapping of claim_medicine Table.
*/
public class ClaimMedicineForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewClaimMedicine = false;
	private ClaimMedicine claimMedicineBean ;
	private String medicineName;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ClaimMedicineForm()
    {
    	this.claimMedicineBean = new ClaimMedicine();
    	this.isNewClaimMedicine = true;
    }
    public ClaimMedicineForm (ClaimMedicine object){
		this.claimMedicineBean = object;
    }
    public boolean isNewClaimMedicine (){

    	return this.isNewClaimMedicine;
    }
	public ClaimMedicine getClaimMedicine (){
		return this.claimMedicineBean ;
	}
	public void setClaimMedicine (ClaimMedicine object){
		this.claimMedicineBean = object;
	}

			
	public void setClaimMedicineId(String obj){

		claimMedicineBean.setClaimMedicineId(StringUtil.getIntegerValue(obj,0));

	}

	public String getClaimMedicineId(){
		return StringUtil.getStringValue(
		claimMedicineBean.getClaimMedicineId());

	}
	
				
	public void setClaimId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Claim claim = new Claim();
			claim.setClaimId(Integer.valueOf(obj));
			claimMedicineBean.setClaimId(claim);
		}

	}

	public String getClaimId(){
		return StringUtil.getStringValue(
		claimMedicineBean.getClaimId().getClaimId());

	}
	
				
	public void setMedicineId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Medicine med = new Medicine();
			med.setMedicineId(Integer.valueOf(obj));
			claimMedicineBean.setMedicineId(med);
		}

	}

	public String getMedicineId(){
		String result = "";
		
		if (claimMedicineBean.getMedicineId() != null){
			result = StringUtil.getStringValue(
					claimMedicineBean.getMedicineId().getMedicineId());
		}
		return result;
		

	}
	
				
	public void setTotalUsage(String obj){

		claimMedicineBean.setTotalUsage(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalUsage(){
		return StringUtil.getStringValue(
		claimMedicineBean.getTotalUsage());

	}
	
				
	public void setTotalCharge(String obj){

		claimMedicineBean.setTotalCharge(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalCharge(){
		return StringUtil.getStringValue(
		claimMedicineBean.getTotalCharge());

	}
	
				
	public void setReferencePrice(String obj){

		claimMedicineBean.setReferencePrice(StringUtil.getDoubleValue(obj,0));

	}

	public String getReferencePrice(){
		return StringUtil.getStringValue(
		claimMedicineBean.getReferencePrice());

	}
	
					public void setDescription(String obj){

		claimMedicineBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		claimMedicineBean.getDescription());

	}
	
				
	public void setCreatedTime(String obj){

		claimMedicineBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		claimMedicineBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		claimMedicineBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		claimMedicineBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		claimMedicineBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		claimMedicineBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		claimMedicineBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		claimMedicineBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		claimMedicineBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		claimMedicineBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		claimMedicineBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		claimMedicineBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		claimMedicineBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		claimMedicineBean.getDeletedStatus());

	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	
	
// class- 
}
