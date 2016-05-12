
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 


/**
 * ProblemCategory is a mapping of problem_category Table.
*/
public class ProblemCategoryValidator implements Validator
// extends+ 

// extends- 

{
	public boolean supports(Class clazz) {
		return ProblemCategoryForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"problemCategoryId","PROBLEM_CATEGORY_ID_REQUIRED","problemCategoryId is required");
																																
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 

}
