
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="item_code_client_mapping")
public class ItemCodeClientMapping implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- item_code_client_mapping.item_code_client_mapping_id --------- 
 schema        = null
 tableName     = item_code_client_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	private Integer itemCodeClientMappingId;
			
	/**data for the column :
	
 --------- item_code_client_mapping.client_id --------- 
 schema        = null
 tableName     = item_code_client_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	private Client clientId;
			
	/**data for the column :
	
 --------- item_code_client_mapping.client_item_code --------- 
 schema        = null
 tableName     = item_code_client_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	private String clientItemCode;
			
	/**data for the column :
	
 --------- item_code_client_mapping.item_name --------- 
 schema        = null
 tableName     = item_code_client_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String itemName;
			
	/**data for the column :
	
 --------- item_code_client_mapping.item_category_id --------- 
 schema        = null
 tableName     = item_code_client_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	private ItemCategory itemCategoryId;
			
	/**data for the column :
	
 --------- item_code_client_mapping.description --------- 
 schema        = null
 tableName     = item_code_client_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	private String description;
			
	/**data for the column :
	
 --------- item_code_client_mapping.item_code --------- 
 schema        = null
 tableName     = item_code_client_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	private String itemCode;
			
	/**data for the column :
	
 --------- item_code_client_mapping.created_time --------- 
 schema        = null
 tableName     = item_code_client_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	
 --------- item_code_client_mapping.created_by --------- 
 schema        = null
 tableName     = item_code_client_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	
 --------- item_code_client_mapping.modified_time --------- 
 schema        = null
 tableName     = item_code_client_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	
 --------- item_code_client_mapping.modified_by --------- 
 schema        = null
 tableName     = item_code_client_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	
 --------- item_code_client_mapping.deleted_time --------- 
 schema        = null
 tableName     = item_code_client_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	
 --------- item_code_client_mapping.deleted_by --------- 
 schema        = null
 tableName     = item_code_client_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	
 --------- item_code_client_mapping.deleted_status --------- 
 schema        = null
 tableName     = item_code_client_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="item_code_client_mapping_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public java.lang.Integer getItemCodeClientMappingId(){
		return itemCodeClientMappingId;
	}
	public void setItemCodeClientMappingId(java.lang.Integer value){
		itemCodeClientMappingId = value;
	}
			// PK GETTER SETTER END

	@ManyToOne
	@JoinColumn(name="client_id")
	public Client getClientId(){
		return clientId;
	}
	public void setClientId(Client value){
		clientId = value;
	}
				@Column(name="client_item_code")
	public java.lang.String getClientItemCode(){
		return clientItemCode;
	}
	public void setClientItemCode(java.lang.String value){
		clientItemCode = value;
	}
				@Column(name="item_name")
	public java.lang.String getItemName(){
		return itemName;
	}
	public void setItemName(java.lang.String value){
		itemName = value;
	}
	@ManyToOne
	@JoinColumn(name="item_category_id")
	public ItemCategory getItemCategoryId(){
		return itemCategoryId;
	}
	public void setItemCategoryId(ItemCategory value){
		itemCategoryId = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
				@Column(name="item_code")
	public java.lang.String getItemCode(){
		return itemCode;
	}
	public void setItemCode(java.lang.String value){
		itemCode = value;
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