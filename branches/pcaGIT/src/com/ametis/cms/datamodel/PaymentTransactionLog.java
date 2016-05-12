
package com.ametis.cms.datamodel;


import java.sql.*;
import java.sql.Date;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="payment_transaction_log")
public class PaymentTransactionLog implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- payment_transaction_log.id_proses --------- 
 schema        = public
 tableName     = payment_transaction_log
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = nextval('payment_transaction_log_id_proses_seq'::regclass)
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 10
 type          = 4 
 isPrimaryKey  = true

=========================================


*/
	private Integer idProses;
			
	/**data for the column :
	
 --------- payment_transaction_log.claim_number --------- 
 schema        = public
 tableName     = payment_transaction_log
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 20
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String claimNumber;
			
	/**data for the column :
	
 --------- payment_transaction_log.no_voucher_number --------- 
 schema        = public
 tableName     = payment_transaction_log
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 30
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String noVoucherNumber;
			
	/**data for the column :
	
 --------- payment_transaction_log.payment_date --------- 
 schema        = public
 tableName     = payment_transaction_log
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = null
 decDigits     = 6
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 29
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp paymentDate;
			
	/**data for the column :
	
 --------- payment_transaction_log.currency --------- 
 schema        = public
 tableName     = payment_transaction_log
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 30
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String currency;
			
	/**data for the column :
	
 --------- payment_transaction_log.amount_paid --------- 
 schema        = public
 tableName     = payment_transaction_log
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = null
 decDigits     = 17
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 17
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double amountPaid;
			
	/**data for the column :
	
 --------- payment_transaction_log.bank_name --------- 
 schema        = public
 tableName     = payment_transaction_log
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String bankName;
			
	/**data for the column :
	
 --------- payment_transaction_log.account_name --------- 
 schema        = public
 tableName     = payment_transaction_log
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
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
	private String accountName;
			
	/**data for the column :
	
 --------- payment_transaction_log.bank_code --------- 
 schema        = public
 tableName     = payment_transaction_log
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 10
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer bankCode;
			
	/**data for the column :
	
 --------- payment_transaction_log.account_number --------- 
 schema        = public
 tableName     = payment_transaction_log
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 10
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer accountNumber;
		
	private Date createdDate;
	
	private String createdBy;
	
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="id_proses")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public java.lang.Integer getIdProses(){
		return idProses;
	}
	public void setIdProses(java.lang.Integer value){
		idProses = value;
	}
			// PK GETTER SETTER END

						@Column(name="claim_number")
	public java.lang.String getClaimNumber(){
		return claimNumber;
	}
	public void setClaimNumber(java.lang.String value){
		claimNumber = value;
	}
				@Column(name="no_voucher_number")
	public java.lang.String getNoVoucherNumber(){
		return noVoucherNumber;
	}
	public void setNoVoucherNumber(java.lang.String value){
		noVoucherNumber = value;
	}
				@Column(name="payment_date")
	public java.sql.Timestamp getPaymentDate(){
		return paymentDate;
	}
	public void setPaymentDate(java.sql.Timestamp value){
		paymentDate = value;
	}
				@Column(name="currency")
	public java.lang.String getCurrency(){
		return currency;
	}
	public void setCurrency(java.lang.String value){
		currency = value;
	}
				@Column(name="amount_paid")
	public java.lang.Double getAmountPaid(){
		return amountPaid;
	}
	public void setAmountPaid(java.lang.Double value){
		amountPaid = value;
	}
				@Column(name="bank_name")
	public java.lang.String getBankName(){
		return bankName;
	}
	public void setBankName(java.lang.String value){
		bankName = value;
	}
				@Column(name="account_name")
	public java.lang.String getAccountName(){
		return accountName;
	}
	public void setAccountName(java.lang.String value){
		accountName = value;
	}
				@Column(name="bank_code")
	public java.lang.Integer getBankCode(){
		return bankCode;
	}
	public void setBankCode(java.lang.Integer value){
		bankCode = value;
	}
				@Column(name="account_number")
	public java.lang.Integer getAccountNumber(){
		return accountNumber;
	}
	public void setAccountNumber(java.lang.Integer value){
		accountNumber = value;
	}
	
	@Column(name="created_date")
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	@Column(name="created_by")
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
		
	//exported key end



}