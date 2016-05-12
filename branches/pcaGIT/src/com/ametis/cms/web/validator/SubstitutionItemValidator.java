
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.SubstitutionItemForm;

// imports- 

/**
 * SubstitutionItem is a mapping of substitution_item Table.
*/
public class SubstitutionItemValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return SubstitutionItemForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"substitutionItemId","SUBSTITUTION_ITEM_ID_REQUIRED","substitutionItemId is required");
																																																																																																																																																												
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"costContainmentId","COST_CONTAINMENT_ID_REQUIRED",
	"costContainmentId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
