
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.ProductLimitTypeForm;

// imports- 

/**
 * ProductLimitType is a mapping of product_limit_type Table.
*/
public class ProductLimitTypeValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ProductLimitTypeForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"productLimitTypeId","PRODUCT_LIMIT_TYPE_ID_REQUIRED","productLimitTypeId is required");
																																
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
