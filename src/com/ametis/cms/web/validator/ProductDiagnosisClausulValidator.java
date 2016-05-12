
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 


/**
 * ProductDiagnosisClausul is a mapping of product_diagnosis_clausul Table.
*/
public class ProductDiagnosisClausulValidator implements Validator
// extends+ 

// extends- 

{
	public boolean supports(Class clazz) {
		return ProductDiagnosisClausulForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"productDiagnosisClausulId","PRODUCT_DIAGNOSIS_CLAUSUL_ID_REQUIRED","productDiagnosisClausulId is required");
																																																																																																																																																																																										
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 

}
