
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * CaseProcedure is a mapping of case_procedure Table.
*/
public class CaseProcedureValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return CaseProcedureForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace
			(errors,"caseProcedureId","CASE_PROCEDURE_ID_REQUIRED","caseProcedureId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"procedureId","PROCEDURE_ID_REQUIRED","Tindakan / Procedure is required");
																				
	}

// class+ 

// class- 
}
