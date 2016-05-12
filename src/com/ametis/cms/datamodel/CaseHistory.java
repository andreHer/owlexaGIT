
package com.ametis.cms.datamodel;


import java.sql.*;
import java.sql.Date;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="case_history")
public class CaseHistory implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private String actionType;

	//Fields
		
	/**data for the column :
	
 --------- case_history.case_history_id --------- 
 schema        = null
 tableName     = case_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
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
	private Integer caseHistoryId;
			
	/**data for the column :
	
 --------- case_history.case_id --------- 
 schema        = null
 tableName     = case_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 10
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Case caseId;
			
	/**data for the column :
	
 --------- case_history.history_time --------- 
 schema        = null
 tableName     = case_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
 remarks       = on update CURRENT_TIMESTAMP
 defaultValue  = CURRENT_TIMESTAMP
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 3
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp historyTime;
									
	/**data for the column :
	
 --------- case_history.created_time --------- 
 schema        = null
 tableName     = case_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
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
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- case_history.created_by --------- 
 schema        = null
 tableName     = case_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
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
	
 --------- case_history.modified_time --------- 
 schema        = null
 tableName     = case_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
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
	
 --------- case_history.modified_by --------- 
 schema        = null
 tableName     = case_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
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
	
 --------- case_history.deleted_time --------- 
 schema        = null
 tableName     = case_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
 remarks       = 
 defaultValue  = null
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
	
 --------- case_history.deleted_by --------- 
 schema        = null
 tableName     = case_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
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
	
 --------- case_history.deleted_status --------- 
 schema        = null
 tableName     = case_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- case_history.before_action_data --------- 
 schema        = null
 tableName     = case_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
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
	private String beforeActionData;
			
	/**data for the column :
	
 --------- case_history.after_action_data --------- 
 schema        = null
 tableName     = case_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 14
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String afterActionData;
			
	/**data for the column :
	
 --------- case_history.action_by --------- 
 schema        = null
 tableName     = case_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
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
	private String actionBy;
		
	// foreign affairs
	
			/** CaseStatus
	data for the foreign class :
	
 --------- case_status.case_status_id --------- 
 schema        = null
 tableName     = case_status
 foreignCol    = after_status
 foreignTab    = case_history
 catalog       = mp-new
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
	private CaseStatus afterStatus;
				/** CaseStatus
	data for the foreign class :
	
 --------- case_status.case_status_id --------- 
 schema        = null
 tableName     = case_status
 foreignCol    = before_status
 foreignTab    = case_history
 catalog       = mp-new
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
	private CaseStatus beforeStatus;
	
	private String caseNumber;
	private String diagnosisCode;
	private String diagnosis2Code;
	private String diagnosis3Code;
	private String providerName;
	private String memberName;
	private String caseCategoryName;
	private String memberNumber;
	private String description;
	private Integer duration;
	private String durationString;
	
			

	@Id
	@Column(name="case_history_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getCaseHistoryId(){
		return caseHistoryId;
	}
	public void setCaseHistoryId(java.lang.Integer value){
		caseHistoryId = value;
	}
			// PK GETTER SETTER END

	@ManyToOne
	@JoinColumn(name="case_id")
	public Case getCaseId(){
		return caseId;
	}
	public void setCaseId(Case value){
		caseId = value;
	}
	@Column(name="history_time")
	public java.sql.Timestamp getHistoryTime(){
		return historyTime;
	}
	public void setHistoryTime(java.sql.Timestamp value){
		historyTime = value;
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
				@Column(name="before_action_data")
	public java.lang.String getBeforeActionData(){
		return beforeActionData;
	}
	public void setBeforeActionData(java.lang.String value){
		beforeActionData = value;
	}
				@Column(name="after_action_data")
	public java.lang.String getAfterActionData(){
		return afterActionData;
	}
	public void setAfterActionData(java.lang.String value){
		afterActionData = value;
	}
				@Column(name="action_by")
	public java.lang.String getActionBy(){
		return actionBy;
	}
	public void setActionBy(java.lang.String value){
		actionBy = value;
	}
		
	// foreign affairs
	
			/** CaseStatus */
	@ManyToOne
	@JoinColumn(name="after_status")
	public CaseStatus getAfterStatus(){
		return this.afterStatus;
	}

	/** CaseStatus */
	public void setAfterStatus(CaseStatus obj){
		this.afterStatus = obj;
	}
				/** CaseStatus */
	@ManyToOne
	@JoinColumn(name="before_status")
	public CaseStatus getBeforeStatus(){
		return this.beforeStatus;
	}

	/** CaseStatus */
	public void setBeforeStatus(CaseStatus obj){
		this.beforeStatus = obj;
	}
	@Column(name="action_type")
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	@Column(name="diagnosis1_code")
	public String getDiagnosisCode() {
		return diagnosisCode;
	}
	public void setDiagnosisCode(String diagnosisCode) {
		this.diagnosisCode = diagnosisCode;
	}
	@Column(name="diagnosis2_code")
	public String getDiagnosis2Code() {
		return diagnosis2Code;
	}
	public void setDiagnosis2Code(String diagnosis2Code) {
		this.diagnosis2Code = diagnosis2Code;
	}
	@Column(name="diagnosis3_code")
	public String getDiagnosis3Code() {
		return diagnosis3Code;
	}
	public void setDiagnosis3Code(String diagnosis3Code) {
		this.diagnosis3Code = diagnosis3Code;
	}
	@Column(name="provider_name")
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	@Column(name="case_number")
	public String getCaseNumber() {
		return caseNumber;
	}
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	@Column(name="member_name")
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	@Column(name="case_category_name")
	public String getCaseCategoryName() {
		return caseCategoryName;
	}
	public void setCaseCategoryName(String caseCategoryName) {
		this.caseCategoryName = caseCategoryName;
	}
	@Column(name="member_number")
	public String getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="duration")
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	@Column(name="duration_string")
	public String getDurationString() {
		return durationString;
	}
	public void setDurationString(String durationString) {
		this.durationString = durationString;
	}		
	
	
}