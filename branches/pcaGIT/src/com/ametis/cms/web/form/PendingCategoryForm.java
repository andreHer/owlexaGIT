
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.PendingCategory;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * PendingCategory is a mapping of pending_category Table.
*/
public class PendingCategoryForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewPendingCategory = false;
	private PendingCategory pendingCategoryBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public PendingCategoryForm()
    {
    	this.pendingCategoryBean = new PendingCategory();
    	this.isNewPendingCategory = true;
    }
    public PendingCategoryForm (PendingCategory object){
		this.pendingCategoryBean = object;
    }
    public boolean isNewPendingCategory (){

    	return this.isNewPendingCategory;
    }
	public PendingCategory getPendingCategory (){
		return this.pendingCategoryBean ;
	}
	public void setPendingCategory (PendingCategory object){
		this.pendingCategoryBean = object;
	}

			
	public void setPendingCategoryId(String obj){

		pendingCategoryBean.setPendingCategoryId(StringUtil.getIntegerValue(obj,0));

	}

	public String getPendingCategoryId(){
		return StringUtil.getStringValue(
		pendingCategoryBean.getPendingCategoryId());

	}
	
					public void setPendingCategoryName(String obj){

		pendingCategoryBean.setPendingCategoryName(new String(obj));

	}

	public String getPendingCategoryName(){
		return StringUtil.getStringValue(
		pendingCategoryBean.getPendingCategoryName());

	}
	
					public void setDescription(String obj){

		pendingCategoryBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		pendingCategoryBean.getDescription());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
