
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="provider_review")
public class ProviderReview implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- provider_review.provider_review_id --------- 
 schema        = null
 tableName     = provider_review
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 15
 type          = -5 
 isPrimaryKey  = true

=========================================


*/
	private Long providerReviewId;
			
	/**data for the column :
	
 --------- provider_review.provider_id --------- 
 schema        = null
 tableName     = provider_review
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

=========================================


*/
	private Integer providerId;
			
	/**data for the column :
	
 --------- provider_review.member_id --------- 
 schema        = null
 tableName     = provider_review
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 10
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer memberId;
			
	/**data for the column :
	
 --------- provider_review.rating --------- 
 schema        = null
 tableName     = provider_review
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 3
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer rating;
			
	/**data for the column :
	
 --------- provider_review.description --------- 
 schema        = null
 tableName     = provider_review
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	
 --------- provider_review.created_time --------- 
 schema        = null
 tableName     = provider_review
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
	
 --------- provider_review.created_by --------- 
 schema        = null
 tableName     = provider_review
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
	
 --------- provider_review.modified_time --------- 
 schema        = null
 tableName     = provider_review
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 8
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- provider_review.modified_by --------- 
 schema        = null
 tableName     = provider_review
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
	
 --------- provider_review.deleted_status --------- 
 schema        = null
 tableName     = provider_review
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- provider_review.deleted_by --------- 
 schema        = null
 tableName     = provider_review
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
	
 --------- provider_review.deleted_time --------- 
 schema        = null
 tableName     = provider_review
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 12
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- provider_review.caller_name --------- 
 schema        = null
 tableName     = provider_review
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 150
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String callerName;
			
	/**data for the column :
	
 --------- provider_review.member_group_id --------- 
 schema        = null
 tableName     = provider_review
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 14
 size          = 10
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer memberGroupId;
			
	/**data for the column :
	
 --------- provider_review.voting_date --------- 
 schema        = null
 tableName     = provider_review
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 15
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date votingDate;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="provider_review_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Long getProviderReviewId(){
		return providerReviewId;
	}
	public void setProviderReviewId(java.lang.Long value){
		providerReviewId = value;
	}
			// PK GETTER SETTER END

						@Column(name="provider_id")
	public java.lang.Integer getProviderId(){
		return providerId;
	}
	public void setProviderId(java.lang.Integer value){
		providerId = value;
	}
				@Column(name="member_id")
	public java.lang.Integer getMemberId(){
		return memberId;
	}
	public void setMemberId(java.lang.Integer value){
		memberId = value;
	}
				@Column(name="rating")
	public java.lang.Integer getRating(){
		return rating;
	}
	public void setRating(java.lang.Integer value){
		rating = value;
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
				@Column(name="caller_name")
	public java.lang.String getCallerName(){
		return callerName;
	}
	public void setCallerName(java.lang.String value){
		callerName = value;
	}
				@Column(name="member_group_id")
	public java.lang.Integer getMemberGroupId(){
		return memberGroupId;
	}
	public void setMemberGroupId(java.lang.Integer value){
		memberGroupId = value;
	}
				@Column(name="voting_date")
	public java.sql.Date getVotingDate(){
		return votingDate;
	}
	public void setVotingDate(java.sql.Date value){
		votingDate = value;
	}
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
		
	//exported key end



}