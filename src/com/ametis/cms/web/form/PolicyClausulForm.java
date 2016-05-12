
package com.ametis.cms.web.form;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * PolicyClausul is a mapping of policy_clausul Table.
*/
public class PolicyClausulForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewPolicyClausul = false;
	private PolicyClausul policyClausulBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/
	private String diagnosisCode;
	private String diagnosisName;
	private String itemName;
	private String procedureCode;
	private String procedureName;
	private String memberGroupName;
	private String policyNumber;
	private String clausulName;
	private String itemCategoryName;

    public PolicyClausulForm()
    {
    	this.policyClausulBean = new PolicyClausul();
    	this.isNewPolicyClausul = true;
    }
    public PolicyClausulForm (PolicyClausul object){
		this.policyClausulBean = object;
    }
    public boolean isNewPolicyClausul (){

    	return this.isNewPolicyClausul;
    }
	public PolicyClausul getPolicyClausul (){
		return this.policyClausulBean ;
	}
	public void setPolicyClausul (PolicyClausul object){
		this.policyClausulBean = object;
	}

			
	public void setId(String obj){

		policyClausulBean.setId(StringUtil.getIntegerValue(obj,0));

	}

	public String getId(){
		return StringUtil.getStringValue(
		policyClausulBean.getId());

	}
	
				
	public void setPolicyId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Policy policy = new Policy();
			policy.setPolicyId(Integer.valueOf(obj));
			policyClausulBean.setPolicyId(policy);
		}

	}

	public String getPolicyId(){
		String result = "";
		
		if (policyClausulBean.getPolicyId() != null){
			result = StringUtil.getStringValue(policyClausulBean.getPolicyId().getPolicyId());
		}
		return result;
		

	}
	
				
	public void setClausulType(String obj){

		policyClausulBean.setClausulType(StringUtil.getIntegerValue(obj,0));

	}

	public String getClausulType(){
		return StringUtil.getStringValue(
		policyClausulBean.getClausulType());

	}
	
					public void setClausulFileLocation(String obj){

		policyClausulBean.setClausulFileLocation(new String(obj));

	}

	public String getClausulFileLocation(){
		return StringUtil.getStringValue(
		policyClausulBean.getClausulFileLocation());

	}
	
				
	public void setCreatedTime(String obj){

		policyClausulBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		policyClausulBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		policyClausulBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		policyClausulBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		policyClausulBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		policyClausulBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		policyClausulBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		policyClausulBean.getModifiedBy());

	}
	
				
	public void setDeletedTime(String obj){

		policyClausulBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		policyClausulBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		policyClausulBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		policyClausulBean.getDeletedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		policyClausulBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		policyClausulBean.getDeletedStatus());

	}
	
				
	public void setStatus(String obj){

		policyClausulBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		policyClausulBean.getStatus());

	}
	
				
	public void setClausulId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Clausul clausul = new Clausul();
			clausul.setClausulId(Integer.valueOf(obj));
			policyClausulBean.setClausulId(clausul);
		}

	}

	public String getClausulId(){
		String result = "";
		
		if (policyClausulBean.getClausulId() != null){
			result = StringUtil.getStringValue(
					policyClausulBean.getClausulId().getClausulId());
		}
		return result;
		

	}
	
	public String getItemCategoryId() {
		String result = "";
		
		if (policyClausulBean.getItemCategoryId() != null){
			result = StringUtil.getStringValue(policyClausulBean.getItemCategoryId().getItemCategoryId());
		}
		
		return result;
	}
	public void setItemCategoryId(String itemCategoryId) {
		if (itemCategoryId!= null && !itemCategoryId.equalsIgnoreCase("")){
			ItemCategory itemCategory = new ItemCategory();
			itemCategory.setItemCategoryId(Integer.valueOf(itemCategoryId));
			
			policyClausulBean.setItemCategoryId(itemCategory);
		}
	
	}
	
	public String getCaseCategoryId() {
		String result = "";
		
		if (policyClausulBean.getCaseCategoryId() != null ){
			result = StringUtil.getStringValue(policyClausulBean.getCaseCategoryId().getCaseCategoryId());
		}
		
		return result;
	}
	public void setCaseCategoryId(String caseCategoryId) {
		if (caseCategoryId != null && !caseCategoryId.equalsIgnoreCase("")){
			CaseCategory cc = new CaseCategory();
			cc.setCaseCategoryId(Integer.valueOf(caseCategoryId));
			
			policyClausulBean.setCaseCategoryId(cc);
		}
	}
	
	public String getItemName() {
String result = "";
		
		if (policyClausulBean.getItemName() != null){
			result = StringUtil.getStringValue(policyClausulBean.getItemName());
		}
		
		return result;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getDiagnosisCode() {
		String result = "";
		
		if (policyClausulBean.getDiagnosisCode() != null){
			result = StringUtil.getStringValue(policyClausulBean.getDiagnosisCode());
		}
		
		return result;
	}
	public void setDiagnosisCode(String diagnosisCode) {
		this.diagnosisCode = diagnosisCode;
	}
	
//	public String getDiagnosisName() {
//		String result = "";
//		
//		if (policyClausulBean.getDiagnosisName() != null){
//			result = StringUtil.getStringValue(policyClausulBean.getDiagnosisName());
//		}
//		
//		return result;
//	}
	public String getDiagnosisName() {
		return diagnosisName;
	}
	public void setDiagnosisName(String diagnosisName) {
		this.diagnosisName = diagnosisName;
	}
	
	public String getDiagnosisId() {
		String result = "";
		
		if (policyClausulBean.getDiagnosisId() != null){
			result = StringUtil.getStringValue(policyClausulBean.getDiagnosisId().getDiagnosisId());
		}
		
		return result;
	}
	public void setDiagnosisId(String diagnosisId) {
		if (diagnosisId != null && !diagnosisId.equalsIgnoreCase("")){
			Diagnosis diag = new Diagnosis();
			diag.setDiagnosisId(Integer.valueOf(diagnosisId));
			
			policyClausulBean.setDiagnosisId(diag);
		}
	}
	
	public String getProcedureId() {
		String result = "";
		
		if (policyClausulBean.getProcedureId() != null){
			result = StringUtil.getStringValue(policyClausulBean.getProcedureId().getProcedureId());
		}
		
		return result;
	}
	public void setProcedureId(String procedureId) {
		if (procedureId != null && !procedureId.equalsIgnoreCase("")){
			Procedure proc = new Procedure();
			proc.setProcedureId(Integer.valueOf(procedureId));
			policyClausulBean.setProcedureId(proc);
		}
	}
	
	public String getDecision() {
String result = "";
		
		if (policyClausulBean.getDecision() != null){
			result = StringUtil.getStringValue(policyClausulBean.getDecision());
		}
		
		return result;
	}
	public void setDecision(String decision) {
		if (decision != null && !decision.equalsIgnoreCase("")){
			policyClausulBean.setDecision(Integer.valueOf(decision));
		}
	}
	
	public String getIsEdc() {
String result = "";
		
		if (policyClausulBean.getIsEdc() != null){
			result = StringUtil.getStringValue(policyClausulBean.getIsEdc());
		}
		
		return result;
	}
	public void setIsEdc(String isEdc) {
		if (isEdc != null && !isEdc.equalsIgnoreCase("")){
			policyClausulBean.setIsEdc(Integer.valueOf(isEdc));
		}
	}
	
	public String getProcedureName() {
		return procedureName;
	}
	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}
	
	public String getProcedureCode() {
		return procedureCode;
	}
	public void setProcedureCode(String procedureCode) {
		this.procedureCode = procedureCode;
	}
		
	public String getLocationId() {
		String result = "";
		
		if (policyClausulBean.getLocationId() != null){
			result = StringUtil.getStringValue(policyClausulBean.getLocationId().getLocationId());
		}
		
		return result;
	}
	public void setLocationId(String locationId) {
		if (locationId != null && !locationId.equalsIgnoreCase("")){
			TreatmentLocation location = new TreatmentLocation();
			location.setLocationId(Integer.valueOf(locationId));
			
			policyClausulBean.setLocationId(location);
		}
	}
	
	public String getPembebananBenefit() {
String result = "";
		
		if (policyClausulBean.getPembebananBenefit() != null){
			result = StringUtil.getStringValue(policyClausulBean.getPembebananBenefit());
		}
		
		return result;
	}
	public void setPembebananBenefit(String pembebananBenefit) {
		if(pembebananBenefit != null && !pembebananBenefit.equalsIgnoreCase("")){
		
			policyClausulBean.setPembebananBenefit(Integer.valueOf(pembebananBenefit));
		}
	}
	
	public String getBenefitAmount() {
		String result = "";
		
		if (policyClausulBean.getBenefitAmount() != null){
			result = StringUtil.getStringValue(policyClausulBean.getBenefitAmount());
		}
		
		return result;
	}
	public void setBenefitAmount(String benefitAmount) {
		if (benefitAmount != null && !benefitAmount.equalsIgnoreCase("")){
			policyClausulBean.setBenefitAmount(Double.valueOf(benefitAmount));
		}
	}
	
	public String getBenefitParameter() {
		String result = "";
		
		if (policyClausulBean.getBenefitParameter() != null){
			result = StringUtil.getStringValue(policyClausulBean.getBenefitParameter());
		}
		
		return result;
	}
	public void setBenefitParameter(String benefitParameter) {
		if (benefitParameter != null && !benefitParameter.equalsIgnoreCase("")){
			policyClausulBean.setBenefitParameter(Integer.valueOf(benefitParameter));
		}
	}
	
	public String getBenefitIteration() {
		String result = "";
		
		if (policyClausulBean.getBenefitIteration() != null){
			result = StringUtil.getStringValue(policyClausulBean.getBenefitIteration());
		}
		
		return result;
	}
	public void setBenefitIteration(String iteration) {
		if (iteration != null && !iteration.equalsIgnoreCase("")){
			policyClausulBean.setBenefitIteration(Integer.valueOf(iteration));
		}
	}	
	public String getIterationType() {
		String result = "";
		
		if (policyClausulBean.getIterationType() != null){
			result = StringUtil.getStringValue(policyClausulBean.getBenefitParameter());
		}
		
		return result;
	}
	public void setIterationType(String iterationParameter) {
		if (iterationParameter != null && !iterationParameter.equalsIgnoreCase("")){
			policyClausulBean.setIterationType(Integer.valueOf(iterationParameter));
		}
	}
	
	public String getAge() {
		String result = "";
		
		if (policyClausulBean.getAge() != null){
			result = StringUtil.getStringValue(policyClausulBean.getAge());
		}
		
		return result;
	}
	public void setAge(String age) {
		if (age != null && !age.equalsIgnoreCase("")){
			policyClausulBean.setAge(Double.valueOf(age));
		}
	}
	
	public String getAgeParameter() {
		String result = "";
		
		if (policyClausulBean.getAgeParameter() != null){
			result = StringUtil.getStringValue(policyClausulBean.getAgeParameter());
		}
		
		return result;
	}
	public void setAgeParameter(String ageParameter) {
		if (ageParameter != null && !ageParameter.equalsIgnoreCase("")){
			policyClausulBean.setAgeParameter(Integer.valueOf(ageParameter));
		}
	}
	
	public String getBenefitReductionPercentage() {
		String result = "";
		
		if (policyClausulBean.getBenefitReductionPercentage() != null){
			result = StringUtil.getStringValue(policyClausulBean.getBenefitReductionPercentage());
		}
		
		return result;
	}
	public void setBenefitReductionPercentage(String benefitReductionPercentage) {
		if (benefitReductionPercentage != null && !benefitReductionPercentage.equalsIgnoreCase("")){
			policyClausulBean.setBenefitReductionPercentage(Double.valueOf(benefitReductionPercentage));
		}
	}
	
	public String getBenefitReductionSubject() {
		String result = "";
		
		if (policyClausulBean.getBenefitReductionSubject() != null){
			result = StringUtil.getStringValue(policyClausulBean.getBenefitReductionSubject());
		}
		
		return result;
	}
	public void setBenefitReductionSubject(String benefitReductionSubject) {
		if (benefitReductionSubject != null && !benefitReductionSubject.equalsIgnoreCase("")){
			policyClausulBean.setBenefitReductionSubject(Integer.valueOf(benefitReductionSubject));
		}
	}
	
	public String getBenefitReductionType() {
		String result = "";
		
		if (policyClausulBean.getBenefitReductionType() != null){
			result = StringUtil.getStringValue(policyClausulBean.getBenefitReductionType());
		}
		
		return result;
	}
	public void setBenefitReductionType(String benefitReductionType) {
		if (benefitReductionType != null && !benefitReductionType.equalsIgnoreCase("")){
			policyClausulBean.setBenefitReductionType(Integer.valueOf(benefitReductionType));
		}
	}
	
	public String getMultiplierCalculationType() {
		String result = "";
		
		if (policyClausulBean.getMultiplierCalculationType() != null){
			result = StringUtil.getStringValue(policyClausulBean.getMultiplierCalculationType());
		}
		
		return result;
	}
	public void setMultiplierCalculationType(String multiplierCalculationType) {
		if (multiplierCalculationType != null && !multiplierCalculationType.equalsIgnoreCase("")){
			policyClausulBean.setMultiplierCalculationType(Integer.valueOf(multiplierCalculationType));
		}
	}
	
	public String getTreatmentUpgradeType() {
		String result = "";
		
		if (policyClausulBean.getTreatmentUpgradeType() != null){
			result = StringUtil.getStringValue(policyClausulBean.getTreatmentUpgradeType().getTreatmentUpgradeTypeId());
		}
		
		return result;
	}
	public void setTreatmentUpgradeType(String treatmentUpgradeType) {
		if (treatmentUpgradeType != null && !treatmentUpgradeType.equalsIgnoreCase("")){
			TreatmentUpgradeType treatment = new TreatmentUpgradeType();
			treatment.setTreatmentUpgradeTypeId(Integer.valueOf(treatmentUpgradeType));
			policyClausulBean.setTreatmentUpgradeType(treatment);
		}
	}	
	public void setDescription(String obj){
		policyClausulBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(policyClausulBean.getDescription());

	}
//	public String getDescription() {
//		String result = "";
//		
//		if (policyClausulBean.getDescription() != null){
//			result = StringUtil.getStringValue(policyClausulBean.getDescription());
//		}
//		
//		return result;
//	}
//	public void setDescription(String desc) {
//		if (desc != null && !desc.equalsIgnoreCase("")){
//			policyClausulBean.setDescription(desc);
//		}
//	}
	public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	public String getMemberGroupName() {
		return memberGroupName;
	}
	public void setMemberGroupName(String memberGroupName) {
		this.memberGroupName = memberGroupName;
	}
	public String getClausulName() {
		return clausulName;
	}
	public void setClausulName(String clausulName) {
		this.clausulName = clausulName;
	}
	public String getItemCategoryName() {
		return itemCategoryName;
	}
	public void setItemCategoryName(String itemCategoryName) {
		this.itemCategoryName = itemCategoryName;
	}
	public String getClaimTreatmentTypeId() {
		String result = "";
		if (policyClausulBean.getClaimTreatmentTypeId() != null){
			result = StringUtil.getStringValue(policyClausulBean.getClaimTreatmentTypeId().getClaimTypeId());
		}
		return result;
	}
	public void setClaimTreatmentTypeId(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			ClaimType claimType = new ClaimType();
			claimType.setClaimTypeId(Integer.valueOf(obj));
			
			policyClausulBean.setClaimTreatmentTypeId(claimType);
		}
	}
	
	
}
