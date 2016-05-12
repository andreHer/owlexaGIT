
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.Dependent;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.Relationship;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * Dependent is a mapping of dependent Table.
*/
public class DependentForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewDependent = false;
	private Dependent dependentBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public DependentForm()
    {
    	this.dependentBean = new Dependent();
    	this.isNewDependent = true;
    }
    public DependentForm (Dependent object){
		this.dependentBean = object;
    }
    public boolean isNewDependent (){

    	return this.isNewDependent;
    }
	public Dependent getDependent (){
		return this.dependentBean ;
	}
	public void setDependent (Dependent object){
		this.dependentBean = object;
	}

			
	public void setDependentId(String obj){

		dependentBean.setDependentId(StringUtil.getIntegerValue(obj,0));

	}

	public String getDependentId(){
		return StringUtil.getStringValue(
		dependentBean.getDependentId());

	}
	
								public void setDependantName(String obj){

		dependentBean.setDependantName(new String(obj));

	}

	public String getDependantName(){
		return StringUtil.getStringValue(
		dependentBean.getDependantName());

	}
	
					public void setDependantNumber(String obj){

		dependentBean.setDependantNumber(new String(obj));

	}

	public String getDependantNumber(){
		return StringUtil.getStringValue(
		dependentBean.getDependantNumber());

	}
	
							
	public void setBirthdate(String obj){

		dependentBean.setBirthdate(java.sql.Date.valueOf(obj));

	}

	public String getBirthdate(){
		return StringUtil.getStringValue(
		dependentBean.getBirthdate());

	}

	
							
	public void setCreatedTime(String obj){

		dependentBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		dependentBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		dependentBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		dependentBean.getCreatedBy());

	}
	
					public void setModifiedBy(String obj){

		dependentBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		dependentBean.getModifiedBy());

	}
	
				
	public void setModifiedTime(String obj){

		dependentBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		dependentBean.getModifiedTime());

	}

	
					public void setDeletedBy(String obj){

		dependentBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		dependentBean.getDeletedBy());

	}
	
				
	public void setDeletedTime(String obj){

		dependentBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		dependentBean.getDeletedTime());

	}

	
				
	public void setDeletedStatus(String obj){

		dependentBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		dependentBean.getDeletedStatus());

	}
	
		

	// foreign affairs
	
	

	
	public void setStatus(String obj){
		SubscriptionStatus fk = new SubscriptionStatus();
		fk.setStatusId(StringUtil.getIntegerValue(obj,0));
		dependentBean.setStatus(fk);

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		dependentBean.getStatus().getStatusId());

	}
	//---
	
	

	
	public void setMemberId(Member obj){
		
		dependentBean.setMemberId(obj);

	}

	public Member getMemberId(){
		return dependentBean.getMemberId();

	}
	//---
	
	

	
	public void setRelationshipId(String obj){
		Relationship fk = new Relationship();
		fk.setRelationshipId(StringUtil.getIntegerValue(obj,0));
		dependentBean.setRelationshipId(fk);

	}

	public String getRelationshipId(){
		return StringUtil.getStringValue(
		dependentBean.getRelationshipId().getRelationshipId());

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
