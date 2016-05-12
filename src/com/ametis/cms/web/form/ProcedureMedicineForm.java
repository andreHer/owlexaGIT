
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * ProcedureMedicine is a mapping of procedure_medicine Table.
*/
public class ProcedureMedicineForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewProcedureMedicine = false;
	private ProcedureMedicine procedureMedicineBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ProcedureMedicineForm()
    {
    	this.procedureMedicineBean = new ProcedureMedicine();
    	this.isNewProcedureMedicine = true;
    }
    public ProcedureMedicineForm (ProcedureMedicine object){
		this.procedureMedicineBean = object;
    }
    public boolean isNewProcedureMedicine (){

    	return this.isNewProcedureMedicine;
    }
	public ProcedureMedicine getProcedureMedicine (){
		return this.procedureMedicineBean ;
	}
	public void setProcedureMedicine (ProcedureMedicine object){
		this.procedureMedicineBean = object;
	}

			
	public void setProcedureMedicineId(String obj){

		procedureMedicineBean.setProcedureMedicineId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProcedureMedicineId(){
		return StringUtil.getStringValue(
		procedureMedicineBean.getProcedureMedicineId());

	}
	
										
	public void setTotalUsage(String obj){

		procedureMedicineBean.setTotalUsage(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalUsage(){
		return StringUtil.getStringValue(
		procedureMedicineBean.getTotalUsage());

	}
	
				
	public void setTotalCharge(String obj){

		procedureMedicineBean.setTotalCharge(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalCharge(){
		return StringUtil.getStringValue(
		procedureMedicineBean.getTotalCharge());

	}
	
				
	public void setReferencePrice(String obj){

		procedureMedicineBean.setReferencePrice(StringUtil.getDoubleValue(obj,0));

	}

	public String getReferencePrice(){
		return StringUtil.getStringValue(
		procedureMedicineBean.getReferencePrice());

	}
	
					public void setDescription(String obj){

		procedureMedicineBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		procedureMedicineBean.getDescription());

	}
	
				
	public void setCreatedTime(String obj){

		procedureMedicineBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		procedureMedicineBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		procedureMedicineBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		procedureMedicineBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		procedureMedicineBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		procedureMedicineBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		procedureMedicineBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		procedureMedicineBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		procedureMedicineBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		procedureMedicineBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		procedureMedicineBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		procedureMedicineBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		procedureMedicineBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		procedureMedicineBean.getDeletedStatus());

	}
	
		

	// foreign affairs
	
	

	
	public void setMedicineId(String obj){
		Medicine fk = new Medicine();
		fk.setMedicineId(StringUtil.getIntegerValue(obj,0));
		procedureMedicineBean.setMedicineId(fk);

	}

	public String getMedicineId(){
		return StringUtil.getStringValue(
		procedureMedicineBean.getMedicineId().getMedicineId());

	}
	//---
	
	

	
	public void setProcedureId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Procedure fk = new Procedure();
			fk.setProcedureId(StringUtil.getIntegerValue(obj,0));
			procedureMedicineBean.setProcedureId(fk);	
		}
		

	}

	public String getProcedureId(){
		String result = "";
		
		if (procedureMedicineBean.getProcedureId() != null){
			result = StringUtil.getStringValue(
					procedureMedicineBean.getProcedureId().getProcedureId());
		}
		return result;
		

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
