
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.MemberGroupForm;

// imports- 

/**
 * MemberGroup is a mapping of member_group Table.
*/
public class MemberGroupValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return MemberGroupForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"memberGroupId","MEMBER_GROUP_ID_REQUIRED","memberGroupId is required");
																																																																																																																																																																																																																																																																																																																																																																																				
// foreign affairs
	
		MemberGroupForm memForm = (MemberGroupForm) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"groupName","GROUP_NAME_REQUIRED","Group Name is required");
		
		// -- foreign affairs end
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"businessCategoryId","BUSINESS_CATEGORY_REQUIRED","Business Category is required");
//		if(memForm.getBusinessCategoryId().equals("") || memForm.getBusinessCategoryId()==null){
//			errors.rejectValue("businessCategoryId", "businessCategoryId is required");
//		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"clientId","CLIENT_ID_REQUIRED","Client is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"bankAccount","BANK_ACCOUNT_REQUIRED","Bank Account is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"bankAccountName","BANK_ACCOUNT_NAME_REQUIRED","Bank Account Name is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"bank","BANKREQUIRED","Bank is required");
		
	}

// class+ 

// class- 
}
