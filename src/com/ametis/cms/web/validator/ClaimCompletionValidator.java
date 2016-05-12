
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.ClaimCompletionForm;

// imports- 


/**
 * ClaimCompletion is a mapping of claim_completion Table.
*/
public class ClaimCompletionValidator implements Validator
// extends+ 

// extends- 

{
	public boolean supports(Class clazz) {
		return ClaimCompletionForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"completeClaimId","COMPLETE_CLAIM_ID_REQUIRED","completeClaimId is required");
																																																																																																																								
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"pendingClaimId","PENDING_CLAIM_ID_REQUIRED",
	"pendingClaimId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 

}
