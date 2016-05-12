
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="policy_coverage_fund")
public class PolicyCoverageFund implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- policy_coverage_fund.policy_coverage_fund_id --------- 
 schema        = null
 tableName     = policy_coverage_fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	private Integer policyCoverageFundId;
			
	/**data for the column :
	
 --------- policy_coverage_fund.case_category_id --------- 
 schema        = null
 tableName     = policy_coverage_fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	private CaseCategory caseCategoryId;
			
	/**data for the column :
	
 --------- policy_coverage_fund.policy_id --------- 
 schema        = null
 tableName     = policy_coverage_fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 10
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Policy policyId;
			
	/**data for the column :
	
 --------- policy_coverage_fund.current_fund_value --------- 
 schema        = null
 tableName     = policy_coverage_fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	private Double currentFundValue;
			
	/**data for the column :
	
 --------- policy_coverage_fund.created_time --------- 
 schema        = null
 tableName     = policy_coverage_fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = on update CURRENT_TIMESTAMP
 defaultValue  = CURRENT_TIMESTAMP
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 5
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- policy_coverage_fund.created_by --------- 
 schema        = null
 tableName     = policy_coverage_fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	private String createdBy;
			
	/**data for the column :
	
 --------- policy_coverage_fund.modified_time --------- 
 schema        = null
 tableName     = policy_coverage_fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- policy_coverage_fund.modified_by --------- 
 schema        = null
 tableName     = policy_coverage_fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	private String modifiedBy;
			
	/**data for the column :
	
 --------- policy_coverage_fund.deleted_time --------- 
 schema        = null
 tableName     = policy_coverage_fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	
 --------- policy_coverage_fund.deleted_by --------- 
 schema        = null
 tableName     = policy_coverage_fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	
 --------- policy_coverage_fund.deleted_status --------- 
 schema        = null
 tableName     = policy_coverage_fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- policy_coverage_fund.status --------- 
 schema        = null
 tableName     = policy_coverage_fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer status;
			
	/**data for the column :
	
 --------- policy_coverage_fund.excess_fund_value --------- 
 schema        = null
 tableName     = policy_coverage_fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double excessFundValue;
	private Double totalAsoValue;
	private Double totalExcessValue;
	
	private Double minimumExcessValue;
	private Double warningExcessValue;
	
	private Double minimumAsoValue;
	private Double warningAsoValue;
	
	private Double fundWarningPercentage;
	private Double blockWarningPercentage;
	
	private Double initialAsoFundValue;
	private Double initialExcessFundValue;
	
	private Double currentAsoFundFinanceValue;
	private Double currentExcessFundFinanceValue;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="policy_coverage_fund_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public java.lang.Integer getPolicyCoverageFundId(){
		return policyCoverageFundId;
	}
	public void setPolicyCoverageFundId(java.lang.Integer value){
		policyCoverageFundId = value;
	}
			// PK GETTER SETTER END

	@ManyToOne
	@JoinColumn(name="case_category_id")
	public CaseCategory getCaseCategoryId(){
		return caseCategoryId;
	}
	public void setCaseCategoryId(CaseCategory value){
		caseCategoryId = value;
	}
	@ManyToOne
	@JoinColumn(name="policy_id")
	public Policy getPolicyId(){
		return policyId;
	}
	public void setPolicyId(Policy value){
		policyId = value;
	}
				@Column(name="current_fund_value")
	public java.lang.Double getCurrentFundValue(){
		return currentFundValue;
	}
	public void setCurrentFundValue(java.lang.Double value){
		currentFundValue = value;
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
				@Column(name="status")
	public java.lang.Integer getStatus(){
		return status;
	}
	public void setStatus(java.lang.Integer value){
		status = value;
	}
				@Column(name="excess_fund_value")
	public java.lang.Double getExcessFundValue(){
		return excessFundValue;
	}
	public void setExcessFundValue(java.lang.Double value){
		excessFundValue = value;
	}
	@Column(name="total_aso_value")
	public Double getTotalAsoValue() {
		return totalAsoValue;
	}
	public void setTotalAsoValue(Double totalAsoValue) {
		this.totalAsoValue = totalAsoValue;
	}
	@Column(name="total_excess_fund_value")
	public Double getTotalExcessValue() {
		return totalExcessValue;
	}
	public void setTotalExcessValue(Double excessValue) {
		this.totalExcessValue = excessValue;
	}
	@Column(name="minimum_excess_value")
	public Double getMinimumExcessValue() {
		return minimumExcessValue;
	}
	public void setMinimumExcessValue(Double minimumExcessValue) {
		this.minimumExcessValue = minimumExcessValue;
	}
	@Column(name="minimum_aso_value")
	public Double getMinimumAsoValue() {
		return minimumAsoValue;
	}
	public void setMinimumAsoValue(Double minimumAsoValue) {
		this.minimumAsoValue = minimumAsoValue;
	}
	@Column(name="fund_warning_percentage")
	public Double getFundWarningPercentage() {
		return fundWarningPercentage;
	}
	public void setFundWarningPercentage(Double fundWarningPercentage) {
		this.fundWarningPercentage = fundWarningPercentage;
	}
	@Column(name="block_warning_percentage")
	public Double getBlockWarningPercentage() {
		return blockWarningPercentage;
	}
	public void setBlockWarningPercentage(Double blockWarningPercentage) {
		this.blockWarningPercentage = blockWarningPercentage;
	}
	@Column(name="warning_aso_value")
	public Double getWarningAsoValue() {
		return warningAsoValue;
	}
	public void setWarningAsoValue(Double warningAsoValue) {
		this.warningAsoValue = warningAsoValue;
	}
	@Column(name="initial_aso_fund_value")
	public Double getInitialAsoFundValue() {
		return initialAsoFundValue;
	}
	public void setInitialAsoFundValue(Double initialAsoFundValue) {
		this.initialAsoFundValue = initialAsoFundValue;
	}
	@Column(name="warning_excess_value")
	public Double getWarningExcessValue() {
		return warningExcessValue;
	}
	public void setWarningExcessValue(Double warningExcessValue) {
		this.warningExcessValue = warningExcessValue;
	}
	@Column(name="initial_excess_fund_value")
	public Double getInitialExcessFundValue() {
		return initialExcessFundValue;
	}
	public void setInitialExcessFundValue(Double initialExcessFundValue) {
		this.initialExcessFundValue = initialExcessFundValue;
	}
	@Column(name="current_aso_fund_finance_value")
	public Double getCurrentAsoFundFinanceValue() {
		return currentAsoFundFinanceValue;
	}
	public void setCurrentAsoFundFinanceValue(Double currentAsoFundFinanceValue) {
		this.currentAsoFundFinanceValue = currentAsoFundFinanceValue;
	}
	@Column(name="current_excess_fund_finance_value")
	public Double getCurrentExcessFundFinanceValue() {
		return currentExcessFundFinanceValue;
	}
	public void setCurrentExcessFundFinanceValue(
			Double currentExcessFundFinanceValue) {
		this.currentExcessFundFinanceValue = currentExcessFundFinanceValue;
	}
		
	
	
	


}