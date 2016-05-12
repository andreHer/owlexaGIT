
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.BatchClaim;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.Outstanding;
import com.ametis.cms.datamodel.PaymentStatus;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * Outstanding is a mapping of outstanding Table.
*/
public class OutstandingForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewOutstanding = false;
	private Outstanding outstandingBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public OutstandingForm()
    {
    	this.outstandingBean = new Outstanding();
    	this.isNewOutstanding = true;
    }
    public OutstandingForm (Outstanding object){
		this.outstandingBean = object;
    }
    public boolean isNewOutstanding (){

    	return this.isNewOutstanding;
    }
	public Outstanding getOutstanding (){
		return this.outstandingBean ;
	}
	public void setOutstanding (Outstanding object){
		this.outstandingBean = object;
	}

			
	public void setOutstandingId(String obj){

		outstandingBean.setOutstandingId(StringUtil.getIntegerValue(obj,0));

	}

	public String getOutstandingId(){
		return StringUtil.getStringValue(
		outstandingBean.getOutstandingId());

	}
	
				
	public void setOutstandingValue(String obj){

		outstandingBean.setOutstandingValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getOutstandingValue(){
		return StringUtil.getStringValue(
		outstandingBean.getOutstandingValue());

	}
	
										
	public void setOutstandingStatus(PaymentStatus obj){

		outstandingBean.setOutstandingStatus(obj);

	}

	public PaymentStatus getOutstandingStatus(){
		return outstandingBean.getOutstandingStatus();

	}
	
				
	public void setOutstandingTime(String obj){

		outstandingBean.setOutstandingTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getOutstandingTime(){
		return StringUtil.getStringValue(
		outstandingBean.getOutstandingTime());

	}

	
				
	public void setCreatedTime(String obj){

		outstandingBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		outstandingBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		outstandingBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		outstandingBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		outstandingBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		outstandingBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		outstandingBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		outstandingBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		outstandingBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		outstandingBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		outstandingBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		outstandingBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		outstandingBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		outstandingBean.getDeletedStatus());

	}
	
				
	public void setOutstandingExcessValue(String obj){

		outstandingBean.setOutstandingExcessValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getOutstandingExcessValue(){
		return StringUtil.getStringValue(
		outstandingBean.getOutstandingExcessValue());

	}
	
		

	// foreign affairs
	
	

	
	public void setClientId(String obj){
		Client fk = new Client();
		fk.setClientId(StringUtil.getIntegerValue(obj,0));
		outstandingBean.setClientId(fk);

	}

	public String getClientId(){
		return StringUtil.getStringValue(
		outstandingBean.getClientId().getClientId());

	}
	//---
	
	

	
	public void setBatchClaimId(String obj){
		BatchClaim fk = new BatchClaim();
		fk.setBatchClaimId(StringUtil.getIntegerValue(obj,0));
		outstandingBean.setBatchClaimId(fk);

	}

	public String getBatchClaimId(){
		return StringUtil.getStringValue(
		outstandingBean.getBatchClaimId().getBatchClaimId());

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
