
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * EdcTransactionLog is a mapping of edc_transaction_log Table.
*/
public class EdcTransactionLogForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewEdcTransactionLog = false;
	private EdcTransactionLog edcTransactionLogBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public EdcTransactionLogForm()
    {
    	this.edcTransactionLogBean = new EdcTransactionLog();
    	this.isNewEdcTransactionLog = true;
    }
    public EdcTransactionLogForm (EdcTransactionLog object){
		this.edcTransactionLogBean = object;
    }
    public boolean isNewEdcTransactionLog (){

    	return this.isNewEdcTransactionLog;
    }
	public EdcTransactionLog getEdcTransactionLog (){
		return this.edcTransactionLogBean ;
	}
	public void setEdcTransactionLog (EdcTransactionLog object){
		this.edcTransactionLogBean = object;
	}

			
	public void setId(String obj){

		edcTransactionLogBean.setId(StringUtil.getIntegerValue(obj,0));

	}

	public String getId(){
		return StringUtil.getStringValue(
		edcTransactionLogBean.getId());

	}
	
					public void setTerminalCode(String obj){

		edcTransactionLogBean.setTerminalCode(new String(obj));

	}

	public String getTerminalCode(){
		return StringUtil.getStringValue(
		edcTransactionLogBean.getTerminalCode());

	}
	
					public void setMerchantCode(String obj){

		edcTransactionLogBean.setMerchantCode(new String(obj));

	}

	public String getMerchantCode(){
		return StringUtil.getStringValue(
		edcTransactionLogBean.getMerchantCode());

	}
	
				
	public void setActivityTime(String obj){

		edcTransactionLogBean.setActivityTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getActivityTime(){
		return StringUtil.getStringValue(
		edcTransactionLogBean.getActivityTime());

	}

	
					public void setActivityLog(String obj){

		edcTransactionLogBean.setActivityLog(new String(obj));

	}

	public String getActivityLog(){
		return StringUtil.getStringValue(
		edcTransactionLogBean.getActivityLog());

	}
	
					public void setActionCode(String obj){

		edcTransactionLogBean.setActionCode(new String(obj));

	}

	public String getActionCode(){
		return StringUtil.getStringValue(
		edcTransactionLogBean.getActionCode());

	}
	
					public void setCardNumber(String obj){

		edcTransactionLogBean.setCardNumber(new String(obj));

	}

	public String getCardNumber(){
		return StringUtil.getStringValue(
		edcTransactionLogBean.getCardNumber());

	}
	
					public void setMemberNumber(String obj){

		edcTransactionLogBean.setMemberNumber(new String(obj));

	}

	public String getMemberNumber(){
		return StringUtil.getStringValue(
		edcTransactionLogBean.getMemberNumber());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
