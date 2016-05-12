
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * ProviderSetMapping is a mapping of provider_set_mapping Table.
*/
public class ProviderSetMappingValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ProviderSetMappingForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"providerSetMappingId","PROVIDER_SET_MAPPING_ID_REQUIRED","providerSetMappingId is required");
																																																																																																																				ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"createdTime","CREATED_TIME_REQUIRED","createdTime is required");
																										ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"modifiedTime","MODIFIED_TIME_REQUIRED","modifiedTime is required");
																										ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"deletedTime","DELETED_TIME_REQUIRED","deletedTime is required");
																														
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"providerSetId","PROVIDER_SET_ID_REQUIRED",
	"providerSetId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
