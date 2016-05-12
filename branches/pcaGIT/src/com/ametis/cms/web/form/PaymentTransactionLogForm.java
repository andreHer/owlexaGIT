
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * PaymentTransactionLog is a mapping of payment_transaction_log Table.
*/
public class PaymentTransactionLogForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewPaymentTransactionLog = false;
	private PaymentTransactionLog paymentTransactionLogBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public PaymentTransactionLogForm()
    {
    	this.paymentTransactionLogBean = new PaymentTransactionLog();
    	this.isNewPaymentTransactionLog = true;
    }
    public PaymentTransactionLogForm (PaymentTransactionLog object){
		this.paymentTransactionLogBean = object;
    }
    public boolean isNewPaymentTransactionLog (){

    	return this.isNewPaymentTransactionLog;
    }
	public PaymentTransactionLog getPaymentTransactionLog (){
		return this.paymentTransactionLogBean ;
	}
	public void setPaymentTransactionLog (PaymentTransactionLog object){
		this.paymentTransactionLogBean = object;
	}

			
	public void setIdProses(String obj){

		paymentTransactionLogBean.setIdProses(StringUtil.getIntegerValue(obj,0));

	}

	public String getIdProses(){
		return StringUtil.getStringValue(
		paymentTransactionLogBean.getIdProses());

	}
	
					public void setClaimNumber(String obj){

		paymentTransactionLogBean.setClaimNumber(new String(obj));

	}

	public String getClaimNumber(){
		return StringUtil.getStringValue(
		paymentTransactionLogBean.getClaimNumber());

	}
	
					public void setNoVoucherNumber(String obj){

		paymentTransactionLogBean.setNoVoucherNumber(new String(obj));

	}

	public String getNoVoucherNumber(){
		return StringUtil.getStringValue(
		paymentTransactionLogBean.getNoVoucherNumber());

	}
	
				
	public void setPaymentDate(String obj){

		paymentTransactionLogBean.setPaymentDate(java.sql.Timestamp.valueOf(obj));

	}

	public String getPaymentDate(){
		return StringUtil.getStringValue(
		paymentTransactionLogBean.getPaymentDate());

	}

	
					public void setCurrency(String obj){

		paymentTransactionLogBean.setCurrency(new String(obj));

	}

	public String getCurrency(){
		return StringUtil.getStringValue(
		paymentTransactionLogBean.getCurrency());

	}
	
				
	public void setAmountPaid(String obj){

		paymentTransactionLogBean.setAmountPaid(StringUtil.getDoubleValue(obj,0));

	}

	public String getAmountPaid(){
		return StringUtil.getStringValue(
		paymentTransactionLogBean.getAmountPaid());

	}
	
					public void setBankName(String obj){

		paymentTransactionLogBean.setBankName(new String(obj));

	}

	public String getBankName(){
		return StringUtil.getStringValue(
		paymentTransactionLogBean.getBankName());

	}
	
					public void setAccountName(String obj){

		paymentTransactionLogBean.setAccountName(new String(obj));

	}

	public String getAccountName(){
		return StringUtil.getStringValue(
		paymentTransactionLogBean.getAccountName());

	}
	
				
	public void setBankCode(String obj){

		paymentTransactionLogBean.setBankCode(StringUtil.getIntegerValue(obj,0));

	}

	public String getBankCode(){
		return StringUtil.getStringValue(
		paymentTransactionLogBean.getBankCode());

	}
	
				
	public void setAccountNumber(String obj){

		paymentTransactionLogBean.setAccountNumber(StringUtil.getIntegerValue(obj,0));

	}

	public String getAccountNumber(){
		return StringUtil.getStringValue(
		paymentTransactionLogBean.getAccountNumber());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
