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
@Table(name="provider_group")
public class ProviderGroup implements Serializable{

	private Integer providerGroupId;
	private String providerGroupName;
	private String providerGroupCode;
	private String description;
	
	private Timestamp createdTime;
	private Timestamp modifiedTime;
	private Timestamp deletedTime;
	private String createdBy;
	private String modifiedBy;
	private String deletedBy;
	
	private Integer deletedStatus;

	@Id
	@Column(name="provider_group_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getProviderGroupId() {
		return providerGroupId;
	}

	public void setProviderGroupId(Integer providerGroupId) {
		this.providerGroupId = providerGroupId;
	}

	@Column(name="provider_group_name")
	public String getProviderGroupName() {
		return providerGroupName;
	}

	public void setProviderGroupName(String providerGroupName) {
		this.providerGroupName = providerGroupName;
	}

	@Column(name="provider_group_code")
	public String getProviderGroupCode() {
		return providerGroupCode;
	}

	public void setProviderGroupCode(String providerGroupCode) {
		this.providerGroupCode = providerGroupCode;
	}

	@Column(name="description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	
	
}
