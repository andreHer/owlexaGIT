
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.ClausulCategoryForm;

// imports- 

/**
 * ClausulCategory is a mapping of clausul_category Table.
*/
public class ClausulCategoryValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ClausulCategoryForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"clausulCategoryId","CLAUSUL_CATEGORY_ID_REQUIRED","clausulCategoryId is required");
																																																																																																																														
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
