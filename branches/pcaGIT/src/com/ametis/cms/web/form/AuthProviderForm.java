
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.AuthProvider;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * AuthProvider is a mapping of auth_provider Table.
*/
public class AuthProviderForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewAuthProvider = false;
	private AuthProvider authProviderBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public AuthProviderForm()
    {
    	this.authProviderBean = new AuthProvider();
    	this.isNewAuthProvider = true;
    }
    public AuthProviderForm (AuthProvider object){
		this.authProviderBean = object;
    }
    public boolean isNewAuthProvider (){

    	return this.isNewAuthProvider;
    }
	public AuthProvider getAuthProvider (){
		return this.authProviderBean ;
	}
	public void setAuthProvider (AuthProvider object){
		this.authProviderBean = object;
	}

			
	public void setAuthProviderId(String obj){

		authProviderBean.setAuthProviderId(StringUtil.getIntegerValue(obj,0));

	}

	public String getAuthProviderId(){
		return StringUtil.getStringValue(
		authProviderBean.getAuthProviderId());

	}
	
				
	public void setProviderId(String obj){

		authProviderBean.setProviderId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProviderId(){
		return StringUtil.getStringValue(
		authProviderBean.getProviderId());

	}
	
					public void setUsername(String obj){

		authProviderBean.setUsername(new String(obj));

	}

	public String getUsername(){
		return StringUtil.getStringValue(
		authProviderBean.getUsername());

	}
	
					public void setPassword(String obj){

		authProviderBean.setPassword(new String(obj));

	}

	public String getPassword(){
		return StringUtil.getStringValue(
		authProviderBean.getPassword());

	}
	
					public void setActivationCode(String obj){

		authProviderBean.setActivationCode(new String(obj));

	}

	public String getActivationCode(){
		return StringUtil.getStringValue(
		authProviderBean.getActivationCode());

	}
	
				
	public void setStatus(String obj){

		authProviderBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		authProviderBean.getStatus());

	}
	
				
	public void setCreatedTime(String obj){

		authProviderBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		authProviderBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		authProviderBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		authProviderBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		authProviderBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		authProviderBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		authProviderBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		authProviderBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		authProviderBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		authProviderBean.getDeletedStatus());

	}
	
					public void setDeletedBy(String obj){

		authProviderBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		authProviderBean.getDeletedBy());

	}
	
				
	public void setDeletedTime(String obj){

		authProviderBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		authProviderBean.getDeletedTime());

	}

	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
