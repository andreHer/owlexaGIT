
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="formularium_medicine")
public class FormulariumMedicine implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- formularium_medicine.formularium_id --------- 
 schema        = null
 tableName     = formularium_medicine
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
	private Integer formulariumId;
			
	/**data for the column :
	
 --------- formularium_medicine.formularium_name --------- 
 schema        = null
 tableName     = formularium_medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 150
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String formulariumName;
			
	/**data for the column :
	
 --------- formularium_medicine.formularium_code --------- 
 schema        = null
 tableName     = formularium_medicine
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
	private String formulariumCode;
			
	/**data for the column :
	
 --------- formularium_medicine.periode --------- 
 schema        = null
 tableName     = formularium_medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 100
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String periode;
			
	/**data for the column :
	
 --------- formularium_medicine.start_date --------- 
 schema        = null
 tableName     = formularium_medicine
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
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date startDate;
			
	/**data for the column :
	
 --------- formularium_medicine.end_date --------- 
 schema        = null
 tableName     = formularium_medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date endDate;
			
	/**data for the column :
	
 --------- formularium_medicine.description --------- 
 schema        = null
 tableName     = formularium_medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String description;
			
	/**data for the column :
	
 --------- formularium_medicine.formularium_type --------- 
 schema        = null
 tableName     = formularium_medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 5
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer formulariumType;
			
	/**data for the column :
	
 --------- formularium_medicine.provider_id --------- 
 schema        = null
 tableName     = formularium_medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 10
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer providerId;
			
	/**data for the column :
	
 --------- formularium_medicine.client_id --------- 
 schema        = null
 tableName     = formularium_medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 10
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer clientId;
			
	/**data for the column :
	
 --------- formularium_medicine.policy_id --------- 
 schema        = null
 tableName     = formularium_medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 10
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer policyId;
			
	/**data for the column :
	
 --------- formularium_medicine.status --------- 
 schema        = null
 tableName     = formularium_medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 5
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer status;
			
	/**data for the column :
	
 --------- formularium_medicine.created_time --------- 
 schema        = null
 tableName     = formularium_medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = on update CURRENT_TIMESTAMP
 defaultValue  = CURRENT_TIMESTAMP
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 13
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- formularium_medicine.created_by --------- 
 schema        = null
 tableName     = formularium_medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 14
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- formularium_medicine.modified_time --------- 
 schema        = null
 tableName     = formularium_medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 15
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- formularium_medicine.modified_by --------- 
 schema        = null
 tableName     = formularium_medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 16
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- formularium_medicine.deleted_time --------- 
 schema        = null
 tableName     = formularium_medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 17
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- formularium_medicine.deleted_by --------- 
 schema        = null
 tableName     = formularium_medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 18
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- formularium_medicine.deleted_status --------- 
 schema        = null
 tableName     = formularium_medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 19
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
	@Column(name="formularium_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getFormulariumId(){
		return formulariumId;
	}
	public void setFormulariumId(java.lang.Integer value){
		formulariumId = value;
	}
			// PK GETTER SETTER END

						@Column(name="formularium_name")
	public java.lang.String getFormulariumName(){
		return formulariumName;
	}
	public void setFormulariumName(java.lang.String value){
		formulariumName = value;
	}
				@Column(name="formularium_code")
	public java.lang.String getFormulariumCode(){
		return formulariumCode;
	}
	public void setFormulariumCode(java.lang.String value){
		formulariumCode = value;
	}
				@Column(name="periode")
	public java.lang.String getPeriode(){
		return periode;
	}
	public void setPeriode(java.lang.String value){
		periode = value;
	}
				@Column(name="start_date")
	public java.sql.Date getStartDate(){
		return startDate;
	}
	public void setStartDate(java.sql.Date value){
		startDate = value;
	}
				@Column(name="end_date")
	public java.sql.Date getEndDate(){
		return endDate;
	}
	public void setEndDate(java.sql.Date value){
		endDate = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
				@Column(name="formularium_type")
	public java.lang.Integer getFormulariumType(){
		return formulariumType;
	}
	public void setFormulariumType(java.lang.Integer value){
		formulariumType = value;
	}
				@Column(name="provider_id")
	public java.lang.Integer getProviderId(){
		return providerId;
	}
	public void setProviderId(java.lang.Integer value){
		providerId = value;
	}
				@Column(name="client_id")
	public java.lang.Integer getClientId(){
		return clientId;
	}
	public void setClientId(java.lang.Integer value){
		clientId = value;
	}
				@Column(name="policy_id")
	public java.lang.Integer getPolicyId(){
		return policyId;
	}
	public void setPolicyId(java.lang.Integer value){
		policyId = value;
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
	
		
	// foreign affairs end

// exported key

	
		
	//exported key end



}