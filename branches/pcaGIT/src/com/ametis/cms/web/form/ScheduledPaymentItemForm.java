
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * ScheduledPaymentItem is a mapping of scheduled_payment_item Table.
*/
public class ScheduledPaymentItemForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewScheduledPaymentItem = false;
	private ScheduledPaymentItem scheduledPaymentItemBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ScheduledPaymentItemForm()
    {
    	this.scheduledPaymentItemBean = new ScheduledPaymentItem();
    	this.isNewScheduledPaymentItem = true;
    }
    public ScheduledPaymentItemForm (ScheduledPaymentItem object){
		this.scheduledPaymentItemBean = object;
    }
    public boolean isNewScheduledPaymentItem (){

    	return this.isNewScheduledPaymentItem;
    }
	public ScheduledPaymentItem getScheduledPaymentItem (){
		return this.scheduledPaymentItemBean ;
	}
	public void setScheduledPaymentItem (ScheduledPaymentItem object){
		this.scheduledPaymentItemBean = object;
	}

			
	public void setId(String obj){

		scheduledPaymentItemBean.setId(StringUtil.getIntegerValue(obj,0));

	}

	public String getId(){
		return StringUtil.getStringValue(
		scheduledPaymentItemBean.getId());

	}
	
				
	public void setScheduledPaymentId(String obj){

		scheduledPaymentItemBean.setScheduledPaymentId(StringUtil.getIntegerValue(obj,0));

	}

	public String getScheduledPaymentId(){
		return StringUtil.getStringValue(
		scheduledPaymentItemBean.getScheduledPaymentId());

	}
	
				
	public void setItemId(String obj){

		scheduledPaymentItemBean.setItemId(StringUtil.getLongValue(obj,0));

	}

	public String getItemId(){
		return StringUtil.getStringValue(
		scheduledPaymentItemBean.getItemId());

	}
	
				
	public void setItemValue(String obj){

		scheduledPaymentItemBean.setItemValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getItemValue(){
		return StringUtil.getStringValue(
		scheduledPaymentItemBean.getItemValue());

	}
	
				
	public void setTotalItem(String obj){

		scheduledPaymentItemBean.setTotalItem(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalItem(){
		return StringUtil.getStringValue(
		scheduledPaymentItemBean.getTotalItem());

	}
	
				
	public void setTotalValue(String obj){

		scheduledPaymentItemBean.setTotalValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalValue(){
		return StringUtil.getStringValue(
		scheduledPaymentItemBean.getTotalValue());

	}
	
				
	public void setTax(String obj){

		scheduledPaymentItemBean.setTax(StringUtil.getDoubleValue(obj,0));

	}

	public String getTax(){
		return StringUtil.getStringValue(
		scheduledPaymentItemBean.getTax());

	}
	
				
	public void setTotalValeAfterTax(String obj){

		scheduledPaymentItemBean.setTotalValeAfterTax(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalValeAfterTax(){
		return StringUtil.getStringValue(
		scheduledPaymentItemBean.getTotalValeAfterTax());

	}
	
				
	public void setStatus(String obj){

		scheduledPaymentItemBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		scheduledPaymentItemBean.getStatus());

	}
	
				
	public void setTaxTypeId(String obj){

		scheduledPaymentItemBean.setTaxTypeId(StringUtil.getIntegerValue(obj,0));

	}

	public String getTaxTypeId(){
		return StringUtil.getStringValue(
		scheduledPaymentItemBean.getTaxTypeId());

	}
	
				
	public void setTaxPercentage(String obj){

		scheduledPaymentItemBean.setTaxPercentage(StringUtil.getDoubleValue(obj,0));

	}

	public String getTaxPercentage(){
		return StringUtil.getStringValue(
		scheduledPaymentItemBean.getTaxPercentage());

	}
	
				
	public void setCreatedTime(String obj){

		scheduledPaymentItemBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		scheduledPaymentItemBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		scheduledPaymentItemBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		scheduledPaymentItemBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		scheduledPaymentItemBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		scheduledPaymentItemBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		scheduledPaymentItemBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		scheduledPaymentItemBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		scheduledPaymentItemBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		scheduledPaymentItemBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		scheduledPaymentItemBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		scheduledPaymentItemBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		scheduledPaymentItemBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		scheduledPaymentItemBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 

}
