
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
@Table(name="case_provider")
public class CaseProvider implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- case_provider.case_provider_id --------- 
 schema        = null
 tableName     = case_provider
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
	private Integer caseProviderId;
			
	/**data for the column :
	
 --------- case_provider.case_id --------- 
 schema        = null
 tableName     = case_provider
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

=========================================


*/
	private Case caseId;
			
	/**data for the column :
	
 --------- case_provider.rumah_sakit --------- 
 schema        = null
 tableName     = case_provider
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
	private String rumahSakit;
			
	/**data for the column :
	
 --------- case_provider.provider_id --------- 
 schema        = null
 tableName     = case_provider
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

=========================================


*/
	private Integer providerId;
			
	/**data for the column :
	
 --------- case_provider.case_status --------- 
 schema        = null
 tableName     = case_provider
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

=========================================


*/
	private Integer caseStatus;
			
	/**data for the column :
	
 --------- case_provider.description --------- 
 schema        = null
 tableName     = case_provider
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String description;
			
	/**data for the column :
	
 --------- case_provider.created_time --------- 
 schema        = null
 tableName     = case_provider
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
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- case_provider.created_by --------- 
 schema        = null
 tableName     = case_provider
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
	
 --------- case_provider.deleted_time --------- 
 schema        = null
 tableName     = case_provider
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
	
 --------- case_provider.deleted_by --------- 
 schema        = null
 tableName     = case_provider
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
	private String deletedBy;
			
	/**data for the column :
	
 --------- case_provider.modified_time --------- 
 schema        = null
 tableName     = case_provider
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
			
	/**data for the column :
	
 --------- case_provider.modified_by --------- 
 schema        = null
 tableName     = case_provider
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
			
	/**data for the column :
	
 --------- case_provider.deleted_status --------- 
 schema        = null
 tableName     = case_provider
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
	private Case initialCase;
	private Case referedByCaseId;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="case_provider_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getCaseProviderId(){
		return caseProviderId;
	}
	public void setCaseProviderId(java.lang.Integer value){
		caseProviderId = value;
	}
			// PK GETTER SETTER END

	@ManyToOne
	@JoinColumn(name="case_id")
	public Case getCaseId(){
		return caseId;
	}
	public void setCaseId(Case value){
		caseId = value;
	}
				@Column(name="rumah_sakit")
	public java.lang.String getRumahSakit(){
		return rumahSakit;
	}
	public void setRumahSakit(java.lang.String value){
		rumahSakit = value;
	}
				@Column(name="provider_id")
	public java.lang.Integer getProviderId(){
		return providerId;
	}
	public void setProviderId(java.lang.Integer value){
		providerId = value;
	}
				@Column(name="case_status")
	public java.lang.Integer getCaseStatus(){
		return caseStatus;
	}
	public void setCaseStatus(java.lang.Integer value){
		caseStatus = value;
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
	@ManyToOne
	@JoinColumn(name="initial_case_id")
	public Case getInitialCase() {
		return initialCase;
	}
	public void setInitialCase(Case initialCase) {
		this.initialCase = initialCase;
	}
	@ManyToOne
	@JoinColumn(name="refered_by_case_id")
	public Case getReferedByCaseId() {
		return referedByCaseId;
	}
	public void setReferedByCaseId(Case referedByCaseId) {
		this.referedByCaseId = referedByCaseId;
	}
		
	


}