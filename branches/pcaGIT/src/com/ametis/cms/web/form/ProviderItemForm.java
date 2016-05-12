
package com.ametis.cms.web.form;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import com.ametis.cms.datamodel.Item;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.ProviderItem;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * ProviderItem is a mapping of provider_item Table.
*/
public class ProviderItemForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewProviderItem = false;
	private ProviderItem providerItemBean ;
	private String providerName;
	private String itemName;
	private MultipartFile multipartFile;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ProviderItemForm()
    {
    	this.providerItemBean = new ProviderItem();
    	this.isNewProviderItem = true;
    }
    public ProviderItemForm (ProviderItem object){
		this.providerItemBean = object;
    }
    public boolean isNewProviderItem (){

    	return this.isNewProviderItem;
    }
	public ProviderItem getProviderItem (){
		return this.providerItemBean ;
	}
	public void setProviderItem (ProviderItem object){
		this.providerItemBean = object;
	}

			
	public void setProviderItemId(String obj){

		providerItemBean.setProviderItemId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProviderItemId(){
		return StringUtil.getStringValue(
		providerItemBean.getProviderItemId());

	}
	
							
	public void setItemId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Item itemBean = new Item();
			itemBean.setItemId(StringUtil.getIntegerValue(obj,0));
			providerItemBean.setItemId(itemBean);
		}

	}

	public String getItemId(){
		String result = "";
		
		if (providerItemBean.getItemId() != null){
			result = providerItemBean.getItemId().getItemId().toString();
		}
		return result;
		

	}
	
				
	public void setSuperVIP(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			providerItemBean.setSvip(StringUtil.getDoubleValue(obj,0));
		}

	}

	public String getSuperVIP(){
String result = "";
		
		if (providerItemBean.getSvip() != null){
			BigDecimal dec = new BigDecimal(providerItemBean.getSvip());
			result = dec.toPlainString();
		}
		return result;

	}
	public void setVIP(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			providerItemBean.setVip(StringUtil.getDoubleValue(obj,0));
		}

	}

	public String getVIP(){
		String result = "";
		
		if (providerItemBean.getVip() != null){
			BigDecimal dec = new BigDecimal(providerItemBean.getVip());
			result = dec.toPlainString();
		}
		return result;
		

	}
	public void setKelas1(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			providerItemBean.setKelas1(StringUtil.getDoubleValue(obj,0));
		}
		

	}

	public String getKelas1(){
		String result = "";
		
		if (providerItemBean.getKelas1() != null){
			BigDecimal dec = new BigDecimal(providerItemBean.getKelas1());
			result = dec.toPlainString();
		}
		return result;

	}
	public void setRawatJalan(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			providerItemBean.setRawatJalan(StringUtil.getDoubleValue(obj,0));
		}
		

	}

	public String getRawatJalan(){
		String result = "";
		
		if (providerItemBean.getRawatJalan() != null){
			BigDecimal dec = new BigDecimal(providerItemBean.getRawatJalan());
			result = dec.toPlainString();
		}
		return result;

	}
	public void setKelas2(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			providerItemBean.setKelas2(StringUtil.getDoubleValue(obj,0));
		}

	}

	public String getKelas2(){
		String result = "";
		
		if (providerItemBean.getKelas2() != null){
			BigDecimal dec = new BigDecimal(providerItemBean.getKelas2());
			result = dec.toPlainString();
		}
		return result;

	}
	public void setKelas3(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			providerItemBean.setKelas3(StringUtil.getDoubleValue(obj,0));
		}

	}

	public String getKelas3(){
		String result = "";
		
		if (providerItemBean.getKelas3() != null){
			BigDecimal dec = new BigDecimal(providerItemBean.getKelas3());
			result = dec.toPlainString();
		}
		return result;

	}
	public void setItemValue(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			providerItemBean.setItemValue(StringUtil.getDoubleValue(obj,0));
		}

	}

	public String getItemValue(){
		return StringUtil.getStringValue(
		providerItemBean.getItemValue());

	}

	

	
	public void setProviderId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Provider fk = new Provider();
			fk.setProviderId(StringUtil.getIntegerValue(obj,0));
			providerItemBean.setProviderId(fk);
		}

	}

	public String getProviderId(){
		String result = "";
		
		if (providerItemBean.getProviderId() != null){
			result = StringUtil.getStringValue(
					providerItemBean.getProviderId().getProviderId()); 
		}
		return result;
		

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
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	
	
}
