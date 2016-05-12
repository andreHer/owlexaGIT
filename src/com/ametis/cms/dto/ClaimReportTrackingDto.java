package com.ametis.cms.dto;

import java.io.Serializable;
import java.sql.Date;

public class ClaimReportTrackingDto implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long claimId;
	private String claimNumber;
	private String caseCategory;
	private String claimType;
	private String memberName;
	private String dateOfBirth;
	private String memberNumber;
	
	private Date admissionDate;
	private Date dischargeDate;
	private Date receivedDate;
	private Date cdvDate;
	
	
	private String paymentNumber;
	private Date paymentConfirmDate;
	private String claimCharge;
	private String claimApprovedValue;
	private String excessValue;
	private String exGratiaValue;
	public Long getClaimId() {
		return claimId;
	}
	public void setClaimId(Long claimId) {
		this.claimId = claimId;
	}
	public String getClaimNumber() {
		return claimNumber;
	}
	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}
	public String getCaseCategory() {
		return caseCategory;
	}
	public void setCaseCategory(String caseCategory) {
		this.caseCategory = caseCategory;
	}
	public String getClaimType() {
		return claimType;
	}
	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}
	public Date getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}
	public Date getDischargeDate() {
		return dischargeDate;
	}
	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}
	public Date getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	public Date getCdvDate() {
		return cdvDate;
	}
	public void setCdvDate(Date cdvDate) {
		this.cdvDate = cdvDate;
	}
	public String getPaymentNumber() {
		return paymentNumber;
	}
	public void setPaymentNumber(String paymentNumber) {
		this.paymentNumber = paymentNumber;
	}
	public Date getPaymentConfirmDate() {
		return paymentConfirmDate;
	}
	public void setPaymentConfirmDate(Date paymentConfirmDate) {
		this.paymentConfirmDate = paymentConfirmDate;
	}
	public String getClaimCharge() {
		return claimCharge;
	}
	public void setClaimCharge(String claimCharge) {
		this.claimCharge = claimCharge;
	}
	public String getClaimApprovedValue() {
		return claimApprovedValue;
	}
	public void setClaimApprovedValue(String claimApprovedValue) {
		this.claimApprovedValue = claimApprovedValue;
	}
	public String getExcessValue() {
		return excessValue;
	}
	public void setExcessValue(String excessValue) {
		this.excessValue = excessValue;
	}
	public String getExGratiaValue() {
		return exGratiaValue;
	}
	public void setExGratiaValue(String exGratiaValue) {
		this.exGratiaValue = exGratiaValue;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	
	
	
	
}
