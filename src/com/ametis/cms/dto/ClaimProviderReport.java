package com.ametis.cms.dto;

public class ClaimProviderReport {
	private String providerName;
	private Integer totalClaim;
	private Double claimCharge;
	private Double claimPaid;
	private Double percentageTotal;
	private Double averageClaim;
	
	public ClaimProviderReport(){}
	
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public Integer getTotalClaim() {
		return totalClaim;
	}
	public void setTotalClaim(Integer totalClaim) {
		this.totalClaim = totalClaim;
	}
	public Double getClaimCharge() {
		return claimCharge;
	}
	public void setClaimCharge(Double claimCharge) {
		this.claimCharge = claimCharge;
	}
	public Double getClaimPaid() {
		return claimPaid;
	}
	public void setClaimPaid(Double claimPaid) {
		this.claimPaid = claimPaid;
	}
	public Double getPercentageTotal() {
		return percentageTotal;
	}
	public void setPercentageTotal(Double percentageTotal) {
		this.percentageTotal = percentageTotal;
	}
	public Double getAverageClaim() {
		return averageClaim;
	}
	public void setAverageClaim(Double averageClaim) {
		this.averageClaim = averageClaim;
	}
	
	
}
