package com.ametis.cms.auth;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import com.ametis.cms.datamodel.Action;
import com.ametis.cms.util.auth.AuthUtil;

public class PaymentAuth implements Serializable{

	private String updateAuth = "false";
	private String deleteAuth = "false";
	private String saveAuth = "false";
	private String viewAuth = "false";
	
	
	private String printPaymentAuth = "false";
	private String printPaymentDetailAuth = "false";
	private String downloadPaymentAuth = "false";
	private String downloadClaimRecapAuth = "false";
	private String confirmPaymentAuth = "false";
	private String dispositionAuth = "false";
	private String openAuth = "false";
	
	private String excessListAuth = "false";
	
	public PaymentAuth(){
		
	}

	public Collection<Action> generateActionCode(){
		Collection<Action> result = new Vector<Action>();
		result.add(AuthUtil.generateAction("SAVE", "Save Payment", "", "PAYMENT-FORM"));
		result.add(AuthUtil.generateAction("VIEW", "View Payment", "", "PAYMENT-FORM"));
		
		result.add(AuthUtil.generateAction("EDIT", "Edit Payment", "", "PAYMENT-FORM"));
		result.add(AuthUtil.generateAction("DELETE", "Delete Payment", "", "PAYMENT-FORM"));
		
		result.add(AuthUtil.generateAction("PRINTPAYMENT", "Print Payment", "", "PAYMENT-FORM"));
		result.add(AuthUtil.generateAction("CONFIRM", "Confirm Payment", "", "PAYMENT-FORM"));
		
		result.add(AuthUtil.generateAction("EXCESSLIST", "Excess List - Payment", "", "PAYMENT-FORM"));
		result.add(AuthUtil.generateAction("PRINTDETAIL", "Print Detail - Payment", "", "PAYMENT-FORM"));
		result.add(AuthUtil.generateAction("DOWNLOADPAYMENT", "Download Payment - Payment", "", "PAYMENT-FORM"));
		result.add(AuthUtil.generateAction("DOWNLOADRECAP", "Download Recap - Payment", "", "PAYMENT-FORM"));
		result.add(AuthUtil.generateAction("OPEN", "Open  - Payment", "", "PAYMENT-FORM"));
		result.add(AuthUtil.generateAction("DISPOSITION", "Disposition - Payment", "", "PAYMENT-FORM"));
		
			
		return result;
	}
	public void loadAuth(Collection<Action> actionList){
		if (actionList != null){
			Iterator<Action> iterator = actionList.iterator();
			Action action = null;
			
			while (iterator.hasNext()){
				action = iterator.next();
				
				if (action != null){
					if (action.getActionCode().equalsIgnoreCase("EXCESSLIST"))
						excessListAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("PRINTDETAIL"))
						printPaymentDetailAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("DOWNLOADPAYMENT"))
						downloadPaymentAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("DOWNLOADRECAP"))
						downloadClaimRecapAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("OPEN"))
						openAuth = "true";
					
					if (action.getActionCode().equalsIgnoreCase("DELETE"))
						deleteAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("EDIT"))
						updateAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("DISPOSITION"))
						dispositionAuth = "true";			
					if (action.getActionCode().equalsIgnoreCase("CONFIRM"))
						confirmPaymentAuth = "true";			
					if (action.getActionCode().equalsIgnoreCase("SAVE"))
						saveAuth = "true";								
					if (action.getActionCode().equalsIgnoreCase("VIEW"))
						viewAuth = "true";					
					
						
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


	public String getUpdateAuth() {
		return updateAuth;
	}

	public void setUpdateAuth(String updateAuth) {
		this.updateAuth = updateAuth;
	}

	public String getDeleteAuth() {
		return deleteAuth;
	}

	public void setDeleteAuth(String deleteAuth) {
		this.deleteAuth = deleteAuth;
	}

	public String getSaveAuth() {
		return saveAuth;
	}

	public void setSaveAuth(String saveAuth) {
		this.saveAuth = saveAuth;
	}

	public String getPrintPaymentAuth() {
		return printPaymentAuth;
	}

	public void setPrintPaymentAuth(String printPaymentAuth) {
		this.printPaymentAuth = printPaymentAuth;
	}

	public String getPrintPaymentDetailAuth() {
		return printPaymentDetailAuth;
	}

	public void setPrintPaymentDetailAuth(String printPaymentDetailAuth) {
		this.printPaymentDetailAuth = printPaymentDetailAuth;
	}

	public String getDownloadPaymentAuth() {
		return downloadPaymentAuth;
	}

	public void setDownloadPaymentAuth(String downloadPaymentAuth) {
		this.downloadPaymentAuth = downloadPaymentAuth;
	}

	public String getDownloadClaimRecapAuth() {
		return downloadClaimRecapAuth;
	}

	public void setDownloadClaimRecapAuth(String downloadClaimRecapAuth) {
		this.downloadClaimRecapAuth = downloadClaimRecapAuth;
	}

	public String getConfirmPaymentAuth() {
		return confirmPaymentAuth;
	}

	public void setConfirmPaymentAuth(String confirmPaymentAuth) {
		this.confirmPaymentAuth = confirmPaymentAuth;
	}

	public String getDispositionAuth() {
		return dispositionAuth;
	}

	public void setDispositionAuth(String dispositionAuth) {
		this.dispositionAuth = dispositionAuth;
	}

	public String getOpenAuth() {
		return openAuth;
	}

	public void setOpenAuth(String openAuth) {
		this.openAuth = openAuth;
	}

	public String getExcessListAuth() {
		return excessListAuth;
	}

	public void setExcessListAuth(String excessListAuth) {
		this.excessListAuth = excessListAuth;
	}
	
	
}
