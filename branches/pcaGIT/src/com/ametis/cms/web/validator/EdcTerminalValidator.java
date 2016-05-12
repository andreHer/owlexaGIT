
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 


/**
 * EdcTerminal is a mapping of edc_terminal Table.
*/
public class EdcTerminalValidator implements Validator
// extends+ 

// extends- 

{
	public boolean supports(Class clazz) {
		return EdcTerminalForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"providerName","PROVIDER_NAME_REQUIRED",
			"Provider Name is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"location","LOCATION_REQUIRED",
			"EDC Location is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"deviceNumber","TERMINAL_ID_REQUIRED",
			"Terminal ID is required");
	}

// class+ 

// class- 

}
