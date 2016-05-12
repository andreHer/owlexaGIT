
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 


/**
 * ClientProviderDiagnosis is a mapping of client_provider_diagnosis Table.
*/
public class ClientProviderDiagnosisValidator implements Validator
// extends+ 

// extends- 

{
	public boolean supports(Class clazz) {
		return ClientProviderDiagnosisForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"clientProviderDiagnosisId","CLIENT_PROVIDER_DIAGNOSIS_ID_REQUIRED","clientProviderDiagnosisId is required");
																																						ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"createdTime","CREATED_TIME_REQUIRED","createdTime is required");
																																																																														
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"diagnosisId","DIAGNOSIS_ID_REQUIRED",
	"diagnosisId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"clientProviderId","CLIENT_PROVIDER_ID_REQUIRED",
	"clientProviderId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 

}
