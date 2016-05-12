
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.AuthProviderForm;

// imports- 

/**
 * AuthProvider is a mapping of auth_provider Table.
*/
public class AuthProviderValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return AuthProviderForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"authProviderId","AUTH_PROVIDER_ID_REQUIRED","authProviderId is required");
																																																																																																																																																						
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
