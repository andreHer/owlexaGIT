
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * ClaimProcedure is a mapping of claim_procedure Table.
*/
public class ClaimProcedureValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ClaimProcedureForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"claimProcedureId","CLAIM_PROCEDURE_ID_REQUIRED","claimProcedureId is required");
																																																																																																																																																																																										
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
