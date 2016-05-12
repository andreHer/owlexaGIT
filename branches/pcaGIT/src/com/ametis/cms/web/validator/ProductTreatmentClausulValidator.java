
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 


/**
 * ProductTreatmentClausul is a mapping of product_treatment_clausul Table.
*/
public class ProductTreatmentClausulValidator implements Validator
// extends+ 

// extends- 

{
	public boolean supports(Class clazz) {
		return ProductTreatmentClausulForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"productTreatmentClausulId","PRODUCT_TREATMENT_CLAUSUL_ID_REQUIRED","productTreatmentClausulId is required");
																																																																																																																																																																														
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 

}
