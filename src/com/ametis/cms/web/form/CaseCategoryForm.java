
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * CaseCategory is a mapping of case_category Table.
*/
public class CaseCategoryForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewCaseCategory = false;
	private CaseCategory caseCategoryBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public CaseCategoryForm()
    {
    	this.caseCategoryBean = new CaseCategory();
    	this.isNewCaseCategory = true;
    }
    public CaseCategoryForm (CaseCategory object){
		this.caseCategoryBean = object;
    }
    public boolean isNewCaseCategory (){

    	return this.isNewCaseCategory;
    }
	public CaseCategory getCaseCategory (){
		return this.caseCategoryBean ;
	}
	public void setCaseCategory (CaseCategory object){
		this.caseCategoryBean = object;
	}

			
	public void setCaseCategoryId(String obj){

		caseCategoryBean.setCaseCategoryId(StringUtil.getIntegerValue(obj,0));

	}

	public String getCaseCategoryId(){
		return StringUtil.getStringValue(
		caseCategoryBean.getCaseCategoryId());

	}


	public void setCaseCategoryCode(String obj){

		caseCategoryBean.setCaseCategoryCode(new String(obj));

	}

	public String getCaseCategoryCode(){
		return StringUtil.getStringValue(
		caseCategoryBean.getCaseCategoryCode());

	}
					public void setCaseCategoryName(String obj){

		caseCategoryBean.setCaseCategoryName(new String(obj));

	}

	public String getCaseCategoryName(){
		return StringUtil.getStringValue(
		caseCategoryBean.getCaseCategoryName());

	}
	
					public void setDescription(String obj){

		caseCategoryBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		caseCategoryBean.getDescription());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
