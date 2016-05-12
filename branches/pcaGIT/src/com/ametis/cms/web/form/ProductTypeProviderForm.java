
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * ProductTypeProvider is a mapping of product_type_provider Table.
*/
public class ProductTypeProviderForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewProductTypeProvider = false;
	private ProductTypeProvider productTypeProviderBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ProductTypeProviderForm()
    {
    	this.productTypeProviderBean = new ProductTypeProvider();
    	this.isNewProductTypeProvider = true;
    }
    public ProductTypeProviderForm (ProductTypeProvider object){
		this.productTypeProviderBean = object;
    }
    public boolean isNewProductTypeProvider (){

    	return this.isNewProductTypeProvider;
    }
	public ProductTypeProvider getProductTypeProvider (){
		return this.productTypeProviderBean ;
	}
	public void setProductTypeProvider (ProductTypeProvider object){
		this.productTypeProviderBean = object;
	}

			
	public void setProductTypeProviderId(String obj){

		productTypeProviderBean.setProductTypeProviderId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProductTypeProviderId(){
		return StringUtil.getStringValue(
		productTypeProviderBean.getProductTypeProviderId());

	}
	
				
	public void setProductTypeId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			ProductType tipe = new ProductType();
			tipe.setProductTypeId(Integer.valueOf(obj));
			productTypeProviderBean.setProductTypeId(tipe);
		}

	}

	public String getProductTypeId(){
		String result = "";
		
		if (productTypeProviderBean.getProductTypeId() != null){
			result = StringUtil.getStringValue(
					productTypeProviderBean.getProductTypeId().getProductTypeId());
		}
		return result;
		

	}
	
				
	public void setProviderId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
		
			Provider provider = new Provider();
			provider.setProviderId(Integer.valueOf(obj));
			productTypeProviderBean.setProviderId(provider);
		}
		

	}

	public String getProviderId(){
		String result = "";
		
		if (productTypeProviderBean.getProviderId() != null){
			result = StringUtil.getStringValue(
					productTypeProviderBean.getProviderId().getProviderId());
		}
		return result;
		

	}
	
				
	public void setStatus(String obj){

		productTypeProviderBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		productTypeProviderBean.getStatus());

	}
	
				
	public void setType(String obj){

		productTypeProviderBean.setType(StringUtil.getIntegerValue(obj,0));

	}

	public String getType(){
		return StringUtil.getStringValue(
		productTypeProviderBean.getType());

	}
	
					public void setDescription(String obj){

		productTypeProviderBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		productTypeProviderBean.getDescription());

	}
	
				
	public void setCreatedTime(String obj){

		productTypeProviderBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		productTypeProviderBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		productTypeProviderBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		productTypeProviderBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		productTypeProviderBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		productTypeProviderBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		productTypeProviderBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		productTypeProviderBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		productTypeProviderBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		productTypeProviderBean.getDeletedStatus());

	}
	
				
	public void setDeletedTime(String obj){

		productTypeProviderBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		productTypeProviderBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		productTypeProviderBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		productTypeProviderBean.getDeletedBy());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
