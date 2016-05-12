
package com.ametis.cms.datamodel;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="claim_investigation")
public class ClaimInvestigation implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- claim_investigation.claim_investigation_id --------- 
 schema        = null
 tableName     = claim_investigation
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
	private Long claimInvestigationId;
									
	/**data for the column :
	
 --------- claim_investigation.investigation_date --------- 
 schema        = null
 tableName     = claim_investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date investigationDate;
			
	/**data for the column :
	
 --------- claim_investigation.created_time --------- 
 schema        = null
 tableName     = claim_investigation
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
	
 --------- claim_investigation.created_by --------- 
 schema        = null
 tableName     = claim_investigation
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
	
 --------- claim_investigation.modified_time --------- 
 schema        = null
 tableName     = claim_investigation
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
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- claim_investigation.modified_by --------- 
 schema        = null
 tableName     = claim_investigation
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
	private String modifiedBy;
			
	/**data for the column :
	
 --------- claim_investigation.deleted_by --------- 
 schema        = null
 tableName     = claim_investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	
 --------- claim_investigation.deleted_time --------- 
 schema        = null
 tableName     = claim_investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	
 --------- claim_investigation.deleted_status --------- 
 schema        = null
 tableName     = claim_investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- claim_investigation.description --------- 
 schema        = null
 tableName     = claim_investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String description;
			
	/**data for the column :
	
 --------- claim_investigation.investigation_subject --------- 
 schema        = null
 tableName     = claim_investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String investigationSubject;
			
	/**data for the column :
	
 --------- claim_investigation.head_doctor --------- 
 schema        = null
 tableName     = claim_investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 14
 size          = 250
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String headDoctor;
			
	/**data for the column :
	
 --------- claim_investigation.consult_doctor --------- 
 schema        = null
 tableName     = claim_investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 15
 size          = 250
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String consultDoctor;
			
	/**data for the column :
	
 --------- claim_investigation.nurse --------- 
 schema        = null
 tableName     = claim_investigation
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 16
 size          = 250
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String nurse;
		
	// foreign affairs
	
			/** InvestigationCategory
	data for the foreign class :
	
 --------- investigation_category.investigation_category_id --------- 
 schema        = null
 tableName     = investigation_category
 foreignCol    = investigation_category_id
 foreignTab    = claim_investigation
 catalog       = insurance
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 5
 type          = 4 
 isPrimaryKey  = true

=========================================



	 */
	private InvestigationCategory investigationCategoryId;
				/** Claim
	data for the foreign class :
	
 --------- claim.claim_id --------- 
 schema        = null
 tableName     = claim
 foreignCol    = claim_id
 foreignTab    = claim_investigation
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
	private Claim claimId;
	private Integer status;
	private Date endDate;
			
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="claim_investigation_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Long getClaimInvestigationId(){
		return claimInvestigationId;
	}
	public void setClaimInvestigationId(java.lang.Long value){
		claimInvestigationId = value;
	}
			// PK GETTER SETTER END

												@Column(name="investigation_date")
	public java.sql.Date getInvestigationDate(){
		return investigationDate;
	}
	public void setInvestigationDate(java.sql.Date value){
		investigationDate = value;
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
				@Column(name="deleted_by")
	public java.lang.String getDeletedBy(){
		return deletedBy;
	}
	public void setDeletedBy(java.lang.String value){
		deletedBy = value;
	}
				@Column(name="deleted_time")
	public java.sql.Timestamp getDeletedTime(){
		return deletedTime;
	}
	public void setDeletedTime(java.sql.Timestamp value){
		deletedTime = value;
	}
				@Column(name="deleted_status")
	public java.lang.Integer getDeletedStatus(){
		return deletedStatus;
	}
	public void setDeletedStatus(java.lang.Integer value){
		deletedStatus = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
				@Column(name="investigation_subject")
	public java.lang.String getInvestigationSubject(){
		return investigationSubject;
	}
	public void setInvestigationSubject(java.lang.String value){
		investigationSubject = value;
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
				@Column(name="nurse")
	public java.lang.String getNurse(){
		return nurse;
	}
	public void setNurse(java.lang.String value){
		nurse = value;
	}
		
	// foreign affairs
	
			/** InvestigationCategory */
	@ManyToOne
	@JoinColumn(name="investigation_category_id")
	public InvestigationCategory getInvestigationCategoryId(){
		return this.investigationCategoryId;
	}

	/** InvestigationCategory */
	public void setInvestigationCategoryId(InvestigationCategory obj){
		this.investigationCategoryId = obj;
	}
				/** Claim */
	@ManyToOne
	@JoinColumn(name="claim_id")
	public Claim getClaimId(){
		return this.claimId;
	}

	/** Claim */
	public void setClaimId(Claim obj){
		this.claimId = obj;
	}
	@Column(name="status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column(name="end_date")
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
			
	


}