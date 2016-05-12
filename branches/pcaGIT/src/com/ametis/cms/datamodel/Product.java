
package com.ametis.cms.datamodel;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="product")
public class Product implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	public static final int PRODUCT_PENDING = -1;
	public static final int PRODUCT_ACTIVE = 1;
	public static final int PRODUCT_INACTIVE = 4;
	
	public static final int ANNUAL_LIMIT_DIFFERENT = 1;
	public static final int ANNUAL_LIMIT_NOT_DIFFERENT = 0;
	
	public static final int EXCESS_PAID_UPFRONT = 1;
	public static final int EXCESS_PAID_LATER = 2;
	
	public static final int ML_CLAIM_PER_LAYER = 1;
	public static final int ML_CLAIM_COB = 2;
	public static final int ML_CLAIM_USING_EXCESS_BUFFER = 3;
	//Fields
		
	/**data for the column :
	
 --------- product.product_id --------- 
 schema        = null
 tableName     = product
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 11
 type          = 4 
 isPrimaryKey  = true

=========================================


*/
	private Integer productId;
			
	/**data for the column :
	
 --------- product.product_code --------- 
 schema        = null
 tableName     = product
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String productCode;
			
	/**data for the column :
	
 --------- product.product_name --------- 
 schema        = null
 tableName     = product
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String productName;
			
	/**data for the column :
	
 --------- product.product_description --------- 
 schema        = null
 tableName     = product
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String productDescription;
			
	/**data for the column :
	
 --------- product.product_status --------- 
 schema        = null
 tableName     = product
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private SubscriptionStatus productStatus;
			
	/**data for the column :
	
 --------- product.max_claim_value --------- 
 schema        = null
 tableName     = product
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 22
 type          = 8 
 isPrimaryKey  = false
	
=========================================


*/
	
	private Double maxClaimValue;
	private String serviceClass;
	/**data for the column :
	
 --------- product.annual_limit_value --------- 
 schema        = null
 tableName     = product
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double annualLimitValue;
												
	/**data for the column :
	
 --------- product.created_time --------- 
 schema        = null
 tableName     = product
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- product.created_by --------- 
 schema        = null
 tableName     = product
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- product.deleted_time --------- 
 schema        = null
 tableName     = product
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- product.deleted_by --------- 
 schema        = null
 tableName     = product
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 14
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- product.modified_time --------- 
 schema        = null
 tableName     = product
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 15
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- product.modified_by --------- 
 schema        = null
 tableName     = product
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 16
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- product.deleted_status --------- 
 schema        = null
 tableName     = product
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 17
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
		
	// foreign affairs
	
			/** Client
	data for the foreign class :
	
 --------- client.client_id --------- 
 schema        = null
 tableName     = client
 foreignCol    = client_id
 foreignTab    = product
 catalog       = insurance
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 11
 type          = 4 
 isPrimaryKey  = true

=========================================



	 */
	private Client clientId;
				/** ProductLimitType
	data for the foreign class :
	
 --------- product_limit_type.product_limit_type_id --------- 
 schema        = null
 tableName     = product_limit_type
 foreignCol    = product_limit_type
 foreignTab    = product
 catalog       = insurance
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 11
 type          = 4 
 isPrimaryKey  = true

=========================================



	 */
	private ProductLimitType productLimitType;
				/** ProductType
	data for the foreign class :
	
 --------- product_type.product_type_id --------- 
 schema        = null
 tableName     = product_type
 foreignCol    = product_type
 foreignTab    = product
 catalog       = insurance
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 11
 type          = 4 
 isPrimaryKey  = true

=========================================



	 */
	private ProductType productType;
	private Integer edcEnabled;
			
	// -- foreign affairs end

	// -- exported key

	
			/** GroupProduct
	data for the exported class :
	
 --------- group_product.product_id --------- 
 schema        = null
 tableName     = group_product
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = product.product_id

=========================================



	 */
	private Set <GroupProduct> groupProducts = new HashSet(0);
				/** MemberProduct
	data for the exported class :
	
 --------- member_product.product_id --------- 
 schema        = null
 tableName     = member_product
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = product.product_id

=========================================



	 */
	private Integer disabilityLength ;
	private Integer waitingPeriode;
	
	/*
	 * premiReturnPercentage berfungsi untuk menandakan apakah 
	 * diakhir periode ini terdapat persentasi premi untuk dikembalikan
	 * apabila jumlah claim tidak mencapai suatu nilai x
	 */
	
	private Double premiReturnPercentage;
	
	/*
	 * premiReturnThreshold berfungsi untuk menandakan batas atas
	 * dari jumlah claim yang mungkin dapat di return preminya.
	 */
	private Double premiReturnThreshold;
	
	private CaseCategory caseCategory;
	private TreatmentLocation treatmentLocation;
	private Product productReference;
	
	private Double cashlessPercentage;
	private Double reimbursementPercentage;
	private Double domesticPercentage;
	private Double overseasPercentage;
	
	private Double reimbursementAnnualLimit;
	
	private Double malePremiumRate;
	private Double femalePremiumRate;
	private Double childrenPremiumRate;
	
	private Integer applyToEmployee;
	private Integer applyToSpouse;
	private Integer applyToChildren;

	private Double surgeryPerDisabilityLimit;
	private TreatmentClass treatmentClassId;
	private Currency productCurrencyId;
	private Integer isUsingSalary;
	private Integer isUsingProrateLimit;
	private Double salaryMultiplier;
	private Policy policyId;
	private Integer allowOverlimit;
	private Integer doSynchronize;
	
	private Integer multilayerMechanism;
	
	private Set <MemberProduct> memberProducts = new HashSet(0);
				/** ProductBenefit
	data for the exported class :
	
 --------- product_benefit.product_id --------- 
 schema        = null
 tableName     = product_benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = product.product_id

=========================================



	 */
	private Set <ProductBenefit> productBenefits = new HashSet(0);
				/** ProductClausul
	data for the exported class :
	
 --------- product_clausul.product_id --------- 
 schema        = null
 tableName     = product_clausul
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = product.product_id

=========================================



	 */
	private Set <ProductClausul> productClausuls = new HashSet(0);
				/** ProductPackage
	data for the exported class :
	
 --------- product_package.product_id --------- 
 schema        = null
 tableName     = product_package
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = product.product_id

=========================================



	 */
	private Integer maxMemberAges;
	private Double costSharingCoverage;
	private String specialTermCondition;
	private Double premiProduct;
	private Integer isTemplate;
	private Integer limitBenefitShowedOnEdc;
	private Integer excessPaymentType;
	private Double productClassRate;
	private Product sharedProductId;
	
	private Set <ProductPackage> productPackages = new HashSet(0);
			
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="product_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getProductId(){
		return productId;
	}
	public void setProductId(java.lang.Integer value){
		productId = value;
	}
			// PK GETTER SETTER END

						@Column(name="product_code")
	public java.lang.String getProductCode(){
		return productCode;
	}
	public void setProductCode(java.lang.String value){
		productCode = value;
	}
				@Column(name="product_name")
	public java.lang.String getProductName(){
		return productName;
	}
	public void setProductName(java.lang.String value){
		productName = value;
	}
				@Column(name="product_description")
	public java.lang.String getProductDescription(){
		return productDescription;
	}
	public void setProductDescription(java.lang.String value){
		productDescription = value;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="product_status")
	public SubscriptionStatus getProductStatus(){
		return productStatus;
	}
	public void setProductStatus(SubscriptionStatus value){
		productStatus = value;
	}
				@Column(name="max_claim_value")
	public java.lang.Double getMaxClaimValue(){
		return maxClaimValue;
		
	}
	public void setMaxClaimValue(java.lang.Double value){
		maxClaimValue = value;
	}
				@Column(name="annual_limit_value")
	public java.lang.Double getAnnualLimitValue(){
		return annualLimitValue;
	}
	public void setAnnualLimitValue(java.lang.Double value){
		annualLimitValue = value;
	}
													@Column(name="created_time")
	public java.sql.Timestamp getCreatedTime(){
		return createdTime;
	}
	public void setCreatedTime(java.sql.Timestamp value){
		createdTime = value;
	}
				@Column(name="created_by")
	public java.lang.String getCreatedBy(){
		return createdBy;
	}
	public void setCreatedBy(java.lang.String value){
		createdBy = value;
	}
				@Column(name="deleted_time")
	public java.sql.Timestamp getDeletedTime(){
		return deletedTime;
	}
	public void setDeletedTime(java.sql.Timestamp value){
		deletedTime = value;
	}
				@Column(name="deleted_by")
	public java.lang.String getDeletedBy(){
		return deletedBy;
	}
	public void setDeletedBy(java.lang.String value){
		deletedBy = value;
	}
				@Column(name="modified_time")
	public java.sql.Timestamp getModifiedTime(){
		return modifiedTime;
	}
	public void setModifiedTime(java.sql.Timestamp value){
		modifiedTime = value;
	}
				@Column(name="modified_by")
	public java.lang.String getModifiedBy(){
		return modifiedBy;
	}
	public void setModifiedBy(java.lang.String value){
		modifiedBy = value;
	}
				@Column(name="deleted_status")
	public java.lang.Integer getDeletedStatus(){
		return deletedStatus;
	}
	public void setDeletedStatus(java.lang.Integer value){
		deletedStatus = value;
	}
		
	// foreign affairs
	
			/** Client */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="client_id")
	public Client getClientId(){
		return this.clientId;
	}

	/** Client */
	public void setClientId(Client obj){
		this.clientId = obj;
	}
				/** ProductLimitType */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="product_limit_type")
	public ProductLimitType getProductLimitType(){
		return this.productLimitType;
	}

	/** ProductLimitType */
	public void setProductLimitType(ProductLimitType obj){
		this.productLimitType = obj;
	}
				/** ProductType */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="product_type")
	public ProductType getProductType(){
		return this.productType;
	}

	/** ProductType */
	public void setProductType(ProductType obj){
		this.productType = obj;
	}
			
	// foreign affairs end

// exported key

	
			/** GroupProduct */
	@OneToMany(mappedBy="productId")
	public Set<GroupProduct> getGroupProducts(){
		return this.groupProducts;
	}

	/** GroupProduct */
	public void setGroupProducts(Set<GroupProduct> obj){
		this.groupProducts = obj;
	}
				/** MemberProduct */
	@OneToMany(mappedBy="productId")
	public Set<MemberProduct> getMemberProducts(){
		return this.memberProducts;
	}

	/** MemberProduct */
	public void setMemberProducts(Set<MemberProduct> obj){
		this.memberProducts = obj;
	}
				/** ProductBenefit */
	@OneToMany(mappedBy="productId")
	public Set<ProductBenefit> getProductBenefits(){
		return this.productBenefits;
	}

	/** ProductBenefit */
	public void setProductBenefits(Set<ProductBenefit> obj){
		this.productBenefits = obj;
	}
				/** ProductClausul */
	@OneToMany(mappedBy="productId")
	public Set<ProductClausul> getProductClausuls(){
		return this.productClausuls;
	}

	/** ProductClausul */
	public void setProductClausuls(Set<ProductClausul> obj){
		this.productClausuls = obj;
	}
				/** ProductPackage */
	@OneToMany(mappedBy="productId")
	public Set<ProductPackage> getProductPackages(){
		return this.productPackages;
	}

	/** ProductPackage */
	public void setProductPackages(Set<ProductPackage> obj){
		this.productPackages = obj;
	}
	@Column(name="disability_length")
	public Integer getDisabilityLength() {
		return disabilityLength;
	}
	public void setDisabilityLength(Integer disabilityLength) {
		this.disabilityLength = disabilityLength;
	}
	@Column(name="waiting_periode")
	public Integer getWaitingPeriode() {
		return waitingPeriode;
	}
	public void setWaitingPeriode(Integer waitingPeriode) {
		this.waitingPeriode = waitingPeriode;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="case_category_id")
	public CaseCategory getCaseCategory() {
		return caseCategory;
	}
	public void setCaseCategory(CaseCategory caseCategory) {
		this.caseCategory = caseCategory;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="treatment_location")
	public TreatmentLocation getTreatmentLocation() {
		return treatmentLocation;
	}
	public void setTreatmentLocation(TreatmentLocation treatmentLocation) {
		this.treatmentLocation = treatmentLocation;
	}
	@Column(name="max_member_ages")
	public Integer getMaxMemberAges() {
		return maxMemberAges;
	}
	public void setMaxMemberAges(Integer maxMemberAges) {
		this.maxMemberAges = maxMemberAges;
	}
	@Column(name="cost_sharing_coverage")
	public Double getCostSharingCoverage() {
		return costSharingCoverage;
	}
	public void setCostSharingCoverage(Double costSharingCoverage) {
		this.costSharingCoverage = costSharingCoverage;
	}
	@Column(name="special_term_and_condition")
	public String getSpecialTermCondition() {
		return specialTermCondition;
	}
	public void setSpecialTermCondition(String specialTermCondition) {
		this.specialTermCondition = specialTermCondition;
	}
	@Column(name="premi_return_percentage")
	public Double getPremiReturnPercentage() {
		return premiReturnPercentage;
	}
	public void setPremiReturnPercentage(Double premiReturnPercentage) {
		this.premiReturnPercentage = premiReturnPercentage;
	}
	@Column(name="premi_return_threshold")
	public Double getPremiReturnThreshold() {
		return premiReturnThreshold;
	}
	public void setPremiReturnThreshold(Double premiReturnThreshold) {
		this.premiReturnThreshold = premiReturnThreshold;
	}
	@Column(name="premi_product")
	public Double getPremiProduct() {
		return premiProduct;
	}
	public void setPremiProduct(Double premiProduct) {
		this.premiProduct = premiProduct;
	}
	@Column(name="service_class")
	public String getServiceClass() {
		return serviceClass;
	}
	public void setServiceClass(String serviceClass) {
		this.serviceClass = serviceClass;
	}
	@Column(name="is_template")
	public Integer getIsTemplate() {
		return isTemplate;
	}
	public void setIsTemplate(Integer isTemplate) {
		this.isTemplate = isTemplate;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="reference_product")
	public Product getProductReference() {
		return productReference;
	}
	public void setProductReference(Product productReference) {
		this.productReference = productReference;
	}
	@Column(name="cashless_percentage")
	public Double getCashlessPercentage() {
		return cashlessPercentage;
	}
	public void setCashlessPercentage(Double cashlessPercentage) {
		this.cashlessPercentage = cashlessPercentage;
	}
	@Column(name="reimbursement_percentage")
	public Double getReimbursementPercentage() {
		return reimbursementPercentage;
	}
	public void setReimbursementPercentage(Double reimbursementPercentage) {
		this.reimbursementPercentage = reimbursementPercentage;
	}
	@Column(name="domestic_percentage")
	public Double getDomesticPercentage() {
		return domesticPercentage;
	}
	public void setDomesticPercentage(Double domesticPercentage) {
		this.domesticPercentage = domesticPercentage;
	}
	@Column(name="overseas_percentage")
	public Double getOverseasPercentage() {
		return overseasPercentage;
	}
	public void setOverseasPercentage(Double overseasPercentage) {
		this.overseasPercentage = overseasPercentage;
	}
	@Column(name="edc_enabled")
	public Integer getEdcEnabled() {
		return edcEnabled;
	}
	public void setEdcEnabled(Integer edcEnabled) {
		this.edcEnabled = edcEnabled;
	}
	@Column(name="reimbursement_annual_limit")
	public Double getReimbursementAnnualLimit() {
		return reimbursementAnnualLimit;
	}
	public void setReimbursementAnnualLimit(Double reimbursementAnnualLimit) {
		this.reimbursementAnnualLimit = reimbursementAnnualLimit;
	}
	@Column(name="limit_published_on_edc")
	public Integer getLimitBenefitShowedOnEdc() {
		return limitBenefitShowedOnEdc;
	}
	public void setLimitBenefitShowedOnEdc(Integer limitBenefitShowedOnEdc) {
		this.limitBenefitShowedOnEdc = limitBenefitShowedOnEdc;
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
	@Column(name="male_premium_rate")
	public Double getMalePremiumRate() {
		return malePremiumRate;
	}
	public void setMalePremiumRate(Double malePremiumRate) {
		this.malePremiumRate = malePremiumRate;
	}
	@Column(name="female_premium_rate")
	public Double getFemalePremiumRate() {
		return femalePremiumRate;
	}
	public void setFemalePremiumRate(Double femalePremiumRate) {
		this.femalePremiumRate = femalePremiumRate;
	}
	@Column(name="child_premium_rate")
	public Double getChildrenPremiumRate() {
		return childrenPremiumRate;
	}
	public void setChildrenPremiumRate(Double childrenPremiumRate) {
		this.childrenPremiumRate = childrenPremiumRate;
	}
	@Column(name="apply_to_employee")
	public Integer getApplyToEmployee() {
		return applyToEmployee;
	}
	public void setApplyToEmployee(Integer applyToEmployee) {
		this.applyToEmployee = applyToEmployee;
	}
	@Column(name="apply_to_spouse")
	public Integer getApplyToSpouse() {
		return applyToSpouse;
	}
	public void setApplyToSpouse(Integer applyToSpouse) {
		this.applyToSpouse = applyToSpouse;
	}
	@Column(name="apply_to_child")
	public Integer getApplyToChildren() {
		return applyToChildren;
	}
	public void setApplyToChildren(Integer applyToChildren) {
		this.applyToChildren = applyToChildren;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="treatment_class_id")
	public TreatmentClass getTreatmentClassId() {
		return treatmentClassId;
	}
	public void setTreatmentClassId(TreatmentClass treatmentClassId) {
		this.treatmentClassId = treatmentClassId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="product_currency_id")
	public Currency getProductCurrencyId() {
		return productCurrencyId;
	}
	public void setProductCurrencyId(Currency productCurrencyId) {
		this.productCurrencyId = productCurrencyId;
	}
	@Column(name="is_using_salary")
	public Integer getIsUsingSalary() {
		return isUsingSalary;
	}
	public void setIsUsingSalary(Integer isUsingSalary) {
		this.isUsingSalary = isUsingSalary;
	}
	@Column(name="is_using_prorate_limit")
	public Integer getIsUsingProrateLimit() {
		return isUsingProrateLimit;
	}
	public void setIsUsingProrateLimit(Integer isUsingProrateLimit) {
		this.isUsingProrateLimit = isUsingProrateLimit;
	}
	@Column(name="salary_multiplier")
	public Double getSalaryMultiplier() {
		return salaryMultiplier;
	}
	public void setSalaryMultiplier(Double salaryMultiplier) {
		this.salaryMultiplier = salaryMultiplier;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="policy_id")
	public Policy getPolicyId() {
		return policyId;
	}
	public void setPolicyId(Policy policyId) {
		this.policyId = policyId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="shared_product_id")
	public Product getSharedProductId() {
		return sharedProductId;
	}
	public void setSharedProductId(Product sharedProductId) {
		this.sharedProductId = sharedProductId;
	}
	@Column(name="surgery_per_disability_limit")
	public Double getSurgeryPerDisabilityLimit() {
		return surgeryPerDisabilityLimit;
	}
	public void setSurgeryPerDisabilityLimit(Double surgeryPerDisabilityLimit) {
		this.surgeryPerDisabilityLimit = surgeryPerDisabilityLimit;
	}
	@Column(name="allow_overlimit")
	public Integer getAllowOverlimit() {
		return allowOverlimit;
	}
	public void setAllowOverlimit(Integer allowOverlimit) {
		this.allowOverlimit = allowOverlimit;
	}
	@Column(name="do_synchronize")
	public Integer getDoSynchronize() {
		return doSynchronize;
	}
	public void setDoSynchronize(Integer doSynchronize) {
		this.doSynchronize = doSynchronize;
	}
	@Column(name="multilayer_mechanism")
	public Integer getMultilayerMechanism() {
		return multilayerMechanism;
	}
	public void setMultilayerMechanism(Integer multilayerMechanism) {
		this.multilayerMechanism = multilayerMechanism;
	}
	
	
	
	
	
	
	
	//exported key end



}