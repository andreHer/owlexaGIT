
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.ProviderForm;

// imports- 

/**
 * Provider is a mapping of provider Table.
*/
public class ProviderClaimValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ProviderForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"providerId","PROVIDER_ID_REQUIRED","providerId is required");
																																																																																																																																																																																																																																																																																																																		
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"providerName","PROVIDER_NAME_REQUIRED",
			"Provider Name is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"providerCategoryId","PROVIDER_CATEGORY_ID_REQUIRED",
				"providerCategoryId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"province","PROVINCE_ID_REQUIRED",
			"Province is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"country","COUNTRY_ID_REQUIRED",
			"Country is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"city","CITY_ID_REQUIRED",
			"City is required");

	}

// class+ 

// class- 
}
