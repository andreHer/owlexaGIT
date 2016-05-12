
package com.ametis.cms.web.form;

import org.springframework.web.multipart.MultipartFile;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * PolicyProvider is a mapping of policy_provider Table.
*/
public class PolicyProviderForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewPolicyProvider = false;
	private PolicyProvider policyProviderBean ;
	private String providerName;
	private MultipartFile multipartFile;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public PolicyProviderForm()
    {
    	this.policyProviderBean = new PolicyProvider();
    	this.isNewPolicyProvider = true;
    }
    public PolicyProviderForm (PolicyProvider object){
		this.policyProviderBean = object;
    }
    public boolean isNewPolicyProvider (){

    	return this.isNewPolicyProvider;
    }
	public PolicyProvider getPolicyProvider (){
		return this.policyProviderBean ;
	}
	public void setPolicyProvider (PolicyProvider object){
		this.policyProviderBean = object;
	}
	
			
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public void setId(String obj){

		policyProviderBean.setPolicyProviderId(StringUtil.getIntegerValue(obj,0));

	}

	public String getId(){
		return StringUtil.getStringValue(
		policyProviderBean.getPolicyProviderId());

	}
	
				
		
				
	public void setProviderId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Provider provider = new Provider();
			provider.setProviderId(StringUtil.getIntegerValue(obj,0));
			policyProviderBean.setProviderId(provider);
		}

	}

	public String getProviderId(){
		return StringUtil.getStringValue(
		policyProviderBean.getProviderId());

	}
	
				
	public void setPolicyId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Policy policy = new Policy();
			policy.setPolicyId(StringUtil.getIntegerValue(obj,0));
			policyProviderBean.setPolicyId(policy);
		}
		

	}

	public String getPolicyId(){
		String result = "";
		
		if (policyProviderBean.getPolicyId() != null){
			result =StringUtil.getStringValue(
					policyProviderBean.getPolicyId().getPolicyId()); 
		}
		return result;
		

	}
	public void setServiceType(String obj){

		policyProviderBean.setServiceType(StringUtil.getIntegerValue(obj,0));

	}

	public String getServiceType(){
		return StringUtil.getStringValue(
		policyProviderBean.getServiceType());

	}
	public void setTotalMember(String obj){

		policyProviderBean.setTotalMember(StringUtil.getIntegerValue(obj,0));

	}

	public String getTotalMember(){
		return StringUtil.getStringValue(
		policyProviderBean.getTotalMember());

	}
	public void setCapitationFee(String obj){

		policyProviderBean.setCapitationFeePerMonth(StringUtil.getDoubleValue(obj,0));

	}

	public String getCapitationFee(){
		return StringUtil.getStringValue(
		policyProviderBean.getCapitationFeePerMonth());

	}
	public void setOutpatient(String obj){

		policyProviderBean.setOutpatient(StringUtil.getIntegerValue(obj,0));

	}

	public String getOutpatient(){
		return StringUtil.getStringValue(
		policyProviderBean.getOutpatient());

	}
	
				
	public void setInpatient(String obj){

		policyProviderBean.setInpatient(StringUtil.getIntegerValue(obj,0));

	}

	public String getInpatient(){
		return StringUtil.getStringValue(
		policyProviderBean.getInpatient());

	}
	
				
	public void setDental(String obj){

		policyProviderBean.setDental(StringUtil.getIntegerValue(obj,0));

	}

	public String getDental(){
		return StringUtil.getStringValue(
		policyProviderBean.getDental());

	}
	
				
	public void setMaternity(String obj){

		policyProviderBean.setMaternity(StringUtil.getIntegerValue(obj,0));

	}

	public String getMaternity(){
		return StringUtil.getStringValue(
		policyProviderBean.getMaternity());

	}
	
				
	public void setOptical(String obj){

		policyProviderBean.setOptical(StringUtil.getIntegerValue(obj,0));

	}

	public String getOptical(){
		return StringUtil.getStringValue(
		policyProviderBean.getOptical());

	}
	
				
	public void setPpk1(String obj){

		policyProviderBean.setPpk1(StringUtil.getIntegerValue(obj,0));

	}

	public String getPpk1(){
		return StringUtil.getStringValue(
		policyProviderBean.getPpk1());

	}
	
				
	public void setPpk2(String obj){

		policyProviderBean.setPpk2(StringUtil.getIntegerValue(obj,0));

	}

	public String getPpk2(){
		return StringUtil.getStringValue(
		policyProviderBean.getPpk2());

	}
	
				
	public void setPpk3(String obj){

		policyProviderBean.setPpk3(StringUtil.getIntegerValue(obj,0));

	}

	public String getPpk3(){
		return StringUtil.getStringValue(
		policyProviderBean.getPpk3());

	}
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}
	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
