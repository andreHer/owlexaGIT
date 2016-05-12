package com.ametis.cms.datamodel;

import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;

import com.ametis.cms.dto.BankAccountDto;
import com.ametis.cms.dto.PolicyDto;

@Entity
@Table(name = "bank_account")
public class BankAccount implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	// Fields

	/**
	 * data for the column :
	 * 
	 * --------- bank_account.id --------- schema = null tableName =
	 * bank_account foreignCol = foreignTab = catalog = jasindo remarks =
	 * auto_increment defaultValue = null decDigits = 0 radix = 10 nullable = 0
	 * ordinal = 1 size = 10 type = 4 isPrimaryKey = true
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private Integer id;

	/**
	 * data for the column :
	 * 
	 * --------- bank_account.bank_account --------- schema = null tableName =
	 * bank_account foreignCol = foreignTab = catalog = jasindo remarks =
	 * defaultValue = null decDigits = 0 radix = 10 nullable = 1 ordinal = 2
	 * size = 255 type = 12 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private String bankAccount;

	/**
	 * data for the column :
	 * 
	 * --------- bank_account.bank --------- schema = null tableName =
	 * bank_account foreignCol = foreignTab = catalog = jasindo remarks =
	 * defaultValue = null decDigits = 0 radix = 10 nullable = 1 ordinal = 3
	 * size = 255 type = 12 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private String bank;

	/**
	 * data for the column :
	 * 
	 * --------- bank_account.bank_branch --------- schema = null tableName =
	 * bank_account foreignCol = foreignTab = catalog = jasindo remarks =
	 * defaultValue = null decDigits = 0 radix = 10 nullable = 1 ordinal = 4
	 * size = 255 type = 12 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private String bankBranch;

	/**
	 * data for the column :
	 * 
	 * --------- bank_account.account_name --------- schema = null tableName =
	 * bank_account foreignCol = foreignTab = catalog = jasindo remarks =
	 * defaultValue = null decDigits = 0 radix = 10 nullable = 1 ordinal = 8
	 * size = 255 type = 12 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private String accountName;

	/**
	 * data for the column :
	 * 
	 * --------- bank_account.bank_id --------- schema = null tableName =
	 * bank_account foreignCol = foreignTab = catalog = jasindo remarks =
	 * defaultValue = null decDigits = 0 radix = 10 nullable = 1 ordinal = 9
	 * size = 10 type = 4 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private Bank bankId;

	// foreign affairs

	/**
	 * Client data for the foreign class :
	 * 
	 * --------- client.client_id --------- schema = null tableName = client
	 * foreignCol = client_id foreignTab = bank_account catalog = jasindo
	 * remarks = auto_increment defaultValue = null decDigits = 0 radix = 10
	 * nullable = 0 ordinal = 1 size = 11 type = 4 isPrimaryKey = true
	 * 
	 * =========================================
	 * 
	 * 
	 * 
	 */
	private Client clientId;
	/**
	 * MemberGroup data for the foreign class :
	 * 
	 * --------- member_group.member_group_id --------- schema = null tableName =
	 * member_group foreignCol = member_group_id foreignTab = bank_account
	 * catalog = jasindo remarks = auto_increment defaultValue = null decDigits =
	 * 0 radix = 10 nullable = 0 ordinal = 1 size = 11 type = 4 isPrimaryKey =
	 * true
	 * 
	 * =========================================
	 * 
	 * 
	 * 
	 */
	private MemberGroup memberGroupId;
	/**
	 * Member data for the foreign class :
	 * 
	 * --------- member.member_id --------- schema = null tableName = member
	 * foreignCol = member_id foreignTab = bank_account catalog = jasindo
	 * remarks = auto_increment defaultValue = null decDigits = 0 radix = 10
	 * nullable = 0 ordinal = 1 size = 11 type = 4 isPrimaryKey = true
	 * 
	 * =========================================
	 * 
	 * 
	 * 
	 */
	private Member memberId;
	/**
	 * Provider data for the foreign class :
	 * 
	 * --------- provider.provider_id --------- schema = null tableName =
	 * provider foreignCol = provider_id foreignTab = bank_account catalog =
	 * jasindo remarks = auto_increment defaultValue = null decDigits = 0 radix =
	 * 10 nullable = 0 ordinal = 1 size = 11 type = 4 isPrimaryKey = true
	 * 
	 * =========================================
	 * 
	 * 
	 * 
	 */
	private Provider providerId;
	private Broker brokerId;

	private Integer isSourcePayment;
	private Currency currencyId;
	private Integer deletedStatus;
	private String createdBy;
	private String modifiedBy;
	private String deletedBy;
	private Timestamp createdTime;
	private Timestamp modifiedTime;
	private Timestamp deletedTime;

	// -- foreign affairs end

	// -- exported key

	// -- exported key end

	// Fields End

	// PK GETTER SETTER
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer value) {
		id = value;
	}

	// PK GETTER SETTER END

	@Column(name = "bank_account")
	public java.lang.String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(java.lang.String value) {
		bankAccount = value;
	}

	@Column(name = "bank")
	public java.lang.String getBank() {
		return bank;
	}

	public void setBank(java.lang.String value) {
		bank = value;
	}

	@Column(name = "bank_branch")
	public java.lang.String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(java.lang.String value) {
		bankBranch = value;
	}

	@Column(name = "account_name")
	public java.lang.String getAccountName() {
		return accountName;
	}

	public void setAccountName(java.lang.String value) {
		accountName = value;
	}

	@ManyToOne
	@JoinColumn(name = "bank_id")
	public Bank getBankId() {
		return bankId;
	}

	public void setBankId(Bank value) {
		bankId = value;
	}

	// foreign affairs

	/** Client */
	@ManyToOne
	@JoinColumn(name = "client_id")
	public Client getClientId() {
		return this.clientId;
	}

	/** Client */
	public void setClientId(Client obj) {
		this.clientId = obj;
	}

	/** MemberGroup */
	@ManyToOne
	@JoinColumn(name = "member_group_id")
	public MemberGroup getMemberGroupId() {
		return this.memberGroupId;
	}

	/** MemberGroup */
	public void setMemberGroupId(MemberGroup obj) {
		this.memberGroupId = obj;
	}

	/** Member */
	@ManyToOne
	@JoinColumn(name = "member_id")
	public Member getMemberId() {
		return this.memberId;
	}

	/** Member */
	public void setMemberId(Member obj) {
		this.memberId = obj;
	}

	/** Provider */
	@ManyToOne
	@JoinColumn(name = "provider_id")
	public Provider getProviderId() {
		return this.providerId;
	}

	/** Provider */
	public void setProviderId(Provider obj) {
		this.providerId = obj;
	}

	@Column(name = "is_source_payment")
	public Integer getIsSourcePayment() {
		return isSourcePayment;
	}

	public void setIsSourcePayment(Integer isSourcePayment) {
		this.isSourcePayment = isSourcePayment;
	}

	// foreign affairs end

	// exported key

	// exported key end

	@ManyToOne
	@JoinColumn(name = "broker_id")
	public Broker getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(Broker brokerId) {
		this.brokerId = brokerId;
	}

	@Column(name = "created_by")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "created_time")
	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name = "deleted_by")
	public String getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(String deletedBy) {
		this.deletedBy = deletedBy;
	}

	@Column(name = "deleted_status")
	public Integer getDeletedStatus() {
		return deletedStatus;
	}

	public void setDeletedStatus(Integer deletedStatus) {
		this.deletedStatus = deletedStatus;
	}

	@Column(name = "deleted_time")
	public Timestamp getDeletedTime() {
		return deletedTime;
	}

	public void setDeletedTime(Timestamp deletedTime) {
		this.deletedTime = deletedTime;
	}

	@Column(name = "modified_by")
	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Column(name = "modified_time")
	public Timestamp getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	@ManyToOne
	@JoinColumn(name="currency_id")
	public Currency getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Currency currencyId) {
		this.currencyId = currencyId;
	}
	
	
//	public BankAccountDto exportDto(){
//		BankAccountDto result = new BankAccountDto();
//		
//		if (accountName != null){
//			result.setAccountName(accountName);
//		}
//		return result;
//
//	}
//	
	
}