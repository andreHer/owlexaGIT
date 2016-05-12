
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * TarifType is a mapping of tarif_type Table.
*/
public class TarifTypeForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewTarifType = false;
	private TarifType tarifTypeBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public TarifTypeForm()
    {
    	this.tarifTypeBean = new TarifType();
    	this.isNewTarifType = true;
    }
    public TarifTypeForm (TarifType object){
		this.tarifTypeBean = object;
    }
    public boolean isNewTarifType (){

    	return this.isNewTarifType;
    }
	public TarifType getTarifType (){
		return this.tarifTypeBean ;
	}
	public void setTarifType (TarifType object){
		this.tarifTypeBean = object;
	}

			
	public void setTarifTypeId(String obj){

		tarifTypeBean.setTarifTypeId(StringUtil.getIntegerValue(obj,0));

	}

	public String getTarifTypeId(){
		return StringUtil.getStringValue(
		tarifTypeBean.getTarifTypeId());

	}
	
					public void setTarifTypeName(String obj){

		tarifTypeBean.setTarifTypeName(new String(obj));

	}

	public String getTarifTypeName(){
		return StringUtil.getStringValue(
		tarifTypeBean.getTarifTypeName());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
