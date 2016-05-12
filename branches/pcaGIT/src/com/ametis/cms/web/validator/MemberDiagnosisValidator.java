
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.MemberDiagnosisForm;

// imports- 

/**
 * MemberDiagnosis is a mapping of member_diagnosis Table.
*/
public class MemberDiagnosisValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return MemberDiagnosisForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"memberDiagnosisId","MEMBER_DIAGNOSIS_ID_REQUIRED","memberDiagnosisId is required");
																																																																																																						
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"diagnosisId","DIAGNOSIS_ID_REQUIRED",
	"diagnosisId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"memberId","MEMBER_ID_REQUIRED",
	"memberId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
