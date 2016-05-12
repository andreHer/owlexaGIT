
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * QuestionaireItem is a mapping of questionaire_item Table.
*/
public class QuestionaireItemForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewQuestionaireItem = false;
	private QuestionaireItem questionaireItemBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public QuestionaireItemForm()
    {
    	this.questionaireItemBean = new QuestionaireItem();
    	this.isNewQuestionaireItem = true;
    }
    public QuestionaireItemForm (QuestionaireItem object){
		this.questionaireItemBean = object;
    }
    public boolean isNewQuestionaireItem (){

    	return this.isNewQuestionaireItem;
    }
	public QuestionaireItem getQuestionaireItem (){
		return this.questionaireItemBean ;
	}
	public void setQuestionaireItem (QuestionaireItem object){
		this.questionaireItemBean = object;
	}

			
	public void setQuestionaireItemId(String obj){

		questionaireItemBean.setQuestionaireItemId(StringUtil.getIntegerValue(obj,0));

	}

	public String getQuestionaireItemId(){
		return StringUtil.getStringValue(
		questionaireItemBean.getQuestionaireItemId());

	}
	
					public void setQuestionaireSubject(String obj){

		questionaireItemBean.setQuestionaireSubject(new String(obj));

	}

	public String getQuestionaireSubject(){
		return StringUtil.getStringValue(
		questionaireItemBean.getQuestionaireSubject());

	}
	
					public void setDescription(String obj){

		questionaireItemBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		questionaireItemBean.getDescription());

	}
	
				
	public void setCoefficient(String obj){

		questionaireItemBean.setCoefficient(StringUtil.getDoubleValue(obj,0));

	}

	public String getCoefficient(){
		return StringUtil.getStringValue(
		questionaireItemBean.getCoefficient());

	}
	
				
	public void setCreatedTime(String obj){

		questionaireItemBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		questionaireItemBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		questionaireItemBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		questionaireItemBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		questionaireItemBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		questionaireItemBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		questionaireItemBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		questionaireItemBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		questionaireItemBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		questionaireItemBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		questionaireItemBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		questionaireItemBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		questionaireItemBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		questionaireItemBean.getDeletedStatus());

	}
	
				
	public void setQuestionaireId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Questionaire questionaire = new Questionaire();
			questionaire.setQuestionaireId(Integer.valueOf(obj));
			questionaireItemBean.setQuestionaireId(questionaire);
		}

	}

	public String getQuestionaireId(){
		String result = "";
		
		if (questionaireItemBean.getQuestionaireId() != null){
			result = StringUtil.getStringValue(
					questionaireItemBean.getQuestionaireId().getQuestionaireId());
		}
		return result;

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 

}
