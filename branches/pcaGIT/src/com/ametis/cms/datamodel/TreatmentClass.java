
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="treatment_class")
public class TreatmentClass implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- treatment_class.treatment_class_id --------- 
 schema        = null
 tableName     = treatment_class
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 4
 type          = 4 
 isPrimaryKey  = true

=========================================


*/
	private Integer treatmentClassId;
			
	/**data for the column :
	
 --------- treatment_class.treatment_class_name --------- 
 schema        = null
 tableName     = treatment_class
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
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
	private String treatmentClassName;
			
	/**data for the column :
	
 --------- treatment_class.treatment_class_code --------- 
 schema        = null
 tableName     = treatment_class
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
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
	private String treatmentClassCode;
			
	/**data for the column :
	
 --------- treatment_class.description --------- 
 schema        = null
 tableName     = treatment_class
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
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
	
 --------- treatment_class.class_index --------- 
 schema        = null
 tableName     = treatment_class
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
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
	private Double classIndex;
			
	/**data for the column :
	
 --------- treatment_class.created_time --------- 
 schema        = null
 tableName     = treatment_class
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
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
	
 --------- treatment_class.created_by --------- 
 schema        = null
 tableName     = treatment_class
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
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
	
 --------- treatment_class.modified_time --------- 
 schema        = null
 tableName     = treatment_class
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
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
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- treatment_class.modified_by --------- 
 schema        = null
 tableName     = treatment_class
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
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
	
 --------- treatment_class.deleted_time --------- 
 schema        = null
 tableName     = treatment_class
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
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
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- treatment_class.deleted_by --------- 
 schema        = null
 tableName     = treatment_class
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
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
	
 --------- treatment_class.deleted_status --------- 
 schema        = null
 tableName     = treatment_class
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
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
	private Integer coefficient;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="treatment_class_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getTreatmentClassId(){
		return treatmentClassId;
	}
	public void setTreatmentClassId(java.lang.Integer value){
		treatmentClassId = value;
	}
			// PK GETTER SETTER END

						@Column(name="treatment_class_name")
	public java.lang.String getTreatmentClassName(){
		return treatmentClassName;
	}
	public void setTreatmentClassName(java.lang.String value){
		treatmentClassName = value;
	}
				@Column(name="treatment_class_code")
	public java.lang.String getTreatmentClassCode(){
		return treatmentClassCode;
	}
	public void setTreatmentClassCode(java.lang.String value){
		treatmentClassCode = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
				@Column(name="class_index")
	public java.lang.Double getClassIndex(){
		return classIndex;
	}
	public void setClassIndex(java.lang.Double value){
		classIndex = value;
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
	@Column(name="coefficient")
	public Integer getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(Integer coefficient) {
		this.coefficient = coefficient;
	}
		
	



}