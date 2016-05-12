
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.BenefitCategory;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * BenefitCategory is a mapping of benefit_category Table.
*/
public class BenefitCategoryForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewBenefitCategory = false;
	private BenefitCategory benefitCategoryBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public BenefitCategoryForm()
    {
    	this.benefitCategoryBean = new BenefitCategory();
    	this.isNewBenefitCategory = true;
    }
    public BenefitCategoryForm (BenefitCategory object){
		this.benefitCategoryBean = object;
    }
    public boolean isNewBenefitCategory (){

    	return this.isNewBenefitCategory;
    }
	public BenefitCategory getBenefitCategory (){
		return this.benefitCategoryBean ;
	}
	public void setBenefitCategory (BenefitCategory object){
		this.benefitCategoryBean = object;
	}

			
	public void setBenefitCategoryId(String obj){

		benefitCategoryBean.setBenefitCategoryId(StringUtil.getIntegerValue(obj,0));

	}

	public String getBenefitCategoryId(){
		return StringUtil.getStringValue(
		benefitCategoryBean.getBenefitCategoryId());

	}
	
					public void setBenefitCategoryName(String obj){

		benefitCategoryBean.setBenefitCategoryName(new String(obj));

	}

	public String getBenefitCategoryName(){
		return StringUtil.getStringValue(
		benefitCategoryBean.getBenefitCategoryName());

	}
	
					public void setDescription(String obj){

		benefitCategoryBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		benefitCategoryBean.getDescription());

	}
	
				
	public void setCreatedTime(String obj){

		benefitCategoryBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		benefitCategoryBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		benefitCategoryBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		benefitCategoryBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		benefitCategoryBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		benefitCategoryBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		benefitCategoryBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		benefitCategoryBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		benefitCategoryBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		benefitCategoryBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		benefitCategoryBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		benefitCategoryBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		benefitCategoryBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		benefitCategoryBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
