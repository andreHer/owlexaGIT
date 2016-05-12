
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.GroupPackageForm;

// imports- 

/**
 * GroupPackage is a mapping of group_package Table.
*/
public class GroupPackageValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return GroupPackageForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"groupPackageId","GROUP_PACKAGE_ID_REQUIRED","groupPackageId is required");
																																																																																																												
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"groupId","GROUP_ID_REQUIRED",
	"groupId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"memberGroupId","MEMBER_GROUP_ID_REQUIRED",
	"memberGroupId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"packageId","PACKAGE_ID_REQUIRED",
	"packageId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
