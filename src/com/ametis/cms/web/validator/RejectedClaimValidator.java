
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.RejectedClaimForm;

// imports- 

/**
 * RejectedClaim is a mapping of rejected_claim Table.
*/
public class RejectedClaimValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return RejectedClaimForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"rejectedClaimId","REJECTED_CLAIM_ID_REQUIRED","rejectedClaimId is required");
																																																																																																																																										
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
