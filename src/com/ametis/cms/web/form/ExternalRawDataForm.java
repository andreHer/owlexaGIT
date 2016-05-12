
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * ExternalRawData is a mapping of external_raw_data Table.
*/
public class ExternalRawDataForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewExternalRawData = false;
	private ExternalRawData externalRawDataBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ExternalRawDataForm()
    {
    	this.externalRawDataBean = new ExternalRawData();
    	this.isNewExternalRawData = true;
    }
    public ExternalRawDataForm (ExternalRawData object){
		this.externalRawDataBean = object;
    }
    public boolean isNewExternalRawData (){

    	return this.isNewExternalRawData;
    }
	public ExternalRawData getExternalRawData (){
		return this.externalRawDataBean ;
	}
	public void setExternalRawData (ExternalRawData object){
		this.externalRawDataBean = object;
	}

			
	public void setId(String obj){

		externalRawDataBean.setId(StringUtil.getIntegerValue(obj,0));

	}

	public String getId(){
		return StringUtil.getStringValue(
		externalRawDataBean.getId());

	}
	
					public void setRawData(String obj){

		externalRawDataBean.setRawData(new String(obj));

	}

	public String getRawData(){
		return StringUtil.getStringValue(
		externalRawDataBean.getRawData());

	}
	
				
	public void setIsMigrated(String obj){

		externalRawDataBean.setIsMigrated(StringUtil.getIntegerValue(obj,0));

	}

	public String getIsMigrated(){
		return StringUtil.getStringValue(
		externalRawDataBean.getIsMigrated());

	}
	
					

	// foreign affairs 
	
	

	
	public void setTemplateId(String obj){
            
            if (obj != null && !obj.equalsIgnoreCase("")){
		ExportImportTemplate fk = new ExportImportTemplate();
		fk.setId(Integer.valueOf(obj));
		externalRawDataBean.setTemplateId(fk);
            }

	}

	public String getTemplateId(){
		return StringUtil.getStringValue(
		externalRawDataBean.getTemplateId().getId());

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
