
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="diagnosis_set")
public class DiagnosisSet implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- diagnosis_set.diagnosis_set_id --------- 
 schema        = null
 tableName     = diagnosis_set
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
	private Integer diagnosisSetId;
			
	/**data for the column :
	
 --------- diagnosis_set.diagnosis_set_name --------- 
 schema        = null
 tableName     = diagnosis_set
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
	private String diagnosisSetName;
			
	/**data for the column :
	
 --------- diagnosis_set.diagnosis_set_code --------- 
 schema        = null
 tableName     = diagnosis_set
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
	private String diagnosisSetCode;
			
	/**data for the column :
	
 --------- diagnosis_set.description --------- 
 schema        = null
 tableName     = diagnosis_set
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
	
 --------- diagnosis_set.created_time --------- 
 schema        = null
 tableName     = diagnosis_set
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
	
 --------- diagnosis_set.created_by --------- 
 schema        = null
 tableName     = diagnosis_set
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
	
 --------- diagnosis_set.modified_time --------- 
 schema        = null
 tableName     = diagnosis_set
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- diagnosis_set.modified_by --------- 
 schema        = null
 tableName     = diagnosis_set
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
	private String modifiedBy;
			
	/**data for the column :
	
 --------- diagnosis_set.deleted_time --------- 
 schema        = null
 tableName     = diagnosis_set
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- diagnosis_set.deleted_by --------- 
 schema        = null
 tableName     = diagnosis_set
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
	private String deletedBy;
			
	/**data for the column :
	
 --------- diagnosis_set.deleted_status --------- 
 schema        = null
 tableName     = diagnosis_set
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
		
	// foreign affairs
	
			/** Client
	data for the foreign class :
	
 --------- client.client_id --------- 
 schema        = null
 tableName     = client
 foreignCol    = client_id
 foreignTab    = diagnosis_set
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
	private Client clientId;
			
	// -- foreign affairs end

	// -- exported key

	
			/** DiagnosisSetDetail
	data for the exported class :
	
 --------- diagnosis_set_detail.diagnosis_set_id --------- 
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
 ordinal       = 2
 size          = 10
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = diagnosis_set.diagnosis_set_id

=========================================



	 */
	private Set <DiagnosisSetDetail> diagnosisSetDetails = new HashSet(0);
			
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="diagnosis_set_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getDiagnosisSetId(){
		return diagnosisSetId;
	}
	public void setDiagnosisSetId(java.lang.Integer value){
		diagnosisSetId = value;
	}
			// PK GETTER SETTER END

						@Column(name="diagnosis_set_name")
	public java.lang.String getDiagnosisSetName(){
		return diagnosisSetName;
	}
	public void setDiagnosisSetName(java.lang.String value){
		diagnosisSetName = value;
	}
				@Column(name="diagnosis_set_code")
	public java.lang.String getDiagnosisSetCode(){
		return diagnosisSetCode;
	}
	public void setDiagnosisSetCode(java.lang.String value){
		diagnosisSetCode = value;
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
	
			/** Client */
	@ManyToOne
	@JoinColumn(name="client_id")
	public Client getClientId(){
		return this.clientId;
	}

	/** Client */
	public void setClientId(Client obj){
		this.clientId = obj;
	}
			
	// foreign affairs end

// exported key

	
			/** DiagnosisSetDetail */
	@OneToMany(mappedBy="diagnosisSetId")
	public Set<DiagnosisSetDetail> getDiagnosisSetDetails(){
		return this.diagnosisSetDetails;
	}

	/** DiagnosisSetDetail */
	public void setDiagnosisSetDetails(Set<DiagnosisSetDetail> obj){
		this.diagnosisSetDetails = obj;
	}
			
	//exported key end



}