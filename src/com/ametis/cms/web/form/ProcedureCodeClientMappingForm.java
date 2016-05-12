
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * ProcedureCodeClientMapping is a mapping of procedure_code_client_mapping Table.
*/
public class ProcedureCodeClientMappingForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewProcedureCodeClientMapping = false;
	private ProcedureCodeClientMapping procedureCodeClientMappingBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ProcedureCodeClientMappingForm()
    {
    	this.procedureCodeClientMappingBean = new ProcedureCodeClientMapping();
    	this.isNewProcedureCodeClientMapping = true;
    }
    public ProcedureCodeClientMappingForm (ProcedureCodeClientMapping object){
		this.procedureCodeClientMappingBean = object;
    }
    public boolean isNewProcedureCodeClientMapping (){

    	return this.isNewProcedureCodeClientMapping;
    }
	public ProcedureCodeClientMapping getProcedureCodeClientMapping (){
		return this.procedureCodeClientMappingBean ;
	}
	public void setProcedureCodeClientMapping (ProcedureCodeClientMapping object){
		this.procedureCodeClientMappingBean = object;
	}

			
	public void setProcedureCodeClientMappingId(String obj){

		procedureCodeClientMappingBean.setProcedureCodeClientMappingId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProcedureCodeClientMappingId(){
		return StringUtil.getStringValue(
		procedureCodeClientMappingBean.getProcedureCodeClientMappingId());

	}
	
					public void setProcedureClientCode(String obj){

		procedureCodeClientMappingBean.setProcedureClientCode(new String(obj));

	}

	public String getProcedureClientCode(){
		return StringUtil.getStringValue(
		procedureCodeClientMappingBean.getProcedureClientCode());

	}
	
				
	public void setProcedureId(String obj){

		procedureCodeClientMappingBean.setProcedureId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProcedureId(){
		return StringUtil.getStringValue(
		procedureCodeClientMappingBean.getProcedureId());

	}
	
				
	public void setClientId(String obj){

		procedureCodeClientMappingBean.setClientId(StringUtil.getIntegerValue(obj,0));

	}

	public String getClientId(){
		return StringUtil.getStringValue(
		procedureCodeClientMappingBean.getClientId());

	}
	
					public void setProcedureCode(String obj){

		procedureCodeClientMappingBean.setProcedureCode(new String(obj));

	}

	public String getProcedureCode(){
		return StringUtil.getStringValue(
		procedureCodeClientMappingBean.getProcedureCode());

	}
	
					public void setProcedureName(String obj){

		procedureCodeClientMappingBean.setProcedureName(new String(obj));

	}

	public String getProcedureName(){
		return StringUtil.getStringValue(
		procedureCodeClientMappingBean.getProcedureName());

	}
	
				
	public void setStatus(String obj){

		procedureCodeClientMappingBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		procedureCodeClientMappingBean.getStatus());

	}
	
				
	public void setCreatedTime(String obj){

		procedureCodeClientMappingBean.setCreatedTime(java.sql.Time.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		procedureCodeClientMappingBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		procedureCodeClientMappingBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		procedureCodeClientMappingBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		procedureCodeClientMappingBean.setModifiedTime(java.sql.Time.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		procedureCodeClientMappingBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		procedureCodeClientMappingBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		procedureCodeClientMappingBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		procedureCodeClientMappingBean.setDeletedTime(java.sql.Time.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		procedureCodeClientMappingBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		procedureCodeClientMappingBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		procedureCodeClientMappingBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		procedureCodeClientMappingBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		procedureCodeClientMappingBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
