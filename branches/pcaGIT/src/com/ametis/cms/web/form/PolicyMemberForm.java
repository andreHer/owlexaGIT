
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * PolicyMember is a mapping of policy_member Table.
*/
public class PolicyMemberForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewPolicyMember = false;
	private PolicyMember policyMemberBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public PolicyMemberForm()
    {
    	this.policyMemberBean = new PolicyMember();
    	this.isNewPolicyMember = true;
    }
    public PolicyMemberForm (PolicyMember object){
		this.policyMemberBean = object;
    }
    public boolean isNewPolicyMember (){

    	return this.isNewPolicyMember;
    }
	public PolicyMember getPolicyMember (){
		return this.policyMemberBean ;
	}
	public void setPolicyMember (PolicyMember object){
		this.policyMemberBean = object;
	}

			
	public void setPolicyMemberId(String obj){

		policyMemberBean.setPolicyMemberId(StringUtil.getLongValue(obj,0));

	}

	public String getPolicyMemberId(){
		return StringUtil.getStringValue(
		policyMemberBean.getPolicyMemberId());

	}
	
										
	public void setJoinDate(String obj){

		policyMemberBean.setJoinDate(java.sql.Date.valueOf(obj));

	}

	public String getJoinDate(){
		return StringUtil.getStringValue(
		policyMemberBean.getJoinDate());

	}

	
				
	public void setEffectiveDate(String obj){

		policyMemberBean.setEffectiveDate(java.sql.Date.valueOf(obj));

	}

	public String getEffectiveDate(){
		return StringUtil.getStringValue(
		policyMemberBean.getEffectiveDate());

	}

	
				
	public void setExpireDate(String obj){

		policyMemberBean.setExpireDate(java.sql.Date.valueOf(obj));

	}

	public String getExpireDate(){
		return StringUtil.getStringValue(
		policyMemberBean.getExpireDate());

	}

	
				
	public void setResignedDate(String obj){

		policyMemberBean.setResignedDate(java.sql.Date.valueOf(obj));

	}

	public String getResignedDate(){
		return StringUtil.getStringValue(
		policyMemberBean.getResignedDate());

	}

	
				
	public void setStatus(String obj){

		policyMemberBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		policyMemberBean.getStatus());

	}
	
		

	// foreign affairs
	
	

	
	public void setPolicyId(String obj){

		Policy fk = new Policy();
		fk.setPolicyId(StringUtil.getIntegerValue(obj,0));
		policyMemberBean.setPolicyId(fk);

	}

	public String getPolicyId(){
		return StringUtil.getStringValue(
		policyMemberBean.getPolicyId().getPolicyId());

	}
	//---
	
	

	
	public void setMemberId(String obj){
		Member fk = new Member();
		fk.setMemberId(StringUtil.getIntegerValue(obj,0));
		policyMemberBean.setMemberId(fk);

	}

	public String getMemberId(){
		return StringUtil.getStringValue(
		policyMemberBean.getMemberId().getMemberId());

	}
	//---
		// -- foreign affairs end

// class+ 

// class- 
}
