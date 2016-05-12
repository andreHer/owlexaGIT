package com.ametis.cms.auth;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import com.ametis.cms.datamodel.Action;
import com.ametis.cms.util.auth.AuthUtil;

public class BatchClaimAuth implements Serializable{

	private String saveAuth = "false";
	private String completeAuth = "false";
	private String closeAuth = "false";
	private String openAuth = "false";
	private String addClaimAuth = "false";
	private String updateAuth = "false";
	private String deleteAuth = "false";
	private String payAuth = "false";
	private String verifyAuth = "false";
	
	private String claimListAuth = "false";
	private String attachmentAuth = "false";
	private String pendingClaimAuth = "false";
	private String excessListAuth = "false";
	private String paymentListAuth = "false";
	
	public BatchClaimAuth(){
		
	}
	public Collection<Action> generateActionCode(){
		Collection<Action> result = new Vector<Action>();
		result.add(AuthUtil.generateAction("SAVE", "Save Batch Claim", "batchclaim-form", "BATCHCLAIM-FORM"));
		result.add(AuthUtil.generateAction("COMPLETE", "Complete Batch Claim", "batchclaim?navigation=complete", "BATCHCLAIM-FORM"));
		
		result.add(AuthUtil.generateAction("EDIT", "Edit Batch Claim", "batchclaim-form", "BATCHCLAIM-FORM"));
		result.add(AuthUtil.generateAction("DELETE", "Delete Batch Claim", "batchclaim?navigation=hapus", "BATCHCLAIM-FORM"));
		
		result.add(AuthUtil.generateAction("PAY", "Pay Batch Claim", "payment-form?navigation=paybatch", "BATCHCLAIM-FORM"));
		result.add(AuthUtil.generateAction("VERIFY", "Verify Batch Claim", "batchclaim?navigation=verifybulk", "BATCHCLAIM-FORM"));
		
		result.add(AuthUtil.generateAction("CLAIMLIST", "Claim List - Batch Claim", "claim", "BATCHCLAIM-FORM"));
		result.add(AuthUtil.generateAction("ATTACHMENTLIST", "Attachment - Batch Claim", "", "BATCHCLAIM-FORM"));
		result.add(AuthUtil.generateAction("PENDINGLIST", "Pending Claim - Batch Claim", "claim", "BATCHCLAIM-FORM"));
		result.add(AuthUtil.generateAction("EXCESSLIST", "Excess List - Batch Claim", "excesscharge", "BATCHCLAIM-FORM"));
		result.add(AuthUtil.generateAction("PAYMENTLIST", "Payment List - Batch Claim", "payment", "BATCHCLAIM-FORM"));
		
		result.add(AuthUtil.generateAction("CLOSE", "Close Batch Claim", "batchclaim?navigation=close", "BATCHCLAIM-FORM"));
		result.add(AuthUtil.generateAction("OPEN", "Open Batch Claim", "batchclaim?navigation=open", "BATCHCLAIM-FORM"));
		result.add(AuthUtil.generateAction("ADDCLAIM", "Add Claim - Batch", "claim-form", "BATCHCLAIM-FORM"));
		
		return result;
	}
	public void loadAuth(Collection<Action> actionList){
		if (actionList != null){
			Iterator<Action> iterator = actionList.iterator();
			Action action = null;
			
			while (iterator.hasNext()){
				action = iterator.next();
				
				if (action != null){
					if (action.getActionCode().equalsIgnoreCase("PAYMENTLIST"))
						paymentListAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("EXCESSLIST"))
						excessListAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("PENDINGLIST"))
						pendingClaimAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("ATTACHMENTLIST"))
						attachmentAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("CLAIMLIST"))
						claimListAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("DELETE"))
						deleteAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("EDIT"))
						updateAuth = "true";
					if (action.getActionCode().equalsIgnoreCase("VERIFY"))
						verifyAuth = "true";			
					if (action.getActionCode().equalsIgnoreCase("PAY"))
						payAuth = "true";			
					if (action.getActionCode().equalsIgnoreCase("SAVE"))
						saveAuth = "true";					
					if (action.getActionCode().equalsIgnoreCase("COMPLETE"))
						completeAuth = "true";					
					if (action.getActionCode().equalsIgnoreCase("CLOSE"))
						closeAuth = "true";					
					if (action.getActionCode().equalsIgnoreCase("OPEN"))
						openAuth = "true";					
					if (action.getActionCode().equalsIgnoreCase("ADDCLAIM"))
						addClaimAuth = "true";
					
						
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
	public String getCompleteAuth() {
		return completeAuth;
	}
	public void setCompleteAuth(String completeAuth) {
		this.completeAuth = completeAuth;
	}
	public String getCloseAuth() {
		return closeAuth;
	}
	public void setCloseAuth(String closeAuth) {
		this.closeAuth = closeAuth;
	}
	public String getOpenAuth() {
		return openAuth;
	}
	public void setOpenAuth(String openAuth) {
		this.openAuth = openAuth;
	}
	public String getAddClaimAuth() {
		return addClaimAuth;
	}
	public void setAddClaimAuth(String addClaimAuth) {
		this.addClaimAuth = addClaimAuth;
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
	public String getPayAuth() {
		return payAuth;
	}
	public void setPayAuth(String payAuth) {
		this.payAuth = payAuth;
	}
	public String getClaimListAuth() {
		return claimListAuth;
	}
	public void setClaimListAuth(String claimListAuth) {
		this.claimListAuth = claimListAuth;
	}
	public String getAttachmentAuth() {
		return attachmentAuth;
	}
	public void setAttachmentAuth(String attachmentAuth) {
		this.attachmentAuth = attachmentAuth;
	}
	public String getPendingClaimAuth() {
		return pendingClaimAuth;
	}
	public void setPendingClaimAuth(String pendingClaimAuth) {
		this.pendingClaimAuth = pendingClaimAuth;
	}
	public String getExcessListAuth() {
		return excessListAuth;
	}
	public void setExcessListAuth(String excessListAuth) {
		this.excessListAuth = excessListAuth;
	}
	public String getPaymentListAuth() {
		return paymentListAuth;
	}
	public void setPaymentListAuth(String paymentListAuth) {
		this.paymentListAuth = paymentListAuth;
	}
	public String getVerifyAuth() {
		return verifyAuth;
	}
	public void setVerifyAuth(String verifyAuth) {
		this.verifyAuth = verifyAuth;
	}
	
	
	
}
