
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * Bank is a mapping of bank Table.
*/
public class BankForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewBank = false;
	private Bank bankBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public BankForm()
    {
    	this.bankBean = new Bank();
    	this.isNewBank = true;
    }
    public BankForm (Bank object){
		this.bankBean = object;
    }
    public boolean isNewBank (){

    	return this.isNewBank;
    }
	public Bank getBank (){
		return this.bankBean ;
	}
	public void setBank (Bank object){
		this.bankBean = object;
	}

			
	public void setBankId(String obj){

		bankBean.setBankId(StringUtil.getIntegerValue(obj,0));

	}

	public String getBankId(){
		return StringUtil.getStringValue(
		bankBean.getBankId());

	}
	
					public void setBankName(String obj){

		bankBean.setBankName(new String(obj));

	}

	public String getBankName(){
		return StringUtil.getStringValue(
		bankBean.getBankName());

	}
	
					public void setSwiftCode(String obj){

		bankBean.setSwiftCode(new String(obj));

	}

	public String getSwiftCode(){
		return StringUtil.getStringValue(
		bankBean.getSwiftCode());

	}
	
					public void setDescription(String obj){

		bankBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		bankBean.getDescription());

	}
	
					public void setBankCode(String obj){

		bankBean.setBankCode(new String(obj));

	}

	public String getBankCode(){
		return StringUtil.getStringValue(
		bankBean.getBankCode());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 

}
