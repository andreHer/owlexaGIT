
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.ExcessCharge;
import com.ametis.cms.datamodel.ExcessReminder;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * ExcessReminder is a mapping of excess_reminder Table.
*/
public class ExcessReminderForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewExcessReminder = false;
	private ExcessReminder excessReminderBean ;
	private String excessChargeNumber;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ExcessReminderForm()
    {
    	this.excessReminderBean = new ExcessReminder();
    	this.isNewExcessReminder = true;
    }
    public ExcessReminderForm (ExcessReminder object){
		this.excessReminderBean = object;
    }
    public boolean isNewExcessReminder (){

    	return this.isNewExcessReminder;
    }
	public ExcessReminder getExcessReminder (){
		return this.excessReminderBean ;
	}
	public void setExcessReminder (ExcessReminder object){
		this.excessReminderBean = object;
	}

			
	public void setExcessReminderId(String obj){

		excessReminderBean.setExcessReminderId(StringUtil.getIntegerValue(obj,0));

	}

	public String getExcessReminderId(){
		return StringUtil.getStringValue(
		excessReminderBean.getExcessReminderId());

	}
	
				
	public void setReminderDate(String obj){

		excessReminderBean.setReminderDate(java.sql.Date.valueOf(obj));

	}

	public String getReminderDate(){
		return StringUtil.getStringValue(
		excessReminderBean.getReminderDate());

	}

	
				
	public void setReminderCounter(String obj){

		excessReminderBean.setReminderCounter(StringUtil.getIntegerValue(obj,0));

	}

	public String getReminderCounter(){
		return StringUtil.getStringValue(
		excessReminderBean.getReminderCounter());

	}
	
					public void setReminderRemarks(String obj){

		excessReminderBean.setReminderRemarks(new String(obj));

	}

	public String getReminderRemarks(){
		return StringUtil.getStringValue(
		excessReminderBean.getReminderRemarks());

	}
	
				
	public void setExcessChargeId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			ExcessCharge excess  = new ExcessCharge();
			excess.setExcessChargeId(Integer.valueOf(obj));
			
			excessReminderBean.setExcessChargeId(excess);
		}

	}

	public String getExcessChargeId(){
		String result = "";
		
		if (excessReminderBean.getExcessChargeId() != null){
			result = excessReminderBean.getExcessChargeId().getExcessChargeId().toString();
		}
		return result;
		

	}
	
	
					public void setReminderBy(String obj){

		excessReminderBean.setReminderBy(new String(obj));

	}

	public String getReminderBy(){
		return StringUtil.getStringValue(
		excessReminderBean.getReminderBy());

	}
	public String getExcessChargeNumber() {
		return excessChargeNumber;
	}
	public void setExcessChargeNumber(String excessChargeNumber) {
		this.excessChargeNumber = excessChargeNumber;
	}
	
	
}
