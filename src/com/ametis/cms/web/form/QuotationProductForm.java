
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * QuotationProduct is a mapping of quotation_product Table.
*/
public class QuotationProductForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewQuotationProduct = false;
	private QuotationProduct quotationProductBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public QuotationProductForm()
    {
    	this.quotationProductBean = new QuotationProduct();
    	this.isNewQuotationProduct = true;
    }
    public QuotationProductForm (QuotationProduct object){
		this.quotationProductBean = object;
    }
    public boolean isNewQuotationProduct (){

    	return this.isNewQuotationProduct;
    }
	public QuotationProduct getQuotationProduct (){
		return this.quotationProductBean ;
	}
	public void setQuotationProduct (QuotationProduct object){
		this.quotationProductBean = object;
	}

			
	public void setQuotationProductId(String obj){

		quotationProductBean.setQuotationProductId(StringUtil.getIntegerValue(obj,0));

	}

	public String getQuotationProductId(){
		return StringUtil.getStringValue(
		quotationProductBean.getQuotationProductId());

	}
	
										
	public void setQuantity(String obj){

		quotationProductBean.setQuantity(StringUtil.getIntegerValue(obj,0));

	}

	public String getQuantity(){
		return StringUtil.getStringValue(
		quotationProductBean.getQuantity());

	}
	
				
	public void setMemberType(String obj){

		quotationProductBean.setMemberType(StringUtil.getIntegerValue(obj,0));

	}

	public String getMemberType(){
		return StringUtil.getStringValue(
		quotationProductBean.getMemberType());

	}
	
				
	public void setCreatedTime(String obj){

		quotationProductBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		quotationProductBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		quotationProductBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		quotationProductBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		quotationProductBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		quotationProductBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		quotationProductBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		quotationProductBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		quotationProductBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		quotationProductBean.getDeletedStatus());

	}
	
				
	public void setModifiedTime(String obj){

		quotationProductBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		quotationProductBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		quotationProductBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		quotationProductBean.getModifiedBy());

	}
	
		

	// foreign affairs
	
	

	
	public void setProductId(String obj){
		Product fk = new Product();
		fk.setProductId(StringUtil.getIntegerValue(obj,0));
		quotationProductBean.setProductId(fk);

	}

	public String getProductId(){
		return StringUtil.getStringValue(
		quotationProductBean.getProductId().getProductId());

	}
	//---
	
	

	
	public void setQuotationId(String obj){
		Quotation fk = new Quotation();
		fk.setQuotationId(StringUtil.getIntegerValue(obj,0));
		quotationProductBean.setQuotationId(fk);

	}

	public String getQuotationId(){
		return StringUtil.getStringValue(
		quotationProductBean.getQuotationId().getQuotationId());

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
