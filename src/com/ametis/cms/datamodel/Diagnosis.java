
package com.ametis.cms.datamodel;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="diagnosis")
public class Diagnosis implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- diagnosis.diagnosis_id --------- 
 schema        = null
 tableName     = diagnosis
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
	private Integer diagnosisId;
			
	/**data for the column :
	
 --------- diagnosis.description --------- 
 schema        = null
 tableName     = diagnosis
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String description;
			
	/**data for the column :
	
 --------- diagnosis.diagnosis_code --------- 
 schema        = null
 tableName     = diagnosis
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
	private String diagnosisCode;
			
	/**data for the column :
	
 --------- diagnosis.diagnosis_name --------- 
 schema        = null
 tableName     = diagnosis
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private String diagnosisName;
			
	/**data for the column :
	
 --------- diagnosis.initial_symptom --------- 
 schema        = null
 tableName     = diagnosis
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private String initialSymptom;
			
	/**data for the column :
	
 --------- diagnosis.vital_sign --------- 
 schema        = null
 tableName     = diagnosis
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
	private String vitalSign;
			
	/**data for the column :
	
 --------- diagnosis.created_time --------- 
 schema        = null
 tableName     = diagnosis
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
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- diagnosis.created_by --------- 
 schema        = null
 tableName     = diagnosis
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- diagnosis.deleted_time --------- 
 schema        = null
 tableName     = diagnosis
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- diagnosis.deleted_by --------- 
 schema        = null
 tableName     = diagnosis
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private String deletedBy;
			
	/**data for the column :
	
 --------- diagnosis.modified_time --------- 
 schema        = null
 tableName     = diagnosis
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- diagnosis.modified_by --------- 
 schema        = null
 tableName     = diagnosis
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
	private String modifiedBy;
			
	/**data for the column :
	
 --------- diagnosis.deleted_status --------- 
 schema        = null
 tableName     = diagnosis
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	
	private String diagnosisGroupCode;
	
	/**data for the column :
	
 --------- diagnosis.diagnosis_group_code --------- 
 schema        = null
 tableName     = diagnosis
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
	
	private String diagnosisGroupDescription;
	
	/**data for the column :
	
 --------- diagnosis.diagnosis_group_description --------- 
 schema        = null
 tableName     = diagnosis
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
	
	private String diagnosisGroupCategory;
	
	/**data for the column :
	
 --------- diagnosis.diagnosis_group_category --------- 
 schema        = null
 tableName     = diagnosis
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
	private Integer deletedStatus;
	private String icd9Code;
	private String icd9Name;
	private String idnDiagnosisName;
	private String alternateCode;
	private String icd10AlternateCode;
	private Integer isChronic;
	private Poliklinik poliklinikId;
	private Integer isCongenital;
	private Integer isGreyArea;
	
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
			/** Case
	data for the exported class :
	
 --------- case.diagnosis1_id --------- 
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
 ordinal       = 11
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = diagnosis.diagnosis_id

=========================================



	 */
	private Set <Case> myCases = new HashSet(0);
							/** Claim
	data for the exported class :
	
 --------- claim.diagnosis_id --------- 
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
 ordinal       = 20
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = diagnosis.diagnosis_id

=========================================



	 */
	private Set <Claim> claims = new HashSet(0);
							/** DiagnosisItem
	data for the exported class :
	
 --------- diagnosis_item.diagnosis_id --------- 
 schema        = null
 tableName     = diagnosis_item
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
 this columns points to  = diagnosis.diagnosis_id

=========================================



	 */
	private Set <DiagnosisItem> diagnosisItems = new HashSet(0);
				/** MemberDiagnosis
	data for the exported class :
	
 --------- member_diagnosis.diagnosis_id --------- 
 schema        = null
 tableName     = member_diagnosis
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
 this columns points to  = diagnosis.diagnosis_id

=========================================



	 */
	private Set <MemberDiagnosis> memberDiagnosiss = new HashSet(0);
			
	private Integer isCritical;
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="diagnosis_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getDiagnosisId(){
		return diagnosisId;
	}
	public void setDiagnosisId(java.lang.Integer value){
		diagnosisId = value;
	}
			// PK GETTER SETTER END

						@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
				@Column(name="diagnosis_code")
	public java.lang.String getDiagnosisCode(){
		return diagnosisCode;
	}
	public void setDiagnosisCode(java.lang.String value){
		diagnosisCode = value;
	}
				@Column(name="diagnosis_name")
	public java.lang.String getDiagnosisName(){
		return diagnosisName;
	}
	public void setDiagnosisName(java.lang.String value){
		diagnosisName = value;
	}
				@Column(name="initial_symptom")
	public java.lang.String getInitialSymptom(){
		return initialSymptom;
	}
	public void setInitialSymptom(java.lang.String value){
		initialSymptom = value;
	}
				@Column(name="vital_sign")
	public java.lang.String getVitalSign(){
		return vitalSign;
	}
	public void setVitalSign(java.lang.String value){
		vitalSign = value;
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
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
			/** Case */
	@OneToMany(mappedBy="diagnosis1Id")
	public Set<Case> getCases(){
		return this.myCases;
	}

	/** Case */
	public void setCases(Set<Case> obj){
		this.myCases = obj;
	}
							/** Claim */
	@OneToMany(mappedBy="diagnosisId")
	public Set<Claim> getClaims(){
		return this.claims;
	}

	/** Claim */
	public void setClaims(Set<Claim> obj){
		this.claims = obj;
	}
							/** DiagnosisItem */
	@OneToMany(mappedBy="diagnosisId")
	public Set<DiagnosisItem> getDiagnosisItems(){
		return this.diagnosisItems;
	}

	/** DiagnosisItem */
	public void setDiagnosisItems(Set<DiagnosisItem> obj){
		this.diagnosisItems = obj;
	}
				/** MemberDiagnosis */
	@OneToMany(mappedBy="diagnosisId")
	public Set<MemberDiagnosis> getMemberDiagnosiss(){
		return this.memberDiagnosiss;
	}

	/** MemberDiagnosis */
	public void setMemberDiagnosiss(Set<MemberDiagnosis> obj){
		this.memberDiagnosiss = obj;
	}
	
	@Column(name="is_critical")
	public Integer getIsCritical() {
		return isCritical;
	}
	public void setIsCritical(Integer isCritical) {
		this.isCritical = isCritical;
	}
	@Column(name="icd_9_code")
	public String getIcd9Code() {
		return icd9Code;
	}
	public void setIcd9Code(String icd9Code) {
		this.icd9Code = icd9Code;
	}
	@Column(name="icd_9_name")
	public String getIcd9Name() {
		return icd9Name;
	}
	public void setIcd9Name(String icd9Name) {
		this.icd9Name = icd9Name;
	}
	@Column(name="idn_diagnosis_name")
	public String getIdnDiagnosisName() {
		return idnDiagnosisName;
	}
	public void setIdnDiagnosisName(String idnDiagnosisName) {
		this.idnDiagnosisName = idnDiagnosisName;
	}
	@Column(name="alternate_code")
	public String getAlternateCode() {
		return alternateCode;
	}
	public void setAlternateCode(String alternateCode) {
		this.alternateCode = alternateCode;
	}
	@Column(name="icd_10_alternate_code")
	public String getIcd10AlternateCode() {
		return icd10AlternateCode;
	}
	public void setIcd10AlternateCode(String icd10AlternateCode) {
		this.icd10AlternateCode = icd10AlternateCode;
	}
	@Column(name="is_chronic")
	public Integer getIsChronic() {
		return isChronic;
	}
	public void setIsChronic(Integer isChronic) {
		this.isChronic = isChronic;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="poliklinik_id")
	public Poliklinik getPoliklinikId() {
		return poliklinikId;
	}
	public void setPoliklinikId(Poliklinik poliklinikId) {
		this.poliklinikId = poliklinikId;
	}
	@Column(name="is_congenital")
	public Integer getIsCongenital() {
		return isCongenital;
	}
	public void setIsCongenital(Integer isCongenital) {
		this.isCongenital = isCongenital;
	}
	@Column(name="diagnosis_group_code")
	public String getDiagnosisGroupCode() {
		return diagnosisGroupCode;
	}
	public void setDiagnosisGroupCode(String diagnosisGroupCode) {
		this.diagnosisGroupCode = diagnosisGroupCode;
	}
	@Column(name="diagnosis_group_description")
	public String getDiagnosisGroupDescription() {
		return diagnosisGroupDescription;
	}
	public void setDiagnosisGroupDescription(String diagnosisGroupDescription) {
		this.diagnosisGroupDescription = diagnosisGroupDescription;
	}
	@Column(name="diagnosis_group_category")
	public String getDiagnosisGroupCategory() {
		return diagnosisGroupCategory;
	}
	public void setDiagnosisGroupCategory(String diagnosisGroupCategory) {
		this.diagnosisGroupCategory = diagnosisGroupCategory;
	}
	@Column(name="is_grey_area")
	public Integer getIsGreyArea() {
		return isGreyArea;
	}
	public void setIsGreyArea(Integer isGreyArea) {
		this.isGreyArea = isGreyArea;
	}
		
	
}