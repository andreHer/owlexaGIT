
package com.ametis.cms.datamodel;


import java.sql.*;
import java.sql.Date;
import java.util.*;
import javax.persistence.*;

import org.hibernate.annotations.Generated;


@Entity
@Table(name="member_diagnosis_exclusion")
public class MemberDiagnosisExclusion implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- member_diagnosis_exclusion.id --------- 
 schema        = null
 tableName     = member_diagnosis_exclusion
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
	
 --------- member_diagnosis_exclusion.diagnosis_id --------- 
 schema        = null
 tableName     = member_diagnosis_exclusion
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
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Diagnosis diagnosisId;
			
	/**data for the column :
	
 --------- member_diagnosis_exclusion.member_id --------- 
 schema        = null
 tableName     = member_diagnosis_exclusion
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private Member memberId;
			
	/**data for the column :
	
 --------- member_diagnosis_exclusion.policy_id --------- 
 schema        = null
 tableName     = member_diagnosis_exclusion
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Policy policyId;
			
	/**data for the column :
	
 --------- member_diagnosis_exclusion.exclusion_status --------- 
 schema        = null
 tableName     = member_diagnosis_exclusion
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 2
 type          = -6 
 isPrimaryKey  = false

=========================================


*/
	private Integer exclusionStatus;
			
	/**data for the column :
	
 --------- member_diagnosis_exclusion.created_time --------- 
 schema        = null
 tableName     = member_diagnosis_exclusion
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = CURRENT_TIMESTAMP
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- member_diagnosis_exclusion.created_by --------- 
 schema        = null
 tableName     = member_diagnosis_exclusion
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private String createdBy;
			
	/**data for the column :
	
 --------- member_diagnosis_exclusion.modified_time --------- 
 schema        = null
 tableName     = member_diagnosis_exclusion
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
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
	
 --------- member_diagnosis_exclusion.modified_by --------- 
 schema        = null
 tableName     = member_diagnosis_exclusion
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	
 --------- member_diagnosis_exclusion.deleted_time --------- 
 schema        = null
 tableName     = member_diagnosis_exclusion
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- member_diagnosis_exclusion.deleted_by --------- 
 schema        = null
 tableName     = member_diagnosis_exclusion
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- member_diagnosis_exclusion.deleted_status --------- 
 schema        = null
 tableName     = member_diagnosis_exclusion
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 2
 type          = -6 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- member_diagnosis_exclusion.modification_information --------- 
 schema        = null
 tableName     = member_diagnosis_exclusion
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String modificationInformation;
	private DiagnosisSet diagnosisSetId;
	private String ageParameter;
	
	private Integer ageConstraint;

	private String url;

	private String type;

	private Integer statusUpdate;
	private Integer preExistingDays;

	private String uploadNote;
	private Date expireDate;
		
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
	@JoinColumn(name="diagnosis_id")
	public Diagnosis getDiagnosisId(){
		return diagnosisId;
	}
	public void setDiagnosisId(Diagnosis value){
		diagnosisId = value;
	}
	@ManyToOne
	@JoinColumn(name="member_id")
	public Member getMemberId(){
		return memberId;
	}
	public void setMemberId(Member value){
		memberId = value;
	}
	@ManyToOne
	@JoinColumn(name="policy_id")
	public Policy getPolicyId(){
		return policyId;
	}
	public void setPolicyId(Policy value){
		policyId = value;
	}
				@Column(name="exclusion_status")
	public java.lang.Integer getExclusionStatus(){
		return exclusionStatus;
	}
	public void setExclusionStatus(java.lang.Integer value){
		exclusionStatus = value;
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
				@Column(name="modification_information")
	public java.lang.String getModificationInformation(){
		return modificationInformation;
	}
	public void setModificationInformation(java.lang.String value){
		modificationInformation = value;
	}
	@ManyToOne
	@JoinColumn(name="diagnosis_set_id")
	public DiagnosisSet getDiagnosisSetId() {
		return diagnosisSetId;
	}
	public void setDiagnosisSetId(DiagnosisSet diagnosisSetId) {
		this.diagnosisSetId = diagnosisSetId;
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
	@Column(name="upload_note")
	public String getUploadNote() {
		return uploadNote;
	}
	public void setUploadNote(String uploadNote) {
		this.uploadNote = uploadNote;
	}
	@Column(name="expire_date")
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	@Column(name="age_constraint")
	public Integer getAgeConstraint() {
		return ageConstraint;
	}
	public void setAgeConstraint(Integer ageConstraint) {
		this.ageConstraint = ageConstraint;
	}
	@Column(name="age_parameter")
	public String getAgeParameter() {
		return ageParameter;
	}
	public void setAgeParameter(String ageParameter) {
		this.ageParameter = ageParameter;
	}
	@Column(name="pre_existing_days")
	public Integer getPreExistingDays() {
		return preExistingDays;
	}
	public void setPreExistingDays(Integer preExistingDays) {
		this.preExistingDays = preExistingDays;
	}

	
	
}