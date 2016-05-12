package com.ametis.cms.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService (targetNamespace="http://iventura.co.id/services/UserWebService")
public interface IUserWebService {

	@WebMethod
	public boolean login (String username, String password) throws Exception;
}
