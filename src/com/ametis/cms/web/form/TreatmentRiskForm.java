
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * TreatmentRisk is a mapping of treatment_risk Table.
*/
public class TreatmentRiskForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewTreatmentRisk = false;
	private TreatmentRisk treatmentRiskBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public TreatmentRiskForm()
    {
    	this.treatmentRiskBean = new TreatmentRisk();
    	this.isNewTreatmentRisk = true;
    }
    public TreatmentRiskForm (TreatmentRisk object){
		this.treatmentRiskBean = object;
    }
    public boolean isNewTreatmentRisk (){

    	return this.isNewTreatmentRisk;
    }
	public TreatmentRisk getTreatmentRisk (){
		return this.treatmentRiskBean ;
	}
	public void setTreatmentRisk (TreatmentRisk object){
		this.treatmentRiskBean = object;
	}

			
	public void setTreatmentRiskId(String obj){

		treatmentRiskBean.setTreatmentRiskId(StringUtil.getIntegerValue(obj,0));

	}

	public String getTreatmentRiskId(){
		return StringUtil.getStringValue(
		treatmentRiskBean.getTreatmentRiskId());

	}
	
					public void setTreatmentRiskName(String obj){

		treatmentRiskBean.setTreatmentRiskName(new String(obj));

	}

	public String getTreatmentRiskName(){
		return StringUtil.getStringValue(
		treatmentRiskBean.getTreatmentRiskName());

	}
	
					public void setTreatmentRiskCode(String obj){

		treatmentRiskBean.setTreatmentRiskCode(new String(obj));

	}

	public String getTreatmentRiskCode(){
		return StringUtil.getStringValue(
		treatmentRiskBean.getTreatmentRiskCode());

	}
	
					public void setDescription(String obj){

		treatmentRiskBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		treatmentRiskBean.getDescription());

	}
	
				
	public void setRiskIndex(String obj){

		treatmentRiskBean.setRiskIndex(StringUtil.getDoubleValue(obj,0));

	}

	public String getRiskIndex(){
		return StringUtil.getStringValue(
		treatmentRiskBean.getRiskIndex());

	}
	
				
	public void setCreatedTime(String obj){

		treatmentRiskBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		treatmentRiskBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		treatmentRiskBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		treatmentRiskBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		treatmentRiskBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		treatmentRiskBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		treatmentRiskBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		treatmentRiskBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		treatmentRiskBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		treatmentRiskBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		treatmentRiskBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		treatmentRiskBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		treatmentRiskBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		treatmentRiskBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
