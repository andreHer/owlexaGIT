
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * RefProvince is a mapping of ref_province Table.
*/
public class RefProvinceForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewRefProvince = false;
	private RefProvince refProvinceBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public RefProvinceForm()
    {
    	this.refProvinceBean = new RefProvince();
    	this.isNewRefProvince = true;
    }
    public RefProvinceForm (RefProvince object){
		this.refProvinceBean = object;
    }
    public boolean isNewRefProvince (){

    	return this.isNewRefProvince;
    }
	public RefProvince getRefProvince (){
		return this.refProvinceBean ;
	}
	public void setRefProvince (RefProvince object){
		this.refProvinceBean = object;
	}

			
	public void setId(String obj){

		refProvinceBean.setId(StringUtil.getIntegerValue(obj,0));

	}

	public String getId(){
		return StringUtil.getStringValue(
		refProvinceBean.getId());

	}
	
				
	public void setCountryId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			RefCountry country = new RefCountry();
			country.setId(Integer.valueOf(obj));
			
			refProvinceBean.setCountryId(country);
		}

	}

	public String getCountryId(){
		return StringUtil.getStringValue(
		refProvinceBean.getCountryId());

	}
	
					public void setProvinceName(String obj){

		refProvinceBean.setProvinceName(new String(obj));

	}

	public String getProvinceName(){
		return StringUtil.getStringValue(
		refProvinceBean.getProvinceName());

	}
	
					public void setProvinceCode(String obj){

		refProvinceBean.setProvinceCode(new String(obj));

	}

	public String getProvinceCode(){
		return StringUtil.getStringValue(
		refProvinceBean.getProvinceCode());

	}
	
					public void setDescription(String obj){

		refProvinceBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		refProvinceBean.getDescription());

	}
	
					public void setLongitude(String obj){

		refProvinceBean.setLongitude(new String(obj));

	}

	public String getLongitude(){
		return StringUtil.getStringValue(
		refProvinceBean.getLongitude());

	}
	
					public void setLatitude(String obj){

		refProvinceBean.setLatitude(new String(obj));

	}

	public String getLatitude(){
		return StringUtil.getStringValue(
		refProvinceBean.getLatitude());

	}
	
				
	public void setCreatedTime(String obj){

		refProvinceBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		refProvinceBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		refProvinceBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		refProvinceBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		refProvinceBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		refProvinceBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		refProvinceBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		refProvinceBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		refProvinceBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		refProvinceBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		refProvinceBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		refProvinceBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		refProvinceBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		refProvinceBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
