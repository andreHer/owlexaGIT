
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.ClaimCompletion;
import com.ametis.cms.datamodel.PendingClaim;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * ClaimCompletion is a mapping of claim_completion Table.
*/
public class ClaimCompletionForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewClaimCompletion = false;
	private ClaimCompletion claimCompletionBean ;
	private String claimNumber;
	private String pendingClaimNumber;
	private String claimId;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ClaimCompletionForm()
    {
    	this.claimCompletionBean = new ClaimCompletion();
    	this.isNewClaimCompletion = true;
    }
    public ClaimCompletionForm (ClaimCompletion object){
		this.claimCompletionBean = object;
    }
    public boolean isNewClaimCompletion (){

    	return this.isNewClaimCompletion;
    }
	public ClaimCompletion getClaimCompletion (){
		return this.claimCompletionBean ;
	}
	public void setClaimCompletion (ClaimCompletion object){
		this.claimCompletionBean = object;
	}

			
	public void setCompleteClaimId(String obj){

		claimCompletionBean.setCompleteClaimId(StringUtil.getLongValue(obj,0));

	}

	public String getCompleteClaimId(){
		return StringUtil.getStringValue(
		claimCompletionBean.getCompleteClaimId());

	}
	
				
	public void setPendingClaimId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			PendingClaim pendingClaim =new PendingClaim();
			pendingClaim.setPendingClaimId(Integer.valueOf(obj));
			
			claimCompletionBean.setPendingClaimId(pendingClaim);
		
		}

	}

	public String getPendingClaimId(){
		String result = "";
		
		if (claimCompletionBean.getPendingClaimId() != null){
			result = claimCompletionBean.getPendingClaimId().getPendingClaimId().toString();
		}
		return result;

	}
	
				
	public void setCompleteDate(String obj){

		claimCompletionBean.setCompleteDate(java.sql.Date.valueOf(obj));

	}

	public String getCompleteDate(){
		return StringUtil.getStringValue(
		claimCompletionBean.getCompleteDate());

	}

	
					public void setCompletionRemarks(String obj){

		claimCompletionBean.setCompletionRemarks(new String(obj));

	}

	public String getCompletionRemarks(){
		return StringUtil.getStringValue(
		claimCompletionBean.getCompletionRemarks());

	}
	public String getClaimNumber() {
		return claimNumber;
	}
	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}
	public String getPendingClaimNumber() {
		return pendingClaimNumber;
	}
	public void setPendingClaimNumber(String pendingClaimNumber) {
		this.pendingClaimNumber = pendingClaimNumber;
	}
	public String getClaimId() {
		return claimId;
	}
	public void setClaimId(String claimId) {
		this.claimId = claimId;
	}

}
