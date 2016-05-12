
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.Benefit;
import com.ametis.cms.datamodel.ItemCategory;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberBenefit;
import com.ametis.cms.datamodel.Product;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * MemberBenefit is a mapping of member_benefit Table.
*/
public class MemberBenefitForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewMemberBenefit = false;
	private MemberBenefit memberBenefitBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public MemberBenefitForm()
    {
    	this.memberBenefitBean = new MemberBenefit();
    	this.isNewMemberBenefit = true;
    }
    public MemberBenefitForm (MemberBenefit object){
		this.memberBenefitBean = object;
    }
    public boolean isNewMemberBenefit (){

    	return this.isNewMemberBenefit;
    }
	public MemberBenefit getMemberBenefit (){
		return this.memberBenefitBean ;
	}
	public void setMemberBenefit (MemberBenefit object){
		this.memberBenefitBean = object;
	}

			
	public void setMemberBenefitId(String obj){

		memberBenefitBean.setMemberBenefitId(StringUtil.getIntegerValue(obj,0));

	}

	public String getMemberBenefitId(){
		return StringUtil.getStringValue(
		memberBenefitBean.getMemberBenefitId());

	}
	
							
	public void setProductId(Product obj){

		memberBenefitBean.setProductId(obj);

	}

	public Product getProductId(){
		return memberBenefitBean.getProductId();

	}
	
							
	public void setCreatedTime(String obj){

		memberBenefitBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		memberBenefitBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		memberBenefitBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		memberBenefitBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		memberBenefitBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		memberBenefitBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		memberBenefitBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		memberBenefitBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		memberBenefitBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		memberBenefitBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		memberBenefitBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		memberBenefitBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		memberBenefitBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		memberBenefitBean.getDeletedStatus());

	}
	
		

	// foreign affairs
	
	

	
	public void setMemberId(String obj){
		Member fk = new Member();
		fk.setMemberId(StringUtil.getIntegerValue(obj,0));
		memberBenefitBean.setMemberId(fk);

	}

	public String getMemberId(){
		return StringUtil.getStringValue(
		memberBenefitBean.getMemberId().getMemberId());

	}
	//---
	
	

	
	public void setItemCategoryId(String obj){
		ItemCategory fk = new ItemCategory();
		fk.setItemCategoryId(StringUtil.getIntegerValue(obj,0));
		memberBenefitBean.setItemCategoryId(fk);

	}

	public String getItemCategoryId(){
		return StringUtil.getStringValue(
		memberBenefitBean.getItemCategoryId().getItemCategoryId());

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
