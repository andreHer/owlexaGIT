
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.MeasurementUnitForm;

// imports- 

/**
 * MeasurementUnit is a mapping of measurement_unit Table.
*/
public class MeasurementUnitValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return MeasurementUnitForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"measurementUnitId","MEASUREMENT_UNIT_ID_REQUIRED","measurementUnitId is required");
																																																																																																																		
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
