package com.ametis.cms.datamodel;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_user")
public class User implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	public static final int ACTIVE = 1;
	public static final int BLOCKED = 2;

	public static final int MEMBER_GROUP = 1;
	public static final int TPA = 2;
	public static final int CLIENT = 3;
	public static final int PROVIDER = 4;
	public static final int BROKER = 5;
	public static final int MANAGEMENT = 6;
	public static final int MEMBER = 7;
	// Fields

	/**
	 * data for the column :
	 * 
	 * --------- user.user_id --------- schema = null tableName = user
	 * foreignCol = foreignTab = catalog = insurance remarks = auto_increment
	 * defaultValue = null decDigits = 0 radix = 10 nullable = 0 ordinal = 1
	 * size = 11 type = 4 isPrimaryKey = true
	 * 
	 * =========================================
	 */
	private Integer userId;

	/**
	 * data for the column :
	 * 
	 * --------- user.username --------- schema = null tableName = user
	 * foreignCol = foreignTab = catalog = insurance remarks = defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 1 ordinal = 2 size = 255 type =
	 * 12 isPrimaryKey = false
	 * 
	 * =========================================
	 */
	private String username;

	/**
	 * data for the column :
	 * 
	 * --------- user.password --------- schema = null tableName = user
	 * foreignCol = foreignTab = catalog = insurance remarks = defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 1 ordinal = 3 size = 255 type =
	 * 12 isPrimaryKey = false
	 * 
	 * =========================================
	 */
	private String password;

	/**
	 * data for the column :
	 * 
	 * --------- user.first_name --------- schema = null tableName = user
	 * foreignCol = foreignTab = catalog = insurance remarks = defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 1 ordinal = 5 size = 255 type =
	 * 12 isPrimaryKey = false
	 * 
	 * =========================================
	 */
	private String firstName;

	/**
	 * data for the column :
	 * 
	 * --------- user.last_name --------- schema = null tableName = user
	 * foreignCol = foreignTab = catalog = insurance remarks = defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 1 ordinal = 6 size = 255 type =
	 * 12 isPrimaryKey = false
	 * 
	 * =========================================
	 */
	private String lastName;

	/**
	 * data for the column :
	 * 
	 * --------- user.email --------- schema = null tableName = user foreignCol
	 * = foreignTab = catalog = insurance remarks = defaultValue = null
	 * decDigits = 0 radix = 10 nullable = 1 ordinal = 7 size = 255 type = 12
	 * isPrimaryKey = false
	 * 
	 * =========================================
	 */
	private String email;

	/**
	 * data for the column :
	 * 
	 * --------- user.telephone --------- schema = null tableName = user
	 * foreignCol = foreignTab = catalog = insurance remarks = defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 1 ordinal = 8 size = 255 type =
	 * 12 isPrimaryKey = false
	 * 
	 * =========================================
	 */
	private String telephone;

	/**
	 * data for the column :
	 * 
	 * --------- user.mobile_phone --------- schema = null tableName = user
	 * foreignCol = foreignTab = catalog = insurance remarks = defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 1 ordinal = 9 size = 255 type =
	 * 12 isPrimaryKey = false
	 * 
	 * =========================================
	 */
	private String mobilePhone;

	/**
	 * data for the column :
	 * 
	 * --------- user.telephone_ext --------- schema = null tableName = user
	 * foreignCol = foreignTab = catalog = insurance remarks = defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 1 ordinal = 10 size = 10 type =
	 * 12 isPrimaryKey = false
	 * 
	 * =========================================
	 */
	private String telephoneExt;

	/**
	 * data for the column :
	 * 
	 * --------- user.description --------- schema = null tableName = user
	 * foreignCol = foreignTab = catalog = insurance remarks = defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 1 ordinal = 11 size = 65535 type
	 * = -1 isPrimaryKey = false
	 * 
	 * =========================================
	 */
	private String description;

	/**
	 * data for the column :
	 * 
	 * --------- user.created_by --------- schema = null tableName = user
	 * foreignCol = foreignTab = catalog = insurance remarks = defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 1 ordinal = 12 size = 50 type =
	 * 12 isPrimaryKey = false
	 * 
	 * =========================================
	 */
	private String createdBy;

	/**
	 * data for the column :
	 * 
	 * --------- user.created_time --------- schema = null tableName = user
	 * foreignCol = foreignTab = catalog = insurance remarks = defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 1 ordinal = 13 size = 19 type =
	 * 93 isPrimaryKey = false
	 * 
	 * =========================================
	 */
	private java.sql.Timestamp createdTime;

	/**
	 * data for the column :
	 * 
	 * --------- user.modified_by --------- schema = null tableName = user
	 * foreignCol = foreignTab = catalog = insurance remarks = defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 1 ordinal = 14 size = 50 type =
	 * 12 isPrimaryKey = false
	 * 
	 * =========================================
	 */
	private String modifiedBy;

	/**
	 * data for the column :
	 * 
	 * --------- user.modified_time --------- schema = null tableName = user
	 * foreignCol = foreignTab = catalog = insurance remarks = defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 1 ordinal = 15 size = 19 type =
	 * 93 isPrimaryKey = false
	 * 
	 * =========================================
	 */
	private java.sql.Timestamp modifiedTime;

	/**
	 * data for the column :
	 * 
	 * --------- user.deleted_by --------- schema = null tableName = user
	 * foreignCol = foreignTab = catalog = insurance remarks = defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 1 ordinal = 16 size = 50 type =
	 * 12 isPrimaryKey = false
	 * 
	 * =========================================
	 */
	private String deletedBy;

	/**
	 * data for the column :
	 * 
	 * --------- user.deleted_time --------- schema = null tableName = user
	 * foreignCol = foreignTab = catalog = insurance remarks = defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 1 ordinal = 17 size = 19 type =
	 * 93 isPrimaryKey = false
	 * 
	 * =========================================
	 */
	private java.sql.Timestamp deletedTime;

	/**
	 * data for the column :
	 * 
	 * --------- user.deleted_status --------- schema = null tableName = user
	 * foreignCol = foreignTab = catalog = insurance remarks = defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 1 ordinal = 18 size = 2 type = 4
	 * isPrimaryKey = false
	 * 
	 * =========================================
	 */
	private Integer deletedStatus;

	/**
	 * data for the column :
	 * 
	 * --------- user.status --------- schema = null tableName = user foreignCol
	 * = foreignTab = catalog = insurance remarks = defaultValue = null
	 * decDigits = 0 radix = 10 nullable = 1 ordinal = 19 size = 2 type = 4
	 * isPrimaryKey = false
	 * 
	 * =========================================
	 */
	private SubscriptionStatus status;

	// foreign affairs

	/**
	 * Role data for the foreign class :
	 * 
	 * --------- role.role_id --------- schema = null tableName = role
	 * foreignCol = role_id foreignTab = user catalog = insurance remarks =
	 * defaultValue = decDigits = 0 radix = 10 nullable = 0 ordinal = 1 size =
	 * 11 type = 4 isPrimaryKey = true
	 * 
	 * =========================================
	 */
	private Role roleId;

	// -- foreign affairs end

	// -- exported key

	/**
	 * FirstCall data for the exported class :
	 * 
	 * --------- first_call.handledBy --------- schema = null tableName =
	 * first_call foreignCol = foreignTab = catalog = insurance remarks =
	 * defaultValue = null decDigits = 0 radix = 10 nullable = 1 ordinal = 8
	 * size = 11 type = 4 isPrimaryKey = false this columns points to =
	 * user.user_id
	 * 
	 * =========================================
	 */
	private Set<FirstCall> firstCalls = new HashSet(0);

	private Timestamp lastLoginTime;
	private String lastLoginIp;
	private String lastLoginSessionId;

	private Client clientId;
	private MemberGroup memberGroupId;
	private Integer userType;
	private Provider providerId;
	private Broker brokerId;
	private Member memberId;
	private Tpa tpaId;

	// Add by aju on 20151012, for the mobile device id
	private String mobileDeviceId;

	// -- exported key end

	// Fields End

	// PK GETTER SETTER
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public java.lang.Integer getUserId() {
		return userId;
	}

	public void setUserId(java.lang.Integer value) {
		userId = value;
	}

	// PK GETTER SETTER END

	@Column(name = "username")
	public java.lang.String getUsername() {
		return username;
	}

	public void setUsername(java.lang.String value) {
		username = value;
	}

	@Column(name = "password")
	public java.lang.String getPassword() {
		return password;
	}

	public void setPassword(java.lang.String value) {
		password = value;
	}

	@Column(name = "first_name")
	public java.lang.String getFirstName() {
		return firstName;
	}

	public void setFirstName(java.lang.String value) {
		firstName = value;
	}

	@Column(name = "last_name")
	public java.lang.String getLastName() {
		return lastName;
	}

	public void setLastName(java.lang.String value) {
		lastName = value;
	}

	@Column(name = "email")
	public java.lang.String getEmail() {
		return email;
	}

	public void setEmail(java.lang.String value) {
		email = value;
	}

	@Column(name = "telephone")
	public java.lang.String getTelephone() {
		return telephone;
	}

	public void setTelephone(java.lang.String value) {
		telephone = value;
	}

	@Column(name = "mobile_phone")
	public java.lang.String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(java.lang.String value) {
		mobilePhone = value;
	}

	@Column(name = "telephone_ext")
	public java.lang.String getTelephoneExt() {
		return telephoneExt;
	}

	public void setTelephoneExt(java.lang.String value) {
		telephoneExt = value;
	}

	@Column(name = "description")
	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String value) {
		description = value;
	}

	@Column(name = "created_by")
	public java.lang.String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(java.lang.String value) {
		createdBy = value;
	}

	@Column(name = "created_time")
	public java.sql.Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(java.sql.Timestamp value) {
		createdTime = value;
	}

	@Column(name = "modified_by")
	public java.lang.String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(java.lang.String value) {
		modifiedBy = value;
	}

	@Column(name = "modified_time")
	public java.sql.Timestamp getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(java.sql.Timestamp value) {
		modifiedTime = value;
	}

	@Column(name = "deleted_by")
	public java.lang.String getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(java.lang.String value) {
		deletedBy = value;
	}

	@Column(name = "deleted_time")
	public java.sql.Timestamp getDeletedTime() {
		return deletedTime;
	}

	public void setDeletedTime(java.sql.Timestamp value) {
		deletedTime = value;
	}

	@Column(name = "deleted_status")
	public java.lang.Integer getDeletedStatus() {
		return deletedStatus;
	}

	public void setDeletedStatus(java.lang.Integer value) {
		deletedStatus = value;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status")
	public SubscriptionStatus getStatus() {
		return status;
	}

	public void setStatus(SubscriptionStatus value) {
		status = value;
	}

	// foreign affairs

	/** Role */
	@ManyToOne
	@JoinColumn(name = "role_id")
	public Role getRoleId() {
		return this.roleId;
	}

	/** Role */
	public void setRoleId(Role obj) {
		this.roleId = obj;
	}

	// foreign affairs end

	// exported key

	/** FirstCall */
	@OneToMany(mappedBy = "handledby")
	public Set<FirstCall> getFirstCalls() {
		return this.firstCalls;
	}

	/** FirstCall */
	public void setFirstCalls(Set<FirstCall> obj) {
		this.firstCalls = obj;
	}

	@Column(name = "last_login_time")
	public Timestamp getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Column(name = "last_login_ip")
	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	@Column(name = "last_login_session_id")
	public String getLastLoginSession() {
		return lastLoginSessionId;
	}

	public void setLastLoginSession(String lastLoginSessionId) {
		this.lastLoginSessionId = lastLoginSessionId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	public Client getClientId() {
		return clientId;
	}

	public void setClientId(Client clientId) {
		this.clientId = clientId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_group_id")
	public MemberGroup getMemberGroupId() {
		return memberGroupId;
	}

	public void setMemberGroupId(MemberGroup memberGroupId) {
		this.memberGroupId = memberGroupId;
	}

	@Column(name = "user_type")
	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "provider_id")
	public Provider getProviderId() {
		return providerId;
	}

	public void setProviderId(Provider providerId) {
		this.providerId = providerId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "broker_id")
	public Broker getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(Broker brokerId) {
		this.brokerId = brokerId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	public Member getMemberId() {
		return memberId;
	}

	public void setMemberId(Member memberId) {
		this.memberId = memberId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tpa_id")
	public Tpa getTpaId() {
		return tpaId;
	}

	public void setTpaId(Tpa tpaId) {
		this.tpaId = tpaId;
	}

	@Column(name = "mobile_device_id")
	public java.lang.String getMobileDeviceId() {
		return mobileDeviceId;
	}

	public void setMobileDeviceId(java.lang.String value) {
		mobileDeviceId = value;
	}

}