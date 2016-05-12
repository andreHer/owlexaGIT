
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * Notification is a mapping of notification Table.
*/
public class NotificationForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewNotification = false;
	private Notification notificationBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public NotificationForm()
    {
    	this.notificationBean = new Notification();
    	this.isNewNotification = true;
    }
    public NotificationForm (Notification object){
		this.notificationBean = object;
    }
    public boolean isNewNotification (){

    	return this.isNewNotification;
    }
	public Notification getNotification (){
		return this.notificationBean ;
	}
	public void setNotification (Notification object){
		this.notificationBean = object;
	}

			
	public void setId(String obj){

		notificationBean.setId(StringUtil.getLongValue(obj,0));

	}

	public String getId(){
		return StringUtil.getStringValue(
		notificationBean.getId());

	}
	
					public void setDestination(String obj){

		notificationBean.setDestination(new String(obj));

	}

	public String getDestination(){
		return StringUtil.getStringValue(
		notificationBean.getDestination());

	}
	
					public void setSender(String obj){

		notificationBean.setSender(new String(obj));

	}

	public String getSender(){
		return StringUtil.getStringValue(
		notificationBean.getSender());

	}
	
					public void setContent(String obj){

		notificationBean.setContent(new String(obj));

	}

	public String getContent(){
		return StringUtil.getStringValue(
		notificationBean.getContent());

	}
	
				
	public void setCreatedTime(String obj){

		notificationBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		notificationBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		notificationBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		notificationBean.getCreatedBy());

	}
	
				
	public void setMemberId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Member member = new Member();
			member.setMemberId(Integer.valueOf(obj));
			notificationBean.setMemberId(member);
		}

	}

	public String getMemberId(){
		return StringUtil.getStringValue(
		notificationBean.getMemberId());

	}
	
				
	public void setTemplateId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			MessageTemplate template = new MessageTemplate();
			template.setId(Integer.valueOf(obj));
			notificationBean.setTemplateId(template);
		}

	}

	public String getTemplateId(){
		return StringUtil.getStringValue(
		notificationBean.getTemplateId());

	}
	
				
	public void setClientId(String obj){
		
		if (obj != null && !obj.equalsIgnoreCase("")){

			Client client = new Client();
			client.setClientId(Integer.valueOf(obj));
			notificationBean.setClientId(client);
		}

	}

	public String getClientId(){
		return StringUtil.getStringValue(
		notificationBean.getClientId());

	}
	
				
	public void setProviderId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Provider provider = new Provider();
			provider.setProviderId(Integer.valueOf(obj));		
			notificationBean.setProviderId(provider);
		}
		

	}

	public String getProviderId(){
		return StringUtil.getStringValue(
		notificationBean.getProviderId());

	}
	
				
	public void setMessageType(String obj){

		notificationBean.setMessageType(StringUtil.getIntegerValue(obj,0));

	}

	public String getMessageType(){
		return StringUtil.getStringValue(
		notificationBean.getMessageType());

	}
	
				
	public void setStatus(String obj){

		notificationBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		notificationBean.getStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
