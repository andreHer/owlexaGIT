package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.ClientCategory;
import com.ametis.cms.datamodel.Currency;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * Client is a mapping of client Table.
 */
public class ClientForm implements java.io.Serializable
// extends+

// extends-
{

	private boolean isNewClient = false;
	private Client clientBean;

	/*
	 * <form>Bean merupakan representasi dari "SINGLE" table dan misalkan ada
	 * form2 yang merupakan referensi dari tabel lain bikin aja field2 bean yang
	 * mengacu ke referensi itu biar nanti automatic loading
	 * 
	 */

	public ClientForm() {
		this.clientBean = new Client();
		this.isNewClient = true;
	}

	public ClientForm(Client object) {
		this.clientBean = object;
	}

	public boolean isNewClient() {

		return this.isNewClient;
	}

	public Client getClient() {
		return this.clientBean;
	}

	public void setClient(Client object) {
		this.clientBean = object;
	}

	public void setClientId(String obj) {

		clientBean.setClientId(StringUtil.getIntegerValue(obj, 0));

	}

	public String getClientId() {
		return StringUtil.getStringValue(clientBean.getClientId());

	}

	public void setClientName(String obj) {

		clientBean.setClientName(new String(obj));

	}

	public String getClientName() {
		return StringUtil.getStringValue(clientBean.getClientName());

	}

	public void setAddress(String obj) {

		clientBean.setAddress(new String(obj));

	}

	public String getAddress() {
		return StringUtil.getStringValue(clientBean.getAddress());

	}

	public String getClientNumber() {
		return StringUtil.getStringValue(clientBean.getClientNumber());
	}

	public void setClientNumber(String clientNumber) {
		clientBean.setClientNumber(new String(clientNumber));
	}

	public String getExpireDate() {
		String expireDate = "";

		if (clientBean.getExpireDate() != null) {
			expireDate = StringUtil
					.getStringValue(clientBean.getExpireDate());
		}

		return expireDate;
	}

	public void setExpireDate(String obj) {
		if (obj != null && obj.equals("")) {
			clientBean.setExpireDate(null);
		} else {
			clientBean.setExpireDate(java.sql.Date.valueOf(obj));
		}
		
	}

	public String getRegistrationDate() {
		String registrationDate = "";

		if (clientBean.getRegistrationDate() != null) {
			registrationDate = StringUtil
					.getStringValue(clientBean.getRegistrationDate());
		}

		return registrationDate;
	}

	public void setRegistrationDate(String obj) {
		
		if (obj != null && obj.equals("")) {
			clientBean.setRegistrationDate(null);
		} else {
			clientBean.setRegistrationDate(java.sql.Date.valueOf(obj));
		}
	}

	public String getRenewalDate() {
		String renewalDate = "";

		if (clientBean.getRenewalDate() != null) {
			renewalDate = StringUtil
					.getStringValue(clientBean.getRenewalDate());
		}

		return renewalDate;

	}

	public void setRenewalDate(String obj) {
		if (obj != null && obj.equals("")) {
			clientBean.setRenewalDate(null);
		} else {
			clientBean.setRenewalDate(java.sql.Date.valueOf(obj));
		}
	}

	public String getResignDate() {
		String resignDate = "";

		if (clientBean.getResignDate() != null) {
			resignDate = StringUtil.getStringValue(clientBean.getResignDate());
		}

		return resignDate;
	}

	public void setResignDate(String obj) {
		if (obj != null && obj.equals("")) {
			clientBean.setResignDate(null);
		} else {
			clientBean.setResignDate(java.sql.Date.valueOf(obj));
		}
	}

	public void setClientWebsite(String obj) {

		clientBean.setClientWebsite(new String(obj));

	}

	public String getClientWebsite() {
		return StringUtil.getStringValue(clientBean.getClientWebsite());

	}

	public void setClientBankAccount(String obj) {

		clientBean.setClientBankAccount(new String(obj));

	}

	public String getClientBankAccount() {
		return StringUtil.getStringValue(clientBean.getClientBankAccount());

	}

	public void setClientFundValue(String obj) {

		clientBean.setClientFundValue(StringUtil.getDoubleValue(obj, 0));

	}

	public String getClientFundValue() {
		return StringUtil.getStringValue(clientBean.getClientFundValue());

	}

	public void setTelephone(String obj) {

		clientBean.setTelephone(new String(obj));

	}

	public String getTelephone() {
		return StringUtil.getStringValue(clientBean.getTelephone());

	}

	public void setFaximile(String obj) {

		clientBean.setFaximile(new String(obj));

	}

	public String getFaximile() {
		return StringUtil.getStringValue(clientBean.getFaximile());

	}

	public void setEmail(String obj) {

		clientBean.setEmail(new String(obj));

	}

	public String getEmail() {
		return StringUtil.getStringValue(clientBean.getEmail());

	}

	public void setCity(String obj) {

		clientBean.setCity(new String(obj));

	}

	public String getCity() {
		return StringUtil.getStringValue(clientBean.getCity());

	}

	public void setProvince(String obj) {

		clientBean.setProvince(new String(obj));

	}

	public String getProvince() {
		return StringUtil.getStringValue(clientBean.getProvince());

	}

	public void setCountry(String obj) {

		clientBean.setCountry(new String(obj));

	}

	public String getCountry() {
		return StringUtil.getStringValue(clientBean.getCountry());

	}

	public void setStatusId(String obj) {

		SubscriptionStatus fk = new SubscriptionStatus();
		fk.setStatusId(StringUtil.getIntegerValue(obj, 0));

		clientBean.setStatusId(fk);

	}

	public String getStatusId() {
		return StringUtil
				.getStringValue(clientBean.getStatusId().getStatusId());

	}

	public void setMinimumFundValue(String obj) {

		clientBean.setMinimumFundValue(StringUtil.getDoubleValue(obj, 0));

	}

	public String getMinimumFundValue() {
		return StringUtil.getStringValue(clientBean.getMinimumFundValue());

	}

	public void setBankName(String obj) {

		clientBean.setBankName(new String(obj));

	}

	public String getBankName() {
		return StringUtil.getStringValue(clientBean.getBankName());

	}

	public void setBankBranch(String obj) {

		clientBean.setBankBranch(new String(obj));

	}

	public String getBankBranch() {
		return StringUtil.getStringValue(clientBean.getBankBranch());

	}

	public void setAccountName(String obj) {

		clientBean.setAccountName(new String(obj));

	}

	public String getAccountName() {
		return StringUtil.getStringValue(clientBean.getAccountName());

	}

	public void setFundCurrency(String obj) {

		if (obj != null && !obj.equalsIgnoreCase("")){
			Currency currency = new Currency();
			currency.setCurrencyId(Integer.valueOf(obj));
			clientBean.setFundCurrency(currency);
		}

	}

	public String getFundCurrency() {
		String result = "";
		
		if (clientBean.getFundCurrency() != null){
			result = clientBean.getFundCurrency().getCurrencyId().toString();
		}
		return result;

	}

	public void setPaymentCurrency(String obj) {

		if (obj != null && !obj.equalsIgnoreCase("")){
			Currency currency = new Currency();
			currency.setCurrencyId(Integer.valueOf(obj));
			clientBean.setPaymentCurrency(currency);
		}

	}

	public String getPaymentCurrency() {
		String result = "";
		
		if (clientBean.getPaymentCurrency() != null){
			result = clientBean.getPaymentCurrency().getCurrencyId().toString();
		}
		return result;

	}

	public void setClientCode(String obj) {
		clientBean.setClientCode(obj);
	}

	public String getClientCode() {
		return clientBean.getClientCode();
	}

	public void setIsUsingFloatingFund (String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			clientBean.setIsUsingFloatingFund(Integer.valueOf(obj));
		}
		else {
			clientBean.setIsUsingFloatingFund(0);
		}
	}
	public String getIsUsingFloatingFund (){
		Integer isUsing = clientBean.getIsUsingFloatingFund();
		String result = "";
		
		if (isUsing != null){
			result = isUsing + "";
		}
		return result;
	}
	public void setFloatingFundUsage (String object){
		clientBean.setFloatingFundUsageType(Integer.valueOf(object));
	}
	public String getFloatingFundUsage (){
		Integer usageType = clientBean.getFloatingFundUsageType();
		String result = "";
		
		if (usageType != null){
			result = usageType + "";
		}
		return result;
	}
	// foreign affairs

	public void setClientCategoryId(String obj) {
		ClientCategory fk = new ClientCategory();
		fk.setClientCategoryId(StringUtil.getIntegerValue(obj, 0));
		clientBean.setClientCategoryId(fk);

	}

	public String getClientCategoryId() {
		String result = "";
		
		if (clientBean.getClientCategoryId() != null){
			result =StringUtil.getStringValue(clientBean.getClientCategoryId()
					.getClientCategoryId());
		}
		return result;
		

	}
	// ---
	// -- foreign affairs end

	// class+

	// class-
}
