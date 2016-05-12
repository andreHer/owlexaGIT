
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.ProductForm;

// imports- 

/**
 * Product is a mapping of product Table.
*/
public class ProductValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ProductForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"productId","PRODUCT_ID_REQUIRED","productId is required");
																																																																																																																																																																																				
// foreign affairs
	ValidationUtils.rejectIfEmptyOrWhitespace
	(errors,"productName","PRODUCT_NAME_REQUIRED",
		"Product Name is required");
	
	ValidationUtils.rejectIfEmptyOrWhitespace
	(errors,"productCode","PRODUCT_CODE_REQUIRED",
		"Product Code is required");
	
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"policyId","POLICY_ID_REQUIRED",
	"Policy Number is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"productLimitType","PRODUCT_LIMIT_TYPE_REQUIRED",
	"productLimitType is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"productType","PRODUCT_TYPE_REQUIRED",
	"productType is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
