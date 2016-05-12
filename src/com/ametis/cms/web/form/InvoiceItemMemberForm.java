
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * InvoiceItemMember is a mapping of invoice_item_member Table.
*/
public class InvoiceItemMemberForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewInvoiceItemMember = false;
	private InvoiceItemMember invoiceItemMemberBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public InvoiceItemMemberForm()
    {
    	this.invoiceItemMemberBean = new InvoiceItemMember();
    	this.isNewInvoiceItemMember = true;
    }
    public InvoiceItemMemberForm (InvoiceItemMember object){
		this.invoiceItemMemberBean = object;
    }
    public boolean isNewInvoiceItemMember (){

    	return this.isNewInvoiceItemMember;
    }
	public InvoiceItemMember getInvoiceItemMember (){
		return this.invoiceItemMemberBean ;
	}
	public void setInvoiceItemMember (InvoiceItemMember object){
		this.invoiceItemMemberBean = object;
	}

			
	public void setInvoiceItemMemberId(String obj){

		invoiceItemMemberBean.setInvoiceItemMemberId(StringUtil.getIntegerValue(obj,0));

	}

	public String getInvoiceItemMemberId(){
		return StringUtil.getStringValue(
		invoiceItemMemberBean.getInvoiceItemMemberId());

	}
	
				
	public void setBillingRate(String obj){

		invoiceItemMemberBean.setBillingRate(StringUtil.getDoubleValue(obj,0));

	}

	public String getBillingRate(){
		return StringUtil.getStringValue(
		invoiceItemMemberBean.getBillingRate());

	}
	
				
	public void setMembershipPeriode(String obj){

		invoiceItemMemberBean.setMembershipPeriode(StringUtil.getIntegerValue(obj,0));

	}

	public String getMembershipPeriode(){
		return StringUtil.getStringValue(
		invoiceItemMemberBean.getMembershipPeriode());

	}
	
				
	public void setBillingMonth(String obj){

		invoiceItemMemberBean.setBillingMonth(StringUtil.getIntegerValue(obj,0));

	}

	public String getBillingMonth(){
		return StringUtil.getStringValue(
		invoiceItemMemberBean.getBillingMonth());

	}
	
				
	public void setBillingYear(String obj){

		invoiceItemMemberBean.setBillingYear(StringUtil.getIntegerValue(obj,0));

	}

	public String getBillingYear(){
		return StringUtil.getStringValue(
		invoiceItemMemberBean.getBillingYear());

	}
	
				
	public void setRefundRate(String obj){

		invoiceItemMemberBean.setRefundRate(StringUtil.getDoubleValue(obj,0));

	}

	public String getRefundRate(){
		return StringUtil.getStringValue(
		invoiceItemMemberBean.getRefundRate());

	}
	
				
	public void setRefundDate(String obj){

		invoiceItemMemberBean.setRefundDate(java.sql.Date.valueOf(obj));

	}

	public String getRefundDate(){
		return StringUtil.getStringValue(
		invoiceItemMemberBean.getRefundDate());

	}

	
				
	public void setDeletedStatus(String obj){

		invoiceItemMemberBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		invoiceItemMemberBean.getDeletedStatus());

	}
	
					public void setCreatedBy(String obj){

		invoiceItemMemberBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		invoiceItemMemberBean.getCreatedBy());

	}
	
				
	public void setCreatedTime(String obj){

		invoiceItemMemberBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		invoiceItemMemberBean.getCreatedTime());

	}

	
					public void setDeletedBy(String obj){

		invoiceItemMemberBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		invoiceItemMemberBean.getDeletedBy());

	}
	
				
	public void setDeletedTime(String obj){

		invoiceItemMemberBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		invoiceItemMemberBean.getDeletedTime());

	}

	
					public void setModifiedBy(String obj){

		invoiceItemMemberBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		invoiceItemMemberBean.getModifiedBy());

	}
	
				
	public void setModifiedTime(String obj){

		invoiceItemMemberBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		invoiceItemMemberBean.getModifiedTime());

	}

	
				
	public void setStatus(String obj){

		invoiceItemMemberBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		invoiceItemMemberBean.getStatus());

	}
	
								

	// foreign affairs
	
	

	
	public void setInvoiceItemId(String obj){
		InvoiceItem fk = new InvoiceItem();
		fk.setInvoiceItemId(StringUtil.getIntegerValue(obj,0));
		invoiceItemMemberBean.setInvoiceItemId(fk);

	}

	public String getInvoiceItemId(){
		return StringUtil.getStringValue(
		invoiceItemMemberBean.getInvoiceItemId().getInvoiceItemId());

	}
	//---
	
	

	
	public void setMemberId(String obj){
		Member fk = new Member();
		fk.setMemberId(StringUtil.getIntegerValue(obj,0));
		invoiceItemMemberBean.setMemberId(fk);

	}

	public String getMemberId(){
		return StringUtil.getStringValue(
		invoiceItemMemberBean.getMemberId().getMemberId());

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
