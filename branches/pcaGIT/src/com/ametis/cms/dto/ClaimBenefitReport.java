package com.ametis.cms.dto;

import java.io.Serializable;

public class ClaimBenefitReport  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String patientName;
	private String memberNo;
	private String relationship;
	private String employeeNo;
	private Integer totalClaim;
	private Double claimCharge;
	private Double claimPaid;
	private Double percentage;
	
	
	public ClaimBenefitReport(){}
	
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
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
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	
	
}
