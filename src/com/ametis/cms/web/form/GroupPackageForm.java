
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.GroupPackage;
import com.ametis.cms.datamodel.InsurancePackage;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * GroupPackage is a mapping of group_package Table.
*/
public class GroupPackageForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewGroupPackage = false;
	private GroupPackage groupPackageBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public GroupPackageForm()
    {
    	this.groupPackageBean = new GroupPackage();
    	this.isNewGroupPackage = true;
    }
    public GroupPackageForm (GroupPackage object){
		this.groupPackageBean = object;
    }
    public boolean isNewGroupPackage (){

    	return this.isNewGroupPackage;
    }
	public GroupPackage getGroupPackage (){
		return this.groupPackageBean ;
	}
	public void setGroupPackage (GroupPackage object){
		this.groupPackageBean = object;
	}

			
	public void setGroupPackageId(String obj){

		groupPackageBean.setGroupPackageId(StringUtil.getIntegerValue(obj,0));

	}

	public String getGroupPackageId(){
		return StringUtil.getStringValue(
		groupPackageBean.getGroupPackageId());

	}
	
										
	public void setCreatedTime(String obj){

		groupPackageBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		groupPackageBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		groupPackageBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		groupPackageBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		groupPackageBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		groupPackageBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		groupPackageBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		groupPackageBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		groupPackageBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		groupPackageBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		groupPackageBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		groupPackageBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		groupPackageBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		groupPackageBean.getDeletedStatus());

	}
	
					


	
	public void setMemberGroupId(String obj){
		MemberGroup fk = new MemberGroup();
		fk.setMemberGroupId(StringUtil.getIntegerValue(obj,0));
		groupPackageBean.setMemberGroupId(fk);

	}

	public String getMemberGroupId(){
		return StringUtil.getStringValue(
		groupPackageBean.getMemberGroupId().getMemberGroupId());

	}
	//---
	
	

	
	public void setPackageId(String obj){
		InsurancePackage fk = new InsurancePackage();
		fk.setPackageId(StringUtil.getIntegerValue(obj,0));
		groupPackageBean.setPackageId(fk);

	}

	public String getPackageId(){
		return StringUtil.getStringValue(
		groupPackageBean.getPackageId().getPackageId());

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
