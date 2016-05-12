
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.MemberGroupProviderForm;

// imports- 

/**
 * MemberGroupProvider is a mapping of member_group_provider Table.
*/
public class MemberGroupProviderValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return MemberGroupProviderForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"memberGroupProviderId","MEMBER_GROUP_PROVIDER_ID_REQUIRED","memberGroupProviderId is required");
																																																																																																						
// foreign affairs
	}

// class+ 

// class- 
}
