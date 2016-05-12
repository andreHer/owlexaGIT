
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * ErrorLog is a mapping of error_log Table.
*/
public class ErrorLogValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ErrorLogForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"id","ID_REQUIRED","id is required");
																																																		ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"createdTime","CREATED_TIME_REQUIRED","createdTime is required");
								
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
