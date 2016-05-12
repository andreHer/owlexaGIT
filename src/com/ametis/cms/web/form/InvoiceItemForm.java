
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.Invoice;
import com.ametis.cms.datamodel.InvoiceItem;
import com.ametis.cms.datamodel.Item;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * InvoiceItem is a mapping of invoice_item Table.
*/
public class InvoiceItemForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewInvoiceItem = false;
	private InvoiceItem invoiceItemBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public InvoiceItemForm()
    {
    	this.invoiceItemBean = new InvoiceItem();
    	this.isNewInvoiceItem = true;
    }
    public InvoiceItemForm (InvoiceItem object){
		this.invoiceItemBean = object;
    }
    public boolean isNewInvoiceItem (){

    	return this.isNewInvoiceItem;
    }
	public InvoiceItem getInvoiceItem (){
		return this.invoiceItemBean ;
	}
	public void setInvoiceItem (InvoiceItem object){
		this.invoiceItemBean = object;
	}

			
	public void setInvoiceItemId(String obj){

		invoiceItemBean.setInvoiceItemId(StringUtil.getIntegerValue(obj,0));

	}

	public String getInvoiceItemId(){
		return StringUtil.getStringValue(
		invoiceItemBean.getInvoiceItemId());

	}
	
										
	public void setInvoiceItemValue(String obj){

		invoiceItemBean.setInvoiceItemValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getInvoiceItemValue(){
		return StringUtil.getStringValue(
		invoiceItemBean.getInvoiceItemValue());

	}
	
				
	public void setItemAmount(String obj){

		invoiceItemBean.setItemAmount(StringUtil.getDoubleValue(obj,0));

	}

	public String getItemAmount(){
		return StringUtil.getStringValue(
		invoiceItemBean.getItemAmount());

	}
	
				
	public void setCreatedTime(String obj){

		invoiceItemBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		invoiceItemBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		invoiceItemBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		invoiceItemBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		invoiceItemBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		invoiceItemBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		invoiceItemBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		invoiceItemBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		invoiceItemBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		invoiceItemBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		invoiceItemBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		invoiceItemBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		invoiceItemBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		invoiceItemBean.getDeletedStatus());

	}
	
		

	// foreign affairs
	
	

	
	public void setItemId(String obj){
		Item fk = new Item();
		fk.setItemId(StringUtil.getIntegerValue(obj,0));
		invoiceItemBean.setItemId(fk);

	}

	public String getItemId(){
		return StringUtil.getStringValue(
		invoiceItemBean.getItemId().getItemId());

	}
	//---
	
	

	
	public void setInvoiceId(String obj){
		Invoice fk = new Invoice();
		fk.setInvoiceId(StringUtil.getIntegerValue(obj,0));
		invoiceItemBean.setInvoiceId(fk);

	}

	public String getInvoiceId(){
		return StringUtil.getStringValue(
		invoiceItemBean.getInvoiceId().getInvoiceId());

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
