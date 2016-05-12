
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * ProviderGroup is a mapping of provider_group Table.
*/
public class ProviderGroupForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewProviderGroup = false;
	private ProviderGroup providerGroupBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ProviderGroupForm()
    {
    	this.providerGroupBean = new ProviderGroup();
    	this.isNewProviderGroup = true;
    }
    public ProviderGroupForm (ProviderGroup object){
		this.providerGroupBean = object;
    }
    public boolean isNewProviderGroup (){

    	return this.isNewProviderGroup;
    }
	public ProviderGroup getProviderGroup (){
		return this.providerGroupBean ;
	}
	public void setProviderGroup (ProviderGroup object){
		this.providerGroupBean = object;
	}

			
	public void setProviderGroupId(String obj){

		providerGroupBean.setProviderGroupId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProviderGroupId(){
		return StringUtil.getStringValue(
		providerGroupBean.getProviderGroupId());

	}
	
					public void setCreatedBy(String obj){

		providerGroupBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		providerGroupBean.getCreatedBy());

	}
	
				
	public void setCreatedTime(String obj){

		providerGroupBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		providerGroupBean.getCreatedTime());

	}

	
					public void setDeletedBy(String obj){

		providerGroupBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		providerGroupBean.getDeletedBy());

	}
	
				
	public void setDeletedTime(String obj){

		providerGroupBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		providerGroupBean.getDeletedTime());

	}

	
					public void setModifiedBy(String obj){

		providerGroupBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		providerGroupBean.getModifiedBy());

	}
	
				
	public void setModifiedTime(String obj){

		providerGroupBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		providerGroupBean.getModifiedTime());

	}

	
					public void setProviderGroupName(String obj){

		providerGroupBean.setProviderGroupName(new String(obj));

	}

	public String getProviderGroupName(){
		return StringUtil.getStringValue(
		providerGroupBean.getProviderGroupName());

	}
	
					public void setProviderGroupCode(String obj){

		providerGroupBean.setProviderGroupCode(new String(obj));

	}

	public String getProviderGroupCode(){
		return StringUtil.getStringValue(
		providerGroupBean.getProviderGroupCode());

	}
	
				
	public void setDeletedStatus(String obj){

		providerGroupBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		providerGroupBean.getDeletedStatus());

	}
	
					public void setDescription(String obj){

		providerGroupBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		providerGroupBean.getDescription());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
