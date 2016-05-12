package com.ametis.cms.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

public class ClaimSCDto implements Serializable{

	private String serviceCategory;
	private String serviceCategoryName;
	private Date claimDate;
	private Double claimApprovedValue;
	private Collection<ClaimItemDto> claimItems;
	private Date admissionDate;
	private Date dischargedDate;
	private Double claimChargeValue;
	private String diagnosis1Code;
	private String diagnosis2Code;
	private String diagnosis3Code;
	private String insuranceCode;
	private String claimNumber;
	private String referenceNumber;

	private Double totalDays;
	
	private String memberNumber;
	private String providerCode;
	private String memberName;
	private Double claimExcessValue;
	
	
	public ClaimSCDto (){}


	public String getClaimNumber() {
		return claimNumber;
	}


	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}


	public String getServiceCategory() {
		return serviceCategory;
	}


	public void setServiceCategory(String serviceCategory) {
		this.serviceCategory = serviceCategory;
	}


	public Date getClaimDate() {
		return claimDate;
	}


	public void setClaimDate(Date claimDate) {
		this.claimDate = claimDate;
	}


	public Double getClaimApprovedValue() {
		return claimApprovedValue;
	}


	public void setClaimApprovedValue(Double claimApprovedValue) {
		this.claimApprovedValue = claimApprovedValue;
	}


	public Collection<ClaimItemDto> getClaimItems() {
		return claimItems;
	}


	public void setClaimItems(Collection<ClaimItemDto> claimItems) {
		this.claimItems = claimItems;
	}


	public Date getAdmissionDate() {
		return admissionDate;
	}


	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}


	public Date getDischargedDate() {
		return dischargedDate;
	}


	public void setDischargedDate(Date dischargedDate) {
		this.dischargedDate = dischargedDate;
	}


	public Double getClaimChargeValue() {
		return claimChargeValue;
	}


	public void setClaimChargeValue(Double claimChargeValue) {
		this.claimChargeValue = claimChargeValue;
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


	public String getMemberNumber() {
		return memberNumber;
	}


	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}


	public String getProviderCode() {
		return providerCode;
	}


	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}


	public String getMemberName() {
		return memberName;
	}


	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}


	public Double getClaimExcessValue() {
		return claimExcessValue;
	}


	public void setClaimExcessValue(Double claimExcessValue) {
		this.claimExcessValue = claimExcessValue;
	}


	public String getInsuranceCode() {
		return insuranceCode;
	}


	public void setInsuranceCode(String insuranceCode) {
		this.insuranceCode = insuranceCode;
	}


	public String getServiceCategoryName() {
		return serviceCategoryName;
	}


	public void setServiceCategoryName(String serviceCategoryName) {
		this.serviceCategoryName = serviceCategoryName;
	}


	public Double getTotalDays() {
		return totalDays;
	}


	public void setTotalDays(Double totalDays) {
		this.totalDays = totalDays;
	}


	public String getReferenceNumber() {
		return referenceNumber;
	}


	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	
	
}
