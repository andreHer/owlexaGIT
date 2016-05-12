
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * FinanceAccount is a mapping of finance_account Table.
*/
public class FinanceAccountForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewFinanceAccount = false;
	private FinanceAccount financeAccountBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public FinanceAccountForm()
    {
    	this.financeAccountBean = new FinanceAccount();
    	this.isNewFinanceAccount = true;
    }
    public FinanceAccountForm (FinanceAccount object){
		this.financeAccountBean = object;
    }
    public boolean isNewFinanceAccount (){

    	return this.isNewFinanceAccount;
    }
	public FinanceAccount getFinanceAccount (){
		return this.financeAccountBean ;
	}
	public void setFinanceAccount (FinanceAccount object){
		this.financeAccountBean = object;
	}

			
	public void setId(String obj){

		financeAccountBean.setId(StringUtil.getIntegerValue(obj,0));

	}

	public String getId(){
		return StringUtil.getStringValue(
		financeAccountBean.getId());

	}
	
					public void setAccountName(String obj){

		financeAccountBean.setAccountName(new String(obj));

	}

	public String getAccountName(){
		return StringUtil.getStringValue(
		financeAccountBean.getAccountName());

	}
	
					public void setAccountCode(String obj){

		financeAccountBean.setAccountCode(new String(obj));

	}

	public String getAccountCode(){
		return StringUtil.getStringValue(
		financeAccountBean.getAccountCode());

	}
	
					public void setDescription(String obj){

		financeAccountBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		financeAccountBean.getDescription());

	}
	
				
	public void setAccountId(String obj){

		financeAccountBean.setAccountId(StringUtil.getIntegerValue(obj,0));

	}

	public String getAccountId(){
		return StringUtil.getStringValue(
		financeAccountBean.getAccountId());

	}
	
				
	public void setCreatedTime(String obj){

		financeAccountBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		financeAccountBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		financeAccountBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		financeAccountBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		financeAccountBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		financeAccountBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		financeAccountBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		financeAccountBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		financeAccountBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		financeAccountBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		financeAccountBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		financeAccountBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		financeAccountBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		financeAccountBean.getDeletedStatus());

	}
	
				
	public void setTipe(String obj){

		financeAccountBean.setTipe(StringUtil.getIntegerValue(obj,0));

	}

	public String getTipe(){
		return StringUtil.getStringValue(
		financeAccountBean.getTipe());

	}
	
				
	public void setCoaId(String obj){

		financeAccountBean.setCoaId(StringUtil.getIntegerValue(obj,0));

	}

	public String getCoaId(){
		return StringUtil.getStringValue(
		financeAccountBean.getCoaId());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 

}
