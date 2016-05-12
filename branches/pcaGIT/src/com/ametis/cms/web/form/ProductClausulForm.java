
package com.ametis.cms.web.form;

import org.springframework.web.multipart.MultipartFile;

import com.ametis.cms.datamodel.Clausul;
import com.ametis.cms.datamodel.Diagnosis;
import com.ametis.cms.datamodel.Product;
import com.ametis.cms.datamodel.ProductClausul;
import com.ametis.cms.datamodel.TreatmentUpgradeType;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * ProductClausul is a mapping of product_clausul Table.
*/
public class ProductClausulForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewProductClausul = false;
	private ProductClausul productClausulBean ;
	private String productName;
	private String clausulName;
	private String umur;
	private String diagnosisCode;
	private String parameterUmur;
	private String decision;
	private String pembebananBenefit;
	private String benefitAmount;
	private String benefitParameter;
	private String procedureId;
	private String procedureName;
	private String diagnosisId;
	
	private TreatmentUpgradeType treatmentUpgradeType;
	
	private Integer reductionType; // fix percentage, prorate, normal benefit
	private Integer reductionSubject; // total charge, ROOM Excluded
	private double benefitReductionPercentage;
	
	private MultipartFile multipartFile;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ProductClausulForm()
    {
    	this.productClausulBean = new ProductClausul();
    	this.isNewProductClausul = true;
    }
    public ProductClausulForm (ProductClausul object){
		this.productClausulBean = object;
    }
    public boolean isNewProductClausul (){

    	return this.isNewProductClausul;
    }
	public ProductClausul getProductClausul (){
		return this.productClausulBean ;
	}
	public void setProductClausul (ProductClausul object){
		this.productClausulBean = object;
	}

			
	public void setProductClausulId(String obj){

		productClausulBean.setProductClausulId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProductClausulId(){
		return StringUtil.getStringValue(
		productClausulBean.getProductClausulId());

	}
	
	public void setDescription(String obj){
		productClausulBean.setDescription(new String(obj));
	}

	public String getDescription(){
		return StringUtil.getStringValue(
		productClausulBean.getDescription());

	}
	
				
	

	
	public void setProductId(String obj){
		Product fk = new Product();
		fk.setProductId(StringUtil.getIntegerValue(obj,0));
		productClausulBean.setProductId(fk);
	}

	public String getProductId(){
		return StringUtil.getStringValue(
		productClausulBean.getProductId().getProductId());
	}

	
	public void setClausulId(String obj){
		Clausul fk = new Clausul();
		fk.setClausulId(StringUtil.getIntegerValue(obj,0));
		productClausulBean.setClausulId(fk);

	}

	public String getClausulId(){
		String result = "";
		
		if (productClausulBean.getClausulId() != null){
			result = StringUtil.getStringValue(
					productClausulBean.getClausulId().getClausulId()); 
		}
		return result;
		

	}
	//---
		// -- foreign affairs end
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getClausulName() {
		return clausulName;
	}
	public void setClausulName(String clausulName) {
		this.clausulName = clausulName;
	}
	public ProductClausul getProductClausulBean() {
		return productClausulBean;
	}
	public void setProductClausulBean(ProductClausul productClausulBean) {
		this.productClausulBean = productClausulBean;
	}
	public String getUmur() {
		return umur;
	}
	public void setUmur(String umur) {
		this.umur = umur;
	}
	public String getDiagnosisCode() {
		return diagnosisCode;
	}
	public void setDiagnosisCode(String diagnosisCode) {
		this.diagnosisCode = diagnosisCode;
	}
	public String getParameterUmur() {
		return parameterUmur;
	}
	public void setParameterUmur(String parameterUmur) {
		this.parameterUmur = parameterUmur;
	}
	public String getDecision() {
		String result = "-1";
		if (productClausulBean.getDecision() != null){
			result = productClausulBean.getDecision().toString();
		}
		return result;
	}
	public void setDecision(String object) {
		if (object != null && !object.equalsIgnoreCase("") && !object.equalsIgnoreCase("-1")){
			
			this.productClausulBean.setDecision(Integer.valueOf(object));
		}
	}
	public String getPembebananBenefit() {
		return pembebananBenefit;
	}
	public void setPembebananBenefit(String pembebananBenefit) {
		this.pembebananBenefit = pembebananBenefit;
	}
	public String getBenefitAmount() {
		return benefitAmount;
	}
	public void setBenefitAmount(String benefitAmount) {
		this.benefitAmount = benefitAmount;
	}
	public String getBenefitParameter() {
		return benefitParameter;
	}
	public void setBenefitParameter(String benefitParameter) {
		this.benefitParameter = benefitParameter;
	}
	public void setNewProductClausul(boolean isNewProductClausul) {
		this.isNewProductClausul = isNewProductClausul;
	}
	public String getProcedureId() {
		return procedureId;
	}
	public void setProcedureId(String procedureId) {
		this.procedureId = procedureId;
	}
	public String getProcedureName() {
		return procedureName;
	}
	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}
	public String getDiagnosisId() {
		String result = "-1";
		if (productClausulBean.getDiagnosisId() != null){
			result = productClausulBean.getDiagnosisId().getDiagnosisId().toString();
		}
		return result;
	}
	public void setDiagnosisId(String object) {
		if (object != null && !object.equalsIgnoreCase("") && !object.equalsIgnoreCase("-1")){
			Diagnosis diagnosis = new Diagnosis();
			diagnosis.setDiagnosisId(Integer.valueOf(object));
			this.productClausulBean.setDiagnosisId(diagnosis);
		}
	}
	public String getTreatmentUpgradeType() {
		String result = "-1";
		if (productClausulBean.getTreatmentUpgradeType() != null){
			result = productClausulBean.getTreatmentUpgradeType().getTreatmentUpgradeTypeId().toString();
		}
		return result;
	}
	public void setTreatmentUpgradeType(String object) {
		if (object != null && !object.equalsIgnoreCase("")){
			TreatmentUpgradeType upgrade = new TreatmentUpgradeType();
			upgrade.setTreatmentUpgradeTypeId(Integer.valueOf(object));
			this.productClausulBean.setTreatmentUpgradeType(upgrade);
		}
		
	}
	public String getReductionType() {
		String result = "-1";
		
		if (productClausulBean.getReductionType() != null){
			result = productClausulBean.getReductionType().toString();
		}
		
		return result;
	}
	public void setReductionType(String object) {
		if (object != null && !object.equalsIgnoreCase("")){
			productClausulBean.setReductionType(Integer.valueOf(object));
		}
	}
	public String getReductionSubject() {
		String result = "-1";
		
		if (productClausulBean.getReductionSubject() != null){
			result = productClausulBean.getReductionSubject().toString();
		}
		
		return result;
	}
	public void setReductionSubject(String object) {
		if (object != null && !object.equalsIgnoreCase("")){
			productClausulBean.setReductionSubject(Integer.valueOf(object));
		}
	}
	public String getBenefitReductionPercentage() {
		String result = "";
		if (productClausulBean.getBenefitReductionPercentage() != null){
			result = productClausulBean.getBenefitReductionPercentage().toString();
		}
		return result;
	}
	public void setBenefitReductionPercentage(double benefitReductionPercentage) {
		this.productClausulBean.setBenefitReductionPercentage(benefitReductionPercentage);
	}
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}
	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}

	
	
	
// class+ 

// class- 
}
