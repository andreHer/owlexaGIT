
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * ClaimReceiving is a mapping of claim_receiving Table.
*/
public class ClaimReceivingValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ClaimReceivingForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"claimReceivingId","CLAIM_RECEIVING_ID_REQUIRED","claimReceivingId is required");
																			
	ValidationUtils.rejectIfEmptyOrWhitespace
				(errors,"providerId","PROVIDER_ID_REQUIRED","providerId is required");			

	}

// class+ 

// class- 
}
