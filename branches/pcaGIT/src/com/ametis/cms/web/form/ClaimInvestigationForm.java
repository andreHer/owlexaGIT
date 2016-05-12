package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimInvestigation;
import com.ametis.cms.datamodel.InvestigationCategory;
import com.ametis.cms.util.StringUtil;

// imports+ 

// imports- 

/**
 * ClaimInvestigation is a mapping of claim_investigation Table.
 */
public class ClaimInvestigationForm implements java.io.Serializable
// extends+

// extends-

{

	private boolean isNewClaimInvestigation = false;

	private ClaimInvestigation claimInvestigationBean;
	private String claimNumber;

	/*
	 * <form>Bean merupakan representasi dari "SINGLE" table dan misalkan ada
	 * form2 yang merupakan referensi dari tabel lain bikin aja field2 bean yang
	 * mengacu ke referensi itu biar nanti automatic loading
	 * 
	 */

	public ClaimInvestigationForm() {
		this.claimInvestigationBean = new ClaimInvestigation();
		this.isNewClaimInvestigation = true;
	}

	public ClaimInvestigationForm(ClaimInvestigation object) {
		this.claimInvestigationBean = object;
	}

	public boolean isNewClaimInvestigation() {

		return this.isNewClaimInvestigation;
	}

	public ClaimInvestigation getClaimInvestigation() {
		return this.claimInvestigationBean;
	}

	public void setClaimInvestigation(ClaimInvestigation object) {
		this.claimInvestigationBean = object;
	}

	public void setClaimInvestigationId(String obj) {

		claimInvestigationBean.setClaimInvestigationId(StringUtil.getLongValue(
				obj, 0));

	}

	public String getClaimInvestigationId() {
		return StringUtil.getStringValue(claimInvestigationBean
				.getClaimInvestigationId());

	}

	public void setInvestigationDate(String obj) {

		claimInvestigationBean.setInvestigationDate(java.sql.Date.valueOf(obj));

	}

	public String getInvestigationDate() {
		return StringUtil.getStringValue(claimInvestigationBean.getInvestigationDate());

	}

	public void setDescription(String obj) {

		claimInvestigationBean.setDescription(new String(obj));

	}

	public String getDescription() {
		return StringUtil.getStringValue(claimInvestigationBean
				.getDescription());

	}

	public void setInvestigationSubject(String obj) {

		claimInvestigationBean.setInvestigationSubject(new String(obj));

	}

	public String getInvestigationSubject() {
		return StringUtil.getStringValue(claimInvestigationBean
				.getInvestigationSubject());

	}

	public void setHeadDoctor(String obj) {

		claimInvestigationBean.setHeadDoctor(new String(obj));

	}

	public String getHeadDoctor() {
		return StringUtil
				.getStringValue(claimInvestigationBean.getHeadDoctor());

	}

	public void setConsultDoctor(String obj) {

		claimInvestigationBean.setConsultDoctor(new String(obj));

	}

	public String getConsultDoctor() {
		return StringUtil.getStringValue(claimInvestigationBean
				.getConsultDoctor());

	}

	public void setNurse(String obj) {

		claimInvestigationBean.setNurse(new String(obj));

	}

	public String getNurse() {
		return StringUtil.getStringValue(claimInvestigationBean.getNurse());

	}

	// foreign affairs

	public void setInvestigationCategoryId(String obj) {

		if (obj != null && !obj.equalsIgnoreCase("")){
			InvestigationCategory ic = new InvestigationCategory();
			ic.setInvestigationCategoryId(Integer.valueOf(obj));
			
			claimInvestigationBean.setInvestigationCategoryId(ic);
		}

	}

	public String getInvestigationCategoryId() {
		String result = "";
		
		if (claimInvestigationBean.getInvestigationCategoryId() != null){
			result = claimInvestigationBean.getInvestigationCategoryId().getInvestigationCategoryId().toString();
		}
		return result;

	}

	// ---

	public void setClaimId(String obj) {

		if (obj != null && !obj.equalsIgnoreCase("")){
			Claim claim = new Claim();
			claim.setClaimId(Integer.valueOf(obj));
			claimInvestigationBean.setClaimId(claim);
		}

	}

	public String getClaimId() {
		String result = "";
		
		if (claimInvestigationBean.getClaimId() != null){
			result = claimInvestigationBean.getClaimId().getClaimId().toString();
		}
		return result;

	}

	public String getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}
	

}
