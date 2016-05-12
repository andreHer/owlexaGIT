package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.CostContainment;
import com.ametis.cms.datamodel.CostContainmentType;
import com.ametis.cms.datamodel.Item;
import com.ametis.cms.util.StringUtil;

// imports+ 

// imports- 

/**
 * CostContainment is a mapping of cost_containment Table.
 */
public class CostContainmentForm implements java.io.Serializable
// extends+

// extends-
{

	private boolean isNewCostContainment = false;

	private CostContainment costContainmentBean;
	private String caseNumber;
	
	private String itemName;
	private String substitutionItemId;
	private String substitutionItemName;
	private String memberId;
	private Integer clientId;

	/*
	 * <form>Bean merupakan representasi dari "SINGLE" table dan misalkan ada
	 * form2 yang merupakan referensi dari tabel lain bikin aja field2 bean yang
	 * mengacu ke referensi itu biar nanti automatic loading
	 * 
	 */

	public CostContainmentForm() {
		this.costContainmentBean = new CostContainment();
		this.isNewCostContainment = true;
	}

	public CostContainmentForm(CostContainment object) {
		this.costContainmentBean = object;
	}

	public boolean isNewCostContainment() {

		return this.isNewCostContainment;
	}

	public CostContainment getCostContainment() {
		return this.costContainmentBean;
	}

	public void setCostContainment(CostContainment object) {
		this.costContainmentBean = object;
	}

	public void setCostContainmentId(String obj) {

		costContainmentBean.setCostContainmentId(StringUtil.getIntegerValue(
				obj, 0));

	}

	public String getCostContainmentId() {
		return StringUtil.getStringValue(costContainmentBean
				.getCostContainmentId());

	}
	

	public void setCaseId(String obj) {

		if (obj != null && !obj.equalsIgnoreCase("")){
			Case caseId = new Case();
			caseId.setCaseId(Integer.valueOf(obj));
			costContainmentBean.setCaseId(caseId);
		}

	}

	public String getCaseId() {
		String result = "";
		
		if (costContainmentBean.getCaseId() != null){
			result = costContainmentBean.getCaseId().getCaseId().toString();
		}
		
		return result;

	}

	public void setMeasurementUnitId(String obj) {

		costContainmentBean.setMeasurementUnitId(StringUtil.getIntegerValue(
				obj, 0));

	}

	public String getMeasurementUnitId() {
		return StringUtil.getStringValue(costContainmentBean
				.getMeasurementUnitId());

	}

	public void setDescription(String obj) {

		costContainmentBean.setDescription(new String(obj));

	}

	public String getDescription() {
		return StringUtil.getStringValue(costContainmentBean.getDescription());

	}

	public void setInitialItemCost(String obj) {

		costContainmentBean.setInitialItemCost(StringUtil
				.getDoubleValue(obj, 0));

	}

	public String getInitialItemCost() {
		return StringUtil.getStringValue(costContainmentBean
				.getInitialItemCost());

	}

	public void setTotalSubstitutionValue(String obj) {

		costContainmentBean.setTotalSubstitutionValue(StringUtil
				.getDoubleValue(obj, 0));

	}

	public String getTotalSubstitutionValue() {
		return StringUtil.getStringValue(costContainmentBean
				.getTotalSubstitutionValue());

	}

	public void setTotalItem(String obj) {

		costContainmentBean.setTotalItem(StringUtil.getDoubleValue(obj, 0));

	}

	public String getTotalItem() {
		return StringUtil.getStringValue(costContainmentBean.getTotalItem());

	}

	

	// foreign affairs

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public void setItemId(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			Item fk = new Item();
			fk.setItemId(StringUtil.getIntegerValue(obj, 0));
			costContainmentBean.setItemId(fk);
		}

	}

	public String getItemId() {
		String result = "";
		if (costContainmentBean.getItemId() != null){
			result =  StringUtil.getStringValue(costContainmentBean.getItemId()
					.getItemId());
		}
		return result;
		
		
		

	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getSubstitutionItemId() {
		return substitutionItemId;
	}

	public void setSubstitutionItemId(String substitutionItemId) {
		this.substitutionItemId = substitutionItemId;
	}

	public String getSubstitutionItemName() {
		return substitutionItemName;
	}

	public void setSubstitutionItemName(String substitutionItemName) {
		this.substitutionItemName = substitutionItemName;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	

}
