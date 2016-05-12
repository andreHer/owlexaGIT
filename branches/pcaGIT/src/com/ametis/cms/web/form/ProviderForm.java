package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.Currency;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.Poliklinik;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.ProviderCategory;
import com.ametis.cms.datamodel.ProviderGroup;
import com.ametis.cms.datamodel.ProviderType;
import com.ametis.cms.datamodel.RefCity;
import com.ametis.cms.datamodel.RefCountry;
import com.ametis.cms.datamodel.RefProvince;
import com.ametis.cms.datamodel.RefRegion;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.datamodel.TreatmentLocation;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * Provider is a mapping of provider Table.
 */
public class ProviderForm implements java.io.Serializable
// extends+

// extends-
{

	private boolean isNewProvider = false;
	private Provider providerBean;
	private String parentProviderName;
	private String regionCode;
	private String provinceCode;
	private String groupCode;
	private String providerCode;
	
	private RefCountry countryId;
	

	/*
	 * <form>Bean merupakan representasi dari "SINGLE" table dan misalkan ada
	 * form2 yang merupakan referensi dari tabel lain bikin aja field2 bean yang
	 * mengacu ke referensi itu biar nanti automatic loading
	 * 
	 */

	public ProviderForm() {
		this.providerBean = new Provider();
		this.isNewProvider = true;
	}

	public ProviderForm(Provider object) {
		this.providerBean = object;
	}

	public boolean isNewProvider() {

		return this.isNewProvider;
	}

	public Provider getProvider() {
		return this.providerBean;
	}

	public void setProvider(Provider object) {
		this.providerBean = object;
	}

	
	public String getParentProviderName() {
		return parentProviderName;
	}

	public void setParentProviderName(String parentProviderName) {
		this.parentProviderName = parentProviderName;
	}

	public void setContactPerson(String obj) {
		providerBean.setContactPerson(new String(obj));
	}

	public String getContactPerson() {
		return StringUtil.getStringValue(providerBean.getContactPerson());
	}

	public void setDescription(String obj) {
		if (obj != null) {
			providerBean.setDescription(obj.trim());
		}
	}

	public String getDescription() {
		String result = "";
		
		if (providerBean.getDescription() != null){
			return StringUtil.getStringValue(providerBean.getDescription().trim());
		}
		else {
			return result;
		}
		
	}

	public void setProviderId(String obj) {

		providerBean.setProviderId(StringUtil.getIntegerValue(obj, 0));

	}

	public String getProviderId() {
		return StringUtil.getStringValue(providerBean.getProviderId());

	}

	public void setProviderName(String obj) {

		providerBean.setProviderName(new String(obj));

	}

	public String getProviderName() {
		return StringUtil.getStringValue(providerBean.getProviderName());

	}

	public void setBank(String obj) {

		providerBean.setBank(new String(obj));

	}

	public String getBank() {
		return StringUtil.getStringValue(providerBean.getBank());

	}

	public void setAddress(String obj) {

		if (obj != null){
			providerBean.setAddress(obj.trim());
		}

	}

	public String getAddress() {
		String result = "";
		
		if (providerBean.getAddress() != null){
			return StringUtil.getStringValue(providerBean.getAddress().trim());
		}
		else {
			return result;
		}
		

	}

	public void setBankAccount(String obj) {

		providerBean.setBankAccount(new String(obj));

	}

	public String getBankAccount() {
		return StringUtil.getStringValue(providerBean.getBankAccount());

	}

	public void setAccountName(String obj) {

		providerBean.setAccountName(new String(obj));

	}

	public String getAccountName() {
		return StringUtil.getStringValue(providerBean.getAccountName());

	}

	public void setTelephone(String obj) {

		providerBean.setTelephone(new String(obj));

	}

	public String getTelephone() {
		return StringUtil.getStringValue(providerBean.getTelephone());

	}

	public void setCity(String obj) {

		providerBean.setCity(new String(obj));

	}

	public String getCity() {
		return StringUtil.getStringValue(providerBean.getCity());

	}

	public void setRegion(String obj) {

		providerBean.setRegion(new String(obj));

	}

	public String getRegion() {
		return StringUtil.getStringValue(providerBean.getRegion());

	}
	public void setCityId(String obj) {

		if (obj != null && !obj.equals("") ){
			RefCity city = new RefCity();
			city.setId(Integer.valueOf(obj));
			
			providerBean.setCityId(city);
		}

	}

	
	public String getCityId() {

		String result = "";
		
		RefCity city = providerBean.getCityId();
		if (city != null){
			result = city.getId().toString();
		}
		return result;

	}

	public void setProvince(String obj) {

		providerBean.setProvince(new String(obj));

	}

	public String getProvince() {
		return StringUtil.getStringValue(providerBean.getProvince());

	}
	
	public void setProvinceId(String obj) {

		if (obj != null && !obj.equals("") ){
			RefProvince province = new RefProvince();
			province.setId(Integer.valueOf(obj));
			
			providerBean.setProvinceId(province);
		}

	}

	public String getProvinceId() {

		String result = "";
		
		RefProvince province = providerBean.getProvinceId();
		if (province != null){
			result = province.getId().toString();
		}
		return result;

	}

	public void setCountry(String obj) {

		providerBean.setCountry(new String(obj));

	}

	public String getCountry() {
		return StringUtil.getStringValue(providerBean.getCountry());

	}
	
	public void setCountryId(String obj) {

		if (obj != null && !obj.equals("") ){
			RefCountry country = new RefCountry();
			country.setId(Integer.valueOf(obj));
			
			providerBean.setCountryId(country);
		}

	}

	public String getCountryId() {

		String result = "";
		
		RefCountry country = providerBean.getCountryId();
		if (country != null){
			result = country.getId().toString();
		}
		return result;

	}
	public void setRegionId(String obj) {

		if (obj != null && !obj.equals("") ){
			RefRegion region = new RefRegion();
			region.setRefRegionId(Integer.valueOf(obj));
			
			providerBean.setRegionId(region);
		}

	}

	public String getRegionId() {

		String result = "";
		
		RefRegion region = providerBean.getRegionId();
		if (region != null){
			result = region.getRefRegionId().toString();
		}
		return result;

	}
	public void setPostalCode(String obj) {

		providerBean.setPostalCode(new String(obj));

	}

	public String getPostalCode() {
		return StringUtil.getStringValue(providerBean.getPostalCode());

	}

	public void setContractStartDate(String obj) {

		if (obj != null && obj.equals("")) {
			providerBean.setContractStartDate(null);
		} else {
			providerBean.setContractStartDate(java.sql.Date.valueOf(obj));
		}

	}

	public String getContractStartDate() {

		String startDate = "";

		if (providerBean.getContractStartDate() != null) {
			startDate = StringUtil.getStringValue(providerBean
					.getContractStartDate());
		}

		return startDate;
	}

	public void setContractEndDate(String obj) {
		if (obj != null && obj.equals("")) {
			providerBean.setContractEndDate(null);
		} else {
			providerBean.setContractEndDate(java.sql.Date.valueOf(obj));
		}

	}

	public String getContractEndDate() {
		String endDate = "";

		if (providerBean.getContractEndDate() != null) {
			endDate = StringUtil.getStringValue(providerBean
					.getContractEndDate());
		}

		return endDate;

	}

	public void setProviderCode(String obj) {

		providerBean.setProviderCode(new String(obj));

	}

	public String getProviderCode() {
		return StringUtil.getStringValue(providerBean.getProviderCode());

	}
	public void setFaximile(String obj) {

		providerBean.setFaximile(new String(obj));

	}

	public String getFaximile() {
		return StringUtil.getStringValue(providerBean.getFaximile());

	}

	public void setEmail(String obj) {

		providerBean.setEmail(new String(obj));

	}

	public String getEmail() {
		return StringUtil.getStringValue(providerBean.getEmail());

	}

	public void setWebsite(String obj) {

		providerBean.setWebsite(new String(obj));

	}

	public String getWebsite() {
		return StringUtil.getStringValue(providerBean.getWebsite());

	}

	public void setCreatedTime(String obj) {

		providerBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime() {
		return StringUtil.getStringValue(providerBean.getCreatedTime());

	}

	public void setCreatedBy(String obj) {

		providerBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy() {
		return StringUtil.getStringValue(providerBean.getCreatedBy());

	}

	public void setDeletedTime(String obj) {

		providerBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime() {
		return StringUtil.getStringValue(providerBean.getDeletedTime());

	}

	public void setDeletedBy(String obj) {

		providerBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy() {
		return StringUtil.getStringValue(providerBean.getDeletedBy());

	}

	public void setModifiedTime(String obj) {

		providerBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime() {
		return StringUtil.getStringValue(providerBean.getModifiedTime());

	}

	public void setModifiedBy(String obj) {

		providerBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy() {
		return StringUtil.getStringValue(providerBean.getModifiedBy());

	}

	public void setDeletedStatus(String obj) {

		providerBean.setDeletedStatus(StringUtil.getIntegerValue(obj, 0));

	}

	public String getDeletedStatus() {
		return StringUtil.getStringValue(providerBean.getDeletedStatus());

	}

	public void setBankBranch(String obj) {

		providerBean.setBankBranch(new String(obj));

	}

	public String getBankBranch() {
		return StringUtil.getStringValue(providerBean.getBankBranch());

	}

	public void setConfirmation(String obj) {

		providerBean.setConfirmation(new String(obj));

	}

	public String getConfirmation() {
		return StringUtil.getStringValue(providerBean.getConfirmation());

	}

	// foreign affairs

	public void setStatusId(String obj) {
		SubscriptionStatus fk = new SubscriptionStatus();
		fk.setStatusId(StringUtil.getIntegerValue(obj, 0));
		providerBean.setStatusId(fk);

	}

	public String getStatusId() {
		return StringUtil.getStringValue(providerBean.getStatusId()
				.getStatusId());

	}

	// ---

	public void setProviderCategoryId(String obj) {
		ProviderCategory fk = new ProviderCategory();
		fk.setProviderCategoryId(StringUtil.getIntegerValue(obj, 0));
		providerBean.setProviderCategoryId(fk);

	}

	public String getProviderCategoryId() {
		return StringUtil.getStringValue(providerBean.getProviderCategoryId()
				.getProviderCategoryId());

	}

	public void setContractNumber(String contractNumber) {
		this.providerBean.setContractNumber(contractNumber);
	}

	public void setContractRenewalType(String tipe) {
		this.providerBean.setRenewalType(tipe);
	}

	public String getContractRenewalType() {
		String result = "";

		if (providerBean != null) {
			result = providerBean.getRenewalType();
		}
		return result;
	}

	public String getContractNumber() {
		String result = "";

		if (providerBean != null) {
			result = providerBean.getContractNumber();

		}
		return result;
	}
	public void setLongitude (String longitude){
		this.providerBean.setLongitude(longitude);
	}
	public String getLongitude (){
		return this.providerBean.getLongitude();
	}
	public void setLatitude(String latitude){
		this.providerBean.setLatitude(latitude);
	}
	public String getLatitude(){
		return this.providerBean.getLatitude();
	}
	
	//penambahan status prospek , by aulia
	public String getStatusProspek(){
		String result = "";
		
		if (providerBean.getStatusProspek() != null){
			result = providerBean.getStatusProspek().toString();
		}
		return result;
	}
	public void setStatusProspek(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){			
			providerBean.setStatusProspek(Integer.valueOf(obj));
		}
	}
	
	
	public void setProviderEdcCode(String edcCode){
		this.providerBean.setEdcCode(edcCode);
		
	}
	public String getProviderEdcCode (){
		return this.providerBean.getEdcCode();
	}
	public void setPaymentPeriode(String payment){
		this.providerBean.setPaymentPeriode(payment);
		
	}
	public String getPaymentPeriode (){
		return this.providerBean.getPaymentPeriode();
	}
	public void setDiscount(String discount){
		if (discount != null && !discount.equalsIgnoreCase("")){
			this.providerBean.setDiscount(Double.valueOf(discount));
		}
		
	}
	public String getDiscount (){
		String result = "";
		
		if (providerBean.getDiscount() != null){
			result = StringUtil.getStringValue(providerBean.getDiscount());
		}
		return result;
		
	}
	public String getParentProviderId(){
		String result = "";
		
		if (providerBean.getProviderGroupId() != null){
			result = providerBean.getProviderGroupId().getProviderId().toString();
		}
		return result;
	}
	public void setParentProviderId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Provider parent = new Provider();
			parent.setProviderId(Integer.valueOf(obj));
			
			providerBean.setProviderGroupId(parent);
		}
	}
	public String getIsUsingCapitation(){
		String result = "";
		
		if (providerBean.getIsUsingCapitation() != null){
			result = providerBean.getIsUsingCapitation().toString();
		}
		return result;
	}
	public void setIsUsingCapitation(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){			
			providerBean.setIsUsingCapitation(Integer.valueOf(obj));
		}
	}
	public String getProviderCurrencyId(){
		String result = "";
		
		if (providerBean.getProviderCurrencyId() != null){
			result = providerBean.getProviderCurrencyId().getCurrencyId().toString();
		}
		return result;
	}
	public void setProviderCurrencyId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Currency currency = new Currency();
			currency.setCurrencyId(Integer.valueOf(obj));
			providerBean.setProviderCurrencyId(currency);
		}
	}
	public String getProviderSpecializationId(){
		String result = "";
		
		if (providerBean.getProviderSpecId() != null){
			result = providerBean.getProviderSpecId().getPoliklinikId().toString();
		}
		return result;
	}
	public void setProviderSpecializationId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Poliklinik poliklinik = new Poliklinik();
			poliklinik.setPoliklinikId(Integer.valueOf(obj));
			providerBean.setProviderSpecId(poliklinik);
		}
		else {
			providerBean.setProviderSpecId(null);
		}
	}
	public String getProviderTypeId(){
		String result = "";
		
		if (providerBean.getProviderTypeId() != null){
			result = providerBean.getProviderTypeId().getProviderTypeId().toString();
		}
		return result;
	}
	public void setProviderTypeId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			ProviderType providerType = new ProviderType();
			providerType.setProviderTypeId(Integer.valueOf(obj));
			providerBean.setProviderTypeId(providerType);
		}
		else {
			providerBean.setProviderTypeId(null);
		}
	}
	/*public void setEdcAlert(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			this.providerBean.setEdcAlert(Integer.valueOf(obj));
		}
		
	}
	public String getEdcAlert(){
		String result = "";
		if (providerBean.getEdcAlert() != null){
			result = StringUtil.getStringValue(providerBean.getEdcAlert());
		}
		return result;
		
	}
	*/
	public void setEdcAlert(String obj){
		this.providerBean.setEdcAlert(StringUtil.getIntegerValue(obj,0));
	}

	public String getEdcAlert(){
		return StringUtil.getStringValue(providerBean.getEdcAlert());
	}

	public Provider getProviderBean() {
		return providerBean;
	}

	public void setProviderBean(Provider providerBean) {
		this.providerBean = providerBean;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	
	//Add 20150828 by FVO for roll paper provider alert
	public void setRollPaperAmount(String obj){
		this.providerBean.setRollPaperAmount(StringUtil.getIntegerValue(obj,0));
	}

	public String getRollPaperAmount(){
		return StringUtil.getStringValue(providerBean.getRollPaperAmount());
	}
	
	public void setRollPaperLimitCustomize(String obj){
		this.providerBean.setRollPaperLimitCustomize(StringUtil.getIntegerValue(obj,0));
	}

	public String getRollPaperLimitCustomize(){
		return StringUtil.getStringValue(providerBean.getRollPaperLimitCustomize());
	}
	
	public void setRollPaperRegisterCustomize(String obj){
		this.providerBean.setRollPaperRegisterCustomize(StringUtil.getDoubleValue(obj,0));
	}

	public String getRollPaperRegisterCustomize(){
		return StringUtil.getStringValue(providerBean.getRollPaperRegisterCustomize());
	}
	
	public void setRollPaperPaymentCustomize(String obj){
		this.providerBean.setRollPaperPaymentCustomize(StringUtil.getDoubleValue(obj,0));
	}

	public String getRollPaperPaymentCustomize(){
		return StringUtil.getStringValue(providerBean.getRollPaperPaymentCustomize());
	}
	public void setGroupId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			ProviderGroup providerGroup = new ProviderGroup();
			providerGroup.setProviderGroupId(Integer.valueOf(obj));
			this.providerBean.setGroupId(providerGroup);
		}
	}

	public String getGroupId(){
		String result = "";
		
		if (providerBean.getGroupId() != null){
			result = providerBean.getGroupId().getProviderGroupId().toString();
		}
		return result;
	}	
}
