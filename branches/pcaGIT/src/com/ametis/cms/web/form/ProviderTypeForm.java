
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * ProviderType is a mapping of provider_type Table.
*/
public class ProviderTypeForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewProviderType = false;
	private ProviderType providerTypeBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ProviderTypeForm()
    {
    	this.providerTypeBean = new ProviderType();
    	this.isNewProviderType = true;
    }
    public ProviderTypeForm (ProviderType object){
		this.providerTypeBean = object;
    }
    public boolean isNewProviderType (){

    	return this.isNewProviderType;
    }
	public ProviderType getProviderType (){
		return this.providerTypeBean ;
	}
	public void setProviderType (ProviderType object){
		this.providerTypeBean = object;
	}

			
	public void setProviderTypeId(String obj){

		providerTypeBean.setProviderTypeId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProviderTypeId(){
		return StringUtil.getStringValue(
		providerTypeBean.getProviderTypeId());

	}
	
					public void setProviderTypeName(String obj){

		providerTypeBean.setProviderTypeName(new String(obj));

	}

	public String getProviderTypeName(){
		return StringUtil.getStringValue(
		providerTypeBean.getProviderTypeName());

	}
	
					public void setProviderTypeCode(String obj){

		providerTypeBean.setProviderTypeCode(new String(obj));

	}

	public String getProviderTypeCode(){
		return StringUtil.getStringValue(
		providerTypeBean.getProviderTypeCode());

	}
	
					public void setDescription(String obj){

		providerTypeBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		providerTypeBean.getDescription());

	}
	
				
	public void setCreatedTime(String obj){

		providerTypeBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		providerTypeBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		providerTypeBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		providerTypeBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		providerTypeBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		providerTypeBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		providerTypeBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		providerTypeBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		providerTypeBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		providerTypeBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		providerTypeBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		providerTypeBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		providerTypeBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		providerTypeBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
