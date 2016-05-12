
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
@Table(name="batch_claim")
public class BatchClaim implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	public static final int BATCH_OPEN = 1;
	public static final int COMPLETE = 12;
	public static final int FINALIZED = 15;
	public static final int PAID = 6;
	public static final int UNREGISTERED = 14;
	public static final int CLOSED = 5;
	public static final int INSTALLMENT_PAYMENT = 16;
	public static final int PAYMENT_ISSUED = 13;
	public static final int CORRECTION = -1;
	
	
	public static final int CASCADE_VOID = 1;
	public static final int VOID_TRANSFER = 2;
	
	private String batchNumberCounter;

	//Fields
		
	/**data for the column :
	
 --------- batch_claim.batch_claim_id --------- 
 schema        = null
 tableName     = batch_claim
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
	private Integer batchClaimId;
			
	/**data for the column :
	
 --------- batch_claim.batch_number_psea --------- 
 schema        = null
 tableName     = batch_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private String batchNumberPsea;
			
	/**data for the column :
	
 --------- batch_claim.batch_claim_date_psea --------- 
 schema        = null
 tableName     = batch_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date batchClaimDatePsea;
			
	/**data for the column :
	
 --------- batch_claim.last_update_batch_psea --------- 
 schema        = null
 tableName     = batch_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date lastUpdateBatchPsea;
			
	/**data for the column :
	
 --------- batch_claim.batch_claim_number --------- 
 schema        = null
 tableName     = batch_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private String batchClaimNumber;
			
	/**data for the column :
	
 --------- batch_claim.batch_claim_date --------- 
 schema        = null
 tableName     = batch_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date batchClaimDate;
			
	/**data for the column :
	
 --------- batch_claim.batch_claim_initial_value --------- 
 schema        = null
 tableName     = batch_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private Double batchClaimInitialValue;
			
	/**data for the column :
	
 --------- batch_claim.batch_claim_final_value --------- 
 schema        = null
 tableName     = batch_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double batchClaimFinalValue;
			
	/**data for the column :
	
 --------- batch_claim.batch_claim_paid_value --------- 
 schema        = null
 tableName     = batch_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double batchClaimPaidValue;
																											
	/**data for the column :
	
 --------- batch_claim.batch_claim_close_date --------- 
 schema        = null
 tableName     = batch_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 18
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date batchClaimCloseDate;
			
	/**data for the column :
	
 --------- batch_claim.description --------- 
 schema        = null
 tableName     = batch_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 19
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String description;
			
	/**data for the column :
	
 --------- batch_claim.total_claim --------- 
 schema        = null
 tableName     = batch_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 20
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer totalClaim;
			
	/**data for the column :
	
 --------- batch_claim.created_time --------- 
 schema        = null
 tableName     = batch_claim
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
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- batch_claim.created_by --------- 
 schema        = null
 tableName     = batch_claim
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
	private String createdBy;
			
	/**data for the column :
	
 --------- batch_claim.deleted_time --------- 
 schema        = null
 tableName     = batch_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 23
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- batch_claim.deleted_by --------- 
 schema        = null
 tableName     = batch_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 24
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- batch_claim.modified_time --------- 
 schema        = null
 tableName     = batch_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 25
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- batch_claim.modified_by --------- 
 schema        = null
 tableName     = batch_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 26
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- batch_claim.deleted_status --------- 
 schema        = null
 tableName     = batch_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 27
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- batch_claim.payment_method --------- 
 schema        = null
 tableName     = batch_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 28
 size          = 1
 type          = 1 
 isPrimaryKey  = false

=========================================


*/
	private PaymentMethod paymentMethod;
			
	/**data for the column :
	
 --------- batch_claim.invoice_number --------- 
 schema        = null
 tableName     = batch_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 29
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String invoiceNumber;
			
	/**data for the column :
	
 --------- batch_claim.incomplete_claim --------- 
 schema        = null
 tableName     = batch_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 30
 size          = 4
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer incompleteClaim;
		
	// foreign affairs
	
			/** CaseStatus
	data for the foreign class :
	
 --------- case_status.case_status_id --------- 
 schema        = null
 tableName     = case_status
 foreignCol    = batch_claim_status
 foreignTab    = batch_claim
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
	private CaseStatus batchClaimStatus;
				/** PaymentRecipient
	data for the foreign class :
	
 --------- payment_recipient.payment_recipient_id --------- 
 schema        = null
 tableName     = payment_recipient
 foreignCol    = payment_recipient
 foreignTab    = batch_claim
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
	private PaymentRecipient paymentRecipient;
				/** Member
	data for the foreign class :
	
 --------- member.member_id --------- 
 schema        = null
 tableName     = member
 foreignCol    = member_id
 foreignTab    = batch_claim
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
				/** Client
	data for the foreign class :
	
 --------- client.client_id --------- 
 schema        = null
 tableName     = client
 foreignCol    = client_id
 foreignTab    = batch_claim
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
	private Client clientId;
				/** ClaimType
	data for the foreign class :
	
 --------- claim_type.claim_type_id --------- 
 schema        = null
 tableName     = claim_type
 foreignCol    = batch_claim_type
 foreignTab    = batch_claim
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
	private ClaimType batchClaimType;
				/** MemberGroup
	data for the foreign class :
	
 --------- member_group.member_group_id --------- 
 schema        = null
 tableName     = member_group
 foreignCol    = member_group_id
 foreignTab    = batch_claim
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
	private MemberGroup memberGroupId;
				/** Provider
	data for the foreign class :
	
 --------- provider.provider_id --------- 
 schema        = null
 tableName     = provider
 foreignCol    = provider_id
 foreignTab    = batch_claim
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
	private Provider providerId;
				/** Priority
	data for the foreign class :
	
 --------- priority.priority_id --------- 
 schema        = null
 tableName     = priority
 foreignCol    = priority
 foreignTab    = batch_claim
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
	private Priority priority;
	
	private Currency claimCurrency;
	private Currency paymentCurrency;
	private java.sql.Date batchDueDate;
	private java.sql.Date paymentDate;		
	private java.sql.Date invoiceDate;
	private Integer cobStatus;
	private Date cobRequestDate;
	private Double cobClaimCharge;
	private Double cobClaimPaid;
	
	private String groupName;
	private String providerName;
	private String memberName;
	private String claimerNumber;
	
	private String clientName;
	private ClaimReceiving claimReceivingId;
	// -- foreign affairs end

	// -- exported key

	
			/** Claim
	data for the exported class :
	
 --------- claim.batch_claim_id --------- 
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
 ordinal       = 6
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = batch_claim.batch_claim_id

=========================================



	 */
	@SuppressWarnings("unchecked")
	private Set <Claim> claims = new HashSet(0);
				/** Outstanding
	data for the exported class :
	
 --------- outstanding.batch_claim_id --------- 
 schema        = null
 tableName     = outstanding
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = batch_claim.batch_claim_id

=========================================



	 */
	@SuppressWarnings("unchecked")
	private Set <Outstanding> outstandings = new HashSet(0);
			
	
	private Double batchExcessValue;
	private Double batchPaidExcessValue;
	private Double biayaMaterai;
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
	@Id
	@Column(name="batch_claim_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getBatchClaimId(){
		return batchClaimId;
	}
	public void setBatchClaimId(java.lang.Integer value){
		batchClaimId = value;
	}
			// PK GETTER SETTER END

						@Column(name="batch_number_psea")
	public java.lang.String getBatchNumberPsea(){
		return batchNumberPsea;
	}
	public void setBatchNumberPsea(java.lang.String value){
		batchNumberPsea = value;
	}
				@Column(name="batch_claim_date_psea")
	public java.sql.Date getBatchClaimDatePsea(){
		return batchClaimDatePsea;
	}
	public void setBatchClaimDatePsea(java.sql.Date value){
		batchClaimDatePsea = value;
	}
				@Column(name="last_update_batch_psea")
	public java.sql.Date getLastUpdateBatchPsea(){
		return lastUpdateBatchPsea;
	}
	public void setLastUpdateBatchPsea(java.sql.Date value){
		lastUpdateBatchPsea = value;
	}
				@Column(name="batch_claim_number")
	public java.lang.String getBatchClaimNumber(){
		return batchClaimNumber;
	}
	public void setBatchClaimNumber(java.lang.String value){
		batchClaimNumber = value;
	}
				@Column(name="batch_claim_date")
	public java.sql.Date getBatchClaimDate(){
		return batchClaimDate;
	}
	public void setBatchClaimDate(java.sql.Date value){
		batchClaimDate = value;
	}
				@Column(name="batch_claim_initial_value")
	public java.lang.Double getBatchClaimInitialValue(){
		return batchClaimInitialValue;
	}
	public void setBatchClaimInitialValue(java.lang.Double value){
		batchClaimInitialValue = value;
	}
				@Column(name="batch_claim_final_value")
	public java.lang.Double getBatchClaimFinalValue(){
		return batchClaimFinalValue;
	}
	public void setBatchClaimFinalValue(java.lang.Double value){
		batchClaimFinalValue = value;
	}
				@Column(name="batch_claim_paid_value")
	public java.lang.Double getBatchClaimPaidValue(){
		return batchClaimPaidValue;
	}
	public void setBatchClaimPaidValue(java.lang.Double value){
		batchClaimPaidValue = value;
	}
																												@Column(name="batch_claim_close_date")
	public java.sql.Date getBatchClaimCloseDate(){
		return batchClaimCloseDate;
	}
	public void setBatchClaimCloseDate(java.sql.Date value){
		batchClaimCloseDate = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
				@Column(name="total_claim")
	public java.lang.Integer getTotalClaim(){
		return totalClaim;
	}
	public void setTotalClaim(java.lang.Integer value){
		totalClaim = value;
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
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="payment_method")
	public PaymentMethod getPaymentMethod(){
		return paymentMethod;
	}
	public void setPaymentMethod(PaymentMethod value){
		paymentMethod = value;
	}
				@Column(name="invoice_number")
	public java.lang.String getInvoiceNumber(){
		return invoiceNumber;
	}
	public void setInvoiceNumber(java.lang.String value){
		invoiceNumber = value;
	}
				@Column(name="incomplete_claim")
	public java.lang.Integer getIncompleteClaim(){
		return incompleteClaim;
	}
	public void setIncompleteClaim(java.lang.Integer value){
		incompleteClaim = value;
	}
	
		
	// foreign affairs
	@Column(name="batch_due_date") 
	public java.sql.Date getBatchDueDate() {
		return batchDueDate;
	}
	public void setBatchDueDate(java.sql.Date batchDueDate) {
		this.batchDueDate = batchDueDate;
	}
			/** CaseStatus */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="batch_claim_status")
	public CaseStatus getBatchClaimStatus(){
		return this.batchClaimStatus;
	}

	/** CaseStatus */
	public void setBatchClaimStatus(CaseStatus obj){
		this.batchClaimStatus = obj;
	}
				/** PaymentRecipient */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="payment_recipient")
	public PaymentRecipient getPaymentRecipient(){
		return this.paymentRecipient;
	}

	/** PaymentRecipient */
	public void setPaymentRecipient(PaymentRecipient obj){
		this.paymentRecipient = obj;
	}
				/** Member */
	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name="member_id" , nullable=true)	
	public Member getMemberId(){
		return this.memberId;
	}

	/** Member */
	public void setMemberId(Member obj){
		this.memberId = obj;
	}
				/** Client */
	@ManyToOne
	@JoinColumn(name="client_id")
	public Client getClientId(){
		return this.clientId;
	}

	/** Client */
	public void setClientId(Client obj){
		this.clientId = obj;
	}
				/** ClaimType */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="batch_claim_type")
	public ClaimType getBatchClaimType(){
		return this.batchClaimType;
	}

	/** ClaimType */
	public void setBatchClaimType(ClaimType obj){
		this.batchClaimType = obj;
	}
				/** MemberGroup */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="member_group_id", nullable=true)	
	public MemberGroup getMemberGroupId(){
		return this.memberGroupId;
	}

	/** MemberGroup */
	public void setMemberGroupId(MemberGroup obj){
		this.memberGroupId = obj;
	}
				/** Provider */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="provider_id", nullable=true)
	public Provider getProviderId(){
		return this.providerId;
	}

	/** Provider */
	public void setProviderId(Provider obj){
		this.providerId = obj;
	}
				/** Priority */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="priority")
	public Priority getPriority(){
		return this.priority;
	}

	/** Priority */
	public void setPriority(Priority obj){
		this.priority = obj;
	}
			
	// foreign affairs end

// exported key

	
			/** Claim */
	@OneToMany(mappedBy="batchClaimId")
	public Set<Claim> getClaims(){
		return this.claims;
	}

	/** Claim */
	public void setClaims(Set<Claim> obj){
		this.claims = obj;
	}
				/** Outstanding */
	@OneToMany(mappedBy="batchClaimId")
	public Set<Outstanding> getOutstandings(){
		return this.outstandings;
	}

	/** Outstanding */
	public void setOutstandings(Set<Outstanding> obj){
		this.outstandings = obj;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="claim_currency")	
	public Currency getClaimCurrency() {
		return claimCurrency;
	}
	public void setClaimCurrency(Currency claimCurrency) {
		this.claimCurrency = claimCurrency;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="payment_currency")
	public Currency getPaymentCurrency() {
		return paymentCurrency;
	}
	public void setPaymentCurrency(Currency paymentCurrency) {
		this.paymentCurrency = paymentCurrency;
	}
	@Column(name="batch_claim_excess_value")
	public Double getBatchExcessValue() {
		return batchExcessValue;
	}
	public void setBatchExcessValue(Double batchExcessValue) {
		this.batchExcessValue = batchExcessValue;
	}
	@Column(name="batch_paid_excess_value")
	public Double getBatchPaidExcessValue() {
		return batchPaidExcessValue;
	}
	public void setBatchPaidExcessValue(Double batchPaidExcessValue) {
		this.batchPaidExcessValue = batchPaidExcessValue;
	}
	@Column(name="batch_payment_date")
	public java.sql.Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(java.sql.Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	@Column(name="biaya_materai")
	public Double getBiayaMaterai() {
		return biayaMaterai;
	}
	public void setBiayaMaterai(Double biayaMaterai) {
		this.biayaMaterai = biayaMaterai;
	}
	@Column(name="invoice_date")
	public java.sql.Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(java.sql.Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	
	public BatchClaim clone (){
		BatchClaim batchClaim = new BatchClaim();
		
		batchClaim.setBatchClaimCloseDate(batchClaimCloseDate);
		batchClaim.setBatchClaimDate(batchClaimDate);
		batchClaim.setBatchClaimDatePsea(batchClaimDatePsea);
		batchClaim.setBatchClaimFinalValue(batchClaimFinalValue);
		batchClaim.setBatchClaimId(batchClaimId);
		batchClaim.setBatchClaimInitialValue(batchClaimInitialValue);
		batchClaim.setBatchClaimNumber(batchClaimNumber);
		batchClaim.setBatchClaimPaidValue(batchClaimPaidValue);
		batchClaim.setBatchClaimStatus(batchClaimStatus);
		batchClaim.setBatchClaimType(batchClaimType);
		batchClaim.setBatchDueDate(batchDueDate);
		batchClaim.setBatchExcessValue(batchExcessValue);
		batchClaim.setBatchNumberPsea(batchNumberPsea);
		batchClaim.setBatchPaidExcessValue(batchPaidExcessValue);
		batchClaim.setBiayaMaterai(biayaMaterai);
		batchClaim.setClaimCurrency(claimCurrency);
		batchClaim.setClaims(claims);
		batchClaim.setClientId(clientId);
		batchClaim.setCreatedBy(createdBy);
		batchClaim.setCreatedTime(createdTime);
		batchClaim.setDeletedBy(deletedBy);
		batchClaim.setDeletedStatus(deletedStatus);
		batchClaim.setDeletedTime(deletedTime);
		batchClaim.setDescription(description);
		batchClaim.setIncompleteClaim(incompleteClaim);
		batchClaim.setInvoiceDate(invoiceDate);
		batchClaim.setInvoiceNumber(invoiceNumber);
		batchClaim.setLastUpdateBatchPsea(lastUpdateBatchPsea);
		batchClaim.setMemberGroupId(memberGroupId);
		batchClaim.setMemberId(memberId);
		batchClaim.setModifiedBy(modifiedBy);
		batchClaim.setModifiedTime(modifiedTime);
		batchClaim.setOutstandings(outstandings);
		batchClaim.setPaymentCurrency(paymentCurrency);
		batchClaim.setPaymentDate(paymentDate);
		batchClaim.setPaymentMethod(paymentMethod);
		batchClaim.setPaymentRecipient(paymentRecipient);
		batchClaim.setPriority(priority);
		batchClaim.setProviderId(providerId);
		batchClaim.setTotalClaim(totalClaim);
		
		
		return batchClaim;
	}
	@Column(name="batch_number_counter")
	public String getBatchNumberCounter() {
		return batchNumberCounter;
	}
	public void setBatchNumberCounter(String batchNumberCounter) {
		this.batchNumberCounter = batchNumberCounter;
	}
	@Column(name="cob_status")
	public Integer getCobStatus() {
		return cobStatus;
	}
	public void setCobStatus(Integer cobStatus) {
		this.cobStatus = cobStatus;
	}
	@Column(name="cob_request_date")
	public Date getCobRequestDate() {
		return cobRequestDate;
	}
	public void setCobRequestDate(Date cobRequestDate) {
		this.cobRequestDate = cobRequestDate;
	}
	@Column(name="cob_claim_charge")
	public Double getCobClaimCharge() {
		return cobClaimCharge;
	}
	public void setCobClaimCharge(Double cobClaimCharge) {
		this.cobClaimCharge = cobClaimCharge;
	}
	@Column(name="cob_claim_paid")
	public Double getCobClaimPaid() {
		return cobClaimPaid;
	}
	public void setCobClaimPaid(Double cobClaimPaid) {
		this.cobClaimPaid = cobClaimPaid;
	}
	@Column(name="group_name")
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	@Column(name="provider_name")
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	@Column(name="member_name")
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	@Column(name="client_name")
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	@Column(name="claimer_number")
	public String getClaimerNumber() {
		return claimerNumber;
	}
	public void setClaimerNumber(String claimerNumber) {
		this.claimerNumber = claimerNumber;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="claim_receiving_id")
	public ClaimReceiving getClaimReceivingId() {
		return claimReceivingId;
	}
	public void setClaimReceivingId(ClaimReceiving claimReceivingId) {
		this.claimReceivingId = claimReceivingId;
	}
	
	
	
	//exported key end



}