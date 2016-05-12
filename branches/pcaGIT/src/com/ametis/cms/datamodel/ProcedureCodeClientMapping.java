
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="procedure_code_client_mapping")
public class ProcedureCodeClientMapping implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- procedure_code_client_mapping.procedure_code_client_mapping_id --------- 
 schema        = null
 tableName     = procedure_code_client_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	private Integer procedureCodeClientMappingId;
			
	/**data for the column :
	
 --------- procedure_code_client_mapping.procedure_client_code --------- 
 schema        = null
 tableName     = procedure_code_client_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String procedureClientCode;
			
	/**data for the column :
	
 --------- procedure_code_client_mapping.procedure_id --------- 
 schema        = null
 tableName     = procedure_code_client_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	private Integer procedureId;
			
	/**data for the column :
	
 --------- procedure_code_client_mapping.client_id --------- 
 schema        = null
 tableName     = procedure_code_client_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 10
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer clientId;
			
	/**data for the column :
	
 --------- procedure_code_client_mapping.procedure_code --------- 
 schema        = null
 tableName     = procedure_code_client_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	private String procedureCode;
			
	/**data for the column :
	
 --------- procedure_code_client_mapping.procedure_name --------- 
 schema        = null
 tableName     = procedure_code_client_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String procedureName;
			
	/**data for the column :
	
 --------- procedure_code_client_mapping.status --------- 
 schema        = null
 tableName     = procedure_code_client_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer status;
			
	/**data for the column :
	
 --------- procedure_code_client_mapping.created_time --------- 
 schema        = null
 tableName     = procedure_code_client_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 8
 type          = 92 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Time createdTime;
			
	/**data for the column :
	
 --------- procedure_code_client_mapping.created_by --------- 
 schema        = null
 tableName     = procedure_code_client_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	
 --------- procedure_code_client_mapping.modified_time --------- 
 schema        = null
 tableName     = procedure_code_client_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 8
 type          = 92 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Time modifiedTime;
			
	/**data for the column :
	
 --------- procedure_code_client_mapping.modified_by --------- 
 schema        = null
 tableName     = procedure_code_client_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	
 --------- procedure_code_client_mapping.deleted_time --------- 
 schema        = null
 tableName     = procedure_code_client_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 8
 type          = 92 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Time deletedTime;
			
	/**data for the column :
	
 --------- procedure_code_client_mapping.deleted_by --------- 
 schema        = null
 tableName     = procedure_code_client_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	
 --------- procedure_code_client_mapping.deleted_status --------- 
 schema        = null
 tableName     = procedure_code_client_mapping
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="procedure_code_client_mapping_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public java.lang.Integer getProcedureCodeClientMappingId(){
		return procedureCodeClientMappingId;
	}
	public void setProcedureCodeClientMappingId(java.lang.Integer value){
		procedureCodeClientMappingId = value;
	}
			// PK GETTER SETTER END

						@Column(name="procedure_client_code")
	public java.lang.String getProcedureClientCode(){
		return procedureClientCode;
	}
	public void setProcedureClientCode(java.lang.String value){
		procedureClientCode = value;
	}
				@Column(name="procedure_id")
	public java.lang.Integer getProcedureId(){
		return procedureId;
	}
	public void setProcedureId(java.lang.Integer value){
		procedureId = value;
	}
				@Column(name="client_id")
	public java.lang.Integer getClientId(){
		return clientId;
	}
	public void setClientId(java.lang.Integer value){
		clientId = value;
	}
				@Column(name="procedure_code")
	public java.lang.String getProcedureCode(){
		return procedureCode;
	}
	public void setProcedureCode(java.lang.String value){
		procedureCode = value;
	}
				@Column(name="procedure_name")
	public java.lang.String getProcedureName(){
		return procedureName;
	}
	public void setProcedureName(java.lang.String value){
		procedureName = value;
	}
				@Column(name="status")
	public java.lang.Integer getStatus(){
		return status;
	}
	public void setStatus(java.lang.Integer value){
		status = value;
	}
				@Column(name="created_time")
	public java.sql.Time getCreatedTime(){
		return createdTime;
	}
	public void setCreatedTime(java.sql.Time value){
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
	public java.sql.Time getModifiedTime(){
		return modifiedTime;
	}
	public void setModifiedTime(java.sql.Time value){
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
	public java.sql.Time getDeletedTime(){
		return deletedTime;
	}
	public void setDeletedTime(java.sql.Time value){
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
	
		
	// foreign affairs end

// exported key

	
		
	//exported key end



}