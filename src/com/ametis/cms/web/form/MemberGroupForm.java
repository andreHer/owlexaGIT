
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.BusinessCategory;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * MemberGroup is a mapping of member_group Table.
*/
public class MemberGroupForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewMemberGroup = false;
	private MemberGroup memberGroupBean ;
	private String clientName;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public MemberGroupForm()
    {
    	this.memberGroupBean = new MemberGroup();
    	this.isNewMemberGroup = true;
    }
    public MemberGroupForm (MemberGroup object){
		this.memberGroupBean = object;
    }
    public boolean isNewMemberGroup (){

    	return this.isNewMemberGroup;
    }
    
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public MemberGroup getMemberGroup (){
		return this.memberGroupBean ;
	}
	public void setMemberGroup (MemberGroup object){
		this.memberGroupBean = object;
	}

			
	public void setMemberGroupId(String obj){

		memberGroupBean.setMemberGroupId(StringUtil.getIntegerValue(obj,0));

	}

	public String getMemberGroupId(){
		String result = "";
		
		if (memberGroupBean.getMemberGroupId() != null){
			result = StringUtil.getStringValue(
					memberGroupBean.getMemberGroupId());
		}
		return result;

	}
	
				
	public void setStatus(String obj){

		SubscriptionStatus subscriptionStatus = new SubscriptionStatus();
		subscriptionStatus.setStatusId(StringUtil.getIntegerValue(obj,0));
		memberGroupBean.setStatus(subscriptionStatus);

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		memberGroupBean.getStatus());

	}
	
					public void setFaximile(String obj){

		memberGroupBean.setFaximile(new String(obj));

	}

	public String getFaximile(){
		return StringUtil.getStringValue(
		memberGroupBean.getFaximile());

	}
	
					public void setWebsite(String obj){

		memberGroupBean.setWebsite(new String(obj));

	}

	public String getWebsite(){
		return StringUtil.getStringValue(
		memberGroupBean.getWebsite());

	}
	public void setBankBranch(String obj){

		memberGroupBean.setBankBranch(new String(obj));

	}

	public String getBankBranch(){
		return StringUtil.getStringValue(
		memberGroupBean.getBankBranch());

	}
					public void setTelephone(String obj){

		memberGroupBean.setTelephone(new String(obj));

	}

	public String getTelephone(){
		return StringUtil.getStringValue(
		memberGroupBean.getTelephone());

	}
	
					public void setEmail(String obj){

		memberGroupBean.setEmail(new String(obj));

	}

	public String getEmail(){
		return StringUtil.getStringValue(
		memberGroupBean.getEmail());

	}
	
				
	public void setEffectiveDate(String obj){
		
		
		if (obj != null && !obj.equals("")){
			memberGroupBean.setEffectiveDate(java.sql.Date.valueOf(obj));
		}
		else {
			memberGroupBean.setEffectiveDate(null);
		}
	}

	public String getEffectiveDate(){
String res = "";
		
		if (memberGroupBean.getEffectiveDate() != null){
			res = StringUtil.getStringValue(memberGroupBean.getEffectiveDate());
		}
		return res;

	}

	
				
	public void setRenewalDate(String obj){

		
		if (obj != null && !obj.equals("")){
			memberGroupBean.setRenewalDate(java.sql.Date.valueOf(obj));
		}
		else {
			memberGroupBean.setRenewalDate(null);
		}
	}

	public String getRenewalDate(){
String res = "";
		
		if (memberGroupBean.getRenewalDate() != null){
			res = StringUtil.getStringValue(memberGroupBean.getRenewalDate());
		}
		return res;

	}

	
				
	public void setJoinDate(String obj){


		if (obj != null && !obj.equals("")){
			memberGroupBean.setJoinDate(java.sql.Date.valueOf(obj));
		}
		else {
			memberGroupBean.setJoinDate(null);
		}

	}

	public String getJoinDate(){
String res = "";
		
		if (memberGroupBean.getJoinDate() != null){
			res = StringUtil.getStringValue(memberGroupBean.getJoinDate());
		}
		return res;

	}
	
	
	
				
	public void setResignedDate(String obj){


		if (obj != null && !obj.equals("")){
			memberGroupBean.setResignedDate(java.sql.Date.valueOf(obj));
		}
		else {
			memberGroupBean.setResignedDate(null);
		}


	}

	public String getResignedDate(){
String res = "";
		
		if (memberGroupBean.getResignedDate() != null){
			res = StringUtil.getStringValue(memberGroupBean.getResignedDate());
		}
		return res;

	}

	
				
	public void setExpireDate(String obj){

		
		if (obj != null && !obj.equals("")){
			memberGroupBean.setExpireDate(java.sql.Date.valueOf(obj));
		}
		else {
			memberGroupBean.setExpireDate(null);
		}

	}

	public String getExpireDate(){
String exDate = "";
		
		if (memberGroupBean.getExpireDate() != null){
			exDate = StringUtil.getStringValue(memberGroupBean.getExpireDate());
		}
		return exDate;

	}

	
					public void setGroupName(String obj){

		memberGroupBean.setGroupName(new String(obj));

	}

	public String getGroupName(){
		return StringUtil.getStringValue(
		memberGroupBean.getGroupName());

	}
	
					public void setProvince(String obj){

		memberGroupBean.setProvince(new String(obj));

	}

	public String getProvince(){
		return StringUtil.getStringValue(
		memberGroupBean.getProvince());

	}
	
					public void setCity(String obj){

		memberGroupBean.setCity(new String(obj));

	}

	public String getCity(){
		return StringUtil.getStringValue(
		memberGroupBean.getCity());

	}
	
					public void setPostalCode(String obj){

		memberGroupBean.setPostalCode(new String(obj));

	}

	public String getPostalCode(){
		return StringUtil.getStringValue(
		memberGroupBean.getPostalCode());

	}
	
					public void setAddress(String obj){

		memberGroupBean.setAddress(new String(obj));

	}

	public String getAddress(){
		return StringUtil.getStringValue(
		memberGroupBean.getAddress());

	}
	
					public void setCountry(String obj){

		memberGroupBean.setCountry(new String(obj));

	}

	public String getCountry(){
		return StringUtil.getStringValue(
		memberGroupBean.getCountry());

	}
	
				
	public void setGroupLimit(String obj){

		memberGroupBean.setGroupLimit(StringUtil.getDoubleValue(obj,0));

	}

	public String getGroupLimit(){
		return StringUtil.getStringValue(
		memberGroupBean.getGroupLimit());

	}
	
				
	public void setActualGroupLimit(String obj){

		memberGroupBean.setActualGroupLimit(StringUtil.getDoubleValue(obj,0));

	}

	public String getActualGroupLimit(){
		return StringUtil.getStringValue(
		memberGroupBean.getActualGroupLimit());

	}
	
					public void setPolicyAgent(String obj){

		memberGroupBean.setPolicyAgent(new String(obj));

	}

	public String getPolicyAgent(){
		return StringUtil.getStringValue(
		memberGroupBean.getPolicyAgent());

	}
	
					public void setPolicyNumber(String obj){

		memberGroupBean.setPolicyNumber(new String(obj));

	}

	public String getPolicyNumber(){
		return StringUtil.getStringValue(
		memberGroupBean.getPolicyNumber());

	}
	
					public void setBankAccount(String obj){

		memberGroupBean.setBankAccount(new String(obj));

	}

	public String getBankAccount(){
		return StringUtil.getStringValue(
		memberGroupBean.getBankAccount());

	}
	
	public void setBankAccountName(String obj){

		memberGroupBean.setBankAccountName(new String(obj));

	}
					
					

	public String getBankAccountName(){
		return StringUtil.getStringValue(
		memberGroupBean.getBankAccountName());

	}
	
					public void setBank(String obj){

		memberGroupBean.setBank(new String(obj));

	}

	public String getBank(){
		return StringUtil.getStringValue(
		memberGroupBean.getBank());

	}
	
	// foreign affairs
	
	

	
	public void setClientId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Client fk = new Client();
			fk.setClientId(StringUtil.getIntegerValue(obj,0));
			memberGroupBean.setClientId(fk);
		}

	}

	public String getClientId(){
		String result = "";
		
		if (memberGroupBean.getClientId() != null){
			result = StringUtil.getStringValue(
					memberGroupBean.getClientId().getClientId());
		}
		return result;
		

	}
	
	public void setBusinessCategoryId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			BusinessCategory fk = new BusinessCategory();
			fk.setBusinessCategoryId(StringUtil.getIntegerValue(obj,0));
			memberGroupBean.setBusinessCategoryId(fk);
		}

	}

	public String getBusinessCategoryId(){
		String result = "";
		
		if (memberGroupBean.getBusinessCategoryId() != null){
			result = StringUtil.getStringValue(
					memberGroupBean.getBusinessCategoryId().getBusinessCategoryId());
		}
		return result;

	}

	public void setGroupCode(String obj){
		memberGroupBean.setMemberGroupCode(new String(obj));
	}
					
					

	public String getGroupCode(){
		return StringUtil.getStringValue(memberGroupBean.getMemberGroupCode());
	}
	public void setTipe(String obj){
		memberGroupBean.setTipe(new String(obj));
	}
					
					

	public String getTipe(){
		return StringUtil.getStringValue(memberGroupBean.getTipe());
	}

}
