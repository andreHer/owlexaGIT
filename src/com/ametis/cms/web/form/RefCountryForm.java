
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * RefCountry is a mapping of ref_country Table.
*/
public class RefCountryForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewRefCountry = false;
	private RefCountry refCountryBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public RefCountryForm()
    {
    	this.refCountryBean = new RefCountry();
    	this.isNewRefCountry = true;
    }
    public RefCountryForm (RefCountry object){
		this.refCountryBean = object;
    }
    public boolean isNewRefCountry (){

    	return this.isNewRefCountry;
    }
	public RefCountry getRefCountry (){
		return this.refCountryBean ;
	}
	public void setRefCountry (RefCountry object){
		this.refCountryBean = object;
	}

			
	public void setId(String obj){

		refCountryBean.setId(StringUtil.getIntegerValue(obj,0));

	}

	public String getId(){
		return StringUtil.getStringValue(
		refCountryBean.getId());

	}
	
					public void setCountryCode(String obj){

		refCountryBean.setCountryCode(new String(obj));

	}

	public String getCountryCode(){
		return StringUtil.getStringValue(
		refCountryBean.getCountryCode());

	}
	
					public void setCountryName(String obj){

		refCountryBean.setCountryName(new String(obj));

	}

	public String getCountryName(){
		return StringUtil.getStringValue(
		refCountryBean.getCountryName());

	}
	
					public void setDescription(String obj){

		refCountryBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		refCountryBean.getDescription());

	}
	
					public void setLongitude(String obj){

		refCountryBean.setLongitude(new String(obj));

	}

	public String getLongitude(){
		return StringUtil.getStringValue(
		refCountryBean.getLongitude());

	}
	
					public void setLatitude(String obj){

		refCountryBean.setLatitude(new String(obj));

	}

	public String getLatitude(){
		return StringUtil.getStringValue(
		refCountryBean.getLatitude());

	}
	
				
	public void setCreatedTime(String obj){

		refCountryBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		refCountryBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		refCountryBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		refCountryBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		refCountryBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		refCountryBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		refCountryBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		refCountryBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		refCountryBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		refCountryBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		refCountryBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		refCountryBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		refCountryBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		refCountryBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
