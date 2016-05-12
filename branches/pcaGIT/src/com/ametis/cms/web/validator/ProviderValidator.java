
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.ProviderForm;

// imports- 

/**
 * Provider is a mapping of provider Table.
*/
public class ProviderValidator implements Validator
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
		(errors,"contractNumber","CONTRACT_NUMBER_REQUIRED",
			"Contract Number is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"bank","BANK_REQUIRED",
			"Bank Name is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"bankAccount","BANK_ACCOUNT_REQUIRED",
			"Bank Account is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"accountName","BANK_ACCOUNT_REQUIRED",
			"Bank Account Name is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"province","PROVINCE_NAME_REQUIRED",
			"Province is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"country","COUNTRY_NAME_REQUIRED",
			"Country is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"city","CITY_NAME_REQUIRED",
			"City is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"contractStartDate","CONTRACT_START_REQUIRED",
			"Contract Start Date is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"contractEndDate","CONTRACT_END_REQUIRED",
			"Contract End Date is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"providerCategoryId","PROVIDER_CATEGORY_ID_REQUIRED",
				"providerCategoryId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
