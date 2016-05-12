
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.Benefit;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * Benefit is a mapping of benefit Table.
*/
public class BenefitForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewBenefit = false;
	private Benefit benefitBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public BenefitForm()
    {
    	this.benefitBean = new Benefit();
    	this.isNewBenefit = true;
    }
    public BenefitForm (Benefit object){
		this.benefitBean = object;
    }
    public boolean isNewBenefit (){

    	return this.isNewBenefit;
    }
	public Benefit getBenefit (){
		return this.benefitBean ;
	}
	public void setBenefit (Benefit object){
		this.benefitBean = object;
	}

			
	public void setBenefitId(String obj){

		benefitBean.setBenefitId(StringUtil.getIntegerValue(obj,0));

	}

	public String getBenefitId(){
		return StringUtil.getStringValue(
		benefitBean.getBenefitId());

	}
	
					public void setBenefitName(String obj){

		benefitBean.setBenefitName(new String(obj));

	}

	public String getBenefitName(){
		return StringUtil.getStringValue(
		benefitBean.getBenefitName());

	}
	
					public void setBenefitDescription(String obj){

		benefitBean.setBenefitDescription(new String(obj));

	}

	public String getBenefitDescription(){
		return StringUtil.getStringValue(
		benefitBean.getBenefitDescription());

	}
	
				
	public void setBenefitValue(String obj){

		benefitBean.setBenefitValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getBenefitValue(){
		return StringUtil.getStringValue(
		benefitBean.getBenefitValue());

	}
	
				
	public void setBenefitType(String obj){

		benefitBean.setBenefitType(StringUtil.getIntegerValue(obj,0));

	}

	public String getBenefitType(){
		return StringUtil.getStringValue(
		benefitBean.getBenefitType());

	}
	
				
	public void setBenefitCategoryId(String obj){

		benefitBean.setBenefitCategoryId(StringUtil.getIntegerValue(obj,0));

	}

	public String getBenefitCategoryId(){
		return StringUtil.getStringValue(
		benefitBean.getBenefitCategoryId());

	}
	
					public void setBenefitMeasurementUnit(String obj){

		benefitBean.setBenefitMeasurementUnit(new String(obj));

	}

	public String getBenefitMeasurementUnit(){
		return StringUtil.getStringValue(
		benefitBean.getBenefitMeasurementUnit());

	}
	
				
	public void setBenefitMaxOccurance(String obj){

		benefitBean.setBenefitMaxOccurance(StringUtil.getDoubleValue(obj,0));

	}

	public String getBenefitMaxOccurance(){
		return StringUtil.getStringValue(
		benefitBean.getBenefitMaxOccurance());

	}
	
					public void setBenefitOccuranceUnit(String obj){

		benefitBean.setBenefitOccuranceUnit(new String(obj));

	}

	public String getBenefitOccuranceUnit(){
		return StringUtil.getStringValue(
		benefitBean.getBenefitOccuranceUnit());

	}
	
				
	public void setCreatedTime(String obj){

		benefitBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		benefitBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		benefitBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		benefitBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		benefitBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		benefitBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		benefitBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		benefitBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		benefitBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		benefitBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		benefitBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		benefitBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		benefitBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		benefitBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
