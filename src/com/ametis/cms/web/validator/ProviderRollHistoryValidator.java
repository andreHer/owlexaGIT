
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * ProviderRollHistory is a mapping of provider_roll_history Table.
*/
public class ProviderRollHistoryValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ProviderRollHistoryForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"providerRollHistoryId","PROVIDER_ROLL_HISTORY_ID_REQUIRED","providerRollHistoryId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"deliveryDate","DELIVERY_DATE_REQUIRED",
			"Delivery Date is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"rollStockAmount","ROLL_STOCK_REQUIRED",
			"Roll Paper Stock Amount is required");
																																																																																																																														
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
