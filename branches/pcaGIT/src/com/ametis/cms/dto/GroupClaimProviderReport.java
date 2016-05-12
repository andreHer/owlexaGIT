package com.ametis.cms.dto;

import java.io.Serializable;
import java.util.Vector;

import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.MemberGroup;

public class GroupClaimProviderReport implements Serializable {

	private static final long serialVersionUID = 1L;
	private String memberGroupName;
	private String serviceType;
	private MemberGroup memberGroupId;
	private CaseCategory caseCategory;
	private String minimumDate;
	private String maximumDate;
	private Vector<GroupClaimProviderReport> reports = new Vector<GroupClaimProviderReport>();
	
	
	public GroupClaimProviderReport(){}
	
	public String getMemberGroupName() {
		return memberGroupName;
	}
	public void setMemberGroupName(String memberGroupName) {
		this.memberGroupName = memberGroupName;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public MemberGroup getMemberGroupId() {
		return memberGroupId;
	}
	public void setMemberGroupId(MemberGroup memberGroupId) {
		this.memberGroupId = memberGroupId;
	}
	public CaseCategory getCaseCategory() {
		return caseCategory;
	}
	public void setCaseCategory(CaseCategory caseCategory) {
		this.caseCategory = caseCategory;
	}
	public String getMinimumDate() {
		return minimumDate;
	}
	public void setMinimumDate(String minimumDate) {
		this.minimumDate = minimumDate;
	}
	public String getMaximumDate() {
		return maximumDate;
	}
	public void setMaximumDate(String maximumDate) {
		this.maximumDate = maximumDate;
	}
	public Vector<GroupClaimProviderReport> getReports() {
		return reports;
	}
	public void setReports(Vector<GroupClaimProviderReport> reports) {
		this.reports = reports;
	}
	
	
}
