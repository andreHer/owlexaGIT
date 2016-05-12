
package com.ametis.cms.datamodel;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="benefit")
public class Benefit implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// treatment location
	public static final int DOMESTIC_TREATMENT = 1;
	public static final int OVERSEAS_TREATMENT = 2;

	//Fields
		
	/**data for the column :
	
 --------- benefit.benefit_id --------- 
 schema        = null
 tableName     = benefit
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
	// DEDUCTION is start from 0% so default is no deduction
	private Double deduction;
	private CaseCategory serviceCategoryId;
	
	private Product product;
	private Double annualBenefitLimit;
	private Integer benefitId;
			
	/**data for the column :
	
 --------- benefit.benefit_name --------- 
 schema        = null
 tableName     = benefit
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
	private String benefitName;
			
	/**data for the column :
	
 --------- benefit.benefit_description --------- 
 schema        = null
 tableName     = benefit
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
	private String benefitDescription;
			
	/**data for the column :
	
 --------- benefit.benefit_value --------- 
 schema        = null
 tableName     = benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double benefitValue;
			
	/**data for the column :
	
 --------- benefit.benefit_type --------- 
 schema        = null
 tableName     = benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer benefitType;
			
	/**data for the column :
	
 --------- benefit.benefit_category_id --------- 
 schema        = null
 tableName     = benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer benefitCategoryId;
			
	/**data for the column :
	
 --------- benefit.benefit_measurement_unit --------- 
 schema        = null
 tableName     = benefit
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
	private String benefitMeasurementUnit;
			
	/**data for the column :
	
 --------- benefit.benefit_max_occurance --------- 
 schema        = null
 tableName     = benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double benefitMaxOccurance;
			
	/**data for the column :
	
 --------- benefit.benefit_occurance_unit --------- 
 schema        = null
 tableName     = benefit
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
	private String benefitOccuranceUnit;
			
	/**data for the column :
	
 --------- benefit.created_time --------- 
 schema        = null
 tableName     = benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- benefit.created_by --------- 
 schema        = null
 tableName     = benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- benefit.deleted_time --------- 
 schema        = null
 tableName     = benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- benefit.deleted_by --------- 
 schema        = null
 tableName     = benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- benefit.modified_time --------- 
 schema        = null
 tableName     = benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 14
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- benefit.modified_by --------- 
 schema        = null
 tableName     = benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 15
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- benefit.deleted_status --------- 
 schema        = null
 tableName     = benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private Integer deletedStatus;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="benefit_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getBenefitId(){
		return benefitId;
	}
	public void setBenefitId(java.lang.Integer value){
		benefitId = value;
	}
			// PK GETTER SETTER END

						@Column(name="benefit_name")
	public java.lang.String getBenefitName(){
		return benefitName;
	}
	public void setBenefitName(java.lang.String value){
		benefitName = value;
	}
				@Column(name="benefit_description")
	public java.lang.String getBenefitDescription(){
		return benefitDescription;
	}
	public void setBenefitDescription(java.lang.String value){
		benefitDescription = value;
	}
				@Column(name="benefit_value")
	public java.lang.Double getBenefitValue(){
		return benefitValue;
	}
	public void setBenefitValue(java.lang.Double value){
		benefitValue = value;
	}
				@Column(name="benefit_type")
	public java.lang.Integer getBenefitType(){
		return benefitType;
	}
	public void setBenefitType(java.lang.Integer value){
		benefitType = value;
	}
				@Column(name="benefit_category_id")
	public java.lang.Integer getBenefitCategoryId(){
		return benefitCategoryId;
	}
	public void setBenefitCategoryId(java.lang.Integer value){
		benefitCategoryId = value;
	}
				@Column(name="benefit_measurement_unit")
	public java.lang.String getBenefitMeasurementUnit(){
		return benefitMeasurementUnit;
	}
	public void setBenefitMeasurementUnit(java.lang.String value){
		benefitMeasurementUnit = value;
	}
				@Column(name="benefit_max_occurance")
	public java.lang.Double getBenefitMaxOccurance(){
		return benefitMaxOccurance;
	}
	public void setBenefitMaxOccurance(java.lang.Double value){
		benefitMaxOccurance = value;
	}
				@Column(name="benefit_occurance_unit")
	public java.lang.String getBenefitOccuranceUnit(){
		return benefitOccuranceUnit;
	}
	public void setBenefitOccuranceUnit(java.lang.String value){
		benefitOccuranceUnit = value;
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
	@Column(name="deduction")
	public Double getDeduction() {
		return deduction;
	}
	public void setDeduction(Double deduction) {
		this.deduction = deduction;
	}
	@ManyToOne
	@JoinColumn(name="service_category_id")
	public CaseCategory getServiceCategoryId() {
		return serviceCategoryId;
	}
	public void setServiceCategoryId(CaseCategory serviceCategoryId) {
		this.serviceCategoryId = serviceCategoryId;
	}
	
	
	
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
		
	//exported key end



}