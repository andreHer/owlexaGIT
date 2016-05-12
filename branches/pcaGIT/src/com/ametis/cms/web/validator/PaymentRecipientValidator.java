
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.PaymentRecipientForm;

// imports- 

/**
 * PaymentRecipient is a mapping of payment_recipient Table.
*/
public class PaymentRecipientValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return PaymentRecipientForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"paymentRecipientId","PAYMENT_RECIPIENT_ID_REQUIRED","paymentRecipientId is required");
																																
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
