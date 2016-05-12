
package com.ametis.cms.datamodel;


import java.sql.*;
import java.sql.Date;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="report_request")
public class ReportRequest implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	public static final int GROUP_SUMMARY_REPORT = 1;
	public static final int GROUP_CLAIM_TABULATION_REPORT = 2;
	public static final int CLIENT_SUMMARY_REPORT = 3;
	public static final int CLIENT_CLAIM_TABULATION_REPORT = 4;

	//Fields
		
	/**data for the column :
	
 --------- report_request.id --------- 
 schema        = null
 tableName     = report_request
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
	
 --------- report_request.request_time --------- 
 schema        = null
 tableName     = report_request
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = on update CURRENT_TIMESTAMP
 defaultValue  = CURRENT_TIMESTAMP
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 3
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp requestTime;
			
	/**data for the column :
	
 --------- report_request.report_type --------- 
 schema        = null
 tableName     = report_request
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private Integer reportType;
			
	/**data for the column :
	
 --------- report_request.table_options --------- 
 schema        = null
 tableName     = report_request
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
	private String tableOptions;
			
	/**data for the column :
	
 --------- report_request.output_extension --------- 
 schema        = null
 tableName     = report_request
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 10
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String outputExtension;
			
	/**data for the column :
	
 --------- report_request.url_report_result --------- 
 schema        = null
 tableName     = report_request
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String urlReportResult;
			
	/**data for the column :
	
 --------- report_request.status --------- 
 schema        = null
 tableName     = report_request
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 1
 type          = -6 
 isPrimaryKey  = false

=========================================


*/
	private Integer status;
			
	/**data for the column :
	
 --------- report_request.created_time --------- 
 schema        = null
 tableName     = report_request
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 9
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- report_request.created_by --------- 
 schema        = null
 tableName     = report_request
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- report_request.modified_time --------- 
 schema        = null
 tableName     = report_request
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 11
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- report_request.modified_by --------- 
 schema        = null
 tableName     = report_request
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private String modifiedBy;
			
	/**data for the column :
	
 --------- report_request.deleted_time --------- 
 schema        = null
 tableName     = report_request
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 13
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- report_request.deleted_by --------- 
 schema        = null
 tableName     = report_request
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private String deletedBy;
			
	/**data for the column :
	
 --------- report_request.deleted_status --------- 
 schema        = null
 tableName     = report_request
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 15
 size          = 1
 type          = -6 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- report_request.query --------- 
 schema        = null
 tableName     = report_request
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 16
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String query;
		
	// foreign affairs
	
			/** User
	data for the foreign class :
	
 --------- user.user_id --------- 
 schema        = null
 tableName     = user
 foreignCol    = user_id
 foreignTab    = report_request
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
	private User userId;
	private String reportSubjectType;
	private Client clientId;
	private MemberGroup memberGroupId;
	private Provider providerId;
	private Broker brokerId;
	private String requestNumber;
	private Date startDate;
	private Date endDate;
	
			
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

									@Column(name="request_time")
	public java.sql.Timestamp getRequestTime(){
		return requestTime;
	}
	public void setRequestTime(java.sql.Timestamp value){
		requestTime = value;
	}
				@Column(name="report_type")
	public java.lang.Integer getReportType(){
		return reportType;
	}
	public void setReportType(java.lang.Integer value){
		reportType = value;
	}
				@Column(name="table_options")
	public java.lang.String getTableOptions(){
		return tableOptions;
	}
	public void setTableOptions(java.lang.String value){
		tableOptions = value;
	}
				@Column(name="output_extension")
	public java.lang.String getOutputExtension(){
		return outputExtension;
	}
	public void setOutputExtension(java.lang.String value){
		outputExtension = value;
	}
				@Column(name="url_report_result")
	public java.lang.String getUrlReportResult(){
		return urlReportResult;
	}
	public void setUrlReportResult(java.lang.String value){
		urlReportResult = value;
	}
				@Column(name="status")
	public java.lang.Integer getStatus(){
		return status;
	}
	public void setStatus(java.lang.Integer value){
		status = value;
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
				@Column(name="deleted_status")
	public java.lang.Integer getDeletedStatus(){
		return deletedStatus;
	}
	public void setDeletedStatus(java.lang.Integer value){
		deletedStatus = value;
	}
				@Column(name="query")
	public java.lang.String getQuery(){
		return query;
	}
	public void setQuery(java.lang.String value){
		query = value;
	}
		
	// foreign affairs
	
			/** User */
	@ManyToOne
	@JoinColumn(name="user_id")
	public User getUserId(){
		return this.userId;
	}

	/** User */
	public void setUserId(User obj){
		this.userId = obj;
	}
	@Column(name="report_subject_type")
	public String getReportSubjectType() {
		return reportSubjectType;
	}
	public void setReportSubjectType(String reportSubjectType) {
		this.reportSubjectType = reportSubjectType;
	}
	@ManyToOne
	@JoinColumn(name="client_id")
	public Client getClientId() {
		return clientId;
	}
	public void setClientId(Client clientId) {
		this.clientId = clientId;
	}
	@ManyToOne
	@JoinColumn(name="member_group_id")
	public MemberGroup getMemberGroupId() {
		return memberGroupId;
	}
	public void setMemberGroupId(MemberGroup memberGroupId) {
		this.memberGroupId = memberGroupId;
	}
	@ManyToOne
	@JoinColumn(name="provider_id")
	public Provider getProviderId() {
		return providerId;
	}
	public void setProviderId(Provider providerId) {
		this.providerId = providerId;
	}
	@ManyToOne
	@JoinColumn(name="broker_id")
	public Broker getBrokerId() {
		return brokerId;
	}
	public void setBrokerId(Broker brokerId) {
		this.brokerId = brokerId;
	}
	@Column(name="request_number")
	public String getRequestNumber() {
		return requestNumber;
	}
	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}
	@Column(name="start_date")
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@Column(name="end_date")
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	


}