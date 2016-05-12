
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.ClientForm;

// imports- 

/**
 * Client is a mapping of client Table.
*/
public class ClientValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ClientForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"clientId","CLIENT_ID_REQUIRED","clientId is required");
																																																																																																																																																																																																																																																																																																																								
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"clientCategoryId","CLIENT_CATEGORY_ID_REQUIRED",
	"clientCategoryId is required");
			// -- foreign affairs end
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"clientName","CLIENT_NAME_ID_REQUIRED",
			"Client Name is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"clientNumber","CLIENT_NUMBER_REQUIRED",
			"Client Number is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"clientCode","CLIENT_CODE_REQUIRED",
			"Client Code is required");
					// -- foreign affairs end
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"registrationDate","REGISTRATION_DATE_REQUIRED",
			"Registration Date is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"expireDate","EXPIRE_DATE_REQUIRED",
			"Expire Date is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"clientBankAccount","BANK_ACCOUNT_REQUIRED",
			"Bank Account is required");
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"accountName","ACCOUNT_NAME_REQUIRED",
			"Account Name is required");
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"bankName","BANK_NAME_REQUIRED",
			"Bank Name is required");

	}

// class+ 

// class- 
}
