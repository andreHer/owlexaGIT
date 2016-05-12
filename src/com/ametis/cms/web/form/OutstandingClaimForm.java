
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * OutstandingClaim is a mapping of outstanding_claim Table.
*/
public class OutstandingClaimForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewOutstandingClaim = false;
	private OutstandingClaim outstandingClaimBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public OutstandingClaimForm()
    {
    	this.outstandingClaimBean = new OutstandingClaim();
    	this.isNewOutstandingClaim = true;
    }
    public OutstandingClaimForm (OutstandingClaim object){
		this.outstandingClaimBean = object;
    }
    public boolean isNewOutstandingClaim (){

    	return this.isNewOutstandingClaim;
    }
	public OutstandingClaim getOutstandingClaim (){
		return this.outstandingClaimBean ;
	}
	public void setOutstandingClaim (OutstandingClaim object){
		this.outstandingClaimBean = object;
	}

			
	public void setOutstandingClaimId(String obj){

		outstandingClaimBean.setOutstandingClaimId(StringUtil.getLongValue(obj,0));

	}

	public String getOutstandingClaimId(){
		return StringUtil.getStringValue(
		outstandingClaimBean.getOutstandingClaimId());

	}
	
				
	public void setClaimDate(String obj){

		outstandingClaimBean.setClaimDate(java.sql.Date.valueOf(obj));

	}

	public String getClaimDate(){
		return StringUtil.getStringValue(
		outstandingClaimBean.getClaimDate());

	}

	
				
	public void setCreatedTime(String obj){

		outstandingClaimBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		outstandingClaimBean.getCreatedTime());

	}

	
					public void setMemberNumber(String obj){

		outstandingClaimBean.setMemberNumber(new String(obj));

	}

	public String getMemberNumber(){
		return StringUtil.getStringValue(
		outstandingClaimBean.getMemberNumber());

	}
	
					public void setCreatedBy(String obj){

		outstandingClaimBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		outstandingClaimBean.getCreatedBy());

	}
	
					public void setProviderCode(String obj){

		outstandingClaimBean.setProviderCode(new String(obj));

	}

	public String getProviderCode(){
		return StringUtil.getStringValue(
		outstandingClaimBean.getProviderCode());

	}
	
				
	public void setClaimValue(String obj){

		outstandingClaimBean.setClaimValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getClaimValue(){
		return StringUtil.getStringValue(
		outstandingClaimBean.getClaimValue());

	}
	
				
	public void setModifiedTime(String obj){

		outstandingClaimBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		outstandingClaimBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		outstandingClaimBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		outstandingClaimBean.getModifiedBy());

	}
	
				
	public void setIsConverted(String obj){

		outstandingClaimBean.setIsConverted(StringUtil.getIntegerValue(obj,0));

	}

	public String getIsConverted(){
		return StringUtil.getStringValue(
		outstandingClaimBean.getIsConverted());

	}
	
				
	public void setClaimId(String obj){

		Claim claim = new Claim();
		claim.setClaimId(StringUtil.getIntegerValue(obj,0));
		outstandingClaimBean.setClaimId(claim);

	}

	public String getClaimId(){
		return StringUtil.getStringValue(
		outstandingClaimBean.getClaimId());

	}
	
					public void setMemberName(String obj){

		outstandingClaimBean.setMemberName(new String(obj));

	}

	public String getMemberName(){
		return StringUtil.getStringValue(
		outstandingClaimBean.getMemberName());

	}
	
					public void setServiceCode(String obj){

		outstandingClaimBean.setServiceCode(new String(obj));

	}

	public String getServiceCode(){
		return StringUtil.getStringValue(
		outstandingClaimBean.getServiceCode());

	}
	
				
	public void setCaseCategoryId(String obj){

		CaseCategory cc = new CaseCategory();
		cc.setCaseCategoryId(StringUtil.getIntegerValue(obj,0));
		outstandingClaimBean.setCaseCategoryId(cc);

	}

	public String getCaseCategoryId(){
		return StringUtil.getStringValue(
		outstandingClaimBean.getCaseCategoryId());

	}
	
				
	public void setConversionTime(String obj){

		outstandingClaimBean.setConversionTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getConversionTime(){
		return StringUtil.getStringValue(
		outstandingClaimBean.getConversionTime());

	}

	
					public void setCovertedBy(String obj){

		outstandingClaimBean.setCovertedBy(new String(obj));

	}

	public String getCovertedBy(){
		return StringUtil.getStringValue(
		outstandingClaimBean.getCovertedBy());

	}
	
					public void setDiagnosis1Code(String obj){

		outstandingClaimBean.setDiagnosis1Code(new String(obj));

	}

	public String getDiagnosis1Code(){
		return StringUtil.getStringValue(
		outstandingClaimBean.getDiagnosis1Code());

	}
	
					public void setDiagnosis2Code(String obj){

		outstandingClaimBean.setDiagnosis2Code(new String(obj));

	}

	public String getDiagnosis2Code(){
		return StringUtil.getStringValue(
		outstandingClaimBean.getDiagnosis2Code());

	}
	
					public void setDiagnosis3Code(String obj){

		outstandingClaimBean.setDiagnosis3Code(new String(obj));

	}

	public String getDiagnosis3Code(){
		return StringUtil.getStringValue(
		outstandingClaimBean.getDiagnosis3Code());

	}
	
					public void setDescription(String obj){

		outstandingClaimBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		outstandingClaimBean.getDescription());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
