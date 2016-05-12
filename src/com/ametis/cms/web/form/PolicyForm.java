
package com.ametis.cms.web.form;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;

import org.springframework.web.multipart.MultipartFile;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * Policy is a mapping of policy Table.
*/
public class PolicyForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewPolicy = false;
	private Policy policyBean ;
	private String clientName;
	private String memberGroupName;
	private String quotationNumber;
	
	private String brokerId;
	private String brokerName;
	private MultipartFile tcFile1;
	private MultipartFile tcFile2;
	private MultipartFile tcFile3;
	//Add by aju on 20150226, tc file name zzzzzz
	private String tcFileName1;
	private String tcFileName2;
	private String tcFileName3;
	
	public String getTcFileName1() {
		String result="";
		if (policyBean.getPolicyTcFile1() != null){
			result = policyBean.getPolicyTcFile1().substring(17);
		}
		
		return result;
	}
	public String getTcFileName2() {
		String result="";
		if (policyBean.getPolicyTcFile2() != null){
			result = policyBean.getPolicyTcFile2().substring(17);
		}
		
		return result;
	}
	public String getTcFileName3() {
		String result="";
		if (policyBean.getPolicyTcFile3() != null){
			result = policyBean.getPolicyTcFile3().substring(17);
		}
		
		return result;
	}
	
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public PolicyForm()
    {
    	this.policyBean = new Policy();
    	this.isNewPolicy = true;
    }
    public PolicyForm (Policy object){
		this.policyBean = object;
    }
    public boolean isNewPolicy (){

    	return this.isNewPolicy;
    }
	public Policy getPolicy (){
		return this.policyBean ;
	}
	public void setPolicy (Policy object){
		this.policyBean = object;
	}

			
	public void setPolicyId(String obj){

		policyBean.setPolicyId(StringUtil.getIntegerValue(obj,0));

	}

	public String getPolicyId(){
		return StringUtil.getStringValue(
		policyBean.getPolicyId());

	}
	
					public void setPolicyNumber(String obj){

		policyBean.setPolicyNumber(new String(obj));

	}

	public String getPolicyNumber(){
		return StringUtil.getStringValue(
		policyBean.getPolicyNumber());

	}
	
							
	public void setEffectiveDate(String obj){

		policyBean.setEffectiveDate(java.sql.Date.valueOf(obj));

	}

	public String getEffectiveDate(){
		return StringUtil.getStringValue(
		policyBean.getEffectiveDate());

	}

	
				
	public void setExpireDate(String obj){

		policyBean.setExpireDate(java.sql.Date.valueOf(obj));

	}

	public String getExpireDate(){
		return StringUtil.getStringValue(
		policyBean.getExpireDate());

	}

	
				
	public void setRequestDate(String obj){

		policyBean.setRequestDate(java.sql.Date.valueOf(obj));

	}

	public String getRequestDate(){
		return StringUtil.getStringValue(
		policyBean.getRequestDate());

	}

	
				
	public void setPolicyDate(String obj){

		policyBean.setPolicyDate(java.sql.Date.valueOf(obj));

	}

	public String getPolicyDate(){
		return StringUtil.getStringValue(
		policyBean.getPolicyDate());

	}


								

	// foreign affairs
	
	

	
	public void setClientId(String obj){
		
		if (obj != null && !obj.equalsIgnoreCase("")){
			Client fk = new Client();
			fk.setClientId(StringUtil.getIntegerValue(obj,0));
			policyBean.setClientId(fk);
		}

	}

	public String getClientId(){
		String result = "";
		
		if (policyBean.getClientId() != null){
			result = StringUtil.getStringValue(
					policyBean.getClientId().getClientId());
		}
		return result;
		

	}
	//---
	
	

	
	public void setMemberGroupId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			MemberGroup fk = new MemberGroup();
			fk.setMemberGroupId(StringUtil.getIntegerValue(obj,0));
			policyBean.setMemberGroupId(fk);
		}

	}

	public String getMemberGroupId(){
		String result = "";
		
		if (policyBean.getMemberGroupId() != null){
			result = StringUtil.getStringValue(
					policyBean.getMemberGroupId().getMemberGroupId());
		}
		return result;
		

	}
	//---
	
	

	
	public void setQuotationId(String obj){
		
		if (obj != null && !obj.equalsIgnoreCase("")){
			Quotation fk = new Quotation();
			fk.setQuotationId(StringUtil.getIntegerValue(obj,0));
			policyBean.setQuotationId(fk);
		}

	}

	public String getQuotationId(){
		String result = "";
		
		if (policyBean.getQuotationId() != null){
			result = StringUtil.getStringValue(
					policyBean.getQuotationId().getQuotationId());
		}
		return result;
		

	}
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
	public String getQuotationNumber() {
		return quotationNumber;
	}
	public void setQuotationNumber(String quotationNumber) {
		this.quotationNumber = quotationNumber;
	}
	public String getBrokerId() {
		return brokerId;
	}
	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
		
		if (brokerId !=null && !brokerId.equalsIgnoreCase("")){
			Broker broker = new Broker();
			broker.setBrokerId(Integer.valueOf(brokerId));
			
			policyBean.setBrokerId(broker);
		}
	}
	public String getBrokerName() {
		return brokerName;
	}
	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}
	public String getProductType() {
		String result = "";
		
		try {
			if (policyBean != null && policyBean.getProductType() != null){
				result = policyBean.getProductType().getProductTypeId().toString();
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	public void setProductType(String productType) {
		if (productType != null && !productType.equals("")){
			ProductType type = new ProductType();
			type.setProductTypeId(Integer.valueOf(productType));
			policyBean.setProductType(type);
		}
	}
	
	
	//Setting tarif type, by Aulia R.
	public String getTarifType() {
		String result = "";
		
		try {
			if (policyBean != null && policyBean.getTarifTypeId() != null){
				result = policyBean.getTarifTypeId().getTarifTypeId().toString();
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	public void setTarifType(String tarifType) {
		if (tarifType != null && !tarifType.equals("")){
			TarifType type = new TarifType();
			type.setTarifTypeId(Integer.valueOf(tarifType));
			policyBean.setTarifTypeId(type);
		}
	}
	//end
	
	public String getSwipeCardPrefix() {
		return this.policyBean.getSwipeCardPrefix();
	}
	public void setSwipeCardPrefix(String object) {
		this.policyBean.setSwipeCardPrefix(object);
	}
	public String getCustomerNumberPrefix() {
		return this.policyBean.getCustomerNumberPrefix();
	}
	public void setCustomerNumberPrefix(String object) {
		this.policyBean.setCustomerNumberPrefix(object);
	}
	public String getReimburseExpireLength() {
		String result = "";
		if (policyBean.getReimburseExpireDay() != null){
			result = StringUtil.getStringValue(policyBean.getReimburseExpireDay());
		}
		return result;
	}
	public void setReimburseExpireLength(String object) {
		if (object != null && !object.equalsIgnoreCase("")){
			this.policyBean.setReimburseExpireDay(Integer.valueOf(object));
		}
	}
	public String getReimburseMaxReceive() {
		String result = "";
		if (policyBean.getReimburseMaxReceiveDay() != null){
			result = StringUtil.getStringValue(policyBean.getReimburseMaxReceiveDay());
		}
		return result;
	}
	public void setReimburseMaxReceive(String object) {
		if (object != null && !object.equalsIgnoreCase("")){
			this.policyBean.setReimburseMaxReceiveDay(Integer.valueOf(object));
		}
	}
	public String getExcessExpireLength() {
		String result = "";
		if (policyBean.getExcessChargeExpireDay() != null){
			result = StringUtil.getStringValue(policyBean.getExcessChargeExpireDay());
		}
		return result;
	}
	public void setExcessExpireLength(String object) {
		if (object != null && !object.equalsIgnoreCase("")){
			this.policyBean.setExcessChargeExpireDay(Integer.valueOf(object));
		}
	}
	public String getExcessPaidUpfront() {
		String result = "";
		if (policyBean.getExcessSelfCovered() != null){
			result = StringUtil.getStringValue(policyBean.getExcessSelfCovered());
		}
		return result;
	}
	public void setExcessPaidUpfront(String object) {
		if (object != null && !object.equalsIgnoreCase("")){
			this.policyBean.setExcessSelfCovered(Integer.valueOf(object));
		}
	}
	public String getProviderAllocation() {
		String result = "";
		if (policyBean.getProviderAllocationType() != null){
			result = StringUtil.getStringValue(policyBean.getProviderAllocationType());
		}
		return result;
	}
	public void setProviderAllocation(String object) {
		if (object != null && !object.equalsIgnoreCase("")){
			this.policyBean.setProviderAllocationType(Integer.valueOf(object));
		}
	}
	public String getUsingSwipeCard() {
		String result = "0";
		
		if (policyBean.getUsingSwipeCard() != null){
			result = policyBean.getUsingSwipeCard().toString();
		}
		
		return result;
	}
	public void setUsingSwipeCard(String usingSwipeCard) {
		if (usingSwipeCard != null && !usingSwipeCard.equals("")){
			this.policyBean.setUsingSwipeCard((Integer.valueOf(usingSwipeCard)));
		}
	}
	public String getPolicyType() {
		String result = "0";
		
		if (policyBean.getPolicyType() != null){
			result = policyBean.getPolicyType().toString();
		}
		
		return result;
	}
	public void setPolicyType(String type) {
		if (type != null && !type.equals("")){
			this.policyBean.setPolicyType((Integer.valueOf(type)));
		}
	}
	
	public String getFundWarningPercentage() {
		String result = "";
		if (policyBean.getFundWarningPercentage() != null){
			result = policyBean.getFundWarningPercentage().toString();
		}
		return result;
	}
	public void setFundWarningPercentage(String fundWarningPercentage) {
		if (fundWarningPercentage != null && !fundWarningPercentage.equals("")){
			this.policyBean.setFundWarningPercentage((Double.valueOf(fundWarningPercentage)));
		}
	}
	

	public String getAsoCoverageList() {
		String result = policyBean.getAsoCoverageList();
		
		return result;
	}
	public void setAsoCoverageList(String asoCoverageList) {
		this.policyBean.setAsoCoverageList(asoCoverageList);
	}
	
	public String getBlockedPercentage() {
		String result = "";
		if (policyBean.getBlockFundPercentage() != null){
			result = policyBean.getBlockFundPercentage().toString();
		}
		return result;
	}
	public void setBlockedPercentage(String fundWarningPercentage) {
		if (fundWarningPercentage != null && !fundWarningPercentage.equals("")){
			this.policyBean.setBlockFundPercentage((Double.valueOf(fundWarningPercentage)));
		}
	}
	
	public String getIsUsingFloatingFund() {
		String result = "";
		if (policyBean.getIsUsingFloatingFund() != null){
			result = policyBean.getIsUsingFloatingFund().toString();
		}
		return result;
	}
	public void setIsUsingFloatingFund(String isUsingFloatingFund) {
		if (isUsingFloatingFund != null && !isUsingFloatingFund.equals("")){
			this.policyBean.setIsUsingFloatingFund((Integer.valueOf(isUsingFloatingFund)));
		}
	}
	
	
	public String getMinimumPolicyFund() {
		String result = "";
		if (policyBean.getMinimumPolicyFund() != null){
			
			BigDecimal bd = new BigDecimal(policyBean.getMinimumPolicyFund());
			result = bd.toPlainString();
		}
		return result;
	}
	public void setMinimumPolicyFund(String obj) {
		
		System.out.println("MIN FUND = " + obj);
		if (obj != null && !obj.equals("")){
			this.policyBean.setMinimumPolicyFund((Double.valueOf(obj)));
		}
	}
	public String getTotalPremium() {
		String result = "";
		if (policyBean.getTotalPremiumValue() != null){
			
			BigDecimal bd = new BigDecimal(policyBean.getTotalPremiumValue());
			result = bd.toPlainString();
		}
		return result;
	}
	public void setTotalPremium(String obj) {
		
		System.out.println("MIN FUND = " + obj);
		if (obj != null && !obj.equals("")){
			this.policyBean.setTotalPremiumValue((Double.valueOf(obj)));
		}
	}
	public String getBlockingFundLimit() {
		String result = "";
		if (policyBean.getFundBlockingLimit() != null){
			
			BigDecimal bd = new BigDecimal(policyBean.getFundBlockingLimit());
			result = bd.toPlainString();
		}
		return result;
	}
	public void setBlockingFundLimit(String obj) {
		
		System.out.println("MIN FUND = " + obj);
		if (obj != null && !obj.equals("")){
			this.policyBean.setFundBlockingLimit((Double.valueOf(obj)));
		}
	}

	public String getInitialExcessFundLimit() {
		String result = "";
		if (policyBean.getInitialFundExcessValue() != null){
			
			BigDecimal bd = new BigDecimal(policyBean.getInitialFundExcessValue());
			result = bd.toPlainString();
		}
		return result;
	}
	public void setInitialExcessFundLimit(String obj) {
		
		System.out.println("MIN FUND = " + obj);
		if (obj != null && !obj.equals("")){
			this.policyBean.setInitialFundExcessValue((Double.valueOf(obj)));
		}
	}

	public String getInitialFundLimit() {
		String result = "";
		if (policyBean.getInitialFundValue() != null){
			
			BigDecimal bd = new BigDecimal(policyBean.getInitialFundValue());
			result = bd.toPlainString();
		}
		return result;
	}
	public void setInitialFundLimit(String obj) {
		
		System.out.println("MIN FUND = " + obj);
		if (obj != null && !obj.equals("")){
			this.policyBean.setInitialFundValue((Double.valueOf(obj)));
		}
	}

	public String getCobStatus() {
		String result = "0";
		
		if (policyBean.getIsCobEnabled() != null){
			result = policyBean.getIsCobEnabled().toString();
		}
		
		return result;
	}
	public void setCobStatus(String status) {
		if (status != null && !status.equals("")){
			this.policyBean.setIsCobEnabled((Integer.valueOf(status)));
		}
	}
	/**
	 *  BUG FIX 0000057: [Policy] Card Type tidak di-simpan di database, sehingga tidak dimunculkan pada detail Policy
	 *  
	 * @return
	 */
	public String getCardTypeId() {
		String result = "";
		
		if (policyBean.getCardTypeId() != null){
			result = policyBean.getCardTypeId().getCardTypeId().toString();
		}
		
		return result;
	}
	public void setCardTypeId(String status) {
		if (status != null && !status.equals("")){
			CardType cardType = new CardType();
			cardType.setCardTypeId(Integer.valueOf(status));
			
			this.policyBean.setCardTypeId(cardType);
		}
	}
	public String getPolicyNotes() {
		String result = policyBean.getPolicyTcNotes();
		
		
		return result;
	}
	public void setPolicyNotes(String status) {
		if (status != null && !status.equals("")){
			this.policyBean.setPolicyTcNotes(status);
		}
	}
	public String getGroupCode() {
		String result = policyBean.getGroupCode();
		
		
		return result;
	}
	public void setGroupCode(String status) {
		if (status != null && !status.equals("")){
			this.policyBean.setGroupCode(status);
		}
	}
	public MultipartFile getTcFile1() {
		return tcFile1;
	}
	public void setTcFile1(MultipartFile tcFile1) {
		this.tcFile1 = tcFile1;
	}
	public MultipartFile getTcFile2() {
		return tcFile2;
	}
	public void setTcFile2(MultipartFile tcFile2) {
		this.tcFile2 = tcFile2;
	}
	public MultipartFile getTcFile3() {
		return tcFile3;
	}
	public void setTcFile3(MultipartFile tcFile3) {
		this.tcFile3 = tcFile3;
	}
	
	
}
