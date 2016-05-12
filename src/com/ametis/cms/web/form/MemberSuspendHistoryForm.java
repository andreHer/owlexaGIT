
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * MemberSuspendHistory is a mapping of member_suspend_history Table.
*/
public class MemberSuspendHistoryForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewMemberSuspendHistory = false;
	private MemberSuspendHistory memberSuspendHistoryBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public MemberSuspendHistoryForm()
    {
    	this.memberSuspendHistoryBean = new MemberSuspendHistory();
    	this.isNewMemberSuspendHistory = true;
    }
    public MemberSuspendHistoryForm (MemberSuspendHistory object){
		this.memberSuspendHistoryBean = object;
    }
    public boolean isNewMemberSuspendHistory (){

    	return this.isNewMemberSuspendHistory;
    }
	public MemberSuspendHistory getMemberSuspendHistory (){
		return this.memberSuspendHistoryBean ;
	}
	public void setMemberSuspendHistory (MemberSuspendHistory object){
		this.memberSuspendHistoryBean = object;
	}

			
	public void setMemberSuspendHistoryId(String obj){

		memberSuspendHistoryBean.setMemberSuspendHistoryId(StringUtil.getIntegerValue(obj,0));

	}

	public String getMemberSuspendHistoryId(){
		return StringUtil.getStringValue(
		memberSuspendHistoryBean.getMemberSuspendHistoryId());

	}
	
							
	public void setStartDate(String obj){

		memberSuspendHistoryBean.setStartDate(java.sql.Date.valueOf(obj));

	}

	public String getStartDate(){
		return StringUtil.getStringValue(
		memberSuspendHistoryBean.getStartDate());

	}

	
				
	public void setEndDate(String obj){

		memberSuspendHistoryBean.setEndDate(java.sql.Date.valueOf(obj));

	}

	public String getEndDate(){
		return StringUtil.getStringValue(
		memberSuspendHistoryBean.getEndDate());

	}

	
					public void setSuspendType(String obj){

		memberSuspendHistoryBean.setSuspendType(new String(obj));

	}

	public String getSuspendType(){
		return StringUtil.getStringValue(
		memberSuspendHistoryBean.getSuspendType());

	}
	
				
	public void setCreatedTime(String obj){

		memberSuspendHistoryBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		memberSuspendHistoryBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		memberSuspendHistoryBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		memberSuspendHistoryBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		memberSuspendHistoryBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		memberSuspendHistoryBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		memberSuspendHistoryBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		memberSuspendHistoryBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		memberSuspendHistoryBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		memberSuspendHistoryBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		memberSuspendHistoryBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		memberSuspendHistoryBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		memberSuspendHistoryBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		memberSuspendHistoryBean.getDeletedStatus());

	}
	
				
	public void setSuspendStatus(String obj){

		memberSuspendHistoryBean.setSuspendStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getSuspendStatus(){
		return StringUtil.getStringValue(
		memberSuspendHistoryBean.getSuspendStatus());

	}
	
		

	// foreign affairs
	
	

	
	public void setMemberId(String obj){
		Member fk = new Member();
		fk.setMemberId(StringUtil.getIntegerValue(obj,0));
		memberSuspendHistoryBean.setMemberId(fk);

	}

	public String getMemberId(){
		return StringUtil.getStringValue(
		memberSuspendHistoryBean.getMemberId().getMemberId());

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
