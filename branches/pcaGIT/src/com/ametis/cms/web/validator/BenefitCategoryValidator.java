
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.BenefitCategoryForm;

// imports- 

/**
 * BenefitCategory is a mapping of benefit_category Table.
*/
public class BenefitCategoryValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return BenefitCategoryForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"benefitCategoryId","BENEFIT_CATEGORY_ID_REQUIRED","benefitCategoryId is required");
																																																																																																																		
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
