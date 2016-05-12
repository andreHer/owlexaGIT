
package com.ametis.cms.web.form;

import java.math.BigDecimal;

import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.Currency;
import com.ametis.cms.datamodel.ExcessCharge;
import com.ametis.cms.datamodel.Fund;
import com.ametis.cms.datamodel.FundCategory;
import com.ametis.cms.datamodel.Outstanding;
import com.ametis.cms.datamodel.PaymentStatus;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.PolicyCoverageFund;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * Fund is a mapping of fund Table.
*/
public class FundForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewFund = false;
	private Fund fundBean ;
	private String clientName;
	private String providerName;
	private String coverageName;
	private String policyNumber;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public FundForm()
    {
    	this.fundBean = new Fund();
    	this.isNewFund = true;
    }
    public FundForm (Fund object){
		this.fundBean = object;
    }
    public boolean isNewFund (){

    	return this.isNewFund;
    }
	public Fund getFund (){
		return this.fundBean ;
	}
	public void setFund (Fund object){
		this.fundBean = object;
	}

			
	public void setFundId(String obj){

		fundBean.setFundId(StringUtil.getIntegerValue(obj,0));

	}

	public String getFundId(){
		return StringUtil.getStringValue(
		fundBean.getFundId());

	}
	
				
	public void setFundValue(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			fundBean.setFundValue(Double.valueOf(obj));
		}

	}

	public String getFundValue(){
		String result = "";
		
		if (fundBean.getFundValue() != null){
			BigDecimal bc = new BigDecimal(fundBean.getFundValue());
			result = bc.toPlainString();
		}
		return result;

	}
	
							
	public void setFundStatus(PaymentStatus obj){

		fundBean.setFundStatus(obj);

	}

	public PaymentStatus getFundStatus(){
		return fundBean.getFundStatus();

	}
	
	public void setFundCode(String obj){

		fundBean.setFundCode(new String(obj));

	}

	public String getFundCode(){
		return StringUtil.getStringValue(
		fundBean.getFundCode());

	}
	
					public void setDescription(String obj){

		fundBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		fundBean.getDescription());

	}
	public String getFundCurrency(){
		String result = "";
		Currency cur = fundBean.getFundCurrency();
		if (cur != null){
			result = cur.getCurrencyId() + "";
		}
		return result;
	}
	public void setFundCurrency (String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Currency cur = new Currency();
			cur.setCurrencyId(Integer.valueOf(obj));
			this.fundBean.setFundCurrency(cur);
		}
	}
	
	public void setFundType (String obj){
		
		if (obj != null && !obj.equalsIgnoreCase("")){
			FundCategory fc = new FundCategory();
			fc.setFundCategoryId(Integer.valueOf(obj));
			fundBean.setFundType(fc);
		}
		
		
		
	}
	
	public String getFundType (){
		String result = "";
		
		FundCategory fc = fundBean.getFundType();
		if (fc != null){
			result = fc.getFundCategoryId() + "";
		}
		return result;
	}
				
	public void setFundTime(String obj){

		fundBean.setFundTime(java.sql.Date.valueOf(obj));

	}

	public String getFundTime(){
		return StringUtil.getStringValue(
		fundBean.getFundTime());

	}

	
				
						public void setApprovedBy(String obj){

		fundBean.setApprovedBy(new String(obj));

	}

	public String getApprovedBy(){
		return StringUtil.getStringValue(
		fundBean.getApprovedBy());

	}
	
		

	// foreign affairs
	
	
	public void setExcessCharge (ExcessCharge obj){
		fundBean.setExcessCharge(obj);
	}
	public ExcessCharge getExcessCharge (){
		return fundBean.getExcessCharge();
	}
	
	public void setOutstanding (Outstanding obj){
		fundBean.setOutstanding(obj);
	}
	public Outstanding getOutstanding(){
		return fundBean.getOutstanding();
	}
	public void setClientId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Client client = new Client();
			client.setClientId(Integer.valueOf(obj));
			fundBean.setClientId(client);
		}

	}

	public String getClientId(){
		String result = "";
		
		if (fundBean.getClientId() != null){
			result = fundBean.getClientId().getClientId().toString();
		}
		return result;

	}
	public void setProviderId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Provider provider = new Provider();
			provider.setProviderId(Integer.valueOf(obj));
			fundBean.setProviderId(provider);
		}

	}

	public String getProviderId(){
		String result = "";
		
		if (fundBean.getProviderId() != null){
			result = fundBean.getProviderId().getProviderId().toString();
		}
		return result;

	}
	public void setPolicyId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Policy policy = new Policy();
			policy.setPolicyId(Integer.valueOf(obj));
			fundBean.setPolicyId(policy);
		}

	}

	public String getPolicyId(){
		String result = "";
		
		if (fundBean.getPolicyId() != null){
			result = fundBean.getPolicyId().getPolicyId().toString();
		}
		return result;

	}
	public void setPolicyCoverageId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			PolicyCoverageFund covFund = new PolicyCoverageFund();
			covFund.setPolicyCoverageFundId(Integer.valueOf(obj));
			fundBean.setPolicyCoverageFundId(covFund);
		}

	}

	public String getPolicyCoverageId(){
		String result = "";
		
		if (fundBean.getPolicyCoverageFundId() != null){
			result = fundBean.getPolicyCoverageFundId().getPolicyCoverageFundId().toString();
		}
		return result;

	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public String getCoverageName() {
		return coverageName;
	}
	public void setCoverageName(String coverageName) {
		this.coverageName = coverageName;
	}
	public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	
	
}
