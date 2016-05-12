
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * MemberGroupProviderDiagnosis is a mapping of member_group_provider_diagnosis Table.
*/
public class MemberGroupProviderDiagnosisForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewMemberGroupProviderDiagnosis = false;
	private MemberGroupProviderDiagnosis memberGroupProviderDiagnosisBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public MemberGroupProviderDiagnosisForm()
    {
    	this.memberGroupProviderDiagnosisBean = new MemberGroupProviderDiagnosis();
    	this.isNewMemberGroupProviderDiagnosis = true;
    }
    public MemberGroupProviderDiagnosisForm (MemberGroupProviderDiagnosis object){
		this.memberGroupProviderDiagnosisBean = object;
    }
    public boolean isNewMemberGroupProviderDiagnosis (){

    	return this.isNewMemberGroupProviderDiagnosis;
    }
	public MemberGroupProviderDiagnosis getMemberGroupProviderDiagnosis (){
		return this.memberGroupProviderDiagnosisBean ;
	}
	public void setMemberGroupProviderDiagnosis (MemberGroupProviderDiagnosis object){
		this.memberGroupProviderDiagnosisBean = object;
	}

			
	public void setMemberGroupProviderDiagnosisId(String obj){

		memberGroupProviderDiagnosisBean.setMemberGroupProviderDiagnosisId(StringUtil.getIntegerValue(obj,0));

	}

	public String getMemberGroupProviderDiagnosisId(){
		return StringUtil.getStringValue(
		memberGroupProviderDiagnosisBean.getMemberGroupProviderDiagnosisId());

	}
	
										
	public void setExclusionStatus(String obj){

		memberGroupProviderDiagnosisBean.setExclusionStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getExclusionStatus(){
		return StringUtil.getStringValue(
		memberGroupProviderDiagnosisBean.getExclusionStatus());

	}
	
				
	public void setCreatedTime(String obj){

		memberGroupProviderDiagnosisBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		memberGroupProviderDiagnosisBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		memberGroupProviderDiagnosisBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		memberGroupProviderDiagnosisBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		memberGroupProviderDiagnosisBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		memberGroupProviderDiagnosisBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		memberGroupProviderDiagnosisBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		memberGroupProviderDiagnosisBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		memberGroupProviderDiagnosisBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		memberGroupProviderDiagnosisBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		memberGroupProviderDiagnosisBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		memberGroupProviderDiagnosisBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		memberGroupProviderDiagnosisBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		memberGroupProviderDiagnosisBean.getDeletedStatus());

	}
	
		

	// foreign affairs
	
	

	
	public void setDiagnosisId(String obj){
		Diagnosis fk = new Diagnosis();
		fk.setDiagnosisId(StringUtil.getIntegerValue(obj,0));
		memberGroupProviderDiagnosisBean.setDiagnosisId(fk);

	}

	public String getDiagnosisId(){
		return StringUtil.getStringValue(
		memberGroupProviderDiagnosisBean.getDiagnosisId().getDiagnosisId());

	}
	//---
	
	

	
	public void setMemberGroupProviderId(String obj){
		MemberGroupProvider fk = new MemberGroupProvider();
		fk.setMemberGroupProviderId(StringUtil.getIntegerValue(obj,0));
		memberGroupProviderDiagnosisBean.setMemberGroupProviderId(fk);

	}

	public String getMemberGroupProviderId(){
		return StringUtil.getStringValue(
		memberGroupProviderDiagnosisBean.getMemberGroupProviderId().getMemberGroupProviderId());

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 

}
