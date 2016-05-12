
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * ExchangeRate is a mapping of exchange_rate Table.
*/
public class ExchangeRateValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ExchangeRateForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"exchangeRateId","EXCHANGE_RATE_ID_REQUIRED","exchangeRateId is required");
		// -- foreign affairs end

	}

// class+ 

// class- 
}
