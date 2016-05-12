
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * Questionaire is a mapping of questionaire Table.
*/
public class QuestionaireForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewQuestionaire = false;
	private Questionaire questionaireBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public QuestionaireForm()
    {
    	this.questionaireBean = new Questionaire();
    	this.isNewQuestionaire = true;
    }
    public QuestionaireForm (Questionaire object){
		this.questionaireBean = object;
    }
    public boolean isNewQuestionaire (){

    	return this.isNewQuestionaire;
    }
	public Questionaire getQuestionaire (){
		return this.questionaireBean ;
	}
	public void setQuestionaire (Questionaire object){
		this.questionaireBean = object;
	}

			
	public void setQuestionaireId(String obj){

		questionaireBean.setQuestionaireId(StringUtil.getIntegerValue(obj,0));

	}

	public String getQuestionaireId(){
		return StringUtil.getStringValue(
		questionaireBean.getQuestionaireId());

	}
	
					public void setQuestionaireName(String obj){

		questionaireBean.setQuestionaireName(new String(obj));

	}

	public String getQuestionaireName(){
		return StringUtil.getStringValue(
		questionaireBean.getQuestionaireName());

	}
	
				
	public void setQuestionaireDate(String obj){

		questionaireBean.setQuestionaireDate(java.sql.Date.valueOf(obj));

	}

	public String getQuestionaireDate(){
		return StringUtil.getStringValue(
		questionaireBean.getQuestionaireDate());

	}

	
					public void setDescription(String obj){

		questionaireBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		questionaireBean.getDescription());

	}
	
				
	public void setCreatedTime(String obj){

		questionaireBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		questionaireBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		questionaireBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		questionaireBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		questionaireBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		questionaireBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		questionaireBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		questionaireBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		questionaireBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		questionaireBean.getDeletedStatus());

	}
	
				
	public void setDeletedTime(String obj){

		questionaireBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		questionaireBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		questionaireBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		questionaireBean.getDeletedBy());

	}
	
				
	public void setStatus(String obj){

		questionaireBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		questionaireBean.getStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 

}
