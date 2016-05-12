
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * PaymentTransactionLog is a mapping of payment_transaction_log Table.
*/
public class PaymentTransactionLogValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return PaymentTransactionLogForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"idProses","ID_PROSES_REQUIRED","idProses is required");
																																																																																																																				
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
