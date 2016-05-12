
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="provider_type_diagnosis_treatment")
public class ProviderTypeDiagnosisTreatment implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- provider_type_diagnosis_treatment.provider_type_diagnosis_treament_id --------- 
 schema        = null
 tableName     = provider_type_diagnosis_treatment
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 15
 type          = 4 
 isPrimaryKey  = true

=========================================


*/
	private Integer providerTypeDiagnosisTreamentId;
			
	/**data for the column :
	
 --------- provider_type_diagnosis_treatment.provider_type_id --------- 
 schema        = null
 tableName     = provider_type_diagnosis_treatment
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 5
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private ProviderType providerTypeId;
			
	/**data for the column :
	
 --------- provider_type_diagnosis_treatment.diagnosis_id --------- 
 schema        = null
 tableName     = provider_type_diagnosis_treatment
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
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
	private Diagnosis diagnosisId;
			
	/**data for the column :
	
 --------- provider_type_diagnosis_treatment.description --------- 
 schema        = null
 tableName     = provider_type_diagnosis_treatment
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
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
	
 --------- provider_type_diagnosis_treatment.reference_note --------- 
 schema        = null
 tableName     = provider_type_diagnosis_treatment
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String referenceNote;
			
	/**data for the column :
	
 --------- provider_type_diagnosis_treatment.age --------- 
 schema        = null
 tableName     = provider_type_diagnosis_treatment
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
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
	private String age;
			
	/**data for the column :
	
 --------- provider_type_diagnosis_treatment.weight --------- 
 schema        = null
 tableName     = provider_type_diagnosis_treatment
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
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
	private String weight;
			
	/**data for the column :
	
 --------- provider_type_diagnosis_treatment.treatment_risk_id --------- 
 schema        = null
 tableName     = provider_type_diagnosis_treatment
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 3
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private TreatmentRisk treatmentRiskId;
			
	/**data for the column :
	
 --------- provider_type_diagnosis_treatment.treatment_class_id --------- 
 schema        = null
 tableName     = provider_type_diagnosis_treatment
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 4
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private TreatmentClass treatmentClassId;
			
	/**data for the column :
	
 --------- provider_type_diagnosis_treatment.alos --------- 
 schema        = null
 tableName     = provider_type_diagnosis_treatment
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double alos;
			
	/**data for the column :
	
 --------- provider_type_diagnosis_treatment.treatment_rate --------- 
 schema        = null
 tableName     = provider_type_diagnosis_treatment
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double treatmentRate;
			
	/**data for the column :
	
 --------- provider_type_diagnosis_treatment.created_time --------- 
 schema        = null
 tableName     = provider_type_diagnosis_treatment
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = on update CURRENT_TIMESTAMP
 defaultValue  = CURRENT_TIMESTAMP
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 12
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- provider_type_diagnosis_treatment.created_by --------- 
 schema        = null
 tableName     = provider_type_diagnosis_treatment
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- provider_type_diagnosis_treatment.modified_time --------- 
 schema        = null
 tableName     = provider_type_diagnosis_treatment
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 14
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- provider_type_diagnosis_treatment.modified_by --------- 
 schema        = null
 tableName     = provider_type_diagnosis_treatment
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 15
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- provider_type_diagnosis_treatment.deleted_time --------- 
 schema        = null
 tableName     = provider_type_diagnosis_treatment
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 16
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- provider_type_diagnosis_treatment.deleted_by --------- 
 schema        = null
 tableName     = provider_type_diagnosis_treatment
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 17
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- provider_type_diagnosis_treatment.deleted_status --------- 
 schema        = null
 tableName     = provider_type_diagnosis_treatment
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 18
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
	private CaseCategory caseCategoryId;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="provider_type_diagnosis_treament_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getProviderTypeDiagnosisTreamentId(){
		return providerTypeDiagnosisTreamentId;
	}
	public void setProviderTypeDiagnosisTreamentId(java.lang.Integer value){
		providerTypeDiagnosisTreamentId = value;
	}
			// PK GETTER SETTER END

	@ManyToOne
	@JoinColumn(name="provider_type_id")
	public ProviderType getProviderTypeId(){
		return providerTypeId;
	}
	public void setProviderTypeId(ProviderType value){
		providerTypeId = value;
	}
	
	@ManyToOne
	@JoinColumn(name="diagnosis_id")
	public Diagnosis getDiagnosisId(){
		return diagnosisId;
	}
	public void setDiagnosisId(Diagnosis value){
		diagnosisId = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
				@Column(name="reference_note")
	public java.lang.String getReferenceNote(){
		return referenceNote;
	}
	public void setReferenceNote(java.lang.String value){
		referenceNote = value;
	}
				@Column(name="age")
	public java.lang.String getAge(){
		return age;
	}
	public void setAge(java.lang.String value){
		age = value;
	}
				@Column(name="weight")
	public java.lang.String getWeight(){
		return weight;
	}
	public void setWeight(java.lang.String value){
		weight = value;
	}
	@ManyToOne
	@JoinColumn(name="treatment_risk_id")
	public TreatmentRisk getTreatmentRiskId(){
		return treatmentRiskId;
	}
	public void setTreatmentRiskId(TreatmentRisk value){
		treatmentRiskId = value;
	}
	@ManyToOne
	@JoinColumn(name="treatment_class_id")
	public TreatmentClass getTreatmentClassId(){
		return treatmentClassId;
	}
	public void setTreatmentClassId(TreatmentClass value){
		treatmentClassId = value;
	}
				@Column(name="alos")
	public java.lang.Double getAlos(){
		return alos;
	}
	public void setAlos(java.lang.Double value){
		alos = value;
	}
				@Column(name="treatment_rate")
	public java.lang.Double getTreatmentRate(){
		return treatmentRate;
	}
	public void setTreatmentRate(java.lang.Double value){
		treatmentRate = value;
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
	@ManyToOne
	@JoinColumn(name="case_category_id")
	public CaseCategory getCaseCategoryId() {
		return caseCategoryId;
	}
	public void setCaseCategoryId(CaseCategory caseCategoryId) {
		this.caseCategoryId = caseCategoryId;
	}
	
	



}