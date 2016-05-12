
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="poliklinik")
public class Poliklinik implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- poliklinik.poliklinik_id --------- 
 schema        = null
 tableName     = poliklinik
 foreignCol    = 
 foreignTab    = 
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
	private Integer poliklinikId;
			
	/**data for the column :
	
 --------- poliklinik.poliklinik_name --------- 
 schema        = null
 tableName     = poliklinik
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 250
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String poliklinikName;
			
	/**data for the column :
	
 --------- poliklinik.poliklinik_code --------- 
 schema        = null
 tableName     = poliklinik
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String poliklinikCode;
			
	/**data for the column :
	
 --------- poliklinik.description --------- 
 schema        = null
 tableName     = poliklinik
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	
 --------- poliklinik.parent_id --------- 
 schema        = null
 tableName     = poliklinik
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 10
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Poliklinik parentId;
			
	/**data for the column :
	
 --------- poliklinik.created_time --------- 
 schema        = null
 tableName     = poliklinik
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = on update CURRENT_TIMESTAMP
 defaultValue  = CURRENT_TIMESTAMP
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 6
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- poliklinik.created_by --------- 
 schema        = null
 tableName     = poliklinik
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	
 --------- poliklinik.deleted_time --------- 
 schema        = null
 tableName     = poliklinik
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	
 --------- poliklinik.deleted_by --------- 
 schema        = null
 tableName     = poliklinik
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	private String deletedBy;
			
	/**data for the column :
	
 --------- poliklinik.deleted_status --------- 
 schema        = null
 tableName     = poliklinik
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
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- poliklinik.modified_time --------- 
 schema        = null
 tableName     = poliklinik
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 11
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- poliklinik.modified_by --------- 
 schema        = null
 tableName     = poliklinik
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
	private String modifiedBy;
	private String poliklinikEdcCode;
	private CaseCategory caseCategoryId;
	private Integer notUsingReference;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="poliklinik_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getPoliklinikId(){
		return poliklinikId;
	}
	public void setPoliklinikId(java.lang.Integer value){
		poliklinikId = value;
	}
			// PK GETTER SETTER END

						@Column(name="poliklinik_name")
	public java.lang.String getPoliklinikName(){
		return poliklinikName;
	}
	public void setPoliklinikName(java.lang.String value){
		poliklinikName = value;
	}
				@Column(name="poliklinik_code")
	public java.lang.String getPoliklinikCode(){
		return poliklinikCode;
	}
	public void setPoliklinikCode(java.lang.String value){
		poliklinikCode = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
	@ManyToOne
	@JoinColumn(name="parent_id")
	public Poliklinik getParentId(){
		return parentId;
	}
	public void setParentId(Poliklinik value){
		parentId = value;
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
				@Column(name="deleted_status")
	public java.lang.Integer getDeletedStatus(){
		return deletedStatus;
	}
	public void setDeletedStatus(java.lang.Integer value){
		deletedStatus = value;
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
	@Column(name="poliklinik_edc_code")
	public String getPoliklinikEdcCode() {
		return poliklinikEdcCode;
	}
	public void setPoliklinikEdcCode(String poliklinikEdcCode) {
		this.poliklinikEdcCode = poliklinikEdcCode;
	}
	@ManyToOne
	@JoinColumn(name="case_category_id")
	public CaseCategory getCaseCategoryId() {
		return caseCategoryId;
	}
	public void setCaseCategoryId(CaseCategory caseCategoryId) {
		this.caseCategoryId = caseCategoryId;
	}
	@Column(name="not_using_reference")
	public Integer getNotUsingReference() {
		return notUsingReference;
	}
	public void setNotUsingReference(Integer notUsingReference) {
		this.notUsingReference = notUsingReference;
	}
	



}