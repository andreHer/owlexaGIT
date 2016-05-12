
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.ClaimItemForm;

// imports- 

/**
 * ClaimItem is a mapping of claim_item Table.
*/
public class ClaimItemValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ClaimItemForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"claimItemId","CLAIM_ITEM_ID_REQUIRED","claimItemId is required");
																																																																																																																																																																																																												
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"itemId","ITEM_ID_REQUIRED",
	"itemId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"claimId","CLAIM_ID_REQUIRED",
	"claimId is required");
	
	}

// class+ 

// class- 
}
