
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * Branch is a mapping of branch Table.
*/
public class BranchForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewBranch = false;
	private Branch branchBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public BranchForm()
    {
    	this.branchBean = new Branch();
    	this.isNewBranch = true;
    }
    public BranchForm (Branch object){
		this.branchBean = object;
    }
    public boolean isNewBranch (){

    	return this.isNewBranch;
    }
	public Branch getBranch (){
		return this.branchBean ;
	}
	public void setBranch (Branch object){
		this.branchBean = object;
	}

			
	public void setBranchId(String obj){

		branchBean.setBranchId(StringUtil.getIntegerValue(obj,0));

	}

	public String getBranchId(){
		return StringUtil.getStringValue(
		branchBean.getBranchId());

	}
	
					public void setBranchName(String obj){

		branchBean.setBranchName(new String(obj));

	}

	public String getBranchName(){
		return StringUtil.getStringValue(
		branchBean.getBranchName());

	}
	
					public void setAddress(String obj){

		branchBean.setAddress(new String(obj));

	}

	public String getAddress(){
		return StringUtil.getStringValue(
		branchBean.getAddress());

	}
	
					public void setCountry(String obj){

		branchBean.setCountry(new String(obj));

	}

	public String getCountry(){
		return StringUtil.getStringValue(
		branchBean.getCountry());

	}
	
					public void setCity(String obj){

		branchBean.setCity(new String(obj));

	}

	public String getCity(){
		return StringUtil.getStringValue(
		branchBean.getCity());

	}
	
					public void setProvince(String obj){

		branchBean.setProvince(new String(obj));

	}

	public String getProvince(){
		return StringUtil.getStringValue(
		branchBean.getProvince());

	}
	
				
	public void setStatusId(String obj){

		branchBean.setStatusId(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatusId(){
		return StringUtil.getStringValue(
		branchBean.getStatusId());

	}
	
				
	public void setCreatedTime(String obj){

		branchBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		branchBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		branchBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		branchBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		branchBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		branchBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		branchBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		branchBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		branchBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		branchBean.getDeletedStatus());

	}
	
				
	public void setModifiedTime(String obj){

		branchBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		branchBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		branchBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		branchBean.getModifiedBy());

	}
	
					public void setBranchHead(String obj){

		branchBean.setBranchHead(new String(obj));

	}

	public String getBranchHead(){
		return StringUtil.getStringValue(
		branchBean.getBranchHead());

	}
	
					public void setBranchFinanceHead(String obj){

		branchBean.setBranchFinanceHead(new String(obj));

	}

	public String getBranchFinanceHead(){
		return StringUtil.getStringValue(
		branchBean.getBranchFinanceHead());

	}
	
					public void setBranchActuaryHead(String obj){

		branchBean.setBranchActuaryHead(new String(obj));

	}

	public String getBranchActuaryHead(){
		return StringUtil.getStringValue(
		branchBean.getBranchActuaryHead());

	}
	
					public void setBranchMarketingHead(String obj){

		branchBean.setBranchMarketingHead(new String(obj));

	}

	public String getBranchMarketingHead(){
		return StringUtil.getStringValue(
		branchBean.getBranchMarketingHead());

	}
	
					public void setBranchClaimHead(String obj){

		branchBean.setBranchClaimHead(new String(obj));

	}

	public String getBranchClaimHead(){
		return StringUtil.getStringValue(
		branchBean.getBranchClaimHead());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
