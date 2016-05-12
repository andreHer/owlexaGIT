package com.ametis.cms.datamodel;

import java.io.Serializable;
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
@Table(name="refund_item")
public class RefundItem implements Serializable{

	private Integer refundItemId;
	private Refund refundId;
	private Integer status;
	
	private MemberImport memberImport;
	private Member memberId;
	
	private Double refundValue;
	private Double paidRefund;
	private Double outstandingValue;
	
	private String createdBy;
	private String modifiedBy;
	private String deletedBy;
	
	private Timestamp createdTime;
	private Timestamp modifiedTime;
	private Timestamp deletedTime;
	
	private Integer deletedStatus;

	@Id
	@Column(name="refund_item_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getRefundItemId() {
		return refundItemId;
	}

	public void setRefundItemId(Integer refundItemId) {
		this.refundItemId = refundItemId;
	}

	@ManyToOne
	@JoinColumn(name="refund_id")
	public Refund getRefundId() {
		return refundId;
	}

	public void setRefundId(Refund refundId) {
		this.refundId = refundId;
	}

	@Column(name="status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@ManyToOne
	@JoinColumn(name="member_import_id")
	public MemberImport getMemberImport() {
		return memberImport;
	}

	public void setMemberImport(MemberImport memberImport) {
		this.memberImport = memberImport;
	}

	@ManyToOne
	@JoinColumn(name="member_id")
	public Member getMemberId() {
		return memberId;
	}

	public void setMemberId(Member memberId) {
		this.memberId = memberId;
	}

	@Column(name="refund_value")
	public Double getRefundValue() {
		return refundValue;
	}

	public void setRefundValue(Double refundValue) {
		this.refundValue = refundValue;
	}

	@Column(name="paid_refund")
	public Double getPaidRefund() {
		return paidRefund;
	}

	public void setPaidRefund(Double paidRefund) {
		this.paidRefund = paidRefund;
	}

	@Column(name="outstanding_value")
	public Double getOutstandingValue() {
		return outstandingValue;
	}

	public void setOutstandingValue(Double outstandingValue) {
		this.outstandingValue = outstandingValue;
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

	@Column(name="deleted_status")
	public Integer getDeletedStatus() {
		return deletedStatus;
	}

	public void setDeletedStatus(Integer deletedStatus) {
		this.deletedStatus = deletedStatus;
	}
	
	
}
