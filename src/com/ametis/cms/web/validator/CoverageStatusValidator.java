
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.CoverageStatusForm;

// imports- 

/**
 * CoverageStatus is a mapping of coverage_status Table.
*/
public class CoverageStatusValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return CoverageStatusForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"coverageStatusId","COVERAGE_STATUS_ID_REQUIRED","coverageStatusId is required");
																																
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
