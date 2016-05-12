
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.InvestigationCategoryForm;

// imports- 

/**
 * InvestigationCategory is a mapping of investigation_category Table.
*/
public class InvestigationCategoryValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return InvestigationCategoryForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"investigationCategoryId","INVESTIGATION_CATEGORY_ID_REQUIRED","investigationCategoryId is required");
																																
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
