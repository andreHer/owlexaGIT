
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * OustandingClaimItem is a mapping of oustanding_claim_item Table.
*/
public class OutstandingClaimItemForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewOustandingClaimItem = false;
	private OutstandingClaimItem oustandingClaimItemBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public OutstandingClaimItemForm()
    {
    	this.oustandingClaimItemBean = new OutstandingClaimItem();
    	this.isNewOustandingClaimItem = true;
    }
    public OutstandingClaimItemForm (OutstandingClaimItem object){
		this.oustandingClaimItemBean = object;
    }
    public boolean isNewOustandingClaimItem (){

    	return this.isNewOustandingClaimItem;
    }
	public OutstandingClaimItem getOustandingClaimItem (){
		return this.oustandingClaimItemBean ;
	}
	public void setOustandingClaimItem (OutstandingClaimItem object){
		this.oustandingClaimItemBean = object;
	}

			
	public void setOutstandingClaimItemId(String obj){

		oustandingClaimItemBean.setOutstandingClaimItemId(StringUtil.getLongValue(obj,0));

	}

	public String getOutstandingClaimItemId(){
		return StringUtil.getStringValue(
		oustandingClaimItemBean.getOutstandingClaimItemId());

	}
	
					public void setItemCode(String obj){

		oustandingClaimItemBean.setItemCode(new String(obj));

	}

	public String getItemCode(){
		return StringUtil.getStringValue(
		oustandingClaimItemBean.getItemCode());

	}
	
					public void setItemName(String obj){

		oustandingClaimItemBean.setItemName(new String(obj));

	}

	public String getItemName(){
		return StringUtil.getStringValue(
		oustandingClaimItemBean.getItemName());

	}
	
					public void setDescription(String obj){

		oustandingClaimItemBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		oustandingClaimItemBean.getDescription());

	}
	
				
	public void setClaimItemAmount(String obj){

		oustandingClaimItemBean.setClaimItemAmount(StringUtil.getDoubleValue(obj,0));

	}

	public String getClaimItemAmount(){
		return StringUtil.getStringValue(
		oustandingClaimItemBean.getClaimItemAmount());

	}
	
				
	public void setClaimItemCharge(String obj){

		oustandingClaimItemBean.setClaimItemCharge(StringUtil.getDoubleValue(obj,0));

	}

	public String getClaimItemCharge(){
		return StringUtil.getStringValue(
		oustandingClaimItemBean.getClaimItemCharge());

	}
	
				
	public void setClaimItemApprovedValue(String obj){

		oustandingClaimItemBean.setClaimItemApprovedValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getClaimItemApprovedValue(){
		return StringUtil.getStringValue(
		oustandingClaimItemBean.getClaimItemApprovedValue());

	}
	
				
	public void setCreatedTime(String obj){

		oustandingClaimItemBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		oustandingClaimItemBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		oustandingClaimItemBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		oustandingClaimItemBean.getCreatedBy());

	}
	
				
	public void setOutstandingClaimId(String obj){

		OutstandingClaim outstanding = new OutstandingClaim();
		outstanding.setOutstandingClaimId(StringUtil.getLongValue(obj,0));
		oustandingClaimItemBean.setOutstandingClaimId(outstanding);

	}

	public String getOutstandingClaimId(){
		return StringUtil.getStringValue(
		oustandingClaimItemBean.getOutstandingClaimId());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
