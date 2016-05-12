
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * Therapy is a mapping of therapy Table.
*/
public class TherapyForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewTherapy = false;
	private Therapy therapyBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public TherapyForm()
    {
    	this.therapyBean = new Therapy();
    	this.isNewTherapy = true;
    }
    public TherapyForm (Therapy object){
		this.therapyBean = object;
    }
    public boolean isNewTherapy (){

    	return this.isNewTherapy;
    }
	public Therapy getTherapy (){
		return this.therapyBean ;
	}
	public void setTherapy (Therapy object){
		this.therapyBean = object;
	}

			
	public void setTherapyId(String obj){

		therapyBean.setTherapyId(StringUtil.getIntegerValue(obj,0));

	}

	public String getTherapyId(){
		return StringUtil.getStringValue(
		therapyBean.getTherapyId());

	}
	
					public void setTherapyName(String obj){

		therapyBean.setTherapyName(new String(obj));

	}

	public String getTherapyName(){
		return StringUtil.getStringValue(
		therapyBean.getTherapyName());

	}
	
					public void setTherapyCode(String obj){

		therapyBean.setTherapyCode(new String(obj));

	}

	public String getTherapyCode(){
		return StringUtil.getStringValue(
		therapyBean.getTherapyCode());

	}
	
					public void setDescription(String obj){

		therapyBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		therapyBean.getDescription());

	}
	
				
	public void setClassIndex(String obj){

		therapyBean.setClassIndex(StringUtil.getDoubleValue(obj,0));

	}

	public String getClassIndex(){
		return StringUtil.getStringValue(
		therapyBean.getClassIndex());

	}
	
				
	public void setCreatedTime(String obj){

		therapyBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		therapyBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		therapyBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		therapyBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		therapyBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		therapyBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		therapyBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		therapyBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		therapyBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		therapyBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		therapyBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		therapyBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		therapyBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		therapyBean.getDeletedStatus());

	}
	
				
	public void setCoefficient(String obj){

		therapyBean.setCoefficient(StringUtil.getIntegerValue(obj,0));

	}

	public String getCoefficient(){
		return StringUtil.getStringValue(
		therapyBean.getCoefficient());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 

}
