
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 


/**
 * MedicineFactory is a mapping of medicine_factory Table.
*/
public class MedicineFactoryValidator implements Validator
// extends+ 

// extends- 

{
	public boolean supports(Class clazz) {
		return MedicineFactoryForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"medicineFactoryId","MEDICINE_FACTORY_ID_REQUIRED","medicineFactoryId is required");
																																																		ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"createdTime","CREATED_TIME_REQUIRED","createdTime is required");
																																																																														
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 

}
