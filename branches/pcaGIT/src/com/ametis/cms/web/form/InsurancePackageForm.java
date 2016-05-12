
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.InsurancePackage;
import com.ametis.cms.datamodel.ProductType;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * InsurancePackage is a mapping of insurance_package Table.
*/
public class InsurancePackageForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewInsurancePackage = false;
	private InsurancePackage insurancePackageBean ;
	private String clientName;
	
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public InsurancePackageForm()
    {
    	this.insurancePackageBean = new InsurancePackage();
    	this.isNewInsurancePackage = true;
    }
    public InsurancePackageForm (InsurancePackage object){
		this.insurancePackageBean = object;
    }
    
    
    public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public boolean isNewInsurancePackage (){

    	return this.isNewInsurancePackage;
    }
	public InsurancePackage getInsurancePackage (){
		return this.insurancePackageBean ;
	}
	public void setInsurancePackage (InsurancePackage object){
		this.insurancePackageBean = object;
	}

			
	public void setPackageId(String obj){

		insurancePackageBean.setPackageId(StringUtil.getIntegerValue(obj,0));

	}

	public String getPackageId(){
		return StringUtil.getStringValue(
		insurancePackageBean.getPackageId());

	}
	
					public void setPackageName(String obj){

		insurancePackageBean.setPackageName(new String(obj));

	}

	public String getPackageName(){
		return StringUtil.getStringValue(
		insurancePackageBean.getPackageName());

	}
	
					public void setPackageCode(String obj){

		insurancePackageBean.setPackageCode(new String(obj));

	}

	public String getPackageCode(){
		return StringUtil.getStringValue(
		insurancePackageBean.getPackageCode());

	}
	
				
	public void setAnnualBenefitValue(String obj){

		insurancePackageBean.setAnnualBenefitValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getAnnualBenefitValue(){
		return StringUtil.getStringValue(
		insurancePackageBean.getAnnualBenefitValue());

	}
	
					public void setPackageDescription(String obj){

		insurancePackageBean.setPackageDescription(new String(obj));

	}

	public String getPackageDescription(){
		return StringUtil.getStringValue(
		insurancePackageBean.getPackageDescription());

	}
	
				
	public void setPackageStatus(String obj){

		insurancePackageBean.setPackageStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getPackageStatus(){
		return StringUtil.getStringValue(
		insurancePackageBean.getPackageStatus());

	}
	
										
	public void setCreatedTime(String obj){

		insurancePackageBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		insurancePackageBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		insurancePackageBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		insurancePackageBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		insurancePackageBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		insurancePackageBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		insurancePackageBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		insurancePackageBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		insurancePackageBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		insurancePackageBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		insurancePackageBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		insurancePackageBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		insurancePackageBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		insurancePackageBean.getDeletedStatus());

	}
	
		

	// foreign affairs
	
	

	
	public void setClientId(String obj){
		Client fk = new Client();
		fk.setClientId(StringUtil.getIntegerValue(obj,0));
		insurancePackageBean.setClientId(fk);

	}

	public String getClientId(){
		return StringUtil.getStringValue(
		insurancePackageBean.getClientId().getClientId());

	}
	//---
	
	

	
	public void setPackageType(String obj){
		ProductType fk = new ProductType();
		fk.setProductTypeId(StringUtil.getIntegerValue(obj,0));
		insurancePackageBean.setPackageType(fk);

	}

	public String getPackageType(){
		return StringUtil.getStringValue(
		insurancePackageBean.getPackageType().getProductTypeId());

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
