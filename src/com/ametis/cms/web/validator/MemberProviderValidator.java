
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 


/**
 * MemberProvider is a mapping of member_provider Table.
*/
public class MemberProviderValidator implements Validator
// extends+ 

// extends- 

{
	public boolean supports(Class clazz) {
		return MemberProviderForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"memberProviderId","MEMBER_PROVIDER_ID_REQUIRED","memberProviderId is required");
																																																																																																																																																																																																						


	}

 

}
