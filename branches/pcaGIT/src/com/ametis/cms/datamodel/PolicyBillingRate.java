
package com.ametis.cms.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "policy_billing_rate")
public class PolicyBillingRate implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	public static final int BILLING_RATE_MEMBER = 1;// mapping to billing
													// subject
	public static final int BILLING_RATE_CLAIM = 2; // mapping to billing
													// subject
	public static final int BILLING_RATE_CLAIM_PER_PROCESS = 3; // mapping to
																// billing
																// subject

	// Fields

	/**
	 * data for the column :
	 * 
	 * --------- policy_billing_rate.policy_billing_rate_id --------- schema =
	 * null tableName = policy_billing_rate foreignCol = foreignTab = catalog =
	 * mp-new remarks = auto_increment defaultValue = null decDigits = 0 radix =
	 * 10 nullable = 0 ordinal = 1 size = 15 type = 4 isPrimaryKey = true
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private Integer policyBillingRateId;

	/**
	 * data for the column :
	 * 
	 * --------- policy_billing_rate.billing_rate_subject --------- schema =
	 * null tableName = policy_billing_rate foreignCol = foreignTab = catalog =
	 * mp-new remarks = defaultValue = null decDigits = 0 radix = 10 nullable =
	 * 1 ordinal = 3 size = 2 type = 4 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private Integer billingRateSubject;

	/**
	 * data for the column :
	 * 
	 * --------- policy_billing_rate.billing_rate_start_quantity ---------
	 * schema = null tableName = policy_billing_rate foreignCol = foreignTab =
	 * catalog = mp-new remarks = defaultValue = null decDigits = 0 radix = 10
	 * nullable = 1 ordinal = 4 size = 5 type = 4 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private Integer billingRateStartQuantity;

	/**
	 * data for the column :
	 * 
	 * --------- policy_billing_rate.billing_rate_end_quantity --------- schema
	 * = null tableName = policy_billing_rate foreignCol = foreignTab = catalog
	 * = mp-new remarks = defaultValue = null decDigits = 0 radix = 10 nullable
	 * = 1 ordinal = 5 size = 5 type = 4 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private Integer billingRateEndQuantity;

	/**
	 * data for the column :
	 * 
	 * --------- policy_billing_rate.billing_rate --------- schema = null
	 * tableName = policy_billing_rate foreignCol = foreignTab = catalog =
	 * mp-new remarks = defaultValue = null decDigits = 0 radix = 10 nullable =
	 * 1 ordinal = 6 size = 22 type = 8 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private Double billingRate;

	/**
	 * data for the column :
	 * 
	 * --------- policy_billing_rate.claim_process_status_start --------- schema
	 * = null tableName = policy_billing_rate foreignCol = foreignTab = catalog
	 * = mp-new remarks = defaultValue = null decDigits = 0 radix = 10 nullable
	 * = 1 ordinal = 7 size = 5 type = 4 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private Integer claimProcessStatusStart;
	private Integer claimProcessStatusEnd;
	private java.sql.Timestamp createdTime;

	/**
	 * data for the column :
	 * 
	 * --------- policy_billing_rate.created_by --------- schema = null
	 * tableName = policy_billing_rate foreignCol = foreignTab = catalog =
	 * mp-new remarks = defaultValue = null decDigits = 0 radix = 10 nullable =
	 * 1 ordinal = 11 size = 50 type = 12 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private String createdBy;

	/**
	 * data for the column :
	 * 
	 * --------- policy_billing_rate.modified_time --------- schema = null
	 * tableName = policy_billing_rate foreignCol = foreignTab = catalog =
	 * mp-new remarks = defaultValue = null decDigits = 0 radix = 10 nullable =
	 * 1 ordinal = 12 size = 19 type = 93 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private java.sql.Timestamp modifiedTime;

	/**
	 * data for the column :
	 * 
	 * --------- policy_billing_rate.modified_by --------- schema = null
	 * tableName = policy_billing_rate foreignCol = foreignTab = catalog =
	 * mp-new remarks = defaultValue = null decDigits = 0 radix = 10 nullable =
	 * 1 ordinal = 13 size = 50 type = 12 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private String modifiedBy;

	/**
	 * data for the column :
	 * 
	 * --------- policy_billing_rate.deleted_time --------- schema = null
	 * tableName = policy_billing_rate foreignCol = foreignTab = catalog =
	 * mp-new remarks = defaultValue = null decDigits = 0 radix = 10 nullable =
	 * 1 ordinal = 14 size = 19 type = 93 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private java.sql.Timestamp deletedTime;

	/**
	 * data for the column :
	 * 
	 * --------- policy_billing_rate.deleted_by --------- schema = null
	 * tableName = policy_billing_rate foreignCol = foreignTab = catalog =
	 * mp-new remarks = defaultValue = null decDigits = 0 radix = 10 nullable =
	 * 1 ordinal = 15 size = 50 type = 12 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private String deletedBy;

	/**
	 * data for the column :
	 * 
	 * --------- policy_billing_rate.deleted_status --------- schema = null
	 * tableName = policy_billing_rate foreignCol = foreignTab = catalog =
	 * mp-new remarks = defaultValue = null decDigits = 0 radix = 10 nullable =
	 * 1 ordinal = 16 size = 2 type = 4 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private Integer deletedStatus;

	// foreign affairs

	/**
	 * Item data for the foreign class :
	 * 
	 * --------- item.item_id --------- schema = null tableName = item
	 * foreignCol = item_id foreignTab = policy_billing_rate catalog = mp-new
	 * remarks = auto_increment defaultValue = null decDigits = 0 radix = 10
	 * nullable = 0 ordinal = 1 size = 11 type = 4 isPrimaryKey = true
	 * 
	 * =========================================
	 * 
	 * 
	 * 
	 */
	private Item itemId;
	/**
	 * Policy data for the foreign class :
	 * 
	 * --------- policy.policy_id --------- schema = null tableName = policy
	 * foreignCol = policy_id foreignTab = policy_billing_rate catalog = mp-new
	 * remarks = auto_increment defaultValue = null decDigits = 0 radix = 10
	 * nullable = 0 ordinal = 1 size = 11 type = 4 isPrimaryKey = true
	 * 
	 * =========================================
	 * 
	 * 
	 * 
	 */
	private Policy policyId;

	// -- foreign affairs end

	// -- exported key

	// -- exported key end

	// Fields End

	// PK GETTER SETTER
	@Id
	@Column(name = "policy_billing_rate_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public java.lang.Integer getPolicyBillingRateId() {
		return policyBillingRateId;
	}

	public void setPolicyBillingRateId(java.lang.Integer value) {
		policyBillingRateId = value;
	}
	// PK GETTER SETTER END

	@Column(name = "billing_rate_subject")
	public java.lang.Integer getBillingRateSubject() {
		return billingRateSubject;
	}

	public void setBillingRateSubject(java.lang.Integer value) {
		billingRateSubject = value;
	}

	@Column(name = "billing_rate_start_quantity")
	public java.lang.Integer getBillingRateStartQuantity() {
		return billingRateStartQuantity;
	}

	public void setBillingRateStartQuantity(java.lang.Integer value) {
		billingRateStartQuantity = value;
	}

	@Column(name = "billing_rate_end_quantity")
	public java.lang.Integer getBillingRateEndQuantity() {
		return billingRateEndQuantity;
	}

	public void setBillingRateEndQuantity(java.lang.Integer value) {
		billingRateEndQuantity = value;
	}

	@Column(name = "billing_rate")
	public java.lang.Double getBillingRate() {
		return billingRate;
	}

	public void setBillingRate(java.lang.Double value) {
		billingRate = value;
	}

	@Column(name = "claim_process_status_start")
	public java.lang.Integer getClaimProcessStatusStart() {
		return claimProcessStatusStart;
	}

	public void setClaimProcessStatusStart(java.lang.Integer value) {
		claimProcessStatusStart = value;
	}

	@Column(name = "claim_process_status_end")
	public java.lang.Integer getClaimProcessStatusEnd() {
		return claimProcessStatusEnd;
	}

	public void setClaimProcessStatusEnd(java.lang.Integer value) {
		claimProcessStatusEnd = value;
	}

	@Column(name = "created_time")
	public java.sql.Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(java.sql.Timestamp value) {
		createdTime = value;
	}

	@Column(name = "created_by")
	public java.lang.String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(java.lang.String value) {
		createdBy = value;
	}

	@Column(name = "modified_time")
	public java.sql.Timestamp getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(java.sql.Timestamp value) {
		modifiedTime = value;
	}

	@Column(name = "modified_by")
	public java.lang.String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(java.lang.String value) {
		modifiedBy = value;
	}

	@Column(name = "deleted_time")
	public java.sql.Timestamp getDeletedTime() {
		return deletedTime;
	}

	public void setDeletedTime(java.sql.Timestamp value) {
		deletedTime = value;
	}

	@Column(name = "deleted_by")
	public java.lang.String getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(java.lang.String value) {
		deletedBy = value;
	}

	@Column(name = "deleted_status")
	public java.lang.Integer getDeletedStatus() {
		return deletedStatus;
	}

	public void setDeletedStatus(java.lang.Integer value) {
		deletedStatus = value;
	}

	// foreign affairs

	/** Item */
	@ManyToOne
	@JoinColumn(name = "item_id")
	public Item getItemId() {
		return this.itemId;
	}

	/** Item */
	public void setItemId(Item obj) {
		this.itemId = obj;
	}

	/** Policy */
	@ManyToOne
	@JoinColumn(name = "policy_id")
	public Policy getPolicyId() {
		return this.policyId;
	}

	/** Policy */
	public void setPolicyId(Policy obj) {
		this.policyId = obj;
	}

	// foreign affairs end

	// exported key

	// exported key end

}