
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.Invoice;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.PaymentStatus;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * Invoice is a mapping of invoice Table.
*/
public class InvoiceForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewInvoice = false;
	private Invoice invoiceBean ;
	private String memberGroupName;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public InvoiceForm()
    {
    	this.invoiceBean = new Invoice();
    	this.isNewInvoice = true;
    }
    public InvoiceForm (Invoice object){
		this.invoiceBean = object;
    }
    public boolean isNewInvoice (){

    	return this.isNewInvoice;
    }
	public Invoice getInvoice (){
		return this.invoiceBean ;
	}
	public void setInvoice (Invoice object){
		this.invoiceBean = object;
	}

			
	public void setInvoiceId(String obj){

		invoiceBean.setInvoiceId(StringUtil.getIntegerValue(obj,0));

	}

	public String getInvoiceId(){
		return StringUtil.getStringValue(
		invoiceBean.getInvoiceId());

	}
	
				
	public void setInvoiceDate(String obj){

		invoiceBean.setInvoiceDate(java.sql.Date.valueOf(obj));

	}

	public String getInvoiceDate(){
		return StringUtil.getStringValue(
		invoiceBean.getInvoiceDate());

	}

	
				
	public void setInvoiceDueDate(String obj){

		invoiceBean.setInvoiceDueDate(java.sql.Date.valueOf(obj));

	}

	public String getInvoiceDueDate(){
		return StringUtil.getStringValue(
		invoiceBean.getInvoiceDueDate());

	}

	
				
	public void setInvoiceValue(String obj){

		invoiceBean.setInvoiceValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getInvoiceValue(){
		return StringUtil.getStringValue(
		invoiceBean.getInvoiceValue());

	}
	
				
	public void setInvoiceStatus(String obj){

		
		PaymentStatus status  = new PaymentStatus();
		status.setPaymentStatusId(StringUtil.getIntegerValue(obj,0));
		invoiceBean.setInvoiceStatus(status);

	}

	public String getInvoiceStatus(){
		return StringUtil.getStringValue(
		invoiceBean.getInvoiceStatus());

	}
	
				
	public void setApprovalTime(String obj){

		invoiceBean.setApprovalTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getApprovalTime(){
		return StringUtil.getStringValue(
		invoiceBean.getApprovalTime());

	}

	
				
	public void setApprovedBy(String obj){

		invoiceBean.setApprovedBy(StringUtil.getIntegerValue(obj,0));

	}

	public String getApprovedBy(){
		return StringUtil.getStringValue(
		invoiceBean.getApprovedBy());

	}
	
					public void setDescription(String obj){

		invoiceBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		invoiceBean.getDescription());

	}
	
				
	public void setCreatedTime(String obj){

		invoiceBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		invoiceBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		invoiceBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		invoiceBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		invoiceBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		invoiceBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		invoiceBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		invoiceBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		invoiceBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		invoiceBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		invoiceBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		invoiceBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		invoiceBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		invoiceBean.getDeletedStatus());

	}
	public void setMemberGroupId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			
			MemberGroup group = new MemberGroup();
			group.setMemberGroupId(Integer.valueOf(obj));
			invoiceBean.setMemberGroupId(group);
			
		}

	}

	public String getMemberGroupId(){
		
		String result = "";
		if (invoiceBean.getMemberGroupId() != null){
			result = StringUtil.getStringValue(
					invoiceBean.getMemberGroupId()); 
		}
		return result;
		

	}
	public String getMemberGroupName() {
		return memberGroupName;
	}
	public void setMemberGroupName(String memberGroupName) {
		this.memberGroupName = memberGroupName;
	}
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
