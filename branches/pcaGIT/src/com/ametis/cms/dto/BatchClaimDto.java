package com.ametis.cms.dto;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class BatchClaimDto implements Serializable{

	private Integer batchClaimId;
	private String otherNumber;
	private Date batchClaimDate;
	private String batchClaimNumber;
	private Integer batchClaimType;
	private Double batchClaimAmount;
	private Integer totalClaim;
	private String invoiceNumber;
	private Double batchClaimFinalValue;
	private Double batchClaimInitialValue;
	private Double batchClaimPaidValue;
	private Date batchClaimDatePsea;
	private Integer batchClaimStatus;
	private Date batchDueDate;
	private Double batchExcessValue;
	private String batchNumberPsea;
	private Integer clientId;
	private Integer memberId;
	
	private Integer memberGroupId;
	private Timestamp createdTime;
	private String createdBy;
	private String description;
	private Integer providerId;
	private Integer paymentMethod;
	private Integer paymentRecipient;
	private Integer paymentCurrency;
	private Date paymentDate;
	private Date invoiceDate;
	private Date batchClaimCloseDate;
	private Double batchPaidExcessValue;
	private Integer claimCurrency;
	private String modifiedBy;
	private Timestamp modifiedTime;
	
	private Date lastUpdateBatchPsea;
	private Integer priorityId;
	
	
	
	public Integer getBatchClaimId() {
		return batchClaimId;
	}
	public void setBatchClaimId(Integer batchClaimId) {
		this.batchClaimId = batchClaimId;
	}
	public String getOtherNumber() {
		return otherNumber;
	}
	public void setOtherNumber(String otherNumber) {
		this.otherNumber = otherNumber;
	}
	public Date getBatchClaimDate() {
		return batchClaimDate;
	}
	public void setBatchClaimDate(Date batchClaimDate) {
		this.batchClaimDate = batchClaimDate;
	}
	public String getBatchClaimNumber() {
		return batchClaimNumber;
	}
	public void setBatchClaimNumber(String batchClaimNumber) {
		this.batchClaimNumber = batchClaimNumber;
	}
	public Integer getBatchClaimType() {
		return batchClaimType;
	}
	public void setBatchClaimType(Integer batchClaimType) {
		this.batchClaimType = batchClaimType;
	}
	public Double getBatchClaimAmount() {
		return batchClaimAmount;
	}
	public void setBatchClaimAmount(Double batchClaimAmount) {
		this.batchClaimAmount = batchClaimAmount;
	}
	public Integer getTotalClaim() {
		return totalClaim;
	}
	public void setTotalClaim(Integer totalClaim) {
		this.totalClaim = totalClaim;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public Double getBatchClaimFinalValue() {
		return batchClaimFinalValue;
	}
	public void setBatchClaimFinalValue(Double batchClaimFinalValue) {
		this.batchClaimFinalValue = batchClaimFinalValue;
	}
	public Double getBatchClaimInitialValue() {
		return batchClaimInitialValue;
	}
	public void setBatchClaimInitialValue(Double batchClaimInitialValue) {
		this.batchClaimInitialValue = batchClaimInitialValue;
	}
	public Double getBatchClaimPaidValue() {
		return batchClaimPaidValue;
	}
	public void setBatchClaimPaidValue(Double batchClaimPaidValue) {
		this.batchClaimPaidValue = batchClaimPaidValue;
	}
	public Date getBatchClaimDatePsea() {
		return batchClaimDatePsea;
	}
	public void setBatchClaimDatePsea(Date batchClaimDatePsea) {
		this.batchClaimDatePsea = batchClaimDatePsea;
	}
	public Integer getBatchClaimStatus() {
		return batchClaimStatus;
	}
	public void setBatchClaimStatus(Integer batchClaimStatus) {
		this.batchClaimStatus = batchClaimStatus;
	}
	public Date getBatchDueDate() {
		return batchDueDate;
	}
	public void setBatchDueDate(Date batchDueDate) {
		this.batchDueDate = batchDueDate;
	}
	public Double getBatchExcessValue() {
		return batchExcessValue;
	}
	public void setBatchExcessValue(Double batchExcessValue) {
		this.batchExcessValue = batchExcessValue;
	}
	public String getBatchNumberPsea() {
		return batchNumberPsea;
	}
	public void setBatchNumberPsea(String batchNumberPsea) {
		this.batchNumberPsea = batchNumberPsea;
	}
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getMemberGroupId() {
		return memberGroupId;
	}
	public void setMemberGroupId(Integer memberGroupId) {
		this.memberGroupId = memberGroupId;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getProviderId() {
		return providerId;
	}
	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}
	public Integer getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public Integer getPaymentRecipient() {
		return paymentRecipient;
	}
	public void setPaymentRecipient(Integer paymentRecipient) {
		this.paymentRecipient = paymentRecipient;
	}
	public Integer getPaymentCurrency() {
		return paymentCurrency;
	}
	public void setPaymentCurrency(Integer paymentCurrency) {
		this.paymentCurrency = paymentCurrency;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public Date getBatchClaimCloseDate() {
		return batchClaimCloseDate;
	}
	public void setBatchClaimCloseDate(Date batchClaimCloseDate) {
		this.batchClaimCloseDate = batchClaimCloseDate;
	}
	public Double getBatchPaidExcessValue() {
		return batchPaidExcessValue;
	}
	public void setBatchPaidExcessValue(Double batchPaidExcessValue) {
		this.batchPaidExcessValue = batchPaidExcessValue;
	}
	public Integer getClaimCurrency() {
		return claimCurrency;
	}
	public void setClaimCurrency(Integer claimCurrency) {
		this.claimCurrency = claimCurrency;
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
	public Date getLastUpdateBatchPsea() {
		return lastUpdateBatchPsea;
	}
	public void setLastUpdateBatchPsea(Date lastUpdateBatchPsea) {
		this.lastUpdateBatchPsea = lastUpdateBatchPsea;
	}
	public Integer getPriorityId() {
		return priorityId;
	}
	public void setPriorityId(Integer priorityId) {
		this.priorityId = priorityId;
	}
	
	
	
}
