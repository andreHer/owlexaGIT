package com.ametis.cms.dto;

import java.io.Serializable;

import com.ametis.cms.datamodel.Case;
import com.ametis.cms.service.CaseService;

public class CaseDto implements Serializable{

	private String caseNumber;
	private String caseId;
	private String memberId;
	private String cardNumber;
	private String Name; 
	private String Client;
	private String Group;
	private String Type; 
	private String Charge;
	private String policyNumber;
	private String dateAndTime;
	private String Hospital;
	private String Agent;
	private String Officer;


	public String getOfficer() {
		return Officer;
	}

	public void setOfficer(String officer) {
		Officer = officer;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getAgent() {
		return Agent;
	}

	public void setAgent(String agent) {
		Agent = agent;
	}

	public String getHospital() {
		return Hospital;
	}

	public void setHospital(String hospital) {
		Hospital = hospital;
	}

	public String getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getCharge() {
		return Charge;
	}

	public void setCharge(String charge) {
		Charge = charge;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getGroup() {
		return Group;
	}

	public void setGroup(String group) {
		Group = group;
	}

	public String getClient() {
		return Client;
	}

	public void setClient(String client) {
		Client = client;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	} 
}
