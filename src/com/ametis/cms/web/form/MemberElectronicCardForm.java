
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * MemberElectronicCard is a mapping of member_electronic_card Table.
*/
public class MemberElectronicCardForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewMemberElectronicCard = false;
	private MemberElectronicCard memberElectronicCardBean ;
	private String memberName;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public MemberElectronicCardForm()
    {
    	this.memberElectronicCardBean = new MemberElectronicCard();
    	this.isNewMemberElectronicCard = true;
    }
    public MemberElectronicCardForm (MemberElectronicCard object){
		this.memberElectronicCardBean = object;
    }
    public boolean isNewMemberElectronicCard (){

    	return this.isNewMemberElectronicCard;
    }
	public MemberElectronicCard getMemberElectronicCard (){
		return this.memberElectronicCardBean ;
	}
	public void setMemberElectronicCard (MemberElectronicCard object){
		this.memberElectronicCardBean = object;
	}

			
	public void setId(String obj){

		memberElectronicCardBean.setId(StringUtil.getIntegerValue(obj,0));

	}

	public String getId(){
		return StringUtil.getStringValue(
		memberElectronicCardBean.getId());

	}
	
				
	public void setMemberId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Member member = new Member();
			member.setMemberId(Integer.valueOf(obj));
			memberElectronicCardBean.setMemberId(member);
		}

	}

	public String getMemberId(){
		String result = "";
		
		if (memberElectronicCardBean.getMemberId() != null){
			result = StringUtil.getStringValue(
					memberElectronicCardBean.getMemberId().getMemberId());
		}
		
		return result;
		

	}
	
					public void setCardNumber(String obj){

		memberElectronicCardBean.setCardNumber(new String(obj));

	}

	public String getCardNumber(){
		return StringUtil.getStringValue(
		memberElectronicCardBean.getCardNumber());

	}
	
				
	public void setRegistrationDate(String obj){

		memberElectronicCardBean.setRegistrationDate(java.sql.Date.valueOf(obj));

	}

	public String getRegistrationDate(){
		return StringUtil.getStringValue(
		memberElectronicCardBean.getRegistrationDate());

	}

	
				
	public void setPolicyId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Policy policy = new Policy();
			policy.setPolicyId(Integer.valueOf(obj));
			memberElectronicCardBean.setPolicyId(policy);
		}

	}

	public String getPolicyId(){
		String result = "";
		
		if (memberElectronicCardBean.getPolicyId()!= null){
			result = StringUtil.getStringValue(
					memberElectronicCardBean.getPolicyId().getPolicyId());
		}
		return result;

	}
	
					public void setMemberNumber(String obj){

		memberElectronicCardBean.setMemberNumber(new String(obj));

	}

	public String getMemberNumber(){
		return StringUtil.getStringValue(
		memberElectronicCardBean.getMemberNumber());

	}
	
				
	public void setCreatedTime(String obj){

		memberElectronicCardBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		memberElectronicCardBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		memberElectronicCardBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		memberElectronicCardBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		memberElectronicCardBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		memberElectronicCardBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		memberElectronicCardBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		memberElectronicCardBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		memberElectronicCardBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		memberElectronicCardBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		memberElectronicCardBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		memberElectronicCardBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		memberElectronicCardBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		memberElectronicCardBean.getDeletedStatus());

	}
	
				
	public void setCardStatus(String obj){

		memberElectronicCardBean.setCardStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getCardStatus(){
		return StringUtil.getStringValue(
		memberElectronicCardBean.getCardStatus());

	}
	
					public void setDescription(String obj){

		memberElectronicCardBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		memberElectronicCardBean.getDescription());

	}
	
				
	public void setReplacedCardId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			MemberElectronicCard card = new MemberElectronicCard();
			card.setId(Integer.valueOf(obj));
			memberElectronicCardBean.setReplacedCardId(card);
		}

	}

	public String getReplacedCardId(){
		String result = "";
		if (memberElectronicCardBean.getReplacedCardId() != null){
			result = StringUtil.getStringValue(
					memberElectronicCardBean.getReplacedCardId().getId());
		}
		
		return result;
		

	}
	
				
	public void setCardType(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			CardType cardType = new CardType();
			cardType.setCardTypeId(Integer.valueOf(obj));
			memberElectronicCardBean.setCardType(cardType);	
		}
		

	}

	public String getCardType(){
		String result = "";
		
		if (memberElectronicCardBean.getCardType() != null){
			result = StringUtil.getStringValue(
					memberElectronicCardBean.getCardType().getCardTypeId());
		}
		return result;

	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 

}
