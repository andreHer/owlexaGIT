
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.ActivityLog;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * ActivityLog is a mapping of activity_log Table.
*/
public class ActivityLogForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewActivityLog = false;
	private ActivityLog activityLogBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ActivityLogForm()
    {
    	this.activityLogBean = new ActivityLog();
    	this.isNewActivityLog = true;
    }
    public ActivityLogForm (ActivityLog object){
		this.activityLogBean = object;
    }
    public boolean isNewActivityLog (){

    	return this.isNewActivityLog;
    }
	public ActivityLog getActivityLog (){
		return this.activityLogBean ;
	}
	public void setActivityLog (ActivityLog object){
		this.activityLogBean = object;
	}

			
	public void setActivityLogId(String obj){

		activityLogBean.setActivityLogId(StringUtil.getIntegerValue(obj,0));

	}

	public String getActivityLogId(){
		return StringUtil.getStringValue(
		activityLogBean.getActivityLogId());

	}
	
					public void setUsername(String obj){

		activityLogBean.setUsername(new String(obj));

	}

	public String getUsername(){
		return StringUtil.getStringValue(
		activityLogBean.getUsername());

	}
	
					public void setActionUrl(String obj){

		activityLogBean.setActionUrl(new String(obj));

	}

	public String getActionUrl(){
		return StringUtil.getStringValue(
		activityLogBean.getActionUrl());

	}
	
					public void setAction(String obj){

		activityLogBean.setAction(new String(obj));

	}

	public String getAction(){
		return StringUtil.getStringValue(
		activityLogBean.getAction());

	}
	
					public void setDescription(String obj){

		activityLogBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		activityLogBean.getDescription());

	}
	
				
	public void setActivityLogTime(String obj){

		activityLogBean.setActivityLogTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getActivityLogTime(){
		return StringUtil.getStringValue(
		activityLogBean.getActivityLogTime());

	}

	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
