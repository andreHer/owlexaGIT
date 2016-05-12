
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.CallCategoryForm;

// imports- 

/**
 * CallCategory is a mapping of call_category Table.
*/
public class CallCategoryValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return CallCategoryForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"callCategoryId","CALL_CATEGORY_ID_REQUIRED","callCategoryId is required");
																																																																																																																														
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
