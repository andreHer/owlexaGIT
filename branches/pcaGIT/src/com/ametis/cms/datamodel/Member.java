
package com.ametis.cms.datamodel;


import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;



@Entity
@Table(name="member")
public class Member implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- member.member_id --------- 
 schema        = null
 tableName     = member
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
	private Integer memberId;
			
	/**data for the column :
	
 --------- member.status --------- 
 schema        = null
 tableName     = member
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
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer status;
			
	/**data for the column :
	
 --------- member.first_name --------- 
 schema        = null
 tableName     = member
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
	private String firstName;
			
	/**data for the column :
	
 --------- member.last_name --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String lastName;
						
	/**data for the column :
	
 --------- member.mobile_phone --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String mobilePhone;
			
	/**data for the column :
	
 --------- member.faximile --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String faximile;
			
	/**data for the column :
	
 --------- member.customer_number --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String customerNumber;
			
	/**data for the column :
	
 --------- member.telephone --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String telephone;
			
	/**data for the column :
	
 --------- member.email --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String email;
			
	/**data for the column :
	
 --------- member.customer_policy_number --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String customerPolicyNumber;
			
	/**data for the column :
	
 --------- member.effective_date --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date effectiveDate;
			
	/**data for the column :
	
 --------- member.renewal_date --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date renewalDate;
			
	/**data for the column :
	
 --------- member.join_date --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 14
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date joinDate;
			
	/**data for the column :
	
 --------- member.resigned_date --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private java.sql.Date resignedDate;
			
	/**data for the column :
	
 --------- member.expire_date --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 16
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date expireDate;
			
	/**data for the column :
	
 --------- member.address --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 17
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String address;
			
	/**data for the column :
	
 --------- member.birthday --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 18
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date birthday;
			
	/**data for the column :
	
 --------- member.birthplace --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 19
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String birthplace;
									
	/**data for the column :
	
 --------- member.customer_limit --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 22
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double customerLimit;
			
	/**data for the column :
	
 --------- member.province --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 23
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String province;
			
	/**data for the column :
	
 --------- member.city --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 24
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String city;
			
	/**data for the column :
	
 --------- member.postal_code --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 25
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String postalCode;
			
	/**data for the column :
	
 --------- member.country --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 26
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String country;
			
	/**data for the column :
	
 --------- member.actual_customer_limit --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 27
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double actualCustomerLimit;
			
	/**data for the column :
	
 --------- member.policy_agent --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 28
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String policyAgent;
			
	/**data for the column :
	
 --------- member.bank_account --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 29
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String bankAccount;
			
	/**data for the column :
	
 --------- member.bank_account_name --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 30
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String bankAccountName;
			
	/**data for the column :
	
 --------- member.bank --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 31
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String bank;
			
	/**data for the column :
	
 --------- member.department --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 32
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String department;
			
	/**data for the column :
	
 --------- member.job_position --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 33
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String jobPosition;
						
	/**data for the column :
	
 --------- member.created_time --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 35
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- member.created_by --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 36
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- member.deleted_time --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 37
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- member.deleted_by --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 38
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- member.modified_time --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 39
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- member.modified_by --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 40
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- member.deleted_status --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 41
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
		
	// foreign affairs
	
			/** ProductType
	data for the foreign class :
	
 --------- product_type.product_type_id --------- 
 schema        = null
 tableName     = product_type
 foreignCol    = member_type
 foreignTab    = member
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
	private ProductType memberType;
				/** Client
	data for the foreign class :
	
 --------- client.client_id --------- 
 schema        = null
 tableName     = client
 foreignCol    = client_id
 foreignTab    = member
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
	private Client clientId;
	private Broker brokerId;
	private String brokerName;
	private String parentName;
	private String parentNumber;
	private Double currentSalary;
	private String currentPolicyNumber;
	private Integer isVIP;
	private Integer isWarning;
	private Integer isBlacklist;
	private Integer policyPaymentPeriode;
	private String paymentPeriodeMethod;
	private Date latestPolicyPaymentDate;
	private Integer autoRenewalStatus;
	private Date pendingRenewalEffectiveDate;
	private Date pendingRenewalExpireDate;
	
	private Date noClaimStartDate;
	private Date noClaimEndDate;
	
	
	private Integer ddEffective;
	private Integer mmEffective;
	private Integer yyyyEffective;
	
	private Integer renewalPeriode;
	
	
				/** MemberGroup
	data for the foreign class :
	
 --------- member_group.member_group_id --------- 
 schema        = null
 tableName     = member_group
 foreignCol    = member_group_id
 foreignTab    = member
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
	private MemberGroup memberGroupId;
				/** Relationship
	data for the foreign class :
	
 --------- relationship.relationship_id --------- 
 schema        = null
 tableName     = relationship
 foreignCol    = relationship_id
 foreignTab    = member
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
	private Relationship relationshipId;
	private Policy currentPolicyId;
	private Date suspendStartDate;
	private Date suspendEndDate;
	private Date previousExpireDate;
	
	private Date nextExpireDate;
	private Date policyExpireDate;
	
	private String nextCustomerNumber;
	private String nextPolicyNumber;
	private String nextCardNumber;
	
	private Date nextEffectiveDate;
	
	
	
			
	// -- foreign affairs end

	// -- exported key

	
			/** BatchClaim
	data for the exported class :
	
 --------- batch_claim.member_id --------- 
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
 ordinal       = 14
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = member.member_id

=========================================



	 */
	private Set <BatchClaim> batchClaims = new HashSet(0);
				/** Case
	data for the exported class :
	
 --------- case.member_id --------- 
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
 ordinal       = 2
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = member.member_id

=========================================



	 */
	private Set <Case> myCases = new HashSet(0);
				/** Claim
	data for the exported class :
	
 --------- claim.member_id --------- 
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
 ordinal       = 3
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = member.member_id

=========================================



	 */
	private Set <Claim> claims = new HashSet(0);
				/** ExcessCharge
	data for the exported class :
	
 --------- excess_charge.member_id --------- 
 schema        = null
 tableName     = excess_charge
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = member.member_id

=========================================



	 */
	private Set <ExcessCharge> excessCharges = new HashSet(0);
				/** FirstCall
	data for the exported class :
	
 --------- first_call.customer_id --------- 
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
 ordinal       = 4
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = member.member_id

=========================================



	 */
	private Set <FirstCall> firstCalls = new HashSet(0);
				/** MemberBenefit
	data for the exported class :
	
 --------- member_benefit.member_id --------- 
 schema        = null
 tableName     = member_benefit
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
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = member.member_id

=========================================



	 */
	private Set <MemberBenefit> memberBenefits = new HashSet(0);
				/** MemberDiagnosis
	data for the exported class :
	
 --------- member_diagnosis.member_id --------- 
 schema        = null
 tableName     = member_diagnosis
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
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = member.member_id

=========================================



	 */
	private Set <MemberDiagnosis> memberDiagnosiss = new HashSet(0);
				/** MemberPackage
	data for the exported class :
	
 --------- member_package.member_id --------- 
 schema        = null
 tableName     = member_package
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
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = member.member_id

=========================================



	 */
	private Set <MemberPackage> memberPackages = new HashSet(0);
				/** MemberProduct
	data for the exported class :
	
 --------- member_product.member_id --------- 
 schema        = null
 tableName     = member_product
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
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = member.member_id

=========================================



	 */
	private Set <MemberProduct> memberProducts = new HashSet(0);
				/** Payment
	data for the exported class :
	
 --------- payment.member_id --------- 
 schema        = null
 tableName     = payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = member.member_id

=========================================



	 */
	private Set <Payment> payments = new HashSet(0);
	private Set<Member> dependants = new HashSet<Member>(0);
	private String bankBranch;
	private String swiftCode;
	private String relationship;
	private String gender;
	private String currentProductCode;
	private Member parentMember;
	private Double currentBenefitUsage;
	private Double currentAnnualPremium;
	private String tipe;
	private String currentCardNumber;
	private Date productUpgradeEffectiveDate;
	private String otherMemberNumber;
	private String employeeIDNumber;
	private String clientName;
	private String groupName;
	private String subPolicyNumber;
	private String linkedCardNumber;
	private String movementNumber;
	private Integer subjectToNoClaim;
	
	private String nextNewGroupCode;
	private String nextNewRelationship;
	private String nextEmployeeNumber;
	
	private Integer latestMonthMembershipBilled;
	private Integer latestYearMembershipBilled;
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="member_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getMemberId(){
		return memberId;
	}
	public void setMemberId(java.lang.Integer value){
		memberId = value;
	}
			// PK GETTER SETTER END
	@Column(name="status")
	public Integer getStatus(){
		return status;
	}
	public void setStatus(Integer value){
		status = value;
	}
	
//	private String formatModifiedTime;
//	@Formula(value="to_char(modified_time, 'YYYY-MM-DD')")
//	public String getFormatModifiedTime() {
//		return formatModifiedTime;
//	}
//	public void setFormatModifiedTime(String formatModifiedTime) {
//		this.formatModifiedTime = formatModifiedTime;
//	}
//	
//	private String formatCreatedTime;
//	@Formula(value="to_char(created_time, 'YYYY-MM-DD')")
//	public String getFormatCreatedTime() {
//		return formatCreatedTime;
//	}
//	public void setFormatCreatedTime(String formatCreatedTime) {
//		this.formatCreatedTime = formatCreatedTime;
//	}

	private String fullName;
	@Formula(value="upper(coalesce(first_name,'')||' '||coalesce(last_name,''))")
	public java.lang.String getFullName(){
		return fullName;
	}
	public void setFullName (java.lang.String value){
		fullName = value;
	}
	
				@Column(name="first_name")
	public java.lang.String getFirstName(){
		return firstName;
	}
	public void setFirstName(java.lang.String value){
		firstName = value;
	}
				@Column(name="last_name")
	public java.lang.String getLastName(){
		return lastName;
	}
	public void setLastName(java.lang.String value){
		lastName = value;
	}
							@Column(name="mobile_phone")
	public java.lang.String getMobilePhone(){
		return mobilePhone;
	}
	public void setMobilePhone(java.lang.String value){
		mobilePhone = value;
	}
				@Column(name="faximile")
	public java.lang.String getFaximile(){
		return faximile;
	}
	public void setFaximile(java.lang.String value){
		faximile = value;
	}
				@Column(name="customer_number")
	public java.lang.String getCustomerNumber(){
		return customerNumber;
	}
	public void setCustomerNumber(java.lang.String value){
		customerNumber = value;
	}
				@Column(name="telephone")
	public java.lang.String getTelephone(){
		return telephone;
	}
	public void setTelephone(java.lang.String value){
		telephone = value;
	}
				@Column(name="email")
	public java.lang.String getEmail(){
		return email;
	}
	public void setEmail(java.lang.String value){
		email = value;
	}
				@Column(name="customer_policy_number")
	public java.lang.String getCustomerPolicyNumber(){
		return customerPolicyNumber;
	}
	public void setCustomerPolicyNumber(java.lang.String value){
		customerPolicyNumber = value;
	}
				@Column(name="effective_date")
	public java.sql.Date getEffectiveDate(){
		return effectiveDate;
	}
	public void setEffectiveDate(java.sql.Date value){
		effectiveDate = value;
	}
				@Column(name="renewal_date")
	public java.sql.Date getRenewalDate(){
		return renewalDate;
	}
	public void setRenewalDate(java.sql.Date value){
		renewalDate = value;
	}
				@Column(name="join_date")
	public java.sql.Date getJoinDate(){
		return joinDate;
	}
	public void setJoinDate(java.sql.Date value){
		joinDate = value;
	}
				@Column(name="resigned_date")
	public java.sql.Date getResignedDate(){
		return resignedDate;
	}
	public void setResignedDate(java.sql.Date value){
		resignedDate = value;
	}
				@Column(name="expire_date")
	public java.sql.Date getExpireDate(){
		return expireDate;
	}
	public void setExpireDate(java.sql.Date value){
		expireDate = value;
	}
				@Column(name="address")
	public java.lang.String getAddress(){
		return address;
	}
	public void setAddress(java.lang.String value){
		address = value;
	}
				@Column(name="birthday")
	public java.sql.Date getBirthday(){
		return birthday;
	}
	public void setBirthday(java.sql.Date value){
		birthday = value;
	}
				@Column(name="birthplace")
	public java.lang.String getBirthplace(){
		return birthplace;
	}
	public void setBirthplace(java.lang.String value){
		birthplace = value;
	}
										@Column(name="customer_limit")
	public java.lang.Double getCustomerLimit(){
		return customerLimit;
	}
	public void setCustomerLimit(java.lang.Double value){
		customerLimit = value;
	}
				@Column(name="province")
	public java.lang.String getProvince(){
		return province;
	}
	public void setProvince(java.lang.String value){
		province = value;
	}
				@Column(name="city")
	public java.lang.String getCity(){
		return city;
	}
	public void setCity(java.lang.String value){
		city = value;
	}
				@Column(name="postal_code")
	public java.lang.String getPostalCode(){
		return postalCode;
	}
	public void setPostalCode(java.lang.String value){
		postalCode = value;
	}
				@Column(name="country")
	public java.lang.String getCountry(){
		return country;
	}
	public void setCountry(java.lang.String value){
		country = value;
	}
				@Column(name="actual_customer_limit")
	public java.lang.Double getActualCustomerLimit(){
		return actualCustomerLimit;
	}
	public void setActualCustomerLimit(java.lang.Double value){
		actualCustomerLimit = value;
	}
				@Column(name="policy_agent")
	public java.lang.String getPolicyAgent(){
		return policyAgent;
	}
	public void setPolicyAgent(java.lang.String value){
		policyAgent = value;
	}
				@Column(name="bank_account")
	public java.lang.String getBankAccount(){
		return bankAccount;
	}
	public void setBankAccount(java.lang.String value){
		bankAccount = value;
	}
				@Column(name="bank_account_name")
	public java.lang.String getBankAccountName(){
		return bankAccountName;
	}
	public void setBankAccountName(java.lang.String value){
		bankAccountName = value;
	}
				@Column(name="bank")
	public java.lang.String getBank(){
		return bank;
	}
	public void setBank(java.lang.String value){
		bank = value;
	}
				@Column(name="department")
	public java.lang.String getDepartment(){
		return department;
	}
	public void setDepartment(java.lang.String value){
		department = value;
	}
				@Column(name="job_position")
	public java.lang.String getJobPosition(){
		return jobPosition;
	}
	public void setJobPosition(java.lang.String value){
		jobPosition = value;
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
		
	// foreign affairs
	
			/** ProductType */
	@ManyToOne (fetch=FetchType.LAZY)
	@JoinColumn(name="member_type",nullable=true)	
	public ProductType getMemberType(){
		return this.memberType;
	}

	/** ProductType */
	public void setMemberType(ProductType obj){
		this.memberType = obj;
	}
				/** Client */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="client_id",nullable=true)
	public Client getClientId(){
		return this.clientId;
	}

	/** Client */
	public void setClientId(Client obj){
		this.clientId = obj;
	}
				/** MemberGroup */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="member_group_id",nullable=true)	
	public MemberGroup getMemberGroupId(){
		return this.memberGroupId;
	}

	/** MemberGroup */
	public void setMemberGroupId(MemberGroup obj){
		this.memberGroupId = obj;
	}
				/** Relationship */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="relationship_id",nullable=true)
	public Relationship getRelationshipId(){
		return this.relationshipId;
	}

	/** Relationship */
	public void setRelationshipId(Relationship obj){
		this.relationshipId = obj;
	}
			
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent_id",nullable=true)	
	public Member getParentMember() {
		return parentMember;
	}
	public void setParentMember(Member parentMember) {
		this.parentMember = parentMember;
	}
			/** BatchClaim */
	@OneToMany(mappedBy="memberId")
	public Set<BatchClaim> getBatchClaims(){
		return this.batchClaims;
	}

	/** BatchClaim */
	public void setBatchClaims(Set<BatchClaim> obj){
		this.batchClaims = obj;
	}
				/** Case */
	@OneToMany(mappedBy="memberId")
	public Set<Case> getCases(){
		return this.myCases;
	}

	/** Case */
	public void setCases(Set<Case> obj){
		this.myCases = obj;
	}
				/** Claim */
	@OneToMany(mappedBy="memberId")
	public Set<Claim> getClaims(){
		return this.claims;
	}

	/** Claim */
	public void setClaims(Set<Claim> obj){
		this.claims = obj;
	}
				/** ExcessCharge */
	@OneToMany(mappedBy="memberId")
	public Set<ExcessCharge> getExcessCharges(){
		return this.excessCharges;
	}

	/** ExcessCharge */
	public void setExcessCharges(Set<ExcessCharge> obj){
		this.excessCharges = obj;
	}
				/** FirstCall */
	@OneToMany(mappedBy="customerId")
	public Set<FirstCall> getFirstCalls(){
		return this.firstCalls;
	}

	/** FirstCall */
	public void setFirstCalls(Set<FirstCall> obj){
		this.firstCalls = obj;
	}
				/** MemberBenefit */
	@OneToMany(mappedBy="memberId")
	public Set<MemberBenefit> getMemberBenefits(){
		return this.memberBenefits;
	}

	/** MemberBenefit */
	public void setMemberBenefits(Set<MemberBenefit> obj){
		this.memberBenefits = obj;
	}
				/** MemberDiagnosis */
	@OneToMany(mappedBy="memberId")
	public Set<MemberDiagnosis> getMemberDiagnosiss(){
		return this.memberDiagnosiss;
	}

	/** MemberDiagnosis */
	public void setMemberDiagnosiss(Set<MemberDiagnosis> obj){
		this.memberDiagnosiss = obj;
	}
				/** MemberPackage */
	@OneToMany(mappedBy="memberId")
	public Set<MemberPackage> getMemberPackages(){
		return this.memberPackages;
	}

	/** MemberPackage */
	public void setMemberPackages(Set<MemberPackage> obj){
		this.memberPackages = obj;
	}
				/** MemberProduct */
	@OneToMany(mappedBy="memberId")
	public Set<MemberProduct> getMemberProducts(){
		return this.memberProducts;
	}

	/** MemberProduct */
	public void setMemberProducts(Set<MemberProduct> obj){
		this.memberProducts = obj;
	}
				/** Payment */
	@OneToMany(mappedBy="memberId")
	public Set<Payment> getPayments(){
		return this.payments;
	}
	

	/** Payment */
	public void setPayments(Set<Payment> obj){
		this.payments = obj;
	}
	
	@OneToMany(mappedBy="memberId")
	public Set<Member> getDependants() {
		return dependants;
	}
	public void setDependants(Set<Member> dependants) {
		this.dependants = dependants;
	}
	@Column (name="bank_branch")
	public String getBankBranch() {
		return bankBranch;
	}
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	@Column(name="relationship")
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	@Column(name="gender")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Column(name="current_product_code")
	public String getCurrentProductCode() {
		return currentProductCode;
	}
	public void setCurrentProductCode(String currentProductCode) {
		this.currentProductCode = currentProductCode;
	}
	@Column(name="current_benefit_usage")
	public Double getCurrentBenefitUsage() {
		return currentBenefitUsage;
	}
	public void setCurrentBenefitUsage(Double currentBenefitUsage) {
		this.currentBenefitUsage = currentBenefitUsage;
	}
	@Column(name="current_annual_premium")
	public Double getCurrentAnnualPremium() {
		return currentAnnualPremium;
	}
	public void setCurrentAnnualPremium(Double currentAnnualPremium) {
		this.currentAnnualPremium = currentAnnualPremium;
	}
	@Column(name="tipe")
	public String getTipe() {
		return tipe;
	}
	public void setTipe(String tipe) {
		this.tipe = tipe;
	}
	@Column(name="current_card_number")
	public String getCurrentCardNumber() {
		return currentCardNumber;
	}
	public void setCurrentCardNumber(String currentCardNumber) {
		this.currentCardNumber = currentCardNumber;
	}
	@Column(name="product_upgrade_effective_date")
	public Date getProductUpgradeEffectiveDate() {
		return productUpgradeEffectiveDate;
	}
	public void setProductUpgradeEffectiveDate(Date productUpgradeEffectiveDate) {
		this.productUpgradeEffectiveDate = productUpgradeEffectiveDate;
	}
	@Column(name="other_member_number")
	public String getOtherMemberNumber() {
		return otherMemberNumber;
	}
	public void setOtherMemberNumber(String otherMemberNumber) {
		this.otherMemberNumber = otherMemberNumber;
	}
	@Column(name="client_name")
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	@Column(name="group_name")
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="broker_id")
	public Broker getBrokerId() {
		return brokerId;
	}
	public void setBrokerId(Broker brokerId) {
		this.brokerId = brokerId;
	}
	@Column(name="broker_name")
	public String getBrokerName() {
		return brokerName;
	}
	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}
	@Column(name="parent_name")
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	@Column(name="parent_number")
	public String getParentNumber() {
		return parentNumber;
	}
	public void setParentNumber(String parentNumber) {
		this.parentNumber = parentNumber;
	}
	@Column(name="current_salary")
	public Double getCurrentSalary() {
		return currentSalary;
	}
	public void setCurrentSalary(Double currentSalary) {
		this.currentSalary = currentSalary;
	}
	@Column(name="is_vip")
	public Integer getIsVIP() {
		return isVIP;
	}
	public void setIsVIP(Integer isVIP) {
		this.isVIP = isVIP;
	}
	@Column(name="current_policy_number")
	public String getCurrentPolicyNumber() {
		return currentPolicyNumber;
	}
	public void setCurrentPolicyNumber(String currentPolicyNumber) {
		this.currentPolicyNumber = currentPolicyNumber;
	}
	@Column(name="swift_code")
	public String getSwiftCode() {
		return swiftCode;
	}
	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="current_policy_id")
	public Policy getCurrentPolicyId() {
		return currentPolicyId;
	}
	public void setCurrentPolicyId(Policy currentPolicyId) {
		this.currentPolicyId = currentPolicyId;
	}
	@Column(name="suspend_start_date")
	public Date getSuspendStartDate() {
		return suspendStartDate;
	}
	public void setSuspendStartDate(Date suspendStartDate) {
		this.suspendStartDate = suspendStartDate;
	}
	@Column(name="suspend_end_date")
	public Date getSuspendEndDate() {
		return suspendEndDate;
	}
	public void setSuspendEndDate(Date suspendEndDate) {
		this.suspendEndDate = suspendEndDate;
	}
	@Column(name="previous_expire_date")
	public Date getPreviousExpireDate() {
		return previousExpireDate;
	}
	public void setPreviousExpireDate(Date previousExpireDate) {
		this.previousExpireDate = previousExpireDate;
	}
	@Column(name="sub_policy_number")
	public String getSubPolicyNumber() {
		return subPolicyNumber;
	}
	public void setSubPolicyNumber(String subPolicyNumber) {
		this.subPolicyNumber = subPolicyNumber;
	}
	@Column(name="linked_card_number")
	public String getLinkedCardNumber() {
		return linkedCardNumber;
	}
	public void setLinkedCardNumber(String linkedCardNumber) {
		this.linkedCardNumber = linkedCardNumber;
	}
	@Column(name="policy_payment_periode")
	public Integer getPolicyPaymentPeriode() {
		return policyPaymentPeriode;
	}
	public void setPolicyPaymentPeriode(Integer policyPaymentPeriode) {
		this.policyPaymentPeriode = policyPaymentPeriode;
	}
	@Column(name="lastest_policy_payment_date")
	public Date getLatestPolicyPaymentDate() {
		return latestPolicyPaymentDate;
	}
	public void setLatestPolicyPaymentDate(Date latestPolicyPaymentDate) {
		this.latestPolicyPaymentDate = latestPolicyPaymentDate;
	}
	@Column(name="auto_renewal_status")
	public Integer getAutoRenewalStatus() {
		return autoRenewalStatus;
	}
	public void setAutoRenewalStatus(Integer autoRenewalStatus) {
		this.autoRenewalStatus = autoRenewalStatus;
	}
	@Column(name="is_warning")
	public Integer getIsWarning() {
		return isWarning;
	}
	public void setIsWarning(Integer isWarning) {
		this.isWarning = isWarning;
	}
	@Column(name="is_blacklist")
	public Integer getIsBlacklist() {
		return isBlacklist;
	}
	public void setIsBlacklist(Integer isBlacklist) {
		this.isBlacklist = isBlacklist;
	}
	@Column(name="next_expire_date")
	public Date getNextExpireDate() {
		return nextExpireDate;
	}
	public void setNextExpireDate(Date nextExpireDate) {
		this.nextExpireDate = nextExpireDate;
	}
	@Column(name="movement_number")
	public String getMovementNumber() {
		return movementNumber;
	}
	public void setMovementNumber(String movementNumber) {
		this.movementNumber = movementNumber;
	}
	@Column(name="subject_to_no_claim")
	public Integer getSubjectToNoClaim() {
		return subjectToNoClaim;
	}
	public void setSubjectToNoClaim(Integer subjectToNoClaim) {
		this.subjectToNoClaim = subjectToNoClaim;
	}
	@Column(name="pending_renew_effective_date")
	public Date getPendingRenewalEffectiveDate() {
		return pendingRenewalEffectiveDate;
	}
	public void setPendingRenewalEffectiveDate(Date pendingRenewalEffectiveDate) {
		this.pendingRenewalEffectiveDate = pendingRenewalEffectiveDate;
	}
	@Column(name="pending_renew_expire_date")
	public Date getPendingRenewalExpireDate() {
		return pendingRenewalExpireDate;
	}
	public void setPendingRenewalExpireDate(Date pendingRenewalExpireDate) {
		this.pendingRenewalExpireDate = pendingRenewalExpireDate;
	}
	@Column(name="no_claim_start_date")
	public Date getNoClaimStartDate() {
		return noClaimStartDate;
	}
	public void setNoClaimStartDate(Date noClaimStartDate) {
		this.noClaimStartDate = noClaimStartDate;
	}
	@Column(name="no_claim_end_date")
	public Date getNoClaimEndDate() {
		return noClaimEndDate;
	}
	public void setNoClaimEndDate(Date noClaimEndDate) {
		this.noClaimEndDate = noClaimEndDate;
	}
	@Column(name="payment_periode_method")
	public String getPaymentPeriodeMethod() {
		return paymentPeriodeMethod;
	}
	public void setPaymentPeriodeMethod(String paymentPeriodeMethod) {
		this.paymentPeriodeMethod = paymentPeriodeMethod;
	}
	@Column(name="policy_expire_date")
	public Date getPolicyExpireDate() {
		return policyExpireDate;
	}
	public void setPolicyExpireDate(Date policyExpireDate) {
		this.policyExpireDate = policyExpireDate;
	}
	@Column(name="next_customer_number")
	public String getNextCustomerNumber() {
		return nextCustomerNumber;
	}
	public void setNextCustomerNumber(String nextCustomerNumber) {
		this.nextCustomerNumber = nextCustomerNumber;
	}
	@Column(name="next_policy_number")
	public String getNextPolicyNumber() {
		return nextPolicyNumber;
	}
	public void setNextPolicyNumber(String nextPolicyNumber) {
		this.nextPolicyNumber = nextPolicyNumber;
	}
	@Column(name="next_card_number")
	public String getNextCardNumber() {
		return nextCardNumber;
	}
	public void setNextCardNumber(String nextCardNumber) {
		this.nextCardNumber = nextCardNumber;
	}
	@Column(name="next_effective_date")
	public Date getNextEffectiveDate() {
		return nextEffectiveDate;
	}
	public void setNextEffectiveDate(Date nextEffectiveDate) {
		this.nextEffectiveDate = nextEffectiveDate;
	}
	@Column(name="renewal_periode")
	public Integer getRenewalPeriode() {
		return renewalPeriode;
	}
	public void setRenewalPeriode(Integer renewalPeriode) {
		this.renewalPeriode = renewalPeriode;
	}
	@Column(name="employee_id_number")
	public String getEmployeeIDNumber() {
		return employeeIDNumber;
	}
	public void setEmployeeIDNumber(String employeeIDNumber) {
		this.employeeIDNumber = employeeIDNumber;
	}
	@Column(name="latest_month_membership_billed")
	public Integer getLatestMonthMembershipBilled() {
		return latestMonthMembershipBilled;
	}
	public void setLatestMonthMembershipBilled(Integer latestMonthMembershipBilled) {
		this.latestMonthMembershipBilled = latestMonthMembershipBilled;
	}
	@Column(name="latest_year_membership_billed")
	public Integer getLatestYearMembershipBilled() {
		return latestYearMembershipBilled;
	}
	public void setLatestYearMembershipBilled(Integer latestYearMembershipBilled) {
		this.latestYearMembershipBilled = latestYearMembershipBilled;
	}
	@Column(name="dd_effective")
	public Integer getDdEffective() {
		return ddEffective;
	}
	public void setDdEffective(Integer ddEffective) {
		this.ddEffective = ddEffective;
	}
	@Column(name="mm_effective")
	public Integer getMmEffective() {
		return mmEffective;
	}
	public void setMmEffective(Integer mmEffective) {
		this.mmEffective = mmEffective;
	}
	@Column(name="yyyy_effective")
	public Integer getYyyyEffective() {
		return yyyyEffective;
	}
	public void setYyyyEffective(Integer yyyyEffective) {
		this.yyyyEffective = yyyyEffective;
	}
	@Column(name="next_new_group_code")
	public String getNextNewGroupCode() {
		return nextNewGroupCode;
	}
	public void setNextNewGroupCode(String nextNewGroupCode) {
		this.nextNewGroupCode = nextNewGroupCode;
	}
	@Column(name="next_new_relationship")
	public String getNextNewRelationship() {
		return nextNewRelationship;
	}
	public void setNextNewRelationship(String nextNewRelationship) {
		this.nextNewRelationship = nextNewRelationship;
	}
	@Column(name="next_employee_number")
	public String getNextEmployeeNumber() {
		return nextEmployeeNumber;
	}
	public void setNextEmployeeNumber(String nextEmployeeNumber) {
		this.nextEmployeeNumber = nextEmployeeNumber;
	}
	
	

}	