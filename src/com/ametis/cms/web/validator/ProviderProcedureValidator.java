
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 


/**
 * ProviderProcedure is a mapping of provider_procedure Table.
*/
public class ProviderProcedureValidator implements Validator
// extends+ 

// extends- 

{
	public boolean supports(Class clazz) {
		return ProviderProcedureForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
																																																																																																																																																																														
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 

}
