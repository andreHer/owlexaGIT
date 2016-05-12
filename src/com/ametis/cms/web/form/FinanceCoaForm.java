
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * FinanceCoa is a mapping of finance_coa Table.
*/
public class FinanceCoaForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewFinanceCoa = false;
	private FinanceCoa financeCoaBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public FinanceCoaForm()
    {
    	this.financeCoaBean = new FinanceCoa();
    	this.isNewFinanceCoa = true;
    }
    public FinanceCoaForm (FinanceCoa object){
		this.financeCoaBean = object;
    }
    public boolean isNewFinanceCoa (){

    	return this.isNewFinanceCoa;
    }
	public FinanceCoa getFinanceCoa (){
		return this.financeCoaBean ;
	}
	public void setFinanceCoa (FinanceCoa object){
		this.financeCoaBean = object;
	}

			
	public void setId(String obj){

		financeCoaBean.setId(StringUtil.getIntegerValue(obj,0));

	}

	public String getId(){
		return StringUtil.getStringValue(
		financeCoaBean.getId());

	}
	
					public void setCoaName(String obj){

		financeCoaBean.setCoaName(new String(obj));

	}

	public String getCoaName(){
		return StringUtil.getStringValue(
		financeCoaBean.getCoaName());

	}
	
					public void setCoaCode(String obj){

		financeCoaBean.setCoaCode(new String(obj));

	}

	public String getCoaCode(){
		return StringUtil.getStringValue(
		financeCoaBean.getCoaCode());

	}
	
					public void setDescription(String obj){

		financeCoaBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		financeCoaBean.getDescription());

	}
	
				
	public void setCreatedTime(String obj){

		financeCoaBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		financeCoaBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		financeCoaBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		financeCoaBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		financeCoaBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		financeCoaBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		financeCoaBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		financeCoaBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		financeCoaBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		financeCoaBean.getDeletedStatus());

	}
	
					public void setDeletedBy(String obj){

		financeCoaBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		financeCoaBean.getDeletedBy());

	}
	
				
	public void setDeletedTime(String obj){

		financeCoaBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		financeCoaBean.getDeletedTime());

	}

	
				
	public void setStatus(String obj){

		financeCoaBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		financeCoaBean.getStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 

}
