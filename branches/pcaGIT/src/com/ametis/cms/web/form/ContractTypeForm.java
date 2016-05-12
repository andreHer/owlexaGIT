
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * ContractType is a mapping of contract_type Table.
*/
public class ContractTypeForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewContractType = false;
	private ContractType contractTypeBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ContractTypeForm()
    {
    	this.contractTypeBean = new ContractType();
    	this.isNewContractType = true;
    }
    public ContractTypeForm (ContractType object){
		this.contractTypeBean = object;
    }
    public boolean isNewContractType (){

    	return this.isNewContractType;
    }
	public ContractType getContractType (){
		return this.contractTypeBean ;
	}
	public void setContractType (ContractType object){
		this.contractTypeBean = object;
	}

			
	public void setContractTypeId(String obj){

		contractTypeBean.setContractTypeId(StringUtil.getIntegerValue(obj,0));

	}

	public String getContractTypeId(){
		return StringUtil.getStringValue(
		contractTypeBean.getContractTypeId());

	}
	
					public void setContractTypeName(String obj){

		contractTypeBean.setContractTypeName(new String(obj));

	}

	public String getContractTypeName(){
		return StringUtil.getStringValue(
		contractTypeBean.getContractTypeName());

	}
	
					public void setContractTypeCode(String obj){

		contractTypeBean.setContractTypeCode(new String(obj));

	}

	public String getContractTypeCode(){
		return StringUtil.getStringValue(
		contractTypeBean.getContractTypeCode());

	}
	
				
	public void setContractChargeType(String obj){

		contractTypeBean.setContractChargeType(StringUtil.getIntegerValue(obj,0));

	}

	public String getContractChargeType(){
		return StringUtil.getStringValue(
		contractTypeBean.getContractChargeType());

	}
	
				
	public void setContractChargePerMember(String obj){

		contractTypeBean.setContractChargePerMember(StringUtil.getDoubleValue(obj,0));

	}

	public String getContractChargePerMember(){
		return StringUtil.getStringValue(
		contractTypeBean.getContractChargePerMember());

	}
	
				
	public void setCreatedTime(String obj){

		contractTypeBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		contractTypeBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		contractTypeBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		contractTypeBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		contractTypeBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		contractTypeBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		contractTypeBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		contractTypeBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		contractTypeBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		contractTypeBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		contractTypeBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		contractTypeBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		contractTypeBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		contractTypeBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
