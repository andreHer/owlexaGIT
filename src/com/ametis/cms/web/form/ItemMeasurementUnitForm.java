
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.ItemCategory;
import com.ametis.cms.datamodel.ItemMeasurementUnit;
import com.ametis.cms.datamodel.MeasurementUnit;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * ItemMeasurementUnit is a mapping of item_measurement_unit Table.
*/
public class ItemMeasurementUnitForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewItemMeasurementUnit = false;
	private ItemMeasurementUnit itemMeasurementUnitBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ItemMeasurementUnitForm()
    {
    	this.itemMeasurementUnitBean = new ItemMeasurementUnit();
    	this.isNewItemMeasurementUnit = true;
    }
    public ItemMeasurementUnitForm (ItemMeasurementUnit object){
		this.itemMeasurementUnitBean = object;
    }
    public boolean isNewItemMeasurementUnit (){

    	return this.isNewItemMeasurementUnit;
    }
	public ItemMeasurementUnit getItemMeasurementUnit (){
		return this.itemMeasurementUnitBean ;
	}
	public void setItemMeasurementUnit (ItemMeasurementUnit object){
		this.itemMeasurementUnitBean = object;
	}

			
	public void setItemMeasurementUnitId(String obj){

		itemMeasurementUnitBean.setItemMeasurementUnitId(StringUtil.getIntegerValue(obj,0));

	}

	public String getItemMeasurementUnitId(){
		return StringUtil.getStringValue(
		itemMeasurementUnitBean.getItemMeasurementUnitId());

	}
	
										
	public void setCreatedTime(String obj){

		itemMeasurementUnitBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		itemMeasurementUnitBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		itemMeasurementUnitBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		itemMeasurementUnitBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		itemMeasurementUnitBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		itemMeasurementUnitBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		itemMeasurementUnitBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		itemMeasurementUnitBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		itemMeasurementUnitBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		itemMeasurementUnitBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		itemMeasurementUnitBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		itemMeasurementUnitBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		itemMeasurementUnitBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		itemMeasurementUnitBean.getDeletedStatus());

	}
	
		

	// foreign affairs
	
	

	
	public void setItemCategoryId(String obj){
		ItemCategory fk = new ItemCategory();
		fk.setItemCategoryId(StringUtil.getIntegerValue(obj,0));
		itemMeasurementUnitBean.setItemCategoryId(fk);

	}

	public String getItemCategoryId(){
		return StringUtil.getStringValue(
		itemMeasurementUnitBean.getItemCategoryId().getItemCategoryId());

	}
	//---
	
	

	
	public void setMeasurementUnitId(String obj){
		MeasurementUnit fk = new MeasurementUnit();
		fk.setMeasurementUnitId(StringUtil.getIntegerValue(obj,0));
		itemMeasurementUnitBean.setMeasurementUnitId(fk);

	}

	public String getMeasurementUnitId(){
		return StringUtil.getStringValue(
		itemMeasurementUnitBean.getMeasurementUnitId().getMeasurementUnitId());

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
