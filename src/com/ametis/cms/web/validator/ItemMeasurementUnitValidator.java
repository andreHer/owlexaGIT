
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.ItemMeasurementUnitForm;

// imports- 

/**
 * ItemMeasurementUnit is a mapping of item_measurement_unit Table.
*/
public class ItemMeasurementUnitValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ItemMeasurementUnitForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"itemMeasurementUnitId","ITEM_MEASUREMENT_UNIT_ID_REQUIRED","itemMeasurementUnitId is required");
																																																																																																						
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"itemCategoryId","ITEM_CATEGORY_ID_REQUIRED",
	"itemCategoryId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"measurementUnitId","MEASUREMENT_UNIT_ID_REQUIRED",
	"measurementUnitId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
