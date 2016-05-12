package com.ametis.cms.web.form;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.CaseInvestigation;
import com.ametis.cms.datamodel.InvestigationCategory;
import com.ametis.cms.datamodel.Medicine;
import com.ametis.cms.datamodel.Procedure;
import com.ametis.cms.util.StringUtil;

// imports+ 

// imports- 

/**
 * CaseInvestigation is a mapping of case_investigation Table.
 */
public class CaseInvestigationForm implements java.io.Serializable
// extends+

// extends-

{

	private boolean isNewCaseInvestigation = false;

	private CaseInvestigation caseInvestigationBean;
	private String procedureName;
	private String medicineName;

	/*
	 * <form>Bean merupakan representasi dari "SINGLE" table dan misalkan ada
	 * form2 yang merupakan referensi dari tabel lain bikin aja field2 bean yang
	 * mengacu ke referensi itu biar nanti automatic loading
	 * 
	 */

	public CaseInvestigationForm() {
		this.caseInvestigationBean = new CaseInvestigation();
		this.isNewCaseInvestigation = true;
	}

	public CaseInvestigationForm(CaseInvestigation object) {
		this.caseInvestigationBean = object;
	}

	public boolean isNewCaseInvestigation() {

		return this.isNewCaseInvestigation;
	}

	
	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public CaseInvestigation getCaseInvestigation() {
		return this.caseInvestigationBean;
	}

	public void setCaseInvestigation(CaseInvestigation object) {
		this.caseInvestigationBean = object;
	}

	public void setCaseInvestigationId(String obj) {

		caseInvestigationBean.setCaseInvestigationId(StringUtil.getLongValue(
				obj, 0));

	}

	public String getCaseInvestigationId() {
		return StringUtil.getStringValue(caseInvestigationBean
				.getCaseInvestigationId());

	}

	public void setCase(Case obj) {

		caseInvestigationBean.setCaseId(obj);

	}

	public Case getCase() {
		return caseInvestigationBean.getCaseId();

	}

	public void setInvestigationCategoryId(InvestigationCategory obj) {

		caseInvestigationBean.setInvestigationCategoryId(obj);

	}

	public InvestigationCategory getInvestigationCategoryId() {
		return caseInvestigationBean.getInvestigationCategoryId();

	}

	public void setInvestigationDate(String obj) {

		caseInvestigationBean.setInvestigationDate(Date.valueOf(obj));

	}

	public String getInvestigationDate() {
		return StringUtil.getStringValue(caseInvestigationBean
				.getInvestigationDate());

	}

	public void setCreatedTime(String obj) {

		caseInvestigationBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime() {
		return StringUtil
				.getStringValue(caseInvestigationBean.getCreatedTime());

	}

	public void setCreatedBy(String obj) {

		caseInvestigationBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy() {
		return StringUtil.getStringValue(caseInvestigationBean.getCreatedBy());

	}

	public void setModifiedTime(String obj) {

		caseInvestigationBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime() {
		return StringUtil.getStringValue(caseInvestigationBean
				.getModifiedTime());

	}

	public void setModifiedBy(String obj) {

		caseInvestigationBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy() {
		return StringUtil.getStringValue(caseInvestigationBean.getModifiedBy());

	}

	public void setDeletedBy(String obj) {

		caseInvestigationBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy() {
		return StringUtil.getStringValue(caseInvestigationBean.getDeletedBy());

	}

	public void setDeletedTime(String obj) {

		caseInvestigationBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime() {
		return StringUtil
				.getStringValue(caseInvestigationBean.getDeletedTime());

	}

	public void setDeletedStatus(String obj) {

		caseInvestigationBean.setDeletedStatus(StringUtil.getIntegerValue(obj,
				0));

	}

	public String getDeletedStatus() {
		return StringUtil.getStringValue(caseInvestigationBean
				.getDeletedStatus());

	}

	public void setDescription(String obj) {

		caseInvestigationBean.setDescription(new String(obj));

	}

	public String getDescription() {
		return StringUtil
				.getStringValue(caseInvestigationBean.getDescription());

	}

	public void setInvestigationSubject(String obj) {

		caseInvestigationBean.setInvestigationSubject(new String(obj));

	}

	public String getInvestigationSubject() {
		return StringUtil.getStringValue(caseInvestigationBean
				.getInvestigationSubject());

	}

	public void setHeadDoctor(String obj) {

		caseInvestigationBean.setHeadDoctor(new String(obj));

	}

	public String getHeadDoctor() {
		return StringUtil.getStringValue(caseInvestigationBean.getHeadDoctor());

	}

	public void setConsultDoctor(String obj) {

		caseInvestigationBean.setConsultDoctor(new String(obj));

	}

	public String getConsultDoctor() {
		return StringUtil.getStringValue(caseInvestigationBean
				.getConsultDoctor());

	}

	public void setNurse(String obj) {

		caseInvestigationBean.setNurse(new String(obj));

	}

	public String getNurse() {
		return StringUtil.getStringValue(caseInvestigationBean.getNurse());

	}

	public void setDecision(Integer obj) {

		caseInvestigationBean.setDecision(obj);

	}

	public Integer getDecision() {
		return caseInvestigationBean.getDecision();

	}

	public void setTotalDays(Integer obj) {

		caseInvestigationBean.setTotalDays(obj);

	}

	public Integer getTotalDays() {
		return caseInvestigationBean.getDecision();

	}

	public void setConciousness(String obj) {

		caseInvestigationBean.setConciousness(new String(obj));

	}

	public String getConciousness() {
		return StringUtil.getStringValue(caseInvestigationBean
				.getConciousness());

	}

	public void setBloodPressure(String obj) {

		caseInvestigationBean.setBloodPressure(new String(obj));

	}

	public String getBloodPressure() {
		return StringUtil.getStringValue(caseInvestigationBean
				.getBloodPressure());

	}

	public void setArtery(String obj) {

		caseInvestigationBean.setArtery(new String(obj));

	}

	public String getArtery() {
		return StringUtil.getStringValue(caseInvestigationBean.getArtery());

	}

	public void setTemperature(String obj) {

		caseInvestigationBean.setTemperature(new String(obj));

	}

	public String getTemperature() {
		return StringUtil
				.getStringValue(caseInvestigationBean.getTemperature());

	}

	public void setRespiratory(String obj) {

		caseInvestigationBean.setRespiratory(new String(obj));

	}

	public String getRespiratory() {
		return StringUtil
				.getStringValue(caseInvestigationBean.getRespiratory());

	}

	public void setSatOxygen(String obj) {

		caseInvestigationBean.setSatOxygen(new String(obj));

	}

	public String getSatOxygen() {
		return StringUtil.getStringValue(caseInvestigationBean.getSatOxygen());

	}

	public void setVentilatorStatus(String obj) {

		caseInvestigationBean.setVentilatorStatus(new String(obj));

	}

	public String getVentilatorStatus() {
		return StringUtil.getStringValue(caseInvestigationBean
				.getVentilatorStatus());

	}

	public void setGcsE(String obj) {

		caseInvestigationBean.setGcsE(new String(obj));

	}

	public String getGcsE() {
		return StringUtil.getStringValue(caseInvestigationBean.getGcsE());

	}

	public void setGcsM(String obj) {

		caseInvestigationBean.setGcsM(new String(obj));

	}

	public String getGcsM() {
		return StringUtil.getStringValue(caseInvestigationBean.getGcsM());

	}

	public void setGcvV(String obj) {

		caseInvestigationBean.setGcvV(new String(obj));

	}

	public String getGcvV() {
		return StringUtil.getStringValue(caseInvestigationBean.getGcvV());

	}

	public void setPulse(String obj) {

		caseInvestigationBean.setPulse(new String(obj));

	}

	public String getPulse() {
		return StringUtil.getStringValue(caseInvestigationBean.getPulse());

	}

	public void setIppvStatus(String obj) {

		caseInvestigationBean.setIppvStatus(new String(obj));

	}

	public String getIppvStatus() {
		return StringUtil.getStringValue(caseInvestigationBean.getIppvStatus());

	}

	public void setSippvStatus(String obj) {

		caseInvestigationBean.setSippvStatus(new String(obj));

	}

	public String getSippvStatus() {
		return StringUtil
				.getStringValue(caseInvestigationBean.getSippvStatus());

	}

	public void setTracheostomy(String obj) {

		caseInvestigationBean.setTracheostomy(new String(obj));

	}

	public String getTracheostomy() {
		return StringUtil.getStringValue(caseInvestigationBean
				.getTracheostomy());

	}

	public void setPeep(String obj) {

		caseInvestigationBean.setPeep(new String(obj));

	}

	public String getPeep() {
		return StringUtil.getStringValue(caseInvestigationBean.getPeep());

	}

	public void setPh(String obj) {

		caseInvestigationBean.setPh(new String(obj));

	}

	public String getPh() {
		return StringUtil.getStringValue(caseInvestigationBean.getPh());

	}

	public void setPo2(String obj) {

		caseInvestigationBean.setPo2(new String(obj));

	}

	public String getPo2() {
		return StringUtil.getStringValue(caseInvestigationBean.getPo2());

	}

	public void setPercentagePcO2(String obj) {

		caseInvestigationBean.setPercentagePcO2(new String(obj));

	}

	public String getPercentagePcO2() {
		return StringUtil.getStringValue(caseInvestigationBean
				.getPercentagePcO2());

	}

	public void setPercentageHcO3(String obj) {

		caseInvestigationBean.setPercentageHcO3(new String(obj));

	}

	public String getPercentageHcO3() {
		return StringUtil.getStringValue(caseInvestigationBean
				.getPercentageHcO3());

	}

	public void setIvLine1(String obj) {

		caseInvestigationBean.setIvLine1(new String(obj));

	}

	public String getIvLine1() {
		return StringUtil.getStringValue(caseInvestigationBean.getIvLine1());

	}

	public void setIvLine2(String obj) {

		caseInvestigationBean.setIvLine2(new String(obj));

	}

	public String getIvLine2() {
		return StringUtil.getStringValue(caseInvestigationBean.getIvLine2());

	}

	public void setProcedureId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Procedure fk = new Procedure();
			fk.setProcedureId(StringUtil.getIntegerValue(obj,0));
			caseInvestigationBean.setProcedureId(fk);
		}

	}

	public String getProcedureId(){
		String result = "";
		
		if (caseInvestigationBean.getProcedureId() != null) {
			result = StringUtil.getStringValue(
					caseInvestigationBean.getProcedureId().getProcedureId());
		}
		return result;

	}


	public String getProcedureName() {
		return procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}
	
	public String getMedicineId() {
		String result = "";
		
		if (caseInvestigationBean.getMedicineId() != null){
			result = caseInvestigationBean.getMedicineId().getMedicineId().toString();
		}
		return result;
	}
	public void setMedicineId(String medicineId) {
		
		if (medicineId != null && !medicineId.equalsIgnoreCase("")){
			Medicine med = new Medicine();
			med.setMedicineId(Integer.valueOf(medicineId));
			caseInvestigationBean.setMedicineId(med);
		}
	}
	
	public String getProcedureCostEstimation() {
		String result = "";
		if (caseInvestigationBean.getProcedureCostEstimation() != null){
			result = caseInvestigationBean.getProcedureCostEstimation().toString();
		}
		return result;
	}
	public void setProcedureCostEstimation(String procedureCostEstimation) {
		if (procedureCostEstimation != null && !procedureCostEstimation.equalsIgnoreCase("")){
			caseInvestigationBean.setProcedureCostEstimation(Double.valueOf(procedureCostEstimation));
		}
	}
	
	public String getProcedureCostReference() {
		String result = "";
		if (caseInvestigationBean.getProcedureCostReference() != null){
			result = caseInvestigationBean.getProcedureCostReference().toString();
		}
		return result;
	}
	public void setProcedureCostReference(String procedureCostReference) {
		if (procedureCostReference != null && !procedureCostReference.equalsIgnoreCase("")){
			caseInvestigationBean.setProcedureCostReference(Double.valueOf(procedureCostReference));
		}
	}
	
	public String getMedicineCostEstimation() {
		String result = "";
		if (caseInvestigationBean.getMedicineCostEstimation() != null){
			result = caseInvestigationBean.getMedicineCostEstimation().toString();
		}
		return result;
	}
	public void setMedicineCostEstimation(String medicineCostEstimation) {
		if (medicineCostEstimation != null && !medicineCostEstimation.equalsIgnoreCase("")){
			this.caseInvestigationBean.setMedicineCostEstimation(Double.valueOf(medicineCostEstimation));
		}
	}
	public void setMedicineCostReference(String medicineCostEstimation) {
		if (medicineCostEstimation != null && !medicineCostEstimation.equalsIgnoreCase("")){
			this.caseInvestigationBean.setMedicineCostReference(Double.valueOf(medicineCostEstimation));
		}
	}
	public String getMedicineCostReference() {
		String result = "";
		if (caseInvestigationBean.getMedicineCostReference() != null){
			result = caseInvestigationBean.getMedicineCostReference().toString();
		}
		return result;
	}
	
}
