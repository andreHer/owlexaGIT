
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * DiagnosisGroup is a mapping of diagnosis_group Table.
*/
public class DiagnosisGroupValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return DiagnosisGroupForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"diagnosisGroupId","DIAGNOSIS_GROUP_ID_REQUIRED","diagnosisGroupId is required");
																																
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
