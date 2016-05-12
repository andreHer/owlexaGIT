
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * ClaimHistory is a mapping of claim_history Table.
*/
public class ClaimHistoryValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ClaimHistoryForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"claimHistoryId","CLAIM_HISTORY_ID_REQUIRED","claimHistoryId is required");
																																																																																																																																																																																												ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"actionTime","ACTION_TIME_REQUIRED","actionTime is required");
																																																																																																																																																																																										
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"claimId","CLAIM_ID_REQUIRED",
	"claimId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
