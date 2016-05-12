
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * QuotationProduct is a mapping of quotation_product Table.
*/
public class QuotationProductValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return QuotationProductForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"quotationProductId","QUOTATION_PRODUCT_ID_REQUIRED","quotationProductId is required");
																																																																																																																														
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"productId","PRODUCT_ID_REQUIRED",
	"productId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"quotationId","QUOTATION_ID_REQUIRED",
	"quotationId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
