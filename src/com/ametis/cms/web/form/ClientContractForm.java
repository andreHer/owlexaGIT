
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.ClientContract;
import com.ametis.cms.datamodel.ContractType;
import com.ametis.cms.datamodel.Currency;
import com.ametis.cms.datamodel.ProductType;
import com.ametis.cms.util.StringUtil;
// imports+ 

// imports- 

/**
 * ClientContract is a mapping of client_contract Table.
*/
public class ClientContractForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewClientContract = false;
	private ClientContract clientContractBean ;
	private String clientName;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ClientContractForm()
    {
    	this.clientContractBean = new ClientContract();
    	this.isNewClientContract = true;
    }
    public ClientContractForm (ClientContract object){
		this.clientContractBean = object;
    }
    public boolean isNewClientContract (){

    	return this.isNewClientContract;
    }
	public ClientContract getClientContract (){
		return this.clientContractBean ;
	}
	public void setClientContract (ClientContract object){
		this.clientContractBean = object;
	}

			
	public void setClientContractId(String obj){

		clientContractBean.setClientContractId(StringUtil.getIntegerValue(obj,0));

	}

	public String getClientContractId(){
		return StringUtil.getStringValue(
		clientContractBean.getClientContractId());

	}
	
					public void setClientContractNumber(String obj){

		clientContractBean.setClientContractNumber(new String(obj));

	}

	public String getClientContractNumber(){
		return StringUtil.getStringValue(
		clientContractBean.getClientContractNumber());

	}
	
										
	public void setContractValuePerMember(String obj){

		clientContractBean.setContractValuePerMember(StringUtil.getDoubleValue(obj,0));

	}

	public String getContractValuePerMember(){
		return StringUtil.getStringValue(
		clientContractBean.getContractValuePerMember());

	}
	
				
	public void setTotalContractValue(String obj){

		clientContractBean.setTotalContractValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalContractValue(){
		return StringUtil.getStringValue(
		clientContractBean.getTotalContractValue());

	}
	
				
	public void setIsUsingProration(String obj){

		clientContractBean.setIsUsingProration(StringUtil.getIntegerValue(obj,0));

	}

	public String getIsUsingProration(){
		return StringUtil.getStringValue(
		clientContractBean.getIsUsingProration());

	}
	
				
	public void setPaymentPeriode(String obj){

		clientContractBean.setPaymentPeriode(StringUtil.getIntegerValue(obj,0));

	}

	public String getPaymentPeriode(){
		return StringUtil.getStringValue(
		clientContractBean.getPaymentPeriode());

	}
	
				
	public void setContractStartDate(String obj){

		clientContractBean.setContractStartDate(java.sql.Date.valueOf(obj));

	}

	public String getContractStartDate(){
		return StringUtil.getStringValue(
		clientContractBean.getContractStartDate());

	}

	
				
	public void setContractEndDate(String obj){

		clientContractBean.setContractEndDate(java.sql.Date.valueOf(obj));

	}

	public String getContractEndDate(){
		return StringUtil.getStringValue(
		clientContractBean.getContractEndDate());

	}

	
				
	public void setTotalMember(String obj){

		clientContractBean.setTotalMember(StringUtil.getIntegerValue(obj,0));

	}

	public String getTotalMember(){
		return StringUtil.getStringValue(
		clientContractBean.getTotalMember());

	}
	
					public void setDescription(String obj){

		clientContractBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		clientContractBean.getDescription());

	}
	
				
	public void setCreatedTime(String obj){

		clientContractBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		clientContractBean.getCreatedTime());

	}

	
					public void setInvoiceDueLength(String obj){

		clientContractBean.setInvoiceDueLength(StringUtil.getIntegerValue(obj, 14));

	}

	public String getInvoiceDueLength(){
		return StringUtil.getStringValue(
		clientContractBean.getInvoiceDueLength());

	}
	
				
	public void setModifiedTime(String obj){

		clientContractBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		clientContractBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		clientContractBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		clientContractBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		clientContractBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		clientContractBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		clientContractBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		clientContractBean.getDeletedBy());

	}
	
				
	public void setMembershipPeriode(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			clientContractBean.setMembershipPeriode(Integer.valueOf(obj));
		}

	}

	public String getMembershipPeriode(){
		return StringUtil.getStringValue(
		clientContractBean.getMembershipPeriode());

	}
	public void setIsRefundAvailable(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			clientContractBean.setIsRefundAvailable(Integer.valueOf(obj));
		}

	}

	public String getIsRefundAvailable(){
		return StringUtil.getStringValue(
		clientContractBean.getIsRefundAvailable());

	}	
	public void setProrateType(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			clientContractBean.setProrateType(Integer.valueOf(obj));
		}

	}

	public String getProrateType(){
		return StringUtil.getStringValue(
		clientContractBean.getProrateType());

	}			
	public void setContractStatus(String obj){

		clientContractBean.setContractStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getContractStatus(){
		return StringUtil.getStringValue(
		clientContractBean.getContractStatus());

	}
	
		

	// foreign affairs
	
	

	
	public void setContractTypeId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			ContractType fk = new ContractType();
			fk.setContractTypeId(StringUtil.getIntegerValue(obj,0));
			clientContractBean.setContractTypeId(fk);
		}

	}

	public String getContractTypeId(){
		String result = "";
		
		if (clientContractBean.getContractTypeId() != null){
			result = StringUtil.getStringValue(
					clientContractBean.getContractTypeId().getContractTypeId());
		}
		
		return result;
		

	}
	public String getProductTypeId(){
		String result = "";
		
		if (clientContractBean.getProductTypeId() != null){
			result = StringUtil.getStringValue(
					clientContractBean.getProductTypeId().getProductTypeId());
		}
		
		return result;
		

	}
	public void setProductTypeId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			ProductType fk = new ProductType();
			fk.setProductTypeId(StringUtil.getIntegerValue(obj,0));
			clientContractBean.setProductTypeId(fk);
		}

	}
	public String getCurrencyId(){
		String result = "";
		
		if (clientContractBean.getCurrencyId() != null){
			result = StringUtil.getStringValue(
					clientContractBean.getCurrencyId().getCurrencyId());
		}
		
		return result;
		

	}
	public void setCurrencyId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Currency fk = new Currency();
			fk.setCurrencyId(StringUtil.getIntegerValue(obj,0));
			clientContractBean.setCurrencyId(fk);
		}

	}
	public void setClientId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Client fk = new Client();
			fk.setClientId(StringUtil.getIntegerValue(obj,0));
			clientContractBean.setClientId(fk);
		}

	}
	


	public String getClientId(){
		String result = "";
		
		
		if (clientContractBean.getClientId() != null){
			result = StringUtil.getStringValue(
					clientContractBean.getClientId().getClientId());
		}
		return result;
		

	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	public String getDiscountPercentage() {
		
		return StringUtil.getStringValue(clientContractBean.getDiscountPercentage());
	}
	public void setDiscountPercentage(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			this.clientContractBean.setDiscountPercentage(Double.valueOf(obj));
		}
	}
	
	public String getDiscountAmountPerQuantity() {
		return StringUtil.getStringValue(clientContractBean.getDiscountAmountPerQuantity());
	}
	public void setDiscountAmountPerQuantity(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			this.clientContractBean.setDiscountAmountPerQuantity(Double.valueOf(obj));
		}
	}
	
	public String getBillingCutOffDate() {
		return StringUtil.getStringValue(clientContractBean.getBillingCutOffDate());

	}
	public void setBillingCutOffDate(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			this.clientContractBean.setBillingCutOffDate(Integer.valueOf(obj));
		}
		
	}
	
	public String getIsUsingVolumeLevel() {
		return StringUtil.getStringValue(clientContractBean.getIsUsingVolumeLevel());
	}
	public void setIsUsingVolumeLevel(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			this.clientContractBean.setIsUsingVolumeLevel(Integer.valueOf(obj));
		}
	}
	
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
