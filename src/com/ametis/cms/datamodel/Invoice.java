
package com.ametis.cms.datamodel;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="invoice")
public class Invoice implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	public static final int POLICY_INVOICE = 2;
	public static final int EXCESS_INVOICE = 1;
	public static final int FEE_INVOICE = 0;
	
	
	

	//Fields
		
	/**data for the column :
	
 --------- invoice.invoice_id --------- 
 schema        = null
 tableName     = invoice
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
	private Integer invoiceId;
			
	/**data for the column :
	
 --------- invoice.invoice_date --------- 
 schema        = null
 tableName     = invoice
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date invoiceDate;
			
	/**data for the column :
	
 --------- invoice.invoice_due_date --------- 
 schema        = null
 tableName     = invoice
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
	private java.sql.Date invoiceDueDate;
			
	/**data for the column :
	
 --------- invoice.invoice_value --------- 
 schema        = null
 tableName     = invoice
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double invoiceValue;
			
	/**data for the column :
	
 --------- invoice.invoice_status --------- 
 schema        = null
 tableName     = invoice
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private PaymentStatus invoiceStatus;
			
	/**data for the column :
	
 --------- invoice.approval_time --------- 
 schema        = null
 tableName     = invoice
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
	private java.sql.Timestamp approvalTime;
			
	/**data for the column :
	
 --------- invoice.approved_by --------- 
 schema        = null
 tableName     = invoice
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer approvedBy;
			
	/**data for the column :
	
 --------- invoice.description --------- 
 schema        = null
 tableName     = invoice
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
	private String description;
			
	/**data for the column :
	
 --------- invoice.created_time --------- 
 schema        = null
 tableName     = invoice
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
	private String createdBy;
	private java.sql.Timestamp deletedTime;
	private String deletedBy;
			
	/**data for the column :
	
 --------- invoice.modified_time --------- 
 schema        = null
 tableName     = invoice
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
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- invoice.modified_by --------- 
 schema        = null
 tableName     = invoice
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
	private String modifiedBy;
			
	/**data for the column :
	
 --------- invoice.deleted_status --------- 
 schema        = null
 tableName     = invoice
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
	private Integer deletedStatus;
	private Policy policyId;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
			/** InvoiceItem
	data for the exported class :
	
 --------- invoice_item.invoice_id --------- 
 schema        = null
 tableName     = invoice_item
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
 this columns points to  = invoice.invoice_id

=========================================



	 */
	private Set <InvoiceItem> invoiceItems = new HashSet(0);
	
	private MemberGroup memberGroupId;
	private String invoiceNumber;
	private Integer invoiceType;
	private String approvalSignature;
	private String billDescription;
	private String createSignature;
	private Double outstanding;
	private Double paid;
	private	java.sql.Date lastPaymentDate;
	private Client clientId;
	private ClientContract contractId;
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
	@Id
	@Column(name="invoice_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getInvoiceId(){
		return invoiceId;
	}
	public void setInvoiceId(java.lang.Integer value){
		invoiceId = value;
	}
			// PK GETTER SETTER END

						@Column(name="invoice_date")
	public java.sql.Date getInvoiceDate(){
		return invoiceDate;
	}
	public void setInvoiceDate(java.sql.Date value){
		invoiceDate = value;
	}
				@Column(name="invoice_due_date")
	public java.sql.Date getInvoiceDueDate(){
		return invoiceDueDate;
	}
	public void setInvoiceDueDate(java.sql.Date value){
		invoiceDueDate = value;
	}
				@Column(name="invoice_value")
	public java.lang.Double getInvoiceValue(){
		return invoiceValue;
	}
	public void setInvoiceValue(java.lang.Double value){
		invoiceValue = value;
	}
	
	@ManyToOne
	@JoinColumn(name="invoice_status")
	public PaymentStatus getInvoiceStatus(){
		return invoiceStatus;
	}
	public void setInvoiceStatus(PaymentStatus value){
		invoiceStatus = value;
	}
				@Column(name="approval_time")
	public java.sql.Timestamp getApprovalTime(){
		return approvalTime;
	}
	public void setApprovalTime(java.sql.Timestamp value){
		approvalTime = value;
	}
				@Column(name="approved_by")
	public java.lang.Integer getApprovedBy(){
		return approvedBy;
	}
	public void setApprovedBy(java.lang.Integer value){
		approvedBy = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
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
	
		
	// foreign affairs end

// exported key

	
			/** InvoiceItem */
	@OneToMany(mappedBy="invoiceId")
	public Set<InvoiceItem> getInvoiceItems(){
		return this.invoiceItems;
	}

	/** InvoiceItem */
	public void setInvoiceItems(Set<InvoiceItem> obj){
		this.invoiceItems = obj;
	}
	@ManyToOne
	@JoinColumn(name="member_group_id")
	public MemberGroup getMemberGroupId() {
		return memberGroupId;
	}
	public void setMemberGroupId(MemberGroup memberGroupId) {
		this.memberGroupId = memberGroupId;
	}
	@Column(name="invoice_number")
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	@Column(name="invoice_type")
	public Integer getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
	}
	@Column(name="approval_signature")
	public String getApprovalSignature() {
		return approvalSignature;
	}
	public void setApprovalSignature(String approvalSignature) {
		this.approvalSignature = approvalSignature;
	}
	
	@Column(name="bill_description")
	public String getBillDescription() {
		return billDescription;
	}
	public void setBillDescription(String billDescription) {
		this.billDescription = billDescription;
	}
	@Column(name="create_signature")
	public String getCreateSignature() {
		return createSignature;
	}
	public void setCreateSignature(String createSignature) {
		this.createSignature = createSignature;
	}
	@Column(name="outstanding")
	public Double getOutstanding() {
		return outstanding;
	}
	public void setOutstanding(Double outstanding) {
		this.outstanding = outstanding;
	}
	@Column(name="paid")
	public Double getPaid() {
		return paid;
	}
	public void setPaid(Double paid) {
		this.paid = paid;
	}
	@Column(name="last_payment_date")
	public java.sql.Date getLastPaymentDate() {
		return lastPaymentDate;
	}
	public void setLastPaymentDate(java.sql.Date lastPaymentDate) {
		this.lastPaymentDate = lastPaymentDate;
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
	@JoinColumn(name="client_id")
	public Client getClientId() {
		return clientId;
	}
	public void setClientId(Client clientId) {
		this.clientId = clientId;
	}
	@ManyToOne
	@JoinColumn(name="contract_id")
	public ClientContract getContractId() {
		return contractId;
	}
	public void setContractId(ClientContract contractId) {
		this.contractId = contractId;
	}
			
	
	
	
	//exported key end
	


}