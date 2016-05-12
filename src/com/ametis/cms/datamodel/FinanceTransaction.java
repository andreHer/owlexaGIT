
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="finance_transaction")
public class FinanceTransaction implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- finance_transaction.id --------- 
 schema        = null
 tableName     = finance_transaction
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
 type          = 4 
 isPrimaryKey  = true

=========================================


*/
	private Integer id;
			
	/**data for the column :
	
 --------- finance_transaction.transaction_number --------- 
 schema        = null
 tableName     = finance_transaction
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private String transactionNumber;
			
	/**data for the column :
	
 --------- finance_transaction.account_code --------- 
 schema        = null
 tableName     = finance_transaction
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private String accountCode;
			
	/**data for the column :
	
 --------- finance_transaction.account_name --------- 
 schema        = null
 tableName     = finance_transaction
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 100
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String accountName;
			
	/**data for the column :
	
 --------- finance_transaction.description --------- 
 schema        = null
 tableName     = finance_transaction
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	
 --------- finance_transaction.value --------- 
 schema        = null
 tableName     = finance_transaction
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private Double value;
			
	/**data for the column :
	
 --------- finance_transaction.debet_value --------- 
 schema        = null
 tableName     = finance_transaction
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private Double debetValue;
			
	/**data for the column :
	
 --------- finance_transaction.credit_value --------- 
 schema        = null
 tableName     = finance_transaction
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private Double creditValue;
			
	/**data for the column :
	
 --------- finance_transaction.account_id --------- 
 schema        = null
 tableName     = finance_transaction
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer accountId;
			
	/**data for the column :
	
 --------- finance_transaction.reference_code --------- 
 schema        = null
 tableName     = finance_transaction
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 150
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String referenceCode;
			
	/**data for the column :
	
 --------- finance_transaction.created_time --------- 
 schema        = null
 tableName     = finance_transaction
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = CURRENT_TIMESTAMP
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- finance_transaction.created_by --------- 
 schema        = null
 tableName     = finance_transaction
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- finance_transaction.modified_time --------- 
 schema        = null
 tableName     = finance_transaction
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
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
	
 --------- finance_transaction.modified_by --------- 
 schema        = null
 tableName     = finance_transaction
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
	
 --------- finance_transaction.deleted_time --------- 
 schema        = null
 tableName     = finance_transaction
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
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
	
 --------- finance_transaction.deleted_by --------- 
 schema        = null
 tableName     = finance_transaction
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	
 --------- finance_transaction.deleted_status --------- 
 schema        = null
 tableName     = finance_transaction
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 17
 size          = 2
 type          = -6 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
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

						@Column(name="transaction_number")
	public java.lang.String getTransactionNumber(){
		return transactionNumber;
	}
	public void setTransactionNumber(java.lang.String value){
		transactionNumber = value;
	}
				@Column(name="account_code")
	public java.lang.String getAccountCode(){
		return accountCode;
	}
	public void setAccountCode(java.lang.String value){
		accountCode = value;
	}
				@Column(name="account_name")
	public java.lang.String getAccountName(){
		return accountName;
	}
	public void setAccountName(java.lang.String value){
		accountName = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
				@Column(name="value")
	public java.lang.Double getValue(){
		return value;
	}
	public void setValue(java.lang.Double value){
		value = value;
	}
				@Column(name="debet_value")
	public java.lang.Double getDebetValue(){
		return debetValue;
	}
	public void setDebetValue(java.lang.Double value){
		debetValue = value;
	}
				@Column(name="credit_value")
	public java.lang.Double getCreditValue(){
		return creditValue;
	}
	public void setCreditValue(java.lang.Double value){
		creditValue = value;
	}
				@Column(name="account_id")
	public java.lang.Integer getAccountId(){
		return accountId;
	}
	public void setAccountId(java.lang.Integer value){
		accountId = value;
	}
				@Column(name="reference_code")
	public java.lang.String getReferenceCode(){
		return referenceCode;
	}
	public void setReferenceCode(java.lang.String value){
		referenceCode = value;
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
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
		
	//exported key end



}