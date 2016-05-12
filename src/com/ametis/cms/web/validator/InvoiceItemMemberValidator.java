package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.InvoiceItemMemberForm;

public class InvoiceItemMemberValidator  implements Validator {

	@Override
	public boolean supports(Class clazz) {
		// TODO Auto-generated method stub
		return InvoiceItemMemberForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
		
	}

}
