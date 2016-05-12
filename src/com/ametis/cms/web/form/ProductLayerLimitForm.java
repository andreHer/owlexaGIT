
package com.ametis.cms.web.form;

import java.math.BigDecimal;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * ProductLayerLimit is a mapping of product_layer_limit Table.
*/
public class ProductLayerLimitForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewProductLayerLimit = false;
	private ProductLayerLimit productLayerLimitBean ;
	private String productName;
	private String diagnosisName;
	
	private String diagnosisSetName;
	
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ProductLayerLimitForm()
    {
    	this.productLayerLimitBean = new ProductLayerLimit();
    	this.isNewProductLayerLimit = true;
    }
    public ProductLayerLimitForm (ProductLayerLimit object){
		this.productLayerLimitBean = object;
    }
    public boolean isNewProductLayerLimit (){

    	return this.isNewProductLayerLimit;
    }
	public ProductLayerLimit getProductLayerLimit (){
		return this.productLayerLimitBean ;
	}
	public void setProductLayerLimit (ProductLayerLimit object){
		this.productLayerLimitBean = object;
	}

			
	public void setProductLayerLimitId(String obj){

		productLayerLimitBean.setProductLayerLimitId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProductLayerLimitId(){
		return StringUtil.getStringValue(
		productLayerLimitBean.getProductLayerLimitId());

	}
	
							
	public void setCoSharePercentage(String obj){

		productLayerLimitBean.setCoSharePercentage(StringUtil.getDoubleValue(obj,0));

	}

	public String getCoSharePercentage(){
		return StringUtil.getStringValue(
		productLayerLimitBean.getCoSharePercentage());

	}
	
				
	public void setCoShareAmount(String obj){

		productLayerLimitBean.setCoShareAmount(StringUtil.getDoubleValue(obj,0));

	}

	public String getCoShareAmount(){
		return StringUtil.getStringValue(
		productLayerLimitBean.getCoShareAmount());

	}
	
				
	public void setAnnualLimit(String obj){

		
		productLayerLimitBean.setAnnualLimit(StringUtil.getDoubleValue(obj,0));

	}

	public String getAnnualLimit(){
		String result = "";
		
		if (productLayerLimitBean.getAnnualLimit() != null){
			BigDecimal bc = new BigDecimal(productLayerLimitBean.getAnnualLimit());
			
			result = bc.toPlainString();
		}
		
		return result;
		

	}
	public void setDeductible(String obj){

		
		productLayerLimitBean.setDeductible(StringUtil.getDoubleValue(obj,0));

	}

	public String getDeductible(){
		String result = "";
		
		if (productLayerLimitBean.getDeductible() != null){
			BigDecimal bc = new BigDecimal(productLayerLimitBean.getDeductible());
			
			result = bc.toPlainString();
		}
		
		return result;
		

	}
				
	public void setLayerLevel(String obj){

		productLayerLimitBean.setLayerLevel(StringUtil.getIntegerValue(obj,0));

	}

	public String getLayerLevel(){
		return StringUtil.getStringValue(
		productLayerLimitBean.getLayerLevel());

	}
	
					public void setDescription(String obj){

		productLayerLimitBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		productLayerLimitBean.getDescription());

	}
	
	public void setProductId(String obj){
		Product fk = new Product();
		fk.setProductId(StringUtil.getIntegerValue(obj,0));
		productLayerLimitBean.setProductId(fk);

	}

	public String getProductId(){
		return StringUtil.getStringValue(
		productLayerLimitBean.getProductId().getProductId());

	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setDiagnosisId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Diagnosis fk = new Diagnosis();
			fk.setDiagnosisId(StringUtil.getIntegerValue(obj,0));
			productLayerLimitBean.setDiagnosisId(fk);
		}

	}

	public String getDiagnosisId(){
		String result = "";
		
		if (productLayerLimitBean.getDiagnosisId() != null){
			result = StringUtil.getStringValue(
					productLayerLimitBean.getDiagnosisId().getDiagnosisId());
		}
		
		return result;
		

	}
	public String getDiagnosisName() {
		return diagnosisName;
	}
	public void setDiagnosisName(String diagnosisName) {
		this.diagnosisName = diagnosisName;
	}
	public void setDiagnosisSetId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			DiagnosisSet fk = new DiagnosisSet();
			fk.setDiagnosisSetId(StringUtil.getIntegerValue(obj,0));
			productLayerLimitBean.setDiagnosisSetId(fk);
		}

	}

	public String getDiagnosisSetId(){
		String result = "";
		
		if (productLayerLimitBean.getDiagnosisSetId() != null){
			result = StringUtil.getStringValue(
					productLayerLimitBean.getDiagnosisSetId().getDiagnosisSetId());
		}
		
		return result;
		

	}
	public void setProductTypeId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			ProductType fk = new ProductType();
			fk.setProductTypeId(StringUtil.getIntegerValue(obj,0));
			productLayerLimitBean.setProductTypeId(fk);
		}

	}

	public String getProductTypeId(){
		String result = "";
		
		if (productLayerLimitBean.getProductTypeId() != null){
			result = StringUtil.getStringValue(
					productLayerLimitBean.getProductTypeId().getProductTypeId());
		}
		
		return result;
		

	}
	public String getDiagnosisSetName() {
		return diagnosisSetName;
	}
	public void setDiagnosisSetName(String diagnosisName) {
		this.diagnosisSetName = diagnosisName;
	}
}
