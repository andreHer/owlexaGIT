
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.GenderForm;

// imports- 

/**
 * Gender is a mapping of gender Table.
*/
public class GenderValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return GenderForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"genderId","GENDER_ID_REQUIRED","genderId is required");
																																
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
