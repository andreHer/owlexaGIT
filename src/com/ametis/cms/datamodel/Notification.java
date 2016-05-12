
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
@Table(name="notification")
public class Notification implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	public static final int MEMBER_CLAIM_NOTIFICATION_EMAIL = 1;
	public static final int ASO_FUND_WARNING_NOTIFICATION_EMAIL = 2;
	public static final int ASO_FUND_BLOCK_NOTIFICATION_EMAIL = 3;
	
	public static final int EXCESS_FUND_WARNING_NOTIFICATION_EMAIL = 4;
	public static final int EXCESS_FUND_BLOCK_NOTIFICATION_EMAIL = 5;

	//Fields
		
	/**data for the column :
	
 --------- notification.id --------- 
 schema        = null
 tableName     = notification
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 15
 type          = -5 
 isPrimaryKey  = true

=========================================


*/
	private Long id;
			
	/**data for the column :
	
 --------- notification.destination --------- 
 schema        = null
 tableName     = notification
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 150
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String destination;
			
	/**data for the column :
	
 --------- notification.sender --------- 
 schema        = null
 tableName     = notification
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 150
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String sender;
			
	/**data for the column :
	
 --------- notification.content --------- 
 schema        = null
 tableName     = notification
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	private String content;
			
	/**data for the column :
	
 --------- notification.created_time --------- 
 schema        = null
 tableName     = notification
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = on update CURRENT_TIMESTAMP
 defaultValue  = CURRENT_TIMESTAMP
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 5
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- notification.created_by --------- 
 schema        = null
 tableName     = notification
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	
 --------- notification.member_id --------- 
 schema        = null
 tableName     = notification
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 15
 type          = -5 
 isPrimaryKey  = false

=========================================


*/
	private Member memberId;
			
	/**data for the column :
	
 --------- notification.template_id --------- 
 schema        = null
 tableName     = notification
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 5
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private MessageTemplate templateId;
			
	/**data for the column :
	
 --------- notification.client_id --------- 
 schema        = null
 tableName     = notification
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 15
 type          = -5 
 isPrimaryKey  = false

=========================================


*/
	private Client clientId;
	private ContactPerson contactPersonId;
	private BatchClaim batchClaimId;
			
	/**data for the column :
	
 --------- notification.provider_id --------- 
 schema        = null
 tableName     = notification
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 10
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Provider providerId;
	private Policy policyId;
	private PolicyCoverageFund policyCoverageFund;
			
	/**data for the column :
	
 --------- notification.message_type --------- 
 schema        = null
 tableName     = notification
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	private Integer messageType;
			
	/**data for the column :
	
 --------- notification.status --------- 
 schema        = null
 tableName     = notification
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	private Integer status;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Long getId(){
		return id;
	}
	public void setId(java.lang.Long value){
		id = value;
	}
			// PK GETTER SETTER END

						@Column(name="destination")
	public java.lang.String getDestination(){
		return destination;
	}
	public void setDestination(java.lang.String value){
		destination = value;
	}
				@Column(name="sender")
	public java.lang.String getSender(){
		return sender;
	}
	public void setSender(java.lang.String value){
		sender = value;
	}
				@Column(name="content")
	public java.lang.String getContent(){
		return content;
	}
	public void setContent(java.lang.String value){
		content = value;
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
	@ManyToOne
	@JoinColumn(name="member_id")
	public Member getMemberId(){
		return memberId;
	}
	public void setMemberId(Member value){
		memberId = value;
	}
	@ManyToOne
	@JoinColumn(name="template_id")
	public MessageTemplate getTemplateId(){
		return templateId;
	}
	public void setTemplateId(MessageTemplate value){
		templateId = value;
	}
	@ManyToOne
	@JoinColumn(name="client_id")
	public Client getClientId(){
		return clientId;
	}
	public void setClientId(Client value){
		clientId = value;
	}
	@ManyToOne
	@JoinColumn(name="provider_id")
	public Provider getProviderId(){
		return providerId;
	}
	public void setProviderId(Provider value){
		providerId = value;
	}
	@Column(name="message_type")
	public java.lang.Integer getMessageType(){
		return messageType;
	}
	public void setMessageType(java.lang.Integer value){
		messageType = value;
	}
				@Column(name="status")
	public java.lang.Integer getStatus(){
		return status;
	}
	public void setStatus(java.lang.Integer value){
		status = value;
	}
	@ManyToOne
	@JoinColumn(name="batch_claim_id")
	public BatchClaim getBatchClaimId() {
		return batchClaimId;
	}
	public void setBatchClaimId(BatchClaim batchClaimId) {
		this.batchClaimId = batchClaimId;
	}
	@ManyToOne
	@JoinColumn(name="policy_id")	
	public Policy getPolicyId() {
		return policyId;
	}
	public void setPolicyId(Policy policyId) {
		this.policyId = policyId;
	}
	@ManyToOne
	@JoinColumn(name="contact_person_id")
	public ContactPerson getContactPersonId() {
		return contactPersonId;
	}
	public void setContactPersonId(ContactPerson contactPersonId) {
		this.contactPersonId = contactPersonId;
	}
	@ManyToOne
	@JoinColumn(name="policy_coverage_fund_id")
	public PolicyCoverageFund getPolicyCoverageFund() {
		return policyCoverageFund;
	}
	public void setPolicyCoverageFund(PolicyCoverageFund policyCoverageFund) {
		this.policyCoverageFund = policyCoverageFund;
	}
	
		
	
		
	//exported key end



}