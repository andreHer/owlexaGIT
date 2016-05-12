
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * ProcedureCodeClientMapping is a mapping of procedure_code_client_mapping Table.
*/
public class ProcedureCodeClientMappingValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ProcedureCodeClientMappingForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {

	}

// class+ 

// class- 
}
