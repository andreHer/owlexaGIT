
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * CardType is a mapping of card_type Table.
*/
public class CardTypeForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewCardType = false;
	private CardType cardTypeBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public CardTypeForm()
    {
    	this.cardTypeBean = new CardType();
    	this.isNewCardType = true;
    }
    public CardTypeForm (CardType object){
		this.cardTypeBean = object;
    }
    public boolean isNewCardType (){

    	return this.isNewCardType;
    }
	public CardType getCardType (){
		return this.cardTypeBean ;
	}
	public void setCardType (CardType object){
		this.cardTypeBean = object;
	}

			
	public void setCardTypeId(String obj){

		cardTypeBean.setCardTypeId(StringUtil.getIntegerValue(obj,0));

	}

	public String getCardTypeId(){
		return StringUtil.getStringValue(
		cardTypeBean.getCardTypeId());

	}
	
					public void setCardTypeName(String obj){

		cardTypeBean.setCardTypeName(new String(obj));

	}

	public String getCardTypeName(){
		return StringUtil.getStringValue(
		cardTypeBean.getCardTypeName());

	}
	
					public void setCardTypeCode(String obj){

		cardTypeBean.setCardTypeCode(new String(obj));

	}

	public String getCardTypeCode(){
		return StringUtil.getStringValue(
		cardTypeBean.getCardTypeCode());

	}
	
					public void setDescription(String obj){

		cardTypeBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		cardTypeBean.getDescription());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 

}
