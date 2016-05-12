
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
public class MemberEDCRegisterForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewMemberClausul = false;
	private String cardNumber;
	private String providerId;
	private String providerName;
	private String caseCategoryId;
	private Date admissionDate;
	private String referenceNumber;
	

    public MemberEDCRegisterForm(){}


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


	public String getReferenceNumber() {
		return referenceNumber;
	}


	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
    
	
}
