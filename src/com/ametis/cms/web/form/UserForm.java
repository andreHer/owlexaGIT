
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.Role;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * User is a mapping of user Table.
*/
public class UserForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewUser = false;
	private User userBean ;
	
	private Integer institutionId;
	private String institutionName;
	
	private String password;
	private String confirmPassword;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public UserForm()
    {
    	this.userBean = new User();
    	this.isNewUser = true;
    }
    public UserForm (User object){
		this.userBean = object;
    }
    public boolean isNewUser (){

    	return this.isNewUser;
    }
	public User getUser (){
		return this.userBean ;
	}
	public void setUser (User object){
		this.userBean = object;
	}

			
	public void setUserId(String obj){

		userBean.setUserId(StringUtil.getIntegerValue(obj,0));

	}

	public String getUserId(){
		return StringUtil.getStringValue(
		userBean.getUserId());

	}
	
					public void setUsername(String obj){

		userBean.setUsername(new String(obj));

	}

	public String getUsername(){
		return StringUtil.getStringValue(
		userBean.getUsername());

	}
	
					public void setPassword(String obj){

		userBean.setPassword(new String(obj));

	}

	public String getPassword(){
		return StringUtil.getStringValue(
		userBean.getPassword());

	}
	
								public void setFirstName(String obj){

		userBean.setFirstName(new String(obj));

	}

	public String getFirstName(){
		return StringUtil.getStringValue(
		userBean.getFirstName());

	}
	
					public void setLastName(String obj){

		userBean.setLastName(new String(obj));

	}

	public String getLastName(){
		return StringUtil.getStringValue(
		userBean.getLastName());

	}
	
					public void setEmail(String obj){

		userBean.setEmail(new String(obj));

	}

	public String getEmail(){
		return StringUtil.getStringValue(
		userBean.getEmail());

	}
	
					public void setTelephone(String obj){

		userBean.setTelephone(new String(obj));

	}

	public String getTelephone(){
		return StringUtil.getStringValue(
		userBean.getTelephone());

	}
	
					public void setMobilePhone(String obj){

		userBean.setMobilePhone(new String(obj));

	}

	public String getMobilePhone(){
		return StringUtil.getStringValue(
		userBean.getMobilePhone());

	}
	
					public void setTelephoneExt(String obj){

		userBean.setTelephoneExt(new String(obj));

	}

	public String getTelephoneExt(){
		return StringUtil.getStringValue(
		userBean.getTelephoneExt());

	}
	
					public void setDescription(String obj){

		userBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		userBean.getDescription());

	}
	
					public void setCreatedBy(String obj){

		userBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		userBean.getCreatedBy());

	}
	
				
	public void setCreatedTime(String obj){

		userBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		userBean.getCreatedTime());

	}

	
					public void setModifiedBy(String obj){

		userBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		userBean.getModifiedBy());

	}
	
				
	public void setModifiedTime(String obj){

		userBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		userBean.getModifiedTime());

	}

	
					public void setDeletedBy(String obj){

		userBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		userBean.getDeletedBy());

	}
	
				
	public void setDeletedTime(String obj){

		userBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		userBean.getDeletedTime());

	}

	
				
	public void setDeletedStatus(String obj){

		userBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		userBean.getDeletedStatus());

	}
	
				
	public void setStatus(SubscriptionStatus obj){

		userBean.setStatus(obj);

	}

	public SubscriptionStatus getStatus(){
		return 
		userBean.getStatus();

	}
	
		

	// foreign affairs
	
	

	
	public void setRoleId(Role obj){
		
		userBean.setRoleId(obj);

	}

	public Role getRoleId(){
		return userBean.getRoleId();

	}
	//---
		// -- foreign affairs end
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public User getUserBean() {
		return userBean;
	}
	public void setUserBean(User userBean) {
		this.userBean = userBean;

	}
	
	public void setUserType (String userType){
		if (userType != null && !userType.equalsIgnoreCase("")){
			this.userBean.setUserType(Integer.valueOf(userType));
		}
		else {
			this.userBean.setUserType(Integer.valueOf(2));
		}
	}
	public String getUserType (){
		String result = "";
		
		if (userBean.getUserType() != null){
			result = userBean.getUserType() + "";
		}
		
		return result;
	}
	public Integer getInstitutionId() {
		return institutionId;
	}
	public void setInstitutionId(Integer institutionId) {
		this.institutionId = institutionId;
	}
	public String getInstitutionName() {
		return institutionName;
	}
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}
	
	

// class+ 

// class- 
}
