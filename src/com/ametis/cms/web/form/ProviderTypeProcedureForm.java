
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * ProviderTypeProcedure is a mapping of provider_type_procedure Table.
*/
public class ProviderTypeProcedureForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewProviderTypeProcedure = false;
	private ProviderTypeProcedure providerTypeProcedureBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ProviderTypeProcedureForm()
    {
    	this.providerTypeProcedureBean = new ProviderTypeProcedure();
    	this.isNewProviderTypeProcedure = true;
    }
    public ProviderTypeProcedureForm (ProviderTypeProcedure object){
		this.providerTypeProcedureBean = object;
    }
    public boolean isNewProviderTypeProcedure (){

    	return this.isNewProviderTypeProcedure;
    }
	public ProviderTypeProcedure getProviderTypeProcedure (){
		return this.providerTypeProcedureBean ;
	}
	public void setProviderTypeProcedure (ProviderTypeProcedure object){
		this.providerTypeProcedureBean = object;
	}

			
	public void setProviderTypeProcedureId(String obj){

		providerTypeProcedureBean.setProviderTypeProcedureId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProviderTypeProcedureId(){
		return StringUtil.getStringValue(
		providerTypeProcedureBean.getProviderTypeProcedureId());

	}
	
				
	public void setProviderTypeId(String obj){


		if (obj != null && !obj.equalsIgnoreCase("")){
			ProviderType type = new ProviderType();
			type.setProviderTypeId(Integer.valueOf(obj));
			providerTypeProcedureBean.setProviderTypeId(type);
		}

	}

	public String getProviderTypeId(){
		return StringUtil.getStringValue(
		providerTypeProcedureBean.getProviderTypeId());

	}
	
				
	public void setProcedureId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Procedure procedure = new Procedure();
			procedure.setProcedureId(Integer.valueOf(obj));
			providerTypeProcedureBean.setProcedureId(procedure);
		}

	}

	public String getProcedureId(){
		return StringUtil.getStringValue(
		providerTypeProcedureBean.getProcedureId());

	}
	
				
	public void setTreatmentClassId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			TreatmentClass treatmentClass = new TreatmentClass();
			treatmentClass.setTreatmentClassId(Integer.valueOf(obj));
		providerTypeProcedureBean.setTreatmentClassId(treatmentClass);
		}

	}

	public String getTreatmentClassId(){
		return StringUtil.getStringValue(
		providerTypeProcedureBean.getTreatmentClassId());

	}
	
				
	public void setTreatmentRiskId(String obj){


		if (obj != null && !obj.equalsIgnoreCase("")){
			TreatmentRisk risk = new TreatmentRisk();
			risk.setTreatmentRiskId(Integer.valueOf(obj));
			providerTypeProcedureBean.setTreatmentRiskId(risk);
		}

	}

	public String getTreatmentRiskId(){
		return StringUtil.getStringValue(
		providerTypeProcedureBean.getTreatmentRiskId());

	}
	
				
	public void setAlos(String obj){

		providerTypeProcedureBean.setAlos(StringUtil.getDoubleValue(obj,0));

	}

	public String getAlos(){
		return StringUtil.getStringValue(
		providerTypeProcedureBean.getAlos());

	}
	
				
	public void setTreatmentRate(String obj){

		providerTypeProcedureBean.setTreatmentRate(StringUtil.getDoubleValue(obj,0));

	}

	public String getTreatmentRate(){
		return StringUtil.getStringValue(
		providerTypeProcedureBean.getTreatmentRate());

	}
	
					public void setDescription(String obj){

		providerTypeProcedureBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		providerTypeProcedureBean.getDescription());

	}
	
					public void setReferenceNote(String obj){

		providerTypeProcedureBean.setReferenceNote(new String(obj));

	}

	public String getReferenceNote(){
		return StringUtil.getStringValue(
		providerTypeProcedureBean.getReferenceNote());

	}
	
				
	public void setCreatedTime(String obj){

		providerTypeProcedureBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		providerTypeProcedureBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		providerTypeProcedureBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		providerTypeProcedureBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		providerTypeProcedureBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		providerTypeProcedureBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		providerTypeProcedureBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		providerTypeProcedureBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		providerTypeProcedureBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		providerTypeProcedureBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		providerTypeProcedureBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		providerTypeProcedureBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		providerTypeProcedureBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		providerTypeProcedureBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
