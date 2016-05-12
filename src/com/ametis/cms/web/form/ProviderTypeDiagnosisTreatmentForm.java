
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * ProviderTypeDiagnosisTreatment is a mapping of provider_type_diagnosis_treatment Table.
*/
public class ProviderTypeDiagnosisTreatmentForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewProviderTypeDiagnosisTreatment = false;
	private ProviderTypeDiagnosisTreatment providerTypeDiagnosisTreatmentBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ProviderTypeDiagnosisTreatmentForm()
    {
    	this.providerTypeDiagnosisTreatmentBean = new ProviderTypeDiagnosisTreatment();
    	this.isNewProviderTypeDiagnosisTreatment = true;
    }
    public ProviderTypeDiagnosisTreatmentForm (ProviderTypeDiagnosisTreatment object){
		this.providerTypeDiagnosisTreatmentBean = object;
    }
    public boolean isNewProviderTypeDiagnosisTreatment (){

    	return this.isNewProviderTypeDiagnosisTreatment;
    }
	public ProviderTypeDiagnosisTreatment getProviderTypeDiagnosisTreatment (){
		return this.providerTypeDiagnosisTreatmentBean ;
	}
	public void setProviderTypeDiagnosisTreatment (ProviderTypeDiagnosisTreatment object){
		this.providerTypeDiagnosisTreatmentBean = object;
	}

			
	public void setProviderTypeDiagnosisTreamentId(String obj){

		providerTypeDiagnosisTreatmentBean.setProviderTypeDiagnosisTreamentId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProviderTypeDiagnosisTreamentId(){
		return StringUtil.getStringValue(
		providerTypeDiagnosisTreatmentBean.getProviderTypeDiagnosisTreamentId());

	}
	
				
	public void setProviderTypeId(String obj){


		if (obj != null && !obj.equalsIgnoreCase("")){
			ProviderType type = new ProviderType();
			type.setProviderTypeId(Integer.valueOf(obj));
			providerTypeDiagnosisTreatmentBean.setProviderTypeId(type);
		}

	}

	public String getProviderTypeId(){
		String result = "";
		
		
		if (providerTypeDiagnosisTreatmentBean.getProviderTypeId() != null){
			result = StringUtil.getStringValue(
			providerTypeDiagnosisTreatmentBean.getProviderTypeId().getProviderTypeId());
		}
		
		return result;

	}
	
				
	public void setDiagnosisId(String obj){


		if (obj != null && !obj.equalsIgnoreCase("")){
			Diagnosis diagnosis = new Diagnosis();
			diagnosis.setDiagnosisId(Integer.valueOf(obj));
			providerTypeDiagnosisTreatmentBean.setDiagnosisId(diagnosis);
		}

	}

	public String getDiagnosisId(){
		return StringUtil.getStringValue(
		providerTypeDiagnosisTreatmentBean.getDiagnosisId());

	}
	
					public void setDescription(String obj){

		providerTypeDiagnosisTreatmentBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		providerTypeDiagnosisTreatmentBean.getDescription());

	}
	
					public void setReferenceNote(String obj){

		providerTypeDiagnosisTreatmentBean.setReferenceNote(new String(obj));

	}

	public String getReferenceNote(){
		return StringUtil.getStringValue(
		providerTypeDiagnosisTreatmentBean.getReferenceNote());

	}
	
				
	public void setAge(String obj){

		providerTypeDiagnosisTreatmentBean.setAge(StringUtil.getStringValue(obj));

	}

	public String getAge(){
		return StringUtil.getStringValue(
		providerTypeDiagnosisTreatmentBean.getAge());

	}
	
				
	public void setWeight(String obj){

		providerTypeDiagnosisTreatmentBean.setWeight(StringUtil.getStringValue(obj));

	}

	public String getWeight(){
		return StringUtil.getStringValue(
		providerTypeDiagnosisTreatmentBean.getWeight());

	}
	
				
	public void setTreatmentRiskId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			TreatmentRisk risk = new TreatmentRisk();
			risk.setTreatmentRiskId(Integer.valueOf(obj));
			providerTypeDiagnosisTreatmentBean.setTreatmentRiskId(risk);
		}
	}

	public String getTreatmentRiskId(){
		return StringUtil.getStringValue(
		providerTypeDiagnosisTreatmentBean.getTreatmentRiskId());

	}
	
				
	public void setTreatmentClassId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			TreatmentClass treatmentClass = new TreatmentClass();
			treatmentClass.setTreatmentClassId(Integer.valueOf(obj));
			providerTypeDiagnosisTreatmentBean.setTreatmentClassId(treatmentClass);
		}
	}

	public String getTreatmentClassId(){
		return StringUtil.getStringValue(
		providerTypeDiagnosisTreatmentBean.getTreatmentClassId());

	}
	
				
	public void setAlos(String obj){

		providerTypeDiagnosisTreatmentBean.setAlos(StringUtil.getDoubleValue(obj,0));

	}

	public String getAlos(){
		return StringUtil.getStringValue(
		providerTypeDiagnosisTreatmentBean.getAlos());

	}
	
				
	public void setTreatmentRate(String obj){

		providerTypeDiagnosisTreatmentBean.setTreatmentRate(StringUtil.getDoubleValue(obj,0));

	}

	public String getTreatmentRate(){
		return StringUtil.getStringValue(
		providerTypeDiagnosisTreatmentBean.getTreatmentRate());

	}
	
				
	public void setCreatedTime(String obj){

		providerTypeDiagnosisTreatmentBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		providerTypeDiagnosisTreatmentBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		providerTypeDiagnosisTreatmentBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		providerTypeDiagnosisTreatmentBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		providerTypeDiagnosisTreatmentBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		providerTypeDiagnosisTreatmentBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		providerTypeDiagnosisTreatmentBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		providerTypeDiagnosisTreatmentBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		providerTypeDiagnosisTreatmentBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		providerTypeDiagnosisTreatmentBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		providerTypeDiagnosisTreatmentBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		providerTypeDiagnosisTreatmentBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		providerTypeDiagnosisTreatmentBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		providerTypeDiagnosisTreatmentBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
