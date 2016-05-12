
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.CaseInvestigationForm;

// imports- 

/**
 * CaseInvestigation is a mapping of case_investigation Table.
*/
public class CaseInvestigationValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return CaseInvestigationForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"caseInvestigationId","CASE_INVESTIGATION_ID_REQUIRED","caseInvestigationId is required");
																	
								
								

		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"investigationSubject","CLAIM_INVESTIGATION_ID_REQUIRED","Subject is required");
		

		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"investigationDate","CLAIM_INVESTIGATION_ID_REQUIRED","Investigation Date is required");
		

		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"description","CLAIM_INVESTIGATION_ID_REQUIRED","Description is required");
	}

}
