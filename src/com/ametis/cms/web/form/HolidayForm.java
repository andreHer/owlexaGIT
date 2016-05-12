
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * Holiday is a mapping of holiday Table.
*/
public class HolidayForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewHoliday = false;
	private Holiday holidayBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public HolidayForm()
    {
    	this.holidayBean = new Holiday();
    	this.isNewHoliday = true;
    }
    public HolidayForm (Holiday object){
		this.holidayBean = object;
    }
    public boolean isNewHoliday (){

    	return this.isNewHoliday;
    }
	public Holiday getHoliday (){
		return this.holidayBean ;
	}
	public void setHoliday (Holiday object){
		this.holidayBean = object;
	}

			
	public void setIdHoliday(String obj){

		holidayBean.setIdHoliday(StringUtil.getIntegerValue(obj,0));

	}

	public String getIdHoliday(){
		return StringUtil.getStringValue(
		holidayBean.getIdHoliday());

	}
	
				
	public void setTglHoliday(String obj){

		holidayBean.setTglHoliday(java.sql.Date.valueOf(obj));

	}

	public String getTglHoliday(){
		return StringUtil.getStringValue(
		holidayBean.getTglHoliday());

	}

	
					public void setDescription(String obj){

		holidayBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		holidayBean.getDescription());

	}
	
					public void setCreatedBy(String obj){

		holidayBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		holidayBean.getCreatedBy());

	}
	
					public void setModifiedBy(String obj){

		holidayBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		holidayBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		holidayBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		holidayBean.getDeletedStatus());

	}
	
				
	public void setModifiedTime(String obj){

		holidayBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		holidayBean.getModifiedTime());

	}

	
				
	public void setCreatedTime(String obj){

		holidayBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		holidayBean.getCreatedTime());

	}

	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
