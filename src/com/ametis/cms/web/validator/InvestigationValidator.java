
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.InvestigationForm;

// imports- 

/**
 * Investigation is a mapping of investigation Table.
*/
public class InvestigationValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return InvestigationForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"investigationId","INVESTIGATION_ID_REQUIRED","investigationId is required");
																																																																																																																																																						
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
