
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.ProductTypeForm;

// imports- 

/**
 * ProductType is a mapping of product_type Table.
*/
public class ProductTypeValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ProductTypeForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"productTypeId","PRODUCT_TYPE_ID_REQUIRED","productTypeId is required");
																																
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
