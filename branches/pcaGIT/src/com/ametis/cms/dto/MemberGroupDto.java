package com.ametis.cms.dto;

import java.io.Serializable;

public class MemberGroupDto implements Serializable{

	private String memberGroupId;
	private String clientId;
	private String memberGroupName;
	private String memberGroupCode;
	
	private String joinDate;
	private String effectiveDate;
	private String expireDate;
	private String renewalDate;
	private String status;
	
	
	
	public MemberGroupDto() {
		
		// TODO Auto-generated constructor stub
	}
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getMemberGroupId() {
		return memberGroupId;
	}
	public void setMemberGroupId(String memberGroupId) {
		this.memberGroupId = memberGroupId;
	}
	public String getMemberGroupName() {
		return memberGroupName;
	}
	public void setMemberGroupName(String memberGroupName) {
		this.memberGroupName = memberGroupName;
	}
	public String getMemberGroupCode() {
		return memberGroupCode;
	}
	public void setMemberGroupCode(String memberGroupCode) {
		this.memberGroupCode = memberGroupCode;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public String getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	public String getRenewalDate() {
		return renewalDate;
	}
	public void setRenewalDate(String renewalDate) {
		this.renewalDate = renewalDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
