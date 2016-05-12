
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * ProviderPoliklinik is a mapping of provider_poliklinik Table.
*/
public class ProviderPoliklinikValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ProviderPoliklinikForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
																				
																																																																														
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
