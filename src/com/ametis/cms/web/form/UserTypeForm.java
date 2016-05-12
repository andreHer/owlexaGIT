
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * UserType is a mapping of user_type Table.
*/
public class UserTypeForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewUserType = false;
	private UserType userTypeBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public UserTypeForm()
    {
    	this.userTypeBean = new UserType();
    	this.isNewUserType = true;
    }
    public UserTypeForm (UserType object){
		this.userTypeBean = object;
    }
    public boolean isNewUserType (){

    	return this.isNewUserType;
    }
	public UserType getUserType (){
		return this.userTypeBean ;
	}
	public void setUserType (UserType object){
		this.userTypeBean = object;
	}

			
	public void setUserTypeId(String obj){

		userTypeBean.setUserTypeId(StringUtil.getIntegerValue(obj,0));

	}

	public String getUserTypeId(){
		return StringUtil.getStringValue(
		userTypeBean.getUserTypeId());

	}
	
					public void setUserTypeName(String obj){

		userTypeBean.setUserTypeName(new String(obj));

	}

	public String getUserTypeName(){
		return StringUtil.getStringValue(
		userTypeBean.getUserTypeName());

	}
	
					public void setDescription(String obj){

		userTypeBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		userTypeBean.getDescription());

	}
	
				
	public void setCreatedTime(String obj){

		userTypeBean.setCreatedTime(java.sql.Time.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		userTypeBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		userTypeBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		userTypeBean.getCreatedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		userTypeBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		userTypeBean.getDeletedStatus());

	}
	
					public void setDeletedBy(String obj){

		userTypeBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		userTypeBean.getDeletedBy());

	}
	
				
	public void setDeletedTime(String obj){

		userTypeBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		userTypeBean.getDeletedTime());

	}

	
				
	public void setModifiedTime(String obj){

		userTypeBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		userTypeBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		userTypeBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		userTypeBean.getModifiedBy());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
