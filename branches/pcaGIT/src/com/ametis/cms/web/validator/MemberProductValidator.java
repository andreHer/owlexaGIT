
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.MemberProductForm;

// imports- 

/**
 * MemberProduct is a mapping of member_product Table.
*/
public class MemberProductValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return MemberProductForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"memberProductId","MEMBER_PRODUCT_ID_REQUIRED","memberProductId is required");
																																																																																																																																																												
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"memberProductStatus","MEMBER_PRODUCT_STATUS_REQUIRED",
	"memberProductStatus is required");
		
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
