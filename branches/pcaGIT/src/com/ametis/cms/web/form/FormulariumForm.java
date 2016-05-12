
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * Formularium is a mapping of formularium Table.
*/
public class FormulariumForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewFormularium = false;
	private Formularium formulariumBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public FormulariumForm()
    {
    	this.formulariumBean = new Formularium();
    	this.isNewFormularium = true;
    }
    public FormulariumForm (Formularium object){
		this.formulariumBean = object;
    }
    public boolean isNewFormularium (){

    	return this.isNewFormularium;
    }
	public Formularium getFormularium (){
		return this.formulariumBean ;
	}
	public void setFormularium (Formularium object){
		this.formulariumBean = object;
	}

			
	public void setFormulariumId(String obj){

		formulariumBean.setFormulariumId(StringUtil.getIntegerValue(obj,0));

	}

	public String getFormulariumId(){
		return StringUtil.getStringValue(
		formulariumBean.getFormulariumId());

	}
	
					public void setFormulariumName(String obj){

		formulariumBean.setFormulariumName(new String(obj));

	}

	public String getFormulariumName(){
		return StringUtil.getStringValue(
		formulariumBean.getFormulariumName());

	}
	
					public void setFormulariumCode(String obj){

		formulariumBean.setFormulariumCode(new String(obj));

	}

	public String getFormulariumCode(){
		return StringUtil.getStringValue(
		formulariumBean.getFormulariumCode());

	}
	
					public void setPeriode(String obj){

		formulariumBean.setPeriode(new String(obj));

	}

	public String getPeriode(){
		return StringUtil.getStringValue(
		formulariumBean.getPeriode());

	}
	
				
	public void setStartDate(String obj){

		formulariumBean.setStartDate(java.sql.Date.valueOf(obj));

	}

	public String getStartDate(){
		return StringUtil.getStringValue(
		formulariumBean.getStartDate());

	}

	
				
	public void setEndDate(String obj){

		formulariumBean.setEndDate(java.sql.Date.valueOf(obj));

	}

	public String getEndDate(){
		return StringUtil.getStringValue(
		formulariumBean.getEndDate());

	}

	
					public void setDescription(String obj){

		formulariumBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		formulariumBean.getDescription());

	}
	
				
	public void setFormulariumType(String obj){

		formulariumBean.setFormulariumType(StringUtil.getIntegerValue(obj,0));

	}

	public String getFormulariumType(){
		return StringUtil.getStringValue(
		formulariumBean.getFormulariumType());

	}
	
				
	public void setProviderId(String obj){

		formulariumBean.setProviderId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProviderId(){
		return StringUtil.getStringValue(
		formulariumBean.getProviderId());

	}
	
				
	public void setClientId(String obj){

		formulariumBean.setClientId(StringUtil.getIntegerValue(obj,0));

	}

	public String getClientId(){
		return StringUtil.getStringValue(
		formulariumBean.getClientId());

	}
	
				
	public void setPolicyId(String obj){

		formulariumBean.setPolicyId(StringUtil.getIntegerValue(obj,0));

	}

	public String getPolicyId(){
		return StringUtil.getStringValue(
		formulariumBean.getPolicyId());

	}
	
				
	public void setStatus(String obj){

		formulariumBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		formulariumBean.getStatus());

	}
	
				
	public void setCreatedTime(String obj){

		formulariumBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		formulariumBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		formulariumBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		formulariumBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		formulariumBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		formulariumBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		formulariumBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		formulariumBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		formulariumBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		formulariumBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		formulariumBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		formulariumBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		formulariumBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		formulariumBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 

}
