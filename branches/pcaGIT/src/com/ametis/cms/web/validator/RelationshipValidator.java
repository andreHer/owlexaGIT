
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.RelationshipForm;

// imports- 

/**
 * Relationship is a mapping of relationship Table.
*/
public class RelationshipValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return RelationshipForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"relationshipId","RELATIONSHIP_ID_REQUIRED","relationshipId is required");
																																
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
