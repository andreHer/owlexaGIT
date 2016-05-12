
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * MemberLimitLayer is a mapping of member_limit_layer Table.
*/
public class MemberLimitLayerForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewMemberLimitLayer = false;
	private MemberLimitLayer memberLimitLayerBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public MemberLimitLayerForm()
    {
    	this.memberLimitLayerBean = new MemberLimitLayer();
    	this.isNewMemberLimitLayer = true;
    }
    public MemberLimitLayerForm (MemberLimitLayer object){
		this.memberLimitLayerBean = object;
    }
    public boolean isNewMemberLimitLayer (){

    	return this.isNewMemberLimitLayer;
    }
	public MemberLimitLayer getMemberLimitLayer (){
		return this.memberLimitLayerBean ;
	}
	public void setMemberLimitLayer (MemberLimitLayer object){
		this.memberLimitLayerBean = object;
	}

			
	public void setMemberLimitLayerId(String obj){

		memberLimitLayerBean.setMemberLimitLayerId(StringUtil.getIntegerValue(obj,0));

	}

	public String getMemberLimitLayerId(){
		return StringUtil.getStringValue(
		memberLimitLayerBean.getMemberLimitLayerId());

	}
	
													
	public void setAnnualLimit(String obj){

		memberLimitLayerBean.setAnnualLimit(StringUtil.getDoubleValue(obj,0));

	}

	public String getAnnualLimit(){
		return StringUtil.getStringValue(
		memberLimitLayerBean.getAnnualLimit());

	}
	
				
	public void setActualBenefitLimit(String obj){

		memberLimitLayerBean.setActualBenefitLimit(StringUtil.getDoubleValue(obj,0));

	}

	public String getActualBenefitLimit(){
		return StringUtil.getStringValue(
		memberLimitLayerBean.getActualBenefitLimit());

	}
	
				
	public void setBenefitUsage(String obj){

		memberLimitLayerBean.setBenefitUsage(StringUtil.getDoubleValue(obj,0));

	}

	public String getBenefitUsage(){
		return StringUtil.getStringValue(
		memberLimitLayerBean.getBenefitUsage());

	}
	
				
	public void setCoSharePercentage(String obj){

		memberLimitLayerBean.setCoSharePercentage(StringUtil.getDoubleValue(obj,0));

	}

	public String getCoSharePercentage(){
		return StringUtil.getStringValue(
		memberLimitLayerBean.getCoSharePercentage());

	}
	
				
	public void setCoShareAmount(String obj){

		memberLimitLayerBean.setCoShareAmount(StringUtil.getDoubleValue(obj,0));

	}

	public String getCoShareAmount(){
		return StringUtil.getStringValue(
		memberLimitLayerBean.getCoShareAmount());

	}
	
				
	public void setStatus(String obj){

		memberLimitLayerBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		memberLimitLayerBean.getStatus());

	}
	
				
	public void setCreatedTime(String obj){

		memberLimitLayerBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		memberLimitLayerBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		memberLimitLayerBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		memberLimitLayerBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		memberLimitLayerBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		memberLimitLayerBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		memberLimitLayerBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		memberLimitLayerBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		memberLimitLayerBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		memberLimitLayerBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		memberLimitLayerBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		memberLimitLayerBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		memberLimitLayerBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		memberLimitLayerBean.getDeletedStatus());

	}
	
		

	// foreign affairs
	
	

	
	public void setProductLimitLayerId(String obj){
		ProductLayerLimit fk = new ProductLayerLimit();
		fk.setProductLayerLimitId(StringUtil.getIntegerValue(obj,0));
		memberLimitLayerBean.setProductLimitLayerId(fk);

	}

	public String getProductLimitLayerId(){
		return StringUtil.getStringValue(
		memberLimitLayerBean.getProductLimitLayerId().getProductLayerLimitId());

	}
	//---
	
	

	
	public void setMemberId(String obj){
		Member fk = new Member();
		fk.setMemberId(StringUtil.getIntegerValue(obj,0));
		memberLimitLayerBean.setMemberId(fk);

	}

	public String getMemberId(){
		return StringUtil.getStringValue(
		memberLimitLayerBean.getMemberId().getMemberId());

	}
	//---
	
	

	
	public void setProductId(String obj){
		Product fk = new Product();
		fk.setProductId(StringUtil.getIntegerValue(obj,0));
		memberLimitLayerBean.setProductId(fk);

	}

	public String getProductId(){
		return StringUtil.getStringValue(
		memberLimitLayerBean.getProductId().getProductId());

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
