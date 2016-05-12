
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.MemberPackageForm;

// imports- 

/**
 * MemberPackage is a mapping of member_package Table.
*/
public class MemberPackageValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return MemberPackageForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"memberPackageId","MEMBER_PACKAGE_ID_REQUIRED","memberPackageId is required");
																																																																																																																																																												
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"memberPackageStatus","MEMBER_PACKAGE_STATUS_REQUIRED",
	"memberPackageStatus is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"memberId","MEMBER_ID_REQUIRED",
	"memberId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"packageId","PACKAGE_ID_REQUIRED",
	"packageId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
