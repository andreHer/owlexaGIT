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
@Table(name="contract_charge_interval")
public class ContractChargeInterval implements Serializable{

	private Integer contractChargeIntervalId;
	private ClientContract clientContractId;
	
	private Integer startVolumeInterval;
	private Integer endVolumeInterval;
	
	private Integer status;
	
	private Double chargeRate;
	
	private String createdBy;
	private String modifiedBy;
	private String deletedBy;
	
	private Timestamp createdTime;
	private Timestamp modifiedTime;
	private Timestamp deletedTime;
	private Integer deletedStatus;
	
	private Double discount;
	
	

	@Id
	@Column(name="contract_charge_interval_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getContractChargeIntervalId() {
		return contractChargeIntervalId;
	}

	public void setContractChargeIntervalId(Integer contractChargeIntervalId) {
		this.contractChargeIntervalId = contractChargeIntervalId;
	}

	@ManyToOne
	@JoinColumn(name="client_contract_id")
	public ClientContract getClientContractId() {
		return clientContractId;
	}

	public void setClientContractId(ClientContract clientContractId) {
		this.clientContractId = clientContractId;
	}

	@Column(name="start_volume_interval")
	public Integer getStartVolumeInterval() {
		return startVolumeInterval;
	}

	public void setStartVolumeInterval(Integer startVolumeInterval) {
		this.startVolumeInterval = startVolumeInterval;
	}

	@Column(name="end_volume_interval")
	public Integer getEndVolumeInterval() {
		return endVolumeInterval;
	}

	public void setEndVolumeInterval(Integer endVolumeInterval) {
		this.endVolumeInterval = endVolumeInterval;
	}

	@Column(name="status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name="charge_rate")
	public Double getChargeRate() {
		return chargeRate;
	}

	public void setChargeRate(Double chargeRate) {
		this.chargeRate = chargeRate;
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

	@Column(name="discount")
	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	
	
	
}
