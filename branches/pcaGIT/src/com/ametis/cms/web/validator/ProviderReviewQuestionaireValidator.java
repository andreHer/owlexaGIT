
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 


/**
 * ProviderReviewQuestionaire is a mapping of provider_review_questionaire Table.
*/
public class ProviderReviewQuestionaireValidator implements Validator
// extends+ 

// extends- 

{
	public boolean supports(Class clazz) {
		return ProviderReviewQuestionaireForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"providerReviewQuestionaireId","PROVIDER_REVIEW_QUESTIONAIRE_ID_REQUIRED","providerReviewQuestionaireId is required");
																																																		ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"createdTime","CREATED_TIME_REQUIRED","createdTime is required");
																										ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"deletedTime","DELETED_TIME_REQUIRED","deletedTime is required");
																																				ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"modifiedTime","MODIFIED_TIME_REQUIRED","modifiedTime is required");
																																
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 

}
