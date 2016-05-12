
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.GroupProductForm;

// imports- 

/**
 * GroupProduct is a mapping of group_product Table.
*/
public class GroupProductValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return GroupProductForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"groupProductId","GROUP_PRODUCT_ID_REQUIRED","groupProductId is required");
																																																																																																												
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"groupId","GROUP_ID_REQUIRED",
	"groupId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"productId","PRODUCT_ID_REQUIRED",
	"productId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"memberGroupId","MEMBER_GROUP_ID_REQUIRED",
	"memberGroupId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
