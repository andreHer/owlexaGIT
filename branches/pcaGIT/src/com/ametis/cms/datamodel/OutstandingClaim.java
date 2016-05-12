
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="outstanding_claim")
public class OutstandingClaim implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- outstanding_claim.outstanding_claim_id --------- 
 schema        = null
 tableName     = outstanding_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 15
 type          = -5 
 isPrimaryKey  = true

=========================================


*/
	private Long outstandingClaimId;
			
	/**data for the column :
	
 --------- outstanding_claim.claim_date --------- 
 schema        = null
 tableName     = outstanding_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date claimDate;
			
	/**data for the column :
	
 --------- outstanding_claim.created_time --------- 
 schema        = null
 tableName     = outstanding_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
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
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- outstanding_claim.member_number --------- 
 schema        = null
 tableName     = outstanding_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
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
	private String memberNumber;
			
	/**data for the column :
	
 --------- outstanding_claim.created_by --------- 
 schema        = null
 tableName     = outstanding_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
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
	private String createdBy;
			
	/**data for the column :
	
 --------- outstanding_claim.provider_code --------- 
 schema        = null
 tableName     = outstanding_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
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
	private String providerCode;
			
	/**data for the column :
	
 --------- outstanding_claim.claim_value --------- 
 schema        = null
 tableName     = outstanding_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double claimValue;
	private Double excessValue;
	private Double approvedValue;
			
	/**data for the column :
	
 --------- outstanding_claim.modified_time --------- 
 schema        = null
 tableName     = outstanding_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
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
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- outstanding_claim.modified_by --------- 
 schema        = null
 tableName     = outstanding_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
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
	private String modifiedBy;
			
	/**data for the column :
	
 --------- outstanding_claim.is_converted --------- 
 schema        = null
 tableName     = outstanding_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer isConverted;
			
	/**data for the column :
	
 --------- outstanding_claim.claim_id --------- 
 schema        = null
 tableName     = outstanding_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
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
	private Claim claimId;
			
	/**data for the column :
	
 --------- outstanding_claim.member_name --------- 
 schema        = null
 tableName     = outstanding_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 100
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String memberName;
			
	/**data for the column :
	
 --------- outstanding_claim.service_code --------- 
 schema        = null
 tableName     = outstanding_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 4
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String serviceCode;
			
	/**data for the column :
	
 --------- outstanding_claim.case_category_id --------- 
 schema        = null
 tableName     = outstanding_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
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
	private CaseCategory caseCategoryId;
			
	/**data for the column :
	
 --------- outstanding_claim.conversion_time --------- 
 schema        = null
 tableName     = outstanding_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
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
	private java.sql.Timestamp conversionTime;
			
	/**data for the column :
	
 --------- outstanding_claim.coverted_by --------- 
 schema        = null
 tableName     = outstanding_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
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
	private String covertedBy;
			
	/**data for the column :
	
 --------- outstanding_claim.diagnosis1_code --------- 
 schema        = null
 tableName     = outstanding_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 17
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String diagnosis1Code;
			
	/**data for the column :
	
 --------- outstanding_claim.diagnosis2_code --------- 
 schema        = null
 tableName     = outstanding_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
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
	private String diagnosis2Code;
			
	/**data for the column :
	
 --------- outstanding_claim.diagnosis3_code --------- 
 schema        = null
 tableName     = outstanding_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 19
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String diagnosis3Code;
			
	/**data for the column :
	
 --------- outstanding_claim.description --------- 
 schema        = null
 tableName     = outstanding_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 20
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String description;
	private Diagnosis diagnosis1Id;
	private Diagnosis diagnosis2Id;
	private Diagnosis diagnosis3Id;
	private Provider providerId;
	private Member memberId;
	private Double precalculatedExcessValue;
	private Double precalculatedApprovedValue;
	
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="outstanding_claim_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Long getOutstandingClaimId(){
		return outstandingClaimId;
	}
	public void setOutstandingClaimId(java.lang.Long value){
		outstandingClaimId = value;
	}
			// PK GETTER SETTER END

						@Column(name="claim_date")
	public java.sql.Date getClaimDate(){
		return claimDate;
	}
	public void setClaimDate(java.sql.Date value){
		claimDate = value;
	}
				@Column(name="created_time")
	public java.sql.Timestamp getCreatedTime(){
		return createdTime;
	}
	public void setCreatedTime(java.sql.Timestamp value){
		createdTime = value;
	}
				@Column(name="member_number")
	public java.lang.String getMemberNumber(){
		return memberNumber;
	}
	public void setMemberNumber(java.lang.String value){
		memberNumber = value;
	}
				@Column(name="created_by")
	public java.lang.String getCreatedBy(){
		return createdBy;
	}
	public void setCreatedBy(java.lang.String value){
		createdBy = value;
	}
				@Column(name="provider_code")
	public java.lang.String getProviderCode(){
		return providerCode;
	}
	public void setProviderCode(java.lang.String value){
		providerCode = value;
	}
				@Column(name="claim_value")
	public java.lang.Double getClaimValue(){
		return claimValue;
	}
	public void setClaimValue(java.lang.Double value){
		claimValue = value;
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
				@Column(name="is_converted")
	public java.lang.Integer getIsConverted(){
		return isConverted;
	}
	public void setIsConverted(java.lang.Integer value){
		isConverted = value;
	}
	@ManyToOne
	@JoinColumn(name="claim_id")
	public Claim getClaimId(){
		return claimId;
	}
	public void setClaimId(Claim value){
		claimId = value;
	}
				@Column(name="member_name")
	public java.lang.String getMemberName(){
		return memberName;
	}
	public void setMemberName(java.lang.String value){
		memberName = value;
	}
				@Column(name="service_code")
	public java.lang.String getServiceCode(){
		return serviceCode;
	}
	public void setServiceCode(java.lang.String value){
		serviceCode = value;
	}
	@ManyToOne
	@JoinColumn(name="case_category_id")
	public CaseCategory getCaseCategoryId(){
		return caseCategoryId;
	}
	public void setCaseCategoryId(CaseCategory value){
		caseCategoryId = value;
	}
				@Column(name="conversion_time")
	public java.sql.Timestamp getConversionTime(){
		return conversionTime;
	}
	public void setConversionTime(java.sql.Timestamp value){
		conversionTime = value;
	}
				@Column(name="coverted_by")
	public java.lang.String getCovertedBy(){
		return covertedBy;
	}
	public void setCovertedBy(java.lang.String value){
		covertedBy = value;
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
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
	
	@ManyToOne
	@JoinColumn(name="diagnosis1_id")
	public Diagnosis getDiagnosis1Id() {
		return diagnosis1Id;
	}
	public void setDiagnosis1Id(Diagnosis diagnosis1Id) {
		this.diagnosis1Id = diagnosis1Id;
	}
	@ManyToOne
	@JoinColumn(name="diagnosis2_id")
	public Diagnosis getDiagnosis2Id() {
		return diagnosis2Id;
	}
	public void setDiagnosis2Id(Diagnosis diagnosis2Id) {
		this.diagnosis2Id = diagnosis2Id;
	}
	@ManyToOne
	@JoinColumn(name="diagnosis3_id")
	public Diagnosis getDiagnosis3Id() {
		return diagnosis3Id;
	}
	public void setDiagnosis3Id(Diagnosis diagnosis3Id) {
		this.diagnosis3Id = diagnosis3Id;
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
	@JoinColumn(name="member_id")
	public Member getMemberId() {
		return memberId;
	}
	public void setMemberId(Member memberId) {
		this.memberId = memberId;
	}
	@Column(name="excess_value")
	public Double getExcessValue() {
		return excessValue;
	}
	public void setExcessValue(Double excessValue) {
		this.excessValue = excessValue;
	}
	@Column(name="approved_value")
	public Double getApprovedValue() {
		return approvedValue;
	}
	public void setApprovedValue(Double approvedValue) {
		this.approvedValue = approvedValue;
	}
	@Column(name="precalculated_excess_value")
	public Double getPrecalculatedExcessValue() {
		return precalculatedExcessValue;
	}
	public void setPrecalculatedExcessValue(Double precalculatedExcessValue) {
		this.precalculatedExcessValue = precalculatedExcessValue;
	}
	@Column(name="precalculated_approved_value")
	public Double getPrecalculatedApprovedValue() {
		return precalculatedApprovedValue;
	}
	public void setPrecalculatedApprovedValue(Double precalculatedApprovedValue) {
		this.precalculatedApprovedValue = precalculatedApprovedValue;
	}
	
	
	
	
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
		
	//exported key end



}