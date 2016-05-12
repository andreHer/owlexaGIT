
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * CardPrinting is a mapping of card_printing Table.
*/
public class CardPrintingValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return CardPrintingForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"cardPrintingId","CARD_PRINTING_ID_REQUIRED","cardPrintingId is required");
																																																																				ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"createdTime","CREATED_TIME_REQUIRED","createdTime is required");
																																																																														
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"clientId","CLIENT_ID_REQUIRED",
	"clientId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
