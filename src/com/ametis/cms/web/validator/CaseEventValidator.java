
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.CaseEventForm;

// imports- 

/**
 * CaseEvent is a mapping of case_event Table.
*/
public class CaseEventValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return CaseEventForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"caseEventId","CASE_EVENT_ID_REQUIRED","caseEventId is required");
																																																																																																																																																																																																
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"case","CASE_ID_REQUIRED",
	"caseId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"description","CASE_ID_REQUIRED",
			"Description is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"diagnosticTesting","CASE_ID_REQUIRED",
			"Diagnostic Testing is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"remarks","CASE_ID_REQUIRED",
			"Remarks is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
