
package com.ametis.cms.web.form;

import org.springframework.web.multipart.MultipartFile;

import com.ametis.cms.datamodel.ClientProvider;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * ClientProvider is a mapping of client_provider Table.
*/
public class ClientProviderForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewClientProvider = false;
	private ClientProvider clientProviderBean ;
	private MultipartFile multipartFile;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ClientProviderForm()
    {
    	this.clientProviderBean = new ClientProvider();
    	this.isNewClientProvider = true;
    }
    public ClientProviderForm (ClientProvider object){
		this.clientProviderBean = object;
    }
    public boolean isNewClientProvider (){

    	return this.isNewClientProvider;
    }
	public ClientProvider getClientProvider (){
		return this.clientProviderBean ;
	}
	public void setClientProvider (ClientProvider object){
		this.clientProviderBean = object;
	}

			
	public void setClientProviderId(String obj){

		clientProviderBean.setClientProviderId(StringUtil.getIntegerValue(obj,0));

	}

	public String getClientProviderId(){
		return StringUtil.getStringValue(
		clientProviderBean.getClientProviderId());

	}
	
	/*
				
	public void setClientId(String obj){

		clientProviderBean.setClientId(StringUtil.getIntegerValue(obj,0));

	}

	public String getClientId(){
		return StringUtil.getStringValue(
		clientProviderBean.getClientId());

	}
	
				
	public void setProviderId(String obj){

		clientProviderBean.setProviderId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProviderId(){
		return StringUtil.getStringValue(
		clientProviderBean.getProviderId());

	}
	*/
				

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
