
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.PendingCategory;
import com.ametis.cms.datamodel.PendingClaim;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * PendingClaim is a mapping of pending_claim Table.
*/
public class PendingClaimForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewPendingClaim = false;
	private PendingClaim pendingClaimBean ;
	private String claimNumber;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public PendingClaimForm()
    {
    	this.pendingClaimBean = new PendingClaim();
    	this.isNewPendingClaim = true;
    }
    public PendingClaimForm (PendingClaim object){
		this.pendingClaimBean = object;
    }
    public boolean isNewPendingClaim (){

    	return this.isNewPendingClaim;
    }
	public PendingClaim getPendingClaim (){
		return this.pendingClaimBean ;
	}
	public void setPendingClaim (PendingClaim object){
		this.pendingClaimBean = object;
	}

			
	public void setPendingClaimId(String obj){

		pendingClaimBean.setPendingClaimId(StringUtil.getIntegerValue(obj,0));

	}

	public String getPendingClaimId(){
		return StringUtil.getStringValue(
		pendingClaimBean.getPendingClaimId());

	}
	
				
	public void setClaimId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Claim claimId = new Claim();
			claimId.setClaimId(Integer.valueOf(obj));
			pendingClaimBean.setClaimId(claimId);
		}

	}

	public String getClaimId(){
		String result = "";
		
		if (pendingClaimBean.getClaimId() != null){
			result = pendingClaimBean.getClaimId().getClaimId().toString();
		}
		return result;
	}
	
				
	public void setPendingCategory(String obj){
		PendingCategory fk = new PendingCategory();
		fk.setPendingCategoryId(StringUtil.getIntegerValue(obj,0));
		pendingClaimBean.setPendingCategory(fk);
	}

	public String getPendingCategory(){
		PendingCategory fk = pendingClaimBean.getPendingCategory();
		Integer id = null;
		if (fk != null){
			id = fk.getPendingCategoryId();
		}
		else {
			id = Integer.valueOf(0);
		}
		return StringUtil.getStringValue(id);
	}
	
				
	public void setPendingDueDate(String obj){

		pendingClaimBean.setPendingDueDate(java.sql.Date.valueOf(obj));

	}

	public String getPendingDueDate(){
		return StringUtil.getStringValue(
		pendingClaimBean.getPendingDueDate());

	}

	
					public void setPendingSubject(String obj){

		pendingClaimBean.setPendingSubject(new String(obj));

	}

	public String getPendingSubject(){
		return StringUtil.getStringValue(
		pendingClaimBean.getPendingSubject());

	}
	
					public void setRemarks(String obj){

		pendingClaimBean.setRemarks(new String(obj));

	}

	public String getRemarks(){
		return StringUtil.getStringValue(
		pendingClaimBean.getRemarks());

	}
	public String getClaimNumber() {
		return claimNumber;
	}
	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}
 
}
