package com.ametis.cms.web.form;

import java.sql.Timestamp;

import javax.persistence.Column;

import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.CaseEvent;
import com.ametis.cms.datamodel.EventCategory;
import com.ametis.cms.util.StringUtil;

// imports+ 

// imports- 

/**
 * CaseEvent is a mapping of case_event Table.
 */
public class CaseEventForm implements java.io.Serializable
// extends+

// extends-
{

	private boolean isNewCaseEvent = false;

	private java.util.Date date;
	private String hour;
	private String minute;
	private CaseEvent caseEventBean;

	/*
	 * <form>Bean merupakan representasi dari "SINGLE" table dan misalkan ada
	 * form2 yang merupakan referensi dari tabel lain bikin aja field2 bean yang
	 * mengacu ke referensi itu biar nanti automatic loading
	 * 
	 */

	public CaseEventForm() {
		this.caseEventBean = new CaseEvent();
		this.isNewCaseEvent = true;
	}

	public CaseEventForm(CaseEvent object) {
		this.caseEventBean = object;
	}

	public boolean isNewCaseEvent() {

		return this.isNewCaseEvent;
	}

	public CaseEvent getCaseEvent() {
		return this.caseEventBean;
	}

	public void setCaseEvent(CaseEvent object) {
		this.caseEventBean = object;
	}

	public void setCaseEventId(String obj) {

		caseEventBean.setCaseEventId(StringUtil.getIntegerValue(obj, 0));

	}

	public String getCaseEventId() {
		return StringUtil.getStringValue(caseEventBean.getCaseEventId());

	}

	public void setDescription(String obj) {

		caseEventBean.setDescription(new String(obj));

	}

	public String getDescription() {
		return StringUtil.getStringValue(caseEventBean.getDescription());

	}

	public void setEventCategoryId(EventCategory obj) {

		caseEventBean.setEventCategoryId(obj);
		// caseEventBean.setEventCategoryId(StringUtil.getIntegerValue(obj,0));

	}

	public EventCategory getEventCategoryId() {
		return caseEventBean.getEventCategoryId();

	}

	public void setTherapy(String obj) {

		caseEventBean.setTherapy(new String(obj));

	}

	public String getTherapy() {
		return StringUtil.getStringValue(caseEventBean.getTherapy());

	}

	public void setVitalSign(String obj) {

		caseEventBean.setVitalSign(new String(obj));

	}

	public String getVitalSign() {
		return StringUtil.getStringValue(caseEventBean.getVitalSign());

	}

	public void setRemarks(String obj) {

		caseEventBean.setRemarks(new String(obj));

	}

	public String getRemarks() {
		return StringUtil.getStringValue(caseEventBean.getRemarks());

	}

	public void setCreatedTime(String obj) {

		caseEventBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime() {
		return StringUtil.getStringValue(caseEventBean.getCreatedTime());

	}

	public void setCreatedBy(String obj) {

		caseEventBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy() {
		return StringUtil.getStringValue(caseEventBean.getCreatedBy());

	}

	public void setDeletedTime(String obj) {

		caseEventBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime() {
		return StringUtil.getStringValue(caseEventBean.getDeletedTime());

	}

	public void setDeletedBy(String obj) {

		caseEventBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy() {
		return StringUtil.getStringValue(caseEventBean.getDeletedBy());

	}

	public void setModifiedTime(String obj) {

		caseEventBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime() {
		return StringUtil.getStringValue(caseEventBean.getModifiedTime());

	}

	public void setModifiedBy(String obj) {

		caseEventBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy() {
		return StringUtil.getStringValue(caseEventBean.getModifiedBy());

	}

	public void setDeletedStatus(String obj) {

		caseEventBean.setDeletedStatus(StringUtil.getIntegerValue(obj, 0));

	}

	public String getDeletedStatus() {
		return StringUtil.getStringValue(caseEventBean.getDeletedStatus());

	}

	public void setDoctorConsult(String obj) {

		caseEventBean.setDoctorConsult(new String(obj));

	}

	public String getDoctorConsult() {
		return StringUtil.getStringValue(caseEventBean.getDoctorConsult());

	}

	public void setNurse(String obj) {

		caseEventBean.setNurse(new String(obj));

	}

	public String getNurse() {
		return StringUtil.getStringValue(caseEventBean.getNurse());

	}

	public void setMonitoredBy(String obj) {

		caseEventBean.setMonitoredBy(new String(obj));

	}

	public String getMonitoredBy() {
		return StringUtil.getStringValue(caseEventBean.getMonitoredBy());

	}

	// foreign affairs

	public void setCase(Case obj) {

		caseEventBean.setCaseId(obj);

	}

	public Case getCase() {
		return caseEventBean.getCaseId();

	}

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public void setMonitoringTime(java.sql.Timestamp time) {
		this.caseEventBean.setMonitoringTime(time);
	}

	public Timestamp getMonitoringTime() {
		return this.caseEventBean.getMonitoringTime();
	}

	public String getCostEstimation() {
		String result = "";

		if (caseEventBean.getCostEstimation() != null) {
			result = caseEventBean.getCostEstimation().toString();
		}
		return result;
	}

	public void setCostEstimation(String costEstimation) {

		if (costEstimation != null && !costEstimation.equals("")) {
			this.caseEventBean
					.setCostEstimation(Double.valueOf(costEstimation));
		}
	}

	public String getProviderContact() {
		return caseEventBean.getProviderContact();
	}

	public void setProviderContact(String providerContact) {
		this.caseEventBean.setProviderContact(providerContact);
	}

	public String getDiagnosisAspect() {
		return caseEventBean.getDiagnosisAspect();
	}

	public void setDiagnosisAspect(String diagnosisAspect) {
		this.caseEventBean.setDiagnosisAspect(diagnosisAspect);
	}

	public String getProcedurePlan() {
		return caseEventBean.getProcedurePlan();
	}

	public void setProcedurePlan(String procedurePlan) {
		this.caseEventBean.setProcedurePlan(procedurePlan);
	}

	public void setEkg(String obj) {

		caseEventBean.setEkg(new String(obj));

	}

	public String getEkg() {
		return StringUtil.getStringValue(caseEventBean.getEkg());

	}

	public void setImpression(String obj) {

		caseEventBean.setImpression(new String(obj));

	}

	public String getImpression() {
		return StringUtil.getStringValue(caseEventBean.getImpression());

	}

	public void setSubjective(String obj) {

		caseEventBean.setSubjective(new String(obj));

	}

	public String getSubjective() {
		return StringUtil.getStringValue(caseEventBean.getSubjective());

	}

	public void setObjective(String obj) {

		caseEventBean.setObjective(new String(obj));

	}

	public String getObjective() {
		return StringUtil.getStringValue(caseEventBean.getObjective());

	}

	public void setAssesment(String obj) {

		caseEventBean.setAssesment(new String(obj));

	}

	public String getAssesment() {
		return StringUtil.getStringValue(caseEventBean.getAssesment());

	}

	public void setPlan(String obj) {

		caseEventBean.setPlan(new String(obj));

	}

	public String getPlan() {
		return StringUtil.getStringValue(caseEventBean.getPlan());

	}

	public void setChiefComplaint(String obj) {

		caseEventBean.setChiefComplaint(new String(obj));

	}

	public String getChiefComplaint() {
		return StringUtil.getStringValue(caseEventBean.getChiefComplaint());

	}

	public void setCurrentMedication(String obj) {

		caseEventBean.setCurrentMedication(new String(obj));

	}

	public String getCurrentMedication() {
		return StringUtil.getStringValue(caseEventBean.getCurrentMedication());

	}

	public void setAllergies(String obj) {

		caseEventBean.setAllergies(new String(obj));

	}

	public String getAllergies() {
		return StringUtil.getStringValue(caseEventBean.getAllergies());

	}

	public void setPhysicalExamination(String obj) {

		caseEventBean.setPhysicalExamination(new String(obj));

	}

	public String getPhysicalExamination() {
		return StringUtil
				.getStringValue(caseEventBean.getPhysicalExamination());

	}

	public void setDiagnosticTesting(String obj) {

		caseEventBean.setDiagnosticTesting(new String(obj));

	}

	public String getDiagnosticTesting() {
		return StringUtil.getStringValue(caseEventBean.getDiagnosticTesting());

	}

	public void setFamilyHistory(String obj) {

		caseEventBean.setFamilyHistory(new String(obj));

	}

	public String getFamilyHistory() {
		return StringUtil.getStringValue(caseEventBean.getFamilyHistory());

	}

	public void setSocialHistory(String obj) {

		caseEventBean.setSocialHistory(new String(obj));

	}

	public String getSocialHistory() {
		return StringUtil.getStringValue(caseEventBean.getSocialHistory());

	}

	public void setPastMedicalHistory(String obj) {

		caseEventBean.setPastMedicalHistory(new String(obj));

	}

	public String getPastMedicalHistory() {
		return StringUtil.getStringValue(caseEventBean.getPastMedicalHistory());

	}

	public void setPastSurgicalHistory(String obj) {

		caseEventBean.setPastSurgicalHistory(new String(obj));

	}

	public String getPastSurgicalHistory() {
		return StringUtil
				.getStringValue(caseEventBean.getPastSurgicalHistory());

	}
	public void setInitialDiagnosis(String obj) {

		if (obj != null && !obj.equalsIgnoreCase("")){
			caseEventBean.setInitialDiagnosis(new String(obj));
		}
		else {
			caseEventBean.setInitialDiagnosis(caseEventBean.getCaseId().getInitialDiagnosis());
		}

	}

	public String getInitialDiagnosis() {
		String result = "";
		
		if (caseEventBean.getInitialDiagnosis() != null){
			result = caseEventBean.getInitialDiagnosis();
		}
		else {
			result = caseEventBean.getCaseId().getInitialDiagnosis();
		}
		return result;
		

	}

}
