
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
@Table(name="client")
public class Client implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	public static final int ACTIVE = 1;
	

	//Fields
		
	/**data for the column :
	
 --------- client.client_id --------- 
 schema        = null
 tableName     = client
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
	private Integer clientId;
	private Integer isDefault;
			
	/**data for the column :
	
 --------- client.client_name --------- 
 schema        = null
 tableName     = client
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
	private String clientName;
			
	/**data for the column :
	
 --------- client.address --------- 
 schema        = null
 tableName     = client
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String address;
			
	/**data for the column :
	
 --------- client.client_website --------- 
 schema        = null
 tableName     = client
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
	private String clientWebsite;
			
	/**data for the column :
	
 --------- client.client_bank_account --------- 
 schema        = null
 tableName     = client
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
	private String clientBankAccount;
			
	/**data for the column :
	
 --------- client.client_fund_value --------- 
 schema        = null
 tableName     = client
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private String clientCode;
	
	private Double clientFundValue;
			
	/**data for the column :
	
 --------- client.telephone --------- 
 schema        = null
 tableName     = client
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
	private String telephone;
			
	/**data for the column :
	
 --------- client.faximile --------- 
 schema        = null
 tableName     = client
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
	private String faximile;
			
	/**data for the column :
	
 --------- client.email --------- 
 schema        = null
 tableName     = client
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
	private String email;
			
	/**data for the column :
	
 --------- client.city --------- 
 schema        = null
 tableName     = client
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
	private String city;
			
	/**data for the column :
	
 --------- client.province --------- 
 schema        = null
 tableName     = client
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
	private String province;
			
	/**data for the column :
	
 --------- client.country --------- 
 schema        = null
 tableName     = client
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
	private String country;
			
	/**data for the column :
	
 --------- client.status_id --------- 
 schema        = null
 tableName     = client
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private SubscriptionStatus statusId;
						
	/**data for the column :
	
 --------- client.created_time --------- 
 schema        = null
 tableName     = client
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- client.created_by --------- 
 schema        = null
 tableName     = client
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private String createdBy;
			
	/**data for the column :
	
 --------- client.deleted_time --------- 
 schema        = null
 tableName     = client
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	
 --------- client.deleted_by --------- 
 schema        = null
 tableName     = client
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	
 --------- client.modified_time --------- 
 schema        = null
 tableName     = client
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 19
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- client.modified_by --------- 
 schema        = null
 tableName     = client
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 20
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- client.deleted_status --------- 
 schema        = null
 tableName     = client
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 21
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- client.minimum_fund_value --------- 
 schema        = null
 tableName     = client
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
	private Double minimumFundValue;
			
	/**data for the column :
	
 --------- client.bank_name --------- 
 schema        = null
 tableName     = client
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
	private String bankName;
			
	/**data for the column :
	
 --------- client.bank_branch --------- 
 schema        = null
 tableName     = client
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
	private String bankBranch;
			
	/**data for the column :
	
 --------- client.account_name --------- 
 schema        = null
 tableName     = client
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
	private String accountName;
			
	/**data for the column :
	
 --------- client.fund_currency --------- 
 schema        = null
 tableName     = client
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 26
 size          = 5
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Currency fundCurrency;
			
	/**data for the column :
	
 --------- client.payment_currency --------- 
 schema        = null
 tableName     = client
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 27
 size          = 5
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Currency paymentCurrency;
		
	// foreign affairs
	
			/** ClientCategory
	data for the foreign class :
	
 --------- client_category.client_category_id --------- 
 schema        = null
 tableName     = client_category
 foreignCol    = client_category_id
 foreignTab    = client
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
	private ClientCategory clientCategoryId;
	private java.sql.Date registrationDate;
	private java.sql.Date renewalDate;
	private java.sql.Date expireDate;
	private Date resignDate;
	private String clientNumber;
	private Integer isUsingFloatingFund;
	private String longitude;
	private String latitude;
	private RefCountry countryId;
	private RefProvince provinceId;
	private RefCity cityId;
	private String clientLogo;
	private Integer currentCardStock;
	private Integer totalCardUsage;

	private Integer floatingFundUsageType;
	private Double excessPaymentFloatingFund;
	private Double excessPaymentFundUsage;
	private Date lastFundReminder;
	private Double fundWarningPercentage;
	// -- foreign affairs end

	// -- exported key

	
			/** BatchClaim
	data for the exported class :
	
 --------- batch_claim.client_id --------- 
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
 ordinal       = 17
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = client.client_id

=========================================



	 */
	private Set <BatchClaim> batchClaims = new HashSet(0);
				/** Fund
	data for the exported class :
	
 --------- fund.client_id --------- 
 schema        = null
 tableName     = fund
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
 this columns points to  = client.client_id

=========================================



	 */
	private Set <Fund> funds = new HashSet(0);
				/** InsurancePackage
	data for the exported class :
	
 --------- insurance_package.client_id --------- 
 schema        = null
 tableName     = insurance_package
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = client.client_id

=========================================



	 */
	private Set <InsurancePackage> insurancePackages = new HashSet(0);
				/** Member
	data for the exported class :
	
 --------- member.client_id --------- 
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
 ordinal       = 34
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = client.client_id

=========================================



	 */
	private Set <Member> members = new HashSet(0);
				/** MemberGroup
	data for the exported class :
	
 --------- member_group.client_id --------- 
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
 ordinal       = 25
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = client.client_id

=========================================



	 */
	private Set <MemberGroup> memberGroups = new HashSet(0);
				/** Outstanding
	data for the exported class :
	
 --------- outstanding.client_id --------- 
 schema        = null
 tableName     = outstanding
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
 this columns points to  = client.client_id

=========================================



	 */
	private Set <Outstanding> outstandings = new HashSet(0);
				/** Product
	data for the exported class :
	
 --------- product.client_id --------- 
 schema        = null
 tableName     = product
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
 this columns points to  = client.client_id

=========================================



	 */
	private Set <Product> products = new HashSet(0);
			
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="client_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getClientId(){
		return clientId;
	}
	public void setClientId(java.lang.Integer value){
		clientId = value;
	}
			// PK GETTER SETTER END

						@Column(name="client_name")
	public java.lang.String getClientName(){
		return clientName;
	}
	public void setClientName(java.lang.String value){
		clientName = value;
	}
				@Column(name="address")
	public java.lang.String getAddress(){
		return address;
	}
	public void setAddress(java.lang.String value){
		address = value;
	}
				@Column(name="client_website")
	public java.lang.String getClientWebsite(){
		return clientWebsite;
	}
	public void setClientWebsite(java.lang.String value){
		clientWebsite = value;
	}
				@Column(name="client_bank_account")
	public java.lang.String getClientBankAccount(){
		return clientBankAccount;
	}
	public void setClientBankAccount(java.lang.String value){
		clientBankAccount = value;
	}
				@Column(name="client_fund_value")
	public java.lang.Double getClientFundValue(){
		return clientFundValue;
	}
	public void setClientFundValue(java.lang.Double value){
		clientFundValue = value;
	}
				@Column(name="telephone")
	public java.lang.String getTelephone(){
		return telephone;
	}
	public void setTelephone(java.lang.String value){
		telephone = value;
	}
				@Column(name="faximile")
	public java.lang.String getFaximile(){
		return faximile;
	}
	public void setFaximile(java.lang.String value){
		faximile = value;
	}
				@Column(name="email")
	public java.lang.String getEmail(){
		return email;
	}
	public void setEmail(java.lang.String value){
		email = value;
	}
				@Column(name="city")
	public java.lang.String getCity(){
		return city;
	}
	public void setCity(java.lang.String value){
		city = value;
	}
				@Column(name="province")
	public java.lang.String getProvince(){
		return province;
	}
	public void setProvince(java.lang.String value){
		province = value;
	}
				@Column(name="country")
	public java.lang.String getCountry(){
		return country;
	}
	public void setCountry(java.lang.String value){
		country = value;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="status_id")
	public SubscriptionStatus getStatusId(){
		return statusId;
	}
	public void setStatusId(SubscriptionStatus value){
		statusId = value;
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
				@Column(name="minimum_fund_value")
	public java.lang.Double getMinimumFundValue(){
		return minimumFundValue;
	}
	public void setMinimumFundValue(java.lang.Double value){
		minimumFundValue = value;
	}
				@Column(name="bank_name")
	public java.lang.String getBankName(){
		return bankName;
	}
	public void setBankName(java.lang.String value){
		bankName = value;
	}
				@Column(name="bank_branch")
	public java.lang.String getBankBranch(){
		return bankBranch;
	}
	public void setBankBranch(java.lang.String value){
		bankBranch = value;
	}
				@Column(name="account_name")
	public java.lang.String getAccountName(){
		return accountName;
	}
	public void setAccountName(java.lang.String value){
		accountName = value;
	}
	@ManyToOne(fetch=FetchType.LAZY)			
	@JoinColumn(name="fund_currency")
	public Currency getFundCurrency(){
		return fundCurrency;
	}
	public void setFundCurrency(Currency value){
		fundCurrency = value;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="payment_currency")
	public Currency getPaymentCurrency(){
		return paymentCurrency;
	}
	public void setPaymentCurrency(Currency value){
		paymentCurrency = value;
	}
		
	// foreign affairs
	
			/** ClientCategory */
	@ManyToOne
	@JoinColumn(name="client_category_id")
	public ClientCategory getClientCategoryId(){
		return this.clientCategoryId;
	}

	/** ClientCategory */
	public void setClientCategoryId(ClientCategory obj){
		this.clientCategoryId = obj;
	}
			
	// foreign affairs end

// exported key

	@Column(name="client_number")
	public String getClientNumber() {
		return clientNumber;
	}
	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}
	@Column(name="expire_date")
	public java.sql.Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(java.sql.Date expireDate) {
		this.expireDate = expireDate;
	}
	@Column(name="registration_date")
	public java.sql.Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(java.sql.Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	@Column(name="renewal_date")
	public java.sql.Date getRenewalDate() {
		return renewalDate;
	}
	public void setRenewalDate(java.sql.Date renewalDate) {
		this.renewalDate = renewalDate;
	}
	@Column(name="resigned_date")
	public Date getResignDate() {
		return resignDate;
	}
	public void setResignDate(Date resignDate) {
		this.resignDate = resignDate;
	}
			/** BatchClaim */
	@OneToMany(mappedBy="clientId")
	public Set<BatchClaim> getBatchClaims(){
		return this.batchClaims;
	}

	/** BatchClaim */
	public void setBatchClaims(Set<BatchClaim> obj){
		this.batchClaims = obj;
	}
				/** Fund */
	@OneToMany(mappedBy="clientId")
	public Set<Fund> getFunds(){
		return this.funds;
	}

	/** Fund */
	public void setFunds(Set<Fund> obj){
		this.funds = obj;
	}
				/** InsurancePackage */
	@OneToMany(mappedBy="clientId")
	public Set<InsurancePackage> getInsurancePackages(){
		return this.insurancePackages;
	}

	/** InsurancePackage */
	public void setInsurancePackages(Set<InsurancePackage> obj){
		this.insurancePackages = obj;
	}
				/** Member */
	@OneToMany(mappedBy="clientId")
	public Set<Member> getMembers(){
		return this.members;
	}

	/** Member */
	public void setMembers(Set<Member> obj){
		this.members = obj;
	}
				/** MemberGroup */
	@OneToMany(mappedBy="clientId")
	public Set<MemberGroup> getMemberGroups(){
		return this.memberGroups;
	}

	/** MemberGroup */
	public void setMemberGroups(Set<MemberGroup> obj){
		this.memberGroups = obj;
	}
				/** Outstanding */
	@OneToMany(mappedBy="clientId")
	public Set<Outstanding> getOutstandings(){
		return this.outstandings;
	}

	/** Outstanding */
	public void setOutstandings(Set<Outstanding> obj){
		this.outstandings = obj;
	}
				/** Product */
	@OneToMany(mappedBy="clientId")
	public Set<Product> getProducts(){
		return this.products;
	}

	/** Product */
	public void setProducts(Set<Product> obj){
		this.products = obj;
	}
	@Column(name="client_code")
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	@Column(name="is_default")
	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	
	@Column(name="is_using_floating_fund")
	public Integer getIsUsingFloatingFund() {
		return isUsingFloatingFund;
	}
	public void setIsUsingFloatingFund(Integer isUsingFloatingFund) {
		this.isUsingFloatingFund = isUsingFloatingFund;
	}
	
	@Column (name="floating_fund_usage_type")
	public Integer getFloatingFundUsageType() {
		return floatingFundUsageType;
	}
	public void setFloatingFundUsageType(Integer floatingFundUsageType) {
		this.floatingFundUsageType = floatingFundUsageType;
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
	@JoinColumn(name="city_id")
	public RefCity getCityId() {
		return cityId;
	}
	public void setCityId(RefCity cityId) {
		this.cityId = cityId;
	}
	@Column(name="client_logo")
	public String getClientLogo() {
		return clientLogo;
	}
	public void setClientLogo(String clientLogo) {
		this.clientLogo = clientLogo;
	}
	@Column(name="current_card_stock")
	public Integer getCurrentCardStock() {
		return currentCardStock;
	}
	public void setCurrentCardStock(Integer currentCardStock) {
		this.currentCardStock = currentCardStock;
	}
	@Column(name="total_card_usage")
	public Integer getTotalCardUsage() {
		return totalCardUsage;
	}
	public void setTotalCardUsage(Integer totalCardUsage) {
		this.totalCardUsage = totalCardUsage;
	}
	@Column(name="excess_payment_floating_fund")
	public Double getExcessPaymentFloatingFund() {
		return excessPaymentFloatingFund;
	}
	public void setExcessPaymentFloatingFund(Double excessPaymentFloatingFund) {
		this.excessPaymentFloatingFund = excessPaymentFloatingFund;
	}
	@Column(name="excess_payment_fund_usage")
	public Double getExcessPaymentFundUsage() {
		return excessPaymentFundUsage;
	}
	public void setExcessPaymentFundUsage(Double excessPaymentFundUsage) {
		this.excessPaymentFundUsage = excessPaymentFundUsage;
	}
	@Column(name="last_fund_reminder")
	public Date getLastFundReminder() {
		return lastFundReminder;
	}
	public void setLastFundReminder(Date lastFundReminder) {
		this.lastFundReminder = lastFundReminder;
	}
	@Column(name="fund_warning_percentage")
	public Double getFundWarningPercentage() {
		return fundWarningPercentage;
	}
	public void setFundWarningPercentage(Double fundWarningPercentage) {
		this.fundWarningPercentage = fundWarningPercentage;
	}
	
	
			
	
	//exported key end



}