
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.ServiceDiagnosis;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * ServiceDiagnosis is a mapping of service_diagnosis Table.
*/
public class ServiceDiagnosisForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewServiceDiagnosis = false;
	private ServiceDiagnosis serviceDiagnosisBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ServiceDiagnosisForm()
    {
    	this.serviceDiagnosisBean = new ServiceDiagnosis();
    	this.isNewServiceDiagnosis = true;
    }
    public ServiceDiagnosisForm (ServiceDiagnosis object){
		this.serviceDiagnosisBean = object;
    }
    public boolean isNewServiceDiagnosis (){

    	return this.isNewServiceDiagnosis;
    }
	public ServiceDiagnosis getServiceDiagnosis (){
		return this.serviceDiagnosisBean ;
	}
	public void setServiceDiagnosis (ServiceDiagnosis object){
		this.serviceDiagnosisBean = object;
	}

			
	public void setServiceDiagnosisId(String obj){

		serviceDiagnosisBean.setServiceDiagnosisId(StringUtil.getIntegerValue(obj,0));

	}

	public String getServiceDiagnosisId(){
		return StringUtil.getStringValue(
		serviceDiagnosisBean.getServiceDiagnosisId());

	}
	
				
	public void setServiceCategoryId(String obj){

		serviceDiagnosisBean.setServiceCategoryId(StringUtil.getIntegerValue(obj,0));

	}

	public String getServiceCategoryId(){
		return StringUtil.getStringValue(
		serviceDiagnosisBean.getServiceCategoryId());

	}
	
				
	public void setDiagnosisId(String obj){

		serviceDiagnosisBean.setDiagnosisId(StringUtil.getIntegerValue(obj,0));

	}

	public String getDiagnosisId(){
		return StringUtil.getStringValue(
		serviceDiagnosisBean.getDiagnosisId());

	}
	
				
	public void setCreatedTime(String obj){

		serviceDiagnosisBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		serviceDiagnosisBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		serviceDiagnosisBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		serviceDiagnosisBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		serviceDiagnosisBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		serviceDiagnosisBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		serviceDiagnosisBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		serviceDiagnosisBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		serviceDiagnosisBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		serviceDiagnosisBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		serviceDiagnosisBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		serviceDiagnosisBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		serviceDiagnosisBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		serviceDiagnosisBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
