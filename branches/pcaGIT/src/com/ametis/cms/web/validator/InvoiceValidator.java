
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.InvoiceForm;

// imports- 

/**
 * Invoice is a mapping of invoice Table.
*/
public class InvoiceValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return InvoiceForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"invoiceId","INVOICE_ID_REQUIRED","invoiceId is required");
																																																																																																																																																																														
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
