
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.CaseProvider;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * CaseProvider is a mapping of case_provider Table.
*/
public class CaseProviderForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewCaseProvider = false;
	private CaseProvider caseProviderBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public CaseProviderForm()
    {
    	this.caseProviderBean = new CaseProvider();
    	this.isNewCaseProvider = true;
    }
    public CaseProviderForm (CaseProvider object){
		this.caseProviderBean = object;
    }
    public boolean isNewCaseProvider (){

    	return this.isNewCaseProvider;
    }
	public CaseProvider getCaseProvider (){
		return this.caseProviderBean ;
	}
	public void setCaseProvider (CaseProvider object){
		this.caseProviderBean = object;
	}

			
	public void setCaseProviderId(String obj){

		caseProviderBean.setCaseProviderId(StringUtil.getIntegerValue(obj,0));

	}

	public String getCaseProviderId(){
		return StringUtil.getStringValue(
		caseProviderBean.getCaseProviderId());

	}
	
				
	public void setCaseId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Case theCase = new Case();
			
			theCase.setCaseId(Integer.valueOf(obj));
			
			this.caseProviderBean.setCaseId(theCase);
			
		}

	}

	public String getCaseId(){
		String result = "";
		
		if (caseProviderBean.getCaseId() != null){
			result =  StringUtil.getStringValue(
					caseProviderBean.getCaseId().getCaseId()); 
		}
		return result;

	}
	
					public void setRumahSakit(String obj){

		caseProviderBean.setRumahSakit(new String(obj));

	}

	public String getRumahSakit(){
		return StringUtil.getStringValue(
		caseProviderBean.getRumahSakit());

	}
	
				
	public void setProviderId(String obj){

		caseProviderBean.setProviderId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProviderId(){
		return StringUtil.getStringValue(
		caseProviderBean.getProviderId());

	}
	
				
	public void setCaseStatus(String obj){

		caseProviderBean.setCaseStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getCaseStatus(){
		return StringUtil.getStringValue(
		caseProviderBean.getCaseStatus());

	}
	
					public void setDescription(String obj){

		caseProviderBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		caseProviderBean.getDescription());

	}
	
				
	public void setCreatedTime(String obj){

		caseProviderBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		caseProviderBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		caseProviderBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		caseProviderBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		caseProviderBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		caseProviderBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		caseProviderBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		caseProviderBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		caseProviderBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		caseProviderBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		caseProviderBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		caseProviderBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		caseProviderBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		caseProviderBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
