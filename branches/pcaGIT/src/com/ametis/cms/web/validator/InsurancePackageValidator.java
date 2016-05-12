
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.InsurancePackageForm;

// imports- 

/**
 * InsurancePackage is a mapping of insurance_package Table.
*/
public class InsurancePackageValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return InsurancePackageForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"packageId","PACKAGE_ID_REQUIRED","packageId is required");
																																																																																																																																																																		
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"clientId","CLIENT_ID_REQUIRED",
	"clientId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"packageType","PACKAGE_TYPE_REQUIRED",
	"packageType is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
