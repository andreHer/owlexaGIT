package com.ametis.cms.dto;

import java.io.Serializable;

public class MemberBenefitDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String memberName ;
	private String benefitItemName;
	private Double benefitLimit;
	private Double maxPercaseBenefitLimit;
	private Integer memberBenefitId;
	private Integer itemCategoryId;
	
	//Add by aju on 20150916, some additional info fufufu
	private Integer benefitCaseCategoryId;
	private String benefitCaseCategoryName;
	private String benefitCaseCategoryCode;
	
	//Add by aju on 20150917, another additional info fufufu
	private String benefitItemCode;
	private Integer benefitCalculationMethodId;
	private String benefitCalculationMethodName;
	private Double reimbursementBenefitLimit;
	private Double cashlessPercentage;
	private Double reimbursementPercentage;
	
	private Double currentBenefit;
	
	
	public MemberBenefitDto(){}
	
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getBenefitItemName() {
		return benefitItemName;
	}
	public void setBenefitItemName(String benefitItemName) {
		this.benefitItemName = benefitItemName;
	}
	public Double getBenefitLimit() {
		return benefitLimit;
	}
	public void setBenefitLimit(Double benefitLimit) {
		this.benefitLimit = benefitLimit;
	}
	public Double getMaxPercaseBenefitLimit() {
		return maxPercaseBenefitLimit;
	}
	public void setMaxPercaseBenefitLimit(Double maxPercaseBenefitLimit) {
		this.maxPercaseBenefitLimit = maxPercaseBenefitLimit;
	}

	public Integer getMemberBenefitId() {
		return memberBenefitId;
	}

	public void setMemberBenefitId(Integer memberBenefitId) {
		this.memberBenefitId = memberBenefitId;
	}

	public Integer getItemCategoryId() {
		return itemCategoryId;
	}

	public void setItemCategoryId(Integer itemCategoryId) {
		this.itemCategoryId = itemCategoryId;
	}

	public Integer getBenefitCaseCategoryId() {
		return benefitCaseCategoryId;
	}

	public void setBenefitCaseCategoryId(Integer benefitCaseCategoryId) {
		this.benefitCaseCategoryId = benefitCaseCategoryId;
	}

	public String getBenefitCaseCategoryName() {
		return benefitCaseCategoryName;
	}

	public void setBenefitCaseCategoryName(String benefitCaseCategoryName) {
		this.benefitCaseCategoryName = benefitCaseCategoryName;
	}

	public String getBenefitCaseCategoryCode() {
		return benefitCaseCategoryCode;
	}

	public void setBenefitCaseCategoryCode(String benefitCaseCategoryCode) {
		this.benefitCaseCategoryCode = benefitCaseCategoryCode;
	}

	public String getBenefitItemCode() {
		return benefitItemCode;
	}

	public void setBenefitItemCode(String benefitItemCode) {
		this.benefitItemCode = benefitItemCode;
	}

	public Integer getBenefitCalculationMethodId() {
		return benefitCalculationMethodId;
	}

	public void setBenefitCalculationMethodId(Integer benefitCalculationMethodId) {
		this.benefitCalculationMethodId = benefitCalculationMethodId;
	}

	public String getBenefitCalculationMethodName() {
		return benefitCalculationMethodName;
	}

	public void setBenefitCalculationMethodName(
			String benefitCalculationMethodName) {
		this.benefitCalculationMethodName = benefitCalculationMethodName;
	}

	public Double getReimbursementBenefitLimit() {
		return reimbursementBenefitLimit;
	}

	public void setReimbursementBenefitLimit(Double reimbursementBenefitLimit) {
		this.reimbursementBenefitLimit = reimbursementBenefitLimit;
	}

	public Double getCashlessPercentage() {
		return cashlessPercentage;
	}

	public void setCashlessPercentage(Double cashlessPercentage) {
		this.cashlessPercentage = cashlessPercentage;
	}

	public Double getReimbursementPercentage() {
		return reimbursementPercentage;
	}

	public void setReimbursementPercentage(Double reimbursementPercentage) {
		this.reimbursementPercentage = reimbursementPercentage;
	}

	public Double getCurrentBenefit() {
		return currentBenefit;
	}

	public void setCurrentBenefit(Double currentBenefit) {
		this.currentBenefit = currentBenefit;
	}
	
	

}
