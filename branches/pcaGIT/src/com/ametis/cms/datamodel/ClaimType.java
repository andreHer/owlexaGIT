
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
@Table(name="claim_type")
public class ClaimType implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	public static final int REIMBURESEMENT = 1;
	public static final int CASHLESS = 2;
	public static final int REIMBURSEMENT_KHUSUS = 3;

	//Fields
		
	/**data for the column :
	
 --------- claim_type.claim_type_id --------- 
 schema        = null
 tableName     = claim_type
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
	private Integer claimTypeId;
			
	/**data for the column :
	
 --------- claim_type.claim_type_name --------- 
 schema        = null
 tableName     = claim_type
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
	private String claimTypeName;
	private String claimTypeCode;
			
	/**data for the column :
	
 --------- claim_type.description --------- 
 schema        = null
 tableName     = claim_type
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
	
 --------- batch_claim.batch_claim_type --------- 
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
 ordinal       = 11
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = claim_type.claim_type_id

=========================================



	 */
	private Set <BatchClaim> batchClaims = new HashSet(0);
				/** Claim
	data for the exported class :
	
 --------- claim.claim_type_id --------- 
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
 ordinal       = 7
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = claim_type.claim_type_id

=========================================



	 */
	private Set <Claim> claims = new HashSet(0);
			
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="claim_type_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getClaimTypeId(){
		return claimTypeId;
	}
	public void setClaimTypeId(java.lang.Integer value){
		claimTypeId = value;
	}
			// PK GETTER SETTER END

						@Column(name="claim_type_name")
	public java.lang.String getClaimTypeName(){
		return claimTypeName;
	}
	public void setClaimTypeName(java.lang.String value){
		claimTypeName = value;
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
	@OneToMany(mappedBy="batchClaimType")
	public Set<BatchClaim> getBatchClaims(){
		return this.batchClaims;
	}

	/** BatchClaim */
	public void setBatchClaims(Set<BatchClaim> obj){
		this.batchClaims = obj;
	}
				/** Claim */
	@OneToMany(mappedBy="claimTypeId")
	public Set<Claim> getClaims(){
		return this.claims;
	}

	/** Claim */
	public void setClaims(Set<Claim> obj){
		this.claims = obj;
	}
	@Column(name="claim_type_code")
	public String getClaimTypeCode() {
		return claimTypeCode;
	}
	public void setClaimTypeCode(String claimTypeCode) {
		this.claimTypeCode = claimTypeCode;
	}
			
	//exported key end

	

}