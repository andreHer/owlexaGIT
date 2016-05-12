
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.BatchClaimForm;

// imports- 

/**
 * BatchClaim is a mapping of batch_claim Table.
*/
public class BatchClaimValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return BatchClaimForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
		
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"batchClaimId","BATCH_CLAIM_ID_REQUIRED","batchClaimId is required");
										
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "claimReceivingId", "CLAIM_RECEIVE_ID_REQUIRED", "claimReceivingId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"clientName","CLIENT_NAME_REQUIRED",
	"clientName is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"batchClaimType","CLAIM_TYPE_REQUIRED",
			"claimType is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"paymentRecipient","PAYMENT_RECIPIENT_REQUIRED",
			"paymentRecipient is required");

		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"batchClaimDate","BATCH_CLAIM_DATE_REQUIRED",
			"batchClaimDate is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"batchDueDate","BATCH_DUE_DATE_REQUIRED",
			"batchDueDate is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"invoiceDate","INVOICE_DATE_REQUIRED",
			"invoiceDate is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"invoiceDate","INVOICE_DATE_REQUIRED",
			"invoiceDate is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"batchClaimInitialValue","BATCH_CLAIM_INITIAL_VALUE_REQUIRED",
			"batchClaimInitialValue is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"totalClaim","TOTAL_CLAIM_REQUIRED",
			"totalClaim is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"claimer","CLAIMER_ID_REQUIRED",
			"claimer is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
		(errors,"invoiceNumber","INVOICE_NUMBER_REQUIRED",
			"invoiceNumber is required");
	}

// class+ 

// class- 
}
