
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="user_group_assignment")
public class UserGroupAssignment implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- user_group_assignment.id --------- 
 schema        = null
 tableName     = user_group_assignment
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
	
 --------- user_group_assignment.user_id --------- 
 schema        = null
 tableName     = user_group_assignment
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
	private Integer userId;
			
	/**data for the column :
	
 --------- user_group_assignment.member_group_id --------- 
 schema        = null
 tableName     = user_group_assignment
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
	private Integer memberGroupId;
			
	/**data for the column :
	
 --------- user_group_assignment.created_time --------- 
 schema        = null
 tableName     = user_group_assignment
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = on update CURRENT_TIMESTAMP
 defaultValue  = CURRENT_TIMESTAMP
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 4
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- user_group_assignment.created_by --------- 
 schema        = null
 tableName     = user_group_assignment
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
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
	private String createdBy;
			
	/**data for the column :
	
 --------- user_group_assignment.modified_time --------- 
 schema        = null
 tableName     = user_group_assignment
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 6
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- user_group_assignment.modified_by --------- 
 schema        = null
 tableName     = user_group_assignment
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 30
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- user_group_assignment.deleted_time --------- 
 schema        = null
 tableName     = user_group_assignment
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 8
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- user_group_assignment.deleted_by --------- 
 schema        = null
 tableName     = user_group_assignment
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 30
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- user_group_assignment.deleted_status --------- 
 schema        = null
 tableName     = user_group_assignment
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 2
 type          = -6 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- user_group_assignment.status --------- 
 schema        = null
 tableName     = user_group_assignment
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
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

						@Column(name="user_id")
	public java.lang.Integer getUserId(){
		return userId;
	}
	public void setUserId(java.lang.Integer value){
		userId = value;
	}
				@Column(name="member_group_id")
	public java.lang.Integer getMemberGroupId(){
		return memberGroupId;
	}
	public void setMemberGroupId(java.lang.Integer value){
		memberGroupId = value;
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