
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.PendingCategoryForm;

// imports- 

/**
 * PendingCategory is a mapping of pending_category Table.
*/
public class PendingCategoryValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return PendingCategoryForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"pendingCategoryId","PENDING_CATEGORY_ID_REQUIRED","pendingCategoryId is required");
																																
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
