package com.ametis.cms.dto;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Vector;

public class ClaimDto implements Serializable{

	private Integer claimId;
	private Integer batchClaimId;
	private Integer caseCategoryId;
	private Date admissionDate;
	private String approvedBy;
	private Timestamp approvedTime;
	private String checkerRemarks;
	private Double claimApprovedValue;
	private Double claimChargeValue;
	private Date claimClosedDate;
	private Date claimDate;
	private Double claimExcessValue;
	private String claimNumber;
	private Double claimPaidValue;
	private Integer claimPaymentStatus;
	private Integer claimProviderStatus;
	private String claimRemarks;
	private Integer claimStatus;
	private Integer claimTypeId;
	private String closeBy;
	private Timestamp closedTime;
	private String createdBy;
	private Timestamp createdTime;
	private String deletedBy;
	private Integer deletedStatus;
	private Timestamp deletedTime;
	private String description;
	private String diagnosis1Code;
	private String diagnosis2Code;
	private String diagnosis3Code;
	
	private String diagnosis1Name;
	private String batchClaimNumber;
	
	private Date dischargeDate;
	private Double exGratiaValue;
	
	private String groupName;
	
	private Integer locationId;
	private String medicalRemarks;
	private Integer memberId;
	
	private String memberName;
	private String memberNumber;
	private Date birthday;
	private Date effectiveDate;
	private Date expireDate;
	
	private String claimPaymentType;
	private String claimServiceType;
	
	private String modifiedBy;
	private Timestamp modifiedTime;
	private String otherNumber;
	private String paidBy;
	private Timestamp paidTime;
	private Date paymentConfirmDate;
	private Date paymentGeneratedDate;
	private Integer paymentId;
	private String paymentNumber;
	private String paymentRemarks;
	private String plan;
	private String productClass;
	private String productId;
	private String providerArea;
	private Integer providerId;
	private String providerName;
	private String relationship;
	private Double remainingMemberLimit;
	private String sex;
	private Integer totalItem;
	private String verifiedBy;
	private Timestamp verifiedTime;
	
	private String bank;
	private String bankAccount;
	private String payeeName;
	private String status;
	
	private String invoiceNumber;
	
	
	private Vector<ClaimItemDto> claimItemList = new Vector<ClaimItemDto>();
	
	public Integer getClaimId() {
		return claimId;
	}
	public void setClaimId(Integer claimId) {
		this.claimId = claimId;
	}
	public Integer getBatchClaimId() {
		return batchClaimId;
	}
	public void setBatchClaimId(Integer batchClaimId) {
		this.batchClaimId = batchClaimId;
	}
	public Integer getCaseCategoryId() {
		return caseCategoryId;
	}
	public void setCaseCategoryId(Integer caseCategoryId) {
		this.caseCategoryId = caseCategoryId;
	}
	public Date getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	public Timestamp getApprovedTime() {
		return approvedTime;
	}
	public void setApprovedTime(Timestamp approvedTime) {
		this.approvedTime = approvedTime;
	}
	public String getCheckerRemarks() {
		return checkerRemarks;
	}
	public void setCheckerRemarks(String checkerRemarks) {
		this.checkerRemarks = checkerRemarks;
	}
	public Double getClaimApprovedValue() {
		return claimApprovedValue;
	}
	public void setClaimApprovedValue(Double claimApprovedValue) {
		this.claimApprovedValue = claimApprovedValue;
	}
	public Double getClaimChargeValue() {
		return claimChargeValue;
	}
	public void setClaimChargeValue(Double claimChargeValue) {
		this.claimChargeValue = claimChargeValue;
	}
	public Date getClaimClosedDate() {
		return claimClosedDate;
	}
	public void setClaimClosedDate(Date claimClosedDate) {
		this.claimClosedDate = claimClosedDate;
	}
	public Date getClaimDate() {
		return claimDate;
	}
	public void setClaimDate(Date claimDate) {
		this.claimDate = claimDate;
	}
	public Double getClaimExcessValue() {
		return claimExcessValue;
	}
	public void setClaimExcessValue(Double claimExcessValue) {
		this.claimExcessValue = claimExcessValue;
	}
	public String getClaimNumber() {
		return claimNumber;
	}
	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}
	public Double getClaimPaidValue() {
		return claimPaidValue;
	}
	public void setClaimPaidValue(Double claimPaidValue) {
		this.claimPaidValue = claimPaidValue;
	}
	public Integer getClaimPaymentStatus() {
		return claimPaymentStatus;
	}
	public void setClaimPaymentStatus(Integer claimPaymentStatus) {
		this.claimPaymentStatus = claimPaymentStatus;
	}
	public Integer getClaimProviderStatus() {
		return claimProviderStatus;
	}
	public void setClaimProviderStatus(Integer claimProviderStatus) {
		this.claimProviderStatus = claimProviderStatus;
	}
	public String getClaimRemarks() {
		return claimRemarks;
	}
	public void setClaimRemarks(String claimRemarks) {
		this.claimRemarks = claimRemarks;
	}
	public Integer getClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(Integer claimStatus) {
		this.claimStatus = claimStatus;
	}
	public Integer getClaimTypeId() {
		return claimTypeId;
	}
	public void setClaimTypeId(Integer claimTypeId) {
		this.claimTypeId = claimTypeId;
	}
	public String getCloseBy() {
		return closeBy;
	}
	public void setCloseBy(String closeBy) {
		this.closeBy = closeBy;
	}
	public Timestamp getClosedTime() {
		return closedTime;
	}
	public void setClosedTime(Timestamp closedTime) {
		this.closedTime = closedTime;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Timestamp getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}
	public String getDeletedBy() {
		return deletedBy;
	}
	public void setDeletedBy(String deletedBy) {
		this.deletedBy = deletedBy;
	}
	public Integer getDeletedStatus() {
		return deletedStatus;
	}
	public void setDeletedStatus(Integer deletedStatus) {
		this.deletedStatus = deletedStatus;
	}
	public Timestamp getDeletedTime() {
		return deletedTime;
	}
	public void setDeletedTime(Timestamp deletedTime) {
		this.deletedTime = deletedTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDiagnosis1Code() {
		return diagnosis1Code;
	}
	public void setDiagnosis1Code(String diagnosis1Code) {
		this.diagnosis1Code = diagnosis1Code;
	}
	public String getDiagnosis2Code() {
		return diagnosis2Code;
	}
	public void setDiagnosis2Code(String diagnosis2Code) {
		this.diagnosis2Code = diagnosis2Code;
	}
	public String getDiagnosis3Code() {
		return diagnosis3Code;
	}
	public void setDiagnosis3Code(String diagnosis3Code) {
		this.diagnosis3Code = diagnosis3Code;
	}
	public Date getDischargeDate() {
		return dischargeDate;
	}
	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}
	public Double getExGratiaValue() {
		return exGratiaValue;
	}
	public void setExGratiaValue(Double exGratiaValue) {
		this.exGratiaValue = exGratiaValue;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Integer getLocationId() {
		return locationId;
	}
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
	public String getMedicalRemarks() {
		return medicalRemarks;
	}
	public void setMedicalRemarks(String medicalRemarks) {
		this.medicalRemarks = medicalRemarks;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Timestamp getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getOtherNumber() {
		return otherNumber;
	}
	public void setOtherNumber(String otherNumber) {
		this.otherNumber = otherNumber;
	}
	public String getPaidBy() {
		return paidBy;
	}
	public void setPaidBy(String paidBy) {
		this.paidBy = paidBy;
	}
	public Timestamp getPaidTime() {
		return paidTime;
	}
	public void setPaidTime(Timestamp paidTime) {
		this.paidTime = paidTime;
	}
	public Date getPaymentConfirmDate() {
		return paymentConfirmDate;
	}
	public void setPaymentConfirmDate(Date paymentConfirmDate) {
		this.paymentConfirmDate = paymentConfirmDate;
	}
	public Date getPaymentGeneratedDate() {
		return paymentGeneratedDate;
	}
	public void setPaymentGeneratedDate(Date paymentGeneratedDate) {
		this.paymentGeneratedDate = paymentGeneratedDate;
	}
	public Integer getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}
	public String getPaymentNumber() {
		return paymentNumber;
	}
	public void setPaymentNumber(String paymentNumber) {
		this.paymentNumber = paymentNumber;
	}
	public String getPaymentRemarks() {
		return paymentRemarks;
	}
	public void setPaymentRemarks(String paymentRemarks) {
		this.paymentRemarks = paymentRemarks;
	}
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	public String getProductClass() {
		return productClass;
	}
	public void setProductClass(String productClass) {
		this.productClass = productClass;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProviderArea() {
		return providerArea;
	}
	public void setProviderArea(String providerArea) {
		this.providerArea = providerArea;
	}
	public Integer getProviderId() {
		return providerId;
	}
	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public Double getRemainingMemberLimit() {
		return remainingMemberLimit;
	}
	public void setRemainingMemberLimit(Double remainingMemberLimit) {
		this.remainingMemberLimit = remainingMemberLimit;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getTotalItem() {
		return totalItem;
	}
	public void setTotalItem(Integer totalItem) {
		this.totalItem = totalItem;
	}
	public String getVerifiedBy() {
		return verifiedBy;
	}
	public void setVerifiedBy(String verifiedBy) {
		this.verifiedBy = verifiedBy;
	}
	public Timestamp getVerifiedTime() {
		return verifiedTime;
	}
	public void setVerifiedTime(Timestamp verifiedTime) {
		this.verifiedTime = verifiedTime;
	}
	public Vector<ClaimItemDto> getClaimItemList() {
		return claimItemList;
	}
	public void setClaimItemList(Vector<ClaimItemDto> claimItemList) {
		this.claimItemList = claimItemList;
	}
	public String getDiagnosis1Name() {
		return diagnosis1Name;
	}
	public void setDiagnosis1Name(String diagnosis1Name) {
		this.diagnosis1Name = diagnosis1Name;
	}
	public String getBatchClaimNumber() {
		return batchClaimNumber;
	}
	public void setBatchClaimNumber(String batchClaimNumber) {
		this.batchClaimNumber = batchClaimNumber;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public String getClaimPaymentType() {
		return claimPaymentType;
	}
	public void setClaimPaymentType(String claimPaymentType) {
		this.claimPaymentType = claimPaymentType;
	}
	public String getClaimServiceType() {
		return claimServiceType;
	}
	public void setClaimServiceType(String claimServiceType) {
		this.claimServiceType = claimServiceType;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getPayeeName() {
		return payeeName;
	}
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	
	
	
}
