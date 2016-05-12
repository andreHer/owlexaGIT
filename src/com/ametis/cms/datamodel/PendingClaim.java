
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
@Table(name="pending_claim")
public class PendingClaim implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- pending_claim.pending_claim_id --------- 
 schema        = null
 tableName     = pending_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 12
 type          = 4 
 isPrimaryKey  = true

=========================================


*/
	private String pendingNumber;
	private Integer pendingClaimId;
			
	/**data for the column :
	
 --------- pending_claim.claim_id --------- 
 schema        = null
 tableName     = pending_claim
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
 type          = -5 
 isPrimaryKey  = false

=========================================


*/
	private Claim claimId;
			
	/**data for the column :
	
 --------- pending_claim.pending_category --------- 
 schema        = null
 tableName     = pending_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private PendingCategory pendingCategory;
			
	/**data for the column :
	
 --------- pending_claim.pending_due_date --------- 
 schema        = null
 tableName     = pending_claim
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
	private java.sql.Date pendingDueDate;
			
	/**data for the column :
	
 --------- pending_claim.pending_subject --------- 
 schema        = null
 tableName     = pending_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 100
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String pendingSubject;
			
	/**data for the column :
	
 --------- pending_claim.remarks --------- 
 schema        = null
 tableName     = pending_claim
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
	private String remarks;
			
	/**data for the column :
	
 --------- pending_claim.created_time --------- 
 schema        = null
 tableName     = pending_claim
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
	
 --------- pending_claim.created_by --------- 
 schema        = null
 tableName     = pending_claim
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
	
 --------- pending_claim.updated_time --------- 
 schema        = null
 tableName     = pending_claim
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
	
 --------- pending_claim.updated_by --------- 
 schema        = null
 tableName     = pending_claim
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
	
 --------- pending_claim.deleted_time --------- 
 schema        = null
 tableName     = pending_claim
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
	
 --------- pending_claim.deleted_by --------- 
 schema        = null
 tableName     = pending_claim
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
	
 --------- pending_claim.deleted_status --------- 
 schema        = null
 tableName     = pending_claim
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
	@Column(name="pending_claim_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getPendingClaimId(){
		return pendingClaimId;
	}
	public void setPendingClaimId(java.lang.Integer value){
		pendingClaimId = value;
	}
			// PK GETTER SETTER END

	@ManyToOne
	@JoinColumn(name="claim_id")
	public Claim getClaimId(){
		return claimId;
	}
	public void setClaimId(Claim value){
		claimId = value;
	}
	@ManyToOne
	@JoinColumn(name="pending_category")
	public PendingCategory getPendingCategory(){
		return pendingCategory;
	}
	public void setPendingCategory(PendingCategory value){
		pendingCategory = value;
	}
				@Column(name="pending_due_date")
	public java.sql.Date getPendingDueDate(){
		return pendingDueDate;
	}
	public void setPendingDueDate(java.sql.Date value){
		pendingDueDate = value;
	}
				@Column(name="pending_subject")
	public java.lang.String getPendingSubject(){
		return pendingSubject;
	}
	public void setPendingSubject(java.lang.String value){
		pendingSubject = value;
	}
				@Column(name="remarks")
	public java.lang.String getRemarks(){
		return remarks;
	}
	public void setRemarks(java.lang.String value){
		remarks = value;
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
	@Column (name="pending_number")
	public String getPendingNumber() {
		return pendingNumber;
	}
	public void setPendingNumber(String pendingNumber) {
		this.pendingNumber = pendingNumber;
	}
	
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
		
	//exported key end



}