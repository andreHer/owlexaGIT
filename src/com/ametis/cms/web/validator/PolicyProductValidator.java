
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 

/**
 * PolicyProduct is a mapping of policy_product Table.
*/
public class PolicyProductValidator implements Validator
{
	public boolean supports(Class clazz) {
		return PolicyProductForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {}
 
}
