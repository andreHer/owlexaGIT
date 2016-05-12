
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.ExcessChargeForm;

// imports- 

/**
 * ExcessCharge is a mapping of excess_charge Table.
*/
public class ExcessChargeValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ExcessChargeForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"excessChargeId","EXCESS_CHARGE_ID_REQUIRED","excessChargeId is required");
																																																																																																																																																																																										
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"memberId","MEMBER_ID_REQUIRED",
	"memberId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"claimId","CLAIM_ID_REQUIRED",
	"claimId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
