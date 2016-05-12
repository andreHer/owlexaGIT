
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.CostContainment;
import com.ametis.cms.datamodel.SubstitutionItem;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * SubstitutionItem is a mapping of substitution_item Table.
*/
public class SubstitutionItemForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewSubstitutionItem = false;
	private SubstitutionItem substitutionItemBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public SubstitutionItemForm()
    {
    	this.substitutionItemBean = new SubstitutionItem();
    	this.isNewSubstitutionItem = true;
    }
    public SubstitutionItemForm (SubstitutionItem object){
		this.substitutionItemBean = object;
    }
    public boolean isNewSubstitutionItem (){

    	return this.isNewSubstitutionItem;
    }
	public SubstitutionItem getSubstitutionItem (){
		return this.substitutionItemBean ;
	}
	public void setSubstitutionItem (SubstitutionItem object){
		this.substitutionItemBean = object;
	}

			
	public void setSubstitutionItemId(String obj){

		substitutionItemBean.setSubstitutionItemId(StringUtil.getIntegerValue(obj,0));

	}

	public String getSubstitutionItemId(){
		return StringUtil.getStringValue(
		substitutionItemBean.getSubstitutionItemId());

	}
	
							
	public void setItemId(String obj){

		substitutionItemBean.setItemId(StringUtil.getIntegerValue(obj,0));

	}

	public String getItemId(){
		return StringUtil.getStringValue(
		substitutionItemBean.getItemId());

	}
	
				
	public void setMeasurementUnit(String obj){

		substitutionItemBean.setMeasurementUnit(StringUtil.getIntegerValue(obj,0));

	}

	public String getMeasurementUnit(){
		return StringUtil.getStringValue(
		substitutionItemBean.getMeasurementUnit());

	}
	
					public void setDescription(String obj){

		substitutionItemBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		substitutionItemBean.getDescription());

	}
	
				
	public void setTotalSubstitutionValue(String obj){

		substitutionItemBean.setTotalSubstitutionValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalSubstitutionValue(){
		return StringUtil.getStringValue(
		substitutionItemBean.getTotalSubstitutionValue());

	}
	
				
	public void setTotalItem(String obj){

		substitutionItemBean.setTotalItem(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalItem(){
		return StringUtil.getStringValue(
		substitutionItemBean.getTotalItem());

	}
	
				
	public void setCreatedTime(String obj){

		substitutionItemBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		substitutionItemBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		substitutionItemBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		substitutionItemBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		substitutionItemBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		substitutionItemBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		substitutionItemBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		substitutionItemBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		substitutionItemBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		substitutionItemBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		substitutionItemBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		substitutionItemBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		substitutionItemBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		substitutionItemBean.getDeletedStatus());

	}
	
		

	// foreign affairs
	
	

	
	public void setCostContainmentId(String obj){
		CostContainment fk = new CostContainment();
		fk.setCostContainmentId(StringUtil.getIntegerValue(obj,0));
		substitutionItemBean.setCostContainmentId(fk);

	}

	public String getCostContainmentId(){
		return StringUtil.getStringValue(
		substitutionItemBean.getCostContainmentId().getCostContainmentId());

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
