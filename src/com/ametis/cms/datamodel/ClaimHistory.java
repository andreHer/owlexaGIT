
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="claim_history")
public class ClaimHistory implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- claim_history.claim_history_id --------- 
 schema        = null
 tableName     = claim_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-20150731
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 15
 type          = 4 
 isPrimaryKey  = true

=========================================


*/
	private Integer claimHistoryId;
						
	/**data for the column :
	
 --------- claim_history.claim_number --------- 
 schema        = null
 tableName     = claim_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-20150731
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
	private String claimNumber;
			
	/**data for the column :
	
 --------- claim_history.diagnosis1_code --------- 
 schema        = null
 tableName     = claim_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-20150731
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String diagnosis1Code;
			
	/**data for the column :
	
 --------- claim_history.diagnosis2_code --------- 
 schema        = null
 tableName     = claim_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-20150731
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String diagnosis2Code;
			
	/**data for the column :
	
 --------- claim_history.diagnosis3_code --------- 
 schema        = null
 tableName     = claim_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-20150731
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
	private String diagnosis3Code;
			
	/**data for the column :
	
 --------- claim_history.diagnosis1_name --------- 
 schema        = null
 tableName     = claim_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-20150731
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
	private String diagnosis1Name;
			
	/**data for the column :
	
 --------- claim_history.diagnosis2_name --------- 
 schema        = null
 tableName     = claim_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-20150731
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String diagnosis2Name;
			
	/**data for the column :
	
 --------- claim_history.diagnosis3_name --------- 
 schema        = null
 tableName     = claim_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-20150731
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String diagnosis3Name;
			
	/**data for the column :
	
 --------- claim_history.provider_name --------- 
 schema        = null
 tableName     = claim_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-20150731
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String providerName;
			
	/**data for the column :
	
 --------- claim_history.member_name --------- 
 schema        = null
 tableName     = claim_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-20150731
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String memberName;
			
	/**data for the column :
	
 --------- claim_history.case_category_name --------- 
 schema        = null
 tableName     = claim_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-20150731
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String caseCategoryName;
			
	/**data for the column :
	
 --------- claim_history.description --------- 
 schema        = null
 tableName     = claim_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-20150731
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String description;
			
	/**data for the column :
	
 --------- claim_history.duration --------- 
 schema        = null
 tableName     = claim_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-20150731
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 14
 size          = 5
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer duration;
			
	/**data for the column :
	
 --------- claim_history.duration_string --------- 
 schema        = null
 tableName     = claim_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-20150731
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 15
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String durationString;
			
	/**data for the column :
	
 --------- claim_history.claim_status --------- 
 schema        = null
 tableName     = claim_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-20150731
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
	private CaseStatus claimStatus;
			
	/**data for the column :
	
 --------- claim_history.action_time --------- 
 schema        = null
 tableName     = claim_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-20150731
 remarks       = on update CURRENT_TIMESTAMP
 defaultValue  = CURRENT_TIMESTAMP
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 17
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp actionTime;
			
	/**data for the column :
	
 --------- claim_history.action_by --------- 
 schema        = null
 tableName     = claim_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-20150731
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 18
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String actionBy;
			
	/**data for the column :
	
 --------- claim_history.created_time --------- 
 schema        = null
 tableName     = claim_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-20150731
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 19
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- claim_history.created_by --------- 
 schema        = null
 tableName     = claim_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-20150731
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 20
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- claim_history.modified_time --------- 
 schema        = null
 tableName     = claim_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-20150731
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 21
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- claim_history.modified_by --------- 
 schema        = null
 tableName     = claim_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-20150731
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 22
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- claim_history.deleted_time --------- 
 schema        = null
 tableName     = claim_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-20150731
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 23
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- claim_history.deleted_by --------- 
 schema        = null
 tableName     = claim_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-20150731
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 24
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- claim_history.deleted_status --------- 
 schema        = null
 tableName     = claim_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-20150731
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 25
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- claim_history.claim_charge_value --------- 
 schema        = null
 tableName     = claim_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-20150731
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 26
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double claimChargeValue;
			
	/**data for the column :
	
 --------- claim_history.claim_approved_value --------- 
 schema        = null
 tableName     = claim_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-20150731
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 27
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double claimApprovedValue;
			
	/**data for the column :
	
 --------- claim_history.claim_excess_value --------- 
 schema        = null
 tableName     = claim_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-20150731
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 28
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double claimExcessValue;
			
	/**data for the column :
	
 --------- claim_history.paid_to_provider --------- 
 schema        = null
 tableName     = claim_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-20150731
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 29
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double paidToProvider;
			
	/**data for the column :
	
 --------- claim_history.pre_paid_excess --------- 
 schema        = null
 tableName     = claim_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-20150731
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 30
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double prePaidExcess;
			
	/**data for the column :
	
 --------- claim_history.refund --------- 
 schema        = null
 tableName     = claim_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-20150731
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 31
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double refund;
			
	/**data for the column :
	
 --------- claim_history.claim_status_name --------- 
 schema        = null
 tableName     = claim_history
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-20150731
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 32
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String claimStatusName;
		
	// foreign affairs
	
			/** Claim
	data for the foreign class :
	
 --------- claim.claim_id --------- 
 schema        = null
 tableName     = claim
 foreignCol    = claim_id
 foreignTab    = claim_history
 catalog       = mp-20150731
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
	private Claim claimId;

	private String actionType;

	// PK GETTER SETTER
			@Id
	@Column(name="claim_history_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getClaimHistoryId(){
		return claimHistoryId;
	}
	public void setClaimHistoryId(java.lang.Integer value){
		claimHistoryId = value;
	}
			// PK GETTER SETTER END

									@Column(name="claim_number")
	public java.lang.String getClaimNumber(){
		return claimNumber;
	}
	public void setClaimNumber(java.lang.String value){
		claimNumber = value;
	}
				@Column(name="diagnosis1_code")
	public java.lang.String getDiagnosis1Code(){
		return diagnosis1Code;
	}
	public void setDiagnosis1Code(java.lang.String value){
		diagnosis1Code = value;
	}
				@Column(name="diagnosis2_code")
	public java.lang.String getDiagnosis2Code(){
		return diagnosis2Code;
	}
	public void setDiagnosis2Code(java.lang.String value){
		diagnosis2Code = value;
	}
				@Column(name="diagnosis3_code")
	public java.lang.String getDiagnosis3Code(){
		return diagnosis3Code;
	}
	public void setDiagnosis3Code(java.lang.String value){
		diagnosis3Code = value;
	}
				@Column(name="diagnosis1_name")
	public java.lang.String getDiagnosis1Name(){
		return diagnosis1Name;
	}
	public void setDiagnosis1Name(java.lang.String value){
		diagnosis1Name = value;
	}
				@Column(name="diagnosis2_name")
	public java.lang.String getDiagnosis2Name(){
		return diagnosis2Name;
	}
	public void setDiagnosis2Name(java.lang.String value){
		diagnosis2Name = value;
	}
				@Column(name="diagnosis3_name")
	public java.lang.String getDiagnosis3Name(){
		return diagnosis3Name;
	}
	public void setDiagnosis3Name(java.lang.String value){
		diagnosis3Name = value;
	}
				@Column(name="provider_name")
	public java.lang.String getProviderName(){
		return providerName;
	}
	public void setProviderName(java.lang.String value){
		providerName = value;
	}
				@Column(name="member_name")
	public java.lang.String getMemberName(){
		return memberName;
	}
	public void setMemberName(java.lang.String value){
		memberName = value;
	}
				@Column(name="case_category_name")
	public java.lang.String getCaseCategoryName(){
		return caseCategoryName;
	}
	public void setCaseCategoryName(java.lang.String value){
		caseCategoryName = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
				@Column(name="duration")
	public java.lang.Integer getDuration(){
		return duration;
	}
	public void setDuration(java.lang.Integer value){
		duration = value;
	}
				@Column(name="duration_string")
	public java.lang.String getDurationString(){
		return durationString;
	}
	public void setDurationString(java.lang.String value){
		durationString = value;
	}
	@ManyToOne
	@JoinColumn(name="claim_status")
	public CaseStatus getClaimStatus(){
		return claimStatus;
	}
	public void setClaimStatus(CaseStatus value){
		claimStatus = value;
	}
				@Column(name="action_time")
	public java.sql.Timestamp getActionTime(){
		return actionTime;
	}
	public void setActionTime(java.sql.Timestamp value){
		actionTime = value;
	}
				@Column(name="action_by")
	public java.lang.String getActionBy(){
		return actionBy;
	}
	public void setActionBy(java.lang.String value){
		actionBy = value;
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
				@Column(name="claim_charge_value")
	public java.lang.Double getClaimChargeValue(){
		return claimChargeValue;
	}
	public void setClaimChargeValue(java.lang.Double value){
		claimChargeValue = value;
	}
				@Column(name="claim_approved_value")
	public java.lang.Double getClaimApprovedValue(){
		return claimApprovedValue;
	}
	public void setClaimApprovedValue(java.lang.Double value){
		claimApprovedValue = value;
	}
				@Column(name="claim_excess_value")
	public java.lang.Double getClaimExcessValue(){
		return claimExcessValue;
	}
	public void setClaimExcessValue(java.lang.Double value){
		claimExcessValue = value;
	}
				@Column(name="paid_to_provider")
	public java.lang.Double getPaidToProvider(){
		return paidToProvider;
	}
	public void setPaidToProvider(java.lang.Double value){
		paidToProvider = value;
	}
				@Column(name="pre_paid_excess")
	public java.lang.Double getPrePaidExcess(){
		return prePaidExcess;
	}
	public void setPrePaidExcess(java.lang.Double value){
		prePaidExcess = value;
	}
				@Column(name="refund")
	public java.lang.Double getRefund(){
		return refund;
	}
	public void setRefund(java.lang.Double value){
		refund = value;
	}
				@Column(name="claim_status_name")
	public java.lang.String getClaimStatusName(){
		return claimStatusName;
	}
	public void setClaimStatusName(java.lang.String value){
		claimStatusName = value;
	}
		
	// foreign affairs
	
			/** Claim */
	@ManyToOne
	@JoinColumn(name="claim_id")
	public Claim getClaimId(){
		return this.claimId;
	}

	/** Claim */
	public void setClaimId(Claim obj){
		this.claimId = obj;
	}
	@Column(name="action_type")
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	


}