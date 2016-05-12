
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="provider_claim_util_report")
public class ProviderClaimUtilReport implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- provider_claim_util_report.id --------- 
 schema        = null
 tableName     = provider_claim_util_report
 foreignCol    = 
 foreignTab    = 
 catalog       = wahdebug
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 10
 type          = 4 
 isPrimaryKey  = true

=========================================


*/
	private Integer id;
			
	/**data for the column :
	
 --------- provider_claim_util_report.provider_name --------- 
 schema        = null
 tableName     = provider_claim_util_report
 foreignCol    = 
 foreignTab    = 
 catalog       = wahdebug
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
	private String providerName;
			
	/**data for the column :
	
 --------- provider_claim_util_report.report_date --------- 
 schema        = null
 tableName     = provider_claim_util_report
 foreignCol    = 
 foreignTab    = 
 catalog       = wahdebug
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date reportDate;
			
	/**data for the column :
	
 --------- provider_claim_util_report.provider_periode --------- 
 schema        = null
 tableName     = provider_claim_util_report
 foreignCol    = 
 foreignTab    = 
 catalog       = wahdebug
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
	private String providerPeriode;
			
	/**data for the column :
	
 --------- provider_claim_util_report.total_claim --------- 
 schema        = null
 tableName     = provider_claim_util_report
 foreignCol    = 
 foreignTab    = 
 catalog       = wahdebug
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 10
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer totalClaim;
			
	/**data for the column :
	
 --------- provider_claim_util_report.total_nominal_claim --------- 
 schema        = null
 tableName     = provider_claim_util_report
 foreignCol    = 
 foreignTab    = 
 catalog       = wahdebug
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double totalNominalClaim;
			
	/**data for the column :
	
 --------- provider_claim_util_report.total_inpatient --------- 
 schema        = null
 tableName     = provider_claim_util_report
 foreignCol    = 
 foreignTab    = 
 catalog       = wahdebug
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 10
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer totalInpatient;
			
	/**data for the column :
	
 --------- provider_claim_util_report.total_nominal_inpatient --------- 
 schema        = null
 tableName     = provider_claim_util_report
 foreignCol    = 
 foreignTab    = 
 catalog       = wahdebug
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
	private Double totalNominalInpatient;
			
	/**data for the column :
	
 --------- provider_claim_util_report.total_outpatient --------- 
 schema        = null
 tableName     = provider_claim_util_report
 foreignCol    = 
 foreignTab    = 
 catalog       = wahdebug
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 10
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer totalOutpatient;
			
	/**data for the column :
	
 --------- provider_claim_util_report.total_nominal_outpatient --------- 
 schema        = null
 tableName     = provider_claim_util_report
 foreignCol    = 
 foreignTab    = 
 catalog       = wahdebug
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double totalNominalOutpatient;
			
	/**data for the column :
	
 --------- provider_claim_util_report.total_dental --------- 
 schema        = null
 tableName     = provider_claim_util_report
 foreignCol    = 
 foreignTab    = 
 catalog       = wahdebug
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 10
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer totalDental;
			
	/**data for the column :
	
 --------- provider_claim_util_report.total_nominal_dental --------- 
 schema        = null
 tableName     = provider_claim_util_report
 foreignCol    = 
 foreignTab    = 
 catalog       = wahdebug
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double totalNominalDental;
			
	/**data for the column :
	
 --------- provider_claim_util_report.total_maternity --------- 
 schema        = null
 tableName     = provider_claim_util_report
 foreignCol    = 
 foreignTab    = 
 catalog       = wahdebug
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 10
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer totalMaternity;
			
	/**data for the column :
	
 --------- provider_claim_util_report.total_nominal_materinity --------- 
 schema        = null
 tableName     = provider_claim_util_report
 foreignCol    = 
 foreignTab    = 
 catalog       = wahdebug
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 14
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double totalNominalMaterinity;
			
	/**data for the column :
	
 --------- provider_claim_util_report.total_optical --------- 
 schema        = null
 tableName     = provider_claim_util_report
 foreignCol    = 
 foreignTab    = 
 catalog       = wahdebug
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 15
 size          = 10
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer totalOptical;
			
	/**data for the column :
	
 --------- provider_claim_util_report.total_nominal_optical --------- 
 schema        = null
 tableName     = provider_claim_util_report
 foreignCol    = 
 foreignTab    = 
 catalog       = wahdebug
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 16
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double totalNominalOptical;
			
	/**data for the column :
	
 --------- provider_claim_util_report.total_mcu --------- 
 schema        = null
 tableName     = provider_claim_util_report
 foreignCol    = 
 foreignTab    = 
 catalog       = wahdebug
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 17
 size          = 10
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer totalMcu;
			
	/**data for the column :
	
 --------- provider_claim_util_report.total_nominal_mcu --------- 
 schema        = null
 tableName     = provider_claim_util_report
 foreignCol    = 
 foreignTab    = 
 catalog       = wahdebug
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 18
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double totalNominalMcu;
			
	/**data for the column :
	
 --------- provider_claim_util_report.provider_id --------- 
 schema        = null
 tableName     = provider_claim_util_report
 foreignCol    = 
 foreignTab    = 
 catalog       = wahdebug
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 19
 size          = 10
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer providerId;
		
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

						@Column(name="provider_name")
	public java.lang.String getProviderName(){
		return providerName;
	}
	public void setProviderName(java.lang.String value){
		providerName = value;
	}
				@Column(name="report_date")
	public java.sql.Date getReportDate(){
		return reportDate;
	}
	public void setReportDate(java.sql.Date value){
		reportDate = value;
	}
				@Column(name="provider_periode")
	public java.lang.String getProviderPeriode(){
		return providerPeriode;
	}
	public void setProviderPeriode(java.lang.String value){
		providerPeriode = value;
	}
				@Column(name="total_claim")
	public java.lang.Integer getTotalClaim(){
		return totalClaim;
	}
	public void setTotalClaim(java.lang.Integer value){
		totalClaim = value;
	}
				@Column(name="total_nominal_claim")
	public java.lang.Double getTotalNominalClaim(){
		return totalNominalClaim;
	}
	public void setTotalNominalClaim(java.lang.Double value){
		totalNominalClaim = value;
	}
				@Column(name="total_inpatient")
	public java.lang.Integer getTotalInpatient(){
		return totalInpatient;
	}
	public void setTotalInpatient(java.lang.Integer value){
		totalInpatient = value;
	}
				@Column(name="total_nominal_inpatient")
	public java.lang.Double getTotalNominalInpatient(){
		return totalNominalInpatient;
	}
	public void setTotalNominalInpatient(java.lang.Double value){
		totalNominalInpatient = value;
	}
				@Column(name="total_outpatient")
	public java.lang.Integer getTotalOutpatient(){
		return totalOutpatient;
	}
	public void setTotalOutpatient(java.lang.Integer value){
		totalOutpatient = value;
	}
				@Column(name="total_nominal_outpatient")
	public java.lang.Double getTotalNominalOutpatient(){
		return totalNominalOutpatient;
	}
	public void setTotalNominalOutpatient(java.lang.Double value){
		totalNominalOutpatient = value;
	}
				@Column(name="total_dental")
	public java.lang.Integer getTotalDental(){
		return totalDental;
	}
	public void setTotalDental(java.lang.Integer value){
		totalDental = value;
	}
				@Column(name="total_nominal_dental")
	public java.lang.Double getTotalNominalDental(){
		return totalNominalDental;
	}
	public void setTotalNominalDental(java.lang.Double value){
		totalNominalDental = value;
	}
				@Column(name="total_maternity")
	public java.lang.Integer getTotalMaternity(){
		return totalMaternity;
	}
	public void setTotalMaternity(java.lang.Integer value){
		totalMaternity = value;
	}
				@Column(name="total_nominal_materinity")
	public java.lang.Double getTotalNominalMaterinity(){
		return totalNominalMaterinity;
	}
	public void setTotalNominalMaterinity(java.lang.Double value){
		totalNominalMaterinity = value;
	}
				@Column(name="total_optical")
	public java.lang.Integer getTotalOptical(){
		return totalOptical;
	}
	public void setTotalOptical(java.lang.Integer value){
		totalOptical = value;
	}
				@Column(name="total_nominal_optical")
	public java.lang.Double getTotalNominalOptical(){
		return totalNominalOptical;
	}
	public void setTotalNominalOptical(java.lang.Double value){
		totalNominalOptical = value;
	}
				@Column(name="total_mcu")
	public java.lang.Integer getTotalMcu(){
		return totalMcu;
	}
	public void setTotalMcu(java.lang.Integer value){
		totalMcu = value;
	}
				@Column(name="total_nominal_mcu")
	public java.lang.Double getTotalNominalMcu(){
		return totalNominalMcu;
	}
	public void setTotalNominalMcu(java.lang.Double value){
		totalNominalMcu = value;
	}
				@Column(name="provider_id")
	public java.lang.Integer getProviderId(){
		return providerId;
	}
	public void setProviderId(java.lang.Integer value){
		providerId = value;
	}
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
		
	//exported key end



}