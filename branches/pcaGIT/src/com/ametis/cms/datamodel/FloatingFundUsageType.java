package com.ametis.cms.datamodel;

import java.io.Serializable;

public class FloatingFundUsageType implements Serializable{

	public static int BOTH_CLAIM_TYPE = 3;
	public static int REIMBURSEMENT_ONLY_CLAIM_TYPE = 2;
	public static int CASHLESS_ONLY_CLAIM_TYPE = 1;
	
	private int floatingFundUsageId;
	private String floatingFundUsage;
	
	
	
	public FloatingFundUsageType() {
		
		// TODO Auto-generated constructor stub
	}
	public int getFloatingFundUsageId() {
		return floatingFundUsageId;
	}
	public void setFloatingFundUsageId(int floatingFundUsageId) {
		this.floatingFundUsageId = floatingFundUsageId;
	}
	public String getFloatingFundUsage() {
		return floatingFundUsage;
	}
	public void setFloatingFundUsage(String floatingFundUsage) {
		this.floatingFundUsage = floatingFundUsage;
	}
	
	
}
