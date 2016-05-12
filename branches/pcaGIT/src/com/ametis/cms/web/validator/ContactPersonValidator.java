
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 


/**
 * ContactPerson is a mapping of contact_person Table.
*/
public class ContactPersonValidator implements Validator
// extends+ 

// extends- 

{
	public boolean supports(Class clazz) {
		return ContactPersonForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"contactPersonId","CONTACT_PERSON_ID_REQUIRED","contactPersonId is required");
														ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"name","NAME_REQUIRED","name is required");
							

	}

// class+ 

// class- 

}
