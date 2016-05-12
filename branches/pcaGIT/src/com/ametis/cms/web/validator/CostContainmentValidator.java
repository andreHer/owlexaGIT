
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.CostContainmentForm;

// imports- 

/**
 * CostContainment is a mapping of cost_containment Table.
*/
public class CostContainmentValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return CostContainmentForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"costContainmentId","COST_CONTAINMENT_ID_REQUIRED","costContainmentId is required");
																																																																																																																																																																								
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"itemName","ITEM_ID_REQUIRED",
	"Item Name is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"substitutionItemName","ITEM_ID_REQUIRED",
			"Item Substitution is required");
			// -- foreign affairs end
		
		

	}

// class+ 

// class- 
}
