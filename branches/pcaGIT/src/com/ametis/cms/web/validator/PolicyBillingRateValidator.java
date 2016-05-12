
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * PolicyBillingRate is a mapping of policy_billing_rate Table.
*/
public class PolicyBillingRateValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return PolicyBillingRateForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"policyBillingRateId","POLICY_BILLING_RATE_ID_REQUIRED","policyBillingRateId is required");
																																																																																																		ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"createdTime","CREATED_TIME_REQUIRED","createdTime is required");
																																																																														
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"itemId","ITEM_ID_REQUIRED",
	"itemId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"policyId","POLICY_ID_REQUIRED",
	"policyId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
