
package com.ametis.cms.web.form;

import java.util.Collection;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * CardPrinting is a mapping of card_printing Table.
*/
public class CardPrintingForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewCardPrinting = false;
	private CardPrinting cardPrintingBean ;
	private Collection<Integer> memberIdList;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public CardPrintingForm()
    {
    	this.cardPrintingBean = new CardPrinting();
    	this.isNewCardPrinting = true;
    }
    public CardPrintingForm (CardPrinting object){
		this.cardPrintingBean = object;
    }
    public boolean isNewCardPrinting (){

    	return this.isNewCardPrinting;
    }
	public CardPrinting getCardPrinting (){
		return this.cardPrintingBean ;
	}
	public void setCardPrinting (CardPrinting object){
		this.cardPrintingBean = object;
	}

			
	public void setCardPrintingId(String obj){

		cardPrintingBean.setCardPrintingId(StringUtil.getIntegerValue(obj,0));

	}

	public String getCardPrintingId(){
		return StringUtil.getStringValue(
		cardPrintingBean.getCardPrintingId());

	}
	
							
	public void setPrintingDate(String obj){

		cardPrintingBean.setPrintingDate(java.sql.Date.valueOf(obj));

	}

	public String getPrintingDate(){
		return StringUtil.getStringValue(
		cardPrintingBean.getPrintingDate());

	}

	
					public void setPrintingNumber(String obj){

		cardPrintingBean.setPrintingNumber(new String(obj));

	}

	public String getPrintingNumber(){
		return StringUtil.getStringValue(
		cardPrintingBean.getPrintingNumber());

	}
	
				
	public void setStatus(String obj){

		cardPrintingBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		cardPrintingBean.getStatus());

	}
	
					public void setDescription(String obj){

		cardPrintingBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		cardPrintingBean.getDescription());

	}
	
	
	
	

	
	public Collection<Integer> getMemberIdList() {
		return memberIdList;
	}
	public void setMemberIdList(Collection<Integer> memberIdList) {
		this.memberIdList = memberIdList;
	}
	public void setClientId(String obj){
		Client fk = new Client();
		fk.setClientId(StringUtil.getIntegerValue(obj,0));
		cardPrintingBean.setClientId(fk);

	}

	public String getClientId(){
		return StringUtil.getStringValue(
		cardPrintingBean.getClientId().getClientId());

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
