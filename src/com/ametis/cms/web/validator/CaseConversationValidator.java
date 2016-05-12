
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.CaseConversationForm;

// imports- 

/**
 * CaseConversation is a mapping of case_conversation Table.
*/
public class CaseConversationValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return CaseConversationForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
		System.out.println ("OBJ : " + obj);
		System.out.println ("ERROR : " + errors);
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"conversationDescription","CASE_ID_REQUIRED",
			"Description is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"conversationSubject","CASE_ID_REQUIRED",
			"Subject is required");
	}

// class+ 

// class- 
}
