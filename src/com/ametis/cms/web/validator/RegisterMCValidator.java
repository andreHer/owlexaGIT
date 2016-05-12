
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.CaseForm;

// imports- 

/**
 * Case is a mapping of case Table.
*/
public class RegisterMCValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return CaseForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
										
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"memberId","MEMBER_ID_REQUIRED",
	"memberId is required");
		

		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"caseStartTime","MEMBER_ID_REQUIRED",
	"Admission Date is required");
		

		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"providerId","PROVIDER_ID_REQUIRED",
	"Provider is required");
		
	}

// class+ 

// class- 
}
