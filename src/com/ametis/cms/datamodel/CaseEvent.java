
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="case_event")
public class CaseEvent implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- case_event.case_event_id --------- 
 schema        = null
 tableName     = case_event
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
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
	private Integer caseEventId;
			
	/**data for the column :
	
 --------- case_event.description --------- 
 schema        = null
 tableName     = case_event
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
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
	private String description;
			
	/**data for the column :
	
 --------- case_event.deleted_status --------- 
 schema        = null
 tableName     = case_event
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
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
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- case_event.modified_time --------- 
 schema        = null
 tableName     = case_event
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
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
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- case_event.modified_by --------- 
 schema        = null
 tableName     = case_event
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- case_event.deleted_by --------- 
 schema        = null
 tableName     = case_event
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- case_event.deleted_time --------- 
 schema        = null
 tableName     = case_event
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
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
	
 --------- case_event.created_time --------- 
 schema        = null
 tableName     = case_event
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
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
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- case_event.created_by --------- 
 schema        = null
 tableName     = case_event
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- case_event.remarks --------- 
 schema        = null
 tableName     = case_event
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String remarks;
			
	/**data for the column :
	
 --------- case_event.therapy --------- 
 schema        = null
 tableName     = case_event
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String therapy;
			
	/**data for the column :
	
 --------- case_event.vital_sign --------- 
 schema        = null
 tableName     = case_event
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String vitalSign;
			
	/**data for the column :
	
 --------- case_event.doctor_consult --------- 
 schema        = null
 tableName     = case_event
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String doctorConsult;
			
	/**data for the column :
	
 --------- case_event.nurse --------- 
 schema        = null
 tableName     = case_event
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 14
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String nurse;
			
	/**data for the column :
	
 --------- case_event.monitored_by --------- 
 schema        = null
 tableName     = case_event
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 15
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String monitoredBy;
			
	/**data for the column :
	
 --------- case_event.monitoring_time --------- 
 schema        = null
 tableName     = case_event
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 16
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp monitoringTime;
			
	/**data for the column :
	
 --------- case_event.provider_contact --------- 
 schema        = null
 tableName     = case_event
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 17
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String providerContact;
			
	/**data for the column :
	
 --------- case_event.diagnosis_aspect --------- 
 schema        = null
 tableName     = case_event
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 18
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String diagnosisAspect;
			
	/**data for the column :
	
 --------- case_event.cost_estimation --------- 
 schema        = null
 tableName     = case_event
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 19
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double costEstimation;
			
	/**data for the column :
	
 --------- case_event.procedure_plan --------- 
 schema        = null
 tableName     = case_event
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 20
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String procedurePlan;
									
	/**data for the column :
	
 --------- case_event.ekg --------- 
 schema        = null
 tableName     = case_event
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 23
 size          = 250
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String ekg;
			
	/**data for the column :
	
 --------- case_event.impression --------- 
 schema        = null
 tableName     = case_event
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 24
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String impression;
			
	/**data for the column :
	
 --------- case_event.subjective --------- 
 schema        = null
 tableName     = case_event
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 25
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String subjective;
			
	/**data for the column :
	
 --------- case_event.objective --------- 
 schema        = null
 tableName     = case_event
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 26
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String objective;
			
	/**data for the column :
	
 --------- case_event.assesment --------- 
 schema        = null
 tableName     = case_event
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 27
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String assesment;
			
	/**data for the column :
	
 --------- case_event.plan --------- 
 schema        = null
 tableName     = case_event
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 28
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String plan;
			
	/**data for the column :
	
 --------- case_event.chief_complaint --------- 
 schema        = null
 tableName     = case_event
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 29
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String chiefComplaint;
			
	/**data for the column :
	
 --------- case_event.current_medication --------- 
 schema        = null
 tableName     = case_event
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 30
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String currentMedication;
			
	/**data for the column :
	
 --------- case_event.allergies --------- 
 schema        = null
 tableName     = case_event
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 31
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String allergies;
			
	/**data for the column :
	
 --------- case_event.physical_examination --------- 
 schema        = null
 tableName     = case_event
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 32
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String physicalExamination;
			
	/**data for the column :
	
 --------- case_event.diagnostic_testing --------- 
 schema        = null
 tableName     = case_event
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 33
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String diagnosticTesting;
			
	/**data for the column :
	
 --------- case_event.family_history --------- 
 schema        = null
 tableName     = case_event
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 34
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String familyHistory;
			
	/**data for the column :
	
 --------- case_event.social_history --------- 
 schema        = null
 tableName     = case_event
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 35
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String socialHistory;
			
	/**data for the column :
	
 --------- case_event.past_medical_history --------- 
 schema        = null
 tableName     = case_event
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 36
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String pastMedicalHistory;
			
	/**data for the column :
	
 --------- case_event.past_surgical_history --------- 
 schema        = null
 tableName     = case_event
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 37
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String pastSurgicalHistory;
		
	// foreign affairs
	
			/** EventCategory
	data for the foreign class :
	
 --------- event_category.event_category_id --------- 
 schema        = null
 tableName     = event_category
 foreignCol    = event_category_id
 foreignTab    = case_event
 catalog       = qsmart-uat
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
	private EventCategory eventCategoryId;
				/** TbCase
	data for the foreign class :
	
 --------- tb_case.case_id --------- 
 schema        = null
 tableName     = tb_case
 foreignCol    = case_id
 foreignTab    = case_event
 catalog       = qsmart-uat
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
	private Case caseId;
	private String initialDiagnosis;
			
	// -- foreign affairs end

	// -- exported key

	
			/** CaseItem
	data for the exported class :
	
 --------- case_item.case_event_id --------- 
 schema        = null
 tableName     = case_item
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 15
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = case_event.case_event_id

=========================================



	 */
	private Set <CaseItem> caseItems = new HashSet(0);
			
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="case_event_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getCaseEventId(){
		return caseEventId;
	}
	public void setCaseEventId(java.lang.Integer value){
		caseEventId = value;
	}
			// PK GETTER SETTER END

						@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
				@Column(name="deleted_status")
	public java.lang.Integer getDeletedStatus(){
		return deletedStatus;
	}
	public void setDeletedStatus(java.lang.Integer value){
		deletedStatus = value;
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
				@Column(name="deleted_by")
	public java.lang.String getDeletedBy(){
		return deletedBy;
	}
	public void setDeletedBy(java.lang.String value){
		deletedBy = value;
	}
				@Column(name="deleted_time")
	public java.sql.Timestamp getDeletedTime(){
		return deletedTime;
	}
	public void setDeletedTime(java.sql.Timestamp value){
		deletedTime = value;
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
				@Column(name="remarks")
	public java.lang.String getRemarks(){
		return remarks;
	}
	public void setRemarks(java.lang.String value){
		remarks = value;
	}
				@Column(name="therapy")
	public java.lang.String getTherapy(){
		return therapy;
	}
	public void setTherapy(java.lang.String value){
		therapy = value;
	}
				@Column(name="vital_sign")
	public java.lang.String getVitalSign(){
		return vitalSign;
	}
	public void setVitalSign(java.lang.String value){
		vitalSign = value;
	}
				@Column(name="doctor_consult")
	public java.lang.String getDoctorConsult(){
		return doctorConsult;
	}
	public void setDoctorConsult(java.lang.String value){
		doctorConsult = value;
	}
				@Column(name="nurse")
	public java.lang.String getNurse(){
		return nurse;
	}
	public void setNurse(java.lang.String value){
		nurse = value;
	}
				@Column(name="monitored_by")
	public java.lang.String getMonitoredBy(){
		return monitoredBy;
	}
	public void setMonitoredBy(java.lang.String value){
		monitoredBy = value;
	}
				@Column(name="monitoring_time")
	public java.sql.Timestamp getMonitoringTime(){
		return monitoringTime;
	}
	public void setMonitoringTime(java.sql.Timestamp value){
		monitoringTime = value;
	}
				@Column(name="provider_contact")
	public java.lang.String getProviderContact(){
		return providerContact;
	}
	public void setProviderContact(java.lang.String value){
		providerContact = value;
	}
				@Column(name="diagnosis_aspect")
	public java.lang.String getDiagnosisAspect(){
		return diagnosisAspect;
	}
	public void setDiagnosisAspect(java.lang.String value){
		diagnosisAspect = value;
	}
				@Column(name="cost_estimation")
	public java.lang.Double getCostEstimation(){
		return costEstimation;
	}
	public void setCostEstimation(java.lang.Double value){
		costEstimation = value;
	}
				@Column(name="procedure_plan")
	public java.lang.String getProcedurePlan(){
		return procedurePlan;
	}
	public void setProcedurePlan(java.lang.String value){
		procedurePlan = value;
	}
										@Column(name="ekg")
	public java.lang.String getEkg(){
		return ekg;
	}
	public void setEkg(java.lang.String value){
		ekg = value;
	}
				@Column(name="impression")
	public java.lang.String getImpression(){
		return impression;
	}
	public void setImpression(java.lang.String value){
		impression = value;
	}
				@Column(name="subjective")
	public java.lang.String getSubjective(){
		return subjective;
	}
	public void setSubjective(java.lang.String value){
		subjective = value;
	}
				@Column(name="objective")
	public java.lang.String getObjective(){
		return objective;
	}
	public void setObjective(java.lang.String value){
		objective = value;
	}
				@Column(name="assesment")
	public java.lang.String getAssesment(){
		return assesment;
	}
	public void setAssesment(java.lang.String value){
		assesment = value;
	}
				@Column(name="monitoring_plan")
	public java.lang.String getPlan(){
		return plan;
	}
	public void setPlan(java.lang.String value){
		plan = value;
	}
				@Column(name="chief_complaint")
	public java.lang.String getChiefComplaint(){
		return chiefComplaint;
	}
	public void setChiefComplaint(java.lang.String value){
		chiefComplaint = value;
	}
				@Column(name="current_medication")
	public java.lang.String getCurrentMedication(){
		return currentMedication;
	}
	public void setCurrentMedication(java.lang.String value){
		currentMedication = value;
	}
				@Column(name="allergies")
	public java.lang.String getAllergies(){
		return allergies;
	}
	public void setAllergies(java.lang.String value){
		allergies = value;
	}
				@Column(name="physical_examination")
	public java.lang.String getPhysicalExamination(){
		return physicalExamination;
	}
	public void setPhysicalExamination(java.lang.String value){
		physicalExamination = value;
	}
				@Column(name="diagnostic_testing")
	public java.lang.String getDiagnosticTesting(){
		return diagnosticTesting;
	}
	public void setDiagnosticTesting(java.lang.String value){
		diagnosticTesting = value;
	}
				@Column(name="family_history")
	public java.lang.String getFamilyHistory(){
		return familyHistory;
	}
	public void setFamilyHistory(java.lang.String value){
		familyHistory = value;
	}
				@Column(name="social_history")
	public java.lang.String getSocialHistory(){
		return socialHistory;
	}
	public void setSocialHistory(java.lang.String value){
		socialHistory = value;
	}
				@Column(name="past_medical_history")
	public java.lang.String getPastMedicalHistory(){
		return pastMedicalHistory;
	}
	public void setPastMedicalHistory(java.lang.String value){
		pastMedicalHistory = value;
	}
				@Column(name="past_surgical_history")
	public java.lang.String getPastSurgicalHistory(){
		return pastSurgicalHistory;
	}
	public void setPastSurgicalHistory(java.lang.String value){
		pastSurgicalHistory = value;
	}
		
	// foreign affairs
	
			/** EventCategory */
	@ManyToOne
	@JoinColumn(name="event_category_id")
	public EventCategory getEventCategoryId(){
		return this.eventCategoryId;
	}

	/** EventCategory */
	public void setEventCategoryId(EventCategory obj){
		this.eventCategoryId = obj;
	}
				/** TbCase */
	@ManyToOne
	@JoinColumn(name="case_id")
	public Case getCaseId(){
		return this.caseId;
	}

	/** TbCase */
	public void setCaseId(Case obj){
		this.caseId = obj;
	}
			
	// foreign affairs end

// exported key

	
			/** CaseItem */
	@OneToMany(mappedBy="caseEventId")
	public Set<CaseItem> getCaseItems(){
		return this.caseItems;
	}

	/** CaseItem */
	public void setCaseItems(Set<CaseItem> obj){
		this.caseItems = obj;
	}
	@Column(name="initial_diagnosis")
	public String getInitialDiagnosis() {
		return initialDiagnosis;
	}
	public void setInitialDiagnosis(String initialDiagnosis) {
		this.initialDiagnosis = initialDiagnosis;
	}
			
	//exported key end



}