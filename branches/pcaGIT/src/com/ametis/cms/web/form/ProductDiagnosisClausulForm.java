
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * ProductDiagnosisClausul is a mapping of product_diagnosis_clausul Table.
*/
public class ProductDiagnosisClausulForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewProductDiagnosisClausul = false;
	private ProductDiagnosisClausul productDiagnosisClausulBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ProductDiagnosisClausulForm()
    {
    	this.productDiagnosisClausulBean = new ProductDiagnosisClausul();
    	this.isNewProductDiagnosisClausul = true;
    }
    public ProductDiagnosisClausulForm (ProductDiagnosisClausul object){
		this.productDiagnosisClausulBean = object;
    }
    public boolean isNewProductDiagnosisClausul (){

    	return this.isNewProductDiagnosisClausul;
    }
	public ProductDiagnosisClausul getProductDiagnosisClausul (){
		return this.productDiagnosisClausulBean ;
	}
	public void setProductDiagnosisClausul (ProductDiagnosisClausul object){
		this.productDiagnosisClausulBean = object;
	}

			
	public void setProductDiagnosisClausulId(String obj){

		productDiagnosisClausulBean.setProductDiagnosisClausulId(StringUtil.getLongValue(obj,0));

	}

	public String getProductDiagnosisClausulId(){
		return StringUtil.getStringValue(
		productDiagnosisClausulBean.getProductDiagnosisClausulId());

	}
	
				
	public void setProductId(String obj){

		productDiagnosisClausulBean.setProductId(StringUtil.getLongValue(obj,0));

	}

	public String getProductId(){
		return StringUtil.getStringValue(
		productDiagnosisClausulBean.getProductId());

	}
	
				
	public void setDiagnosisId(String obj){

		productDiagnosisClausulBean.setDiagnosisId(StringUtil.getLongValue(obj,0));

	}

	public String getDiagnosisId(){
		return StringUtil.getStringValue(
		productDiagnosisClausulBean.getDiagnosisId());

	}
	
				
	public void setDecision(String obj){

		productDiagnosisClausulBean.setDecision(StringUtil.getIntegerValue(obj,0));

	}

	public String getDecision(){
		return StringUtil.getStringValue(
		productDiagnosisClausulBean.getDecision());

	}
	
				
	public void setParameterUmur(String obj){

		productDiagnosisClausulBean.setParameterUmur(StringUtil.getIntegerValue(obj,0));

	}

	public String getParameterUmur(){
		return StringUtil.getStringValue(
		productDiagnosisClausulBean.getParameterUmur());

	}
	
				
	public void setUmur(String obj){

		productDiagnosisClausulBean.setUmur(StringUtil.getIntegerValue(obj,0));

	}

	public String getUmur(){
		return StringUtil.getStringValue(
		productDiagnosisClausulBean.getUmur());

	}
	
				
	public void setBenefitAmount(String obj){

		productDiagnosisClausulBean.setBenefitAmount(StringUtil.getDoubleValue(obj,0));

	}

	public String getBenefitAmount(){
		return StringUtil.getStringValue(
		productDiagnosisClausulBean.getBenefitAmount());

	}
	
				
	public void setBenefitAmountParameter(String obj){

		productDiagnosisClausulBean.setBenefitAmountParameter(StringUtil.getIntegerValue(obj,0));

	}

	public String getBenefitAmountParameter(){
		return StringUtil.getStringValue(
		productDiagnosisClausulBean.getBenefitAmountParameter());

	}
	
				
	public void setBebanBenefit(String obj){

		productDiagnosisClausulBean.setBebanBenefit(StringUtil.getIntegerValue(obj,0));

	}

	public String getBebanBenefit(){
		return StringUtil.getStringValue(
		productDiagnosisClausulBean.getBebanBenefit());

	}
	
				
	public void setCreatedTime(String obj){

		productDiagnosisClausulBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		productDiagnosisClausulBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		productDiagnosisClausulBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		productDiagnosisClausulBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		productDiagnosisClausulBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		productDiagnosisClausulBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		productDiagnosisClausulBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		productDiagnosisClausulBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		productDiagnosisClausulBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		productDiagnosisClausulBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		productDiagnosisClausulBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		productDiagnosisClausulBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		productDiagnosisClausulBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		productDiagnosisClausulBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 

}
