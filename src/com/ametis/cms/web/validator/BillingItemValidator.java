
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * BillingItem is a mapping of billing_item Table.
*/
public class BillingItemValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return BillingItemForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"billingItemId","BILLING_ITEM_ID_REQUIRED","billingItemId is required");
																																																																																																																																						ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"createdTime","CREATED_TIME_REQUIRED","createdTime is required");
																																																																																																
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"memberImportId","MEMBER_IMPORT_ID_REQUIRED",
	"memberImportId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"claimId","CLAIM_ID_REQUIRED",
	"claimId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"clientId","CLIENT_ID_REQUIRED",
	"clientId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"memberId","MEMBER_ID_REQUIRED",
	"memberId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"policyId","POLICY_ID_REQUIRED",
	"policyId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
