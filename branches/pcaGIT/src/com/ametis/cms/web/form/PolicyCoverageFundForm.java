
package com.ametis.cms.web.form;

import java.math.BigDecimal;

import javax.persistence.Column;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * PolicyCoverageFund is a mapping of policy_coverage_fund Table.
*/
public class PolicyCoverageFundForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewPolicyCoverageFund = false;
	private PolicyCoverageFund policyCoverageFundBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public PolicyCoverageFundForm()
    {
    	this.policyCoverageFundBean = new PolicyCoverageFund();
    	this.isNewPolicyCoverageFund = true;
    }
    public PolicyCoverageFundForm (PolicyCoverageFund object){
		this.policyCoverageFundBean = object;
    }
    public boolean isNewPolicyCoverageFund (){

    	return this.isNewPolicyCoverageFund;
    }
	public PolicyCoverageFund getPolicyCoverageFund (){
		return this.policyCoverageFundBean ;
	}
	public void setPolicyCoverageFund (PolicyCoverageFund object){
		this.policyCoverageFundBean = object;
	}

			
	public void setPolicyCoverageFundId(String obj){

		policyCoverageFundBean.setPolicyCoverageFundId(StringUtil.getIntegerValue(obj,0));

	}

	public String getPolicyCoverageFundId(){
		return StringUtil.getStringValue(
		policyCoverageFundBean.getPolicyCoverageFundId());

	}
	
				
	public void setCaseCategoryId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			CaseCategory cc = new CaseCategory();
			cc.setCaseCategoryId(Integer.valueOf(obj));
			policyCoverageFundBean.setCaseCategoryId(cc);
		}

	}

	public String getCaseCategoryId(){
		String result = "";
		
		
		if (policyCoverageFundBean.getCaseCategoryId() != null){
			result =StringUtil.getStringValue(
					policyCoverageFundBean.getCaseCategoryId().getCaseCategoryId()); 
		}
		return result;
			
			
		

	}
	
				
	public void setPolicyId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
				Policy policy = new Policy();
				policy.setPolicyId(Integer.valueOf(obj));
				policyCoverageFundBean.setPolicyId(policy);
		}

	}

	public String getPolicyId(){
		String result = "";
		
		if (policyCoverageFundBean.getPolicyId() != null){
			result = StringUtil.getStringValue(
					policyCoverageFundBean.getPolicyId().getPolicyId());
		}
		return result;
		 

	}
	
				
	public void setCurrentFundValue(String obj){

		policyCoverageFundBean.setCurrentFundValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getCurrentFundValue(){
		return StringUtil.getStringValue(
		policyCoverageFundBean.getCurrentFundValue());

	}
	
								
	public void setStatus(String obj){

		policyCoverageFundBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		policyCoverageFundBean.getStatus());

	}
	
				
	public void setExcessFundValue(String obj){

		policyCoverageFundBean.setExcessFundValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getExcessFundValue(){
		return StringUtil.getStringValue(
		policyCoverageFundBean.getExcessFundValue());

	}
	
	
	
	public String getMinimumExcessValue() {
		String result = "";
		
		if (policyCoverageFundBean.getMinimumExcessValue() != null){
			BigDecimal bc = new BigDecimal(policyCoverageFundBean.getMinimumExcessValue());
			result = bc.toPlainString();
		}
		
		return result;
	}
	public void setMinimumExcessValue(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			this.policyCoverageFundBean.setMinimumExcessValue(Double.valueOf(obj));
		}
	}
	
	public String getMinimumAsoValue() {
		String result = "";
		
		if (policyCoverageFundBean.getMinimumAsoValue() != null){
			BigDecimal bc = new BigDecimal(policyCoverageFundBean.getMinimumAsoValue());
			result = bc.toPlainString();
		}
		
		return result;
	}
	public void setMinimumAsoValue(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			this.policyCoverageFundBean.setMinimumAsoValue(Double.valueOf(obj));
		}
	}
	public String getInitialAsoFundValue() {
		String result = "";
		
		if (policyCoverageFundBean.getInitialAsoFundValue() != null){
			BigDecimal bc = new BigDecimal(policyCoverageFundBean.getInitialAsoFundValue());
			result = bc.toPlainString();
		}
		
		return result;
	}
	public void setInitialAsoFundValue(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			this.policyCoverageFundBean.setInitialAsoFundValue(Double.valueOf(obj));
		}
	}
	public String getInitialExcessFundValue() {
		String result = "";
		
		if (policyCoverageFundBean.getInitialExcessFundValue() != null){
			BigDecimal bc = new BigDecimal(policyCoverageFundBean.getInitialExcessFundValue());
			result = bc.toPlainString();
		}
		
		return result;
	}
	public void setInitialExcessFundValue(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			this.policyCoverageFundBean.setInitialExcessFundValue(Double.valueOf(obj));
		}
	}
	
	public String getFundWarningPercentage() {
		String result = "";
		
		if (policyCoverageFundBean.getFundWarningPercentage() != null){
			BigDecimal bc = new BigDecimal(policyCoverageFundBean.getFundWarningPercentage());
			result = bc.toPlainString();
		}
		
		return result;
	}
	public void setFundWarningPercentage(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			this.policyCoverageFundBean.setFundWarningPercentage(Double.valueOf(obj));
		}
	}
	
	public String getBlockWarningPercentage() {
		String result = "";
		
		if (policyCoverageFundBean.getMinimumAsoValue() != null){
			BigDecimal bc = new BigDecimal(policyCoverageFundBean.getBlockWarningPercentage());
			result = bc.toPlainString();
		}
		
		return result;
	}
	public void setBlockWarningPercentage(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			this.policyCoverageFundBean.setBlockWarningPercentage(Double.valueOf(obj));
		}
	}
}
