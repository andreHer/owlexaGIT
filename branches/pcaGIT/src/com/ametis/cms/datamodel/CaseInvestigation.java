
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="case_investigation")
public class CaseInvestigation implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- case_investigation.case_investigation_id --------- 
 schema        = null
 tableName     = case_investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 20
 type          = -5 
 isPrimaryKey  = true

=========================================


*/
	private Long caseInvestigationId;
			
	/**data for the column :
	
 --------- case_investigation.description --------- 
 schema        = null
 tableName     = case_investigation
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
	
 --------- case_investigation.deleted_status --------- 
 schema        = null
 tableName     = case_investigation
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
	
 --------- case_investigation.modified_time --------- 
 schema        = null
 tableName     = case_investigation
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
	
 --------- case_investigation.modified_by --------- 
 schema        = null
 tableName     = case_investigation
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
	
 --------- case_investigation.deleted_by --------- 
 schema        = null
 tableName     = case_investigation
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
	
 --------- case_investigation.deleted_time --------- 
 schema        = null
 tableName     = case_investigation
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
	
 --------- case_investigation.created_time --------- 
 schema        = null
 tableName     = case_investigation
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
	
 --------- case_investigation.created_by --------- 
 schema        = null
 tableName     = case_investigation
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
	
 --------- case_investigation.nurse --------- 
 schema        = null
 tableName     = case_investigation
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
	private String nurse;
			
	/**data for the column :
	
 --------- case_investigation.investigation_date --------- 
 schema        = null
 tableName     = case_investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date investigationDate;
			
	/**data for the column :
	
 --------- case_investigation.investigation_subject --------- 
 schema        = null
 tableName     = case_investigation
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
	private String investigationSubject;
			
	/**data for the column :
	
 --------- case_investigation.head_doctor --------- 
 schema        = null
 tableName     = case_investigation
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
	private String headDoctor;
			
	/**data for the column :
	
 --------- case_investigation.consult_doctor --------- 
 schema        = null
 tableName     = case_investigation
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
	private String consultDoctor;
			
	/**data for the column :
	
 --------- case_investigation.decision --------- 
 schema        = null
 tableName     = case_investigation
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

=========================================


*/
	private Integer decision;
												
	/**data for the column :
	
 --------- case_investigation.conciousness --------- 
 schema        = null
 tableName     = case_investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 19
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String conciousness;
			
	/**data for the column :
	
 --------- case_investigation.blood_pressure --------- 
 schema        = null
 tableName     = case_investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 20
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String bloodPressure;
			
	/**data for the column :
	
 --------- case_investigation.artery --------- 
 schema        = null
 tableName     = case_investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 21
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String artery;
			
	/**data for the column :
	
 --------- case_investigation.temperature --------- 
 schema        = null
 tableName     = case_investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 22
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String temperature;
			
	/**data for the column :
	
 --------- case_investigation.respiratory --------- 
 schema        = null
 tableName     = case_investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 23
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String respiratory;
			
	/**data for the column :
	
 --------- case_investigation.total_days --------- 
 schema        = null
 tableName     = case_investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 24
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer totalDays;
			
	/**data for the column :
	
 --------- case_investigation.sat_oxygen --------- 
 schema        = null
 tableName     = case_investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 25
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String satOxygen;
			
	/**data for the column :
	
 --------- case_investigation.ventilator_status --------- 
 schema        = null
 tableName     = case_investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 26
 size          = 5
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String ventilatorStatus;
			
	/**data for the column :
	
 --------- case_investigation.gcs_e --------- 
 schema        = null
 tableName     = case_investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 27
 size          = 150
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String gcsE;
			
	/**data for the column :
	
 --------- case_investigation.gcs_m --------- 
 schema        = null
 tableName     = case_investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 28
 size          = 150
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String gcsM;
			
	/**data for the column :
	
 --------- case_investigation.gcv_v --------- 
 schema        = null
 tableName     = case_investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 29
 size          = 150
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String gcvV;
			
	/**data for the column :
	
 --------- case_investigation.pulse --------- 
 schema        = null
 tableName     = case_investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 30
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String pulse;
			
	/**data for the column :
	
 --------- case_investigation.ippv_status --------- 
 schema        = null
 tableName     = case_investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 31
 size          = 5
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String ippvStatus;
			
	/**data for the column :
	
 --------- case_investigation.sippv_status --------- 
 schema        = null
 tableName     = case_investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 32
 size          = 5
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String sippvStatus;
			
	/**data for the column :
	
 --------- case_investigation.tracheostomy --------- 
 schema        = null
 tableName     = case_investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 33
 size          = 5
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String tracheostomy;
			
	/**data for the column :
	
 --------- case_investigation.peep --------- 
 schema        = null
 tableName     = case_investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 34
 size          = 150
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String peep;
			
	/**data for the column :
	
 --------- case_investigation.ph --------- 
 schema        = null
 tableName     = case_investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 35
 size          = 150
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String ph;
			
	/**data for the column :
	
 --------- case_investigation.po2 --------- 
 schema        = null
 tableName     = case_investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 36
 size          = 150
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String po2;
			
	/**data for the column :
	
 --------- case_investigation.percentage_pc_o2 --------- 
 schema        = null
 tableName     = case_investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 37
 size          = 25
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String percentagePcO2;
			
	/**data for the column :
	
 --------- case_investigation.percentage_hc_o3 --------- 
 schema        = null
 tableName     = case_investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 38
 size          = 25
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String percentageHcO3;
			
	/**data for the column :
	
 --------- case_investigation.iv_line1 --------- 
 schema        = null
 tableName     = case_investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 39
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String ivLine1;
			
	/**data for the column :
	
 --------- case_investigation.iv_line2 --------- 
 schema        = null
 tableName     = case_investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 40
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String ivLine2;
		
	// foreign affairs
	
			/** TbCase
	data for the foreign class :
	
 --------- tb_case.case_id --------- 
 schema        = null
 tableName     = tb_case
 foreignCol    = case_id
 foreignTab    = case_investigation
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
				/** InvestigationCategory
	data for the foreign class :
	
 --------- investigation_category.investigation_category_id --------- 
 schema        = null
 tableName     = investigation_category
 foreignCol    = investigation_category_id
 foreignTab    = case_investigation
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
	private InvestigationCategory investigationCategoryId;
				/** MedicalProcedure
	data for the foreign class :
	
 --------- medical_procedure.procedure_id --------- 
 schema        = null
 tableName     = medical_procedure
 foreignCol    = procedure_id
 foreignTab    = case_investigation
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
	private Procedure procedureId;
	private Medicine medicineId;
	private Double procedureCostEstimation;
	private Double procedureCostReference;
	private Double medicineCostEstimation;
	private Double medicineCostReference;
			
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="case_investigation_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Long getCaseInvestigationId(){
		return caseInvestigationId;
	}
	public void setCaseInvestigationId(java.lang.Long value){
		caseInvestigationId = value;
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
				@Column(name="nurse")
	public java.lang.String getNurse(){
		return nurse;
	}
	public void setNurse(java.lang.String value){
		nurse = value;
	}
				@Column(name="investigation_date")
	public java.sql.Date getInvestigationDate(){
		return investigationDate;
	}
	public void setInvestigationDate(java.sql.Date value){
		investigationDate = value;
	}
				@Column(name="investigation_subject")
	public java.lang.String getInvestigationSubject(){
		return investigationSubject;
	}
	public void setInvestigationSubject(java.lang.String value){
		investigationSubject = value;
	}
				@Column(name="head_doctor")
	public java.lang.String getHeadDoctor(){
		return headDoctor;
	}
	public void setHeadDoctor(java.lang.String value){
		headDoctor = value;
	}
				@Column(name="consult_doctor")
	public java.lang.String getConsultDoctor(){
		return consultDoctor;
	}
	public void setConsultDoctor(java.lang.String value){
		consultDoctor = value;
	}
				@Column(name="decision")
	public java.lang.Integer getDecision(){
		return decision;
	}
	public void setDecision(java.lang.Integer value){
		decision = value;
	}
													@Column(name="conciousness")
	public java.lang.String getConciousness(){
		return conciousness;
	}
	public void setConciousness(java.lang.String value){
		conciousness = value;
	}
				@Column(name="blood_pressure")
	public java.lang.String getBloodPressure(){
		return bloodPressure;
	}
	public void setBloodPressure(java.lang.String value){
		bloodPressure = value;
	}
				@Column(name="artery")
	public java.lang.String getArtery(){
		return artery;
	}
	public void setArtery(java.lang.String value){
		artery = value;
	}
				@Column(name="temperature")
	public java.lang.String getTemperature(){
		return temperature;
	}
	public void setTemperature(java.lang.String value){
		temperature = value;
	}
				@Column(name="respiratory")
	public java.lang.String getRespiratory(){
		return respiratory;
	}
	public void setRespiratory(java.lang.String value){
		respiratory = value;
	}
				@Column(name="total_days")
	public java.lang.Integer getTotalDays(){
		return totalDays;
	}
	public void setTotalDays(java.lang.Integer value){
		totalDays = value;
	}
				@Column(name="sat_oxygen")
	public java.lang.String getSatOxygen(){
		return satOxygen;
	}
	public void setSatOxygen(java.lang.String value){
		satOxygen = value;
	}
				@Column(name="ventilator_status")
	public java.lang.String getVentilatorStatus(){
		return ventilatorStatus;
	}
	public void setVentilatorStatus(java.lang.String value){
		ventilatorStatus = value;
	}
				@Column(name="gcs_e")
	public java.lang.String getGcsE(){
		return gcsE;
	}
	public void setGcsE(java.lang.String value){
		gcsE = value;
	}
				@Column(name="gcs_m")
	public java.lang.String getGcsM(){
		return gcsM;
	}
	public void setGcsM(java.lang.String value){
		gcsM = value;
	}
				@Column(name="gcv_v")
	public java.lang.String getGcvV(){
		return gcvV;
	}
	public void setGcvV(java.lang.String value){
		gcvV = value;
	}
				@Column(name="pulse")
	public java.lang.String getPulse(){
		return pulse;
	}
	public void setPulse(java.lang.String value){
		pulse = value;
	}
				@Column(name="ippv_status")
	public java.lang.String getIppvStatus(){
		return ippvStatus;
	}
	public void setIppvStatus(java.lang.String value){
		ippvStatus = value;
	}
				@Column(name="sippv_status")
	public java.lang.String getSippvStatus(){
		return sippvStatus;
	}
	public void setSippvStatus(java.lang.String value){
		sippvStatus = value;
	}
				@Column(name="tracheostomy")
	public java.lang.String getTracheostomy(){
		return tracheostomy;
	}
	public void setTracheostomy(java.lang.String value){
		tracheostomy = value;
	}
				@Column(name="peep")
	public java.lang.String getPeep(){
		return peep;
	}
	public void setPeep(java.lang.String value){
		peep = value;
	}
				@Column(name="ph")
	public java.lang.String getPh(){
		return ph;
	}
	public void setPh(java.lang.String value){
		ph = value;
	}
				@Column(name="po2")
	public java.lang.String getPo2(){
		return po2;
	}
	public void setPo2(java.lang.String value){
		po2 = value;
	}
				@Column(name="percentage_pc_o2")
	public java.lang.String getPercentagePcO2(){
		return percentagePcO2;
	}
	public void setPercentagePcO2(java.lang.String value){
		percentagePcO2 = value;
	}
				@Column(name="percentage_hc_o3")
	public java.lang.String getPercentageHcO3(){
		return percentageHcO3;
	}
	public void setPercentageHcO3(java.lang.String value){
		percentageHcO3 = value;
	}
				@Column(name="iv_line1")
	public java.lang.String getIvLine1(){
		return ivLine1;
	}
	public void setIvLine1(java.lang.String value){
		ivLine1 = value;
	}
				@Column(name="iv_line2")
	public java.lang.String getIvLine2(){
		return ivLine2;
	}
	public void setIvLine2(java.lang.String value){
		ivLine2 = value;
	}
		
	// foreign affairs
	
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
				/** InvestigationCategory */
	@ManyToOne
	@JoinColumn(name="investigation_category_id")
	public InvestigationCategory getInvestigationCategoryId(){
		return this.investigationCategoryId;
	}

	/** InvestigationCategory */
	public void setInvestigationCategoryId(InvestigationCategory obj){
		this.investigationCategoryId = obj;
	}
				/** MedicalProcedure */
	@ManyToOne
	@JoinColumn(name="procedure_id")
	public Procedure getProcedureId(){
		return this.procedureId;
	}

	/** MedicalProcedure */
	public void setProcedureId(Procedure obj){
		this.procedureId = obj;
	}
	@ManyToOne
	@JoinColumn(name="medicine_id")
	public Medicine getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(Medicine medicineId) {
		this.medicineId = medicineId;
	}
	@Column(name="procedure_cost_estimation")
	public Double getProcedureCostEstimation() {
		return procedureCostEstimation;
	}
	public void setProcedureCostEstimation(Double procedureCostEstimation) {
		this.procedureCostEstimation = procedureCostEstimation;
	}
	@Column(name="procedure_cost_reference")
	public Double getProcedureCostReference() {
		return procedureCostReference;
	}
	public void setProcedureCostReference(Double procedureCostReference) {
		this.procedureCostReference = procedureCostReference;
	}
	@Column(name="medicine_cost_estimation")
	public Double getMedicineCostEstimation() {
		return medicineCostEstimation;
	}
	public void setMedicineCostEstimation(Double medicineCostEstimation) {
		this.medicineCostEstimation = medicineCostEstimation;
	}
	@Column(name="medicine_cost_reference")
	public Double getMedicineCostReference() {
		return medicineCostReference;
	}
	public void setMedicineCostReference(Double medicineCostReference) {
		this.medicineCostReference = medicineCostReference;
	}
	
	


}