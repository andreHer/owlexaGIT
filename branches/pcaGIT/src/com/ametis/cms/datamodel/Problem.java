
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="problem")
public class Problem implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- problem.problem_id --------- 
 schema        = null
 tableName     = problem
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
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
	private Integer problemId;
			
	/**data for the column :
	
 --------- problem.problem_time --------- 
 schema        = null
 tableName     = problem
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp problemTime;
			
	/**data for the column :
	
 --------- problem.problem_resolution_time --------- 
 schema        = null
 tableName     = problem
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp problemResolutionTime;
			
	/**data for the column :
	
 --------- problem.claim_id --------- 
 schema        = null
 tableName     = problem
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
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
	private Integer claimId;
			
	/**data for the column :
	
 --------- problem.batch_claim_id --------- 
 schema        = null
 tableName     = problem
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
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
	private Integer batchClaimId;
			
	/**data for the column :
	
 --------- problem.claim_item_id --------- 
 schema        = null
 tableName     = problem
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer claimItemId;
			
	/**data for the column :
	
 --------- problem.payment_id --------- 
 schema        = null
 tableName     = problem
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer paymentId;
			
	/**data for the column :
	
 --------- problem.created_by --------- 
 schema        = null
 tableName     = problem
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
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
	
 --------- problem.resolved_by --------- 
 schema        = null
 tableName     = problem
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
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
	private String resolvedBy;
			
	/**data for the column :
	
 --------- problem.description --------- 
 schema        = null
 tableName     = problem
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String description;
			
	/**data for the column :
	
 --------- problem.problem_type --------- 
 schema        = null
 tableName     = problem
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 3
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer problemType;
			
	/**data for the column :
	
 --------- problem.problem_subject --------- 
 schema        = null
 tableName     = problem
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 3
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer problemSubject;
			
	/**data for the column :
	
 --------- problem.problem_status --------- 
 schema        = null
 tableName     = problem
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
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
	private Integer problemStatus;
			
	/**data for the column :
	
 --------- problem.created_time --------- 
 schema        = null
 tableName     = problem
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 14
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- problem.modified_time --------- 
 schema        = null
 tableName     = problem
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 15
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- problem.modified_by --------- 
 schema        = null
 tableName     = problem
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 16
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- problem.deleted_time --------- 
 schema        = null
 tableName     = problem
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 17
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- problem.deleted_by --------- 
 schema        = null
 tableName     = problem
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 18
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- problem.deleted_status --------- 
 schema        = null
 tableName     = problem
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 19
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
	@Column(name="problem_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getProblemId(){
		return problemId;
	}
	public void setProblemId(java.lang.Integer value){
		problemId = value;
	}
			// PK GETTER SETTER END

						@Column(name="problem_time")
	public java.sql.Timestamp getProblemTime(){
		return problemTime;
	}
	public void setProblemTime(java.sql.Timestamp value){
		problemTime = value;
	}
				@Column(name="problem_resolution_time")
	public java.sql.Timestamp getProblemResolutionTime(){
		return problemResolutionTime;
	}
	public void setProblemResolutionTime(java.sql.Timestamp value){
		problemResolutionTime = value;
	}
				@Column(name="claim_id")
	public java.lang.Integer getClaimId(){
		return claimId;
	}
	public void setClaimId(java.lang.Integer value){
		claimId = value;
	}
				@Column(name="batch_claim_id")
	public java.lang.Integer getBatchClaimId(){
		return batchClaimId;
	}
	public void setBatchClaimId(java.lang.Integer value){
		batchClaimId = value;
	}
				@Column(name="claim_item_id")
	public java.lang.Integer getClaimItemId(){
		return claimItemId;
	}
	public void setClaimItemId(java.lang.Integer value){
		claimItemId = value;
	}
				@Column(name="payment_id")
	public java.lang.Integer getPaymentId(){
		return paymentId;
	}
	public void setPaymentId(java.lang.Integer value){
		paymentId = value;
	}
				@Column(name="created_by")
	public java.lang.String getCreatedBy(){
		return createdBy;
	}
	public void setCreatedBy(java.lang.String value){
		createdBy = value;
	}
				@Column(name="resolved_by")
	public java.lang.String getResolvedBy(){
		return resolvedBy;
	}
	public void setResolvedBy(java.lang.String value){
		resolvedBy = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
				@Column(name="problem_type")
	public java.lang.Integer getProblemType(){
		return problemType;
	}
	public void setProblemType(java.lang.Integer value){
		problemType = value;
	}
				@Column(name="problem_subject")
	public java.lang.Integer getProblemSubject(){
		return problemSubject;
	}
	public void setProblemSubject(java.lang.Integer value){
		problemSubject = value;
	}
				@Column(name="problem_status")
	public java.lang.Integer getProblemStatus(){
		return problemStatus;
	}
	public void setProblemStatus(java.lang.Integer value){
		problemStatus = value;
	}
				@Column(name="created_time")
	public java.sql.Timestamp getCreatedTime(){
		return createdTime;
	}
	public void setCreatedTime(java.sql.Timestamp value){
		createdTime = value;
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
	
		
	// foreign affairs end

// exported key

	
		
	//exported key end



}