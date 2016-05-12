
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="group_claim_util_report")
public class GroupClaimUtilReport implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- group_claim_util_report.id --------- 
 schema        = null
 tableName     = group_claim_util_report
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
 type          = -5 
 isPrimaryKey  = true

=========================================


*/
	private Long id;
			
	/**data for the column :
	
 --------- group_claim_util_report.report_date --------- 
 schema        = null
 tableName     = group_claim_util_report
 foreignCol    = 
 foreignTab    = 
 catalog       = wahdebug
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
	private java.sql.Date reportDate;
			
	/**data for the column :
	
 --------- group_claim_util_report.group_name --------- 
 schema        = null
 tableName     = group_claim_util_report
 foreignCol    = 
 foreignTab    = 
 catalog       = wahdebug
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
	private String groupName;
			
	/**data for the column :
	
 --------- group_claim_util_report.group_periode --------- 
 schema        = null
 tableName     = group_claim_util_report
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
	private String groupPeriode;
			
	/**data for the column :
	
 --------- group_claim_util_report.claim_total --------- 
 schema        = null
 tableName     = group_claim_util_report
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
	private Integer claimTotal;
			
	/**data for the column :
	
 --------- group_claim_util_report.claim_total_nominal --------- 
 schema        = null
 tableName     = group_claim_util_report
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
	private Double claimTotalNominal;
			
	/**data for the column :
	
 --------- group_claim_util_report.total_member --------- 
 schema        = null
 tableName     = group_claim_util_report
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
	private Integer totalMember;
			
	/**data for the column :
	
 --------- group_claim_util_report.total_claim_ratio --------- 
 schema        = null
 tableName     = group_claim_util_report
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
	private Double totalClaimRatio;
			
	/**data for the column :
	
 --------- group_claim_util_report.total_inpatient --------- 
 schema        = null
 tableName     = group_claim_util_report
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
	private Integer totalInpatient;
			
	/**data for the column :
	
 --------- group_claim_util_report.total_nominal_inpatient --------- 
 schema        = null
 tableName     = group_claim_util_report
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
	private Double totalNominalInpatient;
			
	/**data for the column :
	
 --------- group_claim_util_report.total_outpatient --------- 
 schema        = null
 tableName     = group_claim_util_report
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
	private Integer totalOutpatient;
			
	/**data for the column :
	
 --------- group_claim_util_report.total_nominal_outpatient --------- 
 schema        = null
 tableName     = group_claim_util_report
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
	private Double totalNominalOutpatient;
			
	/**data for the column :
	
 --------- group_claim_util_report.total_dental --------- 
 schema        = null
 tableName     = group_claim_util_report
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
	private Integer totalDental;
			
	/**data for the column :
	
 --------- group_claim_util_report.total_nominal_dental --------- 
 schema        = null
 tableName     = group_claim_util_report
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
	private Double totalNominalDental;
			
	/**data for the column :
	
 --------- group_claim_util_report.total_maternity --------- 
 schema        = null
 tableName     = group_claim_util_report
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
	private Integer totalMaternity;
			
	/**data for the column :
	
 --------- group_claim_util_report.total_nominal_maternity --------- 
 schema        = null
 tableName     = group_claim_util_report
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
	private Double totalNominalMaternity;
			
	/**data for the column :
	
 --------- group_claim_util_report.total_optical --------- 
 schema        = null
 tableName     = group_claim_util_report
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
	private Integer totalOptical;
			
	/**data for the column :
	
 --------- group_claim_util_report.total_nominal_optical --------- 
 schema        = null
 tableName     = group_claim_util_report
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
	private Double totalNominalOptical;
			
	/**data for the column :
	
 --------- group_claim_util_report.total_mcu --------- 
 schema        = null
 tableName     = group_claim_util_report
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
	private Integer totalMcu;
			
	/**data for the column :
	
 --------- group_claim_util_report.total_nominal_mcu --------- 
 schema        = null
 tableName     = group_claim_util_report
 foreignCol    = 
 foreignTab    = 
 catalog       = wahdebug
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 20
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double totalNominalMcu;
			
	/**data for the column :
	
 --------- group_claim_util_report.inpatient_claim_ratio --------- 
 schema        = null
 tableName     = group_claim_util_report
 foreignCol    = 
 foreignTab    = 
 catalog       = wahdebug
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 21
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double inpatientClaimRatio;
			
	/**data for the column :
	
 --------- group_claim_util_report.outpatient_claim_ratio --------- 
 schema        = null
 tableName     = group_claim_util_report
 foreignCol    = 
 foreignTab    = 
 catalog       = wahdebug
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 22
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double outpatientClaimRatio;
			
	/**data for the column :
	
 --------- group_claim_util_report.dental_claim_ratio --------- 
 schema        = null
 tableName     = group_claim_util_report
 foreignCol    = 
 foreignTab    = 
 catalog       = wahdebug
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 23
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double dentalClaimRatio;
			
	/**data for the column :
	
 --------- group_claim_util_report.maternity_claim_ratio --------- 
 schema        = null
 tableName     = group_claim_util_report
 foreignCol    = 
 foreignTab    = 
 catalog       = wahdebug
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 24
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double maternityClaimRatio;
			
	/**data for the column :
	
 --------- group_claim_util_report.optical_claim_ratio --------- 
 schema        = null
 tableName     = group_claim_util_report
 foreignCol    = 
 foreignTab    = 
 catalog       = wahdebug
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 25
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double opticalClaimRatio;
			
	/**data for the column :
	
 --------- group_claim_util_report.mcu_claim_ratio --------- 
 schema        = null
 tableName     = group_claim_util_report
 foreignCol    = 
 foreignTab    = 
 catalog       = wahdebug
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
	private Double mcuClaimRatio;
			
	/**data for the column :
	
 --------- group_claim_util_report.group_id --------- 
 schema        = null
 tableName     = group_claim_util_report
 foreignCol    = 
 foreignTab    = 
 catalog       = wahdebug
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 27
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private MemberGroup groupId;
			
	/**data for the column :
	
 --------- group_claim_util_report.total_member_premium --------- 
 schema        = null
 tableName     = group_claim_util_report
 foreignCol    = 
 foreignTab    = 
 catalog       = wahdebug
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
	private Double totalMemberPremium;
	private Double outstandingExcess;
	private Double paidExcess;
	private Double claimChargeValue;
	private Double claimApprovedValue;
	private Double totalExcessCharge;
	
	private Client clientId;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Long getId(){
		return id;
	}
	public void setId(java.lang.Long value){
		id = value;
	}
			// PK GETTER SETTER END

						@Column(name="report_date")
	public java.sql.Date getReportDate(){
		return reportDate;
	}
	public void setReportDate(java.sql.Date value){
		reportDate = value;
	}
				@Column(name="group_name")
	public java.lang.String getGroupName(){
		return groupName;
	}
	public void setGroupName(java.lang.String value){
		groupName = value;
	}
				@Column(name="group_periode")
	public java.lang.String getGroupPeriode(){
		return groupPeriode;
	}
	public void setGroupPeriode(java.lang.String value){
		groupPeriode = value;
	}
				@Column(name="claim_total")
	public java.lang.Integer getClaimTotal(){
		return claimTotal;
	}
	public void setClaimTotal(java.lang.Integer value){
		claimTotal = value;
	}
				@Column(name="claim_total_nominal")
	public java.lang.Double getClaimTotalNominal(){
		return claimTotalNominal;
	}
	public void setClaimTotalNominal(java.lang.Double value){
		claimTotalNominal = value;
	}
				@Column(name="total_member")
	public java.lang.Integer getTotalMember(){
		return totalMember;
	}
	public void setTotalMember(java.lang.Integer value){
		totalMember = value;
	}
				@Column(name="total_claim_ratio")
	public java.lang.Double getTotalClaimRatio(){
		return totalClaimRatio;
	}
	public void setTotalClaimRatio(java.lang.Double value){
		totalClaimRatio = value;
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
				@Column(name="total_nominal_maternity")
	public java.lang.Double getTotalNominalMaternity(){
		return totalNominalMaternity;
	}
	public void setTotalNominalMaternity(java.lang.Double value){
		totalNominalMaternity = value;
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
				@Column(name="inpatient_claim_ratio")
	public java.lang.Double getInpatientClaimRatio(){
		return inpatientClaimRatio;
	}
	public void setInpatientClaimRatio(java.lang.Double value){
		inpatientClaimRatio = value;
	}
				@Column(name="outpatient_claim_ratio")
	public java.lang.Double getOutpatientClaimRatio(){
		return outpatientClaimRatio;
	}
	public void setOutpatientClaimRatio(java.lang.Double value){
		outpatientClaimRatio = value;
	}
				@Column(name="dental_claim_ratio")
	public java.lang.Double getDentalClaimRatio(){
		return dentalClaimRatio;
	}
	public void setDentalClaimRatio(java.lang.Double value){
		dentalClaimRatio = value;
	}
				@Column(name="maternity_claim_ratio")
	public java.lang.Double getMaternityClaimRatio(){
		return maternityClaimRatio;
	}
	public void setMaternityClaimRatio(java.lang.Double value){
		maternityClaimRatio = value;
	}
				@Column(name="optical_claim_ratio")
	public java.lang.Double getOpticalClaimRatio(){
		return opticalClaimRatio;
	}
	public void setOpticalClaimRatio(java.lang.Double value){
		opticalClaimRatio = value;
	}
				@Column(name="mcu_claim_ratio")
	public java.lang.Double getMcuClaimRatio(){
		return mcuClaimRatio;
	}
	public void setMcuClaimRatio(java.lang.Double value){
		mcuClaimRatio = value;
	}
	
	@ManyToOne
	@JoinColumn(name="group_id")
	public MemberGroup getGroupId(){
		return groupId;
	}
	public void setGroupId(MemberGroup value){
		groupId = value;
	}
				@Column(name="total_member_premium")
	public java.lang.Double getTotalMemberPremium(){
		return totalMemberPremium;
	}
	public void setTotalMemberPremium(java.lang.Double value){
		totalMemberPremium = value;
	}
	
	@ManyToOne
	@JoinColumn(name="client_id")
	public Client getClientId() {
		return clientId;
	}
	public void setClientId(Client clientId) {
		this.clientId = clientId;
	}
	@Column(name="outstanding_excess")
	public Double getOutstandingExcess() {
		return outstandingExcess;
	}
	public void setOutstandingExcess(Double outstandingExcess) {
		this.outstandingExcess = outstandingExcess;
	}
	@Column(name="paid_excess")
	public Double getPaidExcess() {
		return paidExcess;
	}
	public void setPaidExcess(Double paidExcess) {
		this.paidExcess = paidExcess;
	}
	@Column(name="claim_charge_value")
	public Double getClaimChargeValue() {
		return claimChargeValue;
	}
	public void setClaimChargeValue(Double claimChargeValue) {
		this.claimChargeValue = claimChargeValue;
	}
	@Column(name="claim_approved_value")
	public Double getClaimApprovedValue() {
		return claimApprovedValue;
	}
	public void setClaimApprovedValue(Double claimApprovedValue) {
		this.claimApprovedValue = claimApprovedValue;
	}
	@Column(name="total_excess_charge")
	public Double getTotalExcessCharge() {
		return totalExcessCharge;
	}
	public void setTotalExcessCharge(Double totalExcessCharge) {
		this.totalExcessCharge = totalExcessCharge;
	}
	
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
		
	//exported key end



}