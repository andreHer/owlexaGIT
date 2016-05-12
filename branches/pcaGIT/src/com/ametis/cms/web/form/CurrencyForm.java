
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.Currency;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * Currency is a mapping of currency Table.
*/
public class CurrencyForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewCurrency = false;
	private Currency currencyBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public CurrencyForm()
    {
    	this.currencyBean = new Currency();
    	this.isNewCurrency = true;
    }
    public CurrencyForm (Currency object){
		this.currencyBean = object;
    }
    public boolean isNewCurrency (){

    	return this.isNewCurrency;
    }
	public Currency getCurrency (){
		return this.currencyBean ;
	}
	public void setCurrency (Currency object){
		this.currencyBean = object;
	}

			
	public void setCurrencyId(String obj){

		currencyBean.setCurrencyId(StringUtil.getIntegerValue(obj,0));

	}

	public String getCurrencyId(){
		return StringUtil.getStringValue(
		currencyBean.getCurrencyId());

	}
	
					public void setCurrencyCode(String obj){

		currencyBean.setCurrencyCode(new String(obj));

	}

	public String getCurrencyCode(){
		return StringUtil.getStringValue(
		currencyBean.getCurrencyCode());

	}
	
					public void setCurrencyName(String obj){

		currencyBean.setCurrencyName(new String(obj));

	}

	public String getCurrencyName(){
		return StringUtil.getStringValue(
		currencyBean.getCurrencyName());

	}
	
					public void setDescription(String obj){

		currencyBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		currencyBean.getDescription());

	}
	
				
	public void setCreatedTime(String obj){

		currencyBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		currencyBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		currencyBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		currencyBean.getCreatedBy());

	}
	
				
	public void setUpdatedTime(String obj){

		currencyBean.setUpdatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getUpdatedTime(){
		return StringUtil.getStringValue(
		currencyBean.getUpdatedTime());

	}

	
					public void setUpdatedBy(String obj){

		currencyBean.setUpdatedBy(new String(obj));

	}

	public String getUpdatedBy(){
		return StringUtil.getStringValue(
		currencyBean.getUpdatedBy());

	}
	
					public void setDeletedBy(String obj){

		currencyBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		currencyBean.getDeletedBy());

	}
	
				
	public void setDeletedTime(String obj){

		currencyBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		currencyBean.getDeletedTime());

	}

	
				
	public void setDeletedStatus(String obj){

		currencyBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		currencyBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
