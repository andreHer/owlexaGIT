package com.ametis.cms.dto;

import java.io.Serializable;
import java.util.Collection;


public class ProviderDto implements Serializable{

	private String providerName;
	private String providerCode;
	private String address;
	private String city;
	private String province;
	private String country;
	private Collection<BankAccountDto> accountName;
	private String providerCategory;
	private String bank;
	private String bankAccount;
	private String bankAccountName;
	private String bankBranch;
	private String providerEDCCode;
	private String status; // provider SubscriptionStatus
	private String deletedStatus; //Add by aju on 20150827
	private String EDC;
	//Add by aju on 20150825, add some field to show
	private String telephone;
	private String faximile;
	private String longitude;
	private String latitude;
	//Add by aju on 20150827, add providerId
	private String providerId;
	
	public Collection<BankAccountDto> getAccountName() {
		return accountName;
	}

	public void setAccountName(Collection<BankAccountDto> accountName) {
		this.accountName = accountName;
	}
	
	public String getEDC() {
		return EDC;
	}

	public void setEDC(String eDC) {
		EDC = eDC;
	}

	public ProviderDto(){}
	
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public String getProviderCode() {
		return providerCode;
	}
	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProviderCategory() {
		return providerCategory;
	}
	public void setProviderCategory(String providerCategory) {
		this.providerCategory = providerCategory;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getBankAccountName() {
		return bankAccountName;
	}
	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}
	public String getBankBranch() {
		return bankBranch;
	}
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	public String getProviderEDCCode() {
		return providerEDCCode;
	}
	public void setProviderEDCCode(String providerEDCCode) {
		this.providerEDCCode = providerEDCCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFaximile() {
		return faximile;
	}

	public void setFaximile(String faximile) {
		this.faximile = faximile;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getDeletedStatus() {
		return deletedStatus;
	}

	public void setDeletedStatus(String deletedStatus) {
		this.deletedStatus = deletedStatus;
	}
	
	
	
	
	
}
