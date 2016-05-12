
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 


/**
 * PolicyProviderDiagnosis is a mapping of policy_provider_diagnosis Table.
*/
public class PolicyProviderDiagnosisValidator implements Validator
// extends+ 

// extends- 

{
	public boolean supports(Class clazz) {
		return PolicyProviderDiagnosisForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"policyProviderDiagnosisId","POLICY_PROVIDER_DIAGNOSIS_ID_REQUIRED","policyProviderDiagnosisId is required");
																																						ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"createdTime","CREATED_TIME_REQUIRED","createdTime is required");
																																																																														
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"diagnosisId","DIAGNOSIS_ID_REQUIRED",
	"diagnosisId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"policyProviderId","POLICY_PROVIDER_ID_REQUIRED",
	"policyProviderId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 

}
