
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.CaseItemForm;

// imports- 

/**
 * CaseItem is a mapping of case_item Table.
*/
public class CaseItemValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return CaseItemForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"caseItemId","CASE_ITEM_ID_REQUIRED","caseItemId is required");
																																																																																																																																																												
// foreign affairs
	
			// -- foreign affairs end

	}

// class+ 

// class- 
}
