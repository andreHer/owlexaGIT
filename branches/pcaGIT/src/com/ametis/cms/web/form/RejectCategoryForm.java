
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.RejectCategory;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * RejectCategory is a mapping of reject_category Table.
*/
public class RejectCategoryForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewRejectCategory = false;
	private RejectCategory rejectCategoryBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public RejectCategoryForm()
    {
    	this.rejectCategoryBean = new RejectCategory();
    	this.isNewRejectCategory = true;
    }
    public RejectCategoryForm (RejectCategory object){
		this.rejectCategoryBean = object;
    }
    public boolean isNewRejectCategory (){

    	return this.isNewRejectCategory;
    }
	public RejectCategory getRejectCategory (){
		return this.rejectCategoryBean ;
	}
	public void setRejectCategory (RejectCategory object){
		this.rejectCategoryBean = object;
	}

			
	public void setRejectCategoryId(String obj){

		rejectCategoryBean.setRejectCategoryId(StringUtil.getIntegerValue(obj,0));

	}

	public String getRejectCategoryId(){
		return StringUtil.getStringValue(
		rejectCategoryBean.getRejectCategoryId());

	}
	
					public void setRejectCategoryName(String obj){

		rejectCategoryBean.setRejectCategoryName(new String(obj));

	}

	public String getRejectCategoryName(){
		return StringUtil.getStringValue(
		rejectCategoryBean.getRejectCategoryName());

	}
	public void setRejectCategoryCode(String obj){

		rejectCategoryBean.setRejectCategoryCode(new String(obj));

	}

	public String getRejectCategoryCode(){
		return StringUtil.getStringValue(
		rejectCategoryBean.getRejectCategoryCode());

	}
	public void setTipe(String obj){

		if (obj != null && !obj.equals("")){
			rejectCategoryBean.setTipe(Integer.valueOf(obj));
		}

	}

	public String getTipe(){
		return StringUtil.getStringValue(
		rejectCategoryBean.getTipe());

	}
	
					public void setDescription(String obj){

		rejectCategoryBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		rejectCategoryBean.getDescription());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
