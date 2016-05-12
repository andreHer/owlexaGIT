
package com.ametis.cms.dto;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class MemberDto implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- member.member_id --------- 
 schema        = null
 tableName     = member
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
	private Integer memberId;
			
	/**data for the column :
	
 --------- member.status --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
			
	/**data for the column :
	
 --------- member.first_name --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String firstName;
			
	/**data for the column :
	
 --------- member.last_name --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	
	private String customerPolicyNumber;
	private String customerNumber;
			
	/**data for the column :
	
 --------- member.effective_date --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date effectiveDate;
			
	/**data for the column :
	
 --------- member.renewal_date --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date renewalDate;
			
	/**data for the column :
	
 --------- member.join_date --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 14
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date joinDate;
			
	/**data for the column :
	
 --------- member.resigned_date --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 15
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date resignedDate;
			
	/**data for the column :
	
 --------- member.expire_date --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 16
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date expireDate;
			
	/**data for the column :
	
 --------- member.address --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 17
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	
	private java.sql.Date birthday;
			
	/**data for the column :
	
 --------- member.birthplace --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 19
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
			private String memberGroupName;
			private String clientName;
			private String status;
			private String memberGroupId;
			private String clientId;
			private String gender;
			private String currentBenefitCode;
			private String annualPremium;
			private String bankAccount;
			private String bank;
			private String bankBranch;
			private String bankAccountName;
			private String clientCode;
			private String PolicyNumber;
			private String CardNumber;
			private String cardStatus;
			private String policyType; // indemnity or managed care
			private String parentId; 
			private String parentName; 
			private String memberGroupCode; 
			private String createdTime;
			private String modifiedTime;

			
	// -- exported key end

	// Fields End

	
	public String getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getMemberGroupCode() {
		return memberGroupCode;
	}
	public void setMemberGroupCode(String memberGroupCode) {
		this.memberGroupCode = memberGroupCode;
	}
	public String getCardNumber() {
		return CardNumber;
	}
	public void setCardNumber(String CardNumber) {
		this.CardNumber = CardNumber;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}	
	public String getParentName() {
				return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public java.lang.Integer getMemberId(){
		return memberId;
	}
	public void setMemberId(java.lang.Integer value){
		memberId = value;
	}
	
				
	public java.lang.String getFirstName(){
		return firstName;
	}
	public void setFirstName(java.lang.String value){
		firstName = value;
	}
				

				
	public java.lang.String getCustomerPolicyNumber(){
		return customerPolicyNumber;
	}
	public void setCustomerPolicyNumber(java.lang.String value){
		customerPolicyNumber = value;
	}
				
	public java.sql.Date getEffectiveDate(){
		return effectiveDate;
	}
	public void setEffectiveDate(java.sql.Date value){
		effectiveDate = value;
	}
				
	public java.sql.Date getRenewalDate(){
		return renewalDate;
	}
	public void setRenewalDate(java.sql.Date value){
		renewalDate = value;
	}
				
	public java.sql.Date getJoinDate(){
		return joinDate;
	}
	public void setJoinDate(java.sql.Date value){
		joinDate = value;
	}
				
	public java.sql.Date getResignedDate(){
		return resignedDate;
	}
	public void setResignedDate(java.sql.Date value){
		resignedDate = value;
	}
	
	public java.sql.Date getExpireDate(){
		return expireDate;
	}
	public void setExpireDate(java.sql.Date value){
		expireDate = value;
	}
	
	
	public java.sql.Date getBirthday(){
		return birthday;
	}
	public void setBirthday(java.sql.Date value){
		birthday = value;
	}
	

	
	public String getMemberGroupName() {
		return memberGroupName;
	}
	public void setMemberGroupName(String memberGroupName) {
		this.memberGroupName = memberGroupName;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	public String getMemberGroupId() {
		return memberGroupId;
	}
	public void setMemberGroupId(String memberGroupId) {
		this.memberGroupId = memberGroupId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCurrentBenefitCode() {
		return currentBenefitCode;
	}
	public void setCurrentBenefitCode(String currentBenefitCode) {
		this.currentBenefitCode = currentBenefitCode;
	}
	public String getAnnualPremium() {
		return annualPremium;
	}
	public void setAnnualPremium(String annualPremium) {
		this.annualPremium = annualPremium;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBankBranch() {
		return bankBranch;
	}
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	public String getBankAccountName() {
		return bankAccountName;
	}
	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	public String getPolicyNumber() {
		return PolicyNumber;
	}
	public void setPolicyNumber(String PolicyNumber) {
		this.PolicyNumber = PolicyNumber;
	}
	public String getCardStatus() {
		return cardStatus;
	}
	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}
	public String getPolicyType() {
		return policyType;
	}
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}
	
	
	//exported key end



}