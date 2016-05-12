
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.SubscriptionStatusForm;

// imports- 

/**
 * SubscriptionStatus is a mapping of subscription_status Table.
*/
public class SubscriptionStatusValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return SubscriptionStatusForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"statusId","STATUS_ID_REQUIRED","statusId is required");
																																
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
