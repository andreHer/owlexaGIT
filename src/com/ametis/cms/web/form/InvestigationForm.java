
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.Investigation;
import com.ametis.cms.datamodel.InvestigationCategory;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * Investigation is a mapping of investigation Table.
*/
public class InvestigationForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewInvestigation = false;
	private Investigation investigationBean ;
	private String hospital;
	private java.util.Date date;
	private String time;
	
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public InvestigationForm()
    {
    	this.investigationBean = new Investigation();
    	this.isNewInvestigation = true;
    }
    public InvestigationForm (Investigation object){
		this.investigationBean = object;
    }
    public boolean isNewInvestigation (){

    	return this.isNewInvestigation;
    }
	public Investigation getInvestigation (){
		return this.investigationBean ;
	}
	public void setInvestigation (Investigation object){
		this.investigationBean = object;
	}

			
	public java.util.Date getDate() {
		return date;
	}
	public void setDate(java.util.Date date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public void setInvestigationId(String obj){

		investigationBean.setInvestigationId(StringUtil.getLongValue(obj,0));

	}

	public String getInvestigationId(){
		return StringUtil.getStringValue(
		investigationBean.getInvestigationId());

	}
	
					public void setInvestigationSubject(String obj){

		investigationBean.setInvestigationSubject(new String(obj));

	}

	public String getInvestigationSubject(){
		return StringUtil.getStringValue(
		investigationBean.getInvestigationSubject());

	}
	
				
	public void setInvestigationCategory(String obj){
		InvestigationCategory fk = new InvestigationCategory();
		fk.setInvestigationCategoryId(StringUtil.getIntegerValue(obj,0));
		investigationBean.setInvestigationCategory(fk);

	}
//	public void setInvestigationCategory(Integer obj){
//		InvestigationCategory fk = new InvestigationCategory();
//		fk.setInvestigationCategoryId(obj);
//		investigationBean.setInvestigationCategory(fk);
//
//	}
	public Integer getInvestigationCategory(){
		InvestigationCategory ic = investigationBean.getInvestigationCategory();
		
		if (ic == null){
			ic = new InvestigationCategory();
		}
		return 	ic.getInvestigationCategoryId();

	}
	
					public void setHeadDoctor(String obj){

		investigationBean.setHeadDoctor(new String(obj));

	}

	public String getHeadDoctor(){
		return StringUtil.getStringValue(
		investigationBean.getHeadDoctor());

	}
	
					public void setConsultDoctor(String obj){

		investigationBean.setConsultDoctor(new String(obj));

	}

	public String getHospital() {
						return hospital;
					}
					public void setHospital(String hospital) {
						this.hospital = hospital;
					}
	public String getConsultDoctor(){
		return StringUtil.getStringValue(
		investigationBean.getConsultDoctor());

	}
	
					public void setDescription(String obj){

		investigationBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		investigationBean.getDescription());

	}
	
				
	public void setCreatedTime(String obj){

		investigationBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		investigationBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		investigationBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		investigationBean.getCreatedBy());

	}
	
				
	public void setUpdatedTime(String obj){

		investigationBean.setUpdatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getUpdatedTime(){
		return StringUtil.getStringValue(
		investigationBean.getUpdatedTime());

	}

	
					public void setUpdatedBy(String obj){

		investigationBean.setUpdatedBy(new String(obj));

	}

	public String getUpdatedBy(){
		return StringUtil.getStringValue(
		investigationBean.getUpdatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		investigationBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		investigationBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		investigationBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		investigationBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		investigationBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		investigationBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
