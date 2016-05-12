
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.ClientCategory;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * ClientCategory is a mapping of client_category Table.
*/
public class ClientCategoryForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewClientCategory = false;
	private ClientCategory clientCategoryBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ClientCategoryForm()
    {
    	this.clientCategoryBean = new ClientCategory();
    	this.isNewClientCategory = true;
    }
    public ClientCategoryForm (ClientCategory object){
		this.clientCategoryBean = object;
    }
    public boolean isNewClientCategory (){

    	return this.isNewClientCategory;
    }
	public ClientCategory getClientCategory (){
		return this.clientCategoryBean ;
	}
	public void setClientCategory (ClientCategory object){
		this.clientCategoryBean = object;
	}

			
	public void setClientCategoryId(String obj){

		clientCategoryBean.setClientCategoryId(StringUtil.getIntegerValue(obj,0));

	}

	public String getClientCategoryId(){
		return StringUtil.getStringValue(
		clientCategoryBean.getClientCategoryId());

	}
	
					public void setClientCategoryName(String obj){

		clientCategoryBean.setClientCategoryName(new String(obj));

	}

	public String getClientCategoryName(){
		return StringUtil.getStringValue(
		clientCategoryBean.getClientCategoryName());

	}
	
					public void setClientCategoryCode(String obj){

		clientCategoryBean.setClientCategoryCode(new String(obj));

	}

	public String getClientCategoryCode(){
		return StringUtil.getStringValue(
		clientCategoryBean.getClientCategoryCode());

	}
	
					public void setDescription(String obj){

		clientCategoryBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		clientCategoryBean.getDescription());

	}
	
				
	public void setCreatedTime(String obj){

		clientCategoryBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		clientCategoryBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		clientCategoryBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		clientCategoryBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		clientCategoryBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		clientCategoryBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		clientCategoryBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		clientCategoryBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		clientCategoryBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		clientCategoryBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		clientCategoryBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		clientCategoryBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		clientCategoryBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		clientCategoryBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
