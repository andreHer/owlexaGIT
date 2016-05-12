
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.Clausul;
import com.ametis.cms.datamodel.ClausulCategory;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * Clausul is a mapping of clausul Table.
*/
public class ClausulForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewClausul = false;
	private Clausul clausulBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ClausulForm()
    {
    	this.clausulBean = new Clausul();
    	this.isNewClausul = true;
    }
    public ClausulForm (Clausul object){
		this.clausulBean = object;
    }
    public boolean isNewClausul (){

    	return this.isNewClausul;
    }
	public Clausul getClausul (){
		return this.clausulBean ;
	}
	public void setClausul (Clausul object){
		this.clausulBean = object;
	}

			
	public void setClausulId(String obj){

		clausulBean.setClausulId(StringUtil.getIntegerValue(obj,0));

	}

	public String getClausulId(){
		return StringUtil.getStringValue(
		clausulBean.getClausulId());

	}
	
					public void setClausulName(String obj){

		clausulBean.setClausulName(new String(obj));

	}

	public String getClausulName(){
		return StringUtil.getStringValue(
		clausulBean.getClausulName());

	}
	
					public void setClausulCode(String obj){

		clausulBean.setClausulCode(new String(obj));

	}

	public String getClausulCode(){
		return StringUtil.getStringValue(
		clausulBean.getClausulCode());

	}
	
					public void setDescription(String obj){

		clausulBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		clausulBean.getDescription());

	}
	
							
	public void setCreatedTime(String obj){

		clausulBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		clausulBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		clausulBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		clausulBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		clausulBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		clausulBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		clausulBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		clausulBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		clausulBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		clausulBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		clausulBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		clausulBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		clausulBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		clausulBean.getDeletedStatus());

	}
	
		

	// foreign affairs
	
	

	
	public void setClausulCategoryId(String obj){
		ClausulCategory fk = new ClausulCategory();
		fk.setClausulCategoryId(StringUtil.getIntegerValue(obj,0));
		clausulBean.setClausulCategoryId(fk);

	}

	public String getClausulCategoryId(){
		return StringUtil.getStringValue(
		clausulBean.getClausulCategoryId().getClausulCategoryId());

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
