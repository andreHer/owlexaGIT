
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.Bank;
import com.ametis.cms.datamodel.BankAccount;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.Currency;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.util.StringUtil;

// imports- 


/**
 * BankAccount is a mapping of bank_account Table.
*/
public class BankAccountForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewBankAccount = false;
    private String tipe;
    private String refId;
    private String previousURL;
    private String bankName;
        
	private BankAccount bankAccountBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public BankAccountForm()
    {
    	this.bankAccountBean = new BankAccount();
    	this.isNewBankAccount = true;
    }
    public BankAccountForm (BankAccount object){
		this.bankAccountBean = object;
    }
    public boolean isNewBankAccount (){

    	return this.isNewBankAccount;
    }
	public BankAccount getBankAccount (){
		return this.bankAccountBean ;
	}
	public void setBankAccount (BankAccount object){
		this.bankAccountBean = object;
	}

			
	public void setId(String obj){

		bankAccountBean.setId(StringUtil.getIntegerValue(obj,0));

	}

	public String getId(){
		return StringUtil.getStringValue(
		bankAccountBean.getId());

	}
	
					public void setAccount(String obj){

		bankAccountBean.setBankAccount(new String(obj));

	}

	public String getAccount(){
		return StringUtil.getStringValue(
		bankAccountBean.getBankAccount());

	}
	
					public void setBank(String obj){

		bankAccountBean.setBank(obj);

	}

	public String getBank(){
		return StringUtil.getStringValue(
		bankAccountBean.getBank());

	}
	
					public void setBankBranch(String obj){

		bankAccountBean.setBankBranch(new String(obj));

	}

	public String getBankBranch(){
		return StringUtil.getStringValue(
		bankAccountBean.getBankBranch());

	}
	
														public void setAccountName(String obj){

		bankAccountBean.setAccountName(new String(obj));

	}

	public String getAccountName(){
		return StringUtil.getStringValue(
		bankAccountBean.getAccountName());

	}
	
								

	// foreign affairs
	
	

	
	public void setBankId(String obj){
            if (obj != null && !obj.equalsIgnoreCase("")){
            
		Bank fk = new Bank();
		fk.setBankId(StringUtil.getIntegerValue(obj,0));
		bankAccountBean.setBankId(fk);
            }

	}

	public String getBankId(){
            String result = "";
            
            if (bankAccountBean.getBankId() != null){
		result =  StringUtil.getStringValue(
		bankAccountBean.getBankId().getBankId());
            }
                
                return result;

	}
	//---
	
	

	
	public void setClientId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Client fk = new Client();
			fk.setClientId(StringUtil.getIntegerValue(obj,0));
			bankAccountBean.setClientId(fk);
		}

	}

	public String getClientId(){
		String result = "";
		
		if (bankAccountBean.getClientId() != null){
			result = StringUtil.getStringValue(
					bankAccountBean.getClientId().getClientId());
		}
		
		return result;
		

	}
	//---
	
	

	
	public void setMemberGroupId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			MemberGroup fk = new MemberGroup();
			fk.setMemberGroupId(StringUtil.getIntegerValue(obj,0));
			bankAccountBean.setMemberGroupId(fk);
		}

	}

	public String getMemberGroupId(){
		String result = "";
		
		if (bankAccountBean.getMemberGroupId() != null){
			result = StringUtil.getStringValue(
					bankAccountBean.getMemberGroupId().getMemberGroupId());
		}
		
		return result;
		

	}
	//---
	
	

	
	public void setMemberId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Member fk = new Member();
			fk.setMemberId(StringUtil.getIntegerValue(obj,0));
			bankAccountBean.setMemberId(fk);
		}

	}

	public String getMemberId(){
		String result = "";
		
		if (bankAccountBean.getMemberId() != null){
			result = StringUtil.getStringValue(
					bankAccountBean.getMemberId().getMemberId());
		}
		return result;
		

	}
	//---
	
	

	public void setCurrencyId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Currency fk = new Currency();
			fk.setCurrencyId(StringUtil.getIntegerValue(obj,0));
			bankAccountBean.setCurrencyId(fk);
		}

	}

	public String getCurrencyId(){
		
		String result = "";
		
		if (bankAccountBean.getCurrencyId() != null){
			result =StringUtil.getStringValue(
					bankAccountBean.getCurrencyId().getCurrencyId()); 
		}
		
		return result;
	}
	
	public void setProviderId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Provider fk = new Provider();
			fk.setProviderId(StringUtil.getIntegerValue(obj,0));
			bankAccountBean.setProviderId(fk);
		}

	}

	public String getProviderId(){
		
		String result = "";
		
		if (bankAccountBean.getProviderId() != null){
			result =StringUtil.getStringValue(
					bankAccountBean.getProviderId().getProviderId()); 
		}
		
		return result;
	}
	//---
		// -- foreign affairs end

        
// class+ 

// class-
	
    public String getPaymentSource() {
    	String result = "";
    	if (bankAccountBean.getIsSourcePayment() != null){
    		result = bankAccountBean.getIsSourcePayment().toString();
    	}
        return result;
    }

    public void setPaymentSource(String obj) {
    	if (obj != null && !obj.equalsIgnoreCase("")){
    		bankAccountBean.setIsSourcePayment(Integer.valueOf(obj));
    	}
    	else {
    		bankAccountBean.setIsSourcePayment(0);
    	}
    }

    public String getPreviousURL() {
        return previousURL;
    }

    public void setPreviousURL(String previousURL) {
        this.previousURL = previousURL;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
    
}
