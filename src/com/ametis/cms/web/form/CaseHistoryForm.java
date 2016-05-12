
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * CaseHistory is a mapping of case_history Table.
*/
public class CaseHistoryForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewCaseHistory = false;
	private CaseHistory caseHistoryBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public CaseHistoryForm()
    {
    	this.caseHistoryBean = new CaseHistory();
    	this.isNewCaseHistory = true;
    }
    public CaseHistoryForm (CaseHistory object){
		this.caseHistoryBean = object;
    }
    public boolean isNewCaseHistory (){

    	return this.isNewCaseHistory;
    }
	public CaseHistory getCaseHistory (){
		return this.caseHistoryBean ;
	}
	public void setCaseHistory (CaseHistory object){
		this.caseHistoryBean = object;
	}

			
	public void setCaseHistoryId(String obj){

		caseHistoryBean.setCaseHistoryId(StringUtil.getIntegerValue(obj,0));

	}

	public String getCaseHistoryId(){
		return StringUtil.getStringValue(
		caseHistoryBean.getCaseHistoryId());

	}
	
				
	public void setCaseId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Case caseBean = new Case();
			caseBean.setCaseId(Integer.valueOf(obj));
			
			caseHistoryBean.setCaseId(caseBean);
		}

	}

	public String getCaseId(){
		
		String result = "";
		
		if (caseHistoryBean.getCaseId() != null){
			result = StringUtil.getStringValue(
					caseHistoryBean.getCaseId()); 
		}
		
		return result;
		

	}
	
				
	public void setHistoryTime(String obj){

		caseHistoryBean.setHistoryTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getHistoryTime(){
		return StringUtil.getStringValue(
		caseHistoryBean.getHistoryTime());

	}

	
	
	public void setBeforeActionData(String obj){
		caseHistoryBean.setBeforeActionData(new String(obj));
	}

	public String getBeforeActionData(){
		return StringUtil.getStringValue(
		caseHistoryBean.getBeforeActionData());
	}
	
	public void setAfterActionData(String obj){
		caseHistoryBean.setAfterActionData(new String(obj));
	}

	public String getAfterActionData(){
		return StringUtil.getStringValue(
		caseHistoryBean.getAfterActionData());
	}
	
					public void setActionBy(String obj){

		caseHistoryBean.setActionBy(new String(obj));

	}

	public String getActionBy(){
		return StringUtil.getStringValue(
		caseHistoryBean.getActionBy());

	}
	
		

	// foreign affairs
	
	

	
	public void setAfterStatus(String obj){
		CaseStatus fk = new CaseStatus();
		fk.setCaseStatusId(StringUtil.getIntegerValue(obj,0));
		caseHistoryBean.setAfterStatus(fk);

	}

	public String getAfterStatus(){
		return StringUtil.getStringValue(
		caseHistoryBean.getAfterStatus().getCaseStatusId());

	}
	//---
	
	

	
	public void setBeforeStatus(String obj){
		CaseStatus fk = new CaseStatus();
		fk.setCaseStatusId(StringUtil.getIntegerValue(obj,0));
		caseHistoryBean.setBeforeStatus(fk);

	}

	public String getBeforeStatus(){
		return StringUtil.getStringValue(
		caseHistoryBean.getBeforeStatus().getCaseStatusId());

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
