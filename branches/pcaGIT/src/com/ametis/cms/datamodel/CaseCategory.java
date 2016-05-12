
package com.ametis.cms.datamodel;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="case_category")
public class CaseCategory implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	public static final int INPATIENT = 1;
	public static final int OUTPATIENT = 2;
	public static final int MATERNITY = 3;
	public static final int DENTAL = 4;
	public static final int OPTICAL = 5;
	
	public static final int SPECIALIST = 6;  // PPK2 Jika Managed Care
	
	public static final int PREINPATIENT = 7;
	public static final int POSTINPATIENT = 8;
	public static final int MEDICAL_CHECK_UP = 9;
	public static final int PREPOSTINPATIENT = 10;
	public static final int MISC = 12; 
	
	public static final int GP_OUTPATIENT = 14;  // PPK1 Umum Jika Managed Care
	public static final int GP_DENTAL = 15; // PPK1 Gigi Jika Managed Care
	
	public static final int SPC_MATA = 15;
	public static final int SPC_ANAK = 16;
	public static final int SPC_THT = 17;
	public static final int SPC_JIWA = 18;
	public static final int SPC_OBSGYN = 19;
	public static final int SPC_SYARAF = 20;
	public static final int SPC_PARU = 21;
	public static final int SPC_JANTUNG = 22;
	public static final int SPC_INTERNIS = 23;
	public static final int SPC_KULIT_KELAMIN = 24;
	public static final int SPC_REHAB_MEDIK = 25;
	
	public static final String INPATIENT_STR = "IP";
	
	public static final String OUTPATIENT_STR = "OP";
	public static final String MATERNITY_STR = "MA";
	public static final String DENTAL_STR = "D";
	public static final String OPTICAL_STR = "OPT";
	public static final String SPECIALIST_STR = "SPC";
	public static final String PREINPATIENT_STR = "PREIP";
	public static final String POSTINPATIENT_STR = "POIP";
	public static final String MEDICAL_CHECK_UP_STR = "MCU";
	public static final String PREPOSTINPATIENT_STR = "PPIP";
	public static final String MISC_STR = "AB"; 
	
	
	// public constant for CLAIM TYPE
	public static final int CLAIM_CASHLESS = 1;
	public static final int CLAIM_REIMBURSEMENT = 2;
	public static final int CLAIM_BOTH = 3;
	
	// public constant for CASE TYPE
	
	public static final int CASE_GL = 1;
	public static final int CASE_GN = 2;
	public static final int CASE_BOTH = 3;
	//Fields
		
	/**data for the column :
	
 --------- case_category.case_category_id --------- 
 schema        = null
 tableName     = case_category
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
	private Integer caseCategoryId;
	private Integer caseType;
	private Integer claimType;
			
	/**data for the column :
	
 --------- case_category.created_time --------- 
 schema        = null
 tableName     = case_category
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- case_category.created_by --------- 
 schema        = null
 tableName     = case_category
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- case_category.deleted_time --------- 
 schema        = null
 tableName     = case_category
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- case_category.deleted_by --------- 
 schema        = null
 tableName     = case_category
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private String deletedBy;
			
	/**data for the column :
	
 --------- case_category.modified_time --------- 
 schema        = null
 tableName     = case_category
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- case_category.modified_by --------- 
 schema        = null
 tableName     = case_category
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- case_category.deleted_status --------- 
 schema        = null
 tableName     = case_category
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- case_category.case_category_name --------- 
 schema        = null
 tableName     = case_category
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
	private String caseCategoryName;
			
	/**data for the column :
	
 --------- case_category.description --------- 
 schema        = null
 tableName     = case_category
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String description;
	private String caseCategoryCode;	
	private String caseCategoryEdcCode;
	private CaseCategory parentCategoryId;
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
			/** Case
	data for the exported class :
	
 --------- case.case_category_id --------- 
 schema        = null
 tableName     = case
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = case_category.case_category_id

=========================================



	 */
	private Set <Case> myCases = new HashSet(0);
				/** Claim
	data for the exported class :
	
 --------- claim.case_category_id --------- 
 schema        = null
 tableName     = claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 26
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = case_category.case_category_id

=========================================



	 */
	private Set <Claim> claims = new HashSet(0);
				/** ProductBenefit
	data for the exported class :
	
 --------- product_benefit.case_category_id --------- 
 schema        = null
 tableName     = product_benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = case_category.case_category_id

=========================================



	 */
	private Set <ProductBenefit> productBenefits = new HashSet(0);
			
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="case_category_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getCaseCategoryId(){
		return caseCategoryId;
	}
	public void setCaseCategoryId(java.lang.Integer value){
		caseCategoryId = value;
	}
			// PK GETTER SETTER END

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
	
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	@Column(name="case_type")
	public Integer getCaseType() {
		return caseType;
	}
	public void setCaseType(Integer caseType) {
		this.caseType = caseType;
	}
	@Column(name="claim_type")
	public Integer getClaimType() {
		return claimType;
	}
	public void setClaimType(Integer claimType) {
		this.claimType = claimType;
	}
			/** Case */
	@OneToMany(mappedBy="caseCategoryId")
	public Set<Case> getCases(){
		return this.myCases;
	}

	/** Case */
	public void setCases(Set<Case> obj){
		this.myCases = obj;
	}
				/** Claim */
	@OneToMany(mappedBy="caseCategoryId")
	public Set<Claim> getClaims(){
		return this.claims;
	}

	/** Claim */
	public void setClaims(Set<Claim> obj){
		this.claims = obj;
	}
				/** ProductBenefit */
	@OneToMany(mappedBy="caseCategoryId")
	public Set<ProductBenefit> getProductBenefits(){
		return this.productBenefits;
	}

	/** ProductBenefit */
	public void setProductBenefits(Set<ProductBenefit> obj){
		this.productBenefits = obj;
	}
	@Column(name="case_category_code")
	public String getCaseCategoryCode() {
		return caseCategoryCode;
	}
	public void setCaseCategoryCode(String caseCategoryCode) {
		this.caseCategoryCode = caseCategoryCode;
	}
	@ManyToOne
	@JoinColumn(name="parent_id")
	public CaseCategory getParentCategoryId() {
		return parentCategoryId;
	}
	public void setParentCategoryId(CaseCategory parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}
	@Column(name="case_category_edc_code")
	public String getCaseCategoryEdcCode() {
		return caseCategoryEdcCode;
	}
	public void setCaseCategoryEdcCode(String caseCategoryEdcCode) {
		this.caseCategoryEdcCode = caseCategoryEdcCode;
	}
			
	
	
	//exported key end



}