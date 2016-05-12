package com.ametis.cms.datamodel;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class GroupClaimUtilizationReport {

	private Long id;
	private String groupName;
	private String groupPeriode;
	private Date reportDate;
	private Integer claimTotal;
	private Double claimNominalTotal;
	private Integer memberTotal;
	private Double totalMemberPremium;
	private Double totalClaimRatio;
	private Integer inpatientTotal;
	private Double inpatientNominalTotal;
	private Integer outpatientTotal;
	private Double outpatientNominalTotal;
	private Integer dentalTotal;
	private Double dentalNominalTotal;
	private Integer maternityTotal;
	private Double maternityNominalTotal;
	private Integer opticalTotal;
	private Double opticalNominalTotal;
	private Integer mcuTotal;
	private Double mcuNominalTotal;
	
	private Double inpatientClaimRatio;
	private Double outpatientClaimRatio;
	private Double dentalClaimRatio;
	private Double maternityClaimRatio;
	private Double opticalClaimRatio;
	private Double mcuClaimRatio;
	private MemberGroup groupId;
	
	
	
	
	public GroupClaimUtilizationReport() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Column(name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="group_name")
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	@Column(name="group_periode")
	public String getGroupPeriode() {
		return groupPeriode;
	}
	public void setGroupPeriode(String groupPeriode) {
		this.groupPeriode = groupPeriode;
	}
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	public Integer getClaimTotal() {
		return claimTotal;
	}
	public void setClaimTotal(Integer claimTotal) {
		this.claimTotal = claimTotal;
	}
	public Double getClaimNominalTotal() {
		return claimNominalTotal;
	}
	public void setClaimNominalTotal(Double claimNominalTotal) {
		this.claimNominalTotal = claimNominalTotal;
	}
	public Integer getMemberTotal() {
		return memberTotal;
	}
	public void setMemberTotal(Integer memberTotal) {
		this.memberTotal = memberTotal;
	}
	public Double getTotalMemberPremium() {
		return totalMemberPremium;
	}
	public void setTotalMemberPremium(Double totalMemberPremium) {
		this.totalMemberPremium = totalMemberPremium;
	}
	public Double getTotalClaimRatio() {
		return totalClaimRatio;
	}
	public void setTotalClaimRatio(Double totalClaimRatio) {
		this.totalClaimRatio = totalClaimRatio;
	}
	public Integer getInpatientTotal() {
		return inpatientTotal;
	}
	public void setInpatientTotal(Integer inpatientTotal) {
		this.inpatientTotal = inpatientTotal;
	}
	public Double getInpatientNominalTotal() {
		return inpatientNominalTotal;
	}
	public void setInpatientNominalTotal(Double inpatientNominalTotal) {
		this.inpatientNominalTotal = inpatientNominalTotal;
	}
	public Integer getOutpatientTotal() {
		return outpatientTotal;
	}
	public void setOutpatientTotal(Integer outpatientTotal) {
		this.outpatientTotal = outpatientTotal;
	}
	public Double getOutpatientNominalTotal() {
		return outpatientNominalTotal;
	}
	public void setOutpatientNominalTotal(Double outpatientNominalTotal) {
		this.outpatientNominalTotal = outpatientNominalTotal;
	}
	public Integer getDentalTotal() {
		return dentalTotal;
	}
	public void setDentalTotal(Integer dentalTotal) {
		this.dentalTotal = dentalTotal;
	}
	public Double getDentalNominalTotal() {
		return dentalNominalTotal;
	}
	public void setDentalNominalTotal(Double dentalNominalTotal) {
		this.dentalNominalTotal = dentalNominalTotal;
	}
	public Integer getMaternityTotal() {
		return maternityTotal;
	}
	public void setMaternityTotal(Integer maternityTotal) {
		this.maternityTotal = maternityTotal;
	}
	public Double getMaternityNominalTotal() {
		return maternityNominalTotal;
	}
	public void setMaternityNominalTotal(Double maternityNominalTotal) {
		this.maternityNominalTotal = maternityNominalTotal;
	}
	public Integer getOpticalTotal() {
		return opticalTotal;
	}
	public void setOpticalTotal(Integer opticalTotal) {
		this.opticalTotal = opticalTotal;
	}
	public Double getOpticalNominalTotal() {
		return opticalNominalTotal;
	}
	public void setOpticalNominalTotal(Double opticalNominalTotal) {
		this.opticalNominalTotal = opticalNominalTotal;
	}
	public Integer getMcuTotal() {
		return mcuTotal;
	}
	public void setMcuTotal(Integer mcuTotal) {
		this.mcuTotal = mcuTotal;
	}
	public Double getMcuNominalTotal() {
		return mcuNominalTotal;
	}
	public void setMcuNominalTotal(Double mcuNominalTotal) {
		this.mcuNominalTotal = mcuNominalTotal;
	}
	public Double getInpatientClaimRatio() {
		return inpatientClaimRatio;
	}
	public void setInpatientClaimRatio(Double inpatientClaimRatio) {
		this.inpatientClaimRatio = inpatientClaimRatio;
	}
	public Double getOutpatientClaimRatio() {
		return outpatientClaimRatio;
	}
	public void setOutpatientClaimRatio(Double outpatientClaimRatio) {
		this.outpatientClaimRatio = outpatientClaimRatio;
	}
	public Double getDentalClaimRatio() {
		return dentalClaimRatio;
	}
	public void setDentalClaimRatio(Double dentalClaimRatio) {
		this.dentalClaimRatio = dentalClaimRatio;
	}
	public Double getMaternityClaimRatio() {
		return maternityClaimRatio;
	}
	public void setMaternityClaimRatio(Double maternityClaimRatio) {
		this.maternityClaimRatio = maternityClaimRatio;
	}
	public Double getOpticalClaimRatio() {
		return opticalClaimRatio;
	}
	public void setOpticalClaimRatio(Double opticalClaimRatio) {
		this.opticalClaimRatio = opticalClaimRatio;
	}
	public Double getMcuClaimRatio() {
		return mcuClaimRatio;
	}
	public void setMcuClaimRatio(Double mcuClaimRatio) {
		this.mcuClaimRatio = mcuClaimRatio;
	}
	public MemberGroup getGroupId() {
		return groupId;
	}
	public void setGroupId(MemberGroup groupId) {
		this.groupId = groupId;
	}
	
	
	
	
}
