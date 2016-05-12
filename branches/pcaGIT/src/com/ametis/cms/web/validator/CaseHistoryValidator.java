
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * CaseHistory is a mapping of case_history Table.
*/
public class CaseHistoryValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return CaseHistoryForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"caseHistoryId","CASE_HISTORY_ID_REQUIRED","caseHistoryId is required");
																										ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"historyTime","HISTORY_TIME_REQUIRED","historyTime is required");
																																																																																																																																										
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"afterStatus","AFTER_STATUS_REQUIRED",
	"afterStatus is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"beforeStatus","BEFORE_STATUS_REQUIRED",
	"beforeStatus is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
