
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="scheduled_payment")
public class ScheduledPayment implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- scheduled_payment.id --------- 
 schema        = null
 tableName     = scheduled_payment
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
	
 --------- scheduled_payment.schedule_date --------- 
 schema        = null
 tableName     = scheduled_payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private java.sql.Date scheduleDate;
			
	/**data for the column :
	
 --------- scheduled_payment.number --------- 
 schema        = null
 tableName     = scheduled_payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 100
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String number;
			
	/**data for the column :
	
 --------- scheduled_payment.description --------- 
 schema        = null
 tableName     = scheduled_payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	
 --------- scheduled_payment.tipe --------- 
 schema        = null
 tableName     = scheduled_payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 2
 type          = -6 
 isPrimaryKey  = false

=========================================


*/
	private Integer tipe;
			
	/**data for the column :
	
 --------- scheduled_payment.total_amount --------- 
 schema        = null
 tableName     = scheduled_payment
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
	private Double totalAmount;
			
	/**data for the column :
	
 --------- scheduled_payment.tax --------- 
 schema        = null
 tableName     = scheduled_payment
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
	private Double tax;
			
	/**data for the column :
	
 --------- scheduled_payment.total_after_tax --------- 
 schema        = null
 tableName     = scheduled_payment
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
	private Double totalAfterTax;
			
	/**data for the column :
	
 --------- scheduled_payment.created_time --------- 
 schema        = null
 tableName     = scheduled_payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = CURRENT_TIMESTAMP
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
			
	/**data for the column :
	
 --------- scheduled_payment.created_by --------- 
 schema        = null
 tableName     = scheduled_payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- scheduled_payment.modified_time --------- 
 schema        = null
 tableName     = scheduled_payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- scheduled_payment.modified_by --------- 
 schema        = null
 tableName     = scheduled_payment
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
	private String modifiedBy;
			
	/**data for the column :
	
 --------- scheduled_payment.deleted_time --------- 
 schema        = null
 tableName     = scheduled_payment
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
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- scheduled_payment.deleted_by --------- 
 schema        = null
 tableName     = scheduled_payment
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
 type          = 1111 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- scheduled_payment.deleted_status --------- 
 schema        = null
 tableName     = scheduled_payment
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
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- scheduled_payment.policy_id --------- 
 schema        = null
 tableName     = scheduled_payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 16
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer policyId;
			
	/**data for the column :
	
 --------- scheduled_payment.expire_date --------- 
 schema        = null
 tableName     = scheduled_payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 17
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date expireDate;
			
	/**data for the column :
	
 --------- scheduled_payment.schedule_generated_date --------- 
 schema        = null
 tableName     = scheduled_payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private java.sql.Date scheduleGeneratedDate;
			
	/**data for the column :
	
 --------- scheduled_payment.status --------- 
 schema        = null
 tableName     = scheduled_payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 19
 size          = 2
 type          = -6 
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
	public java.lang.Integer getId(){
		return id;
	}
	public void setId(java.lang.Integer value){
		id = value;
	}
			// PK GETTER SETTER END

						@Column(name="schedule_date")
	public java.sql.Date getScheduleDate(){
		return scheduleDate;
	}
	public void setScheduleDate(java.sql.Date value){
		scheduleDate = value;
	}
				@Column(name="number")
	public java.lang.String getNumber(){
		return number;
	}
	public void setNumber(java.lang.String value){
		number = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
				@Column(name="tipe")
	public java.lang.Integer getTipe(){
		return tipe;
	}
	public void setTipe(java.lang.Integer value){
		tipe = value;
	}
				@Column(name="total_amount")
	public java.lang.Double getTotalAmount(){
		return totalAmount;
	}
	public void setTotalAmount(java.lang.Double value){
		totalAmount = value;
	}
				@Column(name="tax")
	public java.lang.Double getTax(){
		return tax;
	}
	public void setTax(java.lang.Double value){
		tax = value;
	}
				@Column(name="total_after_tax")
	public java.lang.Double getTotalAfterTax(){
		return totalAfterTax;
	}
	public void setTotalAfterTax(java.lang.Double value){
		totalAfterTax = value;
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
	public String getDeletedBy(){
		return deletedBy;
	}
	public void setDeletedBy(String value){
		deletedBy = value;
	}
				@Column(name="deleted_status")
	public java.lang.Integer getDeletedStatus(){
		return deletedStatus;
	}
	public void setDeletedStatus(java.lang.Integer value){
		deletedStatus = value;
	}
				@Column(name="policy_id")
	public java.lang.Integer getPolicyId(){
		return policyId;
	}
	public void setPolicyId(java.lang.Integer value){
		policyId = value;
	}
				@Column(name="expire_date")
	public java.sql.Date getExpireDate(){
		return expireDate;
	}
	public void setExpireDate(java.sql.Date value){
		expireDate = value;
	}
				@Column(name="schedule_generated_date")
	public java.sql.Date getScheduleGeneratedDate(){
		return scheduleGeneratedDate;
	}
	public void setScheduleGeneratedDate(java.sql.Date value){
		scheduleGeneratedDate = value;
	}
				@Column(name="status")
	public java.lang.Integer getStatus(){
		return status;
	}
	public void setStatus(java.lang.Integer value){
		status = value;
	}
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
		
	//exported key end



}