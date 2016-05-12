
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
@Table(name="investigation")
public class Investigation implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- investigation.investigation_id --------- 
 schema        = null
 tableName     = investigation
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
 type          = -5 
 isPrimaryKey  = true

=========================================


*/
	private Long investigationId;
			
	/**data for the column :
	
 --------- investigation.investigation_subject --------- 
 schema        = null
 tableName     = investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 100
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String investigationSubject;
			
	/**data for the column :
	
 --------- investigation.investigation_category --------- 
 schema        = null
 tableName     = investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 5
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private InvestigationCategory investigationCategory;
			
	/**data for the column :
	
 --------- investigation.head_doctor --------- 
 schema        = null
 tableName     = investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String headDoctor;
			
	/**data for the column :
	
 --------- investigation.consult_doctor --------- 
 schema        = null
 tableName     = investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private String consultDoctor;
			
	/**data for the column :
	
 --------- investigation.description --------- 
 schema        = null
 tableName     = investigation
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
	
 --------- investigation.created_time --------- 
 schema        = null
 tableName     = investigation
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
	private java.sql.Timestamp investigationTime;
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- investigation.created_by --------- 
 schema        = null
 tableName     = investigation
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
	
 --------- investigation.updated_time --------- 
 schema        = null
 tableName     = investigation
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
	private java.sql.Timestamp updatedTime;
			
	/**data for the column :
	
 --------- investigation.updated_by --------- 
 schema        = null
 tableName     = investigation
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
	private String updatedBy;
			
	/**data for the column :
	
 --------- investigation.deleted_time --------- 
 schema        = null
 tableName     = investigation
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
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- investigation.deleted_by --------- 
 schema        = null
 tableName     = investigation
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
	private String deletedBy;
			
	/**data for the column :
	
 --------- investigation.deleted_status --------- 
 schema        = null
 tableName     = investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="investigation_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Long getInvestigationId(){
		return investigationId;
	}
	public void setInvestigationId(java.lang.Long value){
		investigationId = value;
	}
			// PK GETTER SETTER END

						@Column(name="investigation_subject")
	public java.lang.String getInvestigationSubject(){
		return investigationSubject;
	}
	public void setInvestigationSubject(java.lang.String value){
		investigationSubject = value;
	}
	
	@Column(name="investigation_time")
	public java.sql.Timestamp getInvestigationTime() {
		return investigationTime;
	}
	public void setInvestigationTime(java.sql.Timestamp investigationTime) {
		this.investigationTime = investigationTime;
	}
	@ManyToOne
	@JoinColumn(name="investigation_category")
	public InvestigationCategory getInvestigationCategory(){
		return investigationCategory;
	}
	public void setInvestigationCategory(InvestigationCategory value){
		investigationCategory = value;
	}
				@Column(name="head_doctor")
	public java.lang.String getHeadDoctor(){
		return headDoctor;
	}
	public void setHeadDoctor(java.lang.String value){
		headDoctor = value;
	}
				@Column(name="consult_doctor")
	public java.lang.String getConsultDoctor(){
		return consultDoctor;
	}
	public void setConsultDoctor(java.lang.String value){
		consultDoctor = value;
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
				@Column(name="updated_time")
	public java.sql.Timestamp getUpdatedTime(){
		return updatedTime;
	}
	public void setUpdatedTime(java.sql.Timestamp value){
		updatedTime = value;
	}
				@Column(name="updated_by")
	public java.lang.String getUpdatedBy(){
		return updatedBy;
	}
	public void setUpdatedBy(java.lang.String value){
		updatedBy = value;
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