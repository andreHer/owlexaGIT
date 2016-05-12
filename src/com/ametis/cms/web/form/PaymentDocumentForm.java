
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * PaymentDocument is a mapping of payment_document Table.
*/
public class PaymentDocumentForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewPaymentDocument = false;
	private PaymentDocument paymentDocumentBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public PaymentDocumentForm()
    {
    	this.paymentDocumentBean = new PaymentDocument();
    	this.isNewPaymentDocument = true;
    }
    public PaymentDocumentForm (PaymentDocument object){
		this.paymentDocumentBean = object;
    }
    public boolean isNewPaymentDocument (){

    	return this.isNewPaymentDocument;
    }
	public PaymentDocument getPaymentDocument (){
		return this.paymentDocumentBean ;
	}
	public void setPaymentDocument (PaymentDocument object){
		this.paymentDocumentBean = object;
	}

			
	public void setId(String obj){

		paymentDocumentBean.setId(StringUtil.getIntegerValue(obj,0));

	}

	public String getId(){
		return StringUtil.getStringValue(
		paymentDocumentBean.getId());

	}
	
					public void setDocumentName(String obj){

		paymentDocumentBean.setDocumentName(new String(obj));

	}

	public String getDocumentName(){
		return StringUtil.getStringValue(
		paymentDocumentBean.getDocumentName());

	}
	
					public void setDescription(String obj){

		paymentDocumentBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		paymentDocumentBean.getDescription());

	}
	
				
	public void setTipe(String obj){

		paymentDocumentBean.setTipe(StringUtil.getIntegerValue(obj,0));

	}

	public String getTipe(){
		return StringUtil.getStringValue(
		paymentDocumentBean.getTipe());

	}
	
					public void setDocumentUrl(String obj){

		paymentDocumentBean.setDocumentUrl(new String(obj));

	}

	public String getDocumentUrl(){
		return StringUtil.getStringValue(
		paymentDocumentBean.getDocumentUrl());

	}
	
				
	public void setOrder(String obj){

		paymentDocumentBean.setOrder(StringUtil.getIntegerValue(obj,0));

	}

	public String getOrder(){
		return StringUtil.getStringValue(
		paymentDocumentBean.getOrder());

	}
	
				
	public void setCreatedTime(String obj){

		paymentDocumentBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		paymentDocumentBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		paymentDocumentBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		paymentDocumentBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		paymentDocumentBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		paymentDocumentBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		paymentDocumentBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		paymentDocumentBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		paymentDocumentBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		paymentDocumentBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		paymentDocumentBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		paymentDocumentBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		paymentDocumentBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		paymentDocumentBean.getDeletedStatus());

	}
	
				
	public void setPolicyPaymentId(String obj){

		paymentDocumentBean.setPolicyPaymentId(StringUtil.getIntegerValue(obj,0));

	}

	public String getPolicyPaymentId(){
		return StringUtil.getStringValue(
		paymentDocumentBean.getPolicyPaymentId());

	}
	
				
	public void setFloatingFundId(String obj){

		paymentDocumentBean.setFloatingFundId(StringUtil.getIntegerValue(obj,0));

	}

	public String getFloatingFundId(){
		return StringUtil.getStringValue(
		paymentDocumentBean.getFloatingFundId());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 

}
