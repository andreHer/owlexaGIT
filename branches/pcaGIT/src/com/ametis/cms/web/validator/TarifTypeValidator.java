
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * TarifType is a mapping of tarif_type Table.
*/
public class TarifTypeValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return TarifTypeForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"tarifTypeId","TARIF_TYPE_ID_REQUIRED","tarifTypeId is required");
																				
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
