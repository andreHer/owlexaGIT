
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
@Table(name="rejected_claim")
public class RejectedClaim implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- rejected_claim.rejected_claim_id --------- 
 schema        = null
 tableName     = rejected_claim
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
	private String rejectionNumber;
	private Long rejectedClaimId;
			
	/**data for the column :
	
 --------- rejected_claim.claim_id --------- 
 schema        = null
 tableName     = rejected_claim
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
	
 --------- rejected_claim.rejection_category --------- 
 schema        = null
 tableName     = rejected_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 4
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private RejectCategory rejectionCategory;
	private String claimNumber;
			
	/**data for the column :
	
 --------- rejected_claim.rejection_subject --------- 
 schema        = null
 tableName     = rejected_claim
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
	private String rejectionSubject;
			
	/**data for the column :
	
 --------- rejected_claim.description --------- 
 schema        = null
 tableName     = rejected_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	
 --------- rejected_claim.created_time --------- 
 schema        = null
 tableName     = rejected_claim
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
	
 --------- rejected_claim.updated_time --------- 
 schema        = null
 tableName     = rejected_claim
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
	private java.sql.Timestamp updatedTime;
			
	/**data for the column :
	
 --------- rejected_claim.created_by --------- 
 schema        = null
 tableName     = rejected_claim
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
	
 --------- rejected_claim.updated_by --------- 
 schema        = null
 tableName     = rejected_claim
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
	private String updatedBy;
			
	/**data for the column :
	
 --------- rejected_claim.deleted_time --------- 
 schema        = null
 tableName     = rejected_claim
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
	
 --------- rejected_claim.deleted_by --------- 
 schema        = null
 tableName     = rejected_claim
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
	
 --------- rejected_claim.deleted_status --------- 
 schema        = null
 tableName     = rejected_claim
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
	private Member memberId;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="rejected_claim_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Long getRejectedClaimId(){
		return rejectedClaimId;
	}
	public void setRejectedClaimId(java.lang.Long value){
		rejectedClaimId = value;
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
	@JoinColumn(name="rejection_category")
	public RejectCategory getRejectionCategory(){
		return rejectionCategory;
	}
	public void setRejectionCategory(RejectCategory value){
		rejectionCategory = value;
	}
				@Column(name="rejection_subject")
	public java.lang.String getRejectionSubject(){
		return rejectionSubject;
	}
	public void setRejectionSubject(java.lang.String value){
		rejectionSubject = value;
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
				@Column(name="updated_time")
	public java.sql.Timestamp getUpdatedTime(){
		return updatedTime;
	}
	public void setUpdatedTime(java.sql.Timestamp value){
		updatedTime = value;
	}
				@Column(name="created_by")
	public java.lang.String getCreatedBy(){
		return createdBy;
	}
	public void setCreatedBy(java.lang.String value){
		createdBy = value;
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
	@Column(name="rejection_number")
	public String getRejectionNumber() {
		return rejectionNumber;
	}
	public void setRejectionNumber(String rejectionNumber) {
		this.rejectionNumber = rejectionNumber;
	}
	@Column(name="claim_number")
	public String getClaimNumber() {
		return claimNumber;
	}
	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}
	@ManyToOne
	@JoinColumn(name="member_id")
	public Member getMemberId() {
		return memberId;
	}
	public void setMemberId(Member memberId) {
		this.memberId = memberId;
	}
	
	
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
		
	//exported key end



}