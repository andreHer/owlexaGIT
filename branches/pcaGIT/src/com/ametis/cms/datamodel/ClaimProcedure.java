
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;

import org.hibernate.annotations.Generated;


@Entity
@Table(name="claim_procedure")
public class ClaimProcedure implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- claim_procedure.claim_procedure_id --------- 
 schema        = public
 tableName     = claim_procedure
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = nextval('claim_procedure_claim_procedure_id_seq'::regclass)
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 10
 type          = 4 
 isPrimaryKey  = true

=========================================


*/
	private Integer claimProcedureId;
			
	/**data for the column :
	
 --------- claim_procedure.claim_id --------- 
 schema        = public
 tableName     = claim_procedure
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
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
	private Claim claimId;
			
	/**data for the column :
	
 --------- claim_procedure.procedure_id --------- 
 schema        = public
 tableName     = claim_procedure
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
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
	private Procedure procedureId;
			
	/**data for the column :
	
 --------- claim_procedure.total_usage --------- 
 schema        = public
 tableName     = claim_procedure
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = null
 decDigits     = 17
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 17
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double totalUsage;
			
	/**data for the column :
	
 --------- claim_procedure.total_charge --------- 
 schema        = public
 tableName     = claim_procedure
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = null
 decDigits     = 17
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 17
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double totalCharge;
			
	/**data for the column :
	
 --------- claim_procedure.reference_price --------- 
 schema        = public
 tableName     = claim_procedure
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = null
 decDigits     = 17
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 17
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double referencePrice;
			
	/**data for the column :
	
 --------- claim_procedure.description --------- 
 schema        = public
 tableName     = claim_procedure
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 2147483647
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String description;
			
	/**data for the column :
	
 --------- claim_procedure.created_time --------- 
 schema        = public
 tableName     = claim_procedure
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = null
 decDigits     = 6
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 29
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- claim_procedure.created_by --------- 
 schema        = public
 tableName     = claim_procedure
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
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
	private String createdBy;
			
	/**data for the column :
	
 --------- claim_procedure.modified_time --------- 
 schema        = public
 tableName     = claim_procedure
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = null
 decDigits     = 6
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 29
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- claim_procedure.modified_by --------- 
 schema        = public
 tableName     = claim_procedure
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
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
	private String modifiedBy;
			
	/**data for the column :
	
 --------- claim_procedure.deleted_time --------- 
 schema        = public
 tableName     = claim_procedure
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = null
 decDigits     = 6
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 29
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- claim_procedure.deleted_by --------- 
 schema        = public
 tableName     = claim_procedure
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- claim_procedure.deleted_status --------- 
 schema        = public
 tableName     = claim_procedure
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
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
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- claim_procedure.approved_value --------- 
 schema        = public
 tableName     = claim_procedure
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = null
 decDigits     = 17
 radix         = 10
 nullable      = 1
 ordinal       = 15
 size          = 17
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double approvedValue;
			
	/**data for the column :
	
 --------- claim_procedure.status --------- 
 schema        = public
 tableName     = claim_procedure
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 16
 size          = 10
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer status;
	
	private String approvalRemarks;
	
	private Integer caseProcedureId;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="claim_procedure_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public java.lang.Integer getClaimProcedureId(){
		return claimProcedureId;
	}
	public void setClaimProcedureId(java.lang.Integer value){
		claimProcedureId = value;
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
	@JoinColumn(name="procedure_id")
	public Procedure getProcedureId(){
		return procedureId;
	}
	public void setProcedureId(Procedure value){
		procedureId = value;
	}
	
				@Column(name="total_usage")
	public java.lang.Double getTotalUsage(){
		return totalUsage;
	}
	public void setTotalUsage(java.lang.Double value){
		totalUsage = value;
	}
				@Column(name="total_charge")
	public java.lang.Double getTotalCharge(){
		return totalCharge;
	}
	public void setTotalCharge(java.lang.Double value){
		totalCharge = value;
	}
				@Column(name="reference_price")
	public java.lang.Double getReferencePrice(){
		return referencePrice;
	}
	public void setReferencePrice(java.lang.Double value){
		referencePrice = value;
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
				@Column(name="approved_value")
	public java.lang.Double getApprovedValue(){
		return approvedValue;
	}
	public void setApprovedValue(java.lang.Double value){
		approvedValue = value;
	}
				@Column(name="status")
	public java.lang.Integer getStatus(){
		return status;
	}
	public void setStatus(java.lang.Integer value){
		status = value;
	}
	
	@Column(name="approval_remarks")
	public String getApprovalRemarks() {
		return approvalRemarks;
	}
	public void setApprovalRemarks(String approvalRemarks) {
		this.approvalRemarks = approvalRemarks;
	}
	
	@Column(name="case_procedure_id")
	public Integer getCaseProcedureId() {
		return caseProcedureId;
	}
	public void setCaseProcedureId(Integer caseProcedureId) {
		this.caseProcedureId = caseProcedureId;
	}
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
		
	//exported key end



}