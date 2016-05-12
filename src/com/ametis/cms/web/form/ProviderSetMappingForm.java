
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * ProviderSetMapping is a mapping of provider_set_mapping Table.
*/
public class ProviderSetMappingForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewProviderSetMapping = false;
	private ProviderSetMapping providerSetMappingBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ProviderSetMappingForm()
    {
    	this.providerSetMappingBean = new ProviderSetMapping();
    	this.isNewProviderSetMapping = true;
    }
    public ProviderSetMappingForm (ProviderSetMapping object){
		this.providerSetMappingBean = object;
    }
    public boolean isNewProviderSetMapping (){

    	return this.isNewProviderSetMapping;
    }
	public ProviderSetMapping getProviderSetMapping (){
		return this.providerSetMappingBean ;
	}
	public void setProviderSetMapping (ProviderSetMapping object){
		this.providerSetMappingBean = object;
	}

			
	public void setProviderSetMappingId(String obj){

		providerSetMappingBean.setProviderSetMappingId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProviderSetMappingId(){
		return StringUtil.getStringValue(
		providerSetMappingBean.getProviderSetMappingId());

	}
	
							
	public void setProviderId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Provider provider = new Provider();
			provider.setProviderId(Integer.valueOf(obj));
			providerSetMappingBean.setProviderId(provider);
		}

	}

	public String getProviderId(){
		String result = "";
		
		if (providerSetMappingBean.getProviderId() != null){
			result =StringUtil.getStringValue(
					providerSetMappingBean.getProviderId().getProviderId()); 
		}
		return result;

	}
	
					public void setProviderName(String obj){

		providerSetMappingBean.setProviderName(new String(obj));

	}

	public String getProviderName(){
		return StringUtil.getStringValue(
		providerSetMappingBean.getProviderName());

	}
	
					public void setProviderCode(String obj){

		providerSetMappingBean.setProviderCode(new String(obj));

	}

	public String getProviderCode(){
		return StringUtil.getStringValue(
		providerSetMappingBean.getProviderCode());

	}
	
					public void setCity(String obj){

		providerSetMappingBean.setCity(new String(obj));

	}

	public String getCity(){
		return StringUtil.getStringValue(
		providerSetMappingBean.getCity());

	}
	
					public void setProvince(String obj){

		providerSetMappingBean.setProvince(new String(obj));

	}

	public String getProvince(){
		return StringUtil.getStringValue(
		providerSetMappingBean.getProvince());

	}
	
					public void setAddress(String obj){

		providerSetMappingBean.setAddress(new String(obj));

	}

	public String getAddress(){
		return StringUtil.getStringValue(
		providerSetMappingBean.getAddress());

	}
	
					public void setCountery(String obj){

		providerSetMappingBean.setCountery(new String(obj));

	}

	public String getCountery(){
		return StringUtil.getStringValue(
		providerSetMappingBean.getCountery());

	}
	
				
	public void setStatus(String obj){

		providerSetMappingBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		providerSetMappingBean.getStatus());

	}
	
				
	public void setCreatedTime(String obj){

		providerSetMappingBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		providerSetMappingBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		providerSetMappingBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		providerSetMappingBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		providerSetMappingBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		providerSetMappingBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		providerSetMappingBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		providerSetMappingBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		providerSetMappingBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		providerSetMappingBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		providerSetMappingBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		providerSetMappingBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		providerSetMappingBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		providerSetMappingBean.getDeletedStatus());

	}
	
		

	// foreign affairs
	
	

	
	public void setProviderSetId(String obj){
		ProviderSet fk = new ProviderSet();
		fk.setProviderSetId(StringUtil.getIntegerValue(obj,0));
		providerSetMappingBean.setProviderSetId(fk);

	}

	public String getProviderSetId(){
		return StringUtil.getStringValue(
		providerSetMappingBean.getProviderSetId().getProviderSetId());

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
