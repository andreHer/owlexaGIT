
package com.ametis.cms.datamodel;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="clausul_category")
public class ClausulCategory implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	public static final int CLAIM_ACTIVATION_CLAUSUL = 1;
	public static final int DIAGNOSIS_CLAUSUL = 2;
	public static final int MEDICINE_CLAUSUL = 3;
	public static final int FACILITIES_THERAPY = 4;
	public static final int MISCELLANEOUS = -1;
	
	//Fields
		
	/**data for the column :
	
 --------- clausul_category.clausul_category_id --------- 
 schema        = null
 tableName     = clausul_category
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private Integer clausulCategoryId;
			
	/**data for the column :
	
 --------- clausul_category.clausul_category_name --------- 
 schema        = null
 tableName     = clausul_category
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
	private String clausulCategoryName;
			
	/**data for the column :
	
 --------- clausul_category.clausul_category_code --------- 
 schema        = null
 tableName     = clausul_category
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private String clausulCategoryCode;
			
	/**data for the column :
	
 --------- clausul_category.description --------- 
 schema        = null
 tableName     = clausul_category
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private String description;
			
	/**data for the column :
	
 --------- clausul_category.created_time --------- 
 schema        = null
 tableName     = clausul_category
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
	
 --------- clausul_category.created_by --------- 
 schema        = null
 tableName     = clausul_category
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
	private String createdBy;
			
	/**data for the column :
	
 --------- clausul_category.deleted_time --------- 
 schema        = null
 tableName     = clausul_category
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
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- clausul_category.deleted_by --------- 
 schema        = null
 tableName     = clausul_category
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
	
 --------- clausul_category.modified_time --------- 
 schema        = null
 tableName     = clausul_category
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
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- clausul_category.modified_by --------- 
 schema        = null
 tableName     = clausul_category
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
	
 --------- clausul_category.deleted_status --------- 
 schema        = null
 tableName     = clausul_category
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
			/** Clausul
	data for the exported class :
	
 --------- clausul.clausul_category_id --------- 
 schema        = null
 tableName     = clausul
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = clausul_category.clausul_category_id

=========================================



	 */
	private Set <Clausul> clausuls = new HashSet(0);
			
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="clausul_category_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getClausulCategoryId(){
		return clausulCategoryId;
	}
	public void setClausulCategoryId(java.lang.Integer value){
		clausulCategoryId = value;
	}
			// PK GETTER SETTER END

						@Column(name="clausul_category_name")
	public java.lang.String getClausulCategoryName(){
		return clausulCategoryName;
	}
	public void setClausulCategoryName(java.lang.String value){
		clausulCategoryName = value;
	}
				@Column(name="clausul_category_code")
	public java.lang.String getClausulCategoryCode(){
		return clausulCategoryCode;
	}
	public void setClausulCategoryCode(java.lang.String value){
		clausulCategoryCode = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
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
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
			/** Clausul */
	@OneToMany(mappedBy="clausulCategoryId")
	public Set<Clausul> getClausuls(){
		return this.clausuls;
	}

	/** Clausul */
	public void setClausuls(Set<Clausul> obj){
		this.clausuls = obj;
	}
			
	//exported key end



}