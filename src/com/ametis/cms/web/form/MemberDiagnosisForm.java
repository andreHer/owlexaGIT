
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.Diagnosis;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberDiagnosis;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * MemberDiagnosis is a mapping of member_diagnosis Table.
*/
public class MemberDiagnosisForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewMemberDiagnosis = false;
	private MemberDiagnosis memberDiagnosisBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public MemberDiagnosisForm()
    {
    	this.memberDiagnosisBean = new MemberDiagnosis();
    	this.isNewMemberDiagnosis = true;
    }
    public MemberDiagnosisForm (MemberDiagnosis object){
		this.memberDiagnosisBean = object;
    }
    public boolean isNewMemberDiagnosis (){

    	return this.isNewMemberDiagnosis;
    }
	public MemberDiagnosis getMemberDiagnosis (){
		return this.memberDiagnosisBean ;
	}
	public void setMemberDiagnosis (MemberDiagnosis object){
		this.memberDiagnosisBean = object;
	}

			
	public void setMemberDiagnosisId(String obj){

		memberDiagnosisBean.setMemberDiagnosisId(StringUtil.getIntegerValue(obj,0));

	}

	public String getMemberDiagnosisId(){
		return StringUtil.getStringValue(
		memberDiagnosisBean.getMemberDiagnosisId());

	}
	
										
	public void setCreatedTime(String obj){

		memberDiagnosisBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		memberDiagnosisBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		memberDiagnosisBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		memberDiagnosisBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		memberDiagnosisBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		memberDiagnosisBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		memberDiagnosisBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		memberDiagnosisBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		memberDiagnosisBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		memberDiagnosisBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		memberDiagnosisBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		memberDiagnosisBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		memberDiagnosisBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		memberDiagnosisBean.getDeletedStatus());

	}
	
		

	// foreign affairs
	
	

	
	public void setDiagnosisId(String obj){
		Diagnosis fk = new Diagnosis();
		fk.setDiagnosisId(StringUtil.getIntegerValue(obj,0));
		memberDiagnosisBean.setDiagnosisId(fk);

	}

	public String getDiagnosisId(){
		return StringUtil.getStringValue(
		memberDiagnosisBean.getDiagnosisId().getDiagnosisId());

	}
	//---
	
	

	
	public void setMemberId(String obj){
		Member fk = new Member();
		fk.setMemberId(StringUtil.getIntegerValue(obj,0));
		memberDiagnosisBean.setMemberId(fk);

	}

	public String getMemberId(){
		return StringUtil.getStringValue(
		memberDiagnosisBean.getMemberId().getMemberId());

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
