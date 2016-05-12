
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
@Table(name="first_call")
public class FirstCall implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public static final int COMPLAIN = 1;
	public static final int INFORMATION = 3;
	public static final int INPATIENT = 2;
	public static final int OUTPATIENT = 5;
	public static final int MISC = 4;
	
	public static final int CALL_LOG_TYPE_MEMBER = 1;
	public static final int CALL_LOG_TYPE_CASE = 2;
	public static final int CALL_LOG_TYPE_CLAIM = 3;

	//Fields
		
	/**data for the column :
	
 --------- first_call.call_id --------- 
 schema        = null
 tableName     = first_call
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
	private Integer callId;
	
	private String callNumber;
			
	/**data for the column :
	
 --------- first_call.call_start_time --------- 
 schema        = null
 tableName     = first_call
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp callStartTime;
			
	/**data for the column :
	
 --------- first_call.call_end_time --------- 
 schema        = null
 tableName     = first_call
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp callEndTime;
						
	/**data for the column :
	
 --------- first_call.description --------- 
 schema        = null
 tableName     = first_call
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private String description;
						
	/**data for the column :
	
 --------- first_call.remarks --------- 
 schema        = null
 tableName     = first_call
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String remarks;
						
	/**data for the column :
	
 --------- first_call.caller_name --------- 
 schema        = null
 tableName     = first_call
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private String callerName;
			
	/**data for the column :
	
 --------- first_call.city --------- 
 schema        = null
 tableName     = first_call
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private String city;
						
	/**data for the column :
	
 --------- first_call.created_time --------- 
 schema        = null
 tableName     = first_call
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- first_call.created_by --------- 
 schema        = null
 tableName     = first_call
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	
 --------- first_call.deleted_time --------- 
 schema        = null
 tableName     = first_call
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 14
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- first_call.deleted_by --------- 
 schema        = null
 tableName     = first_call
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private String deletedBy;
			
	/**data for the column :
	
 --------- first_call.modified_time --------- 
 schema        = null
 tableName     = first_call
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- first_call.modified_by --------- 
 schema        = null
 tableName     = first_call
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private String modifiedBy;
			
	/**data for the column :
	
 --------- first_call.deleted_status --------- 
 schema        = null
 tableName     = first_call
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 18
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- first_call.location --------- 
 schema        = null
 tableName     = first_call
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private String location;
			
	/**data for the column :
	
 --------- first_call.provider --------- 
 schema        = null
 tableName     = first_call
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private String provider;
		
	// foreign affairs
	
			/** User
	data for the foreign class :
	
 --------- user.user_id --------- 
 schema        = null
 tableName     = user
 foreignCol    = handledBy
 foreignTab    = first_call
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
	private User handledby;
				/** CaseStatus
	data for the foreign class :
	
 --------- case_status.case_status_id --------- 
 schema        = null
 tableName     = case_status
 foreignCol    = status
 foreignTab    = first_call
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
	private CaseStatus status;
				/** CallCategory
	data for the foreign class :
	
 --------- call_category.call_category_id --------- 
 schema        = null
 tableName     = call_category
 foreignCol    = call_category_id
 foreignTab    = first_call
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
	private CallCategory callCategoryId;
				/** Member
	data for the foreign class :
	
 --------- member.member_id --------- 
 schema        = null
 tableName     = member
 foreignCol    = customer_id
 foreignTab    = first_call
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
	private Member customerId;
	private Case caseId;
	private Claim claimId;
	
	private String telephone;
	private String followup;
	private Priority priority;
	private String callerType;
			
	private Integer callStatus;
	
	private Integer callLogType;
	/**data for the column :
	
 --------- first_call.call_log_type --------- 
 schema        = null
 tableName     = first_call
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 11
 type          = 4
 isPrimaryKey  = false

=========================================


*/	
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="call_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getCallId(){
		return callId;
	}
	public void setCallId(java.lang.Integer value){
		callId = value;
	}
			// PK GETTER SETTER END

						@Column(name="call_start_time")
	public java.sql.Timestamp getCallStartTime(){
		return callStartTime;
	}
	public void setCallStartTime(java.sql.Timestamp value){
		callStartTime = value;
	}
				@Column(name="call_end_time")
	public java.sql.Timestamp getCallEndTime(){
		return callEndTime;
	}
	public void setCallEndTime(java.sql.Timestamp value){
		callEndTime = value;
	}
							@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
							@Column(name="remarks")
	public java.lang.String getRemarks(){
		return remarks;
	}
	public void setRemarks(java.lang.String value){
		remarks = value;
	}
							@Column(name="caller_name")
	public java.lang.String getCallerName(){
		return callerName;
	}
	public void setCallerName(java.lang.String value){
		callerName = value;
	}
				@Column(name="city")
	public java.lang.String getCity(){
		return city;
	}
	public void setCity(java.lang.String value){
		city = value;
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
				@Column(name="location")
	public java.lang.String getLocation(){
		return location;
	}
	public void setLocation(java.lang.String value){
		location = value;
	}
				@Column(name="provider")
	public java.lang.String getProvider(){
		return provider;
	}
	public void setProvider(java.lang.String value){
		provider = value;
	}
		
	@Column(name="call_number")
	public String getCallNumber() {
		return callNumber;
	}
	public void setCallNumber(String callNumber) {
		this.callNumber = callNumber;
	}
	// foreign affairs = bisa dibilang ini adalah FK ke tabel lain
	
			/** User */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="handledBy")
	public User getHandledby(){
		return this.handledby;
	}

	/** User */
	public void setHandledby(User obj){
		this.handledby = obj;
	}
				/** CaseStatus */
	@ManyToOne
	@JoinColumn(name="status")
	public CaseStatus getStatus(){
		return this.status;
	}

	/** CaseStatus */
	public void setStatus(CaseStatus obj){
		this.status = obj;
	}
				/** CallCategory */
	@ManyToOne
	@JoinColumn(name="call_category_id")
	public CallCategory getCallCategoryId(){
		return this.callCategoryId;
	}

	/** CallCategory */
	public void setCallCategoryId(CallCategory obj){
		this.callCategoryId = obj;
	}
	
	/** Member */
	//@ManyToOne(fetch=FetchType.LAZY)
	@ManyToOne
	@JoinColumn(name="customer_id")
	public Member getCustomerId(){
		return this.customerId;
	}

	/** Member */
	public void setCustomerId(Member obj){
		this.customerId = obj;
	}
	
	
	/**Priority*/
	//Ini ngerefer ke kolom priority di tabel first_call
	@ManyToOne
	@JoinColumn(name="priority")
	public Priority getPriority() {
		return priority;
	}
	/**Priority*/
	public void setPriority(Priority obj) {
		this.priority = obj;
	}
	@Column(name="need_followup")
	public String getFollowup() {
		return followup;
	}
	public void setFollowup(String followup) {		
		this.followup = followup;
	}
	@Column(name="telephone_number")
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	@Column(name="caller_type")
	public String getCallerType() {
		return callerType;
	}
	public void setCallerType(String callerType) {
		this.callerType = callerType;
	}
	@ManyToOne
	@JoinColumn(name="case_id")
	public Case getCaseId() {
		return caseId;
	}
	public void setCaseId(Case caseId) {
		this.caseId = caseId;
	}
	@ManyToOne
	@JoinColumn(name="claim_id")
	public Claim getClaimId() {
		return claimId;
	}
	public void setClaimId(Claim claimId) {
		this.claimId = claimId;
	}
	@Column(name="call_status")
	public Integer getCallStatus() {
		return callStatus;
	}
	public void setCallStatus(Integer callStatus) {
		this.callStatus = callStatus;
	}
	@Column(name="call_log_type")
	public Integer getCallLogType() {
		return callLogType;
	}
	public void setCallLogType(Integer callLogType) {
		this.callLogType = callLogType;
	}
	
	
	

}