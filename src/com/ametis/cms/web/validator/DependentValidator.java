
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.DependentForm;

// imports- 

/**
 * Dependent is a mapping of dependent Table.
*/
public class DependentValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return DependentForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"dependentId","DEPENDENT_ID_REQUIRED","dependentId is required");
																																																																																																																																																
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"status","STATUS_REQUIRED",
	"status is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"memberId","MEMBER_ID_REQUIRED",
	"memberId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"relationshipId","RELATIONSHIP_ID_REQUIRED",
	"relationshipId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
