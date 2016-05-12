
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.InvestigationCategory;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * InvestigationCategory is a mapping of investigation_category Table.
*/
public class InvestigationCategoryForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewInvestigationCategory = false;
	private InvestigationCategory investigationCategoryBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public InvestigationCategoryForm()
    {
    	this.investigationCategoryBean = new InvestigationCategory();
    	this.isNewInvestigationCategory = true;
    }
    public InvestigationCategoryForm (InvestigationCategory object){
		this.investigationCategoryBean = object;
    }
    public boolean isNewInvestigationCategory (){

    	return this.isNewInvestigationCategory;
    }
	public InvestigationCategory getInvestigationCategory (){
		return this.investigationCategoryBean ;
	}
	public void setInvestigationCategory (InvestigationCategory object){
		this.investigationCategoryBean = object;
	}

			
	public void setInvestigationCategoryId(String obj){

		investigationCategoryBean.setInvestigationCategoryId(StringUtil.getIntegerValue(obj,0));

	}

	public String getInvestigationCategoryId(){
		return StringUtil.getStringValue(
		investigationCategoryBean.getInvestigationCategoryId());

	}
	
					public void setInvestigationCategoryName(String obj){

		investigationCategoryBean.setInvestigationCategoryName(new String(obj));

	}

	public String getInvestigationCategoryName(){
		return StringUtil.getStringValue(
		investigationCategoryBean.getInvestigationCategoryName());

	}
	
					public void setDescription(String obj){

		investigationCategoryBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		investigationCategoryBean.getDescription());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
