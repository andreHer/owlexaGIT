
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * ProductTypePoliklinik is a mapping of product_type_poliklinik Table.
*/
public class ProductTypePoliklinikForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewProductTypePoliklinik = false;
	private ProductTypePoliklinik productTypePoliklinikBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ProductTypePoliklinikForm()
    {
    	this.productTypePoliklinikBean = new ProductTypePoliklinik();
    	this.isNewProductTypePoliklinik = true;
    }
    public ProductTypePoliklinikForm (ProductTypePoliklinik object){
		this.productTypePoliklinikBean = object;
    }
    public boolean isNewProductTypePoliklinik (){

    	return this.isNewProductTypePoliklinik;
    }
	public ProductTypePoliklinik getProductTypePoliklinik (){
		return this.productTypePoliklinikBean ;
	}
	public void setProductTypePoliklinik (ProductTypePoliklinik object){
		this.productTypePoliklinikBean = object;
	}

			
	public void setProductTypePoliklinikId(String obj){

		productTypePoliklinikBean.setProductTypePoliklinikId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProductTypePoliklinikId(){
		return StringUtil.getStringValue(
		productTypePoliklinikBean.getProductTypePoliklinikId());

	}
	
				
	public void setProductTypeId(String obj){

		if (obj != null && !obj.equals("")){
			ProductType type = new ProductType();
			type.setProductTypeId(Integer.valueOf(obj));
			productTypePoliklinikBean.setProductTypeId(type);
		}
		

	}

	public String getProductTypeId(){
		String result = "";
		
		if (productTypePoliklinikBean.getProductTypeId() != null){
			result = StringUtil.getStringValue(
					productTypePoliklinikBean.getProductTypeId().getProductTypeId());
		}
		return result;
		

	}
	
				
	public void setPoliklinikId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Poliklinik poli = new Poliklinik();
			poli.setPoliklinikId(Integer.valueOf(obj));
			productTypePoliklinikBean.setPoliklinikId(poli);
		}
		

	}

	public String getPoliklinikId(){
		String result = "";
		
		if (productTypePoliklinikBean.getPoliklinikId() != null){
			result = StringUtil.getStringValue(
					productTypePoliklinikBean.getPoliklinikId().getPoliklinikId());
		}
		return result;
		

	}
	
				
	public void setStatus(String obj){

		productTypePoliklinikBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		productTypePoliklinikBean.getStatus());

	}
	
				
	public void setType(String obj){

		productTypePoliklinikBean.setType(StringUtil.getIntegerValue(obj,0));

	}

	public String getType(){
		return StringUtil.getStringValue(
		productTypePoliklinikBean.getType());

	}
	
					public void setDescription(String obj){

		productTypePoliklinikBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		productTypePoliklinikBean.getDescription());

	}
	
				
	public void setCreatedTime(String obj){

		productTypePoliklinikBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		productTypePoliklinikBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		productTypePoliklinikBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		productTypePoliklinikBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		productTypePoliklinikBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		productTypePoliklinikBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		productTypePoliklinikBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		productTypePoliklinikBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		productTypePoliklinikBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		productTypePoliklinikBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		productTypePoliklinikBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		productTypePoliklinikBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		productTypePoliklinikBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		productTypePoliklinikBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
