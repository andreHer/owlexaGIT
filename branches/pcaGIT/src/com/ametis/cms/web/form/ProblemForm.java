
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * Problem is a mapping of problem Table.
*/
public class ProblemForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewProblem = false;
	private Problem problemBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ProblemForm()
    {
    	this.problemBean = new Problem();
    	this.isNewProblem = true;
    }
    public ProblemForm (Problem object){
		this.problemBean = object;
    }
    public boolean isNewProblem (){

    	return this.isNewProblem;
    }
	public Problem getProblem (){
		return this.problemBean ;
	}
	public void setProblem (Problem object){
		this.problemBean = object;
	}

			
	public void setProblemId(String obj){

		problemBean.setProblemId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProblemId(){
		return StringUtil.getStringValue(
		problemBean.getProblemId());

	}
	
				
	public void setProblemTime(String obj){

		problemBean.setProblemTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getProblemTime(){
		return StringUtil.getStringValue(
		problemBean.getProblemTime());

	}

	
				
	public void setProblemResolutionTime(String obj){

		problemBean.setProblemResolutionTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getProblemResolutionTime(){
		return StringUtil.getStringValue(
		problemBean.getProblemResolutionTime());

	}

	
				
	public void setClaimId(String obj){

		problemBean.setClaimId(StringUtil.getIntegerValue(obj,0));

	}

	public String getClaimId(){
		return StringUtil.getStringValue(
		problemBean.getClaimId());

	}
	
				
	public void setBatchClaimId(String obj){

		problemBean.setBatchClaimId(StringUtil.getIntegerValue(obj,0));

	}

	public String getBatchClaimId(){
		return StringUtil.getStringValue(
		problemBean.getBatchClaimId());

	}
	
				
	public void setClaimItemId(String obj){

		problemBean.setClaimItemId(StringUtil.getIntegerValue(obj,0));

	}

	public String getClaimItemId(){
		return StringUtil.getStringValue(
		problemBean.getClaimItemId());

	}
	
				
	public void setPaymentId(String obj){

		problemBean.setPaymentId(StringUtil.getIntegerValue(obj,0));

	}

	public String getPaymentId(){
		return StringUtil.getStringValue(
		problemBean.getPaymentId());

	}
	
					public void setCreatedBy(String obj){

		problemBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		problemBean.getCreatedBy());

	}
	
					public void setResolvedBy(String obj){

		problemBean.setResolvedBy(new String(obj));

	}

	public String getResolvedBy(){
		return StringUtil.getStringValue(
		problemBean.getResolvedBy());

	}
	
					public void setDescription(String obj){

		problemBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		problemBean.getDescription());

	}
	
				
	public void setProblemType(String obj){

		problemBean.setProblemType(StringUtil.getIntegerValue(obj,0));

	}

	public String getProblemType(){
		return StringUtil.getStringValue(
		problemBean.getProblemType());

	}
	
				
	public void setProblemSubject(String obj){

		problemBean.setProblemSubject(StringUtil.getIntegerValue(obj,0));

	}

	public String getProblemSubject(){
		return StringUtil.getStringValue(
		problemBean.getProblemSubject());

	}
	
				
	public void setProblemStatus(String obj){

		problemBean.setProblemStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getProblemStatus(){
		return StringUtil.getStringValue(
		problemBean.getProblemStatus());

	}
	
				
	public void setCreatedTime(String obj){

		problemBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		problemBean.getCreatedTime());

	}

	
				
	public void setModifiedTime(String obj){

		problemBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		problemBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		problemBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		problemBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		problemBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		problemBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		problemBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		problemBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		problemBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		problemBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
