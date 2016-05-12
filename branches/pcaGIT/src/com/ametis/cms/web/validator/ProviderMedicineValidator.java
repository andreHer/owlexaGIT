
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * ProviderMedicine is a mapping of provider_medicine Table.
*/
public class ProviderMedicineValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ProviderMedicineForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
							
			// -- foreign affairs end

	}

// class+ 

// class- 
}
