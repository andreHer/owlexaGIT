
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.DiagnosisForm;

// imports- 

/**
 * Diagnosis is a mapping of diagnosis Table.
*/
public class DiagnosisValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return DiagnosisForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"diagnosisId","DIAGNOSIS_ID_REQUIRED","diagnosisId is required");
																																																																																																																																																						
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
