
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * ProviderReview is a mapping of provider_review Table.
*/
public class ProviderReviewForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewProviderReview = false;
	private ProviderReview providerReviewBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ProviderReviewForm()
    {
    	this.providerReviewBean = new ProviderReview();
    	this.isNewProviderReview = true;
    }
    public ProviderReviewForm (ProviderReview object){
		this.providerReviewBean = object;
    }
    public boolean isNewProviderReview (){

    	return this.isNewProviderReview;
    }
	public ProviderReview getProviderReview (){
		return this.providerReviewBean ;
	}
	public void setProviderReview (ProviderReview object){
		this.providerReviewBean = object;
	}

			
	public void setProviderReviewId(String obj){

		providerReviewBean.setProviderReviewId(StringUtil.getLongValue(obj,0));

	}

	public String getProviderReviewId(){
		return StringUtil.getStringValue(
		providerReviewBean.getProviderReviewId());

	}
	
				
	public void setProviderId(String obj){

		providerReviewBean.setProviderId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProviderId(){
		return StringUtil.getStringValue(
		providerReviewBean.getProviderId());

	}
	
				
	public void setMemberId(String obj){

		providerReviewBean.setMemberId(StringUtil.getIntegerValue(obj,0));

	}

	public String getMemberId(){
		return StringUtil.getStringValue(
		providerReviewBean.getMemberId());

	}
	
				
	public void setRating(String obj){

		providerReviewBean.setRating(StringUtil.getIntegerValue(obj,0));

	}

	public String getRating(){
		return StringUtil.getStringValue(
		providerReviewBean.getRating());

	}
	
					public void setDescription(String obj){

		providerReviewBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		providerReviewBean.getDescription());

	}
	
				
	public void setCreatedTime(String obj){

		providerReviewBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		providerReviewBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		providerReviewBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		providerReviewBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		providerReviewBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		providerReviewBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		providerReviewBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		providerReviewBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		providerReviewBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		providerReviewBean.getDeletedStatus());

	}
	
					public void setDeletedBy(String obj){

		providerReviewBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		providerReviewBean.getDeletedBy());

	}
	
				
	public void setDeletedTime(String obj){

		providerReviewBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		providerReviewBean.getDeletedTime());

	}

	
					public void setCallerName(String obj){

		providerReviewBean.setCallerName(new String(obj));

	}

	public String getCallerName(){
		return StringUtil.getStringValue(
		providerReviewBean.getCallerName());

	}
	
				
	public void setMemberGroupId(String obj){

		providerReviewBean.setMemberGroupId(StringUtil.getIntegerValue(obj,0));

	}

	public String getMemberGroupId(){
		return StringUtil.getStringValue(
		providerReviewBean.getMemberGroupId());

	}
	
				
	public void setVotingDate(String obj){

		providerReviewBean.setVotingDate(java.sql.Date.valueOf(obj));

	}

	public String getVotingDate(){
		return StringUtil.getStringValue(
		providerReviewBean.getVotingDate());

	}

	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 

}
