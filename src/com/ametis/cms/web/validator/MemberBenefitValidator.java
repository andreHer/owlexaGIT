
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.MemberBenefitForm;

// imports- 

/**
 * MemberBenefit is a mapping of member_benefit Table.
*/
public class MemberBenefitValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return MemberBenefitForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"memberBenefitId","MEMBER_BENEFIT_ID_REQUIRED","memberBenefitId is required");
																																																																																																																		
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"memberId","MEMBER_ID_REQUIRED",
	"memberId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"itemCategoryId","ITEM_CATEGORY_ID_REQUIRED",
	"itemCategoryId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
