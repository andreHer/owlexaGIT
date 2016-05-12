
package com.ametis.cms.web.form;

import java.sql.Date;

import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.ProductType;
import com.ametis.cms.datamodel.Relationship;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * Member is a mapping of member Table.
*/
public class MemberForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewMember = false;
	private Member memberBean ;
	private String currentPolicyNumber;
	private String productCode;
	private Date admissionDate; // hanya untuk register RKI
	
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public MemberForm()
    {
    	this.memberBean = new Member();
    	this.isNewMember = true;
    }
    public MemberForm (Member object){
		this.memberBean = object;
    }
    public boolean isNewMember (){

    	return this.isNewMember;
    }
	public Member getMember (){
		return this.memberBean ;
	}
	public void setMember (Member object){
		this.memberBean = object;
	}

			
	public void setCurrentPolicyId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Policy policy = new Policy();
			policy.setPolicyId(Integer.valueOf(obj));
			memberBean.setCurrentPolicyId(policy);
		}

	}

	public String getCurrentPolicyId(){
		String result = "";
		
		if (memberBean.getCurrentPolicyId() != null){
			result = memberBean.getCurrentPolicyId().getPolicyId().toString();
		}
		return result;

	}
	public void setCurrentPolicyNumber (String obj){
		if (obj != null){
			this.currentPolicyNumber = obj;
		}
	}
	public String getCurrentPolicyNumber (){
		return currentPolicyNumber;
	}
	public void setMemberId(String obj){

		memberBean.setMemberId(StringUtil.getIntegerValue(obj,0));

	}

	public String getMemberId(){
		return StringUtil.getStringValue(
		memberBean.getMemberId());

	}
	public void setCurrentProductCode (String currentProductCode){
		if (currentProductCode != null){
			this.memberBean.setCurrentProductCode(currentProductCode.trim());
		}
	}
	public String getCurrentProductCode (){
		return memberBean.getCurrentProductCode();
	}
	public void setInsuranceProduct(String productCode){
		this.productCode = productCode;
	}
	public String getInsuranceProduct (){
		return this.productCode;
	}
	
				
	
	
					public void setFirstName(String obj){

		memberBean.setFirstName(new String(obj));

	}

	public String getFirstName(){
		return StringUtil.getStringValue(
		memberBean.getFirstName());

	}
	
					public void setLastName(String obj){

		memberBean.setLastName(new String(obj));

	}

	public String getLastName(){
		return StringUtil.getStringValue(
		memberBean.getLastName());

	}
	
								public void setMobilePhone(String obj){

		memberBean.setMobilePhone(new String(obj));

	}

	public String getMobilePhone(){
		return StringUtil.getStringValue(
		memberBean.getMobilePhone());

	}
	
					public void setFaximile(String obj){

		memberBean.setFaximile(new String(obj));

	}

	public String getFaximile(){
		return StringUtil.getStringValue(
		memberBean.getFaximile());

	}
	
					public void setCustomerNumber(String obj){

		memberBean.setCustomerNumber(new String(obj));

	}

	public String getCustomerNumber(){
		return StringUtil.getStringValue(
		memberBean.getCustomerNumber());

	}
	
					public void setTelephone(String obj){

		memberBean.setTelephone(new String(obj));

	}

	public String getTelephone(){
		return StringUtil.getStringValue(
		memberBean.getTelephone());

	}
	
					public void setEmail(String obj){

		memberBean.setEmail(new String(obj));

	}

	public String getEmail(){
		return StringUtil.getStringValue(
		memberBean.getEmail());

	}
	
					public void setCustomerPolicyNumber(String obj){

		memberBean.setCustomerPolicyNumber(new String(obj));

	}

	public String getCustomerPolicyNumber(){
		return StringUtil.getStringValue(
		memberBean.getCustomerPolicyNumber());

	}
	
				
	public void setEffectiveDate(String obj){

		
		if (obj != null && !obj.equals("")){
			memberBean.setEffectiveDate(java.sql.Date.valueOf(obj));
		}
		else {
			memberBean.setEffectiveDate(null);
		}

	}

	public String getEffectiveDate(){
String effectiveDate = "";
		
		if (memberBean.getEffectiveDate() != null){
			effectiveDate = StringUtil.getStringValue(memberBean.getEffectiveDate());
		}
		return effectiveDate;

	}

	
				
	public void setRenewalDate(String obj){

		if (obj != null && !obj.equals("")){
			memberBean.setRenewalDate(java.sql.Date.valueOf(obj));
		}
		else {
			memberBean.setRenewalDate(null);
		}

	}

	public String getRenewalDate(){
		
		String renewalDate = "";
		
		if (memberBean.getRenewalDate() != null){
			renewalDate = StringUtil.getStringValue(memberBean.getRenewalDate());
		}
		return renewalDate; 

	}

	
				
	public void setJoinDate(String obj){

		if (obj != null && !obj.equals("")){
			memberBean.setJoinDate(java.sql.Date.valueOf(obj));
		}
		else {
			memberBean.setJoinDate(null);
		}

	}

	public String getJoinDate(){
String joinDate = "";
		
		if (memberBean.getJoinDate() != null){
			joinDate = StringUtil.getStringValue(memberBean.getJoinDate());
		}
		return joinDate;

	}

	
				
	public void setResignedDate(String obj){

		
		if (obj != null && !obj.equals("")){
			memberBean.setResignedDate(java.sql.Date.valueOf(obj));
		}
		else {
			memberBean.setResignedDate(null);
		}
	}

	public String getResignedDate(){
String resignedDate = "";
		
		if (memberBean.getResignedDate() != null){
			resignedDate = StringUtil.getStringValue(memberBean.getResignedDate());
		}
		return resignedDate;

	}

	
				
	public void setExpireDate(String obj){

		if (obj != null && !obj.equals("")){
			memberBean.setExpireDate(java.sql.Date.valueOf(obj));
		}
		else {
			memberBean.setExpireDate(null);
		}

	}

	public String getExpireDate(){
String expireDate = "";
		
		if (memberBean.getExpireDate() != null){
			expireDate = StringUtil.getStringValue(memberBean.getExpireDate());
		}
		return expireDate;

	}

	
					public void setAddress(String obj){

		memberBean.setAddress(new String(obj));

	}

	public String getAddress(){
		return StringUtil.getStringValue(
		memberBean.getAddress());

	}
	
				
	public void setBirthday(String obj){

	
		if (obj != null && !obj.equals("")){
			memberBean.setBirthday(java.sql.Date.valueOf(obj));
		}
		else {
			memberBean.setBirthday(null);
		}
	}

	public String getBirthday(){
String birthDate = "";
		
		if (memberBean.getBirthday() != null){
			birthDate = StringUtil.getStringValue(memberBean.getBirthday());
		}
		return birthDate;

	}

	
					public void setBirthplace(String obj){

		memberBean.setBirthplace(new String(obj));

	}

	public String getBirthplace(){
		return StringUtil.getStringValue(
		memberBean.getBirthplace());

	}
	
										
	public void setCustomerLimit(String obj){

		memberBean.setCustomerLimit(StringUtil.getDoubleValue(obj,0));

	}

	public String getCustomerLimit(){
		return StringUtil.getStringValue(
		memberBean.getCustomerLimit());

	}
	
					public void setProvince(String obj){

		memberBean.setProvince(new String(obj));

	}

	public String getProvince(){
		return StringUtil.getStringValue(
		memberBean.getProvince());

	}
	
					public void setCity(String obj){

		memberBean.setCity(new String(obj));

	}

	public String getCity(){
		return StringUtil.getStringValue(
		memberBean.getCity());

	}
	
					public void setPostalCode(String obj){

		memberBean.setPostalCode(new String(obj));

	}

	public String getPostalCode(){
		return StringUtil.getStringValue(
		memberBean.getPostalCode());

	}
	
					public void setCountry(String obj){

		memberBean.setCountry(new String(obj));

	}

	public String getCountry(){
		return StringUtil.getStringValue(
		memberBean.getCountry());

	}
	
				
	public void setActualCustomerLimit(String obj){

		memberBean.setActualCustomerLimit(StringUtil.getDoubleValue(obj,0));

	}

	public String getActualCustomerLimit(){
		return StringUtil.getStringValue(
		memberBean.getActualCustomerLimit());

	}
	
					public void setPolicyAgent(String obj){

		memberBean.setPolicyAgent(new String(obj));

	}

	public String getPolicyAgent(){
		return StringUtil.getStringValue(
		memberBean.getPolicyAgent());

	}
	
					public void setBankAccount(String obj){

		memberBean.setBankAccount(new String(obj));

	}

	public String getBankAccount(){
		return StringUtil.getStringValue(
		memberBean.getBankAccount());

	}
	
					public void setBankAccountName(String obj){

		memberBean.setBankAccountName(new String(obj));

	}

	public String getBankAccountName(){
		return StringUtil.getStringValue(
		memberBean.getBankAccountName());

	}
	public void setMemberEDCNumber(String obj){

		memberBean.setCurrentCardNumber(new String(obj));

	}

	public String getMemberEDCNumber(){
		return StringUtil.getStringValue(
		memberBean.getCurrentCardNumber());

	}
	
					public void setBank(String obj){

		memberBean.setBank(new String(obj));

	}

	public String getBank(){
		return StringUtil.getStringValue(
		memberBean.getBank());

	}
	
					public void setDepartment(String obj){

		memberBean.setDepartment(new String(obj));

	}

	public String getDepartment(){
		return StringUtil.getStringValue(
		memberBean.getDepartment());

	}
	
					public void setJobPosition(String obj){

		memberBean.setJobPosition(new String(obj));

	}

	public String getJobPosition(){
		return StringUtil.getStringValue(
		memberBean.getJobPosition());

	}
	
							
	public void setCreatedTime(String obj){

		memberBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		memberBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		memberBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		memberBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		memberBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		memberBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		memberBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		memberBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		memberBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		memberBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		memberBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		memberBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		memberBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		memberBean.getDeletedStatus());

	}
	
		

	// foreign affairs
	
	

	
	public void setMemberType(String obj){
		ProductType fk = new ProductType();
		fk.setProductTypeId(StringUtil.getIntegerValue(obj,0));
		memberBean.setMemberType(fk);

	}

	public String getMemberType(){
		return StringUtil.getStringValue(
		memberBean.getMemberType().getProductTypeId());

	}
	//---
	
	/*public void setPaymentRecipient(PaymentRecipient obj) {

		batchClaimBean.setPaymentRecipient(obj);

	}

	public PaymentRecipient getPaymentRecipient() {
		return batchClaimBean.getPaymentRecipient();

	}*/

	
	public void setClientId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Client client = new Client();
			client.setClientId(Integer.valueOf(obj));
			memberBean.setClientId(client);
		}
	}

	public String getClientId(){
		String result = "";
		if (memberBean.getClientId() != null){
			result = memberBean.getClientId().getClientId().toString();
		}
		return result;

	}
	public void setClientName(String obj){		
		memberBean.setClientName(obj);
	}

	public String getClientName(){
		return memberBean.getClientName();

	}
	public void setGroupName(String obj){		
		memberBean.setGroupName(obj);
	}

	public String getGroupName(){
		return memberBean.getGroupName();

	}
	//---
	
	public void setStatus(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			memberBean.setStatus(Integer.valueOf(obj));
		}
	}

	public String getStatus(){
		String result = "";
		
		if (memberBean.getStatus() != null){
		result = memberBean.getStatus().toString();
		
		
		}
		return result;

	}

	//---
	
	public void setMemberGroupId(String obj){		
		if (obj != null && !obj.equalsIgnoreCase("")){
			MemberGroup group = new MemberGroup();
			group.setMemberGroupId(Integer.valueOf(obj));
			memberBean.setMemberGroupId(group);
		}
		else {

		}
	}

	public String getMemberGroupId(){
		String result = "";
		
		if (memberBean.getMemberGroupId() != null){
			result = memberBean.getMemberGroupId().getMemberGroupId().toString();
		}
		return result;

	}
	//---
	
	

	
	public void setRelationshipId(String obj){
		Relationship fk = new Relationship();
		fk.setRelationshipId(StringUtil.getIntegerValue(obj,0));
		memberBean.setRelationshipId(fk);

	}

	public String getRelationshipId(){
		String relId = "";
		
		if (memberBean.getRelationshipId() != null){
		relId=  StringUtil.getStringValue(
		memberBean.getRelationshipId().getRelationshipId());
		}
	return relId;

	}

}
