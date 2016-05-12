
package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 


/**
 * ClientContractItem is a mapping of client_contract_item Table.
*/
public class ClientContractItemValidator implements Validator
// extends+ 

// extends- 

{
	public boolean supports(Class clazz) {
		return ClientContractItemForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"clientContractItemId","CLIENT_CONTRACT_ITEM_ID_REQUIRED","clientContractItemId is required");
																	
	
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"clientContractId","CLIENT_CONTRACT_ID_REQUIRED",
	"clientContractId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"itemId","ITEM_ID_REQUIRED",
	"itemId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 

}
