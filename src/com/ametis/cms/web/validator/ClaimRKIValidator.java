package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.ClaimForm;

// imports- 

/**
 * Claim is a mapping of claim Table.
 */
public class ClaimRKIValidator implements Validator
// extends+

// extends-
{
	public boolean supports(Class clazz) {
		return ClaimForm.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "diagnosisId",
				"DIAGNOSIS_ID_REQUIRED", "Diagnosis 1 Information Is Required");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "memberName",
				"MEMBER_ID_REQUIRED", "Member Information Is Required");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "providerId",
				"PROVIDER_ID_REQUIRED", "Provider Is Required");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "caseCategoryId",
				"CASE_CATEGORY_ID_REQUIRED", "Service Category Is Required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "admissionDate",
				"ADMISSION_DATE_REQUIRED", "Admission Date Is Required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dischargeDate",
				"DISCHARGE_DATE_REQUIRED", "Discharge Date Is Required");

		// -- foreign affairs end

	}

	// class+

	// class-
}
