
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.ProductType;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * ProductType is a mapping of product_type Table.
*/
public class ProductTypeForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewProductType = false;
	private ProductType productTypeBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ProductTypeForm()
    {
    	this.productTypeBean = new ProductType();
    	this.isNewProductType = true;
    }
    public ProductTypeForm (ProductType object){
		this.productTypeBean = object;
    }
    public boolean isNewProductType (){

    	return this.isNewProductType;
    }
	public ProductType getProductType (){
		return this.productTypeBean ;
	}
	public void setProductType (ProductType object){
		this.productTypeBean = object;
	}

			
	public void setProductTypeId(String obj){

		productTypeBean.setProductTypeId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProductTypeId(){
		return StringUtil.getStringValue(
		productTypeBean.getProductTypeId());

	}
	
					public void setProductTypeName(String obj){

		productTypeBean.setProductTypeName(new String(obj));

	}

	public String getProductTypeName(){
		return StringUtil.getStringValue(
		productTypeBean.getProductTypeName());

	}
	
					public void setDescription(String obj){

		productTypeBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		productTypeBean.getDescription());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
