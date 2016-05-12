
package com.ametis.cms.datamodel;


import java.sql.*;
import java.sql.Date;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;

import com.ametis.cms.dto.PolicyDto;


@Entity
@Table(name="policy")
public class Policy implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	public static final int PROVIDER_CLIENT_USAGE_TYPE = 1;
	public static final int PROVIDER_GROUP_USAGE_TYPE = 2;
	public static final int PROVIDER_MEMBER_USAGE_TYPE = 3;
	public static final int PROVIDER_POLICY_USAGE_TYPE = 4;
	
	public static final int ALL_PROVIDER_USAGE = 0;
	
	public static final int INDEMNITY_POLICY = 1;
	public static final int MANAGED_CARE_POLICY = 2;
	
	public static final int FUND_LEVEL_POLICY = 1;
	public static final int FUND_LEVEL_COVERAGE = 2;

	//Fields
		
	/**data for the column :
	
 --------- policy.policy_id --------- 
 schema        = null
 tableName     = policy
 foreignCol    = 
 foreignTab    = 
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
	private Integer policyId;
			
	/**data for the column :
	
 --------- policy.policy_number --------- 
 schema        = null
 tableName     = policy
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 100
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String policyNumber;
	private String groupCode;
						
	/**data for the column :
	
 --------- policy.effective_date --------- 
 schema        = null
 tableName     = policy
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
	private java.sql.Date effectiveDate;
			
	/**data for the column :
	
 --------- policy.expire_date --------- 
 schema        = null
 tableName     = policy
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
	private java.sql.Date expireDate;
			
	/**data for the column :
	
 --------- policy.request_date --------- 
 schema        = null
 tableName     = policy
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
	private java.sql.Date requestDate;
			
	/**data for the column :
	
 --------- policy.policy_date --------- 
 schema        = null
 tableName     = policy
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
	private java.sql.Date policyDate;
			
	/**data for the column :
	
 --------- policy.created_time --------- 
 schema        = null
 tableName     = policy
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- policy.created_by --------- 
 schema        = null
 tableName     = policy
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
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
	private String createdBy;
			
	/**data for the column :
	
 --------- policy.modified_time --------- 
 schema        = null
 tableName     = policy
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
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
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- policy.modified_by --------- 
 schema        = null
 tableName     = policy
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
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
	private String modifiedBy;
			
	/**data for the column :
	
 --------- policy.deleted_status --------- 
 schema        = null
 tableName     = policy
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
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
			
	/**data for the column :
	
 --------- policy.deleted_time --------- 
 schema        = null
 tableName     = policy
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
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
			
	/**data for the column :
	
 --------- policy.deleted_by --------- 
 schema        = null
 tableName     = policy
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 14
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
								
	// foreign affairs
	
			/** Client
	data for the foreign class :
	
 --------- client.client_id --------- 
 schema        = null
 tableName     = client
 foreignCol    = client_id
 foreignTab    = policy
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
	private Client clientId;
				/** MemberGroup
	data for the foreign class :
	
 --------- member_group.member_group_id --------- 
 schema        = null
 tableName     = member_group
 foreignCol    = member_group_id
 foreignTab    = policy
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
	private MemberGroup memberGroupId;
				/** Quotation
	data for the foreign class :
	
 --------- quotation.quotation_id --------- 
 schema        = null
 tableName     = quotation
 foreignCol    = quotation_id
 foreignTab    = policy
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
	private Quotation quotationId;
	private Broker brokerId;
	private Integer newBusinessMember;
	private Double newBusinessValue;
	private Integer movementMember;
	private Double movementValue;
	private Integer terminationMember;
	private Double terminationValue;
	private SubscriptionStatus policyStatus;
	private Double totalPremiumValue;
	private Integer totalPolicyMember;
	private ProductType productType;
	private String swipeCardPrefix;
	private String customerNumberPrefix;
	private Integer usingSwipeCard;
	private Integer excessChargeExpireDay;
	private Integer reimburseExpireDay;
	private Integer reimburseMaxReceiveDay;
	private Integer excessSelfCovered;
	private Integer providerAllocationType;
	
	private CardType cardTypeId;
	private Branch branchId;
	private Integer policyType;
	private Integer isCobEnabled;
	private String policyTcNotes;
	private String policyTcFile1;
	private String policyTcFile2;
	private String policyTcFile3;
	
	private Double fundWarningPercentage;
	private Date lastFundWarningDate;
	private Integer isUsingFloatingFund;
	
	private Double policyFundUsage;
	private Double policyExcessFundUsage;
	private Double currentPolicyFund;
	private Double minimumPolicyFund;
	private Double currentExcessFund;
	private Double minimumExcessFund;
	private Double excessBlockingLimit;
	
	private Double fundBlockingLimit;
	private Double totalPolicyFund;
	private Double blockedFundPercentage;
	private Date renewalDate;
	private Date extendedDate;
	private Date previousExpireDate;
	private Double totalExcessFund;
	
	private String asoCoverageList;
	private Integer doSynchronize;
	private Date lastFundWarningNotification;
	
	private Double initialFundValue;
	private Double initialFundExcessValue;
	

	private Double currentAsoFundFinanceValue;
	private Double currentExcessFundFinanceValue;

	private TarifType tarifTypeId;

	
	
			
	// -- foreign affairs end

	// -- exported key

	
			/** PolicyMember
	data for the exported class :
	
 --------- policy_member.policy_id --------- 
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
 ordinal       = 2
 size          = 11
 type          = -5 
 isPrimaryKey  = false
 this columns points to  = policy.policy_id

=========================================



	 */
	private Set <PolicyMember> policyMembers = new HashSet(0);
				/** PolicyPayment
	data for the exported class :
	
 --------- policy_payment.policy_id --------- 
 schema        = null
 tableName     = policy_payment
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 11
 type          = -5 
 isPrimaryKey  = false
 this columns points to  = policy.policy_id

=========================================



	 */
	private Set <PolicyPayment> policyPayments = new HashSet(0);
				/** PolicyProduct
	data for the exported class :
	
 --------- policy_product.policy_id --------- 
 schema        = null
 tableName     = policy_product
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 11
 type          = -5 
 isPrimaryKey  = false
 this columns points to  = policy.policy_id

=========================================



	 */
	private Set <PolicyProduct> policyProducts = new HashSet(0);
			
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="policy_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getPolicyId(){
		return policyId;
	}
	public void setPolicyId(java.lang.Integer value){
		policyId = value;
	}
			// PK GETTER SETTER END

						@Column(name="policy_number")
	public java.lang.String getPolicyNumber(){
		return policyNumber;
	}
	public void setPolicyNumber(java.lang.String value){
		policyNumber = value;
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
				@Column(name="request_date")
	public java.sql.Date getRequestDate(){
		return requestDate;
	}
	public void setRequestDate(java.sql.Date value){
		requestDate = value;
	}
				@Column(name="policy_date")
	public java.sql.Date getPolicyDate(){
		return policyDate;
	}
	public void setPolicyDate(java.sql.Date value){
		policyDate = value;
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
				/** MemberGroup */
	@ManyToOne
	@JoinColumn(name="member_group_id")
	public MemberGroup getMemberGroupId(){
		return this.memberGroupId;
	}

	/** MemberGroup */
	public void setMemberGroupId(MemberGroup obj){
		this.memberGroupId = obj;
	}
				/** Quotation */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="quotation_id")
	public Quotation getQuotationId(){
		return this.quotationId;
	}

	/** Quotation */
	public void setQuotationId(Quotation obj){
		this.quotationId = obj;
	}
			
	// foreign affairs end

// exported key

	
			/** PolicyMember */
	@OneToMany(mappedBy="policyId")
	public Set<PolicyMember> getPolicyMembers(){
		return this.policyMembers;
	}

	/** PolicyMember */
	public void setPolicyMembers(Set<PolicyMember> obj){
		this.policyMembers = obj;
	}
				/** PolicyPayment */
	@OneToMany(mappedBy="policyId")
	public Set<PolicyPayment> getPolicyPayments(){
		return this.policyPayments;
	}

	/** PolicyPayment */
	public void setPolicyPayments(Set<PolicyPayment> obj){
		this.policyPayments = obj;
	}
				/** PolicyProduct */
	@OneToMany(mappedBy="policyId")
	public Set<PolicyProduct> getPolicyProducts(){
		return this.policyProducts;
	}

	/** PolicyProduct */
	public void setPolicyProducts(Set<PolicyProduct> obj){
		this.policyProducts = obj;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="broker_id")
	public Broker getBrokerId() {
		return brokerId;
	}
	public void setBrokerId(Broker brokerId) {
		this.brokerId = brokerId;
	}
	@Column(name="new_business_member")
	public Integer getNewBusinessMember() {
		return newBusinessMember;
	}
	public void setNewBusinessMember(Integer newBusinessMember) {
		this.newBusinessMember = newBusinessMember;
	}
	@Column(name="new_business_value")
	public Double getNewBusinessValue() {
		return newBusinessValue;
	}
	public void setNewBusinessValue(Double newBusinessValue) {
		this.newBusinessValue = newBusinessValue;
	}
	@Column(name="movement_member")
	public Integer getMovementMember() {
		return movementMember;
	}
	public void setMovementMember(Integer movementMember) {
		this.movementMember = movementMember;
	}
	@Column(name="movement_value")
	public Double getMovementValue() {
		return movementValue;
	}
	public void setMovementValue(Double movementValue) {
		this.movementValue = movementValue;
	}
	@Column(name="termination_member")
	public Integer getTerminationMember() {
		return terminationMember;
	}
	public void setTerminationMember(Integer terminationMember) {
		this.terminationMember = terminationMember;
	}
	
	@Column(name="termination_value")
	public Double getTerminationValue() {
		return terminationValue;
	}
	public void setTerminationValue(Double terminationValue) {
		this.terminationValue = terminationValue;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="policy_status")
	public SubscriptionStatus getPolicyStatus() {
		return policyStatus;
	}
	public void setPolicyStatus(SubscriptionStatus policyStatus) {
		this.policyStatus = policyStatus;
	}
	@Column(name="total_premium_value")
	public Double getTotalPremiumValue() {
		return totalPremiumValue;
	}
	public void setTotalPremiumValue(Double totalPremiumValue) {
		this.totalPremiumValue = totalPremiumValue;
	}
	@Column(name="total_policy_member")
	public Integer getTotalPolicyMember() {
		return totalPolicyMember;
	}
	public void setTotalPolicyMember(Integer totalPolicyMember) {
		this.totalPolicyMember = totalPolicyMember;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="product_type_id")
	public ProductType getProductType() {
		return productType;
	}
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	@Column(name="swipe_card_prefix")
	public String getSwipeCardPrefix() {
		return swipeCardPrefix;
	}
	public void setSwipeCardPrefix(String swipeCardPrefix) {
		this.swipeCardPrefix = swipeCardPrefix;
	}
	@Column(name="customer_number_prefix")
	public String getCustomerNumberPrefix() {
		return customerNumberPrefix;
	}
	public void setCustomerNumberPrefix(String customerNumberPrefix) {
		this.customerNumberPrefix = customerNumberPrefix;
	}
	@Column(name="using_swipe_card")
	public Integer getUsingSwipeCard() {
		return usingSwipeCard;
	}
	public void setUsingSwipeCard(Integer usingSwipeCard) {
		this.usingSwipeCard = usingSwipeCard;
	}
	@Column(name="excess_charge_expire_day")
	public Integer getExcessChargeExpireDay() {
		return excessChargeExpireDay;
	}
	public void setExcessChargeExpireDay(Integer excessChargeExpireDay) {
		this.excessChargeExpireDay = excessChargeExpireDay;
	}
	@Column(name="reimburse_expire_day")
	public Integer getReimburseExpireDay() {
		return reimburseExpireDay;
	}
	public void setReimburseExpireDay(Integer reimburseExpireDay) {
		this.reimburseExpireDay = reimburseExpireDay;
	}
	@Column(name="reimburse_max_receive_day")
	public Integer getReimburseMaxReceiveDay() {
		return reimburseMaxReceiveDay;
	}
	public void setReimburseMaxReceiveDay(Integer reimburseMaxReceiveDay) {
		this.reimburseMaxReceiveDay = reimburseMaxReceiveDay;
	}
	@Column(name="excess_self_covered")
	public Integer getExcessSelfCovered() {
		return excessSelfCovered;
	}
	public void setExcessSelfCovered(Integer excessSelfCovered) {
		this.excessSelfCovered = excessSelfCovered;
	}
	@Column(name="provider_allocation_type")
	public Integer getProviderAllocationType() {
		return providerAllocationType;
	}
	public void setProviderAllocationType(Integer providerAllocationType) {
		this.providerAllocationType = providerAllocationType;
	}
			
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="card_type_id")
	public CardType getCardTypeId() {
		return cardTypeId;
	}
	public void setCardTypeId(CardType cardTypeId) {
		this.cardTypeId = cardTypeId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branch_id")	
	public Branch getBranchId() {
		return branchId;
	}
	public void setBranchId(Branch branchId) {
		this.branchId = branchId;
	}
	public PolicyDto exportDto(){
		PolicyDto result = new PolicyDto();
		
		if (effectiveDate != null){
			result.setEffectiveDate(effectiveDate.toString());
		}
		if (expireDate != null){
			result.setExpireDate(expireDate.toString());
		}
		if (policyNumber != null){
			result.setPolicyNumber(policyNumber);
		}
		if (memberGroupId != null){
			result.setMemberGroupName(memberGroupId.getGroupName());
			result.setMemberGroupId(memberGroupId.getMemberGroupId().toString());
		}
		if (policyStatus != null){
			result.setPolicyStatus(policyStatus.toString());
		}
		if (policyType != null){
			result.setPolicyType(policyType.toString());
		}
		else {
			result.setPolicyType(Policy.INDEMNITY_POLICY+"");
		}
		if (isCobEnabled != null){
			result.setCobStatus(isCobEnabled.toString());
		}
		else {
			result.setCobStatus("0");
		}
		
		return result;
	}
	//exported key end
	@Column(name="policy_type")
	public Integer getPolicyType() {
		return policyType;
	}
	public void setPolicyType(Integer policyType) {
		this.policyType = policyType;
	}
	@Column(name="is_cob_enabled")
	public Integer getIsCobEnabled() {
		return isCobEnabled;
	}
	public void setIsCobEnabled(Integer isCobEnabled) {
		this.isCobEnabled = isCobEnabled;
	}
	@Column(name="policy_tc_notes")
	public String getPolicyTcNotes() {
		return policyTcNotes;
	}
	public void setPolicyTcNotes(String policyTcNotes) {
		this.policyTcNotes = policyTcNotes;
	}
	@Column(name="policy_tc_file1")
	public String getPolicyTcFile1() {
		return policyTcFile1;
	}
	public void setPolicyTcFile1(String policyTcFile1) {
		this.policyTcFile1 = policyTcFile1;
	}
	@Column(name="policy_tc_file2")
	public String getPolicyTcFile2() {
		return policyTcFile2;
	}
	public void setPolicyTcFile2(String policyTcFile2) {
		this.policyTcFile2 = policyTcFile2;
	}
	@Column(name="policy_tc_file3")
	public String getPolicyTcFile3() {
		return policyTcFile3;
	}
	public void setPolicyTcFile3(String policyTcFile3) {
		this.policyTcFile3 = policyTcFile3;
	}
	@Column(name="fund_warning_percentage")
	public Double getFundWarningPercentage() {
		return fundWarningPercentage;
	}
	public void setFundWarningPercentage(Double fundWarningPercentage) {
		this.fundWarningPercentage = fundWarningPercentage;
	}
	@Column(name="last_fund_warning_date")
	public Date getLastFundWarningDate() {
		return lastFundWarningDate;
	}
	public void setLastFundWarningDate(Date lastFundWarningDate) {
		this.lastFundWarningDate = lastFundWarningDate;
	}
	@Column(name="is_using_floating_fund")
	public Integer getIsUsingFloatingFund() {
		return isUsingFloatingFund;
	}
	public void setIsUsingFloatingFund(Integer isUsingFloatingFund) {
		this.isUsingFloatingFund = isUsingFloatingFund;
	}
	@Column(name="current_policy_fund")
	public Double getCurrentPolicyFund() {
		return currentPolicyFund;
	}
	public void setCurrentPolicyFund(Double currentPolicyFund) {
		this.currentPolicyFund = currentPolicyFund;
	}
	@Column(name="minimum_policy_fund")
	public Double getMinimumPolicyFund() {
		return minimumPolicyFund;
	}
	public void setMinimumPolicyFund(Double minimumPolicyFund) {
		this.minimumPolicyFund = minimumPolicyFund;
	}
	@Column(name="blocked_fund_percentage")
	public Double getBlockFundPercentage() {
		return blockedFundPercentage;
	}
	public void setBlockFundPercentage(Double fundPercentage) {
		this.blockedFundPercentage = fundPercentage;
	}
	@Column(name="current_excess_fund")
	public Double getCurrentExcessFund() {
		return currentExcessFund;
	}
	public void setCurrentExcessFund(Double currentExcessFund) {
		this.currentExcessFund = currentExcessFund;
	}
	@Column(name="renewal_date")
	public Date getRenewalDate() {
		return renewalDate;
	}
	public void setRenewalDate(Date renewalDate) {
		this.renewalDate = renewalDate;
	}
	@Column(name="extended_date")
	public Date getExtendedDate() {
		return extendedDate;
	}
	public void setExtendedDate(Date extendedDate) {
		this.extendedDate = extendedDate;
	}
	@Column(name="previous_expire_date")
	public Date getPreviousExpireDate() {
		return previousExpireDate;
	}
	public void setPreviousExpireDate(Date previousExpireDate) {
		this.previousExpireDate = previousExpireDate;
	}
	@Column(name="aso_coverage_list")
	public String getAsoCoverageList() {
		return asoCoverageList;
	}
	public void setAsoCoverageList(String asoCoverageList) {
		this.asoCoverageList = asoCoverageList;
	}
	@Column(name="total_policy_fund")
	public Double getTotalPolicyFund() {
		return totalPolicyFund;
	}
	public void setTotalPolicyFund(Double totalPolicyFund) {
		this.totalPolicyFund = totalPolicyFund;
	}
	@Column(name="total_excess_fund")
	public Double getTotalExcessFund() {
		return totalExcessFund;
	}
	public void setTotalExcessFund(Double totalExcessFund) {
		this.totalExcessFund = totalExcessFund;
	}
	@Column(name="group_code")
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	@Column(name="policy_fund_usage")
	public Double getPolicyFundUsage() {
		return policyFundUsage;
	}
	public void setPolicyFundUsage(Double policyFundUsage) {
		this.policyFundUsage = policyFundUsage;
	}
	@Column(name="policy_excess_fund_usage")
	public Double getPolicyExcessFundUsage() {
		return policyExcessFundUsage;
	}
	public void setPolicyExcessFundUsage(Double policyExcessFundUsage) {
		this.policyExcessFundUsage = policyExcessFundUsage;
	}
	@Column(name="fund_blocking_limit")
	public Double getFundBlockingLimit() {
		return fundBlockingLimit;
	}
	public void setFundBlockingLimit(Double fundBlockingLimit) {
		this.fundBlockingLimit = fundBlockingLimit;
	}
	@Column(name="do_synchronize")
	public Integer getDoSynchronize() {
		return doSynchronize;
	}
	public void setDoSynchronize(Integer doSynchronize) {
		this.doSynchronize = doSynchronize;
	}
	@Column(name="last_fund_warning_notification")
	public Date getLastFundWarningNotification() {
		return lastFundWarningNotification;
	}
	public void setLastFundWarningNotification(Date lastFundWarningNotification) {
		this.lastFundWarningNotification = lastFundWarningNotification;
	}
	@Column(name="initial_fund_value")
	public Double getInitialFundValue() {
		return initialFundValue;
	}
	public void setInitialFundValue(Double initialFundValue) {
		this.initialFundValue = initialFundValue;
	}
	@Column(name="initial_fund_excess_value")
	public Double getInitialFundExcessValue() {
		return initialFundExcessValue;
	}
	public void setInitialFundExcessValue(Double initialFundExcessValue) {
		this.initialFundExcessValue = initialFundExcessValue;
	}
	@Column(name="minimum_excess_fund")
	public Double getMinimumExcessFund() {
		return minimumExcessFund;
	}
	public void setMinimumExcessFund(Double minimumExcessFund) {
		this.minimumExcessFund = minimumExcessFund;
	}
	
	@Column(name="excess_blocking_limit")
	public Double getExcessBlockingLimit() {
		return excessBlockingLimit;
	}
	public void setExcessBlockingLimit(Double excessBlockingLimit) {
		this.excessBlockingLimit = excessBlockingLimit;
	}
	@Column(name="current_aso_fund_finance_value")
	public Double getCurrentAsoFundFinanceValue() {
		return currentAsoFundFinanceValue;
	}
	public void setCurrentAsoFundFinanceValue(Double currentAsoFundFinanceValue) {
		this.currentAsoFundFinanceValue = currentAsoFundFinanceValue;
	}
	

	@Column(name="current_excess_fund_finance_value")
	public Double getCurrentExcessFundFinanceValue() {
		return currentExcessFundFinanceValue;
	}
	public void setCurrentExcessFundFinanceValue(
			Double currentExcessFundFinanceValue) {
		this.currentExcessFundFinanceValue = currentExcessFundFinanceValue;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tarif_type_id")	
	public TarifType getTarifTypeId() {
		return tarifTypeId;
	}
	public void setTarifTypeId(TarifType tarifTypeId) {
		this.tarifTypeId = tarifTypeId;
	}

	
	
	

}