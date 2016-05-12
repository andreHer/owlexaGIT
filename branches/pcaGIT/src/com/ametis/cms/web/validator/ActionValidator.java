
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.ActionForm;

// imports- 

/**
 * Action is a mapping of action Table.
*/
public class ActionValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ActionForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"actionId","ACTION_ID_REQUIRED","actionId is required");
																																																																																																																																										
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
