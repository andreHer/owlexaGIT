
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * UserType is a mapping of user_type Table.
*/
public class UserTypeValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return UserTypeForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"userTypeId","USER_TYPE_ID_REQUIRED","userTypeId is required");
																																																																																				ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"deletedTime","DELETED_TIME_REQUIRED","deletedTime is required");
														ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"modifiedTime","MODIFIED_TIME_REQUIRED","modifiedTime is required");
																				
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
