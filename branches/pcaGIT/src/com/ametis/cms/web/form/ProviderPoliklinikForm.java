
package com.ametis.cms.web.form;

import org.springframework.web.multipart.MultipartFile;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * ProviderPoliklinik is a mapping of provider_poliklinik Table.
*/
public class ProviderPoliklinikForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewProviderPoliklinik = false;
	private ProviderPoliklinik providerPoliklinikBean ;
	private String providerName;
	private MultipartFile multipartFile;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ProviderPoliklinikForm()
    {
    	this.providerPoliklinikBean = new ProviderPoliklinik();
    	this.isNewProviderPoliklinik = true;
    }
    public ProviderPoliklinikForm (ProviderPoliklinik object){
		this.providerPoliklinikBean = object;
    }
    public boolean isNewProviderPoliklinik (){

    	return this.isNewProviderPoliklinik;
    }
	public ProviderPoliklinik getProviderPoliklinik (){
		return this.providerPoliklinikBean ;
	}
	public void setProviderPoliklinik (ProviderPoliklinik object){
		this.providerPoliklinikBean = object;
	}

			
	public void setProviderPoliklinikId(String obj){

		providerPoliklinikBean.setProviderPoliklinikId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProviderPoliklinikId(){
		return StringUtil.getStringValue(
		providerPoliklinikBean.getProviderPoliklinikId());

	}
	
				
	public void setProviderId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Provider provider = new Provider();
			provider.setProviderId(Integer.valueOf(obj));
			providerPoliklinikBean.setProviderId(provider);
		}
		
		

	}

	public String getProviderId(){
		String result = "";
		
		if (providerPoliklinikBean.getProviderId() != null){
			result = StringUtil.getStringValue(
					providerPoliklinikBean.getProviderId().getProviderId());
		}
		
		return result;
		

	}
	
				
	public void setPoliklinikId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Poliklinik poliklinik = new Poliklinik();
			poliklinik.setPoliklinikId(Integer.valueOf(obj));
			providerPoliklinikBean.setPoliklinikId(poliklinik);
		}
		

	}

	public String getPoliklinikId(){
		String result = "";
		
		if (providerPoliklinikBean.getPoliklinikId() != null){
			result = StringUtil.getStringValue(
					providerPoliklinikBean.getPoliklinikId().getPoliklinikId());
		}
		return result;
		

	}
	
				
	public void setStatus(String obj){

		providerPoliklinikBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		providerPoliklinikBean.getStatus());

	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}
	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}

	
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
