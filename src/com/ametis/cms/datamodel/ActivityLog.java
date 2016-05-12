
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
@Table(name="activity_log")
public class ActivityLog implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- activity_log.activity_log_id --------- 
 schema        = null
 tableName     = activity_log
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = 
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 15
 type          = 4 
 isPrimaryKey  = true

=========================================


*/
	private Integer activityLogId;
			
	/**data for the column :
	
 --------- activity_log.username --------- 
 schema        = null
 tableName     = activity_log
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String username;
			
	/**data for the column :
	
 --------- activity_log.action_url --------- 
 schema        = null
 tableName     = activity_log
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String actionUrl;
			
	/**data for the column :
	
 --------- activity_log.action --------- 
 schema        = null
 tableName     = activity_log
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String action;
			
	/**data for the column :
	
 --------- activity_log.description --------- 
 schema        = null
 tableName     = activity_log
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
	
 --------- activity_log.activity_log_time --------- 
 schema        = null
 tableName     = activity_log
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
	private java.sql.Timestamp activityLogTime;
	private String ipAddress;
	private String sessionId;
	private String actionQuery;
	private Member memberId;
	private Provider providerId;
	private MemberGroup memberGroupId;
	private Claim claimId;
	private Payment paymentId;
	private Case caseId;
	private ExcessCharge excessId;
	private Policy policyId;
	
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="activity_log_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getActivityLogId(){
		return activityLogId;
	}
	public void setActivityLogId(java.lang.Integer value){
		activityLogId = value;
	}
			// PK GETTER SETTER END

						@Column(name="username")
	public java.lang.String getUsername(){
		return username;
	}
	public void setUsername(java.lang.String value){
		username = value;
	}
				@Column(name="action_url")
	public java.lang.String getActionUrl(){
		return actionUrl;
	}
	public void setActionUrl(java.lang.String value){
		actionUrl = value;
	}
				@Column(name="action")
	public java.lang.String getAction(){
		return action;
	}
	public void setAction(java.lang.String value){
		action = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
				@Column(name="activity_log_time")
	public java.sql.Timestamp getActivityLogTime(){
		return activityLogTime;
	}
	public void setActivityLogTime(java.sql.Timestamp value){
		activityLogTime = value;
	}
	@Column(name="ip_address")
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	@Column(name="session_id")
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	@Column(name="request_query")
	public String getActionQuery() {
		return actionQuery;
	}
	public void setActionQuery(String actionQuery) {
		this.actionQuery = actionQuery;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="member_id")
	public Member getMemberId() {
		return memberId;
	}
	public void setMemberId(Member memberId) {
		this.memberId = memberId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="provider_id")
	public Provider getProviderId() {
		return providerId;
	}
	public void setProviderId(Provider providerId) {
		this.providerId = providerId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="member_group_id")
	public MemberGroup getMemberGroupId() {
		return memberGroupId;
	}
	public void setMemberGroupId(MemberGroup memberGroupId) {
		this.memberGroupId = memberGroupId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="claim_id")
	public Claim getClaimId() {
		return claimId;
	}
	public void setClaimId(Claim claimId) {
		this.claimId = claimId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="payment_id")
	public Payment getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Payment paymentId) {
		this.paymentId = paymentId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="case_id")
	public Case getCaseId() {
		return caseId;
	}
	public void setCaseId(Case caseId) {
		this.caseId = caseId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="excess_charge_id")
	public ExcessCharge getExcessId() {
		return excessId;
	}
	public void setExcessId(ExcessCharge excessId) {
		this.excessId = excessId;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="policy_id")
	public Policy getPolicyId() {
		return policyId;
	}
	public void setPolicyId(Policy policyId) {
		this.policyId = policyId;
	}
		
	
	


}