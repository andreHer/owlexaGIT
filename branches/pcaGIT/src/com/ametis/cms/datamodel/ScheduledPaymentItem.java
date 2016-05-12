
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="scheduled_payment_item")
public class ScheduledPaymentItem implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- scheduled_payment_item.id --------- 
 schema        = null
 tableName     = scheduled_payment_item
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
	
 --------- scheduled_payment_item.scheduled_payment_id --------- 
 schema        = null
 tableName     = scheduled_payment_item
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
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer scheduledPaymentId;
			
	/**data for the column :
	
 --------- scheduled_payment_item.item_id --------- 
 schema        = null
 tableName     = scheduled_payment_item
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
 type          = -5 
 isPrimaryKey  = false

=========================================


*/
	private Long itemId;
			
	/**data for the column :
	
 --------- scheduled_payment_item.item_value --------- 
 schema        = null
 tableName     = scheduled_payment_item
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
	private Double itemValue;
			
	/**data for the column :
	
 --------- scheduled_payment_item.total_item --------- 
 schema        = null
 tableName     = scheduled_payment_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private Double totalItem;
			
	/**data for the column :
	
 --------- scheduled_payment_item.total_value --------- 
 schema        = null
 tableName     = scheduled_payment_item
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
	private Double totalValue;
			
	/**data for the column :
	
 --------- scheduled_payment_item.tax --------- 
 schema        = null
 tableName     = scheduled_payment_item
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
	
 --------- scheduled_payment_item.total_vale_after_tax --------- 
 schema        = null
 tableName     = scheduled_payment_item
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
	private Double totalValeAfterTax;
			
	/**data for the column :
	
 --------- scheduled_payment_item.status --------- 
 schema        = null
 tableName     = scheduled_payment_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 2
 type          = -6 
 isPrimaryKey  = false

=========================================


*/
	private Integer status;
			
	/**data for the column :
	
 --------- scheduled_payment_item.tax_type_id --------- 
 schema        = null
 tableName     = scheduled_payment_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer taxTypeId;
			
	/**data for the column :
	
 --------- scheduled_payment_item.tax_percentage --------- 
 schema        = null
 tableName     = scheduled_payment_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double taxPercentage;
			
	/**data for the column :
	
 --------- scheduled_payment_item.created_time --------- 
 schema        = null
 tableName     = scheduled_payment_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = CURRENT_TIMESTAMP
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- scheduled_payment_item.created_by --------- 
 schema        = null
 tableName     = scheduled_payment_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private String createdBy;
			
	/**data for the column :
	
 --------- scheduled_payment_item.modified_time --------- 
 schema        = null
 tableName     = scheduled_payment_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 14
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- scheduled_payment_item.modified_by --------- 
 schema        = null
 tableName     = scheduled_payment_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 15
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- scheduled_payment_item.deleted_time --------- 
 schema        = null
 tableName     = scheduled_payment_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 16
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- scheduled_payment_item.deleted_by --------- 
 schema        = null
 tableName     = scheduled_payment_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 17
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- scheduled_payment_item.deleted_status --------- 
 schema        = null
 tableName     = scheduled_payment_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 18
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

						@Column(name="scheduled_payment_id")
	public java.lang.Integer getScheduledPaymentId(){
		return scheduledPaymentId;
	}
	public void setScheduledPaymentId(java.lang.Integer value){
		scheduledPaymentId = value;
	}
				@Column(name="item_id")
	public java.lang.Long getItemId(){
		return itemId;
	}
	public void setItemId(java.lang.Long value){
		itemId = value;
	}
				@Column(name="item_value")
	public java.lang.Double getItemValue(){
		return itemValue;
	}
	public void setItemValue(java.lang.Double value){
		itemValue = value;
	}
				@Column(name="total_item")
	public java.lang.Double getTotalItem(){
		return totalItem;
	}
	public void setTotalItem(java.lang.Double value){
		totalItem = value;
	}
				@Column(name="total_value")
	public java.lang.Double getTotalValue(){
		return totalValue;
	}
	public void setTotalValue(java.lang.Double value){
		totalValue = value;
	}
				@Column(name="tax")
	public java.lang.Double getTax(){
		return tax;
	}
	public void setTax(java.lang.Double value){
		tax = value;
	}
				@Column(name="total_vale_after_tax")
	public java.lang.Double getTotalValeAfterTax(){
		return totalValeAfterTax;
	}
	public void setTotalValeAfterTax(java.lang.Double value){
		totalValeAfterTax = value;
	}
				@Column(name="status")
	public java.lang.Integer getStatus(){
		return status;
	}
	public void setStatus(java.lang.Integer value){
		status = value;
	}
				@Column(name="tax_type_id")
	public java.lang.Integer getTaxTypeId(){
		return taxTypeId;
	}
	public void setTaxTypeId(java.lang.Integer value){
		taxTypeId = value;
	}
				@Column(name="tax_percentage")
	public java.lang.Double getTaxPercentage(){
		return taxPercentage;
	}
	public void setTaxPercentage(java.lang.Double value){
		taxPercentage = value;
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