
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.ClaimTypeForm;

// imports- 

/**
 * ClaimType is a mapping of claim_type Table.
*/
public class ClaimTypeValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ClaimTypeForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"claimTypeId","CLAIM_TYPE_ID_REQUIRED","claimTypeId is required");
																																
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
