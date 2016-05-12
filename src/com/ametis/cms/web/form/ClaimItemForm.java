package com.ametis.cms.web.form;

import java.util.Date;

import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimItem;
import com.ametis.cms.datamodel.CoverageStatus;
import com.ametis.cms.datamodel.Item;
import com.ametis.cms.datamodel.MeasurementUnit;
import com.ametis.cms.util.StringUtil;

// imports+ 

// imports- 

/**
 * ClaimItem is a mapping of claim_item Table.
 */
public class ClaimItemForm implements java.io.Serializable
// extends+

// extends-
{

	private boolean isNewClaimItem = false;

	private ClaimItem claimItemBean;
	private String itemName;
	private String claimNumber;
	private String memberId;

	/*
	 * <form>Bean merupakan representasi dari "SINGLE" table dan misalkan ada
	 * form2 yang merupakan referensi dari tabel lain bikin aja field2 bean yang
	 * mengacu ke referensi itu biar nanti automatic loading
	 * 
	 */

	public ClaimItemForm() {
		this.claimItemBean = new ClaimItem();
		this.isNewClaimItem = true;
	}

	public ClaimItemForm(ClaimItem object) {
		this.claimItemBean = object;
	}

	public boolean isNewClaimItem() {

		return this.isNewClaimItem;
	}

	public ClaimItem getClaimItem() {
		return this.claimItemBean;
	}

	public void setClaimItem(ClaimItem object) {
		this.claimItemBean = object;
	}

	public void setClaimItemId(String obj) {

		claimItemBean.setClaimItemId(StringUtil.getIntegerValue(obj, 0));

	}

	public String getClaimItemId() {
		return StringUtil.getStringValue(claimItemBean.getClaimItemId());

	}

	public void setClaimItemRemarks(String obj) {

		claimItemBean.setClaimItemRemarks(new String(obj));

	}

	public String getClaimItemRemarks() {
		return StringUtil.getStringValue(claimItemBean.getClaimItemRemarks());

	}

	public void setClaimItemDescription(String obj) {

		claimItemBean.setClaimItemDescription(new String(obj));

	}

	public String getClaimItemDescription() {
		return StringUtil.getStringValue(claimItemBean
				.getClaimItemDescription());

	}

	public void setClaimItemStatus(CaseStatus obj) {

		claimItemBean.setClaimItemStatus(obj);

	}

	public CaseStatus getClaimItemStatus() {
		return claimItemBean.getClaimItemStatus();

	}

	public void setClaimItemValue(String obj) {

		claimItemBean.setClaimItemValue(StringUtil.getDoubleValue(obj, 0));

	}

	public String getClaimItemValue() {
		return StringUtil.getStringValue(claimItemBean.getClaimItemValue());

	}

	public void setClaimItemAmount(String obj) {

		claimItemBean.setClaimItemAmount(StringUtil.getDoubleValue(obj, 0));

	}

	public String getClaimItemAmount() {
		return StringUtil.getStringValue(claimItemBean.getClaimItemAmount());

	}

	public void setClaimItemApprovedValue(String obj) {

		claimItemBean.setClaimItemApprovedValue(StringUtil.getDoubleValue(obj,
				0));

	}

	public String getClaimItemApprovedValue() {
		return StringUtil.getStringValue(claimItemBean
				.getClaimItemApprovedValue());

	}

	public void setClaimItemCoveredValue(String obj) {

		claimItemBean.setClaimItemCoveredValue(StringUtil
				.getDoubleValue(obj, 0));

	}

	public String getClaimItemCoveredValue() {
		return StringUtil.getStringValue(claimItemBean
				.getClaimItemCoveredValue());

	}

	public void setCoverageStatus(CoverageStatus obj) {

		claimItemBean.setCoverageStatus(obj);

	}

	public CoverageStatus getCoverageStatus() {
		
		return claimItemBean.getCoverageStatus();

	}

	public void setCreatedTime(String obj) {

		claimItemBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime() {
		return StringUtil.getStringValue(claimItemBean.getCreatedTime());

	}

	public void setCreatedBy(String obj) {

		claimItemBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy() {
		return StringUtil.getStringValue(claimItemBean.getCreatedBy());

	}

	public void setDeletedTime(String obj) {

		claimItemBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime() {
		return StringUtil.getStringValue(claimItemBean.getDeletedTime());

	}

	public void setDeletedBy(String obj) {

		claimItemBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy() {
		return StringUtil.getStringValue(claimItemBean.getDeletedBy());

	}

	public void setModifiedTime(String obj) {

		claimItemBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime() {
		return StringUtil.getStringValue(claimItemBean.getModifiedTime());

	}

	public void setModifiedBy(String obj) {

		claimItemBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy() {
		return StringUtil.getStringValue(claimItemBean.getModifiedBy());

	}

	public void setDeletedStatus(String obj) {

		claimItemBean.setDeletedStatus(StringUtil.getIntegerValue(obj, 0));

	}

	public String getDeletedStatus() {
		return StringUtil.getStringValue(claimItemBean.getDeletedStatus());

	}

	// foreign affairs

	public void setItemId(String obj) {

		if (obj != null && !obj.equalsIgnoreCase("")){
			Item item = new Item();
			item.setItemId(Integer.valueOf(obj));
			claimItemBean.setItemId(item);
		}

	}

	public String getItemId() {
		
		String result = "";
		
		if (claimItemBean.getItemId() != null){
			result = claimItemBean.getItemId().getItemId().toString();
		}
		return result;
		

	}

	// ---

	public void setClaimId(String obj) {

		if (obj != null && !obj.equals("")){
			Claim claim = new Claim();
			claim.setClaimId(Integer.valueOf(obj));
			claimItemBean.setClaimId(claim);
		}

	}

	public String getClaimId() {
		
		String result = "";
		
		if (claimItemBean.getClaimId() != null){
			result = claimItemBean.getClaimId().getClaimId().toString();
		}
		
		return result;

	}

	// ---

	public void setMeasurementUnitId(String obj) {
		MeasurementUnit fk = new MeasurementUnit();
		fk.setMeasurementUnitId(StringUtil.getIntegerValue(obj, 0));
		claimItemBean.setMeasurementUnitId(fk);

	}

	public String getMeasurementUnitId() {
		
		String result = "";
		
		if (claimItemBean.getMeasurementUnitId() != null){
			StringUtil.getStringValue(claimItemBean.getMeasurementUnitId()
					.getMeasurementUnitId());
		}
		
		return result; 

	}
	
	
	
	public String getBenefitCheckedBy() {
		return claimItemBean.getBenefitCheckedBy();
	}
	public void setBenefitCheckedBy(String benefitCheckedBy) {
		claimItemBean.setBenefitCheckedBy(benefitCheckedBy);
	}
	
	public Date getBenefitCheckedDate() {
		return claimItemBean.getBenefitCheckedDate();
	}
	public void setBenefitCheckedDate(java.sql.Date benefitCheckedDate) {
		claimItemBean.setBenefitCheckedDate(benefitCheckedDate);
	}
	
	public Double getExcessValue() {
		return claimItemBean.getExcessValue();
	}
	public void setExcessValue(Double excessValue) {
		claimItemBean.setExcessValue(excessValue);
	}
	
	public Double getFaultExcessProvider() {
		return claimItemBean.getFaultExcessProvider();
	}
	public void setFaultExcessProvider(Double faultExcessProvider) {
		claimItemBean.setFaultExcessProvider(faultExcessProvider);
	}
	
	
	public String getVerifiedBy() {
		return claimItemBean.getVerifiedBy();
	}
	public void setVerifiedBy(String verifiedBy) {
		claimItemBean.setVerifiedBy(verifiedBy);
	}
	
	public Date getVerifiedDate() {
		return claimItemBean.getVerifiedDate();
	}
	public void setVerifiedDate(java.sql.Date verifiedDate) {
		claimItemBean.setVerifiedDate(verifiedDate);
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	
}
