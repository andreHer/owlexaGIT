
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.ProductLimitType;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * ProductLimitType is a mapping of product_limit_type Table.
*/
public class ProductLimitTypeForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewProductLimitType = false;
	private ProductLimitType productLimitTypeBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ProductLimitTypeForm()
    {
    	this.productLimitTypeBean = new ProductLimitType();
    	this.isNewProductLimitType = true;
    }
    public ProductLimitTypeForm (ProductLimitType object){
		this.productLimitTypeBean = object;
    }
    public boolean isNewProductLimitType (){

    	return this.isNewProductLimitType;
    }
	public ProductLimitType getProductLimitType (){
		return this.productLimitTypeBean ;
	}
	public void setProductLimitType (ProductLimitType object){
		this.productLimitTypeBean = object;
	}

			
	public void setProductLimitTypeId(String obj){

		productLimitTypeBean.setProductLimitTypeId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProductLimitTypeId(){
		return StringUtil.getStringValue(
		productLimitTypeBean.getProductLimitTypeId());

	}
	
					public void setProductLimit(String obj){

		productLimitTypeBean.setProductLimit(new String(obj));

	}

	public String getProductLimit(){
		return StringUtil.getStringValue(
		productLimitTypeBean.getProductLimit());

	}
	
					public void setDescription(String obj){

		productLimitTypeBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		productLimitTypeBean.getDescription());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
