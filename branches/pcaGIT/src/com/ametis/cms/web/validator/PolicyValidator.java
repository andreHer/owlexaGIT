
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * Policy is a mapping of policy Table.
*/
public class PolicyValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return PolicyForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
						
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"clientId","CLIENT_ID_REQUIRED",
	"clientId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"memberGroupId","MEMBER_GROUP_ID_REQUIRED",
	"memberGroupId is required");


	}

// class+ 

// class- 
}
