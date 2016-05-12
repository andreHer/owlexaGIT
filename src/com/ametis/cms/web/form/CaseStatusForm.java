
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * CaseStatus is a mapping of case_status Table.
*/
public class CaseStatusForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewCaseStatus = false;
	private CaseStatus caseStatusBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public CaseStatusForm()
    {
    	this.caseStatusBean = new CaseStatus();
    	this.isNewCaseStatus = true;
    }
    public CaseStatusForm (CaseStatus object){
		this.caseStatusBean = object;
    }
    public boolean isNewCaseStatus (){

    	return this.isNewCaseStatus;
    }
	public CaseStatus getCaseStatus (){
		return this.caseStatusBean ;
	}
	public void setCaseStatus (CaseStatus object){
		this.caseStatusBean = object;
	}

			
	public void setCaseStatusId(String obj){

		caseStatusBean.setCaseStatusId(StringUtil.getIntegerValue(obj,0));

	}

	public String getCaseStatusId(){
		return StringUtil.getStringValue(
		caseStatusBean.getCaseStatusId());

	}
	
					public void setCaseStatusName(String obj){

		caseStatusBean.setCaseStatusName(new String(obj));

	}

	public String getCaseStatusName(){
		return StringUtil.getStringValue(
		caseStatusBean.getCaseStatusName());

	}
	
					public void setDescription(String obj){

		caseStatusBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		caseStatusBean.getDescription());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
