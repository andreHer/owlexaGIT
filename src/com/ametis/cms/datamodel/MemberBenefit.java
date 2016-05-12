
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

import com.ametis.cms.dto.MemberBenefitDto;
import com.ametis.cms.dto.MemberProductDto;


@Entity
@Table(name="member_benefit")
public class MemberBenefit implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	/*
	 * Jika Occurance Method berarti nilai yang dibayarkan
	 * as Charge, namun apabila terdapat clausul cost sharing
	 * maka per occurance harus di kalikan dengan cost sharing
	 * 
	 */
	public static final int OCCURANCE_METHOD = 1;
	
	/*
	 * metode ini akan mengakumulasikan seluruh benefit yang
	 * ada dan pernah.
	 */
	public static final int ACCUMULATIVE_VALUE = 3;
	public static final int MAX_PEROCCURANCE_METHOD = 4;
	public static final int MAX_DAILY_AND_OCCURANCE_METHOD = 5;
	
	
	public static final int PERDISABILITY = 2;
	
	public static final int MEMBER_PRODUCT_BENEFIT = 1; // untuk benefit type
	public static final int MEMBER_POLICY_BENEFIT = 2; // untuk benefit type
	//Fields
		
	/**data for the column :
	
 --------- member_benefit.member_benefit_id --------- 
 schema        = null
 tableName     = member_benefit
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
	private Integer memberBenefitId;
	private Integer memberBenefitStatus;
	private String clientItemCode;
	private String itemEdcCode;
					
	/**data for the column :
	
 --------- member_benefit.created_time --------- 
 schema        = null
 tableName     = member_benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- member_benefit.created_by --------- 
 schema        = null
 tableName     = member_benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- member_benefit.deleted_time --------- 
 schema        = null
 tableName     = member_benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- member_benefit.deleted_by --------- 
 schema        = null
 tableName     = member_benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- member_benefit.modified_time --------- 
 schema        = null
 tableName     = member_benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- member_benefit.modified_by --------- 
 schema        = null
 tableName     = member_benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- member_benefit.deleted_status --------- 
 schema        = null
 tableName     = member_benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
		
	// foreign affairs
	
			/** Member
	data for the foreign class :
	
 --------- member.member_id --------- 
 schema        = null
 tableName     = member
 foreignCol    = member_id
 foreignTab    = member_benefit
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
	private Member memberId;
				/** ItemCategory
	data for the foreign class :
	
 --------- item_category.item_category_id --------- 
 schema        = null
 tableName     = item_category
 foreignCol    = item_category_id
 foreignTab    = member_benefit
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
	private ItemCategory itemCategoryId;
	
	/*
	 * Property benefitUsage berfungsi untuk melihat jumlah
	 * benefit yang telah digunakan oleh member yang bersangkutan
	 * variable tersebut dapat digunakan untuk melakukan analisa
	 * terhadap member apabila ingin di rawat.
	 */
	private Double benefitUsage;
	/*
	 * Variabel benefitLimit berfungsi untuk menandakan jumlah maksimum
	 * benefit yang dimilih oleh seorang member
	 * 
	 */
	private Double benefitLimit;
	
	/*
	 * Variabel currentBenefitPosition berfungsi untuk melihat 
	 * posisi sisa benefit yang dapat dipergunakan oleh member
	 * variabel ini berbanding terbalik dengan benefit Usage
	 * sehingga currentBenefitPosition + benefitUsage = benefitLimit
	 */
	private Double currentBenefitPosition;
	
	/*
	 * Variabel benefitExcessed berfungsi untuk melihat 
	 * berapa banyak seorang member mengalami kelebihan penggunaan
	 * benefit.
	 */
	private Double benefitExcessed;
	private TreatmentLocation treatmentLocation;
	
	/*
	 * Variabel benefitUpgradeLimit berfungsi untuk menentukan
	 * berapa besar maksimum benefit yang dapat digunakan apabila
	 * member mengalami kenaikan kelas akibat tidak tersedianya
	 * fasilitas yang sesuai dengan nilai benefit. 
	 */
	private Double benefitUpgradeLimit;
	
	/*
	 * Variabel isBenefitUpgradable berfungsi untuk menentukan 
	 * apakah benefit dari member dapat diupgrade karena tidak
	 * tersedianya fasilitas yang sesuai.
	 */
	private Boolean isBenefitUpgradable;
	
	private CaseCategory caseCategoryId;
	
	private CaseCategory productCaseCategoryId;
	
	private Boolean providerContract;
	
	private Boolean isCostSharing;
	
	private Integer costSharingMethod;
	
	private Double costSharingPercentage;
	private Double costSharingAmount;
	
	private Double maxBenefitOccurance;
	private Double benefitOccuranceUsage;
	
	private String information;
	
	private BenefitUsageType benefitCalculationMethod;
	
	private Boolean isAsCharge;
	
	private Product productId;
	
	private Double maxOccurancePerCase;
	
	
	private Double benefitUsagePercentage;
	private Double benefitExGratia;
	private Double annualBenefit;
	private Double maxBenefitPerCase; // untuk perDisability
	
	private Double cashlessPercentage;
	private Double reimbursementPercentage;
	private Integer treatmentLevel;
    private Double reimbursementBenefitLimit;
    private Boolean reimbursementAsCharge;
    
    private Double cashlessBenefitUsage;
    private Double reimbursementBenefitUsage;
    private Double cashlessUsagePercentage;
    private Double reimbursementUsagePercentage;
    
    private Boolean isEDCEnabled;
    private Integer benefitLimitPublishedOnEdc;
    private Date effectiveDate;
    private Date expireDate;
    private MemberProduct memberProductId;
    private MemberBenefit sharedBenefitId;
    private MemberBenefit familyBenefitId;
    
    private Double deductibleLimit;
    private Boolean divertBenefit;
    private Integer deductionType;
    private Integer isDeductPlanLimit;
    private PolicyBenefit policyBenefitId;
    private Integer benefitType;
    
    private Diagnosis diagnosisId;
    private DiagnosisSet diagnosisSetId;
    private Procedure procedureId;
    
    private Integer benefitUpgradeType;
    private Double benefitUpgradePercentage;

	private Integer isSMMBenefit;

	private Double smmLimit;

	private Integer effectiveSMMDay;
	
	private MemberLimitLayer memberLimitLayerId;
    

	// PK GETTER SETTER
			@Id
	@Column(name="member_benefit_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getMemberBenefitId(){
		return memberBenefitId;
	}
	public void setMemberBenefitId(java.lang.Integer value){
		memberBenefitId = value;
	}
			// PK GETTER SETTER END

	
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
	
			/** Member */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="member_id")
	public Member getMemberId(){
		return this.memberId;
	}

	/** Member */
	public void setMemberId(Member obj){
		this.memberId = obj;
	}
				/** ItemCategory */
	@ManyToOne
	@JoinColumn(name="item_category_id")
	public ItemCategory getItemCategoryId(){
		return this.itemCategoryId;
	}

	/** ItemCategory */
	public void setItemCategoryId(ItemCategory obj){
		this.itemCategoryId = obj;
	}
	@Column(name="benefit_usage")
	public Double getBenefitUsage() {
		return benefitUsage;
	}
	public void setBenefitUsage(Double benefitUsage) {
		this.benefitUsage = benefitUsage;
	}
	@Column(name="benefit_limit")
	public Double getBenefitLimit() {
		return benefitLimit;
	}
	public void setBenefitLimit(Double benefitLimit) {
		this.benefitLimit = benefitLimit;
	}
	@Column(name="current_benefit_position")
	public Double getCurrentBenefitPosition() {
		return currentBenefitPosition;
	}
	public void setCurrentBenefitPosition(Double currentBenefitPosition) {
		this.currentBenefitPosition = currentBenefitPosition;
	}
	@Column(name="benefit_excessed")
	public Double getBenefitExcessed() {
		return benefitExcessed;
	}
	public void setBenefitExcessed(Double benefitExcessed) {
		this.benefitExcessed = benefitExcessed;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="treatment_location")
	public TreatmentLocation getTreatmentLocation() {
		return treatmentLocation;
	}
	public void setTreatmentLocation(TreatmentLocation treatmentLocation) {
		this.treatmentLocation = treatmentLocation;
	}
	@Column(name="benefit_upgrade_limit")
	public Double getBenefitUpgradeLimit() {
		return benefitUpgradeLimit;
	}
	public void setBenefitUpgradeLimit(Double benefitUpgradeLimit) {
		this.benefitUpgradeLimit = benefitUpgradeLimit;
	}
	@Column(name="is_benefit_upgradable")
	public Boolean isBenefitUpgradable() {
		return isBenefitUpgradable;
	}
	public void setBenefitUpgradable(Boolean isBenefitUpgradable) {
		this.isBenefitUpgradable = isBenefitUpgradable;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="case_category_id")
	public CaseCategory getCaseCategoryId() {
		return caseCategoryId;
	}
	public void setCaseCategoryId(CaseCategory caseCategoryId) {
		this.caseCategoryId = caseCategoryId;
	}
	@Column(name="provider_contract")
	public Boolean isProviderContract() {
		return providerContract;
	}
	public void setProviderContract(Boolean providerContract) {
		this.providerContract = providerContract;
	}
	@Column(name="is_cost_sharing")
	public Boolean getIsCostSharing() {
		return isCostSharing;
	}
	public void setIsCostSharing(Boolean isCostSharing) {
		this.isCostSharing = isCostSharing;
	}
	@Column(name="cost_sharing_method")
	public Integer getCostSharingMethod() {
		return costSharingMethod;
	}
	public void setCostSharingMethod(Integer costSharingMethod) {
		this.costSharingMethod = costSharingMethod;
	}
	@Column(name="cost_sharing_percentage")
	public Double getCostSharingPercentage() {
		return costSharingPercentage;
	}
	public void setCostSharingPercentage(Double costSharingPercentage) {
		this.costSharingPercentage = costSharingPercentage;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="product_id")
	public Product getProductId() {
		return productId;
	}
	public void setProductId(Product productId) {
		this.productId = productId;
	}
	@Column(name="max_occurance")
	public Double getMaxBenefitOccurance() {
		return maxBenefitOccurance;
	}
	public void setMaxBenefitOccurance(Double maxBenefitOccurance) {
		this.maxBenefitOccurance = maxBenefitOccurance;
	}
	@Column(name="occurance_usage")
	public Double getBenefitOccuranceUsage() {
		return benefitOccuranceUsage;
	}
	public void setBenefitOccuranceUsage(Double benefitOccuranceUsage) {
		this.benefitOccuranceUsage = benefitOccuranceUsage;
	}
	@Column(name="information")
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="benefit_calculation_method")
	public BenefitUsageType getBenefitCalculationMethod() {
		return benefitCalculationMethod;
	}
	public void setBenefitCalculationMethod(BenefitUsageType benefitCalculationMethod) {
		this.benefitCalculationMethod = benefitCalculationMethod;
	}
	@Column(name="is_as_charge")
	public Boolean getIsAsCharge() {
		return isAsCharge;
	}
	public void setIsAsCharge(Boolean isAsCharge) {
		this.isAsCharge = isAsCharge;
	}
	@Column(name="max_occurance_per_case")
	public Double getMaxOccurancePerCase() {
		return maxOccurancePerCase;
	}
	public void setMaxOccurancePerCase(Double maxOccurancePerCase) {
		this.maxOccurancePerCase = maxOccurancePerCase;
	}
	@Column(name="max_benefit_per_case")
	public Double getMaxBenefitPerCase() {
		return maxBenefitPerCase;
	}
	public void setMaxBenefitPerCase(Double maxBenefitPerCase) {
		this.maxBenefitPerCase = maxBenefitPerCase;
	}
	@Column(name="member_benefit_status")
	public Integer getMemberBenefitStatus() {
		return memberBenefitStatus;
	}
	public void setMemberBenefitStatus(Integer memberBenefitStatus) {
		this.memberBenefitStatus = memberBenefitStatus;
	}
	@Column(name="benefit_usage_percentage")
	public Double getBenefitUsagePercentage() {
		return benefitUsagePercentage;
	}
	public void setBenefitUsagePercentage(Double benefitUsagePercentage) {
		this.benefitUsagePercentage = benefitUsagePercentage;
	}
	@Column(name="benefit_ex_gratia")
	public Double getBenefitExGratia() {
		return benefitExGratia;
	}
	public void setBenefitExGratia(Double benefitExGratia) {
		this.benefitExGratia = benefitExGratia;
	}
	@Column(name="annual_benefit")
	public Double getAnnualBenefit() {
		return annualBenefit;
	}
	public void setAnnualBenefit(Double annualBenefit) {
		this.annualBenefit = annualBenefit;
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
	@Column(name="treatment_level")
	public Integer getTreatmentLevel() {
		return treatmentLevel;
	}
	public void setTreatmentLevel(Integer treatmentLevel) {
		this.treatmentLevel = treatmentLevel;
	}

	
	
	
	
	
	// foreign affairs end

// exported key

	
		
	//exported key end

        @Column(name="cashless_benefit_usage")
    public Double getCashlessBenefitUsage() {
        return cashlessBenefitUsage;
    }

    public void setCashlessBenefitUsage(Double cashlessBenefitUsage) {
        this.cashlessBenefitUsage = cashlessBenefitUsage;
    }

    @Column(name="reimbursement_as_charge")
    public Boolean getReimbursementAsCharge() {
        return reimbursementAsCharge;
    }

    public void setReimbursementAsCharge(Boolean reimbursementAsCharge) {
        this.reimbursementAsCharge = reimbursementAsCharge;
    }

    @Column(name="reimbursement_benefit_limit")
    public Double getReimbursementBenefitLimit() {
        return reimbursementBenefitLimit;
    }

    public void setReimbursementBenefitLimit(Double reimbursementBenefitLimit) {
        this.reimbursementBenefitLimit = reimbursementBenefitLimit;
    }

    @Column(name="reimbursement_benefit_usage")
    public Double getReimbursementBenefitUsage() {
        return reimbursementBenefitUsage;
    }

    public void setReimbursementBenefitUsage(Double reimbursementBenefitUsage) {
        this.reimbursementBenefitUsage = reimbursementBenefitUsage;
    }

    @Column(name="cashless_usage_percentage")
    public Double getCashlessUsagePercentage() {
        return cashlessUsagePercentage;
    }

    public void setCashlessUsagePercentage(Double cashlessUsagePercentage) {
        this.cashlessUsagePercentage = cashlessUsagePercentage;
    }

    @Column(name="reimbursement_usage_percentage")
    public Double getReimbursementUsagePercentage() {
        return reimbursementUsagePercentage;
    }

    public void setReimbursementUsagePercentage(Double reimbursementUsagePercentage) {
        this.reimbursementUsagePercentage = reimbursementUsagePercentage;
    }
    
    @Column(name="is_edc_enabled")
	public Boolean getIsEDCEnabled() {
		return isEDCEnabled;
	}
	public void setIsEDCEnabled(Boolean isEDCEnabled) {
		this.isEDCEnabled = isEDCEnabled;
	}
	@Column(name="limit_published_on_edc")
	public Integer getBenefitLimitPublishedOnEdc() {
		return benefitLimitPublishedOnEdc;
	}
	public void setBenefitLimitPublishedOnEdc(Integer benefitLimitPublishedOnEdc) {
		this.benefitLimitPublishedOnEdc = benefitLimitPublishedOnEdc;
	}
	@Column(name="effective_date")
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	@Column(name="expire_date")
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="member_product_id")
	public MemberProduct getMemberProductId() {
		return memberProductId;
	}
	public void setMemberProductId(MemberProduct memberProductId) {
		this.memberProductId = memberProductId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="shared_benefit_id")
	public MemberBenefit getSharedBenefitId() {
		return sharedBenefitId;
	}
	public void setSharedBenefitId(MemberBenefit sharedBenefitId) {
		this.sharedBenefitId = sharedBenefitId;
	}
	
	@Column(name="deductible_limit")
	public Double getDeductibleLimit() {
		return deductibleLimit;
	}
	public void setDeductibleLimit(Double deductibleLimit) {
		this.deductibleLimit = deductibleLimit;
	}
	@Column(name="cost_sharing_amount")
	public Double getCostSharingAmount() {
		return costSharingAmount;
	}
	public void setCostSharingAmount(Double costSharingAmount) {
		this.costSharingAmount = costSharingAmount;
	}
	@ManyToOne
	@JoinColumn(name="product_case_category_id")
	public CaseCategory getProductCaseCategoryId() {
		return productCaseCategoryId;
	}
	public void setProductCaseCategoryId(CaseCategory productCaseCategoryId) {
		this.productCaseCategoryId = productCaseCategoryId;
	}
	@Column(name="divert_benefit")
	public Boolean getDivertBenefit() {
		return divertBenefit;
	}
	public void setDivertBenefit(Boolean divertBenefit) {
		this.divertBenefit = divertBenefit;
	}
	@Column(name="deduction_type")
	public Integer getDeductionType() {
		return deductionType;
	}
	public void setDeductionType(Integer deductionType) {
		this.deductionType = deductionType;
	}
	@Column(name="is_deduct_plan_limit")
	public Integer getIsDeductPlanLimit() {
		return isDeductPlanLimit;
	}
	public void setIsDeductPlanLimit(Integer isDeductPlanLimit) {
		this.isDeductPlanLimit = isDeductPlanLimit;
	}
	@Column(name="client_item_code")
	public String getClientItemCode() {
		return clientItemCode;
	}
	public void setClientItemCode(String clientItemCode) {
		this.clientItemCode = clientItemCode;
	}
	@Column(name="item_edc_code")
	public String getItemEdcCode() {
		return itemEdcCode;
	}
	public void setItemEdcCode(String itemEdcCode) {
		this.itemEdcCode = itemEdcCode;
	}
	@ManyToOne
	@JoinColumn(name="policy_benefit_id")
	public PolicyBenefit getPolicyBenefitId() {
		return policyBenefitId;
	}
	public void setPolicyBenefitId(PolicyBenefit policyBenefitId) {
		this.policyBenefitId = policyBenefitId;
	}
	@Column(name="benefit_type")
	public Integer getBenefitType() {
		return benefitType;
	}
	public void setBenefitType(Integer benefitType) {
		this.benefitType = benefitType;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="diagnosis_id")
	public Diagnosis getDiagnosisId() {
		return diagnosisId;
	}
	public void setDiagnosisId(Diagnosis diagnosisId) {
		this.diagnosisId = diagnosisId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="diagnosis_set_id")
	public DiagnosisSet getDiagnosisSetId() {
		return diagnosisSetId;
	}
	public void setDiagnosisSetId(DiagnosisSet diagnosisSetId) {
		this.diagnosisSetId = diagnosisSetId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="procedure_id")
	public Procedure getProcedureId() {
		return procedureId;
	}
	public void setProcedureId(Procedure procedureId) {
		this.procedureId = procedureId;
	}
	@Column(name="benefit_upgrade_type")
	public Integer getBenefitUpgradeType() {
		return benefitUpgradeType;
	}
	public void setBenefitUpgradeType(Integer benefitUpgradeType) {
		this.benefitUpgradeType = benefitUpgradeType;
	}
	@Column(name="benefit_upgrade_percentage")
	public Double getBenefitUpgradePercentage() {
		return benefitUpgradePercentage;
	}
	public void setBenefitUpgradePercentage(Double benefitUpgradePercentage) {
		this.benefitUpgradePercentage = benefitUpgradePercentage;
	}
	
	

	@Column(name="is_smm_benefit")
	public Integer getIsSMMBenefit() {
		return isSMMBenefit;
	}

	public void setIsSMMBenefit(Integer isSMMBenefit) {
		this.isSMMBenefit = isSMMBenefit;
	}

	@Column(name="smm_limit")
	public Double getSmmLimit() {
		return smmLimit;
	}

	public void setSmmLimit(Double smmLimit) {
		this.smmLimit = smmLimit;
	}

	@Column(name="effective_smm_day")
	public Integer getEffectiveSMMDay() {
		return effectiveSMMDay;
	}

	public void setEffectiveSMMDay(Integer effectiveSMMDay) {
		this.effectiveSMMDay = effectiveSMMDay;
	}
	@ManyToOne
	@JoinColumn(name="member_limit_layer_id")
	public MemberLimitLayer getMemberLimitLayerId() {
		return memberLimitLayerId;
	}
	public void setMemberLimitLayerId(MemberLimitLayer memberLimitLayerId) {
		this.memberLimitLayerId = memberLimitLayerId;
	}
	
	
	@ManyToOne
	@JoinColumn(name="family_benefit_id")
	public MemberBenefit getFamilyBenefitId() {
		return familyBenefitId;
	}
	public void setFamilyBenefitId(MemberBenefit familyBenefitId) {
		this.familyBenefitId = familyBenefitId;
	}
	//Add by aju on 20150910, for making memberBenefitDto fufufu
	public MemberBenefitDto exportDto(){
		
		MemberBenefitDto memberBenefit = new MemberBenefitDto();
		
		memberBenefit.setMemberBenefitId(memberBenefitId);
		memberBenefit.setItemCategoryId(itemCategoryId.getItemCategoryId());
		
		memberBenefit.setBenefitItemName(itemCategoryId.getItemCategoryName());
		memberBenefit.setBenefitItemCode(itemCategoryId.getItemCategoryCode());
		
		memberBenefit.setBenefitCalculationMethodId(benefitCalculationMethod.getBenefitUsageTypeId());
		memberBenefit.setBenefitCalculationMethodName(benefitCalculationMethod.getBenefitUsage());
		
		memberBenefit.setBenefitLimit(benefitLimit);
		memberBenefit.setCashlessPercentage(cashlessPercentage);
		memberBenefit.setReimbursementBenefitLimit(reimbursementBenefitLimit);
		memberBenefit.setReimbursementPercentage(reimbursementPercentage);
		memberBenefit.setCurrentBenefit(currentBenefitPosition);
		
		memberBenefit.setMaxPercaseBenefitLimit(maxBenefitPerCase);
		memberBenefit.setMemberName(memberId.getFirstName());
		
		memberBenefit.setBenefitCaseCategoryId(caseCategoryId.getCaseCategoryId());
		memberBenefit.setBenefitCaseCategoryName(caseCategoryId.getCaseCategoryName());
		memberBenefit.setBenefitCaseCategoryCode(caseCategoryId.getCaseCategoryCode());
		
		
		return memberBenefit;
	}

}