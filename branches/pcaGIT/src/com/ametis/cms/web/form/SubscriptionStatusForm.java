
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * SubscriptionStatus is a mapping of subscription_status Table.
*/
public class SubscriptionStatusForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewSubscriptionStatus = false;
	private SubscriptionStatus subscriptionStatusBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public SubscriptionStatusForm()
    {
    	this.subscriptionStatusBean = new SubscriptionStatus();
    	this.isNewSubscriptionStatus = true;
    }
    public SubscriptionStatusForm (SubscriptionStatus object){
		this.subscriptionStatusBean = object;
    }
    public boolean isNewSubscriptionStatus (){

    	return this.isNewSubscriptionStatus;
    }
	public SubscriptionStatus getSubscriptionStatus (){
		return this.subscriptionStatusBean ;
	}
	public void setSubscriptionStatus (SubscriptionStatus object){
		this.subscriptionStatusBean = object;
	}

			
	public void setStatusId(String obj){

		subscriptionStatusBean.setStatusId(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatusId(){
		return StringUtil.getStringValue(
		subscriptionStatusBean.getStatusId());

	}
	
					public void setStatus(String obj){

		subscriptionStatusBean.setStatus(new String(obj));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		subscriptionStatusBean.getStatus());

	}
	
					public void setDescription(String obj){

		subscriptionStatusBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		subscriptionStatusBean.getDescription());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
