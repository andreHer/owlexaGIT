
package com.ametis.cms.datamodel;


import java.sql.*;
import java.sql.Date;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="payment_installment")
public class PaymentInstallment implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- payment_installment.payment_installment_id --------- 
 schema        = null
 tableName     = payment_installment
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
 type          = -5 
 isPrimaryKey  = true

=========================================


*/
	private Long paymentInstallmentId;
			
	/**data for the column :
	
 --------- payment_installment.payment_id --------- 
 schema        = null
 tableName     = payment_installment
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
	private Payment paymentId;
			
	/**data for the column :
	
 --------- payment_installment.installment_number --------- 
 schema        = null
 tableName     = payment_installment
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String installmentNumber;
			
	/**data for the column :
	
 --------- payment_installment.description --------- 
 schema        = null
 tableName     = payment_installment
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
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
	private String description;
			
	/**data for the column :
	
 --------- payment_installment.total_value --------- 
 schema        = null
 tableName     = payment_installment
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
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
	private Double totalValue;
			
	/**data for the column :
	
 --------- payment_installment.total_claim --------- 
 schema        = null
 tableName     = payment_installment
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 5
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer totalClaim;
			
	/**data for the column :
	
 --------- payment_installment.status --------- 
 schema        = null
 tableName     = payment_installment
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer status;
			
	/**data for the column :
	
 --------- payment_installment.created_time --------- 
 schema        = null
 tableName     = payment_installment
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
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
	
 --------- payment_installment.created_by --------- 
 schema        = null
 tableName     = payment_installment
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
	private String createdBy;
			
	/**data for the column :
	
 --------- payment_installment.modified_time --------- 
 schema        = null
 tableName     = payment_installment
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
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
	
 --------- payment_installment.modified_by --------- 
 schema        = null
 tableName     = payment_installment
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
	private String modifiedBy;
			
	/**data for the column :
	
 --------- payment_installment.deleted_time --------- 
 schema        = null
 tableName     = payment_installment
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
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
	
 --------- payment_installment.deleted_by --------- 
 schema        = null
 tableName     = payment_installment
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
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
	private String deletedBy;
			
	/**data for the column :
	
 --------- payment_installment.deleted_status --------- 
 schema        = null
 tableName     = payment_installment
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
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
	private Date paymentDate;
	private Date dispositionDate;
	private PaymentBatch paymentBatchId;
	private Integer paymentBatchingStatus;
	private String approvalNote;
	private String approvedBy;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="payment_installment_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Long getPaymentInstallmentId(){
		return paymentInstallmentId;
	}
	public void setPaymentInstallmentId(java.lang.Long value){
		paymentInstallmentId = value;
	}
			// PK GETTER SETTER END

	@ManyToOne
	@JoinColumn(name="payment_id")
	public Payment getPaymentId(){
		return paymentId;
	}
	public void setPaymentId(Payment value){
		paymentId = value;
	}
				@Column(name="installment_number")
	public java.lang.String getInstallmentNumber(){
		return installmentNumber;
	}
	public void setInstallmentNumber(java.lang.String value){
		installmentNumber = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
				@Column(name="total_value")
	public java.lang.Double getTotalValue(){
		return totalValue;
	}
	public void setTotalValue(java.lang.Double value){
		totalValue = value;
	}
				@Column(name="total_claim")
	public java.lang.Integer getTotalClaim(){
		return totalClaim;
	}
	public void setTotalClaim(java.lang.Integer value){
		totalClaim = value;
	}
				@Column(name="status")
	public java.lang.Integer getStatus(){
		return status;
	}
	public void setStatus(java.lang.Integer value){
		status = value;
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
	@Column(name="payment_date")
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	@Column(name="disposition_date")
	public Date getDispositionDate() {
		return dispositionDate;
	}
	public void setDispositionDate(Date dispositionDate) {
		this.dispositionDate = dispositionDate;
	}
	@ManyToOne
	@JoinColumn(name="payment_batch_id")
	public PaymentBatch getPaymentBatchId() {
		return paymentBatchId;
	}
	public void setPaymentBatchId(PaymentBatch paymentBatchId) {
		this.paymentBatchId = paymentBatchId;
	}
	@Column(name="payment_batching_status")
	public Integer getPaymentBatchingStatus() {
		return paymentBatchingStatus;
	}
	public void setPaymentBatchingStatus(Integer paymentBatchingStatus) {
		this.paymentBatchingStatus = paymentBatchingStatus;
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
		
	



}