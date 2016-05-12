
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * ExchangeRate is a mapping of exchange_rate Table.
*/
public class ExchangeRateForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewExchangeRate = false;
	private ExchangeRate exchangeRateBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ExchangeRateForm()
    {
    	this.exchangeRateBean = new ExchangeRate();
    	this.isNewExchangeRate = true;
    }
    public ExchangeRateForm (ExchangeRate object){
		this.exchangeRateBean = object;
    }
    public boolean isNewExchangeRate (){

    	return this.isNewExchangeRate;
    }
	public ExchangeRate getExchangeRate (){
		return this.exchangeRateBean ;
	}
	public void setExchangeRate (ExchangeRate object){
		this.exchangeRateBean = object;
	}

			
	public void setExchangeRateId(String obj){

		exchangeRateBean.setExchangeRateId(StringUtil.getIntegerValue(obj,0));

	}

	public String getExchangeRateId(){
		return StringUtil.getStringValue(
		exchangeRateBean.getExchangeRateId());

	}
	
					public void setCurrencyExchangeName(String obj){

		exchangeRateBean.setCurrencyExchangeName(new String(obj));

	}

	public String getCurrencyExchangeName(){
		return StringUtil.getStringValue(
		exchangeRateBean.getCurrencyExchangeName());

	}
	
				
	public void setExchangeRateDate(String obj){

		exchangeRateBean.setExchangeRateDate(java.sql.Date.valueOf(obj));

	}

	public String getExchangeRateDate(){
		return StringUtil.getStringValue(
		exchangeRateBean.getExchangeRateDate());

	}

	
				
	public void setFirstCurrencyId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Currency curr = new Currency();
			curr.setCurrencyId(Integer.valueOf(obj));
			exchangeRateBean.setFirstCurrencyId(curr);
		}

	}

	public String getFirstCurrencyId(){
		String result = "";
		
		if (exchangeRateBean.getFirstCurrencyId() != null){
			result = StringUtil.getStringValue(
					exchangeRateBean.getFirstCurrencyId().getCurrencyId());
		}
		return result;
		

	}
	
				
	public void setSecondCurrencyId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Currency curr = new Currency();
			curr.setCurrencyId(Integer.valueOf(obj));
			exchangeRateBean.setSecondCurrencyId(curr);
		}

	}

	public String getSecondCurrencyId(){
		String result = "";
		
		if (exchangeRateBean.getFirstCurrencyId() != null){
			result = StringUtil.getStringValue(
					exchangeRateBean.getSecondCurrencyId().getCurrencyId());
		}
		return result;

	}
	
				
	public void setRateFirstToSecond(String obj){

		exchangeRateBean.setRateFirstToSecond(StringUtil.getDoubleValue(obj,0));

	}

	public String getRateFirstToSecond(){
		return StringUtil.getStringValue(
		exchangeRateBean.getRateFirstToSecond());

	}
	
				
	public void setRateSecondToFirst(String obj){

		exchangeRateBean.setRateSecondToFirst(StringUtil.getDoubleValue(obj,0));

	}

	public String getRateSecondToFirst(){
		return StringUtil.getStringValue(
		exchangeRateBean.getRateSecondToFirst());

	}
	
					public void setDescription(String obj){

		exchangeRateBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		exchangeRateBean.getDescription());

	}
	
				
	public void setCreatedTime(String obj){

		exchangeRateBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		exchangeRateBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		exchangeRateBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		exchangeRateBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		exchangeRateBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		exchangeRateBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		exchangeRateBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		exchangeRateBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		exchangeRateBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		exchangeRateBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		exchangeRateBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		exchangeRateBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		exchangeRateBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		exchangeRateBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
