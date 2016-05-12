
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * UserGroupAssignment is a mapping of user_group_assignment Table.
*/
public class UserGroupAssignmentForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewUserGroupAssignment = false;
	private UserGroupAssignment userGroupAssignmentBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public UserGroupAssignmentForm()
    {
    	this.userGroupAssignmentBean = new UserGroupAssignment();
    	this.isNewUserGroupAssignment = true;
    }
    public UserGroupAssignmentForm (UserGroupAssignment object){
		this.userGroupAssignmentBean = object;
    }
    public boolean isNewUserGroupAssignment (){

    	return this.isNewUserGroupAssignment;
    }
	public UserGroupAssignment getUserGroupAssignment (){
		return this.userGroupAssignmentBean ;
	}
	public void setUserGroupAssignment (UserGroupAssignment object){
		this.userGroupAssignmentBean = object;
	}

			
	public void setId(String obj){

		userGroupAssignmentBean.setId(StringUtil.getIntegerValue(obj,0));

	}

	public String getId(){
		return StringUtil.getStringValue(
		userGroupAssignmentBean.getId());

	}
	
				
	public void setUserId(String obj){

		userGroupAssignmentBean.setUserId(StringUtil.getIntegerValue(obj,0));

	}

	public String getUserId(){
		return StringUtil.getStringValue(
		userGroupAssignmentBean.getUserId());

	}
	
				
	public void setMemberGroupId(String obj){

		userGroupAssignmentBean.setMemberGroupId(StringUtil.getIntegerValue(obj,0));

	}

	public String getMemberGroupId(){
		return StringUtil.getStringValue(
		userGroupAssignmentBean.getMemberGroupId());

	}
	
				
	public void setCreatedTime(String obj){

		userGroupAssignmentBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		userGroupAssignmentBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		userGroupAssignmentBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		userGroupAssignmentBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		userGroupAssignmentBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		userGroupAssignmentBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		userGroupAssignmentBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		userGroupAssignmentBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		userGroupAssignmentBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		userGroupAssignmentBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		userGroupAssignmentBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		userGroupAssignmentBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		userGroupAssignmentBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		userGroupAssignmentBean.getDeletedStatus());

	}
	
				
	public void setStatus(String obj){

		userGroupAssignmentBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		userGroupAssignmentBean.getStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
