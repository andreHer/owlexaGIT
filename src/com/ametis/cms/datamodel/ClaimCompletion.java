
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
@Table(name="claim_completion")
public class ClaimCompletion implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- claim_completion.complete_claim_id --------- 
 schema        = null
 tableName     = claim_completion
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 10
 type          = -5 
 isPrimaryKey  = true

=========================================


*/
	private Long completeClaimId;
						
	/**data for the column :
	
 --------- claim_completion.complete_date --------- 
 schema        = null
 tableName     = claim_completion
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
	private java.sql.Date completeDate;
			
	/**data for the column :
	
 --------- claim_completion.completion_remarks --------- 
 schema        = null
 tableName     = claim_completion
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
	private String completionRemarks;
			
	/**data for the column :
	
 --------- claim_completion.created_time --------- 
 schema        = null
 tableName     = claim_completion
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
	
 --------- claim_completion.created_by --------- 
 schema        = null
 tableName     = claim_completion
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
	
 --------- claim_completion.modified_time --------- 
 schema        = null
 tableName     = claim_completion
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
	
 --------- claim_completion.modified_by --------- 
 schema        = null
 tableName     = claim_completion
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
	
 --------- claim_completion.deleted_by --------- 
 schema        = null
 tableName     = claim_completion
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
	
 --------- claim_completion.deleted_time --------- 
 schema        = null
 tableName     = claim_completion
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
	
 --------- claim_completion.deleted_status --------- 
 schema        = null
 tableName     = claim_completion
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
		
	// foreign affairs
	
			/** PendingClaim
	data for the foreign class :
	
 --------- pending_claim.pending_claim_id --------- 
 schema        = null
 tableName     = pending_claim
 foreignCol    = pending_claim_id
 foreignTab    = claim_completion
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
	private PendingClaim pendingClaimId;
			
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="complete_claim_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Long getCompleteClaimId(){
		return completeClaimId;
	}
	public void setCompleteClaimId(java.lang.Long value){
		completeClaimId = value;
	}
			// PK GETTER SETTER END

									@Column(name="complete_date")
	public java.sql.Date getCompleteDate(){
		return completeDate;
	}
	public void setCompleteDate(java.sql.Date value){
		completeDate = value;
	}
				@Column(name="completion_remarks")
	public java.lang.String getCompletionRemarks(){
		return completionRemarks;
	}
	public void setCompletionRemarks(java.lang.String value){
		completionRemarks = value;
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
		
	// foreign affairs
	
			/** PendingClaim */
	@ManyToOne
	@JoinColumn(name="pending_claim_id")
	public PendingClaim getPendingClaimId(){
		return this.pendingClaimId;
	}

	/** PendingClaim */
	public void setPendingClaimId(PendingClaim obj){
		this.pendingClaimId = obj;
	}
			
	// foreign affairs end

// exported key

	
		
	//exported key end



}