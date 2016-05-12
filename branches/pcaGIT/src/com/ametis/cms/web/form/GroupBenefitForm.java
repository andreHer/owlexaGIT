
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.GroupBenefit;
import com.ametis.cms.datamodel.ItemCategory;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * GroupBenefit is a mapping of group_benefit Table.
*/
public class GroupBenefitForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewGroupBenefit = false;
	private GroupBenefit groupBenefitBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public GroupBenefitForm()
    {
    	this.groupBenefitBean = new GroupBenefit();
    	this.isNewGroupBenefit = true;
    }
    public GroupBenefitForm (GroupBenefit object){
		this.groupBenefitBean = object;
    }
    public boolean isNewGroupBenefit (){

    	return this.isNewGroupBenefit;
    }
	public GroupBenefit getGroupBenefit (){
		return this.groupBenefitBean ;
	}
	public void setGroupBenefit (GroupBenefit object){
		this.groupBenefitBean = object;
	}

			
	public void setGroupBenefitId(String obj){

		groupBenefitBean.setGroupBenefitId(StringUtil.getIntegerValue(obj,0));

	}

	public String getGroupBenefitId(){
		return StringUtil.getStringValue(
		groupBenefitBean.getGroupBenefitId());

	}
	
										
	public void setBenefitLimit(String obj){

		groupBenefitBean.setBenefitLimit(StringUtil.getDoubleValue(obj,0));

	}

	public String getBenefitLimit(){
		return StringUtil.getStringValue(
		groupBenefitBean.getBenefitLimit());

	}
	
				
	public void setCreatedTime(String obj){

		groupBenefitBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		groupBenefitBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		groupBenefitBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		groupBenefitBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		groupBenefitBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		groupBenefitBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		groupBenefitBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		groupBenefitBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		groupBenefitBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		groupBenefitBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		groupBenefitBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		groupBenefitBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		groupBenefitBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		groupBenefitBean.getDeletedStatus());

	}
	
				
	public void setBenefitType(String obj){

		groupBenefitBean.setBenefitType(StringUtil.getIntegerValue(obj,0));

	}

	public String getBenefitType(){
		return StringUtil.getStringValue(
		groupBenefitBean.getBenefitType());

	}
	
		

	// foreign affairs
	
	

	
	public void setMemberGroupId(String obj){
		MemberGroup fk = new MemberGroup();
		fk.setMemberGroupId(StringUtil.getIntegerValue(obj,0));
		groupBenefitBean.setMemberGroupId(fk);

	}

	public String getMemberGroupId(){
		return StringUtil.getStringValue(
		groupBenefitBean.getMemberGroupId().getMemberGroupId());

	}
	//---
	
	

	
	public void setItemCategoryId(String obj){
		ItemCategory fk = new ItemCategory();
		fk.setItemCategoryId(StringUtil.getIntegerValue(obj,0));
		groupBenefitBean.setItemCategoryId(fk);

	}

	public String getItemCategoryId(){
		return StringUtil.getStringValue(
		groupBenefitBean.getItemCategoryId().getItemCategoryId());

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
