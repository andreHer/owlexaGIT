
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.OutstandingForm;

// imports- 

/**
 * Outstanding is a mapping of outstanding Table.
*/
public class OutstandingValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return OutstandingForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"outstandingId","OUTSTANDING_ID_REQUIRED","outstandingId is required");
																																																																																																																																																						
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"clientId","CLIENT_ID_REQUIRED",
	"clientId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"batchClaimId","BATCH_CLAIM_ID_REQUIRED",
	"batchClaimId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
