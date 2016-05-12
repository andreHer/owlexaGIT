
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * Poliklinik is a mapping of poliklinik Table.
*/
public class PoliklinikForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewPoliklinik = false;
	private Poliklinik poliklinikBean ;
	private String parentName;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public PoliklinikForm()
    {
    	this.poliklinikBean = new Poliklinik();
    	this.isNewPoliklinik = true;
    }
    public PoliklinikForm (Poliklinik object){
		this.poliklinikBean = object;
    }
    public boolean isNewPoliklinik (){

    	return this.isNewPoliklinik;
    }
	public Poliklinik getPoliklinik (){
		return this.poliklinikBean ;
	}
	public void setPoliklinik (Poliklinik object){
		this.poliklinikBean = object;
	}

			
	public void setPoliklinikId(String obj){

		poliklinikBean.setPoliklinikId(StringUtil.getIntegerValue(obj,0));

	}

	public String getPoliklinikId(){
		return StringUtil.getStringValue(
		poliklinikBean.getPoliklinikId());

	}
	
					public void setPoliklinikName(String obj){

		poliklinikBean.setPoliklinikName(new String(obj));

	}

	public String getPoliklinikName(){
		return StringUtil.getStringValue(
		poliklinikBean.getPoliklinikName());

	}
	
					public void setPoliklinikCode(String obj){

		poliklinikBean.setPoliklinikCode(new String(obj));

	}

	public String getPoliklinikCode(){
		return StringUtil.getStringValue(
		poliklinikBean.getPoliklinikCode());

	}
	
					public void setDescription(String obj){

		poliklinikBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		poliklinikBean.getDescription());

	}
	
				
	public void setParentId(String obj){

		
		if (obj != null && !obj.equals("")){
		
			Poliklinik poliklinik  = new Poliklinik();
			poliklinik.setPoliklinikId(Integer.valueOf(obj));
			poliklinikBean.setParentId(poliklinik);
		}
		
		
		

	}

	public String getParentId(){
		String result = "";
		
		if (poliklinikBean.getParentId() != null){
			result = poliklinikBean.getParentId().getPoliklinikId().toString();
		}
		
		return result;

	}
	
				
	public void setCreatedTime(String obj){

		poliklinikBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		poliklinikBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		poliklinikBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		poliklinikBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		poliklinikBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		poliklinikBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		poliklinikBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		poliklinikBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		poliklinikBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		poliklinikBean.getDeletedStatus());

	}
	
				
	public void setModifiedTime(String obj){

		poliklinikBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		poliklinikBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		poliklinikBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		poliklinikBean.getModifiedBy());

	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getPoliklinikEdcCode() {
		return poliklinikBean.getPoliklinikEdcCode();
	}
	public void setPoliklinikEdcCode(String parentName) {
		this.poliklinikBean.setPoliklinikEdcCode(parentName);
	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
