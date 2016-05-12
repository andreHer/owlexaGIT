
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 


/**
 * ProviderService is a mapping of provider_service Table.
*/
public class ProviderServiceValidator implements Validator
// extends+ 

// extends- 

{
	public boolean supports(Class clazz) {
		return ProviderServiceForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"providerServiceId","PROVIDER_SERVICE_ID_REQUIRED","providerServiceId is required");
																																																																																																																		
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"caseCategoryId","CASE_CATEGORY_ID_REQUIRED",
	"Case Category is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"providerId","PROVIDER_ID_REQUIRED",
	"Provider is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"discount","PROVIDER_ID_REQUIRED",
			"Discount Info is required, If No Discount Enter 0");
			// -- foreign affairs end

	}

// class+ 

// class- 

}
