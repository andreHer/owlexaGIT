
package com.ametis.cms.web.form;

import org.springframework.web.multipart.MultipartFile;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * ProviderMedicine is a mapping of provider_medicine Table.
*/
public class ProviderMedicineForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewProviderMedicine = false;
	private ProviderMedicine providerMedicineBean ;
	private String providerName;
	private MultipartFile medicineFile; 
	private String medicineName;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ProviderMedicineForm()
    {
    	this.providerMedicineBean = new ProviderMedicine();
    	this.isNewProviderMedicine = true;
    }
    public ProviderMedicineForm (ProviderMedicine object){
		this.providerMedicineBean = object;
    }
    public boolean isNewProviderMedicine (){

    	return this.isNewProviderMedicine;
    }
	public ProviderMedicine getProviderMedicine (){
		return this.providerMedicineBean ;
	}
	public void setProviderMedicine (ProviderMedicine object){
		this.providerMedicineBean = object;
	}

			
	public void setProviderMedicineId(String obj){

		providerMedicineBean.setProviderMedicineId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProviderMedicineId(){
		return StringUtil.getStringValue(
		providerMedicineBean.getProviderMedicineId());

	}
	

				
	public void setSvip(String obj){

		providerMedicineBean.setSvip(StringUtil.getDoubleValue(obj,0));

	}

	public String getSvip(){
		return StringUtil.getStringValue(
		providerMedicineBean.getSvip());

	}
	public void setVip(String obj){

		providerMedicineBean.setVip(StringUtil.getDoubleValue(obj,0));

	}

	public String getVip(){
		return StringUtil.getStringValue(
		providerMedicineBean.getVip());

	}
	public void setKelas1(String obj){

		providerMedicineBean.setKelas1(StringUtil.getDoubleValue(obj,0));

	}

	public String getKelas1(){
		return StringUtil.getStringValue(
		providerMedicineBean.getKelas1());

	}
	public void setKelas2(String obj){

		providerMedicineBean.setKelas2(StringUtil.getDoubleValue(obj,0));

	}

	public String getKelas2(){
		return StringUtil.getStringValue(
		providerMedicineBean.getKelas2());

	}
	public void setKelas3(String obj){

		providerMedicineBean.setKelas3(StringUtil.getDoubleValue(obj,0));

	}

	public String getKelas3(){
		return StringUtil.getStringValue(
		providerMedicineBean.getKelas3());

	}
	public void setItemValue(String obj){

		providerMedicineBean.setItemValue(StringUtil.getDoubleValue(obj,0));

	}

	public String getItemValue(){
		return StringUtil.getStringValue(
		providerMedicineBean.getItemValue());

	}							

	// foreign affairs
	
	

	
	public void setProviderId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Provider fk = new Provider();
			fk.setProviderId(StringUtil.getIntegerValue(obj,0));
			providerMedicineBean.setProviderId(fk);
		}

	}

	public String getProviderId(){
		String result = "";
		
		if (providerMedicineBean.getProviderId() != null){
			result = StringUtil.getStringValue(
					providerMedicineBean.getProviderId().getProviderId());
		}
		return result;

	}
	public void setMedicineId(String obj){
		
		if (obj != null && !obj.equalsIgnoreCase("")){
			Medicine fk = new Medicine();
			fk.setMedicineId(StringUtil.getIntegerValue(obj,0));
			providerMedicineBean.setMedicineId(fk);
		}

	}

	public String getMedicineId(){
		String result = "";
		
		if (providerMedicineBean.getMedicineId() != null){
			result = StringUtil.getStringValue(
					providerMedicineBean.getMedicineId().getMedicineId());

		}
		return result;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public MultipartFile getMedicineFile() {
		return medicineFile;
	}
	public void setMedicineFile(MultipartFile medicineFile) {
		this.medicineFile = medicineFile;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
 
	
}
