
package com.ametis.cms.web.form;

import javax.persistence.Column;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * ClientContractItem is a mapping of client_contract_item Table.
*/
public class ClientContractItemForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewClientContractItem = false;
	private ClientContractItem clientContractItemBean ;
	private String contractNumber;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ClientContractItemForm()
    {
    	this.clientContractItemBean = new ClientContractItem();
    	this.isNewClientContractItem = true;
    }
    public ClientContractItemForm (ClientContractItem object){
		this.clientContractItemBean = object;
    }
    public boolean isNewClientContractItem (){

    	return this.isNewClientContractItem;
    }
	public ClientContractItem getClientContractItem (){
		return this.clientContractItemBean ;
	}
	public void setClientContractItem (ClientContractItem object){
		this.clientContractItemBean = object;
	}

			
	public void setClientContractItemId(String obj){

		clientContractItemBean.setClientContractItemId(StringUtil.getIntegerValue(obj,0));

	}

	public String getClientContractItemId(){
		return StringUtil.getStringValue(
		clientContractItemBean.getClientContractItemId());

	}
	
										
	public void setItemPrice(String obj){

		clientContractItemBean.setItemPrice(StringUtil.getDoubleValue(obj,0));

	}

	public String getItemPrice(){
		return StringUtil.getStringValue(
		clientContractItemBean.getItemPrice());

	}
	
				
	public void setItemPriceReference(String obj){

		clientContractItemBean.setItemPriceReference(StringUtil.getDoubleValue(obj,0));

	}

	public String getItemPriceReference(){
		return StringUtil.getStringValue(
		clientContractItemBean.getItemPriceReference());

	}
	
					public void setDescription(String obj){

		clientContractItemBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		clientContractItemBean.getDescription());

	}
	
				
	public void setStatus(String obj){

		clientContractItemBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		clientContractItemBean.getStatus());

	}
	
				


	
	public void setCaseCategoryId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			CaseCategory cc = new CaseCategory();
			cc.setCaseCategoryId(Integer.valueOf(obj));
			clientContractItemBean.setCaseCategoryId(cc);
		}
	}

	public String getCaseCategoryId(){
		String result = "";
		
		if (clientContractItemBean.getCaseCategoryId() != null){
			result = clientContractItemBean.getCaseCategoryId().getCaseCategoryId().toString();
		}
		return result;
		
	}
	
				
	public void setClaimType(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			
			clientContractItemBean.setClaimType(Integer.valueOf(obj));
		}
	}

	public String getClaimType(){
		return StringUtil.getStringValue(
		clientContractItemBean.getClaimType());
	}
	
	public void setContractUnit(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			clientContractItemBean.setContractUnitType(new Integer(obj));
		}
	}

	public String getContractUnit(){
		return StringUtil.getStringValue(
		clientContractItemBean.getContractUnitType());
	}
	
				
	
		

	// foreign affairs
	
	

	
	public void setClientContractId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			ClientContract fk = new ClientContract();
			fk.setClientContractId(StringUtil.getIntegerValue(obj,0));
			clientContractItemBean.setClientContractId(fk);
		}

	}

	public String getClientContractId(){
		String result = "";
		
		if (clientContractItemBean.getClientContractId() != null){
			result =StringUtil.getStringValue(
					clientContractItemBean.getClientContractId().getClientContractId()); 
		}
		
		return result;
		

	}
	//---
	
	

	
	public void setItemId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Item fk = new Item();
			fk.setItemId(StringUtil.getIntegerValue(obj,0));
			clientContractItemBean.setItemId(fk);
		}

	}

	public String getItemId(){
		String result = "";
		if (clientContractItemBean.getItemId() != null){
			result = StringUtil.getStringValue(
					clientContractItemBean.getItemId().getItemId());
		}
		
		return result;
		

	}
	public String getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	
	
	public String getVolumeStart() {
		return StringUtil.getStringValue(this.clientContractItemBean.getVolumeStart());
	}
	public void setVolumeStart(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			this.clientContractItemBean.setVolumeStart(Integer.valueOf(obj));
		}
	}
	
	public String getVolumeEnd() {
		return StringUtil.getStringValue(this.clientContractItemBean.getVolumeEnd());
	}
	public void setVolumeEnd(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			this.clientContractItemBean.setVolumeEnd(Integer.valueOf(obj));
		}
	}
	
	public String getDiscountPercentage() {
		return StringUtil.getStringValue(this.clientContractItemBean.getDiscountPercentage());
	}
	public void setDiscountPercentage(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			this.clientContractItemBean.setDiscountPercentage(Double.valueOf(obj));
		}
	}
	
	public String getDiscountAmountPerQuantity() {
		return StringUtil.getStringValue(this.clientContractItemBean.getDiscountAmountPerQuantity());
	}
	public void setDiscountAmountPerQuantity(String obj) {
		if (obj!=null && !obj.equalsIgnoreCase("")){
			this.clientContractItemBean.setDiscountAmountPerQuantity(Double.valueOf(obj));
		}
	}
	public String getClaimStatusProcess() {
		return StringUtil.getStringValue(clientContractItemBean.getClaimStatusProcess());
	}
	public void setClaimStatusProcess(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			clientContractItemBean.setClaimStatusProcess(Integer.valueOf(obj));
		}
	}
}

