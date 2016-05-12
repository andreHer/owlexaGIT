package com.ametis.cms.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.ametis.cms.dto.EDCTransactionLogDto;

@WebService (targetNamespace="http://ametis.co.id/services/EDCTransactionLogWebService")
public interface IEDCTransactionLogWebService {

	@WebMethod
	public void logActivity (EDCTransactionLogDto log) ;
}
