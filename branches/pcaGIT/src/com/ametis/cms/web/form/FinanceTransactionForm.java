
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * FinanceTransaction is a mapping of finance_transaction Table.
*/
public class FinanceTransactionForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewFinanceTransaction = false;
	private FinanceTransaction financeTransactionBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public FinanceTransactionForm()
    {
    	this.financeTransactionBean = new FinanceTransaction();
    	this.isNewFinanceTransaction = true;
    }
    public FinanceTransactionForm (FinanceTransaction object){
		this.financeTransactionBean = object;
    }
    public boolean isNewFinanceTransaction (){

    	return this.isNewFinanceTransaction;
    }
	public FinanceTransaction getFinanceTransaction (){
		return this.financeTransactionBean ;
	}
	public void setFinanceTransaction (FinanceTransaction object){
		this.financeTransactionBean = object;
	}

			
	public void setId(String obj){

		financeTransactionBean.setId(StringUtil.getIntegerValue(obj,0));

	}

	public String getId(){
		return StringUtil.getStringValue(
		financeTransactionBean.getId());

	}
	
					public void setTransactionNumber(String obj){

		financeTransactionBean.setTransactionNumber(new String(obj));

	}

	public String getTransactionNumber(){
		return StringUtil.getStringValue(
		financeTransactionBean.getTransactionNumber());

	}
	
					public void setAccountCode(String obj){

		financeTransactionBean.setAccountCode(new String(obj));

	}

	public String getAccountCode(){
		return StringUtil.getStringValue(
		financeTransactionBean.getAccountCode());

	}
	
					public void setAccountName(String obj){

		financeTransactionBean.setAccountName(new String(obj));

	}

	public String getAccountName(){
		return StringUtil.getStringValue(
		financeTransactionBean.getAccountName());

	}
	
					public void setDescription(String obj){

		financeTransactionBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		financeTransactionBean.getDescription());

	}
	
				
	public void setValue(String obj){

		financeTransactionBean.setValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getValue(){
		return StringUtil.getStringValue(
		financeTransactionBean.getValue());

	}
	
				
	public void setDebetValue(String obj){

		financeTransactionBean.setDebetValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getDebetValue(){
		return StringUtil.getStringValue(
		financeTransactionBean.getDebetValue());

	}
	
				
	public void setCreditValue(String obj){

		financeTransactionBean.setCreditValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getCreditValue(){
		return StringUtil.getStringValue(
		financeTransactionBean.getCreditValue());

	}
	
				
	public void setAccountId(String obj){

		financeTransactionBean.setAccountId(StringUtil.getIntegerValue(obj,0));

	}

	public String getAccountId(){
		return StringUtil.getStringValue(
		financeTransactionBean.getAccountId());

	}
	
					public void setReferenceCode(String obj){

		financeTransactionBean.setReferenceCode(new String(obj));

	}

	public String getReferenceCode(){
		return StringUtil.getStringValue(
		financeTransactionBean.getReferenceCode());

	}
	
				
	public void setCreatedTime(String obj){

		financeTransactionBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		financeTransactionBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		financeTransactionBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		financeTransactionBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		financeTransactionBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		financeTransactionBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		financeTransactionBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		financeTransactionBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		financeTransactionBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		financeTransactionBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		financeTransactionBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		financeTransactionBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		financeTransactionBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		financeTransactionBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 

}
