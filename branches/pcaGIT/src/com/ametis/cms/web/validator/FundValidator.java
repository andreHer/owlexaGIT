
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.FundForm;

// imports- 

/**
 * Fund is a mapping of fund Table.
*/
public class FundValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return FundForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
	

	}

// class+ 

// class- 
}
