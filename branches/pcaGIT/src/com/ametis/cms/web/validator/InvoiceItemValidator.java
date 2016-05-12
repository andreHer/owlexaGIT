
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.InvoiceItemForm;

// imports- 

/**
 * InvoiceItem is a mapping of invoice_item Table.
*/
public class InvoiceItemValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return InvoiceItemForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"invoiceItemId","INVOICE_ITEM_ID_REQUIRED","invoiceItemId is required");
																																																																																																																														
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"itemId","ITEM_ID_REQUIRED",
	"itemId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"invoiceId","INVOICE_ID_REQUIRED",
	"invoiceId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
