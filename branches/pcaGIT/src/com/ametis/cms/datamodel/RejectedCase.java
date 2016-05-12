
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
@Table(name="rejected_case")
public class RejectedCase implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- rejected_case.rejected_case_id --------- 
 schema        = null
 tableName     = rejected_case
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
	private Long rejectedCaseId;
						
	/**data for the column :
	
 --------- rejected_case.rejection_date --------- 
 schema        = null
 tableName     = rejected_case
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date rejectionDate;
			
	/**data for the column :
	
 --------- rejected_case.rejection_description --------- 
 schema        = null
 tableName     = rejected_case
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private String rejectionDescription;
			
	/**data for the column :
	
 --------- rejected_case.estimated_cost --------- 
 schema        = null
 tableName     = rejected_case
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private Double estimatedCost;
			
	/**data for the column :
	
 --------- rejected_case.created_time --------- 
 schema        = null
 tableName     = rejected_case
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- rejected_case.created_by --------- 
 schema        = null
 tableName     = rejected_case
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	
 --------- rejected_case.modified_by --------- 
 schema        = null
 tableName     = rejected_case
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
	
 --------- rejected_case.modified_time --------- 
 schema        = null
 tableName     = rejected_case
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
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- rejected_case.deleted_time --------- 
 schema        = null
 tableName     = rejected_case
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
	
 --------- rejected_case.deleted_by --------- 
 schema        = null
 tableName     = rejected_case
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	
 --------- rejected_case.deleted_status --------- 
 schema        = null
 tableName     = rejected_case
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	
			/** TbCase
	data for the foreign class :
	
 --------- tb_case.case_id --------- 
 schema        = null
 tableName     = tb_case
 foreignCol    = case_id
 foreignTab    = rejected_case
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
	private Case caseId;
	private RejectCategory rejectCategoryId;
	private String rejectionNumber;
			
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="rejected_case_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Long getRejectedCaseId(){
		return rejectedCaseId;
	}
	public void setRejectedCaseId(java.lang.Long value){
		rejectedCaseId = value;
	}
			// PK GETTER SETTER END

									@Column(name="rejection_date")
	public java.sql.Date getRejectionDate(){
		return rejectionDate;
	}
	public void setRejectionDate(java.sql.Date value){
		rejectionDate = value;
	}
				@Column(name="rejection_description")
	public java.lang.String getRejectionDescription(){
		return rejectionDescription;
	}
	public void setRejectionDescription(java.lang.String value){
		rejectionDescription = value;
	}
				@Column(name="estimated_cost")
	public java.lang.Double getEstimatedCost(){
		return estimatedCost;
	}
	public void setEstimatedCost(java.lang.Double value){
		estimatedCost = value;
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
				@Column(name="modified_by")
	public java.lang.String getModifiedBy(){
		return modifiedBy;
	}
	public void setModifiedBy(java.lang.String value){
		modifiedBy = value;
	}
				@Column(name="modified_time")
	public java.sql.Timestamp getModifiedTime(){
		return modifiedTime;
	}
	public void setModifiedTime(java.sql.Timestamp value){
		modifiedTime = value;
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
	
			/** TbCase */
	@ManyToOne
	@JoinColumn(name="case_id")
	public Case getCaseId(){
		return this.caseId;
	}

	/** TbCase */
	public void setCaseId(Case obj){
		this.caseId = obj;
	}
	@ManyToOne
	@JoinColumn(name="category_id")
	public RejectCategory getRejectCategoryId() {
		return rejectCategoryId;
	}
	public void setRejectCategoryId(RejectCategory rejectCategoryId) {
		this.rejectCategoryId = rejectCategoryId;
	}
	@Column(name="rejection_number")
	public String getRejectionNumber() {
		return rejectionNumber;
	}
	public void setRejectionNumber(String rejectionNumber) {
		this.rejectionNumber = rejectionNumber;
	}
			
	// foreign affairs end

// exported key

	
		
	//exported key end



}