package com.ametis.cms.web.form;

import java.math.BigDecimal;

import com.ametis.cms.datamodel.BatchClaim;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.Outstanding;
import com.ametis.cms.datamodel.Payment;
import com.ametis.cms.datamodel.PaymentStatus;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * Payment is a mapping of payment Table.
 */
public class PaymentForm implements java.io.Serializable
// extends+

// extends-
{

	private boolean isNewPayment = false;
	private Payment paymentBean;
	private String paymentRecipient;
	private String batchNumber;

	/*
	 * <form>Bean merupakan representasi dari "SINGLE" table dan misalkan ada
	 * form2 yang merupakan referensi dari tabel lain bikin aja field2 bean yang
	 * mengacu ke referensi itu biar nanti automatic loading
	 * 
	 */

	public PaymentForm() {
		this.paymentBean = new Payment();
		this.isNewPayment = true;
	}

	public PaymentForm(Payment object) {
		this.paymentBean = object;
	}

	public boolean isNewPayment() {

		return this.isNewPayment;
	}

	public Payment getPayment() {
		return this.paymentBean;
	}

	public void setPayment(Payment object) {
		this.paymentBean = object;
	}

	public void setPaymentId(String obj) {

		paymentBean.setPaymentId(StringUtil.getIntegerValue(obj, 0));

	}

	public String getPaymentId() {
		return StringUtil.getStringValue(paymentBean.getPaymentId());

	}

	public void setPaymentValue(String obj) {

		double paymentValue = StringUtil.getDoubleValue(obj, 0);
		paymentBean.setPaymentValue(paymentValue);

	}

	public String getPaymentValue() {
		return StringUtil.getStringValue(paymentBean.getPaymentValue());

	}
	
	public void setBatchClaim (String batchClaim){
		
		if (batchClaim != null && !batchClaim.equalsIgnoreCase("")){
			BatchClaim batchClaimObj = new BatchClaim();
			batchClaimObj.setBatchClaimId(Integer.valueOf(batchClaim));
			paymentBean.setBatchClaim(batchClaimObj);
		}
		
	}
	public String getBatchClaim(){
		
		String result = "";
		if (paymentBean.getBatchClaim() != null){
			result =  paymentBean.getBatchClaim().getBatchClaimId().toString();
		}
		return result;
	}
	

	public void setRemarks(String obj) {

		paymentBean.setRemarks(new String(obj));

	}

	public String getRemarks() {
		return StringUtil.getStringValue(paymentBean.getRemarks());

	}

	public void setPaymentConfirmDate (String obj){
		paymentBean.setPaymentConfirmDate(java.sql.Date.valueOf(obj));
	}
	public String getPaymentConfirmDate (){
		String result = "";
		
		if (paymentBean.getPaymentConfirmDate() != null){
			result = StringUtil.getStringValue(paymentBean.getPaymentConfirmDate());
		}
		
		return result;
	}
	public void setBankName(String obj) {

		paymentBean.setBankName(obj);

	}

	public String getBankName() {
		return StringUtil.getStringValue(paymentBean.getBankName());

	}

	public void setBankBranch(String obj) {
		paymentBean.setBankBranch(obj);

	}

	public String getBankBranch() {
		return StringUtil.getStringValue(paymentBean.getBankBranch());
	}

	public void setAccountNumber(String obj) {

		paymentBean.setAccountNumber(obj);

	}

	public String getAccountNumber() {
		return StringUtil.getStringValue(paymentBean.getAccountNumber());

	}

	public void setGiroNumber(String obj) {

		paymentBean.setGiroNumber(obj);

	}

	public String getGiroNumber() {
		return StringUtil.getStringValue(paymentBean.getGiroNumber());

	}

	public void setPayeeName(String obj) {

		paymentBean.setPayeeName(obj);

	}

	public String getPayeeName() {
		return StringUtil.getStringValue(paymentBean.getPayeeName());

	}

	public void setPaymentTime(String obj) {

		paymentBean.setPaymentTime(java.sql.Date.valueOf(obj));

	}

	public String getPaymentTime() {
		return StringUtil.getStringValue(paymentBean.getPaymentTime());

	}

	public void setPaymentStatus(String obj) {


		if (obj != null && !obj.equalsIgnoreCase("")){
		
			PaymentStatus status = new PaymentStatus();
			status.setPaymentStatusId(StringUtil.getIntegerValue(obj, 0));
			paymentBean.setPaymentStatus(status);
		}
		
		

	}

	public String getPaymentStatus() {
		
		String result = "";
		
		if (paymentBean.getPaymentStatus() != null){
			result = StringUtil.getStringValue(paymentBean.getPaymentStatus().getPaymentStatusId());
		}
		
		return result;

	}

	public void setCreatedTime(String obj) {

		paymentBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime() {
		return StringUtil.getStringValue(paymentBean.getCreatedTime());

	}

	public void setCreatedBy(String obj) {

		paymentBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy() {
		return StringUtil.getStringValue(paymentBean.getCreatedBy());

	}

	public void setDeletedTime(String obj) {

		paymentBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime() {
		return StringUtil.getStringValue(paymentBean.getDeletedTime());

	}

	public void setDeletedBy(String obj) {

		paymentBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy() {
		return StringUtil.getStringValue(paymentBean.getDeletedBy());

	}

	public void setModifiedTime(String obj) {

		paymentBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime() {
		return StringUtil.getStringValue(paymentBean.getModifiedTime());

	}

	public void setModifiedBy(String obj) {

		paymentBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy() {
		return StringUtil.getStringValue(paymentBean.getModifiedBy());

	}

	public void setDeletedStatus(String obj) {

		paymentBean.setDeletedStatus(StringUtil.getIntegerValue(obj, 0));

	}

	public String getDeletedStatus() {
		return StringUtil.getStringValue(paymentBean.getDeletedStatus());

	}

	public void setPaymentNumber(String obj) {
		paymentBean.setPaymentNumber(obj);
	}

	public String getPaymentNumber() {
		return paymentBean.getPaymentNumber();
	}

	public void setValueDescription(String obj) {
		paymentBean.setValueDescription(obj);
	}

	public String getValueDescription() {
		return paymentBean.getValueDescription();
	}

	// foreign affairs

	public void setOutstandingId(String obj) {
		Outstanding fk = new Outstanding();
		fk.setOutstandingId(StringUtil.getIntegerValue(obj, 0));
		paymentBean.setOutstandingId(fk);

	}

	public String getOutstandingId() {
		return StringUtil.getStringValue(paymentBean.getOutstandingId()
				.getOutstandingId());

	}

	// ---

	public void setMemberId(String obj) {
		
		if (obj != null && !obj.trim().equals("")){
			Member fk = new Member();
			fk.setMemberId(StringUtil.getIntegerValue(obj, 0));
			paymentBean.setMemberId(fk);
		}

	}

	public String getMemberId() {
		
		if (paymentBean.getMemberId() != null){
		
			return StringUtil.getStringValue(paymentBean.getMemberId()
				.getMemberId());
		}
		else {
			return "";
		}

	}

	// ---

	public void setMemberGroupId (String obj){
		if (obj != null && !obj.trim().equals("")){
			MemberGroup fk = new MemberGroup();
			fk.setMemberGroupId(StringUtil.getIntegerValue(obj, 0));
			paymentBean.setMemberGroup(fk);
		}
	}
	public void setProviderId(String obj) {
		
		if (obj != null && !obj.trim().equals("")){
		
			Provider fk = new Provider();
			fk.setProviderId(StringUtil.getIntegerValue(obj, 0));
			paymentBean.setProviderId(fk);
		}

	}

	public String getProviderId() {
		
		if (paymentBean.getProviderId() != null){
			return StringUtil.getStringValue(paymentBean.getProviderId()
				.getProviderId());
		}
		else {
			return "";
		}

	}

	public String getPaymentRecipient() {
		return paymentRecipient;
	}

	public void setPaymentRecipient(String paymentRecipient) {
		this.paymentRecipient = paymentRecipient;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	public void setConfirmedPaymentValue (String obj){
		double paymentValue = StringUtil.getDoubleValue(obj, 0);
		paymentBean.setConfirmedPaymentValue(paymentValue);
		
	}
	public String getConfirmedPaymentValue(){
		return StringUtil.getStringValue(paymentBean.getConfirmedPaymentValue());
	}

	public void setAdministrationCost (String obj){
		if (obj != null && !obj.equals("")){
			double paymentValue = StringUtil.getDoubleValue(obj, 0);
			paymentBean.setAdministrationCost(paymentValue);
		}
		
	}
	public String getAdministrationCost(){
		return StringUtil.getStringValue(paymentBean.getAdministrationCost());
	}

	public void setMaterai (String obj){
		if (obj != null && !obj.equals("")){
			double paymentValue = StringUtil.getDoubleValue(obj, 0);
			paymentBean.setBiayaMaterai(paymentValue);
		}
		
	}
	public String getMaterai(){
		return StringUtil.getStringValue(paymentBean.getBiayaMaterai());
	}

	public void setDiscount (String obj){
		if (obj != null && !obj.equals("")){
			double paymentValue = StringUtil.getDoubleValue(obj, 0);
			paymentBean.setDiscount(paymentValue);
		}
		
	}
	public String getDiscount(){
		return StringUtil.getStringValue(paymentBean.getDiscount());
	}
	public void setClaimPaymentValue (String obj){
		if (obj != null && !obj.equals("")){
			double paymentValue = StringUtil.getDoubleValue(obj, 0);
			paymentBean.setClaimPaymentValue(paymentValue);
		}
		
	}
	public String getClaimPaymentValue(){
		String result = "";
		
		if (paymentBean.getClaimPaymentValue() != null){
			BigDecimal bigDecimal = new BigDecimal(paymentBean.getClaimPaymentValue());
			result = bigDecimal.toPlainString();
		}
		
		return result;
	}
	
}
