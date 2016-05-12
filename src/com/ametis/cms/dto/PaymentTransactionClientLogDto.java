package com.ametis.cms.dto;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class PaymentTransactionClientLogDto implements Serializable{

	private String claimNumber;
	private String noVoucherNumber;
	private String currency;
	private String bankName;
	private String accountName;
	private Timestamp paymentDate;
	private Integer bankCode;
	private Integer accountNumber;
	private Double amountPaid;
	public String getClaimNumber() {
		return claimNumber;
	}
	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}
	public String getNoVoucherNumber() {
		return noVoucherNumber;
	}
	public void setNoVoucherNumber(String noVoucherNumber) {
		this.noVoucherNumber = noVoucherNumber;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public Timestamp getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}
	public Integer getBankCode() {
		return bankCode;
	}
	public void setBankCode(Integer bankCode) {
		this.bankCode = bankCode;
	}
	public Integer getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Double getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}
	

	
}
