package com.ametis.cms.datamodel;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ametis.cms.dto.MemberProductDto;

@Entity
@Table(name = "member_product")
public class MemberProduct implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	// Fields

	/**
	 * data for the column :
	 * 
	 * --------- member_product.member_product_id --------- schema = null
	 * tableName = member_product foreignCol = foreignTab = catalog = insurance
	 * remarks = auto_increment defaultValue = null decDigits = 0 radix = 10
	 * nullable = 0 ordinal = 1 size = 11 type = 4 isPrimaryKey = true
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private Integer memberProductId;

	/**
	 * data for the column :
	 * 
	 * --------- member_product.register_date --------- schema = null tableName =
	 * member_product foreignCol = foreignTab = catalog = insurance remarks =
	 * defaultValue = null decDigits = 0 radix = 10 nullable = 1 ordinal = 4
	 * size = 10 type = 91 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private java.sql.Date registerDate;

	/**
	 * data for the column :
	 * 
	 * --------- member_product.expire_date --------- schema = null tableName =
	 * member_product foreignCol = foreignTab = catalog = insurance remarks =
	 * defaultValue = null decDigits = 0 radix = 10 nullable = 1 ordinal = 5
	 * size = 10 type = 91 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private java.sql.Date expireDate;

	/**
	 * data for the column :
	 * 
	 * --------- member_product.renewal_date --------- schema = null tableName =
	 * member_product foreignCol = foreignTab = catalog = insurance remarks =
	 * defaultValue = null decDigits = 0 radix = 10 nullable = 1 ordinal = 6
	 * size = 10 type = 91 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private java.sql.Date renewalDate;

	/**
	 * data for the column :
	 * 
	 * --------- member_product.created_time --------- schema = null tableName =
	 * member_product foreignCol = foreignTab = catalog = insurance remarks =
	 * defaultValue = null decDigits = 0 radix = 10 nullable = 1 ordinal = 8
	 * size = 19 type = 93 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private java.sql.Timestamp createdTime;

	/**
	 * data for the column :
	 * 
	 * --------- member_product.created_by --------- schema = null tableName =
	 * member_product foreignCol = foreignTab = catalog = insurance remarks =
	 * defaultValue = null decDigits = 0 radix = 10 nullable = 1 ordinal = 9
	 * size = 50 type = 12 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private String createdBy;

	/**
	 * data for the column :
	 * 
	 * --------- member_product.deleted_time --------- schema = null tableName =
	 * member_product foreignCol = foreignTab = catalog = insurance remarks =
	 * defaultValue = null decDigits = 0 radix = 10 nullable = 1 ordinal = 10
	 * size = 19 type = 93 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private java.sql.Timestamp deletedTime;

	/**
	 * data for the column :
	 * 
	 * --------- member_product.deleted_by --------- schema = null tableName =
	 * member_product foreignCol = foreignTab = catalog = insurance remarks =
	 * defaultValue = null decDigits = 0 radix = 10 nullable = 1 ordinal = 11
	 * size = 50 type = 12 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private String deletedBy;

	/**
	 * data for the column :
	 * 
	 * --------- member_product.modified_time --------- schema = null tableName =
	 * member_product foreignCol = foreignTab = catalog = insurance remarks =
	 * defaultValue = null decDigits = 0 radix = 10 nullable = 1 ordinal = 12
	 * size = 19 type = 93 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private java.sql.Timestamp modifiedTime;

	/**
	 * data for the column :
	 * 
	 * --------- member_product.modified_by --------- schema = null tableName =
	 * member_product foreignCol = foreignTab = catalog = insurance remarks =
	 * defaultValue = null decDigits = 0 radix = 10 nullable = 1 ordinal = 13
	 * size = 50 type = 12 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private String modifiedBy;

	/**
	 * data for the column :
	 * 
	 * --------- member_product.deleted_status --------- schema = null tableName =
	 * member_product foreignCol = foreignTab = catalog = insurance remarks =
	 * defaultValue = null decDigits = 0 radix = 10 nullable = 1 ordinal = 14
	 * size = 11 type = 4 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private Integer deletedStatus;

	/**
	 * data for the column :
	 * 
	 * --------- member_product.is_product_active --------- schema = null
	 * tableName = member_product foreignCol = foreignTab = catalog = insurance
	 * remarks = defaultValue = null decDigits = 0 radix = 10 nullable = 1
	 * ordinal = 15 size = 2 type = 4 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private Integer isProductActive;

	// foreign affairs

	/**
	 * SubscriptionStatus data for the foreign class :
	 * 
	 * --------- subscription_status.status_id --------- schema = null tableName =
	 * subscription_status foreignCol = member_product_status foreignTab =
	 * member_product catalog = insurance remarks = auto_increment defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 0 ordinal = 1 size = 11 type = 4
	 * isPrimaryKey = true
	 * 
	 * =========================================
	 * 
	 * 
	 * 
	 */
	private SubscriptionStatus memberProductStatus;
	/**
	 * Member data for the foreign class :
	 * 
	 * --------- member.member_id --------- schema = null tableName = member
	 * foreignCol = member_id foreignTab = member_product catalog = insurance
	 * remarks = auto_increment defaultValue = null decDigits = 0 radix = 10
	 * nullable = 0 ordinal = 1 size = 11 type = 4 isPrimaryKey = true
	 * 
	 * =========================================
	 * 
	 * 
	 * 
	 */
	private Member memberId;
	/**
	 * Product data for the foreign class :
	 * 
	 * --------- product.product_id --------- schema = null tableName = product
	 * foreignCol = product_id foreignTab = member_product catalog = insurance
	 * remarks = auto_increment defaultValue = null decDigits = 0 radix = 10
	 * nullable = 0 ordinal = 1 size = 11 type = 4 isPrimaryKey = true
	 * 
	 * =========================================
	 * 
	 * 
	 * 
	 */
	private Product productId;

	private Double actualBenefitLimit;
	
	private Double benefitUsage; // total penggunaan per waktu polis
	
	private Date lastClaimDate;
	private String lastClaimNumber;
	private Double annualBenefit;
	private Double benefitUsagePercentage;
	private Double benefitExcessed;
	private Double benefitExGratia;
	private Double annualPremium;
	private Boolean isEDCEnabled;
	private Integer limitBenefitPublishedOnEdc;
	private Integer excessPaymentType;
	private Double productClassRate;
	private Currency productCurrencyId;
	private MemberProduct familyProductId;
	private MemberProduct secondaryCoverageId;
	private Integer isProrateLimit;
	private Integer disabilityLength;
	private String currentCardNumber;
	private String currentMemberNumber;
	private String currentPolicyNumber;
	private Policy currentPolicyId;
	private Date gracePeriodeDate;
	

	// -- foreign affairs end

	// -- exported key

	// -- exported key end

	// Fields End

	// PK GETTER SETTER
	@Id
	@Column(name = "member_product_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public java.lang.Integer getMemberProductId() {
		return memberProductId;
	}

	public void setMemberProductId(java.lang.Integer value) {
		memberProductId = value;
	}

	// PK GETTER SETTER END

	@Column(name = "register_date")
	public java.sql.Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(java.sql.Date value) {
		registerDate = value;
	}

	@Column(name = "expire_date")
	public java.sql.Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(java.sql.Date value) {
		expireDate = value;
	}

	@Column(name = "renewal_date")
	public java.sql.Date getRenewalDate() {
		return renewalDate;
	}

	public void setRenewalDate(java.sql.Date value) {
		renewalDate = value;
	}

	@Column(name = "created_time")
	public java.sql.Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(java.sql.Timestamp value) {
		createdTime = value;
	}

	@Column(name = "created_by")
	public java.lang.String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(java.lang.String value) {
		createdBy = value;
	}

	@Column(name = "deleted_time")
	public java.sql.Timestamp getDeletedTime() {
		return deletedTime;
	}

	public void setDeletedTime(java.sql.Timestamp value) {
		deletedTime = value;
	}

	@Column(name = "deleted_by")
	public java.lang.String getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(java.lang.String value) {
		deletedBy = value;
	}

	@Column(name = "modified_time")
	public java.sql.Timestamp getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(java.sql.Timestamp value) {
		modifiedTime = value;
	}

	@Column(name = "modified_by")
	public java.lang.String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(java.lang.String value) {
		modifiedBy = value;
	}

	@Column(name = "deleted_status")
	public java.lang.Integer getDeletedStatus() {
		return deletedStatus;
	}

	public void setDeletedStatus(java.lang.Integer value) {
		deletedStatus = value;
	}

	@Column(name = "is_product_active")
	public java.lang.Integer getIsProductActive() {
		return isProductActive;
	}

	public void setIsProductActive(java.lang.Integer value) {
		isProductActive = value;
	}

	// foreign affairs

	/** SubscriptionStatus */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "member_product_status")
	public SubscriptionStatus getMemberProductStatus() {
		return this.memberProductStatus;
	}

	/** SubscriptionStatus */
	public void setMemberProductStatus(SubscriptionStatus obj) {
		this.memberProductStatus = obj;
	}

	/** Member */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "member_id")
	public Member getMemberId() {
		return this.memberId;
	}

	/** Member */
	public void setMemberId(Member obj) {
		this.memberId = obj;
	}

	/** Product */
	@ManyToOne
	@JoinColumn(name = "product_id")
	public Product getProductId() {
		return this.productId;
	}

	/** Product */
	public void setProductId(Product obj) {
		this.productId = obj;
	}

	@Column(name = "actual_benefit_limit")
	public Double getActualBenefitLimit() {
		return actualBenefitLimit;
	}

	public void setActualBenefitLimit(Double actualBenefitLimit) {
		this.actualBenefitLimit = actualBenefitLimit;
	}

	@Column(name = "benefit_usage")
	public Double getBenefitUsage() {
		return benefitUsage;
	}

	public void setBenefitUsage(Double benefitUsage) {
		this.benefitUsage = benefitUsage;
	}

	@Column(name = "last_member_claim")
	public Date getLastClaimDate() {
		return lastClaimDate;
	}

	public void setLastClaimDate(Date lastClaimDate) {
		this.lastClaimDate = lastClaimDate;
	}

	@Column(name = "last_claim_number")
	public String getLastClaimNumber() {
		return lastClaimNumber;
	}

	public void setLastClaimNumber(String lastClaimNumber) {
		this.lastClaimNumber = lastClaimNumber;
	}

	@Column(name = "annual_benefit")
	public Double getAnnualBenefit() {
		return annualBenefit;
	}

	public void setAnnualBenefit(Double annualBenefit) {
		this.annualBenefit = annualBenefit;
	}

	@Column(name = "benefit_usage_percentage")
	public Double getBenefitUsagePercentage() {
		return benefitUsagePercentage;
	}

	public void setBenefitUsagePercentage(Double benefitUsagePercentage) {
		this.benefitUsagePercentage = benefitUsagePercentage;
	}

	@Column(name = "excessed_value")
	public Double getBenefitExcessed() {
		return benefitExcessed;
	}

	public void setBenefitExcessed(Double benefitExcessed) {
		this.benefitExcessed = benefitExcessed;
	}

	@Column(name = "ex_gratia_value")
	public Double getBenefitExGratia() {
		return benefitExGratia;
	}

	public void setBenefitExGratia(Double benefitExGratia) {
		this.benefitExGratia = benefitExGratia;
	}

	@Column(name = "annual_premium")
	public Double getAnnualPremium() {
		return annualPremium;
	}

	public void setAnnualPremium(Double annualPremium) {
		this.annualPremium = annualPremium;
	}

	@Column(name="is_edc_enabled")
	public Boolean isEDCEnabled() {
		return isEDCEnabled;
	}

	public void setEDCEnabled(Boolean isEDCEnabled) {
		this.isEDCEnabled = isEDCEnabled;
	}

	@Column(name="limit_published_on_edc")
	public Integer getLimitBenefitPublishedOnEdc() {
		return limitBenefitPublishedOnEdc;
	}

	public void setLimitBenefitPublishedOnEdc(Integer limitBenefitPublishedOnEdc) {
		this.limitBenefitPublishedOnEdc = limitBenefitPublishedOnEdc;
	}

	@Column(name="excess_payment_type")
	public Integer getExcessPaymentType() {
		return excessPaymentType;
	}

	public void setExcessPaymentType(Integer excessPaymentType) {
		this.excessPaymentType = excessPaymentType;
	}

	@Column(name="product_class_rate")
	public Double getProductClassRate() {
		return productClassRate;
	}

	public void setProductClassRate(Double productClassRate) {
		this.productClassRate = productClassRate;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="product_currency_id")
	public Currency getProductCurrencyId() {
		return productCurrencyId;
	}

	public void setProductCurrencyId(Currency productCurrencyId) {
		this.productCurrencyId = productCurrencyId;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="family_product_id")
	public MemberProduct getFamilyProductId() {
		return familyProductId;
	}

	public void setFamilyProductId(MemberProduct familyProductId) {
		this.familyProductId = familyProductId;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="secondary_coverage_id")
	public MemberProduct getSecondaryCoverageId() {
		return secondaryCoverageId;
	}

	public void setSecondaryCoverageId(MemberProduct secondaryCoverageId) {
		this.secondaryCoverageId = secondaryCoverageId;
	}

	@Column(name="is_prorate_limit")
	public Integer getIsProrateLimit() {
		return isProrateLimit;
	}

	public void setIsProrateLimit(Integer isProrateLimit) {
		this.isProrateLimit = isProrateLimit;
	}

	@Column(name="disability_length")
	public Integer getDisabilityLength() {
		return disabilityLength;
	}

	public void setDisabilityLength(Integer disabilityLength) {
		this.disabilityLength = disabilityLength;
	}

	@Column(name="current_card_number")
	public String getCurrentCardNumber() {
		return currentCardNumber;
	}

	public void setCurrentCardNumber(String currentCardNumber) {
		this.currentCardNumber = currentCardNumber;
	}

	@Column(name="current_member_number")
	public String getCurrentMemberNumber() {
		return currentMemberNumber;
	}

	public void setCurrentMemberNumber(String currentMemberNumber) {
		this.currentMemberNumber = currentMemberNumber;
	}

	@Column(name="current_policy_number")
	public String getCurrentPolicyNumber() {
		return currentPolicyNumber;
	}

	public void setCurrentPolicyNumber(String currentPolicyNumber) {
		this.currentPolicyNumber = currentPolicyNumber;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="current_policy_id")
	public Policy getCurrentPolicyId() {
		return currentPolicyId;
	}

	@Column(name="grace_periode_date")
	public Date getGracePeriodeDate() {
		return gracePeriodeDate;
	}

	public void setGracePeriodeDate(Date gracePeriodeDate) {
		this.gracePeriodeDate = gracePeriodeDate;
	}

	public void setCurrentPolicyId(Policy currentPolicyId) {
		this.currentPolicyId = currentPolicyId;
	}

	//Add by aju on 20150910, for making memberProductDto fufufu
	public MemberProductDto exportDto(){
		
		MemberProductDto memberProduct = new MemberProductDto();
		
		memberProduct.setMemberProductId(memberProductId);
		
		memberProduct.setMemberId(memberId.getMemberId());
		memberProduct.setMemberName(memberId.getFullName());
		memberProduct.setMemberNumber(memberId.getCustomerNumber());
		
		memberProduct.setProductId(productId.getProductId());
		memberProduct.setProductCategoryName(productId.getProductName());
		memberProduct.setProductCode(productId.getProductCode());
		memberProduct.setProductCategoryCode(productId.getCaseCategory().getCaseCategoryCode());
		//Add by aju on 20150916, some additional info fufufu
		memberProduct.setProductTypeId(productId.getProductType().getProductTypeId());
		memberProduct.setProductTypeName(productId.getProductType().getProductTypeName());
		memberProduct.setMemberProductStatusId(memberProductStatus.getStatusId());
		memberProduct.setMemberProductStatus(memberProductStatus.getStatus());
		memberProduct.setProductCategoryCodeName(productId.getCaseCategory().getCaseCategoryName());
		memberProduct.setProductLimitTypeId(productId.getProductLimitType().getProductLimitTypeId());
		memberProduct.setProductLimitType(productId.getProductLimitType().getProductLimit());
		memberProduct.setCurrencyId(productId.getProductCurrencyId().getCurrencyId());
		memberProduct.setCurrencyName(productId.getProductCurrencyId().getCurrencyName());
		
		memberProduct.setActualBenefit((actualBenefitLimit == null ? 0 : actualBenefitLimit));
		memberProduct.setAnnualBenefit((annualBenefit == null ? 0 : annualBenefit));
		memberProduct.setBenefitUsage((benefitUsage == null ? 0 : benefitUsage));
		memberProduct.setBenefitUsagePercentage((benefitUsagePercentage == null ? 0 : benefitUsagePercentage));
		memberProduct.setExcessedValue((benefitExcessed == null ? 0 : benefitExcessed));
		
		return memberProduct;
	}
	
	

}