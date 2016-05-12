
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.ActivityLogForm;

// imports- 

/**
 * ActivityLog is a mapping of activity_log Table.
*/
public class ActivityLogValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ActivityLogForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"activityLogId","ACTIVITY_LOG_ID_REQUIRED","activityLogId is required");
																																																																				
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
