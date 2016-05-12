
package com.ametis.cms.datamodel;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="role")
public class Role implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	public static final int ADMINISTRATOR = 0;
	public static final int CASE_MANAGEMENT_HEAD = 1;
	public static final int CASE_MANAGEMENT_STAFF = 2;
	public static final int CLAIM_STAFF = 3;
	public static final int CLAIM_HEAD = 4;
	public static final int MEMBERSHIP = 22;
	public static final int CUSTOMER_SERVICE = 5;
	public static final int PROVIDER_RELATION = 6;
	public static final int FINANCE_HEAD = 7;
	public static final int FINANCE_STAFF =8;
	public static final int EXCESS_STAFF = 9;
	public static final int EXCESS_HEAD = 10;
	public static final int MEMBER_GROUP = 11;
	public static final int BROKER = 12;
	public static final int BRANCH = 13;
	public static final int BUSDEV_STAFF = 14;
	public static final int ADMINISTRATION_STAFF = 15;
	public static final int CLAIM_CHECKER = 16;
	public static final int MEMBER = 17;
	public static final int EDC_HELPDESK = 19;
	//Fields
		
	/**data for the column :
	
 --------- role.role_id --------- 
 schema        = null
 tableName     = role
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = 
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 11
 type          = 4 
 isPrimaryKey  = true

=========================================


*/
	private Integer roleId;
			
	/**data for the column :
	
 --------- role.role_name --------- 
 schema        = null
 tableName     = role
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String roleName;
			
	/**data for the column :
	
 --------- role.description --------- 
 schema        = null
 tableName     = role
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String description;
			
	/**data for the column :
	
 --------- role.created_by --------- 
 schema        = null
 tableName     = role
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- role.created_time --------- 
 schema        = null
 tableName     = role
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- role.modified_by --------- 
 schema        = null
 tableName     = role
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- role.modified_time --------- 
 schema        = null
 tableName     = role
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- role.deleted_by --------- 
 schema        = null
 tableName     = role
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
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
	private String deletedBy;
			
	/**data for the column :
	
 --------- role.deleted_time --------- 
 schema        = null
 tableName     = role
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- role.deleted_status --------- 
 schema        = null
 tableName     = role
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private Integer deletedStatus;
	private String menuTemplateUrl;
	private String sidebarTemplateUrl;
	private UserType userTypeId;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
			/** RoleAction
	data for the exported class :
	
 --------- role_action.role_id --------- 
 schema        = null
 tableName     = role_action
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = role.role_id

=========================================



	 */
	private Set <RoleAction> roleActions = new HashSet(0);
				/** User
	data for the exported class :
	
 --------- user.role_id --------- 
 schema        = null
 tableName     = user
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = role.role_id

=========================================



	 */
	private Set <User> users = new HashSet(0);
			
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="role_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getRoleId(){
		return roleId;
	}
	public void setRoleId(java.lang.Integer value){
		roleId = value;
	}
			// PK GETTER SETTER END

						@Column(name="role_name")
	public java.lang.String getRoleName(){
		return roleName;
	}
	public void setRoleName(java.lang.String value){
		roleName = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
				@Column(name="created_by")
	public java.lang.String getCreatedBy(){
		return createdBy;
	}
	public void setCreatedBy(java.lang.String value){
		createdBy = value;
	}
				@Column(name="created_time")
	public java.sql.Timestamp getCreatedTime(){
		return createdTime;
	}
	public void setCreatedTime(java.sql.Timestamp value){
		createdTime = value;
	}
				@Column(name="modified_by")
	public java.lang.String getModifiedBy(){
		return modifiedBy;
	}
	public void setModifiedBy(java.lang.String value){
		modifiedBy = value;
	}
				@Column(name="modified_time")
	public java.sql.Timestamp getModifiedTime(){
		return modifiedTime;
	}
	public void setModifiedTime(java.sql.Timestamp value){
		modifiedTime = value;
	}
				@Column(name="deleted_by")
	public java.lang.String getDeletedBy(){
		return deletedBy;
	}
	public void setDeletedBy(java.lang.String value){
		deletedBy = value;
	}
				@Column(name="deleted_time")
	public java.sql.Timestamp getDeletedTime(){
		return deletedTime;
	}
	public void setDeletedTime(java.sql.Timestamp value){
		deletedTime = value;
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

	
			/** RoleAction */
	@OneToMany(mappedBy="roleId")
	public Set<RoleAction> getRoleActions(){
		return this.roleActions;
	}

	/** RoleAction */
	public void setRoleActions(Set<RoleAction> obj){
		this.roleActions = obj;
	}
				/** User */
	@OneToMany(mappedBy="roleId")
	public Set<User> getUsers(){
		return this.users;
	}

	/** User */
	public void setUsers(Set<User> obj){
		this.users = obj;
	}
	@Column(name="menu_template_url")
	public String getMenuTemplateUrl() {
		return menuTemplateUrl;
	}
	public void setMenuTemplateUrl(String menuTemplateUrl) {
		this.menuTemplateUrl = menuTemplateUrl;
	}
	@Column(name="sidebar_template_url")
	public String getSidebarTemplateUrl() {
		return sidebarTemplateUrl;
	}
	public void setSidebarTemplateUrl(String sidebarTemplateUrl) {
		this.sidebarTemplateUrl = sidebarTemplateUrl;
	}
	@ManyToOne
	@JoinColumn(name="user_type_id")
	public UserType getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(UserType userTypeId) {
		this.userTypeId = userTypeId;
	}
			
	

}