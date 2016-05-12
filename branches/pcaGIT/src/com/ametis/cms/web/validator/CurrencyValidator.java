
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.CurrencyForm;

// imports- 

/**
 * Currency is a mapping of currency Table.
*/
public class CurrencyValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return CurrencyForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"currencyId","CURRENCY_ID_REQUIRED","currencyId is required");
																																																																																																																														
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
