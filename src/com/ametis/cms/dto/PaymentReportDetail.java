package com.ametis.cms.dto;

import java.util.Collection;
import java.util.Vector;

import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimItem;

public class PaymentReportDetail {
	
	private Claim claim;
	private Collection<ClaimItem> claimItems = new Vector<ClaimItem>();
	
	
	public PaymentReportDetail(){
		
	}
	
	public Claim getClaim() {
		return claim;
	}
	public void setClaim(Claim claim) {
		this.claim = claim;
	}
	public Collection<ClaimItem> getClaimItems() {
		return claimItems;
	}
	public void setClaimItems(Collection<ClaimItem> claimItems) {
		this.claimItems = claimItems;
	}
	
	
	
	

}
