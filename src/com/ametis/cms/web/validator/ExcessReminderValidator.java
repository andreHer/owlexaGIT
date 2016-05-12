
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.ExcessReminderForm;

// imports- 

/**
 * ExcessReminder is a mapping of excess_reminder Table.
*/
public class ExcessReminderValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ExcessReminderForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"excessReminderId","EXCESS_REMINDER_ID_REQUIRED","excessReminderId is required");
																																																																																																																																																						
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
