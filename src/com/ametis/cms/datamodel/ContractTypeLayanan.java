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
@Table(name="contract_type_layanan")
public class ContractTypeLayanan implements Serializable{

	private Integer contractTypeLayananId;
	private ContractType contractTypeId;
	private Layanan layananId;
	
	private Integer startIntervalVolume;
	private Integer endIntervalVolume;
	
	private Double chargeRate;
	private Integer status;
	
	private String createdBy;
	private String modifiedBy;
	private String deletedBy;
	
	private Timestamp createdTime;
	private Timestamp modifiedTime;
	private Timestamp deletedTime;
	private Integer deletedStatus;
	
	@Id
	@Column(name="contract_type_layanan_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getContractTypeLayananId() {
		return contractTypeLayananId;
	}
	public void setContractTypeLayananId(Integer contractTypeLayananId) {
		this.contractTypeLayananId = contractTypeLayananId;
	}
	@ManyToOne
	@JoinColumn(name="contract_type_id")
	public ContractType getContractTypeId() {
		return contractTypeId;
	}
	public void setContractTypeId(ContractType contractTypeId) {
		this.contractTypeId = contractTypeId;
	}
	@ManyToOne
	@JoinColumn(name="layanan_id")
	public Layanan getLayananId() {
		return layananId;
	}
	public void setLayananId(Layanan layananId) {
		this.layananId = layananId;
	}
	@Column(name="start_interval_volume")
	public Integer getStartIntervalVolume() {
		return startIntervalVolume;
	}
	public void setStartIntervalVolume(Integer startIntervalVolume) {
		this.startIntervalVolume = startIntervalVolume;
	}
	@Column(name="end_interval_volume")
	public Integer getEndIntervalVolume() {
		return endIntervalVolume;
	}
	public void setEndIntervalVolume(Integer endIntervalVolume) {
		this.endIntervalVolume = endIntervalVolume;
	}
	@Column(name="charge_rate")
	public Double getChargeRate() {
		return chargeRate;
	}
	public void setChargeRate(Double chargeRate) {
		this.chargeRate = chargeRate;
	}
	@Column(name="status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
