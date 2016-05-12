
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.Role;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * Role is a mapping of role Table.
*/
public class RoleForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewRole = false;
	private Role roleBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public RoleForm()
    {
    	this.roleBean = new Role();
    	this.isNewRole = true;
    }
    public RoleForm (Role object){
		this.roleBean = object;
    }
    public boolean isNewRole (){

    	return this.isNewRole;
    }
	public Role getRole (){
		return this.roleBean ;
	}
	public void setRole (Role object){
		this.roleBean = object;
	}

			
	public void setRoleId(String obj){

		roleBean.setRoleId(StringUtil.getIntegerValue(obj,0));

	}

	public String getRoleId(){
		return StringUtil.getStringValue(
		roleBean.getRoleId());

	}
	
					public void setRoleName(String obj){

		roleBean.setRoleName(new String(obj));

	}

	public String getRoleName(){
		return StringUtil.getStringValue(
		roleBean.getRoleName());

	}
	
					public void setDescription(String obj){

		roleBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		roleBean.getDescription());

	}
	
					public void setCreatedBy(String obj){

		roleBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		roleBean.getCreatedBy());

	}
	
				
	public void setCreatedTime(String obj){

		roleBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		roleBean.getCreatedTime());

	}

	
					public void setModifiedBy(String obj){

		roleBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		roleBean.getModifiedBy());

	}
	
				
	public void setModifiedTime(String obj){

		roleBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		roleBean.getModifiedTime());

	}

	
					public void setDeletedBy(String obj){

		roleBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		roleBean.getDeletedBy());

	}
	
				
	public void setDeletedTime(String obj){

		roleBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		roleBean.getDeletedTime());

	}

	
				
	public void setDeletedStatus(String obj){

		roleBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		roleBean.getDeletedStatus());

	}
	

	public void setMenuTemplateURL(String obj){

		roleBean.setMenuTemplateUrl(obj);

	}

	public String getMenuTemplateURL(){
		return roleBean.getMenuTemplateUrl();

	}
	public void setSidebarTemplateURL(String obj){

		roleBean.setSidebarTemplateUrl(obj);

	}

	public String getSidebarTemplateURL(){
		return roleBean.getSidebarTemplateUrl();

	}

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
