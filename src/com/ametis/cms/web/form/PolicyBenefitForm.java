
package com.ametis.cms.web.form;

import java.math.BigDecimal;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 


/**
 * PolicyBenefit is a mapping of policy_benefit Table.
*/
public class PolicyBenefitForm implements java.io.Serializable
// extends+ 

// extends- 

{


	private boolean isNewPolicyBenefit = false;
	private PolicyBenefit policyBenefitBean ;
	private String groupName;
	private String policyNumber;
	private String itemCategoryName;
	private String diagnosisName;
	private String diagnosisSetName;
	private String procedureName;
	

    public PolicyBenefitForm()
    {
    	this.policyBenefitBean = new PolicyBenefit();
    	this.isNewPolicyBenefit = true;
    }
    public PolicyBenefitForm (PolicyBenefit object){
		this.policyBenefitBean = object;
    }
    public boolean isNewPolicyBenefit (){

    	return this.isNewPolicyBenefit;
    }
	public PolicyBenefit getPolicyBenefit (){
		return this.policyBenefitBean ;
	}
	public void setPolicyBenefit (PolicyBenefit object){
		this.policyBenefitBean = object;
	}

			
	public void setPolicyBenefitId(String obj){

		policyBenefitBean.setPolicyBenefitId(StringUtil.getIntegerValue(obj,0));

	}

	public String getPolicyBenefitId(){
		return StringUtil.getStringValue(
		policyBenefitBean.getPolicyBenefitId());

	}
	
				
	public void setPolicyId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Policy policy = new Policy();
			policy.setPolicyId(Integer.valueOf(obj));
			policyBenefitBean.setPolicyId(policy);
		}
		

	}

	public String getPolicyId(){
		return StringUtil.getStringValue(
		policyBenefitBean.getPolicyId().getPolicyId());

	}
	
				
	public void setItemCategoryId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			ItemCategory ic = new ItemCategory();
			ic.setItemCategoryId(Integer.valueOf(obj));
			policyBenefitBean.setItemCategoryId(ic);
			
		}

	}

	public String getItemCategoryId(){
		String result = "";
		
		if (policyBenefitBean.getItemCategoryId() != null){
			result = policyBenefitBean.getItemCategoryId().getItemCategoryId().toString();
		}
		return result;

	}
	
				
	public void setCaseCategoryId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			CaseCategory ic = new CaseCategory();
			ic.setCaseCategoryId(Integer.valueOf(obj));
			policyBenefitBean.setCaseCategoryId(ic);
			
		}

	}

	public String getCaseCategoryId(){
		String result = "";
		
		if (policyBenefitBean.getCaseCategoryId() != null){
			result =StringUtil.getStringValue(
					policyBenefitBean.getCaseCategoryId().getCaseCategoryId()); 
		}
		return result;
		

	}

	
				
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	public void setBenefitUsage(String obj){

		policyBenefitBean.setBenefitUsage(StringUtil.getDoubleValue(obj,0));

	}

	public String getBenefitUsage(){
		return StringUtil.getStringValue(
		policyBenefitBean.getBenefitUsage());

	}
	
				
	public void setCurrentBenefitPosition(String obj){
		policyBenefitBean.setCurrentBenefitPosition(StringUtil.getDoubleValue(obj,0));

	}

	public String getCurrentBenefitPosition(){
		return StringUtil.getStringValue(
		policyBenefitBean.getCurrentBenefitPosition());

	}
	
				
	public void setBenefitLimit(String obj){

		policyBenefitBean.setBenefitLimit(StringUtil.getDoubleValue(obj,0));

	}

	public String getBenefitLimit(){
		String result = "";
		
		if (policyBenefitBean.getBenefitLimit() != null){
			BigDecimal bc = new BigDecimal(policyBenefitBean.getBenefitLimit());
			result = bc.toPlainString();
		}
		return result;
		

	}
	
				
	public void setAnnualBenefit(String obj){

		policyBenefitBean.setAnnualBenefit(StringUtil.getDoubleValue(obj,0));

	}

	public String getAnnualBenefit(){
String result = "";
		
		if (policyBenefitBean.getAnnualBenefit() != null){
			BigDecimal bc = new BigDecimal(policyBenefitBean.getAnnualBenefit());
			result = bc.toPlainString();
		}
		return result;

	}
	
				
	public void setOccuranceUsage(String obj){

		policyBenefitBean.setOccuranceUsage(StringUtil.getIntegerValue(obj,0));

	}

	public String getOccuranceUsage(){
		return StringUtil.getStringValue(
		policyBenefitBean.getOccuranceUsage());

	}
	
				
	public void setMaxOccurancePerMember(String obj){

		policyBenefitBean.setMaxOccurancePerMember(StringUtil.getDoubleValue(obj,0));

	}

	public String getMaxOccurancePerMember(){
		return StringUtil.getStringValue(
		policyBenefitBean.getMaxOccurancePerMember());

	}
	
				
	public void setMaxUsagePerMember(String obj){

		policyBenefitBean.setMaxUsagePerMember(StringUtil.getDoubleValue(obj,0));

	}

	public String getMaxUsagePerMember(){
		return StringUtil.getStringValue(
		policyBenefitBean.getMaxUsagePerMember());

	}
	
				
	public void setProviderContract(String obj){

		policyBenefitBean.setProviderContract(StringUtil.getIntegerValue(obj,0));

	}

	public String getProviderContract(){
		return StringUtil.getStringValue(
		policyBenefitBean.getProviderContract());

	}
	
				
	public void setIsCostSharing(String obj){

		policyBenefitBean.setIsCostSharing(StringUtil.getIntegerValue(obj,0));

	}

	public String getIsCostSharing(){
		return StringUtil.getStringValue(
		policyBenefitBean.getIsCostSharing());

	}
	
				
	public void setCashlessPercentage(String obj){

		policyBenefitBean.setCashlessPercentage(StringUtil.getDoubleValue(obj,0));

	}

	public String getCashlessPercentage(){
		return StringUtil.getStringValue(
		policyBenefitBean.getCashlessPercentage());

	}
	
				
	public void setReimbursementPercentage(String obj){

		policyBenefitBean.setReimbursementPercentage(StringUtil.getDoubleValue(obj,0));

	}

	public String getReimbursementPercentage(){
		return StringUtil.getStringValue(
		policyBenefitBean.getReimbursementPercentage());

	}
	
				
	public void setTreatmentLocation(String obj){

		policyBenefitBean.setTreatmentLocation(StringUtil.getIntegerValue(obj,0));

	}

	public String getTreatmentLocation(){
		return StringUtil.getStringValue(
		policyBenefitBean.getTreatmentLocation());

	}
	
					public void setDescription(String obj){

		policyBenefitBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		policyBenefitBean.getDescription());

	}
	
				
	public void setStatus(String obj){

		policyBenefitBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		policyBenefitBean.getStatus());

	}
	public String getItemCategoryName() {
		return itemCategoryName;
	}
	public void setItemCategoryName(String itemCategoryName) {
		this.itemCategoryName = itemCategoryName;
	}
	public String getDiagnosisName() {
		return diagnosisName;
	}
	public void setDiagnosisName(String diagnosisName) {
		this.diagnosisName = diagnosisName;
	}
	public String getDiagnosisSetName() {
		return diagnosisSetName;
	}
	public void setDiagnosisSetName(String diagnosisSetName) {
		this.diagnosisSetName = diagnosisSetName;
	}
	public String getProcedureName() {
		return procedureName;
	}
	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}
	public String getDiagnosisId() {
		String result  = "";
		
		if (policyBenefitBean.getDiagnosisId() != null){
			result = policyBenefitBean.getDiagnosisId().getDiagnosisId().toString();
		}
		return result;
	}
	public void setDiagnosisId(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			Diagnosis diagnosis = new Diagnosis();
			diagnosis.setDiagnosisId(Integer.valueOf(obj));
			this.policyBenefitBean.setDiagnosisId(diagnosis);
		}
	}
	public String getDiagnosisSetId() {
		String result  = "";
		
		if (policyBenefitBean.getDiagnosisSetId() != null){
			result = policyBenefitBean.getDiagnosisSetId().getDiagnosisSetId().toString();
		}
		return result;
	}
	public void setDiagnosisSetId(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			DiagnosisSet diagnosis = new DiagnosisSet();
			diagnosis.setDiagnosisSetId(Integer.valueOf(obj));
			this.policyBenefitBean.setDiagnosisSetId(diagnosis);
		}
	}
	public String getProcedureId() {
		String result  = "";
		
		if (policyBenefitBean.getProcedureId() != null){
			result = policyBenefitBean.getProcedureId().getProcedureId().toString();
		}
		return result;
	}
	public void setProcedureId(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			Procedure proc = new Procedure();
			proc.setProcedureId(Integer.valueOf(obj));
			this.policyBenefitBean.setProcedureId(proc);
		}
	}
	public String getBenefitType() {
		String result  = "";
		
		if (policyBenefitBean.getBenefitType() != null){
			result = policyBenefitBean.getBenefitType().toString();
		}
		return result;
	}
	public void setBenefitType(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			this.policyBenefitBean.setBenefitType(Integer.valueOf(obj));
		}
	}
		


}
