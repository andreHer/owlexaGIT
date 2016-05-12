
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 


/**
 * Configuration is a mapping of configuration Table.
*/
public class ConfigurationValidator implements Validator
// extends+ 

// extends- 

{
	public boolean supports(Class clazz) {
		return ConfigurationForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
																																																																																
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 

}
