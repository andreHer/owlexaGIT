
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;

import org.apache.velocity.texen.util.PropertiesUtil;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 


/**
 * Bank is a mapping of bank Table.
*/
public class BankValidator implements Validator
// extends+ 

// extends- 

{

	private ResourceBundleMessageSource alertProperties;
	
	
	
	public ResourceBundleMessageSource getAlertProperties() {
		return alertProperties;
	}
	public void setAlertProperties(ResourceBundleMessageSource alertProperties) {
		this.alertProperties = alertProperties;
	}
	public boolean supports(Class clazz) {
		return BankForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
			ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"bankId","BANK_ID_REQUIRED","bankId is required");
			
	ValidationUtils.rejectIfEmptyOrWhitespace
	(errors,"bankName","BANK_NAME_REQUIRED",
		"Bank Name is required");	
	
	ValidationUtils.rejectIfEmptyOrWhitespace
	(errors,"swiftCode","SWIFT_CODE_REQUIRED",
		"Swift Code is required");
	
	ValidationUtils.rejectIfEmptyOrWhitespace
	(errors,"bankCode","BANK_CODE_REQUIRED",
		"Clearing Code is required");
																																																								
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 

}
