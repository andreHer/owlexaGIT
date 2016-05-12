
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.ClausulCategory;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * ClausulCategory is a mapping of clausul_category Table.
*/
public class ClausulCategoryForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewClausulCategory = false;
	private ClausulCategory clausulCategoryBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ClausulCategoryForm()
    {
    	this.clausulCategoryBean = new ClausulCategory();
    	this.isNewClausulCategory = true;
    }
    public ClausulCategoryForm (ClausulCategory object){
		this.clausulCategoryBean = object;
    }
    public boolean isNewClausulCategory (){

    	return this.isNewClausulCategory;
    }
	public ClausulCategory getClausulCategory (){
		return this.clausulCategoryBean ;
	}
	public void setClausulCategory (ClausulCategory object){
		this.clausulCategoryBean = object;
	}

			
	public void setClausulCategoryId(String obj){

		clausulCategoryBean.setClausulCategoryId(StringUtil.getIntegerValue(obj,0));

	}

	public String getClausulCategoryId(){
		return StringUtil.getStringValue(
		clausulCategoryBean.getClausulCategoryId());

	}
	
					public void setClausulCategoryName(String obj){

		clausulCategoryBean.setClausulCategoryName(new String(obj));

	}

	public String getClausulCategoryName(){
		return StringUtil.getStringValue(
		clausulCategoryBean.getClausulCategoryName());

	}
	
					public void setClausulCategoryCode(String obj){

		clausulCategoryBean.setClausulCategoryCode(new String(obj));

	}

	public String getClausulCategoryCode(){
		return StringUtil.getStringValue(
		clausulCategoryBean.getClausulCategoryCode());

	}
	
					public void setDescription(String obj){

		clausulCategoryBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		clausulCategoryBean.getDescription());

	}
	
				
	public void setCreatedTime(String obj){

		clausulCategoryBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		clausulCategoryBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		clausulCategoryBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		clausulCategoryBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		clausulCategoryBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		clausulCategoryBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		clausulCategoryBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		clausulCategoryBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		clausulCategoryBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		clausulCategoryBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		clausulCategoryBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		clausulCategoryBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		clausulCategoryBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		clausulCategoryBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
