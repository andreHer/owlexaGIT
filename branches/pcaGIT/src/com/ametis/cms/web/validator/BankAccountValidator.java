
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 


/**
 * BankAccount is a mapping of bank_account Table.
*/
public class BankAccountValidator implements Validator
// extends+ 

// extends- 

{
	public boolean supports(Class clazz) {
		return BankAccountForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"id","ID_REQUIRED","id is required");
								ValidationUtils.rejectIfEmptyOrWhitespace
								 (errors,"account","ACCOUNT_NUMBER_REQUIRED","Account Number is required");																																																																																	
// foreign affairs
								ValidationUtils.rejectIfEmptyOrWhitespace
								 (errors,"bankId","BANK_NAME_REQUIRED","Bank Name is required");		
		

	}

// class+ 

// class- 

}
