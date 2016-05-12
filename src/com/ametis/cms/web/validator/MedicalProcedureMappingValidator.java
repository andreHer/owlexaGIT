
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * MedicalProcedureMapping is a mapping of medical_procedure_mapping Table.
*/
public class MedicalProcedureMappingValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return MedicalProcedureMappingForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"medicalProcedureMappingId","MEDICAL_PROCEDURE_MAPPING_ID_REQUIRED","medicalProcedureMappingId is required");
																																																																																																		ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"createdTime","CREATED_TIME_REQUIRED","createdTime is required");
																										ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"modifiedTime","MODIFIED_TIME_REQUIRED","modifiedTime is required");
																										ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"deletedTime","DELETED_TIME_REQUIRED","deletedTime is required");
																														
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
