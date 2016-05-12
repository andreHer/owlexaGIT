
package com.ametis.cms.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class UserDto implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	//Fields
		
	/**data for the column :
	
 --------- user.user_id --------- 
 schema        = null
 tableName     = user
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
	private Integer userId;
			
	/**data for the column :
	
 --------- user.username --------- 
 schema        = null
 tableName     = user
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String username;
			
	/**data for the column :
	
 --------- user.password --------- 
 schema        = null
 tableName     = user
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
	private String password;
						
	/**data for the column :
	
 --------- user.first_name --------- 
 schema        = null
 tableName     = user
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
	private String firstName;
			
	/**data for the column :
	
 --------- user.last_name --------- 
 schema        = null
 tableName     = user
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String lastName;
			
	/**data for the column :
	
 --------- user.email --------- 
 schema        = null
 tableName     = user
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String email;
			
	/**data for the column :
	
 --------- user.telephone --------- 
 schema        = null
 tableName     = user
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String telephone;
			
	/**data for the column :
	
 --------- user.mobile_phone --------- 
 schema        = null
 tableName     = user
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String mobilePhone;
			
	/**data for the column :
	
 --------- user.telephone_ext --------- 
 schema        = null
 tableName     = user
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 10
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String telephoneExt;
			
	/**data for the column :
	
 --------- user.description --------- 
 schema        = null
 tableName     = user
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String description;
			
	/**data for the column :
	
 --------- user.created_by --------- 
 schema        = null
 tableName     = user
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- user.created_time --------- 
 schema        = null
 tableName     = user
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- user.modified_by --------- 
 schema        = null
 tableName     = user
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 14
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- user.modified_time --------- 
 schema        = null
 tableName     = user
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 15
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- user.deleted_by --------- 
 schema        = null
 tableName     = user
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 16
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- user.deleted_time --------- 
 schema        = null
 tableName     = user
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 17
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- user.deleted_status --------- 
 schema        = null
 tableName     = user
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 18
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- user.status --------- 
 schema        = null
 tableName     = user
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 19
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private String status;
		
	// foreign affairs
	
			/** Role
	data for the foreign class :
	
 --------- role.role_id --------- 
 schema        = null
 tableName     = role
 foreignCol    = role_id
 foreignTab    = user
 catalog       = insurance
 remarks       = 
 defaultValue  = 
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 11
 type          = 4 
 isPrimaryKey  = true

=========================================



	 */
	private String roleId;
	private java.sql.Timestamp lastLoginTime;
	private String lastLoginIp;
	private String lastLoginSessionId;
	private String clientId;
	private String memberGroupId;
	private String userType;
	private String providerId;
	private String brokerId;
	private String memberId;
	private String tpaId;
	
	//Add by aju on 20151012, for mobile device id
	private String mobileDeviceId;
	
	private Boolean isSuccess;
	private String loginReason;
	private String registerReason;
	// -- exported key end

	// Fields End


	
	public java.lang.Integer getUserId(){
		return userId;
	}
	public void setUserId(java.lang.Integer value){
		userId = value;
	}
	
	public java.lang.String getUsername(){
		return username;
	}
	public void setUsername(java.lang.String value){
		username = value;
	}
				
	public java.lang.String getPassword(){
		return password;
	}
	public void setPassword(java.lang.String value){
		password = value;
	}
						
	public java.lang.String getFirstName(){
		return firstName;
	}
	public void setFirstName(java.lang.String value){
		firstName = value;
	}
				
	public java.lang.String getLastName(){
		return lastName;
	}
	public void setLastName(java.lang.String value){
		lastName = value;
	}
				
	public java.lang.String getEmail(){
		return email;
	}
	public void setEmail(java.lang.String value){
		email = value;
	}
				
	public java.lang.String getTelephone(){
		return telephone;
	}
	public void setTelephone(java.lang.String value){
		telephone = value;
	}
				
	public java.lang.String getMobilePhone(){
		return mobilePhone;
	}
	public void setMobilePhone(java.lang.String value){
		mobilePhone = value;
	}
				
	public java.lang.String getTelephoneExt(){
		return telephoneExt;
	}
	public void setTelephoneExt(java.lang.String value){
		telephoneExt = value;
	}
				
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
				
	public java.lang.String getCreatedBy(){
		return createdBy;
	}
	public void setCreatedBy(java.lang.String value){
		createdBy = value;
	}
				
	public java.sql.Timestamp getCreatedTime(){
		return createdTime;
	}
	public void setCreatedTime(java.sql.Timestamp value){
		createdTime = value;
	}
				
	public java.lang.String getModifiedBy(){
		return modifiedBy;
	}
	public void setModifiedBy(java.lang.String value){
		modifiedBy = value;
	}
				
	public java.sql.Timestamp getModifiedTime(){
		return modifiedTime;
	}
	public void setModifiedTime(java.sql.Timestamp value){
		modifiedTime = value;
	}
				
	public java.lang.String getDeletedBy(){
		return deletedBy;
	}
	public void setDeletedBy(java.lang.String value){
		deletedBy = value;
	}
				
	public java.sql.Timestamp getDeletedTime(){
		return deletedTime;
	}
	public void setDeletedTime(java.sql.Timestamp value){
		deletedTime = value;
	}
				
	public java.lang.Integer getDeletedStatus(){
		return deletedStatus;
	}
	public void setDeletedStatus(java.lang.Integer value){
		deletedStatus = value;
	}
	
	public String getStatus(){
		return status;
	}
	public void setStatus(String value){
		status = value;
	}
		
	
	public String getRoleId(){
		return this.roleId;
	}
	public void setRoleId(String obj){
		this.roleId = obj;
	}
	
	
	public java.sql.Timestamp getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(java.sql.Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	
	public String getLastLoginSession() {
		return lastLoginSessionId;
	}
	public void setLastLoginSession(String lastLoginSessionId) {
		this.lastLoginSessionId = lastLoginSessionId;
	}
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	public String getMemberGroupId() {
		return memberGroupId;
	}
	public void setMemberGroupId(String memberGroupId) {
		this.memberGroupId = memberGroupId;
	}
	
	
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	
	public String getBrokerId() {
		return brokerId;
	}
	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}
		
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	public String getTpaId() {
		return tpaId;
	}
	public void setTpaId(String tpaId) {
		this.tpaId = tpaId;
	}
	public String getMobileDeviceId() {
		return mobileDeviceId;
	}
	public void setMobileDeviceId(String mobileDeviceId) {
		this.mobileDeviceId = mobileDeviceId;
	}
	public Boolean getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getLoginReason() {
		return loginReason;
	}
	public void setLoginReason(String loginReason) {
		this.loginReason = loginReason;
	}
	public String getRegisterReason() {
		return registerReason;
	}
	public void setRegisterReason(String registerReason) {
		this.registerReason = registerReason;
	}
			

}