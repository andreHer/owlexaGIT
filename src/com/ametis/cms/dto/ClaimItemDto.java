package com.ametis.cms.dto;

import java.io.Serializable;

public class ClaimItemDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String itemCode ;
	private String claimNumber;
	private Double claimItemAmount;
	private Double claimItemValue;
	private Double claimItemApprovedValue;
	private String description;
	private String benefitRemarks;
	private String claimItemName;
	private Double excessValue;
	private Integer claimItemId;
	
	
	public Integer getClaimItemId() {
		return claimItemId;
	}
	public void setClaimItemId(Integer claimItemId) {
		this.claimItemId = claimItemId;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getClaimNumber() {
		return claimNumber;
	}
	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}
	public Double getClaimItemAmount() {
		return claimItemAmount;
	}
	public void setClaimItemAmount(Double claimItemAmount) {
		this.claimItemAmount = claimItemAmount;
	}
	public Double getClaimItemValue() {
		return claimItemValue;
	}
	public void setClaimItemValue(Double claimItemValue) {
		this.claimItemValue = claimItemValue;
	}
	public Double getClaimItemApprovedValue() {
		return claimItemApprovedValue;
	}
	public void setClaimItemApprovedValue(Double claimItemApprovedValue) {
		this.claimItemApprovedValue = claimItemApprovedValue;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBenefitRemarks() {
		return benefitRemarks;
	}
	public void setBenefitRemarks(String benefitRemarks) {
		this.benefitRemarks = benefitRemarks;
	}
	public String getClaimItemName() {
		return claimItemName;
	}
	public void setClaimItemName(String claimItemName) {
		this.claimItemName = claimItemName;
	}
	public Double getExcessValue() {
		return excessValue;
	}
	public void setExcessValue(Double excessValue) {
		this.excessValue = excessValue;
	}
	
	

	
}
