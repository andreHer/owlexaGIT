package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.CostContainment;

public class CaseRejectionForm {
	private Case caseBean;
	private CostContainment costContainment;
	private boolean isNewCaseRejection = true;
	public Case getCaseBean() {
		return caseBean;
	}
	public void setCaseBean(Case caseBean) {
		this.caseBean = caseBean;
	}
	public CostContainment getCostContainment() {
		return costContainment;
	}
	public void setCostContainment(CostContainment costContainment) {
		this.costContainment = costContainment;
	}
	public boolean isNewCaseRejection() {
		return isNewCaseRejection;
	}
	public void setNewCaseRejection(boolean isNewCaseRejection) {
		this.isNewCaseRejection = isNewCaseRejection;
	}
	
	
	
	
}
