
package com.ametis.cms.web.form;

import org.springframework.web.multipart.MultipartFile;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * ProviderDoctor is a mapping of provider_doctor Table.
*/
public class ProviderDoctorForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewProviderDoctor = false;
	private ProviderDoctor providerDoctorBean ;
	private String providerName;
	private MultipartFile multipartFile;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ProviderDoctorForm()
    {
    	this.providerDoctorBean = new ProviderDoctor();
    	this.isNewProviderDoctor = true;
    }
    public ProviderDoctorForm (ProviderDoctor object){
		this.providerDoctorBean = object;
    }
    public boolean isNewProviderDoctor (){

    	return this.isNewProviderDoctor;
    }
	public ProviderDoctor getProviderDoctor(){
		return this.providerDoctorBean ;
	}
	public void setProviderDoctor (ProviderDoctor object){
		this.providerDoctorBean = object;
	}

	public void setProviderDoctorId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			providerDoctorBean.setProviderDoctorId(Integer.valueOf(obj));
		}

	}

	public String getProviderDoctorId(){
		return StringUtil.getStringValue(
		providerDoctorBean.getProviderDoctorId());

	}

	
					public void setDoctorName(String obj){

		providerDoctorBean.setDoctorName(new String(obj));

	}

	public String getDoctorName(){
		return StringUtil.getStringValue(
		providerDoctorBean.getDoctorName());

	}
	
	public void setIdNumber(String obj){

		providerDoctorBean.setIdNumber(new String(obj));

	}

	public String getIdNumber(){
		return StringUtil.getStringValue(
		providerDoctorBean.getIdNumber());

	}
	public void setPoliklinikId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Poliklinik poliklinik = new Poliklinik();
			poliklinik.setPoliklinikId(Integer.valueOf(obj));
			providerDoctorBean.setProviderPoliklinikId(poliklinik);
		}

	}

	public String getPoliklinikId(){
		String result = "";
		if (providerDoctorBean.getProviderPoliklinikId() != null){
			result = providerDoctorBean.getProviderPoliklinikId().getPoliklinikId().toString();
		}		
		return result;

	}
	public void setProviderId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Provider provider = new Provider();
			provider.setProviderId(Integer.valueOf(obj));
			providerDoctorBean.setProviderId(provider);
		}

	}

	public String getProviderId(){
		String result = "";
		if (providerDoctorBean.getProviderId() != null){
			result = providerDoctorBean.getProviderId().getProviderId().toString();
		}		
		return result;

	}
	public void setDescription(String obj){

		providerDoctorBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		providerDoctorBean.getDescription());

	}
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}
	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
	public String getMonday() {
		return providerDoctorBean.getMonday();
	}
	public void setMonday(String day) {
		providerDoctorBean.setMonday(day);
	}
	public String getTuesday() {
		return providerDoctorBean.getTuesday();
	}
	public void setTuesday(String day) {
		providerDoctorBean.setTuesday(day);
	}
	public String getWednesday() {
		return providerDoctorBean.getWednesday();
	}
	public void setWednesday(String day) {
		providerDoctorBean.setWednesday(day);
	}
	public String getThursday() {
		return providerDoctorBean.getThursday();
	}
	public void setThursday(String day) {
		providerDoctorBean.setThursday(day);
	}
	public String getFriday() {
		return providerDoctorBean.getFriday();
	}
	public void setFriday(String day) {
		providerDoctorBean.setFriday(day);
	}
	public String getSaturday() {
		return providerDoctorBean.getSaturday();
	}
	public void setSaturday(String day) {
		providerDoctorBean.setSaturday(day);
	}
	public String getSunday() {
		return providerDoctorBean.getSunday();
	}
	public void setSunday(String day) {
		providerDoctorBean.setSunday(day);
	}
	
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
			
	
}
