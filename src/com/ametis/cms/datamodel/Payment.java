
package com.ametis.cms.datamodel;


import java.sql.Date;
import java.sql.Timestamp;
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
@Table(name="payment")
public class Payment implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields

	private String paymentCounterNumber;
	/**data for the column :
	
 --------- payment.payment_id --------- 
 schema        = null
 tableName     = payment
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
	private Integer paymentId;
			
	/**data for the column :
	
 --------- payment.payment_value --------- 
 schema        = null
 tableName     = payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double paymentValue;
												
	/**data for the column :
	
 --------- payment.remarks --------- 
 schema        = null
 tableName     = payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String remarks;
			
	/**data for the column :
	
 --------- payment.bank_name --------- 
 schema        = null
 tableName     = payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String bankName;
			
	/**data for the column :
	
 --------- payment.account_number --------- 
 schema        = null
 tableName     = payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String accountNumber;
			
	/**data for the column :
	
 --------- payment.giro_number --------- 
 schema        = null
 tableName     = payment
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
	private String giroNumber;
			
	/**data for the column :
	
 --------- payment.payee_name --------- 
 schema        = null
 tableName     = payment
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
	private String payeeName;
			
	/**data for the column :
	
 --------- payment.payment_time --------- 
 schema        = null
 tableName     = payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date paymentTime;
			
	/**data for the column :
	
 --------- payment.payment_status --------- 
 schema        = null
 tableName     = payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private PaymentStatus paymentStatus;
			
	/**data for the column :
	
 --------- payment.created_time --------- 
 schema        = null
 tableName     = payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- payment.created_by --------- 
 schema        = null
 tableName     = payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 14
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- payment.deleted_time --------- 
 schema        = null
 tableName     = payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 15
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- payment.deleted_by --------- 
 schema        = null
 tableName     = payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 16
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- payment.modified_time --------- 
 schema        = null
 tableName     = payment
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
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- payment.modified_by --------- 
 schema        = null
 tableName     = payment
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
	private String modifiedBy;
			
	/**data for the column :
	
 --------- payment.deleted_status --------- 
 schema        = null
 tableName     = payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 19
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
		
	// foreign affairs
	
			/** Outstanding
	data for the foreign class :
	
 --------- outstanding.outstanding_id --------- 
 schema        = null
 tableName     = outstanding
 foreignCol    = outstanding_id
 foreignTab    = payment
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
	private Outstanding outstandingId;
				/** Member
	data for the foreign class :
	
 --------- member.member_id --------- 
 schema        = null
 tableName     = member
 foreignCol    = member_id
 foreignTab    = payment
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
				/** Provider
	data for the foreign class :
	
 --------- provider.provider_id --------- 
 schema        = null
 tableName     = provider
 foreignCol    = provider_id
 foreignTab    = payment
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
			

	
	private String paymentNumber;
	private String bankBranch;
	private String valueDescription;
	private BatchClaim batchClaim;
	private MemberGroup memberGroup;
	private Date paymentConfirmDate;
	private Double confirmedPaymentValue;
	private Date dispositionDate;
	private String documentBin;
	private String bankCity;
	private String otherPaymentNumber;
	private String otherPaymentNumberCounter;
	
	private String paymentApprovedBy;
	private Timestamp paymentApprovalTime;
	private String approvalSignature;
	
	private String paymentConfirmBy;
	private Timestamp paymentConfirmTime;
	private String paymentConfirmSignature;
	
	private PaymentBatch paymentBatchId;
	private String accountNumberClean;
	private Integer paymentBatchingStatus;
	private Double administrationCost;
	private Double claimPaymentValue;
	
	private Double biayaMaterai;
	private Double discount;
	
	private Set <Claim> claims = new HashSet(0);
			
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="payment_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getPaymentId(){
		return paymentId;
	}
	public void setPaymentId(java.lang.Integer value){
		paymentId = value;
	}
			// PK GETTER SETTER END

	@Column(name="payment_value")
	public java.lang.Double getPaymentValue(){
		return paymentValue;
	}
	public void setPaymentValue(java.lang.Double value){
		paymentValue = value;
	}
	
	@Column(name="remarks")
	public java.lang.String getRemarks(){
		return remarks;
	}
	public void setRemarks(java.lang.String value){
		remarks = value;
	}
	@Column(name="bank_name")
	public java.lang.String getBankName(){
		return bankName;
	}
	public void setBankName(java.lang.String value){
		bankName = value;
	}
	@Column(name="account_number")
	public java.lang.String getAccountNumber(){
		return accountNumber;
	}
	public void setAccountNumber(java.lang.String value){
		accountNumber = value;
	}
	@Column(name="giro_number")
	public java.lang.String getGiroNumber(){
		return giroNumber;
	}
	public void setGiroNumber(java.lang.String value){
		giroNumber = value;
	}
				@Column(name="payee_name")
	public java.lang.String getPayeeName(){
		return payeeName;
	}
	public void setPayeeName(java.lang.String value){
		payeeName = value;
	}
				@Column(name="payment_time")
	public java.sql.Date getPaymentTime(){
		return paymentTime;
	}
	public void setPaymentTime(java.sql.Date value){
		paymentTime = value;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="payment_status")
	public PaymentStatus getPaymentStatus(){
		return paymentStatus;
	}
	public void setPaymentStatus(PaymentStatus value){
		paymentStatus = value;
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
	
			/** Outstanding */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="outstanding_id",nullable=true)
	public Outstanding getOutstandingId(){
		return this.outstandingId;
	}

	/** Outstanding */
	public void setOutstandingId(Outstanding obj){
		this.outstandingId = obj;
	}
				/** Member */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="member_id",nullable=true)
	public Member getMemberId(){
		return this.memberId;
	}

	/** Member */
	public void setMemberId(Member obj){
		this.memberId = obj;
	}
				/** Provider */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="provider_id",nullable=true)
	public Provider getProviderId(){
		return this.providerId;
	}

	/** Provider */
	public void setProviderId(Provider obj){
		this.providerId = obj;
	}
	
			
	// foreign affairs end

// exported key

	@Column (name="payment_number")
	public String getPaymentNumber() {
		return paymentNumber;
	}
	public void setPaymentNumber(String paymentNumber) {
		this.paymentNumber = paymentNumber;
	}
	@Column (name="value_description")
	public String getValueDescription() {
		return valueDescription;
	}
	public void setValueDescription(String valueDescription) {
		this.valueDescription = valueDescription;
	}
			/** Claim */
	@OneToMany(mappedBy="paymentId")
	public Set<Claim> getClaims(){
		return this.claims;
	}

	/** Claim */
	public void setClaims(Set<Claim> obj){
		this.claims = obj;
	}
	@Column(name="bank_branch")
	public String getBankBranch() {
		return bankBranch;
	}
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	@ManyToOne
	@JoinColumn(name="batch_claim_id")
	public BatchClaim getBatchClaim() {
		return batchClaim;
	}
	public void setBatchClaim(BatchClaim batchClaim) {
		this.batchClaim = batchClaim;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="member_group_id")
	public MemberGroup getMemberGroup() {
		return memberGroup;
	}
	public void setMemberGroup(MemberGroup memberGroup) {
		this.memberGroup = memberGroup;
	}
	@Column(name="payment_confirm_date")
	public Date getPaymentConfirmDate() {
		return paymentConfirmDate;
	}
	public void setPaymentConfirmDate(Date paymentConfirmDate) {
		this.paymentConfirmDate = paymentConfirmDate;
	}
	@Column(name="confirmed_payment_value")
	public Double getConfirmedPaymentValue() {
		return confirmedPaymentValue;
	}
	public void setConfirmedPaymentValue(Double confirmedPaymentValue) {
		this.confirmedPaymentValue = confirmedPaymentValue;
	}
	
	public Payment clone (){
		Payment payment = new Payment();
		
		payment.setAccountNumber(accountNumber);
		payment.setBankBranch(bankBranch);
		payment.setBankName(bankName);
		payment.setBatchClaim(batchClaim);
		payment.setClaims(claims);
		payment.setConfirmedPaymentValue(confirmedPaymentValue);
		payment.setCreatedBy(createdBy);
		payment.setCreatedTime(createdTime);
		payment.setDeletedBy(deletedBy);
		payment.setDeletedTime(deletedTime);
		payment.setDeletedStatus(deletedStatus);
		payment.setGiroNumber(giroNumber);
		payment.setMemberGroup(memberGroup);
		payment.setMemberId(memberId);
		payment.setModifiedBy(modifiedBy);
		payment.setModifiedTime(modifiedTime);
		payment.setOutstandingId(outstandingId);
		payment.setPayeeName(payeeName);
		payment.setPaymentConfirmDate(paymentConfirmDate);
		payment.setPaymentId(paymentId);
		payment.setPaymentNumber(paymentNumber);
		payment.setPaymentStatus(paymentStatus);
		payment.setPaymentTime(paymentTime);
		payment.setPaymentValue(paymentValue);
		payment.setProviderId(providerId);
		payment.setRemarks(remarks);
		payment.setValueDescription(valueDescription);
		
		return payment;
	}
	//exported key end
	
	@Column(name="payment_number_counter")
	public String getPaymentCounterNumber() {
		return paymentCounterNumber;
	}
	public void setPaymentCounterNumber(String paymentCounterNumber) {
		this.paymentCounterNumber = paymentCounterNumber;
	}
	
	@Column(name="disposition_date")
	public Date getDispositionDate() {
		return dispositionDate;
	}
	public void setDispositionDate(Date dispositionDate) {
		this.dispositionDate = dispositionDate;
	}
	@Column(name="document_bin")
	public String getDocumentBin() {
		return documentBin;
	}
	public void setDocumentBin(String documentBin) {
		this.documentBin = documentBin;
	}
	@Column(name="bank_city")
	public String getBankCity() {
		return bankCity;
	}
	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}
	@Column(name="other_payment_number")
	public String getOtherPaymentNumber() {
		return otherPaymentNumber;
	}
	public void setOtherPaymentNumber(String otherPaymentNumber) {
		this.otherPaymentNumber = otherPaymentNumber;
	}
	@Column(name="other_payment_number_counter")
	public String getOtherPaymentNumberCounter() {
		return otherPaymentNumberCounter;
	}
	public void setOtherPaymentNumberCounter(String otherPaymentNumberCounter) {
		this.otherPaymentNumberCounter = otherPaymentNumberCounter;
	}
	@Column(name="payment_approved_by")
	public String getPaymentApprovedBy() {
		return paymentApprovedBy;
	}
	public void setPaymentApprovedBy(String paymentApprovedBy) {
		this.paymentApprovedBy = paymentApprovedBy;
	}
	@Column(name="payment_approval_time")
	public Timestamp getPaymentApprovalTime() {
		return paymentApprovalTime;
	}
	public void setPaymentApprovalTime(Timestamp paymentApprovalTime) {
		this.paymentApprovalTime = paymentApprovalTime;
	}
	@Column(name="approval_signature")
	public String getApprovalSignature() {
		return approvalSignature;
	}
	public void setApprovalSignature(String approvalSignature) {
		this.approvalSignature = approvalSignature;
	}
	@Column(name="payment_confirm_by")
	public String getPaymentConfirmBy() {
		return paymentConfirmBy;
	}
	public void setPaymentConfirmBy(String paymentConfirmBy) {
		this.paymentConfirmBy = paymentConfirmBy;
	}
	@Column(name="payment_confirm_time")
	public Timestamp getPaymentConfirmTime() {
		return paymentConfirmTime;
	}
	public void setPaymentConfirmTime(Timestamp paymentConfirmTime) {
		this.paymentConfirmTime = paymentConfirmTime;
	}
	@Column(name="payment_confirm_signature")
	public String getPaymentConfirmSignature() {
		return paymentConfirmSignature;
	}
	public void setPaymentConfirmSignature(String paymentConfirmSignature) {
		this.paymentConfirmSignature = paymentConfirmSignature;
	}
	@ManyToOne
	@JoinColumn(name="payment_batch_id")
	public PaymentBatch getPaymentBatchId() {
		return paymentBatchId;
	}
	public void setPaymentBatchId(PaymentBatch paymentBatchId) {
		this.paymentBatchId = paymentBatchId;
	}
	
	@Column(name="account_number_clean")
	public String getAccountNumberClean() {
		return accountNumberClean;
	}
	public void setAccountNumberClean(String accountNumberClean) {
		this.accountNumberClean = accountNumberClean;
	}
	@Column(name="payment_batching_status")
	public Integer getPaymentBatchingStatus() {
		return paymentBatchingStatus;
	}
	public void setPaymentBatchingStatus(Integer paymentBatchingStatus) {
		this.paymentBatchingStatus = paymentBatchingStatus;
	}
	@Column(name="administration_cost")
	public Double getAdministrationCost() {
		return administrationCost;
	}
	public void setAdministrationCost(Double administrationCost) {
		this.administrationCost = administrationCost;
	}
	@Column(name="claim_payment_value")
	public Double getClaimPaymentValue() {
		return claimPaymentValue;
	}
	public void setClaimPaymentValue(Double claimPaymentValue) {
		this.claimPaymentValue = claimPaymentValue;
	}
	@Column(name="materai")
	public Double getBiayaMaterai() {
		return biayaMaterai;
	}
	public void setBiayaMaterai(Double biayaMaterai) {
		this.biayaMaterai = biayaMaterai;
	}
	@Column(name="discount")
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	
	


}