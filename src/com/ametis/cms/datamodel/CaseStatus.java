
package com.ametis.cms.datamodel;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="case_status")
public class CaseStatus implements java.io.Serializable{
	
	
	private static final long serialVersionUID = 1L;
	public static final int VOID = -1;
	public static final int CASE_OPEN = 1;
	public static final int CASE_CLOSED = 5;
	public static final int CASE_REFERED = 17;
	public static final int CASE_FINALIZED = 15;
	public static final int CASE_APPROVED = 9;
	public static final int CASE_GREY_AREA = 18;
	
	public static final int CASE_PENDING = 10;
	public static final int CASE_PENDING_INVESTIGATION = 7;
	public static final int CASE_PENDING_DOCUMENT = 2;
	//Add 20150414 by FVO, penambahan status baru untuk claim yang di create melalui case
	public static final int CASE_PRE_OPEN = 19;
	//Add by aju on 20150824, for preAdmission
	public static final int CASE_PRE_AUTH = 20;
	public static final int CASE_PRE_REJECT = 21;
	public static final int CASE_PRE_PENDING = 22;
	public static final int CASE_REJECT = 4;

	 

	//Fields
		
	/**data for the column :
	
 --------- case_status.case_status_id --------- 
 schema        = null
 tableName     = case_status
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
 type          = 4 
 isPrimaryKey  = true

=========================================


*/
	private Integer caseStatusId;
			
	/**data for the column :
	
 --------- case_status.case_status_name --------- 
 schema        = null
 tableName     = case_status
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String caseStatusName;
			
	/**data for the column :
	
 --------- case_status.description --------- 
 schema        = null
 tableName     = case_status
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String description;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
			/** BatchClaim
	data for the exported class :
	
 --------- batch_claim.batch_claim_status --------- 
 schema        = null
 tableName     = batch_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = case_status.case_status_id

=========================================



	 */
	private Set <BatchClaim> batchClaims = new HashSet(0);
				/** Case
	data for the exported class :
	
 --------- case.case_status_id --------- 
 schema        = null
 tableName     = case
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = case_status.case_status_id

=========================================



	 */
	private Set <Case> myCases = new HashSet(0);
				/** Claim
	data for the exported class :
	
 --------- claim.claim_status --------- 
 schema        = null
 tableName     = claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 24
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = case_status.case_status_id

=========================================



	 */
	private Set <Claim> claims = new HashSet(0);
				/** FirstCall
	data for the exported class :
	
 --------- first_call.status --------- 
 schema        = null
 tableName     = first_call
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = case_status.case_status_id

=========================================



	 */
	private Set <FirstCall> firstCalls = new HashSet(0);
	
	public CaseStatus(int status){
		this.caseStatusId = Integer.valueOf(status);
	}
	public CaseStatus(){
		
	}
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="case_status_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getCaseStatusId(){
		return caseStatusId;
	}
	public void setCaseStatusId(java.lang.Integer value){
		caseStatusId = value;
	}
			// PK GETTER SETTER END

						@Column(name="case_status_name")
	public java.lang.String getCaseStatusName(){
		return caseStatusName;
	}
	public void setCaseStatusName(java.lang.String value){
		caseStatusName = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
			/** BatchClaim */
	@OneToMany(mappedBy="batchClaimStatus")
	public Set<BatchClaim> getBatchClaims(){
		return this.batchClaims;
	}

	/** BatchClaim */
	public void setBatchClaims(Set<BatchClaim> obj){
		this.batchClaims = obj;
	}
				/** Case */
	@OneToMany(mappedBy="caseStatusId")
	public Set<Case> getCases(){
		return this.myCases;
	}

	/** Case */
	public void setCases(Set<Case> obj){
		this.myCases = obj;
	}
				/** Claim */
	@OneToMany(mappedBy="claimStatus")
	public Set<Claim> getClaims(){
		return this.claims;
	}

	/** Claim */
	public void setClaims(Set<Claim> obj){
		this.claims = obj;
	}
				/** FirstCall */
	@OneToMany(mappedBy="status")
	public Set<FirstCall> getFirstCalls(){
		return this.firstCalls;
	}

	/** FirstCall */
	public void setFirstCalls(Set<FirstCall> obj){
		this.firstCalls = obj;
	}
			
	//exported key end
	private Integer orderStatus;

	@Column(name="order_status")
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	
}