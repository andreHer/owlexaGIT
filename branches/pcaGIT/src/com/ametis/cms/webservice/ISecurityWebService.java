package com.ametis.cms.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService (targetNamespace="http://ametis.co.id/services/SecurityWebService")

public interface ISecurityWebService {


	@WebMethod
	public String getToken(String plainURL);
	
	
	
}
