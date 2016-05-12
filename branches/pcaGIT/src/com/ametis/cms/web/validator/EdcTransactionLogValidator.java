
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * EdcTransactionLog is a mapping of edc_transaction_log Table.
*/
public class EdcTransactionLogValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return EdcTransactionLogForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"id","ID_REQUIRED","id is required");
																																																																																												
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
