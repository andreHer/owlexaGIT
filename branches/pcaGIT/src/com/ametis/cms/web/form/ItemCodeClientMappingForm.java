
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * ItemCodeClientMapping is a mapping of item_code_client_mapping Table.
*/
public class ItemCodeClientMappingForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewItemCodeClientMapping = false;
	private ItemCodeClientMapping itemCodeClientMappingBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ItemCodeClientMappingForm()
    {
    	this.itemCodeClientMappingBean = new ItemCodeClientMapping();
    	this.isNewItemCodeClientMapping = true;
    }
    public ItemCodeClientMappingForm (ItemCodeClientMapping object){
		this.itemCodeClientMappingBean = object;
    }
    public boolean isNewItemCodeClientMapping (){

    	return this.isNewItemCodeClientMapping;
    }
	public ItemCodeClientMapping getItemCodeClientMapping (){
		return this.itemCodeClientMappingBean ;
	}
	public void setItemCodeClientMapping (ItemCodeClientMapping object){
		this.itemCodeClientMappingBean = object;
	}

			
	public void setItemCodeClientMappingId(String obj){

		itemCodeClientMappingBean.setItemCodeClientMappingId(StringUtil.getIntegerValue(obj,0));

	}

	public String getItemCodeClientMappingId(){
		return StringUtil.getStringValue(
		itemCodeClientMappingBean.getItemCodeClientMappingId());

	}
	
				
	public void setClientId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Client client = new Client();
			client.setClientId(Integer.valueOf(obj));
			itemCodeClientMappingBean.setClientId(client);
		}

	}

	public String getClientId(){
		String result = "";
		
		if (itemCodeClientMappingBean.getClientId() != null){
			result = StringUtil.getStringValue(
					itemCodeClientMappingBean.getClientId().toString());
		}
		return result;
		

	}
	
					public void setClientItemCode(String obj){

		itemCodeClientMappingBean.setClientItemCode(new String(obj));

	}

	public String getClientItemCode(){
		return StringUtil.getStringValue(
		itemCodeClientMappingBean.getClientItemCode());

	}
	
					public void setItemName(String obj){

		itemCodeClientMappingBean.setItemName(new String(obj));

	}

	public String getItemName(){
		return StringUtil.getStringValue(
		itemCodeClientMappingBean.getItemName());

	}
	
				
	public void setItemCategoryId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			
			ItemCategory itemCategoryId = new ItemCategory();
			itemCategoryId.setItemCategoryId(Integer.valueOf(obj));
			itemCodeClientMappingBean.setItemCategoryId(itemCategoryId);
		}

	}

	public String getItemCategoryId(){
		String result = "";
		
		if (itemCodeClientMappingBean.getItemCategoryId() != null){
			result = StringUtil.getStringValue(
						itemCodeClientMappingBean.getItemCategoryId());
		}
		
		return result;

	}
	
					public void setDescription(String obj){

		itemCodeClientMappingBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		itemCodeClientMappingBean.getDescription());

	}
	
					public void setItemCode(String obj){

		itemCodeClientMappingBean.setItemCode(new String(obj));

	}

	public String getItemCode(){
		return StringUtil.getStringValue(
		itemCodeClientMappingBean.getItemCode());

	}
	
				
	public void setCreatedTime(String obj){

		itemCodeClientMappingBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		itemCodeClientMappingBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		itemCodeClientMappingBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		itemCodeClientMappingBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		itemCodeClientMappingBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		itemCodeClientMappingBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		itemCodeClientMappingBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		itemCodeClientMappingBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		itemCodeClientMappingBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		itemCodeClientMappingBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		itemCodeClientMappingBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		itemCodeClientMappingBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		itemCodeClientMappingBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		itemCodeClientMappingBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
