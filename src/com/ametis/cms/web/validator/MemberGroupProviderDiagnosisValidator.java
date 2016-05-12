
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 


/**
 * MemberGroupProviderDiagnosis is a mapping of member_group_provider_diagnosis Table.
*/
public class MemberGroupProviderDiagnosisValidator implements Validator
// extends+ 

// extends- 

{
	public boolean supports(Class clazz) {
		return MemberGroupProviderDiagnosisForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"memberGroupProviderDiagnosisId","MEMBER_GROUP_PROVIDER_DIAGNOSIS_ID_REQUIRED","memberGroupProviderDiagnosisId is required");
																																						ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"createdTime","CREATED_TIME_REQUIRED","createdTime is required");
																										ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"modifiedTime","MODIFIED_TIME_REQUIRED","modifiedTime is required");
																										ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"deletedTime","DELETED_TIME_REQUIRED","deletedTime is required");
																														
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"diagnosisId","DIAGNOSIS_ID_REQUIRED",
	"diagnosisId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"memberGroupProviderId","MEMBER_GROUP_PROVIDER_ID_REQUIRED",
	"memberGroupProviderId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 

}
