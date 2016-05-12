
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * PaymentInstallment is a mapping of payment_installment Table.
*/
public class PaymentInstallmentForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewPaymentInstallment = false;
	private PaymentInstallment paymentInstallmentBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public PaymentInstallmentForm()
    {
    	this.paymentInstallmentBean = new PaymentInstallment();
    	this.isNewPaymentInstallment = true;
    }
    public PaymentInstallmentForm (PaymentInstallment object){
		this.paymentInstallmentBean = object;
    }
    public boolean isNewPaymentInstallment (){

    	return this.isNewPaymentInstallment;
    }
	public PaymentInstallment getPaymentInstallment (){
		return this.paymentInstallmentBean ;
	}
	public void setPaymentInstallment (PaymentInstallment object){
		this.paymentInstallmentBean = object;
	}

			
	public void setPaymentInstallmentId(String obj){

		paymentInstallmentBean.setPaymentInstallmentId(StringUtil.getLongValue(obj,0));

	}

	public String getPaymentInstallmentId(){
		return StringUtil.getStringValue(
		paymentInstallmentBean.getPaymentInstallmentId());

	}
	
				
	public void setPaymentId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Payment payment = new Payment();
			payment.setPaymentId(Integer.valueOf(obj));
			paymentInstallmentBean.setPaymentId(payment);
		}

	}

	public String getPaymentId(){
		return StringUtil.getStringValue(
		paymentInstallmentBean.getPaymentId());

	}
	
					public void setInstallmentNumber(String obj){

		paymentInstallmentBean.setInstallmentNumber(new String(obj));

	}

	public String getInstallmentNumber(){
		return StringUtil.getStringValue(
		paymentInstallmentBean.getInstallmentNumber());

	}
	
					public void setDescription(String obj){

		paymentInstallmentBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		paymentInstallmentBean.getDescription());

	}
	
				
	public void setTotalValue(String obj){

		paymentInstallmentBean.setTotalValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalValue(){
		return StringUtil.getStringValue(
		paymentInstallmentBean.getTotalValue());

	}
	
				
	public void setTotalClaim(String obj){

		paymentInstallmentBean.setTotalClaim(StringUtil.getIntegerValue(obj,0));

	}

	public String getTotalClaim(){
		return StringUtil.getStringValue(
		paymentInstallmentBean.getTotalClaim());

	}
	
				
	public void setStatus(String obj){

		paymentInstallmentBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		paymentInstallmentBean.getStatus());

	}
	
				
	public void setCreatedTime(String obj){

		paymentInstallmentBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		paymentInstallmentBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		paymentInstallmentBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		paymentInstallmentBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		paymentInstallmentBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		paymentInstallmentBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		paymentInstallmentBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		paymentInstallmentBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		paymentInstallmentBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		paymentInstallmentBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		paymentInstallmentBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		paymentInstallmentBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		paymentInstallmentBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		paymentInstallmentBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 

}
