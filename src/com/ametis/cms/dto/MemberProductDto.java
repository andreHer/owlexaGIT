package com.ametis.cms.dto;

import java.io.Serializable;

public class MemberProductDto implements Serializable {

	private Integer memberProductId;
	private Integer memberId;
	private Integer productId;
	private String memberName;
	private String memberNumber;
	private String productCode;
	
	//Add by aju on 20150910, add some memberProduct info to show fufufu :D
	private String productCategoryName;
	private String productCategoryCode;
	private double annualBenefit;
	private double actualBenefit;
	private double benefitUsage;
	private double excessedValue;
	private double benefitUsagePercentage;
	//Add by aju on 201509016, some additional info
	private Integer productTypeId;
	private String productTypeName;
	private Integer memberProductStatusId;
	private String memberProductStatus;
	private String productCategoryCodeName;
	private Integer productLimitTypeId;
	private String productLimitType;
	private Integer currencyId;
	private String currencyName;
	
	
	public Integer getMemberProductId() {
		return memberProductId;
	}
	public void setMemberProductId(Integer memberProductId) {
		this.memberProductId = memberProductId;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
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
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductCategoryName() {
		return productCategoryName;
	}
	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}
	public double getAnnualBenefit() {
		return annualBenefit;
	}
	public void setAnnualBenefit(double annualBenefit) {
		this.annualBenefit = annualBenefit;
	}
	public double getActualBenefit() {
		return actualBenefit;
	}
	public void setActualBenefit(double actualBenefit) {
		this.actualBenefit = actualBenefit;
	}
	public double getBenefitUsage() {
		return benefitUsage;
	}
	public void setBenefitUsage(double benefitUsage) {
		this.benefitUsage = benefitUsage;
	}
	public double getExcessedValue() {
		return excessedValue;
	}
	public void setExcessedValue(double excessedValue) {
		this.excessedValue = excessedValue;
	}
	public double getBenefitUsagePercentage() {
		return benefitUsagePercentage;
	}
	public void setBenefitUsagePercentage(double benefitUsagePercentage) {
		this.benefitUsagePercentage = benefitUsagePercentage;
	}
	public String getProductCategoryCode() {
		return productCategoryCode;
	}
	public void setProductCategoryCode(String productCategoryCode) {
		this.productCategoryCode = productCategoryCode;
	}
	public Integer getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	public Integer getMemberProductStatusId() {
		return memberProductStatusId;
	}
	public void setMemberProductStatusId(Integer memberProductStatusId) {
		this.memberProductStatusId = memberProductStatusId;
	}
	public String getMemberProductStatus() {
		return memberProductStatus;
	}
	public void setMemberProductStatus(String memberProductStatus) {
		this.memberProductStatus = memberProductStatus;
	}
	public String getProductCategoryCodeName() {
		return productCategoryCodeName;
	}
	public void setProductCategoryCodeName(String productCategoryCodeName) {
		this.productCategoryCodeName = productCategoryCodeName;
	}
	public Integer getProductLimitTypeId() {
		return productLimitTypeId;
	}
	public void setProductLimitTypeId(Integer productLimitTypeId) {
		this.productLimitTypeId = productLimitTypeId;
	}
	public String getProductLimitType() {
		return productLimitType;
	}
	public void setProductLimitType(String productLimitType) {
		this.productLimitType = productLimitType;
	}
	public Integer getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	
	
}
