
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.RejectedCaseForm;

// imports- 

/**
 * RejectedCase is a mapping of rejected_case Table.
*/
public class RejectedCaseValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return RejectedCaseForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								
	}

// class+ 

// class- 
}
