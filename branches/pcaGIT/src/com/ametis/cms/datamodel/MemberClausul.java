
package com.ametis.cms.datamodel;


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
@Table(name="member_clausul")
public class MemberClausul implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- member_clausul.member_clausul_id --------- 
 schema        = null
 tableName     = member_clausul
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
	private Integer memberClausulId;
			
	/**data for the column :
	
 --------- member_clausul.member_id --------- 
 schema        = null
 tableName     = member_clausul
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

=========================================


*/
	private Member memberId;
			
	/**data for the column :
	
 --------- member_clausul.clausul_id --------- 
 schema        = null
 tableName     = member_clausul
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

=========================================


*/
	private Clausul clausulId;
			
	/**data for the column :
	
 --------- member_clausul.created_time --------- 
 schema        = null
 tableName     = member_clausul
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- member_clausul.created_by --------- 
 schema        = null
 tableName     = member_clausul
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- member_clausul.deleted_time --------- 
 schema        = null
 tableName     = member_clausul
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- member_clausul.deleted_by --------- 
 schema        = null
 tableName     = member_clausul
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- member_clausul.modified_time --------- 
 schema        = null
 tableName     = member_clausul
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- member_clausul.modified_by --------- 
 schema        = null
 tableName     = member_clausul
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- member_clausul.deleted_status --------- 
 schema        = null
 tableName     = member_clausul
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private Integer deletedStatus;
		
	private String information;
	
	private Diagnosis diagnosisId;
	private Double umur;
	private Integer parameterUmur;
	private Integer decision;
	
	private Double benefitAmount;
	private Integer benefitParameter;
	
	private TreatmentUpgradeType treatmentUpgradeType;
	
	private Integer reductionType; // fix percentage, prorate, normal benefit
	private Integer reductionSubject; // total charge, ROOM Excluded
	private Double benefitReductionPercentage;
	
	private CaseCategory caseCategoryId;
	private MemberProduct memberProductId;
	private Integer multiplierCalculationType;
	private Integer memberClausulStatus;
	
	private Double benefitUsage;

	private Integer isAnnualBenefitClausul;

	private Integer isPerdisabilityClausul;

	private Integer isDailyClausul;

	private String clausulName;

	private Integer clausulLayer;

	private Integer clausulType;

	private DiagnosisSet diagnosisSetId;

	private String diagnosisSetName;
	
	private MemberLimitLayer memberLimitLayerId;
			
	
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="member_clausul_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getMemberClausulId(){
		return memberClausulId;
	}
	public void setMemberClausulId(java.lang.Integer value){
		memberClausulId = value;
	}
			// PK GETTER SETTER END

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="member_id")
	public Member getMemberId(){
		return memberId;
	}
	public void setMemberId(Member value){
		memberId = value;
	}
	@ManyToOne
	@JoinColumn(name="clausul_id")
	public Clausul getClausulId(){
		return clausulId;
	}
	public void setClausulId(Clausul value){
		clausulId = value;
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
	@Column(name="information")
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	@ManyToOne
	@JoinColumn(name="diagnosis_id")
	public Diagnosis getDiagnosisId() {
		return diagnosisId;
	}
	public void setDiagnosisId(Diagnosis diagnosisCode) {
		this.diagnosisId = diagnosisCode;
	}
	@Column(name="umur")
	public Double getUmur() {
		return umur;
	}
	public void setUmur(Double umur) {
		this.umur = umur;
	}
	@Column(name="parameter_umur")
	public Integer getParameterUmur() {
		return parameterUmur;
	}
	public void setParameterUmur(Integer parameterUmur) {
		this.parameterUmur = parameterUmur;
	}
	@Column(name="decision")
	public Integer getDecision() {
		return decision;
	}
	public void setDecision(Integer decision) {
		this.decision = decision;
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
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="treatment_upgrade_type")
	public TreatmentUpgradeType getTreatmentUpgradeType() {
		return treatmentUpgradeType;
	}
	public void setTreatmentUpgradeType(TreatmentUpgradeType treatmentUpgradeType) {
		this.treatmentUpgradeType = treatmentUpgradeType;
	}
	@Column(name="reduction_type")
	public Integer getReductionType() {
		return reductionType;
	}
	public void setReductionType(Integer reductionType) {
		this.reductionType = reductionType;
	}
	@Column(name="reduction_subject")
	public Integer getReductionSubject() {
		return reductionSubject;
	}
	public void setReductionSubject(Integer reductionSubject) {
		this.reductionSubject = reductionSubject;
	}
	@Column(name="benefit_reduction_percentage")
	public Double getBenefitReductionPercentage() {
		return benefitReductionPercentage;
	}
	public void setBenefitReductionPercentage(Double benefitReductionPercentage) {
		this.benefitReductionPercentage = benefitReductionPercentage;
	}
	@ManyToOne
	@JoinColumn(name="case_category_id")
	public CaseCategory getCaseCategoryId() {
		return caseCategoryId;
	}
	public void setCaseCategoryId(CaseCategory caseCategoryId) {
		this.caseCategoryId = caseCategoryId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="member_product_id")
	public MemberProduct getMemberProductId() {
		return memberProductId;
	}
	public void setMemberProductId(MemberProduct memberProductId) {
		this.memberProductId = memberProductId;
	}
	@Column(name="multiplier_calculation_type")
	public Integer getMultiplierCalculationType() {
		return multiplierCalculationType;
	}
	public void setMultiplierCalculationType(Integer multiplierCalculationType) {
		this.multiplierCalculationType = multiplierCalculationType;
	}
	@Column(name="member_clausul_status")
	public Integer getMemberClausulStatus() {
		return memberClausulStatus;
	}
	public void setMemberClausulStatus(Integer memberClausulStatus) {
		this.memberClausulStatus = memberClausulStatus;
	}

	
	@Column(name="is_annual_benefit_clausul")
	public Integer getIsAnnualBenefitClausul() {
		return isAnnualBenefitClausul;
	}
	public void setIsAnnualBenefitClausul(Integer isAnnualBenefitClausul) {
		this.isAnnualBenefitClausul = isAnnualBenefitClausul;
	}
	@Column(name="is_perdisability_clausul")
	public Integer getIsPerdisabilityClausul() {
		return isPerdisabilityClausul;
	}
	public void setIsPerdisabilityClausul(Integer isPerdisabilityClausul) {
		this.isPerdisabilityClausul = isPerdisabilityClausul;
	}
	@Column(name="is_daily_clausul")
	public Integer getIsDailyClausul() {
		return isDailyClausul;
	}
	public void setIsDailyClausul(Integer isDailyClausul) {
		this.isDailyClausul = isDailyClausul;
	}
	@Column(name="clausul_name")
	public String getClausulName() {
		return clausulName;
	}
	public void setClausulName(String clausulName) {
		this.clausulName = clausulName;
	}
	@Column(name="clausul_layer")
	public Integer getClausulLayer() {
		return clausulLayer;
	}
	public void setClausulLayer(Integer clausulLayer) {
		this.clausulLayer = clausulLayer;
	}
	@Column(name="clausul_type")
	public Integer getClausulType() {
		return clausulType;
	}
	public void setClausulType(Integer clausulType) {
		this.clausulType = clausulType;
	}
	@ManyToOne
	@JoinColumn(name="diagnosis_set_id")
	public DiagnosisSet getDiagnosisSetId() {
		return diagnosisSetId;
	}
	public void setDiagnosisSetId(DiagnosisSet diagnosisSetId) {
		this.diagnosisSetId = diagnosisSetId;
	}
	@Column(name="diagnosis_set_name")
	public String getDiagnosisSetName() {
		return diagnosisSetName;
	}
	public void setDiagnosisSetName(String diagnosisSetName) {
		this.diagnosisSetName = diagnosisSetName;
	}
	@ManyToOne
	@JoinColumn(name="member_limit_layer_id")
	public MemberLimitLayer getMemberLimitLayerId() {
		return memberLimitLayerId;
	}
	public void setMemberLimitLayerId(MemberLimitLayer memberLimitLayerId) {
		this.memberLimitLayerId = memberLimitLayerId;
	}
			
	


}