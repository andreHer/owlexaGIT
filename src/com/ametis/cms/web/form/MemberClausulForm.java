
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.Clausul;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberClausul;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * MemberClausul is a mapping of member_clausul Table.
*/
public class MemberClausulForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewMemberClausul = false;
	private MemberClausul memberClausulBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public MemberClausulForm()
    {
    	this.memberClausulBean = new MemberClausul();
    	this.isNewMemberClausul = true;
    }
    public MemberClausulForm (MemberClausul object){
		this.memberClausulBean = object;
    }
    public boolean isNewMemberClausul (){

    	return this.isNewMemberClausul;
    }
	public MemberClausul getMemberClausul (){
		return this.memberClausulBean ;
	}
	public void setMemberClausul (MemberClausul object){
		this.memberClausulBean = object;
	}

			
	public void setMemberClausulId(String obj){

		memberClausulBean.setMemberClausulId(StringUtil.getIntegerValue(obj,0));

	}

	public String getMemberClausulId(){
		return StringUtil.getStringValue(
		memberClausulBean.getMemberClausulId());

	}
	
				
	public void setMemberId(String obj){

		if (obj != null || !obj.equals("")){
			Member member = new Member();
			member.setMemberId(StringUtil.getIntegerValue(obj,0));
			memberClausulBean.setMemberId(member);
		}
	}

	public String getMemberId(){
		return StringUtil.getStringValue(
		memberClausulBean.getMemberId());

	}
	
				
	public void setClausulId(String obj){

		if (obj != null || !obj.equals("")){
			Clausul clausul = new Clausul();
			clausul.setClausulId(StringUtil.getIntegerValue(obj,0));
			memberClausulBean.setClausulId(clausul);
		}

	}

	public String getClausulId(){
		return StringUtil.getStringValue(
		memberClausulBean.getClausulId());

	}
	
				
	public void setCreatedTime(String obj){

		memberClausulBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		memberClausulBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		memberClausulBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		memberClausulBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		memberClausulBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		memberClausulBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		memberClausulBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		memberClausulBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		memberClausulBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		memberClausulBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		memberClausulBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		memberClausulBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		memberClausulBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		memberClausulBean.getDeletedStatus());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
