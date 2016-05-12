
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * DiagnosisMedicine is a mapping of diagnosis_medicine Table.
*/
public class DiagnosisMedicineValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return DiagnosisMedicineForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {

	}

// class+ 

// class- 
}
