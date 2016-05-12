
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.FirstCallForm;

// imports- 

/**
 * FirstCall is a mapping of first_call Table.
*/
public class FirstCallValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return FirstCallForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"callId","CALL_ID_REQUIRED","callId is required");
																																																																																																																																																																																																																		
// foreign affairs
	
		/*ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"handledby","HANDLEDBY_REQUIRED",
	"handledby is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"status","STATUS_REQUIRED",
	"status is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"callCategoryId","CALL_CATEGORY_ID_REQUIRED",
	"callCategoryId is required");*/
		
			}

// class+ 

// class- 
}
