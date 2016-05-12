
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.ProviderItemForm;

// imports- 

/**
 * ProviderItem is a mapping of provider_item Table.
*/
public class ProviderItemValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ProviderItemForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"providerItemId","PROVIDER_ITEM_ID_REQUIRED","providerItemId is required");
																																																																																																																								
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"kelas1","KELAS1_REQUIRED",
	"Tarif Kelas 1 is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"kelas2","KELAS2_REQUIRED",
			"Tarif Kelas 2 is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"kelas3","KELAS3_REQUIRED",
			"Tarif Kelas 3 is required");
		
		
			// -- foreign affairs end

	}

// class+ 

// class- 
}
