
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * Holiday is a mapping of holiday Table.
*/
public class HolidayValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return HolidayForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"idHoliday","ID_HOLIDAY_REQUIRED","idHoliday is required");
																																																																																										
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
