
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * Quotation is a mapping of quotation Table.
*/
public class QuotationForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewQuotation = false;
	private Quotation quotationBean ;
	private String clientName;
	private String memberGroupName;
	private String brokerName;
	private String branchName;
	
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public QuotationForm()
    {
    	this.quotationBean = new Quotation();
    	this.isNewQuotation = true;
    }
    public QuotationForm (Quotation object){
		this.quotationBean = object;
    }
    public boolean isNewQuotation (){

    	return this.isNewQuotation;
    }
	public Quotation getQuotation (){
		return this.quotationBean ;
	}
	public void setQuotation (Quotation object){
		this.quotationBean = object;
	}

			
	public void setQuotationId(String obj){

		quotationBean.setQuotationId(StringUtil.getIntegerValue(obj,0));

	}

	public String getQuotationId(){
		return StringUtil.getStringValue(
		quotationBean.getQuotationId());

	}
	
					public void setQuotationNo(String obj){

		quotationBean.setQuotationNo(new String(obj));

	}

	public String getQuotationNo(){
		return StringUtil.getStringValue(
		quotationBean.getQuotationNo());

	}
	
				
	public void setQuotationDate(String obj){

		quotationBean.setQuotationDate(java.sql.Date.valueOf(obj));

	}

	public String getQuotationDate(){
		return StringUtil.getStringValue(
		quotationBean.getQuotationDate());

	}

	
				
	public void setRequestDate(String obj){

		quotationBean.setRequestDate(java.sql.Date.valueOf(obj));

	}

	public String getRequestDate(){
		return StringUtil.getStringValue(
		quotationBean.getRequestDate());

	}

	
							
	public void setEffectiveDate(String obj){

		quotationBean.setEffectiveDate(java.sql.Date.valueOf(obj));

	}

	public String getEffectiveDate(){
		return StringUtil.getStringValue(
		quotationBean.getEffectiveDate());

	}

	
				
	public void setRenewalDate(String obj){

		quotationBean.setRenewalDate(java.sql.Date.valueOf(obj));

	}

	public String getRenewalDate(){
		return StringUtil.getStringValue(
		quotationBean.getRenewalDate());

	}

	
				
	public void setPaymentMode(String obj){
		
		if (obj != null && !obj.equalsIgnoreCase("")){
			PaymentMode mode = new PaymentMode();
			mode.setPaymentModeId(Integer.valueOf(obj));
			quotationBean.setPaymentMode(mode);
		}

	}

	public String getPaymentMode(){
		String result = "";
		
		if (quotationBean.getPaymentMode() != null){
			result = StringUtil.getStringValue(
					quotationBean.getPaymentMode().getPaymentModeId());
		}
		return result;

	}
	
					public void setRemarks(String obj){

		quotationBean.setRemarks(new String(obj));

	}

	public String getRemarks(){
		return StringUtil.getStringValue(
		quotationBean.getRemarks());

	}
	
				
	public void setQuotationTypeId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			QuotationType qType = new QuotationType();
			qType.setQuotationTypeId(Integer.valueOf(obj));
			quotationBean.setQuotationTypeId(qType);
		}

	}

	public String getQuotationTypeId(){
		String result = "";
		
		if (quotationBean.getQuotationTypeId() != null){
			result = StringUtil.getStringValue(
					quotationBean.getQuotationTypeId().getQuotationTypeId());
		}
		return result;

	}
	

	public void setCardTypeId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			CardType qType = new CardType();
			qType.setCardTypeId(Integer.valueOf(obj));
			quotationBean.setCardTypeId(qType);
		}

	}

	public String getCardTypeId(){
		String result = "";
		
		if (quotationBean.getCardTypeId() != null){
			result = StringUtil.getStringValue(
					quotationBean.getCardTypeId().getCardTypeId());
		}
		return result;

	}
	public void setTpaId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Tpa tpa = new Tpa();
			tpa.setTpaId(Integer.valueOf(obj));
			quotationBean.setTpaId(tpa);
		}

	}

	public String getTpaId(){
		String result = "";
		
		if (quotationBean.getTpaId() != null){
			result = StringUtil.getStringValue(
					quotationBean.getTpaId().getTpaId());
		}
		return result;

	}
	public void setIsIndividual(String obj){

		quotationBean.setIsIndividual(StringUtil.getIntegerValue(obj,0));

	}

	public String getIsIndividual(){
		return StringUtil.getStringValue(
		quotationBean.getIsIndividual());

	}
	
				
	public void setIsFamilyPlan(String obj){

		quotationBean.setIsFamilyPlan(StringUtil.getIntegerValue(obj,0));

	}

	public String getIsFamilyPlan(){
		return StringUtil.getStringValue(
		quotationBean.getIsFamilyPlan());

	}
	
				
	public void setIsDiscountGroupByEmployee(String obj){

		quotationBean.setIsDiscountGroupByEmployee(StringUtil.getIntegerValue(obj,0));

	}

	public String getIsDiscountGroupByEmployee(){
		return StringUtil.getStringValue(
		quotationBean.getIsDiscountGroupByEmployee());

	}
	
				
	public void setInstallmentAmount(String obj){

		quotationBean.setInstallmentAmount(StringUtil.getIntegerValue(obj,0));

	}

	public String getInstallmentAmount(){
		return StringUtil.getStringValue(
		quotationBean.getInstallmentAmount());

	}
	
				
	public void setAggregateLimit(String obj){

		quotationBean.setAggregateLimit(StringUtil.getDoubleValue(obj,0));

	}

	public String getAggregateLimit(){
		return StringUtil.getStringValue(
		quotationBean.getAggregateLimit());

	}
	
				
	public void setToc(String obj){

		quotationBean.setToc(StringUtil.getIntegerValue(obj,0));

	}

	public String getToc(){
		return StringUtil.getStringValue(
		quotationBean.getToc());

	}
	
				
	public void setPayorId(String obj){

		quotationBean.setPayorId(StringUtil.getIntegerValue(obj,0));

	}

	public String getPayorId(){
		return StringUtil.getStringValue(
		quotationBean.getPayorId());

	}
	
							
	public void setIsAgentCommisionGrossPremi(String obj){

		quotationBean.setIsAgentCommisionGrossPremi(StringUtil.getIntegerValue(obj,0));

	}

	public String getIsAgentCommisionGrossPremi(){
		return StringUtil.getStringValue(
		quotationBean.getIsAgentCommisionGrossPremi());

	}
	
							
	public void setMaxChildren(String obj){

		quotationBean.setMaxChildren(StringUtil.getIntegerValue(obj,0));

	}

	public String getMaxChildren(){
		return StringUtil.getStringValue(
		quotationBean.getMaxChildren());

	}
	
				
	public void setIsAsoPolicy(String obj){

		quotationBean.setIsAsoPolicy(StringUtil.getIntegerValue(obj,0));

	}

	public String getIsAsoPolicy(){
		return StringUtil.getStringValue(
		quotationBean.getIsAsoPolicy());

	}
	
				
	public void setAsoDeposit(String obj){

		quotationBean.setAsoDeposit(StringUtil.getDoubleValue(obj,0));

	}

	public String getAsoDeposit(){
		return StringUtil.getStringValue(
		quotationBean.getAsoDeposit());

	}
	
				
	public void setBrcDate(String obj){

		quotationBean.setBrcDate(java.sql.Date.valueOf(obj));

	}

	public String getBrcDate(){
		return StringUtil.getStringValue(
		quotationBean.getBrcDate());

	}

	
					public void setRequestedBy(String obj){

		quotationBean.setRequestedBy(new String(obj));

	}

	public String getRequestedBy(){
		return StringUtil.getStringValue(
		quotationBean.getRequestedBy());

	}
	
				
	public void setIsUnitPremi(String obj){

		quotationBean.setIsUnitPremi(StringUtil.getIntegerValue(obj,0));

	}

	public String getIsUnitPremi(){
		return StringUtil.getStringValue(
		quotationBean.getIsUnitPremi());

	}
	
				
	public void setIsWifeOnly(String obj){

		quotationBean.setIsWifeOnly(StringUtil.getIntegerValue(obj,0));

	}

	public String getIsWifeOnly(){
		return StringUtil.getStringValue(
		quotationBean.getIsWifeOnly());

	}
	
				
	public void setUangPertanggungan(String obj){

		quotationBean.setUangPertanggungan(StringUtil.getDoubleValue(obj,0));

	}

	public String getUangPertanggungan(){
		return StringUtil.getStringValue(
		quotationBean.getUangPertanggungan());

	}
	
				
	public void setUangPremi(String obj){

		quotationBean.setUangPremi(StringUtil.getDoubleValue(obj,0));

	}

	public String getUangPremi(){
		return StringUtil.getStringValue(
		quotationBean.getUangPremi());

	}
	
				
	public void setTotalMember(String obj){

		quotationBean.setTotalMember(StringUtil.getIntegerValue(obj,0));

	}

	public String getTotalMember(){
		return StringUtil.getStringValue(
		quotationBean.getTotalMember());

	}
	
				
	public void setBudgetPremi(String obj){

		quotationBean.setBudgetPremi(StringUtil.getDoubleValue(obj,0));

	}

	public String getBudgetPremi(){
		return StringUtil.getStringValue(
		quotationBean.getBudgetPremi());

	}
	
				
	public void setComission(String obj){

		quotationBean.setComission(StringUtil.getDoubleValue(obj,0));

	}

	public String getComission(){
		return StringUtil.getStringValue(
		quotationBean.getComission());

	}
	
				
	public void setClaimRatio(String obj){

		quotationBean.setClaimRatio(StringUtil.getDoubleValue(obj,0));

	}

	public String getClaimRatio(){
		return StringUtil.getStringValue(
		quotationBean.getClaimRatio());

	}
	
								

	// foreign affairs
	
	

	
	public void setBrokerId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Broker fk = new Broker();
			fk.setBrokerId(StringUtil.getIntegerValue(obj,0));
			quotationBean.setBrokerId(fk);
		}

	}

	public String getBrokerId(){
		String result = "";
		
		if (quotationBean.getBrokerId() != null){
			result =StringUtil.getStringValue(
					quotationBean.getBrokerId().getBrokerId());
		}
		return result;

	}
	//---
	
	

	
	public void setClientId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Client fk = new Client();
			fk.setClientId(StringUtil.getIntegerValue(obj,0));
			quotationBean.setClientId(fk);
		}

	}

	public String getClientId(){
		String result = "";
		
		if (quotationBean.getClientId() != null){
			result = StringUtil.getStringValue(
					quotationBean.getClientId().getClientId());
		}
		return result;

	}
	//---
	
	

	
	public void setMemberGroupId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			MemberGroup fk = new MemberGroup();
			fk.setMemberGroupId(StringUtil.getIntegerValue(obj,0));
			quotationBean.setMemberGroupId(fk);
		}

	}

	public String getMemberGroupId(){
		String result = "";
		
		if (quotationBean.getMemberGroupId() != null){
			result = StringUtil.getStringValue(
					quotationBean.getMemberGroupId().getMemberGroupId());
		}
		return result;

	}
	//---
	
	

	
	public void setCurrencyId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Currency fk = new Currency();
			fk.setCurrencyId(StringUtil.getIntegerValue(obj,0));
			quotationBean.setCurrencyId(fk);
		}

	}

	public String getCurrencyId(){
		String result = "";
		if (quotationBean.getCurrencyId() != null){
			result = StringUtil.getStringValue(
					quotationBean.getCurrencyId().getCurrencyId());
		}
		return result;

	}
	//---
	
	

	
	public void setBranchId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Branch fk = new Branch();
			fk.setBranchId(StringUtil.getIntegerValue(obj,0));
			quotationBean.setBranchId(fk);
		}

	}

	public String getBranchId(){
		String result = "";
		
		if (quotationBean.getBranchId() != null){
			result = StringUtil.getStringValue(
					quotationBean.getBranchId().getBranchId());
		}
		return result;

	}
	//---
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getMemberGroupName() {
		return memberGroupName;
	}
	public void setMemberGroupName(String memberGroupName) {
		this.memberGroupName = memberGroupName;
	}
	public String getBrokerName() {
		return brokerName;
	}
	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	

	
	
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
