package com.ametis.cms.dto;

import java.io.Serializable;

public class PolicyDto implements Serializable {

	private String policyId;
	private String memberGroupId;
	private String policyStatus;
	private String effectiveDate;
	private String expireDate;
	private String memberGroupName;
	private String policyNumber;
	private String cobStatus;
	private String policyType;
	
	public PolicyDto(){}
	
	public String getPolicyId() {
		return policyId;
	}
	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}
	public String getMemberGroupId() {
		return memberGroupId;
	}
	public void setMemberGroupId(String memberGroupId) {
		this.memberGroupId = memberGroupId;
	}
	public String getPolicyStatus() {
		return policyStatus;
	}
	public void setPolicyStatus(String policyStatus) {
		this.policyStatus = policyStatus;
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
	public String getMemberGroupName() {
		return memberGroupName;
	}
	public void setMemberGroupName(String memberGroupName) {
		this.memberGroupName = memberGroupName;
	}
	public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getCobStatus() {
		return cobStatus;
	}

	public void setCobStatus(String cobStatus) {
		this.cobStatus = cobStatus;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}
	
	
}
