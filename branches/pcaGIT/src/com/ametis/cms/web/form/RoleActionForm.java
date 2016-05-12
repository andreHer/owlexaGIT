
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.Action;
import com.ametis.cms.datamodel.Role;
import com.ametis.cms.datamodel.RoleAction;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * RoleAction is a mapping of role_action Table.
*/
public class RoleActionForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewRoleAction = false;
	private RoleAction roleActionBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public RoleActionForm()
    {
    	this.roleActionBean = new RoleAction();
    	this.isNewRoleAction = true;
    }
    public RoleActionForm (RoleAction object){
		this.roleActionBean = object;
    }
    public boolean isNewRoleAction (){

    	return this.isNewRoleAction;
    }
	public RoleAction getRoleAction (){
		return this.roleActionBean ;
	}
	public void setRoleAction (RoleAction object){
		this.roleActionBean = object;
	}

			
	public void setRoleActionId(String obj){

		roleActionBean.setRoleActionId(StringUtil.getIntegerValue(obj,0));

	}

	public String getRoleActionId(){
		return StringUtil.getStringValue(
		roleActionBean.getRoleActionId());

	}
	
										
	public void setHapus(String obj){

		roleActionBean.setHapus(StringUtil.getIntegerValue(obj,0));

	}

	public String getHapus(){
		return StringUtil.getStringValue(
		roleActionBean.getHapus());

	}
	
				
	public void setBaca(String obj){

		roleActionBean.setBaca(StringUtil.getIntegerValue(obj,0));

	}

	public String getBaca(){
		return StringUtil.getStringValue(
		roleActionBean.getBaca());

	}
	
				
	public void setUbah(String obj){

		roleActionBean.setUbah(StringUtil.getIntegerValue(obj,0));

	}

	public String getUbah(){
		return StringUtil.getStringValue(
		roleActionBean.getUbah());

	}
	
				
	public void setTambah(String obj){

		roleActionBean.setTambah(StringUtil.getIntegerValue(obj,0));

	}

	public String getTambah(){
		return StringUtil.getStringValue(
		roleActionBean.getTambah());

	}
	
					public void setCreatedBy(String obj){

		roleActionBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		roleActionBean.getCreatedBy());

	}
	
				
	public void setCreatedTime(String obj){

		roleActionBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		roleActionBean.getCreatedTime());

	}

	
					public void setModifiedBy(String obj){

		roleActionBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		roleActionBean.getModifiedBy());

	}
	
				
	public void setModifiedTime(String obj){

		roleActionBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		roleActionBean.getModifiedTime());

	}

	
		

	// foreign affairs
	
	

	
	public void setActionId(String obj){
		Action fk = new Action();
		fk.setActionId(StringUtil.getIntegerValue(obj,0));
		roleActionBean.setActionId(fk);

	}

	public String getActionId(){
		return StringUtil.getStringValue(
		roleActionBean.getActionId().getActionId());

	}
	//---
	
	

	
	public void setRoleId(String obj){
		Role fk = new Role();
		fk.setRoleId(StringUtil.getIntegerValue(obj,0));
		roleActionBean.setRoleId(fk);

	}

	public String getRoleId(){
		return StringUtil.getStringValue(
		roleActionBean.getRoleId().getRoleId());

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
