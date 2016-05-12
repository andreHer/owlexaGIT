
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * EdcTerminal is a mapping of edc_terminal Table.
*/
public class EdcTerminalForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewEdcTerminal = false;
	private EdcTerminal edcTerminalBean ;
	private String providerName;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public EdcTerminalForm()
    {
    	this.edcTerminalBean = new EdcTerminal();
    	this.isNewEdcTerminal = true;
    }
    public EdcTerminalForm (EdcTerminal object){
		this.edcTerminalBean = object;
    }
    public boolean isNewEdcTerminal (){

    	return this.isNewEdcTerminal;
    }
	public EdcTerminal getEdcTerminal (){
		return this.edcTerminalBean ;
	}
	public void setEdcTerminal (EdcTerminal object){
		this.edcTerminalBean = object;
	}

			
	public void setId(String obj){

		edcTerminalBean.setId(StringUtil.getIntegerValue(obj,0));

	}

	public String getId(){
		return StringUtil.getStringValue(
		edcTerminalBean.getId());

	}
	
				
	public void setProviderId(String obj){

		if (obj != null && !obj.equals("")){
			Provider provider = new Provider();
			provider.setProviderId(Integer.valueOf(obj));
			
			edcTerminalBean.setProviderId(provider);
		}

	}

	public String getProviderId(){
		String result = "";
		
		if (edcTerminalBean.getProviderId() != null){
			result = edcTerminalBean.getProviderId().getProviderId().toString();
		}
		
		return result;
		

	}
	
					public void setLocation(String obj){

		edcTerminalBean.setLocation(new String(obj));

	}

	public String getLocation(){
		return StringUtil.getStringValue(
		edcTerminalBean.getLocation());

	}
	
					public void setDescription(String obj){

		edcTerminalBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		edcTerminalBean.getDescription());

	}
	
					public void setDeviceNumber(String obj){

		edcTerminalBean.setDeviceNumber(new String(obj));

	}

	public String getDeviceNumber(){
		return StringUtil.getStringValue(
		edcTerminalBean.getDeviceNumber());

	}
	
					public void setAuthorizationKey(String obj){

		edcTerminalBean.setAuthorizationKey(new String(obj));

	}

	public String getAuthorizationKey(){
		return StringUtil.getStringValue(
		edcTerminalBean.getAuthorizationKey());

	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	
	public String getIsFault() {
		return StringUtil.getStringValue(edcTerminalBean.getIsFault());
	}

	public void setIsFault(String isFault) {
		if (isFault != null){
			edcTerminalBean.setIsFault(Integer.valueOf(isFault));
		}
		else {
			edcTerminalBean.setIsFault(Integer.valueOf(0));
		}
	}
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 

}
