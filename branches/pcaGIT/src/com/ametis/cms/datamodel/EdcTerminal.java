
package com.ametis.cms.datamodel;


import java.sql.*;
import java.sql.Date;
import java.util.*;
import javax.persistence.*;

import org.hibernate.annotations.Generated;


@Entity
@Table(name="edc_terminal")
public class EdcTerminal implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- edc_terminal.id --------- 
 schema        = null
 tableName     = edc_terminal
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
	
 --------- edc_terminal.provider_id --------- 
 schema        = null
 tableName     = edc_terminal
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private Provider providerId;
			
	/**data for the column :
	
 --------- edc_terminal.location --------- 
 schema        = null
 tableName     = edc_terminal
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String location;
			
	/**data for the column :
	
 --------- edc_terminal.description --------- 
 schema        = null
 tableName     = edc_terminal
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String description;
			
	/**data for the column :
	
 --------- edc_terminal.device_number --------- 
 schema        = null
 tableName     = edc_terminal
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 150
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deviceNumber;
			
	/**data for the column :
	
 --------- edc_terminal.authorization_key --------- 
 schema        = null
 tableName     = edc_terminal
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	
	private Date lastTransaction;
	
	/**data for the column :
	
 --------- edc_terminal.last_transaction --------- 
 schema        = null
 tableName     = edc_terminal
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private String authorizationKey;
	private Integer deletedStatus;
	private Timestamp createdTime;
	private Timestamp modifiedTime;
	private Timestamp deletedTime;
	private String deletedBy;
	private String modifiedBy;
	private String createdBy;

	private String url;

	private String type;

	private Integer statusUpdate;

	private String uploadNote;
	
	private Integer isFault;
		
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

	@ManyToOne
	@JoinColumn(name="provider_id")
	public Provider getProviderId(){
		return providerId;
	}
	public void setProviderId(Provider value){
		providerId = value;
	}
				@Column(name="location")
	public java.lang.String getLocation(){
		return location;
	}
	public void setLocation(java.lang.String value){
		location = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
				@Column(name="device_number")
	public java.lang.String getDeviceNumber(){
		return deviceNumber;
	}
	public void setDeviceNumber(java.lang.String value){
		deviceNumber = value;
	}
				@Column(name="authorization_key")
	public java.lang.String getAuthorizationKey(){
		return authorizationKey;
	}
	public void setAuthorizationKey(java.lang.String value){
		authorizationKey = value;
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


	@Column(name="url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name="type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name="status_update")
	public Integer getStatusUpdate() {
		return statusUpdate;
	}
	public void setStatusUpdate(Integer statusUpdate) {
		this.statusUpdate = statusUpdate;
	}
	@Column(name="upload_note")
	public String getUploadNote() {
		return uploadNote;
	}
	public void setUploadNote(String uploadNote) {
		this.uploadNote = uploadNote;
	}
	@Column(name="last_transaction")
	public Date getLastTransaction() {
		return lastTransaction;
	}
	public void setLastTransaction(Date lastTransaction) {
		this.lastTransaction = lastTransaction;
	}
	@Column(name="is_fault")
	public Integer getIsFault() {
		return isFault;
	}
	public void setIsFault(Integer isFault) {
		this.isFault = isFault;
	}
}