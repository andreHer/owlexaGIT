
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.FundCategoryForm;

// imports- 

/**
 * FundCategory is a mapping of fund_category Table.
*/
public class FundCategoryValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return FundCategoryForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"fundCategoryId","FUND_CATEGORY_ID_REQUIRED","fundCategoryId is required");
																																												
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
