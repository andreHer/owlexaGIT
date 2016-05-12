
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * ExternalPaymentData is a mapping of external_payment_data Table.
*/
public class ExternalPaymentDataForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewExternalPaymentData = false;
	private ExternalPaymentData externalPaymentDataBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ExternalPaymentDataForm()
    {
    	this.externalPaymentDataBean = new ExternalPaymentData();
    	this.isNewExternalPaymentData = true;
    }
    public ExternalPaymentDataForm (ExternalPaymentData object){
		this.externalPaymentDataBean = object;
    }
    public boolean isNewExternalPaymentData (){

    	return this.isNewExternalPaymentData;
    }
	public ExternalPaymentData getExternalPaymentData (){
		return this.externalPaymentDataBean ;
	}
	public void setExternalPaymentData (ExternalPaymentData object){
		this.externalPaymentDataBean = object;
	}

			
	public void setId(String obj){

		externalPaymentDataBean.setId(StringUtil.getLongValue(obj,0));

	}

	public String getId(){
		return StringUtil.getStringValue(
		externalPaymentDataBean.getId());

	}
	
					public void setPolicyHolder(String obj){

		externalPaymentDataBean.setPolicyHolder(new String(obj));

	}

	public String getPolicyHolder(){
		return StringUtil.getStringValue(
		externalPaymentDataBean.getPolicyHolder());

	}
	
					public void setCdvNumber(String obj){

		externalPaymentDataBean.setCdvNumber(new String(obj));

	}

	public String getCdvNumber(){
		return StringUtil.getStringValue(
		externalPaymentDataBean.getCdvNumber());

	}
	
				
	public void setClaimPaidDate(String obj){

		externalPaymentDataBean.setClaimPaidDate(java.sql.Date.valueOf(obj));

	}

	public String getClaimPaidDate(){
		return StringUtil.getStringValue(
		externalPaymentDataBean.getClaimPaidDate());

	}

	
				
	public void setIsSynchronized(String obj){

		externalPaymentDataBean.setIsSynchronized(StringUtil.getIntegerValue(obj,0));

	}

	public String getIsSynchronized(){
		return StringUtil.getStringValue(
		externalPaymentDataBean.getIsSynchronized());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
