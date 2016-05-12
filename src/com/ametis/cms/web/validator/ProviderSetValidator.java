
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * ProviderSet is a mapping of provider_set Table.
*/
public class ProviderSetValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ProviderSetForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								

	}

// class+ 

// class- 
}
