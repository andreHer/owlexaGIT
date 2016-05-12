package com.ametis.cms.dto;

import java.io.Serializable;

public class ClaimReceiveDto implements Serializable{

	private String clientName;
	private String clientId;
	private String receiveDate;
	private String providerName;
	private String providerId;
	
	private String memberGroupName;
	private String memberGroupId;
	
	private String memberName;
	private String memberId;
	
	//riyan
	private String totalReceiving;
	
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	public String getMemberGroupName() {
		return memberGroupName;
	}
	public void setMemberGroupName(String memberGroupName) {
		this.memberGroupName = memberGroupName;
	}
	public String getMemberGroupId() {
		return memberGroupId;
	}
	public void setMemberGroupId(String memberGroupId) {
		this.memberGroupId = memberGroupId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getTotalReceiving() {
		return totalReceiving;
	}
	public void setTotalReceiving(String totalReceiving) {
		this.totalReceiving = totalReceiving;
	}
	
	
	
	
}
