
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.DiscountUsageType;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * DiscountUsageType is a mapping of discount_usage_type Table.
*/
public class DiscountUsageTypeForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewDiscountUsageType = false;
	private DiscountUsageType discountUsageTypeBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public DiscountUsageTypeForm()
    {
    	this.discountUsageTypeBean = new DiscountUsageType();
    	this.isNewDiscountUsageType = true;
    }
    public DiscountUsageTypeForm (DiscountUsageType object){
		this.discountUsageTypeBean = object;
    }
    public boolean isNewDiscountUsageType (){

    	return this.isNewDiscountUsageType;
    }
	public DiscountUsageType getDiscountUsageType (){
		return this.discountUsageTypeBean ;
	}
	public void setDiscountUsageType (DiscountUsageType object){
		this.discountUsageTypeBean = object;
	}

			
	public void setDiscountUsageTypeId(String obj){

		discountUsageTypeBean.setDiscountUsageTypeId(StringUtil.getIntegerValue(obj,0));

	}

	public String getDiscountUsageTypeId(){
		return StringUtil.getStringValue(
		discountUsageTypeBean.getDiscountUsageTypeId());

	}
	
					public void setDiscountUsageTypeName(String obj){

		discountUsageTypeBean.setDiscountUsageTypeName(new String(obj));

	}

	public String getDiscountUsageTypeName(){
		return StringUtil.getStringValue(
		discountUsageTypeBean.getDiscountUsageTypeName());

	}
	
					public void setDescription(String obj){

		discountUsageTypeBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		discountUsageTypeBean.getDescription());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
