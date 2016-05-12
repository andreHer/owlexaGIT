package com.ametis.cms.datamodel;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="layanan")
public class Layanan implements Serializable {
	private Integer layananId;
	private String layananName;
	private String layananCode;
	private String description;
	private Integer status;
	
	private Double basicChargeRate;
	private String createdBy;
	private String modifiedBy;
	private String deletedBy;
	private Timestamp createdTime;
	private Timestamp modifiedTime;
	private Timestamp deletedTime;
	private Integer deletedStatus;
	
	@Id
	@Column(name="layanan_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getLayananId() {
		return layananId;
	}
	public void setLayananId(Integer layananId) {
		this.layananId = layananId;
	}
	@Column(name="layanan_name")
	public String getLayananName() {
		return layananName;
	}
	public void setLayananName(String layananName) {
		this.layananName = layananName;
	}
	@Column(name="layanan_code")
	public String getLayananCode() {
		return layananCode;
	}
	public void setLayananCode(String layananCode) {
		this.layananCode = layananCode;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column(name="basic_charge_rate")
	public Double getBasicChargeRate() {
		return basicChargeRate;
	}
	public void setBasicChargeRate(Double basicChargeRate) {
		this.basicChargeRate = basicChargeRate;
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
