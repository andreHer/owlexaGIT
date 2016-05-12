package com.ametis.cms.auth;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import com.ametis.cms.datamodel.Action;
import com.ametis.cms.util.auth.AuthUtil;

public class MemberAuth implements Serializable{

	private String viewAuth = "false";
	private String deleteAuth = "false";
	private String updateAuth = "false";
	private String saveAuth = "false";
	
	private String dependentAuth = "false";
	private String benefitAuth = "false";
	private String clausulAuth = "false";
	private String claimListAuth = "false";
	private String productListAuth = "false";
	private String caseListAuth = "false";
	private String providerListAuth = "false";
	private String excessChargeAuth = "false";
	
	private String terminateAuth = "false";
	private String renewAuth = "false";
	private String guaranteeLetterAuth = "false";
	private String guaranteeNumberAuth = "false";
	private String firstCallAuth = "false";
	private String activateAuth = "false";
	
	public MemberAuth(){
		
	}

	public Collection<Action> generateActionCode(){
		Collection<Action> result = new Vector<Action>();
		result.add(AuthUtil.generateAction("SAVE", "Save Batch Claim", "", "MEMBER-FORM"));
		result.add(AuthUtil.generateAction("COMPLETE", "Complete Batch Claim", "", "MEMBER-FORM"));
		
		result.add(AuthUtil.generateAction("EDIT", "Edit Batch Claim", "", "MEMBER-FORM"));
		result.add(AuthUtil.generateAction("DELETE", "Delete Batch Claim", "", "MEMBER-FORM"));
		
		result.add(AuthUtil.generateAction("PAY", "Pay Batch Claim", "", "MEMBER-FORM"));
		result.add(AuthUtil.generateAction("VERIFY", "Verify Batch Claim", "", "MEMBER-FORM"));
		
		result.add(AuthUtil.generateAction("CLAIMLIST", "Claim List - Member", "", "MEMBER-FORM"));
		result.add(AuthUtil.generateAction("PRODUCTLIST", "Product - Member", "", "MEMBER-FORM"));
		result.add(AuthUtil.generateAction("PROVIDERLIST", "Provider List - Member", "", "MEMBER-FORM"));
		result.add(AuthUtil.generateAction("CLAUSULLIST", "Clausul List - Member", "", "MEMBER-FORM"));
		result.add(AuthUtil.generateAction("BENEFITLIST", "Benefit List - Member", "", "MEMBER-FORM"));
		result.add(AuthUtil.generateAction("CASELIST", "Case List - Member", "", "MEMBER-FORM"));
		result.add(AuthUtil.generateAction("DEPENDENTLIST", "Dependent List - Member", "", "MEMBER-FORM"));
		result.add(AuthUtil.generateAction("EXCESSLIST", "Excess List - Member", "", "MEMBER-FORM"));
		
		result.add(AuthUtil.generateAction("ACTIVATE", "Activate Member", "", "MEMBER-FORM"));
		result.add(AuthUtil.generateAction("TERMINATE", "Terminate Member", "", "MEMBER-FORM"));
		result.add(AuthUtil.generateAction("RENEW", "Renew Membership", "", "MEMBER-FORM"));
		
		result.add(AuthUtil.generateAction("GL", "Guarantee Letter", "", "MEMBER-FORM"));
		result.add(AuthUtil.generateAction("GN", "Guarantee Number", "", "MEMBER-FORM"));
		result.add(AuthUtil.generateAction("FIRSTCALL", "First Call", "", "MEMBER-FORM"));
		
		return result;
	}
	public void loadAuth(Collection<Action> actionList){
		if (actionList != null){
			Iterator<Action> iterator = actionList.iterator();
			Action action = null;
			
			while (iterator.hasNext()){
				action = iterator.next();
				
				if (action != null){
					if (action.getActionCode().equalsIgnoreCase("PRODUCTLIST"))
						productListAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("PROVIDERLIST"))
						providerListAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("CLAUSULLIST"))
						clausulAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("EXCESSLIST"))
						excessChargeAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("BENEFITLIST"))
						benefitAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("CASELIST"))
						caseListAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("CLAIMLIST"))
						claimListAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("DEPENDENTLIST"))
						dependentAuth = "true";
					
					if (action.getActionCode().equalsIgnoreCase("DELETE"))
						deleteAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("EDIT"))
						updateAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("TERMINATE"))
						terminateAuth = "true";			
					if (action.getActionCode().equalsIgnoreCase("ACTIVATE"))
						activateAuth = "true";			
					if (action.getActionCode().equalsIgnoreCase("SAVE"))
						saveAuth = "true";					
					if (action.getActionCode().equalsIgnoreCase("RENEW"))
						renewAuth = "true";					
					if (action.getActionCode().equalsIgnoreCase("VIEW"))
						viewAuth = "true";					
					if (action.getActionCode().equalsIgnoreCase("FIRSTCALL"))
						firstCallAuth = "true";					
					if (action.getActionCode().equalsIgnoreCase("GL"))
						guaranteeLetterAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("GN"))
						guaranteeNumberAuth = "true";
						
				}
			}
		}
	}
	public String getViewAuth() {
		return viewAuth;
	}

	public void setViewAuth(String viewAuth) {
		this.viewAuth = viewAuth;
	}

	public String getDeleteAuth() {
		return deleteAuth;
	}

	public void setDeleteAuth(String deleteAuth) {
		this.deleteAuth = deleteAuth;
	}

	public String getUpdateAuth() {
		return updateAuth;
	}

	public void setUpdateAuth(String updateAuth) {
		this.updateAuth = updateAuth;
	}

	public String getDependentAuth() {
		return dependentAuth;
	}

	public void setDependentAuth(String dependentAuth) {
		this.dependentAuth = dependentAuth;
	}

	public String getBenefitAuth() {
		return benefitAuth;
	}

	public void setBenefitAuth(String benefitAuth) {
		this.benefitAuth = benefitAuth;
	}

	public String getClausulAuth() {
		return clausulAuth;
	}

	public void setClausulAuth(String clausulAuth) {
		this.clausulAuth = clausulAuth;
	}

	public String getClaimListAuth() {
		return claimListAuth;
	}

	public void setClaimListAuth(String claimListAuth) {
		this.claimListAuth = claimListAuth;
	}

	public String getProductListAuth() {
		return productListAuth;
	}

	public void setProductListAuth(String productListAuth) {
		this.productListAuth = productListAuth;
	}

	public String getCaseListAuth() {
		return caseListAuth;
	}

	public void setCaseListAuth(String caseListAuth) {
		this.caseListAuth = caseListAuth;
	}

	public String getProviderListAuth() {
		return providerListAuth;
	}

	public void setProviderListAuth(String providerListAuth) {
		this.providerListAuth = providerListAuth;
	}

	public String getExcessChargeAuth() {
		return excessChargeAuth;
	}

	public void setExcessChargeAuth(String excessChargeAuth) {
		this.excessChargeAuth = excessChargeAuth;
	}

	public String getTerminateAuth() {
		return terminateAuth;
	}

	public void setTerminateAuth(String terminateAuth) {
		this.terminateAuth = terminateAuth;
	}

	public String getRenewAuth() {
		return renewAuth;
	}

	public void setRenewAuth(String renewAuth) {
		this.renewAuth = renewAuth;
	}

	public String getGuaranteeLetterAuth() {
		return guaranteeLetterAuth;
	}

	public void setGuaranteeLetterAuth(String guaranteeLetterAuth) {
		this.guaranteeLetterAuth = guaranteeLetterAuth;
	}

	public String getGuaranteeNumberAuth() {
		return guaranteeNumberAuth;
	}

	public void setGuaranteeNumberAuth(String guaranteeNumberAuth) {
		this.guaranteeNumberAuth = guaranteeNumberAuth;
	}

	public String getFirstCallAuth() {
		return firstCallAuth;
	}

	public void setFirstCallAuth(String firstCallAuth) {
		this.firstCallAuth = firstCallAuth;
	}

	public String getActivateAuth() {
		return activateAuth;
	}

	public void setActivateAuth(String activateAuth) {
		this.activateAuth = activateAuth;
	}

	public String getSaveAuth() {
		return saveAuth;
	}

	public void setSaveAuth(String saveAuth) {
		this.saveAuth = saveAuth;
	}
	
	
}
