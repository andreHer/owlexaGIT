
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.CaseEvent;
import com.ametis.cms.datamodel.CaseItem;
import com.ametis.cms.datamodel.Item;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * CaseItem is a mapping of case_item Table.
*/
public class CaseItemForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewCaseItem = false;
	private CaseItem caseItemBean ;
	private String itemName;
	
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public CaseItemForm()
    {
    	this.caseItemBean = new CaseItem();
    	this.isNewCaseItem = true;
    }
    public CaseItemForm (CaseItem object){
		this.caseItemBean = object;
    }
    public boolean isNewCaseItem (){

    	return this.isNewCaseItem;
    }
	public CaseItem getCaseItem (){
		return this.caseItemBean ;
	}
	public void setCaseItem (CaseItem object){
		this.caseItemBean = object;
	}

			
	public void setCaseItemId(String obj){

		caseItemBean.setCaseItemId(StringUtil.getIntegerValue(obj,0));

	}

	public String getCaseItemId(){
		return StringUtil.getStringValue(
		caseItemBean.getCaseItemId());

	}
	
				
	public void setItemId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Item item = new Item();
			item.setItemId(Integer.valueOf(obj));
			caseItemBean.setItemId(item);	
		}
	}

	public String getItemId(){
		
		String result = "";
		
		if (caseItemBean.getItemId() != null){
			result = StringUtil.getStringValue(
					caseItemBean.getItemId().getItemId()); 
		}
		return result;

	}
	
	public void setCaseId (String caseId){
		
		if (caseId != null && !caseId.equalsIgnoreCase("")){
			Case theCase = new Case();
			theCase.setCaseId(Integer.valueOf(caseId));
			
			caseItemBean.setCaseId(theCase);
		}
	}
	public String getCaseId(){
		String result = "";
		
		if (caseItemBean.getCaseId() != null){
			result = StringUtil.getStringValue(caseItemBean.getCaseId().getCaseId());
		}
		
		return result;
	}
				
	public void setUsageTime(String obj){

		caseItemBean.setUsageTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getUsageTime(){
		return StringUtil.getStringValue(
		caseItemBean.getUsageTime());

	}

	
				
	public void setUsageAmount(String obj){

		caseItemBean.setUsageAmount(StringUtil.getDoubleValue(obj,0));

	}

	public String getUsageAmount(){
		return StringUtil.getStringValue(
		caseItemBean.getUsageAmount());

	}
	
				
	public void setUsageValue(String obj){

		caseItemBean.setUsageValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getUsageValue(){
		return StringUtil.getStringValue(
		caseItemBean.getUsageValue());

	}
	
							
	public void setCreatedTime(String obj){

		caseItemBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		caseItemBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		caseItemBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		caseItemBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		caseItemBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		caseItemBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		caseItemBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		caseItemBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		caseItemBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		caseItemBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		caseItemBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		caseItemBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		caseItemBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		caseItemBean.getDeletedStatus());

	}
	
					public void setDescription(String obj){

		caseItemBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		caseItemBean.getDescription());

	}
	
		

	// foreign affairs
	
	

	
	public void setCaseEventId(String obj){
		
		
		if (obj == null || obj.equals("")){
			caseItemBean.setCaseEventId(null);
		}
		else {
			CaseEvent fk = new CaseEvent();
			fk.setCaseEventId(StringUtil.getIntegerValue(obj,0));
			caseItemBean.setCaseEventId(fk);	
		}

	}

	public String getCaseEventId(){
		return StringUtil.getStringValue(
		caseItemBean.getCaseEventId().getCaseEventId());

	}
	//---
		// -- foreign affairs end
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

// class+ 

// class- 
}
