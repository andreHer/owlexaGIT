
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="provider_history")
public class ProviderHistory implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- provider_history.provider_history_id --------- 
 schema        = null
 tableName     = provider_history
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
 type          = 4 
 isPrimaryKey  = true

=========================================


*/
	private Integer providerHistoryId;
			
	/**data for the column :
	
 --------- provider_history.provider_id --------- 
 schema        = null
 tableName     = provider_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
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
	private Provider providerId;
			
	/**data for the column :
	
 --------- provider_history.status --------- 
 schema        = null
 tableName     = provider_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer status;
			
	/**data for the column :
	
 --------- provider_history.action_type --------- 
 schema        = null
 tableName     = provider_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
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
	private String actionType;
			
	/**data for the column :
	
 --------- provider_history.action_time --------- 
 schema        = null
 tableName     = provider_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
 remarks       = on update CURRENT_TIMESTAMP
 defaultValue  = CURRENT_TIMESTAMP
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 5
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp actionTime;
			
	/**data for the column :
	
 --------- provider_history.created_time --------- 
 schema        = null
 tableName     = provider_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- provider_history.created_by --------- 
 schema        = null
 tableName     = provider_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
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
	private String createdBy;
			
	/**data for the column :
	
 --------- provider_history.modified_time --------- 
 schema        = null
 tableName     = provider_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
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
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- provider_history.modified_by --------- 
 schema        = null
 tableName     = provider_history
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
	private String modifiedBy;
			
	/**data for the column :
	
 --------- provider_history.deleted_time --------- 
 schema        = null
 tableName     = provider_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
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
	
 --------- provider_history.deleted_by --------- 
 schema        = null
 tableName     = provider_history
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
	private String deletedBy;
			
	/**data for the column :
	
 --------- provider_history.deleted_status --------- 
 schema        = null
 tableName     = provider_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
	private String description;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="provider_history_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public java.lang.Integer getProviderHistoryId(){
		return providerHistoryId;
	}
	public void setProviderHistoryId(java.lang.Integer value){
		providerHistoryId = value;
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
	@Column(name="status")
	public java.lang.Integer getStatus(){
		return status;
	}
	public void setStatus(java.lang.Integer value){
		status = value;
	}
				@Column(name="action_type")
	public java.lang.String getActionType(){
		return actionType;
	}
	public void setActionType(java.lang.String value){
		actionType = value;
	}
				@Column(name="action_time")
	public java.sql.Timestamp getActionTime(){
		return actionTime;
	}
	public void setActionTime(java.sql.Timestamp value){
		actionTime = value;
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
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
		
	


}