
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * ProviderCapitationFund is a mapping of provider_capitation_fund Table.
*/
public class ProviderCapitationFundForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewProviderCapitationFund = false;
	private ProviderCapitationFund providerCapitationFundBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ProviderCapitationFundForm()
    {
    	this.providerCapitationFundBean = new ProviderCapitationFund();
    	this.isNewProviderCapitationFund = true;
    }
    public ProviderCapitationFundForm (ProviderCapitationFund object){
		this.providerCapitationFundBean = object;
    }
    public boolean isNewProviderCapitationFund (){

    	return this.isNewProviderCapitationFund;
    }
	public ProviderCapitationFund getProviderCapitationFund (){
		return this.providerCapitationFundBean ;
	}
	public void setProviderCapitationFund (ProviderCapitationFund object){
		this.providerCapitationFundBean = object;
	}

			
	public void setProviderCapitationFundId(String obj){

		providerCapitationFundBean.setProviderCapitationFundId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProviderCapitationFundId(){
		return StringUtil.getStringValue(
		providerCapitationFundBean.getProviderCapitationFundId());

	}
	
				
	public void setProviderId(String obj){

		providerCapitationFundBean.setProviderId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProviderId(){
		return StringUtil.getStringValue(
		providerCapitationFundBean.getProviderId());

	}
	
				
	public void setClientId(String obj){

		providerCapitationFundBean.setClientId(StringUtil.getIntegerValue(obj,0));

	}

	public String getClientId(){
		return StringUtil.getStringValue(
		providerCapitationFundBean.getClientId());

	}
	
				
	public void setCurrentCapitationFund(String obj){

		providerCapitationFundBean.setCurrentCapitationFund(StringUtil.getDoubleValue(obj,0));

	}

	public String getCurrentCapitationFund(){
		return StringUtil.getStringValue(
		providerCapitationFundBean.getCurrentCapitationFund());

	}
	
				
	public void setTotalCapitationFund(String obj){

		providerCapitationFundBean.setTotalCapitationFund(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalCapitationFund(){
		return StringUtil.getStringValue(
		providerCapitationFundBean.getTotalCapitationFund());

	}
	
				
	public void setCapitationFundUsage(String obj){

		providerCapitationFundBean.setCapitationFundUsage(StringUtil.getDoubleValue(obj,0));

	}

	public String getCapitationFundUsage(){
		return StringUtil.getStringValue(
		providerCapitationFundBean.getCapitationFundUsage());

	}
	
				
	public void setLastAdditionDate(String obj){

		providerCapitationFundBean.setLastAdditionDate(java.sql.Date.valueOf(obj));

	}

	public String getLastAdditionDate(){
		return StringUtil.getStringValue(
		providerCapitationFundBean.getLastAdditionDate());

	}

	
				
	public void setLastUsageDate(String obj){

		providerCapitationFundBean.setLastUsageDate(java.sql.Date.valueOf(obj));

	}

	public String getLastUsageDate(){
		return StringUtil.getStringValue(
		providerCapitationFundBean.getLastUsageDate());

	}

	
				
	public void setCreatedTime(String obj){

		providerCapitationFundBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		providerCapitationFundBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		providerCapitationFundBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		providerCapitationFundBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		providerCapitationFundBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		providerCapitationFundBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		providerCapitationFundBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		providerCapitationFundBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		providerCapitationFundBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		providerCapitationFundBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		providerCapitationFundBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		providerCapitationFundBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		providerCapitationFundBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		providerCapitationFundBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
