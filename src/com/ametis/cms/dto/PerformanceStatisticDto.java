package com.ametis.cms.dto;

import java.io.Serializable;

public class PerformanceStatisticDto implements Serializable{
	
	private Integer totalUnregisteredClaim;
	private Integer totalPendingClaim;
	private Integer totalSubmittedClaim;
	private Integer totalVerifiedClaim;
	private Integer totalCheckedClaim;
	private Integer totalPendingPaymentClaim;
	private Integer totalPendingExcess;
	private Integer totalDueCashlessClaim;
	private Integer totalDueReimbursementClaim;
	
	
	
	public PerformanceStatisticDto(){
		
		this.totalCheckedClaim = 0;
		this.totalPendingClaim = 0;
		this.totalSubmittedClaim = 0;
		this.totalVerifiedClaim = 0;
		this.totalCheckedClaim = 0;
		this.totalPendingPaymentClaim = 0;
		this.totalUnregisteredClaim = 0;
		this.totalPendingExcess = 0;
		this.totalDueCashlessClaim = 0;
		this.totalDueReimbursementClaim = 0;
		
		
	}


	public Integer getTotalUnregisteredClaim() {
		return totalUnregisteredClaim;
	}


	public void setTotalUnregisteredClaim(Integer totalUnregisteredClaim) {
		this.totalUnregisteredClaim = totalUnregisteredClaim;
	}


	public Integer getTotalPendingClaim() {
		return totalPendingClaim;
	}


	public void setTotalPendingClaim(Integer totalPendingClaim) {
		this.totalPendingClaim = totalPendingClaim;
	}


	public Integer getTotalSubmittedClaim() {
		return totalSubmittedClaim;
	}


	public void setTotalSubmittedClaim(Integer totalSubmittedClaim) {
		this.totalSubmittedClaim = totalSubmittedClaim;
	}


	public Integer getTotalVerifiedClaim() {
		return totalVerifiedClaim;
	}


	public void setTotalVerifiedClaim(Integer totalVerifiedClaim) {
		this.totalVerifiedClaim = totalVerifiedClaim;
	}


	public Integer getTotalCheckedClaim() {
		return totalCheckedClaim;
	}


	public void setTotalCheckedClaim(Integer totalCheckedClaim) {
		this.totalCheckedClaim = totalCheckedClaim;
	}


	public Integer getTotalPendingPaymentClaim() {
		return totalPendingPaymentClaim;
	}


	public void setTotalPendingPaymentClaim(Integer totalPendingPaymentClaim) {
		this.totalPendingPaymentClaim = totalPendingPaymentClaim;
	}


	public Integer getTotalPendingExcess() {
		return totalPendingExcess;
	}


	public void setTotalPendingExcess(Integer totalPendingExcess) {
		this.totalPendingExcess = totalPendingExcess;
	}


	public Integer getTotalDueCashlessClaim() {
		return totalDueCashlessClaim;
	}


	public void setTotalDueCashlessClaim(Integer totalDueCashlessClaim) {
		this.totalDueCashlessClaim = totalDueCashlessClaim;
	}


	public Integer getTotalDueReimbursementClaim() {
		return totalDueReimbursementClaim;
	}


	public void setTotalDueReimbursementClaim(Integer totalDueReimbursementClaim) {
		this.totalDueReimbursementClaim = totalDueReimbursementClaim;
	}
	
}
