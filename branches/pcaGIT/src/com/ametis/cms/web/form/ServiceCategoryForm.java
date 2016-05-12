
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.ServiceCategory;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * ServiceCategory is a mapping of service_category Table.
*/
public class ServiceCategoryForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewServiceCategory = false;
	private ServiceCategory serviceCategoryBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ServiceCategoryForm()
    {
    	this.serviceCategoryBean = new ServiceCategory();
    	this.isNewServiceCategory = true;
    }
    public ServiceCategoryForm (ServiceCategory object){
		this.serviceCategoryBean = object;
    }
    public boolean isNewServiceCategory (){

    	return this.isNewServiceCategory;
    }
	public ServiceCategory getServiceCategory (){
		return this.serviceCategoryBean ;
	}
	public void setServiceCategory (ServiceCategory object){
		this.serviceCategoryBean = object;
	}

			
	public void setServiceCategoryId(String obj){

		serviceCategoryBean.setServiceCategoryId(StringUtil.getIntegerValue(obj,0));

	}

	public String getServiceCategoryId(){
		return StringUtil.getStringValue(
		serviceCategoryBean.getServiceCategoryId());

	}
	
					public void setServiceCategoryName(String obj){

		serviceCategoryBean.setServiceCategoryName(new String(obj));

	}

	public String getServiceCategoryName(){
		return StringUtil.getStringValue(
		serviceCategoryBean.getServiceCategoryName());

	}
	
					public void setDescription(String obj){

		serviceCategoryBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		serviceCategoryBean.getDescription());

	}
	
					public void setServiceCode(String obj){

		serviceCategoryBean.setServiceCode(new String(obj));

	}

	public String getServiceCode(){
		return StringUtil.getStringValue(
		serviceCategoryBean.getServiceCode());

	}
	
				
	public void setCreatedTime(String obj){

		serviceCategoryBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		serviceCategoryBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		serviceCategoryBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		serviceCategoryBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		serviceCategoryBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		serviceCategoryBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		serviceCategoryBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		serviceCategoryBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		serviceCategoryBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		serviceCategoryBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		serviceCategoryBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		serviceCategoryBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		serviceCategoryBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		serviceCategoryBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
