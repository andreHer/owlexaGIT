
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * ProcedureDiagnosis is a mapping of procedure_diagnosis Table.
*/
public class ProcedureDiagnosisValidator implements Validator
// extends+ 


// extends- 
{
	public boolean supports(Class clazz) {
		return ProcedureDiagnosisForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {

	}

// class+ 

// class- 
}
