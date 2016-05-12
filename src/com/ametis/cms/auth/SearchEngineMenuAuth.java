package com.ametis.cms.auth;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import com.ametis.cms.datamodel.Action;
import com.ametis.cms.util.auth.AuthUtil;

public class SearchEngineMenuAuth implements Serializable {
	
	private String batchClaimAuth = "false";
	private String claimAuth = "false";
	private String paymentAuth = "false";
	private String excessAuth = "false";
	private String itemAuth = "false";
	private String itemCategoryAuth = "false";
	
	public SearchEngineMenuAuth(){}
	
	public Collection<Action> generateActionCode(){
		Collection<Action> result = new Vector<Action>();
		result.add(AuthUtil.generateAction("BATCHCLAIM", "Search Batch Claim", "", "SEARCHENGINE-MENU"));
		result.add(AuthUtil.generateAction("CLAIM", "Search Claim", "", "SEARCHENGINE-MENU"));
		
		result.add(AuthUtil.generateAction("PAYMENT", "Search Payment", "", "SEARCHENGINE-MENU"));
		result.add(AuthUtil.generateAction("EXCESS", "Search Excess Claim", "", "SEARCHENGINE-MENU"));
		
		result.add(AuthUtil.generateAction("ITEM", "Search Item", "", "SEARCHENGINE-MENU"));
		result.add(AuthUtil.generateAction("ITEMCATEGORY", "Search Item Category", "", "SEARCHENGINE-MENU"));
		
		
		
		return result;
	}
	public void loadAuth(Collection<Action> actionList){
		if (actionList != null){
			Iterator<Action> iterator = actionList.iterator();
			Action action = null;
			
			while (iterator.hasNext()){
				action = iterator.next();
				
				if (action != null){
					if (action.getActionCode().equalsIgnoreCase("BATCHCLAIM"))
						batchClaimAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("CLAIM"))
						claimAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("PAYMENT"))
						paymentAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("EXCESS"))
						excessAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("ITEM"))
						itemAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("ITEMCATEGORY"))
						itemCategoryAuth = "true";	
				}
			}
		}
	}
	

	public String getBatchClaimAuth() {
		return batchClaimAuth;
	}

	public void setBatchClaimAuth(String batchClaimAuth) {
		this.batchClaimAuth = batchClaimAuth;
	}

	public String getClaimAuth() {
		return claimAuth;
	}

	public void setClaimAuth(String claimAuth) {
		this.claimAuth = claimAuth;
	}

	public String getPaymentAuth() {
		return paymentAuth;
	}

	public void setPaymentAuth(String paymentAuth) {
		this.paymentAuth = paymentAuth;
	}

	public String getExcessAuth() {
		return excessAuth;
	}

	public void setExcessAuth(String excessAuth) {
		this.excessAuth = excessAuth;
	}

	public String getItemAuth() {
		return itemAuth;
	}

	public void setItemAuth(String itemAuth) {
		this.itemAuth = itemAuth;
	}

	public String getItemCategoryAuth() {
		return itemCategoryAuth;
	}

	public void setItemCategoryAuth(String itemCategoryAuth) {
		this.itemCategoryAuth = itemCategoryAuth;
	}
	
	

}
