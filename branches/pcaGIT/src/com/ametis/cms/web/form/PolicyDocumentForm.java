
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * PolicyDocument is a mapping of policy_document Table.
*/
public class PolicyDocumentForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewPolicyDocument = false;
	private PolicyDocument policyDocumentBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public PolicyDocumentForm()
    {
    	this.policyDocumentBean = new PolicyDocument();
    	this.isNewPolicyDocument = true;
    }
    public PolicyDocumentForm (PolicyDocument object){
		this.policyDocumentBean = object;
    }
    public boolean isNewPolicyDocument (){

    	return this.isNewPolicyDocument;
    }
	public PolicyDocument getPolicyDocument (){
		return this.policyDocumentBean ;
	}
	public void setPolicyDocument (PolicyDocument object){
		this.policyDocumentBean = object;
	}

			
	public void setId(String obj){

		policyDocumentBean.setId(StringUtil.getIntegerValue(obj,0));

	}

	public String getId(){
		return StringUtil.getStringValue(
		policyDocumentBean.getId());

	}
	
				
	public void setPolicyId(String obj){

		policyDocumentBean.setPolicyId(StringUtil.getLongValue(obj,0));

	}

	public String getPolicyId(){
		return StringUtil.getStringValue(
		policyDocumentBean.getPolicyId());

	}
	
					public void setDocumentName(String obj){

		policyDocumentBean.setDocumentName(new String(obj));

	}

	public String getDocumentName(){
		return StringUtil.getStringValue(
		policyDocumentBean.getDocumentName());

	}
	
					public void setDescription(String obj){

		policyDocumentBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		policyDocumentBean.getDescription());

	}
	
					public void setDocumentUrl(String obj){

		policyDocumentBean.setDocumentUrl(new String(obj));

	}

	public String getDocumentUrl(){
		return StringUtil.getStringValue(
		policyDocumentBean.getDocumentUrl());

	}
	
				
	public void setTipe(String obj){

		policyDocumentBean.setTipe(StringUtil.getIntegerValue(obj,0));

	}

	public String getTipe(){
		return StringUtil.getStringValue(
		policyDocumentBean.getTipe());

	}
	
				
	public void setCreatedTime(String obj){

		policyDocumentBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		policyDocumentBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		policyDocumentBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		policyDocumentBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		policyDocumentBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		policyDocumentBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		policyDocumentBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		policyDocumentBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		policyDocumentBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		policyDocumentBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		policyDocumentBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		policyDocumentBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		policyDocumentBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		policyDocumentBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 

}
