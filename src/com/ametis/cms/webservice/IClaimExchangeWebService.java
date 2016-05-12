package com.ametis.cms.webservice;

import java.util.Collection;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceClient;

import com.ametis.cms.dto.ClaimExchangeDto;

@WebService (targetNamespace="http://ametis.co.id/services/ClaimWebService")
//@WebServiceClient(name = "MobileApplicationWebService", targetNamespace = "http://webservice.cms.ametis.com", wsdlLocation = "http://localhost:8080/services/MobileApplicationWebService?wsdl")
public interface IClaimExchangeWebService {

	@WebMethod
	public Collection<ClaimExchangeDto> getClaimExchange (Integer claimId, String username, String password) throws Exception;
}
