package com.ametis.cms.dto;

import java.io.Serializable;

public class PaymentReportSummary implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private double claimChargeValue = 0.0;
	private double claimApprovedValue = 0.0;
	private double claimRejectedValue = 0.0;
	private double claimExGratiaValue = 0.0;
	private double claimExcessValue = 0.0;
	
	
	public PaymentReportSummary(){
		
	}


	public double getClaimChargeValue() {
		return claimChargeValue;
	}


	public void setClaimChargeValue(double claimChargeValue) {
		this.claimChargeValue = claimChargeValue;
	}


	public double getClaimApprovedValue() {
		return claimApprovedValue;
	}


	public void setClaimApprovedValue(double claimApprovedValue) {
		this.claimApprovedValue = claimApprovedValue;
	}


	public double getClaimRejectedValue() {
		return claimRejectedValue;
	}


	public void setClaimRejectedValue(double claimRejectedValue) {
		this.claimRejectedValue = claimRejectedValue;
	}


	public double getClaimExGratiaValue() {
		return claimExGratiaValue;
	}


	public void setClaimExGratiaValue(double claimExGratiaValue) {
		this.claimExGratiaValue = claimExGratiaValue;
	}


	public double getClaimExcessValue() {
		return claimExcessValue;
	}


	public void setClaimExcessValue(double claimExcessValue) {
		this.claimExcessValue = claimExcessValue;
	}
	
	public String toString(){
		String result = "";
		
		result += "CHARGE VALUE : " + this.claimChargeValue;
		result += "\nAPPROVED VALUE : " + this.claimApprovedValue;
		result += "\nREJECTED VALUE : " + this.claimRejectedValue;
		result += "\nEXCESS VALUE : " + this.claimExcessValue;
		result += "\nEX GRATIA VALUE: " + this.claimExGratiaValue;
		
		return result;
	}

}
