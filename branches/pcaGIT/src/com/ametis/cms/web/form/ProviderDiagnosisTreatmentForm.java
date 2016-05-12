
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * ProviderDiagnosisTreatment is a mapping of provider_diagnosis_treatment Table.
*/
public class ProviderDiagnosisTreatmentForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewProviderDiagnosisTreatment = false;
	private ProviderDiagnosisTreatment providerDiagnosisTreatmentBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ProviderDiagnosisTreatmentForm()
    {
    	this.providerDiagnosisTreatmentBean = new ProviderDiagnosisTreatment();
    	this.isNewProviderDiagnosisTreatment = true;
    }
    public ProviderDiagnosisTreatmentForm (ProviderDiagnosisTreatment object){
		this.providerDiagnosisTreatmentBean = object;
    }
    public boolean isNewProviderDiagnosisTreatment (){

    	return this.isNewProviderDiagnosisTreatment;
    }
	public ProviderDiagnosisTreatment getProviderDiagnosisTreatment (){
		return this.providerDiagnosisTreatmentBean ;
	}
	public void setProviderDiagnosisTreatment (ProviderDiagnosisTreatment object){
		this.providerDiagnosisTreatmentBean = object;
	}

			
	public void setProviderDiagnosisTreatmentId(String obj){

		providerDiagnosisTreatmentBean.setProviderDiagnosisTreatmentId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProviderDiagnosisTreatmentId(){
		return StringUtil.getStringValue(
		providerDiagnosisTreatmentBean.getProviderDiagnosisTreatmentId());

	}
	
				
	public void setProviderId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
		
			Provider provider = new Provider();
			provider.setProviderId(Integer.valueOf(obj));
			providerDiagnosisTreatmentBean.setProviderId(provider);
		}
		

	}

	public String getProviderId(){
		String result = "";
		
		if (providerDiagnosisTreatmentBean.getProviderId() != null){
			result = StringUtil.getStringValue(
					providerDiagnosisTreatmentBean.getProviderId().getProviderId());
		}
		return result;
		

	}
	
				
	public void setDiagnosisId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Diagnosis diagnosis = new Diagnosis();
			diagnosis.setDiagnosisId(Integer.valueOf(obj));
			providerDiagnosisTreatmentBean.setDiagnosisId(diagnosis);
		}

	}

	public String getDiagnosisId(){
		return StringUtil.getStringValue(
		providerDiagnosisTreatmentBean.getDiagnosisId());

	}
	
				
	public void setTreatmentClassId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			TreatmentClass treatmenClass= new TreatmentClass();
			treatmenClass.setTreatmentClassId(Integer.valueOf(obj));
			providerDiagnosisTreatmentBean.setTreatmentClassId(treatmenClass);
		}

	}

	public String getTreatmentClassId(){
		return StringUtil.getStringValue(
		providerDiagnosisTreatmentBean.getTreatmentClassId());

	}
	
				
	public void setTreatmentRiskId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			TreatmentRisk risk = new TreatmentRisk();
			risk.setTreatmentRiskId(Integer.valueOf(obj));
			providerDiagnosisTreatmentBean.setTreatmentRiskId(risk);
		}

	}

	public String getTreatmentRiskId(){
		return StringUtil.getStringValue(
		providerDiagnosisTreatmentBean.getTreatmentRiskId());

	}
	
					public void setDescription(String obj){

		providerDiagnosisTreatmentBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		providerDiagnosisTreatmentBean.getDescription());

	}
	
					public void setReferenceNote(String obj){

		providerDiagnosisTreatmentBean.setReferenceNote(new String(obj));

	}

	public String getReferenceNote(){
		return StringUtil.getStringValue(
		providerDiagnosisTreatmentBean.getReferenceNote());

	}
	
				
	public void setWeight(String obj){

		providerDiagnosisTreatmentBean.setWeight(StringUtil.getDoubleValue(obj,0));

	}

	public String getWeight(){
		return StringUtil.getStringValue(
		providerDiagnosisTreatmentBean.getWeight());

	}
	
				
	public void setAge(String obj){

		providerDiagnosisTreatmentBean.setAge(StringUtil.getDoubleValue(obj,0));

	}

	public String getAge(){
		return StringUtil.getStringValue(
		providerDiagnosisTreatmentBean.getAge());

	}
	
				
	public void setAlos(String obj){

		providerDiagnosisTreatmentBean.setAlos(StringUtil.getDoubleValue(obj,0));

	}

	public String getAlos(){
		return StringUtil.getStringValue(
		providerDiagnosisTreatmentBean.getAlos());

	}
	
				
	public void setTreatmentRate(String obj){

		providerDiagnosisTreatmentBean.setTreatmentRate(StringUtil.getDoubleValue(obj,0));

	}

	public String getTreatmentRate(){
		return StringUtil.getStringValue(
		providerDiagnosisTreatmentBean.getTreatmentRate());

	}
	
				
	public void setCreatedTime(String obj){

		providerDiagnosisTreatmentBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		providerDiagnosisTreatmentBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		providerDiagnosisTreatmentBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		providerDiagnosisTreatmentBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		providerDiagnosisTreatmentBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		providerDiagnosisTreatmentBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		providerDiagnosisTreatmentBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		providerDiagnosisTreatmentBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		providerDiagnosisTreatmentBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		providerDiagnosisTreatmentBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		providerDiagnosisTreatmentBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		providerDiagnosisTreatmentBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		providerDiagnosisTreatmentBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		providerDiagnosisTreatmentBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
