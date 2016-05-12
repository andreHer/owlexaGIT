
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * ErrorLog is a mapping of error_log Table.
*/
public class ErrorLogForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewErrorLog = false;
	private ErrorLog errorLogBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ErrorLogForm()
    {
    	this.errorLogBean = new ErrorLog();
    	this.isNewErrorLog = true;
    }
    public ErrorLogForm (ErrorLog object){
		this.errorLogBean = object;
    }
    public boolean isNewErrorLog (){

    	return this.isNewErrorLog;
    }
	public ErrorLog getErrorLog (){
		return this.errorLogBean ;
	}
	public void setErrorLog (ErrorLog object){
		this.errorLogBean = object;
	}

			
	public void setId(String obj){

		errorLogBean.setId(StringUtil.getIntegerValue(obj,0));

	}

	public String getId(){
		return StringUtil.getStringValue(
		errorLogBean.getId());

	}
	
					public void setCreatedBy(String obj){

		errorLogBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		errorLogBean.getCreatedBy());

	}
	
					public void setDescription(String obj){

		errorLogBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		errorLogBean.getDescription());

	}
	
					public void setCurrentUrl(String obj){

		errorLogBean.setCurrentUrl(new String(obj));

	}

	public String getCurrentUrl(){
		return StringUtil.getStringValue(
		errorLogBean.getCurrentUrl());

	}
	
				
	public void setCreatedTime(String obj){

		errorLogBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		errorLogBean.getCreatedTime());

	}

	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
