
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ExcessCharge;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.PaymentStatus;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * ExcessCharge is a mapping of excess_charge Table.
*/
public class ExcessChargeForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewExcessCharge = false;
	private ExcessCharge excessChargeBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ExcessChargeForm()
    {
    	this.excessChargeBean = new ExcessCharge();
    	this.isNewExcessCharge = true;
    }
    public ExcessChargeForm (ExcessCharge object){
		this.excessChargeBean = object;
    }
    public boolean isNewExcessCharge (){

    	return this.isNewExcessCharge;
    }
	public ExcessCharge getExcessCharge (){
		return this.excessChargeBean ;
	}
	public void setExcessCharge (ExcessCharge object){
		this.excessChargeBean = object;
	}

			
	public void setExcessChargeId(String obj){

		excessChargeBean.setExcessChargeId(StringUtil.getIntegerValue(obj,0));

	}

	public String getExcessChargeId(){
		return StringUtil.getStringValue(
		excessChargeBean.getExcessChargeId());

	}
	
				
	public void setExcessChargeValue(String obj){

		excessChargeBean.setExcessChargeValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getExcessChargeValue(){
		return StringUtil.getStringValue(
		excessChargeBean.getExcessChargeValue());

	}
	
										
	public void setExcessChargeStatus(PaymentStatus obj){

		excessChargeBean.setExcessChargeStatus(obj);

	}

	public PaymentStatus getExcessChargeStatus(){
		return excessChargeBean.getExcessChargeStatus();

	}
	
					public void setRemarks(String obj){

		excessChargeBean.setRemarks(new String(obj));

	}

	public String getRemarks(){
		return StringUtil.getStringValue(
		excessChargeBean.getRemarks());

	}
	
				
	public void setExcessChargeTime(String obj){

		excessChargeBean.setExcessChargeTime(java.sql.Date.valueOf(obj));

	}

	public String getExcessChargeTime(){
		return StringUtil.getStringValue(
		excessChargeBean.getExcessChargeTime());

	}

	
				
	public void setCreatedTime(String obj){

		excessChargeBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		excessChargeBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		excessChargeBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		excessChargeBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		excessChargeBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		excessChargeBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		excessChargeBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		excessChargeBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		excessChargeBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		excessChargeBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		excessChargeBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		excessChargeBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		excessChargeBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		excessChargeBean.getDeletedStatus());

	}
	
				
	public void setLastReminderTime(String obj){

		excessChargeBean.setLastReminderTime(java.sql.Date.valueOf(obj));

	}

	public String getLastReminderTime(){
		return StringUtil.getStringValue(
		excessChargeBean.getLastReminderTime());

	}

	
				
	public void setNextReminderTime(String obj){

		excessChargeBean.setNextReminderTime(java.sql.Date.valueOf(obj));

	}

	public String getNextReminderTime(){
		return StringUtil.getStringValue(
		excessChargeBean.getNextReminderTime());

	}

	
	public void setExcessChargeNumber (String obj){
		excessChargeBean.setExcessChargeNumber(obj);
	}
	public String getExcessChargeNumber (){
		return excessChargeBean.getExcessChargeNumber();
	}
	public void setReminderCounter(String obj){

		excessChargeBean.setReminderCounter(StringUtil.getIntegerValue(obj,0));

	}

	public String getReminderCounter(){
		return StringUtil.getStringValue(
		excessChargeBean.getReminderCounter());

	}
	
		

	// foreign affairs
	
	

	
	public void setMemberId(String obj){
		Member fk = new Member();
		fk.setMemberId(StringUtil.getIntegerValue(obj,0));
		excessChargeBean.setMemberId(fk);

	}

	public String getMemberId(){
		return StringUtil.getStringValue(
		excessChargeBean.getMemberId().getMemberId());

	}
	//---
	
	

	
	public void setClaimId(String obj){
		Claim fk = new Claim();
		fk.setClaimId(StringUtil.getIntegerValue(obj,0));
		excessChargeBean.setClaimId(fk);

	}

	public String getClaimId(){
		return StringUtil.getStringValue(
		excessChargeBean.getClaimId().getClaimId());

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
