
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.GroupProduct;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.Product;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * GroupProduct is a mapping of group_product Table.
*/
public class GroupProductForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewGroupProduct = false;
	private GroupProduct groupProductBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public GroupProductForm()
    {
    	this.groupProductBean = new GroupProduct();
    	this.isNewGroupProduct = true;
    }
    public GroupProductForm (GroupProduct object){
		this.groupProductBean = object;
    }
    public boolean isNewGroupProduct (){

    	return this.isNewGroupProduct;
    }
	public GroupProduct getGroupProduct (){
		return this.groupProductBean ;
	}
	public void setGroupProduct (GroupProduct object){
		this.groupProductBean = object;
	}

			
	public void setGroupProductId(String obj){

		groupProductBean.setGroupProductId(StringUtil.getIntegerValue(obj,0));

	}

	public String getGroupProductId(){
		return StringUtil.getStringValue(
		groupProductBean.getGroupProductId());

	}
	
										
	public void setCreatedTime(String obj){

		groupProductBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		groupProductBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		groupProductBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		groupProductBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		groupProductBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		groupProductBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		groupProductBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		groupProductBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		groupProductBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		groupProductBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		groupProductBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		groupProductBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		groupProductBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		groupProductBean.getDeletedStatus());

	}
	
					

	// foreign affairs
	
	

	
	//---
	
	

	
	public void setProductId(String obj){
		Product fk = new Product();
		fk.setProductId(StringUtil.getIntegerValue(obj,0));
		groupProductBean.setProductId(fk);

	}

	public String getProductId(){
		return StringUtil.getStringValue(
		groupProductBean.getProductId().getProductId());

	}
	//---
	
	

	
	public void setMemberGroupId(String obj){
		MemberGroup fk = new MemberGroup();
		fk.setMemberGroupId(StringUtil.getIntegerValue(obj,0));
		groupProductBean.setMemberGroupId(fk);

	}

	public String getMemberGroupId(){
		return StringUtil.getStringValue(
		groupProductBean.getMemberGroupId().getMemberGroupId());

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
