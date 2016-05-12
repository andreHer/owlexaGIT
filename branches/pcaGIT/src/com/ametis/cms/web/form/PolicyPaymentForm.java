package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;

// imports+ 

// imports- 

/**
 * PolicyPayment is a mapping of policy_payment Table.
 */
public class PolicyPaymentForm implements java.io.Serializable
// extends+

// extends-
{

	private boolean isNewPolicyPayment = false;
	private PolicyPayment policyPaymentBean;

	/*
	 * <form>Bean merupakan representasi dari "SINGLE" table dan misalkan ada
	 * form2 yang merupakan referensi dari tabel lain bikin aja field2 bean yang
	 * mengacu ke referensi itu biar nanti automatic loading
	 * 
	 */

	public PolicyPaymentForm() {
		this.policyPaymentBean = new PolicyPayment();
		this.isNewPolicyPayment = true;
	}

	public PolicyPaymentForm(PolicyPayment object) {
		this.policyPaymentBean = object;
	}

	public boolean isNewPolicyPayment() {

		return this.isNewPolicyPayment;
	}

	public PolicyPayment getPolicyPayment() {
		return this.policyPaymentBean;
	}

	public void setPolicyPayment(PolicyPayment object) {
		this.policyPaymentBean = object;
	}

	public void setPolicyPaymentId(String obj) {

		policyPaymentBean.setPolicyPaymentId(StringUtil.getLongValue(obj, 0));

	}

	public String getPolicyPaymentId() {
		return StringUtil
				.getStringValue(policyPaymentBean.getPolicyPaymentId());

	}

	// foreign affairs

	public void setPolicyId(String obj) {

		if (obj != null && !obj.equalsIgnoreCase("")) {
			Policy fk = new Policy();
			fk.setPolicyId(StringUtil.getIntegerValue(obj, 0));
			policyPaymentBean.setPolicyId(fk);
		}

	}

	public String getPolicyId() {
		return StringUtil.getStringValue(policyPaymentBean.getPolicyId()
				.getPolicyId());

	}
	// ---
	// -- foreign affairs end

	// class+

	// class-
}
