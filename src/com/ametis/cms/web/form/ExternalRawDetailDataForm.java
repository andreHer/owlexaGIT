
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * ExternalRawDetailData is a mapping of external_raw_detail_data Table.
*/
public class ExternalRawDetailDataForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewExternalRawDetailData = false;
	private ExternalRawDetailData externalRawDetailDataBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ExternalRawDetailDataForm()
    {
    	this.externalRawDetailDataBean = new ExternalRawDetailData();
    	this.isNewExternalRawDetailData = true;
    }
    public ExternalRawDetailDataForm (ExternalRawDetailData object){
		this.externalRawDetailDataBean = object;
    }
    public boolean isNewExternalRawDetailData (){

    	return this.isNewExternalRawDetailData;
    }
	public ExternalRawDetailData getExternalRawDetailData (){
		return this.externalRawDetailDataBean ;
	}
	public void setExternalRawDetailData (ExternalRawDetailData object){
		this.externalRawDetailDataBean = object;
	}

			
	public void setId(String obj){

		externalRawDetailDataBean.setId(StringUtil.getIntegerValue(obj,0));

	}

	public String getId(){
		return StringUtil.getStringValue(
		externalRawDetailDataBean.getId());

	}
	
					public void setData(String obj){

		externalRawDetailDataBean.setData(new String(obj));

	}

	public String getData(){
		return StringUtil.getStringValue(
		externalRawDetailDataBean.getData());

	}
	
				
	public void setIsMigrated(String obj){

		externalRawDetailDataBean.setIsMigrated(StringUtil.getIntegerValue(obj,0));

	}

	public String getIsMigrated(){
		return StringUtil.getStringValue(
		externalRawDetailDataBean.getIsMigrated());

	}
	
				
	public void setTemplateId(String obj){

            if (obj != null && !obj.equalsIgnoreCase("")){
                ExportImportTemplate template = new ExportImportTemplate();
                template.setId(Integer.valueOf(obj));
		externalRawDetailDataBean.setTemplateId(template);
            }

	}

	public String getTemplateId(){
		return StringUtil.getStringValue(
		externalRawDetailDataBean.getTemplateId());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
