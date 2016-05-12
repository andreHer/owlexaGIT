
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 


/**
 * CardType is a mapping of card_type Table.
*/
public class CardTypeValidator implements Validator
// extends+ 

// extends- 

{
	public boolean supports(Class clazz) {
		return CardTypeForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"cardTypeId","CARD_TYPE_ID_REQUIRED","cardTypeId is required");
																																												
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 

}
