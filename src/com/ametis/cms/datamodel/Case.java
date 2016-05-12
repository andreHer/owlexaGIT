
package com.ametis.cms.datamodel;


import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="tb_case")
public class Case implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	
	public static final int CASE_INPATIENT = 1;
	public static final int CASE_OUTPATIENT = 2;
	
	public static final int CASE_PPK1 = 3;
	public static final int CASE_PPK2 = 4;
	public static final int CASE_REFERENCE = 5;
	
	public static final int CASE_OPEN = 1;
	public static final int CASE_APPROVED  = 9;
	public static final int CASE_REJECT = 4;
	public static final int CASE_CLOSED = 5; 
	public static final int CASE_TERMINATED  = 13;
	public static final int CASE_CLAIM_PROCESSED = 15;
	public static final int CASE_EDC = 2;
	public static final int CASE_CALL_CENTER = 1;
	public static final int CASE_EDC_MANUAL = 3;
	public static final int CASE_REFERED = 17;
	public static final int CASE_PENDING_DOCUMENT = 2;
	public static final int CASE_PENDING = 10;
	public static final int CASE_PENDING_INVESTIGATION = 7;
	//Add by aju on 20150824, for preAdmission
	public static final int CASE_PRE_AUTH = 20;
	public static final int CASE_PRE_REJECT = 21;
	public static final int CASE_PRE_PENDING = 22;
	public static final int CASE_PRE_OPEN = 19; //equals to PRE_AUTH->PRE_APPROVED
	public static final int CASE_CHECKED = 8;//adq
	
	public static final int CASE_MANUAL_REG_DEFECTIVE = 1;
	public static final int CASE_MANUAL_REG_LOST_SIGNAL = 2;
	public static final int CASE_MANUAL_REG_EDC_PENDING = 3;

	//Fields
		
	/**data for the column :
	
 --------- case.case_id --------- 
 schema        = null
 tableName     = case
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
	private Integer caseId;
									
	/**data for the column :
	
 --------- case.case_start_time --------- 
 schema        = null
 tableName     = case
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
	private Date caseStartTime;
			
	/**data for the column :
	
 --------- case.case_end_time --------- 
 schema        = null
 tableName     = case
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
	private java.sql.Date caseEndTime;
						
	/**data for the column :
	
 --------- case.description --------- 
 schema        = null
 tableName     = case
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
	private String description;
			
	/**data for the column :
	
 --------- case.initial_symptom --------- 
 schema        = null
 tableName     = case
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String initialSymptom;
			
	/**data for the column :
	
 --------- case.case_number --------- 
 schema        = null
 tableName     = case
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
	private String caseNumber;
			
	/**data for the column :
	
 --------- case.physician --------- 
 schema        = null
 tableName     = case
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
	private String physician;
									
	/**data for the column :
	
 --------- case.case_handler --------- 
 schema        = null
 tableName     = case
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private String caseHandler;
			
	/**data for the column :
	
 --------- case.room --------- 
 schema        = null
 tableName     = case
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private Item itemId;
			
	/**data for the column :
	
 --------- case.long_of_stay --------- 
 schema        = null
 tableName     = case
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private Integer longOfStay;
			
	/**data for the column :
	
 --------- case.case_type --------- 
 schema        = null
 tableName     = case
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private Integer caseType;
			
	/**data for the column :
	
 --------- case.created_time --------- 
 schema        = null
 tableName     = case
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 17
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- case.created_by --------- 
 schema        = null
 tableName     = case
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 18
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- case.deleted_time --------- 
 schema        = null
 tableName     = case
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 19
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- case.deleted_by --------- 
 schema        = null
 tableName     = case
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 20
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- case.modified_time --------- 
 schema        = null
 tableName     = case
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 21
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- case.modified_by --------- 
 schema        = null
 tableName     = case
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 22
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- case.deleted_status --------- 
 schema        = null
 tableName     = case
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 23
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- case.provider_id --------- 
 schema        = null
 tableName     = case
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 24
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Provider providerId;
		
	// foreign affairs
	
			/** CaseStatus
	data for the foreign class :
	
 --------- case_status.case_status_id --------- 
 schema        = null
 tableName     = case_status
 foreignCol    = case_status_id
 foreignTab    = case
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
	private CaseStatus caseStatusId;
	private CaseStatus secondaryStatus;
				/** Member
	data for the foreign class :
	
 --------- member.member_id --------- 
 schema        = null
 tableName     = member
 foreignCol    = member_id
 foreignTab    = case
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
	private Member memberId;
				/** Diagnosis
	data for the foreign class :
	
 --------- diagnosis.diagnosis_id --------- 
 schema        = null
 tableName     = diagnosis
 foreignCol    = diagnosis1_id
 foreignTab    = case
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
	private Diagnosis diagnosis1Id;
				/** Diagnosis
	data for the foreign class :
	
 --------- diagnosis.diagnosis_id --------- 
 schema        = null
 tableName     = diagnosis
 foreignCol    = diagnosis2_id
 foreignTab    = case
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
	private Diagnosis diagnosis2Id;
	
	private Diagnosis diagnosis3Id;
	
	private Priority priorityId;
	
	
				/** CaseCategory
	data for the foreign class :
	
 --------- case_category.case_category_id --------- 
 schema        = null
 tableName     = case_category
 foreignCol    = case_category_id
 foreignTab    = case
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
	private CaseCategory caseCategoryId;
			
	// -- foreign affairs end

	// -- exported key

	
			/** CaseEvent
	data for the exported class :
	
 --------- case_event.case_id --------- 
 schema        = null
 tableName     = case_event
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
 this columns points to  = case.case_id

=========================================



	 */
	private Integer isClaimed;
	private Set <CaseEvent> caseEvents = new HashSet(0);
				/** Claim
	data for the exported class :
	
 --------- claim.case_id --------- 
 schema        = null
 tableName     = claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 17
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = case.case_id

=========================================



	 */
	
	private Integer manualRegistration;
			
	/**data for the column :
	
 --------- case.manual_registration --------- 
 schema        = null
 tableName     = case
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 17
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	
	private Set <Claim> claims = new HashSet(0);

	private java.sql.Timestamp closingTime;
	private Integer realLongOfStay;
	private String roomAndBoard;
	private Double caseClaimValue;
	private Double caseApprovedValue;
	private Double caseExcessValue;
	private Double costContainmentValue;
	private Case referedCaseId;
	private FirstCall callId;
	private Integer isExpired;
	private Set <CostContainment> costContainment = new HashSet(0);
	private Claim claimId;
	private String initialDiagnosis;
	private Integer roomAndBoardStatus;
	private String anamnesa;
	private String roomAndBoardUsage;
	private Policy policyId;
	private Integer referalStatus;
	private Provider referedProviderId;
	private ProviderDoctor providerDoctorId;
	private String doctorName;
	private ProviderItem providerItemId;
	private Poliklinik poliklinikId;
	private MemberGroup memberGroupId;
	
	private String caseStatusName;
	private String memberName;
	private String memberNumber;
	
	private String diagnosis1Code;
	private String diagnosis1Name;
	private String diagnosis2Code;
	private String diagnosis2Name;
	private String diagnosis3Code;
	private String diagnosis3Name;
	
	private String providerName;
	
	private Poliklinik referedPoliklinikId;
	private ProviderDoctor referedDoctorId;
	private String referedDoctorName;
	private String electronicRefNumber;
	private String caseReferalNumber;
	private Double preRemainingLimit;
	private Double postRemainingLimit;
	
	private String exGratiaNotes;
	private Integer isExGratia;
	private String exGratiaDocumentURL;
	private User assigneeId;
	private String assignmentNote;
	private Integer isGLIssued;
	private String currentCardNumber;
	private EdcTerminal terminalId;
	private String merchantId;
	private Date issuedDate;
	private Date declinedDate;
	
	private MemberProduct memberProductId;
	
	//Add by aju on 20150805, for COB :D
	private Integer isLinkedMember;
	
	//Add by FVO on 20150810, for threshold
	private Double thresholdValue;
	private Double inaCbgTreatmentRate;
	private Double inaCbgAlos;
	
	//Add by FVO on 20150811, for RISK
	private Integer hasComplication;
			
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="case_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getCaseId(){
		return caseId;
	}
	public void setCaseId(java.lang.Integer value){
		caseId = value;
	}
			// PK GETTER SETTER END

												@Column(name="case_start_time")
	public java.sql.Date getCaseStartTime(){
		return caseStartTime;
	}
	public void setCaseStartTime(java.sql.Date value){
		caseStartTime = value;
	}
				@Column(name="case_end_time")
	public java.sql.Date getCaseEndTime(){
		return caseEndTime;
	}
	public void setCaseEndTime(java.sql.Date value){
		caseEndTime = value;
	}
							@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
				@Column(name="initial_symptom")
	public java.lang.String getInitialSymptom(){
		return initialSymptom;
	}
	public void setInitialSymptom(java.lang.String value){
		initialSymptom = value;
	}
				@Column(name="case_number")
	public java.lang.String getCaseNumber(){
		return caseNumber;
	}
	public void setCaseNumber(java.lang.String value){
		caseNumber = value;
	}
	
	/** MemberGroup */
	@ManyToOne
	@JoinColumn(name = "member_group_id")
	public MemberGroup getMemberGroupId() {
		return this.memberGroupId;
	}

	/** MemberGroup */
	public void setMemberGroupId(MemberGroup obj) {
		this.memberGroupId = obj;
	}
	
				@Column(name="physician")
	public java.lang.String getPhysician(){
		return physician;
	}
	public void setPhysician(java.lang.String value){
		physician = value;
	}
										@Column(name="case_handler")
	public java.lang.String getCaseHandler(){
		return caseHandler;
	}
	public void setCaseHandler(java.lang.String value){
		caseHandler = value;
	}
				
				@Column(name="long_of_stay")
	public java.lang.Integer getLongOfStay(){
		return longOfStay;
	}
	public void setLongOfStay(java.lang.Integer value){
		longOfStay = value;
	}
				@Column(name="case_type")
	public java.lang.Integer getCaseType(){
		return caseType;
	}
	public void setCaseType(java.lang.Integer value){
		caseType = value;
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
	
			/** CaseStatus */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="case_status_id")
	public CaseStatus getCaseStatusId(){
		return this.caseStatusId;
	}

	/** CaseStatus */
	public void setCaseStatusId(CaseStatus obj){
		this.caseStatusId = obj;
	}
				/** Member */
	@ManyToOne
	@JoinColumn(name="member_id")
	public Member getMemberId(){
		return this.memberId;
	}

	/** Member */
	public void setMemberId(Member obj){
		this.memberId = obj;
		
		this.memberName = obj.getFirstName();
		this.memberNumber = obj.getCustomerPolicyNumber();
	}
				/** Diagnosis */
	@ManyToOne
	@JoinColumn(name="diagnosis1_id")
	public Diagnosis getDiagnosis1Id(){
		return this.diagnosis1Id;
	}

	/** Diagnosis */
	public void setDiagnosis1Id(Diagnosis obj){
		this.diagnosis1Id = obj;
		
	}
				/** Diagnosis */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="diagnosis2_id")
	public Diagnosis getDiagnosis2Id(){
		return this.diagnosis2Id;
	}

	/** Diagnosis */
	public void setDiagnosis2Id(Diagnosis obj){
		this.diagnosis2Id = obj;
	}
	
				/** Diagnosis */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="diagnosis3_id")
	public Diagnosis getDiagnosis3Id(){
		return this.diagnosis3Id;
	}

	/** Diagnosis */
	public void setDiagnosis3Id(Diagnosis obj){
		this.diagnosis3Id = obj;
	}
	
	/**Room*/
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="room")
	public Item getItemId(){
		return this.itemId;
	}
	public void setItemId(Item obj){
		this.itemId = obj;
	}
	
	/**Priority*/
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="priority_id")
	public Priority getPriorityId() {
		return this.priorityId;
	}
	public void setPriorityId(Priority priorityId) {
		this.priorityId = priorityId;
	}
	
				/** CaseCategory */
	@ManyToOne
	@JoinColumn(name="case_category_id")
	public CaseCategory getCaseCategoryId(){
		return this.caseCategoryId;
	}

	/** CaseCategory */
	public void setCaseCategoryId(CaseCategory obj){
		this.caseCategoryId = obj;
	}
	
	/**Povider*/
	
	@ManyToOne
	@JoinColumn(name="provider_id")				
	public Provider getProviderId(){
		return this.providerId;
	}
	public void setProviderId(Provider value){
		providerId = value;
	}
		
			
	// foreign affairs end

// exported key

	
			/** CaseEvent */
	@OneToMany(mappedBy="caseId")
	public Set<CaseEvent> getCaseEvents(){
		return this.caseEvents;
	}

	/** CaseEvent */
	public void setCaseEvents(Set<CaseEvent> obj){
		this.caseEvents = obj;
	}
				/** Claim */
	@OneToMany(mappedBy="caseId")
	public Set<Claim> getClaims(){
		return this.claims;
	}

	/** Claim */
	public void setClaims(Set<Claim> obj){
		this.claims = obj;
	}
			
	@OneToMany(mappedBy="caseId")
	public Set<CostContainment> getCostContainments(){
		return this.costContainment;
	}
	
	public void setCostContainments(Set<CostContainment> obj){
		this.costContainment = obj	;
	}
	//exported key end
	@Column(name="closing_time")
	public java.sql.Timestamp getClosingTime() {
		return closingTime;
	}
	public void setClosingTime(java.sql.Timestamp closingTime) {
		this.closingTime = closingTime;
	}
	@Column(name="real_long_of_stay")
	public Integer getRealLongOfStay() {
		return realLongOfStay;
	}
	public void setRealLongOfStay(Integer realLongOfStay) {
		this.realLongOfStay = realLongOfStay;
	}
	@Column(name="room_and_board")
	public String getRoomAndBoard() {
		return roomAndBoard;
	}
	public void setRoomAndBoard(String roomAndBoard) {
		this.roomAndBoard = roomAndBoard;
	}
	@Column(name="case_claim_value")
	public Double getCaseClaimValue() {
		return caseClaimValue;
	}
	public void setCaseClaimValue(Double caseClaimValue) {
		this.caseClaimValue = caseClaimValue;
	}
	@Column(name="case_approved_value")
	public Double getCaseApprovedValue() {
		return caseApprovedValue;
	}
	public void setCaseApprovedValue(Double caseApprovedValue) {
		this.caseApprovedValue = caseApprovedValue;
	}
	@Column(name="case_excess_value")
	public Double getCaseExcessValue() {
		return caseExcessValue;
	}
	public void setCaseExcessValue(Double caseExcessValue) {
		this.caseExcessValue = caseExcessValue;
	}
	@Column(name="cost_containment_value")
	public Double getCostContainmentValue() {
		return costContainmentValue;
	}
	public void setCostContainmentValue(Double costContainmentValue) {
		this.costContainmentValue = costContainmentValue;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ref_case_id")
	public Case getReferedCaseId() {
		return referedCaseId;
	}
	public void setReferedCaseId(Case referedCaseId) {
		this.referedCaseId = referedCaseId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="call_id")
	public FirstCall getCallId() {
		return callId;
	}
	public void setCallId(FirstCall callId) {
		this.callId = callId;
	}
	@Column(name="is_expired")
	public Integer getIsExpired() {
		return isExpired;
	}
	public void setIsExpired(Integer isExpired) {
		this.isExpired = isExpired;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="claim_id")
	public Claim getClaimId() {
		return claimId;
	}
	public void setClaimId(Claim claimId) {
		this.claimId = claimId;
	}
	@Column(name="initial_diagnosis")
	public String getInitialDiagnosis() {
		return initialDiagnosis;
	}
	public void setInitialDiagnosis(String initialDiagnosis) {
		this.initialDiagnosis = initialDiagnosis;
	}
	@Column(name="room_and_board_status")
	public Integer getRoomAndBoardStatus() {
		return roomAndBoardStatus;
	}
	public void setRoomAndBoardStatus(Integer roomAndBoardStatus) {
		this.roomAndBoardStatus = roomAndBoardStatus;
	}
	@Column(name="anamnesa")
	public String getAnamnesa() {
		return anamnesa;
	}
	public void setAnamnesa(String anamnesa) {
		this.anamnesa = anamnesa;
	}
	@Column(name="room_and_board_usage")
	public String getRoomAndBoardUsage() {
		return roomAndBoardUsage;
	}
	public void setRoomAndBoardUsage(String roomAndBoardUsage) {
		this.roomAndBoardUsage = roomAndBoardUsage;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="policy_id")
	public Policy getPolicyId() {
		return policyId;
	}
	public void setPolicyId(Policy policyId) {
		this.policyId = policyId;
	}
	@Column(name="is_claimed")
	public Integer getIsClaimed() {
		return isClaimed;
	}
	public void setIsClaimed(Integer isClaimed) {
		this.isClaimed = isClaimed;
	}
	@Column(name="referal_status")
	public Integer getReferalStatus() {
		return referalStatus;
	}
	public void setReferalStatus(Integer referalStatus) {
		this.referalStatus = referalStatus;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="refered_provider_id")
	public Provider getReferedProviderId() {
		return referedProviderId;
	}
	public void setReferedProviderId(Provider referedProviderId) {
		this.referedProviderId = referedProviderId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="doctor_id")
	public ProviderDoctor getProviderDoctorId() {
		return providerDoctorId;
	}
	public void setProviderDoctorId(ProviderDoctor providerDoctorId) {
		this.providerDoctorId = providerDoctorId;
	}
	@Column(name="doctor_name")
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="provider_item_id")
	public ProviderItem getProviderItemId() {
		return providerItemId;
	}
	public void setProviderItemId(ProviderItem providerItemId) {
		this.providerItemId = providerItemId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="poliklinik_id")
	public Poliklinik getPoliklinikId() {
		return poliklinikId;
	}
	public void setPoliklinikId(Poliklinik poliklinikId) {
		this.poliklinikId = poliklinikId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="refered_poliklinik_id")
	public Poliklinik getReferedPoliklinikId() {
		return referedPoliklinikId;
	}
	public void setReferedPoliklinikId(Poliklinik referedPoliklinikId) {
		this.referedPoliklinikId = referedPoliklinikId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="refered_doctor_id")
	public ProviderDoctor getReferedDoctorId() {
		return referedDoctorId;
	}
	public void setReferedDoctorId(ProviderDoctor referedDoctorId) {
		this.referedDoctorId = referedDoctorId;
	}
	@Column(name="refered_doctor_name")
	public String getReferedDoctorName() {
		return referedDoctorName;
	}
	public void setReferedDoctorName(String referedDoctorName) {
		this.referedDoctorName = referedDoctorName;
	}
	@Column(name="electronic_ref_number")
	public String getElectronicRefNumber() {
		return electronicRefNumber;
	}
	public void setElectronicRefNumber(String electronicRefNumber) {
		this.electronicRefNumber = electronicRefNumber;
	}
	@Column(name="case_referal_number")
	public String getCaseReferalNumber() {
		return caseReferalNumber;
	}
	public void setCaseReferalNumber(String caseReferalNumber) {
		this.caseReferalNumber = caseReferalNumber;
	}
	@Column(name="pre_remaining_limit")
	public Double getPreRemainingLimit() {
		return preRemainingLimit;
	}
	public void setPreRemainingLimit(Double preRemainingLimit) {
		this.preRemainingLimit = preRemainingLimit;
	}
	@Column(name="post_remaining_limit")
	public Double getPostRemainingLimit() {
		return postRemainingLimit;
	}
	public void setPostRemainingLimit(Double postRemainingLimit) {
		this.postRemainingLimit = postRemainingLimit;
	}
	@Column(name="ex_gratia_notes")
	public String getExGratiaNotes() {
		return exGratiaNotes;
	}
	public void setExGratiaNotes(String exGratiaNotes) {
		this.exGratiaNotes = exGratiaNotes;
	}
	@Column(name="is_ex_gratia")
	public Integer getIsExGratia() {
		return isExGratia;
	}
	public void setIsExGratia(Integer isExGratia) {
		this.isExGratia = isExGratia;
	}
	@Column(name="ex_gratia_document_url")
	public String getExGratiaDocumentURL() {
		return exGratiaDocumentURL;
	}
	public void setExGratiaDocumentURL(String exGratiaDocumentURL) {
		this.exGratiaDocumentURL = exGratiaDocumentURL;
	}
	@ManyToOne
	@JoinColumn(name="assignee_id")
	public User getAssigneeId() {
		return assigneeId;
	}
	public void setAssigneeId(User assigneeId) {
		this.assigneeId = assigneeId;
	}
	@Column(name="assignment_note")
	public String getAssignmentNote() {
		return assignmentNote;
	}
	public void setAssignmentNote(String assignmentNote) {
		this.assignmentNote = assignmentNote;
	}
	@Column(name="is_gl_issued")
	public Integer getIsGLIssued() {
		return isGLIssued;
	}
	public void setIsGLIssued(Integer isGLIssued) {
		this.isGLIssued = isGLIssued;
	}
	@Column(name="current_card_number")
	public String getCurrentCardNumber() {
		return currentCardNumber;
	}
	public void setCurrentCardNumber(String currentCardNumber) {
		this.currentCardNumber = currentCardNumber;
	}
	@ManyToOne
	@JoinColumn(name="secondary_status")
	public CaseStatus getSecondaryStatus() {
		return secondaryStatus;
	}
	public void setSecondaryStatus(CaseStatus secondaryStatus) {
		this.secondaryStatus = secondaryStatus;
	}
	@ManyToOne
	@JoinColumn(name="terminal_id")
	public EdcTerminal getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(EdcTerminal terminalId) {
		this.terminalId = terminalId;
	}
	@Column(name="merchant_id")
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	@Column(name="issued_date")
	public Date getIssuedDate() {
		return issuedDate;
	}
	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}
	@Column(name="declined_date")
	public Date getDeclinedDate() {
		return declinedDate;
	}
	public void setDeclinedDate(Date declinedDate) {
		this.declinedDate = declinedDate;
	}
	
	@Column(name="case_status_name")
	public String getCaseStatusName() {
		return caseStatusName;
	}
	public void setCaseStatusName(String caseStatusName) {
		this.caseStatusName = caseStatusName;
	}
	@Column(name="member_name")
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	@Column(name="member_number")
	public String getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}
	public String toHistoryString(){
		
		
		String result = "Case Number = " + caseNumber + " \n";
		
		if (providerId != null){
			result += "Provider Name = " + providerId.getProviderName() + "\n";
		}
		
		if (this.diagnosis1Id != null){
			result += "Diagnosis 1 Name = " + diagnosis1Name + " [" + diagnosis1Code +"] \n";
		}
		if (this.diagnosis2Id != null){
			result += "Diagnosis 2 Name = " + diagnosis2Name + " [" + diagnosis2Code +"] \n";
		}
		if (this.diagnosis3Id != null){
			result += "Diagnosis 3 Name = " + diagnosis3Name + " [" + diagnosis3Code +"] \n";
		}
		
		
		result += "Member Name = " + memberName + " Number = " + memberNumber + "\n";
		
		if (this.currentCardNumber != null){
			result  += "Card Number = " + currentCardNumber + "\n";
		}
		
		if (this.terminalId != null){
			result  += "Terminal ID = " + terminalId + "\n";
		}
		if (this.roomAndBoard != null){
			result  += "Room and Board = " + roomAndBoard + "\n";
		}
		if (this.anamnesa != null){
			result  += "Anamnesa = " + anamnesa + "\n";
		}
		if (caseClaimValue != null){
			result  += "Charge Value = " + caseClaimValue + "\n";
		}
		if (caseApprovedValue != null){
			result  += "Approved Value = " + caseApprovedValue + "\n";
		}
		if (caseExcessValue != null){
			result  += "Excess Value = " + caseExcessValue + "\n";
		}
		
		if (caseStartTime != null){
			result  += "Case Start Time = " + caseStartTime.toString() + "\n";
		}
		if (caseEndTime != null) {
			result += "Case End Time = " + caseEndTime.toString() + "\n";
		}
		if (caseStatusId != null) {
			result += "Case Status = " + caseStatusName +"\n";
		}
		return result;
	}
	@Column(name="diagnosis1_code")
	public String getDiagnosis1Code() {
		return diagnosis1Code;
	}
	public void setDiagnosis1Code(String diagnosis1Code) {
		this.diagnosis1Code = diagnosis1Code;
	}
	@Column(name="diagnosis1_name")
	public String getDiagnosis1Name() {
		return diagnosis1Name;
	}
	public void setDiagnosis1Name(String diagnosis1Name) {
		this.diagnosis1Name = diagnosis1Name;
	}
	@Column(name="diagnosis2_code")
	public String getDiagnosis2Code() {
		return diagnosis2Code;
	}
	public void setDiagnosis2Code(String diagnosis2Code) {
		this.diagnosis2Code = diagnosis2Code;
	}
	@Column(name="diagnosis2_name")
	public String getDiagnosis2Name() {
		return diagnosis2Name;
	}
	public void setDiagnosis2Name(String diagnosis2Name) {
		this.diagnosis2Name = diagnosis2Name;
	}
	@Column(name="diagnosis3_code")
	public String getDiagnosis3Code() {
		return diagnosis3Code;
	}
	public void setDiagnosis3Code(String diagnosis3Code) {
		this.diagnosis3Code = diagnosis3Code;
	}
	@Column(name="diagnosis3_name")
	public String getDiagnosis3Name() {
		return diagnosis3Name;
	}
	public void setDiagnosis3Name(String diagnosis3Name) {
		this.diagnosis3Name = diagnosis3Name;
	}
	@Column(name="provider_name")
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	@Column(name="manual_registration")
	public Integer getManualRegistration() {
		return manualRegistration;
	}
	public void setManualRegistration(Integer manualRegistration) {
		this.manualRegistration = manualRegistration;
	}
	
	//Add by aju on 20150805, for COB :d
	@Column(name="is_linked_member")
	public Integer getIsLinkedMember() {
		return isLinkedMember;
	}
	public void setIsLinkedMember(Integer isLinkedMember) {
		this.isLinkedMember = isLinkedMember;
	}
	
	@Column(name="threshold_value")
	public Double getThresholdValue() {
		return thresholdValue;
	}
	public void setThresholdValue(Double thresholdValue) {
		this.thresholdValue = thresholdValue;
	}
	@Column(name="ina_cbg_treatment_rate")
	public Double getInaCbgTreatmentRate() {
		return inaCbgTreatmentRate;
	}
	public void setInaCbgTreatmentRate(Double inaCbgTreatmentRate) {
		this.inaCbgTreatmentRate = inaCbgTreatmentRate;
	}
	@Column(name="ina_cbg_alos")
	public Double getInaCbgAlos() {
		return inaCbgAlos;
	}
	public void setInaCbgAlos(Double inaCbgAlos) {
		this.inaCbgAlos = inaCbgAlos;
	}
	
	@Column(name = "has_complication")
	public Integer getHasComplication() {
		return hasComplication;
	}
	public void setHasComplication(Integer hasComplication) {
		this.hasComplication = hasComplication;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="member_product_id")
	public MemberProduct getMemberProductId() {
		return memberProductId;
	}
	public void setMemberProductId(MemberProduct memberProductId) {
		this.memberProductId = memberProductId;
	}

	

}