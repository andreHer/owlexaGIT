
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * ProductTreatmentClausul is a mapping of product_treatment_clausul Table.
*/
public class ProductTreatmentClausulForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewProductTreatmentClausul = false;
	private ProductTreatmentClausul productTreatmentClausulBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ProductTreatmentClausulForm()
    {
    	this.productTreatmentClausulBean = new ProductTreatmentClausul();
    	this.isNewProductTreatmentClausul = true;
    }
    public ProductTreatmentClausulForm (ProductTreatmentClausul object){
		this.productTreatmentClausulBean = object;
    }
    public boolean isNewProductTreatmentClausul (){

    	return this.isNewProductTreatmentClausul;
    }
	public ProductTreatmentClausul getProductTreatmentClausul (){
		return this.productTreatmentClausulBean ;
	}
	public void setProductTreatmentClausul (ProductTreatmentClausul object){
		this.productTreatmentClausulBean = object;
	}

			
	public void setProductTreatmentClausulId(String obj){

		productTreatmentClausulBean.setProductTreatmentClausulId(StringUtil.getLongValue(obj,0));

	}

	public String getProductTreatmentClausulId(){
		return StringUtil.getStringValue(
		productTreatmentClausulBean.getProductTreatmentClausulId());

	}
	
				
	public void setItemId(String obj){

		productTreatmentClausulBean.setItemId(StringUtil.getLongValue(obj,0));

	}

	public String getItemId(){
		return StringUtil.getStringValue(
		productTreatmentClausulBean.getItemId());

	}
	
				
	public void setDecision(String obj){

		productTreatmentClausulBean.setDecision(StringUtil.getIntegerValue(obj,0));

	}

	public String getDecision(){
		return StringUtil.getStringValue(
		productTreatmentClausulBean.getDecision());

	}
	
				
	public void setUmur(String obj){

		productTreatmentClausulBean.setUmur(StringUtil.getIntegerValue(obj,0));

	}

	public String getUmur(){
		return StringUtil.getStringValue(
		productTreatmentClausulBean.getUmur());

	}
	
				
	public void setParameterUmur(String obj){

		productTreatmentClausulBean.setParameterUmur(StringUtil.getIntegerValue(obj,0));

	}

	public String getParameterUmur(){
		return StringUtil.getStringValue(
		productTreatmentClausulBean.getParameterUmur());

	}
	
				
	public void setBenefitAmount(String obj){

		productTreatmentClausulBean.setBenefitAmount(StringUtil.getDoubleValue(obj,0));

	}

	public String getBenefitAmount(){
		return StringUtil.getStringValue(
		productTreatmentClausulBean.getBenefitAmount());

	}
	
				
	public void setBenefitAmountParameter(String obj){

		productTreatmentClausulBean.setBenefitAmountParameter(StringUtil.getIntegerValue(obj,0));

	}

	public String getBenefitAmountParameter(){
		return StringUtil.getStringValue(
		productTreatmentClausulBean.getBenefitAmountParameter());

	}
	
				
	public void setBebanBenefit(String obj){

		productTreatmentClausulBean.setBebanBenefit(StringUtil.getIntegerValue(obj,0));

	}

	public String getBebanBenefit(){
		return StringUtil.getStringValue(
		productTreatmentClausulBean.getBebanBenefit());

	}
	
				
	public void setCreatedTime(String obj){

		productTreatmentClausulBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		productTreatmentClausulBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		productTreatmentClausulBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		productTreatmentClausulBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		productTreatmentClausulBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		productTreatmentClausulBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		productTreatmentClausulBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		productTreatmentClausulBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		productTreatmentClausulBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		productTreatmentClausulBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		productTreatmentClausulBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		productTreatmentClausulBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		productTreatmentClausulBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		productTreatmentClausulBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 

}
