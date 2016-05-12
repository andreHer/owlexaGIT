
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * ProblemCategory is a mapping of problem_category Table.
*/
public class ProblemCategoryForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewProblemCategory = false;
	private ProblemCategory problemCategoryBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ProblemCategoryForm()
    {
    	this.problemCategoryBean = new ProblemCategory();
    	this.isNewProblemCategory = true;
    }
    public ProblemCategoryForm (ProblemCategory object){
		this.problemCategoryBean = object;
    }
    public boolean isNewProblemCategory (){

    	return this.isNewProblemCategory;
    }
	public ProblemCategory getProblemCategory (){
		return this.problemCategoryBean ;
	}
	public void setProblemCategory (ProblemCategory object){
		this.problemCategoryBean = object;
	}

			
	public void setProblemCategoryId(String obj){

		problemCategoryBean.setProblemCategoryId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProblemCategoryId(){
		return StringUtil.getStringValue(
		problemCategoryBean.getProblemCategoryId());

	}
	
					public void setProblemCategoryName(String obj){

		problemCategoryBean.setProblemCategoryName(new String(obj));

	}

	public String getProblemCategoryName(){
		return StringUtil.getStringValue(
		problemCategoryBean.getProblemCategoryName());

	}
	
					public void setDescription(String obj){

		problemCategoryBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		problemCategoryBean.getDescription());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 

}
