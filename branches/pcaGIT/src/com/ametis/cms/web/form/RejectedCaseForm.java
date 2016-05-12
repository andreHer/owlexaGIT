
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.RejectCategory;
import com.ametis.cms.datamodel.RejectedCase;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * RejectedCase is a mapping of rejected_case Table.
*/
public class RejectedCaseForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewRejectedCase = false;
	private RejectedCase rejectedCaseBean ;
	private String caseNumber;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public RejectedCaseForm()
    {
    	this.rejectedCaseBean = new RejectedCase();
    	this.isNewRejectedCase = true;
    }
    public RejectedCaseForm (RejectedCase object){
		this.rejectedCaseBean = object;
    }
    public boolean isNewRejectedCase (){

    	return this.isNewRejectedCase;
    }
	public RejectedCase getRejectedCase (){
		return this.rejectedCaseBean ;
	}
	public void setRejectedCase (RejectedCase object){
		this.rejectedCaseBean = object;
	}

			
	public void setRejectedCaseId(String obj){

		rejectedCaseBean.setRejectedCaseId(StringUtil.getLongValue(obj,0));

	}

	public String getRejectedCaseId(){
		return StringUtil.getStringValue(
		rejectedCaseBean.getRejectedCaseId());

	}
	
							
	public void setRejectionDate(String obj){

		rejectedCaseBean.setRejectionDate(java.sql.Date.valueOf(obj));

	}

	public String getRejectionDate(){
		return StringUtil.getStringValue(
		rejectedCaseBean.getRejectionDate());

	}

	
					public void setRejectionDescription(String obj){

		rejectedCaseBean.setRejectionDescription(new String(obj));

	}

	public String getRejectionDescription(){
		return StringUtil.getStringValue(
		rejectedCaseBean.getRejectionDescription());

	}
	
				
	public void setEstimatedCost(String obj){

		rejectedCaseBean.setEstimatedCost(StringUtil.getDoubleValue(obj,0));

	}

	public String getEstimatedCost(){
		return StringUtil.getStringValue(
		rejectedCaseBean.getEstimatedCost());

	}
	
				
	public void setCreatedTime(String obj){

		rejectedCaseBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		rejectedCaseBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		rejectedCaseBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		rejectedCaseBean.getCreatedBy());

	}
	
					public void setModifiedBy(String obj){

		rejectedCaseBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		rejectedCaseBean.getModifiedBy());

	}
	
				
	public void setModifiedTime(String obj){

		rejectedCaseBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		rejectedCaseBean.getModifiedTime());

	}

	
				
	public void setDeletedTime(String obj){

		rejectedCaseBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		rejectedCaseBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		rejectedCaseBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		rejectedCaseBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		rejectedCaseBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		rejectedCaseBean.getDeletedStatus());

	}
	
		

	// foreign affairs
	
	

	
	public void setCaseId(String obj){
		
		if (obj != null && !obj.equals("")){
			Case caseId = new Case();
			caseId.setCaseId(Integer.valueOf(obj));
			rejectedCaseBean.setCaseId(caseId);
		}

	}

	
	public String getCaseId(){
		String result = "";
		
		
		if (rejectedCaseBean.getCaseId() != null){
			result = rejectedCaseBean.getCaseId().getCaseId().toString();
		}
		
		return result;
		

	}
	public void setCaseNumber (String obj){
		this.caseNumber = obj;
	}
	public String getCaseNumber (){
		return this.caseNumber;
	}
	public void setCategoryId(String obj){

		if (obj != null && !obj.equals("")){
			RejectCategory category = new RejectCategory();
			category.setRejectCategoryId(Integer.valueOf(obj));
			rejectedCaseBean.setRejectCategoryId(category);
		}

	}

	public String getCategoryId(){
		
		String result = "";
		
		if (rejectedCaseBean.getRejectCategoryId() != null){
			result = rejectedCaseBean.getRejectCategoryId().getRejectCategoryId().toString();
		}
		
		return result;	

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
