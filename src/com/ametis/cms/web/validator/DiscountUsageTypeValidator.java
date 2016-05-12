
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.DiscountUsageTypeForm;

// imports- 

/**
 * DiscountUsageType is a mapping of discount_usage_type Table.
*/
public class DiscountUsageTypeValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return DiscountUsageTypeForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"discountUsageTypeId","DISCOUNT_USAGE_TYPE_ID_REQUIRED","discountUsageTypeId is required");
																																
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
