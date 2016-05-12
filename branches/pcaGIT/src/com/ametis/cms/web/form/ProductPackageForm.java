
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.InsurancePackage;
import com.ametis.cms.datamodel.Product;
import com.ametis.cms.datamodel.ProductPackage;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * ProductPackage is a mapping of product_package Table.
*/
public class ProductPackageForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewProductPackage = false;
	private ProductPackage productPackageBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ProductPackageForm()
    {
    	this.productPackageBean = new ProductPackage();
    	this.isNewProductPackage = true;
    }
    public ProductPackageForm (ProductPackage object){
		this.productPackageBean = object;
    }
    public boolean isNewProductPackage (){

    	return this.isNewProductPackage;
    }
	public ProductPackage getProductPackage (){
		return this.productPackageBean ;
	}
	public void setProductPackage (ProductPackage object){
		this.productPackageBean = object;
	}

			
	public void setProductPackageId(String obj){

		productPackageBean.setProductPackageId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProductPackageId(){
		return StringUtil.getStringValue(
		productPackageBean.getProductPackageId());

	}
	
										
	public void setQuantity(String obj){

		productPackageBean.setQuantity(StringUtil.getIntegerValue(obj,0));

	}

	public String getQuantity(){
		return StringUtil.getStringValue(
		productPackageBean.getQuantity());

	}
	
				
	public void setCreatedTime(String obj){

		productPackageBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		productPackageBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		productPackageBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		productPackageBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		productPackageBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		productPackageBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		productPackageBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		productPackageBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		productPackageBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		productPackageBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		productPackageBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		productPackageBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		productPackageBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		productPackageBean.getDeletedStatus());

	}
	
		

	// foreign affairs
	
	

	
	public void setProductId(String obj){
		Product fk = new Product();
		fk.setProductId(StringUtil.getIntegerValue(obj,0));
		productPackageBean.setProductId(fk);

	}

	public String getProductId(){
		return StringUtil.getStringValue(
		productPackageBean.getProductId().getProductId());

	}
	//---
	
	

	
	public void setPackageId(String obj){
		InsurancePackage fk = new InsurancePackage();
		fk.setPackageId(StringUtil.getIntegerValue(obj,0));
		productPackageBean.setPackageId(fk);

	}

	public String getPackageId(){
		return StringUtil.getStringValue(
		productPackageBean.getPackageId().getPackageId());

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
