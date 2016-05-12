
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.PriorityForm;

// imports- 

/**
 * Priority is a mapping of priority Table.
*/
public class PriorityValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return PriorityForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"priorityId","PRIORITY_ID_REQUIRED","priorityId is required");
																																
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
