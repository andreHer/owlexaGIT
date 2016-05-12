
package com.ametis.cms.web.form;

import java.math.BigDecimal;

import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.Currency;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.Product;
import com.ametis.cms.datamodel.ProductLimitType;
import com.ametis.cms.datamodel.ProductType;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.datamodel.TreatmentClass;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * Product is a mapping of product Table.
*/
public class ProductForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewProduct = false;
	private Product productBean ;
	private String annualLimitUnlimited = "";
	private String maxClaimValueUnlimited = "";
	private String disabilityLengthUndefined = "";
	private String waitingPeriodeUndefined = "";
	private String clientName = "";
	private String productReference;
	private String policyNumber;
	private String sharedProductName;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ProductForm()
    {
    	this.productBean = new Product();
    	this.isNewProduct = true;
    }
    public ProductForm (Product object){
		this.productBean = object;
    }
    public void setIsNewProduct (){
    	this.isNewProduct = true;
    }
    public boolean isNewProduct (){

    	return this.isNewProduct;
    }
	public Product getProduct (){
		return this.productBean ;
	}
	public void setProduct (Product object){
		this.productBean = object;
	}
	
	
	
			
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getAnnualLimitUnlimited() {
		return annualLimitUnlimited;
	}
	public void setAnnualLimitUnlimited(String annualLimitUnlimited) {
		this.annualLimitUnlimited = annualLimitUnlimited;
	}
	public String getMaxClaimValueUnlimited() {
		return maxClaimValueUnlimited;
	}
	public void setMaxClaimValueUnlimited(String maxClaimValueUnlimited) {
		this.maxClaimValueUnlimited = maxClaimValueUnlimited;
	}
	public void setProductId(String obj){

		productBean.setProductId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProductId(){
		return StringUtil.getStringValue(
		productBean.getProductId());

	}
	
					public void setProductCode(String obj){

		productBean.setProductCode(new String(obj));

	}

	public String getProductCode(){
		return StringUtil.getStringValue(
		productBean.getProductCode());

	}
	
					public void setProductName(String obj){

		productBean.setProductName(new String(obj));

	}

	public String getProductName(){
		return StringUtil.getStringValue(
		productBean.getProductName());

	}
	
					public void setProductDescription(String obj){

		productBean.setProductDescription(new String(obj));

	}

	public String getProductDescription(){
		return StringUtil.getStringValue(
		productBean.getProductDescription());

	}
	
				
	public void setProductStatus(String obj){

		if (obj != null){
			SubscriptionStatus status = new SubscriptionStatus();
			status.setStatusId(StringUtil.getIntegerValue(obj, 0));
			productBean.setProductStatus(status);
		}
	}

	public String getProductStatus(){
		
		if (productBean.getProductStatus() != null){
			return StringUtil.getStringValue(
					productBean.getProductStatus().getStatusId());	
		}
		else {
			return "";
		}
		

	}
	
				
	public void setMaxClaimValue(String obj){

		productBean.setMaxClaimValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getMaxClaimValue(){
		String result = "";
		
		if (productBean.getMaxClaimValue() != null){
			BigDecimal bc = new BigDecimal(productBean.getMaxClaimValue().doubleValue());
			result = bc.toPlainString();
		}
		return result;

	}
	public void setSurgeryPerDisabilityLimit(String obj){

		productBean.setSurgeryPerDisabilityLimit(StringUtil.getDoubleValue(obj,0));

	}

	public String getSurgeryPerDisabilityLimit(){
		String result = "";
		
		if (productBean.getSurgeryPerDisabilityLimit() != null){
			BigDecimal bc = new BigDecimal(productBean.getSurgeryPerDisabilityLimit().doubleValue());
			result = bc.toPlainString();
		}
		return result;

	}
	
				
	public void setAnnualLimitValue(String obj){

		productBean.setAnnualLimitValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getAnnualLimitValue(){
		String result = "";
		
		if (productBean.getAnnualLimitValue() != null){
			BigDecimal bc = new BigDecimal(productBean.getAnnualLimitValue().doubleValue());
			result = bc.toPlainString();
		}
		return result;

	}
	
													
	public void setCreatedTime(String obj){

		productBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		productBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		productBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		productBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		productBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		productBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		productBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		productBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		productBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		productBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		productBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		productBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		productBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		productBean.getDeletedStatus());

	}
	
		

	// foreign affairs
	
	

	
	public void setClientId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Client fk = new Client();
			fk.setClientId(StringUtil.getIntegerValue(obj,0));
			productBean.setClientId(fk);
		}

	}

	public String getClientId(){
		String result = "";
		
		if (productBean.getClientId() != null){
			result = productBean.getClientId().getClientId().toString();
		}
		return result;

	}
	//---
	
	

	public void setCaseCategory (String obj){
		
		if (obj != null && !obj.equalsIgnoreCase("")){
			CaseCategory cc = new CaseCategory();
			cc.setCaseCategoryId(StringUtil.getIntegerValue(obj, 0));
			productBean.setCaseCategory(cc);
		}
	}
	public String getCaseCategory (){
		
		String result = "";
		
		if (productBean.getCaseCategory() != null){
			result = StringUtil.getStringValue(productBean.getCaseCategory().getCaseCategoryId(),"");
		}
		
		return result;
	}
	
	public void setProductLimitType(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){

			ProductLimitType fk = new ProductLimitType();
			fk.setProductLimitTypeId(StringUtil.getIntegerValue(obj,0));
			productBean.setProductLimitType(fk);
		
		}

	}

	public String getProductLimitType(){
		return StringUtil.getStringValue(
		productBean.getProductLimitType().getProductLimitTypeId());

	}
	//---
	
	
	
	
	public void setProductType(String obj){
		ProductType fk = new ProductType();
		fk.setProductTypeId(StringUtil.getIntegerValue(obj,0));
		productBean.setProductType(fk);

	}

	public String getProductType(){
		return StringUtil.getStringValue(
		productBean.getProductType().getProductTypeId());

	}
	//---
		// -- foreign affairs end
	public String getDisabilityLengthUndefined() {
		String result = "";
		
		if (productBean.getDisabilityLength() != null){
			result = productBean.getDisabilityLength().toString();
		}
		return result;
	}
	public void setDisabilityLengthUndefined(String disabilityLengthUndefined) {
		if (disabilityLengthUndefined != null && !disabilityLengthUndefined.equalsIgnoreCase("")){
			this.productBean.setDisabilityLength(Integer.valueOf(disabilityLengthUndefined));
		}
	
	}
	public String getWaitingPeriodeUndefined() {
		String result = "";
		
		if (productBean.getWaitingPeriode() != null){
			result = productBean.getWaitingPeriode().toString();
		}
		return result;
	}
	public void setWaitingPeriodeUndefined(String object) {
		if (object != null && !object.equalsIgnoreCase("")){
			this.productBean.setWaitingPeriode(Integer.valueOf(object));
		}
		
	}
	public void setProductClass (String obj){
		productBean.setServiceClass(obj);
	}
	public String getProductClass (){
		return productBean.getServiceClass();
	}
	public String getProductReference() {
		return productReference;
	}
	public void setProductReference(String productReference) {
		this.productReference = productReference;
	}
	
	public void setReimbursementPercentage (String obj){
		
		if (obj != null && !obj.equalsIgnoreCase("")){
			this.productBean.setReimbursementPercentage(Double.valueOf(obj));
		}
	}
	public String getReimbursementPercentage(){
		String result = "";
		
		if (this.productBean.getReimbursementPercentage() != null){
			result = this.productBean.getReimbursementPercentage()+"";
		}
		return result;
	}
	public void setCashlessPercentage (String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			this.productBean.setCashlessPercentage(Double.valueOf(obj));
		}
	}
	public String getCashlessPercentage(){		
		
		String result = "";
		
		if (this.productBean.getCashlessPercentage() != null){
			result = this.productBean.getCashlessPercentage()+"";
		}
		return result;
		
	}
	public void setDomesticPercentage(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			this.productBean.setDomesticPercentage(Double.valueOf(obj));
		}
	}
	public String getDomesticPercentage(){
		String result = "";
		
		if (this.productBean.getDomesticPercentage() != null){
			result = this.productBean.getDomesticPercentage()+"";
		}
		return result;
		
		
	}
	public void setOverseasPercentage(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			this.productBean.setOverseasPercentage(Double.valueOf(obj));
		}
	}
	public String getOverseasPercentage(){
		
		String result = "";
		
		if (this.productBean.getOverseasPercentage() != null){
			result = this.productBean.getOverseasPercentage()+"";
		}
		return result;	
		
	}
	public void setReimbursementAnnualLimit (String obj){
		if (obj != null && !obj.equals("")){
			this.productBean.setReimbursementAnnualLimit(Double.valueOf(obj));
		}
	}
	public String getReimbursementAnnualLimit (){
		String result = "";
		
		if (productBean.getReimbursementAnnualLimit() != null){
			BigDecimal bc = new BigDecimal(productBean.getReimbursementAnnualLimit().doubleValue());
			result = bc.toPlainString();
		}
		return result;
	}
	public void setIsEdcEnabled (String object){
		
		if (object != null && !object.equals("")){
			//Modify by aju oon 20150217, to remove the card type hardcode
//			if (object.equals("1")){
//				productBean.setEdcEnabled(Integer.valueOf(1));
//			}
//			else {
//				productBean.setEdcEnabled(Integer.valueOf(0));
//			}
			
			productBean.setEdcEnabled(Integer.valueOf(object)-1);
		}
	}
	public String getIsEdcEnabled(){
		//modify by aju on 20150217, to remove the card type hardcode
		//String result = "0";
		String result = "0";
		
		if (productBean.getEdcEnabled() !=null){
			//Modify by aju on 20150217, get the card type from table, no hardcode anymore, base index are 0 on page
			result = Integer.toString(Integer.parseInt( productBean.getEdcEnabled().toString()) + 1);
		}
		return result;
	}
	public void setBenefitShowOnEdc (String object){
		if (object != null && !object.equals("")){
			if (object.equals("1")){
				productBean.setLimitBenefitShowedOnEdc(Integer.valueOf(1));
			}
			else {
				productBean.setLimitBenefitShowedOnEdc(Integer.valueOf(0));
			}
		}
	}
	public String getBenefitShowOnEdc(){
		String result = "0";
		
		if (productBean.getLimitBenefitShowedOnEdc() !=null){
			result = productBean.getLimitBenefitShowedOnEdc().toString();
		}
		return result;
	}
	public void setExcessPaymentType (String object){
		
		if (object != null && !object.equals("")){			
			productBean.setExcessPaymentType(Integer.valueOf(object));
		}
	}
	public String getExcessPaymentType(){
		String result = "0";
		
		if (productBean.getExcessPaymentType() !=null){
			result = productBean.getExcessPaymentType().toString();
		}
		return result;
	}
	public void setRoomRate (String object){
		
		if (object != null && !object.equals("")){			
			productBean.setProductClassRate(Double.valueOf(object));
		}
	}
	public String getRoomRate (){
		String result = "0";
		
		if (productBean.getProductClassRate() !=null){
			result = productBean.getProductClassRate().toString();
		}
		return result;
	}
	public void setTreatmentClassId (String object){
		
		if (object != null && !object.equals("")){			
			TreatmentClass treatmentClass = new TreatmentClass();
			treatmentClass.setTreatmentClassId(Integer.valueOf(object));
			productBean.setTreatmentClassId(treatmentClass);
		}
	}
	public String getTreatmentClassId (){
		String result = "0";
		
		if (productBean.getTreatmentClassId() !=null){
			result = productBean.getTreatmentClassId().getTreatmentClassId().toString();
		}
		return result;
	}
	public void setProductCurrencyId (String object){
		
		if (object != null && !object.equals("")){			
			Currency currency = new Currency();
			currency.setCurrencyId(Integer.valueOf(object));
			productBean.setProductCurrencyId(currency);
		}
	}
	public String getProductCurrencyId (){
		String result = "0";
		
		if (productBean.getProductCurrencyId() !=null){
			result = productBean.getProductCurrencyId().getCurrencyId().toString();
		}
		return result;
	}
	public void setIsUsingSalary (String object){
		
		if (object != null && !object.equals("")){			
			
			productBean.setIsUsingSalary(Integer.valueOf(object));
		}
	}
	public String getIsUsingSalary (){
		String result = "";
		
		if (productBean.getIsUsingSalary() !=null){
			result = productBean.getIsUsingSalary().toString();
		}
		return result;
	}
	public void setIsUsingProrateLimit (String object){
		
		if (object != null && !object.equals("")){			
			
			productBean.setIsUsingProrateLimit(Integer.valueOf(object));
		}
	}
	public String getIsUsingProrateLimit (){
		String result = "";
		
		if (productBean.getIsUsingProrateLimit() !=null){
			result = productBean.getIsUsingProrateLimit().toString();
		}
		return result;
	}
	public void setSalaryMultiplier (String object){
		
		if (object != null && !object.equals("")){			
			
			productBean.setSalaryMultiplier(Double.valueOf(object));
		}
	}
	public String getSalaryMultiplier (){
		String result = "1";
		
		if (productBean.getSalaryMultiplier() !=null){
			result = productBean.getSalaryMultiplier().toString();
		}
		return result;
	}
	public void setPolicyId (String object){
		
		if (object != null && !object.equals("")){			
			Policy policy = new Policy();
			policy.setPolicyId(Integer.valueOf(object));
			productBean.setPolicyId(policy);
		}
	}
	public String getPolicyId (){
		String result = "";
		
		if (productBean.getPolicyId() !=null){
			result = productBean.getPolicyId().getPolicyId().toString();
		}
		return result;
	}
	public void setSharedProductId (String object){
		
		if (object != null && !object.equals("")){			
			Product product = new Product();
			product.setProductId(Integer.valueOf(object));
			productBean.setSharedProductId(product);
		}
	}
	public String getSharedProductId (){
		String result = "";
		
		if (productBean.getSharedProductId() !=null){
			result = productBean.getSharedProductId().getProductId().toString();
		}
		return result;
	}
	public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	public String getSharedProductName() {
		return sharedProductName;
	}
	public void setSharedProductName(String sharedProductName) {
		this.sharedProductName = sharedProductName;
	}
	
}
