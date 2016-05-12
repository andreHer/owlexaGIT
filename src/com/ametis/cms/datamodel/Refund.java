package com.ametis.cms.datamodel;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="refund")
public class Refund implements Serializable{

	private Integer refundId;
	private String refundNumber;
	private Date refundDate;
	private Integer status;
	private String description;
	
	private Double totalRefund;
	private Double paidRefund;
	private Double outstanding;
	
	private String createdBy;
	private String modifiedBy;
	private String deletedBy;
	
	private Timestamp createdTime;
	private Timestamp modifiedTime;
	private Timestamp deletedTime;
	
	private Integer deletedStatus;

	@Id
	@Column(name="refund_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getRefundId() {
		return refundId;
	}

	public void setRefundId(Integer refundId) {
		this.refundId = refundId;
	}

	@Column(name="refund_number")
	public String getRefundNumber() {
		return refundNumber;
	}

	public void setRefundNumber(String refundNumber) {
		this.refundNumber = refundNumber;
	}

	@Column(name="refund_date")
	public Date getRefundDate() {
		return refundDate;
	}

	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}

	@Column(name="status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name="description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="total_refund")
	public Double getTotalRefund() {
		return totalRefund;
	}

	public void setTotalRefund(Double totalRefund) {
		this.totalRefund = totalRefund;
	}

	@Column(name="paid_refund")
	public Double getPaidRefund() {
		return paidRefund;
	}

	public void setPaidRefund(Double paidRefund) {
		this.paidRefund = paidRefund;
	}

	@Column(name="outstanding")
	public Double getOutstanding() {
		return outstanding;
	}

	public void setOutstanding(Double outstanding) {
		this.outstanding = outstanding;
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
