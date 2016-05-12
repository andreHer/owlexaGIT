
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * ProviderReviewQuestionaire is a mapping of provider_review_questionaire Table.
*/
public class ProviderReviewQuestionaireForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewProviderReviewQuestionaire = false;
	private ProviderReviewQuestionaire providerReviewQuestionaireBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ProviderReviewQuestionaireForm()
    {
    	this.providerReviewQuestionaireBean = new ProviderReviewQuestionaire();
    	this.isNewProviderReviewQuestionaire = true;
    }
    public ProviderReviewQuestionaireForm (ProviderReviewQuestionaire object){
		this.providerReviewQuestionaireBean = object;
    }
    public boolean isNewProviderReviewQuestionaire (){

    	return this.isNewProviderReviewQuestionaire;
    }
	public ProviderReviewQuestionaire getProviderReviewQuestionaire (){
		return this.providerReviewQuestionaireBean ;
	}
	public void setProviderReviewQuestionaire (ProviderReviewQuestionaire object){
		this.providerReviewQuestionaireBean = object;
	}

			
	public void setProviderReviewQuestionaireId(String obj){

		providerReviewQuestionaireBean.setProviderReviewQuestionaireId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProviderReviewQuestionaireId(){
		return StringUtil.getStringValue(
		providerReviewQuestionaireBean.getProviderReviewQuestionaireId());

	}
	
				
	public void setQuestionaireItemId(String obj){

		providerReviewQuestionaireBean.setQuestionaireItemId(StringUtil.getIntegerValue(obj,0));

	}

	public String getQuestionaireItemId(){
		return StringUtil.getStringValue(
		providerReviewQuestionaireBean.getQuestionaireItemId());

	}
	
				
	public void setVote(String obj){

		providerReviewQuestionaireBean.setVote(StringUtil.getIntegerValue(obj,0));

	}

	public String getVote(){
		return StringUtil.getStringValue(
		providerReviewQuestionaireBean.getVote());

	}
	
					public void setDescription(String obj){

		providerReviewQuestionaireBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		providerReviewQuestionaireBean.getDescription());

	}
	
				
	public void setCreatedTime(String obj){

		providerReviewQuestionaireBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		providerReviewQuestionaireBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		providerReviewQuestionaireBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		providerReviewQuestionaireBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		providerReviewQuestionaireBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		providerReviewQuestionaireBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		providerReviewQuestionaireBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		providerReviewQuestionaireBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		providerReviewQuestionaireBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		providerReviewQuestionaireBean.getDeletedStatus());

	}
	
				
	public void setModifiedTime(String obj){

		providerReviewQuestionaireBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		providerReviewQuestionaireBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		providerReviewQuestionaireBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		providerReviewQuestionaireBean.getModifiedBy());

	}
	
				
	public void setStatus(String obj){

		providerReviewQuestionaireBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		providerReviewQuestionaireBean.getStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 

}
