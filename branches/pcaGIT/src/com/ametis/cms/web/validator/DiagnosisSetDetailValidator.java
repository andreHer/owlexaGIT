
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * DiagnosisSetDetail is a mapping of diagnosis_set_detail Table.
*/
public class DiagnosisSetDetailValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return DiagnosisSetDetailForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"diagnosisSetDetailId","DIAGNOSIS_SET_DETAIL_ID_REQUIRED","diagnosisSetDetailId is required");
																																																																				ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"createdTime","CREATED_TIME_REQUIRED","createdTime is required");
																																																																																				
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"diagnosisId","DIAGNOSIS_ID_REQUIRED",
	"diagnosisId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"diagnosisSetId","DIAGNOSIS_SET_ID_REQUIRED",
	"diagnosisSetId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
