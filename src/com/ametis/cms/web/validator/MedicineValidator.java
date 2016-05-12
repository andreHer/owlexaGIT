
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * Medicine is a mapping of medicine Table.
*/
public class MedicineValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return MedicineForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"medicineId","MEDICINE_ID_REQUIRED","medicineId is required");
																																																																																																																																																																																																																		
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
