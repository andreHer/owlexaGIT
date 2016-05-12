
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="document")
public class Document implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- document.document_id --------- 
 schema        = null
 tableName     = document
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
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
	private Integer documentId;
			
	/**data for the column :
	
 --------- document.document_number --------- 
 schema        = null
 tableName     = document
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
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
	private String documentNumber;
									
	/**data for the column :
	
 --------- document.status --------- 
 schema        = null
 tableName     = document
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer status;
			
	/**data for the column :
	
 --------- document.document_url --------- 
 schema        = null
 tableName     = document
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
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
	private String documentUrl;
			
	/**data for the column :
	
 --------- document.created_time --------- 
 schema        = null
 tableName     = document
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = on update CURRENT_TIMESTAMP
 defaultValue  = CURRENT_TIMESTAMP
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 7
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- document.created_by --------- 
 schema        = null
 tableName     = document
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
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
	
 --------- document.modified_time --------- 
 schema        = null
 tableName     = document
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
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
	
 --------- document.modified_by --------- 
 schema        = null
 tableName     = document
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
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
	private String modifiedBy;
			
	/**data for the column :
	
 --------- document.deleted_status --------- 
 schema        = null
 tableName     = document
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
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
	
 --------- document.deleted_by --------- 
 schema        = null
 tableName     = document
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
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
	
 --------- document.deleted_time --------- 
 schema        = null
 tableName     = document
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
					
	// foreign affairs
	
			/** Claim
	data for the foreign class :
	
 --------- claim.claim_id --------- 
 schema        = null
 tableName     = claim
 foreignCol    = claim_id
 foreignTab    = document
 catalog       = qsmart-uat
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
				/** DocumentCategory
	data for the foreign class :
	
 --------- document_category.document_category_id --------- 
 schema        = null
 tableName     = document_category
 foreignCol    = document_category_id
 foreignTab    = document
 catalog       = qsmart-uat
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
	private DocumentCategory documentCategoryId;
				/** Member
	data for the foreign class :
	
 --------- member.member_id --------- 
 schema        = null
 tableName     = member
 foreignCol    = member_id
 foreignTab    = document
 catalog       = qsmart-uat
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
	private Member memberId;
	private Case caseId;
			
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="document_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getDocumentId(){
		return documentId;
	}
	public void setDocumentId(java.lang.Integer value){
		documentId = value;
	}
			// PK GETTER SETTER END

						@Column(name="document_number")
	public java.lang.String getDocumentNumber(){
		return documentNumber;
	}
	public void setDocumentNumber(java.lang.String value){
		documentNumber = value;
	}
										@Column(name="status")
	public java.lang.Integer getStatus(){
		return status;
	}
	public void setStatus(java.lang.Integer value){
		status = value;
	}
				@Column(name="document_url")
	public java.lang.String getDocumentUrl(){
		return documentUrl;
	}
	public void setDocumentUrl(java.lang.String value){
		documentUrl = value;
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
				@Column(name="deleted_status")
	public java.lang.Integer getDeletedStatus(){
		return deletedStatus;
	}
	public void setDeletedStatus(java.lang.Integer value){
		deletedStatus = value;
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
					
	// foreign affairs
	
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
				/** DocumentCategory */
	@ManyToOne
	@JoinColumn(name="document_category_id")
	public DocumentCategory getDocumentCategoryId(){
		return this.documentCategoryId;
	}

	/** DocumentCategory */
	public void setDocumentCategoryId(DocumentCategory obj){
		this.documentCategoryId = obj;
	}
				/** Member */
	@ManyToOne
	@JoinColumn(name="member_id")
	public Member getMemberId(){
		return this.memberId;
	}

	/** Member */
	public void setMemberId(Member obj){
		this.memberId = obj;
	}
	@ManyToOne
	@JoinColumn(name="case_id")
	public Case getCaseId() {
		return caseId;
	}
	public void setCaseId(Case caseId) {
		this.caseId = caseId;
	}
			
	


}