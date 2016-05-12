
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.ProductPackageForm;

// imports- 

/**
 * ProductPackage is a mapping of product_package Table.
*/
public class ProductPackageValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ProductPackageForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"productPackageId","PRODUCT_PACKAGE_ID_REQUIRED","productPackageId is required");
																																																																																																																		
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"productId","PRODUCT_ID_REQUIRED",
	"productId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"packageId","PACKAGE_ID_REQUIRED",
	"packageId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
