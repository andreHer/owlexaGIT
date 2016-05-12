
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.ItemCategoryForm;

// imports- 

/**
 * ItemCategory is a mapping of item_category Table.
*/
public class ItemCategoryValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ItemCategoryForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"itemCategoryId","ITEM_CATEGORY_ID_REQUIRED","itemCategoryId is required");
								
		ValidationUtils.rejectIfEmptyOrWhitespace
								 (errors,"itemCategoryName","ITEM_CATEGORY_NAME_REQUIRED","Item Category Name is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		 (errors,"itemCategoryCode","ITEM_CATEGORY_CODE_REQUIRED","Item Category Code is required");
																																																																																																																														
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
