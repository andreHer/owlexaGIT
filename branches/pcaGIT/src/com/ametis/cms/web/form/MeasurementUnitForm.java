
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.MeasurementUnit;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * MeasurementUnit is a mapping of measurement_unit Table.
*/
public class MeasurementUnitForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewMeasurementUnit = false;
	private MeasurementUnit measurementUnitBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public MeasurementUnitForm()
    {
    	this.measurementUnitBean = new MeasurementUnit();
    	this.isNewMeasurementUnit = true;
    }
    public MeasurementUnitForm (MeasurementUnit object){
		this.measurementUnitBean = object;
    }
    public boolean isNewMeasurementUnit (){

    	return this.isNewMeasurementUnit;
    }
	public MeasurementUnit getMeasurementUnit (){
		return this.measurementUnitBean ;
	}
	public void setMeasurementUnit (MeasurementUnit object){
		this.measurementUnitBean = object;
	}

			
	public void setMeasurementUnitId(String obj){

		measurementUnitBean.setMeasurementUnitId(StringUtil.getIntegerValue(obj,0));

	}

	public String getMeasurementUnitId(){
		return StringUtil.getStringValue(
		measurementUnitBean.getMeasurementUnitId());

	}
	
					public void setMeasurementUnitName(String obj){

		measurementUnitBean.setMeasurementUnitName(new String(obj));

	}

	public String getMeasurementUnitName(){
		return StringUtil.getStringValue(
		measurementUnitBean.getMeasurementUnitName());

	}
	
					public void setDescription(String obj){

		measurementUnitBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		measurementUnitBean.getDescription());

	}
	
				
	public void setCreatedTime(String obj){

		measurementUnitBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		measurementUnitBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		measurementUnitBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		measurementUnitBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		measurementUnitBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		measurementUnitBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		measurementUnitBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		measurementUnitBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		measurementUnitBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		measurementUnitBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		measurementUnitBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		measurementUnitBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		measurementUnitBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		measurementUnitBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
