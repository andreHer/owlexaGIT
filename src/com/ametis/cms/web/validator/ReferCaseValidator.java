
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.CaseForm;

// imports- 

/**
 * Case is a mapping of case Table.
*/
public class ReferCaseValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return CaseForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
										
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"isRefered","MEMBER_ID_REQUIRED",
	"Status Rujukan is required");
		

		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"referedPoliklinikId","MEMBER_ID_REQUIRED",
	"Poliklinik is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"diagnosis1Id","MEMBER_ID_REQUIRED",
	"Primary Diagnosis is required");
	}

// class+ 

// class- 
}
