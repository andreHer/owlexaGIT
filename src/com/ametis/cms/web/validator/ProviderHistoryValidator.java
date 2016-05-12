
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * ProviderHistory is a mapping of provider_history Table.
*/
public class ProviderHistoryValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ProviderHistoryForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"providerHistoryId","PROVIDER_HISTORY_ID_REQUIRED","providerHistoryId is required");
																																																		ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"actionTime","ACTION_TIME_REQUIRED","actionTime is required");
																																																																																										
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
