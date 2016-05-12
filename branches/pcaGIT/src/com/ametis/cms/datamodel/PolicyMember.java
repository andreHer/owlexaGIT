
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="policy_member")
public class PolicyMember implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- policy_member.policy_member_id --------- 
 schema        = null
 tableName     = policy_member
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
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
	private Long policyMemberId;
									
	/**data for the column :
	
 --------- policy_member.join_date --------- 
 schema        = null
 tableName     = policy_member
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
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
	private java.sql.Date joinDate;
			
	/**data for the column :
	
 --------- policy_member.effective_date --------- 
 schema        = null
 tableName     = policy_member
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date effectiveDate;
			
	/**data for the column :
	
 --------- policy_member.expire_date --------- 
 schema        = null
 tableName     = policy_member
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date expireDate;
			
	/**data for the column :
	
 --------- policy_member.resigned_date --------- 
 schema        = null
 tableName     = policy_member
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date resignedDate;
			
	/**data for the column :
	
 --------- policy_member.status --------- 
 schema        = null
 tableName     = policy_member
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer status;
		
	// foreign affairs
	
			/** Policy
	data for the foreign class :
	
 --------- policy.policy_id --------- 
 schema        = null
 tableName     = policy
 foreignCol    = policy_id
 foreignTab    = policy_member
 catalog       = wanaartha
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
	private Policy policyId;
				/** Member
	data for the foreign class :
	
 --------- member.member_id --------- 
 schema        = null
 tableName     = member
 foreignCol    = member_id
 foreignTab    = policy_member
 catalog       = wanaartha
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
		
	private Double annualPremium;
	private Double propratePremium;
	private Product productId;
	private String memberNumber;
        private PolicyMemberMovement movementId;
	
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="policy_member_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Long getPolicyMemberId(){
		return policyMemberId;
	}
	public void setPolicyMemberId(java.lang.Long value){
		policyMemberId = value;
	}
			// PK GETTER SETTER END

												@Column(name="join_date")
	public java.sql.Date getJoinDate(){
		return joinDate;
	}
	public void setJoinDate(java.sql.Date value){
		joinDate = value;
	}
				@Column(name="effective_date")
	public java.sql.Date getEffectiveDate(){
		return effectiveDate;
	}
	public void setEffectiveDate(java.sql.Date value){
		effectiveDate = value;
	}
				@Column(name="expire_date")
	public java.sql.Date getExpireDate(){
		return expireDate;
	}
	public void setExpireDate(java.sql.Date value){
		expireDate = value;
	}
				@Column(name="resigned_date")
	public java.sql.Date getResignedDate(){
		return resignedDate;
	}
	public void setResignedDate(java.sql.Date value){
		resignedDate = value;
	}
				@Column(name="status")
	public java.lang.Integer getStatus(){
		return status;
	}
	public void setStatus(java.lang.Integer value){
		status = value;
	}
		
	// foreign affairs
	
			/** Policy */
	@ManyToOne
	@JoinColumn(name="policy_id")
	public Policy getPolicyId(){
		return this.policyId;
	}

	/** Policy */
	public void setPolicyId(Policy obj){
		this.policyId = obj;
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
	
	@Column(name="annual_premium")
	public Double getAnnualPremium() {
		return annualPremium;
	}
	public void setAnnualPremium(Double annualPremium) {
		this.annualPremium = annualPremium;
	}
	@Column(name="prorate_premium")
	public Double getPropratePremium() {
		return propratePremium;
	}
	public void setPropratePremium(Double propratePremium) {
		this.propratePremium = propratePremium;
	}
	@ManyToOne
	@JoinColumn (name="product_id")	
	public Product getProductId() {
		return productId;
	}
	public void setProductId(Product productId) {
		this.productId = productId;
	}
	@Column (name="member_number")
	public String getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}
			
	
	
	// foreign affairs end

// exported key

	
		
	//exported key end

        @ManyToOne
        @JoinColumn (name="movement_id")
    public PolicyMemberMovement getMovementId() {
        return movementId;
    }

    public void setMovementId(PolicyMemberMovement movementId) {
        this.movementId = movementId;
    }



}