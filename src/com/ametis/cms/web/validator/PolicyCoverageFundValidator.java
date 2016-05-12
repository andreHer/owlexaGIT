
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * PolicyCoverageFund is a mapping of policy_coverage_fund Table.
*/
public class PolicyCoverageFundValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return PolicyCoverageFundForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"policyCoverageFundId","POLICY_COVERAGE_FUND_ID_REQUIRED","policyCoverageFundId is required");
																						
																																																																																																						
// foreign affairs
		// -- foreign affairs end

	}

// class+ 

// class- 
}
