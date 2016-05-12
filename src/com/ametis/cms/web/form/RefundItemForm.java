
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * RefundItem is a mapping of refund_item Table.
*/
public class RefundItemForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewRefundItem = false;
	private RefundItem refundItemBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public RefundItemForm()
    {
    	this.refundItemBean = new RefundItem();
    	this.isNewRefundItem = true;
    }
    public RefundItemForm (RefundItem object){
		this.refundItemBean = object;
    }
    public boolean isNewRefundItem (){

    	return this.isNewRefundItem;
    }
	public RefundItem getRefundItem (){
		return this.refundItemBean ;
	}
	public void setRefundItem (RefundItem object){
		this.refundItemBean = object;
	}

			
	public void setRefundItemId(String obj){

		refundItemBean.setRefundItemId(StringUtil.getIntegerValue(obj,0));

	}

	public String getRefundItemId(){
		return StringUtil.getStringValue(
		refundItemBean.getRefundItemId());

	}
	
				
	public void setOutstandingValue(String obj){

		refundItemBean.setOutstandingValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getOutstandingValue(){
		return StringUtil.getStringValue(
		refundItemBean.getOutstandingValue());

	}
	
				
	public void setPaidRefund(String obj){

		refundItemBean.setPaidRefund(StringUtil.getDoubleValue(obj,0));

	}

	public String getPaidRefund(){
		return StringUtil.getStringValue(
		refundItemBean.getPaidRefund());

	}
	
				
	public void setRefundValue(String obj){

		refundItemBean.setRefundValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getRefundValue(){
		return StringUtil.getStringValue(
		refundItemBean.getRefundValue());

	}
	
				
	public void setDeletedStatus(String obj){

		refundItemBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		refundItemBean.getDeletedStatus());

	}
	
					public void setCreatedBy(String obj){

		refundItemBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		refundItemBean.getCreatedBy());

	}
	
				
	public void setCreatedTime(String obj){

		refundItemBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		refundItemBean.getCreatedTime());

	}

	
					public void setDeletedBy(String obj){

		refundItemBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		refundItemBean.getDeletedBy());

	}
	
				
	public void setDeletedTime(String obj){

		refundItemBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		refundItemBean.getDeletedTime());

	}

	
					public void setModifiedBy(String obj){

		refundItemBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		refundItemBean.getModifiedBy());

	}
	
				
	public void setModifiedTime(String obj){

		refundItemBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		refundItemBean.getModifiedTime());

	}

	
				
	public void setStatus(String obj){

		refundItemBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		refundItemBean.getStatus());

	}
	
											

	// foreign affairs
	
	

	
	public void setMemberImportId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
		MemberImport fk = new MemberImport();
		fk.setId(StringUtil.getIntegerValue(obj,0));
		refundItemBean.setMemberImport(fk);
		}

	}

	public String getMemberImportId(){
		String result = "";
		
		if (refundItemBean.getMemberImport() != null){
			result= StringUtil.getStringValue(
					refundItemBean.getMemberImport().getId());
		}
		
		return result;

	}
	//---
	
	

	
	public void setRefundId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Refund fk = new Refund();
			fk.setRefundId(StringUtil.getIntegerValue(obj,0));
			refundItemBean.setRefundId(fk);
		}

	}

	public String getRefundId(){
		String result = "";
		
		if (refundItemBean.getRefundId() != null){
			result = StringUtil.getStringValue(
					refundItemBean.getRefundId().getRefundId());
		}
		return result;

	}
	//---
	
	

	
	public void setMemberId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Member fk = new Member();
			fk.setMemberId(StringUtil.getIntegerValue(obj,0));
			refundItemBean.setMemberId(fk);
		}

	}

	public String getMemberId(){
		String result = "";
		
		if (refundItemBean.getMemberId() != null){
			result = StringUtil.getStringValue(
					refundItemBean.getMemberId().getMemberId());
		}
		
		return result;

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
