
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.ServiceDiagnosisForm;

// imports- 

/**
 * ServiceDiagnosis is a mapping of service_diagnosis Table.
*/
public class ServiceDiagnosisValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ServiceDiagnosisForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"serviceDiagnosisId","SERVICE_DIAGNOSIS_ID_REQUIRED","serviceDiagnosisId is required");
																																																																																																																		
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
