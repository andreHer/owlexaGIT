
package com.ametis.cms.web.form;

import org.springframework.web.multipart.MultipartFile;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * PolicyDiagnosisExclusion is a mapping of policy_diagnosis_exclusion Table.
*/
public class PolicyDiagnosisExclusionForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewPolicyDiagnosisExclusion = false;
	private PolicyDiagnosisExclusion policyDiagnosisExclusionBean ;
	private MultipartFile multipartFile;


    public PolicyDiagnosisExclusionForm()
    {
    	this.policyDiagnosisExclusionBean = new PolicyDiagnosisExclusion();
    	this.isNewPolicyDiagnosisExclusion = true;
    }
    public PolicyDiagnosisExclusionForm (PolicyDiagnosisExclusion object){
		this.policyDiagnosisExclusionBean = object;
    }
    public boolean isNewPolicyDiagnosisExclusion (){

    	return this.isNewPolicyDiagnosisExclusion;
    }
	public PolicyDiagnosisExclusion getPolicyDiagnosisExclusion (){
		return this.policyDiagnosisExclusionBean ;
	}
	public void setPolicyDiagnosisExclusion (PolicyDiagnosisExclusion object){
		this.policyDiagnosisExclusionBean = object;
	}

			
	public void setId(String obj){

		policyDiagnosisExclusionBean.setId(StringUtil.getIntegerValue(obj,0));

	}

	public String getId(){
		return StringUtil.getStringValue(
		policyDiagnosisExclusionBean.getId());

	}
	
				
	public void setPolicyId(String obj){

		policyDiagnosisExclusionBean.setPolicyId(StringUtil.getIntegerValue(obj,0));

	}

	public String getPolicyId(){
		return StringUtil.getStringValue(
		policyDiagnosisExclusionBean.getPolicyId());

	}
	
				
	public void setDiagnosisId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Diagnosis diagnosis = new Diagnosis();
			diagnosis.setDiagnosisId(Integer.valueOf(obj));
			policyDiagnosisExclusionBean.setDiagnosisId(diagnosis);
		}

	}

	public String getDiagnosisId(){
		String result = "";
		
		if (policyDiagnosisExclusionBean.getDiagnosisId() != null){
			result = StringUtil.getStringValue(
					policyDiagnosisExclusionBean.getDiagnosisId().getDiagnosisId()); 
		}
		return result;
		

	}
	
				
	public void setCreatedTime(String obj){

		policyDiagnosisExclusionBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		policyDiagnosisExclusionBean.getCreatedTime());

	}

	
				
	
					public void setModificationInformation(String obj){

		policyDiagnosisExclusionBean.setModificationInformation(new String(obj));

	}

	public String getModificationInformation(){
		return StringUtil.getStringValue(
		policyDiagnosisExclusionBean.getModificationInformation());

	}
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}
	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
	

}
