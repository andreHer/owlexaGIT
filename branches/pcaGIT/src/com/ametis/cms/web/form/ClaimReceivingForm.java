
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * ClaimReceiving is a mapping of claim_receiving Table.
*/
public class ClaimReceivingForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewClaimReceiving = false;
	private ClaimReceiving claimReceivingBean ;
	private String clientName;
	private String memberGroupName;
	private String providerName;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ClaimReceivingForm()
    {
    	this.claimReceivingBean = new ClaimReceiving();
    	this.isNewClaimReceiving = true;
    }
    public ClaimReceivingForm (ClaimReceiving object){
		this.claimReceivingBean = object;
    }
    public boolean isNewClaimReceiving (){

    	return this.isNewClaimReceiving;
    }
	public ClaimReceiving getClaimReceiving (){
		return this.claimReceivingBean ;
	}
	public void setClaimReceiving (ClaimReceiving object){
		this.claimReceivingBean = object;
	}

			
	public void setClaimReceivingId(String obj){

		claimReceivingBean.setClaimReceivingId(StringUtil.getIntegerValue(obj,0));

	}

	public String getClaimReceivingId(){
		return StringUtil.getStringValue(
		claimReceivingBean.getClaimReceivingId());

	}
	
					public void setReceivingNumber(String obj){

		claimReceivingBean.setReceivingNumber(new String(obj));

	}

	public String getReceivingNumber(){
		return StringUtil.getStringValue(
		claimReceivingBean.getReceivingNumber());

	}
	
													
	public void setReceiveDate(String obj){

		claimReceivingBean.setReceiveDate(java.sql.Date.valueOf(obj));

	}

	public String getReceiveDate(){
		return StringUtil.getStringValue(
		claimReceivingBean.getReceiveDate());

	}


	public void setReceiveTime(String obj){

		claimReceivingBean.setReceiveTime(obj);

	}

	public String getReceiveTime(){
		return StringUtil.getStringValue(
		claimReceivingBean.getReceiveTime());

	}
				
	public void setTotalReceiving(String obj){

		claimReceivingBean.setTotalReceiving(StringUtil.getIntegerValue(obj,0));

	}

	public String getTotalReceiving(){
		return StringUtil.getStringValue(
		claimReceivingBean.getTotalReceiving());

	}
	
					public void setCourier(String obj){

		claimReceivingBean.setCourier(new String(obj));

	}

	public String getCourier(){
		return StringUtil.getStringValue(
		claimReceivingBean.getCourier());

	}
	
					public void setDescription(String obj){

		claimReceivingBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		claimReceivingBean.getDescription());

	}
	

	
				
	public void setReceivingStatus(String obj){

		claimReceivingBean.setReceivingStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getReceivingStatus(){
		return StringUtil.getStringValue(
		claimReceivingBean.getReceivingStatus());

	}
	
		

	// foreign affairs
	
	

	
	public void setClientId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Client fk = new Client();
			fk.setClientId(StringUtil.getIntegerValue(obj,0));
			claimReceivingBean.setClientId(fk);
		}

	}

	public String getClientId(){
		String result = "";
		
		if (claimReceivingBean.getClientId() != null){
			result = StringUtil.getStringValue(
					claimReceivingBean.getClientId().getClientId());
		}
		
		return result;

	}
	//---
	
	

	
	public void setProviderId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Provider fk = new Provider();
			fk.setProviderId(StringUtil.getIntegerValue(obj,0));
			claimReceivingBean.setProviderId(fk);
		}

	}

	public String getProviderId(){
		String result = "";
		
		if (claimReceivingBean.getProviderId() != null){
		result = StringUtil.getStringValue(
		claimReceivingBean.getProviderId().getProviderId());
		
		}
		return result;

	}
	//---
	
	

	
	public void setMemberGroupId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			MemberGroup fk = new MemberGroup();
			fk.setMemberGroupId(StringUtil.getIntegerValue(obj,0));
			claimReceivingBean.setMemberGroupId(fk);
		}

	}

	public String getMemberGroupId(){
		String result = "";
		
		if (claimReceivingBean.getMemberGroupId() != null){
			result = StringUtil.getStringValue(
					claimReceivingBean.getMemberGroupId().getMemberGroupId());
		
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
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	
}
