
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * ItemCategoryMap is a mapping of item_category_map Table.
*/
public class ItemCategoryMapForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewItemCategoryMap = false;
	private ItemCategoryMap itemCategoryMapBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ItemCategoryMapForm()
    {
    	this.itemCategoryMapBean = new ItemCategoryMap();
    	this.isNewItemCategoryMap = true;
    }
    public ItemCategoryMapForm (ItemCategoryMap object){
		this.itemCategoryMapBean = object;
    }
    public boolean isNewItemCategoryMap (){

    	return this.isNewItemCategoryMap;
    }
	public ItemCategoryMap getItemCategoryMap (){
		return this.itemCategoryMapBean ;
	}
	public void setItemCategoryMap (ItemCategoryMap object){
		this.itemCategoryMapBean = object;
	}

			
	public void setItemCategoryMapId(String obj){

		itemCategoryMapBean.setItemCategoryMapId(StringUtil.getIntegerValue(obj,0));

	}

	public String getItemCategoryMapId(){
		return StringUtil.getStringValue(
		itemCategoryMapBean.getItemCategoryMapId());

	}
	
										
	public void setIsActive(String obj){

		itemCategoryMapBean.setIsActive(StringUtil.getIntegerValue(obj,0));

	}

	public String getIsActive(){
		return StringUtil.getStringValue(
		itemCategoryMapBean.getIsActive());

	}
	
					

	// foreign affairs
	
	

	
	public void setCaseCategoryId(String obj){
		CaseCategory fk = new CaseCategory();
		fk.setCaseCategoryId(StringUtil.getIntegerValue(obj,0));
		itemCategoryMapBean.setCaseCategoryId(fk);

	}

	public String getCaseCategoryId(){
		return StringUtil.getStringValue(
		itemCategoryMapBean.getCaseCategoryId().getCaseCategoryId());

	}
	//---
	
	

	
	public void setItemId(String obj){
		Item fk = new Item();
		fk.setItemId(StringUtil.getIntegerValue(obj,0));
		itemCategoryMapBean.setItemId(fk);

	}

	public String getItemId(){
		return StringUtil.getStringValue(
		itemCategoryMapBean.getItemId().getItemId());

	}
	//---
	
	

	
	public void setItemCategoryId(String obj){
		ItemCategory fk = new ItemCategory();
		fk.setItemCategoryId(StringUtil.getIntegerValue(obj,0));
		itemCategoryMapBean.setItemCategoryId(fk);

	}

	public String getItemCategoryId(){
		return StringUtil.getStringValue(
		itemCategoryMapBean.getItemCategoryId().getItemCategoryId());

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
