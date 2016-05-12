package com.ametis.cms.dto;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Vector;
import java.util.Collection;

public class ClaimExchangeDto implements Serializable{
	
	/* CLAIM EXCHANGE HEADER */
	
	private String clientCode;
	private String groupCode;
	private String policyNo;
	private String claimsId;
	private String claimsStatus;
	private String claimsProcessStatus;
	private Integer memberId;
	private String cardNumber;
	private String clientMemberId;
	private String memberName;
	private String prodType;
	private String claimsType;
	private String productId;
	private Date admissionDate;
	private Date dischargeDate;
	private String icdCode;
	private String diagnosisDesc;
	private String icdCode2;
	private String diagnosisDesc2;
	private String icdCode3;
	private String diagnosisDesc3;
	private String providerCode;
	private String providerName;
	private String providerCodeReimburse;
    private String providerNameReimburse;
    private String currency;
    private Double amountIncurred;
    private Double amountApprove;
    private Double amountExcess;
    private Double amountExcessPaid;
    private Double amountPaidToProvider;
    private Double amountExcessByProvider;
    private Double amountRefund;
    private String doctorName;
    private String batchNumber;
    private String providerInvoiceNo;
    private Date providerInvoiceDate;
    private Date approveDate;
    private Date receivedDate;
    private Date registerDate;
    private Date submissionDate;
    private Date dueDate;
    private String los;
    private String roomUsage;
    private String remarks;
	
	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getClaimsId() {
		return claimsId;
	}

	public void setClaimsId(String claimsId) {
		this.claimsId = claimsId;
	}

	public String getClaimsStatus() {
		return claimsStatus;
	}

	public void setClaimsStatus(String claimsStatus) {
		this.claimsStatus = claimsStatus;
	}

	public String getClaimsProcessStatus() {
		return claimsProcessStatus;
	}

	public void setClaimsProcessStatus(String claimsProcessStatus) {
		this.claimsProcessStatus = claimsProcessStatus;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getClientMemberId() {
		return clientMemberId;
	}

	public void setClientMemberId(String clientMemberId) {
		this.clientMemberId = clientMemberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getProdType() {
		return prodType;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
	}

	public String getClaimsType() {
		return claimsType;
	}

	public void setClaimsType(String claimsType) {
		this.claimsType = claimsType;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
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

	public String getIcdCode() {
		return icdCode;
	}

	public void setIcdCode(String icdCode) {
		this.icdCode = icdCode;
	}

	public String getDiagnosisDesc() {
		return diagnosisDesc;
	}

	public void setDiagnosisDesc(String diagnosisDesc) {
		this.diagnosisDesc = diagnosisDesc;
	}

	public String getIcdCode2() {
		return icdCode2;
	}

	public void setIcdCode2(String icdCode2) {
		this.icdCode2 = icdCode2;
	}

	public String getDiagnosisDesc2() {
		return diagnosisDesc2;
	}

	public void setDiagnosisDesc2(String diagnosisDesc2) {
		this.diagnosisDesc2 = diagnosisDesc2;
	}

	public String getIcdCode3() {
		return icdCode3;
	}

	public void setIcdCode3(String icdCode3) {
		this.icdCode3 = icdCode3;
	}

	public String getDiagnosisDesc3() {
		return diagnosisDesc3;
	}

	public void setDiagnosisDesc3(String diagnosisDesc3) {
		this.diagnosisDesc3 = diagnosisDesc3;
	}

	public String getProviderCode() {
		return providerCode;
	}

	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getProviderCodeReimburse() {
		return providerCodeReimburse;
	}

	public void setProviderCodeReimburse(String providerCodeReimburse) {
		this.providerCodeReimburse = providerCodeReimburse;
	}

	public String getProviderNameReimburse() {
		return providerNameReimburse;
	}

	public void setProviderNameReimburse(String providerNameReimburse) {
		this.providerNameReimburse = providerNameReimburse;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getAmountIncurred() {
		return amountIncurred;
	}

	public void setAmountIncurred(Double amountIncurred) {
		this.amountIncurred = amountIncurred;
	}

	public Double getAmountApprove() {
		return amountApprove;
	}

	public void setAmountApprove(Double amountApprove) {
		this.amountApprove = amountApprove;
	}

	public Double getAmountExcess() {
		return amountExcess;
	}

	public void setAmountExcess(Double amountExcess) {
		this.amountExcess = amountExcess;
	}

	public Double getAmountExcessPaid() {
		return amountExcessPaid;
	}

	public void setAmountExcessPaid(Double amountExcessPaid) {
		this.amountExcessPaid = amountExcessPaid;
	}

	public Double getAmountPaidToProvider() {
		return amountPaidToProvider;
	}

	public void setAmountPaidToProvider(Double amountPaidToProvider) {
		this.amountPaidToProvider = amountPaidToProvider;
	}

	public Double getAmountExcessByProvider() {
		return amountExcessByProvider;
	}

	public void setAmountExcessByProvider(Double amountExcessByProvider) {
		this.amountExcessByProvider = amountExcessByProvider;
	}

	public Double getAmountRefund() {
		return amountRefund;
	}

	public void setAmountRefund(Double amountRefund) {
		this.amountRefund = amountRefund;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getProviderInvoiceNo() {
		return providerInvoiceNo;
	}

	public void setProviderInvoiceNo(String providerInvoiceNo) {
		this.providerInvoiceNo = providerInvoiceNo;
	}

	public Date getProviderInvoiceDate() {
		return providerInvoiceDate;
	}

	public void setProviderInvoiceDate(Date providerInvoiceDate) {
		this.providerInvoiceDate = providerInvoiceDate;
	}

	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getLos() {
		return los;
	}

	public void setLos(String los) {
		this.los = los;
	}

	public String getRoomUsage() {
		return roomUsage;
	}

	public void setRoomUsage(String roomUsage) {
		this.roomUsage = roomUsage;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	

	/* CLAIM EXCHANGE DETAILS */
	private Collection<ClaimExchangeDetailDto> claimExchangeDetailList = new Vector<ClaimExchangeDetailDto>();
	
	public Collection<ClaimExchangeDetailDto> getClaimExchangeDetailList() {
		return claimExchangeDetailList;
	}

	public void setClaimExchangeDetailList(
			Collection<ClaimExchangeDetailDto> claimExchangeDetailList) {
		this.claimExchangeDetailList = claimExchangeDetailList;
	}
	
}
