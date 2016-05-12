package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.ClaimForm;
import com.ametis.cms.web.form.MemberRKIForm;

// imports- 

/**
 * Claim is a mapping of claim Table.
 */
public class MemberRKIValidator implements Validator
// extends+

// extends-
{
	public boolean supports(Class clazz) {
		return MemberRKIForm.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cardNumber",
				"CARD_NUMBER_REQUIRED", "Card Number Data Is Required");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "providerName",
				"PROVIDER_NAME_REQUIRED", "Provider Data Is Required");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "caseCategoryId",
				"CASE_CATEGORY_ID_REQUIRED", "Service Category Is Required");

		// -- foreign affairs end

	}

	// class+

	// class-
}
