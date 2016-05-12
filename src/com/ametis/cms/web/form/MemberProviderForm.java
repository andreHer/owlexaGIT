
package com.ametis.cms.web.form;

import org.springframework.web.multipart.MultipartFile;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * MemberProvider is a mapping of member_provider Table.
*/
public class MemberProviderForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewMemberProvider = false;
	private MemberProvider memberProviderBean ;
	private String providerName;
	private MultipartFile multipartFile;
	private String memberName;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public MemberProviderForm()
    {
    	this.memberProviderBean = new MemberProvider();
    	this.isNewMemberProvider = true;
    }
    public MemberProviderForm (MemberProvider object){
		this.memberProviderBean = object;
    }
    public boolean isNewMemberProvider (){

    	return this.isNewMemberProvider;
    }
	public MemberProvider getMemberProvider (){
		return this.memberProviderBean ;
	}
	public void setMemberProvider (MemberProvider object){
		this.memberProviderBean = object;
	}

			
	public void setMemberProviderId(String obj){

		memberProviderBean.setMemberProviderId(StringUtil.getIntegerValue(obj,0));

	}

	public String getMemberProviderId(){
		return StringUtil.getStringValue(
		memberProviderBean.getMemberProviderId());

	}
	
				
	public void setDeletedStatus(String obj){

		memberProviderBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		memberProviderBean.getDeletedStatus());

	}
	
				
	public void setModifiedTime(String obj){

		memberProviderBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		memberProviderBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		memberProviderBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		memberProviderBean.getModifiedBy());

	}
	
					public void setDeletedBy(String obj){

		memberProviderBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		memberProviderBean.getDeletedBy());

	}
	
				
	public void setDeletedTime(String obj){

		memberProviderBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		memberProviderBean.getDeletedTime());

	}

	
				
	public void setCreatedTime(String obj){

		memberProviderBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		memberProviderBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		memberProviderBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		memberProviderBean.getCreatedBy());

	}
	
				
	public void setDental(String obj){

		memberProviderBean.setDental(StringUtil.getIntegerValue(obj,0));

	}

	public String getDental(){
		return StringUtil.getStringValue(
		memberProviderBean.getDental());

	}
	
				
	public void setInpatient(String obj){

		memberProviderBean.setInpatient(StringUtil.getIntegerValue(obj,0));

	}

	public String getInpatient(){
		return StringUtil.getStringValue(
		memberProviderBean.getInpatient());

	}
	
				
	public void setMaternity(String obj){

		memberProviderBean.setMaternity(StringUtil.getIntegerValue(obj,0));

	}

	public String getMaternity(){
		return StringUtil.getStringValue(
		memberProviderBean.getMaternity());

	}
	
				
	public void setOptical(String obj){

		memberProviderBean.setOptical(StringUtil.getIntegerValue(obj,0));

	}

	public String getOptical(){
		return StringUtil.getStringValue(
		memberProviderBean.getOptical());

	}
	
				
	public void setOutpatient(String obj){

		memberProviderBean.setOutpatient(StringUtil.getIntegerValue(obj,0));

	}

	public String getOutpatient(){
		return StringUtil.getStringValue(
		memberProviderBean.getOutpatient());

	}
	
				
	public void setPpk1(String obj){

		memberProviderBean.setPpk1(StringUtil.getIntegerValue(obj,0));

	}

	public String getPpk1(){
		return StringUtil.getStringValue(
		memberProviderBean.getPpk1());

	}
	
				
	public void setPpk2(String obj){

		memberProviderBean.setPpk2(StringUtil.getIntegerValue(obj,0));

	}

	public String getPpk2(){
		return StringUtil.getStringValue(
		memberProviderBean.getPpk2());

	}
	
				
	public void setPpk3(String obj){

		memberProviderBean.setPpk3(StringUtil.getIntegerValue(obj,0));

	}

	public String getPpk3(){
		return StringUtil.getStringValue(
		memberProviderBean.getPpk3());

	}
	
								

	// foreign affairs
	
	

	
	public void setMemberId(String obj){
		Member fk = new Member();
		fk.setMemberId(StringUtil.getIntegerValue(obj,0));
		memberProviderBean.setMemberId(fk);

	}

	public String getMemberId(){
		String result = "";
		
		if (memberProviderBean.getMemberId() != null){
			result =StringUtil.getStringValue(
					memberProviderBean.getMemberId().getMemberId()); 
		}
		return result;
			
		

	}
	//---
	
	

	
	public void setProviderId(String obj){
		Provider fk = new Provider();
		fk.setProviderId(StringUtil.getIntegerValue(obj,0));
		memberProviderBean.setProviderId(fk);

	}

	public String getProviderId(){
		String result = "";
		
		if (memberProviderBean.getProviderId() != null){
			result = StringUtil.getStringValue(
					memberProviderBean.getProviderId().getProviderId());
		}
		return result;

	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}
	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	

}
