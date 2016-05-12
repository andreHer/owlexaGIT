
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * Quotation is a mapping of quotation Table.
*/
public class QuotationValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return QuotationForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"quotationId","QUOTATION_ID_REQUIRED","quotationId is required");
																																																																																																																																																																																																																																																																																																																																																																																																		
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"brokerId","BROKER_ID_REQUIRED",
	"brokerId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"clientId","CLIENT_ID_REQUIRED",
	"clientId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"memberGroupId","MEMBER_GROUP_ID_REQUIRED",
	"memberGroupId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"currencyId","CURRENCY_ID_REQUIRED",
	"currencyId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"branchId","BRANCH_ID_REQUIRED",
	"branchId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"brokerId","BROKER_ID_REQUIRED",
	"brokerId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
