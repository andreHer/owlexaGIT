
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.InsurancePackage;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberPackage;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * MemberPackage is a mapping of member_package Table.
*/
public class MemberPackageForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewMemberPackage = false;
	private MemberPackage memberPackageBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public MemberPackageForm()
    {
    	this.memberPackageBean = new MemberPackage();
    	this.isNewMemberPackage = true;
    }
    public MemberPackageForm (MemberPackage object){
		this.memberPackageBean = object;
    }
    public boolean isNewMemberPackage (){

    	return this.isNewMemberPackage;
    }
	public MemberPackage getMemberPackage (){
		return this.memberPackageBean ;
	}
	public void setMemberPackage (MemberPackage object){
		this.memberPackageBean = object;
	}

			
	public void setMemberPackageId(String obj){

		memberPackageBean.setMemberPackageId(StringUtil.getIntegerValue(obj,0));

	}

	public String getMemberPackageId(){
		return StringUtil.getStringValue(
		memberPackageBean.getMemberPackageId());

	}
	
										
	public void setRegisterDate(String obj){

		memberPackageBean.setRegisterDate(java.sql.Date.valueOf(obj));

	}

	public String getRegisterDate(){
		return StringUtil.getStringValue(
		memberPackageBean.getRegisterDate());

	}

	
				
	public void setExpireDate(String obj){

		memberPackageBean.setExpireDate(java.sql.Date.valueOf(obj));

	}

	public String getExpireDate(){
		return StringUtil.getStringValue(
		memberPackageBean.getExpireDate());

	}

	
				
	public void setRenewalDate(String obj){

		memberPackageBean.setRenewalDate(java.sql.Date.valueOf(obj));

	}

	public String getRenewalDate(){
		return StringUtil.getStringValue(
		memberPackageBean.getRenewalDate());

	}

	
							
	public void setCreatedTime(String obj){

		memberPackageBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		memberPackageBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		memberPackageBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		memberPackageBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		memberPackageBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		memberPackageBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		memberPackageBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		memberPackageBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		memberPackageBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		memberPackageBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		memberPackageBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		memberPackageBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		memberPackageBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		memberPackageBean.getDeletedStatus());

	}
	
				
	public void setResignDate(String obj){

		memberPackageBean.setResignDate(java.sql.Date.valueOf(obj));

	}

	public String getResignDate(){
		return StringUtil.getStringValue(
		memberPackageBean.getResignDate());

	}

	
		

	// foreign affairs
	
	

	
	public void setMemberPackageStatus(String obj){
		SubscriptionStatus fk = new SubscriptionStatus();
		fk.setStatusId(StringUtil.getIntegerValue(obj,0));
		memberPackageBean.setMemberPackageStatus(fk);

	}

	public String getMemberPackageStatus(){
		return StringUtil.getStringValue(
		memberPackageBean.getMemberPackageStatus().getStatusId());

	}
	//---
	
	

	
	public void setMemberId(String obj){
		Member fk = new Member();
		fk.setMemberId(StringUtil.getIntegerValue(obj,0));
		memberPackageBean.setMemberId(fk);

	}

	public String getMemberId(){
		return StringUtil.getStringValue(
		memberPackageBean.getMemberId().getMemberId());

	}
	//---
	
	

	
	public void setPackageId(String obj){
		InsurancePackage fk = new InsurancePackage();
		fk.setPackageId(StringUtil.getIntegerValue(obj,0));
		memberPackageBean.setPackageId(fk);

	}

	public String getPackageId(){
		return StringUtil.getStringValue(
		memberPackageBean.getPackageId().getPackageId());

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
