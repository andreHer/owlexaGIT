package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.ClaimForm;

// imports- 

/**
 * Claim is a mapping of claim Table.
 */
public class ClaimValidator implements Validator
// extends+

// extends-
{
	public boolean supports(Class clazz) {
		return ClaimForm.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "claimId",
				"CLAIM_ID_REQUIRED", "claimId is required");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "claimTypeId",
				"CLAIM_TYPE_ID_REQUIRED", "claimTypeId is required");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "memberId",
				"MEMBER_ID_REQUIRED", "memberId is required");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "caseCategoryId",
				"CASE_CATEGORY_ID_REQUIRED", "caseCategoryId is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "admissionDate",
				"ADMISSION_DATE_REQUIRED", "admissionDate is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dischargeDate",
				"DISCHARGE_DATE_REQUIRED", "dischargeDate is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "diagnosisId",
				"DISCHARGE_DATE_REQUIRED", "diagnosisId is required");
		//Created By Andre
		
		/*
		 * harus dipertimbangkan apakah bagian Provider sudah input list dokter atau belum
		 * dan juga jika Reimbursement tidak diperlukan 
		 */
		/**
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "doctorName",
				"MEMBER_ID_REQUIRED", "doctorName is required");
		*/
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "providerName",
				"MEMBER_ID_REQUIRED", "providerName is required");
	}

}
