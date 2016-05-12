
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.ExcessCharge;
import com.ametis.cms.datamodel.ExcessChargeItem;
import com.ametis.cms.datamodel.Item;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * ExcessChargeItem is a mapping of excess_charge_item Table.
*/
public class ExcessChargeItemForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewExcessChargeItem = false;
	private ExcessChargeItem excessChargeItemBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ExcessChargeItemForm()
    {
    	this.excessChargeItemBean = new ExcessChargeItem();
    	this.isNewExcessChargeItem = true;
    }
    public ExcessChargeItemForm (ExcessChargeItem object){
		this.excessChargeItemBean = object;
    }
    public boolean isNewExcessChargeItem (){

    	return this.isNewExcessChargeItem;
    }
	public ExcessChargeItem getExcessChargeItem (){
		return this.excessChargeItemBean ;
	}
	public void setExcessChargeItem (ExcessChargeItem object){
		this.excessChargeItemBean = object;
	}

			
	public void setExcessChargeItemId(String obj){

		excessChargeItemBean.setExcessChargeItemId(StringUtil.getIntegerValue(obj,0));

	}

	public String getExcessChargeItemId(){
		return StringUtil.getStringValue(
		excessChargeItemBean.getExcessChargeItemId());

	}
	
				
	public void setExcessChargeId(ExcessCharge obj){

		excessChargeItemBean.setExcessChargeId(obj);

	}

	public ExcessCharge getExcessChargeId(){
		return excessChargeItemBean.getExcessChargeId();

	}
	
				
	public void setItemId(Item obj){

		excessChargeItemBean.setItemId(obj);

	}

	public Item getItemId(){
		return excessChargeItemBean.getItemId();

	}
	
				
	public void setValue(String obj){

		excessChargeItemBean.setValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getValue(){
		return StringUtil.getStringValue(
		excessChargeItemBean.getValue());

	}
	
				
	public void setCreatedTime(String obj){

		excessChargeItemBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		excessChargeItemBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		excessChargeItemBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		excessChargeItemBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		excessChargeItemBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		excessChargeItemBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		excessChargeItemBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		excessChargeItemBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		excessChargeItemBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		excessChargeItemBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		excessChargeItemBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		excessChargeItemBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		excessChargeItemBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		excessChargeItemBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
