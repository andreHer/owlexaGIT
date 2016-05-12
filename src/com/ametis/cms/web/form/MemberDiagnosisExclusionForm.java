
package com.ametis.cms.web.form;

import org.springframework.web.multipart.MultipartFile;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * MemberDiagnosisExclusion is a mapping of member_diagnosis_exclusion Table.
*/
public class MemberDiagnosisExclusionForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewMemberDiagnosisExclusion = false;
	private MemberDiagnosisExclusion memberDiagnosisExclusionBean ;
	private MultipartFile multipartFile;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public MemberDiagnosisExclusionForm()
    {
    	this.memberDiagnosisExclusionBean = new MemberDiagnosisExclusion();
    	this.isNewMemberDiagnosisExclusion = true;
    }
    public MemberDiagnosisExclusionForm (MemberDiagnosisExclusion object){
		this.memberDiagnosisExclusionBean = object;
    }
    public boolean isNewMemberDiagnosisExclusion (){

    	return this.isNewMemberDiagnosisExclusion;
    }
	public MemberDiagnosisExclusion getMemberDiagnosisExclusion (){
		return this.memberDiagnosisExclusionBean ;
	}
	public void setMemberDiagnosisExclusion (MemberDiagnosisExclusion object){
		this.memberDiagnosisExclusionBean = object;
	}

			
	public void setId(String obj){

		memberDiagnosisExclusionBean.setId(StringUtil.getIntegerValue(obj,0));

	}

	public String getId(){
		return StringUtil.getStringValue(
		memberDiagnosisExclusionBean.getId());

	}
	
				
	public void setDiagnosisId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Diagnosis diagnosis = new Diagnosis();
			diagnosis.setDiagnosisId(Integer.valueOf(obj));
			memberDiagnosisExclusionBean.setDiagnosisId(diagnosis);
		}

	}

	public String getDiagnosisId(){
		String result = "";
		
		if (memberDiagnosisExclusionBean.getDiagnosisId() != null){
			result = StringUtil.getStringValue(
					memberDiagnosisExclusionBean.getDiagnosisId().toString());
		}
		return result;
		

	}
	
				
	public void setMemberId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Member member = new Member();
			member.setMemberId(Integer.valueOf(obj));
			memberDiagnosisExclusionBean.setMemberId(member);
		}

	}

	public String getMemberId(){

		String result = "";
		
		
		if (memberDiagnosisExclusionBean.getMemberId() != null){
			result = StringUtil.getStringValue(
					memberDiagnosisExclusionBean.getMemberId().toString());
		}
		return result;

	}
	
				
	public void setPolicyId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Policy policy =new Policy();
			policy.setPolicyId(Integer.valueOf(obj));
			memberDiagnosisExclusionBean.setPolicyId(policy);
		}

	}

	public String getPolicyId(){
		String result = "";
		
		if (memberDiagnosisExclusionBean.getPolicyId() != null){
			result = StringUtil.getStringValue(
					memberDiagnosisExclusionBean.getPolicyId().toString());
		}
		return result;
		

	}
	
				
	public void setExclusionStatus(String obj){

		memberDiagnosisExclusionBean.setExclusionStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getExclusionStatus(){
		return StringUtil.getStringValue(
		memberDiagnosisExclusionBean.getExclusionStatus());

	}
	
				
	public void setCreatedTime(String obj){

		memberDiagnosisExclusionBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		memberDiagnosisExclusionBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		memberDiagnosisExclusionBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		memberDiagnosisExclusionBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		memberDiagnosisExclusionBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		memberDiagnosisExclusionBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		memberDiagnosisExclusionBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		memberDiagnosisExclusionBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		memberDiagnosisExclusionBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		memberDiagnosisExclusionBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		memberDiagnosisExclusionBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		memberDiagnosisExclusionBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		memberDiagnosisExclusionBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		memberDiagnosisExclusionBean.getDeletedStatus());

	}
	
					public void setModificationInformation(String obj){

		memberDiagnosisExclusionBean.setModificationInformation(new String(obj));

	}

	public String getModificationInformation(){
		return StringUtil.getStringValue(
		memberDiagnosisExclusionBean.getModificationInformation());

	}
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}
	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
	
		
	

}
