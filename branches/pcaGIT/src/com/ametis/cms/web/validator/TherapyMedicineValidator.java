
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 


/**
 * TherapyMedicine is a mapping of therapy_medicine Table.
*/
public class TherapyMedicineValidator implements Validator
// extends+ 

// extends- 

{
	public boolean supports(Class clazz) {
		return TherapyMedicineForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"therapyMedicineId","THERAPY_MEDICINE_ID_REQUIRED","therapyMedicineId is required");
																																																																																																																																																																		
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 

}
