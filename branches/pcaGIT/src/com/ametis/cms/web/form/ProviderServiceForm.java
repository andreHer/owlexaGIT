
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * ProviderService is a mapping of provider_service Table.
*/
public class ProviderServiceForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewProviderService = false;
	private ProviderService providerServiceBean ;
	private String providerName;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ProviderServiceForm()
    {
    	this.providerServiceBean = new ProviderService();
    	this.isNewProviderService = true;
    }
    public ProviderServiceForm (ProviderService object){
		this.providerServiceBean = object;
    }
    public boolean isNewProviderService (){

    	return this.isNewProviderService;
    }
	public ProviderService getProviderService (){
		return this.providerServiceBean ;
	}
	public void setProviderService (ProviderService object){
		this.providerServiceBean = object;
	}

			
	public void setProviderServiceId(String obj){

		providerServiceBean.setProviderServiceId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProviderServiceId(){
		return StringUtil.getStringValue(
		providerServiceBean.getProviderServiceId());

	}
	
				
	public void setDiscount(String obj){

		providerServiceBean.setDiscount(StringUtil.getDoubleValue(obj,0));

	}

	public String getDiscount(){
		return StringUtil.getStringValue(
		providerServiceBean.getDiscount());

	}
	public void setDiscountType(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			providerServiceBean.setDiscountType(Integer.valueOf(obj));
		}

	}

	public String getDiscountType(){
		String result = "";
		
		if (providerServiceBean.getDiscountType() != null){
			result = StringUtil.getStringValue(
					providerServiceBean.getDiscountType());
		}
		return result;
		

	}
	
	public void setCaseCategoryId(String obj){
		CaseCategory fk = new CaseCategory();
		fk.setCaseCategoryId(StringUtil.getIntegerValue(obj,0));
		providerServiceBean.setCaseCategoryId(fk);

	}

	public String getCaseCategoryId(){
		String result = "";
		
		if (providerServiceBean.getCaseCategoryId() != null){
			result = StringUtil.getStringValue(
				providerServiceBean.getCaseCategoryId().getCaseCategoryId());
		}
		
		return result;
		

	}
	//---
	
	

	
	public void setProviderId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Provider fk = new Provider();
			fk.setProviderId(StringUtil.getIntegerValue(obj,0));
			providerServiceBean.setProviderId(fk);
		}

	}

	public String getProviderId(){
		String result = "";
		
		if (providerServiceBean.getProviderId() != null){
			result = StringUtil.getStringValue(
				providerServiceBean.getProviderId().getProviderId());
		}
		return result;
		

	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	
}
