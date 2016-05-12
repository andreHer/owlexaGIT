
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="edc_transaction_log")
public class EdcTransactionLog implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- edc_transaction_log.id --------- 
 schema        = null
 tableName     = edc_transaction_log
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private Integer id;
			
	/**data for the column :
	
 --------- edc_transaction_log.terminal_code --------- 
 schema        = null
 tableName     = edc_transaction_log
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 100
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String terminalCode;
			
	/**data for the column :
	
 --------- edc_transaction_log.merchant_code --------- 
 schema        = null
 tableName     = edc_transaction_log
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 100
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String merchantCode;
			
	/**data for the column :
	
 --------- edc_transaction_log.activity_time --------- 
 schema        = null
 tableName     = edc_transaction_log
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = CURRENT_TIMESTAMP
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp activityTime;
			
	/**data for the column :
	
 --------- edc_transaction_log.activity_log --------- 
 schema        = null
 tableName     = edc_transaction_log
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String activityLog;
			
	/**data for the column :
	
 --------- edc_transaction_log.action_code --------- 
 schema        = null
 tableName     = edc_transaction_log
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String actionCode;
			
	/**data for the column :
	
 --------- edc_transaction_log.card_number --------- 
 schema        = null
 tableName     = edc_transaction_log
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 100
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String cardNumber;
			
	/**data for the column :
	
 --------- edc_transaction_log.member_number --------- 
 schema        = null
 tableName     = edc_transaction_log
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 100
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String memberNumber;
	private String traceNumber;
	private String responseCode;
	private String referenceNumber;
	private String groupName;
	private String caseCategoryCode;
	private String providerName;
	private String additionalInfo;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getId(){
		return id;
	}
	public void setId(java.lang.Integer value){
		id = value;
	}
			// PK GETTER SETTER END

						@Column(name="terminal_code")
	public java.lang.String getTerminalCode(){
		return terminalCode;
	}
	public void setTerminalCode(java.lang.String value){
		terminalCode = value;
	}
				@Column(name="merchant_code")
	public java.lang.String getMerchantCode(){
		return merchantCode;
	}
	public void setMerchantCode(java.lang.String value){
		merchantCode = value;
	}
				@Column(name="activity_time")
	public java.sql.Timestamp getActivityTime(){
		return activityTime;
	}
	public void setActivityTime(java.sql.Timestamp value){
		activityTime = value;
	}
				@Column(name="activity_log")
	public java.lang.String getActivityLog(){
		return activityLog;
	}
	public void setActivityLog(java.lang.String value){
		activityLog = value;
	}
				@Column(name="action_code")
	public java.lang.String getActionCode(){
		return actionCode;
	}
	public void setActionCode(java.lang.String value){
		actionCode = value;
	}
				@Column(name="card_number")
	public java.lang.String getCardNumber(){
		return cardNumber;
	}
	public void setCardNumber(java.lang.String value){
		cardNumber = value;
	}
				@Column(name="member_number")
	public java.lang.String getMemberNumber(){
		return memberNumber;
	}
	public void setMemberNumber(java.lang.String value){
		memberNumber = value;
	}
	@Column(name="trace_number")
	public String getTraceNumber() {
		return traceNumber;
	}
	public void setTraceNumber(String traceNumber) {
		this.traceNumber = traceNumber;
	}
	@Column(name="response_code")
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	@Column(name="reference_number")
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	@Column(name="group_name")
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	@Column(name="case_category_code")
	public String getCaseCategoryCode() {
		return caseCategoryCode;
	}
	public void setCaseCategoryCode(String caseCategoryCode) {
		this.caseCategoryCode = caseCategoryCode;
	}
	@Column(name="provider_name")
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	@Column(name="additional_info")
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
		
	



}