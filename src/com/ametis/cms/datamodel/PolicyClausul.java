
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="policy_clausul")
public class PolicyClausul implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	public static final int MA_MAX_CHILDREN = 1;

	//Fields
		
	/**data for the column :
	
 --------- policy_clausul.id --------- 
 schema        = null
 tableName     = policy_clausul
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
	private Integer id;
			
	/**data for the column :
	
 --------- policy_clausul.policy_id --------- 
 schema        = null
 tableName     = policy_clausul
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 11
 type          = -5 
 isPrimaryKey  = false

=========================================


*/
	private Policy policyId;
			
	/**data for the column :
	
 --------- policy_clausul.clausul_type --------- 
 schema        = null
 tableName     = policy_clausul
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 2
 type          = -6 
 isPrimaryKey  = false

=========================================


*/
	private Integer clausulType;
			
	/**data for the column :
	
 --------- policy_clausul.clausul_file_location --------- 
 schema        = null
 tableName     = policy_clausul
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private String clausulFileLocation;
			
	/**data for the column :
	
 --------- policy_clausul.created_time --------- 
 schema        = null
 tableName     = policy_clausul
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	
 --------- policy_clausul.created_by --------- 
 schema        = null
 tableName     = policy_clausul
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 30
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- policy_clausul.modified_time --------- 
 schema        = null
 tableName     = policy_clausul
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- policy_clausul.modified_by --------- 
 schema        = null
 tableName     = policy_clausul
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 30
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- policy_clausul.deleted_time --------- 
 schema        = null
 tableName     = policy_clausul
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- policy_clausul.deleted_by --------- 
 schema        = null
 tableName     = policy_clausul
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 30
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- policy_clausul.deleted_status --------- 
 schema        = null
 tableName     = policy_clausul
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 2
 type          = -6 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- policy_clausul.status --------- 
 schema        = null
 tableName     = policy_clausul
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 5
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer status;
			
	/**data for the column :
	
 --------- policy_clausul.clausul_id --------- 
 schema        = null
 tableName     = policy_clausul
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Clausul clausulId;
	private ItemCategory itemCategoryId;
	private CaseCategory caseCategoryId;
	private String itemName;
	private String diagnosisCode;
	private String diagnosisName;
	private Diagnosis diagnosisId;
	private Procedure procedureId;
	private Integer decision;
	private Integer isEdc;
	private String procedureName;
	private String procedureCode;
	private TreatmentLocation locationId;
	private Integer pembebananBenefit;
	private Double benefitAmount;
	private Integer benefitParameter;
	private Double age;
	private Integer ageParameter;
	private Double benefitReductionPercentage;
	private Integer benefitReductionSubject;
	private Integer benefitReductionType;
	private Integer multiplierCalculationType;
	private TreatmentUpgradeType treatmentUpgradeType;
	private String description;
	private ClaimType claimTreatmentTypeId;
	private Integer benefitIteration;
	private Integer iterationType;
	
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getId(){
		return id;
	}
	public void setId(java.lang.Integer value){
		id = value;
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
				@Column(name="clausul_type")
	public java.lang.Integer getClausulType(){
		return clausulType;
	}
	public void setClausulType(java.lang.Integer value){
		clausulType = value;
	}
				@Column(name="clausul_file_location")
	public java.lang.String getClausulFileLocation(){
		return clausulFileLocation;
	}
	public void setClausulFileLocation(java.lang.String value){
		clausulFileLocation = value;
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
				@Column(name="deleted_by")
	public java.lang.String getDeletedBy(){
		return deletedBy;
	}
	public void setDeletedBy(java.lang.String value){
		deletedBy = value;
	}
				@Column(name="deleted_status")
	public java.lang.Integer getDeletedStatus(){
		return deletedStatus;
	}
	public void setDeletedStatus(java.lang.Integer value){
		deletedStatus = value;
	}
				@Column(name="status")
	public java.lang.Integer getStatus(){
		return status;
	}
	public void setStatus(java.lang.Integer value){
		status = value;
	}
	
	@ManyToOne
	@JoinColumn(name="clausul_id")
	public Clausul getClausulId(){
		return clausulId;
	}
	public void setClausulId(Clausul value){
		clausulId = value;
	}
	@ManyToOne
	@JoinColumn(name="item_category_id")
	public ItemCategory getItemCategoryId() {
		return itemCategoryId;
	}
	public void setItemCategoryId(ItemCategory itemCategoryId) {
		this.itemCategoryId = itemCategoryId;
	
	}
	@ManyToOne
	@JoinColumn(name="case_category_id")
	public CaseCategory getCaseCategoryId() {
		return caseCategoryId;
	}
	public void setCaseCategoryId(CaseCategory caseCategoryId) {
		this.caseCategoryId = caseCategoryId;
	}
	@Column(name="item_name")
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	@Column(name="diagnosis_code")
	public String getDiagnosisCode() {
		return diagnosisCode;
	}
	public void setDiagnosisCode(String diagnosisCode) {
		this.diagnosisCode = diagnosisCode;
	}
	@Column(name="diagnosis_name")
	public String getDiagnosisName() {
		return diagnosisName;
	}
	public void setDiagnosisName(String diagnosisName) {
		this.diagnosisName = diagnosisName;
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
	@JoinColumn(name="procedure_id")
	public Procedure getProcedureId() {
		return procedureId;
	}
	public void setProcedureId(Procedure procedureId) {
		this.procedureId = procedureId;
	}
	@Column(name="decision")
	public Integer getDecision() {
		return decision;
	}
	public void setDecision(Integer decision) {
		this.decision = decision;
	}
	@Column(name="is_edc")
	public Integer getIsEdc() {
		return isEdc;
	}
	public void setIsEdc(Integer isEdc) {
		this.isEdc = isEdc;
	}
	@Column(name="procedure_name")
	public String getProcedureName() {
		return procedureName;
	}
	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}
	@Column(name="procedure_code")
	public String getProcedureCode() {
		return procedureCode;
	}
	public void setProcedureCode(String procedureCode) {
		this.procedureCode = procedureCode;
	}
	
	@ManyToOne
	@JoinColumn (name="treatment_location_id")	
	public TreatmentLocation getLocationId() {
		return locationId;
	}
	public void setLocationId(TreatmentLocation locationId) {
		this.locationId = locationId;
	}
	@Column(name="pembebanan_benefit")
	public Integer getPembebananBenefit() {
		return pembebananBenefit;
	}
	public void setPembebananBenefit(Integer pembebananBenefit) {
		this.pembebananBenefit = pembebananBenefit;
	}
	@Column(name="benefit_amount")
	public Double getBenefitAmount() {
		return benefitAmount;
	}
	public void setBenefitAmount(Double benefitAmount) {
		this.benefitAmount = benefitAmount;
	}
	@Column(name="benefit_parameter")
	public Integer getBenefitParameter() {
		return benefitParameter;
	}
	public void setBenefitParameter(Integer benefitParameter) {
		this.benefitParameter = benefitParameter;
	}
	@Column(name="age")
	public Double getAge() {
		return age;
	}
	public void setAge(Double age) {
		this.age = age;
	}
	@Column(name="age_parameter")
	public Integer getAgeParameter() {
		return ageParameter;
	}
	public void setAgeParameter(Integer ageParameter) {
		this.ageParameter = ageParameter;
	}
	@Column(name="benefit_reduction_percentage")
	public Double getBenefitReductionPercentage() {
		return benefitReductionPercentage;
	}
	public void setBenefitReductionPercentage(Double benefitReductionPercentage) {
		this.benefitReductionPercentage = benefitReductionPercentage;
	}
	@Column(name="benefit_reduction_subject")
	public Integer getBenefitReductionSubject() {
		return benefitReductionSubject;
	}
	public void setBenefitReductionSubject(Integer benefitReductionSubject) {
		this.benefitReductionSubject = benefitReductionSubject;
	}
	@Column(name="benefit_reduction_type")
	public Integer getBenefitReductionType() {
		return benefitReductionType;
	}
	public void setBenefitReductionType(Integer benefitReductionType) {
		this.benefitReductionType = benefitReductionType;
	}
	@Column(name="multiplier_calculation_type")
	public Integer getMultiplierCalculationType() {
		return multiplierCalculationType;
	}
	public void setMultiplierCalculationType(Integer multiplierCalculationType) {
		this.multiplierCalculationType = multiplierCalculationType;
	}
	@ManyToOne
	@JoinColumn(name="treatment_upgrade_type")
	public TreatmentUpgradeType getTreatmentUpgradeType() {
		return treatmentUpgradeType;
	}
	public void setTreatmentUpgradeType(TreatmentUpgradeType treatmentUpgradeType) {
		this.treatmentUpgradeType = treatmentUpgradeType;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@ManyToOne
	@JoinColumn(name="claim_treatment_type_id")
	public ClaimType getClaimTreatmentTypeId() {
		return claimTreatmentTypeId;
	}
	public void setClaimTreatmentTypeId(ClaimType claimTreatmentTypeId) {
		this.claimTreatmentTypeId = claimTreatmentTypeId;
	}
	@Column(name="benefit_iteration")
	public Integer getBenefitIteration() {
		return benefitIteration;
	}
	public void setBenefitIteration(Integer benefitIteration) {
		this.benefitIteration = benefitIteration;
	}
	@Column(name="iteration_type")
	public Integer getIterationType() {
		return iterationType;
	}
	public void setIterationType(Integer iterationType) {
		this.iterationType = iterationType;
	}
		
	
	


}