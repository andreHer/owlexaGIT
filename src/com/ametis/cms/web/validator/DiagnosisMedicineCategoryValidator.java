
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 


/**
 * DiagnosisMedicineCategory is a mapping of diagnosis_medicine_category Table.
*/
public class DiagnosisMedicineCategoryValidator implements Validator
// extends+ 

// extends- 

{
	public boolean supports(Class clazz) {
		return DiagnosisMedicineCategoryForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"diagnosisMedCategoryId","DIAGNOSIS_MED_CATEGORY_ID_REQUIRED","diagnosisMedCategoryId is required");
																																																																																																																																																																		
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 

}
