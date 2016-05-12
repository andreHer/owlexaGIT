
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * ClaimProcedure is a mapping of claim_procedure Table.
*/
public class ClaimProcedureForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewClaimProcedure = false;
	private ClaimProcedure claimProcedureBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ClaimProcedureForm()
    {
    	this.claimProcedureBean = new ClaimProcedure();
    	this.isNewClaimProcedure = true;
    }
    public ClaimProcedureForm (ClaimProcedure object){
		this.claimProcedureBean = object;
    }
    public boolean isNewClaimProcedure (){

    	return this.isNewClaimProcedure;
    }
	public ClaimProcedure getClaimProcedure (){
		return this.claimProcedureBean ;
	}
	public void setClaimProcedure (ClaimProcedure object){
		this.claimProcedureBean = object;
	}

			
	public void setClaimProcedureId(String obj){

		claimProcedureBean.setClaimProcedureId(StringUtil.getIntegerValue(obj,0));

	}

	public String getClaimProcedureId(){
		return StringUtil.getStringValue(
		claimProcedureBean.getClaimProcedureId());

	}
	
				
	/*public void setClaimId(String obj){

		claimProcedureBean.setClaimId(StringUtil.getIntegerValue(obj,0));

	}
*/
	public String getClaimId(){
		return StringUtil.getStringValue(
		claimProcedureBean.getClaimId());

	}
	
				
	/*public void setProcedureId(String obj){

		claimProcedureBean.setProcedureId(StringUtil.getIntegerValue(obj,0));

	}*/

	public String getProcedureId(){
		return StringUtil.getStringValue(
		claimProcedureBean.getProcedureId());

	}
	
				
	public void setTotalUsage(String obj){

		claimProcedureBean.setTotalUsage(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalUsage(){
		return StringUtil.getStringValue(
		claimProcedureBean.getTotalUsage());

	}
	
				
	public void setTotalCharge(String obj){

		claimProcedureBean.setTotalCharge(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalCharge(){
		return StringUtil.getStringValue(
		claimProcedureBean.getTotalCharge());

	}
	
				
	public void setReferencePrice(String obj){

		claimProcedureBean.setReferencePrice(StringUtil.getDoubleValue(obj,0));

	}

	public String getReferencePrice(){
		return StringUtil.getStringValue(
		claimProcedureBean.getReferencePrice());

	}
	
					public void setDescription(String obj){

		claimProcedureBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		claimProcedureBean.getDescription());

	}
	
				
	public void setCreatedTime(String obj){

		claimProcedureBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		claimProcedureBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		claimProcedureBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		claimProcedureBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		claimProcedureBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		claimProcedureBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		claimProcedureBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		claimProcedureBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		claimProcedureBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		claimProcedureBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		claimProcedureBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		claimProcedureBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		claimProcedureBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		claimProcedureBean.getDeletedStatus());

	}
	
				
	public void setApprovedValue(String obj){

		claimProcedureBean.setApprovedValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getApprovedValue(){
		return StringUtil.getStringValue(
		claimProcedureBean.getApprovedValue());

	}
	
				
	public void setStatus(String obj){

		claimProcedureBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		claimProcedureBean.getStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
