
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * OustandingClaimItem is a mapping of oustanding_claim_item Table.
*/
public class OutstandingClaimItemValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return OutstandingClaimItemForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"outstandingClaimItemId","OUTSTANDING_CLAIM_ITEM_ID_REQUIRED","outstandingClaimItemId is required");
																																																																																																																				
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
