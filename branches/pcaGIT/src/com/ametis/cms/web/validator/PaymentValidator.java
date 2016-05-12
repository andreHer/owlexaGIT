
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.PaymentForm;

// imports- 

/**
 * Payment is a mapping of payment Table.
*/
public class PaymentValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return PaymentForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"paymentId","PAYMENT_ID_REQUIRED","paymentId is required");
/*
			ValidationUtils.rejectIfEmptyOrWhitespace
			 (errors,"payeeName","PAYMENT_ID_REQUIRED","paymentId is required");

			ValidationUtils.rejectIfEmptyOrWhitespace
			 (errors,"accountNumber","PAYMENT_ID_REQUIRED","paymentId is required");
*/
// foreign affairs

	}

// class+ 

// class- 
}
