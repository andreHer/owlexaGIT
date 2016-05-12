
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


@Entity
@Table(name="member_group")
public class MemberGroup implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	public static final int MEMBER_GROUP_PENDING = -1;
	public static final int MEMBER_GROUP_ACTIVE = 1;
	public static final int MEMBER_GROUP_TERMINATED = 2;
	public static final int MEMBER_GROUP_RESIGNED = 3;
	
	
	
	public static final String GROUP_NAME = "GroupName";
    public static final String GROUP_CODE = "GroupCode";
    
    public static final String CLIENT_CODE = "ClientCode";
    public static final String SUSPEND_DATE = "SuspendDate";
    public static final String SUSPEND_END_DATE = "SuspendEndDate";
    public static final String SUSPEND_REASON = "SuspendReason";
    public static final String SUSPEND_POLICY_NUMBER = "PolicyNumber";
    public static final String SUSPEND_ACTION_TYPE = "ActionType";

	//Fields
		
	/**data for the column :
	
 --------- member_group.member_group_id --------- 
 schema        = null
 tableName     = member_group
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
	private Integer memberGroupId;
			
	/**data for the column :
	
 --------- member_group.status --------- 
 schema        = null
 tableName     = member_group
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
	private SubscriptionStatus status;
			
	/**data for the column :
	
 --------- member_group.faximile --------- 
 schema        = null
 tableName     = member_group
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
	private String faximile;
			
	/**data for the column :
	
 --------- member_group.website --------- 
 schema        = null
 tableName     = member_group
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
	private String website;
			
	/**data for the column :
	
 --------- member_group.telephone --------- 
 schema        = null
 tableName     = member_group
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String telephone;
			
	/**data for the column :
	
 --------- member_group.email --------- 
 schema        = null
 tableName     = member_group
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
	private String email;
			
	/**data for the column :
	
 --------- member_group.effective_date --------- 
 schema        = null
 tableName     = member_group
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private java.sql.Date effectiveDate;
			
	/**data for the column :
	
 --------- member_group.renewal_date --------- 
 schema        = null
 tableName     = member_group
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date renewalDate;
			
	/**data for the column :
	
 --------- member_group.join_date --------- 
 schema        = null
 tableName     = member_group
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date joinDate;
			
	/**data for the column :
	
 --------- member_group.resigned_date --------- 
 schema        = null
 tableName     = member_group
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date resignedDate;
			
	/**data for the column :
	
 --------- member_group.expire_date --------- 
 schema        = null
 tableName     = member_group
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date expireDate;
			
	/**data for the column :
	
 --------- member_group.group_name --------- 
 schema        = null
 tableName     = member_group
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String groupName;
			
	/**data for the column :
	
 --------- member_group.province --------- 
 schema        = null
 tableName     = member_group
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String province;
			
	/**data for the column :
	
 --------- member_group.city --------- 
 schema        = null
 tableName     = member_group
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 14
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String city;
			
	/**data for the column :
	
 --------- member_group.postal_code --------- 
 schema        = null
 tableName     = member_group
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 15
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String postalCode;
			
	/**data for the column :
	
 --------- member_group.address --------- 
 schema        = null
 tableName     = member_group
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 16
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String address;
			
	/**data for the column :
	
 --------- member_group.country --------- 
 schema        = null
 tableName     = member_group
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 17
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String country;
			
	/**data for the column :
	
 --------- member_group.group_limit --------- 
 schema        = null
 tableName     = member_group
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 18
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double groupLimit;
			
	/**data for the column :
	
 --------- member_group.actual_group_limit --------- 
 schema        = null
 tableName     = member_group
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 19
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double actualGroupLimit;
			
	/**data for the column :
	
 --------- member_group.policy_agent --------- 
 schema        = null
 tableName     = member_group
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 20
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String policyAgent;
			
	/**data for the column :
	
 --------- member_group.policy_number --------- 
 schema        = null
 tableName     = member_group
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 21
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String policyNumber;
			
	/**data for the column :
	
 --------- member_group.bank_account --------- 
 schema        = null
 tableName     = member_group
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 22
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String bankBranch;
	
	private String bankAccount;
			
	/**data for the column :
	
 --------- member_group.bank_account_name --------- 
 schema        = null
 tableName     = member_group
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
	private String bankAccountName;
			
	/**data for the column :
	
 --------- member_group.bank --------- 
 schema        = null
 tableName     = member_group
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
	private String bank;
						
	/**data for the column :
	
 --------- member_group.created_time --------- 
 schema        = null
 tableName     = member_group
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 26
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- member_group.created_by --------- 
 schema        = null
 tableName     = member_group
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 27
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- member_group.deleted_time --------- 
 schema        = null
 tableName     = member_group
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 28
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- member_group.deleted_by --------- 
 schema        = null
 tableName     = member_group
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 29
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- member_group.modified_time --------- 
 schema        = null
 tableName     = member_group
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 30
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- member_group.modified_by --------- 
 schema        = null
 tableName     = member_group
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 31
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- member_group.deleted_status --------- 
 schema        = null
 tableName     = member_group
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 32
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
		
	// foreign affairs
	
			/** Client
	data for the foreign class :
	
 --------- client.client_id --------- 
 schema        = null
 tableName     = client
 foreignCol    = client_id
 foreignTab    = member_group
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
	private Double annualGroupPremium;
	private Boolean isExGratiaAllowed;
	private String contactPerson;
	private String tipe;
	private Policy currentActivePolicyId;
	private String businessCategoryName;
	private String memberGroupReferenceNumber;
	
	
			
	// -- foreign affairs end

	// -- exported key

	
			/** BatchClaim
	data for the exported class :
	
 --------- batch_claim.member_group_id --------- 
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
 ordinal       = 15
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = member_group.member_group_id

=========================================



	 */
	private Set <BatchClaim> batchClaims = new HashSet(0);
				/** GroupBenefit
	data for the exported class :
	
 --------- group_benefit.member_group_id --------- 
 schema        = null
 tableName     = group_benefit
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
 this columns points to  = member_group.member_group_id

=========================================



	 */
	private Set <GroupBenefit> groupBenefits = new HashSet(0);
				/** GroupPackage
	data for the exported class :
	
 --------- group_package.group_id --------- 
 schema        = null
 tableName     = group_package
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
 this columns points to  = member_group.member_group_id

=========================================



	 */
	private Set <GroupPackage> groupPackages = new HashSet(0);
							/** GroupProduct
	data for the exported class :
	
 --------- group_product.group_id --------- 
 schema        = null
 tableName     = group_product
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
 this columns points to  = member_group.member_group_id

=========================================



	 */
	private Set <GroupProduct> groupProducts = new HashSet(0);
							/** Member
	data for the exported class :
	
 --------- member.member_group_id --------- 
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
 ordinal       = 20
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = member_group.member_group_id

=========================================



	 */
	private Set <Member> members = new HashSet(0);
				/** MemberGroupProvider
	data for the exported class :
	
 --------- member_group_provider.member_group_id --------- 
 schema        = null
 tableName     = member_group_provider
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
 this columns points to  = member_group.member_group_id

=========================================



	 */
	private Set <MemberGroupProvider> memberGroupProviders = new HashSet(0);
			
	private String memberGroupCode ;
	private String clientCode;
	private Broker brokerId;
	private String longitude;
	private String latitude;
	private RefCity cityId;
	private RefCountry countryId;
	private RefProvince provinceId;
	private BusinessCategory businessCategoryId;
	private Double asoGroupFundValue;
	private Double asoGroupMinimumFundValue;
	private Double asoGroupAlertPercentage;
	private Double asoGroupExcessFundValue;
	private Integer asoAlertType;
	
	private Date suspendDate;
	private Date suspendEndDate;
	private String suspendReason;
	private String unsuspendReason;
	private String actionType;
	
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="member_group_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getMemberGroupId(){
		return memberGroupId;
	}
	public void setMemberGroupId(java.lang.Integer value){
		memberGroupId = value;
	}
			// PK GETTER SETTER END

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="status")
	public SubscriptionStatus getStatus(){
		return status;
	}
	public void setStatus(SubscriptionStatus value){
		status = value;
	}
				@Column(name="faximile")
	public java.lang.String getFaximile(){
		return faximile;
	}
	public void setFaximile(java.lang.String value){
		faximile = value;
	}
				@Column(name="website")
	public java.lang.String getWebsite(){
		return website;
	}
	public void setWebsite(java.lang.String value){
		website = value;
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
				@Column(name="group_name")
	public java.lang.String getGroupName(){
		return groupName;
	}
	public void setGroupName(java.lang.String value){
		groupName = value;
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
				@Column(name="address")
	public java.lang.String getAddress(){
		return address;
	}
	public void setAddress(java.lang.String value){
		address = value;
	}
				@Column(name="country")
	public java.lang.String getCountry(){
		return country;
	}
	public void setCountry(java.lang.String value){
		country = value;
	}
				@Column(name="group_limit")
	public java.lang.Double getGroupLimit(){
		return groupLimit;
	}
	public void setGroupLimit(java.lang.Double value){
		groupLimit = value;
	}
				@Column(name="actual_group_limit")
	public java.lang.Double getActualGroupLimit(){
		return actualGroupLimit;
	}
	public void setActualGroupLimit(java.lang.Double value){
		actualGroupLimit = value;
	}
				@Column(name="policy_agent")
	public java.lang.String getPolicyAgent(){
		return policyAgent;
	}
	public void setPolicyAgent(java.lang.String value){
		policyAgent = value;
	}
				@Column(name="policy_number")
	public java.lang.String getPolicyNumber(){
		return policyNumber;
	}
	public void setPolicyNumber(java.lang.String value){
		policyNumber = value;
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
	@Column(name="bank_branch")
	public String getBankBranch() {
		return bankBranch;
	}
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
			
	// foreign affairs
	
			/** Client */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="client_id")
	public Client getClientId(){
		return this.clientId;
	}

	/** Client */
	public void setClientId(Client obj){
		this.clientId = obj;
	}
			
	// foreign affairs end

// exported key

	
			/** BatchClaim */
	@OneToMany(mappedBy="memberGroupId")
	public Set<BatchClaim> getBatchClaims(){
		return this.batchClaims;
	}

	/** BatchClaim */
	public void setBatchClaims(Set<BatchClaim> obj){
		this.batchClaims = obj;
	}
				/** GroupBenefit */
	@OneToMany(mappedBy="memberGroupId")
	public Set<GroupBenefit> getGroupBenefits(){
		return this.groupBenefits;
	}

	/** GroupBenefit */
	public void setGroupBenefits(Set<GroupBenefit> obj){
		this.groupBenefits = obj;
	}
				/** GroupPackage */
	@OneToMany(mappedBy="memberGroupId")
	public Set<GroupPackage> getGroupPackages(){
		return this.groupPackages;
	}

	/** GroupPackage */
	public void setGroupPackages(Set<GroupPackage> obj){
		this.groupPackages = obj;
	}
							/** GroupProduct */
	@OneToMany(mappedBy="memberGroupId")
	public Set<GroupProduct> getGroupProducts(){
		return this.groupProducts;
	}

	/** GroupProduct */
	public void setGroupProducts(Set<GroupProduct> obj){
		this.groupProducts = obj;
	}
							/** Member */
	@OneToMany(mappedBy="memberGroupId")
	public Set<Member> getMembers(){
		return this.members;
	}

	/** Member */
	public void setMembers(Set<Member> obj){
		this.members = obj;
	}
				/** MemberGroupProvider */
	@OneToMany(mappedBy="memberGroupId")
	public Set<MemberGroupProvider> getMemberGroupProviders(){
		return this.memberGroupProviders;
	}

	/** MemberGroupProvider */
	public void setMemberGroupProviders(Set<MemberGroupProvider> obj){
		this.memberGroupProviders = obj;
	}
	@Column(name="group_code")
	public String getMemberGroupCode() {
		return memberGroupCode;
	}
	public void setMemberGroupCode(String memberGroupCode) {
		this.memberGroupCode = memberGroupCode;
	}
	@Column(name="annual_group_premium")
	public Double getAnnualGroupPremium() {
		return annualGroupPremium;
	}
	public void setAnnualGroupPremium(Double annualGroupPremium) {
		this.annualGroupPremium = annualGroupPremium;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="broker_id")
	public Broker getBrokerId() {
		return brokerId;
	}
	public void setBrokerId(Broker brokerId) {
		this.brokerId = brokerId;
	}
	@Column(name="is_exgratia_allowed")
	public Boolean getIsExGratiaAllowed() {
		return isExGratiaAllowed;
	}
	public void setIsExGratiaAllowed(Boolean isExGratiaAllowed) {
		this.isExGratiaAllowed = isExGratiaAllowed;
	}
	@Column(name="contact_person")
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	@Column(name="tipe")
	public String getTipe() {
		return tipe;
	}
	public void setTipe(String tipe) {
		this.tipe = tipe;
	}
	@Column(name="longitude")
	public String getLongitude() {
		return longitude;
	}
	
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	@Column(name="latitude")
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="city_id")
	public RefCity getCityId() {
		return cityId;
	}
	public void setCityId(RefCity cityId) {
		this.cityId = cityId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="country_id")
	public RefCountry getCountryId() {
		return countryId;
	}
	public void setCountryId(RefCountry countryId) {
		this.countryId = countryId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="province_id")
	public RefProvince getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(RefProvince provinceId) {
		this.provinceId = provinceId;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="current_active_policy_id")
	public Policy getCurrentActivePolicyId() {
		return currentActivePolicyId;
	}
	public void setCurrentActivePolicyId(Policy currentActivePolicyId) {
		this.currentActivePolicyId = currentActivePolicyId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="business_category_id")
	public BusinessCategory getBusinessCategoryId() {
		return businessCategoryId;
	}
	public void setBusinessCategoryId(BusinessCategory businessCategoryId) {
		this.businessCategoryId = businessCategoryId;
	}
	@Column(name="business_category_name")
	public String getBusinessCategoryName() {
		return businessCategoryName;
	}
	public void setBusinessCategoryName(String businessCategoryName) {
		this.businessCategoryName = businessCategoryName;
	}
	@Column(name="member_group_reference_number")
	public String getMemberGroupReferenceNumber() {
		return memberGroupReferenceNumber;
	}
	public void setMemberGroupReferenceNumber(String memberGroupReferenceNumber) {
		this.memberGroupReferenceNumber = memberGroupReferenceNumber;
	}
	@Column(name="aso_group_fund_value")
	public Double getAsoGroupFundValue() {
		return asoGroupFundValue;
	}
	public void setAsoGroupFundValue(Double asoGroupFundValue) {
		this.asoGroupFundValue = asoGroupFundValue;
	}
	@Column(name="aso_group_minimum_fund_value")
	public Double getAsoGroupMinimumFundValue() {
		return asoGroupMinimumFundValue;
	}
	public void setAsoGroupMinimumFundValue(Double asoGroupMinimumFundValue) {
		this.asoGroupMinimumFundValue = asoGroupMinimumFundValue;
	}
	@Column(name="aso_group_alert_percentage")
	public Double getAsoGroupAlertPercentage() {
		return asoGroupAlertPercentage;
	}
	public void setAsoGroupAlertPercentage(Double asoGroupAlertPercentage) {
		this.asoGroupAlertPercentage = asoGroupAlertPercentage;
	}
	@Column(name="aso_alert_type")
	public Integer getAsoAlertType() {
		return asoAlertType;
	}
	public void setAsoAlertType(Integer asoAlertType) {
		this.asoAlertType = asoAlertType;
	}
	@Column(name="aso_group_excess_fund_value")
	public Double getAsoGroupExcessFundValue() {
		return asoGroupExcessFundValue;
	}
	public void setAsoGroupExcessFundValue(Double asoGroupExcessFundValue) {
		this.asoGroupExcessFundValue = asoGroupExcessFundValue;
	}
	
	@Column(name="suspend_date")
    public Date getSuspendDate() {
		return suspendDate;
	}
	public void setSuspendDate(Date suspendDate) {
		this.suspendDate = suspendDate;
	}
	@Column(name="suspend_reason")
	public String getSuspendReason() {
		return suspendReason;
	}
	public void setSuspendReason(String suspendReason) {
		this.suspendReason = suspendReason;
	}
	@Column(name="suspend_end_date")
	public Date getSuspendEndDate() {
		return suspendEndDate;
	}
	public void setSuspendEndDate(Date suspendEndDate) {
		this.suspendEndDate = suspendEndDate;
	}
	
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public void setValue (String key, String value){
        System.out.println("KEY = "+ key + " VALUE = " + value);
    	
        if (key.equalsIgnoreCase(GROUP_NAME)){
            this.groupName = value;
        }
        if (key.equalsIgnoreCase(GROUP_CODE)){
            this.memberGroupCode = value;
        }
        if (key.equalsIgnoreCase(SUSPEND_ACTION_TYPE)){
            this.actionType = value;
        }
        if (key.equalsIgnoreCase(SUSPEND_POLICY_NUMBER)){
            this.policyNumber = value;
        }
        if (key.equalsIgnoreCase(SUSPEND_REASON)){
            this.suspendReason = value;
        }
        if (key.equalsIgnoreCase(CLIENT_CODE)){
            this.clientCode = value;
        }
        if (key.equalsIgnoreCase(SUSPEND_DATE)){
            if (value != null && !value.trim().equalsIgnoreCase("")){
                this.suspendDate = Date.valueOf(value);
            }
        }
        if (key.equalsIgnoreCase(SUSPEND_END_DATE)){
            if (value != null && !value.trim().equalsIgnoreCase("")){
                this.suspendEndDate = Date.valueOf(value);
            }
        }

    }
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	@Column(name="unsuspend_reason")
	public String getUnsuspendReason() {
		return unsuspendReason;
	}
	public void setUnsuspendReason(String unsuspendReason) {
		this.unsuspendReason = unsuspendReason;
	}
	
	
	//exported key end



}