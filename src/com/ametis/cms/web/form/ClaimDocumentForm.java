
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * ClaimDocument is a mapping of claim_document Table.
*/
public class ClaimDocumentForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewClaimDocument = false;
	private ClaimDocument claimDocumentBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ClaimDocumentForm()
    {
    	this.claimDocumentBean = new ClaimDocument();
    	this.isNewClaimDocument = true;
    }
    public ClaimDocumentForm (ClaimDocument object){
		this.claimDocumentBean = object;
    }
    public boolean isNewClaimDocument (){

    	return this.isNewClaimDocument;
    }
	public ClaimDocument getClaimDocument (){
		return this.claimDocumentBean ;
	}
	public void setClaimDocument (ClaimDocument object){
		this.claimDocumentBean = object;
	}

			
	public void setId(String obj){

		claimDocumentBean.setId(StringUtil.getIntegerValue(obj,0));

	}

	public String getId(){
		return StringUtil.getStringValue(
		claimDocumentBean.getId());

	}
	
				
	public void setClaimId(String obj){

		claimDocumentBean.setClaimId(StringUtil.getLongValue(obj,0));

	}

	public String getClaimId(){
		return StringUtil.getStringValue(
		claimDocumentBean.getClaimId());

	}
	
					public void setDocumentName(String obj){

		claimDocumentBean.setDocumentName(new String(obj));

	}

	public String getDocumentName(){
		return StringUtil.getStringValue(
		claimDocumentBean.getDocumentName());

	}
	
					public void setDescription(String obj){

		claimDocumentBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		claimDocumentBean.getDescription());

	}
	
				
	public void setTipe(String obj){

		claimDocumentBean.setTipe(StringUtil.getIntegerValue(obj,0));

	}

	public String getTipe(){
		return StringUtil.getStringValue(
		claimDocumentBean.getTipe());

	}
	
					public void setDocumentUrl(String obj){

		claimDocumentBean.setDocumentUrl(new String(obj));

	}

	public String getDocumentUrl(){
		return StringUtil.getStringValue(
		claimDocumentBean.getDocumentUrl());

	}
	
				
	public void setOrder(String obj){

		claimDocumentBean.setOrder(StringUtil.getIntegerValue(obj,0));

	}

	public String getOrder(){
		return StringUtil.getStringValue(
		claimDocumentBean.getOrder());

	}
	
				
	public void setCreatedTime(String obj){

		claimDocumentBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		claimDocumentBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		claimDocumentBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		claimDocumentBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		claimDocumentBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		claimDocumentBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		claimDocumentBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		claimDocumentBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		claimDocumentBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		claimDocumentBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		claimDocumentBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		claimDocumentBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		claimDocumentBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		claimDocumentBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 

}
