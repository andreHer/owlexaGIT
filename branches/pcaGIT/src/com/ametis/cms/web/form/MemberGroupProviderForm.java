
package com.ametis.cms.web.form;

import org.springframework.web.multipart.MultipartFile;

import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.MemberGroupProvider;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * MemberGroupProvider is a mapping of member_group_provider Table.
*/
public class MemberGroupProviderForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewMemberGroupProvider = false;
	private MemberGroupProvider memberGroupProviderBean ;
	private String memberGroupName;
	private MultipartFile multipartFile;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public MemberGroupProviderForm()
    {
    	this.memberGroupProviderBean = new MemberGroupProvider();
    	this.isNewMemberGroupProvider = true;
    }
    public MemberGroupProviderForm (MemberGroupProvider object){
		this.memberGroupProviderBean = object;
    }
    public boolean isNewMemberGroupProvider (){

    	return this.isNewMemberGroupProvider;
    }
	public MemberGroupProvider getMemberGroupProvider (){
		return this.memberGroupProviderBean ;
	}
	public void setMemberGroupProvider (MemberGroupProvider object){
		this.memberGroupProviderBean = object;
	}

			
	public void setMemberGroupProviderId(String obj){

		memberGroupProviderBean.setMemberGroupProviderId(StringUtil.getIntegerValue(obj,0));

	}

	public String getMemberGroupProviderId(){
		return StringUtil.getStringValue(
		memberGroupProviderBean.getMemberGroupProviderId());

	}
	
										


	
	public void setMemberGroupId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			MemberGroup fk = new MemberGroup();
			fk.setMemberGroupId(StringUtil.getIntegerValue(obj,0));
			memberGroupProviderBean.setMemberGroupId(fk);
		}

	}

	public String getMemberGroupId(){
		String result = "";
		
		if (memberGroupProviderBean.getMemberGroupId() != null){
			result = StringUtil.getStringValue(
					memberGroupProviderBean.getMemberGroupId().getMemberGroupId());
		}
		return result;
		

	}
	//---
	
	

	
	public void setProviderId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Provider fk = new Provider();
			fk.setProviderId(StringUtil.getIntegerValue(obj,0));
			memberGroupProviderBean.setProviderId(fk);
		}

	}

	public String getProviderId(){
		String result = "";
		
		if (memberGroupProviderBean.getProviderId() != null){
			result = StringUtil.getStringValue(
					memberGroupProviderBean.getProviderId().getProviderId());
		}
		return result;
		

	}
	public String getMemberGroupName() {
		return memberGroupName;
	}
	public void setMemberGroupName(String memberGroupName) {
		this.memberGroupName = memberGroupName;
	}
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}
	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
 
	
}
