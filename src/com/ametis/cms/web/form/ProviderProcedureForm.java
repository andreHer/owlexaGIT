
package com.ametis.cms.web.form;

import java.math.BigDecimal;

import javax.persistence.Column;

import org.springframework.web.multipart.MultipartFile;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * ProviderProcedure is a mapping of provider_procedure Table.
*/
public class ProviderProcedureForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewProviderProcedure = false;
	private ProviderProcedure providerProcedureBean ;
	private String providerName;
	private String procedureName;
	private MultipartFile procedureFile;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ProviderProcedureForm()
    {
    	this.providerProcedureBean = new ProviderProcedure();
    	this.isNewProviderProcedure = true;
    }
    public ProviderProcedureForm (ProviderProcedure object){
		this.providerProcedureBean = object;
    }
    public boolean isNewProviderProcedure (){

    	return this.isNewProviderProcedure;
    }
	public ProviderProcedure getProviderProcedure (){
		return this.providerProcedureBean ;
	}
	public void setProviderProcedure (ProviderProcedure object){
		this.providerProcedureBean = object;
	}

	
			
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public String getProcedureName() {
		return procedureName;
	}
	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}
	public void setProviderProcedureId(String obj){

		providerProcedureBean.setProviderProcedureId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProviderProcedureId(){
		return StringUtil.getStringValue(
		providerProcedureBean.getProviderProcedureId());

	}
	
				
	public void setProviderId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Provider provider = new Provider();
			provider.setProviderId(Integer.valueOf(obj));
			providerProcedureBean.setProviderId(provider);
		}

	}

	public String getProviderId(){
		String result = "";
		
		if (providerProcedureBean.getProviderId() != null){
			result = StringUtil.getStringValue(
					providerProcedureBean.getProviderId().getProviderId());
		}
		return result;
		

	}
	
				
	public void setProcedureId(String obj){

		Procedure procedure = new Procedure();
		procedure.setProcedureId(Integer.valueOf(obj));
		providerProcedureBean.setProcedureId(procedure);

	}

	public String getProcedureId(){
		return StringUtil.getStringValue(
		providerProcedureBean.getProcedureId().getProcedureId());

	}
	
				
	public void setAverageLengthOfStay(String obj){

		providerProcedureBean.setAverageLengthOfStay(StringUtil.getIntegerValue(obj,0));

	}

	public String getAverageLengthOfStay(){
		return StringUtil.getStringValue(
		providerProcedureBean.getAverageLengthOfStay());

	}
	
				
	public void setAverageCost(String obj){

		providerProcedureBean.setAverageCost(StringUtil.getDoubleValue(obj,0));

	}

	public String getAverageCost(){
		return StringUtil.getStringValue(
		providerProcedureBean.getAverageCost());

	}
	
				
	public void setVersionDate(String obj){

		providerProcedureBean.setVersionDate(java.sql.Date.valueOf(obj));

	}

	public String getVersionDate(){
		return StringUtil.getStringValue(
		providerProcedureBean.getVersionDate());

	}

	
				
	public void setStatus(String obj){

		providerProcedureBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		providerProcedureBean.getStatus());

	}
	
				
	public void setProcedureClass(String obj){


		if (obj != null && !obj.equalsIgnoreCase("")){
			TreatmentClass treatmentClass = new TreatmentClass();
			treatmentClass.setTreatmentClassId(Integer.valueOf(obj));
			providerProcedureBean.setProcedureClass(treatmentClass);
		}

	}

	public String getProcedureClass(){
		String result = "";
		if (providerProcedureBean.getProcedureClass() != null){
			result = StringUtil.getStringValue(
						providerProcedureBean.getProcedureClass().getTreatmentClassId());
		}
		return result;

	}
	
	
	public String getC1() {
		String result = "";
		if (providerProcedureBean.getC1() != null){
			BigDecimal bigDec = new BigDecimal(providerProcedureBean.getC1());
			
			result  = bigDec.toPlainString();
		}
		return result;
	}
	public void setC1(String c1) {
		if (c1 != null && !c1.equalsIgnoreCase("")){
			this.providerProcedureBean.setSvip(Double.valueOf(c1));
		}
	}
	
	public String getC2() {
		String result = "";
		if (providerProcedureBean.getC2() != null){
			BigDecimal bigDec = new BigDecimal(providerProcedureBean.getC2());
			
			result  = bigDec.toPlainString();
		}
		return result;
		
	}
	public void setC2(String c2) {
		if (c2 != null && !c2.equalsIgnoreCase("")){
			this.providerProcedureBean.setSvip(Double.valueOf(c2));
		}
	}
	
	public String getC3() {
		String result = "";
		if (providerProcedureBean.getC3() != null){
			BigDecimal bigDec = new BigDecimal(providerProcedureBean.getC3());
			
			result  = bigDec.toPlainString();
		}
		return result;
	}
	public void setC3(String c3) {
		if (c3 != null && !c3.equalsIgnoreCase("")){
			this.providerProcedureBean.setSvip(Double.valueOf(c3));
		}
	}
	
	public String getVip() {
		String result = "";
		if (providerProcedureBean.getVip() != null){
			BigDecimal bigDec = new BigDecimal(providerProcedureBean.getVip());
			
			result  = bigDec.toPlainString();
		}
		return result;
	}
	public void setVip(String vip) {
		if (vip != null && !vip.equalsIgnoreCase("")){
			this.providerProcedureBean.setSvip(Double.valueOf(vip));
		}
	}
	
	public String getSvip() {
		
		String result = "";
		if (providerProcedureBean.getSvip() != null){
			BigDecimal bigDec = new BigDecimal(providerProcedureBean.getSvip());
			
			result  = bigDec.toPlainString();
		}
		return result;
	}
	public void setSvip(String svip) {
		if (svip != null && !svip.equalsIgnoreCase("")){
			this.providerProcedureBean.setSvip(Double.valueOf(svip));
		}
	}
	public String getRj() {
		String result = "";
		if (providerProcedureBean.getRj() != null){
			BigDecimal bigDec = new BigDecimal(providerProcedureBean.getRj());
			
			result  = bigDec.toPlainString();
		}
		return result;
	}
	public void setRj(String svip) {
		if (svip != null && !svip.equalsIgnoreCase("")){
			this.providerProcedureBean.setRj(Double.valueOf(svip));
		}
	}
	public MultipartFile getProcedureFile() {
		return procedureFile;
	}
	public void setProcedureFile(MultipartFile procedureFile) {
		this.procedureFile = procedureFile;
	}

	
}
