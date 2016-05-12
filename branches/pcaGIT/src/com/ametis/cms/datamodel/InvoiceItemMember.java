package com.ametis.cms.datamodel;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="invoice_item_member")
public class InvoiceItemMember implements Serializable{
	private Integer invoiceItemMemberId;
	private Member memberId;
	private InvoiceItem invoiceItemId;
	
	private Double billingRate;
	
	private Integer status;
	private Integer billingMonth;
	private Integer billingYear;
	
	private Timestamp createdTime;
	private Timestamp modifiedTime;
	private Timestamp deletedTime;
	
	private String createdBy;
	private String modifiedBy;
	private String deletedBy;
	
	private Integer deletedStatus;
	private Double refundRate;
	private Date refundDate;
	
	private Integer membershipPeriode;
	

	@Id
	@Column(name="invoice_item_member_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getInvoiceItemMemberId() {
		return invoiceItemMemberId;
	}

	public void setInvoiceItemMemberId(Integer invoiceItemMemberId) {
		this.invoiceItemMemberId = invoiceItemMemberId;
	}

	@ManyToOne
	@JoinColumn(name="member_id")
	public Member getMemberId() {
		return memberId;
	}

	public void setMemberId(Member memberId) {
		this.memberId = memberId;
	}

	@ManyToOne
	@JoinColumn(name="invoice_item_id")
	public InvoiceItem getInvoiceItemId() {
		return invoiceItemId;
	}

	public void setInvoiceItemId(InvoiceItem invoiceItemId) {
		this.invoiceItemId = invoiceItemId;
	}

	@Column(name="billing_rate")
	public Double getBillingRate() {
		return billingRate;
	}

	public void setBillingRate(Double billingRate) {
		this.billingRate = billingRate;
	}

	@Column(name="status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name="billing_month")
	public Integer getBillingMonth() {
		return billingMonth;
	}

	public void setBillingMonth(Integer billingMonth) {
		this.billingMonth = billingMonth;
	}

	@Column(name="billing_year")
	public Integer getBillingYear() {
		return billingYear;
	}

	public void setBillingYear(Integer billingYear) {
		this.billingYear = billingYear;
	}

	@Column(name="created_time")
	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name="modified_time")
	public Timestamp getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	@Column(name="deleted_time")
	public Timestamp getDeletedTime() {
		return deletedTime;
	}

	public void setDeletedTime(Timestamp deletedTime) {
		this.deletedTime = deletedTime;
	}

	@Column(name="created_by")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name="modified_by")
	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Column(name="deleted_by")
	public String getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(String deletedBy) {
		this.deletedBy = deletedBy;
	}

	@Column(name="deleted_status")
	public Integer getDeletedStatus() {
		return deletedStatus;
	}

	public void setDeletedStatus(Integer deletedStatus) {
		this.deletedStatus = deletedStatus;
	}

	@Column(name="refund_rate")
	public Double getRefundRate() {
		return refundRate;
	}

	public void setRefundRate(Double refundRate) {
		this.refundRate = refundRate;
	}

	@Column(name="refund_date")
	public Date getRefundDate() {
		return refundDate;
	}

	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}

	@Column(name="membership_periode")
	public Integer getMembershipPeriode() {
		return membershipPeriode;
	}

	public void setMembershipPeriode(Integer membershipPeriode) {
		this.membershipPeriode = membershipPeriode;
	}
	
	
	
}
