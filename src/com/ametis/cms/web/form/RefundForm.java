
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * Refund is a mapping of refund Table.
*/
public class RefundForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewRefund = false;
	private Refund refundBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public RefundForm()
    {
    	this.refundBean = new Refund();
    	this.isNewRefund = true;
    }
    public RefundForm (Refund object){
		this.refundBean = object;
    }
    public boolean isNewRefund (){

    	return this.isNewRefund;
    }
	public Refund getRefund (){
		return this.refundBean ;
	}
	public void setRefund (Refund object){
		this.refundBean = object;
	}

			
	public void setRefundId(String obj){

		refundBean.setRefundId(StringUtil.getIntegerValue(obj,0));

	}

	public String getRefundId(){
		return StringUtil.getStringValue(
		refundBean.getRefundId());

	}
	
					public void setRefundNumber(String obj){

		refundBean.setRefundNumber(new String(obj));

	}

	public String getRefundNumber(){
		return StringUtil.getStringValue(
		refundBean.getRefundNumber());

	}
	
				
	public void setTotalRefund(String obj){

		refundBean.setTotalRefund(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalRefund(){
		return StringUtil.getStringValue(
		refundBean.getTotalRefund());

	}
	
				
	public void setPaidRefund(String obj){

		refundBean.setPaidRefund(StringUtil.getDoubleValue(obj,0));

	}

	public String getPaidRefund(){
		return StringUtil.getStringValue(
		refundBean.getPaidRefund());

	}
	
				
	public void setRefundDate(String obj){

		refundBean.setRefundDate(java.sql.Date.valueOf(obj));

	}

	public String getRefundDate(){
		return StringUtil.getStringValue(
		refundBean.getRefundDate());

	}

	
				
	public void setDeletedStatus(String obj){

		refundBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		refundBean.getDeletedStatus());

	}
	
				
	public void setOutstanding(String obj){

		refundBean.setOutstanding(StringUtil.getDoubleValue(obj,0));

	}

	public String getOutstanding(){
		return StringUtil.getStringValue(
		refundBean.getOutstanding());

	}
	
					public void setCreatedBy(String obj){

		refundBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		refundBean.getCreatedBy());

	}
	
				
	public void setCreatedTime(String obj){

		refundBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		refundBean.getCreatedTime());

	}

	
					public void setDeletedBy(String obj){

		refundBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		refundBean.getDeletedBy());

	}
	
				
	public void setDeletedTime(String obj){

		refundBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		refundBean.getDeletedTime());

	}

	
					public void setModifiedBy(String obj){

		refundBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		refundBean.getModifiedBy());

	}
	
				
	public void setModifiedTime(String obj){

		refundBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		refundBean.getModifiedTime());

	}

	
					public void setDescription(String obj){

		refundBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		refundBean.getDescription());

	}
	
				
	public void setStatus(String obj){

		refundBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		refundBean.getStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
