package com.ametis.cms.dto;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;

public class BankAccountDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String accountName;
	private Collection<BankAccountDto> bankAccounts;
	
	public Collection<BankAccountDto> getBankAccounts() {
		return bankAccounts;
	}
	public void setBankAccounts(Collection<BankAccountDto> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	
	
	
	
	
}
