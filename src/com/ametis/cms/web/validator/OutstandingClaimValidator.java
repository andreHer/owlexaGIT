
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * OutstandingClaim is a mapping of outstanding_claim Table.
*/
public class OutstandingClaimValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return OutstandingClaimForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"outstandingClaimId","OUTSTANDING_CLAIM_ID_REQUIRED","outstandingClaimId is required");
																																																																																																																																																																																																																																												
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
