
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * DocumentCategory is a mapping of document_category Table.
*/
public class DocumentCategoryForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewDocumentCategory = false;
	private DocumentCategory documentCategoryBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public DocumentCategoryForm()
    {
    	this.documentCategoryBean = new DocumentCategory();
    	this.isNewDocumentCategory = true;
    }
    public DocumentCategoryForm (DocumentCategory object){
		this.documentCategoryBean = object;
    }
    public boolean isNewDocumentCategory (){

    	return this.isNewDocumentCategory;
    }
	public DocumentCategory getDocumentCategory (){
		return this.documentCategoryBean ;
	}
	public void setDocumentCategory (DocumentCategory object){
		this.documentCategoryBean = object;
	}

			
	public void setDocumentCategoryId(String obj){

		documentCategoryBean.setDocumentCategoryId(StringUtil.getIntegerValue(obj,0));

	}

	public String getDocumentCategoryId(){
		return StringUtil.getStringValue(
		documentCategoryBean.getDocumentCategoryId());

	}
	
					public void setDocumentCategoryName(String obj){

		documentCategoryBean.setDocumentCategoryName(new String(obj));

	}

	public String getDocumentCategoryName(){
		return StringUtil.getStringValue(
		documentCategoryBean.getDocumentCategoryName());

	}
	
					public void setDocumentCategoryCode(String obj){

		documentCategoryBean.setDocumentCategoryCode(new String(obj));

	}

	public String getDocumentCategoryCode(){
		return StringUtil.getStringValue(
		documentCategoryBean.getDocumentCategoryCode());

	}
	
					public void setDescription(String obj){

		documentCategoryBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		documentCategoryBean.getDescription());

	}
	
				
	public void setAccessibleToClient(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
		documentCategoryBean.setAccessibleToClient(Integer.valueOf(obj));
		}
		else {
			documentCategoryBean.setAccessibleToClient(0);
		}
	}

	public String getAccessibleToClient(){
		return StringUtil.getStringValue(
		documentCategoryBean.getAccessibleToClient());

	}
	public void setAccessibleToHRD(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			documentCategoryBean.setAccessibleToHRD(new Integer(obj));
		}
		else {
			documentCategoryBean.setAccessibleToHRD(0);
		}
	}
	public String getAccessibleToHRD(){
		return StringUtil.getStringValue(documentCategoryBean.getAccessibleToHRD());
	}				
	public void setAccessibleToProvider(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			documentCategoryBean.setAccessibleToProvider(Integer.valueOf(obj));
		}
		else {
			documentCategoryBean.setAccessibleToProvider(0);
		}
	}

	public String getAccessibleToProvider(){
		return StringUtil.getStringValue(documentCategoryBean.getAccessibleToProvider());
	}
	public void setAccessibleToBroker(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			documentCategoryBean.setAccessibleToBroker(new Integer(obj));
		}
		else {
			documentCategoryBean.setAccessibleToBroker(0);
		}
	}

	public String getAccessibleToBroker(){
		
		return StringUtil.getStringValue(documentCategoryBean.getAccessibleToBroker());
	}
	
				
	public void setDeletedTime(String obj){

		documentCategoryBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		documentCategoryBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		documentCategoryBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		documentCategoryBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		documentCategoryBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		documentCategoryBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
