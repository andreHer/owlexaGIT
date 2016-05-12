
package com.ametis.cms.web.form;

import java.sql.Date;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * ReportRequest is a mapping of report_request Table.
*/
public class ReportRequestForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewReportRequest = false;
	private ReportRequest reportRequestBean ;
	private Integer subjectId;
	private String subjectName;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ReportRequestForm()
    {
    	this.reportRequestBean = new ReportRequest();
    	this.isNewReportRequest = true;
    }
    public ReportRequestForm (ReportRequest object){
		this.reportRequestBean = object;
    }
    public boolean isNewReportRequest (){

    	return this.isNewReportRequest;
    }
	public ReportRequest getReportRequest (){
		return this.reportRequestBean ;
	}
	public void setReportRequest (ReportRequest object){
		this.reportRequestBean = object;
	}

			
	public void setId(String obj){

		reportRequestBean.setId(StringUtil.getIntegerValue(obj,0));

	}

	public String getId(){
		return StringUtil.getStringValue(
		reportRequestBean.getId());

	}
	
							
	public void setRequestTime(String obj){

		reportRequestBean.setRequestTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getRequestTime(){
		return StringUtil.getStringValue(
		reportRequestBean.getRequestTime());

	}

	
				
	public void setReportType(String obj){

		reportRequestBean.setReportType(StringUtil.getIntegerValue(obj,0));

	}

	public String getReportType(){
		return StringUtil.getStringValue(
		reportRequestBean.getReportType());

	}
	
					public void setTableOptions(String obj){

		reportRequestBean.setTableOptions(new String(obj));

	}

	public String getTableOptions(){
		return StringUtil.getStringValue(
		reportRequestBean.getTableOptions());

	}
	
					public void setOutputExtension(String obj){

		reportRequestBean.setOutputExtension(new String(obj));

	}

	public String getOutputExtension(){
		return StringUtil.getStringValue(
		reportRequestBean.getOutputExtension());

	}
	
					public void setUrlReportResult(String obj){

		reportRequestBean.setUrlReportResult(new String(obj));

	}

	public String getUrlReportResult(){
		return StringUtil.getStringValue(
		reportRequestBean.getUrlReportResult());

	}
	
				
	public void setStatus(String obj){

		reportRequestBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		reportRequestBean.getStatus());

	}
	
				
	public void setCreatedTime(String obj){

		reportRequestBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		reportRequestBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		reportRequestBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		reportRequestBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		reportRequestBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		reportRequestBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		reportRequestBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		reportRequestBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		reportRequestBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		reportRequestBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		reportRequestBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		reportRequestBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		reportRequestBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		reportRequestBean.getDeletedStatus());

	}
	
	public void setQuery(String obj){
		reportRequestBean.setQuery(new String(obj));
	}

	public String getQuery(){
		return StringUtil.getStringValue(
		reportRequestBean.getQuery());
	}
	public void setStartDate(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			reportRequestBean.setStartDate(Date.valueOf(obj));
		}
	}
	public String getStartDate(){
		return StringUtil.getStringValue(
		reportRequestBean.getStartDate());
	}
	public void setEndDate(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			reportRequestBean.setEndDate(Date.valueOf(obj));
		}
	}
	public String getEndDate(){
		return StringUtil.getStringValue(
		reportRequestBean.getEndDate());
	}
	
	public void setRequestNumber(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			reportRequestBean.setRequestNumber(obj);
		}
	}
	public String getRequestNumber(){
		return StringUtil.getStringValue(
		reportRequestBean.getRequestNumber());
	}
		
	public void setReportSubjectType(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			reportRequestBean.setReportSubjectType(obj);
		}
	}
	public String getReportSubjectType(){
		return StringUtil.getStringValue(
		reportRequestBean.getReportSubjectType());
	}
	// foreign affairs
	public Integer getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	

	//---
		// -- foreign affairs end

// class+ 

// class- 

}
