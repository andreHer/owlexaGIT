
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.CallCategory;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * CallCategory is a mapping of call_category Table.
*/
public class CallCategoryForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewCallCategory = false;
	private CallCategory callCategoryBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public CallCategoryForm()
    {
    	this.callCategoryBean = new CallCategory();
    	this.isNewCallCategory = true;
    }
    public CallCategoryForm (CallCategory object){
		this.callCategoryBean = object;
    }
    public boolean isNewCallCategory (){

    	return this.isNewCallCategory;
    }
	public CallCategory getCallCategory (){
		return this.callCategoryBean ;
	}
	public void setCallCategory (CallCategory object){
		this.callCategoryBean = object;
	}

			
	public void setCallCategoryId(String obj){

		callCategoryBean.setCallCategoryId(StringUtil.getIntegerValue(obj,0));

	}

	public String getCallCategoryId(){
		return StringUtil.getStringValue(
		callCategoryBean.getCallCategoryId());

	}
	
					public void setCallCategoryName(String obj){

		callCategoryBean.setCallCategoryName(new String(obj));

	}

	public String getCallCategoryName(){
		return StringUtil.getStringValue(
		callCategoryBean.getCallCategoryName());

	}
	
					public void setCallCategoryCode(String obj){

		callCategoryBean.setCallCategoryCode(new String(obj));

	}

	public String getCallCategoryCode(){
		return StringUtil.getStringValue(
		callCategoryBean.getCallCategoryCode());

	}
	
					public void setDescription(String obj){

		callCategoryBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		callCategoryBean.getDescription());

	}
	
				
	public void setCreatedTime(String obj){

		callCategoryBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		callCategoryBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		callCategoryBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		callCategoryBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		callCategoryBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		callCategoryBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		callCategoryBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		callCategoryBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		callCategoryBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		callCategoryBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		callCategoryBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		callCategoryBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		callCategoryBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		callCategoryBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
