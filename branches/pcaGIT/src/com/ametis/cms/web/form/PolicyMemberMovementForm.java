
package com.ametis.cms.web.form;

import org.springframework.web.multipart.MultipartFile;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * PolicyMemberMovement is a mapping of policy_member_movement Table.
*/
public class PolicyMemberMovementForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewPolicyMemberMovement = false;
	private PolicyMemberMovement policyMemberMovementBean ;
	private MultipartFile multipartFile;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public PolicyMemberMovementForm()
    {
    	this.policyMemberMovementBean = new PolicyMemberMovement();
    	this.isNewPolicyMemberMovement = true;
    }
    public PolicyMemberMovementForm (PolicyMemberMovement object){
		this.policyMemberMovementBean = object;
    }
    public boolean isNewPolicyMemberMovement (){

    	return this.isNewPolicyMemberMovement;
    }
	public PolicyMemberMovement getPolicyMemberMovement (){
		return this.policyMemberMovementBean ;
	}
	public void setPolicyMemberMovement (PolicyMemberMovement object){
		this.policyMemberMovementBean = object;
	}

			
	public void setId(String obj){

		policyMemberMovementBean.setId(StringUtil.getIntegerValue(obj,0));

	}

	public String getId(){
		return StringUtil.getStringValue(
		policyMemberMovementBean.getId());

	}
	
					public void setMovementNumber(String obj){

		policyMemberMovementBean.setMovementNumber(new String(obj));

	}

	public String getMovementNumber(){
		return StringUtil.getStringValue(
		policyMemberMovementBean.getMovementNumber());

	}
	
				
	public void setMovementDate(String obj){

		policyMemberMovementBean.setMovementDate(java.sql.Date.valueOf(obj));

	}

	public String getMovementDate(){
		return StringUtil.getStringValue(
		policyMemberMovementBean.getMovementDate());

	}

	
					public void setMovementFilePath(String obj){

		policyMemberMovementBean.setMovementFilePath(new String(obj));

	}

	public String getMovementFilePath(){
		return StringUtil.getStringValue(
		policyMemberMovementBean.getMovementFilePath());

	}
	
				
	public void setPolicyId(String obj){

            if (obj != null && !obj.equalsIgnoreCase("")){
                Policy policy = new Policy();
                policy.setPolicyId(Integer.valueOf(obj));
		policyMemberMovementBean.setPolicyId(policy);
            }

	}

	public String getPolicyId(){
		String result = "";
		if (policyMemberMovementBean.getPolicyId() != null){
			result = StringUtil.getStringValue(
					
					policyMemberMovementBean.getPolicyId().getPolicyId());
		}
		return result;

	}
	
				
	public void setMovementType(String obj){

		policyMemberMovementBean.setMovementType(StringUtil.getIntegerValue(obj,0));

	}

	public String getMovementType(){
		return StringUtil.getStringValue(
		policyMemberMovementBean.getMovementType());

	}
	
				
	public void setStatus(String obj){

		policyMemberMovementBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		policyMemberMovementBean.getStatus());

	}
	
					public void setDescription(String obj){

		policyMemberMovementBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		policyMemberMovementBean.getDescription());

	}
	
				
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}
	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
	
	public void setExportImportTemplate(String obj){

		if(obj != null && !obj.equals("")){
			ExportImportTemplate template = new ExportImportTemplate();
			template.setId(Integer.valueOf(obj));
			policyMemberMovementBean.setTemplateId(template);
		}

	}

	public String getExportImportTemplate(){
		String result = "";
		
		if (policyMemberMovementBean.getTemplateId() != null){
			result = policyMemberMovementBean.getTemplateId().getId().toString();
		}
		
		return result;
		

	}
    public String getFileFormat() {
        return policyMemberMovementBean.getFileFormat();
    }
    
    public void setFileFormat(String fileType) {
        this.policyMemberMovementBean.setFileFormat(fileType);
    }
    public String getDatePattern() {
        return policyMemberMovementBean.getDatePattern();
    }
    
    public void setDatePattern(String pattern) {
        this.policyMemberMovementBean.setDatePattern(pattern);
    }
    public String getFirstLineHeader() {
    	String result = "";
    	
    	if (policyMemberMovementBean.getFirstLineHeader() != null){
    		result = policyMemberMovementBean.getFirstLineHeader().toString();
    	}
        return result;
    }
    
    public void setFirstLineHeader(String header) {
    	if (header != null && !header.equalsIgnoreCase("")){
    		this.policyMemberMovementBean.setFirstLineHeader(Integer.valueOf(header));
    	}
    }
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 

}
