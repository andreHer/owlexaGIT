
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.Item;
import com.ametis.cms.datamodel.ItemCategory;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * Item is a mapping of item Table.
*/
public class ItemForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewItem = false;
	private Item itemBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ItemForm()
    {
    	this.itemBean = new Item();
    	this.isNewItem = true;
    }
    public ItemForm (Item object){
		this.itemBean = object;
    }
    public boolean isNewItem (){

    	return this.isNewItem;
    }
	public Item getItem (){
		return this.itemBean ;
	}
	public void setItem (Item object){
		this.itemBean = object;
	}

			
	public void setItemId(String obj){

		itemBean.setItemId(StringUtil.getIntegerValue(obj,0));

	}

	public String getItemId(){
		return StringUtil.getStringValue(
		itemBean.getItemId());

	}
	
								public void setItemName(String obj){

		itemBean.setItemName(new String(obj));

	}

	public String getItemName(){
		return StringUtil.getStringValue(
		itemBean.getItemName());

	}
	
					public void setItemCode(String obj){

		itemBean.setItemCode(new String(obj));

	}

	public String getItemCode(){
		return StringUtil.getStringValue(
		itemBean.getItemCode());

	}
	public void setItemEDCName(String obj){

		itemBean.setItemEDCName(new String(obj));

	}

	public String getItemEDCName(){
		return StringUtil.getStringValue(
		itemBean.getItemEDCName());

	}
	public void setItemAlternateCode(String obj){

		itemBean.setItemAlternateCode(new String(obj));

	}

	public String getItemAlternateCode(){
		return StringUtil.getStringValue(
		itemBean.getItemAlternateCode());

	}
	
	public void setItemEDCCode(String obj){

		itemBean.setItemEDCCode(new String(obj));

	}

	public String getItemEDCCode(){
		return StringUtil.getStringValue(
		itemBean.getItemEDCCode());

	}
	
					public void setItemDescription(String obj){

		itemBean.setItemDescription(new String(obj));

	}

	public String getItemDescription(){
		return StringUtil.getStringValue(
		itemBean.getItemDescription());

	}
	
				
	public void setItemDefaultValue(String obj){

		itemBean.setItemDefaultValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getItemDefaultValue(){
		return StringUtil.getStringValue(
		itemBean.getItemDefaultValue());

	}
	
				
	public void setCreatedTime(String obj){

		itemBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		itemBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		itemBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		itemBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		itemBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		itemBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		itemBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		itemBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		itemBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		itemBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		itemBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		itemBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		itemBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		itemBean.getDeletedStatus());

	}
	
	public void setMemberMovementType(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			itemBean.setMemberMovementType(obj);
		}

	}

	public String getMemberMovementType(){
		
		return StringUtil.getStringValue(
		itemBean.getMemberMovementType());

	}	

	// foreign affairs
	
	

	
	public void setItemCategoryId(ItemCategory obj){
		
		itemBean.setItemCategoryId(obj);

	}

	public ItemCategory getItemCategoryId(){
		return itemBean.getItemCategoryId();

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
