
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * Broker is a mapping of broker Table.
*/
public class BrokerForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewBroker = false;
	private Broker brokerBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public BrokerForm()
    {
    	this.brokerBean = new Broker();
    	this.isNewBroker = true;
    }
    public BrokerForm (Broker object){
		this.brokerBean = object;
    }
    public boolean isNewBroker (){

    	return this.isNewBroker;
    }
	public Broker getBroker (){
		return this.brokerBean ;
	}
	public void setBroker (Broker object){
		this.brokerBean = object;
	}

			
	public void setBrokerId(String obj){

		brokerBean.setBrokerId(StringUtil.getIntegerValue(obj,0));

	}

	public String getBrokerId(){
		return StringUtil.getStringValue(
		brokerBean.getBrokerId());

	}
	
					public void setBrokerName(String obj){

		brokerBean.setBrokerName(new String(obj));

	}

	public String getBrokerName(){
		return StringUtil.getStringValue(
		brokerBean.getBrokerName());

	}
	
					public void setBrokerCode(String obj){

		brokerBean.setBrokerCode(new String(obj));

	}

	public String getBrokerCode(){
		return StringUtil.getStringValue(
		brokerBean.getBrokerCode());

	}
	
					public void setAddress(String obj){

		brokerBean.setAddress(new String(obj));

	}

	public String getAddress(){
		return StringUtil.getStringValue(
		brokerBean.getAddress());

	}
	
					public void setCity(String obj){

		brokerBean.setCity(new String(obj));

	}

	public String getCity(){
		return StringUtil.getStringValue(
		brokerBean.getCity());

	}
	
					public void setProvince(String obj){

		brokerBean.setProvince(new String(obj));

	}

	public String getProvince(){
		return StringUtil.getStringValue(
		brokerBean.getProvince());

	}
	
					public void setCountry(String obj){

		brokerBean.setCountry(new String(obj));

	}

	public String getCountry(){
		return StringUtil.getStringValue(
		brokerBean.getCountry());

	}
	
					public void setZipcode(String obj){

		brokerBean.setZipcode(new String(obj));

	}

	public String getZipcode(){
		return StringUtil.getStringValue(
		brokerBean.getZipcode());

	}
	
					public void setTelephone(String obj){

		brokerBean.setTelephone(new String(obj));

	}

	public String getTelephone(){
		return StringUtil.getStringValue(
		brokerBean.getTelephone());

	}
	
					public void setFaximile(String obj){

		brokerBean.setFaximile(new String(obj));

	}

	public String getFaximile(){
		return StringUtil.getStringValue(
		brokerBean.getFaximile());

	}
	
				
	public void setBrokerType(String obj){

		brokerBean.setBrokerType(StringUtil.getIntegerValue(obj,0));

	}

	public String getBrokerType(){
		return StringUtil.getStringValue(
		brokerBean.getBrokerType());

	}
	
				
	public void setStatus(String obj){

		brokerBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		brokerBean.getStatus());

	}
	
				
	public void setCreatedTime(String obj){

		brokerBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		brokerBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		brokerBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		brokerBean.getCreatedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		brokerBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		brokerBean.getDeletedStatus());

	}
	
					public void setDeletedBy(String obj){

		brokerBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		brokerBean.getDeletedBy());

	}
	
				
	public void setDeletedTime(String obj){

		brokerBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		brokerBean.getDeletedTime());

	}

	
				
	public void setModifiedTime(String obj){

		brokerBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		brokerBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		brokerBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		brokerBean.getModifiedBy());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
