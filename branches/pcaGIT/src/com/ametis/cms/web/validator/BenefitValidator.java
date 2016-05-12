
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.BenefitForm;

// imports- 

/**
 * Benefit is a mapping of benefit Table.
*/
public class BenefitValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return BenefitForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"benefitId","BENEFIT_ID_REQUIRED","benefitId is required");
																																																																																																																																																																																										
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
