
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * ProviderHistory is a mapping of provider_history Table.
*/
public class ProviderHistoryForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewProviderHistory = false;
	private ProviderHistory providerHistoryBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ProviderHistoryForm()
    {
    	this.providerHistoryBean = new ProviderHistory();
    	this.isNewProviderHistory = true;
    }
    public ProviderHistoryForm (ProviderHistory object){
		this.providerHistoryBean = object;
    }
    public boolean isNewProviderHistory (){

    	return this.isNewProviderHistory;
    }
	public ProviderHistory getProviderHistory (){
		return this.providerHistoryBean ;
	}
	public void setProviderHistory (ProviderHistory object){
		this.providerHistoryBean = object;
	}

			
	public void setProviderHistoryId(String obj){

		providerHistoryBean.setProviderHistoryId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProviderHistoryId(){
		return StringUtil.getStringValue(
		providerHistoryBean.getProviderHistoryId());

	}
	
				
	public void setProviderId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Provider provider = new Provider();
			provider.setProviderId(Integer.valueOf(obj));
			providerHistoryBean.setProviderId(provider);
		}

	}

	public String getProviderId(){
		String result = "";
		
		if (providerHistoryBean.getProviderId() != null){
			result = StringUtil.getStringValue(
					providerHistoryBean.getProviderId().getProviderId()); 
		}
		return result;

	}
	
				
	public void setStatus(String obj){

		providerHistoryBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		providerHistoryBean.getStatus());

	}
	
					public void setActionType(String obj){

		providerHistoryBean.setActionType(new String(obj));

	}

	public String getActionType(){
		return StringUtil.getStringValue(
		providerHistoryBean.getActionType());

	}
	
				
	public void setActionTime(String obj){

		providerHistoryBean.setActionTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getActionTime(){
		return StringUtil.getStringValue(
		providerHistoryBean.getActionTime());

	}

	
}
