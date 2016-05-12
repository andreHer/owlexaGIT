
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * RefCity is a mapping of ref_city Table.
*/
public class RefCityForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewRefCity = false;
	private RefCity refCityBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public RefCityForm()
    {
    	this.refCityBean = new RefCity();
    	this.isNewRefCity = true;
    }
    public RefCityForm (RefCity object){
		this.refCityBean = object;
    }
    public boolean isNewRefCity (){

    	return this.isNewRefCity;
    }
	public RefCity getRefCity (){
		return this.refCityBean ;
	}
	public void setRefCity (RefCity object){
		this.refCityBean = object;
	}

			
	public void setId(String obj){

		refCityBean.setId(StringUtil.getIntegerValue(obj,0));

	}

	public String getId(){
		return StringUtil.getStringValue(
		refCityBean.getId());

	}
	
					public void setCityName(String obj){

		refCityBean.setCityName(new String(obj));

	}

	public String getCityName(){
		return StringUtil.getStringValue(
		refCityBean.getCityName());

	}
	
					public void setCityCode(String obj){

		refCityBean.setCityCode(new String(obj));

	}

	public String getCityCode(){
		return StringUtil.getStringValue(
		refCityBean.getCityCode());

	}
	
					public void setDescription(String obj){

		refCityBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		refCityBean.getDescription());

	}
	
					public void setLongitude(String obj){

		refCityBean.setLongitude(new String(obj));

	}

	public String getLongitude(){
		return StringUtil.getStringValue(
		refCityBean.getLongitude());

	}
	
					public void setLatitude(String obj){

		refCityBean.setLatitude(new String(obj));

	}

	public String getLatitude(){
		return StringUtil.getStringValue(
		refCityBean.getLatitude());

	}
	
				
	public void setCreatedTime(String obj){

		refCityBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		refCityBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		refCityBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		refCityBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		refCityBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		refCityBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		refCityBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		refCityBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		refCityBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		refCityBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		refCityBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		refCityBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		refCityBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		refCityBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
