package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.BatchClaim;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.ClaimReceiving;
import com.ametis.cms.datamodel.ClaimType;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.Currency;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.PaymentMethod;
import com.ametis.cms.datamodel.PaymentRecipient;
import com.ametis.cms.datamodel.Priority;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.util.StringUtil;

// imports+ 

// imports- 

/**
 * BatchClaim is a mapping of batch_claim Table.
 */
public class BatchClaimForm implements java.io.Serializable
// extends+

// extends-
{

	private boolean isNewBatchClaim = false;

	private BatchClaim batchClaimBean;
	
	private String unregistered;

	private Integer claimerId;

	private String claimer;
	private String clientName;
	private String claimReceivingNumber;
	
	//tambahan
	private String totalClaim;

	/*
	 * <form>Bean merupakan representasi dari "SINGLE" table dan misalkan ada
	 * form2 yang merupakan referensi dari tabel lain bikin aja field2 bean yang
	 * mengacu ke referensi itu biar nanti automatic loading
	 * 
	 */

	public BatchClaimForm() {
		this.batchClaimBean = new BatchClaim();
		this.isNewBatchClaim = true;
	}

	public BatchClaimForm(BatchClaim object) {
		this.batchClaimBean = object;
	}

	
	
	
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClaimer() {
		return claimer;
	}

	public void setBatchDueDate (java.util.Date date){
		//batchClaimBean.setBatchDueDate(java.sql.Date.valueOf(date));
		//batchClaimBean.setBatchDueDate(Da);
		java.sql.Date res = null;
		
		if (date != null){
			res = new java.sql.Date(date.getTime());
		}
		batchClaimBean.setBatchDueDate(res);
		//batchClaimBean.setBatchDueDate(StringUtil.getDateValue(date,"dd-MM-yyyy"));
	}
	public java.util.Date getBatchDueDate (){		
		
		return batchClaimBean.getBatchDueDate();
		//return StringUtil
		//.getStringValue(batchClaimBean.getBatchDueDate());
	}
	public void setClaimer(String claimer) {
		this.claimer = claimer;
	}

	public Integer getClaimerId() {
		return claimerId;
	}

	public void setClaimerId(Integer claimerId) {
		this.claimerId = claimerId;
	}

	public boolean isNewBatchClaim() {

		return this.isNewBatchClaim;
	}

	public BatchClaim getBatchClaim() {
		return this.batchClaimBean;
	}

	public void setBatchClaim(BatchClaim object) {
		this.batchClaimBean = object;
	}

	public void setBatchClaimId(String obj) {

		batchClaimBean.setBatchClaimId(StringUtil.getIntegerValue(obj, 0));

	}

	public String getBatchClaimId() {
		return StringUtil.getStringValue(batchClaimBean.getBatchClaimId());

	}

	public void setBatchNumberPsea(String obj) {

		batchClaimBean.setBatchNumberPsea(new String(obj));

	}

	public String getBatchNumberPsea() {
		return StringUtil.getStringValue(batchClaimBean.getBatchNumberPsea());

	}

	public void setBatchClaimDatePsea(String obj) {

		batchClaimBean.setBatchClaimDatePsea(java.sql.Date.valueOf(obj));

	}

	public String getBatchClaimDatePsea() {
		return StringUtil
				.getStringValue(batchClaimBean.getBatchClaimDatePsea());

	}

	public void setLastUpdateBatchPsea(String obj) {

		batchClaimBean.setLastUpdateBatchPsea(java.sql.Date.valueOf(obj));

	}

	public String getLastUpdateBatchPsea() {
		return StringUtil.getStringValue(batchClaimBean
				.getLastUpdateBatchPsea());

	}

	public void setBatchClaimNumber(String obj) {

		batchClaimBean.setBatchClaimNumber(new String(obj));

	}

	public String getBatchClaimNumber() {
		return StringUtil.getStringValue(batchClaimBean.getBatchClaimNumber());

	}

	public void setBatchClaimDate(java.util.Date obj) {
		java.sql.Date res = null;
		
		if (obj != null){
			res = new java.sql.Date(obj.getTime());
		}
		//batchClaimBean.setBatchClaimDate(java.sql.Date.valueOf(obj));
		batchClaimBean.setBatchClaimDate(res);
		//batchClaimBean.setBatchClaimDate(StringUtil.getDateValue(obj,"dd-MM-yyyy"));

	}

	public java.util.Date getBatchClaimDate() {
	
		//return StringUtil.getStringValue(batchClaimBean.getBatchClaimDate());
		return batchClaimBean.getBatchClaimDate();

	}

	public void setBatchClaimInitialValue(String obj) {

		if (obj != null && !obj.equalsIgnoreCase("")){
			batchClaimBean.setBatchClaimInitialValue(StringUtil.getDoubleValue(obj,
						0));	
		}
//		

	}

	public String getBatchClaimInitialValue() {
		String result = "";
		if (batchClaimBean.getBatchClaimInitialValue() != null){
			result = StringUtil.getStringValue(batchClaimBean
			.getBatchClaimInitialValue());
		}
		return result;

	}

	public void setBatchClaimFinalValue(String obj) {

		batchClaimBean.setBatchClaimFinalValue(StringUtil
				.getDoubleValue(obj, 0));

	}

	public String getBatchClaimFinalValue() {
		return StringUtil.getStringValue(batchClaimBean
				.getBatchClaimFinalValue());

	}

	public void setBatchClaimPaidValue(String obj) {

		batchClaimBean
				.setBatchClaimPaidValue(StringUtil.getDoubleValue(obj, 0));

	}

	public String getBatchClaimPaidValue() {
		return StringUtil.getStringValue(batchClaimBean
				.getBatchClaimPaidValue());

	}

	public void setBatchClaimCloseDate(String obj) {

		batchClaimBean.setBatchClaimCloseDate(java.sql.Date.valueOf(obj));

	}

	public String getBatchClaimCloseDate() {
		return StringUtil.getStringValue(batchClaimBean
				.getBatchClaimCloseDate());

	}
	
	public void setInvoiceDate(String obj) {

		batchClaimBean.setInvoiceDate(java.sql.Date.valueOf(obj));

	}

	public String getInvoiceDate() {
		return StringUtil.getStringValue(batchClaimBean
				.getInvoiceDate());

	}

	public void setDescription(String obj) {

		batchClaimBean.setDescription(new String(obj));

	}

	public String getDescription() {
		return StringUtil.getStringValue(batchClaimBean.getDescription());

	}

	/*public void setTotalClaim(String obj) {

		batchClaimBean.setTotalClaim(StringUtil.getIntegerValue(obj, 0));

	}

	public String getTotalClaim() {
		return StringUtil.getStringValue(batchClaimBean.getTotalClaim());

	}*/
	
	

	public void setPaymentMethod(String obj) {

		if (obj != null && !obj.equalsIgnoreCase("")){
			PaymentMethod method = new PaymentMethod();
			method.setPaymentMethodId(Integer.valueOf(obj));
			batchClaimBean.setPaymentMethod(method);
		}

	}

	public String getTotalClaim() {
		String result = StringUtil.getStringValue(batchClaimBean.getTotalClaim());
		
		if (result == null || result.equalsIgnoreCase("")){
			result = totalClaim;
		}
		return result;
	}

	/**
	 * BUG FIX FOR 0000019: Update Batch Claim
	 * added conditional if not null and not empty
	 * @param totalClaim
	 */
	public void setTotalClaim(String totalClaim) {
		if (totalClaim != null && !totalClaim.equalsIgnoreCase("")){
			batchClaimBean.setTotalClaim(StringUtil.getIntegerValue(totalClaim, 0));
		}

		this.totalClaim = totalClaim;
	}

	public String getPaymentMethod() {
		String result = "";
		if (batchClaimBean.getPaymentMethod() != null){
			result= batchClaimBean.getPaymentMethod().getPaymentMethodId().toString();
		}
		return result;

	}

	public void setInvoiceNumber(String obj) {

		batchClaimBean.setInvoiceNumber(new String(obj));

	}

	public String getInvoiceNumber() {
		return StringUtil.getStringValue(batchClaimBean.getInvoiceNumber());

	}

	public void setIncompleteClaim(String obj) {

		batchClaimBean.setIncompleteClaim(StringUtil.getIntegerValue(obj, 0));

	}

	public String getIncompleteClaim() {
		return StringUtil.getStringValue(batchClaimBean.getIncompleteClaim());

	}


	// ---

	public void setPaymentRecipient(String obj) {

		if (obj != null && !obj.equals("")){
			PaymentRecipient recipient = new PaymentRecipient();
			recipient.setPaymentRecipientId(Integer.valueOf(obj));
			batchClaimBean.setPaymentRecipient(recipient);
		}

	}

	public String getPaymentRecipient() {
		String result = "";
		
		if (batchClaimBean.getPaymentRecipient() != null){
			result= batchClaimBean.getPaymentRecipient().getPaymentRecipientId().toString();
		}
		
		return result;

	}

	// ---

	public void setMemberId(String obj) {

		if (obj != null && !obj.equalsIgnoreCase("")){
			Member member = new Member();
			member.setMemberId(Integer.valueOf(obj));
			
			batchClaimBean.setMemberId(member);
		}

	}

	public String getMemberId() {
		String result = "";
		
		
		if (batchClaimBean.getMemberId() != null){
			result = batchClaimBean.getMemberId().getMemberId().toString();
		}
		
		return result;

	}

	// ---

	public void setClientId(String obj) {

		if (obj != null && !obj.equalsIgnoreCase("")){
			Client client = new Client();
			client.setClientId(Integer.valueOf(obj));
			batchClaimBean.setClientId(client);
		}

	}

	public String getClientId() {
		
		String result = "";
		if (batchClaimBean.getClientId() != null && batchClaimBean.getClientId().getClientId() != null){
		
			result = batchClaimBean.getClientId().getClientId().toString();
		}
		
		return result;

	}

	// ---

	public void setBatchClaimType(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			ClaimType ctype = new ClaimType();
			ctype.setClaimTypeId(Integer.valueOf(obj));
			
			batchClaimBean.setBatchClaimType(ctype);
		}

	}

	public String getBatchClaimType() {
		String result = "";
		if (batchClaimBean.getBatchClaimType() != null){
			result = batchClaimBean.getBatchClaimType().getClaimTypeId().toString();
		}
		
		return result;

	}

	// ---

	public void setMemberGroupId(String obj) {

		if (obj != null && !obj.equalsIgnoreCase("")){
			MemberGroup group = new MemberGroup();
			group.setMemberGroupId(Integer.valueOf(obj));
			batchClaimBean.setMemberGroupId(group);
		}

	}

	public String getMemberGroupId() {
		String result = "";
		if (batchClaimBean.getMemberGroupId() != null){
			result = batchClaimBean.getMemberGroupId().getMemberGroupId().toString();
		}
		return result;

	}

	// ---

	public void setProviderId(String obj) {

		if (obj != null && !obj.equalsIgnoreCase("")){
				Provider provider = new Provider();
				provider.setProviderId(Integer.valueOf(obj));
				batchClaimBean.setProviderId(provider);
		}

	}

	public String getProviderId() {
		String result = "";
		if (batchClaimBean.getProviderId() != null){
			result = batchClaimBean.getProviderId().getProviderId().toString();
		}
		return result;

	}

	// ---

	public void setPriority(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			Priority prio = new Priority();
			prio.setPriorityId(Integer.valueOf(obj));
			batchClaimBean.setPriority(prio);
		}

	}

	public String getPriority() {
		String result = "";
		if (batchClaimBean.getPriority() != null){
			result= batchClaimBean.getPriority().getPriorityId().toString();
		}
		
		return result;

	}
	public void setClaimCurrency (String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Currency currency = new Currency();
			currency.setCurrencyId(Integer.valueOf(obj));
			batchClaimBean.setClaimCurrency(currency);
		}
	}
	public String getClaimCurrency(){
		String result = "";
		
		if (batchClaimBean.getClaimCurrency() != null && batchClaimBean.getClaimCurrency().getCurrencyId() != null){
			result = batchClaimBean.getClaimCurrency().getCurrencyId().toString();
		}
		return result;
		
	}
	public void setPaymentCurrency (String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Currency currency = new Currency();
			currency.setCurrencyId(Integer.valueOf(obj));
			batchClaimBean.setPaymentCurrency(currency);
		}
	}
	public String getPaymentCurrency(){
		String result = "";
		
		if (batchClaimBean.getPaymentCurrency() != null && batchClaimBean.getPaymentCurrency().getCurrencyId() != null){
			result = batchClaimBean.getPaymentCurrency().getCurrencyId().toString();
		}
		return result;
	}

	public String getUnregistered() {
		return unregistered;
	}

	public void setUnregistered(String unregistered) {
		this.unregistered = unregistered;
	}

	public void setBiayaMaterai (String obj){
		
		batchClaimBean
		.setBiayaMaterai(StringUtil.getDoubleValue(obj, 0));

	}
	public String getBiayaMaterai (){		
		return StringUtil.getStringValue(batchClaimBean
				.getBiayaMaterai());
	}

	public void setClaimReceivingId (String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			ClaimReceiving receiving = new ClaimReceiving();
			receiving.setClaimReceivingId(Integer.valueOf(obj));
			batchClaimBean.setClaimReceivingId(receiving);
		}
	}
	public String getClaimReceivingId(){
		String result = "";
		
		if (batchClaimBean.getClaimReceivingId() != null && batchClaimBean.getClaimReceivingId().getClaimReceivingId() != null){
			result = batchClaimBean.getClaimReceivingId().getClaimReceivingId().toString();
		}
		return result;
	}

	public String getClaimReceivingNumber() {
		return claimReceivingNumber;
	}

	public void setClaimReceivingNumber(String claimReceivingNumber) {
		this.claimReceivingNumber = claimReceivingNumber;
	}
	
	
	
	
}
