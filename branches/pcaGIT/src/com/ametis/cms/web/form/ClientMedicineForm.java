
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * ClientMedicine is a mapping of client_medicine Table.
*/
public class ClientMedicineForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewClientMedicine = false;
	private ClientMedicine clientMedicineBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ClientMedicineForm()
    {
    	this.clientMedicineBean = new ClientMedicine();
    	this.isNewClientMedicine = true;
    }
    public ClientMedicineForm (ClientMedicine object){
		this.clientMedicineBean = object;
    }
    public boolean isNewClientMedicine (){

    	return this.isNewClientMedicine;
    }
	public ClientMedicine getClientMedicine (){
		return this.clientMedicineBean ;
	}
	public void setClientMedicine (ClientMedicine object){
		this.clientMedicineBean = object;
	}

			
	public void setClientMedicineId(String obj){

		clientMedicineBean.setClientMedicineId(StringUtil.getIntegerValue(obj,0));

	}

	public String getClientMedicineId(){
		return StringUtil.getStringValue(
		clientMedicineBean.getClientMedicineId());

	}
	
				
	public void setDeletedStatus(String obj){

		clientMedicineBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		clientMedicineBean.getDeletedStatus());

	}
	
				
	public void setModifiedTime(String obj){

		clientMedicineBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		clientMedicineBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		clientMedicineBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		clientMedicineBean.getModifiedBy());

	}
	
					public void setDeletedBy(String obj){

		clientMedicineBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		clientMedicineBean.getDeletedBy());

	}
	
				
	public void setDeletedTime(String obj){

		clientMedicineBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		clientMedicineBean.getDeletedTime());

	}

	
				
	public void setCreatedTime(String obj){

		clientMedicineBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		clientMedicineBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		clientMedicineBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		clientMedicineBean.getCreatedBy());

	}
	
				
	public void setItemValue(String obj){

		clientMedicineBean.setItemValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getItemValue(){
		return StringUtil.getStringValue(
		clientMedicineBean.getItemValue());

	}
	
										
	public void setReferencePrice(String obj){

		clientMedicineBean.setReferencePrice(StringUtil.getDoubleValue(obj,0));

	}

	public String getReferencePrice(){
		return StringUtil.getStringValue(
		clientMedicineBean.getReferencePrice());

	}
	
					public void setDescription(String obj){

		clientMedicineBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		clientMedicineBean.getDescription());

	}
	
		

	// foreign affairs
	
	

	
	public void setMedicineId(String obj){
		Medicine fk = new Medicine();
		fk.setMedicineId(StringUtil.getIntegerValue(obj,0));
		clientMedicineBean.setMedicineId(fk);

	}

	public String getMedicineId(){
		return StringUtil.getStringValue(
		clientMedicineBean.getMedicineId().getMedicineId());

	}
	//---
	
	

	
	public void setClientId(String obj){
		Client fk = new Client();
		fk.setClientId(StringUtil.getIntegerValue(obj,0));
		clientMedicineBean.setClientId(fk);

	}

	public String getClientId(){
		return StringUtil.getStringValue(
		clientMedicineBean.getClientId().getClientId());

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
