
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.AuthMember;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * AuthMember is a mapping of auth_member Table.
*/
public class AuthMemberForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewAuthMember = false;
	private AuthMember authMemberBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public AuthMemberForm()
    {
    	this.authMemberBean = new AuthMember();
    	this.isNewAuthMember = true;
    }
    public AuthMemberForm (AuthMember object){
		this.authMemberBean = object;
    }
    public boolean isNewAuthMember (){

    	return this.isNewAuthMember;
    }
	public AuthMember getAuthMember (){
		return this.authMemberBean ;
	}
	public void setAuthMember (AuthMember object){
		this.authMemberBean = object;
	}

			
	public void setAuthMemberId(String obj){

		authMemberBean.setAuthMemberId(StringUtil.getIntegerValue(obj,0));

	}

	public String getAuthMemberId(){
		return StringUtil.getStringValue(
		authMemberBean.getAuthMemberId());

	}
	
				
	public void setMemberId(String obj){

		authMemberBean.setMemberId(StringUtil.getIntegerValue(obj,0));

	}

	public String getMemberId(){
		return StringUtil.getStringValue(
		authMemberBean.getMemberId());

	}
	
					public void setUsername(String obj){

		authMemberBean.setUsername(new String(obj));

	}

	public String getUsername(){
		return StringUtil.getStringValue(
		authMemberBean.getUsername());

	}
	
					public void setPassword(String obj){

		authMemberBean.setPassword(new String(obj));

	}

	public String getPassword(){
		return StringUtil.getStringValue(
		authMemberBean.getPassword());

	}
	
					public void setCreatedBy(String obj){

		authMemberBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		authMemberBean.getCreatedBy());

	}
	
				
	public void setCreatedTime(String obj){

		authMemberBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		authMemberBean.getCreatedTime());

	}

	
					public void setModifiedBy(String obj){

		authMemberBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		authMemberBean.getModifiedBy());

	}
	
				
	public void setModifiedTime(String obj){

		authMemberBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		authMemberBean.getModifiedTime());

	}

	
				
	public void setDeletedTime(String obj){

		authMemberBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		authMemberBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		authMemberBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		authMemberBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		authMemberBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		authMemberBean.getDeletedStatus());

	}
	
				
	public void setStatus(String obj){

		authMemberBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		authMemberBean.getStatus());

	}
	
					public void setActivationCode(String obj){

		authMemberBean.setActivationCode(new String(obj));

	}

	public String getActivationCode(){
		return StringUtil.getStringValue(
		authMemberBean.getActivationCode());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
