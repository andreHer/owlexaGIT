
package com.ametis.cms.datamodel;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="product_clausul")
public class ProductClausul implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- product_clausul.product_clausul_id --------- 
 schema        = null
 tableName     = product_clausul
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
	private Integer productClausulId;
									
	/**data for the column :
	
 --------- product_clausul.description --------- 
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
 ordinal       = 4
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String description;
			
	/**data for the column :
	
 --------- product_clausul.created_time --------- 
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
 ordinal       = 5
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- product_clausul.created_by --------- 
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
 ordinal       = 6
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- product_clausul.deleted_time --------- 
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
 ordinal       = 7
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- product_clausul.deleted_by --------- 
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
 ordinal       = 8
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- product_clausul.modified_time --------- 
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
 ordinal       = 9
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- product_clausul.modified_by --------- 
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
 ordinal       = 10
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- product_clausul.deleted_status --------- 
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
 ordinal       = 11
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
		
	// foreign affairs
	
			/** Product
	data for the foreign class :
	
 --------- product.product_id --------- 
 schema        = null
 tableName     = product
 foreignCol    = product_id
 foreignTab    = product_clausul
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
	private Product productId;
				/** Clausul
	data for the foreign class :
	
 --------- clausul.clausul_id --------- 
 schema        = null
 tableName     = clausul
 foreignCol    = clausul_id
 foreignTab    = product_clausul
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
	private Clausul clausulId;
	private String diagnosisCode;
	private Double umur;
	private Integer parameterUmur;
	private Integer decision;
	private Integer pembebananBenefit;
	private Double benefitAmount;
	private Integer benefitParameter;
	
	private TreatmentUpgradeType treatmentUpgradeType;
	
	private Integer reductionType; // fix percentage, prorate, normal benefit
	private Integer reductionSubject; // total charge, ROOM Excluded
	private Double benefitReductionPercentage;
	
	private Integer isAnnualBenefitClausul;
	private Integer isPerdisabilityClausul;
	private Integer isDailyClausul;
	
	private String clausulName;
	private Integer clausulLayer;
	private Integer clausulType;
	
			
	private String diagnosisName;
	private Item itemId;
	private String itemName;
	private Diagnosis diagnosisId;
	
	private DiagnosisSet diagnosisSetId;
	private String diagnosisSetName;
	
	private Integer multiplierCalculationType;
	private Integer isDiagnosis;
	private Procedure procedureId;
	private String procedureName;
	private String url;
	private String type;
	private Integer statusUpdate;
	private ProductLayerLimit productLayerLimitId;
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="product_clausul_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getProductClausulId(){
		return productClausulId;
	}
	public void setProductClausulId(java.lang.Integer value){
		productClausulId = value;
	}
			// PK GETTER SETTER END

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
	
			/** Product */
	@ManyToOne
	@JoinColumn(name="product_id")
	public Product getProductId(){
		return this.productId;
	}

	/** Product */
	public void setProductId(Product obj){
		this.productId = obj;
	}
				/** Clausul */
	@ManyToOne
	@JoinColumn(name="clausul_id")
	public Clausul getClausulId(){
		return this.clausulId;
	}

	/** Clausul */
	public void setClausulId(Clausul obj){
		this.clausulId = obj;
	}
	@Column(name="diagnosis_code")
	public String getDiagnosisCode() {
		return diagnosisCode;
	}
	public void setDiagnosisCode(String diagnosisCode) {
		this.diagnosisCode = diagnosisCode;
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
	@Column(name="diagnosis_name")
	public String getDiagnosisName() {
		return diagnosisName;
	}
	public void setDiagnosisName(String diagnosisName) {
		this.diagnosisName = diagnosisName;
	}
	@ManyToOne
	@JoinColumn(name="item_id")
	public Item getItemId() {
		return itemId;
	}
	public void setItemId(Item itemId) {
		this.itemId = itemId;
	}
	@Column(name="item_name")
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	@ManyToOne
	@JoinColumn(name="treatment_upgrade_type")
	public TreatmentUpgradeType getTreatmentUpgradeType() {
		return treatmentUpgradeType;
	}
	public void setTreatmentUpgradeType(TreatmentUpgradeType treatmentUpgradeType) {
		this.treatmentUpgradeType = treatmentUpgradeType;
	}
	@Column(name="benefit_reduction_type")
	public Integer getReductionType() {
		return reductionType;
	}
	public void setReductionType(Integer reductionType) {
		this.reductionType = reductionType;
	}
	@Column(name="benefit_reduction_subject")
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
	@JoinColumn(name="diagnosis_id")
	public Diagnosis getDiagnosisId() {
		return diagnosisId;
	}
	public void setDiagnosisId(Diagnosis diagnosisId) {
		this.diagnosisId = diagnosisId;
	}
	@Column(name="multiplier_calculation_type")
	public Integer getMultiplierCalculationType() {
		return multiplierCalculationType;
	}
	public void setMultiplierCalculationType(Integer multiplierCalculationType) {
		this.multiplierCalculationType = multiplierCalculationType;
	}
	@Column(name="is_diagnosis")
	public Integer getIsDiagnosis() {
		return isDiagnosis;
	}
	public void setIsDiagnosis(Integer isDiagnosis) {
		this.isDiagnosis = isDiagnosis;
	}
	@ManyToOne
	@JoinColumn(name="procedure_id")
	public Procedure getProcedureId() {
		return procedureId;
	}
	public void setProcedureId(Procedure procedureId) {
		this.procedureId = procedureId;
	}
	@Column(name="procedure_name")
	public String getProcedureName() {
		return procedureName;
	}
	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}
	@Column(name="url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name="type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name="status_update")
	public Integer getStatusUpdate() {
		return statusUpdate;
	}
	public void setStatusUpdate(Integer statusUpdate) {
		this.statusUpdate = statusUpdate;
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
	@JoinColumn(name="product_layer_limit_id")
	public ProductLayerLimit getProductLayerLimitId() {
		return productLayerLimitId;
	}
	public void setProductLayerLimitId(ProductLayerLimit productLayerLimitId) {
		this.productLayerLimitId = productLayerLimitId;
	}
			
	

}