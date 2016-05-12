
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.Action;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * Action is a mapping of action Table.
*/
public class ActionForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewAction = false;
	private Action actionBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ActionForm()
    {
    	this.actionBean = new Action();
    	this.isNewAction = true;
    }
    public ActionForm (Action object){
		this.actionBean = object;
    }
    public boolean isNewAction (){

    	return this.isNewAction;
    }
	public Action getAction (){
		return this.actionBean ;
	}
	public void setAction (Action object){
		this.actionBean = object;
	}

			
	public void setActionId(String obj){

		actionBean.setActionId(StringUtil.getIntegerValue(obj,0));

	}

	public String getActionId(){
		return StringUtil.getStringValue(
		actionBean.getActionId());

	}
	
					public void setActionName(String obj){

		actionBean.setActionName(new String(obj));

	}

	public String getActionName(){
		return StringUtil.getStringValue(
		actionBean.getActionName());

	}
	
					public void setDescription(String obj){

		actionBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		actionBean.getDescription());

	}
	public void setActionCode (String obj){
		actionBean.setActionCode(new String (obj));
	}
	public String getActionCode (){
		return StringUtil.getStringValue(actionBean.getActionCode());
	}
					public void setActionUrl(String obj){

		actionBean.setActionUrl(new String(obj));

	}

	public String getActionUrl(){
		return StringUtil.getStringValue(
		actionBean.getActionUrl());

	}
	
				
	public void setCreatedTime(String obj){

		actionBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		actionBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		actionBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		actionBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		actionBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		actionBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		actionBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		actionBean.getModifiedBy());

	}
	
					public void setDeletedBy(String obj){

		actionBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		actionBean.getDeletedBy());

	}
	
				
	public void setDeletedTime(String obj){

		actionBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		actionBean.getDeletedTime());

	}

	
				
	public void setDeletedStatus(String obj){

		actionBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		actionBean.getDeletedStatus());

	}
	
				
	public void setActionGroupId(String obj){

		actionBean.setActionGroupId(StringUtil.getIntegerValue(obj,0));

	}

	public String getActionGroupId(){
		return StringUtil.getStringValue(
		actionBean.getActionGroupId());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
