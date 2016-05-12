
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.RoleForm;

// imports- 

/**
 * Role is a mapping of role Table.
*/
public class RoleValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return RoleForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"roleId","ROLE_ID_REQUIRED","roleId is required");
								
								ValidationUtils.rejectIfEmptyOrWhitespace
								 (errors,"roleName","ROLE_ID_REQUIRED","Role Name is required");
								
								ValidationUtils.rejectIfEmptyOrWhitespace
								 (errors,"menuTemplateURL","ROLE_ID_REQUIRED","Menu Template is required");
																																																																																																																		
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
