
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.BenefitUsageType;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * BenefitUsageType is a mapping of benefit_usage_type Table.
*/
public class BenefitUsageTypeForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewBenefitUsageType = false;
	private BenefitUsageType benefitUsageTypeBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public BenefitUsageTypeForm()
    {
    	this.benefitUsageTypeBean = new BenefitUsageType();
    	this.isNewBenefitUsageType = true;
    }
    public BenefitUsageTypeForm (BenefitUsageType object){
		this.benefitUsageTypeBean = object;
    }
    public boolean isNewBenefitUsageType (){

    	return this.isNewBenefitUsageType;
    }
	public BenefitUsageType getBenefitUsageType (){
		return this.benefitUsageTypeBean ;
	}
	public void setBenefitUsageType (BenefitUsageType object){
		this.benefitUsageTypeBean = object;
	}

			
	public void setBenefitUsageTypeId(String obj){

		benefitUsageTypeBean.setBenefitUsageTypeId(StringUtil.getIntegerValue(obj,0));

	}

	public String getBenefitUsageTypeId(){
		return StringUtil.getStringValue(
		benefitUsageTypeBean.getBenefitUsageTypeId());

	}
	
					public void setBenefitUsage(String obj){

		benefitUsageTypeBean.setBenefitUsage(new String(obj));

	}

	public String getBenefitUsage(){
		return StringUtil.getStringValue(
		benefitUsageTypeBean.getBenefitUsage());

	}
	
					public void setDescription(String obj){

		benefitUsageTypeBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		benefitUsageTypeBean.getDescription());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
