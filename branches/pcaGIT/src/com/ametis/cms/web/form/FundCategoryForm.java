
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.FundCategory;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * FundCategory is a mapping of fund_category Table.
*/
public class FundCategoryForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewFundCategory = false;
	private FundCategory fundCategoryBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public FundCategoryForm()
    {
    	this.fundCategoryBean = new FundCategory();
    	this.isNewFundCategory = true;
    }
    public FundCategoryForm (FundCategory object){
		this.fundCategoryBean = object;
    }
    public boolean isNewFundCategory (){

    	return this.isNewFundCategory;
    }
	public FundCategory getFundCategory (){
		return this.fundCategoryBean ;
	}
	public void setFundCategory (FundCategory object){
		this.fundCategoryBean = object;
	}

			
	public void setFundCategoryId(String obj){

		fundCategoryBean.setFundCategoryId(StringUtil.getIntegerValue(obj,0));

	}

	public String getFundCategoryId(){
		return StringUtil.getStringValue(
		fundCategoryBean.getFundCategoryId());

	}
	
					public void setFundCategoryName(String obj){

		fundCategoryBean.setFundCategoryName(new String(obj));

	}

	public String getFundCategoryName(){
		return StringUtil.getStringValue(
		fundCategoryBean.getFundCategoryName());

	}
	
					public void setFundCategoryCode(String obj){

		fundCategoryBean.setFundCategoryCode(new String(obj));

	}

	public String getFundCategoryCode(){
		return StringUtil.getStringValue(
		fundCategoryBean.getFundCategoryCode());

	}
	
					public void setDescription(String obj){

		fundCategoryBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		fundCategoryBean.getDescription());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
