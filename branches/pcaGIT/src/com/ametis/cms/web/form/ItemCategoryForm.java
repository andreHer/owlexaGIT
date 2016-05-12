
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.ItemCategory;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * ItemCategory is a mapping of item_category Table.
*/
public class ItemCategoryForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewItemCategory = false;
	private ItemCategory itemCategoryBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ItemCategoryForm()
    {
    	this.itemCategoryBean = new ItemCategory();
    	this.isNewItemCategory = true;
    }
    public ItemCategoryForm (ItemCategory object){
		this.itemCategoryBean = object;
    }
    public boolean isNewItemCategory (){

    	return this.isNewItemCategory;
    }
	public ItemCategory getItemCategory (){
		return this.itemCategoryBean ;
	}
	public void setItemCategory (ItemCategory object){
		this.itemCategoryBean = object;
	}

			
	public void setItemCategoryId(String obj){

		itemCategoryBean.setItemCategoryId(StringUtil.getIntegerValue(obj,0));

	}

	public String getItemCategoryId(){
		return StringUtil.getStringValue(
		itemCategoryBean.getItemCategoryId());

	}
	
					public void setDescription(String obj){

		itemCategoryBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		itemCategoryBean.getDescription());

	}
	
					public void setItemCategoryName(String obj){

		itemCategoryBean.setItemCategoryName(new String(obj));

	}

	public String getItemCategoryName(){
		return StringUtil.getStringValue(
		itemCategoryBean.getItemCategoryName());

	}
	public void setItemCategoryEDCName(String obj){

		itemCategoryBean.setItemCategoryEDCName(new String(obj));

	}

	public String getItemCategoryEDCName(){
		return StringUtil.getStringValue(
		itemCategoryBean.getItemCategoryEDCName());

	}
	
					public void setItemCategoryCode(String obj){

		itemCategoryBean.setItemCategoryCode(new String(obj));

	}

	public String getItemCategoryCode(){
		return StringUtil.getStringValue(
		itemCategoryBean.getItemCategoryCode());

	}
	public void setItemCategoryAlternateCode(String obj){

		itemCategoryBean.setItemCategoryAlternateCode(new String(obj));

	}

	public String getItemCategoryAlternateCode(){
		return StringUtil.getStringValue(
		itemCategoryBean.getItemCategoryAlternateCode());

	}
	public void setItemCategoryAlternateCode2(String obj){
		itemCategoryBean.setItemCategoryAlternateCode2(new String(obj));
	}

	public String getItemCategoryAlternateCode2(){
		return StringUtil.getStringValue(
		itemCategoryBean.getItemCategoryAlternateCode2());
	}
	public void setIsEdcItem(String obj){

		if (obj != null){
			itemCategoryBean.setIsEdcItem(new Integer(obj));
		}
		else {
			itemCategoryBean.setIsEdcItem(new Integer(0));
		}

	}

	public String getIsEdcItem(){
		return StringUtil.getStringValue(
		itemCategoryBean.getIsEdcItem());

	}
	public void setItemEDCCode(String obj){

		itemCategoryBean.setItemCategoryEDCCode(obj);

	}

	public String getItemEDCCode(){
		return StringUtil.getStringValue(
		itemCategoryBean.getItemCategoryEDCCode());

	}
	public void setCaseCategoryId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			CaseCategory cc = new CaseCategory();
			cc.setCaseCategoryId(Integer.valueOf(obj));
			itemCategoryBean.setCaseCategoryId(cc);
		}

	}

	public String getCaseCategoryId(){
		String result = "";
		
		if (itemCategoryBean.getCaseCategoryId() != null){
			result = itemCategoryBean.getCaseCategoryId().getCaseCategoryId().toString();
		}
		
		return result;
		

	}			
	public void setCreatedTime(String obj){

		itemCategoryBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		itemCategoryBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		itemCategoryBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		itemCategoryBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		itemCategoryBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		itemCategoryBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		itemCategoryBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		itemCategoryBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		itemCategoryBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		itemCategoryBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		itemCategoryBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		itemCategoryBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		itemCategoryBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		itemCategoryBean.getDeletedStatus());

	}
	
	public void setIsClientInvoiceItem(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			itemCategoryBean.setIsClientInvoiceItem(StringUtil.getIntegerValue(obj,0));
		}

	}

	public String getIsClientInvoiceItem(){
		String result = "";
		

		if (itemCategoryBean.getIsClientInvoiceItem() != null){
			result = itemCategoryBean.getIsClientInvoiceItem().toString();
		}
		
		return result;

	}	

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
