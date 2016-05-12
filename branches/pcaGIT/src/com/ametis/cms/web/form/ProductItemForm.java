
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.ProductItem;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * ProductItem is a mapping of product_item Table.
*/
public class ProductItemForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewProductItem = false;
	private ProductItem productItemBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ProductItemForm()
    {
    	this.productItemBean = new ProductItem();
    	this.isNewProductItem = true;
    }
    public ProductItemForm (ProductItem object){
		this.productItemBean = object;
    }
    public boolean isNewProductItem (){

    	return this.isNewProductItem;
    }
	public ProductItem getProductItem (){
		return this.productItemBean ;
	}
	public void setProductItem (ProductItem object){
		this.productItemBean = object;
	}

			
	public void setProductItemId(String obj){

		productItemBean.setProductItemId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProductItemId(){
		return StringUtil.getStringValue(
		productItemBean.getProductItemId());

	}
	
				
	public void setProductId(String obj){

		productItemBean.setProductId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProductId(){
		return StringUtil.getStringValue(
		productItemBean.getProductId());

	}
	
				
	public void setItemId(String obj){

		productItemBean.setItemId(StringUtil.getIntegerValue(obj,0));

	}

	public String getItemId(){
		return StringUtil.getStringValue(
		productItemBean.getItemId());

	}
	
				
	public void setCreatedTime(String obj){

		productItemBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		productItemBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		productItemBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		productItemBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		productItemBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		productItemBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		productItemBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		productItemBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		productItemBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		productItemBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		productItemBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		productItemBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		productItemBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		productItemBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
