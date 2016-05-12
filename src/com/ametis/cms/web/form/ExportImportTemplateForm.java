
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * ExportImportTemplate is a mapping of export_import_template Table.
*/
public class ExportImportTemplateForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewExportImportTemplate = false;
	private ExportImportTemplate exportImportTemplateBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ExportImportTemplateForm()
    {
    	this.exportImportTemplateBean = new ExportImportTemplate();
    	this.isNewExportImportTemplate = true;
    }
    public ExportImportTemplateForm (ExportImportTemplate object){
		this.exportImportTemplateBean = object;
    }
    public boolean isNewExportImportTemplate (){

    	return this.isNewExportImportTemplate;
    }
	public ExportImportTemplate getExportImportTemplate (){
		return this.exportImportTemplateBean ;
	}
	public void setExportImportTemplate (ExportImportTemplate object){
		this.exportImportTemplateBean = object;
	}

			
	public void setId(String obj){

		exportImportTemplateBean.setId(StringUtil.getIntegerValue(obj,0));

	}

	public String getId(){
		return StringUtil.getStringValue(
		exportImportTemplateBean.getId());

	}
	
					public void setTemplateName(String obj){

		exportImportTemplateBean.setTemplateName(new String(obj));

	}

	public String getTemplateName(){
		return StringUtil.getStringValue(
		exportImportTemplateBean.getTemplateName());

	}
	
					public void setDescription(String obj){

		exportImportTemplateBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		exportImportTemplateBean.getDescription());

	}
	
					public void setTemplateMapping(String obj){

		exportImportTemplateBean.setTemplateMapping(new String(obj));

	}

	public String getTemplateMapping(){
		return StringUtil.getStringValue(
		exportImportTemplateBean.getTemplateMapping());

	}
	public void setJenisTransfer(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			exportImportTemplateBean.setJenisTransfer(Integer.valueOf(obj));
		}

	}

	public String getJenisTransfer(){
		String result = "";
		
		if (exportImportTemplateBean.getJenisTransfer() != null){
			result = StringUtil.getStringValue(
					exportImportTemplateBean.getJenisTransfer());
		}
		
		return result;
		

	}
	
				
	public void setTipe(String obj){

		exportImportTemplateBean.setTipe(StringUtil.getIntegerValue(obj,0));

	}

	public String getTipe(){
		return StringUtil.getStringValue(
		exportImportTemplateBean.getTipe());

	}
	
				
	public void setCreatedTime(String obj){

		exportImportTemplateBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		exportImportTemplateBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		exportImportTemplateBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		exportImportTemplateBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		exportImportTemplateBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		exportImportTemplateBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		exportImportTemplateBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		exportImportTemplateBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		exportImportTemplateBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		exportImportTemplateBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		exportImportTemplateBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		exportImportTemplateBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		exportImportTemplateBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		exportImportTemplateBean.getDeletedStatus());

	}
	
		
	public void setDelimiter(String obj){

		exportImportTemplateBean.setDelimiter(obj);

	}

	public String getDelimiter(){
		return StringUtil.getStringValue(
		exportImportTemplateBean.getDelimiter());

	}
	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
