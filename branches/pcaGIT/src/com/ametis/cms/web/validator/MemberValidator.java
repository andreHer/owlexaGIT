
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.MemberForm;

// imports- 

/**
 * Member is a mapping of member Table.
*/
public class MemberValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return MemberForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
															
		
	
		
	
			// -- foreign affairs end

	}

// class+ 

// class- 
}
