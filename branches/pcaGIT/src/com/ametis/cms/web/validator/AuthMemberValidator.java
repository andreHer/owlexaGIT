
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.AuthMemberForm;

// imports- 

/**
 * AuthMember is a mapping of auth_member Table.
*/
public class AuthMemberValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return AuthMemberForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"authMemberId","AUTH_MEMBER_ID_REQUIRED","authMemberId is required");
																																																																																																																																																						
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
