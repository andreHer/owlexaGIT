
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 


/**
 * ReportRequest is a mapping of report_request Table.
*/
public class ReportRequestValidator implements Validator
// extends+ 

// extends- 

{
	public boolean supports(Class clazz) {
		return ReportRequestForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
																												


	}

// class+ 

// class- 

}
