
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.DiagnosisItemForm;

// imports- 

/**
 * DiagnosisItem is a mapping of diagnosis_item Table.
*/
public class DiagnosisItemValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return DiagnosisItemForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"diagnosisItemId","DIAGNOSIS_ITEM_ID_REQUIRED","diagnosisItemId is required");
																																																																																																						
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"diagnosisId","DIAGNOSIS_ID_REQUIRED",
	"diagnosisId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"itemId","ITEM_ID_REQUIRED",
	"itemId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
