
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.ItemForm;

// imports- 

/**
 * Item is a mapping of item Table.
*/
public class ItemValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ItemForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"itemId","ITEM_ID_REQUIRED","itemId is required");
																																																																																																																																																
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"itemCategoryId","ITEM_CATEGORY_ID_REQUIRED",
	"itemCategoryId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
