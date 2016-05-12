package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.Claim;

public class CompleteClaimForm implements java.io.Serializable{
	
	private boolean isNewCompleteClaim = false;
	
	private String completionDescription;
	
	private java.sql.Date completionDate;
	
	private Claim claim;
	
	public CompleteClaimForm (Claim claim){
		this.claim = claim;
	}
	public CompleteClaimForm(){}
	
	

	public java.sql.Date getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(java.sql.Date completionDate) {
		this.completionDate = completionDate;
	}

	public String getCompletionDescription() {
		return completionDescription;
	}

	public void setCompletionDescription(String completionDescription) {
		this.completionDescription = completionDescription;
	}

	public boolean isNewCompleteClaim() {
		return isNewCompleteClaim;
	}

	public void setNewCompleteClaim(boolean isNewCompleteClaim) {
		this.isNewCompleteClaim = isNewCompleteClaim;
	}

	public Claim getClaim() {
		return claim;
	}

	public void setClaim(Claim claim) {
		this.claim = claim;
	}
	
	
	

}
