
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.ProductClausulForm;

// imports- 

/**
 * ProductClausul is a mapping of product_clausul Table.
*/
public class ProductClausulValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ProductClausulForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"clausulName","PRODUCT_CODE_REQUIRED",
			"Clausul Name is required");
	}

// class+ 

// class- 
}
