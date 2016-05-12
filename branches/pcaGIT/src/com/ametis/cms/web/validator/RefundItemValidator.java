
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * RefundItem is a mapping of refund_item Table.
*/
public class RefundItemValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return RefundItemForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"refundItemId","REFUND_ITEM_ID_REQUIRED","refundItemId is required");
																																																																																																																																																												
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"memberImportId","MEMBER_IMPORT_ID_REQUIRED",
	"memberImportId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"refundId","REFUND_ID_REQUIRED",
	"refundId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"memberId","MEMBER_ID_REQUIRED",
	"memberId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
