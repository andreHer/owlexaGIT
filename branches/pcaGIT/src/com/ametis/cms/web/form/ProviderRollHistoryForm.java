
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * ProviderRollHistory is a mapping of provider_roll_history Table.
*/
public class ProviderRollHistoryForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewProviderRollHistory = false;
	private ProviderRollHistory providerRollHistoryBean ;
	private String providerName;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ProviderRollHistoryForm()
    {
    	this.providerRollHistoryBean = new ProviderRollHistory();
    	this.isNewProviderRollHistory = true;
    }
    public ProviderRollHistoryForm (ProviderRollHistory object){
		this.providerRollHistoryBean = object;
    }
    public boolean isNewProviderRollHistory (){

    	return this.isNewProviderRollHistory;
    }
	public ProviderRollHistory getProviderRollHistory (){
		return this.providerRollHistoryBean ;
	}
	public void setProviderRollHistory (ProviderRollHistory object){
		this.providerRollHistoryBean = object;
	}

			
	public void setProviderRollHistoryId(String obj){

		providerRollHistoryBean.setProviderRollHistoryId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProviderRollHistoryId(){
		return StringUtil.getStringValue(
		providerRollHistoryBean.getProviderRollHistoryId());

	}
	
				
//	public void setProviderId(String obj){
//
//		providerRollHistoryBean.setProviderId(StringUtil.getIntegerValue(obj,0));
//
//	}
//
//	public String getProviderId(){
//		return StringUtil.getStringValue(
//		providerRollHistoryBean.getProviderId());
//
//	}
//	
	public void setProviderId(String obj){

		if (obj != null && !obj.equals("")){
			Provider provider = new Provider();
			provider.setProviderId(Integer.valueOf(obj));
			
			providerRollHistoryBean.setProviderId(provider);
		}

	}

	public String getProviderId(){
		String result = "";
		
		if (providerRollHistoryBean.getProviderId() != null){
			result = providerRollHistoryBean.getProviderId().getProviderId().toString();
		}
		
		return result;
		

	}
	
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
			
	
	public void setDeliveryDate(String obj) {
		if (obj != null && obj.equals("")) {
			providerRollHistoryBean.setDeliveryDate(null);
		} else {
			providerRollHistoryBean.setDeliveryDate(java.sql.Date.valueOf(obj));
		}

	}

	public String getDeliveryDate() {

		String deliveryDate = "";

		if (providerRollHistoryBean.getDeliveryDate() != null) {
			deliveryDate = StringUtil.getStringValue(providerRollHistoryBean.getDeliveryDate());
		}

		return deliveryDate;
	}

	
				
	public void setRollStockAmount(String obj){

		providerRollHistoryBean.setRollStockAmount(StringUtil.getIntegerValue(obj,0));

	}

	public String getRollStockAmount(){
		return StringUtil.getStringValue(
		providerRollHistoryBean.getRollStockAmount());

	}
	
				
	public void setCreatedTime(String obj){

		providerRollHistoryBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		providerRollHistoryBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		providerRollHistoryBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		providerRollHistoryBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		providerRollHistoryBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		providerRollHistoryBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		providerRollHistoryBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		providerRollHistoryBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		providerRollHistoryBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		providerRollHistoryBean.getDeletedStatus());

	}
	
				
	public void setDeletedTime(String obj){

		providerRollHistoryBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		providerRollHistoryBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		providerRollHistoryBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		providerRollHistoryBean.getDeletedBy());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
