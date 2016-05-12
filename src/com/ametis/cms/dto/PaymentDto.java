package com.ametis.cms.dto;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class PaymentDto implements Serializable{

	private String paymentNumberCounter;
	private Integer paymentId;
	private Double paymentValue;
	private String remarks;
	private String bankName;
	private String accountNumber;
	private String giroNumber;
	private String payeeName;
	private Date paymentTime;
	private Integer paymentStatus;
	private Timestamp createdTime;
	private String createdBy;
	private String modifiedBy;
	private String deletedBy;
	private Timestamp modifiedTime;
	private Timestamp deletedTime;
	private Integer deletedStatus;
	private Integer memberId;
	private Integer providerId;
	private Integer memberGroupId;
	private String paymentNumber;
	private String bankBranch;
	private String valueDescription;
	private Integer batchClaimId;
	private Date paymentConfirmDate;
	private Double paymentConfirmedValue;
	private Date dispositionDate;
	private String documentBin;
	public String getPaymentNumberCounter() {
		return paymentNumberCounter;
	}
	public void setPaymentNumberCounter(String paymentNumberCounter) {
		this.paymentNumberCounter = paymentNumberCounter;
	}
	public Integer getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}
	public Double getPaymentValue() {
		return paymentValue;
	}
	public void setPaymentValue(Double paymentValue) {
		this.paymentValue = paymentValue;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getGiroNumber() {
		return giroNumber;
	}
	public void setGiroNumber(String giroNumber) {
		this.giroNumber = giroNumber;
	}
	public String getPayeeName() {
		return payeeName;
	}
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	public Date getPaymentTime() {
		return paymentTime;
	}
	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}
	public Integer getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(Integer paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public Timestamp getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getDeletedBy() {
		return deletedBy;
	}
	public void setDeletedBy(String deletedBy) {
		this.deletedBy = deletedBy;
	}
	public Timestamp getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public Timestamp getDeletedTime() {
		return deletedTime;
	}
	public void setDeletedTime(Timestamp deletedTime) {
		this.deletedTime = deletedTime;
	}
	public Integer getDeletedStatus() {
		return deletedStatus;
	}
	public void setDeletedStatus(Integer deletedStatus) {
		this.deletedStatus = deletedStatus;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getProviderId() {
		return providerId;
	}
	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}
	public Integer getMemberGroupId() {
		return memberGroupId;
	}
	public void setMemberGroupId(Integer memberGroupId) {
		this.memberGroupId = memberGroupId;
	}
	public String getPaymentNumber() {
		return paymentNumber;
	}
	public void setPaymentNumber(String paymentNumber) {
		this.paymentNumber = paymentNumber;
	}
	public String getBankBranch() {
		return bankBranch;
	}
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	public String getValueDescription() {
		return valueDescription;
	}
	public void setValueDescription(String valueDescription) {
		this.valueDescription = valueDescription;
	}
	public Integer getBatchClaimId() {
		return batchClaimId;
	}
	public void setBatchClaimId(Integer batchClaimId) {
		this.batchClaimId = batchClaimId;
	}
	public Date getPaymentConfirmDate() {
		return paymentConfirmDate;
	}
	public void setPaymentConfirmDate(Date paymentConfirmDate) {
		this.paymentConfirmDate = paymentConfirmDate;
	}
	public Double getPaymentConfirmedValue() {
		return paymentConfirmedValue;
	}
	public void setPaymentConfirmedValue(Double paymentConfirmedValue) {
		this.paymentConfirmedValue = paymentConfirmedValue;
	}
	public Date getDispositionDate() {
		return dispositionDate;
	}
	public void setDispositionDate(Date dispositionDate) {
		this.dispositionDate = dispositionDate;
	}
	public String getDocumentBin() {
		return documentBin;
	}
	public void setDocumentBin(String documentBin) {
		this.documentBin = documentBin;
	}
	
	
}
