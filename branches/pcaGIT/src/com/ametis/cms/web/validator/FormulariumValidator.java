
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 


/**
 * Formularium is a mapping of formularium Table.
*/
public class FormulariumValidator implements Validator
// extends+ 

// extends- 

{
	public boolean supports(Class clazz) {
		return FormulariumForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {}
}
