package com.ametis.cms.datamodel;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

public class PaymentResult implements Serializable{

	private String paymentVoucherNumber;
	private Integer statusProses;
	private String errorMessage;
	
	public String getPaymentVoucherNumber() {
		return paymentVoucherNumber;
	}

	public void setPaymentVoucherNumber(String paymentVoucherNumber) {
		this.paymentVoucherNumber = paymentVoucherNumber;
	}

	

	public Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}

	private Timestamp dateTime;
	private String reason;
	
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getStatusProses() {
		return statusProses;
	}

	public void setStatusProses(Integer statusProses) {
		this.statusProses = statusProses;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	
}
