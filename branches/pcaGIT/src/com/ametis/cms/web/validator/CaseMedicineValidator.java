
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * CaseMedicine is a mapping of case_medicine Table.
*/
public class CaseMedicineValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return CaseMedicineForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"caseMedicineId","CASE_MEDICINE_ID_REQUIRED","caseMedicineId is required");
																																																																																																																																																																		
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
