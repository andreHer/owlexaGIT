
package com.ametis.cms.datamodel;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="fund")
public class Fund implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	public static final int CLIENT_FLOATING_FUND = 1;
	public static final int EXCESS_CHARGE_PAYMENT = 2;
	public static final int OUTSTANDING_PAYMENT = 3;
	public static final int POLICY_PAYMENT = 4;
	public static final int PROVIDER_CAPITATION_PAYMENT = 5;
	public static final int GROUP_FLOATING_FUND = 6;
	public static final int EXCESS_FLOATING_FUND = 7;
	public static final int POLICY_FLOATING_FUND = 8;
	public static final int POLICY_COVERAGE_FUND = 9;
	public static final int POLICY_EXCESS_FUND = 10;
	public static final int POLICY_COVERAGE_EXCESS_FUND = 11;
	
	
	private String fundNumberCounter;

	//Fields
		
	/**data for the column :
	
 --------- fund.fund_id --------- 
 schema        = null
 tableName     = fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 11
 type          = 4 
 isPrimaryKey  = true

=========================================


*/
	private Integer fundId;
			
	/**data for the column :
	
 --------- fund.fund_value --------- 
 schema        = null
 tableName     = fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double fundValue;
						
	/**data for the column :
	
 --------- fund.fund_status --------- 
 schema        = null
 tableName     = fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private PaymentStatus fundStatus;
			
	/**data for the column :
	
 --------- fund.fund_code --------- 
 schema        = null
 tableName     = fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String fundCode;
			
	/**data for the column :
	
 --------- fund.description --------- 
 schema        = null
 tableName     = fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String description;
			
	/**data for the column :
	
 --------- fund.fund_time --------- 
 schema        = null
 tableName     = fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private Date fundTime;
			
	/**data for the column :
	
 --------- fund.created_time --------- 
 schema        = null
 tableName     = fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- fund.created_by --------- 
 schema        = null
 tableName     = fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- fund.deleted_time --------- 
 schema        = null
 tableName     = fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- fund.deleted_by --------- 
 schema        = null
 tableName     = fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- fund.modified_time --------- 
 schema        = null
 tableName     = fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- fund.modified_by --------- 
 schema        = null
 tableName     = fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- fund.deleted_status --------- 
 schema        = null
 tableName     = fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 14
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- fund.approved_by --------- 
 schema        = null
 tableName     = fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 15
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String approvedBy;
		
	// foreign affairs
	
			/** Client
	data for the foreign class :
	
 --------- client.client_id --------- 
 schema        = null
 tableName     = client
 foreignCol    = client_id
 foreignTab    = fund
 catalog       = insurance
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 11
 type          = 4 
 isPrimaryKey  = true

=========================================



	 */
	private Client clientId;
	private FundCategory fundType;
	private Currency fundCurrency;
	private ExcessCharge excessCharge;
	private Outstanding outstanding;
	private Policy policyId;		
	private Provider providerId;
	private MemberGroup memberGroupId;
	private PolicyCoverageFund policyCoverageFundId;
	
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="fund_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getFundId(){
		return fundId;
	}
	public void setFundId(java.lang.Integer value){
		fundId = value;
	}
			// PK GETTER SETTER END

						@Column(name="fund_value")
	public java.lang.Double getFundValue(){
		return fundValue;
	}
	public void setFundValue(java.lang.Double value){
		fundValue = value;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fund_status")
	public PaymentStatus getFundStatus(){
		return fundStatus;
	}
	public void setFundStatus(PaymentStatus value){
		fundStatus = value;
	}
				@Column(name="fund_code")
	public java.lang.String getFundCode(){
		return fundCode;
	}
	public void setFundCode(java.lang.String value){
		fundCode = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
				@Column(name="fund_time")
	public java.sql.Date getFundTime(){
		return fundTime;
	}
	public void setFundTime(java.sql.Date value){
		fundTime = value;
	}
				@Column(name="created_time")
	public java.sql.Timestamp getCreatedTime(){
		return createdTime;
	}
	public void setCreatedTime(java.sql.Timestamp value){
		createdTime = value;
	}
				@Column(name="created_by")
	public java.lang.String getCreatedBy(){
		return createdBy;
	}
	public void setCreatedBy(java.lang.String value){
		createdBy = value;
	}
				@Column(name="deleted_time")
	public java.sql.Timestamp getDeletedTime(){
		return deletedTime;
	}
	public void setDeletedTime(java.sql.Timestamp value){
		deletedTime = value;
	}
				@Column(name="deleted_by")
	public java.lang.String getDeletedBy(){
		return deletedBy;
	}
	public void setDeletedBy(java.lang.String value){
		deletedBy = value;
	}
				@Column(name="modified_time")
	public java.sql.Timestamp getModifiedTime(){
		return modifiedTime;
	}
	public void setModifiedTime(java.sql.Timestamp value){
		modifiedTime = value;
	}
				@Column(name="modified_by")
	public java.lang.String getModifiedBy(){
		return modifiedBy;
	}
	public void setModifiedBy(java.lang.String value){
		modifiedBy = value;
	}
				@Column(name="deleted_status")
	public java.lang.Integer getDeletedStatus(){
		return deletedStatus;
	}
	public void setDeletedStatus(java.lang.Integer value){
		deletedStatus = value;
	}
				@Column(name="approved_by")
	public java.lang.String getApprovedBy(){
		return approvedBy;
	}
	public void setApprovedBy(java.lang.String value){
		approvedBy = value;
	}
		
	// foreign affairs
	
			/** Client */
	@ManyToOne
	@JoinColumn(name="client_id")
	public Client getClientId(){
		return this.clientId;
	}

	/** Client */
	public void setClientId(Client obj){
		this.clientId = obj;
	}
	@ManyToOne
	@JoinColumn(name="fund_type")	
	public FundCategory getFundType() {
		return fundType;
	}
	public void setFundType(FundCategory fundType) {
		this.fundType = fundType;
	}
	@ManyToOne
	@JoinColumn(name="fund_currency")
	public Currency getFundCurrency() {
		return fundCurrency;
	}
	public void setFundCurrency(Currency fundCurrency) {
		this.fundCurrency = fundCurrency;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="excess_charge_id")
	public ExcessCharge getExcessCharge() {
		return excessCharge;
	}
	public void setExcessCharge(ExcessCharge excessCharge) {
		this.excessCharge = excessCharge;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="outstanding_id")
	public Outstanding getOutstanding() {
		return outstanding;
	}
	public void setOutstanding(Outstanding outstanding) {
		this.outstanding = outstanding;
	}
	@Column(name="fund_number_counter")
	public String getFundNumberCounter() {
		return fundNumberCounter;
	}
	public void setFundNumberCounter(String fundNumberCounter) {
		this.fundNumberCounter = fundNumberCounter;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="policy_id")
	public Policy getPolicyId() {
		return policyId;
	}
	public void setPolicyId(Policy policyId) {
		this.policyId = policyId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="provider_id")
	public Provider getProviderId() {
		return providerId;
	}
	public void setProviderId(Provider providerId) {
		this.providerId = providerId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="member_group_id")
	public MemberGroup getMemberGroupId() {
		return memberGroupId;
	}
	public void setMemberGroupId(MemberGroup memberGroupId) {
		this.memberGroupId = memberGroupId;
	}
	@ManyToOne
	@JoinColumn(name="policy_coverage_fund_id")
	public PolicyCoverageFund getPolicyCoverageFundId() {
		return policyCoverageFundId;
	}
	public void setPolicyCoverageFundId(PolicyCoverageFund policyCoverageFundId) {
		this.policyCoverageFundId = policyCoverageFundId;
	}
	
	



}