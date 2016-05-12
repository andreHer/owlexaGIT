package com.ametis.cms.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="number_counter")
public class NumberCounter implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer counterId;
	private Integer batchNumberCounter;
	private Integer claimNumberCounter;
	private Integer paymentNumberCounter;
	private Integer fundNumberCounter;
	private Integer outstandingNumberCounter;
	private Integer guaranteeLettterCounter;
	private Integer guaranteeNumberCounter;
	private Integer excessNumberCounter;
	private Client client;
	private Integer callNumberCounter;
	private Integer otherPaymentCounter;
	private Integer pendingNumberCounter;
	private Integer invoiceNumberCounter;
	
	public NumberCounter(){}
	
	@Id
	@Column(name="counter_id")
	public Integer getCounterId() {
		return counterId;
	}
	public void setCounterId(Integer counterId) {
		this.counterId = counterId;
	}
	@Column(name="batch_claim_counter")
	public Integer getBatchNumberCounter() {
		return batchNumberCounter;
	}
	public void setBatchNumberCounter(Integer batchNumberCounter) {
		this.batchNumberCounter = batchNumberCounter;
	}
	@Column(name="claim_counter")
	public Integer getClaimNumberCounter() {
		return claimNumberCounter;
	}
	public void setClaimNumberCounter(Integer claimNumberCounter) {
		this.claimNumberCounter = claimNumberCounter;
	}
	@Column(name="payment_counter")
	public Integer getPaymentNumberCounter() {
		return paymentNumberCounter;
	}
	public void setPaymentNumberCounter(Integer paymentNumberCounter) {
		this.paymentNumberCounter = paymentNumberCounter;
	}
	@Column(name="fund_counter")
	public Integer getFundNumberCounter() {
		return fundNumberCounter;
	}
	public void setFundNumberCounter(Integer fundNumberCounter) {
		this.fundNumberCounter = fundNumberCounter;
	}
	@Column(name="outstanding_counter")
	public Integer getOutstandingNumberCounter() {
		return outstandingNumberCounter;
	}
	public void setOutstandingNumberCounter(Integer outstandingNumberCounter) {
		this.outstandingNumberCounter = outstandingNumberCounter;
	}
	@Column(name="gl_counter")
	public Integer getGuaranteeLettterCounter() {
		return guaranteeLettterCounter;
	}
	public void setGuaranteeLettterCounter(Integer guaranteeLettterCounter) {
		this.guaranteeLettterCounter = guaranteeLettterCounter;
	}
	@Column(name="gn_counter")
	public Integer getGuaranteeNumberCounter() {
		return guaranteeNumberCounter;
	}
	public void setGuaranteeNumberCounter(Integer guaranteeNumberCounter) {
		this.guaranteeNumberCounter = guaranteeNumberCounter;
	}
	@Column(name="excess_counter")
	public Integer getExcessNumberCounter() {
		return excessNumberCounter;
	}
	public void setExcessNumberCounter(Integer excessNumberCounter) {
		this.excessNumberCounter = excessNumberCounter;
	}

	@ManyToOne
	@JoinColumn(name="client_id")
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Column(name="call_counter")
	public Integer getCallNumberCounter() {
		return callNumberCounter;
	}

	public void setCallNumberCounter(Integer callNumberCounter) {
		this.callNumberCounter = callNumberCounter;
	}

	@Column(name="other_payment_counter")
	public Integer getOtherPaymentCounter() {
		return otherPaymentCounter;
	}

	public void setOtherPaymentCounter(Integer otherPaymentCounter) {
		this.otherPaymentCounter = otherPaymentCounter;
	}

	@Column(name="pending_number_counter")
	public Integer getPendingNumberCounter() {
		return pendingNumberCounter;
	}

	public void setPendingNumberCounter(Integer pendingNumberCounter) {
		this.pendingNumberCounter = pendingNumberCounter;
	}

	@Column(name="invoice_counter")
	public Integer getInvoiceNumberCounter() {
		return invoiceNumberCounter;
	}

	public void setInvoiceNumberCounter(Integer invoiceNumberCounter) {
		this.invoiceNumberCounter = invoiceNumberCounter;
	}
	
	
	
	
	
}
