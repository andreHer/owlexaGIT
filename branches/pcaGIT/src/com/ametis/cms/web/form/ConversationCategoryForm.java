
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.ConversationCategory;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * ConversationCategory is a mapping of conversation_category Table.
*/
public class ConversationCategoryForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewConversationCategory = false;
	private ConversationCategory conversationCategoryBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ConversationCategoryForm()
    {
    	this.conversationCategoryBean = new ConversationCategory();
    	this.isNewConversationCategory = true;
    }
    public ConversationCategoryForm (ConversationCategory object){
		this.conversationCategoryBean = object;
    }
    public boolean isNewConversationCategory (){

    	return this.isNewConversationCategory;
    }
	public ConversationCategory getConversationCategory (){
		return this.conversationCategoryBean ;
	}
	public void setConversationCategory (ConversationCategory object){
		this.conversationCategoryBean = object;
	}

			
	public void setConversationCategoryId(String obj){

		conversationCategoryBean.setConversationCategoryId(StringUtil.getIntegerValue(obj,0));

	}

	public String getConversationCategoryId(){
		return StringUtil.getStringValue(
		conversationCategoryBean.getConversationCategoryId());

	}
	
					public void setConversationCategoryName(String obj){

		conversationCategoryBean.setConversationCategoryName(new String(obj));

	}

	public String getConversationCategoryName(){
		return StringUtil.getStringValue(
		conversationCategoryBean.getConversationCategoryName());

	}
	
					public void setDescription(String obj){

		conversationCategoryBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		conversationCategoryBean.getDescription());

	}
	
					public void setCreatedBy(String obj){

		conversationCategoryBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		conversationCategoryBean.getCreatedBy());

	}
	
				
	public void setCreatedTime(String obj){

		conversationCategoryBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		conversationCategoryBean.getCreatedTime());

	}

	
					public void setUpdatedBy(String obj){

		conversationCategoryBean.setUpdatedBy(new String(obj));

	}

	public String getUpdatedBy(){
		return StringUtil.getStringValue(
		conversationCategoryBean.getUpdatedBy());

	}
	
				
	public void setUpdatedTime(String obj){

		conversationCategoryBean.setUpdatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getUpdatedTime(){
		return StringUtil.getStringValue(
		conversationCategoryBean.getUpdatedTime());

	}

	
				
	public void setDeletedTime(String obj){

		conversationCategoryBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		conversationCategoryBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		conversationCategoryBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		conversationCategoryBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		conversationCategoryBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		conversationCategoryBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
