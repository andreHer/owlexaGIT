
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="payment_batch")
public class PaymentBatch implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- payment_batch.id --------- 
 schema        = null
 tableName     = payment_batch
 foreignCol    = 
 foreignTab    = 
 catalog       = jasindo
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
	private Integer id;
			
	/**data for the column :
	
 --------- payment_batch.payment_batch_number --------- 
 schema        = null
 tableName     = payment_batch
 foreignCol    = 
 foreignTab    = 
 catalog       = jasindo
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
	private String paymentBatchNumber;
			
	/**data for the column :
	
 --------- payment_batch.payment_batch_date --------- 
 schema        = null
 tableName     = payment_batch
 foreignCol    = 
 foreignTab    = 
 catalog       = jasindo
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
	private java.sql.Date paymentBatchDate;
			
	/**data for the column :
	
 --------- payment_batch.payment_batch_confirm_date --------- 
 schema        = null
 tableName     = payment_batch
 foreignCol    = 
 foreignTab    = 
 catalog       = jasindo
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
	private java.sql.Date paymentBatchConfirmDate;
			
	/**data for the column :
	
 --------- payment_batch.outstanding_payment --------- 
 schema        = null
 tableName     = payment_batch
 foreignCol    = 
 foreignTab    = 
 catalog       = jasindo
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double outstandingPayment;
			
	/**data for the column :
	
 --------- payment_batch.paid_payment --------- 
 schema        = null
 tableName     = payment_batch
 foreignCol    = 
 foreignTab    = 
 catalog       = jasindo
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double paidPayment;
			
	/**data for the column :
	
 --------- payment_batch.total_payment --------- 
 schema        = null
 tableName     = payment_batch
 foreignCol    = 
 foreignTab    = 
 catalog       = jasindo
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
	private Double totalPayment;
			
	/**data for the column :
	
 --------- payment_batch.created_time --------- 
 schema        = null
 tableName     = payment_batch
 foreignCol    = 
 foreignTab    = 
 catalog       = jasindo
 remarks       = on update CURRENT_TIMESTAMP
 defaultValue  = CURRENT_TIMESTAMP
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 8
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- payment_batch.created_by --------- 
 schema        = null
 tableName     = payment_batch
 foreignCol    = 
 foreignTab    = 
 catalog       = jasindo
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 25
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- payment_batch.modified_time --------- 
 schema        = null
 tableName     = payment_batch
 foreignCol    = 
 foreignTab    = 
 catalog       = jasindo
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 10
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- payment_batch.modified_by --------- 
 schema        = null
 tableName     = payment_batch
 foreignCol    = 
 foreignTab    = 
 catalog       = jasindo
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 25
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- payment_batch.deleted_time --------- 
 schema        = null
 tableName     = payment_batch
 foreignCol    = 
 foreignTab    = 
 catalog       = jasindo
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 12
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- payment_batch.deleted_by --------- 
 schema        = null
 tableName     = payment_batch
 foreignCol    = 
 foreignTab    = 
 catalog       = jasindo
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 25
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- payment_batch.deleted_status --------- 
 schema        = null
 tableName     = payment_batch
 foreignCol    = 
 foreignTab    = 
 catalog       = jasindo
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 14
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- payment_batch.batch_status --------- 
 schema        = null
 tableName     = payment_batch
 foreignCol    = 
 foreignTab    = 
 catalog       = jasindo
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 15
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer batchStatus;
	private BankAccount paymentAccountSource;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
			/** Payment
	data for the exported class :
	
 --------- payment.payment_batch_id --------- 
 schema        = null
 tableName     = payment
 foreignCol    = 
 foreignTab    = 
 catalog       = jasindo
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 39
 size          = 15
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = payment_batch.id

=========================================



	 */
	private Set <Payment> payments = new HashSet(0);
	
	private PaymentStatus paymentStatus;
	private String approvalNote;
	private String approvedBy;
	private Timestamp approvedTime;
	
			
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

						@Column(name="payment_batch_number")
	public java.lang.String getPaymentBatchNumber(){
		return paymentBatchNumber;
	}
	public void setPaymentBatchNumber(java.lang.String value){
		paymentBatchNumber = value;
	}
				@Column(name="payment_batch_date")
	public java.sql.Date getPaymentBatchDate(){
		return paymentBatchDate;
	}
	public void setPaymentBatchDate(java.sql.Date value){
		paymentBatchDate = value;
	}
				@Column(name="payment_batch_confirm_date")
	public java.sql.Date getPaymentBatchConfirmDate(){
		return paymentBatchConfirmDate;
	}
	public void setPaymentBatchConfirmDate(java.sql.Date value){
		paymentBatchConfirmDate = value;
	}
				@Column(name="outstanding_payment")
	public java.lang.Double getOutstandingPayment(){
		return outstandingPayment;
	}
	public void setOutstandingPayment(java.lang.Double value){
		outstandingPayment = value;
	}
				@Column(name="paid_payment")
	public java.lang.Double getPaidPayment(){
		return paidPayment;
	}
	public void setPaidPayment(java.lang.Double value){
		paidPayment = value;
	}
				@Column(name="total_payment")
	public java.lang.Double getTotalPayment(){
		return totalPayment;
	}
	public void setTotalPayment(java.lang.Double value){
		totalPayment = value;
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
				@Column(name="batch_status")
	public java.lang.Integer getBatchStatus(){
		return batchStatus;
	}
	public void setBatchStatus(java.lang.Integer value){
		batchStatus = value;
	}
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
			/** Payment */
	@OneToMany(mappedBy="paymentBatchId")
	public Set<Payment> getPayments(){
		return this.payments;
	}

	/** Payment */
	public void setPayments(Set<Payment> obj){
		this.payments = obj;
	}
	
	@ManyToOne
	@JoinColumn(name="payment_account_source")
	public BankAccount getPaymentAccountSource() {
		return paymentAccountSource;
	}
	public void setPaymentAccountSource(BankAccount paymentAccountSource) {
		this.paymentAccountSource = paymentAccountSource;
	}
	@ManyToOne
	@JoinColumn(name="payment_status")
	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	@Column(name="approval_note")
	public String getApprovalNote() {
		return approvalNote;
	}
	public void setApprovalNote(String approvalNote) {
		this.approvalNote = approvalNote;
	}
	@Column(name="approved_by")
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	@Column(name="approved_time")
	public Timestamp getApprovedTime() {
		return approvedTime;
	}
	public void setApprovedTime(Timestamp approvedTime) {
		this.approvedTime = approvedTime;
	}
			
	
	//exported key end

	

}