
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * ContactPerson is a mapping of contact_person Table.
*/
public class ContactPersonForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewContactPerson = false;
        private String refId;
        private String tipe;
        private String previousURL;
	private ContactPerson contactPersonBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ContactPersonForm()
    {
    	this.contactPersonBean = new ContactPerson();
    	this.isNewContactPerson = true;
    }
    public ContactPersonForm (ContactPerson object){
		this.contactPersonBean = object;
    }
    public boolean isNewContactPerson (){

    	return this.isNewContactPerson;
    }
	public ContactPerson getContactPerson (){
		return this.contactPersonBean ;
	}
	public void setContactPerson (ContactPerson object){
		this.contactPersonBean = object;
	}

			
	public void setContactPersonId(String obj){

		contactPersonBean.setContactPersonId(StringUtil.getIntegerValue(obj,0));

	}

	public String getContactPersonId(){
		return StringUtil.getStringValue(
		contactPersonBean.getContactPersonId());

	}
	
					public void setName(String obj){

		contactPersonBean.setName(new String(obj));

	}

	public String getName(){
		return StringUtil.getStringValue(
		contactPersonBean.getName());

	}
	
					public void setTelephone(String obj){

		contactPersonBean.setTelephone(new String(obj));

	}

	public String getTelephone(){
		return StringUtil.getStringValue(
		contactPersonBean.getTelephone());

	}
	
					public void setHandphone(String obj){

		contactPersonBean.setHandphone(new String(obj));

	}

	public String getHandphone(){
		return StringUtil.getStringValue(
		contactPersonBean.getHandphone());

	}
	
					public void setEmail(String obj){

		contactPersonBean.setEmail(new String(obj));

	}

	public String getEmail(){
		return StringUtil.getStringValue(
		contactPersonBean.getEmail());

	}
	
					public void setDepartment(String obj){

		contactPersonBean.setDepartment(new String(obj));

	}

	public String getDepartment(){
		return StringUtil.getStringValue(
		contactPersonBean.getDepartment());

	}
	
					public void setJobPosition(String obj){

		contactPersonBean.setJobPosition(new String(obj));

	}

	public String getJobPosition(){
		return StringUtil.getStringValue(
		contactPersonBean.getJobPosition());

	}
	
				
	public void setCreatedTime(String obj){

		contactPersonBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		contactPersonBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		contactPersonBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		contactPersonBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		contactPersonBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		contactPersonBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		contactPersonBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		contactPersonBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		contactPersonBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		contactPersonBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		contactPersonBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		contactPersonBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		contactPersonBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		contactPersonBean.getDeletedStatus());

	}
	
	public String getPaymentOfficer() {
		return StringUtil.getStringValue(
				contactPersonBean.getPaymentOfficer());
	}
	public void setPaymentOfficer(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			contactPersonBean.setPaymentOfficer(StringUtil.getIntegerValue(obj, 0));
		}else{
			contactPersonBean.setPaymentOfficer(0);
		}
	}
	
											

	// foreign affairs
	
	

	
	public void setClientId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Client fk = new Client();
			fk.setClientId(StringUtil.getIntegerValue(obj,0));
			contactPersonBean.setClientId(fk);
		}

	}

	public String getClientId(){
		String result = "";
		
		if (contactPersonBean.getClientId() != null){
			result = StringUtil.getStringValue(
					contactPersonBean.getClientId().getClientId());
		}
		
		return result;
		

	}
	//---
	
	

	
	public void setMemberGroupId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			MemberGroup fk = new MemberGroup();
			fk.setMemberGroupId(StringUtil.getIntegerValue(obj,0));
			contactPersonBean.setMemberGroupId(fk);
		}

	}

	public String getMemberGroupId(){
		String result = "";
		
		if (contactPersonBean.getMemberGroupId() != null){
			result = StringUtil.getStringValue(
					contactPersonBean.getMemberGroupId().getMemberGroupId());
		}
		return result;

	}
	//---
	
	

	
	public void setProviderId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Provider fk = new Provider();
			fk.setProviderId(StringUtil.getIntegerValue(obj,0));
			contactPersonBean.setProviderId(fk);
		}

	}

	public String getProviderId(){
		String result = "";
		
		if (contactPersonBean.getProviderId() != null){
			result = StringUtil.getStringValue(
					contactPersonBean.getProviderId().getProviderId());
		}
		return result;
		

	}
	//---
		// -- foreign affairs end
        
        

// class+ 

// class- 

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getPreviousURL() {
        return previousURL;
    }

    public void setPreviousURL(String previousURL) {
        this.previousURL = previousURL;
    }

    
}
