
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * PolicyPayment is a mapping of policy_payment Table.
*/
public class PolicyPaymentValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return PolicyPaymentForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"policyPaymentId","POLICY_PAYMENT_ID_REQUIRED","policyPaymentId is required");
														
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"policyId","POLICY_ID_REQUIRED",
	"policyId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
