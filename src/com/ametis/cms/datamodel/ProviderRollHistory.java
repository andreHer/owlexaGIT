
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;

import org.hibernate.annotations.Generated;


@Entity
@Table(name="provider_roll_history")
public class ProviderRollHistory implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- provider_roll_history.provider_roll_history_id --------- 
 schema        = public
 tableName     = provider_roll_history
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = nextval('provider_roll_history_provider_roll_history_id_seq'::regclass)
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 10
 type          = 4 
 isPrimaryKey  = true

=========================================


*/
	private Integer providerRollHistoryId;
			
	/**data for the column :
	
 --------- provider_roll_history.provider_id --------- 
 schema        = public
 tableName     = provider_roll_history
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
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
	private Provider providerId;
			
	/**data for the column :
	
 --------- provider_roll_history.delivery_date --------- 
 schema        = public
 tableName     = provider_roll_history
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 13
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date deliveryDate;
			
	/**data for the column :
	
 --------- provider_roll_history.roll_stock_amount --------- 
 schema        = public
 tableName     = provider_roll_history
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 10
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer rollStockAmount;
			
	/**data for the column :
	
 --------- provider_roll_history.created_time --------- 
 schema        = public
 tableName     = provider_roll_history
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = null
 decDigits     = 6
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 29
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- provider_roll_history.created_by --------- 
 schema        = public
 tableName     = provider_roll_history
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- provider_roll_history.modified_time --------- 
 schema        = public
 tableName     = provider_roll_history
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = null
 decDigits     = 6
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 29
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- provider_roll_history.modified_by --------- 
 schema        = public
 tableName     = provider_roll_history
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
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
	private String modifiedBy;
			
	/**data for the column :
	
 --------- provider_roll_history.deleted_status --------- 
 schema        = public
 tableName     = provider_roll_history
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 10
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- provider_roll_history.deleted_time --------- 
 schema        = public
 tableName     = provider_roll_history
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = null
 decDigits     = 6
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 29
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- provider_roll_history.deleted_by --------- 
 schema        = public
 tableName     = provider_roll_history
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="provider_roll_history_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public java.lang.Integer getProviderRollHistoryId(){
		return providerRollHistoryId;
	}
	public void setProviderRollHistoryId(java.lang.Integer value){
		providerRollHistoryId = value;
	}
			// PK GETTER SETTER END

	@ManyToOne
	@JoinColumn(name="provider_id")
	public Provider getProviderId(){
		return providerId;
	}
	public void setProviderId(Provider value){
		providerId = value;
	}
				@Column(name="delivery_date")
	public java.sql.Date getDeliveryDate(){
		return deliveryDate;
	}
	public void setDeliveryDate(java.sql.Date value){
		deliveryDate = value;
	}
				@Column(name="roll_stock_amount")
	public java.lang.Integer getRollStockAmount(){
		return rollStockAmount;
	}
	public void setRollStockAmount(java.lang.Integer value){
		rollStockAmount = value;
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
				@Column(name="deleted_status")
	public java.lang.Integer getDeletedStatus(){
		return deletedStatus;
	}
	public void setDeletedStatus(java.lang.Integer value){
		deletedStatus = value;
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
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
		
	//exported key end



}