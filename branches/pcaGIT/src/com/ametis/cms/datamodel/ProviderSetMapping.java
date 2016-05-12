
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="provider_set_mapping")
public class ProviderSetMapping implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- provider_set_mapping.provider_set_mapping_id --------- 
 schema        = null
 tableName     = provider_set_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	private Integer providerSetMappingId;
						
	/**data for the column :
	
 --------- provider_set_mapping.provider_id --------- 
 schema        = null
 tableName     = provider_set_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 10
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Provider providerId;
			
	/**data for the column :
	
 --------- provider_set_mapping.provider_name --------- 
 schema        = null
 tableName     = provider_set_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	private String providerName;
			
	/**data for the column :
	
 --------- provider_set_mapping.provider_code --------- 
 schema        = null
 tableName     = provider_set_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String providerCode;
			
	/**data for the column :
	
 --------- provider_set_mapping.city --------- 
 schema        = null
 tableName     = provider_set_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 100
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String city;
			
	/**data for the column :
	
 --------- provider_set_mapping.province --------- 
 schema        = null
 tableName     = provider_set_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 100
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String province;
			
	/**data for the column :
	
 --------- provider_set_mapping.address --------- 
 schema        = null
 tableName     = provider_set_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	private String address;
			
	/**data for the column :
	
 --------- provider_set_mapping.countery --------- 
 schema        = null
 tableName     = provider_set_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 100
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String countery;
			
	/**data for the column :
	
 --------- provider_set_mapping.status --------- 
 schema        = null
 tableName     = provider_set_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer status;
			
	/**data for the column :
	
 --------- provider_set_mapping.created_time --------- 
 schema        = null
 tableName     = provider_set_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = on update CURRENT_TIMESTAMP
 defaultValue  = CURRENT_TIMESTAMP
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 11
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- provider_set_mapping.created_by --------- 
 schema        = null
 tableName     = provider_set_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	
 --------- provider_set_mapping.modified_time --------- 
 schema        = null
 tableName     = provider_set_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 13
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- provider_set_mapping.modified_by --------- 
 schema        = null
 tableName     = provider_set_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	
 --------- provider_set_mapping.deleted_time --------- 
 schema        = null
 tableName     = provider_set_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 15
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- provider_set_mapping.deleted_by --------- 
 schema        = null
 tableName     = provider_set_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	
 --------- provider_set_mapping.deleted_status --------- 
 schema        = null
 tableName     = provider_set_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 17
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
		
	// foreign affairs
	
			/** ProviderSet
	data for the foreign class :
	
 --------- provider_set.provider_set_id --------- 
 schema        = null
 tableName     = provider_set
 foreignCol    = provider_set_id
 foreignTab    = provider_set_mapping
 catalog       = insura-master
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 10
 type          = 4 
 isPrimaryKey  = true

=========================================



	 */
	private ProviderSet providerSetId;
			
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="provider_set_mapping_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getProviderSetMappingId(){
		return providerSetMappingId;
	}
	public void setProviderSetMappingId(java.lang.Integer value){
		providerSetMappingId = value;
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
				@Column(name="provider_name")
	public java.lang.String getProviderName(){
		return providerName;
	}
	public void setProviderName(java.lang.String value){
		providerName = value;
	}
				@Column(name="provider_code")
	public java.lang.String getProviderCode(){
		return providerCode;
	}
	public void setProviderCode(java.lang.String value){
		providerCode = value;
	}
				@Column(name="city")
	public java.lang.String getCity(){
		return city;
	}
	public void setCity(java.lang.String value){
		city = value;
	}
				@Column(name="province")
	public java.lang.String getProvince(){
		return province;
	}
	public void setProvince(java.lang.String value){
		province = value;
	}
				@Column(name="address")
	public java.lang.String getAddress(){
		return address;
	}
	public void setAddress(java.lang.String value){
		address = value;
	}
				@Column(name="countery")
	public java.lang.String getCountery(){
		return countery;
	}
	public void setCountery(java.lang.String value){
		countery = value;
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
		
	// foreign affairs
	
			/** ProviderSet */
	@ManyToOne
	@JoinColumn(name="provider_set_id")
	public ProviderSet getProviderSetId(){
		return this.providerSetId;
	}

	/** ProviderSet */
	public void setProviderSetId(ProviderSet obj){
		this.providerSetId = obj;
	}
			
	// foreign affairs end

// exported key

	
		
	//exported key end



}