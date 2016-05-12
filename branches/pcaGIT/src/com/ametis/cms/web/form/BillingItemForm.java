
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * BillingItem is a mapping of billing_item Table.
*/
public class BillingItemForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewBillingItem = false;
	private BillingItem billingItemBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public BillingItemForm()
    {
    	this.billingItemBean = new BillingItem();
    	this.isNewBillingItem = true;
    }
    public BillingItemForm (BillingItem object){
		this.billingItemBean = object;
    }
    public boolean isNewBillingItem (){

    	return this.isNewBillingItem;
    }
	public BillingItem getBillingItem (){
		return this.billingItemBean ;
	}
	public void setBillingItem (BillingItem object){
		this.billingItemBean = object;
	}

			
	public void setBillingItemId(String obj){

		billingItemBean.setBillingItemId(StringUtil.getIntegerValue(obj,0));

	}

	public String getBillingItemId(){
		return StringUtil.getStringValue(
		billingItemBean.getBillingItemId());

	}
	
				
	public void setInvoiceId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Invoice invoice = new Invoice();
			invoice.setInvoiceId(Integer.valueOf(obj));
			billingItemBean.setInvoiceId(invoice);
		}

	}

	public String getInvoiceId(){
		String result = "";
		
		if (billingItemBean.getInvoiceId() != null){
			result = StringUtil.getStringValue(
					billingItemBean.getInvoiceId().getInvoiceId());
		}
		return result;

	}
	
				
	public void setItemId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Item item = new Item();
			item.setItemId(Integer.valueOf(obj));
			billingItemBean.setItemId(item);
		}
	}

	public String getItemId(){
		String result = "";
		
		if (billingItemBean.getItemId() != null){
			result = StringUtil.getStringValue(
					billingItemBean.getItemId().getItemId());
		}
		return result;

	}
	
				
	public void setBillingValue(String obj){

		billingItemBean.setBillingValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getBillingValue(){
		return StringUtil.getStringValue(
		billingItemBean.getBillingValue());

	}
	
				
	public void setBillingStatus(String obj){

		billingItemBean.setBillingStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getBillingStatus(){
		return StringUtil.getStringValue(
		billingItemBean.getBillingStatus());

	}
	
										
	public void setClaimStatus(String obj){

		billingItemBean.setClaimStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getClaimStatus(){
		return StringUtil.getStringValue(
		billingItemBean.getClaimStatus());

	}
	
					public void setMemberName(String obj){

		billingItemBean.setMemberName(new String(obj));

	}

	public String getMemberName(){
		return StringUtil.getStringValue(
		billingItemBean.getMemberName());

	}
	
					public void setPolicyNumber(String obj){

		billingItemBean.setPolicyNumber(new String(obj));

	}

	public String getPolicyNumber(){
		return StringUtil.getStringValue(
		billingItemBean.getPolicyNumber());

	}
	
					public void setMemberNumber(String obj){

		billingItemBean.setMemberNumber(new String(obj));

	}

	public String getMemberNumber(){
		return StringUtil.getStringValue(
		billingItemBean.getMemberNumber());

	}
	
					public void setClaimNumber(String obj){

		billingItemBean.setClaimNumber(new String(obj));

	}

	public String getClaimNumber(){
		return StringUtil.getStringValue(
		billingItemBean.getClaimNumber());

	}
	
				
	public void setCreatedTime(String obj){

		billingItemBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		billingItemBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		billingItemBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		billingItemBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		billingItemBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		billingItemBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		billingItemBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		billingItemBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		billingItemBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		billingItemBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		billingItemBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		billingItemBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		billingItemBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		billingItemBean.getDeletedStatus());

	}
	
											

	// foreign affairs
	
	

	
	public void setMemberImportId(String obj){
		MemberImport fk = new MemberImport();
		fk.setId(StringUtil.getIntegerValue(obj,0));
		billingItemBean.setMemberImportId(fk);

	}

	public String getMemberImportId(){
		return StringUtil.getStringValue(
		billingItemBean.getMemberImportId().getId());

	}
	//---
	
	

	
	public void setClaimId(String obj){
		Claim fk = new Claim();
		fk.setClaimId(StringUtil.getIntegerValue(obj,0));
		billingItemBean.setClaimId(fk);

	}

	public String getClaimId(){
		return StringUtil.getStringValue(
		billingItemBean.getClaimId().getClaimId());

	}
	//---
	
	

	
	public void setClientId(String obj){
		Client fk = new Client();
		fk.setClientId(StringUtil.getIntegerValue(obj,0));
		billingItemBean.setClientId(fk);

	}

	public String getClientId(){
		return StringUtil.getStringValue(
		billingItemBean.getClientId().getClientId());

	}
	//---
	
	

	
	public void setMemberId(String obj){
		Member fk = new Member();
		fk.setMemberId(StringUtil.getIntegerValue(obj,0));
		billingItemBean.setMemberId(fk);

	}

	public String getMemberId(){
		return StringUtil.getStringValue(
		billingItemBean.getMemberId().getMemberId());

	}
	//---
	
	

	
	public void setPolicyId(String obj){
		Policy fk = new Policy();
		fk.setPolicyId(StringUtil.getIntegerValue(obj,0));
		billingItemBean.setPolicyId(fk);

	}

	public String getPolicyId(){
		return StringUtil.getStringValue(
		billingItemBean.getPolicyId().getPolicyId());

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
