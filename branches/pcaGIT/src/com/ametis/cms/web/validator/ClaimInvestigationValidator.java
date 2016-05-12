
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.ClaimInvestigationForm;

// imports- 

/**
 * ClaimInvestigation is a mapping of claim_investigation Table.
*/
public class ClaimInvestigationValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ClaimInvestigationForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace
			(errors,"claimInvestigationId","CLAIM_INVESTIGATION_ID_REQUIRED","claimInvestigationId is required");
																																

		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"investigationSubject","CLAIM_INVESTIGATION_ID_REQUIRED","Subject is required");
		

		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"investigationDate","CLAIM_INVESTIGATION_ID_REQUIRED","Investigation Date is required");
		

		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"description","CLAIM_INVESTIGATION_ID_REQUIRED","Description is required");

		// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
