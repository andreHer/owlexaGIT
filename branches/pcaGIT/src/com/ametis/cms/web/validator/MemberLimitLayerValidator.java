
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * MemberLimitLayer is a mapping of member_limit_layer Table.
*/
public class MemberLimitLayerValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return MemberLimitLayerForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"memberLimitLayerId","MEMBER_LIMIT_LAYER_ID_REQUIRED","memberLimitLayerId is required");
																																																																																																								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"createdTime","CREATED_TIME_REQUIRED","createdTime is required");
																										ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"modifiedTime","MODIFIED_TIME_REQUIRED","modifiedTime is required");
																										ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"deletedTime","DELETED_TIME_REQUIRED","deletedTime is required");
																														
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"productLimitLayerId","PRODUCT_LIMIT_LAYER_ID_REQUIRED",
	"productLimitLayerId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"memberId","MEMBER_ID_REQUIRED",
	"memberId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"productId","PRODUCT_ID_REQUIRED",
	"productId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
