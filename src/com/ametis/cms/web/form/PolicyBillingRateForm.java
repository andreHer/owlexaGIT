
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * PolicyBillingRate is a mapping of policy_billing_rate Table.
*/
public class PolicyBillingRateForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewPolicyBillingRate = false;
	private PolicyBillingRate policyBillingRateBean ;
	private String policyNumber;
	

    public PolicyBillingRateForm()
    {
    	this.policyBillingRateBean = new PolicyBillingRate();
    	this.isNewPolicyBillingRate = true;
    }
    public PolicyBillingRateForm (PolicyBillingRate object){
		this.policyBillingRateBean = object;
    }
    public boolean isNewPolicyBillingRate (){

    	return this.isNewPolicyBillingRate;
    }
	public PolicyBillingRate getPolicyBillingRate (){
		return this.policyBillingRateBean ;
	}
	public void setPolicyBillingRate (PolicyBillingRate object){
		this.policyBillingRateBean = object;
	}

			
	public void setPolicyBillingRateId(String obj){

		policyBillingRateBean.setPolicyBillingRateId(StringUtil.getIntegerValue(obj,0));

	}

	public String getPolicyBillingRateId(){
		return StringUtil.getStringValue(
		policyBillingRateBean.getPolicyBillingRateId());

	}
	
							
	public void setBillingRateSubject(String obj){

		policyBillingRateBean.setBillingRateSubject(StringUtil.getIntegerValue(obj,0));

	}

	public String getBillingRateSubject(){
		return StringUtil.getStringValue(
		policyBillingRateBean.getBillingRateSubject());

	}
	
				
	public void setBillingRateStartQuantity(String obj){

		policyBillingRateBean.setBillingRateStartQuantity(StringUtil.getIntegerValue(obj,0));

	}

	public String getBillingRateStartQuantity(){
		return StringUtil.getStringValue(
		policyBillingRateBean.getBillingRateStartQuantity());

	}
	
				
	public void setBillingRateEndQuantity(String obj){

		policyBillingRateBean.setBillingRateEndQuantity(StringUtil.getIntegerValue(obj,0));

	}

	public String getBillingRateEndQuantity(){
		return StringUtil.getStringValue(
		policyBillingRateBean.getBillingRateEndQuantity());

	}
	
				
	public void setBillingRate(String obj){

		policyBillingRateBean.setBillingRate(StringUtil.getDoubleValue(obj,0));

	}

	public String getBillingRate(){
		return StringUtil.getStringValue(
		policyBillingRateBean.getBillingRate());

	}
	
				
	public void setClaimProcessStatusStart(String obj){

		policyBillingRateBean.setClaimProcessStatusStart(StringUtil.getIntegerValue(obj,0));

	}

	public String getClaimProcessStatusStart(){
		return StringUtil.getStringValue(
		policyBillingRateBean.getClaimProcessStatusStart());

	}
	
				
	public void setClaimProcessStatusEnd(String obj){

		policyBillingRateBean.setClaimProcessStatusEnd(StringUtil.getIntegerValue(obj,0));

	}

	public String getClaimProcessStatusEnd(){
		return StringUtil.getStringValue(
		policyBillingRateBean.getClaimProcessStatusEnd());

	}
	
							
	public void setCreatedTime(String obj){

		policyBillingRateBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		policyBillingRateBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		policyBillingRateBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		policyBillingRateBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		policyBillingRateBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		policyBillingRateBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		policyBillingRateBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		policyBillingRateBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		policyBillingRateBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		policyBillingRateBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		policyBillingRateBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		policyBillingRateBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		policyBillingRateBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		policyBillingRateBean.getDeletedStatus());

	}
	
		

	// foreign affairs
	
	

	
	public void setItemId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Item fk = new Item();
			fk.setItemId(StringUtil.getIntegerValue(obj,0));
			policyBillingRateBean.setItemId(fk);
		}

	}

	public String getItemId(){
		String result = "";
		
		if (policyBillingRateBean.getItemId() != null){
			result = StringUtil.getStringValue(
					policyBillingRateBean.getItemId().getItemId());
		}
		
		return result;

	}
	//---
	
	

	
	public void setPolicyId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Policy fk = new Policy();
			fk.setPolicyId(StringUtil.getIntegerValue(obj,0));
			policyBillingRateBean.setPolicyId(fk);
		}

	}

	public String getPolicyId(){
		String result = "";
		
		if (policyBillingRateBean.getPolicyId() != null){
			result = StringUtil.getStringValue(
					policyBillingRateBean.getPolicyId().getPolicyId());
		}
		return result;

	}
	public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	
}
