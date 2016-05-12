
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.EventCategory;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * EventCategory is a mapping of event_category Table.
*/
public class EventCategoryForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewEventCategory = false;
	private EventCategory eventCategoryBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public EventCategoryForm()
    {
    	this.eventCategoryBean = new EventCategory();
    	this.isNewEventCategory = true;
    }
    public EventCategoryForm (EventCategory object){
		this.eventCategoryBean = object;
    }
    public boolean isNewEventCategory (){

    	return this.isNewEventCategory;
    }
	public EventCategory getEventCategory (){
		return this.eventCategoryBean ;
	}
	public void setEventCategory (EventCategory object){
		this.eventCategoryBean = object;
	}

			
	public void setEventCategoryId(String obj){

		eventCategoryBean.setEventCategoryId(StringUtil.getIntegerValue(obj,0));

	}

	public String getEventCategoryId(){
		return StringUtil.getStringValue(
		eventCategoryBean.getEventCategoryId());

	}
	
					public void setEventCategoryName(String obj){

		eventCategoryBean.setEventCategoryName(new String(obj));

	}

	public String getEventCategoryName(){
		return StringUtil.getStringValue(
		eventCategoryBean.getEventCategoryName());

	}
	
					public void setDescription(String obj){

		eventCategoryBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		eventCategoryBean.getDescription());

	}
	
				
	public void setCreatedTime(String obj){

		eventCategoryBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		eventCategoryBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		eventCategoryBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		eventCategoryBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		eventCategoryBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		eventCategoryBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		eventCategoryBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		eventCategoryBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		eventCategoryBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		eventCategoryBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		eventCategoryBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		eventCategoryBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		eventCategoryBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		eventCategoryBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
