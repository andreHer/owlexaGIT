
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.ServiceCategoryForm;

// imports- 

/**
 * ServiceCategory is a mapping of service_category Table.
*/
public class ServiceCategoryValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ServiceCategoryForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"serviceCategoryId","SERVICE_CATEGORY_ID_REQUIRED","serviceCategoryId is required");
																																																																																																																														
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
