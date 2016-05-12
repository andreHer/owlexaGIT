
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * ProviderClaimUtilReport is a mapping of provider_claim_util_report Table.
*/
public class ProviderClaimUtilReportForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewProviderClaimUtilReport = false;
	private ProviderClaimUtilReport providerClaimUtilReportBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ProviderClaimUtilReportForm()
    {
    	this.providerClaimUtilReportBean = new ProviderClaimUtilReport();
    	this.isNewProviderClaimUtilReport = true;
    }
    public ProviderClaimUtilReportForm (ProviderClaimUtilReport object){
		this.providerClaimUtilReportBean = object;
    }
    public boolean isNewProviderClaimUtilReport (){

    	return this.isNewProviderClaimUtilReport;
    }
	public ProviderClaimUtilReport getProviderClaimUtilReport (){
		return this.providerClaimUtilReportBean ;
	}
	public void setProviderClaimUtilReport (ProviderClaimUtilReport object){
		this.providerClaimUtilReportBean = object;
	}

			
	public void setId(String obj){

		providerClaimUtilReportBean.setId(StringUtil.getIntegerValue(obj,0));

	}

	public String getId(){
		return StringUtil.getStringValue(
		providerClaimUtilReportBean.getId());

	}
	
					public void setProviderName(String obj){

		providerClaimUtilReportBean.setProviderName(new String(obj));

	}

	public String getProviderName(){
		return StringUtil.getStringValue(
		providerClaimUtilReportBean.getProviderName());

	}
	
				
	public void setReportDate(String obj){

		providerClaimUtilReportBean.setReportDate(java.sql.Date.valueOf(obj));

	}

	public String getReportDate(){
		return StringUtil.getStringValue(
		providerClaimUtilReportBean.getReportDate());

	}

	
					public void setProviderPeriode(String obj){

		providerClaimUtilReportBean.setProviderPeriode(new String(obj));

	}

	public String getProviderPeriode(){
		return StringUtil.getStringValue(
		providerClaimUtilReportBean.getProviderPeriode());

	}
	
				
	public void setTotalClaim(String obj){

		providerClaimUtilReportBean.setTotalClaim(StringUtil.getIntegerValue(obj,0));

	}

	public String getTotalClaim(){
		return StringUtil.getStringValue(
		providerClaimUtilReportBean.getTotalClaim());

	}
	
				
	public void setTotalNominalClaim(String obj){

		providerClaimUtilReportBean.setTotalNominalClaim(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalNominalClaim(){
		return StringUtil.getStringValue(
		providerClaimUtilReportBean.getTotalNominalClaim());

	}
	
				
	public void setTotalInpatient(String obj){

		providerClaimUtilReportBean.setTotalInpatient(StringUtil.getIntegerValue(obj,0));

	}

	public String getTotalInpatient(){
		return StringUtil.getStringValue(
		providerClaimUtilReportBean.getTotalInpatient());

	}
	
				
	public void setTotalNominalInpatient(String obj){

		providerClaimUtilReportBean.setTotalNominalInpatient(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalNominalInpatient(){
		return StringUtil.getStringValue(
		providerClaimUtilReportBean.getTotalNominalInpatient());

	}
	
				
	public void setTotalOutpatient(String obj){

		providerClaimUtilReportBean.setTotalOutpatient(StringUtil.getIntegerValue(obj,0));

	}

	public String getTotalOutpatient(){
		return StringUtil.getStringValue(
		providerClaimUtilReportBean.getTotalOutpatient());

	}
	
				
	public void setTotalNominalOutpatient(String obj){

		providerClaimUtilReportBean.setTotalNominalOutpatient(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalNominalOutpatient(){
		return StringUtil.getStringValue(
		providerClaimUtilReportBean.getTotalNominalOutpatient());

	}
	
				
	public void setTotalDental(String obj){

		providerClaimUtilReportBean.setTotalDental(StringUtil.getIntegerValue(obj,0));

	}

	public String getTotalDental(){
		return StringUtil.getStringValue(
		providerClaimUtilReportBean.getTotalDental());

	}
	
				
	public void setTotalNominalDental(String obj){

		providerClaimUtilReportBean.setTotalNominalDental(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalNominalDental(){
		return StringUtil.getStringValue(
		providerClaimUtilReportBean.getTotalNominalDental());

	}
	
				
	public void setTotalMaternity(String obj){

		providerClaimUtilReportBean.setTotalMaternity(StringUtil.getIntegerValue(obj,0));

	}

	public String getTotalMaternity(){
		return StringUtil.getStringValue(
		providerClaimUtilReportBean.getTotalMaternity());

	}
	
				
	public void setTotalNominalMaterinity(String obj){

		providerClaimUtilReportBean.setTotalNominalMaterinity(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalNominalMaterinity(){
		return StringUtil.getStringValue(
		providerClaimUtilReportBean.getTotalNominalMaterinity());

	}
	
				
	public void setTotalOptical(String obj){

		providerClaimUtilReportBean.setTotalOptical(StringUtil.getIntegerValue(obj,0));

	}

	public String getTotalOptical(){
		return StringUtil.getStringValue(
		providerClaimUtilReportBean.getTotalOptical());

	}
	
				
	public void setTotalNominalOptical(String obj){

		providerClaimUtilReportBean.setTotalNominalOptical(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalNominalOptical(){
		return StringUtil.getStringValue(
		providerClaimUtilReportBean.getTotalNominalOptical());

	}
	
				
	public void setTotalMcu(String obj){

		providerClaimUtilReportBean.setTotalMcu(StringUtil.getIntegerValue(obj,0));

	}

	public String getTotalMcu(){
		return StringUtil.getStringValue(
		providerClaimUtilReportBean.getTotalMcu());

	}
	
				
	public void setTotalNominalMcu(String obj){

		providerClaimUtilReportBean.setTotalNominalMcu(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalNominalMcu(){
		return StringUtil.getStringValue(
		providerClaimUtilReportBean.getTotalNominalMcu());

	}
	
				
	public void setProviderId(String obj){

		providerClaimUtilReportBean.setProviderId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProviderId(){
		return StringUtil.getStringValue(
		providerClaimUtilReportBean.getProviderId());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
