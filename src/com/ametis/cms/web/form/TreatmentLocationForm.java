
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.TreatmentLocation;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * TreatmentLocation is a mapping of treatment_location Table.
*/
public class TreatmentLocationForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewTreatmentLocation = false;
	private TreatmentLocation treatmentLocationBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public TreatmentLocationForm()
    {
    	this.treatmentLocationBean = new TreatmentLocation();
    	this.isNewTreatmentLocation = true;
    }
    public TreatmentLocationForm (TreatmentLocation object){
		this.treatmentLocationBean = object;
    }
    public boolean isNewTreatmentLocation (){

    	return this.isNewTreatmentLocation;
    }
	public TreatmentLocation getTreatmentLocation (){
		return this.treatmentLocationBean ;
	}
	public void setTreatmentLocation (TreatmentLocation object){
		this.treatmentLocationBean = object;
	}

			
	public void setLocationId(String obj){

		treatmentLocationBean.setLocationId(StringUtil.getIntegerValue(obj,0));

	}

	public String getLocationId(){
		return StringUtil.getStringValue(
		treatmentLocationBean.getLocationId());

	}
	
					public void setLocation(String obj){

		treatmentLocationBean.setLocation(new String(obj));

	}

	public String getLocation(){
		return StringUtil.getStringValue(
		treatmentLocationBean.getLocation());

	}
	
					public void setDescription(String obj){

		treatmentLocationBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		treatmentLocationBean.getDescription());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
