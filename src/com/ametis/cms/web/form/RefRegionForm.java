
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * RefRegion is a mapping of ref_region Table.
*/
public class RefRegionForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewRefRegion = false;
	private RefRegion refRegionBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public RefRegionForm()
    {
    	this.refRegionBean = new RefRegion();
    	this.isNewRefRegion = true;
    }
    public RefRegionForm (RefRegion object){
		this.refRegionBean = object;
    }
    public boolean isNewRefRegion (){

    	return this.isNewRefRegion;
    }
	public RefRegion getRefRegion (){
		return this.refRegionBean ;
	}
	public void setRefRegion (RefRegion object){
		this.refRegionBean = object;
	}

			
	public void setRefRegionId(String obj){

		refRegionBean.setRefRegionId(StringUtil.getIntegerValue(obj,0));

	}

	public String getRefRegionId(){
		return StringUtil.getStringValue(
		refRegionBean.getRefRegionId());

	}
	
					public void setRegionName(String obj){

		refRegionBean.setRegionName(new String(obj));

	}

	public String getRegionName(){
		return StringUtil.getStringValue(
		refRegionBean.getRegionName());

	}
	
					public void setRegionCode(String obj){

		refRegionBean.setRegionCode(new String(obj));

	}

	public String getRegionCode(){
		return StringUtil.getStringValue(
		refRegionBean.getRegionCode());

	}
	
					public void setDescription(String obj){

		refRegionBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		refRegionBean.getDescription());

	}
	
				
	public void setCreatedTime(String obj){

		refRegionBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		refRegionBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		refRegionBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		refRegionBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		refRegionBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		refRegionBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		refRegionBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		refRegionBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		refRegionBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		refRegionBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		refRegionBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		refRegionBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		refRegionBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		refRegionBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 

}
