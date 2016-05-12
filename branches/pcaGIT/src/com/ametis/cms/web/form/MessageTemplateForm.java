
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * MessageTemplate is a mapping of message_template Table.
*/
public class MessageTemplateForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewMessageTemplate = false;
	private MessageTemplate messageTemplateBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public MessageTemplateForm()
    {
    	this.messageTemplateBean = new MessageTemplate();
    	this.isNewMessageTemplate = true;
    }
    public MessageTemplateForm (MessageTemplate object){
		this.messageTemplateBean = object;
    }
    public boolean isNewMessageTemplate (){

    	return this.isNewMessageTemplate;
    }
	public MessageTemplate getMessageTemplate (){
		return this.messageTemplateBean ;
	}
	public void setMessageTemplate (MessageTemplate object){
		this.messageTemplateBean = object;
	}

			
	public void setId(String obj){

		messageTemplateBean.setId(StringUtil.getIntegerValue(obj,0));

	}

	public String getId(){
		return StringUtil.getStringValue(
		messageTemplateBean.getId());

	}
	
					public void setTemplateName(String obj){

		messageTemplateBean.setTemplateName(new String(obj));

	}

	public String getTemplateName(){
		return StringUtil.getStringValue(
		messageTemplateBean.getTemplateName());

	}
	
					public void setTemplateUrl(String obj){

		messageTemplateBean.setTemplateUrl(new String(obj));

	}

	public String getTemplateUrl(){
		return StringUtil.getStringValue(
		messageTemplateBean.getTemplateUrl());

	}
	
					public void setDescription(String obj){

		messageTemplateBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		messageTemplateBean.getDescription());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
