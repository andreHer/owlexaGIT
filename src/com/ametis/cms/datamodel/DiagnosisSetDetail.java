
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="diagnosis_set_detail")
public class DiagnosisSetDetail implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- diagnosis_set_detail.diagnosis_set_detail_id --------- 
 schema        = null
 tableName     = diagnosis_set_detail
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
	private Integer diagnosisSetDetailId;
						
	/**data for the column :
	
 --------- diagnosis_set_detail.diagnosis_code --------- 
 schema        = null
 tableName     = diagnosis_set_detail
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
	private String diagnosisCode;
			
	/**data for the column :
	
 --------- diagnosis_set_detail.diagnosis_name --------- 
 schema        = null
 tableName     = diagnosis_set_detail
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
	private String diagnosisName;
			
	/**data for the column :
	
 --------- diagnosis_set_detail.description --------- 
 schema        = null
 tableName     = diagnosis_set_detail
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String description;
			
	/**data for the column :
	
 --------- diagnosis_set_detail.status --------- 
 schema        = null
 tableName     = diagnosis_set_detail
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer status;
			
	/**data for the column :
	
 --------- diagnosis_set_detail.created_time --------- 
 schema        = null
 tableName     = diagnosis_set_detail
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = on update CURRENT_TIMESTAMP
 defaultValue  = CURRENT_TIMESTAMP
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 7
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- diagnosis_set_detail.created_by --------- 
 schema        = null
 tableName     = diagnosis_set_detail
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	
 --------- diagnosis_set_detail.modified_time --------- 
 schema        = null
 tableName     = diagnosis_set_detail
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	
 --------- diagnosis_set_detail.modified_by --------- 
 schema        = null
 tableName     = diagnosis_set_detail
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	
 --------- diagnosis_set_detail.deleted_time --------- 
 schema        = null
 tableName     = diagnosis_set_detail
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- diagnosis_set_detail.deleted_by --------- 
 schema        = null
 tableName     = diagnosis_set_detail
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
	private String deletedBy;
			
	/**data for the column :
	
 --------- diagnosis_set_detail.deleted_status --------- 
 schema        = null
 tableName     = diagnosis_set_detail
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
					
	// foreign affairs
	
			/** Diagnosis
	data for the foreign class :
	
 --------- diagnosis.diagnosis_id --------- 
 schema        = null
 tableName     = diagnosis
 foreignCol    = diagnosis_id
 foreignTab    = diagnosis_set_detail
 catalog       = insura-master
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
	private Diagnosis diagnosisId;
				/** DiagnosisSet
	data for the foreign class :
	
 --------- diagnosis_set.diagnosis_set_id --------- 
 schema        = null
 tableName     = diagnosis_set
 foreignCol    = diagnosis_set_id
 foreignTab    = diagnosis_set_detail
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
	private DiagnosisSet diagnosisSetId;
			
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="diagnosis_set_detail_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getDiagnosisSetDetailId(){
		return diagnosisSetDetailId;
	}
	public void setDiagnosisSetDetailId(java.lang.Integer value){
		diagnosisSetDetailId = value;
	}
			// PK GETTER SETTER END

									@Column(name="diagnosis_code")
	public java.lang.String getDiagnosisCode(){
		return diagnosisCode;
	}
	public void setDiagnosisCode(java.lang.String value){
		diagnosisCode = value;
	}
				@Column(name="diagnosis_name")
	public java.lang.String getDiagnosisName(){
		return diagnosisName;
	}
	public void setDiagnosisName(java.lang.String value){
		diagnosisName = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
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
	
			/** Diagnosis */
	@ManyToOne
	@JoinColumn(name="diagnosis_id")
	public Diagnosis getDiagnosisId(){
		return this.diagnosisId;
	}

	/** Diagnosis */
	public void setDiagnosisId(Diagnosis obj){
		this.diagnosisId = obj;
	}
				/** DiagnosisSet */
	@ManyToOne
	@JoinColumn(name="diagnosis_set_id")
	public DiagnosisSet getDiagnosisSetId(){
		return this.diagnosisSetId;
	}

	/** DiagnosisSet */
	public void setDiagnosisSetId(DiagnosisSet obj){
		this.diagnosisSetId = obj;
	}
			
	// foreign affairs end

// exported key

	
		
	//exported key end



}