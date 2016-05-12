
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.ProviderCategory;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * ProviderCategory is a mapping of provider_category Table.
*/
public class ProviderCategoryForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewProviderCategory = false;
	private ProviderCategory providerCategoryBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ProviderCategoryForm()
    {
    	this.providerCategoryBean = new ProviderCategory();
    	this.isNewProviderCategory = true;
    }
    public ProviderCategoryForm (ProviderCategory object){
		this.providerCategoryBean = object;
    }
    public boolean isNewProviderCategory (){

    	return this.isNewProviderCategory;
    }
	public ProviderCategory getProviderCategory (){
		return this.providerCategoryBean ;
	}
	public void setProviderCategory (ProviderCategory object){
		this.providerCategoryBean = object;
	}

			
	public void setProviderCategoryId(String obj){

		providerCategoryBean.setProviderCategoryId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProviderCategoryId(){
		return StringUtil.getStringValue(
		providerCategoryBean.getProviderCategoryId());

	}
	
					public void setProviderCategoryCode(String obj){

		providerCategoryBean.setProviderCategoryCode(new String(obj));

	}

	public String getProviderCategoryCode(){
		return StringUtil.getStringValue(
		providerCategoryBean.getProviderCategoryCode());

	}
	
					public void setProviderCategoryName(String obj){

		providerCategoryBean.setProviderCategoryName(new String(obj));

	}

	public String getProviderCategoryName(){
		return StringUtil.getStringValue(
		providerCategoryBean.getProviderCategoryName());

	}
	
					public void setDescription(String obj){

		providerCategoryBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		providerCategoryBean.getDescription());

	}
	
				
	public void setCreatedTime(String obj){

		providerCategoryBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		providerCategoryBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		providerCategoryBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		providerCategoryBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		providerCategoryBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		providerCategoryBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		providerCategoryBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		providerCategoryBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		providerCategoryBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		providerCategoryBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		providerCategoryBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		providerCategoryBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		providerCategoryBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		providerCategoryBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
