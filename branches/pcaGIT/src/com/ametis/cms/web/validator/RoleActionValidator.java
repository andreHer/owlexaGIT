
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.RoleActionForm;

// imports- 

/**
 * RoleAction is a mapping of role_action Table.
*/
public class RoleActionValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return RoleActionForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"roleActionId","ROLE_ACTION_ID_REQUIRED","roleActionId is required");
																																																																																																																				
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"actionId","ACTION_ID_REQUIRED",
	"actionId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"roleId","ROLE_ID_REQUIRED",
	"roleId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
