package com.ametis.cms.web.form;

import java.util.Date;

import com.ametis.cms.datamodel.Claim;

public class PendingCompleteForm {
	private Claim claimBean;
	private Date completeDate;
	
	
	public Claim getClaimBean() {
		return claimBean;
	}
	public void setClaimBean(Claim claimBean) {
		this.claimBean = claimBean;
	}
	public Date getCompleteDate() {
		return completeDate;
	}
	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}
	
	
}
