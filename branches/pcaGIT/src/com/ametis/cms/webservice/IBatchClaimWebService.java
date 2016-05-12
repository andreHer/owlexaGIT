package com.ametis.cms.webservice;

import javax.jws.WebService;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.BatchClaim;
import com.ametis.cms.datamodel.Payment;
import com.ametis.cms.dto.BatchClaimDto;
import com.ametis.cms.dto.PaymentDto;

@WebService (targetNamespace="http://ametis.co.id/services/BatchClaimWebService")
public interface IBatchClaimWebService {

	public String createBatchClaim (BatchClaimDto batchClaim, ActionUser actionUser);
	public String createBatchClaimWithCDV (BatchClaimDto batchClaim, String cdvNumber,String cdvDate, ActionUser actionUser);
	public String createBatchClaimWithPayment (BatchClaimDto batchClaim, PaymentDto payment, ActionUser actionUser);

	public BatchClaim getBatchClaim (String number);
	public BatchClaim getBatchClaimByOtherNumber (String otherNumber);
}
