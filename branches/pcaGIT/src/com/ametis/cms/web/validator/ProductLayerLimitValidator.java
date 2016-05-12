
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * ProductLayerLimit is a mapping of product_layer_limit Table.
*/
public class ProductLayerLimitValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ProductLayerLimitForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"productId","PRODUCT_ID_REQUIRED",
	"productId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
