
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * ProcedureDiagnosis is a mapping of procedure_diagnosis Table.
*/
public class ProcedureDiagnosisForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewProcedureDiagnosis = false;
	private ProcedureDiagnosis procedureDiagnosisBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ProcedureDiagnosisForm()
    {
    	this.procedureDiagnosisBean = new ProcedureDiagnosis();
    	this.isNewProcedureDiagnosis = true;
    }
    public ProcedureDiagnosisForm (ProcedureDiagnosis object){
		this.procedureDiagnosisBean = object;
    }
    public boolean isNewProcedureDiagnosis (){

    	return this.isNewProcedureDiagnosis;
    }
	public ProcedureDiagnosis getProcedureDiagnosis (){
		return this.procedureDiagnosisBean ;
	}
	public void setProcedureDiagnosis (ProcedureDiagnosis object){
		this.procedureDiagnosisBean = object;
	}

			
	public void setProcedureDiagnosisId(String obj){

		procedureDiagnosisBean.setProcedureDiagnosisId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProcedureDiagnosisId(){
		return StringUtil.getStringValue(
		procedureDiagnosisBean.getProcedureDiagnosisId());

	}
	
										
	public void setTotalUsage(String obj){

		procedureDiagnosisBean.setTotalUsage(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalUsage(){
		return StringUtil.getStringValue(
		procedureDiagnosisBean.getTotalUsage());

	}
	
				
	public void setTotalCharge(String obj){

		procedureDiagnosisBean.setTotalCharge(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalCharge(){
		return StringUtil.getStringValue(
		procedureDiagnosisBean.getTotalCharge());

	}
	
				
	public void setReferencePrice(String obj){

		procedureDiagnosisBean.setReferencePrice(StringUtil.getDoubleValue(obj,0));

	}

	public String getReferencePrice(){
		return StringUtil.getStringValue(
		procedureDiagnosisBean.getReferencePrice());

	}
	
					public void setDescription(String obj){

		procedureDiagnosisBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		procedureDiagnosisBean.getDescription());

	}
	
				
	public void setCreatedTime(String obj){

		procedureDiagnosisBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		procedureDiagnosisBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		procedureDiagnosisBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		procedureDiagnosisBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		procedureDiagnosisBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		procedureDiagnosisBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		procedureDiagnosisBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		procedureDiagnosisBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		procedureDiagnosisBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		procedureDiagnosisBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		procedureDiagnosisBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		procedureDiagnosisBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		procedureDiagnosisBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		procedureDiagnosisBean.getDeletedStatus());

	}
	
		

	// foreign affairs
	
	

	
	public void setDiagnosisId(String obj){
		Diagnosis fk = new Diagnosis();
		fk.setDiagnosisId(StringUtil.getIntegerValue(obj,0));
		procedureDiagnosisBean.setDiagnosisId(fk);

	}

	public String getDiagnosisId(){
		return StringUtil.getStringValue(
		procedureDiagnosisBean.getDiagnosisId().getDiagnosisId());

	}
	//---
	
	

	
	public void setProcedureId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Procedure fk = new Procedure();
			fk.setProcedureId(StringUtil.getIntegerValue(obj,0));
			procedureDiagnosisBean.setProcedureId(fk);
		}

	}

	public String getProcedureId(){
		String result = "";
		
		if (procedureDiagnosisBean.getProcedureId() != null){
			result = StringUtil.getStringValue(
					procedureDiagnosisBean.getProcedureId().getProcedureId()); 
		}
		
		return result;

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
