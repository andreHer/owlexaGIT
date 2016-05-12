
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.ClausulForm;

// imports- 

/**
 * Clausul is a mapping of clausul Table.
*/
public class ClausulValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ClausulForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"clausulId","CLAUSUL_ID_REQUIRED","clausulId is required");
																																																																																																																																				
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"clausulCategoryId","CLAUSUL_CATEGORY_ID_REQUIRED",
	"clausulCategoryId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
