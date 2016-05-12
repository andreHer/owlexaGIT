
package com.ametis.cms.web.form;

import java.sql.Date;

import com.ametis.cms.datamodel.Clausul;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberClausul;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * MemberClausul is a mapping of member_clausul Table.
*/
public class MemberRKIForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewMemberClausul = false;
	private String cardNumber;
	private String providerId;
	private String providerName;
	private String caseCategoryId;
	private Date admissionDate;
	private String manualRegistration;
	//Add by aju on 20150824, for preAdmission flag
	private boolean preAdmission;
	

    public boolean getPreAdmission() {
		return preAdmission;
	}


	public void setPreAdmission(boolean preAdmission) {
		this.preAdmission = preAdmission;
	}


	public MemberRKIForm(){}


	public String getCardNumber() {
		return cardNumber;
	}


	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}


	public String getProviderId() {
		return providerId;
	}


	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}


	public String getProviderName() {
		return providerName;
	}


	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}


	public String getCaseCategoryId() {
		return caseCategoryId;
	}


	public void setCaseCategoryId(String caseCategoryId) {
		this.caseCategoryId = caseCategoryId;
	}
	public void setAdmissionDate(String obj){

		
		if (obj != null && !obj.equals("")){
			this.admissionDate = java.sql.Date.valueOf(obj);
		}
		else {
			this.admissionDate = new java.sql.Date(System.currentTimeMillis());
		}

	}

	public String getAdmissionDate(){
		String effectiveDate = "";
		
		if (admissionDate != null){
			effectiveDate = StringUtil.getStringValue(admissionDate);
		}
		else {
			effectiveDate = StringUtil.getStringValue(new Date(System.currentTimeMillis()));
		}
		return effectiveDate;

	}


	public String getManualRegistration() {
		return manualRegistration;
	}
	public void setManualRegistration(String manualRegistration) {
		this.manualRegistration = manualRegistration;
	}
    
	
	
}
