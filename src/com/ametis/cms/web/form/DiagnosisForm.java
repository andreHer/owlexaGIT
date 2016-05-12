
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.Diagnosis;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * Diagnosis is a mapping of diagnosis Table.
*/
public class DiagnosisForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewDiagnosis = false;
	private Diagnosis diagnosisBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public DiagnosisForm()
    {
    	this.diagnosisBean = new Diagnosis();
    	this.isNewDiagnosis = true;
    }
    public DiagnosisForm (Diagnosis object){
		this.diagnosisBean = object;
    }
    public boolean isNewDiagnosis (){

    	return this.isNewDiagnosis;
    }
	public Diagnosis getDiagnosis (){
		return this.diagnosisBean ;
	}
	public void setDiagnosis (Diagnosis object){
		this.diagnosisBean = object;
	}

			
	public void setDiagnosisId(String obj){

		diagnosisBean.setDiagnosisId(StringUtil.getIntegerValue(obj,0));

	}

	public String getDiagnosisId(){
		return StringUtil.getStringValue(
		diagnosisBean.getDiagnosisId());

	}
	
					public void setDescription(String obj){

		diagnosisBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		diagnosisBean.getDescription());

	}
	
					public void setDiagnosisCode(String obj){

		diagnosisBean.setDiagnosisCode(new String(obj));

	}

	public String getDiagnosisCode(){
		return StringUtil.getStringValue(
		diagnosisBean.getDiagnosisCode());

	}
	
					public void setDiagnosisName(String obj){

		diagnosisBean.setDiagnosisName(new String(obj));

	}

	public String getDiagnosisName(){
		return StringUtil.getStringValue(
		diagnosisBean.getDiagnosisName());

	}
	
					public void setInitialSymptom(String obj){

		diagnosisBean.setInitialSymptom(new String(obj));

	}

	public String getInitialSymptom(){
		return StringUtil.getStringValue(
		diagnosisBean.getInitialSymptom());

	}
	
					public void setVitalSign(String obj){

		diagnosisBean.setVitalSign(new String(obj));

	}

	public String getVitalSign(){
		return StringUtil.getStringValue(
		diagnosisBean.getVitalSign());

	}
	
				
	public void setCreatedTime(String obj){

		diagnosisBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		diagnosisBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		diagnosisBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		diagnosisBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		diagnosisBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		diagnosisBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		diagnosisBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		diagnosisBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		diagnosisBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		diagnosisBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		diagnosisBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		diagnosisBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		diagnosisBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		diagnosisBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
