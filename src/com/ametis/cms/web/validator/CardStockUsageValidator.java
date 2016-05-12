
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * CardStockUsage is a mapping of card_stock_usage Table.
*/
public class CardStockUsageValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return CardStockUsageForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"id","ID_REQUIRED","id is required");
	}

// class+ 

// class- 
}
