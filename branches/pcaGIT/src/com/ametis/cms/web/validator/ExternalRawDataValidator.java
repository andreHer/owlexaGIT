
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * ExternalRawData is a mapping of external_raw_data Table.
*/
public class ExternalRawDataValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ExternalRawDataForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"id","ID_REQUIRED","id is required");
																																						
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"templateId","TEMPLATE_ID_REQUIRED",
	"templateId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
