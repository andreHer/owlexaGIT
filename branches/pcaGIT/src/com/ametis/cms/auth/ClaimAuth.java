package com.ametis.cms.auth;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import com.ametis.cms.datamodel.Action;
import com.ametis.cms.util.auth.AuthUtil;

public class ClaimAuth implements Serializable {

	private String saveAuth = "false";
	private String deleteAuth = "false";
	private String updateAuth = "false";
	private String viewAuth = "false";
	
	private String rejectAuth = "false";
	private String pendingAuth = "false";
	private String addItemAuth = "false";
	private String extractAuth = "false";
	private String completeAuth = "false";
	private String checkAuth = "false";
	private String verifyAuth = "false";
	private String openAuth = "false";
	
	private String printPending = "false";
	private String printReject = "false";
	private String printExcess = "false";
	
	private String claimItemListAuth = "false";
	private String benefitListAuth = "false";
	private String clausulListAuth = "false";
	private String caseHistoryAuth = "false";
	private String investigationAuth = "false";
	private String claimHistoryAuth = "false";
	
	public ClaimAuth(){
		
	}

	public Collection<Action> generateActionCode(){
		Collection<Action> result = new Vector<Action>();
		result.add(AuthUtil.generateAction("SAVE", "Save Claim", "claim-form", "CLAIM-FORM"));

		result.add(AuthUtil.generateAction("EDIT", "Edit Claim", "claim-form", "CLAIM-FORM"));
		result.add(AuthUtil.generateAction("DELETE", "Delete Claim", "claim?navigation=hapus", "CLAIM-FORM"));
		
		result.add(AuthUtil.generateAction("COMPLETE", "Complete Claim", "", "CLAIM-FORM"));
		
		
		result.add(AuthUtil.generateAction("CHECK", "Benefit Check Claim", "", "CLAIM-FORM"));
		result.add(AuthUtil.generateAction("VERIFY", "Verify Claim", "", "CLAIM-FORM"));
		
		result.add(AuthUtil.generateAction("ITEMLIST", "Item List - Claim", "", "CLAIM-FORM"));
		result.add(AuthUtil.generateAction("BENEFITLIST", "Benefit - Claim", "", "CLAIM-FORM"));
		result.add(AuthUtil.generateAction("CLAUSULLIST", "Clausul - Claim", "", "CLAIM-FORM"));
		result.add(AuthUtil.generateAction("INVESTIGATION", "Investigation List - Claim", "", "CLAIM-FORM"));
		result.add(AuthUtil.generateAction("CASELIST", "Case List - Claim", "", "CLAIM-FORM"));
		result.add(AuthUtil.generateAction("HISTORYLIST", "History List - Claim", "payment", "CLAIM-FORM"));
		
		result.add(AuthUtil.generateAction("CLOSE", "Close Batch Claim", "", "CLAIM-FORM"));
		result.add(AuthUtil.generateAction("OPEN", "Open Batch Claim", "", "CLAIM-FORM"));
		
		result.add(AuthUtil.generateAction("ADDITEM", "Add Item - Claim", "", "CLAIM-FORM"));
		result.add(AuthUtil.generateAction("VIEW", "View Claim", "", "CLAIM-FORM"));
		result.add(AuthUtil.generateAction("EXTRACT", "Extract - Claim", "", "CLAIM-FORM"));
		result.add(AuthUtil.generateAction("PENDING", "Pending Claim", "", "CLAIM-FORM"));
		result.add(AuthUtil.generateAction("REJECT", "Reject Claim", "", "CLAIM-FORM"));
		
		result.add(AuthUtil.generateAction("PRINTPENDING", "Print Pending Claim", "", "CLAIM-FORM"));
		result.add(AuthUtil.generateAction("PRINTREJECT", "Print Reject Claim", "", "CLAIM-FORM"));
		result.add(AuthUtil.generateAction("PRINTEXCESS", "Print Excess Claim", "", "CLAIM-FORM"));
		
		return result;
	}
	public void loadAuth(Collection<Action> actionList){
		if (actionList != null){
			Iterator<Action> iterator = actionList.iterator();
			Action action = null;
			
			while (iterator.hasNext()){
				action = iterator.next();
				
				if (action != null){
					if (action.getActionCode().equalsIgnoreCase("INVESTIGATION"))
						investigationAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("CLAUSULLIST"))
						clausulListAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("BENEFITLIST"))
						benefitListAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("CASELIST"))
						caseHistoryAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("HISTORYLIST"))
						claimHistoryAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("ITEMLIST"))
						claimItemListAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("DELETE"))
						deleteAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("EDIT"))
						updateAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("VERIFY"))
						verifyAuth = "true";			
					if (action.getActionCode().equalsIgnoreCase("CHECK"))
						checkAuth = "true";			
					if (action.getActionCode().equalsIgnoreCase("SAVE"))
						saveAuth = "true";					
					if (action.getActionCode().equalsIgnoreCase("COMPLETE"))
						completeAuth = "true";					
					if (action.getActionCode().equalsIgnoreCase("PRINTPENDING"))
						printPending = "true";
					if (action.getActionCode().equalsIgnoreCase("PRINTREJECT"))
						printReject = "true";
					if (action.getActionCode().equalsIgnoreCase("PRINTEXCESS"))
						printExcess = "true";
					
					if (action.getActionCode().equalsIgnoreCase("PENDING"))
						pendingAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("REJECT"))
						rejectAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("EXTRACT"))
						extractAuth = "true";					
					if (action.getActionCode().equalsIgnoreCase("OPEN"))
						openAuth = "true";					
					if (action.getActionCode().equalsIgnoreCase("ADDITEM"))
						addItemAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("VIEW"))
						viewAuth = "true";
						
				}
			}
		}
	}
	public String getSaveAuth() {
		return saveAuth;
	}

	public void setSaveAuth(String saveAuth) {
		this.saveAuth = saveAuth;
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

	public String getViewAuth() {
		return viewAuth;
	}

	public void setViewAuth(String viewAuth) {
		this.viewAuth = viewAuth;
	}

	public String getRejectAuth() {
		return rejectAuth;
	}

	public void setRejectAuth(String rejectAuth) {
		this.rejectAuth = rejectAuth;
	}

	public String getPendingAuth() {
		return pendingAuth;
	}

	public void setPendingAuth(String pendingAuth) {
		this.pendingAuth = pendingAuth;
	}

	public String getAddItemAuth() {
		return addItemAuth;
	}

	public void setAddItemAuth(String addItemAuth) {
		this.addItemAuth = addItemAuth;
	}

	public String getExtractAuth() {
		return extractAuth;
	}

	public void setExtractAuth(String extractAuth) {
		this.extractAuth = extractAuth;
	}

	public String getCompleteAuth() {
		return completeAuth;
	}

	public void setCompleteAuth(String completeAuth) {
		this.completeAuth = completeAuth;
	}

	public String getCheckAuth() {
		return checkAuth;
	}

	public void setCheckAuth(String checkAuth) {
		this.checkAuth = checkAuth;
	}

	public String getVerifyAuth() {
		return verifyAuth;
	}

	public void setVerifyAuth(String verifyAuth) {
		this.verifyAuth = verifyAuth;
	}

	public String getOpenAuth() {
		return openAuth;
	}

	public void setOpenAuth(String openAuth) {
		this.openAuth = openAuth;
	}

	public String getPrintPending() {
		return printPending;
	}

	public void setPrintPending(String printPending) {
		this.printPending = printPending;
	}

	public String getPrintReject() {
		return printReject;
	}

	public void setPrintReject(String printReject) {
		this.printReject = printReject;
	}

	public String getPrintExcess() {
		return printExcess;
	}

	public void setPrintExcess(String printExcess) {
		this.printExcess = printExcess;
	}

	public String getClaimItemListAuth() {
		return claimItemListAuth;
	}

	public void setClaimItemListAuth(String claimItemListAuth) {
		this.claimItemListAuth = claimItemListAuth;
	}

	public String getBenefitListAuth() {
		return benefitListAuth;
	}

	public void setBenefitListAuth(String benefitListAuth) {
		this.benefitListAuth = benefitListAuth;
	}

	public String getClausulListAuth() {
		return clausulListAuth;
	}

	public void setClausulListAuth(String clausulListAuth) {
		this.clausulListAuth = clausulListAuth;
	}

	public String getCaseHistoryAuth() {
		return caseHistoryAuth;
	}

	public void setCaseHistoryAuth(String caseHistoryAuth) {
		this.caseHistoryAuth = caseHistoryAuth;
	}

	public String getInvestigationAuth() {
		return investigationAuth;
	}

	public void setInvestigationAuth(String investigationAuth) {
		this.investigationAuth = investigationAuth;
	}

	public String getClaimHistoryAuth() {
		return claimHistoryAuth;
	}

	public void setClaimHistoryAuth(String claimHistoryAuth) {
		this.claimHistoryAuth = claimHistoryAuth;
	}
	
	
	
}
