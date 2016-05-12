
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * PolicyMember is a mapping of policy_member Table.
*/
public class PolicyMemberValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return PolicyMemberForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"policyMemberId","POLICY_MEMBER_ID_REQUIRED","policyMemberId is required");
																																																																																
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"policyId","POLICY_ID_REQUIRED",
	"policyId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"memberId","MEMBER_ID_REQUIRED",
	"memberId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
