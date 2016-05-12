
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * CardStock is a mapping of card_stock Table.
*/
public class CardStockForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewCardStock = false;
	private CardStock cardStockBean ;
	private String clientName;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public CardStockForm()
    {
    	this.cardStockBean = new CardStock();
    	this.isNewCardStock = true;
    }
    public CardStockForm (CardStock object){
		this.cardStockBean = object;
    }
    public boolean isNewCardStock (){

    	return this.isNewCardStock;
    }
	public CardStock getCardStock (){
		return this.cardStockBean ;
	}
	public void setCardStock (CardStock object){
		this.cardStockBean = object;
	}

			
	public void setId(String obj){

		cardStockBean.setId(StringUtil.getLongValue(obj,0));

	}

	public String getId(){
		return StringUtil.getStringValue(
		cardStockBean.getId());

	}
	
					public void setCardStockBatchNumber(String obj){

		cardStockBean.setCardStockBatchNumber(new String(obj));

	}

	public String getCardStockBatchNumber(){
		return StringUtil.getStringValue(
		cardStockBean.getCardStockBatchNumber());

	}
	
				
	public void setBatchDate(String obj){

		cardStockBean.setBatchDate(java.sql.Date.valueOf(obj));

	}

	public String getBatchDate(){
		return StringUtil.getStringValue(
		cardStockBean.getBatchDate());

	}

	
				
	public void setInitialStock(String obj){

		cardStockBean.setInitialStock(StringUtil.getIntegerValue(obj,0));

	}

	public String getInitialStock(){
		return StringUtil.getStringValue(
		cardStockBean.getInitialStock());

	}
	
				
	public void setAdditionalStock(String obj){

		cardStockBean.setAdditionalStock(StringUtil.getIntegerValue(obj,0));

	}

	public String getAdditionalStock(){
		return StringUtil.getStringValue(
		cardStockBean.getAdditionalStock());

	}
	
				
	public void setRemainingStock(String obj){

		cardStockBean.setRemainingStock(StringUtil.getIntegerValue(obj,0));

	}

	public String getRemainingStock(){
		return StringUtil.getStringValue(
		cardStockBean.getRemainingStock());

	}
	
				
	public void setStatus(String obj){

		cardStockBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		cardStockBean.getStatus());

	}
	
				
	public void setClientId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Client client = new Client();
			client.setClientId(Integer.valueOf(obj));
			cardStockBean.setClientId(client);
		}

	}

	public String getClientId(){
		String result = "";
		
		if (cardStockBean.getClientId() != null){
			result = StringUtil.getStringValue(
					cardStockBean.getClientId().getClientId());
		}
		
		return result;
		

	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
		
	
}
