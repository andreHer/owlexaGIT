
package com.ametis.cms.dto;

import java.io.Serializable;

public class ClaimExchangeDetailDto implements Serializable{

	private Integer claimId;
	private String benefitCode;
	private String benefitDescription;
	private Double qtyBenefit;
	private Double amountIncurred;
	private Double amountApproved;
	private Double amountNotApproved;
	private Double amountExcessPaid;
	private Double amountExcessNotPaid;
	private Double amountRefund;
	private Double amountPaidToProv;
	private Double amountDiscount;
	private String remarksCode;
	private String remarks;
	
	public Integer getClaimId() {
		return claimId;
	}
	public void setClaimId(Integer claimId) {
		this.claimId = claimId;
	}
	public String getBenefitCode() {
		return benefitCode;
	}
	public void setBenefitCode(String benefitCode) {
		this.benefitCode = benefitCode;
	}
	public String getBenefitDescription() {
		return benefitDescription;
	}
	public void setBenefitDescription(String benefitDescription) {
		this.benefitDescription = benefitDescription;
	}
	public Double getQtyBenefit() {
		return qtyBenefit;
	}
	public void setQtyBenefit(Double qtyBenefit) {
		this.qtyBenefit = qtyBenefit;
	}
	public Double getAmountIncurred() {
		return amountIncurred;
	}
	public void setAmountIncurred(Double amountIncurred) {
		this.amountIncurred = amountIncurred;
	}
	public Double getAmountApproved() {
		return amountApproved;
	}
	public void setAmountApproved(Double amountApproved) {
		this.amountApproved = amountApproved;
	}
	public Double getAmountNotApproved() {
		return amountNotApproved;
	}
	public void setAmountNotApproved(Double amountNotApproved) {
		this.amountNotApproved = amountNotApproved;
	}
	public Double getAmountExcessPaid() {
		return amountExcessPaid;
	}
	public void setAmountExcessPaid(Double amountExcessPaid) {
		this.amountExcessPaid = amountExcessPaid;
	}
	public Double getAmountExcessNotPaid() {
		return amountExcessNotPaid;
	}
	public void setAmountExcessNotPaid(Double amountExcessNotPaid) {
		this.amountExcessNotPaid = amountExcessNotPaid;
	}
	public Double getAmountRefund() {
		return amountRefund;
	}
	public void setAmountRefund(Double amountRefund) {
		this.amountRefund = amountRefund;
	}
	public Double getAmountPaidToProv() {
		return amountPaidToProv;
	}
	public void setAmountPaidToProv(Double amountPaidToProv) {
		this.amountPaidToProv = amountPaidToProv;
	}
	public Double getAmountDiscount() {
		return amountDiscount;
	}
	public void setAmountDiscount(Double amountDiscount) {
		this.amountDiscount = amountDiscount;
	}
	public String getRemarksCode() {
		return remarksCode;
	}
	public void setRemarksCode(String remarksCode) {
		this.remarksCode = remarksCode;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
