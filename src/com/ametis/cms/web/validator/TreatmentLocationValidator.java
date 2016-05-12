
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.TreatmentLocationForm;

// imports- 

/**
 * TreatmentLocation is a mapping of treatment_location Table.
*/
public class TreatmentLocationValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return TreatmentLocationForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"locationId","LOCATION_ID_REQUIRED","locationId is required");
																																
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
