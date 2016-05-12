
package com.ametis.cms.web.validator;

import java.util.Collection;
import java.util.Vector;

import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.UserForm;

// imports- 

/**
 * User is a mapping of user Table.
*/
public class UserValidator implements Validator
// extends+ 

// extends- 
{
	
	public boolean supports(Class clazz) {
		return UserForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"userId","USER_ID_REQUIRED","userId is required");
																																																																																																																																																																																																																								
// foreign affairs
								
		UserForm userForm = ( UserForm ) obj;
	
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"roleId","ROLE_ID_REQUIRED",
			"roleId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"username","USERNAME_REQUIRED",
			"Username is required");
			// -- foreign affairs end
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"firstName","FIRST_NAME_REQUIRED",
			"First Name is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"telephone","TELEPHONE_REQUIRED",
			"Telephone is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"telephone","TELEPHONE_REQUIRED",
			"Telephone is required");
		
//		ValidationUtils.rejectIfEmptyOrWhitespace
//		(errors,"telephoneExt","TELEPHONE_EXT_REQUIRED",
//			"Telephone & Telephone Extention is required");
		
		if (userForm.getUserType() != null){
			if (!userForm.getUserType().equalsIgnoreCase("2")){
				ValidationUtils.rejectIfEmptyOrWhitespace
				(errors,"institutionName","INSTITUTION_NAME_REQUIRED",
					"Institution Name is required");
			}
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"email","EMAIL_REQUIRED",
			"Email is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"email","EMAIL_REQUIRED",
			"Email is required");
	}

// class+ 

// class- 
}
