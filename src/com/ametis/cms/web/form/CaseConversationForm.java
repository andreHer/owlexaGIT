
package com.ametis.cms.web.form;

import java.sql.Timestamp;
import java.util.Date;

import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.CaseConversation;
import com.ametis.cms.datamodel.ConversationCategory;
import com.ametis.cms.datamodel.Priority;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * CaseConversation is a mapping of case_conversation Table.
*/
public class CaseConversationForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewCaseConversation = false;
	private CaseConversation caseConversationBean ;
	private String time;
	private String hour;
	private String minute;
	
	private java.sql.Date date;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public CaseConversationForm()
    {
    	this.caseConversationBean = new CaseConversation();
    	this.isNewCaseConversation = true;
    }
    public CaseConversationForm (CaseConversation object){
		this.caseConversationBean = object;
    }
    public boolean isNewCaseConversation (){

    	return this.isNewCaseConversation;
    }
	public CaseConversation getCaseConversation (){
		return this.caseConversationBean ;
	}
	public void setCaseConversation (CaseConversation object){
		this.caseConversationBean = object;
	}

			
	public void setCase(Case obj){

		
		caseConversationBean.setCaseId(obj);

	}

	public Case getCase(){
		
		return caseConversationBean.getCaseId();

	}
	
				
	public void setConversationId(String obj){

		caseConversationBean.setConversationId(StringUtil.getLongValue(obj,0));

	}

	public String getConversationId(){
		return StringUtil.getStringValue(
		caseConversationBean.getConversationId());

	}
	
	public void setConversationSubject(String obj){

		caseConversationBean.setConversationSubject(new String(obj));

	}

	public String getConversationSubject(){
		return StringUtil.getStringValue(
		caseConversationBean.getConversationSubject());

	}
	
	public void setConversationDescription(String obj){

		caseConversationBean.setConversationDescription(new String(obj));

	}

	public String getConversationDescription(){
		return StringUtil.getStringValue(
		caseConversationBean.getConversationDescription());

	}
	
	
	public String getDate() {
		String result = "";
		if (this.date != null){
			result = date.toString();
		}
		return result;
	}
	public void setDate(String date) {
		if (date != null && !date.equalsIgnoreCase("")){
			this.date = java.sql.Date.valueOf(date);
		}
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public void setConversationTime(Timestamp obj){

		caseConversationBean.setConversationTime(obj);

	}

	public Timestamp getConversationTime(){
		return caseConversationBean.getConversationTime();

	}

	
				
	public void setConversationCategory(ConversationCategory obj){

		caseConversationBean.setConversationCategory(obj);

	}

	public ConversationCategory getConversationCategory(){
		return caseConversationBean.getConversationCategory();

	}
	
	public void setCreatedBy(String obj){

		caseConversationBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		caseConversationBean.getCreatedBy());

	}
	
				
	public void setCreatedTime(String obj){

		caseConversationBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		caseConversationBean.getCreatedTime());

	}

	
					public void setModifiedBy(String obj){

		caseConversationBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		caseConversationBean.getModifiedBy());

	}
	
				
	public void setModifiedTime(String obj){

		caseConversationBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		caseConversationBean.getModifiedTime());

	}

	
					public void setDeletedBy(String obj){

		caseConversationBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		caseConversationBean.getDeletedBy());

	}
	
				
	public void setDeletedTime(String obj){

		caseConversationBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		caseConversationBean.getDeletedTime());

	}

	
				
	public void setDeletedStatus(String obj){

		caseConversationBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		caseConversationBean.getDeletedStatus());

	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public String getMinute() {
		return minute;
	}
	public void setMinute(String minute) {
		this.minute = minute;
	}
	public void setPriority(String obj){

		if (obj != null && obj.equalsIgnoreCase("")){
			Priority priority = new Priority();
			priority.setPriorityId(Integer.valueOf(obj));
			
			caseConversationBean.setPriority(priority);
		}

	}

	public String getPriority(){
		String result = "";
		
		if (caseConversationBean.getPriority() != null){
			result = StringUtil.getStringValue(
				caseConversationBean.getPriority().getPriorityId());
		}
		
		return result ;
		

	}
	public void setStatus(String obj){

		caseConversationBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		caseConversationBean.getStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
