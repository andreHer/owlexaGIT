
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="policy_payment")
public class PolicyPayment implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- policy_payment.policy_payment_id --------- 
 schema        = null
 tableName     = policy_payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 11
 type          = -5 
 isPrimaryKey  = true

=========================================


*/
	private Long policyPaymentId;
			
	/**data for the column :
	
 --------- policy_payment.policy_id --------- 
 schema        = null
 tableName     = policy_payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 11
 type          = -5 
 isPrimaryKey  = false

=========================================


*/
	private Policy policyId;
			
	/**data for the column :
	
 --------- policy_payment.scheduled_payment_id --------- 
 schema        = null
 tableName     = policy_payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer scheduledPaymentId;
			
	/**data for the column :
	
 --------- policy_payment.total_amount --------- 
 schema        = null
 tableName     = policy_payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private Double totalAmount;
			
	/**data for the column :
	
 --------- policy_payment.payment_date --------- 
 schema        = null
 tableName     = policy_payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date paymentDate;
			
	/**data for the column :
	
 --------- policy_payment.payment_confirm_date --------- 
 schema        = null
 tableName     = policy_payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private java.sql.Date paymentConfirmDate;
			
	/**data for the column :
	
 --------- policy_payment.confirmed_by --------- 
 schema        = null
 tableName     = policy_payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private String confirmedBy;
			
	/**data for the column :
	
 --------- policy_payment.created_time --------- 
 schema        = null
 tableName     = policy_payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- policy_payment.created_by --------- 
 schema        = null
 tableName     = policy_payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	
 --------- policy_payment.deleted_time --------- 
 schema        = null
 tableName     = policy_payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	
 --------- policy_payment.deleted_by --------- 
 schema        = null
 tableName     = policy_payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	
 --------- policy_payment.deleted_status --------- 
 schema        = null
 tableName     = policy_payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 2
 type          = -6 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- policy_payment.modified_time --------- 
 schema        = null
 tableName     = policy_payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	
 --------- policy_payment.modified_by --------- 
 schema        = null
 tableName     = policy_payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	
 --------- policy_payment.payment_status --------- 
 schema        = null
 tableName     = policy_payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 15
 size          = 2
 type          = -6 
 isPrimaryKey  = false

=========================================


*/
	private Integer paymentStatus;
			
	/**data for the column :
	
 --------- policy_payment.description --------- 
 schema        = null
 tableName     = policy_payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 16
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String description;
			
	/**data for the column :
	
 --------- policy_payment.invoice_id --------- 
 schema        = null
 tableName     = policy_payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 17
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer invoiceId;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="policy_payment_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Long getPolicyPaymentId(){
		return policyPaymentId;
	}
	public void setPolicyPaymentId(java.lang.Long value){
		policyPaymentId = value;
	}
			// PK GETTER SETTER END
@ManyToOne
						@JoinColumn(name="policy_id")
	public Policy getPolicyId(){
		return policyId;
	}
	public void setPolicyId(Policy value){
		policyId = value;
	}
				@Column(name="scheduled_payment_id")
	public java.lang.Integer getScheduledPaymentId(){
		return scheduledPaymentId;
	}
	public void setScheduledPaymentId(java.lang.Integer value){
		scheduledPaymentId = value;
	}
				@Column(name="total_amount")
	public java.lang.Double getTotalAmount(){
		return totalAmount;
	}
	public void setTotalAmount(java.lang.Double value){
		totalAmount = value;
	}
				@Column(name="payment_date")
	public java.sql.Date getPaymentDate(){
		return paymentDate;
	}
	public void setPaymentDate(java.sql.Date value){
		paymentDate = value;
	}
				@Column(name="payment_confirm_date")
	public java.sql.Date getPaymentConfirmDate(){
		return paymentConfirmDate;
	}
	public void setPaymentConfirmDate(java.sql.Date value){
		paymentConfirmDate = value;
	}
				@Column(name="confirmed_by")
	public java.lang.String getConfirmedBy(){
		return confirmedBy;
	}
	public void setConfirmedBy(java.lang.String value){
		confirmedBy = value;
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
				@Column(name="deleted_status")
	public java.lang.Integer getDeletedStatus(){
		return deletedStatus;
	}
	public void setDeletedStatus(java.lang.Integer value){
		deletedStatus = value;
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
				@Column(name="payment_status")
	public java.lang.Integer getPaymentStatus(){
		return paymentStatus;
	}
	public void setPaymentStatus(java.lang.Integer value){
		paymentStatus = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
				@Column(name="invoice_id")
	public java.lang.Integer getInvoiceId(){
		return invoiceId;
	}
	public void setInvoiceId(java.lang.Integer value){
		invoiceId = value;
	}
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
		
	//exported key end



}