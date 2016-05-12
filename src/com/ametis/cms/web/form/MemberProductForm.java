
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberProduct;
import com.ametis.cms.datamodel.Product;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * MemberProduct is a mapping of member_product Table.
*/
public class MemberProductForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewMemberProduct = false;
	private MemberProduct memberProductBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public MemberProductForm()
    {
    	this.memberProductBean = new MemberProduct();
    	this.isNewMemberProduct = true;
    }
    public MemberProductForm (MemberProduct object){
		this.memberProductBean = object;
    }
    public boolean isNewMemberProduct (){

    	return this.isNewMemberProduct;
    }
	public MemberProduct getMemberProduct (){
		return this.memberProductBean ;
	}
	public void setMemberProduct (MemberProduct object){
		this.memberProductBean = object;
	}

			
	public void setMemberProductId(String obj){

		memberProductBean.setMemberProductId(StringUtil.getIntegerValue(obj,0));

	}

	public String getMemberProductId(){
		return StringUtil.getStringValue(
		memberProductBean.getMemberProductId());

	}
	
										
	public void setRegisterDate(String obj){

		memberProductBean.setRegisterDate(java.sql.Date.valueOf(obj));

	}

	public String getRegisterDate(){
		return StringUtil.getStringValue(
		memberProductBean.getRegisterDate());

	}

	
				
	public void setExpireDate(String obj){

		memberProductBean.setExpireDate(java.sql.Date.valueOf(obj));

	}

	public String getExpireDate(){
		return StringUtil.getStringValue(
		memberProductBean.getExpireDate());

	}

	
				
	public void setRenewalDate(String obj){

		memberProductBean.setRenewalDate(java.sql.Date.valueOf(obj));

	}

	public String getRenewalDate(){
		return StringUtil.getStringValue(
		memberProductBean.getRenewalDate());

	}

	
							
	public void setCreatedTime(String obj){

		memberProductBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		memberProductBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		memberProductBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		memberProductBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		memberProductBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		memberProductBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		memberProductBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		memberProductBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		memberProductBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		memberProductBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		memberProductBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		memberProductBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		memberProductBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		memberProductBean.getDeletedStatus());

	}
	
				
	public void setIsProductActive(String obj){

		memberProductBean.setIsProductActive(StringUtil.getIntegerValue(obj,0));

	}

	public String getIsProductActive(){
		return StringUtil.getStringValue(
		memberProductBean.getIsProductActive());

	}
	
		

	// foreign affairs
	
	

	
	public void setMemberProductStatus(String obj){
		SubscriptionStatus fk = new SubscriptionStatus();
		fk.setStatusId(StringUtil.getIntegerValue(obj,0));
		memberProductBean.setMemberProductStatus(fk);

	}

	public String getMemberProductStatus(){
		return StringUtil.getStringValue(
		memberProductBean.getMemberProductStatus().getStatusId());

	}
	//---
	
	

	
	public void setMemberId(String obj){
		Member fk = new Member();
		fk.setMemberId(StringUtil.getIntegerValue(obj,0));
		memberProductBean.setMemberId(fk);

	}

	public String getMemberId(){
		return StringUtil.getStringValue(
		memberProductBean.getMemberId().getMemberId());

	}
	//---
	
	

	
	public void setProductId(String obj){
		Product fk = new Product();
		fk.setProductId(StringUtil.getIntegerValue(obj,0));
		memberProductBean.setProductId(fk);

	}

	public String getProductId(){
		return StringUtil.getStringValue(
		memberProductBean.getProductId().getProductId());

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
