
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * Broker is a mapping of broker Table.
*/
public class BrokerValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return BrokerForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"brokerId","BROKER_ID_REQUIRED","brokerId is required");
																																																																																																																																																																																																																														
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
