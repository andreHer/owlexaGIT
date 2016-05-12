
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 


/**
 * MedicineCategory is a mapping of medicine_category Table.
*/
public class MedicineCategoryValidator implements Validator
// extends+ 

// extends- 

{
	public boolean supports(Class clazz) {
		return MedicineCategoryForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"medicineCategoryId","MEDICINE_CATEGORY_ID_REQUIRED","medicineCategoryId is required");
																																																		ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"createdTime","CREATED_TIME_REQUIRED","createdTime is required");
																										ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"modifiedTime","MODIFIED_TIME_REQUIRED","modifiedTime is required");
																										ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"deletedTime","DELETED_TIME_REQUIRED","deletedTime is required");
																														
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 

}
