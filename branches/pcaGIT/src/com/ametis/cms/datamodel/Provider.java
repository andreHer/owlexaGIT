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
@Table(name = "provider")
public class Provider implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final int DOKTER_PPK1 =	10;
	public static final int DOKTER_SPECIALIST_PPK2 = 11;
	public static final int RUMAH_SAKIT_PPK3 = 1;

	public static final String PROVIDER_NAME = "ProviderName";
	public static final String PROVIDER_CATEGORY = "ProviderCategory";
	public static final String PROVIDER_EDC_CODE = "ProviderEDCCode";
	
	public static final String PROVIDER_PROVINCE = "Province";
	public static final String PROVIDER_COUNTRY = "Country";
	public static final String PROVIDER_CITY = "City";
	public static final String PROVIDER_ADDRESS = "Address";
	public static final String PROVIDER_ADDRESS2 = "Address2";
	
	public static final String CONTRACT_NUMBER = "ContractNumber";
	public static final String CONTRACT_START_DATE = "StartDate";
	public static final String CONTRACT_END_DATE = "EndDate";
	
	public static final String PHONE = "Telephone";
	public static final String FAX = "Fax";
	
	
	public static final String BANK = "Bank";
	public static final String BANK_ACCOUNT = "BankAccount";
	public static final String BANK_ACCOUNT_NAME = "AccountName";
	public static final String BANK_BRANCH = "BankBranch";
	
	public static final String PAYMENT_PERIODE = "PaymentPeriode";
	public static final String CONTACT_PERSON = "ContactPerson";	
	
	public static final String INPATIENT = "IP";
	public static final String OUTPATIENT = "OP";
	public static final String MATERNITY = "MAT";
	public static final String DENTAL = "DENTAL";
	public static final String OPTIC = "OPTIC";
	public static final String LAB = "LAB";
	public static final String ICU = "ICU";
	
	
	
	private Integer providerId;
	private TreatmentLocation providerLocation;	
	private String providerName;
	private String bank;
	private String address;	
	private String bankAccount;	
	private String accountName;	
	private String telephone;	
	private String city;	
	private String province;
	private String region;
	private String country;	
	private String postalCode;	
	private java.sql.Date contractStartDate;	
	private java.sql.Date contractEndDate;
	private String faximile;
	private String email;
	private String website;	
	private java.sql.Timestamp createdTime;
	private String createdBy;
	private java.sql.Timestamp deletedTime;
	private String deletedBy;
	private java.sql.Timestamp modifiedTime;
	private String modifiedBy;
	private Integer deletedStatus;
	private String bankBranch;
	private String confirmation;
	private String contactPerson;
	private String description;
	private SubscriptionStatus statusId;
	private ProviderCategory providerCategoryId;
	private String providerCode;
	private String providerCodeLong;
	private String contractNumber;
	private String renewalType;
	private String isEdcAvailable;	
	
	
	private Set<BatchClaim> batchClaims = new HashSet(0);	
	private Set<Claim> claims = new HashSet(0);	
	private Set<MemberGroupProvider> memberGroupProviders = new HashSet(0);	
	private Set<Payment> payments = new HashSet(0);	
	private Set<ProviderItem> providerItems = new HashSet(0);
	private String longitude;
	private String latitude;
	private String edcCode;
	private RefCity cityId;
	private RefCountry countryId;
	private RefProvince provinceId;
	private RefRegion regionId;
	
	private String inpatient;
	private String outpatient;
	private String dental;
	private String maternity;
	private String icu;
	private String lab;
	private String optical;
	
	private String ppk1;
	private String ppk2;
	private String ppk3;
	
	private String secondaryAddress;
	private String paymentPeriode;
	private Double discount;
	
	private Provider providerGroupId;
	private Integer isUsingCapitation;
	private Double currentCapitationFund;
	private Currency providerCurrencyId;
	private Poliklinik providerSpecId;
	
	private ProviderGroup groupId;
	private Integer statusProspek;

	//Add 20150811 by FVO, to get provider type
	private ProviderType providerTypeId;
	
	//Add 20150819 by FVO, for set EDC alert
	private Integer edcAlert;
	
	//Add 20150828 by FVO, for total roll paper
	private Integer rollPaperAmount;
	private Double rollPaperRegisterCustomize; //Default = 0
	private Double rollPaperPaymentCustomize; //Default = 0
	private Integer rollPaperLimitCustomize; //Default = 0
	private Double rollPaperActualLength;
	
	// -- exported key end

	// Fields End

	// PK GETTER SETTER
	@Id
	@Column(name = "provider_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public java.lang.Integer getProviderId() {
		return providerId;
	}

	public void setProviderId(java.lang.Integer value) {
		providerId = value;
	}

	// PK GETTER SETTER END

	@Column(name = "provider_name")
	public java.lang.String getProviderName() {
		return providerName;
	}

	public void setProviderName(java.lang.String value) {
		providerName = value;
	}

	@Column(name = "bank")
	public java.lang.String getBank() {
		return bank;
	}

	public void setBank(java.lang.String value) {
		bank = value;
	}

	@Column(name = "address")
	public java.lang.String getAddress() {
		return address;
	}

	public void setAddress(java.lang.String value) {
		address = value;
	}

	@Column(name = "bank_account")
	public java.lang.String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(java.lang.String value) {
		bankAccount = value;
	}

	@Column(name = "account_name")
	public java.lang.String getAccountName() {
		return accountName;
	}

	public void setAccountName(java.lang.String value) {
		accountName = value;
	}

	@Column(name = "telephone")
	public java.lang.String getTelephone() {
		return telephone;
	}

	public void setTelephone(java.lang.String value) {
		telephone = value;
	}

	@Column(name = "city")
	public java.lang.String getCity() {
		return city;
	}

	public void setCity(java.lang.String value) {
		city = value;
	}

	@Column(name = "province")
	public java.lang.String getProvince() {
		return province;
	}

	public void setProvince(java.lang.String value) {
		province = value;
	}

	@Column(name = "country")
	public java.lang.String getCountry() {
		return country;
	}

	public void setCountry(java.lang.String value) {
		country = value;
	}
	@Column(name = "region")
	public java.lang.String getRegion() {
		return region;
	}

	public void setRegion(java.lang.String value) {
		region = value;
	}

	@Column(name = "postal_code")
	public java.lang.String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(java.lang.String value) {
		postalCode = value;
	}

	@Column(name = "contract_start_date")
	public java.sql.Date getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(java.sql.Date value) {
		contractStartDate = value;
	}

	@Column(name = "contract_end_date")
	public java.sql.Date getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(java.sql.Date value) {
		contractEndDate = value;
	}

	@Column(name = "faximile")
	public java.lang.String getFaximile() {
		return faximile;
	}

	public void setFaximile(java.lang.String value) {
		faximile = value;
	}

	@Column(name = "email")
	public java.lang.String getEmail() {
		return email;
	}

	public void setEmail(java.lang.String value) {
		email = value;
	}

	@Column(name = "website")
	public java.lang.String getWebsite() {
		return website;
	}

	public void setWebsite(java.lang.String value) {
		website = value;
	}

	@Column(name = "created_time")
	public java.sql.Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(java.sql.Timestamp value) {
		createdTime = value;
	}

	@Column(name = "created_by")
	public java.lang.String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(java.lang.String value) {
		createdBy = value;
	}

	@Column(name = "deleted_time")
	public java.sql.Timestamp getDeletedTime() {
		return deletedTime;
	}

	public void setDeletedTime(java.sql.Timestamp value) {
		deletedTime = value;
	}

	@Column(name = "deleted_by")
	public java.lang.String getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(java.lang.String value) {
		deletedBy = value;
	}

	@Column(name = "modified_time")
	public java.sql.Timestamp getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(java.sql.Timestamp value) {
		modifiedTime = value;
	}

	@Column(name = "modified_by")
	public java.lang.String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(java.lang.String value) {
		modifiedBy = value;
	}

	@Column(name = "deleted_status")
	public java.lang.Integer getDeletedStatus() {
		return deletedStatus;
	}

	public void setDeletedStatus(java.lang.Integer value) {
		deletedStatus = value;
	}

	@Column(name = "bank_branch")
	public java.lang.String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(java.lang.String value) {
		bankBranch = value;
	}

	@Column(name = "confirmation")
	public java.lang.String getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(java.lang.String value) {
		confirmation = value;
	}

	// foreign affairs

	/** SubscriptionStatus */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "status_id")
	public SubscriptionStatus getStatusId() {
		return this.statusId;
	}

	/** SubscriptionStatus */
	public void setStatusId(SubscriptionStatus obj) {
		this.statusId = obj;
	}

	/** ProviderCategory */
	@ManyToOne
	@JoinColumn(name = "provider_category_id")
	public ProviderCategory getProviderCategoryId() {
		return this.providerCategoryId;
	}

	/** ProviderCategory */
	public void setProviderCategoryId(ProviderCategory obj) {
		this.providerCategoryId = obj;
	}

	// foreign affairs end

	// exported key

	/** BatchClaim */
	@OneToMany(mappedBy = "providerId")
	public Set<BatchClaim> getBatchClaims() {
		return this.batchClaims;
	}

	/** BatchClaim */
	public void setBatchClaims(Set<BatchClaim> obj) {
		this.batchClaims = obj;
	}

	/** Claim */
	@OneToMany(mappedBy = "providerId")
	public Set<Claim> getClaims() {
		return this.claims;
	}

	/** Claim */
	public void setClaims(Set<Claim> obj) {
		this.claims = obj;
	}

	/** MemberGroupProvider */
	@OneToMany(mappedBy = "providerId")
	public Set<MemberGroupProvider> getMemberGroupProviders() {
		return this.memberGroupProviders;
	}

	/** MemberGroupProvider */
	public void setMemberGroupProviders(Set<MemberGroupProvider> obj) {
		this.memberGroupProviders = obj;
	}

	/** Payment */
	@OneToMany(mappedBy = "providerId")
	public Set<Payment> getPayments() {
		return this.payments;
	}

	/** Payment */
	public void setPayments(Set<Payment> obj) {
		this.payments = obj;
	}

	/** ProviderItem */
	@OneToMany(mappedBy = "providerId")
	public Set<ProviderItem> getProviderItems() {
		return this.providerItems;
	}

	/** ProviderItem */
	public void setProviderItems(Set<ProviderItem> obj) {
		this.providerItems = obj;
	}

	@Column(name = "contact_person")
	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "provider_location")
	public TreatmentLocation getProviderLocation() {
		return providerLocation;
	}

	public void setProviderLocation(TreatmentLocation providerLocation) {
		this.providerLocation = providerLocation;
	}

	@Column(name = "provider_code")
	public String getProviderCode() {
		return providerCode;
	}

	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}

	@Column(name = "contract_number")
	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String toString() {
		String result = "";

		result += "provider name \t\t: " + providerName + "\n";
		if (providerCategoryId != null) {
			result += "provider category \t\t: "
					+ providerCategoryId.getProviderCategoryName() + "\n";
		}
		result += "provider code \t\t: " + providerCode + "\n";
		result += "Contract Start \t\t: " + contractStartDate + "\n";
		result += "Contract End \t\t: " + contractEndDate + "\n";
		result += "Contract Number \t\t: " + contractNumber + "\n";

		return result;
	}

	// exported key end
	@Column(name = "longitude")
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Column(name = "latitude")
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "city_id")
	public RefCity getCityId() {
		return cityId;
	}

	public void setCityId(RefCity cityId) {
		this.cityId = cityId;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "country_id")
	public RefCountry getCountryId() {
		return countryId;
	}

	public void setCountryId(RefCountry countryId) {
		this.countryId = countryId;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "province_id")
	public RefProvince getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(RefProvince provinceId) {
		this.provinceId = provinceId;
	}

	@Column(name = "renewal_type")
	public String getRenewalType() {
		return renewalType;
	}

	public void setRenewalType(String renewalType) {
		this.renewalType = renewalType;
	}

	@Column(name="provider_edc_code")
	public String getEdcCode() {
		return edcCode;
	}

	public void setEdcCode(String edcCode) {
		this.edcCode = edcCode;
	}

	@Column(name="inpatient")
	public String getInpatient() {
		return inpatient;
	}

	public void setInpatient(String inpatient) {
		this.inpatient = inpatient;
	}


	@Column(name="outpatient")
	public String getOutpatient() {
		return outpatient;
	}

	public void setOutpatient(String outpatient) {
		this.outpatient = outpatient;
	}

	@Column(name="dental")
	public String getDental() {
		return dental;
	}

	public void setDental(String dental) {
		this.dental = dental;
	}

	@Column(name="maternity")
	public String getMaternity() {
		return maternity;
	}

	public void setMaternity(String maternity) {
		this.maternity = maternity;
	}

	@Column(name="icu")
	public String getIcu() {
		return icu;
	}

	public void setIcu(String icu) {
		this.icu = icu;
	}

	@Column(name="lab")
	public String getLab() {
		return lab;
	}

	public void setLab(String lab) {
		this.lab = lab;
	}

	@Column(name="optical")
	public String getOptical() {
		return optical;
	}

	public void setOptical(String optical) {
		this.optical = optical;
	}

	@Column(name="secondary_address")
	public String getSecondaryAddress() {
		return secondaryAddress;
	}

	public void setSecondaryAddress(String secondaryAddress) {
		this.secondaryAddress = secondaryAddress;
	}
	
	@Column(name="payment_periode")
	public String getPaymentPeriode() {
		return paymentPeriode;
	}

	public void setPaymentPeriode(String paymentPeriode) {
		this.paymentPeriode = paymentPeriode;
	}

	public void setValue (String key, String value){
            
			if (key.equalsIgnoreCase(PROVIDER_CATEGORY)){
				if (value.equalsIgnoreCase("RS")){
					ProviderCategory providerCategory = new ProviderCategory();
					providerCategory.setProviderCategoryId(ProviderCategory.RUMAH_SAKIT);
					this.providerCategoryId =providerCategory;
				}
				if (value.equalsIgnoreCase("KLINIK")){
					ProviderCategory providerCategory = new ProviderCategory();
					providerCategory.setProviderCategoryId(ProviderCategory.KLINIK);
					this.providerCategoryId =providerCategory;
				}
				if (value.equalsIgnoreCase("APOTEK")){
					ProviderCategory providerCategory = new ProviderCategory();
					providerCategory.setProviderCategoryId(ProviderCategory.APOTEK);
					this.providerCategoryId =providerCategory;
				}
				if (value.equalsIgnoreCase("GP")){
					
					ProviderCategory providerCategory = new ProviderCategory();
					providerCategory.setProviderCategoryId(ProviderCategory.PERSONAL_DOCTOR);
					this.providerCategoryId =providerCategory;
				}
				if (value.equalsIgnoreCase("RB")){
					ProviderCategory providerCategory = new ProviderCategory();
					providerCategory.setProviderCategoryId(ProviderCategory.RUMAH_BERSALIN);
					this.providerCategoryId =providerCategory;
				}
				if (value.equalsIgnoreCase("LAB")){
					ProviderCategory providerCategory = new ProviderCategory();
					providerCategory.setProviderCategoryId(ProviderCategory.LAB);
					this.providerCategoryId =providerCategory;
				}				
			}
            if (key.equalsIgnoreCase(PROVIDER_NAME)){
                this.providerName = value;
            }
            
            if (key.equalsIgnoreCase(CONTRACT_START_DATE)){
                if (value != null && !value.trim().equalsIgnoreCase("")){
                    this.contractStartDate = Date.valueOf(value);
                }
            }
            if (key.equalsIgnoreCase(CONTRACT_END_DATE)){
                if (value != null && !value.trim().equalsIgnoreCase("")){
                    this.contractEndDate = Date.valueOf(value);
                }
            }
            if (key.equalsIgnoreCase(CONTRACT_NUMBER)){
                contractNumber = value;
            }
           
            if (key.equalsIgnoreCase(BANK_ACCOUNT_NAME)){
                accountName = value;
            }
            if (key.equalsIgnoreCase(BANK_ACCOUNT)){
                bankAccount = value;
            }
            if (key.equalsIgnoreCase(BANK_BRANCH)){
                this.bankBranch = value;
            }
            if (key.equalsIgnoreCase(BANK)){
                this.bank = value;
            }
            if (key.equalsIgnoreCase(PROVIDER_COUNTRY)){
                country = value;
            }
            if (key.equalsIgnoreCase(PROVIDER_PROVINCE)){
                this.province = value;
            }
            if (key.equalsIgnoreCase(PROVIDER_CITY)){
                this.city = value;
            }            
            if (key.equalsIgnoreCase(PROVIDER_ADDRESS)){
                this.address = value;
            }
            if (key.equalsIgnoreCase(PROVIDER_ADDRESS2)){
                this.secondaryAddress = value;
            }
            if (key.equalsIgnoreCase(PHONE)){
                this.telephone = value;
            }
            if (key.equalsIgnoreCase(FAX)){
                this.faximile = value;
            }
            if (key.equalsIgnoreCase(PROVIDER_EDC_CODE)){
                this.edcCode = value;
            }
            if (key.equalsIgnoreCase(INPATIENT)){
                this.inpatient = value;
            }
            if (key.equalsIgnoreCase(OUTPATIENT)){
                this.outpatient = value;
            }
            if (key.equalsIgnoreCase(MATERNITY)){
                this.maternity = value;
            }
            if (key.equalsIgnoreCase(DENTAL)){
                this.dental = value;
            }            
            if (key.equalsIgnoreCase(OPTIC)){
                this.optical = value;
            }            
            if (key.equalsIgnoreCase(LAB)){
                this.lab = value;
            }           
            if (key.equalsIgnoreCase(ICU)){
                this.icu = value;
            }    
            if (key.equalsIgnoreCase(PAYMENT_PERIODE)){
            	this.paymentPeriode = value;
            }
        }

	@Column(name="discount")
	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="provider_group_id")
	public Provider getProviderGroupId() {
		return providerGroupId;
	}

	public void setProviderGroupId(Provider providerGroupId) {
		this.providerGroupId = providerGroupId;
	}

	@Column(name="is_using_capitation")
	public Integer getIsUsingCapitation() {
		return isUsingCapitation;
	}

	public void setIsUsingCapitation(Integer isUsingCapitation) {
		this.isUsingCapitation = isUsingCapitation;
	}

	@Column(name="current_capitation_fund")
	public Double getCurrentCapitationFund() {
		return currentCapitationFund;
	}

	public void setCurrentCapitationFund(Double currentCapitationFund) {
		this.currentCapitationFund = currentCapitationFund;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="provider_currency_id")
	public Currency getProviderCurrencyId() {
		return providerCurrencyId;
	}

	public void setProviderCurrencyId(Currency providerCurrencyId) {
		this.providerCurrencyId = providerCurrencyId;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="provider_specialist_id")
	public Poliklinik getProviderSpecId() {
		return providerSpecId;
	}

	public void setProviderSpecId(Poliklinik providerSpecId) {
		this.providerSpecId = providerSpecId;
	}

	@Column(name="ppk1")
	public String getPpk1() {
		return ppk1;
	}

	public void setPpk1(String ppk1) {
		this.ppk1 = ppk1;
	}

	@Column(name="ppk2")
	public String getPpk2() {
		return ppk2;
	}

	public void setPpk2(String ppk2) {
		this.ppk2 = ppk2;
	}

	@Column(name="ppk3")
	public String getPpk3() {
		return ppk3;
	}

	public void setPpk3(String ppk3) {
		this.ppk3 = ppk3;
	}

	@Column(name="status_prospek")
	public Integer getStatusProspek() {
		return statusProspek;
	}

	public void setStatusProspek(Integer statusProspek) {
		this.statusProspek = statusProspek;
	}

	/** ProviderType */
	@ManyToOne
	@JoinColumn(name="provider_type_id")
	public ProviderType getProviderTypeId() {
		return providerTypeId;
	}

	/** ProviderType */
	public void setProviderTypeId(ProviderType providerTypeId) {
		this.providerTypeId = providerTypeId;
	}

	@Column(name="edc_alert")
	public Integer getEdcAlert() {
		return edcAlert;
	}

	public void setEdcAlert(Integer edcAlert) {
		this.edcAlert = edcAlert;
	}

	@Column(name="roll_paper_amount")
	public Integer getRollPaperAmount() {
		return rollPaperAmount;
	}

	public void setRollPaperAmount(Integer rollPaperAmount) {
		this.rollPaperAmount = rollPaperAmount;
	}

	@Column(name="roll_paper_register_customize")
	public Double getRollPaperRegisterCustomize() {
		return rollPaperRegisterCustomize;
	}

	public void setRollPaperRegisterCustomize(Double rollPaperRegisterCustomize) {
		this.rollPaperRegisterCustomize = rollPaperRegisterCustomize;
	}

	@Column(name="roll_paper_payment_customize")
	public Double getRollPaperPaymentCustomize() {
		return rollPaperPaymentCustomize;
	}

	public void setRollPaperPaymentCustomize(Double rollPaperPaymentCustomize) {
		this.rollPaperPaymentCustomize = rollPaperPaymentCustomize;
	}

	@Column(name="roll_paper_limit_customize")
	public Integer getRollPaperLimitCustomize() {
		return rollPaperLimitCustomize;
	}

	public void setRollPaperLimitCustomize(Integer rollPaperLimitCustomize) {
		this.rollPaperLimitCustomize = rollPaperLimitCustomize;
	}

	@Column(name="roll_paper_actual_length")
	public Double getRollPaperActualLength() {
		return rollPaperActualLength;
	}

	public void setRollPaperActualLength(Double rollPaperActualLength) {
		this.rollPaperActualLength = rollPaperActualLength;
	}

	@ManyToOne
	@JoinColumn(name="group_id")
	public ProviderGroup getGroupId() {
		return groupId;
	}

	public void setGroupId(ProviderGroup groupId) {
		this.groupId = groupId;
	}

	@ManyToOne
	@JoinColumn(name="region_id")
	public RefRegion getRegionId() {
		return regionId;
	}

	public void setRegionId(RefRegion regionId) {
		this.regionId = regionId;
	}

	@Column(name="provider_code_long")
	public String getProviderCodeLong() {
		return providerCodeLong;
	}

	public void setProviderCodeLong(String providerCodeLong) {
		this.providerCodeLong = providerCodeLong;
	}
	
        
}