
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.ExcessChargeItemForm;

// imports- 

/**
 * ExcessChargeItem is a mapping of excess_charge_item Table.
*/
public class ExcessChargeItemValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ExcessChargeItemForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"excessChargeItemId","EXCESS_CHARGE_ITEM_ID_REQUIRED","excessChargeItemId is required");
																																																																																																																														
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
