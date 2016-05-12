
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * ProviderSet is a mapping of provider_set Table.
*/
public class ProviderSetForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewProviderSet = false;
	private ProviderSet providerSetBean ;
	private String clientName;
	private String groupName;
	
	
    public ProviderSetForm()
    {
    	this.providerSetBean = new ProviderSet();
    	this.isNewProviderSet = true;
    }
    public ProviderSetForm (ProviderSet object){
		this.providerSetBean = object;
    }
    public boolean isNewProviderSet (){

    	return this.isNewProviderSet;
    }
	public ProviderSet getProviderSet (){
		return this.providerSetBean ;
	}
	public void setProviderSet (ProviderSet object){
		this.providerSetBean = object;
	}

			
	public void setProviderSetId(String obj){

		providerSetBean.setProviderSetId(StringUtil.getIntegerValue(obj,0));

	}

	public String getProviderSetId(){
		return StringUtil.getStringValue(
		providerSetBean.getProviderSetId());

	}
	
					public void setProviderSetName(String obj){

		providerSetBean.setProviderSetName(new String(obj));

	}

	public String getProviderSetName(){
		return StringUtil.getStringValue(
		providerSetBean.getProviderSetName());

	}
	
					public void setProviderSetCode(String obj){

		providerSetBean.setProviderSetCode(new String(obj));

	}

	public String getProviderSetCode(){
		return StringUtil.getStringValue(
		providerSetBean.getProviderSetCode());

	}
	
					public void setDescription(String obj){

		providerSetBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		providerSetBean.getDescription());

	}
	
				
	public void setStatus(String obj){

		providerSetBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		providerSetBean.getStatus());

	}
	
				
	public void setClientId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Client client = new Client();
			client.setClientId(Integer.valueOf(obj));
			providerSetBean.setClientId(client);
		}

	}

	public String getClientId(){
		String result = "";
		
		if (providerSetBean.getClientId() != null){
			result =StringUtil.getStringValue(
					providerSetBean.getClientId().getClientId()); 
		}
		return result;
		

	}
	
				
	public void setMemberGroupId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			MemberGroup group = new MemberGroup();
			group.setMemberGroupId(Integer.valueOf(obj));
			providerSetBean.setMemberGroupId(group);
		}

	}

	public String getMemberGroupId(){
		String result = "";
		
		if (providerSetBean.getMemberGroupId() != null){
			result = StringUtil.getStringValue(
					providerSetBean.getMemberGroupId().getMemberGroupId());
		}
		return result;
		

	}
	
				
	public void setCreatedTime(String obj){

		providerSetBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		providerSetBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		providerSetBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		providerSetBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		providerSetBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		providerSetBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		providerSetBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		providerSetBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		providerSetBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		providerSetBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		providerSetBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		providerSetBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		providerSetBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		providerSetBean.getDeletedStatus());

	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
