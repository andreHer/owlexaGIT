
package com.ametis.cms.datamodel;


import java.sql.*;
import java.sql.Date;
import java.util.*;
import javax.persistence.*;

import org.hibernate.annotations.Generated;


@Entity
@Table(name="member_provider")
public class MemberProvider implements java.io.Serializable{
	public static final int PAYMENT_CAPITATION = 1;
	public static final int PAYMENT_FFS = 2;
	public static final int PAYMENT_INDEMNITY = 3;
	
	
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- member_provider.member_provider_id --------- 
 schema        = null
 tableName     = member_provider
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
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
	private Integer memberProviderId;
			
	/**data for the column :
	
 --------- member_provider.deleted_status --------- 
 schema        = null
 tableName     = member_provider
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
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
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- member_provider.modified_time --------- 
 schema        = null
 tableName     = member_provider
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- member_provider.modified_by --------- 
 schema        = null
 tableName     = member_provider
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
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
	private String modifiedBy;
			
	/**data for the column :
	
 --------- member_provider.deleted_by --------- 
 schema        = null
 tableName     = member_provider
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
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
	private String deletedBy;
			
	/**data for the column :
	
 --------- member_provider.deleted_time --------- 
 schema        = null
 tableName     = member_provider
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- member_provider.created_time --------- 
 schema        = null
 tableName     = member_provider
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
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
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- member_provider.created_by --------- 
 schema        = null
 tableName     = member_provider
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
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
	private String createdBy;
			
	/**data for the column :
	
 --------- member_provider.dental --------- 
 schema        = null
 tableName     = member_provider
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer dental;
			
	/**data for the column :
	
 --------- member_provider.inpatient --------- 
 schema        = null
 tableName     = member_provider
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer inpatient;
			
	/**data for the column :
	
 --------- member_provider.maternity --------- 
 schema        = null
 tableName     = member_provider
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer maternity;
			
	/**data for the column :
	
 --------- member_provider.optical --------- 
 schema        = null
 tableName     = member_provider
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer optical;
			
	/**data for the column :
	
 --------- member_provider.outpatient --------- 
 schema        = null
 tableName     = member_provider
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer outpatient;
	private Integer mcuLab;
			
	/**data for the column :
	
 --------- member_provider.ppk1 --------- 
 schema        = null
 tableName     = member_provider
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
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
	private Integer ppk1;
			
	/**data for the column :
	
 --------- member_provider.ppk2 --------- 
 schema        = null
 tableName     = member_provider
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 15
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer ppk2;
			
	/**data for the column :
	
 --------- member_provider.ppk3 --------- 
 schema        = null
 tableName     = member_provider
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 16
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer ppk3;
								
	// foreign affairs
	
			/** Member
	data for the foreign class :
	
 --------- member.member_id --------- 
 schema        = null
 tableName     = member
 foreignCol    = member_id
 foreignTab    = member_provider
 catalog       = healthcare
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
	private Member memberId;
				/** Provider
	data for the foreign class :
	
 --------- provider.provider_id --------- 
 schema        = null
 tableName     = provider
 foreignCol    = provider_id
 foreignTab    = member_provider
 catalog       = healthcare
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
	private Provider providerId;
	private Date lastReviewed;
	private Integer totalReviewed;
	private ProviderSet providerSetId;
	private Integer paymentType;
	
	private Integer mappingType;

	private String url;

	private String type;

	private Integer statusUpdate;

	private String uploadNote;
		// PK GETTER SETTER
			@Id
	@Column(name="member_provider_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getMemberProviderId(){
		return memberProviderId;
	}
	public void setMemberProviderId(java.lang.Integer value){
		memberProviderId = value;
	}
			// PK GETTER SETTER END

						@Column(name="deleted_status")
	public java.lang.Integer getDeletedStatus(){
		return deletedStatus;
	}
	public void setDeletedStatus(java.lang.Integer value){
		deletedStatus = value;
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
				@Column(name="deleted_by")
	public java.lang.String getDeletedBy(){
		return deletedBy;
	}
	public void setDeletedBy(java.lang.String value){
		deletedBy = value;
	}
				@Column(name="deleted_time")
	public java.sql.Timestamp getDeletedTime(){
		return deletedTime;
	}
	public void setDeletedTime(java.sql.Timestamp value){
		deletedTime = value;
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
				@Column(name="dental")
	public java.lang.Integer getDental(){
		return dental;
	}
	public void setDental(java.lang.Integer value){
		dental = value;
	}
				@Column(name="inpatient")
	public java.lang.Integer getInpatient(){
		return inpatient;
	}
	public void setInpatient(java.lang.Integer value){
		inpatient = value;
	}
				@Column(name="maternity")
	public java.lang.Integer getMaternity(){
		return maternity;
	}
	public void setMaternity(java.lang.Integer value){
		maternity = value;
	}
				@Column(name="optical")
	public java.lang.Integer getOptical(){
		return optical;
	}
	public void setOptical(java.lang.Integer value){
		optical = value;
	}
				@Column(name="outpatient")
	public java.lang.Integer getOutpatient(){
		return outpatient;
	}
	public void setOutpatient(java.lang.Integer value){
		outpatient = value;
	}
				@Column(name="ppk1")
	public java.lang.Integer getPpk1(){
		return ppk1;
	}
	public void setPpk1(java.lang.Integer value){
		ppk1 = value;
	}
				@Column(name="ppk2")
	public java.lang.Integer getPpk2(){
		return ppk2;
	}
	public void setPpk2(java.lang.Integer value){
		ppk2 = value;
	}
				@Column(name="ppk3")
	public java.lang.Integer getPpk3(){
		return ppk3;
	}
	public void setPpk3(java.lang.Integer value){
		ppk3 = value;
	}
								
	// foreign affairs
	
			/** Member */
	@ManyToOne
	@JoinColumn(name="member_id")
	public Member getMemberId(){
		return this.memberId;
	}

	/** Member */
	public void setMemberId(Member obj){
		this.memberId = obj;
	}
				/** Provider */
	@ManyToOne
	@JoinColumn(name="provider_id")
	public Provider getProviderId(){
		return this.providerId;
	}

	/** Provider */
	public void setProviderId(Provider obj){
		this.providerId = obj;
	}
	public void setValue(String key, String value){
		
	}
	@Column(name="last_reviewed")
	public Date getLastReviewed() {
		return lastReviewed;
	}
	public void setLastReviewed(Date lastReviewed) {
		this.lastReviewed = lastReviewed;
	}
	@Column(name="total_reviewed")
	public Integer getTotalReviewed() {
		return totalReviewed;
	}
	public void setTotalReviewed(Integer totalReviewed) {
		this.totalReviewed = totalReviewed;
	}
	@ManyToOne
	@JoinColumn(name="provider_set_id")
	public ProviderSet getProviderSetId() {
		return providerSetId;
	}
	public void setProviderSetId(ProviderSet providerSetId) {
		this.providerSetId = providerSetId;
	}
	@Column(name="mapping_type")
	public Integer getMappingType() {
		return mappingType;
	}
	public void setMappingType(Integer mappingType) {
		this.mappingType = mappingType;
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
	@Column(name="mcu_lab")
	public Integer getMcuLab() {
		return mcuLab;
	}
	public void setMcuLab(Integer mcuLab) {
		this.mcuLab = mcuLab;
	}
	@Column(name="payment_type")
	public Integer getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}
	

}