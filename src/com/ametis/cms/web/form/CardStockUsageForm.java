
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * CardStockUsage is a mapping of card_stock_usage Table.
*/
public class CardStockUsageForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewCardStockUsage = false;
	private CardStockUsage cardStockUsageBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public CardStockUsageForm()
    {
    	this.cardStockUsageBean = new CardStockUsage();
    	this.isNewCardStockUsage = true;
    }
    public CardStockUsageForm (CardStockUsage object){
		this.cardStockUsageBean = object;
    }
    public boolean isNewCardStockUsage (){

    	return this.isNewCardStockUsage;
    }
	public CardStockUsage getCardStockUsage (){
		return this.cardStockUsageBean ;
	}
	public void setCardStockUsage (CardStockUsage object){
		this.cardStockUsageBean = object;
	}

			
	public void setId(String obj){

		cardStockUsageBean.setId(StringUtil.getIntegerValue(obj,0));

	}

	public String getId(){
		return StringUtil.getStringValue(
		cardStockUsageBean.getId());

	}
	
					public void setCardPrintBatchNumber(String obj){

		cardStockUsageBean.setCardPrintBatchNumber(new String(obj));

	}

	public String getCardPrintBatchNumber(){
		return StringUtil.getStringValue(
		cardStockUsageBean.getCardPrintBatchNumber());

	}
	
				
	public void setCardPrintBatchDate(String obj){

		cardStockUsageBean.setCardPrintBatchDate(java.sql.Date.valueOf(obj));

	}

	public String getCardPrintBatchDate(){
		return StringUtil.getStringValue(
		cardStockUsageBean.getCardPrintBatchDate());

	}

	
				
	public void setClientId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Client client = new Client();
			client.setClientId(Integer.valueOf(obj));
			cardStockUsageBean.setClientId(client);
		}

	}

	public String getClientId(){
		String result = "";
		
		if (cardStockUsageBean.getClientId() != null){
			result = StringUtil.getStringValue(
					cardStockUsageBean.getClientId().getClientId());
		}
		return result;
		

	}
	
				
	public void setCardPrintTotal(String obj){

		cardStockUsageBean.setCardPrintTotal(StringUtil.getIntegerValue(obj,0));

	}

	public String getCardPrintTotal(){
		return StringUtil.getStringValue(
		cardStockUsageBean.getCardPrintTotal());

	}
	
				
	public void setPolicyId(String obj){

		if (obj != null && !obj.equals("")){
			Policy policy = new Policy();
			policy.setPolicyId(Integer.valueOf(obj));
			cardStockUsageBean.setPolicyId(policy);
		}

	}

	public String getPolicyId(){
		String result = "";
		
		if (cardStockUsageBean.getPolicyId() != null){
			result  = StringUtil.getStringValue(
					cardStockUsageBean.getPolicyId().getPolicyId());
		}
		return result;
		

	}
	
				


// class+ 

// class- 
}
