
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * PolicyMedicine is a mapping of policy_medicine Table.
*/
public class PolicyMedicineValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return PolicyMedicineForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"policyMedicineId","POLICY_MEDICINE_ID_REQUIRED","policyMedicineId is required");
																																																																																																																																										
// foreign affairs
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"medicineId","MEDICINE_ID_REQUIRED",
	"medicineId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"policyId","POLICY_ID_REQUIRED",
	"policyId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
