
package com.ametis.cms.datamodel;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="role_action")
public class RoleAction implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- role_action.role_action_id --------- 
 schema        = null
 tableName     = role_action
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
	private Integer roleActionId;
									
	/**data for the column :
	
 --------- role_action.hapus --------- 
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
 ordinal       = 4
 size          = 1
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer hapus;
			
	/**data for the column :
	
 --------- role_action.baca --------- 
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
 ordinal       = 5
 size          = 1
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer baca;
			
	/**data for the column :
	
 --------- role_action.ubah --------- 
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
 ordinal       = 6
 size          = 1
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer ubah;
			
	/**data for the column :
	
 --------- role_action.tambah --------- 
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
 ordinal       = 7
 size          = 1
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer tambah;
			
	/**data for the column :
	
 --------- role_action.created_by --------- 
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
 ordinal       = 8
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- role_action.created_time --------- 
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
 ordinal       = 9
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- role_action.modified_by --------- 
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
 ordinal       = 10
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- role_action.modified_time --------- 
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
 ordinal       = 11
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
		
	// foreign affairs
	
			/** Action
	data for the foreign class :
	
 --------- action.action_id --------- 
 schema        = null
 tableName     = action
 foreignCol    = action_id
 foreignTab    = role_action
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
	private Action actionId;
				/** Role
	data for the foreign class :
	
 --------- role.role_id --------- 
 schema        = null
 tableName     = role
 foreignCol    = role_id
 foreignTab    = role_action
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
	private Role roleId;
			
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="role_action_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getRoleActionId(){
		return roleActionId;
	}
	public void setRoleActionId(java.lang.Integer value){
		roleActionId = value;
	}
			// PK GETTER SETTER END

												@Column(name="hapus")
	public java.lang.Integer getHapus(){
		return hapus;
	}
	public void setHapus(java.lang.Integer value){
		hapus = value;
	}
				@Column(name="baca")
	public java.lang.Integer getBaca(){
		return baca;
	}
	public void setBaca(java.lang.Integer value){
		baca = value;
	}
				@Column(name="ubah")
	public java.lang.Integer getUbah(){
		return ubah;
	}
	public void setUbah(java.lang.Integer value){
		ubah = value;
	}
				@Column(name="tambah")
	public java.lang.Integer getTambah(){
		return tambah;
	}
	public void setTambah(java.lang.Integer value){
		tambah = value;
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
		
	// foreign affairs
	
			/** Action */
	@ManyToOne
	@JoinColumn(name="action_id")
	public Action getActionId(){
		return this.actionId;
	}

	/** Action */
	public void setActionId(Action obj){
		this.actionId = obj;
	}
				/** Role */
	@ManyToOne
	@JoinColumn(name="role_id")
	public Role getRoleId(){
		return this.roleId;
	}

	/** Role */
	public void setRoleId(Role obj){
		this.roleId = obj;
	}
			
	// foreign affairs end

// exported key

	
		
	//exported key end



}