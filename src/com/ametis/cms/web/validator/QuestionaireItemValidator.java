
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 


/**
 * QuestionaireItem is a mapping of questionaire_item Table.
*/
public class QuestionaireItemValidator implements Validator
// extends+ 

// extends- 

{
	public boolean supports(Class clazz) {
		return QuestionaireItemForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"questionaireItemId","QUESTIONAIRE_ITEM_ID_REQUIRED","questionaireItemId is required");
																																																		ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"createdTime","CREATED_TIME_REQUIRED","createdTime is required");
																																																																																										
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 

}
