
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.CaseStatusForm;

// imports- 

/**
 * CaseStatus is a mapping of case_status Table.
*/
public class CaseStatusValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return CaseStatusForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"caseStatusId","CASE_STATUS_ID_REQUIRED","caseStatusId is required");
																																
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
