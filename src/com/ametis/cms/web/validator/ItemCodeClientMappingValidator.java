
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * ItemCodeClientMapping is a mapping of item_code_client_mapping Table.
*/
public class ItemCodeClientMappingValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ItemCodeClientMappingForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {

	}

// class+ 

// class- 
}
