
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * Procedure is a mapping of procedure Table.
*/
public class ProcedureForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewProcedure = false;
	private Procedure procedureBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ProcedureForm()
    {
    	this.procedureBean = new Procedure();
    	this.isNewProcedure = true;
    }
    public ProcedureForm (Procedure object){
		this.procedureBean = object;
    }
    public boolean isNewProcedure (){

    	return this.isNewProcedure;
    }
	public Procedure getProcedure (){
		return this.procedureBean ;
	}
	public void setProcedure (Procedure object){
		this.procedureBean = object;
	}

			
	public void setProcedureId(String obj){

		procedureBean.setProcedureId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProcedureId(){
		return StringUtil.getStringValue(
		procedureBean.getProcedureId());

	}
	
					public void setProcedureCode(String obj){

		procedureBean.setProcedureCode(new String(obj));

	}

	public String getProcedureCode(){
		return StringUtil.getStringValue(
		procedureBean.getProcedureCode());

	}
	
					public void setProcedureName(String obj){

		procedureBean.setProcedureName(new String(obj));

	}

	public String getProcedureName(){
		return StringUtil.getStringValue(
		procedureBean.getProcedureName());

	}
	
					public void setDescription(String obj){

		procedureBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		procedureBean.getDescription());

	}
	
				
	public void setCreatedTime(String obj){

		procedureBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		procedureBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		procedureBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		procedureBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		procedureBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		procedureBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		procedureBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		procedureBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		procedureBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		procedureBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		procedureBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		procedureBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		procedureBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		procedureBean.getDeletedStatus());

	}
	
				
	public void setStatus(String obj){

		procedureBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		procedureBean.getStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
