
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 


/**
 * Therapy is a mapping of therapy Table.
*/
public class TherapyValidator implements Validator
// extends+ 

// extends- 

{
	public boolean supports(Class clazz) {
		return TherapyForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"therapyId","THERAPY_ID_REQUIRED","therapyId is required");
																																																														ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"createdTime","CREATED_TIME_REQUIRED","createdTime is required");
																																																																																										
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 

}
