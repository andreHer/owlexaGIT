package com.ametis.cms.dto;

public class ClaimDiagnosisReport {

	private String diagnosis;
	private Integer noParticipant;
	private Integer totalClaim;
	private Double claimCharge;
	private Double benefitPaid;
	private Double percentageTotal;
	private Double averageCharge;
	
	public ClaimDiagnosisReport(){}
	
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public Integer getNoParticipant() {
		return noParticipant;
	}
	public void setNoParticipant(Integer noParticipant) {
		this.noParticipant = noParticipant;
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
	public Double getBenefitPaid() {
		return benefitPaid;
	}
	public void setBenefitPaid(Double benefitPaid) {
		this.benefitPaid = benefitPaid;
	}
	public Double getPercentageTotal() {
		return percentageTotal;
	}
	public void setPercentageTotal(Double percentageTotal) {
		this.percentageTotal = percentageTotal;
	}
	public Double getAverageCharge() {
		return averageCharge;
	}
	public void setAverageCharge(Double averageCharge) {
		this.averageCharge = averageCharge;
	}
	
	
}
