
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 


/**
 * ProviderDoctor is a mapping of provider_doctor Table.
*/
public class ProviderDoctorValidator implements Validator
// extends+ 

// extends- 

{
	public boolean supports(Class clazz) {
		return ProviderDoctorForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
	

	}

// class+ 

// class- 

}
