package com.ametis.cms.auth;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import com.ametis.cms.datamodel.Action;
import com.ametis.cms.util.auth.AuthUtil;
import com.sun.org.apache.bcel.internal.generic.FLOAD;

public class FinanceMenuAuth implements Serializable{

	private String financeMenuAuth = "false";
	private String excessMenuAuth = "false";
	private String floatingFundMenuAuth = "false";
	private String paymentMenuAuth = "false";
	
	
	public FinanceMenuAuth(){}

	public Collection<Action> generateActionCode(){
		Collection<Action> result = new Vector<Action>();
		result.add(AuthUtil.generateAction("FINANCETOP", "Top Menu Finance", "", "FINANCE-MENU"));
		result.add(AuthUtil.generateAction("EXCESS", "Menu Excess Charge", "", "FINANCE-MENU"));
		
		result.add(AuthUtil.generateAction("FUND", "Menu Floating Fund", "", "FINANCE-MENU"));
		result.add(AuthUtil.generateAction("PAYMENT", "Menu Payment", "", "FINANCE-MENU"));
		return result;
	}
	public void loadAuth(Collection<Action> actionList){
		if (actionList != null){
			Iterator<Action> iterator = actionList.iterator();
			Action action = null;
			
			while (iterator.hasNext()){
				action = iterator.next();
				
				if (action != null){
					if (action.getActionCode().equalsIgnoreCase("FINANCETOP"))
						financeMenuAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("EXCESS"))
						excessMenuAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("FUND"))
						floatingFundMenuAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("PAYMENT"))
						paymentMenuAuth = "true";
						
				}
			}
		}
	}

	public String getFinanceMenuAuth() {
		return financeMenuAuth;
	}


	public void setFinanceMenuAuth(String financeMenuAuth) {
		this.financeMenuAuth = financeMenuAuth;
	}


	public String getExcessMenuAuth() {
		return excessMenuAuth;
	}


	public void setExcessMenuAuth(String excessMenuAuth) {
		this.excessMenuAuth = excessMenuAuth;
	}


	public String getFloatingFundMenuAuth() {
		return floatingFundMenuAuth;
	}


	public void setFloatingFundMenuAuth(String floatingFundMenuAuth) {
		this.floatingFundMenuAuth = floatingFundMenuAuth;
	}
	
}
