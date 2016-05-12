
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.Diagnosis;
import com.ametis.cms.datamodel.DiagnosisItem;
import com.ametis.cms.datamodel.Item;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * DiagnosisItem is a mapping of diagnosis_item Table.
*/
public class DiagnosisItemForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewDiagnosisItem = false;
	private DiagnosisItem diagnosisItemBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public DiagnosisItemForm()
    {
    	this.diagnosisItemBean = new DiagnosisItem();
    	this.isNewDiagnosisItem = true;
    }
    public DiagnosisItemForm (DiagnosisItem object){
		this.diagnosisItemBean = object;
    }
    public boolean isNewDiagnosisItem (){

    	return this.isNewDiagnosisItem;
    }
	public DiagnosisItem getDiagnosisItem (){
		return this.diagnosisItemBean ;
	}
	public void setDiagnosisItem (DiagnosisItem object){
		this.diagnosisItemBean = object;
	}

			
	public void setDiagnosisItemId(String obj){

		diagnosisItemBean.setDiagnosisItemId(StringUtil.getIntegerValue(obj,0));

	}

	public String getDiagnosisItemId(){
		return StringUtil.getStringValue(
		diagnosisItemBean.getDiagnosisItemId());

	}
	
										
	public void setCreatedTime(String obj){

		diagnosisItemBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		diagnosisItemBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		diagnosisItemBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		diagnosisItemBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		diagnosisItemBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		diagnosisItemBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		diagnosisItemBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		diagnosisItemBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		diagnosisItemBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		diagnosisItemBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		diagnosisItemBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		diagnosisItemBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		diagnosisItemBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		diagnosisItemBean.getDeletedStatus());

	}
	
		

	// foreign affairs
	
	

	
	public void setDiagnosisId(String obj){
		Diagnosis fk = new Diagnosis();
		fk.setDiagnosisId(StringUtil.getIntegerValue(obj,0));
		diagnosisItemBean.setDiagnosisId(fk);

	}

	public String getDiagnosisId(){
		return StringUtil.getStringValue(
		diagnosisItemBean.getDiagnosisId().getDiagnosisId());

	}
	//---
	
	

	
	public void setItemId(String obj){
		Item fk = new Item();
		fk.setItemId(StringUtil.getIntegerValue(obj,0));
		diagnosisItemBean.setItemId(fk);

	}

	public String getItemId(){
		return StringUtil.getStringValue(
		diagnosisItemBean.getItemId().getItemId());

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
