
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="policy_benefit")
public class PolicyBenefit implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	public static final int ITEM_BENEFIT_TYPE = 1;
	public static final int DIAGNOSIS_BENEFIT_TYPE = 2;
	public static final int DIAGNOSIS_SET_BENEFIT_TYPE = 3;
	public static final int PROCEDURE_BENEFIT_TYPE = 4;
	public static final int COVERAGE_BENEFIT_TYPE = 5;

	//Fields
		
	/**data for the column :
	
 --------- policy_benefit.policy_benefit_id --------- 
 schema        = null
 tableName     = policy_benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private Integer policyBenefitId;
			
	/**data for the column :
	
 --------- policy_benefit.policy_id --------- 
 schema        = null
 tableName     = policy_benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 2
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Policy policyId;
			
	/**data for the column :
	
 --------- policy_benefit.item_category_id --------- 
 schema        = null
 tableName     = policy_benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 3
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private ItemCategory itemCategoryId;
			
	/**data for the column :
	
 --------- policy_benefit.case_category_id --------- 
 schema        = null
 tableName     = policy_benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 4
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private CaseCategory caseCategoryId;
			
	/**data for the column :
	
 --------- policy_benefit.benefit_usage --------- 
 schema        = null
 tableName     = policy_benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double benefitUsage;
			
	/**data for the column :
	
 --------- policy_benefit.current_benefit_position --------- 
 schema        = null
 tableName     = policy_benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private Double currentBenefitPosition;
			
	/**data for the column :
	
 --------- policy_benefit.benefit_limit --------- 
 schema        = null
 tableName     = policy_benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private Double benefitLimit;
			
	/**data for the column :
	
 --------- policy_benefit.annual_benefit --------- 
 schema        = null
 tableName     = policy_benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double annualBenefit;
			
	/**data for the column :
	
 --------- policy_benefit.occurance_usage --------- 
 schema        = null
 tableName     = policy_benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer occuranceUsage;
			
	/**data for the column :
	
 --------- policy_benefit.max_occurance_per_member --------- 
 schema        = null
 tableName     = policy_benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Double maxOccurancePerMember;
			
	/**data for the column :
	
 --------- policy_benefit.max_usage_per_member --------- 
 schema        = null
 tableName     = policy_benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private Double maxUsagePerMember;
			
	/**data for the column :
	
 --------- policy_benefit.provider_contract --------- 
 schema        = null
 tableName     = policy_benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 1
 type          = -6 
 isPrimaryKey  = false

=========================================


*/
	private Integer providerContract;
			
	/**data for the column :
	
 --------- policy_benefit.is_cost_sharing --------- 
 schema        = null
 tableName     = policy_benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 1
 type          = -6 
 isPrimaryKey  = false

=========================================


*/
	private Integer isCostSharing;
			
	/**data for the column :
	
 --------- policy_benefit.cashless_percentage --------- 
 schema        = null
 tableName     = policy_benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 14
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double cashlessPercentage;
			
	/**data for the column :
	
 --------- policy_benefit.reimbursement_percentage --------- 
 schema        = null
 tableName     = policy_benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 15
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double reimbursementPercentage;
			
	/**data for the column :
	
 --------- policy_benefit.treatment_location --------- 
 schema        = null
 tableName     = policy_benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 16
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer treatmentLocation;
			
	/**data for the column :
	
 --------- policy_benefit.description --------- 
 schema        = null
 tableName     = policy_benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 17
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String description;
			
	/**data for the column :
	
 --------- policy_benefit.created_time --------- 
 schema        = null
 tableName     = policy_benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = on update CURRENT_TIMESTAMP
 defaultValue  = CURRENT_TIMESTAMP
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 18
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- policy_benefit.created_by --------- 
 schema        = null
 tableName     = policy_benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 19
 size          = 25
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- policy_benefit.modified_time --------- 
 schema        = null
 tableName     = policy_benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 20
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- policy_benefit.modified_by --------- 
 schema        = null
 tableName     = policy_benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 21
 size          = 25
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- policy_benefit.deleted_time --------- 
 schema        = null
 tableName     = policy_benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 22
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- policy_benefit.deleted_status --------- 
 schema        = null
 tableName     = policy_benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 23
 size          = 1
 type          = -6 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- policy_benefit.deleted_by --------- 
 schema        = null
 tableName     = policy_benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 24
 size          = 25
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- policy_benefit.status --------- 
 schema        = null
 tableName     = policy_benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 25
 size          = 1
 type          = -6 
 isPrimaryKey  = false

=========================================


*/
	private Integer status;
	
	private Diagnosis diagnosisId;
	private DiagnosisSet diagnosisSetId;
	private Procedure procedureId;
	private Integer benefitType;
	private Double deductibleLimit;
	private Double costSharingPercentage;
	private Double costSharingAmount;
	
	private Double reimbursementLimit;
	private Double maxOccurancePerCase;
	private BenefitUsageType benefitUsageType;
	
		

	// PK GETTER SETTER
			@Id
	@Column(name="policy_benefit_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getPolicyBenefitId(){
		return policyBenefitId;
	}
	public void setPolicyBenefitId(java.lang.Integer value){
		policyBenefitId = value;
	}
			// PK GETTER SETTER END

	@ManyToOne
	@JoinColumn(name="policy_id")
	public Policy getPolicyId(){
		return policyId;
	}
	public void setPolicyId(Policy value){
		policyId = value;
	}
	
	@ManyToOne
	@JoinColumn(name="item_category_id")
	public ItemCategory getItemCategoryId(){
		return itemCategoryId;
	}
	public void setItemCategoryId(ItemCategory value){
		itemCategoryId = value;
	}
	@ManyToOne
	@JoinColumn(name="case_category_id")
	public CaseCategory getCaseCategoryId(){
		return caseCategoryId;
	}
	public void setCaseCategoryId(CaseCategory value){
		caseCategoryId = value;
	}
				@Column(name="benefit_usage")
	public java.lang.Double getBenefitUsage(){
		return benefitUsage;
	}
	public void setBenefitUsage(java.lang.Double value){
		benefitUsage = value;
	}
				@Column(name="current_benefit_position")
	public java.lang.Double getCurrentBenefitPosition(){
		return currentBenefitPosition;
	}
	public void setCurrentBenefitPosition(java.lang.Double value){
		currentBenefitPosition = value;
	}
				@Column(name="benefit_limit")
	public java.lang.Double getBenefitLimit(){
		return benefitLimit;
	}
	public void setBenefitLimit(java.lang.Double value){
		benefitLimit = value;
	}
				@Column(name="annual_benefit")
	public java.lang.Double getAnnualBenefit(){
		return annualBenefit;
	}
	public void setAnnualBenefit(java.lang.Double value){
		annualBenefit = value;
	}
				@Column(name="occurance_usage")
	public java.lang.Integer getOccuranceUsage(){
		return occuranceUsage;
	}
	public void setOccuranceUsage(java.lang.Integer value){
		occuranceUsage = value;
	}
				@Column(name="max_occurance_per_member")
	public java.lang.Double getMaxOccurancePerMember(){
		return maxOccurancePerMember;
	}
	public void setMaxOccurancePerMember(java.lang.Double value){
		maxOccurancePerMember = value;
	}
				@Column(name="max_usage_per_member")
	public Double getMaxUsagePerMember(){
		return maxUsagePerMember;
	}
	public void setMaxUsagePerMember(Double value){
		maxUsagePerMember = value;
	}
				@Column(name="provider_contract")
	public java.lang.Integer getProviderContract(){
		return providerContract;
	}
	public void setProviderContract(java.lang.Integer value){
		providerContract = value;
	}
				@Column(name="is_cost_sharing")
	public java.lang.Integer getIsCostSharing(){
		return isCostSharing;
	}
	public void setIsCostSharing(java.lang.Integer value){
		isCostSharing = value;
	}
				@Column(name="cashless_percentage")
	public java.lang.Double getCashlessPercentage(){
		return cashlessPercentage;
	}
	public void setCashlessPercentage(java.lang.Double value){
		cashlessPercentage = value;
	}
				@Column(name="reimbursement_percentage")
	public java.lang.Double getReimbursementPercentage(){
		return reimbursementPercentage;
	}
	public void setReimbursementPercentage(java.lang.Double value){
		reimbursementPercentage = value;
	}
				@Column(name="treatment_location")
	public java.lang.Integer getTreatmentLocation(){
		return treatmentLocation;
	}
	public void setTreatmentLocation(java.lang.Integer value){
		treatmentLocation = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
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
				@Column(name="deleted_time")
	public java.sql.Timestamp getDeletedTime(){
		return deletedTime;
	}
	public void setDeletedTime(java.sql.Timestamp value){
		deletedTime = value;
	}
				@Column(name="deleted_status")
	public java.lang.Integer getDeletedStatus(){
		return deletedStatus;
	}
	public void setDeletedStatus(java.lang.Integer value){
		deletedStatus = value;
	}
				@Column(name="deleted_by")
	public java.lang.String getDeletedBy(){
		return deletedBy;
	}
	public void setDeletedBy(java.lang.String value){
		deletedBy = value;
	}
				@Column(name="status")
	public java.lang.Integer getStatus(){
		return status;
	}
	public void setStatus(java.lang.Integer value){
		status = value;
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
	@Column(name="benefit_type")
	public Integer getBenefitType() {
		return benefitType;
	}
	public void setBenefitType(Integer benefitType) {
		this.benefitType = benefitType;
	}
	@Column(name="deductible_limit")
	public Double getDeductibleLimit() {
		return deductibleLimit;
	}
	public void setDeductibleLimit(Double deductibleLimit) {
		this.deductibleLimit = deductibleLimit;
	}
	@Column(name="cost_sharing_percentage")
	public Double getCostSharingPercentage() {
		return costSharingPercentage;
	}
	public void setCostSharingPercentage(Double costSharingPercentage) {
		this.costSharingPercentage = costSharingPercentage;
	}
	@Column(name="cost_sharing_amount")
	public Double getCostSharingAmount() {
		return costSharingAmount;
	}
	public void setCostSharingAmount(Double costSharingAmount) {
		this.costSharingAmount = costSharingAmount;
	}
	@Column(name="reimbursement_limit")
	public Double getReimbursementLimit() {
		return reimbursementLimit;
	}
	public void setReimbursementLimit(Double reimbursementLimit) {
		this.reimbursementLimit = reimbursementLimit;
	}
	@Column(name="max_occurance_per_case")
	public Double getMaxOccurancePerCase() {
		return maxOccurancePerCase;
	}
	public void setMaxOccurancePerCase(Double maxOccurancePerCase) {
		this.maxOccurancePerCase = maxOccurancePerCase;
	}
	@ManyToOne
	@JoinColumn(name="benefit_usage_type")
	public BenefitUsageType getBenefitUsageType() {
		return benefitUsageType;
	}
	public void setBenefitUsageType(BenefitUsageType benefitUsageType) {
		this.benefitUsageType = benefitUsageType;
	}
		
	
	
	
}