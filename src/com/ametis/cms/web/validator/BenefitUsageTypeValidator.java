
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.BenefitUsageTypeForm;

// imports- 

/**
 * BenefitUsageType is a mapping of benefit_usage_type Table.
*/
public class BenefitUsageTypeValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return BenefitUsageTypeForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"benefitUsageTypeId","BENEFIT_USAGE_TYPE_ID_REQUIRED","benefitUsageTypeId is required");
																																
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
