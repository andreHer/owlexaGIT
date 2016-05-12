
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="diagnosis_mapping")
public class DiagnosisMapping implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- diagnosis_mapping.diagnosis_mapping_id --------- 
 schema        = null
 tableName     = diagnosis_mapping
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
	private Integer diagnosisMappingId;
			
	/**data for the column :
	
 --------- diagnosis_mapping.client_id --------- 
 schema        = null
 tableName     = diagnosis_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 10
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer clientId;
			
	/**data for the column :
	
 --------- diagnosis_mapping.diagnosis_code --------- 
 schema        = null
 tableName     = diagnosis_mapping
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
	
 --------- diagnosis_mapping.diagnosis_name --------- 
 schema        = null
 tableName     = diagnosis_mapping
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
	
 --------- diagnosis_mapping.description --------- 
 schema        = null
 tableName     = diagnosis_mapping
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
	
 --------- diagnosis_mapping.client_diagnosis_code --------- 
 schema        = null
 tableName     = diagnosis_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	private String clientDiagnosisCode;
			
	/**data for the column :
	
 --------- diagnosis_mapping.member_group_id --------- 
 schema        = null
 tableName     = diagnosis_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 10
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer memberGroupId;
			
	/**data for the column :
	
 --------- diagnosis_mapping.created_time --------- 
 schema        = null
 tableName     = diagnosis_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	
 --------- diagnosis_mapping.created_by --------- 
 schema        = null
 tableName     = diagnosis_mapping
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
	private String createdBy;
			
	/**data for the column :
	
 --------- diagnosis_mapping.modified_time --------- 
 schema        = null
 tableName     = diagnosis_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	
 --------- diagnosis_mapping.modified_by --------- 
 schema        = null
 tableName     = diagnosis_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	
 --------- diagnosis_mapping.deleted_time --------- 
 schema        = null
 tableName     = diagnosis_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	
 --------- diagnosis_mapping.deleted_by --------- 
 schema        = null
 tableName     = diagnosis_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	
 --------- diagnosis_mapping.deleted_status --------- 
 schema        = null
 tableName     = diagnosis_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
			
	/**data for the column :
	
 --------- diagnosis_mapping.diagnosis_id --------- 
 schema        = null
 tableName     = diagnosis_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 15
 size          = 10
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer diagnosisId;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="diagnosis_mapping_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getDiagnosisMappingId(){
		return diagnosisMappingId;
	}
	public void setDiagnosisMappingId(java.lang.Integer value){
		diagnosisMappingId = value;
	}
			// PK GETTER SETTER END

						@Column(name="client_id")
	public java.lang.Integer getClientId(){
		return clientId;
	}
	public void setClientId(java.lang.Integer value){
		clientId = value;
	}
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
				@Column(name="client_diagnosis_code")
	public java.lang.String getClientDiagnosisCode(){
		return clientDiagnosisCode;
	}
	public void setClientDiagnosisCode(java.lang.String value){
		clientDiagnosisCode = value;
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
				@Column(name="diagnosis_id")
	public java.lang.Integer getDiagnosisId(){
		return diagnosisId;
	}
	public void setDiagnosisId(java.lang.Integer value){
		diagnosisId = value;
	}
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
		
	//exported key end



}