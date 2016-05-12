
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * CaseProcedure is a mapping of case_procedure Table.
*/
public class CaseProcedureForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewCaseProcedure = false;
	private CaseProcedure caseProcedureBean ;
	private String procedureName;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public CaseProcedureForm()
    {
    	this.caseProcedureBean = new CaseProcedure();
    	this.isNewCaseProcedure = true;
    }
    public CaseProcedureForm (CaseProcedure object){
		this.caseProcedureBean = object;
    }
    public boolean isNewCaseProcedure (){

    	return this.isNewCaseProcedure;
    }
	public CaseProcedure getCaseProcedure (){
		return this.caseProcedureBean ;
	}
	public void setCaseProcedure (CaseProcedure object){
		this.caseProcedureBean = object;
	}

			
	public void setCaseProcedureId(String obj){

		caseProcedureBean.setCaseProcedureId(StringUtil.getIntegerValue(obj,0));

	}

	public String getCaseProcedureId(){
		return StringUtil.getStringValue(
		caseProcedureBean.getCaseProcedureId());

	}
	
				
	public void setCaseId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Case theCase = new Case();
			theCase.setCaseId(Integer.valueOf(obj));
			caseProcedureBean.setCaseId(theCase);
		}

	}

	public String getCaseId(){
		String result = "";
		
		if (caseProcedureBean.getCaseId() != null){
			result =StringUtil.getStringValue(
					caseProcedureBean.getCaseId().getCaseId());
		}
		return result;
		

	}
	
				
	public void setProcedureId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Procedure procedure = new Procedure();
			procedure.setProcedureId(Integer.valueOf(obj));
			caseProcedureBean.setProcedureId(procedure);
		}


	}

	public String getProcedureId(){
		String result = "";
		
		if (caseProcedureBean.getProcedureId() != null){
			result = StringUtil.getStringValue(
					caseProcedureBean.getProcedureId().getProcedureId());
		}
		
		return result;
		

	}
	
				
	public void setTotalCharge(String obj){

		caseProcedureBean.setTotalCharge(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalCharge(){
		return StringUtil.getStringValue(
		caseProcedureBean.getTotalCharge());

	}
	
				
	public void setReferencePrice(String obj){
		
		caseProcedureBean.setReferencePrice(StringUtil.getDoubleValue(obj,0));

	}

	public String getReferencePrice(){
		return StringUtil.getStringValue(
		caseProcedureBean.getReferencePrice());

	}
	
					public void setDescription(String obj){

		caseProcedureBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		caseProcedureBean.getDescription());

	}
	
				
	public void setCreatedTime(String obj){

		caseProcedureBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		caseProcedureBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		caseProcedureBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		caseProcedureBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		caseProcedureBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		caseProcedureBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		caseProcedureBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		caseProcedureBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		caseProcedureBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		caseProcedureBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		caseProcedureBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		caseProcedureBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		caseProcedureBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		caseProcedureBean.getDeletedStatus());

	}
	
				
	public void setStatus(String obj){

		caseProcedureBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		caseProcedureBean.getStatus());

	}
	
					public void setApprovalRemarks(String obj){

		caseProcedureBean.setApprovalRemarks(new String(obj));

	}

	public String getApprovalRemarks(){
		return StringUtil.getStringValue(
		caseProcedureBean.getApprovalRemarks());

	}
	public String getProcedureName() {
		return procedureName;
	}
	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}
	
		
}
