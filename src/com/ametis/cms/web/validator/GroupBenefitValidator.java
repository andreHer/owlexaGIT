
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.GroupBenefitForm;

// imports- 

/**
 * GroupBenefit is a mapping of group_benefit Table.
*/
public class GroupBenefitValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return GroupBenefitForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"groupBenefitId","GROUP_BENEFIT_ID_REQUIRED","groupBenefitId is required");
																																																																																																																														
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"memberGroupId","MEMBER_GROUP_ID_REQUIRED",
	"memberGroupId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"itemCategoryId","ITEM_CATEGORY_ID_REQUIRED",
	"itemCategoryId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
