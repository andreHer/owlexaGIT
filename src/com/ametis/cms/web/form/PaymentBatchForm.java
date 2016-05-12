
package com.ametis.cms.web.form;

import java.util.Collection;
import java.util.Vector;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * PaymentBatch is a mapping of payment_batch Table.
*/
public class PaymentBatchForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewPaymentBatch = false;
	private PaymentBatch paymentBatchBean ;
	private String[] paymentList;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public PaymentBatchForm()
    {
    	this.paymentBatchBean = new PaymentBatch();
    	this.isNewPaymentBatch = true;
    }
    public PaymentBatchForm (PaymentBatch object){
		this.paymentBatchBean = object;
    }
    public boolean isNewPaymentBatch (){

    	return this.isNewPaymentBatch;
    }
	public PaymentBatch getPaymentBatch (){
		return this.paymentBatchBean ;
	}
	public void setPaymentBatch (PaymentBatch object){
		this.paymentBatchBean = object;
	}

			
	public void setId(String obj){

		paymentBatchBean.setId(StringUtil.getIntegerValue(obj,0));

	}

	public String getId(){
		return StringUtil.getStringValue(
		paymentBatchBean.getId());

	}
	
					public void setPaymentBatchNumber(String obj){

		paymentBatchBean.setPaymentBatchNumber(new String(obj));

	}

	public String getPaymentBatchNumber(){
		return StringUtil.getStringValue(
		paymentBatchBean.getPaymentBatchNumber());

	}
	
				
	public void setPaymentBatchDate(String obj){

		paymentBatchBean.setPaymentBatchDate(java.sql.Date.valueOf(obj));

	}

	public String getPaymentBatchDate(){
		return StringUtil.getStringValue(
		paymentBatchBean.getPaymentBatchDate());

	}

	
				
	public void setPaymentBatchConfirmDate(String obj){

		paymentBatchBean.setPaymentBatchConfirmDate(java.sql.Date.valueOf(obj));

	}

	public String getPaymentBatchConfirmDate(){
		return StringUtil.getStringValue(
		paymentBatchBean.getPaymentBatchConfirmDate());

	}

	
				
	public void setOutstandingPayment(String obj){

		paymentBatchBean.setOutstandingPayment(StringUtil.getDoubleValue(obj,0));

	}

	public String getOutstandingPayment(){
		return StringUtil.getStringValue(
		paymentBatchBean.getOutstandingPayment());

	}
	
				
	public void setPaidPayment(String obj){

		paymentBatchBean.setPaidPayment(StringUtil.getDoubleValue(obj,0));

	}

	public String getPaidPayment(){
		return StringUtil.getStringValue(
		paymentBatchBean.getPaidPayment());

	}
	
				
	public void setTotalPayment(String obj){

		paymentBatchBean.setTotalPayment(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalPayment(){
		return StringUtil.getStringValue(
		paymentBatchBean.getTotalPayment());

	}
	
				
	public void setCreatedTime(String obj){

		paymentBatchBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		paymentBatchBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		paymentBatchBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		paymentBatchBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		paymentBatchBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		paymentBatchBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		paymentBatchBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		paymentBatchBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		paymentBatchBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		paymentBatchBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		paymentBatchBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		paymentBatchBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		paymentBatchBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		paymentBatchBean.getDeletedStatus());

	}
	
				
	public void setBatchStatus(String obj){

		paymentBatchBean.setBatchStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getBatchStatus(){
		return StringUtil.getStringValue(
		paymentBatchBean.getBatchStatus());

	}
	public String getPaymentAccountSource(){
		String result = "";
		if (paymentBatchBean.getPaymentAccountSource() != null){
			result = paymentBatchBean.getPaymentAccountSource().getId().toString();
		}
		return result;
	}
	public void setPaymentAccountSource (String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			
			BankAccount account = new BankAccount();
			account.setId(Integer.valueOf(obj));
			paymentBatchBean.setPaymentAccountSource(account);
		}
	}
	public String[] getPaymentList() {
		return paymentList;
	}
	public void setPaymentList(String[] paymentList) {
		this.paymentList = paymentList;
	}
	public Collection<Integer> getPaymentListCollection(){
		Collection<Integer> result = new Vector<Integer>();
		
		if (paymentList != null){
			for (int i = 0; i < paymentList.length; i++){
				result.add(Integer.valueOf(paymentList[i]));
			}
		}
		
		return result;
	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
