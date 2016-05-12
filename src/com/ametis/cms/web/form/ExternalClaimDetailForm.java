
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * ExternalClaimDetail is a mapping of external_claim_detail Table.
*/
public class ExternalClaimDetailForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewExternalClaimDetail = false;
	private ExternalClaimDetail externalClaimDetailBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ExternalClaimDetailForm()
    {
    	this.externalClaimDetailBean = new ExternalClaimDetail();
    	this.isNewExternalClaimDetail = true;
    }
    public ExternalClaimDetailForm (ExternalClaimDetail object){
		this.externalClaimDetailBean = object;
    }
    public boolean isNewExternalClaimDetail (){

    	return this.isNewExternalClaimDetail;
    }
	public ExternalClaimDetail getExternalClaimDetail (){
		return this.externalClaimDetailBean ;
	}
	public void setExternalClaimDetail (ExternalClaimDetail object){
		this.externalClaimDetailBean = object;
	}

			
	public void setId(String obj){

		externalClaimDetailBean.setId(StringUtil.getIntegerValue(obj,0));

	}

	public String getId(){
		return StringUtil.getStringValue(
		externalClaimDetailBean.getId());

	}
	
					public void setClaimNumber(String obj){

		externalClaimDetailBean.setClaimNumber(new String(obj));

	}

	public String getClaimNumber(){
		return StringUtil.getStringValue(
		externalClaimDetailBean.getClaimNumber());

	}
	
					public void setItemCode(String obj){

		externalClaimDetailBean.setItemCode(new String(obj));

	}

	public String getItemCode(){
		return StringUtil.getStringValue(
		externalClaimDetailBean.getItemCode());

	}
	
					public void setItemName(String obj){

		externalClaimDetailBean.setItemName(new String(obj));

	}

	public String getItemName(){
		return StringUtil.getStringValue(
		externalClaimDetailBean.getItemName());

	}
	
				
	public void setChargeValue(String obj){

		externalClaimDetailBean.setChargeValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getChargeValue(){
		return StringUtil.getStringValue(
		externalClaimDetailBean.getChargeValue());

	}
	
				
	public void setApprovedValue(String obj){

		externalClaimDetailBean.setApprovedValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getApprovedValue(){
		return StringUtil.getStringValue(
		externalClaimDetailBean.getApprovedValue());

	}
	
				
	public void setExcessValue(String obj){

		externalClaimDetailBean.setExcessValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getExcessValue(){
		return StringUtil.getStringValue(
		externalClaimDetailBean.getExcessValue());

	}
	
					public void setStatus(String obj){

		externalClaimDetailBean.setStatus(new String(obj));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		externalClaimDetailBean.getStatus());

	}
	
					public void setCreatedBy(String obj){

		externalClaimDetailBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		externalClaimDetailBean.getCreatedBy());

	}
	
				
	public void setCreatedTime(String obj){

		externalClaimDetailBean.setCreatedTime(java.sql.Date.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		externalClaimDetailBean.getCreatedTime());

	}

	
				
	public void setModifiedTime(String obj){

		externalClaimDetailBean.setModifiedTime(java.sql.Date.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		externalClaimDetailBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		externalClaimDetailBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		externalClaimDetailBean.getModifiedBy());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
