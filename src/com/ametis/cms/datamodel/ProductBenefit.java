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

@Entity
@Table(name = "product_benefit")
public class ProductBenefit implements java.io.Serializable {


    private static final long serialVersionUID = 1L;
    public static final int LEVEL_KOMPLEKS = 0;
    public static final int LEVEL_BESAR = 1;
    public static final int LEVEL_SEDANG = 2;
    public static final int LEVEL_KECIL = 3;
    public static final int DEPENDENT_SHARED_BENEFIT = 1;
    public static final int DEPENDENT_EXCLUSIVE_BENEFIT = 2;
    public static final int DEPENDENT_UNCOVERED = 0;
    
	public static final String PRODUCT_CODE = "ProductCode";
	public static final String BENEFIT_CODE = "BenefitCode";
	public static final String BENEFIT_NAME = "BenefitName";
	public static final String POLICY_NUMBER = "PolicyNumber";
	public static final String PRODUCT_SERVICE = "ProductService";
	public static final String BENEFIT_RULES = "BenefitRule";
	public static final String BENEFIT_AMOUNT = "BenefitAmount";
	public static final String BENEFIT_AMOUNT_PER_CASE  = "BenefitAmountPerCase";
	public static final String BENEFIT_COUNT_PERCASE = "BenefitCountPerCase";
	public static final String BENEFIT_COUNT_PER_ANNUM = "BenefitCountPerAnnum";
	public static final String BENEFIT_DEDUCTIBLE = "Deductible";
	public static final String PRINCIPAL_CO_SHARE_AMOUNT = "PrincipalCoShareAmount";
	public static final String PRINCIPAL_CO_SHARE_PERCENTAGE = "PrincipalCoSharePercentage";
	public static final String DEPENDENT_CO_SHARE_AMOUNT = "DependentCoShareAmount";
	public static final String DEPENDENT_CO_SHARE_PERCENTAGE = "DependentCoSharePercentage";
	public static final String SHARED_BENEFIT_CODE = "SharedBenefitCode";
	public static final String IS_USING_EDC = "IsEDC";
	public static final String BENEFIT_SHOW_EDC = "BenefitShowEDC";
	public static final String BENEFIT_LOCATION = "Location";
	public static final String EXCLUDE_PLAN_LIMIT = "ExcludePlanLimit";

	
	public void setValue (String key, String value){
        System.out.println("KEY = "+ key + " VALUE = " + value);
    	
        if (key.equalsIgnoreCase(PRODUCT_CODE))
            this.productCode = value;        
        if (key.equalsIgnoreCase(POLICY_NUMBER))
            this.policyNumber = value;        
        if (key.equalsIgnoreCase(BENEFIT_CODE))
            this.benefitCode = value;        
        if (key.equalsIgnoreCase(PRODUCT_SERVICE))
            this.caseCategory = value;        
        if (key.equalsIgnoreCase(BENEFIT_RULES)){
        	if (value != null && !value.trim().equalsIgnoreCase("")){
            	BenefitUsageType ruleType = null;
            	if (value.equalsIgnoreCase("01")){
            		ruleType = new BenefitUsageType();
            		ruleType.setBenefitUsageTypeId(MemberBenefit.MAX_DAILY_AND_OCCURANCE_METHOD);
            	}
            	else if (value.equalsIgnoreCase("02")){
            		ruleType = new BenefitUsageType();
            		ruleType.setBenefitUsageTypeId(MemberBenefit.PERDISABILITY);
            	}
            	else if (value.equalsIgnoreCase("03")){
            		ruleType = new BenefitUsageType();
            		ruleType.setBenefitUsageTypeId(MemberBenefit.ACCUMULATIVE_VALUE);
				}
            	if (ruleType != null){
            		this.benefitUsageType = ruleType;
            	}
            }
        	this.benefitRules = value;
        }
                    
        if (key.equalsIgnoreCase(BENEFIT_NAME))
            this.benefitName = value;
        
        if (key.equalsIgnoreCase(SHARED_BENEFIT_CODE))
        	this.sharedBenefitCode = value;
        
        if (key.equalsIgnoreCase(BENEFIT_AMOUNT)){
            if (value != null && !value.trim().equalsIgnoreCase("")){
                this.benefitLimit = Double.valueOf(value);
            }
        }
        if (key.equalsIgnoreCase(BENEFIT_AMOUNT_PER_CASE)){
            if (value != null && !value.trim().equalsIgnoreCase("")){
            	this.maxBenefitPerCase = Double.valueOf(value);
            }
        }
        if (key.equalsIgnoreCase(BENEFIT_COUNT_PER_ANNUM)){
            if (value != null && !value.trim().equalsIgnoreCase("")){
            	this.maxOccurance = Integer.valueOf(value);
            }
        }
        if (key.equalsIgnoreCase(BENEFIT_COUNT_PERCASE)){
            if (value != null && !value.trim().equalsIgnoreCase("")){
            	this.maxOccurancePerCase = Double.valueOf(value);
            }
        }
        if (key.equalsIgnoreCase(BENEFIT_DEDUCTIBLE)){
            if (value != null && !value.trim().equalsIgnoreCase("")){
            	this.deductibleLimit = Double.valueOf(value);
            }
        }
        if (key.equalsIgnoreCase(PRINCIPAL_CO_SHARE_AMOUNT)){
            if (value != null && !value.trim().equalsIgnoreCase("")){
            	this.costSharingAmount = Double.valueOf(value);
            }
        }
        if (key.equalsIgnoreCase(PRINCIPAL_CO_SHARE_PERCENTAGE)){
            if (value != null && !value.trim().equalsIgnoreCase("")){
            	this.costSharingPercentage = Double.valueOf(value);
            }
        }
        if (key.equalsIgnoreCase(DEPENDENT_CO_SHARE_AMOUNT)){
            if (value != null && !value.trim().equalsIgnoreCase("")){
            	this.dependentCoShareAmount = Double.valueOf(value);
            }
        }
        if (key.equalsIgnoreCase(DEPENDENT_CO_SHARE_PERCENTAGE)){
            if (value != null && !value.trim().equalsIgnoreCase("")){
            	this.dependentCoSharePercentage = Double.valueOf(value);
            }
        }
        if (key.equalsIgnoreCase(BENEFIT_SHOW_EDC)){
            if (value != null && value.trim().equalsIgnoreCase("Y")){            	
            	this.benefitShowedOnEdc = 1;
            }
            else {
            	this.benefitShowedOnEdc = 0;
            }
        }
        if (key.equalsIgnoreCase(IS_USING_EDC)){
        	if (value != null && value.trim().equalsIgnoreCase("Y")){            	
            	this.isEdcEnabled = 1;
            }
            else {
            	this.isEdcEnabled = 0;
            }
        }
        if (key.equalsIgnoreCase(BENEFIT_LOCATION)){
            if (value != null && !value.trim().equalsIgnoreCase("")){
            	TreatmentLocation location = null;
            	if (value.equalsIgnoreCase("01")){
            		location = new TreatmentLocation();
            		location.setLocationId(TreatmentLocation.DOMESTIC);
            	}
            	else if (value.equalsIgnoreCase("02")){
            		location = new TreatmentLocation();
            		location.setLocationId(TreatmentLocation.OVERSEAS);
            	}
            	else if (value.equalsIgnoreCase("03")){
            		location = new TreatmentLocation();
            		location.setLocationId(TreatmentLocation.BOTH);
				}
            	if (location != null){
            		this.treatmentLocation = location;
            	}
            }
        }
        if (key.equalsIgnoreCase(EXCLUDE_PLAN_LIMIT)){
        	if (value != null && value.trim().equalsIgnoreCase("Y")){            	
            	this.isDeductPlanLimit =0;
            }
            else {
            	this.isDeductPlanLimit = 1;
            }
        }

    }
	
	private String productCode;
	private String benefitCode;
	private String benefitName;
	private String policyNumber;
	private String caseCategory;
	private String benefitRules;
	private String sharedBenefitCode;
	
    private Integer treatmentLevel;
    //Fields
    /**
     * data for the column :
     *
     * --------- product_benefit.product_benefit_id --------- schema = null
     * tableName = product_benefit foreignCol = foreignTab = catalog = insurance
     * remarks = auto_increment defaultValue = null decDigits = 0 radix = 10
     * nullable = 0 ordinal = 1 size = 11 type = 4 isPrimaryKey = true
     *
     * =========================================
     *
     *
     */
    private Integer productBenefitId;
    private Integer coverageDaysInterval;
    private Integer preCoverageDaysInterval;
    private Integer benefitUpgradeCoverageDaysInterval;
    /**
     * data for the column :
     *
     * --------- product_benefit.benefit_limit --------- schema = null tableName
     * = product_benefit foreignCol = foreignTab = catalog = insurance remarks =
     * defaultValue = null decDigits = 0 radix = 10 nullable = 1 ordinal = 2
     * size = 22 type = 8 isPrimaryKey = false
     *
     * =========================================
     *
     *
     */
    private Double benefitLimit;
    /**
     * data for the column :
     *
     * --------- product_benefit.provider_contract --------- schema = null
     * tableName = product_benefit foreignCol = foreignTab = catalog = insurance
     * remarks = defaultValue = null decDigits = 0 radix = 10 nullable = 1
     * ordinal = 3 size = 11 type = 4 isPrimaryKey = false
     *
     * =========================================
     *
     *
     */
    private Integer providerContract;
    /**
     * data for the column :
     *
     * --------- product_benefit.max_occurance --------- schema = null tableName
     * = product_benefit foreignCol = foreignTab = catalog = insurance remarks =
     * defaultValue = null decDigits = 0 radix = 10 nullable = 1 ordinal = 4
     * size = 11 type = 4 isPrimaryKey = false
     *
     * =========================================
     *
     *
     */
    private Integer maxOccurance;
    /**
     * data for the column :
     *
     * --------- product_benefit.discount --------- schema = null tableName =
     * product_benefit foreignCol = foreignTab = catalog = insurance remarks =
     * defaultValue = null decDigits = 0 radix = 10 nullable = 1 ordinal = 5
     * size = 22 type = 8 isPrimaryKey = false
     *
     * =========================================
     *
     *
     */
    private Double discount;
    /**
     * data for the column :
     *
     * --------- product_benefit.created_time --------- schema = null tableName
     * = product_benefit foreignCol = foreignTab = catalog = insurance remarks =
     * defaultValue = null decDigits = 0 radix = 10 nullable = 1 ordinal = 13
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
     * --------- product_benefit.created_by --------- schema = null tableName =
     * product_benefit foreignCol = foreignTab = catalog = insurance remarks =
     * defaultValue = null decDigits = 0 radix = 10 nullable = 1 ordinal = 14
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
     * --------- product_benefit.deleted_time --------- schema = null tableName
     * = product_benefit foreignCol = foreignTab = catalog = insurance remarks =
     * defaultValue = null decDigits = 0 radix = 10 nullable = 1 ordinal = 15
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
     * --------- product_benefit.deleted_by --------- schema = null tableName =
     * product_benefit foreignCol = foreignTab = catalog = insurance remarks =
     * defaultValue = null decDigits = 0 radix = 10 nullable = 1 ordinal = 16
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
     * --------- product_benefit.modified_time --------- schema = null tableName
     * = product_benefit foreignCol = foreignTab = catalog = insurance remarks =
     * defaultValue = null decDigits = 0 radix = 10 nullable = 1 ordinal = 17
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
     * --------- product_benefit.modified_by --------- schema = null tableName =
     * product_benefit foreignCol = foreignTab = catalog = insurance remarks =
     * defaultValue = null decDigits = 0 radix = 10 nullable = 1 ordinal = 18
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
     * --------- product_benefit.deleted_status --------- schema = null
     * tableName = product_benefit foreignCol = foreignTab = catalog = insurance
     * remarks = defaultValue = null decDigits = 0 radix = 10 nullable = 1
     * ordinal = 19 size = 11 type = 4 isPrimaryKey = false
     *
     * =========================================
     *
     *
     */
    private Integer deletedStatus;
    // foreign affairs
    /**
     * DiscountUsageType data for the foreign class :
     *
     * --------- discount_usage_type.discount_usage_type_id --------- schema =
     * null tableName = discount_usage_type foreignCol = discount_usage_type
     * foreignTab = product_benefit catalog = insurance remarks = auto_increment
     * defaultValue = null decDigits = 0 radix = 10 nullable = 0 ordinal = 1
     * size = 11 type = 4 isPrimaryKey = true
     *
     * =========================================
     *
     *
     *
     */
    private DiscountUsageType discountUsageType;
    /**
     * BenefitUsageType data for the foreign class :
     *
     * --------- benefit_usage_type.benefit_usage_type_id --------- schema =
     * null tableName = benefit_usage_type foreignCol = benefit_usage_type
     * foreignTab = product_benefit catalog = insurance remarks = auto_increment
     * defaultValue = null decDigits = 0 radix = 10 nullable = 0 ordinal = 1
     * size = 11 type = 4 isPrimaryKey = true
     *
     * =========================================
     *
     *
     *
     */
    private BenefitUsageType benefitUsageType;
    /**
     * Product data for the foreign class :
     *
     * --------- product.product_id --------- schema = null tableName = product
     * foreignCol = product_id foreignTab = product_benefit catalog = insurance
     * remarks = auto_increment defaultValue = null decDigits = 0 radix = 10
     * nullable = 0 ordinal = 1 size = 11 type = 4 isPrimaryKey = true
     *
     * =========================================
     *
     *
     *
     */
    private Product productId;
    /**
     * MeasurementUnit data for the foreign class :
     *
     * --------- measurement_unit.measurement_unit_id --------- schema = null
     * tableName = measurement_unit foreignCol = measurement_unit foreignTab =
     * product_benefit catalog = insurance remarks = auto_increment defaultValue
     * = null decDigits = 0 radix = 10 nullable = 0 ordinal = 1 size = 11 type =
     * 4 isPrimaryKey = true
     *
     * =========================================
     *
     *
     *
     */
    private MeasurementUnit measurementUnit;
    /**
     * TreatmentLocation data for the foreign class :
     *
     * --------- treatment_location.location_id --------- schema = null
     * tableName = treatment_location foreignCol = treatment_location foreignTab
     * = product_benefit catalog = insurance remarks = auto_increment
     * defaultValue = null decDigits = 0 radix = 10 nullable = 0 ordinal = 1
     * size = 11 type = 4 isPrimaryKey = true
     *
     * =========================================
     *
     *
     *
     */
    private TreatmentLocation treatmentLocation;
    /**
     * ItemCategory data for the foreign class :
     *
     * --------- item_category.item_category_id --------- schema = null
     * tableName = item_category foreignCol = item_category_id foreignTab =
     * product_benefit catalog = insurance remarks = auto_increment defaultValue
     * = null decDigits = 0 radix = 10 nullable = 0 ordinal = 1 size = 11 type =
     * 4 isPrimaryKey = true
     *
     * =========================================
     *
     *
     *
     */
    private ItemCategory itemCategoryId;
    /**
     * CaseCategory data for the foreign class :
     *
     * --------- case_category.case_category_id --------- schema = null
     * tableName = case_category foreignCol = case_category_id foreignTab =
     * product_benefit catalog = insurance remarks = auto_increment defaultValue
     * = null decDigits = 0 radix = 10 nullable = 0 ordinal = 1 size = 11 type =
     * 4 isPrimaryKey = true
     *
     * =========================================
     *
     *
     *
     */
    private CaseCategory caseCategoryId;
    private Boolean isBenefitUpgradable;
    private Double benefitUpgradeLimit;
    private Boolean isCostSharing;
    private Double costSharingPercentage;
    private Integer costSharingMethod;
    private Boolean isAsCharge;
    private Double maxOccurancePerCase;
    private String information;
    private Double maxBenefitPerCase;
    private Integer dependentSharingMethod;
    private Integer dependentCoveredStatus;
    private Double cashlessPercentage;
    private Double reimbursementPercentage;
    private Boolean reimburseAsCharge;
    private Double reimbursementBenefitLimit;
    private Integer isEdcEnabled;
    private Integer benefitShowedOnEdc;
    
    private DiagnosisSet diagnosisSetId;
    private Procedure procedureId;
    private Diagnosis diagnosisId;
    
    private ProductBenefit sharedBenefitId;
    private Double deductibleLimit;
    private Double costSharingAmount;
    private Boolean divertBenefit;
    
    private Integer deductionType;
    private Integer isDeductPlanLimit;
    private Integer benefitUpgradeType;
    private Double benefitUpgradePercentage;
    
    private Double dependentCoSharePercentage;
    private Double dependentCoShareAmount;
    
    private String clientItemCode;
    private ProductBenefit referenceBenefitId;
    private Integer isSMMBenefit;
    private Double smmLimit;
    private Integer effectiveSMMDay;
    private ProductLayerLimit productLayerId;
    
    @Id
    @Column(name = "product_benefit_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public java.lang.Integer getProductBenefitId() {
        return productBenefitId;
    }

    public void setProductBenefitId(java.lang.Integer value) {
        productBenefitId = value;
    }
    // PK GETTER SETTER END

    @Column(name = "benefit_limit")
    public java.lang.Double getBenefitLimit() {
        return benefitLimit;
    }

    public void setBenefitLimit(java.lang.Double value) {
        benefitLimit = value;
    }

    @Column(name = "provider_contract")
    public java.lang.Integer getProviderContract() {
        return providerContract;
    }

    public void setProviderContract(java.lang.Integer value) {
        providerContract = value;
    }

    @Column(name = "max_occurance")
    public java.lang.Integer getMaxOccurance() {
        return maxOccurance;
    }

    public void setMaxOccurance(java.lang.Integer value) {
        maxOccurance = value;
    }

    @Column(name = "discount")
    public java.lang.Double getDiscount() {
        return discount;
    }

    public void setDiscount(java.lang.Double value) {
        discount = value;
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

    // foreign affairs
    /**
     * DiscountUsageType
     */
    @ManyToOne
    @JoinColumn(name = "discount_usage_type")
    public DiscountUsageType getDiscountUsageType() {
        return this.discountUsageType;
    }

    /**
     * DiscountUsageType
     */
    public void setDiscountUsageType(DiscountUsageType obj) {
        this.discountUsageType = obj;
    }

    /**
     * BenefitUsageType
     */
    @ManyToOne
    @JoinColumn(name = "benefit_usage_type")
    public BenefitUsageType getBenefitUsageType() {
        return this.benefitUsageType;
    }

    /**
     * BenefitUsageType
     */
    public void setBenefitUsageType(BenefitUsageType obj) {
        this.benefitUsageType = obj;
    }

    /**
     * Product
     */
    @ManyToOne
    @JoinColumn(name = "product_id")
    public Product getProductId() {
        return this.productId;
    }

    /**
     * Product
     */
    public void setProductId(Product obj) {
        this.productId = obj;
    }

    /**
     * MeasurementUnit
     */
    @ManyToOne
    @JoinColumn(name = "measurement_unit")
    public MeasurementUnit getMeasurementUnit() {
        return this.measurementUnit;
    }

    /**
     * MeasurementUnit
     */
    public void setMeasurementUnit(MeasurementUnit obj) {
        this.measurementUnit = obj;
    }

    /**
     * TreatmentLocation
     */
    @ManyToOne
    @JoinColumn(name = "treatment_location")
    public TreatmentLocation getTreatmentLocation() {
        return this.treatmentLocation;
    }

    /**
     * TreatmentLocation
     */
    public void setTreatmentLocation(TreatmentLocation obj) {
        this.treatmentLocation = obj;
    }

    /**
     * ItemCategory
     */
    @ManyToOne
    @JoinColumn(name = "item_category_id")
    public ItemCategory getItemCategoryId() {
        return this.itemCategoryId;
    }

    /**
     * ItemCategory
     */
    public void setItemCategoryId(ItemCategory obj) {
        this.itemCategoryId = obj;
    }

    /**
     * CaseCategory
     */
    @ManyToOne
    @JoinColumn(name = "case_category_id")
    public CaseCategory getCaseCategoryId() {
        return this.caseCategoryId;
    }

    /**
     * CaseCategory
     */
    public void setCaseCategoryId(CaseCategory obj) {
        this.caseCategoryId = obj;
    }

    @Column(name = "is_benefit_upgradable")
    public Boolean isBenefitUpgradable() {
        return isBenefitUpgradable;
    }

    public void setBenefitUpgradable(Boolean isBenefitUpgradable) {
        this.isBenefitUpgradable = isBenefitUpgradable;
    }

    @Column(name = "benefit_upgrade_limit")
    public Double getBenefitUpgradeLimit() {
        return benefitUpgradeLimit;
    }

    public void setBenefitUpgradeLimit(Double benefitUpgradeLimit) {
        this.benefitUpgradeLimit = benefitUpgradeLimit;
    }

    @Column(name = "is_cost_sharing")
    public Boolean isCostSharing() {
        return isCostSharing;
    }

    public void setCostSharing(Boolean isCostSharing) {
        this.isCostSharing = isCostSharing;
    }

    @Column(name = "cost_sharing_percentage")
    public Double getCostSharingPercentage() {
        return costSharingPercentage;
    }

    public void setCostSharingPercentage(Double costSharingPercentage) {
        this.costSharingPercentage = costSharingPercentage;
    }

    @Column(name = "cost_sharing_method")
    public Integer getCostSharingMethod() {
        return costSharingMethod;
    }

    public void setCostSharingMethod(Integer costSharingMethod) {
        this.costSharingMethod = costSharingMethod;
    }

    @Column(name = "is_as_charge")
    public Boolean getIsAsCharge() {
        return isAsCharge;
    }

    public void setIsAsCharge(Boolean isAsCharge) {
        this.isAsCharge = isAsCharge;
    }

    @Column(name = "max_occurance_per_case")
    public Double getMaxOccurancePerCase() {
        return maxOccurancePerCase;
    }

    public void setMaxOccurancePerCase(Double maxOccurancePerCase) {
        this.maxOccurancePerCase = maxOccurancePerCase;
    }

    @Column(name = "information")
    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @Column(name = "max_benefit_per_case")
    public Double getMaxBenefitPerCase() {
        return maxBenefitPerCase;
    }

    public void setMaxBenefitPerCase(Double maxBenefitPerCase) {
        this.maxBenefitPerCase = maxBenefitPerCase;
    }

    @Column(name = "treatment_level")
    public Integer getTreatmentLevel() {
        return treatmentLevel;
    }

    public void setTreatmentLevel(Integer treatmentLevel) {
        this.treatmentLevel = treatmentLevel;
    }

    @Column(name = "coverage_days_interval")
    public Integer getCoverageDaysInterval() {
        return coverageDaysInterval;
    }

    public void setCoverageDaysInterval(Integer coverageDaysInterval) {
        this.coverageDaysInterval = coverageDaysInterval;
    }

    @Column(name = "benefit_upgrade_days_interval")
    public Integer getBenefitUpgradeCoverageDaysInterval() {
        return benefitUpgradeCoverageDaysInterval;
    }

    public void setBenefitUpgradeCoverageDaysInterval(
            Integer benefitUpgradeCoverageDaysInterval) {
        this.benefitUpgradeCoverageDaysInterval = benefitUpgradeCoverageDaysInterval;
    }

    @Column(name = "dependent_benefit_coverage_method")
    public Integer getDependentSharingMethod() {
        return dependentSharingMethod;
    }

    public void setDependentSharingMethod(Integer dependentSharingMethod) {
        this.dependentSharingMethod = dependentSharingMethod;
    }

    @Column(name = "is_dependant_covered")
    public Integer getDependentCoveredStatus() {
        return dependentCoveredStatus;
    }

    public void setDependentCoveredStatus(Integer dependentCoveredStatus) {
        this.dependentCoveredStatus = dependentCoveredStatus;
    }

    @Column(name = "cashless_percentage")
    public Double getCashlessPercentage() {
        return cashlessPercentage;
    }

    public void setCashlessPercentage(Double cashlessPercentage) {
        this.cashlessPercentage = cashlessPercentage;
    }

    @Column(name = "reimbursement_percentage")
    public Double getReimbursementPercentage() {
        return reimbursementPercentage;
    }

    public void setReimbursementPercentage(Double reimbursementPercentage) {
        this.reimbursementPercentage = reimbursementPercentage;
    }

    // foreign affairs end
// exported key
    //exported key end
    @Column(name = "reimburse_as_charge")
    public Boolean getReimburseAsCharge() {
        return reimburseAsCharge;
    }

    public void setReimburseAsCharge(Boolean reimburseAsCharge) {
        this.reimburseAsCharge = reimburseAsCharge;
    }

    @Column(name = "reimbursement_benefit_limit")
    public Double getReimbursementBenefitLimit() {
        return reimbursementBenefitLimit;
    }

    public void setReimbursementBenefitLimit(Double reimbursementBenefitLimit) {
        this.reimbursementBenefitLimit = reimbursementBenefitLimit;
    }

    @Column(name="is_edc_enabled")
	public Integer getIsEdcEnabled() {
		return isEdcEnabled;
	}

	public void setIsEdcEnabled(Integer isEdcEnabled) {
		this.isEdcEnabled = isEdcEnabled;
	}

	@Column(name="limit_published_on_edc")
	public Integer getBenefitShowedOnEdc() {
		return benefitShowedOnEdc;
	}

	public void setBenefitShowedOnEdc(Integer benefitShowedOnEdc) {
		this.benefitShowedOnEdc = benefitShowedOnEdc;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="shared_benefit_id")
	public ProductBenefit getSharedBenefitId() {
		return sharedBenefitId;
	}

	public void setSharedBenefitId(ProductBenefit sharedBenefitId) {
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

	@Column(name="dependent_co_share_percentage")
	public Double getDependentCoSharePercentage() {
		return dependentCoSharePercentage;
	}

	public void setDependentCoSharePercentage(Double dependentCoSharePercentage) {
		this.dependentCoSharePercentage = dependentCoSharePercentage;
	}

	@Column(name="dependent_co_share_amount")
	public Double getDependentCoShareAmount() {
		return dependentCoShareAmount;
	}

	public void setDependentCoShareAmount(Double dependentCoShareAmount) {
		this.dependentCoShareAmount = dependentCoShareAmount;
	}

	@Column(name="client_item_code")
	public String getClientItemCode() {
		return clientItemCode;
	}

	public void setClientItemCode(String clientItemCode) {
		this.clientItemCode = clientItemCode;
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

	@Column(name="pre_coverage_days_interval")
	public Integer getPreCoverageDaysInterval() {
		return preCoverageDaysInterval;
	}

	public void setPreCoverageDaysInterval(Integer preCoverageDaysInterval) {
		this.preCoverageDaysInterval = preCoverageDaysInterval;
	}

	@ManyToOne
	@JoinColumn(name="diagnosis_set_id")
	public DiagnosisSet getDiagnosisSetId() {
		return diagnosisSetId;
	}

	public void setDiagnosisSetId(DiagnosisSet diagnosisSetId) {
		this.diagnosisSetId = diagnosisSetId;
	}

	@ManyToOne
	@JoinColumn(name="procedure_id")
	public Procedure getProcedureId() {
		return procedureId;
	}

	public void setProcedureId(Procedure procedureId) {
		this.procedureId = procedureId;
	}

	@ManyToOne
	@JoinColumn(name="diagnosis_id")
	public Diagnosis getDiagnosisId() {
		return diagnosisId;
	}

	public void setDiagnosisId(Diagnosis diagnosisId) {
		this.diagnosisId = diagnosisId;
	}

	@ManyToOne
	@JoinColumn(name="reference_benefit_id")
	public ProductBenefit getReferenceBenefitId() {
		return referenceBenefitId;
	}

	public void setReferenceBenefitId(ProductBenefit referenceBenefitId) {
		this.referenceBenefitId = referenceBenefitId;
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
	@JoinColumn(name="product_layer_id")
	public ProductLayerLimit getProductLayerId() {
		return productLayerId;
	}

	public void setProductLayerId(ProductLayerLimit productLayerId) {
		this.productLayerId = productLayerId;
	}
	
	@Column(name="product_code")
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	@Column(name="benefit_code")
	public String getBenefitCode() {
		return benefitCode;
	}

	public void setBenefitCode(String benefitCode) {
		this.benefitCode = benefitCode;
	}

	@Column(name="benefit_name")
	public String getBenefitName() {
		return benefitName;
	}

	public void setBenefitName(String benefitName) {
		this.benefitName = benefitName;
	}

	@Column(name="policy_number")
	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	@Column(name="case_category")
	public String getCaseCategory() {
		return caseCategory;
	}

	public void setCaseCategory(String caseCategory) {
		this.caseCategory = caseCategory;
	}

	@Column(name="benefit_rules")
	public String getBenefitRules() {
		return benefitRules;
	}

	public void setBenefitRules(String benefitRules) {
		this.benefitRules = benefitRules;
	}

	public Boolean getIsBenefitUpgradable() {
		return isBenefitUpgradable;
	}

	public void setIsBenefitUpgradable(Boolean isBenefitUpgradable) {
		this.isBenefitUpgradable = isBenefitUpgradable;
	}

	public Boolean getIsCostSharing() {
		return isCostSharing;
	}

	public void setIsCostSharing(Boolean isCostSharing) {
		this.isCostSharing = isCostSharing;
	}

	@Column(name="shared_benefit_code")
	public String getSharedBenefitCode() {
		return sharedBenefitCode;
	}

	public void setSharedBenefitCode(String sharedBenefitCode) {
		this.sharedBenefitCode = sharedBenefitCode;
	}

	
	
	
    
}